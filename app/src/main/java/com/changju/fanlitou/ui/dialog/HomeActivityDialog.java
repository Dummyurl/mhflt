package com.changju.fanlitou.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.changju.fanlitou.R;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.JavascriptClass;
import com.changju.fanlitou.util.ApiUtils;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import static com.changju.fanlitou.R.id.web_view;


/**
 * Created by chengww on 2017/8/14.
 * 首页活动的弹窗
 */

public class HomeActivityDialog extends DialogFragment {

    public static final String URL = "URL";
    private String url;
    private BaseActivity context;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = (BaseActivity) activity;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定

        dialog.setContentView(R.layout.dialog_main_dialog);
        initView(dialog);
        Bundle bundle = getArguments();

        if (bundle != null) {
            url = bundle.getString(URL);
            if (mWebView != null && !TextUtils.isEmpty(url)) {
                if (url.startsWith("http://") || url.startsWith("https://")) {
                    mWebView.loadUrl(ApiUtils.getHomeDialogH5());
                } else {
                    mWebView.loadDataWithBaseURL(null, url, "text/html", "utf-8", null);
                }
            }
        }

        dialog.setCanceledOnTouchOutside(true); // 外部点击取消

        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();
//        window.setWindowAnimations(R.style.AnimBottom);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.gravity = Gravity.CENTER;
        window.setAttributes(lp);

        return dialog;
    }

    private WebView mWebView;

    private void initView(Dialog dialog) {
        mWebView = (WebView) dialog.findViewById(web_view);
        initWeb(mWebView);
    }


    private void initWeb(final WebView mWebView) {
        mWebView.setBackgroundColor(Color.TRANSPARENT);
        WebSettings mWebSettings = mWebView.getSettings();
        mWebSettings.setLoadWithOverviewMode(true);
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true); // 设置支持js的弹窗
        mWebSettings.setLoadsImagesAutomatically(true);
        //启用地理定位
        mWebSettings.setGeolocationEnabled(true);
        //设置可以访问文件
        mWebSettings.setAllowFileAccess(true);
        //支持JS
        mWebSettings.setJavaScriptEnabled(true);

        mWebView.setBackgroundColor(getResources().getColor(android.R.color.transparent));

//        String oldua = mWebSettings.getUserAgentString();
//        mWebSettings.setUserAgentString(oldua + "fanlitou_android");

        //网页保存数据到LocalStorage
        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setDatabaseEnabled(true);
        mWebSettings.setAppCacheEnabled(true);
        String appCachePath = context.getCacheDir().getAbsolutePath();
        mWebSettings.setAppCachePath(appCachePath);
        //当前webView显示网页
        mWebView.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (!url.startsWith("http") && !url.startsWith("https")) {
                    try {
                        // 以下固定写法
                        final Intent intent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse(url));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                    } catch (Exception e) {
                        // 防止没有安装的情况
                        e.printStackTrace();
                    }
                    return true;
                }
                return false;
            }
        });

        mWebView.addJavascriptInterface(new JavascriptClass(context), "control"); // 其中的flt_android作为h5调用java方法的标识符
        mWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public int show(FragmentTransaction transaction, String tag) {
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(this, tag).addToBackStack(null);
        return transaction.commitAllowingStateLoss();
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            super.show(manager, tag);
        } catch (IllegalStateException ignore) {
            ignore.printStackTrace();
        }
    }
}
