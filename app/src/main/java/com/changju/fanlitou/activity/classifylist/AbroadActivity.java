package com.changju.fanlitou.activity.classifylist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.bid.BidAbroadActivity;
import com.changju.fanlitou.activity.bid.BidFixedActivity;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.bean.homeclassify.Banner;
import com.changju.fanlitou.bean.homeclassify.HomeAbroad;
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

public class AbroadActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {

    public static final String ABROAD = "abroad";
    private TextView tv_classify_abroad_banner;
    private ImageView fenghjr_logo, meixin_logo;
    private RecyclerView fengjr_list, meixin_list;
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
        return R.layout.activity_classify_list_abroad;
    }

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "海外二级页");

        findViewById(R.id.iv_back_abroad).setOnClickListener(this);
        tv_classify_abroad_banner = (TextView) (findViewById(R.id.tv_classify_abroad_banner));
        fenghjr_logo = (ImageView) findViewById(R.id.fenghjr_logo);
        meixin_logo = (ImageView) findViewById(R.id.meixin_logo);
        fengjr_list = (RecyclerView) findViewById(R.id.fengjr_list);
        meixin_list = (RecyclerView) findViewById(R.id.meixin_list);

        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);

        refreshLayout = (SmartRefreshLayout) findViewById(R.id.abroad_refresh_layout);
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
            case R.id.iv_back_abroad:
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
        final Context context = AbroadActivity.this;
        OkGo.get(ApiUtils.getClassifyBanner(context, ABROAD)).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Banner banner = ParseUtils.parseByGson(s, Banner.class);
                tv_classify_abroad_banner.setText(banner.getTitle());
                Glide.with(getApplicationContext()).load(banner.getUrl())
                        .into(new SimpleTarget<GlideDrawable>() {
                            @Override
                            public void onResourceReady(GlideDrawable resource,
                                                        GlideAnimation<? super GlideDrawable> glideAnimation) {
                                tv_classify_abroad_banner.setBackground(resource);
                            }
                        });
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                tv_classify_abroad_banner.setBackground(getResources().getDrawable(R.mipmap.ic_launcher));
            }
        });

        OkGo.get(ApiUtils.getHomeForeign(context)).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                HomeAbroad homeAbroad = ParseUtils.parseByGson(s, HomeAbroad.class);
                //fenghjr数据
                HomeAbroad.FenghjrBidsInfoBean fenghjrBidsInfoBean = homeAbroad.getFenghjr_bids_info();
                HomeAbroad.FenghjrBidsInfoBean.PlatformInfoBean platformInfoBean = fenghjrBidsInfoBean.getPlatform_info();
                List<HomeAbroad.FenghjrBidsInfoBean.BidsListBean> bidsListBeanList = fenghjrBidsInfoBean.getBids_list();
                //meixin数据
                HomeAbroad.MeixinBidsInfoBean meixinBidsInfoBean = homeAbroad.getMeixin_bids_info();
                HomeAbroad.MeixinBidsInfoBean.PlatformInfoBeanX platformInfoBeanX = meixinBidsInfoBean.getPlatform_info();
                List<HomeAbroad.MeixinBidsInfoBean.BidsListBeanX> bidsListBeanXList = meixinBidsInfoBean.getBids_list();

                Glide.with(getApplicationContext()).load(platformInfoBean.getLogo()).into(fenghjr_logo);
                Glide.with(getApplicationContext()).load(platformInfoBeanX.getLogo()).into(meixin_logo);
                fengjr_list.setNestedScrollingEnabled(false);
                meixin_list.setNestedScrollingEnabled(false);
                fengjr_list.setLayoutManager(new GridLayoutManager(context, 2));
                meixin_list.setLayoutManager(new GridLayoutManager(context, 2));
                if (adapter == null) {
                    adapter = new SimpleAdapter(bidsListBeanList);
                    //这里设置点击 点击应跳转到海外特定标的的详情页面
                    adapter.setOnItemClickListener(AbroadActivity.this);
                    adapter.bindToRecyclerView(fengjr_list);
                } else {
                    adapter.setNewData(bidsListBeanList);
                }
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.GONE);

                if (adapter2 == null) {
                    adapter2 = new SimpleAdapter2(bidsListBeanXList);
                    //这里设置点击 点击应跳转到海外特定标的的详情页面
                    adapter2.setOnItemClickListener(AbroadActivity.this);
                    adapter2.bindToRecyclerView(meixin_list);
                } else {
                    adapter2.setNewData(bidsListBeanXList);
                }

                if (isRefresh) {
                    refreshLayout.finishRefresh();
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                NormalUtils.showToast(AbroadActivity.this, R.string.net_error);
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
        AbroadActivity activity = AbroadActivity.this;
        //凤凰金融
        if (adapter instanceof SimpleAdapter) {
            SimpleAdapter adapter1 = (SimpleAdapter) adapter;
            Bundle args = new Bundle();
            args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,
                    adapter1.getItem(position).getBid_id());
            args.putString(BidFixedActivity.BID_NAME, adapter1.getItem(position).getName());
            activity.startActivity(BidAbroadActivity.class, args);
        }
        //美信金融
        if (adapter instanceof SimpleAdapter2) {
            SimpleAdapter2 simpleAdapter = (SimpleAdapter2) adapter;
            Bundle args = new Bundle();
            args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,
                    simpleAdapter.getItem(position).getBid_id());
            args.putString(BidFixedActivity.BID_NAME, simpleAdapter.getItem(position).getName());

            activity.startActivity(BidAbroadActivity.class, args);
        }
    }

    class SimpleAdapter extends BaseQuickAdapter<HomeAbroad.FenghjrBidsInfoBean.BidsListBean, BaseViewHolder> {

        public SimpleAdapter(@Nullable List<HomeAbroad.FenghjrBidsInfoBean.BidsListBean> data) {
            super(R.layout.classify_listitem_abroad, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeAbroad.FenghjrBidsInfoBean.BidsListBean item) {
//            fenghjr_logo = (ImageView)findViewById(R.id.fenghjr_logo);
            helper.setText(R.id.platform_name, item.getPlatform().getName())
                    .setText(R.id.bid_list_name, item.getName())
                    .setText(R.id.bid_interest, Html.fromHtml(item.getInterest() +
                            "<small>%</small><br><small><small><small><font color='#a5a5a5'>年化收益</font></small></small></small></br>"))
                    .setText(R.id.raise_interest, Html.fromHtml(item.getBonus_value() +
                            "<small>%</small><br><small><small><small><font color='#a5a5a5'>返利</font></small></small></small></br>"))
                    .setText(R.id.bid_list_duration, item.getDuration()
                            + item.getDuration_unit_str())
                    .setText(R.id.bid_list_min_amount, item.getMin_amount_str() + "美金");

        }
    }

    class SimpleAdapter2 extends BaseQuickAdapter<HomeAbroad.MeixinBidsInfoBean.BidsListBeanX, BaseViewHolder> {

        public SimpleAdapter2(@Nullable List<HomeAbroad.MeixinBidsInfoBean.BidsListBeanX> data) {
            super(R.layout.classify_listitem_abroad, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeAbroad.MeixinBidsInfoBean.BidsListBeanX item) {
//            fenghjr_logo = (ImageView)findViewById(R.id.fenghjr_logo);
            helper.setText(R.id.platform_name, item.getPlatform().getName())
                    .setText(R.id.bid_list_name, item.getName())
                    .setText(R.id.bid_interest, Html.fromHtml(item.getInterest() +
                            "<small>%</small><br><small><small><small><font color='#a5a5a5'>年化收益</font></small></small></small></br>"))
                    .setText(R.id.raise_interest, Html.fromHtml(item.getBonus_value() +
                            "<small>%</small><br><small><small><small><font color='#a5a5a5'>返利</font></small></small></small></br>"))
                    .setText(R.id.bid_list_duration,
                            item.getDuration() + item.getDuration_unit_str())
                    .setText(R.id.bid_list_min_amount, item.getMin_amount_str() + "美金");
        }
    }

}
