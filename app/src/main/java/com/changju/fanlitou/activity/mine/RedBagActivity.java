package com.changju.fanlitou.activity.mine;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.adapter.BannerAdapter;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.mine.RedBag;
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
 * Created by chengww on 2017/5/26.
 */

public class RedBagActivity extends BaseActivity {

    private SimpleAdapter adapter1, adapter2, adapter3, adapter;

    private AnimDialog mAnimDialog;

    private boolean[] isFirsts = new boolean[]{true, true, true};

    private int defaultTab = -1;

    private SmartRefreshLayout refreshLayout;

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
        return R.layout.activity_red_bag;
    }

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    public void initView(View view) {

        UserUtils.checkLogin(this, RedBagActivity.class);

        GrowingIO.getInstance().setPageName(this, "我的-我的奖励");

        mViewPager = (ViewPager) findViewById(R.id.viewpage_red_bag);
        mTabLayout = (TabLayout) findViewById(R.id.tab_red_bag);
        mAnimDialog = AnimDialog.showDialog(this);
        findViewById(R.id.iv_back_my_account).setOnClickListener(this);

        refreshLayout = (SmartRefreshLayout) findViewById(R.id.red_bag_refresh_layout);
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
                            bindRedBag(RedBagActivity.this, 0);
                        } else if (mViewPager.getCurrentItem() == 1) {
                            bindRedBag(RedBagActivity.this, 1);
                        } else {
                            bindRedBag(RedBagActivity.this, 2);
                        }
                    }
                }, 1000);
            }
        });
    }

    private int[] pages = new int[]{1, 1, 1};

    @Override
    public void doBusiness(final Context mContext) {
        final List<View> lists = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            final int finalI = i;
            final RecyclerView mRecyclerView = new RecyclerView(this);
            mRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
            mRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(this));
            switch (i) {
                case 0:
                    adapter1 = new SimpleAdapter(null);
                    adapter1.bindToRecyclerView(mRecyclerView);
                    adapter1.disableLoadMoreIfNotFullPage();
                    adapter1.setEmptyView(R.layout.view_load_error);
                    adapter1.getEmptyView().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            isRefresh = false;
                            bindRedBag(mContext, finalI);
                        }
                    });
                    //下拉刷新监听
                    pullToRefresh(mContext, i, adapter1, mRecyclerView);
                    break;
                case 1:
                    adapter2 = new SimpleAdapter(null);
                    adapter2.bindToRecyclerView(mRecyclerView);
                    adapter2.disableLoadMoreIfNotFullPage();
                    adapter2.setEmptyView(R.layout.view_load_error);
                    adapter2.getEmptyView().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            isRefresh = false;
                            bindRedBag(mContext, finalI);
                        }
                    });
                    //下拉刷新监听
                    pullToRefresh(mContext, i, adapter2, mRecyclerView);
                    break;
                case 2:
                    adapter3 = new SimpleAdapter(null);
                    adapter3.bindToRecyclerView(mRecyclerView);
                    adapter3.disableLoadMoreIfNotFullPage();
                    adapter3.setEmptyView(R.layout.view_load_error);
                    adapter3.getEmptyView().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            isRefresh = false;
                            bindRedBag(mContext, finalI);
                        }
                    });

                    //下拉刷新监听
                    pullToRefresh(mContext, i, adapter3, mRecyclerView);
                    break;
            }

            lists.add(mRecyclerView);

        }
        mViewPager.setAdapter(new BannerAdapter(
                lists, new String[]{"现金券", "加息券", "家庭体验金"}));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        if (isFirsts[0]) {
                            isRefresh = false;
                            bindRedBag(mContext, 0);
                            isFirsts[0] = false;
                        }

                        break;
                    case 1:
                        if (isFirsts[1]) {
                            isRefresh = false;
                            bindRedBag(mContext, 1);
                            isFirsts[1] = false;
                        }
                        break;
                    case 2:
                        if (isFirsts[2]) {
                            isRefresh = false;
                            bindRedBag(mContext, 2);
                            isFirsts[2] = false;
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

    private void setEmptyView(BaseQuickAdapter adapter, final Context mContext, final int finalI) {
        adapter.setEmptyView(R.layout.view_load_error);
        adapter.getEmptyView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindRedBag(mContext, finalI);
            }
        });
    }

    private void pullToRefresh(final Context mContext, final int finalI, final BaseQuickAdapter adapter, final RecyclerView mRecyclerView) {
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                OkGo.get(ApiUtils.getMyRedPacketInfo(mContext, finalI + 1, pages[finalI] + 1))
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                RedBag redBag = ParseUtils.parseByGson(s, RedBag.class);
                                if (redBag.getRed_packets() == null || redBag.getRed_packets().size() < 1 || redBag.getTotal() < 1) {
                                    loadMoreEnd(adapter, mRecyclerView);
                                } else {

                                    pages[finalI] = pages[finalI] + 1;

                                    adapter.addData(redBag.getRed_packets());
                                    adapter.loadMoreComplete();
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

    private void bindRedBag(final Context mContext, final int i) {
        switch (i) {
            case 0:
                adapter = adapter1;
                break;
            case 1:
                adapter = adapter2;
                break;
            case 2:
                adapter = adapter3;
                break;
        }

        pages[i] = 1;

        if (!isRefresh) {
            mAnimDialog.show();
        }

        OkGo.get(ApiUtils.getMyRedPacketInfo(mContext, (i + 1), pages[i]))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        RedBag redBag = ParseUtils.parseByGson(s, RedBag.class);
                        adapter.setNewData(redBag.getRed_packets());
                        adapter.setEmptyView(R.layout.view_empty);
                        TextView tv = (TextView) adapter.getEmptyView().
                                findViewById(R.id.tv_content);
                        tv.setText("暂无可用的红包");
                        if (mAnimDialog != null && mAnimDialog.isShowing()) {
                            mAnimDialog.dismiss();
                        }
                        if (isRefresh) {
                            refreshLayout.finishRefresh();
                        }
                        if (defaultTab > -1 && defaultTab < 3) {
                            mViewPager.setCurrentItem(defaultTab, false);
                            defaultTab = -1;
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        NormalUtils.showToast(mContext, R.string.net_error);
                        if (mAnimDialog != null && mAnimDialog.isShowing()) {
                            mAnimDialog.dismiss();
                        }
                        if (isRefresh) {
                            refreshLayout.finishRefresh();
                        }

                        if (defaultTab > -1 && defaultTab < 3) {
                            mViewPager.setCurrentItem(defaultTab, false);
                            defaultTab = -1;
                        }

                        setEmptyView(adapter, mContext, i);
                    }
                });
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_my_account:
                finish();
                break;
        }
    }

    class SimpleAdapter extends BaseQuickAdapter<RedBag.RedPacketsBean, BaseViewHolder> {

        public SimpleAdapter(@Nullable List<RedBag.RedPacketsBean> data) {
            super(R.layout.recycler_item_red_bag, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, RedBag.RedPacketsBean item) {
            TextView tv_is_used = helper.getView(R.id.tv_is_used);
            TextView tv_bag_num = helper.getView(R.id.tv_bag_num);
            TextView tv_bag_type = helper.getView(R.id.tv_bag_type);
            TextView tv_data = helper.getView(R.id.tv_data);
            helper.setText(R.id.tv_is_used, item.getStatus_str())
                    .setText(R.id.tv_bag_num, item.getAmount())
                    .setText(R.id.tv_bag_type, item.getType_str())
                    .setText(R.id.tv_data, item.getDate_str());


            List<String> description = item.getDescription();
            TextView tv_tztj = helper.getView(R.id.tv_tztj);
            if (description == null || description.size() == 0) {
                tv_tztj.setVisibility(View.INVISIBLE);
            } else {
                tv_tztj.setVisibility(View.VISIBLE);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < description.size(); i++) {
                    sb.append("· ").append(description.get(i));
                    if (i < description.size() - 1) {
                        sb.append("\n");
                    }
                }
                tv_tztj.setText(sb);
            }

            RelativeLayout layout_root_red_bag = helper.getView(R.id.layout_root_red_bag);
            String class_name = item.getClass_name();
            switch (class_name) {
                case "notUsed":
                    layout_root_red_bag.setBackgroundResource(
                            R.mipmap.bg_red_bag_unused);
                    tv_is_used.setBackgroundResource(R.mipmap.bg_unused);
                    tv_tztj.setTextColor(getResources().getColor(R.color.colorBidName));
                    tv_data.setTextColor(getResources().getColor(R.color.colorBidName));
                    tv_bag_type.setTextColor(getResources().getColor(R.color.colorBidName));
                    tv_bag_num.setTextColor(getResources().getColor(R.color.colorBidName));
                    tv_is_used.setTextColor(getResources().getColor(R.color.colorTextRed));
                    break;
                case "notReceive":
                case "used":
                case "expired":
                    layout_root_red_bag.setBackgroundResource(
                            R.mipmap.bg_red_bag_used);
                    tv_is_used.setBackgroundResource(R.mipmap.bg_used);
                    tv_tztj.setTextColor(getResources().getColor(R.color.colorTextThird));
                    tv_data.setTextColor(getResources().getColor(R.color.colorTextThird));
                    tv_bag_type.setTextColor(getResources().getColor(R.color.colorTextThird));
                    tv_bag_num.setTextColor(getResources().getColor(R.color.colorTextThird));
                    tv_is_used.setTextColor(getResources().getColor(R.color.colorTextThird));
                    break;
            }

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
                            setEmptyFooter(adapter);
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

    private void setEmptyFooter(BaseQuickAdapter adapter) {
        View view = new View(this);
        ViewGroup.LayoutParams para = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, NormalUtils.dp2px(this, 15));
        view.setLayoutParams(para);
        adapter.setFooterView(view);
    }
}
