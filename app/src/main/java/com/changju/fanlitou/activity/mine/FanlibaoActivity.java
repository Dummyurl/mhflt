package com.changju.fanlitou.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.WebActivity;
import com.changju.fanlitou.adapter.BannerAdapter;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.fanlitou.DetailRecords;
import com.changju.fanlitou.bean.fanlitou.FanlibaoSummaryInfo;
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

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;


public class FanlibaoActivity extends BaseActivity {

    private String[] titles = {"收益明细", "交易记录", "资金流水"};
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private AnimDialog mAnimDialog;
    //默认进入的tab
    private int defaultTab = -1;
    private RecyclerView mOneRecycler;
    private RecyclerView mTwoRecycler;
    private RecyclerView mThreeRecycler;
    private SmartRefreshLayout refreshLayout;
    //loading&error
    private View include;
    private View include_load_error;

    private SimpleIncomeDetailAdapter mAdapter0;
    private SimpleTransactionRecordAdapter mAdapter1;
    private SimpleFundCurrentAdapter mAdapter2;

    private DetailRecords detailRecords0, detailRecords1, detailRecords2;

    private List<DetailRecords.DatalistBean> result0, result1, result2;

    private FanlibaoSummaryInfo fanlibaoSummaryInfo;


    private TextView tv_fanlibao_yue;
    private TextView tv_yesterday_income;
    private TextView tv_total_income;
    private TextView tv_7days_income;
    private TextView tv_center;
    private LinearLayout center_layout;
    private boolean is_open_jifen;
    private ImageView activity_fanlibao_introduce;

    //是否刷新
    private boolean isRefresh;

    @Override
    public void initParams(Bundle params) {
        String defaultTabStr = params.getString(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
        if (!TextUtils.isEmpty(defaultTabStr) && NormalUtils.isInteger(defaultTabStr)) {
            defaultTab = Integer.valueOf(defaultTabStr);
        } else {
            defaultTab = params.getInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
        }

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_fanlibao;
    }


    @Override
    public void initView(View view) {

        UserUtils.checkLogin(this, FanlibaoActivity.class);

        GrowingIO.getInstance().setPageName(this, "我的-返利宝");

        tv_fanlibao_yue = (TextView) findViewById(R.id.tv_fanlibao_yue);
        tv_yesterday_income = (TextView) findViewById(R.id.tv_yesterday_income);
        tv_total_income = (TextView) findViewById(R.id.tv_total_income);
        tv_7days_income = (TextView) findViewById(R.id.tv_7days_income);
        tv_center = (TextView) findViewById(R.id.tv_center);
        center_layout = (LinearLayout) findViewById(R.id.center_layout);
        center_layout.setOnClickListener(this);
        activity_fanlibao_introduce = (ImageView) findViewById(R.id.activity_fanlibao_introduce);

        findViewById(R.id.activity_fanlibao_iv_back).setOnClickListener(this);
        findViewById(R.id.activity_fanlibao_introduce).setOnClickListener(this);
        mViewPager = (ViewPager) findViewById(R.id.viewpager_fanlibao);
        mTabLayout = (TabLayout) findViewById(R.id.tab_fanlibao);
        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);
        List<View> lists = new ArrayList<>();
        mOneRecycler = new RecyclerView(this);
        mOneRecycler.setLayoutManager(new WrapContentLinearLayoutManager(this));
        mOneRecycler.setOverScrollMode(View.OVER_SCROLL_NEVER);
        lists.add(mOneRecycler);

        mTwoRecycler = new RecyclerView(this);
        mTwoRecycler.setLayoutManager(new WrapContentLinearLayoutManager(this));
        mTwoRecycler.setOverScrollMode(View.OVER_SCROLL_NEVER);
        lists.add(mTwoRecycler);

        mThreeRecycler = new RecyclerView(this);
        mThreeRecycler.setLayoutManager(new WrapContentLinearLayoutManager(this));
        mThreeRecycler.setOverScrollMode(View.OVER_SCROLL_NEVER);
        lists.add(mThreeRecycler);

        mAdapter0 = new SimpleIncomeDetailAdapter(null);
        mAdapter1 = new SimpleTransactionRecordAdapter(null);
        mAdapter2 = new SimpleFundCurrentAdapter(null);

        mAdapter0.bindToRecyclerView(mOneRecycler);

        mAdapter1.bindToRecyclerView(mTwoRecycler);

        mAdapter2.bindToRecyclerView(mThreeRecycler);

        pullToRefresh(mAdapter0, 1, mOneRecycler);
        pullToRefresh(mAdapter1, 2, mTwoRecycler);
        pullToRefresh(mAdapter2, 3, mThreeRecycler);

        mViewPager.setAdapter(new BannerAdapter(lists, titles));
        mTabLayout.setupWithViewPager(mViewPager);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                if (pos == 1 && detailRecords1 == null) {
                    if (mAnimDialog == null)
                        mAnimDialog = AnimDialog.showDialog(FanlibaoActivity.this);
                    mAnimDialog.show();
                    initBean(1, false);
                } else if (pos == 2 && detailRecords2 == null) {
                    if (mAnimDialog == null)
                        mAnimDialog = AnimDialog.showDialog(FanlibaoActivity.this);
                    mAnimDialog.show();
                    initBean(2, false);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        refreshLayout = (SmartRefreshLayout) findViewById(R.id.fanlibao_refresh_layout);
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
                        initData(FanlibaoActivity.this);
                        if (mViewPager.getCurrentItem() == 0) {
                            page1 = 1;
                            initBean(0, false);
                        } else if (mViewPager.getCurrentItem() == 1) {
                            page2 = 1;
                            initBean(1, false);
                        } else {
                            page3 = 1;
                            initBean(2, false);
                        }
                    }
                }, 1000);
            }
        });
    }

    /**
     * 请求后台数据
     *
     * @param
     * @param type
     */
    private void initBean(int type, final boolean isLoadermore) {
        switch (type) {
            case 0:
                OkGo.get(ApiUtils.getFanlibaoDetailRecord(FanlibaoActivity.this, page1, 10, "income"))
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                page1++;
                                detailRecords0 = ParseUtils.parseByGson(s, DetailRecords.class);
                                if (result0 == null) {
                                    result0 = new ArrayList<>();
                                }
                                //如果是加载更多
                                if (isLoadermore) {
                                    if (detailRecords0.getDatalist() == null || detailRecords0.getDatalist().size() < 1) {
                                        mAdapter0.loadMoreEnd();
                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    Thread.sleep(500);
                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            mAdapter0.setEnableLoadMore(false);
                                                        }
                                                    });
                                                    RecyclerView.LayoutManager manager = mOneRecycler.getLayoutManager();
                                                    if (manager == null) return;
                                                    if (manager instanceof LinearLayoutManager) {
                                                        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) manager;
                                                        mOneRecycler.postDelayed(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                if ((linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1) != mAdapter0.getItemCount()) {
                                                                    mAdapter0.setEnableLoadMore(true);
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
                                        mAdapter0.addData(detailRecords0.getDatalist());
                                        mAdapter0.loadMoreComplete();
                                    }
                                } else {
                                    result0.clear();
                                    result0.addAll(detailRecords0.getDatalist());
                                    mAdapter0.setNewData(result0);
                                    if (mAnimDialog != null && mAnimDialog.isShowing()) {
                                        mAnimDialog.dismiss();
                                    }
                                    mAdapter0.setEmptyView(R.layout.view_empty_fanlibao);
                                }
                            }

                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                super.onError(call, response, e);
                                NormalUtils.showToast(FanlibaoActivity.this, R.string.net_error);
                                if (mAnimDialog != null && mAnimDialog.isShowing()) {
                                    mAnimDialog.dismiss();
                                }

                                if (isLoadermore) {
                                    mAdapter0.loadMoreFail();
                                }
                            }
                        });
                break;
            case 1:
                OkGo.get(ApiUtils.getFanlibaoDetailRecord(FanlibaoActivity.this, page2, 10, "transaction"))
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                page2++;
                                detailRecords1 = ParseUtils.parseByGson(s, DetailRecords.class);
                                if (result1 == null) {
                                    result1 = new ArrayList<>();
                                }
                                if (isLoadermore) {
                                    if (detailRecords1.getDatalist() == null || detailRecords1.getDatalist().size() < 1) {
                                        mAdapter1.loadMoreEnd();
                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    Thread.sleep(500);
                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            mAdapter1.setEnableLoadMore(false);
                                                        }
                                                    });
                                                    RecyclerView.LayoutManager manager = mTwoRecycler.getLayoutManager();
                                                    if (manager == null) return;
                                                    if (manager instanceof LinearLayoutManager) {
                                                        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) manager;
                                                        mTwoRecycler.postDelayed(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                if ((linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1) != mAdapter1.getItemCount()) {
                                                                    mAdapter1.setEnableLoadMore(true);
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
                                        mAdapter1.addData(detailRecords1.getDatalist());
                                        mAdapter1.loadMoreComplete();
                                    }
                                } else {
                                    result1.clear();
                                    result1.addAll(detailRecords1.getDatalist());
                                    mAdapter1.setNewData(result1);
                                    if (mAnimDialog != null && mAnimDialog.isShowing()) {
                                        mAnimDialog.dismiss();
                                    }
                                    mAdapter1.setEmptyView(R.layout.view_empty_fanlibao);
                                }
                            }

                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                super.onError(call, response, e);
                                NormalUtils.showToast(FanlibaoActivity.this, R.string.net_error);
                                if (mAnimDialog != null && mAnimDialog.isShowing()) {
                                    mAnimDialog.dismiss();
                                }
                                if (isLoadermore) {
                                    mAdapter1.loadMoreFail();
                                }
                            }
                        });
                break;
            case 2:
                OkGo.get(ApiUtils.getFanlibaoDetailRecord(FanlibaoActivity.this, page3, 10, "cashrecord"))
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                page3++;
                                detailRecords2 = ParseUtils.parseByGson(s, DetailRecords.class);
                                if (result2 == null) {
                                    result2 = new ArrayList<>();
                                }
                                if (isLoadermore) {
                                    if (detailRecords2.getDatalist() == null || detailRecords2.getDatalist().size() < 1) {
                                        mAdapter2.loadMoreEnd();
                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    Thread.sleep(500);
                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            mAdapter2.setEnableLoadMore(false);
                                                        }
                                                    });
                                                    RecyclerView.LayoutManager manager = mThreeRecycler.getLayoutManager();
                                                    if (manager == null) return;
                                                    if (manager instanceof LinearLayoutManager) {
                                                        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) manager;
                                                        mThreeRecycler.postDelayed(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                if ((linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1) != mAdapter2.getItemCount()) {
                                                                    mAdapter2.setEnableLoadMore(true);
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
                                        mAdapter2.addData(detailRecords2.getDatalist());
                                        mAdapter2.loadMoreComplete();
                                    }
                                } else {
                                    result2.clear();
                                    result2.addAll(detailRecords2.getDatalist());
                                    mAdapter2.setNewData(result2);
                                    if (mAnimDialog != null && mAnimDialog.isShowing()) {
                                        mAnimDialog.dismiss();
                                    }
                                    mAdapter2.setEmptyView(R.layout.view_empty_fanlibao);
                                }

                            }

                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                super.onError(call, response, e);
                                NormalUtils.showToast(FanlibaoActivity.this, R.string.net_error);
                                if (mAnimDialog != null && mAnimDialog.isShowing()) {
                                    mAnimDialog.dismiss();
                                }
                                if (isLoadermore) {
                                    mAdapter2.loadMoreFail();
                                }
                            }
                        });
                break;
        }

    }

    class SimpleIncomeDetailAdapter extends BaseQuickAdapter<DetailRecords.DatalistBean, BaseViewHolder> {
        public SimpleIncomeDetailAdapter(@Nullable List<DetailRecords.DatalistBean> data) {
            super(R.layout.recycler_item_fanlibao_incomedetail, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, DetailRecords.DatalistBean item) {
            helper.setText(R.id.tv_income_yesterday, item.getAmount() + "元")
                    .setText(R.id.tv_income_total, item.getAccum_income())
                    .setText(R.id.tv_date, item.getDate());
            //分别为绿色 红色 黑色
            switch (item.getClassX()) {
                case "negative":
                    helper.setTextColor(R.id.tv_income_yesterday, Color.parseColor("#6db247"));
                    break;
                case "positive":
                    helper.setTextColor(R.id.tv_income_yesterday, Color.parseColor("#f94529"));
                    break;
                case "zero":
                    helper.setTextColor(R.id.tv_income_yesterday, Color.parseColor("#323232"));
                    break;
            }
        }
    }

    class SimpleTransactionRecordAdapter extends BaseQuickAdapter<DetailRecords.DatalistBean, BaseViewHolder> {
        public SimpleTransactionRecordAdapter(@Nullable List<DetailRecords.DatalistBean> data) {
            super(R.layout.recycler_item_fanlibao_fundcurrent, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, DetailRecords.DatalistBean item) {
            helper.getView(R.id.tv_balance).setVisibility(View.GONE);
            helper.setTextColor(R.id.yue, Color.parseColor("#323232"));
            helper.setText(R.id.yue, item.getAmount() + "元")
                    .setText(R.id.tv_desc, item.getRemark())
                    .setText(R.id.tv_money_detail_time, item.getDate())
                    .setText(R.id.tv_money_detail_status, item.getType());
            Glide.with(FanlibaoActivity.this).load(item.getIcon())
                    .into((ImageView) helper.getView(R.id.img));
        }
    }

    class SimpleFundCurrentAdapter extends BaseQuickAdapter<DetailRecords.DatalistBean, BaseViewHolder> {
        public SimpleFundCurrentAdapter(@Nullable List<DetailRecords.DatalistBean> data) {
            super(R.layout.recycler_item_fanlibao_fundcurrent, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, DetailRecords.DatalistBean item) {
            helper.setText(R.id.yue, item.getAmount() + "元")
                    .setText(R.id.tv_desc, item.getRemark())
                    .setText(R.id.tv_money_detail_time, item.getDate())
                    .setText(R.id.tv_money_detail_status, item.getType())
                    .setText(R.id.tv_balance, "(可用余额:" + item.getBalance() + "元)");
            Glide.with(FanlibaoActivity.this).load(item.getIcon())
                    .into((ImageView) helper.getView(R.id.img));
            //分别为绿色 红色 黑色
            switch (item.getClassX()) {
                case "negative":
                    helper.setTextColor(R.id.yue, Color.parseColor("#6db247"));
                    break;
                case "positive":
                    helper.setTextColor(R.id.yue, Color.parseColor("#f94529"));
                    break;
                case "zero":
                    helper.setTextColor(R.id.yue, Color.parseColor("#323232"));
                    break;
            }
        }
    }

    int page1 = 1;
    int page2 = 1;
    int page3 = 1;

    private void pullToRefresh(final BaseQuickAdapter adapter, final int finalI, RecyclerView mRecyclerView) {
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                initBean(finalI - 1, true);
            }
        }, mRecyclerView);
    }

    @Override
    public void doBusiness(final Context mContext) {

    }

    public void initData(final Context mContext) {
        if (!UserUtils.isLogin(FanlibaoActivity.this)) {
            NormalUtils.showToast(FanlibaoActivity.this, "请先登录");
            finish();
        }

        OkGo.get(ApiUtils.getFanlibaoSummaryInfo(mContext)).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                fanlibaoSummaryInfo = ParseUtils.parseByGson(s, FanlibaoSummaryInfo.class);
                tv_fanlibao_yue.setText(fanlibaoSummaryInfo.getBalance());
                tv_yesterday_income.setText("昨日收益" + fanlibaoSummaryInfo.getLatest_income() + "元");
                tv_total_income.setText(fanlibaoSummaryInfo.getAccum_income());
                switch (fanlibaoSummaryInfo.getAccum_income_class()) {
                    case "negative":
                        tv_total_income.setTextColor(Color.parseColor("#6db247"));
                        break;
                    case "positive":
                        tv_total_income.setTextColor(Color.parseColor("#f94529"));
                        break;
                    case "zero":
                    default:
                        tv_total_income.setTextColor(Color.parseColor("#323232"));
                        break;
                }


                tv_7days_income.setText(fanlibaoSummaryInfo.getYear_yld());
                //true的时候 不显示介绍页入口 false显示
                is_open_jifen = fanlibaoSummaryInfo.isIs_open_jifen();
                if (is_open_jifen) {
                    if (!fanlibaoSummaryInfo.getNotify_info().isIs_show()) {
                        center_layout.setVisibility(View.GONE);
                    } else {
                        center_layout.setVisibility(View.VISIBLE);
                        tv_center.setText(fanlibaoSummaryInfo.getNotify_info().getAmount() + "元提现正在处理中，处理完成即可到账");
                        tv_center.setCompoundDrawables(null, null, null, null);
                    }

                    activity_fanlibao_introduce.setVisibility(View.VISIBLE);

                } else {
                    center_layout.setVisibility(View.VISIBLE);
                    activity_fanlibao_introduce.setVisibility(View.GONE);
                    tv_center.setText("返利宝，让返利也实时生息");
                    tv_center.setCompoundDrawablesWithIntrinsicBounds(null, null,
                            getResources().getDrawable(R.mipmap.fund_detail_arrow_new), null);
                }

                if (isRefresh) {
                    refreshLayout.finishRefresh();
                } else {
                    OkGo.get(ApiUtils.getFanlibaoDetailRecord(mContext, page1, 10, "income")).execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            detailRecords0 = ParseUtils.parseByGson(s, DetailRecords.class);
                            if (result0 == null) {
                                result0 = new ArrayList<>();
                            }
                            result0.clear();
                            result0.addAll(detailRecords0.getDatalist());
                            mAdapter0.setNewData(result0);

                            if (defaultTab > -1) {
                                mViewPager.setCurrentItem(defaultTab, false);
                                defaultTab = -1;
                            }
                            mAdapter0.setEmptyView(R.layout.view_empty_fanlibao);

                            include.setVisibility(View.GONE);
                            include_load_error.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);
                            if (defaultTab > -1) {
                                mViewPager.setCurrentItem(defaultTab, false);
                                defaultTab = -1;
                            }
                        }
                    });
                }

            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                NormalUtils.showToast(FanlibaoActivity.this, R.string.net_error);
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.VISIBLE);
                if (isRefresh) {
                    refreshLayout.finishRefresh();
                }
            }
        });
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.activity_fanlibao_iv_back:
                finish();
                break;
            case R.id.activity_fanlibao_introduce:
                startToIntroduce();
                //跳转到返利宝介绍
                break;
            case R.id.include_load_error:
                include_load_error.setVisibility(View.GONE);
                include.setVisibility(View.VISIBLE);
                isRefresh = false;
                initData(this);
                break;
            case R.id.center_layout:
                if (!is_open_jifen)
                    startToIntroduce();
                break;
        }
    }

    private void startToIntroduce() {
        Bundle args = new Bundle();
        args.putString(WebActivity.TITLE, "关于返利宝");
        args.putString(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, ApiUtils.getFanlibaoIntroduceH5());
        startActivity(WebActivity.class, args);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isRefresh = false;
        initData(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Bundle args = intent.getExtras();
        if (args != null) {
            initParams(args);
            if (defaultTab > -1) {
                mViewPager.setCurrentItem(defaultTab, false);
                defaultTab = -1;
            }
        }
    }
}
