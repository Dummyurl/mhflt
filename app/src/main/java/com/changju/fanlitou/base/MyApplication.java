package com.changju.fanlitou.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.changju.fanlitou.util.MyImageLoader;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.UserUtils;
import com.growingio.android.sdk.collection.Configuration;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;
import com.lzy.okgo.OkGo;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.tencent.smtt.sdk.QbSdk;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.util.logging.Level;

/**
 * Created by chengww on 2017/5/3.
 */

public class MyApplication extends Application {

    public static final String APP_NAME = "fanlitou";

    public static final String CONFIG = "config";
    public static final String IS_FIRST = "is_first";

    public static Boolean isDebug = true;
    public static final String USER_INFO = "user_info";
    public static final String IS_LOGIN = "is_login";
    public static final String key = "fanlitou2017";

    public static int ScreenWidth;
    public static final String TOKEN = "token";
    public static final String USER_NAME = "user_name";
    public static String deviceToken;

    private static MyApplication instance = null;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //初始化网络框架
        OkGo.init(this);

        UMShareAPI.get(this);

        //isDebug package
        isDebug = NormalUtils.isApkInDebug(this);

        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return isDebug;
            }
        });

        //全局参数
        try {
            OkGo.getInstance()
//                    .setCacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                    .setRetryCount(0)
                    .setConnectTimeout(2500)
                    .setCertificates();
            //保存cookie
//                    .setCookieStore(new PersistentCookieStore());

            if (isDebug) {
                //是否打印okgo的内部异常
                OkGo.getInstance().debug("OkGo", Level.INFO, true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        PushAgent mPushAgent = PushAgent.getInstance(this);

        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                MyApplication.deviceToken = deviceToken;
            }

            @Override
            public void onFailure(String s, String s1) {
                if (isDebug) {
                    Log.d("okgo_um_register", "um register failed!");
                }
            }
        });

        mPushAgent.setPushCheck(isDebug);

        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。

        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                if (isDebug) {
                    Log.d("fanlitou-tbs", "tbs内核开启" + arg0);
                }
            }

            @Override
            public void onCoreInitFinished() {
                if (isDebug) {
                    Log.d("fanlitou-tbs", "tbs内核初始化完成");
                }
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);

        GrowingIO.startWithConfiguration(this, new Configuration()
                .useID()
                .setChannel(NormalUtils.getChannelName(this))
                .setDebugMode(isDebug)
        );

        if (UserUtils.isLogin(this)) {
            UserUtils.setGrowingIOCS(this);
        }
        //初始化图片加载器
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new MyImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(false);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(1);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
        imagePicker.setMultiMode(false);

    }

    /**
     * ----------------------- 一些公共的变量 -------------------------
     */
    private long lockTime = 0; // 保存的是最近一次调用onPause()的系统时间

    /**
     * ----------------------- 一些set/get方法,你猜是啥 -------------------------
     */
    public long getLockTime() {
        return lockTime;
    }

    public void setLockTime(long lockTime) {
        this.lockTime = lockTime;
    }

    //各个平台的配置，建议放在全局Application或者程序入口
    {
        PlatformConfig.setWeixin("wx01813369e331e716", "a444b64b0d2bf52aeeed79b0dd55e068");
        PlatformConfig.setSinaWeibo("3696400537", "9e151303b1dc4ade1233a5b3ab946732", "http://www.weibo.com");
        PlatformConfig.setQQZone("1105284927", "2QvMH02rFKwNThoY");
        Config.DEBUG = isDebug;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
