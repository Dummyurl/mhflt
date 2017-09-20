package com.changju.fanlitou.activity.classifylist;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.bid.BidFixedActivity;
import com.changju.fanlitou.activity.fund.FundDetailActivity;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.homeclassify.Banner;
import com.changju.fanlitou.bean.homeclassify.HomeFund;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
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
 * Created by Administrator on 2017/5/25.
 */

public class FundActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {

    public static final String FUND = "fund";
    private TextView tv_classify_fund_banner;
    private HomeFund homeFund;
    private List<HomeFund.ResultBean> resultBeanList;
    private TextView tv_fund_1, tv_fund_2, tv_fund_3, tv_fund_4;
    private RecyclerView rv_fund_1, rv_fund_2, rv_fund_3, rv_fund_4;
    private SimpleAdapter adapter1, adapter2, adapter3, adapter4;
    private SmartRefreshLayout refreshLayout;
    //loading&error
    private View include;
    private View include_load_error;

    //是否刷新
    private boolean isRefresh;

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_classify_list_fund;
    }

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "基金二级页");

        findViewById(R.id.iv_back_fund).setOnClickListener(this);

        tv_classify_fund_banner = (TextView) findViewById(R.id.tv_classify_fund_banner);
        tv_fund_1 = (TextView) findViewById(R.id.tv_fund_1);
        tv_fund_2 = (TextView) findViewById(R.id.tv_fund_2);
        tv_fund_3 = (TextView) findViewById(R.id.tv_fund_3);
        tv_fund_4 = (TextView) findViewById(R.id.tv_fund_4);
        rv_fund_1 = (RecyclerView) findViewById(R.id.rv_fund_1);
        rv_fund_2 = (RecyclerView) findViewById(R.id.rv_fund_2);
        rv_fund_3 = (RecyclerView) findViewById(R.id.rv_fund_3);
        rv_fund_4 = (RecyclerView) findViewById(R.id.rv_fund_4);

        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);

        refreshLayout = (SmartRefreshLayout) findViewById(R.id.fund_refresh_layout);
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
                        initData();
                    }
                }, 1000);
            }
        });

    }

    @Override
    public void doBusiness(Context mContext) {
        isRefresh = false;
        initData();
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_fund:
                finish();
                break;
            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                include_load_error.setVisibility(View.GONE);
                doBusiness(this);
                break;
        }

    }

    private void initData() {
        final Context context = FundActivity.this;
        OkGo.get(ApiUtils.getClassifyBanner(context, FUND)).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Banner banner = ParseUtils.parseByGson(s, Banner.class);
                tv_classify_fund_banner.setText(banner.getTitle());
                Glide.with(getApplicationContext()).load(banner.getUrl())
                        .into(new SimpleTarget<GlideDrawable>() {
                            @Override
                            public void onResourceReady(GlideDrawable resource,
                                                        GlideAnimation<? super GlideDrawable> glideAnimation) {
                                tv_classify_fund_banner.setBackground(resource);
                            }
                        });

            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                tv_classify_fund_banner.setBackground(getResources().getDrawable(R.mipmap.ic_launcher));
            }
        });

        OkGo.get(ApiUtils.getHomeFund(context)).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                homeFund = ParseUtils.parseByGson(s, HomeFund.class);
                resultBeanList = homeFund.getResult();

                tv_fund_1.setText(resultBeanList.get(0).getName());
                tv_fund_2.setText(resultBeanList.get(1).getName());
                tv_fund_3.setText(resultBeanList.get(2).getName());
                tv_fund_4.setText(resultBeanList.get(3).getName());

                List<HomeFund.ResultBean.FundListBean> fund_list1 = resultBeanList.get(0).getFund_list();
                List<HomeFund.ResultBean.FundListBean> fund_list2 = resultBeanList.get(1).getFund_list();
                List<HomeFund.ResultBean.FundListBean> fund_list3 = resultBeanList.get(2).getFund_list();
                List<HomeFund.ResultBean.FundListBean> fund_list4 = resultBeanList.get(3).getFund_list();

                rv_fund_1.setNestedScrollingEnabled(false);
                rv_fund_2.setNestedScrollingEnabled(false);
                rv_fund_3.setNestedScrollingEnabled(false);
                rv_fund_4.setNestedScrollingEnabled(false);
                rv_fund_1.setLayoutManager(new WrapContentLinearLayoutManager(context));
                rv_fund_2.setLayoutManager(new WrapContentLinearLayoutManager(context));
                rv_fund_3.setLayoutManager(new WrapContentLinearLayoutManager(context));
                rv_fund_4.setLayoutManager(new WrapContentLinearLayoutManager(context));

                if (adapter1 == null) {
                    adapter1 = new SimpleAdapter(fund_list1);
                    //这里设置点击 点击应跳转到海外特定标的的详情页面
                    adapter1.setOnItemClickListener(FundActivity.this);
                    adapter1.bindToRecyclerView(rv_fund_1);
                } else {
                    adapter1.setNewData(fund_list1);
                }

                if (adapter2 == null) {
                    adapter2 = new SimpleAdapter(fund_list2);
                    //这里设置点击 点击应跳转到海外特定标的的详情页面
                    adapter2.setOnItemClickListener(FundActivity.this);
                    adapter2.bindToRecyclerView(rv_fund_2);
                } else {
                    adapter2.setNewData(fund_list2);
                }

                if (adapter3 == null) {
                    adapter3 = new SimpleAdapter(fund_list3);
                    //这里设置点击 点击应跳转到海外特定标的的详情页面
                    adapter3.setOnItemClickListener(FundActivity.this);
                    adapter3.bindToRecyclerView(rv_fund_3);
                } else {
                    adapter3.setNewData(fund_list3);
                }

                if (adapter4 == null) {
                    adapter4 = new SimpleAdapter(fund_list4);
                    //这里设置点击 点击应跳转到海外特定标的的详情页面
                    adapter4.setOnItemClickListener(FundActivity.this);
                    adapter4.bindToRecyclerView(rv_fund_4);
                } else {
                    adapter4.setNewData(fund_list2);
                }

                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.GONE);

                if (isRefresh) {
                    refreshLayout.finishRefresh();
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                NormalUtils.showToast(FundActivity.this, R.string.net_error);
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.VISIBLE);

                if (isRefresh) {
                    refreshLayout.finishRefresh();
                }
            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        FundActivity activity = FundActivity.this;
        Bundle args = new Bundle();
        args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, ((SimpleAdapter) adapter).getItem(position).getFund_id());
        args.putString(BidFixedActivity.BID_NAME,
                ((SimpleAdapter) adapter).getItem(position).getFund_name());
        args.putString(FundDetailActivity.FUND_CODE,
                ((SimpleAdapter) adapter).getItem(position).getFund_code());
        activity.startActivity(FundDetailActivity.class, args);
    }

    class SimpleAdapter extends BaseQuickAdapter<HomeFund.ResultBean.FundListBean, BaseViewHolder> {

        public SimpleAdapter(@Nullable List<HomeFund.ResultBean.FundListBean> data) {
            super(R.layout.classify_listitem_fund, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeFund.ResultBean.FundListBean item) {
            helper.setText(R.id.tv_1, item.getYear_gr() + "%")
                    .setText(R.id.tv_3, item.getFund_name())
                    .setText(R.id.tv_4, item.getInvest_type() + " | " + item.getRisk());
            if (helper.getAdapterPosition() == getData().size() - 1) {
                helper.getView(R.id.bottom_line).setBackgroundColor(Color.WHITE);
            } else {
                helper.getView(R.id.bottom_line).setBackgroundColor(getResources().getColor(R.color.colorLine));
            }
        }
    }

}
