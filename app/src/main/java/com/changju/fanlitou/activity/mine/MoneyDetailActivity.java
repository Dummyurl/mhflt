package com.changju.fanlitou.activity.mine;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.adapter.CanBindTwiceAdapter;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.mine.MoneyDetail;
import com.changju.fanlitou.ui.DrawableAlignedButton;
import com.changju.fanlitou.ui.dialog.AnimDialog;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.changju.fanlitou.util.UserUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zhangming on 2017/6/7.
 */

public class MoneyDetailActivity extends BaseActivity {

    private PopupWindow mPopWindow;

    private RecyclerView recycle_money_detail;

    private SmartRefreshLayout refreshLayout;

    private LinearLayout layout_top_filter;


    //loading&error
    private View include;
    private View include_load_error;

    //页码
    private int page = 1;

    //是否刷新
    private boolean isRefresh;

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_money_detail;
    }

    @Override
    public void initView(View view) {

        UserUtils.checkLogin(this, MoneyDetailActivity.class);

        GrowingIO.getInstance().setPageName(this, "我的-资金明细");

        findViewById(R.id.iv_money_detail_back).setOnClickListener(this);

        mAnimDialog = AnimDialog.showDialog(this);

        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);

        recycle_money_detail = (RecyclerView) findViewById(R.id.recycle_money_detail);
        recycle_money_detail.setLayoutManager(new WrapContentLinearLayoutManager(this));
        sectionAdapter = new SectionAdapter(R.layout.recycler_item_money_detail, null);

        sectionAdapter.bindToRecyclerView(recycle_money_detail);
        sectionAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                isRefresh = false;
                initData(false);
            }
        }, recycle_money_detail);

        sectionAdapter.setEmptyView(R.layout.view_empty);
        TextView tv = (TextView) sectionAdapter.getEmptyView().
                findViewById(R.id.tv_content);
        tv.setText("暂无资金明细");

        layout_top_filter = (LinearLayout) findViewById(R.id.layout_top_filter);

        refreshLayout = (SmartRefreshLayout) findViewById(R.id.money_detail_refresh_layout);
        refreshLayout.setRefreshHeader(new MaterialHeader(this).setColorSchemeColors(0xfff95353));
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
    }

    private SectionAdapter sectionAdapter;

    @Override
    public void doBusiness(Context mContext) {
        isRefresh = false;
        initData(true);

    }

    @Override
    public void widgetClick(View v) {
        if (v instanceof DrawableAlignedButton) {
            TextTag tag = (TextTag) v.getTag();
            if (tag.isPop()) {
                List<MoneyDetail.FilterListBean.ContentBean> content =
                        filters.get(tag.getIndex()).getContent();
                showPopupWindow(v, content, tag.getIndex());
            } else {
                if (mPopWindow != null && mPopWindow.isShowing())
                    mPopWindow.dismiss();
                int pos = tag.getIndex();


                //还原
                for (int i = 0; i < layout_top_filter.getChildCount(); i++) {
                    if (pos == i)
                        continue;
                    DrawableAlignedButton btn = (DrawableAlignedButton)
                            layout_top_filter.getChildAt(i);
                    TextTag btn_tag = (TextTag) btn.getTag();
                    if (btn_tag.isPop()) {
                        btn.setText(filters.get(btn_tag.getIndex()).getName());
                    }
                }
                if (pos != filterIndex) {
                    content_id = "";
                    updateTopFilter(pos, filterIndex);
                }
            }
        } else {
            switch (v.getId()) {
                case R.id.iv_money_detail_back:
                    finish();
                    break;
                case R.id.include_load_error:
                    include.setVisibility(View.VISIBLE);
                    include_load_error.setVisibility(View.GONE);
                    isRefresh = false;
                    initData(true);
                    break;
            }
        }

    }

    private void updateTopFilter(int newPos, int oldPos) {
        DrawableAlignedButton newBtn = (DrawableAlignedButton)
                layout_top_filter.getChildAt(newPos);
        DrawableAlignedButton oldBtn = (DrawableAlignedButton)
                layout_top_filter.getChildAt(oldPos);
        oldBtn.setTextColor(R.color.colorBidName);
        newBtn.setTextColor(R.color.colorTextRed);
        filterIndex = newPos;
        //刷新
        filter_id = filters.get(newPos).getId();
        page = 1;
        mAnimDialog.show();
        isRefresh = false;
        initData(true);
    }

    public class SectionAdapter extends BaseQuickAdapter<MoneyDetail.TransactionBean, BaseViewHolder> {

        public SectionAdapter(int layoutResId, List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MoneyDetail.TransactionBean item) {
            helper.setText(R.id.tv_money_detail_time, item.getDate())
                    .setText(R.id.tv_money_detail_status, item.getType_str())
                    .setText(R.id.tv_balance, "（可用余额：" + item.getBalance() + "元）")
                    .setText(R.id.tv_desc, item.getDesc());

            ImageView logo = helper.getView(R.id.img);
            Glide.with(MoneyDetailActivity.this.getApplicationContext()).
                    load(item.getImg()).into(logo);

            /**
             * 展示样式
             * red：红色
             * pos：正值
             * nag：负值
             */
            TextView yue = helper.getView(R.id.yue);
            switch (item.getClass_name()) {
                case "red":
                    yue.setTextColor(getResources().getColor(R.color.colorTextRed));
                    yue.setText(item.getAmount() + "元");
                    break;
                case "pos":
                    yue.setTextColor(getResources().getColor(R.color.colorTextRed));
                    yue.setText("+" + item.getAmount() + "元");
                    break;
                case "neg":
                    yue.setTextColor(getResources().getColor(R.color.colorTextGreen));
                    yue.setText("-" + item.getAmount() + "元");
                    break;
                default:
                    yue.setTextColor(getResources().getColor(R.color.colorTextSecondary));
                    yue.setText(item.getAmount() + "元");
                    break;
            }
        }
    }

    //筛选相关
    private String content_id = "";

    private String filter_id = "";

    private List<MoneyDetail.FilterListBean> filters;

    private int filterIndex = 0;

    private AnimDialog mAnimDialog;

    private void initData(boolean isFirst) {
        if (mAnimDialog == null)
            mAnimDialog = AnimDialog.showDialog(this);

        if (page == 1 && !isFirst && !isRefresh)
            mAnimDialog.show();

        final Context context = MoneyDetailActivity.this;
        OkGo.get(ApiUtils.getMoneyDetail(context, page, content_id, filter_id))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        MoneyDetail moneyDetail = ParseUtils.parseByGson(s, MoneyDetail.class);
                        List<MoneyDetail.TransactionBean> transactions = moneyDetail.getTransaction();

                        if (filters == null) {
                            include.setVisibility(View.GONE);
                            include_load_error.setVisibility(View.GONE);
                        }

                        if (page == 1)
                            sectionAdapter.setNewData(transactions);
                        else {
                            if (transactions == null || transactions.size() < 1) {
                                loadMoreEnd(sectionAdapter, recycle_money_detail);
                            } else {
                                sectionAdapter.addData(transactions);
                                sectionAdapter.loadMoreComplete();
                            }
                        }

                        //上方筛选
                        if (filters == null || filters.size() < 1) {
                            filters = moneyDetail.getFilter_list();
                            if (filters == null || filters.size() < 1) {
                                //筛选为空 隐藏
                                layout_top_filter.setVisibility(View.GONE);
                            } else {
                                layout_top_filter.setVisibility(View.VISIBLE);
                                for (int i = 0; i < filters.size(); i++) {
                                    MoneyDetail.FilterListBean filter =
                                            filters.get(i);
                                    List<MoneyDetail.FilterListBean.ContentBean> content =
                                            filter.getContent();

                                    DrawableAlignedButton text = new DrawableAlignedButton(context);
                                    text.setGravity(Gravity.CENTER);
                                    LinearLayout.LayoutParams paras = new LinearLayout.LayoutParams(
                                            0, ViewGroup.LayoutParams.MATCH_PARENT);
                                    paras.weight = 1;
                                    text.setLayoutParams(paras);
                                    text.setText(filter.getName());
                                    if (i == filterIndex) {
                                        text.setTextColor(R.color.colorTextRed);
                                    } else {
                                        text.setTextColor(R.color.colorBidName);
                                    }
                                    text.setOnClickListener(MoneyDetailActivity.this);
                                    layout_top_filter.addView(text);
                                    //没有下拉菜单
                                    if (content == null || content.size() < 1) {
                                        text.setTag(new TextTag(i, false));
                                    } else {
                                        //有下拉菜单
                                        text.setTag(new TextTag(i, true));
                                        text.setDrawableEnd(R.mipmap.ic_down_gray);
                                    }

                                }
                            }
                        }


                        page++;

                        if (mAnimDialog != null && mAnimDialog.isShowing()) {
                            mAnimDialog.dismiss();
                        }

                        if (isRefresh) {
                            refreshLayout.finishRefresh();
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        NormalUtils.showToast(context, R.string.net_error);
                        if (filters == null) {
                            include.setVisibility(View.GONE);
                            include_load_error.setVisibility(View.VISIBLE);
                        }

                        if (page > 1)
                            sectionAdapter.loadMoreFail();

                        if (mAnimDialog != null && mAnimDialog.isShowing()) {
                            mAnimDialog.dismiss();
                        }

                        if (isRefresh) {
                            refreshLayout.finishRefresh();
                        }
                    }
                });
    }


    class TextTag {
        private int index;
        private boolean isPop;

        public TextTag(int index, boolean isPop) {
            this.index = index;
            this.isPop = isPop;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public boolean isPop() {
            return isPop;
        }

        public void setPop(boolean pop) {
            isPop = pop;
        }
    }

    private void showPopupWindow(View v,
                                 List<MoneyDetail.FilterListBean.ContentBean>
                                         contents, final int index) {
        if (mPopWindow != null && mPopWindow.isShowing()) {
            mPopWindow.dismiss();
            return;
        }
        View contentView = LayoutInflater.from(this).inflate(R.layout.pupwindow_test, null);
        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(this));

        int[] location = calculatePopWindowPos(v, layout_top_filter);
        int h = NormalUtils.getScreenHeight(this);
        mPopWindow = new PopupWindow(contentView,
                RecyclerView.LayoutParams.MATCH_PARENT,
                h - location[1], true);
        final DrawableAlignedButton btn = (DrawableAlignedButton) v;
        SimplePopAdapter adapter = new SimplePopAdapter(contents);
        adapter.setChooseText(btn.getText());
        adapter.bindToRecyclerView(recyclerView);
        mPopWindow.setContentView(contentView);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TextView text = (TextView) view;
                text.setTextColor(getResources().getColor(R.color.colorTextRed));
                MoneyDetail.FilterListBean.ContentBean content =
                        filters.get(index).getContent().get(position);
                btn.setText(content.getName());
                //还原
                for (int i = 0; i < layout_top_filter.getChildCount(); i++) {
                    if (index == i)
                        continue;
                    DrawableAlignedButton btn = (DrawableAlignedButton)
                            layout_top_filter.getChildAt(i);
                    TextTag btn_tag = (TextTag) btn.getTag();
                    if (btn_tag.isPop()) {
                        btn.setText(filters.get(btn_tag.getIndex()).getName());
                    }
                }
                content_id = content.getId();
                updateTopFilter(index, filterIndex);
                mPopWindow.dismiss();
            }
        });
        //点击空白处时，隐藏掉pop窗口
        mPopWindow.setFocusable(false);

        mPopWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        contentView.findViewById(R.id.view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopWindow.dismiss();
            }
        });
    }


    /**
     * 计算出来的位置，y方向就在anchorView的上面和下面对齐显示，x方向就是与屏幕右边对齐显示
     * 如果anchorView的位置有变化，就可以适当自己额外加入偏移来修正
     *
     * @param anchorView  呼出window的view
     * @param contentView window的内容布局
     * @return window显示的左上角的xOff, yOff坐标
     */
    private static int[] calculatePopWindowPos(final View anchorView, final View contentView) {
        final int windowPos[] = new int[2];
        final int anchorLoc[] = new int[2];
        // 获取锚点View在屏幕上的左上角坐标位置
        anchorView.getLocationOnScreen(anchorLoc);
        final int anchorHeight = anchorView.getHeight();
        // 获取屏幕的高宽
        final int screenWidth = NormalUtils.getScreenWidth(anchorView.getContext());
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        // 计算contentView的高宽
        final int windowWidth = contentView.getMeasuredWidth();

        windowPos[0] = screenWidth - windowWidth;
        windowPos[1] = anchorLoc[1] + anchorHeight;

        return windowPos;
    }

    class SimplePopAdapter extends CanBindTwiceAdapter<MoneyDetail.FilterListBean.ContentBean, BaseViewHolder> {

        private String chooseText = "";

        public void setChooseText(String chooseText) {
            this.chooseText = chooseText;
        }

        public SimplePopAdapter(@Nullable List data) {
            super(R.layout.recycler_money_detail, data);
        }


        @Override
        protected void convert(BaseViewHolder helper, MoneyDetail.FilterListBean.ContentBean item) {
            final TextView text = helper.getView(R.id.text);
            text.setText(item.getName());
            if (item.getName().equals(chooseText))
                text.setTextColor(getResources().getColor(R.color.colorTextRed));
            else
                text.setTextColor(getResources().getColor(R.color.colorBidName));
        }
    }

    private void loadMoreEnd(final BaseQuickAdapter adapter, final RecyclerView mRecyclerView) {
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
                    RecyclerView.LayoutManager manager = mRecyclerView.getLayoutManager();
                    if (manager == null) return;
                    if (manager instanceof LinearLayoutManager) {
                        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) manager;
                        mRecyclerView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if ((linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1) != adapter.getItemCount()) {
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
    }
}
