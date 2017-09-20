package com.changju.fanlitou.activity.classifylist;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.bid.BidFixedActivity;
import com.changju.fanlitou.activity.platform.PlatformDetailActivity;
import com.changju.fanlitou.adapter.CanBindTwiceAdapter;
import com.changju.fanlitou.adapter.DiscoverBidFixedAdapter;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.discover.DiscoverBidFixed;
import com.changju.fanlitou.bean.homeclassify.Banner;
import com.changju.fanlitou.bean.homeclassify.HomeFixed;
import com.changju.fanlitou.fragment.drawable_layout_item.BidDrawableFragment;
import com.changju.fanlitou.ui.dialog.AnimDialog;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/5/25.
 */

public class FixedActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {

    public static final String FIXED = "fix";
    private TextView tv_classify_fixed_banner;

    private DrawerLayout drawer_layout_platform;

    private RecyclerView fixed_list;
    private SmartRefreshLayout refreshLayout;

    private ImageView logo_app_operation;
    private ImageView platform_platform_logo_app_square;
    private TextView background;
    private TextView bid_interest;
    private TextView bid_name;

    private TextView tv_new_user, tv_income, tv_duration;
    private ImageView iv_income, iv_duration;

    private DiscoverBidFixed discoverBidFixed;
    private List<DiscoverBidFixed.BidListBean> fixedListBean;
    private DiscoverBidFixedAdapter fixedAdapter;

    //loading&error
    private View include;
    private View include_load_error;
    private AnimDialog animDialog;

    private HomeFixed homeFixed;
    private HomeFixed.NewestFixPlatformBean newestFixPlatformBean;
    private HomeFixed.HighestRateFixBidBean highestRateFixBidBean;
    private View include_filter_top;
    private TextView tv_new_user_top;
    private TextView tv_income_top;
    private ImageView iv_income_top;
    private TextView tv_duration_top;
    private ImageView iv_duration_top;

    //是否刷新
    private boolean isRefresh;

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_classify_list_fixed;
    }

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "固收二级页");

        animDialog = AnimDialog.showDialog(this);

        findViewById(R.id.iv_back_fixed).setOnClickListener(this);

        //header视图
        View header_fixed = LayoutInflater.from(this).inflate(R.layout.header_fixed, null);

        RelativeLayout rl_fixed_1 = (RelativeLayout) header_fixed.findViewById(R.id.rl_fixed_1);
        RelativeLayout rl_fixed_2 = (RelativeLayout) header_fixed.findViewById(R.id.rl_fixed_2);
        rl_fixed_1.setOnClickListener(this);
        rl_fixed_2.setOnClickListener(this);
        //列表头部两个对着的格子里面的数据
        background = (TextView) header_fixed.findViewById(R.id.background);
        bid_interest = (TextView) header_fixed.findViewById(R.id.bid_interest);
        bid_name = (TextView) header_fixed.findViewById(R.id.bid_name);
        logo_app_operation = (ImageView) header_fixed.findViewById(R.id.logo_app_operation);
        platform_platform_logo_app_square = (ImageView)
                header_fixed.findViewById(R.id.platform_platform_logo_app_square);

        //紧挨着列表上方的筛选的几个按钮
        tv_new_user = (TextView) header_fixed.findViewById(R.id.tv_new_user);
        tv_new_user.setOnClickListener(this);
        tv_income = (TextView) header_fixed.findViewById(R.id.tv_income);
        iv_income = (ImageView) header_fixed.findViewById(R.id.iv_income);
        tv_duration = (TextView) header_fixed.findViewById(R.id.tv_duration);
        iv_duration = (ImageView) header_fixed.findViewById(R.id.iv_duration);
        header_fixed.findViewById(R.id.layout_income).setOnClickListener(this);
        header_fixed.findViewById(R.id.layout_duration).setOnClickListener(this);
        header_fixed.findViewById(R.id.iv_filter_discover).setOnClickListener(this);
        tv_classify_fixed_banner = (TextView)
                header_fixed.findViewById(R.id.tv_classify_fixed_banner);


        drawer_layout_platform = (DrawerLayout) findViewById(R.id.drawer_layout_platform);
        //禁止侧边滑动
        drawer_layout_platform.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        //loading&&error
        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);

        //top_filter
        include_filter_top = findViewById(R.id.include_filter_top);
        tv_new_user_top = (TextView) include_filter_top.findViewById(R.id.tv_new_user);
        tv_new_user_top.setOnClickListener(this);
        include_filter_top.findViewById(R.id.layout_income).setOnClickListener(this);
        include_filter_top.findViewById(R.id.layout_duration).setOnClickListener(this);
        include_filter_top.findViewById(R.id.iv_filter_discover).setOnClickListener(this);

        tv_income_top = (TextView) include_filter_top.findViewById(R.id.tv_income);
        iv_income_top = (ImageView) include_filter_top.findViewById(R.id.iv_income);
        tv_duration_top = (TextView) include_filter_top.findViewById(R.id.tv_duration);
        iv_duration_top = (ImageView) include_filter_top.findViewById(R.id.iv_duration);

        refreshLayout = (SmartRefreshLayout) findViewById(R.id.fixed_refresh_layout);
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
                        initData(false);
                    }
                }, 1000);
            }
        });

        fixed_list = (RecyclerView) findViewById(R.id.fixed_list);
        final WrapContentLinearLayoutManager wrapContentLinearLayoutManager =
                new WrapContentLinearLayoutManager(FixedActivity.this);
        fixed_list.setLayoutManager(wrapContentLinearLayoutManager);
        fixed_list.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View view = wrapContentLinearLayoutManager.findViewByPosition(1);
                if (view != null) {
                    if (view.getTop() <= NormalUtils.dp2px(FixedActivity.this, 40)) {
                        include_filter_top.setVisibility(View.VISIBLE);
                    } else {
                        include_filter_top.setVisibility(View.GONE);
                    }
                }
            }
        });

        fixedAdapter = new DiscoverBidFixedAdapter(fixedListBean);
        fixedAdapter.bindToRecyclerView(fixed_list);
        pullToRefresh(fixedAdapter);
        fixedAdapter.disableLoadMoreIfNotFullPage();
        fixedAdapter.setOnItemClickListener(this);
        fixedAdapter.setHeaderView(header_fixed);
    }

    @Override
    public void doBusiness(Context mContext) {
        isRefresh = false;
        initHeader();
        initData(true);
    }


    private int readyCount;
    private int errorCount;

    private synchronized void readyForShow(boolean b) {
        if (b)
            readyCount++;
        else {
            errorCount++;
            NormalUtils.showToast(this, R.string.net_error);
            if (errorCount == 1) {
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.VISIBLE);
            }

        }

        if (readyCount >= 3) {
            include.setVisibility(View.GONE);
            include_load_error.setVisibility(View.GONE);
        }
    }

    private void initHeader() {
        final Context context = FixedActivity.this;
        OkGo.get(ApiUtils.getClassifyBanner(context, FIXED)).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Banner banner = ParseUtils.parseByGson(s, Banner.class);
                tv_classify_fixed_banner.setText(banner.getTitle());
                Glide.with(getApplicationContext()).load(banner.getUrl())
                        .into(new SimpleTarget<GlideDrawable>() {
                            @Override
                            public void onResourceReady(GlideDrawable resource,
                                                        GlideAnimation<? super GlideDrawable> glideAnimation) {
                                tv_classify_fixed_banner.setBackground(resource);
                            }
                        });
                readyForShow(true);
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                readyForShow(false);
            }
        });

        OkGo.get(ApiUtils.getHomeFixed(context)).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                homeFixed = ParseUtils.parseByGson(s, HomeFixed.class);
                newestFixPlatformBean = homeFixed.getNewest_fix_platform();
                highestRateFixBidBean = homeFixed.getHighest_rate_fix_bid();

                background.setText(newestFixPlatformBean.getBackground());
                bid_interest.setText(highestRateFixBidBean.getBid_interest());
                bid_name.setText(highestRateFixBidBean.getBid_name());

                Glide.with(getApplicationContext()).load(newestFixPlatformBean.getLogo_app_operation()).into(logo_app_operation);
                Glide.with(getApplicationContext()).load(highestRateFixBidBean.getPlatform().getPlatform_logo_app_square())
                        .into(platform_platform_logo_app_square);


                //筛选Fragment
                mBidDrawableFragment = BidDrawableFragment.newInstance(homeFixed.getFilter_list());

                readyForShow(true);
            }


            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                readyForShow(false);
            }
        });
    }

    public void initData(boolean isFirst) {

        if (!isFirst && !isRefresh)
            animDialog.show();

        //切换平台类别重置分页page
        page = 1;
        FixedActivity activity = FixedActivity.this;

        if (isFirst || activity.getBid_list_paras() == null) {
            selectedMap = new HashMap<>();
        } else {
            selectedMap = activity.getBid_list_paras();
        }

        //右侧筛选
        StringBuilder paras = new StringBuilder();

        for (Map.Entry entry : selectedMap.entrySet()) {
            paras.append(entry.getValue());
        }

        //收益和排序
        String order_by_name = "";
        String order_by_type = "";

        switch (incomeAndDuration) {
            case 1:
                order_by_name = "interest";
                order_by_type = "asc";
                break;
            case 2:
                order_by_name = "interest";
                order_by_type = "desc";
                break;
            case 3:
                order_by_name = "duration";
                order_by_type = "asc";
                break;
            case 4:
                order_by_name = "duration";
                order_by_type = "desc";
                break;
        }


        OkGo.get(ApiUtils.getBidList(activity
                , order_by_name, order_by_type, new_user, page, 10) + paras)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        discoverBidFixed = ParseUtils.parseByGson(s, DiscoverBidFixed.class);
                        fixedListBean = discoverBidFixed.getBid_list();

                        fixedAdapter.setNewData(fixedListBean);
                        fixedAdapter.setEmptyView(R.layout.view_empty_top);
                        ImageView imageView = (ImageView) fixedAdapter.getEmptyView().findViewById(R.id.iv_content);
                        imageView.setPadding(0, 160, 0, 0);
                        TextView tv = (TextView) fixedAdapter.getEmptyView().findViewById(R.id.tv_content);
                        tv.setText("暂无数据");
                        fixedAdapter.setHeaderAndEmpty(true);


                        include.setVisibility(View.GONE);
                        include_load_error.setVisibility(View.GONE);

                        if (animDialog != null && animDialog.isShowing()) {
                            animDialog.dismiss();
                        }

                        readyForShow(true);

                        if (isRefresh) {
                            refreshLayout.finishRefresh();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);

                        readyForShow(false);

                        fixedAdapter.setEmptyView(R.layout.view_load_error);
                        fixedAdapter.setHeaderAndEmpty(true);
                        if (animDialog != null && animDialog.isShowing()) {
                            animDialog.dismiss();
                        }

                        if (isRefresh) {
                            refreshLayout.finishRefresh();
                        }

                    }

                });
    }

    /**
     * 收益&期限排序
     * 0 没有排序
     * 1 收益 正序
     * 2 收益 倒序
     * 3 期限 正序
     * 4 期限 倒序
     */
    private int incomeAndDuration = 0;

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_fixed:
                finish();
                break;
            //打开筛选
            case R.id.iv_filter_discover:
                openDrawableLayout();
                break;
            case R.id.tv_new_user:
                //新手标-->非新手标
                if (new_user == 1) {
                    tv_new_user.setTextColor(
                            getResources().getColor(R.color.colorTextSecondary));
                    tv_new_user_top.setTextColor(
                            getResources().getColor(R.color.colorTextSecondary));
                    new_user = 2;
                } else {
                    //非新手标/不限-->新手标
                    tv_new_user.setTextColor(
                            getResources().getColor(R.color.colorTextRed));
                    tv_new_user_top.setTextColor(
                            getResources().getColor(R.color.colorTextRed));
                    new_user = 1;
                }
                //加载数据
                //initHeader();
                isRefresh = false;
                initData(false);
                break;
            case R.id.layout_income:
                if (incomeAndDuration != 1)
                    incomeAndDuration = 1;
                else
                    incomeAndDuration = 2;
                notifyIncomeAndDuration();
                break;
            case R.id.layout_duration:
                if (incomeAndDuration != 3)
                    incomeAndDuration = 3;
                else
                    incomeAndDuration = 4;
                notifyIncomeAndDuration();
                break;
            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                include_load_error.setVisibility(View.GONE);
                readyCount = 0;
                doBusiness(this);
                break;
            case R.id.rl_fixed_1:
                Bundle args = new Bundle();
                args.putInt(PlatformDetailActivity.PLATFORM_ID, newestFixPlatformBean.getId());
                args.putString(PlatformDetailActivity.PLATFORM_NAME, newestFixPlatformBean.getName());
                startActivity(PlatformDetailActivity.class, args);
                break;
            case R.id.rl_fixed_2:
                Bundle args2 = new Bundle();
                args2.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,
                        highestRateFixBidBean.getBid_id());
                args2.putString(BidFixedActivity.BID_NAME,
                        highestRateFixBidBean.getBid_name());
                startActivity(BidFixedActivity.class, args2);
        }
    }

    /**
     * 刷新期限&收益TextView显示
     * 0 没有排序
     * 1 收益 正序
     * 2 收益 倒序
     * 3 期限 正序
     * 4 期限 倒序
     */
    private void notifyIncomeAndDuration() {
        switch (incomeAndDuration) {
            case 1:
                tv_income.setTextColor(getResources().getColor(R.color.colorTextRed));
                tv_duration.setTextColor(getResources().getColor(R.color.colorTextSecondary));
                iv_income.setImageResource(R.mipmap.redup);
                iv_duration.setImageResource(R.mipmap.order);

                tv_income_top.setTextColor(getResources().getColor(R.color.colorTextRed));
                tv_duration_top.setTextColor(getResources().getColor(R.color.colorTextSecondary));
                iv_income_top.setImageResource(R.mipmap.redup);
                iv_duration_top.setImageResource(R.mipmap.order);
                break;
            case 2:
                tv_income.setTextColor(getResources().getColor(R.color.colorTextRed));
                tv_duration.setTextColor(getResources().getColor(R.color.colorTextSecondary));
                iv_income.setImageResource(R.mipmap.reddown);
                iv_duration.setImageResource(R.mipmap.order);

                tv_income_top.setTextColor(getResources().getColor(R.color.colorTextRed));
                tv_duration_top.setTextColor(getResources().getColor(R.color.colorTextSecondary));
                iv_income_top.setImageResource(R.mipmap.reddown);
                iv_duration_top.setImageResource(R.mipmap.order);
                break;
            case 3:
                tv_income.setTextColor(getResources().getColor(R.color.colorTextSecondary));
                tv_duration.setTextColor(getResources().getColor(R.color.colorTextRed));
                iv_income.setImageResource(R.mipmap.order);
                iv_duration.setImageResource(R.mipmap.redup);

                tv_income_top.setTextColor(getResources().getColor(R.color.colorTextSecondary));
                tv_duration_top.setTextColor(getResources().getColor(R.color.colorTextRed));
                iv_income_top.setImageResource(R.mipmap.order);
                iv_duration_top.setImageResource(R.mipmap.redup);
                break;
            case 4:
                tv_income.setTextColor(getResources().getColor(R.color.colorTextSecondary));
                tv_duration.setTextColor(getResources().getColor(R.color.colorTextRed));
                iv_income.setImageResource(R.mipmap.order);
                iv_duration.setImageResource(R.mipmap.reddown);

                tv_income_top.setTextColor(getResources().getColor(R.color.colorTextSecondary));
                tv_duration_top.setTextColor(getResources().getColor(R.color.colorTextRed));
                iv_income_top.setImageResource(R.mipmap.order);
                iv_duration_top.setImageResource(R.mipmap.reddown);
                break;
            default:
                tv_income.setTextColor(getResources().getColor(R.color.colorTextSecondary));
                tv_duration.setTextColor(getResources().getColor(R.color.colorTextSecondary));
                iv_income.setImageResource(R.mipmap.order);
                iv_duration.setImageResource(R.mipmap.order);

                tv_income_top.setTextColor(getResources().getColor(R.color.colorTextSecondary));
                tv_duration_top.setTextColor(getResources().getColor(R.color.colorTextSecondary));
                iv_income_top.setImageResource(R.mipmap.order);
                iv_duration_top.setImageResource(R.mipmap.order);
                break;
        }
        //initHeader();
        isRefresh = false;
        initData(false);
    }

    private Map<String, String> selectedMap;

    //分页
    private int page = 1;

    //是否是新手标
    private int new_user = 0;

    /**
     * 上拉加载
     *
     * @param adapter 进行上拉加载的adapter
     */
    private void pullToRefresh(final CanBindTwiceAdapter adapter) {

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                final FixedActivity activity = FixedActivity.this;
                selectedMap = activity.getBid_list_paras();

                //收益和排序
                String order_by_name = "";
                String order_by_type = "";

                switch (incomeAndDuration) {
                    case 1:
                        order_by_name = "interest";
                        order_by_type = "asc";
                        break;
                    case 2:
                        order_by_name = "interest";
                        order_by_type = "desc";
                        break;
                    case 3:
                        order_by_name = "duration";
                        order_by_type = "asc";
                        break;
                    case 4:
                        order_by_name = "duration";
                        order_by_type = "desc";
                        break;
                }

                //右侧筛选
                StringBuilder paras = new StringBuilder();

                if (selectedMap != null && selectedMap.size() > 0) {
                    for (Map.Entry entry : selectedMap.entrySet()) {
                        paras.append(entry.getValue());
                    }
                }

                OkGo.get(ApiUtils.getBidList(activity,
                        order_by_name, order_by_type, new_user, page + 1, 10) + paras)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                //加载成功，页数自增
                                page++;

                                discoverBidFixed = ParseUtils.parseByGson(s, DiscoverBidFixed.class);
                                fixedListBean = discoverBidFixed.getBid_list();
                                if (fixedListBean == null || fixedListBean.size() < 1) {
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
                                                RecyclerView.LayoutManager manager = fixed_list.getLayoutManager();
                                                if (manager == null) return;
                                                if (manager instanceof LinearLayoutManager) {
                                                    final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) manager;
                                                    fixed_list.postDelayed(new Runnable() {
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
                                } else {
                                    adapter.addData(fixedListBean);
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
        }, fixed_list);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        FixedActivity activity = FixedActivity.this;
        if (adapter instanceof DiscoverBidFixedAdapter) {
            Bundle args = new Bundle();
            args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,
                    fixedAdapter.getItem(position).getBid_id());
            args.putString(BidFixedActivity.BID_NAME,
                    fixedAdapter.getItem(position).getBid_name());
            activity.startActivity(BidFixedActivity.class, args);
        }
    }

    public void openDrawableLayout() {

        if (mBidDrawableFragment == null)
            return;

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.layout_drawable_open,
                mBidDrawableFragment).commit();
        drawer_layout_platform.openDrawer(Gravity.END);
    }

    public DrawerLayout getDrawableLayout() {
        return drawer_layout_platform;
    }

    private BidDrawableFragment mBidDrawableFragment;

    public Map<String, String> getBid_list_paras() {
        if (mBidDrawableFragment != null) {
            return mBidDrawableFragment.getSelectedMap();
        }
        return null;
    }


}
