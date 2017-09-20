package com.changju.fanlitou.activity;

/**
 * Created by ZM on 2017/5/9.
 *
 */

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.View;

import com.changju.fanlitou.R;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.MyApplication;
import com.changju.fanlitou.bean.home.MainMenu;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.FileUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;

import java.lang.ref.WeakReference;

import okhttp3.Call;
import okhttp3.Response;

public class SplashActivity extends BaseActivity {

    private long time;

    @Override
    public void initParams(Bundle params) {
        args = params;
    }

    @Override
    public int bindLayout() {
//        setAllowFullScreen(true);
        time = System.currentTimeMillis();
        return R.layout.activity_splash;
    }

    private SharedPreferences sp;
    private Bundle args;

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "启动页");
        AndPermission.with(this)
                .requestCode(100)
                .permission(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.CAMERA)
                .send();


        sp = getSharedPreferences(MyApplication.CONFIG, Context.MODE_PRIVATE);
        if (args == null)
            args = new Bundle();
        // 禁止唤起手势页
        disablePatternLock(true);
    }

    @Override
    public void doBusiness(final Context mContext) {

        if (!(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)){
            initData();
        }

    }

    private void initData() {
        OkGo.get(ApiUtils.getMenuInfo())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        FileUtils.saveData(SplashActivity.this,"main_menu",s);

                        MainMenu menu = ParseUtils.parseByGson(s, MainMenu.class);
                        if (menu != null && menu.isIs_show())
                            args.putParcelable(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, menu);

                        initReady();

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);

                        MainMenu menu = ParseUtils.parseByGson(
                                FileUtils.getData(SplashActivity.this,"main_menu"),
                                MainMenu.class);

                        if (menu != null && menu.isIs_show())
                            args.putParcelable(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, menu);

                        initReady();
                    }
                });

//        OkGo.get(ApiUtils.getHomePage(mContext))
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        if (TextUtils.isEmpty(s))
//                            return;
//                        HomeBean homeBean = ParseUtils.parseByGson(s, HomeBean.class);
//                        args.putParcelable(HomeFragment.HOME_BEAN,homeBean);
//                        FileUtils.saveData(mContext, HomeFragment.HOME_BEAN,s);
//                        initReady();
//                    }
//
//                    @Override
//                    public void onError(Call call, Response response, Exception e) {
//                        super.onError(call, response, e);
//                        NormalUtils.showToast(mContext,R.string.net_error);
//
//                        //缓存数据加载
//                        String dataStr = FileUtils.getData(mContext,
//                                HomeFragment.HOME_BEAN);
//                        HomeBean homeBean = ParseUtils.parseByGson(dataStr,HomeBean.class);
//                        if (!TextUtils.isEmpty(dataStr)){
//                            args.putParcelable(HomeFragment.HOME_BEAN,homeBean);
//                        }
//                        initReady();
//                    }
//                });
    }

    private Handler handler = new MyHandler(this);

    private static class MyHandler extends Handler {
        WeakReference<SplashActivity> mActivity;

        MyHandler(SplashActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            SplashActivity theActivity = mActivity.get();
            switch (msg.what) {
                case 10086:
                    theActivity.activityStart();
                    break;
            }
        }
    }

    private void initReady() {
        final long remainTime = System.currentTimeMillis() - time - 500;
        if (remainTime >= 0) {
            activityStart();
        } else {
            handler.sendEmptyMessageDelayed(10086, -remainTime);
        }

    }

    private void activityStart() {
        //第一次进入app没有手势密码
        if (sp.getBoolean(MyApplication.IS_FIRST, true)) {
            startActivity(GuideActivity.class, args);
        } else {
            startActivity(MainActivity.class, args);
        }
        finishNotJump();

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void onBackPressed() {
        // 阻止Lock页面的返回事件
        moveTaskToBack(true);
    }

    @PermissionYes(100)
    private void getCalendarYes() {
        initData();
    }

    @PermissionNo(100)
    private void getCalendarNo() {
        initData();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // 这个Fragment所在的Activity的onRequestPermissionsResult()如果重写了，不能删除super.onRes...
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AndPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }


}
