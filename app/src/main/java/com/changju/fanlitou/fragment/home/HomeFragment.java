package com.changju.fanlitou.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.MainActivity;
import com.changju.fanlitou.activity.WebActivity;
import com.changju.fanlitou.activity.classifylist.AbroadActivity;
import com.changju.fanlitou.activity.classifylist.CrowdFundingActivity;
import com.changju.fanlitou.activity.classifylist.CurrentActivity;
import com.changju.fanlitou.activity.classifylist.FixedActivity;
import com.changju.fanlitou.activity.classifylist.FundActivity;
import com.changju.fanlitou.activity.classifylist.GoldActivity;
import com.changju.fanlitou.activity.classifylist.InsuranceActivity;
import com.changju.fanlitou.activity.classifylist.SilkBagActivity;
import com.changju.fanlitou.activity.platform.PlatformDetailActivity;
import com.changju.fanlitou.base.JavascriptClass;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.MyApplication;
import com.changju.fanlitou.bean.home.HomeBean;
import com.changju.fanlitou.bean.home.RecommendBid;
import com.changju.fanlitou.ui.ImageTextButton;
import com.changju.fanlitou.ui.dialog.CustomDialogFragment;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.FileUtils;
import com.changju.fanlitou.util.NetworkImageHolderView;
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
 * Created by chengww on 2017/5/4.
 */

public class HomeFragment extends LazyFragment implements View.OnTouchListener {

    private ConvenientBanner convenientBanner;

    private TextView tv_favorite_commend_home;

    private SmartRefreshLayout refreshLayout;

    //收藏平台
    private LinearLayout linear_favorite_home;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    public static HomeFragment newInstance(boolean isLazyLoad) {

        Bundle args = new Bundle();
        args.putBoolean(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, isLazyLoad);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private ScrollView scroll_view_home;
    //最后触摸到的X坐标位置
    private float mLastX;

    //是否刷新
    private boolean isRefresh;

    @Override
    public void initView() {

        GrowingIO.getInstance().setPageName(this, "首页");

        tv_favorite_commend_home = (TextView) findViewById(R.id.tv_favorite_commend_home);

        convenientBanner = (ConvenientBanner) findViewById(R.id.convenientBanner);
        linear_favorite_home = (LinearLayout) findViewById(R.id.linear_favorite_home);
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.fragment_home_refresh_layout);
        refreshLayout.setRefreshHeader(new MaterialHeader(activity).setColorSchemeColors(0xfff95353));
        refreshLayout.setEnableHeaderTranslationContent(false);
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                refreshlayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isRefresh = true;
                        initData(activity);
                    }
                }, 1000);
            }
        });

        ImageTextButton btn_fixed_home = (ImageTextButton) findViewById(R.id.btn_fixed_home);
        ImageTextButton btn_fund_home = (ImageTextButton) findViewById(R.id.btn_fund_home);
        ImageTextButton btn_current_home = (ImageTextButton) findViewById(R.id.btn_current_home);
        ImageTextButton btn_gold_home = (ImageTextButton) findViewById(R.id.btn_gold_home);
        ImageTextButton btn_crowd_funding_home = (ImageTextButton) findViewById(R.id.btn_crowd_funding_home);
        ImageTextButton btn_abroad_home = (ImageTextButton) findViewById(R.id.btn_abroad_home);
        ImageTextButton btn_insurance_home = (ImageTextButton) findViewById(R.id.btn_insurance_home);
        ImageTextButton btn_silk_bag_home = (ImageTextButton) findViewById(R.id.btn_silk_bag_home);

        btn_fixed_home.setOnClickListener(this);
        btn_fund_home.setOnClickListener(this);
        btn_current_home.setOnClickListener(this);
        btn_gold_home.setOnClickListener(this);
        btn_crowd_funding_home.setOnClickListener(this);
        btn_abroad_home.setOnClickListener(this);
        btn_insurance_home.setOnClickListener(this);
        btn_silk_bag_home.setOnClickListener(this);

        scroll_view_home = (ScrollView) findViewById(R.id.scroll_view_home);

        // 解决滑动冲突
        scroll_view_home.setOnTouchListener(this);
        findViewById(R.id.horizontal_scroll_home).setOnTouchListener(this);
        // 设置广告轮播图的触摸事件，当用户按下时停止轮播，抬起时继续轮播
        convenientBanner.setOnTouchListener(this);

        Bundle args = getArguments();
        if (args != null) {
            homeBean = args.getParcelable(HOME_BEAN);
        }
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.btn_silk_bag_home:
                startActivity(new Intent(activity, SilkBagActivity.class));
                break;
            case R.id.btn_abroad_home:
                startActivity(new Intent(activity, AbroadActivity.class));
                break;
            case R.id.btn_gold_home:
                startActivity(new Intent(activity, GoldActivity.class));
                break;
            case R.id.btn_crowd_funding_home:
                startActivity(new Intent(activity, CrowdFundingActivity.class));
                break;
            case R.id.btn_fixed_home:
                startActivity(new Intent(activity, FixedActivity.class));
                break;
            case R.id.btn_current_home:
                startActivity(new Intent(activity, CurrentActivity.class));
                break;
            case R.id.btn_insurance_home:
                startActivity(new Intent(activity, InsuranceActivity.class));
                break;
            case R.id.btn_fund_home:
                startActivity(new Intent(activity, FundActivity.class));
                break;
        }
    }

    private List<HomeBean.BannersBean> banners;
    public static final String HOME_BEAN = "HomeBean.json";

    @Override
    public void doBusiness(final Context mContext) {
    }

    public void initData(final Context mContext) {
        OkGo.get(ApiUtils.getHomePage(mContext))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        if (TextUtils.isEmpty(s))
                            return;
                        homeBean = ParseUtils.parseByGson(s, HomeBean.class);
                        bindData();
                        FileUtils.saveData(mContext, HOME_BEAN, s);
                        if (isRefresh) {
                            refreshLayout.finishRefresh();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        NormalUtils.showToast(mContext, R.string.net_error);

                        //缓存数据加载
                        String dataStr = FileUtils.getData(mContext, HOME_BEAN);
                        homeBean = ParseUtils.parseByGson(dataStr, HomeBean.class);
                        if (!TextUtils.isEmpty(dataStr)) {
                            bindData();
                        }

                        if (isRefresh) {
                            refreshLayout.finishRefresh();
                        }
                    }
                });

        //加载推荐标的,待重构成ChildFragment
        initRecommend_bid();

    }

    private HomeBean homeBean;

    private void bindData() {
        if (homeBean == null || !isVisible())
            return;
        banners = homeBean.getBanners();
        initViewPager(banners);
        List<HomeBean.UserFavoritePlatformsBean> userFavoritePlatforms
                = homeBean.getUser_favorite_platforms();
        initUserFavoritePlatforms(userFavoritePlatforms);
//        swipe_refresh_home.stopRefresh(true);
    }

    /**
     * 加载推荐标的,待重构成ChildFragment
     */
    private void initRecommend_bid() {
        OkGo.get(ApiUtils.getHomeBidRecommend())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        bindRecommendBidBean(s);
                        FileUtils.saveData(activity, RECOMMEND_BID_BEAN, s);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        bindRecommendBidBean(FileUtils.getData(activity, RECOMMEND_BID_BEAN));
                    }
                });


    }

    public static final String RECOMMEND_BID_BEAN = "RecommendBidBean.json";

    private void bindRecommendBidBean(String s) {

        if (TextUtils.isEmpty(s))
            return;

        RecommendBid recommend = ParseUtils.parseByGson(s, RecommendBid.class);
        RecommendBid.RecommendBidBean bean = recommend.getRecommend_bid();
        if (bean.isIs_show()) {
            LinearLayout layout_recommend_bid_title_home = (LinearLayout)
                    findViewById(R.id.layout_recommend_bid_title_home);
            if (layout_recommend_bid_title_home != null) {
                TextView text_view = (TextView) layout_recommend_bid_title_home.getChildAt(0);
                text_view.setText(bean.getTitle());
                layout_recommend_bid_title_home.setVisibility(View.VISIBLE);
            }

            //解初始打开fragment未add导致的crash
            if (!isAdded())
                return;
            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
            BidFragment bidFragment = BidFragment.newInstance(s, bean.getBid_type(), false);

            GrowingIO.getInstance().trackFragment(activity, bidFragment);

            //不用add，避免刷新的时候重复创建Fragment
            fragmentTransaction.replace(R.id.frame_recommend_bid, bidFragment);
            fragmentTransaction.commitAllowingStateLoss();
        }

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_home;
    }

    /**
     * 加载推荐收藏平台
     */
    private void initUserFavoritePlatforms(List<HomeBean.UserFavoritePlatformsBean> userFavoritePlatformsBean) {
        //已经有数据了，清空
        if (linear_favorite_home.getChildCount() > 0)
            linear_favorite_home.removeAllViews();

        if (UserUtils.isLogin(activity)) {
            tv_favorite_commend_home.setText("您收藏的平台");
        } else {
            tv_favorite_commend_home.setText("推荐收藏的平台");
        }
        int size = userFavoritePlatformsBean.size();
        //推荐平台padding
        if (MyApplication.ScreenWidth <= 0) {
            MyApplication.ScreenWidth = NormalUtils.getScreenWidth(activity);
        }
        int padding = (MyApplication.ScreenWidth
                - NormalUtils.sp2px(activity, 64) * 4) / 8;
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                final HomeBean.UserFavoritePlatformsBean favorite = userFavoritePlatformsBean.get(i);
                final ImageTextButton imageText = new ImageTextButton(activity);
                initPlatformStyle(activity, imageText);
                imageText.setPadding(padding, 8, padding, 8);
                Glide.with(getApplicationContext()).load(favorite.getLogo_url())
                        .into(new SimpleTarget<GlideDrawable>() {
                            @Override
                            public void onResourceReady(GlideDrawable resource,
                                                        GlideAnimation<? super GlideDrawable> glideAnimation) {
                                imageText.setImageDrawable(resource);
                            }
                        });
                imageText.setTextViewText(favorite.getPlatform_name());
                imageText.isHot(favorite.isIs_hot());
                linear_favorite_home.addView(imageText);
                imageText.setId(i);
                imageText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //platform_type字段加好之后 做跳转到平台详情页面
                        Bundle bundle = new Bundle();

                        //平台名字 "抱财网"等    平台类型"固收"等  平台id 数字
                        String name = favorite.getPlatform_name();
                        int platform_id = favorite.getPlatform_id();

                        bundle.putInt(PlatformDetailActivity.PLATFORM_ID, platform_id);
                        //bundle.putString(PlatformDetailActivity.PLATFORM_TYPE, platform_type);
                        bundle.putString(PlatformDetailActivity.PLATFORM_NAME, name);
                        activity.startActivity(PlatformDetailActivity.class, bundle);
                    }
                });
            }
        }
        //收藏平台加号
        ImageTextButton imageText = new ImageTextButton(activity);
        initPlatformStyle(activity, imageText);
        imageText.setImageResource(R.mipmap.favorite_add);
        imageText.setTextViewText(getString(R.string.favorite_add));
        linear_favorite_home.addView(imageText);
        imageText.setPadding(padding, 8, padding, 8);
        imageText.setBackgroundResource(R.drawable.selector_btn_pressed);
        imageText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
    }

    /**
     * 显示收藏弹窗
     */
    public void showAlertDialog() {

        CustomDialogFragment fragment;
        if (UserUtils.isLogin(activity)) {
            Bundle bundle = new Bundle();
            bundle.putInt(CustomDialogFragment.TYPE, CustomDialogFragment.FAVORITE_PLATFORM);
            fragment = new CustomDialogFragment();
            fragment.setArguments(bundle);
            fragment.show(getChildFragmentManager(), "favorite_add");
        } else {
            Bundle bundle = new Bundle();
            bundle.putInt(CustomDialogFragment.TYPE, CustomDialogFragment.LOGIN);
            fragment = new CustomDialogFragment();
            fragment.setArguments(bundle);
            fragment.show(getChildFragmentManager(), "login");
        }

    }

    /**
     * 初始化imageTextButton的style
     *
     * @param imageText
     */
    private void initPlatformStyle(Context context, ImageTextButton imageText) {
        imageText.setImageSize(context, 30, 30);
        imageText.setTextColor(activity.getResources().getColor(R.color.colorTextPrimary));
        imageText.setTextSize(12);
        imageText.setBackgroundResource(R.drawable.selector_btn_pressed);
        //12sp*最多5个字
        imageText.setLayoutPxSize(NormalUtils.sp2px(context, 62));
    }

    private MainActivity activity;

    private void initViewPager(final List<HomeBean.BannersBean> banners) {

        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new NetworkImageHolderView();
            }
        }, banners)
                .setPointViewVisible(true)
                .startTurning(6000)
                .setPageIndicator(new int[]{R.mipmap.banner_unselected, R.mipmap.banner_selected})
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        //banner图片点击事件
                        HomeBean.BannersBean data = banners.get(position);
                        String type = data.getType();
                        switch (type) {
                            case "webview":
                                final HomeBean.BannersBean.WebviewInfoBean web = data.getWebview_info();

                                if (!TextUtils.isEmpty(web.getWebview_url())) {

                                    if (web.is_has_footer()) {
                                        JavascriptClass jc = new JavascriptClass(activity);
                                        jc.activity(web.getWebview_url());
                                    } else {
                                        Bundle args = new Bundle();
                                        args.putString(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, web.getWebview_url());
                                        args.putInt(WebActivity.STATUS_TYPE,
                                                web.isIs_has_header() ?
                                                        WebActivity.TYPE_NORMAL : WebActivity.TYPE_ACTIVITY);
                                        args.putString(WebActivity.TITLE, web.getTitle());
                                        Intent intent = new Intent(activity, WebActivity.class);
                                        intent.putExtras(args);
                                        activity.startActivity(intent);
                                    }

                                }
                                break;
                            case "original":
                                final HomeBean.BannersBean.OriginalInfoBean original = data.getOriginal_info();
                                if ("platform_detail".equals(original.getOriginal_type())) {

                                    Bundle args = new Bundle();
                                    args.putString(PlatformDetailActivity.PLATFORM_NAME, original.getPlatform_name());
                                    args.putInt(PlatformDetailActivity.PLATFORM_ID, original.getPlatform_id());
                                    Intent intent = new Intent(activity, PlatformDetailActivity.class);
                                    intent.putExtras(args);
                                    activity.startActivity(intent);
                                }

                                break;
                            default:
                                NormalUtils.showToast(activity, "banner类型有误");
                                break;
                        }
                    }
                })
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);

    }

    /**
     * 此滑动监听是解决scrollView、horizontalScrollView、
     * ViewPager、SwipeRefreshLayout之间相互冲突的问题
     * 同时让ViewPager在手指滑动的时候停止轮播
     *
     * @param view
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // 记录点击到ViewPager时候，手指的X坐标
                mLastX = motionEvent.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                // 超过阈值
                if (Math.abs(motionEvent.getX() - mLastX) > 2f) {
                    scroll_view_home.requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP:
                // 用户抬起手指，恢复父布局状态
                scroll_view_home.requestDisallowInterceptTouchEvent(false);
                break;
            case MotionEvent.ACTION_CANCEL:// 事件取消
                break;
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        isRefresh = false;
        initData(activity);
    }

}
