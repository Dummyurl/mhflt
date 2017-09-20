package com.changju.fanlitou.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.MainActivity;
import com.changju.fanlitou.activity.intelligent.IntelligentWithdrawActivity;
import com.changju.fanlitou.activity.platform.PlatformDetailActivity;
import com.changju.fanlitou.adapter.BannerAdapter;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.mine.AccountManagerNormal;
import com.changju.fanlitou.bean.mine.AccountManagerQuick;
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

/**
 * Created by chengww on 2017/7/12.
 */

public class PlatformManagerActivity extends BaseActivity {

    private NormalAdapter normalAdapter;
    private QuickAdapter quickAdapter;

    private boolean[] isFirsts = new boolean[]{true, true};

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private SmartRefreshLayout refreshLayout;

    private int[] pages = new int[]{1, 1};

    private AnimDialog mAnimDialog;

    //默认进入的tab
    private int defaultTab = -1;

    private boolean isRefresh;

    @Override
    public void initParams(Bundle params) {
        defaultTab = params.getInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_platform_manager;
    }

    @Override
    public void initView(View view) {

        UserUtils.checkLogin(this, PlatformManagerActivity.class);

        GrowingIO.getInstance().setPageName(this, "我的-平台管理");

        mViewPager = (ViewPager) findViewById(R.id.viewpage_red_bag);
        mTabLayout = (TabLayout) findViewById(R.id.tab_red_bag);

        findViewById(R.id.iv_back_my_account).setOnClickListener(this);
        mAnimDialog = AnimDialog.showDialog(this);

        refreshLayout = (SmartRefreshLayout) findViewById(R.id.platform_manager_refresh_layout);
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
                        if (mViewPager.getCurrentItem() == 0) {
                            bindData(PlatformManagerActivity.this, 0);
                        } else if (mViewPager.getCurrentItem() == 1) {
                            bindData(PlatformManagerActivity.this, 1);
                        }
                    }
                }, 1000);
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {
        final List<View> lists = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            final RecyclerView mRecyclerView = new RecyclerView(this);
            mRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(this));
            mRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
            switch (i) {
                case 0:
                    normalAdapter = new NormalAdapter(null);
                    normalAdapter.bindToRecyclerView(mRecyclerView);
                    normalAdapter.setEmptyView(R.layout.view_load_error);
                    normalAdapter.getEmptyView().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            isRefresh = false;
                            bindData(PlatformManagerActivity.this, 0);
                        }
                    });
                    //下拉刷新监听
                    pullToRefresh(mContext, i, normalAdapter, mRecyclerView);

                    break;
                case 1:
                    quickAdapter = new QuickAdapter(null);
                    quickAdapter.bindToRecyclerView(mRecyclerView);
                    quickAdapter.setEmptyView(R.layout.view_load_error);
                    quickAdapter.getEmptyView().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            isRefresh = false;
                            bindData(PlatformManagerActivity.this, 1);
                        }
                    });
                    View view = new View(this);
                    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            NormalUtils.dp2px(this, 15));
                    view.setLayoutParams(params);
                    quickAdapter.setFooterView(view);

                    //下拉刷新监听
                    pullToRefresh(mContext, i, quickAdapter, mRecyclerView);
                    break;
            }

            lists.add(mRecyclerView);

        }
        mViewPager.setAdapter(new BannerAdapter(
                lists, new String[]{"普通投资", "快捷投资"}));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        if (isFirsts[0]) {
                            isRefresh = false;
                            bindData(PlatformManagerActivity.this, 0);
                            isFirsts[0] = false;

                        }

                        break;
                    case 1:
                        if (isFirsts[1]) {
                            isRefresh = false;
                            bindData(PlatformManagerActivity.this, 1);
                            isFirsts[1] = false;
                        }
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mTabLayout.setupWithViewPager(mViewPager);


    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_my_account:
                startToMinePage();
                break;
        }
    }

    class NormalAdapter extends BaseQuickAdapter<AccountManagerNormal.RegisterRecordBean, BaseViewHolder> {

        public NormalAdapter(@Nullable List<AccountManagerNormal.RegisterRecordBean> data) {
            super(R.layout.recycler_item_platform_manager_normal, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final AccountManagerNormal.RegisterRecordBean item) {
            helper.setText(R.id.tv_date, item.getRegister_time())
                    .setText(R.id.tv_platform_name, item.getPlatform_name())
                    .setText(R.id.tv_remark, item.getRemark())
                    .setText(R.id.tv_is_enjoy_bonus, item.getIs_enjoy_bonus());

            TextView enjoy_bonus = helper.getView(R.id.tv_is_enjoy_bonus);
            if (item.isBonus_red()) {
                enjoy_bonus.setTextColor(getResources().getColor(R.color.colorTextRed));
            } else {
                enjoy_bonus.setTextColor(getResources().getColor(R.color.colorBidName));
            }

            ImageView platformLogo = helper.getView(R.id.iv_platform_logo);
            Glide.with(getApplicationContext())
                    .load(item.getPlatform_logo())
                    .into(platformLogo);

            helper.getView(R.id.layout_root).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle args = new Bundle();
                    args.putInt(PlatformDetailActivity.PLATFORM_ID, item.getPlatform_id());
                    args.putString(PlatformDetailActivity.PLATFORM_NAME, item.getPlatform_name());
                    Intent intent = new Intent(PlatformManagerActivity.this, PlatformDetailActivity.class);
                    intent.putExtras(args);
                    PlatformManagerActivity.this.startActivity(intent);
                }
            });
        }
    }

    class QuickAdapter extends BaseQuickAdapter<AccountManagerQuick.RegisterRecordBean, BaseViewHolder> {

        public QuickAdapter(@Nullable List<AccountManagerQuick.RegisterRecordBean> data) {
            super(R.layout.recycler_item_platform_manager_quick, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final AccountManagerQuick.RegisterRecordBean item) {
            helper.setText(R.id.tv_balance, item.getBalance())
                    .setText(R.id.tv_platform_name, item.getPlatform_name())
                    .setText(R.id.tv_receiving_principal_interest, item.getReceiving_principal_interest())
                    .setText(R.id.tv_invest_bids, item.getInvest_bids());

            if (TextUtils.isEmpty(item.getBank_account_no())) {
                helper.setText(R.id.tv_bank_info, "暂未绑定银行卡");
            } else {
                helper.setText(R.id.tv_bank_info, item.getBank_name() + "：" + item.getBank_account_no());
            }

            ImageView platformLogo = helper.getView(R.id.iv_platform_logo);
            Glide.with(getApplicationContext())
                    .load(item.getPlatform_app_logo())
                    .into(platformLogo);

            TextView btn_withdraw = helper.getView(R.id.btn_withdraw);

            float balance;
            if (TextUtils.isEmpty(item.getBalance())) {
                balance = 0;
            } else {
                balance = Float.valueOf(item.getBalance());
            }

            btn_withdraw.setEnabled(balance > 0);

            btn_withdraw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle args = new Bundle();
                    args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, item.getPlatform_id());
                    PlatformManagerActivity.this.startActivity(
                            IntelligentWithdrawActivity.class, args);
                }
            });

            View root = helper.getView(R.id.layout_root);
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle args = new Bundle();
                    args.putInt(PlatformDetailActivity.PLATFORM_ID, item.getPlatform_id());
                    args.putString(PlatformDetailActivity.PLATFORM_NAME, item.getPlatform_name());
                    Intent intent = new Intent(PlatformManagerActivity.this, PlatformDetailActivity.class);
                    intent.putExtras(args);
                    PlatformManagerActivity.this.startActivity(intent);
                }
            });
        }
    }

    private void pullToRefresh(final Context mContext, final int finalI, final BaseQuickAdapter adapter, final RecyclerView mRecyclerView) {
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                String type = "";
                if (finalI == 1)
                    type = "quick";
                else {
                    type = "normal";
                }
                OkGo.get(ApiUtils.getAccountManagement(mContext, type, pages[finalI] + 1))
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                if (finalI == 1) {
                                    AccountManagerQuick quick =
                                            ParseUtils.parseByGson(s, AccountManagerQuick.class);
                                    if (quick.getRegister_record() == null || quick.getRegister_record().size() < 1 || quick.getTotal_count() < 1) {
                                        loadMoreEnd(adapter, mRecyclerView);
                                    } else {
                                        pages[finalI] = pages[finalI] + 1;
                                        quickAdapter.addData(quick.getRegister_record());
                                        adapter.loadMoreComplete();
                                    }
                                } else {
                                    AccountManagerNormal normal =
                                            ParseUtils.parseByGson(s, AccountManagerNormal.class);
                                    if (normal.getRegister_record() == null || normal.getRegister_record().size() < 1 || normal.getTotal_count() < 1) {
                                        loadMoreEnd(adapter, mRecyclerView);
                                    } else {
                                        pages[finalI] = pages[finalI] + 1;
                                        normalAdapter.addData(normal.getRegister_record());
                                        adapter.loadMoreComplete();
                                    }
                                }

                            }

                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                super.onError(call, response, e);
                                NormalUtils.showToast(mContext, R.string.net_error);
                                adapter.loadMoreFail();
                            }
                        });
            }
        }, mRecyclerView);

    }

    private void bindData(final Context mContext, final int i) {
        if (!isRefresh) {
            mAnimDialog.show();
        }

        String type = "";
        if (i == 1)
            type = "quick";
        else {
            type = "normal";
        }

        pages[i] = 1;

        OkGo.get(ApiUtils.getAccountManagement(mContext, type, pages[i]))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        if (i == 1) {
                            AccountManagerQuick quick =
                                    ParseUtils.parseByGson(s, AccountManagerQuick.class);
                            quickAdapter.setNewData(quick.getRegister_record());
                            quickAdapter.setEmptyView(R.layout.view_empty);
                            TextView tv = (TextView) quickAdapter.getEmptyView().
                                    findViewById(R.id.tv_content);
                            tv.setText("暂未注册平台");
                        } else {
                            AccountManagerNormal normal =
                                    ParseUtils.parseByGson(s, AccountManagerNormal.class);
                            normalAdapter.setNewData(normal.getRegister_record());
                            normalAdapter.setEmptyView(R.layout.view_empty);
                            TextView tv = (TextView) normalAdapter.getEmptyView().
                                    findViewById(R.id.tv_content);
                            tv.setText("暂未注册平台");

                        }
                        if (mAnimDialog != null && mAnimDialog.isShowing()) {
                            mAnimDialog.dismiss();
                        }

                        if (defaultTab > -1) {
                            mViewPager.setCurrentItem(defaultTab, false);
                            defaultTab = -1;
                        }

                        if (isRefresh) {
                            refreshLayout.finishRefresh();
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        NormalUtils.showToast(mContext, R.string.net_error);
                        if (mAnimDialog != null && mAnimDialog.isShowing()) {
                            mAnimDialog.dismiss();
                        }
                        if (defaultTab > -1) {
                            mViewPager.setCurrentItem(defaultTab, false);
                            defaultTab = -1;
                        }

                        if (isRefresh) {
                            refreshLayout.finishRefresh();
                        }
                    }
                });
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

    private void startToMinePage() {
        Bundle args = new Bundle();
        args.putBoolean(MainActivity.MINE_PAGE, true);
        startActivity(MainActivity.class, args);
    }

    @Override
    public void onBackPressed() {
        startToMinePage();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Bundle args = intent.getExtras();
        if (args != null) {
            defaultTab = args.getInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
            mViewPager.setCurrentItem(defaultTab);
            isRefresh = false;
            bindData(this, defaultTab);
        }
    }
}
