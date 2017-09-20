package com.changju.fanlitou.activity.fund;

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
import android.widget.ScrollView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.MainActivity;
import com.changju.fanlitou.activity.mine.MyFundTradeRecordActivity;
import com.changju.fanlitou.adapter.BannerAdapter;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.fund.FundBasicInfo;
import com.changju.fanlitou.bean.mine.CompleteOrdersBean;
import com.changju.fanlitou.bean.mine.FundSummaryInfo;
import com.changju.fanlitou.bean.mine.HoldingFundBean;
import com.changju.fanlitou.bean.mine.ProcessingFundBean;
import com.changju.fanlitou.ui.WrapContentViewPager;
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


public class MyFundActivity extends BaseActivity {

    private TextView tv_go_question;
    private ImageView activityMyFundIvBack;
    private TextView activityMyFundRecord;
    private ScrollView layoutScroll;
    private TextView activityMyFundTotalVal;
    private TextView yesterdayIncome;
    private TextView totalIncome;
    private TabLayout tabMyFund;
    private WrapContentViewPager viewpagerMyFund;
    private String[] titles = {"持有中", "处理中", "已完成"};
    private FundBasicInfo fundBasicInfo;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private AnimDialog mAnimDialog;
    //默认进入的tab
    private int defaultTab;
    private RecyclerView mOneRecycler;
    private RecyclerView mTwoRecycler;
    private RecyclerView mThreeRecycler;
    //loading&error
    private View include;
    private View include_load_error;
    private SimpleHoldingAdapter mAdapter1;
    private SimpleHandlingAdapter mAdapter2;
    private SimpleFinishedAdapter mAdapter3;
    private FundSummaryInfo fundSummary;
    private HoldingFundBean holdingFund;
    private ProcessingFundBean processingFundBean;
    private CompleteOrdersBean completeOrdersBean;
    private List<HoldingFundBean.ResultBean> result;
    private List<ProcessingFundBean.ResultBean> result1;
    private List<CompleteOrdersBean.ResultBean> result2;
    private TextView activity_my_fund_total_type;

    private SmartRefreshLayout refreshLayout;

    private boolean show_holding_higher_fund;

    //是否刷新
    private boolean isRefresh;

    @Override
    public void initParams(Bundle params) {
        String defaultTab_str = params.getString(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
        if (!TextUtils.isEmpty(defaultTab_str) && NormalUtils.isInteger(defaultTab_str)) {
            defaultTab = Integer.valueOf(defaultTab_str);
        } else {
            defaultTab = params.getInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
        }

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_my_fund;
    }


    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "基金-我的基金");

        tv_go_question = (TextView) findViewById(R.id.tv_go_question);
        tv_go_question.setOnClickListener(this);
        activityMyFundIvBack = (ImageView) findViewById(R.id.activity_my_fund_iv_back);
        activity_my_fund_total_type = (TextView) findViewById(R.id.activity_my_fund_total_type);
        activityMyFundIvBack.setOnClickListener(this);
        activity_my_fund_total_type.setOnClickListener(this);
        activityMyFundRecord = (TextView) findViewById(R.id.activity_my_fund_record);
        activityMyFundRecord.setOnClickListener(this);
        activityMyFundTotalVal = (TextView) findViewById(R.id.activity_my_fund_total_val);
        yesterdayIncome = (TextView) findViewById(R.id.yesterday_income);
        totalIncome = (TextView) findViewById(R.id.total_income);
        mViewPager = (ViewPager) findViewById(R.id.viewpager_my_fund);
        mTabLayout = (TabLayout) findViewById(R.id.tab_my_fund);
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

        mAdapter1 = new SimpleHoldingAdapter(null);
        mAdapter2 = new SimpleHandlingAdapter(null);
        mAdapter3 = new SimpleFinishedAdapter(null);

        mAdapter1.bindToRecyclerView(mOneRecycler);
        mAdapter1.setEmptyView(R.layout.view_empty_top);
        ImageView imageView = (ImageView) mAdapter1.getEmptyView().findViewById(R.id.iv_content);
        imageView.setPadding(0, 160, 0, 0);
        TextView textView = (TextView) mAdapter1.getEmptyView().findViewById(R.id.tv_content);
        textView.setText("暂无数据");

        mAdapter2.bindToRecyclerView(mTwoRecycler);
        mAdapter2.setEmptyView(R.layout.view_empty_top);
        ImageView imageView2 = (ImageView) mAdapter2.getEmptyView().findViewById(R.id.iv_content);
        imageView2.setPadding(0, 160, 0, 0);
        TextView textView2 = (TextView) mAdapter2.getEmptyView().findViewById(R.id.tv_content);
        textView2.setText("暂无数据");

        mAdapter3.bindToRecyclerView(mThreeRecycler);
        mAdapter3.setEmptyView(R.layout.view_empty_top);
        ImageView imageView3 = (ImageView) mAdapter3.getEmptyView().findViewById(R.id.iv_content);
        imageView3.setPadding(0, 160, 0, 0);
        TextView textView3 = (TextView) mAdapter3.getEmptyView().findViewById(R.id.tv_content);
        textView3.setText("暂无数据");

        pullToRefresh(mAdapter1, 1, mOneRecycler);
        pullToRefresh(mAdapter2, 2, mTwoRecycler);
        pullToRefresh(mAdapter3, 3, mThreeRecycler);

        mViewPager.setAdapter(new BannerAdapter(lists, titles));
        mTabLayout.setupWithViewPager(mViewPager);

        mAdapter1.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle args = new Bundle();
                args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,
                        mAdapter1.getItem(position).getId());
                startActivity(FundDetailActivity.class, args);

            }
        });
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                if (pos == 1 && processingFundBean == null) {
                    if (mAnimDialog == null)
                        mAnimDialog = AnimDialog.showDialog(MyFundActivity.this);

                    if (!mAnimDialog.isShowing())
                        mAnimDialog.show();

                    initBean(1, false);

                } else if (pos == 2 && completeOrdersBean == null) {
                    if (mAnimDialog == null)
                        mAnimDialog = AnimDialog.showDialog(MyFundActivity.this);

                    if (!mAnimDialog.isShowing())
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

        refreshLayout = (SmartRefreshLayout) findViewById(R.id.my_fund_refresh_layout);
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
                        initData(MyFundActivity.this);
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

    private void setEmptyView(BaseQuickAdapter adapter) {
        if (adapter == null)
            return;

        adapter.setEmptyView(R.layout.view_empty_top);
        ImageView imageView = (ImageView) adapter.getEmptyView().findViewById(R.id.iv_content);
        imageView.setPadding(0, 160, 0, 0);
        TextView textView = (TextView) adapter.getEmptyView().findViewById(R.id.tv_content);
        textView.setText("暂无数据");
    }

    /**
     * 请求后台数据
     *
     * @param
     * @param type
     */
    private void initBean(int type, final boolean b) {
        switch (type) {
            case 0:
                OkGo.get(ApiUtils.getHoldingFund(MyFundActivity.this, page1, 10))
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                page1++;
                                holdingFund = ParseUtils.parseByGson(s, HoldingFundBean.class);
                                if (result == null) {
                                    result = new ArrayList<>();
                                }
                                //如果是加载更多
                                if (b) {
                                    if (holdingFund.getResult() == null || holdingFund.getResult().size() < 1) {
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
                                                    RecyclerView.LayoutManager manager = mOneRecycler.getLayoutManager();
                                                    if (manager == null) return;
                                                    if (manager instanceof LinearLayoutManager) {
                                                        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) manager;
                                                        mOneRecycler.postDelayed(new Runnable() {
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
                                        mAdapter1.addData(holdingFund.getResult());
                                        mAdapter1.loadMoreComplete();
                                    }
                                } else {
                                    result.clear();
                                    result.addAll(holdingFund.getResult());
                                    mAdapter1.setNewData(result);
                                    if (mAnimDialog != null && mAnimDialog.isShowing()) {
                                        mAnimDialog.dismiss();
                                    }
                                    setEmptyView(mAdapter1);
                                }
                            }

                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                super.onError(call, response, e);
                                NormalUtils.showToast(MyFundActivity.this, R.string.net_error);
                                if (mAnimDialog != null && mAnimDialog.isShowing()) {
                                    mAnimDialog.dismiss();
                                }

                                if (b) {
                                    mAdapter1.loadMoreFail();
                                } else {
                                    setEmptyView(mAdapter1);
                                }
                            }
                        });

                break;
            case 1:
                OkGo.get(ApiUtils.getProcessingFund(MyFundActivity.this, page2, 10))
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                page2++;
                                processingFundBean = ParseUtils.parseByGson(s, ProcessingFundBean.class);
                                if (result1 == null) {
                                    result1 = new ArrayList<>();
                                }
                                if (b) {
                                    if (processingFundBean.getResult() == null || processingFundBean.getResult().size() < 1) {
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
                                                    RecyclerView.LayoutManager manager = mTwoRecycler.getLayoutManager();
                                                    if (manager == null) return;
                                                    if (manager instanceof LinearLayoutManager) {
                                                        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) manager;
                                                        mTwoRecycler.postDelayed(new Runnable() {
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
                                        mAdapter2.addData(processingFundBean.getResult());
                                        mAdapter2.loadMoreComplete();
                                    }
                                } else {
                                    result1.clear();
                                    result1.addAll(processingFundBean.getResult());
                                    mAdapter2.setNewData(result1);
                                    if (mAnimDialog != null && mAnimDialog.isShowing()) {
                                        mAnimDialog.dismiss();
                                    }
                                    setEmptyView(mAdapter2);
                                }
                            }

                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                super.onError(call, response, e);
                                NormalUtils.showToast(MyFundActivity.this, R.string.net_error);
                                if (mAnimDialog != null && mAnimDialog.isShowing()) {
                                    mAnimDialog.dismiss();
                                }
                                if (b) {
                                    mAdapter2.loadMoreFail();
                                } else {
                                    setEmptyView(mAdapter2);
                                }
                            }
                        });
                break;
            case 2:
                OkGo.get(ApiUtils.getCompleteOrders(MyFundActivity.this, page3, 10))
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                page3++;
                                completeOrdersBean = ParseUtils.parseByGson(s, CompleteOrdersBean.class);
                                if (result2 == null) {
                                    result2 = new ArrayList<>();
                                }
                                if (b) {
                                    if (completeOrdersBean.getResult() == null || completeOrdersBean.getResult().size() < 1) {
                                        mAdapter3.loadMoreEnd();
                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    Thread.sleep(500);
                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            mAdapter3.setEnableLoadMore(false);
                                                        }
                                                    });
                                                    RecyclerView.LayoutManager manager = mThreeRecycler.getLayoutManager();
                                                    if (manager == null) return;
                                                    if (manager instanceof LinearLayoutManager) {
                                                        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) manager;
                                                        mThreeRecycler.postDelayed(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                if ((linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1) != mAdapter3.getItemCount()) {
                                                                    mAdapter3.setEnableLoadMore(true);
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
                                        mAdapter3.addData(completeOrdersBean.getResult());
                                        mAdapter3.loadMoreComplete();
                                    }
                                } else {
                                    result2.clear();
                                    result2.addAll(completeOrdersBean.getResult());
                                    mAdapter3.setNewData(result2);
                                    if (mAnimDialog != null && mAnimDialog.isShowing()) {
                                        mAnimDialog.dismiss();
                                    }
                                    setEmptyView(mAdapter3);
                                }

                            }

                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                super.onError(call, response, e);
                                NormalUtils.showToast(MyFundActivity.this, R.string.net_error);
                                if (mAnimDialog != null && mAnimDialog.isShowing()) {
                                    mAnimDialog.dismiss();
                                }
                                if (b) {
                                    mAdapter3.loadMoreFail();
                                } else {
                                    setEmptyView(mAdapter3);
                                }
                            }
                        });
                break;
        }

    }

    class SimpleHoldingAdapter extends BaseQuickAdapter<HoldingFundBean.ResultBean, BaseViewHolder> {
        public SimpleHoldingAdapter(@Nullable List<HoldingFundBean.ResultBean> data) {
            super(R.layout.recycler_item_my_fund_holding, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HoldingFundBean.ResultBean item) {
            helper.setText(R.id.my_fund_holding_tv_fund_name, item.getName() + "(" + item.getFund_code() + ")")
                    .setText(R.id.my_fund_holding_money, item.getInvest_amount() + "")
                    .setText(R.id.my_fund_holding_share, "在持份额(份): " + item.getShare())
                    .setText(R.id.my_fund_holding_total_rebate, item.getTotal_income())
                    .setText(R.id.my_fund_holding_yesterday_income, item.getRecent_income())
                    .addOnClickListener(R.id.my_fund_holding_ll);
            if (item.isInterest_start_date_notify()) {
                helper.setText(R.id.my_fund_holding_yesterday, "预计收益时间");
                helper.setTextColor(R.id.my_fund_holding_yesterday_income, Color.parseColor("#f94529"));
            } else {
                helper.setText(R.id.my_fund_holding_yesterday, "昨日收益(元)");

                if (!item.isInterest_start_date_notify()) {
                    //非日期--昨日收益
                    Float recent_income = Float.valueOf(item.getRecent_income());
                    if (recent_income < 0) {
                        helper.setTextColor(R.id.my_fund_holding_yesterday_income, Color.parseColor("#6db247"));
                    } else if (recent_income > 0) {
                        helper.setTextColor(R.id.my_fund_holding_yesterday_income, Color.parseColor("#f94529"));
                    } else {
                        helper.setTextColor(R.id.my_fund_holding_yesterday_income, Color.parseColor("#5e5e5e"));
                    }
                } else {
                    //日期的时候
                    helper.setTextColor(R.id.my_fund_holding_yesterday_income, Color.parseColor("#f94529"));
                }


            }

            Float total_income = Float.valueOf(item.getTotal_income());
            if (total_income < 0) {
                helper.setTextColor(R.id.my_fund_holding_total_rebate, Color.parseColor("#6eb53f"));
            } else if (total_income > 0) {
                helper.setTextColor(R.id.my_fund_holding_total_rebate, Color.parseColor("#f95353"));
            } else {
                helper.setTextColor(R.id.my_fund_holding_total_rebate, Color.parseColor("#5e5e5e"));
            }

        }
    }

    class SimpleHandlingAdapter extends BaseQuickAdapter<ProcessingFundBean.ResultBean, BaseViewHolder> {
        public SimpleHandlingAdapter(@Nullable List<ProcessingFundBean.ResultBean> data) {
            super(R.layout.recycler_item_my_fund_handling, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ProcessingFundBean.ResultBean item) {
            helper.setText(R.id.my_fund_handling_tv_fund_name, item.getName() + "(" + item.getFund_code() + ")")
                    .setText(R.id.my_fund_handling_tv_state, item.getStatus_str());
            if (item.getStatus() == 1) {
                helper.setText(R.id.my_fund_handling_is, "金额(元)");
                helper.setText(R.id.my_fund_handling_money, item.getProcessing_amount());
                helper.setText(R.id.my_fund_handling_tv_confirm_time_is, "预计确认时间");
                helper.setText(R.id.my_fund_handling_tv_confirm_time, item.getExpect_confirm_date());


            } else {
                helper.setText(R.id.my_fund_handling_is, "份额(份)");
                helper.setText(R.id.my_fund_handling_money, item.getProcessing_share());
                helper.setText(R.id.my_fund_handling_tv_confirm_time_is, "预计到账时间");
                helper.setText(R.id.my_fund_handling_tv_confirm_time, item.getOperating_date());
            }

        }
    }

    class SimpleFinishedAdapter extends BaseQuickAdapter<CompleteOrdersBean.ResultBean, BaseViewHolder> {
        public SimpleFinishedAdapter(@Nullable List<CompleteOrdersBean.ResultBean> data) {
            super(R.layout.recycler_item_my_fund_finished, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, CompleteOrdersBean.ResultBean item) {
            helper.setText(R.id.my_fund_finished_tv_fund_name, item.getName() + "(" + item.getFund_code() + ")")
                    .setText(R.id.my_fund_finished_tv_state, item.getStatus_str())
                    .setText(R.id.my_fund_finished_result_tv, item.getMsg());
            switch (item.getStatus()) {
                case 2:
                case 3:
                case 6:
                    helper.setTextColor(R.id.my_fund_finished_tv_state, Color.parseColor("#f94529"))
                            .setText(R.id.my_fund_finished_money_is, "份额(份)")
                            .setText(R.id.my_fund_finished_tv_confirm, "到账时间")
                            .setText(R.id.my_fund_finished_money, item.getShares())
                            .setText(R.id.my_fund_finished_tv_confirm_time, item.getArrive_time())
                    ;
                    break;
                case 1:
                case 4:
                case 5:
                    helper.setTextColor(R.id.my_fund_finished_tv_state, Color.parseColor("#6eb53f"))
                            .setText(R.id.my_fund_finished_money_is, "金额(元)")
                            .setText(R.id.my_fund_finished_tv_confirm, "确认时间")
                            .setText(R.id.my_fund_finished_money, item.getAmount())
                            .setText(R.id.my_fund_finished_tv_confirm_time, item.getConfirm_time());
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
        if (!UserUtils.isLogin(MyFundActivity.this)) {
            NormalUtils.showToast(MyFundActivity.this, "请先登录");
            finish();
            return;
        }
        String done = ApiUtils.getFundSummaryInfo(mContext);
        OkGo.get(done).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                fundSummary = ParseUtils.parseByGson(s, FundSummaryInfo.class);
                yesterdayIncome.setText(fundSummary.getYesterday_income());
                activity_my_fund_total_type.setText(fundSummary.getRisk_level());
                if (!TextUtils.isEmpty(fundSummary.getRisk_level())) {
                    activity_my_fund_total_type.setTextColor(getResources().getColor(R.color.colorTextBlue));
                    activity_my_fund_total_type.setBackgroundResource(R.drawable.shape_tag_blue);
                } else {
                    findViewById(R.id.ceping_layout).setVisibility(View.VISIBLE);
                }
                if (0 == Float.parseFloat(fundSummary.getYesterday_income())) {
                    yesterdayIncome.setTextColor(Color.parseColor("#323232"));
                } else if (Float.parseFloat(fundSummary.getYesterday_income()) > 0) {
                    yesterdayIncome.setTextColor(Color.parseColor("#f94529"));
                } else {
                    yesterdayIncome.setTextColor(Color.parseColor("#6db247"));
                }
                totalIncome.setText(fundSummary.getTotal_income());
                if (0 == Float.parseFloat(fundSummary.getTotal_income())) {
                    totalIncome.setTextColor(Color.parseColor("#323232"));
                } else if (Float.parseFloat(fundSummary.getTotal_income()) > 0) {
                    totalIncome.setTextColor(Color.parseColor("#f94529"));
                } else {
                    totalIncome.setTextColor(Color.parseColor("#6db247"));
                }

                show_holding_higher_fund = fundSummary.isShow_holding_higher_fund();

                activityMyFundTotalVal.setText(fundSummary.getInvest_amount());

                if (isRefresh) {
                    refreshLayout.finishRefresh();
                } else {
                    OkGo.get(ApiUtils.getHoldingFund(mContext, page1, 10)).execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            holdingFund = ParseUtils.parseByGson(s, HoldingFundBean.class);
                            if (result == null) {
                                result = new ArrayList<>();
                            }
                            result.clear();
                            result.addAll(holdingFund.getResult());
                            mAdapter1.setNewData(result);
                            include.setVisibility(View.GONE);
                            include_load_error.setVisibility(View.GONE);
                            setEmptyView(mAdapter1);
                            page1++;
                        }
                    });
                }

                if (defaultTab > 0)
                    mViewPager.setCurrentItem(defaultTab, false);

            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                NormalUtils.showToast(MyFundActivity.this, R.string.net_error);
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
            case R.id.activity_my_fund_iv_back:
                startToMinePage();
                break;
            case R.id.activity_my_fund_record:
                startActivity(new Intent(MyFundActivity.this, MyFundTradeRecordActivity.class));
                break;
            case R.id.include_load_error:
                include_load_error.setVisibility(View.GONE);
                include.setVisibility(View.VISIBLE);
                isRefresh = false;
                initData(this);
                break;
            case R.id.activity_my_fund_total_type:
                String risk_level = activity_my_fund_total_type.getText().toString();
                if (!TextUtils.isEmpty(risk_level)) {
                    Bundle args = new Bundle();
                    args.putString(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, risk_level);
                    args.putBoolean(FundQuestionResultActivity.class.getSimpleName(), show_holding_higher_fund);
                    startActivity(FundQuestionResultActivity.class, args);
                }
                break;
            case R.id.tv_go_question:
                startActivity(new Intent(MyFundActivity.this, FundQuestionActivity.class));
                break;
        }
    }

    private void startToMinePage() {
        Bundle args = new Bundle();
        args.putBoolean(MainActivity.MINE_PAGE, true);
        startActivity(MainActivity.class, args);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isRefresh = false;
        initData(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startToMinePage();
    }
}
