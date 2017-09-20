package com.changju.fanlitou.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.changju.fanlitou.R;
import com.changju.fanlitou.adapter.MyFragmentPagerAdapter;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.MyApplication;
import com.changju.fanlitou.bean.Update;
import com.changju.fanlitou.bean.home.HomeDialog;
import com.changju.fanlitou.bean.home.MainMenu;
import com.changju.fanlitou.fragment.WebFragment;
import com.changju.fanlitou.fragment.account.AccountFragment;
import com.changju.fanlitou.fragment.discover.DiscoverFragment;
import com.changju.fanlitou.fragment.drawable_layout_item.PlatformDrawableFragment;
import com.changju.fanlitou.fragment.home.HomeFragment;
import com.changju.fanlitou.fragment.mine.MineFragment;
import com.changju.fanlitou.ui.NoScrollViewPager;
import com.changju.fanlitou.ui.dialog.DialogMainActivity;
import com.changju.fanlitou.ui.dialog.HomeActivityDialog;
import com.changju.fanlitou.ui.dialog.UpdateDialog;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.FileUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.changju.fanlitou.util.UserUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends BaseActivity {

    private boolean isToPlatformPage;

    private boolean isToMine;

    public static final String MINE_PAGE = "MINE_PAGE";

    private NoScrollViewPager mViewPager;

    private DrawerLayout drawer_layout_platform;
    private String activity_url;

    public List<Fragment> getFragments() {
        return mFragments;
    }

    private List<Fragment> mFragments = new ArrayList<>();

    //再按一次退出提示
    long startTime = 0;

    private Bundle args;

    private MainMenu menu;

    //接收推送index
    private int bottom_index = -1;
    private int top_index = -1;

    @Override
    public void initParams(Bundle params) {
        args = params;
        isToPlatformPage = params.getBoolean(MainActivity.class.getSimpleName());
        activity_url = args.getString(WebFragment.class.getSimpleName());

        getPushIndex(params);
    }

    /**
     * 获取推送跳转index，跳转到指定页面
     *
     * @param params
     */
    public void getPushIndex(Bundle params) {
        String bottom_index_str = params.getString("bottom_index");
        if (!TextUtils.isEmpty(bottom_index_str) && NormalUtils.isInteger(bottom_index_str)) {
            bottom_index = Integer.valueOf(bottom_index_str);
        }

        String top_index_str = params.getString("top_index");
        if (!TextUtils.isEmpty(top_index_str) && NormalUtils.isInteger(top_index_str)) {
            top_index = Integer.valueOf(top_index_str);
        }
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    private RadioButton rb_fake, rb_activity;

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "首页/发现/活动/账本/我的");

        rb_fake = (RadioButton) findViewById(R.id.rb_fake);
        rb_activity = (RadioButton) findViewById(R.id.rb_activity);

        mViewPager = (NoScrollViewPager) view.findViewById(R.id.view_pager_main);

        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radio_group_bottom_main);
        //下方点击
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            if (i == 0)
                ((RadioButton) radioGroup.getChildAt(0)).setChecked(true);
            radioGroup.getChildAt(i).setOnClickListener(this);
        }

        //第一页不进行懒加载
        HomeFragment homeFragment = HomeFragment.newInstance(false);
        DiscoverFragment discoverFragment = new DiscoverFragment();
        AccountFragment accountFragment = new AccountFragment();
        MineFragment mineFragment = new MineFragment();

        mFragments.add(homeFragment);
        mFragments.add(discoverFragment);
        mFragments.add(accountFragment);
        mFragments.add(mineFragment);

        web = WebFragment.newInstance();
        mFragments.add(web);

        GrowingIO.getInstance().trackFragment(this, homeFragment);
        GrowingIO.getInstance().trackFragment(this, mineFragment);
        GrowingIO.getInstance().trackFragment(this, web);

        initMainMenu();

        drawer_layout_platform = (DrawerLayout) findViewById(R.id.drawer_layout_platform);
        //禁止侧边滑动
        drawer_layout_platform.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

    }

    private void initMainMenu() {
        //首页中间活动button
        if (args != null) {
            menu = args.getParcelable(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
            if (menu != null) {
                dealWithMainMenu(menu.isIs_show());
            } else {
                bindMainMenu();
            }

        } else {
            bindMainMenu();
        }

    }

    private void bindMainMenu() {
        OkGo.get(ApiUtils.getMenuInfo())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        FileUtils.saveData(MainActivity.this, "main_menu", s);

                        menu = ParseUtils.parseByGson(s, MainMenu.class);

                        if (menu != null)
                            dealWithMainMenu(menu.isIs_show());
                        else {
                            dealWithMainMenu(false);
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);

                        menu = ParseUtils.parseByGson(
                                FileUtils.getData(MainActivity.this, "main_menu"),
                                MainMenu.class);

                        if (menu != null)
                            dealWithMainMenu(menu.isIs_show());
                        else {
                            dealWithMainMenu(false);
                        }

                    }
                });
    }

    private void dealWithMainMenu(boolean isActivity) {
        if (isActivity) {
            rb_activity.setText(menu.getMenu_name());
            Glide.with(getApplicationContext()).load(menu.getMenu_logo()).into(new SimpleTarget<GlideDrawable>() {
                @Override
                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                    resource.setBounds(0, 0, NormalUtils.dp2px(MainActivity.this, 22), NormalUtils.dp2px(MainActivity.this, 22));
                    rb_activity.setCompoundDrawables(null, resource, null, null);
                }
            });

            rb_activity.setVisibility(View.VISIBLE);
            rb_fake.setVisibility(View.VISIBLE);
            rb_activity.setOnClickListener(this);
        } else {
            rb_activity.setVisibility(View.GONE);
            rb_fake.setVisibility(View.GONE);
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        mViewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragments));
        mViewPager.setOffscreenPageLimit(5);
        //检查更新
        checkUpdate();
        //跳转活动页
        startToActivity(activity_url);
        //push推送页面
        startToPushPage();
    }

    public void startToPushPage() {
        if (bottom_index > -1 && bottom_index < 4) {
            switch (bottom_index) {
                case 0:
                    RadioButton rb_home = $(R.id.rb_home);
                    rb_home.callOnClick();
                    rb_home.setChecked(true);
                    break;
                case 1:
                    RadioButton rb_discover = $(R.id.rb_discover);
                    rb_discover.callOnClick();
                    rb_discover.setChecked(true);
                    break;
                case 2:
                    RadioButton rb_account = $(R.id.rb_account);
                    rb_account.callOnClick();
                    rb_account.setChecked(true);
                    break;
                case 3:
                    RadioButton rb_mine = $(R.id.rb_mine);
                    rb_mine.callOnClick();
                    rb_mine.setChecked(true);
                    break;
            }
            if (bottom_index == 1 && top_index > -1 && top_index < 2) {
                mViewPager.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        DiscoverFragment discover = (DiscoverFragment) mFragments.get(1);
                        discover.setCurrentItem(top_index);
                    }
                }, 200);

            }

            if (bottom_index == 2 && top_index > -1 && top_index < 3) {
                mViewPager.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AccountFragment account = (AccountFragment) mFragments.get(2);
                        account.setCurrentItem(top_index);
                        top_index = -1;
                    }
                }, 200);

            }
        }

        bottom_index = -1;
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.rb_home:
                mViewPager.setCurrentItem(0, false);
                checkShowHomeDialog();
                break;
            case R.id.rb_discover:
                mViewPager.setCurrentItem(1, false);
                break;
            case R.id.rb_account:
                mViewPager.setCurrentItem(2, false);
                break;
            case R.id.rb_mine:
                mViewPager.setCurrentItem(3, false);
                break;
            case R.id.rb_activity:
                switch (menu.getMenu_type()) {
                    case "single":
                        showActivity(menu.getActivity_list().get(0).getActivity_url());
                        break;
                    case "multiple":
                        showAlertDialog();
                        break;
                }
                break;
        }
    }

    public void showAlertDialog() {

        Bundle bundle = new Bundle();
        bundle.putParcelable(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, menu);
        DialogMainActivity fragment = new DialogMainActivity();
        fragment.setArguments(bundle);
        fragment.show(getSupportFragmentManager(), "activity");
    }

    private WebFragment web;

    public void showActivity(final String activity_url) {
        mViewPager.setCurrentItem(4, false);
        if (rb_fake != null) {
            rb_fake.setChecked(true);
        }
        if (!web.loadUrl(activity_url)) {
            mViewPager.postDelayed(new Runnable() {
                @Override
                public void run() {
                    web.loadUrl(activity_url);
                }
            }, 100);
        }
    }

    /**
     * 再按一次退出
     */
    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - startTime >= 2000) {
            NormalUtils.showToast(this, R.string.twice_exit);
            startTime = currentTime;
        } else {
            finish();
        }
    }


    public static final int TYPE_PLATFORM = 0;
    public static final int TYPE_BID = 1;

    public void openDrawableLayout(int type) {
        if (type == TYPE_PLATFORM) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.layout_drawable_open, mPlatformDrawableFragment).commitAllowingStateLoss();
        }
        drawer_layout_platform.openDrawer(Gravity.END);
    }

    public DrawerLayout getDrawableLayout() {
        return drawer_layout_platform;
    }

    private PlatformDrawableFragment mPlatformDrawableFragment
            = PlatformDrawableFragment.newInstance();


    public int[] getPlatform_paras() {
        return mPlatformDrawableFragment.getPlatform_paras();
    }

    public int[] clearFilter() {
        return mPlatformDrawableFragment.clearFilter();
    }


    //检查更新
    private void checkUpdate() {
        OkGo.get(ApiUtils.getVersionInfo(this)).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Update update = ParseUtils.parseByGson(s, Update.class);
                if (update != null) {
                    if (update.getUpdate_type() == 3) {
                        doUpdate(update);
                    } else if (update.getUpdate_type() == 2) {
                        if (!(update.getVersion_name().equals(
                                NormalUtils.getIgnoreVersion(MainActivity.this)) &&
                                !NormalUtils.isNeedUpdate(MainActivity.this))) {
                            //忽略版本号相等且未过期 无需更新，其他--更新
                            doUpdate(update);
                        }
                    }
                }
            }
        });
    }

    private void doUpdate(Update update) {
        UpdateDialog dialog = new UpdateDialog();
        Bundle bundle = new Bundle();
        bundle.putParcelable(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, update);
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), "update");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 手势密码lockTime置零：不置零的效果是，如果在设定的一分钟之内再次打开APP则不会弹出手势密码
        // 因为应用的Activity全部finish后Application可能还存在
        // 这句置零代码也可以放在启动APP页面onResume()方法之前
        MyApplication.getInstance().setLockTime(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isToPlatformPage) {
            //发现页
            mViewPager.setCurrentItem(1, false);
            DiscoverFragment discover = (DiscoverFragment) mFragments.get(1);
            discover.selectATab(0);
            RadioButton rb = (RadioButton) findViewById(R.id.rb_discover);
            rb.setChecked(true);
            isToPlatformPage = false;
            return;
        }

        if (isToMine) {
            mViewPager.setCurrentItem(3, false);
            RadioButton rb = (RadioButton) findViewById(R.id.rb_mine);
            rb.setChecked(true);
            isToMine = false;
        }

        if (mViewPager.getCurrentItem() == 0) {
            checkShowHomeDialog();
        }

    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Bundle args = intent.getExtras();
        if (args != null) {
            isToPlatformPage = args.getBoolean(MainActivity.class.getSimpleName());
            isToMine = args.getBoolean(MINE_PAGE);

            activity_url = args.getString(WebFragment.class.getSimpleName());

            getPushIndex(args);

            startToActivity(activity_url);

            startToPushPage();

        }
    }

    private void startToActivity(String url) {
        if (!TextUtils.isEmpty(url)) {
            if (web != null) {
                showActivity(url);
                activity_url = "";
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //分享回调
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    private HomeActivityDialog activityDialog;

    /**
     * 隐藏首页活动弹窗
     */

    public void dismissActivityDialog() {
        if (activityDialog != null && activityDialog.isAdded())
            activityDialog.dismissAllowingStateLoss();
    }

    //首页-弹框信息
    private void checkShowHomeDialog() {
        String[] cookieValue = UserUtils.getCookieValue(this);
        OkGo.get(ApiUtils.getHomeDialog(this, cookieValue[0], cookieValue[1]))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        HomeDialog homeDialog = ParseUtils.parseByGson(s, HomeDialog.class);
                        //保存CookieValue
                        UserUtils.keepCookieValue(MainActivity.this, homeDialog.getUser_cookie_value(), homeDialog.getNo_user_cookie_value());

                        String homeDialogUrl = homeDialog.getUrl();
                        if (homeDialog.is_show_dialog() && !TextUtils.isEmpty(homeDialogUrl)) {
                            activityDialog = new HomeActivityDialog();
                            Bundle args = new Bundle();
                            args.putString(HomeActivityDialog.URL, homeDialogUrl);
                            activityDialog.setArguments(args);

                            activityDialog.show(getSupportFragmentManager(), "activityDialog");

                        }

                    }
                });
    }
}
