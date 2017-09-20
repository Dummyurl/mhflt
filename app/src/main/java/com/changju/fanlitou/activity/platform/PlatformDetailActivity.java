package com.changju.fanlitou.activity.platform;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.LoginActivity;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.MyApplication;
import com.changju.fanlitou.bean.LoginBean;
import com.changju.fanlitou.bean.discover.platformbid.DiscoverPlatformBidAbroad;
import com.changju.fanlitou.bean.discover.platformbid.DiscoverPlatformBidCrowdfunding;
import com.changju.fanlitou.bean.discover.platformbid.DiscoverPlatformBidCurrent;
import com.changju.fanlitou.bean.discover.platformbid.DiscoverPlatformBidFixed;
import com.changju.fanlitou.bean.discover.platformbid.DiscoverPlatformBidGold;
import com.changju.fanlitou.bean.discover.platformbid.DiscoverPlatformBidInsurance;
import com.changju.fanlitou.bean.discover.platformbid.DiscoverPlatformBidWuchou;
import com.changju.fanlitou.bean.discover.platformbid.IDiscoverPlatformBid;
import com.changju.fanlitou.bean.mine.PlatformDetailsBean;
import com.changju.fanlitou.fragment.platform.ListFragment;
import com.changju.fanlitou.fragment.platform.PlatformBannerViewUtils;
import com.changju.fanlitou.ui.dialog.ImageDialog;
import com.changju.fanlitou.ui.dialog.NormalWhiteDialog;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.MessageEvent;
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

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chengww on 2017/5/27.
 */

public class PlatformDetailActivity extends BaseActivity {
    public static final String PLATFORM_NAME = "PLATFORM_NAME";
    public static final String PLATFORM_TYPE = "PLATFORM_TYPE";
    public static final String PLATFORM_ID = "PLATFORM_ID";

    List<View> views;
    private ImageView iv_platformdetail_favor;

    List<String> mTitles = new ArrayList<>();
    private TabLayout mTabLayout;
    private AppBarLayout appBar;
    private int platform_id;
    private String platform_name;

    private PlatformDetailsBean banner;
    private ImageView third_iv;
    private LinearLayout rectangle_tag;
    private TextView third_background;
    private LinearLayout rectangle_tag_top;
    private SmartRefreshLayout refreshLayout;

    //loading&error
    private View include;
    private View include_load_error;

    private IDiscoverPlatformBid iDiscoverPlatformBid;
    private int page = 1;
    private ListFragment listFragment;

    //是否刷新
    private boolean isRefresh;

    @Override
    public void initParams(Bundle params) {
        String platform_id_str = params.getString(PLATFORM_ID);
        if (!TextUtils.isEmpty(platform_id_str) && NormalUtils.isInteger(platform_id_str)) {
            platform_id = Integer.valueOf(platform_id_str);
        } else {
            platform_id = params.getInt(PLATFORM_ID);
        }
        platform_name = params.getString(PLATFORM_NAME);
        //platform_type = params.getString(PLATFORM_TYPE);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_third;
    }

    @Override
    public void initView(View view) {
        //不侵入状态栏 + 10dp 上下padding
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, NormalUtils.getStatusBarHeight(this));
            findViewById(R.id.tv_status).setLayoutParams(params);
        }

        GrowingIO.getInstance().setPageName(this, "平台详情");

//        mViewPager = new WrapContentViewPager(this);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
//        mTabLayoutTop = (TabLayout) findViewById(R.id.tabs_top);
//        tabs_top_line = findViewById(R.id.tabs_top_line);
        appBar = (AppBarLayout) findViewById(R.id.appBar);
        //隐藏的高度

        rectangle_tag_top = (LinearLayout) findViewById(R.id.rectangle_tag_top);
        third_iv = (ImageView) findViewById(R.id.third_iv);
        rectangle_tag = (LinearLayout) findViewById(R.id.rectangle_tag);
        third_background = (TextView) findViewById(R.id.third_background);
//        ll_content = (LinearLayout) findViewById(R.id.ll_content);
        ImageView iv_platformdetail_back = (ImageView) findViewById(R.id.iv_platformdetail_back);
        iv_platformdetail_back.setOnClickListener(this);
        TextView tv_platformdetail_name = (TextView) findViewById(R.id.tv_platformdetail_name);
        tv_platformdetail_name.setText(platform_name);
        iv_platformdetail_favor = (ImageView) findViewById(R.id.iv_platformdetail_favor);
        iv_platformdetail_favor.setOnClickListener(this);
        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);

        refreshLayout = (SmartRefreshLayout) findViewById(R.id.platform_detail_refresh_layout);
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
                        page = 1;
                        getData();
                    }
                }, 1000);
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //初始verticalOffset为0 向下滑动变复负值
                //Log.e("zm",""+verticalOffset+"   "+third_ll_height);
                //打印出来third_ll_height330 当rectangle_tag滑到触及顶部时 rl_content显示出来
                int abs = Math.abs(verticalOffset);
                if (abs < NormalUtils.dp2px(PlatformDetailActivity.this, 82)) {
                    rectangle_tag.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    for (int i = 0; i < rectangle_tag.getChildCount(); i++) {
                        rectangle_tag_top.setVisibility(View.INVISIBLE);
                    }
                } else {
                    rectangle_tag_top.setVisibility(View.VISIBLE);
                }

            }
        });
        if (platform_id > 0) {
            isRefresh = false;
            getData();
        } else {
            finish();
        }

    }

    private void getData() {
        final String url = ApiUtils.getPlatformDetails(this, platform_id);

        OkGo.get(url).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                banner = ParseUtils.parseByGson(s, PlatformDetailsBean.class);
                //右上角是否已经收藏
                if (banner.isIs_checked()) {
                    iv_platformdetail_favor.setImageDrawable(getResources().getDrawable(R.mipmap.platform_favored));
                } else {
                    iv_platformdetail_favor.setImageDrawable(getResources().getDrawable(R.mipmap.platform_favor));
                }

                fullDate();
                if (banner.getTab_list().size() < 2) {
                    mTabLayout.setSelectedTabIndicatorHeight(0);
//                    mTabLayoutTop.setSelectedTabIndicatorHeight(0);
                }

//                include.setVisibility(View.GONE);
//                include_load_error.setVisibility(View.GONE);
                if (isRefresh) {
                    refreshLayout.finishRefresh();
                }
            }


            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                NormalUtils.showToast(PlatformDetailActivity.this, R.string.net_error);
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.VISIBLE);

                if (isRefresh) {
                    refreshLayout.finishRefresh();
                }
            }
        });

    }

    public void fullDate() {

        final String uriFixed = ApiUtils.getPlatformBidlistFixed(this, platform_id, page, 10);
        final String uriAbroad = ApiUtils.getPlatformBidlistForeign(this, platform_id, page, 10);
        final String uriCurrent = ApiUtils.getPlatformBidlistCurrent(this, platform_id);
        final String uriCrowdfunding = ApiUtils.getPlatformBidlistCrowdfunding(this, platform_id, page, 10);
        final String uriWuchou = ApiUtils.getPlatformBidlistWuchou(this, platform_id, page, 10);
        final String uriInsurance = ApiUtils.getPlatformBidlistInsurance(this, platform_id, page, 10);
        final String uriGold = ApiUtils.getPlatformBidlistGold(this, platform_id, page, 10);
        //获得banner成功后才去请求下一步数据 否者banner可能报空指针
        switch (banner.getBid_type()) {
            case "fixed":
            case "固收":
                //服务端把活期的平台星火钱包的bid_type改成了"星火"
            case "星火":
                if (platform_name.equals("星火钱包")) {
                    //使用活期筛选后 唯一的平台是星火钱包 其平台platform_type竟然是"固收"
                    //此处发现使用固收筛选后 平台列表里面也有星火钱包
                    //星火钱包里是两个列表 固收+活期
                    OkGo.get(uriCurrent).execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            iDiscoverPlatformBid = ParseUtils.parseByGson(s, DiscoverPlatformBidCurrent.class);
                            if (listFragment == null)
                                initViewData();
                            else
                                listFragment.addData(iDiscoverPlatformBid, page);

                            include.setVisibility(View.GONE);
                            include_load_error.setVisibility(View.GONE);

                            page++;
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);
                            NormalUtils.showToast(PlatformDetailActivity.this, R.string.net_error);
                            if (iDiscoverPlatformBid == null) {
                                include.setVisibility(View.GONE);
                                include_load_error.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                    break;
                }
                OkGo.get(uriFixed).execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        iDiscoverPlatformBid = ParseUtils.parseByGson(s, DiscoverPlatformBidFixed.class);
                        if (listFragment == null)
                            initViewData();
                        else
                            listFragment.addData(iDiscoverPlatformBid, page);

                        include.setVisibility(View.GONE);
                        include_load_error.setVisibility(View.GONE);

                        page++;
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        NormalUtils.showToast(PlatformDetailActivity.this, R.string.net_error);
                        if (iDiscoverPlatformBid == null) {
                            include.setVisibility(View.GONE);
                            include_load_error.setVisibility(View.VISIBLE);
                        } else {
                            listFragment.addData(new DiscoverPlatformBidFixed(), page);
                        }
                    }
                });
                break;
            case "海外":
            case "abroad":
                OkGo.get(uriAbroad).execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        iDiscoverPlatformBid = ParseUtils.parseByGson(s, DiscoverPlatformBidAbroad.class);
                        if (listFragment == null)
                            initViewData();
                        else
                            listFragment.addData(iDiscoverPlatformBid, page);
                        include.setVisibility(View.GONE);
                        include_load_error.setVisibility(View.GONE);

                        page++;
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        NormalUtils.showToast(PlatformDetailActivity.this, R.string.net_error);
                        if (iDiscoverPlatformBid == null) {
                            include.setVisibility(View.GONE);
                            include_load_error.setVisibility(View.VISIBLE);
                        } else {
                            listFragment.addData(new DiscoverPlatformBidAbroad(), page);
                        }
                    }
                });
                break;
            //好像就没有platform_form是"活期" ？？？
            //使用活期筛选后只有一个星火钱包 其platform_type是"固收"
            case "活期":
            case "current":
                OkGo.get(uriCurrent).execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        iDiscoverPlatformBid = ParseUtils.parseByGson(s, DiscoverPlatformBidCurrent.class);
                        if (listFragment == null)
                            initViewData();
                        else
                            listFragment.addData(iDiscoverPlatformBid, page);
                        include.setVisibility(View.GONE);
                        include_load_error.setVisibility(View.GONE);

                        page++;
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        NormalUtils.showToast(PlatformDetailActivity.this, R.string.net_error);
                        if (iDiscoverPlatformBid == null) {
                            include.setVisibility(View.GONE);
                            include_load_error.setVisibility(View.VISIBLE);
                        } else {
                            listFragment.addData(new DiscoverPlatformBidCurrent(), page);
                        }
                    }
                });
                break;
            case "众筹":
            case "crowdfunding":
            case "vc":
                //platform里面没有"物筹" VC和多彩投的platform_type都是"众筹" 再根据platform_name做判断
                if (platform_name.equals("维C理财")) {
                    OkGo.get(uriWuchou).execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            iDiscoverPlatformBid = ParseUtils.parseByGson(s, DiscoverPlatformBidWuchou.class);
                            if (listFragment == null)
                                initViewData();
                            else
                                listFragment.addData(iDiscoverPlatformBid, page);
                            include.setVisibility(View.GONE);
                            include_load_error.setVisibility(View.GONE);

                            page++;
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);
                            NormalUtils.showToast(PlatformDetailActivity.this, R.string.net_error);
                            if (iDiscoverPlatformBid == null) {
                                include.setVisibility(View.GONE);
                                include_load_error.setVisibility(View.VISIBLE);
                            } else {
                                listFragment.addData(new DiscoverPlatformBidWuchou(), page);
                            }
                        }
                    });
                    break;
                }
                OkGo.get(uriCrowdfunding).execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        iDiscoverPlatformBid = ParseUtils.parseByGson(s, DiscoverPlatformBidCrowdfunding.class);
                        if (listFragment == null)
                            initViewData();
                        else
                            listFragment.addData(iDiscoverPlatformBid, page);
                        include.setVisibility(View.GONE);
                        include_load_error.setVisibility(View.GONE);

                        page++;
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        NormalUtils.showToast(PlatformDetailActivity.this, R.string.net_error);
                        if (iDiscoverPlatformBid == null) {
                            include.setVisibility(View.GONE);
                            include_load_error.setVisibility(View.VISIBLE);
                        } else {
                            listFragment.addData(new DiscoverPlatformBidCrowdfunding(), page);
                        }
                    }
                });
                break;
//                    case "物筹":
//                        OkGo.get(uriWuchou).execute(new StringCallback() {
//                            @Override
//                            public void onSuccess(String s, Call call, Response response) {
//                                iDiscoverPlatformBid = ParseUtils.parseByGson(s, DiscoverPlatformBidWuchou.class);
//                                initViewData();
//                            }
//                        });
//                        break;
            case "保险":
            case "insurance":
                OkGo.get(uriInsurance).execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        iDiscoverPlatformBid = ParseUtils.parseByGson(s, DiscoverPlatformBidInsurance.class);
                        if (listFragment == null)
                            initViewData();
                        else
                            listFragment.addData(iDiscoverPlatformBid, page);
                        include.setVisibility(View.GONE);
                        include_load_error.setVisibility(View.GONE);

                        page++;
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        NormalUtils.showToast(PlatformDetailActivity.this, R.string.net_error);
                        if (iDiscoverPlatformBid == null) {
                            include.setVisibility(View.GONE);
                            include_load_error.setVisibility(View.VISIBLE);
                        } else {
                            listFragment.addData(new DiscoverPlatformBidInsurance(), page);
                        }
                    }
                });
                break;
            case "黄金":
            case "gold":
                OkGo.get(uriGold).execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        iDiscoverPlatformBid = ParseUtils.parseByGson(s, DiscoverPlatformBidGold.class);
                        if (listFragment == null)
                            initViewData();
                        else
                            listFragment.addData(iDiscoverPlatformBid, page);
                        include.setVisibility(View.GONE);
                        include_load_error.setVisibility(View.GONE);

                        page++;
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        NormalUtils.showToast(PlatformDetailActivity.this, R.string.net_error);
                        if (iDiscoverPlatformBid == null) {
                            include.setVisibility(View.GONE);
                            include_load_error.setVisibility(View.VISIBLE);
                        } else {
                            listFragment.addData(new DiscoverPlatformBidGold(), page);
                        }
                    }
                });
                break;
        }
    }


    private void initViewData() {
        Glide.with(getApplicationContext()).load(banner.getLogo())
                .animate(R.anim.glide_anim_scale).into(third_iv);

        //tag
        rectangle_tag.removeAllViews();
        List<PlatformDetailsBean.TagListBean> tag_list = banner.getTag_list();
        if (tag_list != null && tag_list.size() > 0) {
            //为普通状态下的taglist添加tag
            for (int i = 0; i < tag_list.size(); i++) {

                final PlatformDetailsBean.TagListBean tag = tag_list.get(i);
                TextView textView;

                switch (tag_list.get(i).getStyle()) {
                    default:
                    case "blue":
                        textView = getTextView(tag.getTag_name(),
                                getResources().getColor(R.color.colorTextBlue), R.drawable.shape_tag_blue);
                        break;
                    case "red":
                        textView = getTextView(tag.getTag_name(),
                                getResources().getColor(R.color.colorTextRed), R.drawable.shape_tag_red);

                        break;
                    case "yellow":
                        textView = getTextView(tag.getTag_name(),
                                getResources().getColor(R.color.colorTextYellow), R.drawable.shape_tag_yellow);
                        break;

                }

                //点击事件
                if (tag.isIs_show_dialog()) {
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //平台详情里面的弹框只有图片类型
                            String tagContent = tag.getDialog_img();
                            showDialog(tag.getTag_name(), tagContent, "img");
                        }
                    });
                }

                rectangle_tag.addView(textView);
            }


            //为滑动到顶部的taglist添加tag
            for (int i = 0; i < tag_list.size(); i++) {

                final PlatformDetailsBean.TagListBean tag = tag_list.get(i);
                TextView textView = getTextView(tag.getTag_name(),
                        getResources().getColor(android.R.color.white), R.drawable.shape_tag_white);
                if (tag.isIs_show_dialog()) {
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //平台详情里面的弹框只有图片类型
                            String tagContent = tag.getDialog_img();
                            showDialog(tag.getTag_name(), tagContent, "img");
                        }
                    });
                }
                rectangle_tag_top.addView(textView);


            }
        }
        third_background.setText(banner.getBackground());
        //封装好tab的数据
        List<PlatformDetailsBean.TabListBean> tab_list = banner.getTab_list();
        for (int i = 0; i < tab_list.size(); i++) {
            mTitles.add(tab_list.get(i).getTitle());
        }

        //tab下面的数据 一个列表加上上面的一个到三个不等的头部
        views = new ArrayList<>();
        PlatformBannerViewUtils bannerViews = new PlatformBannerViewUtils(banner, this);
        for (int i = 0; i < tab_list.size(); i++) {
            switch (tab_list.get(i).getTab_class()) {
                case 1:
                    views.add(bannerViews.initHotEvent());
                    break;
                case 2:
                    views.add(bannerViews.initPlatformIntroduction());
                    break;
                case 3:
                    views.add(bannerViews.initPlatformRules());
                    break;
            }
        }

        listFragment = ListFragment.newInstance(this, iDiscoverPlatformBid, views);

        setupViewPager(listFragment);

    }

    private void showDialog(String title, String content, String type) {
        Bundle bundle = new Bundle();
        bundle.putString(NormalWhiteDialog.TITLE, title);
        bundle.putString(NormalWhiteDialog.CONTENT, content);
        switch (type) {
            case "img":
                ImageDialog dialog = new ImageDialog();
                dialog.setArguments(bundle);
                dialog.show(getSupportFragmentManager(), "img");
                break;
            //平台详情里面用不到文字类型的dialog
            case "text":
                NormalWhiteDialog textDialog = new NormalWhiteDialog();
                textDialog.setArguments(bundle);
                textDialog.show(getSupportFragmentManager(), "text");
                break;
        }
    }

    @NonNull
    private TextView getTextView(String tag_name, int color, int shape_tag_color) {
        TextView textView = new TextView(PlatformDetailActivity.this);
        textView.setText(tag_name);
        textView.setTextSize(12);
        textView.setTextColor(color);
        textView.setPadding(NormalUtils.dp2px(PlatformDetailActivity.this, 5),
                NormalUtils.dp2px(PlatformDetailActivity.this, 2),
                NormalUtils.dp2px(PlatformDetailActivity.this, 5),
                NormalUtils.dp2px(PlatformDetailActivity.this, 2));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        params.setMargins(NormalUtils.dp2px(PlatformDetailActivity.this, 3),
                0, NormalUtils.dp2px(PlatformDetailActivity.this, 3), 0);
        textView.setLayoutParams(params);
        textView.setBackgroundResource(shape_tag_color);
        return textView;
    }


    private void setupViewPager(ListFragment listFragment) {

        for (int i = 0; i < mTitles.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(mTitles.get(i)));
        }

        MyTabSelectListener tabListener = new MyTabSelectListener(listFragment);

        mTabLayout.addOnTabSelectedListener(tabListener);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.iv_platformdetail_back:
                finish();
                break;
            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                include_load_error.setVisibility(View.GONE);
                doBusiness(this);
                break;
            case R.id.iv_platformdetail_favor:
                if (UserUtils.isLogin(this)) {
                    String time = String.valueOf(System.currentTimeMillis());
                    String random = ApiUtils.getRandom();
                    OkGo.post(ApiUtils.postSaveFavoritePlatformFromDetail())
                            .params("t", time)
                            .params("random", random)
                            .params("sign", NormalUtils.md5(time + random + MyApplication.key))
                            .params("user_name", UserUtils.getUserName(PlatformDetailActivity.this))
                            .params("login_token", UserUtils.getLoginToken(PlatformDetailActivity.this))
                            .params("platform_id", platform_id + "")
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    LoginBean bean = ParseUtils.parseByGson(s, LoginBean.class);
                                    if (bean.isSuccess()) {
                                        if (banner.isIs_checked())
                                            iv_platformdetail_favor.setImageResource(R.mipmap.platform_favor);
                                        else
                                            iv_platformdetail_favor.setImageResource(R.mipmap.platform_favored);

                                        banner.setIs_checked(!banner.isIs_checked());
                                    }
                                    EventBus.getDefault().post(new MessageEvent("platform"));
                                }

                                @Override
                                public void onError(Call call, Response response, Exception e) {
                                    super.onError(call, response, e);
                                    NormalUtils.showToast(PlatformDetailActivity.this,
                                            R.string.net_error);
                                }
                            });
                } else {
                    startActivity(LoginActivity.class);
                }
        }

    }


    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        if (llTab == null)
            return;

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

    class MyTabSelectListener implements TabLayout.OnTabSelectedListener {

        private ListFragment listFragment;

        public MyTabSelectListener(ListFragment listFragment) {
            this.listFragment = listFragment;
        }

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
//            mTabLayoutTop.getTabAt(tab.getPosition()).select();
            mTabLayout.getTabAt(tab.getPosition()).select();
            listFragment.onPageChange(tab.getPosition());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }
}