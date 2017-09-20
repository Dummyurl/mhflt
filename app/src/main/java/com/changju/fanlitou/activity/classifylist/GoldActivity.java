package com.changju.fanlitou.activity.classifylist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.bid.BidFixedActivity;
import com.changju.fanlitou.activity.bid.BidGoldActivity;
import com.changju.fanlitou.adapter.BannerAdapter;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.homeclassify.Banner;
import com.changju.fanlitou.bean.homeclassify.HomeGold;
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

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/5/25.
 */

public class GoldActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {

    private AnimDialog mAnimDialog;
    public static final String GOLD = "gold";
    private TextView tv_classify_gold_banner;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private RecyclerView mGuanjiaRecycler;
    private RecyclerView mQianbaoRecycler;
    private SimpleAdapter adapter1, adapter2;
    private HomeGold homeGold1, homeGold2;
    private LinearLayout ll_guanjia, ll_qianbao;
    //黄金管家head
    private RelativeLayout current_layout_root;
    private TextView platform_name_plus_name;
    private TextView current_price;
    private TextView bid_interest_gold;
    private TextView bonus_interest_gold;
    private ImageView img_url;

    //黄金钱包head
    private RelativeLayout current_layout_root2;
    private LinearLayout new_layout_root;
    private LinearLayout tejia_layout_root;
    private TextView platform_name_plus_name2;
    private TextView current_price2;
    private TextView bid_interest_gold2;
    private TextView bonus_interest_gold2;
    private ImageView img_url2;

    private TextView platform_name;
    private TextView now_gold_price;
    private TextView bid_interest;
    private TextView invest_qixian;

    private TextView platform_name2;
    private TextView now_gold_price2;
    private TextView bid_interest2;
    private TextView invest_qixian2;

    //loading&error
    private View include;
    private View include_load_error;
    private SmartRefreshLayout refreshLayout;

    private boolean isRefresh;

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_classify_list_gold;
    }

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "黄金二级页");

        findViewById(R.id.iv_back_gold).setOnClickListener(this);

        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);

        tv_classify_gold_banner = (TextView) findViewById(R.id.tv_classify_gold_banner);
        mViewPager = (ViewPager) findViewById(R.id.viewpager_gold);
        mTabLayout = (TabLayout) findViewById(R.id.tab_classify_gold);

        List<View> lists = new ArrayList<>();
        mGuanjiaRecycler = new RecyclerView(this);
        mGuanjiaRecycler.setOverScrollMode(NestedScrollView.OVER_SCROLL_NEVER);
        mGuanjiaRecycler.setLayoutManager(new WrapContentLinearLayoutManager(this));
        mQianbaoRecycler = new RecyclerView(this);
        mQianbaoRecycler.setOverScrollMode(NestedScrollView.OVER_SCROLL_NEVER);
        mQianbaoRecycler.setLayoutManager(new WrapContentLinearLayoutManager(this));

        adapter1 = new SimpleAdapter(null);
        adapter1.setOnItemClickListener(this);
        adapter1.bindToRecyclerView(mGuanjiaRecycler);

        adapter2 = new SimpleAdapter(null);
        adapter2.setOnItemClickListener(this);
        adapter2.bindToRecyclerView(mQianbaoRecycler);

        lists.add(mGuanjiaRecycler);
        lists.add(mQianbaoRecycler);

        mViewPager.setAdapter(new BannerAdapter(
                lists, new String[]{"黄金管家", "黄金钱包"}));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                //Log.e("zm",pos+"");
                if (pos == 0 && homeGold1 == null) {
                    if (mAnimDialog == null)
                        mAnimDialog = AnimDialog.showDialog(GoldActivity.this);
                    mAnimDialog.show();
                    isRefresh = false;
                    initBean(pos, "aujin");
                } else if (pos == 1 && homeGold2 == null) {
                    if (mAnimDialog == null)
                        mAnimDialog = AnimDialog.showDialog(GoldActivity.this);
                    mAnimDialog.show();
                    isRefresh = false;
                    initBean(pos, "g-banker");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        ll_guanjia = (LinearLayout) inflater.inflate(R.layout.classify_head_gold_guanjia, null);
        ll_qianbao = (LinearLayout) inflater.inflate(R.layout.classify_head_gold_qianbao, null);

        //黄金管家的head里的控件
        current_layout_root = (RelativeLayout) ll_guanjia.findViewById(R.id.current_layout_root);
        current_layout_root.setOnClickListener(this);
        platform_name_plus_name = (TextView) ll_guanjia.findViewById(R.id.platform_name_plus_name);
        current_price = (TextView) ll_guanjia.findViewById(R.id.current_price);
        bid_interest_gold = (TextView) ll_guanjia.findViewById(R.id.bid_interest_gold);
        bonus_interest_gold = (TextView) ll_guanjia.findViewById(R.id.bonus_interest_gold);
        img_url = (ImageView) ll_guanjia.findViewById(R.id.img_url);
        adapter1.setHeaderView(ll_guanjia);
        //无效 ？？？
//        adapter1.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                switch(view.getId()){
//                    case R.id.current_layout_root:
//                        Bundle args = new Bundle();
//                        args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,homeGold1.getCurrent_bid_data().getBid_id());
//                        GoldActivity.this.startActivity(BidGoldActivity.class, args);
//                }
//            }
//        });

        //黄金钱包的head里的控件
        current_layout_root2 = (RelativeLayout) ll_qianbao.findViewById(R.id.current_layout_root2);
        new_layout_root = (LinearLayout) ll_qianbao.findViewById(R.id.new_layout_root);
        tejia_layout_root = (LinearLayout) ll_qianbao.findViewById(R.id.tejia_layout_root);
        current_layout_root2.setOnClickListener(this);
        new_layout_root.setOnClickListener(this);
        tejia_layout_root.setOnClickListener(this);

        platform_name_plus_name2 = (TextView) ll_qianbao.findViewById(R.id.platform_name_plus_name);
        current_price2 = (TextView) ll_qianbao.findViewById(R.id.current_price);
        bid_interest_gold2 = (TextView) ll_qianbao.findViewById(R.id.bid_interest_gold);
        bonus_interest_gold2 = (TextView) ll_qianbao.findViewById(R.id.bonus_interest_gold);
        img_url2 = (ImageView) ll_qianbao.findViewById(R.id.img_url);

        platform_name = (TextView) ll_qianbao.findViewById(R.id.platform_name);
        now_gold_price = (TextView) ll_qianbao.findViewById(R.id.now_gold_price);
        bid_interest = (TextView) ll_qianbao.findViewById(R.id.bid_interest);
        invest_qixian = (TextView) ll_qianbao.findViewById(R.id.invest_qixian);

        platform_name2 = (TextView) ll_qianbao.findViewById(R.id.platform_name2);
        now_gold_price2 = (TextView) ll_qianbao.findViewById(R.id.now_gold_price2);
        bid_interest2 = (TextView) ll_qianbao.findViewById(R.id.bid_interest2);
        invest_qixian2 = (TextView) ll_qianbao.findViewById(R.id.invest_qixian2);

        adapter2.setHeaderView(ll_qianbao);

        refreshLayout = (SmartRefreshLayout) findViewById(R.id.gold_refresh_layout);
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
                        if (mViewPager.getCurrentItem() == 0) {
                            initBean(0, "aujin");
                        } else {
                            initBean(1, "g-banker");
                        }
                    }
                }, 1000);
            }
        });
    }

    private void initBean(final int pos, String s) {
        OkGo.get(ApiUtils.getHomeGold(GoldActivity.this, s)).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                if (pos == 0) {
                    homeGold1 = ParseUtils.parseByGson(s, HomeGold.class);
                    adapter1.setNewData(homeGold1.getFix_bid_list_data());
                    initGuanjiaHead(homeGold1);
                    adapter1.setEmptyView(R.layout.view_empty);
                } else if (pos == 1) {
                    homeGold2 = ParseUtils.parseByGson(s, HomeGold.class);
                    adapter2.setNewData(homeGold2.getFix_bid_list_data());
                    initQianbaoHead(homeGold2);
                    adapter2.setEmptyView(R.layout.view_empty);
                }
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.GONE);
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
                NormalUtils.showToast(GoldActivity.this, R.string.net_error);
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.VISIBLE);
                if (mAnimDialog != null && mAnimDialog.isShowing()) {
                    mAnimDialog.dismiss();
                }

                if (isRefresh) {
                    refreshLayout.finishRefresh();
                }
            }
        });
    }

    private void initGuanjiaHead(HomeGold homeGold1) {
        HomeGold.CurrentBidDataBean currentBidDataBean = homeGold1.getCurrent_bid_data();
        platform_name_plus_name.setText(
                currentBidDataBean.getPlatform().getName() + "   " + currentBidDataBean.getName());
        current_price.setText("金价" + currentBidDataBean.getCurrent_price() + "元/克");
        bid_interest_gold.setText(currentBidDataBean.getInterest() + "");
        bonus_interest_gold.setText(" +" + currentBidDataBean.getPlatform_bonus_interest() + "%");
        Glide.with(getApplicationContext()).load(currentBidDataBean.getImg_url()).into(img_url);
    }

    private void initQianbaoHead(HomeGold homeGold2) {
        HomeGold.CurrentBidDataBean currentBidDataBean = homeGold2.getCurrent_bid_data();
        HomeGold.FixNewUserBidDataBean fixNewUserBidDataBean = homeGold2.getFix_new_user_bid_data();
        HomeGold.FixBargainPriceBidDataBean fixBargainPriceBidDataBean = homeGold2.getFix_bargain_price_bid_data();

        platform_name_plus_name2.setText(
                currentBidDataBean.getPlatform().getName() + "   " + currentBidDataBean.getName());
        current_price2.setText("金价" + currentBidDataBean.getCurrent_price() + "元/克");
        bid_interest_gold2.setText(currentBidDataBean.getInterest() + "");
        bonus_interest_gold2.setText(" +" + currentBidDataBean.getPlatform_bonus_interest() + "%");
        Glide.with(getApplicationContext()).load(currentBidDataBean.getImg_url()).into(img_url2);

        platform_name.setText(fixNewUserBidDataBean.getName());
        now_gold_price.setText(fixNewUserBidDataBean.getCurrent_price());
        bid_interest.setText(fixNewUserBidDataBean.getPlatform_bonus_interest() + "%");
        invest_qixian.setText((int) (Float.parseFloat(fixNewUserBidDataBean.getDuration()))
                + fixNewUserBidDataBean.getDuration_unit_str());

        platform_name2.setText(fixBargainPriceBidDataBean.getName());
        now_gold_price2.setText(fixBargainPriceBidDataBean.getCurrent_price());
        bid_interest2.setText(fixBargainPriceBidDataBean.getPlatform_bonus_interest() + "%");
        invest_qixian2.setText((int) (Float.parseFloat(fixBargainPriceBidDataBean.getDuration()))
                + fixBargainPriceBidDataBean.getDuration_unit_str());

    }

    @Override
    public void doBusiness(Context mContext) {
        isRefresh = false;
        initData();
        //初始时加载黄金管家
        initBean(0, "aujin");
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_gold:
                finish();
                break;
            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                include_load_error.setVisibility(View.GONE);
                doBusiness(this);
                break;
            case R.id.current_layout_root:
                Bundle args = new Bundle();
                args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, homeGold1.getCurrent_bid_data().getBid_id());
                GoldActivity.this.startActivity(BidGoldActivity.class, args);
                break;
            case R.id.current_layout_root2:
                Bundle args1 = new Bundle();
                args1.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, homeGold2.getCurrent_bid_data().getBid_id());
                GoldActivity.this.startActivity(BidGoldActivity.class, args1);
                break;
            case R.id.new_layout_root:
                Bundle args2 = new Bundle();
                args2.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, homeGold2.getFix_new_user_bid_data().getBid_id());
                GoldActivity.this.startActivity(BidGoldActivity.class, args2);
                break;
            case R.id.tejia_layout_root:
                Bundle args3 = new Bundle();
                args3.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, homeGold2.getFix_bargain_price_bid_data().getBid_id());
                GoldActivity.this.startActivity(BidGoldActivity.class, args3);
                break;
        }
    }

    private void initData() {
        final Context context = GoldActivity.this;
        //上面的banner
        OkGo.get(ApiUtils.getClassifyBanner(context, GOLD)).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Banner banner = ParseUtils.parseByGson(s, Banner.class);
                tv_classify_gold_banner.setText(banner.getTitle());
                Glide.with(getApplicationContext()).load(banner.getUrl())
                        .into(new SimpleTarget<GlideDrawable>() {
                            @Override
                            public void onResourceReady(GlideDrawable resource,
                                                        GlideAnimation<? super GlideDrawable> glideAnimation) {
                                tv_classify_gold_banner.setBackground(resource);
                            }
                        });
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
            }
        });

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Bundle args = new Bundle();
        args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,
                ((SimpleAdapter) adapter).getItem(position).getBid_id());
        args.putString(BidFixedActivity.BID_NAME,
                ((SimpleAdapter) adapter).getItem(position).getName());
        GoldActivity.this.startActivity(BidGoldActivity.class, args);
    }


    class SimpleAdapter extends BaseQuickAdapter<HomeGold.FixBidListDataBean, BaseViewHolder> {

        public SimpleAdapter(@Nullable List<HomeGold.FixBidListDataBean> data) {
            super(R.layout.classify_listitem_gold, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeGold.FixBidListDataBean item) {
            HomeGold.FixBidListDataBean.PlatformBeanXXX platformBeanX = item.getPlatform();
            //List<HomeGold.FixBidListDataBean.TagListBeanXXX> tagListBeanXList = item.getTag_list();

            //遍历判断是否显示新手标 或按日返
//            for(HomeGold.FixBidListDataBean.TagListBeanXXX tagListBeanX : tagListBeanXList){
//
//                if(tagListBeanX.getName().equals("新手标")){
//                    if( tagListBeanX.isIs_show_dialog() ){
//                        helper.getView(R.id.tag_list_newer).setVisibility(View.VISIBLE);
//                    } else {
//                        helper.getView(R.id.tag_list_newer).setVisibility(View.GONE);
//                    }
//                }
//
//                if(tagListBeanX.getName().equals("按日返")){
//                    if( tagListBeanX.isIs_show_dialog() ){
//                        helper.getView(R.id.tag_list_dayback).setVisibility(View.VISIBLE);
//                    } else {
//                        helper.getView(R.id.tag_list_dayback).setVisibility(View.GONE);
//                    }
//                }
//
//            }
            if (item.getBid_type() == 0) {
                helper.getView(R.id.tag_list_newer).setVisibility(View.VISIBLE);
            } else {
                helper.getView(R.id.tag_list_newer).setVisibility(View.GONE);
            }
            helper.setText(R.id.name, item.getName())
                    .setText(R.id.interest, item.getInterest() + "")
                    .setText(R.id.platform_bonus_interest, " + " + item.getPlatform_bonus_interest())
                    .setText(R.id.duration_plus_duration_unit,
                            (int) (Float.parseFloat(item.getDuration())) + item.getDuration_unit_str());
            ImageView platform_logo_app_square = (helper.getView(R.id.platform_logo_app_square));
            Glide.with(getApplicationContext()).load(platformBeanX.getLogo_app_square()).into(platform_logo_app_square);
        }

    }
}
