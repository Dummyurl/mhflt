package com.changju.fanlitou.activity.classifylist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.bid.BidCurrentActivity;
import com.changju.fanlitou.activity.bid.BidFixedActivity;
import com.changju.fanlitou.activity.bid.BidGoldActivity;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.homeclassify.Banner;
import com.changju.fanlitou.bean.homeclassify.HomeCurrent;
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

public class CurrentActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {

    public static final String CURRENT = "current";
    private TextView tv_classify_current_banner;

    private LinearLayout head, foot;
    private RecyclerView current_list;
    private RecyclerView current_list_gold;
    private SmartRefreshLayout refreshLayout;

    private SimpleAdapter adapter;
    private SimpleAdapter2 adapter2;

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
        return R.layout.activity_classify_list_current;
    }

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "活期二级页");

        findViewById(R.id.iv_back_current).setOnClickListener(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        head = (LinearLayout) inflater.inflate(R.layout.classify_head_current, null);
        tv_classify_current_banner = (TextView) (head.findViewById(R.id.tv_classify_current_banner));
        foot = (LinearLayout) inflater.inflate(R.layout.classify_foot_current, null);
        current_list = (RecyclerView) findViewById(R.id.current_list);
        current_list_gold = (RecyclerView) foot.findViewById(R.id.current_list_gold);


        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);

        refreshLayout = (SmartRefreshLayout) findViewById(R.id.current_refresh_layout);
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
            case R.id.iv_back_current:
                finish();
                break;
            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                include_load_error.setVisibility(View.GONE);
                doBusiness(this);
                break;
        }

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        CurrentActivity activity = CurrentActivity.this;
        //活期列表 跳BidCurrentActivity
        if (adapter instanceof SimpleAdapter) {
            SimpleAdapter adapter1 = (SimpleAdapter) adapter;
            Bundle args = new Bundle();
            args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,
                    adapter1.getItem(position).getBid_id());
            args.putString(BidFixedActivity.BID_NAME,
                    adapter1.getItem(position).getName());
            activity.startActivity(BidCurrentActivity.class, args);
        }
        //黄金活期列表 跳BidGoldActivity
        if (adapter instanceof SimpleAdapter2) {
            SimpleAdapter2 adapter1 = (SimpleAdapter2) adapter;
            Bundle args = new Bundle();
            args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,
                    adapter1.getItem(position).getBid_id());
            args.putString(BidFixedActivity.BID_NAME,
                    adapter1.getItem(position).getName());
            activity.startActivity(BidGoldActivity.class, args);
        }
    }

    class SimpleAdapter extends BaseQuickAdapter<HomeCurrent.CurrentBidsBean, BaseViewHolder> {

        public SimpleAdapter(@Nullable List<HomeCurrent.CurrentBidsBean> data) {
            super(R.layout.classify_listitem_current, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeCurrent.CurrentBidsBean item) {
            helper.setText(R.id.tv_bid_name, item.getName())
                    .setText(R.id.tv_num1, item.getInterest() + "")
                    .setText(R.id.tv_num3, item.getPlatform_bonus_interest() + "")
                    .setText(R.id.tv_dsfe, item.getShare_name() + item.getShare_amount() + "");
        }
    }

    class SimpleAdapter2 extends BaseQuickAdapter<HomeCurrent.CurrentGoldBidsBean, BaseViewHolder> {

        public SimpleAdapter2(@Nullable List<HomeCurrent.CurrentGoldBidsBean> data) {
            super(R.layout.classify_listitem_current_gold, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeCurrent.CurrentGoldBidsBean item) {
            helper.setText(R.id.current_price, item.getCurrent_price() + "")
                    .setText(R.id.interest, item.getInterest() + "%")
                    .setText(R.id.platform_bonus_interest, item.getPlatform_bonus_interest() + "%");
        }
    }

    private void initData() {
        final Context context = CurrentActivity.this;

        OkGo.get(ApiUtils.getClassifyBanner(context, CURRENT)).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Banner banner = ParseUtils.parseByGson(s, Banner.class);
                tv_classify_current_banner.setText(banner.getTitle());
                Glide.with(getApplicationContext()).load(banner.getUrl())
                        .into(new SimpleTarget<GlideDrawable>() {
                            @Override
                            public void onResourceReady(GlideDrawable resource,
                                                        GlideAnimation<? super GlideDrawable> glideAnimation) {
                                tv_classify_current_banner.setBackground(resource);
                            }
                        });
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                tv_classify_current_banner.setBackground(getResources().getDrawable(R.mipmap.ic_launcher));
            }
        });

        OkGo.get(ApiUtils.getHomeCurrent(context)).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                HomeCurrent homeCurrent = ParseUtils.parseByGson(s, HomeCurrent.class);
                List<HomeCurrent.CurrentBidsBean> currentBidsBeanList = homeCurrent.getCurrent_bids();
                List<HomeCurrent.CurrentGoldBidsBean> currentGoldBidsBeanList = homeCurrent.getCurrent_gold_bids();
                current_list.setLayoutManager(new WrapContentLinearLayoutManager(context));
                current_list_gold.setLayoutManager(new WrapContentLinearLayoutManager(context));

                if (adapter == null) {
                    adapter = new SimpleAdapter(currentBidsBeanList);
                    //这里设置点击 点击应该是要跳转到黄金特定标的的详情页面
                    adapter.setOnItemClickListener(CurrentActivity.this);
                    adapter.bindToRecyclerView(current_list);
                } else {
                    adapter.setNewData(currentBidsBeanList);
                }
                adapter.setEmptyView(R.layout.view_empty);
                adapter.removeAllHeaderView();
                adapter.setHeaderView(head);
                adapter.setFooterView(foot);
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.GONE);

                if (adapter2 == null) {
                    adapter2 = new SimpleAdapter2(currentGoldBidsBeanList);
                    //这里设置点击 点击应该是要跳转到黄金特定标的的详情页面
                    adapter2.setOnItemClickListener(CurrentActivity.this);
                    adapter2.bindToRecyclerView(current_list_gold);
                } else {
                    adapter2.setNewData(currentGoldBidsBeanList);
                }

                if (isRefresh) {
                    refreshLayout.finishRefresh();
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                NormalUtils.showToast(CurrentActivity.this, R.string.net_error);
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.VISIBLE);

                if (isRefresh) {
                    refreshLayout.finishRefresh();
                }
            }
        });
    }
}
