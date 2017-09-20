package com.changju.fanlitou.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.widget.Toast;

import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.MyApplication;
import com.tencent.smtt.sdk.WebView;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chengww on 2017/5/7.
 */

public class NormalUtils {

    public static int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int px2dp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 查询用户是否已登录
     *
     * @param context
     * @return
     */
    public static boolean isLogin(Context context) {
        if (MyApplication.isDebug)
            return true;
        SharedPreferences mSharedPreferences = context.getSharedPreferences(MyApplication.USER_INFO, Activity.MODE_PRIVATE);
        return mSharedPreferences.getBoolean(MyApplication.IS_LOGIN, false);
    }

    /**
     * 返回并格式化byte的数据大小对应的文本
     *
     * @param size
     * @return
     */
    public static String getDataSize(long size) {
        DecimalFormat formater = new DecimalFormat("0.0");
        if (size < 0) {
            return "未知大小";
        }
        if (size < 1024) {
            return size + "B";
        } else if (size < 1024 * 1024) {
            float kbsize = size / 1024f;
            return formater.format(kbsize) + "KB";
        } else if (size < 1024 * 1024 * 1024) {
            float mbsize = size / 1024f / 1024f;
            return formater.format(mbsize) + "MB";
        } else if (size < 1024 * 1024 * 1024 * 1024) {
            float gbsize = size / 1024f / 1024f / 1024f;
            return formater.format(gbsize) + "GB";
        } else {
            return "size: error";
        }
    }

    //判断整数（int）
    public static boolean isInteger(String str) {
        if (null == str || "".equals(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    //判断浮点数（double和float）
    public static boolean isDouble(String str) {
        if (null == str || "".equals(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static int getStatusBarHeight(Context context) {
        Class<?> clz;
        Object obj;
        Field field;
        int x = 0, status_bar = 0;
        try {
            clz = Class.forName("com.android.internal.R$dimen");
            obj = clz.newInstance();
            field = clz.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            status_bar = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return status_bar;
    }

    /**
     * 利用MD5进行加密
     */
    public static String md5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
            // 16位的加密
            //return buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Toast toast;

    /**
     * 全局吐司公共方法
     *
     * @param context
     * @param message
     */
    public static void showToast(Context context, CharSequence message) {
        if (TextUtils.isEmpty(message))
            return;

        if (toast == null)
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        else
            toast.setText(message);
        toast.show();
    }

    public static void showToast(Context context, int strId) {
        if (toast == null)
            toast = Toast.makeText(context,
                    context.getResources().getString(strId), Toast.LENGTH_SHORT);
        else
            toast.setText(context.getResources().getString(strId));
        toast.show();
    }

    /**
     * 获取屏幕高度(px)
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕宽度(px)
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    //获取版本号:versionName
    public static String getVersion(Context context) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "unknown";
        }
    }

    /**
     * 判断上次用户更新时点击下次再说是否超过了24小时
     *
     * @param context
     * @return
     */
    public static boolean isNeedUpdate(Context context) {
        SharedPreferences sp = context.getSharedPreferences(
                "update.info", Context.MODE_PRIVATE);
        return System.currentTimeMillis() - sp.getLong(
                LazyFragment.INTENT_BOOLEAN_LAZYLOAD, 0) > 48 * 60 * 60 * 1000;
    }

    /**
     * 获取用户忽略升级的版本名
     *
     * @param context
     * @return
     */
    public static String getIgnoreVersion(Context context) {
        SharedPreferences sp = context.getSharedPreferences(
                "update.info", Context.MODE_PRIVATE);
        return sp.getString("version", "");
    }

    public static void setIgnoreVersion(Context context, String version) {
        SharedPreferences sp = context.getSharedPreferences(
                "update.info", Context.MODE_PRIVATE);
        sp.edit().putString("version", version).apply();
    }

    public static void setIgnoreUpdateTime(Context context) {
        SharedPreferences sp = context.getSharedPreferences(
                "update.info", Context.MODE_PRIVATE);
        sp.edit().putLong(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, System.currentTimeMillis()).apply();
    }

    /**
     * 判断字符串是否是手机号
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        if (TextUtils.isEmpty(mobiles))
            return false;
        Pattern p = Pattern.compile("^(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[57])[0-9]{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * float大数据显示不显示为科学计数法
     *
     * @param d
     * @return
     */
    public static String bigFloat2String(float d) {
        BigDecimal d1 = new BigDecimal(Double.toString(d));
        BigDecimal d2 = new BigDecimal(Integer.toString(1));
        // 四舍五入,保留2位小数
        return d1.divide(d2, 2, BigDecimal.ROUND_HALF_UP).toString();
    }

    //获取状态栏高度
    public static int getStatus(Context context) {
        int statusBarHeight2 = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusBarHeight2 = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight2;
    }

    /**
     * 初始化标的详情页面中，下方产品详情webview
     *
     * @param mWebView
     * @param introduction
     */
    public static void initBidWeb(WebView mWebView, String introduction) {
        StringBuilder sb = new StringBuilder();
        //拼接一段HTML代码
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>fanlitou</title>");
        sb.append("<link href=\"file:///android_asset/style.css\" rel=\"stylesheet\" type=\"text/css\" media=\"all\">");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<section class=\"spark-product-details\">\n" +
                "    <div class=\"spark-product-title\"><div class=\"spark-pro-t\">产品详情</div></div>\n" +
                "    <div class=\"spark-product-cont ng-binding\">");
        sb.append(introduction);
        sb.append("</div>\n" +
                "</section>");
        sb.append("</body>");
        sb.append("</html>");
        //使用简单的loadData方法会导致乱码，可能是Android API的Bug
        //show.loadData(sb.toString(), "text/html", "utf-8");
        //加载、并显示HTML代码
        mWebView.loadDataWithBaseURL(null, sb.toString(), "text/html", "utf-8", null);
    }

    /**
     * 判断当前应用是否是debug状态
     *
     * @param context
     * @return
     */
    public static boolean isApkInDebug(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 对Html字符进行转义
     *
     * @param str
     * @return
     */
    public static String htmlReplace(String str) {

        if (TextUtils.isEmpty(str))
            return "";

        str = str.replace("&quot;", "\"");
        str = str.replace("&ldquo;", "\"");
        str = str.replace("&rdquo;", "\"");
        str = str.replace("&nbsp;", " ");
        str = str.replace("&#39;", "'");
        str = str.replace("&rsquo;", "’");
        str = str.replace("&mdash;", "—");
        str = str.replace("&ndash;", "–");
        return str;
    }

    /**
     * 获取渠道名
     *
     * @param ctx
     * @return
     */
    public static String getChannelName(Context ctx) {
        if (ctx == null) {
            return null;
        }
        String channelName = "no_channel";
        try {
            PackageManager packageManager = ctx.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        channelName = applicationInfo.metaData.getString("UMENG_CHANNEL","no_channel");
                    }
                }

            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return channelName;
    }
}
