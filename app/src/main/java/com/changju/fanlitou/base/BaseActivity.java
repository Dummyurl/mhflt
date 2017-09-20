package com.changju.fanlitou.base;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.changju.fanlitou.activity.MainActivity;
import com.changju.fanlitou.activity.SplashActivity;
import com.changju.fanlitou.lock_pattern.activity.CreateGestureActivity;
import com.changju.fanlitou.lock_pattern.activity.GestureLoginActivity;
import com.changju.fanlitou.lock_pattern.util.cache.ACache;
import com.changju.fanlitou.util.UserUtils;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;

import java.util.List;


/**
 * Created by chengww on 2017/5/3.
 */

public abstract class BaseActivity extends FragmentActivity implements
        View.OnClickListener {
    /**
     * 是否沉浸状态栏
     **/
    private boolean isSetStatusBar = true;
    /**
     * 是否允许全屏
     **/
    private boolean mAllowFullScreen = false;
    /**
     * 是否禁止旋转屏幕
     **/
    private boolean isAllowScreenRotate = false;
    /**
     * 当前Activity渲染的视图View
     **/
    private View mContextView = null;
    /**
     * 是否输出日志信息
     **/
    private boolean isDebug;
    private String APP_NAME;
    protected final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myApp = MyApplication.getInstance();
        isDebug = MyApplication.isDebug;
        APP_NAME = MyApplication.APP_NAME;
        $Log(TAG + "-->onCreate()");
        try {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null)
                initParams(bundle);
            mContextView = LayoutInflater.from(this)
                    .inflate(bindLayout(), null);
            if (mAllowFullScreen) {
                this.getWindow().setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
                requestWindowFeature(Window.FEATURE_NO_TITLE);
            }
            if (isSetStatusBar) {
                steepStatusBar();
            }
            setContentView(mContextView);
            if (!isAllowScreenRotate) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            } else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }

            if (!MyApplication.isDebug){
                initViewAndData();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (MyApplication.isDebug){
            initViewAndData();
        }

        //统计应用启动数据
        PushAgent.getInstance(this).onAppStart();
    }

    private void initViewAndData() {
        initView(mContextView);
        doBusiness(this);
    }

    /**
     * 沉浸状态栏
     */
    private void steepStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }

    /**
     * 初始化Bundle参数
     *
     * @param params
     */
    public abstract void initParams(Bundle params);

    /**
     * 绑定布局
     *
     * @return
     */
    public abstract int bindLayout();

    /**
     * 重写： 1.是否沉浸状态栏 2.是否全屏 3.是否禁止旋转屏幕
     */
    // public abstract void setActivityPre();

    /**
     * 初始化控件
     *
     * @param view
     */
    public abstract void initView(final View view);

    /**
     * 业务操作
     *
     * @param mContext
     */
    public abstract void doBusiness(Context mContext);

    /**
     * View点击
     **/
    public abstract void widgetClick(View v);

    @Override
    public void onClick(View v) {
        widgetClick(v);
    }

    /**
     * 页面跳转
     *
     * @param clz 跳转Activity的Class对象
     */
    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }

    /**
     * 携带数据的页面跳转
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T $(int resId) {
        return (T) super.findViewById(resId);
    }

    /**
     * 含有Bundle通过Class打开编辑界面
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onResume() {
        super.onResume();
        $Log(TAG + "--->onResume()");
        ACache aCache = ACache.get(this);
        if (UserUtils.isLogin(this)) {
            String userName = UserUtils.getUserName(this);
            //提示设置手势密码,避免无限跳转创建手势密码页面
            if (!aCache.hasGesture(userName) &&
                    !UserUtils.isSkipGesture(this, userName) &&
                    ! (this instanceof CreateGestureActivity) &&
                    !(this instanceof SplashActivity)) {
                startActivity(CreateGestureActivity.class);
            } else if (enableLock && aCache.hasGesture(userName)) {
                //有手势密码--进行手势密码验证
                long durTime = System.currentTimeMillis() - myApp.getLockTime();
                if (durTime > 5 * 60 * 1000) {
                    startActivity(GestureLoginActivity.class);
                }
            }
        }

        MobclickAgent.onResume(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        $Log(TAG + "--->onDestroy()");
    }

    /**
     * 是否允许全屏
     *
     * @param allowFullScreen
     */
    public void setAllowFullScreen(boolean allowFullScreen) {
        this.mAllowFullScreen = allowFullScreen;
    }

    /**
     * [是否设置沉浸状态栏]
     *
     * @param isSetStatusBar
     */
    public void setSteepStatusBar(boolean isSetStatusBar) {
        this.isSetStatusBar = isSetStatusBar;
    }

    /**
     * 是否允许屏幕旋转
     *
     * @param isAllowScreenRotate
     */
    public void setScreenRotate(boolean isAllowScreenRotate) {
        this.isAllowScreenRotate = isAllowScreenRotate;
    }

    /**
     * 日志输出
     *
     * @param msg
     */
    protected void $Log(String msg) {
        if (isDebug) {
            Log.d(APP_NAME, msg);
        }
    }

    private MyApplication myApp;

    // 页面是否允许唤起手势密码
    private boolean enableLock = true;
    // 下一个页面是否唤起手势密码
    private boolean nextShowLock = true;

    public boolean isEnableLock() {
        return enableLock;
    }

    /**
     * 部分页面禁用手势密码需要调用该方法，例如启动页、注册登录页、解锁页（LockActivity）等
     * 在这些页面如果停留时间较久后，如果想进入下一个页面时不弹出手势，需要在finish前手动添加
     * myApp.setLockTime(System.currentTimeMillis());
     * 或者传入新的参数进行标识，在onPause中根据标识判断是否setLockTime
     * 本例选择传入参数
     * nextShowLock 为false 表示onPause()会调用setLockTime()，则下一个页面不会唤起手势
     *
     * @param nextShowLock
     */
    protected void disablePatternLock(boolean nextShowLock) {
        enableLock = false;
        this.nextShowLock = nextShowLock;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (enableLock || !nextShowLock) {
            // 更新 lockTime
            myApp.setLockTime(System.currentTimeMillis());
        }

        MobclickAgent.onPause(this);
    }

    public void onActivityResume() {
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //FragmentActivity被系统回收不保存状态
//        super.onSaveInstanceState(outState);
    }

    @Override
    public void finish() {
        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfoList = am.getRunningTasks(1);
        ActivityManager.RunningTaskInfo runningTaskInfo = taskInfoList.get(0);
        String shortClassName = runningTaskInfo.topActivity.getShortClassName();
        if (runningTaskInfo.numActivities == 1 &&
                !shortClassName.equals("MainActivity") &&
                !shortClassName.equals("GuideActivity") &&
                !shortClassName.equals("SplashActivity")){
            startActivity(MainActivity.class);
        }
        super.finish();
    }

    public void finishNotJump() {
        super.finish();
    }
}
