package com.changju.fanlitou.activity.fund;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.changju.fanlitou.R;
import com.changju.fanlitou.adapter.CanBindTwiceAdapter;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.fund.HistoryUnitNav;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zhangming on 2017/7/18.
 */

public class FundIncomeActivity extends BaseActivity {

    private HistoryUnitNav historyUnitNav;
    private List<HistoryUnitNav.DataListBean> dataListBeanList;
    private HistoryUnitNav.DataListTitleBean dataListTitleBean;
    private SimpleAdapter simpleAdapter;
    private int page = 1;
    //loading&error
    private View include;
    private View include_load_error;

    private int fund_id;
    @Bind(R.id.tv_1)
    TextView tv_1;
    @Bind(R.id.tv_2)
    TextView tv_2;
    @Bind(R.id.tv_3)
    TextView tv_3;
    @Bind(R.id.tv_4)
    TextView tv_4;

    @Bind(R.id.rv_income)
    RecyclerView rv_income;

    @Bind(R.id.income_title)
    TextView income_title;

    @Bind(R.id.iv_back_income)
    ImageView iv_back_income;
    @Override
    public void initParams(Bundle params) {
        fund_id = params.getInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_fund_income;
    }

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "基金-历史净值");

        ButterKnife.bind(this);
        iv_back_income.setOnClickListener(this);
        rv_income.setLayoutManager(new WrapContentLinearLayoutManager(this));
        rv_income.setOverScrollMode(View.OVER_SCROLL_NEVER);
        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);
    }

    @Override
    public void doBusiness(Context mContext) {
        OkGo.get(ApiUtils.getFundingHistoryUnitNav(mContext,fund_id,page,20)).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                historyUnitNav = ParseUtils.parseByGson(s,HistoryUnitNav.class);
                dataListBeanList = historyUnitNav.getData_list();
                dataListTitleBean = historyUnitNav.getData_list_title();
                income_title.setText(historyUnitNav.getTitle());
                tv_1.setText(dataListTitleBean.getCol_1());
                tv_2.setText(dataListTitleBean.getCol_2());
                tv_3.setText(dataListTitleBean.getCol_3());
                tv_4.setText(dataListTitleBean.getCol_4());
                if(simpleAdapter == null){
                    simpleAdapter = new SimpleAdapter(dataListBeanList);
                    simpleAdapter.setLoadMoreView(new CustomLoadMoreView());
                    simpleAdapter.bindToRecyclerView(rv_income);
                    simpleAdapter.setEmptyView(R.layout.view_empty);
                    pullToRefresh(simpleAdapter);
                    simpleAdapter.disableLoadMoreIfNotFullPage();
                }else{
                    simpleAdapter.setNewData(dataListBeanList);
                }

                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.GONE);
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                NormalUtils.showToast(FundIncomeActivity.this, R.string.net_error);
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.VISIBLE);
            }
        });

    }

    /**
     * 上拉加载
     *
     * @param adapter 进行上拉加载的adapter
     */
    private void pullToRefresh(final CanBindTwiceAdapter adapter) {
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                final Activity activity = FundIncomeActivity.this;
                OkGo.get(ApiUtils.getFundingHistoryUnitNav(activity,fund_id, page + 1, 20)).execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        page++;
                        historyUnitNav = ParseUtils.parseByGson(s, HistoryUnitNav.class);
                        dataListBeanList = historyUnitNav.getData_list();
                        if (dataListBeanList == null || dataListBeanList.size() < 1) {
                            adapter.loadMoreEnd();
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(500);
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                adapter.setEnableLoadMore(false);
                                            }
                                        });
                                        RecyclerView.LayoutManager manager = rv_income.getLayoutManager();
                                        if (manager == null) return;
                                        if (manager instanceof LinearLayoutManager) {
                                            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) manager;
                                            rv_income.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    if ((linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1)
                                                            != adapter.getItemCount()) {
                                                        adapter.setEnableLoadMore(true);
                                                    }
                                                }
                                            }, 50);
                                        }

                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();
                        } else {
                            adapter.addData(dataListBeanList);
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
        }, rv_income);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_income:
                finish();
                break;
            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                include_load_error.setVisibility(View.GONE);
                doBusiness(this);
                break;
        }

    }

    class SimpleAdapter extends CanBindTwiceAdapter<HistoryUnitNav.DataListBean, BaseViewHolder> {

        public SimpleAdapter( @Nullable List<HistoryUnitNav.DataListBean> data) {
            super(R.layout.fund_detail_listitem, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HistoryUnitNav.DataListBean item) {
            helper.setText(R.id.tv_1,item.getDate())
                    .setText(R.id.tv_2,item.getUnit_nav())
                    .setText(R.id.tv_3,item.getAccum_nav())
                    .setText(R.id.tv_4,item.getDay_gr());

            if(item.getDay_gr().substring(0,1).equals("+")){
                helper.setTextColor(R.id.tv_4,getResources().getColor(R.color.fund_text_red));
            }else if(item.getDay_gr().substring(0,1).equals("-")){
                helper.setTextColor(R.id.tv_4,getResources().getColor(R.color.fund_text_green));
            }else {
                helper.setTextColor(R.id.tv_4,getResources().getColor(R.color.colorBidName));
            }
        }
    }

    public final class CustomLoadMoreView extends LoadMoreView {

        @Override
        public int getLayoutId() {
            return R.layout.view_load_more;
        }

        /**
         * 如果返回true，数据全部加载完毕后会隐藏加载更多
         * 如果返回false，数据全部加载完毕后会显示getLoadEndViewId()布局
         */
        @Override
        public boolean isLoadEndGone() {
            return true;
        }

        @Override
        protected int getLoadingViewId() {
            return R.id.load_more_loading_view;
        }

        @Override
        protected int getLoadFailViewId() {
            return R.id.load_more_load_fail_view;
        }

        /**
         * isLoadEndGone()为true，可以返回0
         * isLoadEndGone()为false，不能返回0
         */
        @Override
        protected int getLoadEndViewId() {
            return R.id.load_more_load_end_view;
        }
    }
}
