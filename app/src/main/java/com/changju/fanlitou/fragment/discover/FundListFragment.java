package com.changju.fanlitou.fragment.discover;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.MainActivity;
import com.changju.fanlitou.activity.bid.BidFixedActivity;
import com.changju.fanlitou.activity.fund.FundDetailActivity;
import com.changju.fanlitou.activity.fund.FundSearchActivity;
import com.changju.fanlitou.adapter.CanBindTwiceAdapter;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.fund.FundList;
import com.changju.fanlitou.ui.dialog.AnimDialog;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.FileUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

import static android.view.Gravity.CENTER;

/**
 * Created by chengww on 2017/5/8.
 */

public class FundListFragment extends LazyFragment implements BaseQuickAdapter.OnItemClickListener {

    private AnimDialog mAnimDialog;
    private FundList fundListbean;
    private List<FundList.ProductsBean> productsBeanList;
    private TabLayout rv_fund_filter;
    private RecyclerView rv_fund_list;
    private FundFilterItemAdapter mFundFilterItemAdapter;
    private FundListItemAdapter mFundListItemAdapter;
    private ArrayList<String> hot_search;
    private String fund_type_code = "all";
    private String values_type_code = "day_price_increase";
    private TextView tv_fund_title;
    private TextView screenBtn;
    private PopupWindow mPopWindow;
    SparseBooleanArray isChecked;
    private LinearLayoutManager linearLayoutManager;

    private SmartRefreshLayout refreshLayout;
    private int page = 1;
    private List<FundList.FundTypeFilterBean> filters;

    private MainActivity activity;

    private boolean isRefresh;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Bundle args = new Bundle();
        args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,
                mFundListItemAdapter.getItem(position).getFund_id());
        args.putString(BidFixedActivity.BID_NAME, mFundListItemAdapter.getItem(position).getFund_name());
        args.putString(FundDetailActivity.FUND_CODE, mFundListItemAdapter.getItem(position).getFund_code());
        activity.startActivity(FundDetailActivity.class, args);
    }

    @Override
    protected void initView() {

        GrowingIO.getInstance().setPageName(this, "发现-基金");

        mAnimDialog = AnimDialog.showDialog(activity);
        mAnimDialog.show();
        rv_fund_filter = (TabLayout) findViewById(R.id.rv_fund_filter);
        rv_fund_list = (RecyclerView) findViewById(R.id.rv_fund_list);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(activity);
        linearLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        rv_fund_list.setLayoutManager(linearLayoutManager2);
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.fund_refresh_layout);
        refreshLayout.setRefreshHeader(new MaterialHeader(activity).setColorSchemeColors(0xfff95353));
        refreshLayout.setEnableHeaderTranslationContent(false);
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                refreshlayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isRefresh = true;
                        page = 1;
                        initData(false);
                    }
                }, 1000);
            }
        });


        tv_fund_title = (TextView) findViewById(R.id.tv_fund_title);
        screenBtn = (TextView) findViewById(R.id.screen_btn_fund_list);

        findViewById(R.id.ib_fund_search).setOnClickListener(this);
        screenBtn.setText("日涨幅");
        screenBtn.setOnClickListener(this);
        isChecked = new SparseBooleanArray();
        isChecked.put(0, true);

        mFundListItemAdapter = new FundListItemAdapter(productsBeanList);
        mFundListItemAdapter.bindToRecyclerView(rv_fund_list);
        mFundListItemAdapter.setOnItemClickListener(FundListFragment.this);
        pullToRefresh(mFundListItemAdapter, rv_fund_list);

        rv_fund_filter.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                dismissPopWindow();
                fund_type_code = fundListbean.getFund_type_filter().get(tab.getPosition()).getCode();
                //点击filter里面内容 下面values_type_code 默认变成日涨幅的列表
                values_type_code = "day_price_increase";
                mAnimDialog.show();
                page = 1;
                isRefresh = false;
                initData(false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void pullToRefresh(final CanBindTwiceAdapter adapter, final RecyclerView mRecyclerView) {
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                OkGo.get(ApiUtils.getFundList(fund_type_code, values_type_code, page + 1, 20, "")).execute(new StringCallback() {

                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        page++;
                        FundList fundListbean = ParseUtils.parseByGson(s, FundList.class);
                        List<FundList.ProductsBean> productsBeanList = fundListbean.getProducts();
                        if (productsBeanList == null || productsBeanList.size() < 1 || !fundListbean.isHas_more()) {
                            adapter.loadMoreEnd();
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    mRecyclerView.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            adapter.setEnableLoadMore(false);
                                        }
                                    }, 500);
                                    RecyclerView.LayoutManager manager = mRecyclerView.getLayoutManager();
                                    if (manager == null) return;
                                    if (manager instanceof LinearLayoutManager) {
                                        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) manager;
                                        mRecyclerView.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                if ((linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1) != adapter.getItemCount()) {
                                                    adapter.removeAllFooterView();
                                                    adapter.setEnableLoadMore(true);

                                                }
                                            }
                                        }, 50);
                                    }
                                }
                            }).start();
                        } else {
                            adapter.addData(productsBeanList);
                            adapter.loadMoreComplete();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        NormalUtils.showToast(activity, R.string.net_error);
                        adapter.loadMoreFail();
                    }
                });
            }
        }, mRecyclerView);


    }

    public void addFooterView(CanBindTwiceAdapter adapter) {
        TextView textView = new TextView(activity);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);
        textView.setTextColor(activity.getResources().getColor(R.color.colorTextThird));
        textView.setText("基金销售服务由上海利得基金销售有限公司提供");
        textView.setTextSize(12);
        textView.setGravity(CENTER);
        textView.setPadding(0, 30, 0, 50);

        adapter.setFooterView(textView);
    }

    @Override
    protected void doBusiness(Context context) {
        isRefresh = false;
        initData(false);
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_fund;
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.ib_fund_search:
                Bundle args = new Bundle();
                args.putStringArrayList(FundSearchActivity.HOT_SEARCH, hot_search);
                Intent i = new Intent(activity, FundSearchActivity.class);
                i.putExtras(args);
                startActivity(i);
                break;
            case R.id.screen_btn_fund_list:
                if (fundListbean != null) {
                    showScreenPopupWindow();
                }
                break;
        }
    }

    /**
     *
     */
    private void showScreenPopupWindow() {
        if (mPopWindow != null && mPopWindow.isShowing()) {
            mPopWindow.dismiss();
            return;
        }
        View contentView = LayoutInflater.from(activity).inflate(R.layout.pupwindow_screen_fund_list, null);
        final int anchorLoc[] = new int[2];
        screenBtn.getLocationOnScreen(anchorLoc);
        final int anchorHeight = screenBtn.getHeight();
        int h = NormalUtils.getScreenHeight(getApplicationContext());
        mPopWindow = new PopupWindow(contentView,
                RecyclerView.LayoutParams.MATCH_PARENT,
                h - anchorLoc[1] - anchorHeight, true);
        mPopWindow.setBackgroundDrawable(null);
        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getApplicationContext()));
        SimplePopAdapter adapter = new SimplePopAdapter(fundListbean.getValues_filter());
        adapter.setChooseText(screenBtn.getText().toString());
        adapter.bindToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TextView text = (TextView) view;
                text.setTextColor(activity.getResources().getColor(R.color.colorTextRed));
                screenBtn.setText(fundListbean.getValues_filter().get(position).getName());
                values_type_code = fundListbean.getValues_filter().get(position).getCode();
                mAnimDialog.show();
                page = 1;
                mFundListItemAdapter.removeAllFooterView();
                isRefresh = false;
                initData(true);
                mPopWindow.dismiss();
            }
        });
        //点击空白处时，隐藏掉pop窗口
        mPopWindow.setFocusable(false);

        mPopWindow.showAtLocation(screenBtn, Gravity.BOTTOM, 0, 0);
        contentView.findViewById(R.id.view1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopWindow.dismiss();
            }
        });
        contentView.findViewById(R.id.view2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopWindow.dismiss();
            }
        });
    }

    class SimplePopAdapter extends CanBindTwiceAdapter<FundList.ValuesFilterBean, BaseViewHolder> {

        private String chooseText = "";

        public void setChooseText(String chooseText) {
            this.chooseText = chooseText;
        }

        public SimplePopAdapter(@Nullable List data) {
            super(R.layout.recycler_fund_list, data);
        }


        @Override
        protected void convert(BaseViewHolder helper, FundList.ValuesFilterBean item) {
            final TextView text = helper.getView(R.id.text);
            text.setText(item.getName());
            if (item.getName().equals(chooseText))
                text.setTextColor(activity.getResources().getColor(R.color.colorTextRed));
            else
                text.setTextColor(activity.getResources().getColor(R.color.colorBidName));
        }
    }

    private void initData(final Boolean isFromPup) {
        OkGo.get(ApiUtils.getFundList(fund_type_code, values_type_code, page, 20, "")).execute(new StringCallback() {

            @Override
            public void onSuccess(String s, Call call, Response response) {
                fundListbean = ParseUtils.parseByGson(s, FundList.class);

                FileUtils.saveData(activity, "FundList", s);

                if (!isRefresh) {
                    bindFilter(isFromPup);
                }

                if (page == 1) {
                    mFundListItemAdapter.setEmptyView(R.layout.view_empty);
                    mFundListItemAdapter.setNewData(productsBeanList);
                } else {
                    mFundListItemAdapter.addData(productsBeanList);
                }

                hot_search = fundListbean.getHot_search();
                if (isRefresh) {
                    refreshLayout.finishRefresh();
                }
                if (mAnimDialog != null && mAnimDialog.isShowing()) {
                    mAnimDialog.dismiss();
                }

                page++;
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                NormalUtils.showToast(activity
                        , R.string.net_error);
                //第一次加载网络错误
                if (productsBeanList == null) {
                    String fundList = FileUtils.getData(activity, "FundList");
                    fundListbean = ParseUtils.parseByGson(fundList, FundList.class);
                    if (fundList != null) {
                        productsBeanList = fundListbean.getProducts();
                        bindFilter(isFromPup);
                    }
                    mFundListItemAdapter.setNewData(productsBeanList);
                    mFundListItemAdapter.setEmptyView(R.layout.view_load_error);
                    mFundListItemAdapter.getEmptyView().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            page = 1;
                            isRefresh = false;
                            initData(false);
                        }
                    });
                }
                if (isRefresh) {
                    refreshLayout.finishRefresh();
                }
                if (mAnimDialog != null && mAnimDialog.isShowing()) {
                    mAnimDialog.dismiss();
                }
            }
        });
    }

    public void bindFilter(Boolean isFromPup) {
        productsBeanList = fundListbean.getProducts();
        //isShow_currency_fund() 是否是货币类型
        //点击右侧pup走到这里就不重新设置基金列表的表头，如果是第一次加载或者点击fitter列表走到这里需要重新设置表头
        if (!isFromPup) {
            if (fundListbean.isShow_currency_fund()) {
                tv_fund_title.setText("万份收益");
                screenBtn.setText("七日年化");
            } else {
                tv_fund_title.setText("单位净值");
                screenBtn.setText("日涨幅");
            }
        }

        if (rv_fund_filter.getTabCount() < 1) {
            filters = fundListbean.getFund_type_filter();
            rv_fund_filter.removeAllTabs();
            for (int i = 0; i < filters.size(); i++) {
                rv_fund_filter.addTab(rv_fund_filter.newTab().setText(filters.get(i).getName()));
            }
        }
    }

    /**
     * 当弹窗mPopWindow不为空且在显示状态时，使mPopWindow消失
     */
    public void dismissPopWindow() {
        if (mPopWindow != null && mPopWindow.isShowing()) {
            mPopWindow.dismiss();
        }
    }

    class FundFilterItemAdapter extends CanBindTwiceAdapter<FundList.FundTypeFilterBean, BaseViewHolder> {


        public FundFilterItemAdapter(List<FundList.FundTypeFilterBean> data) {
            super(R.layout.fund_item_text, data);

        }

        @Override
        protected void convert(final BaseViewHolder helper, final FundList.FundTypeFilterBean item) {
            TextView textView = helper.getView(R.id.tv);
            if (isChecked.get(helper.getAdapterPosition(), false)) {
                textView.setTextColor(activity.getResources().getColor(R.color.fund_text_red));
            } else {
                textView.setTextColor(activity.getResources().getColor(R.color.colorTextSecondary));
            }
            helper.setText(R.id.tv, item.getName());

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismissPopWindow();
                    isChecked.clear();
                    isChecked.put(helper.getAdapterPosition(), true);
                    fund_type_code = fundListbean.getFund_type_filter().get(helper.getAdapterPosition()).getCode();
                    //点击filter里面内容 下面values_type_code 默认变成日涨幅的列表
                    values_type_code = "day_price_increase";
                    FundFilterItemAdapter.this.notifyDataSetChanged();
                    mAnimDialog.show();
                    page = 1;
                    mFundListItemAdapter.removeAllFooterView();
                    isRefresh = false;
                    initData(false);
                }
            });
        }
    }

    class FundListItemAdapter extends CanBindTwiceAdapter<FundList.ProductsBean, BaseViewHolder> {

        public FundListItemAdapter(List<FundList.ProductsBean> data) {
            super(R.layout.recycler_item_discovery_fundlist, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final FundList.ProductsBean item) {

            //仅有日涨幅
            helper.setText(R.id.tv_fund_name, item.getFund_name())
                    .setText(R.id.tv_fund_code, item.getFund_code())
                    .setText(R.id.tv_grand_income_percent, item.getAlt_value())
                    .setText(R.id.tv_latest_value, item.getLatest_value());

            if (item.getAlt_value().substring(0, 1).equals("+")) {
                helper.setTextColor(R.id.tv_grand_income_percent, activity.getResources().getColor(R.color.fund_text_red));
            } else if (item.getAlt_value().substring(0, 1).equals("-")) {
                helper.setTextColor(R.id.tv_grand_income_percent, activity.getResources().getColor(R.color.fund_text_green));
            }
        }
    }
}
