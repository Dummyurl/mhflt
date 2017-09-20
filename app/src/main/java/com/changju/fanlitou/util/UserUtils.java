package com.changju.fanlitou.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;

import com.changju.fanlitou.activity.LoginActivity;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.MyApplication;
import com.growingio.android.sdk.collection.GrowingIO;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by chengww on 2017/5/16.
 */

public class UserUtils {
    /**
     * 用户是否已登录
     *
     * @param context
     * @return
     */
    public static boolean isLogin(Context context) {
        SharedPreferences sp = context.getSharedPreferences(MyApplication.USER_INFO, Context.MODE_PRIVATE);
        return sp.getBoolean(MyApplication.IS_LOGIN, false);
    }

    /**
     * 获取用户名
     *
     * @param context
     * @return
     */
    public static String getUserName(Context context) {
        SharedPreferences sp = context.getSharedPreferences(MyApplication.USER_INFO, Context.MODE_PRIVATE);
        if (sp.getBoolean(MyApplication.IS_LOGIN, false))
            return sp.getString(MyApplication.USER_NAME, "");
        return "";
    }

    /**
     * 获取login_token
     *
     * @param context
     * @return
     */
    public static String getLoginToken(Context context) {
        SharedPreferences sp = context.getSharedPreferences(MyApplication.USER_INFO, Context.MODE_PRIVATE);
        if (sp.getBoolean(MyApplication.IS_LOGIN, false))
            return sp.getString(MyApplication.TOKEN, "");
        return "";
    }

    /**
     * 清除登录信息--退出登录
     *
     * @param context
     */
    public static void clearLogin(Context context) {
        SharedPreferences sp = context.getSharedPreferences(MyApplication.USER_INFO, Context.MODE_PRIVATE);
        sp.edit().putBoolean(MyApplication.IS_LOGIN, false)
                .putString(MyApplication.TOKEN, "")
                .putString(MyApplication.USER_NAME, "")
                .apply();

        //登出
        MobclickAgent.onProfileSignOff();
    }

    /**
     * 保存登录信息
     *
     * @param context
     * @param token
     * @param userName
     */
    public static void setLogin(Context context, String token, String userName) {
        SharedPreferences sp = context.getSharedPreferences(MyApplication.USER_INFO, Context.MODE_PRIVATE);
        sp.edit().putBoolean(MyApplication.IS_LOGIN, true).apply();
        if (!TextUtils.isEmpty(token))
            sp.edit().putString(MyApplication.TOKEN, token).apply();
        if (!TextUtils.isEmpty(userName)) {
            sp.edit().putString(MyApplication.USER_NAME, userName).apply();

            setGrowingIOCS(context);

            MobclickAgent.onProfileSignIn(userName);
        }
    }

    public static void setGrowingIOCS(Context context) {
        GrowingIO growingIO = GrowingIO.getInstance();
        growingIO.setCS1("user_id", UserUtils.getUserName(context));
        growingIO.setCS2("um_device_token", MyApplication.deviceToken);
        growingIO.setCS3("channel", NormalUtils.getChannelName(context));
        growingIO.setCS4("growing_io_device_id", growingIO.getDeviceId());
    }

    private static final String TAG = "LockCount";
    private static final String SKIP = "isSkip";

    /**
     * 保存用户输入手势密码出错的次数
     *
     * @param context
     * @param num
     * @param userName
     */
    public static void setLockCount(Context context, int num, String userName) {
        SharedPreferences sp = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        sp.edit().putInt(userName, num).apply();
    }

    /**
     * 获取用户输入手势密码出错的次数
     *
     * @param context
     * @param userName
     * @return
     */
    public static int getLockCount(Context context, String userName) {
        SharedPreferences sp = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        return sp.getInt(userName, 5);
    }

    /**
     * 用户是否跳过了手势密码的设置
     *
     * @param context
     * @param userName
     * @return
     */
    public static boolean isSkipGesture(Context context, String userName) {
        SharedPreferences sp = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        return sp.getBoolean(userName + SKIP, false);
    }

    /**
     * 设置用户跳过手势密码
     *
     * @param context
     * @param userName
     * @param isSkip
     */
    public static void setSkipGesture(Context context, String userName, boolean isSkip) {
        SharedPreferences sp = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        sp.edit().putBoolean(userName + SKIP, isSkip).apply();
    }

    /**
     * 保存首页弹窗cookie_value
     *
     * @param context
     * @param
     */
    public static void keepCookieValue(Context context, String user_cookie_value, String no_user_cookie_name) {
        SharedPreferences sp1 = context.getSharedPreferences(MyApplication.USER_INFO + "unknown", Context.MODE_PRIVATE);
        sp1.edit().putString("user_cookie_value", no_user_cookie_name)
                .putLong("cookie_value_time", System.currentTimeMillis())
                .apply();

        if (isLogin(context)) {
            String userName = getUserName(context);
            SharedPreferences sp = context.getSharedPreferences(MyApplication.USER_INFO + userName, Context.MODE_PRIVATE);
            sp.edit().putString("user_cookie_value", user_cookie_value)
                    .putLong("cookie_value_time", System.currentTimeMillis())
                    .apply();
        }

    }

    public static String[] getCookieValue(Context context) {
        String[] results = new String[2];

        results[0] = "";
        long yearLong = 365L * 24 * 60 * 60 * 100;

        if (isLogin(context)) {
            String userName = getUserName(context);
            SharedPreferences sp = context.getSharedPreferences(MyApplication.USER_INFO + userName, Context.MODE_PRIVATE);
            if (System.currentTimeMillis() - sp.getLong("cookie_value_time", 0) > yearLong) {
                sp.edit().putString("user_cookie_value", "")
                        .putLong("cookie_value_time", System.currentTimeMillis())
                        .apply();
                results[0] = "";
            }else {
                results[0] = sp.getString("user_cookie_value", "");
            }

        }

        SharedPreferences sp1 = context.getSharedPreferences(MyApplication.USER_INFO + "unknown", Context.MODE_PRIVATE);
        results[1] = "";

        if (System.currentTimeMillis() - sp1.getLong("cookie_value_time", 0) > yearLong) {
            sp1.edit().putString("user_cookie_value", "")
                    .putLong("cookie_value_time", System.currentTimeMillis())
                    .apply();
            results[1] = "";
        }else {
            results[1] = sp1.getString("user_cookie_value", "");
        }

        return results;

    }

    public static void checkLogin(BaseActivity activity, Class clazz) {
        if (!isLogin(activity)) {
            Bundle args = new Bundle();
            args.putSerializable(LoginActivity.CLASS_NAME, clazz);
            activity.startActivity(LoginActivity.class, args);
            activity.finish();
        }
    }
}
