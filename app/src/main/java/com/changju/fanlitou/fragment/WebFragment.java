package com.changju.fanlitou.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.MainActivity;
import com.changju.fanlitou.base.JavascriptClass;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.util.NormalUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * Created by chengww on 2017/5/4.
 */

public class WebFragment extends LazyFragment {

    private WebView mWebView;
    private ProgressBar mProgressBar;
    private LinearLayout rootLayout;

//    private TextView tv_title_web;

    //判断网页加载是否错误
    private boolean isInErrorState = false;

    //错误页
    private LinearLayout mEmptyView;


    @Override
    public int bindLayout() {
        return R.layout.activity_web;
    }

    public static WebFragment newInstance() {

        Bundle args = new Bundle();
        args.putBoolean(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, false);
        WebFragment fragment = new WebFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initView() {

        GrowingIO.getInstance().setPageName(this, "首页活动页面");

        rootLayout = (LinearLayout) findViewById(R.id.layout_web);

//        ImageView iv_back_web = (ImageView) findViewById(R.id.iv_back);
        //设置回退按钮，点击事件
//        iv_back_web.setOnClickListener(this);

//        tv_title_web = (TextView) findViewById(R.id.tv_title);
//        tv_title_web.setText("加载中...");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            FrameLayout.LayoutParams params =
                    new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            NormalUtils.getStatusBarHeight(getActivity()));
            findViewById(R.id.view).setLayoutParams(params);

            findViewById(R.id.layout_title_normal).setVisibility(View.GONE);
            findViewById(R.id.layout_title_platform).setVisibility(View.GONE);
        }

        mWebView = (WebView) findViewById(R.id.webView_web);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar_web);

        //tv_error_msg
        mEmptyView = (LinearLayout) findViewById(R.id.empty_view_root);

        initWeb();
    }


    public boolean loadUrl(String url) {

        if (!TextUtils.isEmpty(url) && mWebView != null) {
            mWebView.loadUrl(url);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        Bundle args = getArguments();
        if (args != null) {
            String url = args.getString(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
            if (!TextUtils.isEmpty(url))
                mWebView.loadUrl(url);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //解决WebView页面中播放了音频,退出Activity后音频仍然在播放的问题
        if (mWebView != null) {
            rootLayout.removeView(mWebView);
            mWebView.destroy();
        }
    }

    @Override
    public void widgetClick(View v) {

    }

    @SuppressLint("JavascriptInterface")
    private void initWeb() {
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
        //网页保存数据到LocalStorage
        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setDatabaseEnabled(true);
        mWebSettings.setAppCacheEnabled(true);

        String oldua = mWebSettings.getUserAgentString();
        mWebSettings.setUserAgentString(oldua + "fanlitou_android");


        String appCachePath = getActivity().getCacheDir().getAbsolutePath();
        mWebSettings.setAppCachePath(appCachePath);
        //当前webView显示网页
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                isInErrorState = true;
                mEmptyView.setVisibility(View.VISIBLE);
                mWebView.setVisibility(View.GONE);
                mEmptyView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isInErrorState = false;
                        mWebView.reload();
                    }
                });
//                tv_title_web.setText("网络已断开");
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
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
        //progress
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress >= 35) {
                    //是否加载错误页面
                    if (!isInErrorState) {
                        mEmptyView.setVisibility(View.GONE);
                        mWebView.setVisibility(View.VISIBLE);
                    } else {
                        mEmptyView.setVisibility(View.VISIBLE);
                        mWebView.setVisibility(View.GONE);
//                        tv_title_web.setText("网络已断开");
                    }
                }
                if (newProgress >= 90) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(newProgress);
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
//                tv_title_web.setText(title);
            }
        });
        //下载监听
        mWebView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        mWebView.addJavascriptInterface(new JavascriptClass((MainActivity) getActivity()), "control"); // 其中的flt_android作为h5调用java方法的标识符
    }

}
