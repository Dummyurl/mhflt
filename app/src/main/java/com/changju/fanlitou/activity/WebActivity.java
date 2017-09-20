package com.changju.fanlitou.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.intelligent.PurchaseResultActivity;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.JavascriptClass;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.ui.dialog.AgreementDialog;
import com.changju.fanlitou.ui.dialog.HomeActivityDialog;
import com.changju.fanlitou.ui.dialog.RechargeBankStorageDialog;
import com.changju.fanlitou.util.NormalUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;

/**
 * Created by chengww on 2017/6/19.
 */

public class WebActivity extends BaseActivity {

    private com.tencent.smtt.sdk.WebView mWebView;
    private ProgressBar mProgressBar;
    private RelativeLayout layout_title_normal, layout_title_platform;
    private LinearLayout rootLayout;
    private View empty_view_root;
    private String title;
    private TextView tv_title_normal, tv_platform_name, tv_bid_name, tv_share;
    private String url;
    //判断网页加载是否错误
    private boolean isInErrorState = false;

    private boolean isFromIntelligent;//是否从智能投顾跳转来的==header白色

    public static final String IS_FROM_INTELLIGENT = "IS_FROM_INTELLIGENT";

    private int statusType;

    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_PLATFORM = 1;
    public static final int TYPE_ACTIVITY = 2;

    public static final String STATUS_TYPE = "STATUS_TYPE";

    public static final String PLATFORM_NAME = "PLATFORM_NAME";
    public static final String BID_NAME = "BID_NAME";

    public static final String TITLE = "TITLE";

    private String platformName, bidName;
    private JavascriptClass javascriptClass;

    //上传文件uri
    private ValueCallback<Uri> mUploadMessage;
    private RechargeBankStorageDialog rechargeBankStorageDialog;

    @Override
    public void initParams(Bundle params) {
        url = params.getString(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);

        String status = params.getString(STATUS_TYPE);
        if (!TextUtils.isEmpty(status) && NormalUtils.isInteger(status)){
            statusType = Integer.valueOf(status);
        }else {
            statusType = params.getInt(STATUS_TYPE);
        }
        platformName = params.getString(PLATFORM_NAME);
        bidName = params.getString(BID_NAME);
        title = params.getString(TITLE);

        isFromIntelligent = params.getBoolean(IS_FROM_INTELLIGENT);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_web;
    }

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "H5界面");

        rootLayout = (LinearLayout) findViewById(R.id.layout_web);
        layout_title_normal = (RelativeLayout) findViewById(R.id.layout_title_normal);
        tv_title_normal = (TextView) findViewById(R.id.tv_title_normal);

        layout_title_platform = (RelativeLayout) findViewById(R.id.layout_title_platform);
        tv_platform_name = (TextView) findViewById(R.id.tv_platform_name);
        tv_bid_name = (TextView) findViewById(R.id.tv_bid_name);

        tv_platform_name.setText(platformName);
        tv_bid_name.setText(bidName);

        tv_share = (TextView) findViewById(R.id.tv_share);

        //状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, NormalUtils.getStatusBarHeight(this));
            switch (statusType) {
                case TYPE_NORMAL:
                default:
                    //非活动
                    layout_title_normal.setVisibility(View.VISIBLE);
                    layout_title_platform.setVisibility(View.GONE);

                    View tv_status_bar = findViewById(R.id.tv_status_bar);
                    tv_status_bar.setLayoutParams(params);

                    ImageView iv_back_normal = (ImageView) findViewById(R.id.iv_back_normal);
                    iv_back_normal.setOnClickListener(this);

                    tv_title_normal.setText(title);

                    //从智能投顾跳转来的，header白底
                    if (isFromIntelligent) {
                        layout_title_normal.setBackground(getResources().getDrawable(R.drawable.bg_title_bar_line));
                        tv_status_bar.setBackgroundColor(getResources().getColor(R.color.colorStatusGray));
                        tv_title_normal.setTextColor(getResources().getColor(R.color.colorTextSecondary));
                        iv_back_normal.setImageResource(R.mipmap.ic_left_black);
                    }
                    break;
                case TYPE_PLATFORM:
                    layout_title_normal.setVisibility(View.GONE);
                    layout_title_platform.setVisibility(View.VISIBLE);

                    findViewById(R.id.tv_status_bar).setLayoutParams(params);

                    findViewById(R.id.layout_back).setOnClickListener(this);

                    findViewById(R.id.tv_refresh).setOnClickListener(this);

                    break;
                case TYPE_ACTIVITY:
                    layout_title_normal.setVisibility(View.GONE);
                    layout_title_platform.setVisibility(View.GONE);

                    FrameLayout.LayoutParams p =
                            new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, NormalUtils.getStatusBarHeight(this));
                    findViewById(R.id.view).setLayoutParams(p);
                    break;
            }

        }

        mWebView = (WebView) findViewById(R.id.webView_web);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar_web);
        empty_view_root = findViewById(R.id.empty_view_root);
        empty_view_root.setOnClickListener(this);

        initWeb();

    }

    @Override
    public void doBusiness(Context mContext) {
        if (!TextUtils.isEmpty(url))
            mWebView.loadUrl(url);
        else {
            NormalUtils.showToast(mContext, "错误：链接为空");
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解决WebView页面中播放了音频,退出Activity后音频仍然在播放的问题
        if (mWebView != null) {
            rootLayout.removeView(mWebView);
            mWebView.destroy();
        }

        UMShareAPI.get(this).release();
    }

    @Override
    public void onBackPressed() {
        if (isFromCallBack && isBackIconActionBack) {
            Intent intent = getIntent();
            intent.putExtra(JavascriptClass.class.getSimpleName(),jsonResult);
            setResult(RESULT_OK,intent);
            finish();
        } else {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
            } else {
                finish();
            }
        }

    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_normal:
            case R.id.layout_back:
                if (isFromCallBack) {
                    if (isBackIconActionBack) {
                        Intent intent = getIntent();
                        intent.putExtra(JavascriptClass.class.getSimpleName(),jsonResult);
                        setResult(RESULT_OK,intent);
                    }
                    finish();
                } else {
                    if (isBackIconActionBack) {
                        if (mWebView.canGoBack()) {
                            mWebView.goBack();
                        } else {
                            finish();
                        }
                    } else {
                        finish();
                    }
                }
                break;
            case R.id.tv_refresh:
            case R.id.empty_view_root:
                mWebView.reload();
                break;
        }
    }

    private void initWeb() {
        final WebSettings mWebSettings = mWebView.getSettings();
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

        String oldua = mWebSettings.getUserAgentString();
        mWebSettings.setUserAgentString(oldua + "fanlitou_android");

        //缩放
        if (statusType == TYPE_PLATFORM) {
            //支持屏幕缩放
            mWebSettings.setSupportZoom(true);
            mWebSettings.setBuiltInZoomControls(true);
            //不显示webview缩放按钮
            mWebSettings.setDisplayZoomControls(false);
        } else {
            mWebSettings.setSupportZoom(false);
            mWebSettings.setBuiltInZoomControls(false);
        }

        //网页保存数据到LocalStorage
        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setDatabaseEnabled(true);
        mWebSettings.setAppCacheEnabled(true);
        String appCachePath = this.getCacheDir().getAbsolutePath();
        mWebSettings.setAppCachePath(appCachePath);
        //当前webView显示网页
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                isInErrorState = true;
                empty_view_root.setVisibility(View.VISIBLE);
                mWebView.setVisibility(View.GONE);
                empty_view_root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isInErrorState = false;
                        mWebView.reload();
                    }
                });

//                if (tv_title_normal.getVisibility() == View.VISIBLE) {
//                    tv_title_normal.setText("网络不给力");
//                }
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                WebActivity.this.url = url;
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    try {
                        //未知URI用其他应用打开
                        Intent intent = new Intent(Intent.ACTION_VIEW,
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
                        empty_view_root.setVisibility(View.GONE);
                        mWebView.setVisibility(View.VISIBLE);
                    } else {
                        empty_view_root.setVisibility(View.VISIBLE);
                        mWebView.setVisibility(View.GONE);

                        if (tv_title_normal.getVisibility() == View.VISIBLE) {
                            tv_title_normal.setText("网络不给力");
                        }
                    }
                }

                if (newProgress >= 90) {
                    mProgressBar.setVisibility(View.GONE);

                    mWebView.loadUrl("javascript:if(window.control.show_share_btn){}else if(window.control){window.control.hide_share_btn()}");
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(newProgress);
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {

                if (TextUtils.isEmpty(title))
                    return;

                if (tv_title_normal.getVisibility() == View.VISIBLE) {
                    if (TextUtils.isEmpty(WebActivity.this.title)) {
                        tv_title_normal.setText(title);
                    } else {
                        WebActivity.this.title = "";
                    }
                }

            }

            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, FileChooserParams fileChooserParams) {
                return super.onShowFileChooser(webView, valueCallback, fileChooserParams);
            }

            // Android > 4.1.1 调用这个方法
            public void openFileChooser(ValueCallback<Uri> uploadMsg,
                                        String acceptType, String capture) {
                mUploadMessage = uploadMsg;
                choosePicture();

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

        javascriptClass = new JavascriptClass(this);
        mWebView.addJavascriptInterface(javascriptClass, "control");
    }

    private String jsonResult;

    public void setJsonResult(String jsonResult) {
        this.jsonResult = jsonResult;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case IMAGE_PICKER:
                if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
                    if (mUploadMessage == null)
                        break;
                    if (data != null) {
                        ArrayList<ImageItem> photos = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                        Uri result = Uri.parse(photos.get(0).path);
                        mUploadMessage.onReceiveValue(result);
                        mUploadMessage = null;
                    } else {
                        mUploadMessage.onReceiveValue(null);
                    }
                }
                break;
            case JavascriptClass.REQUEST_CALL_BACK:
                if (rechargeBankStorageDialog == null || data == null || resultCode != RESULT_OK)
                    break;

                rechargeBankStorageDialog.onResult(data.getStringExtra(JavascriptClass.class.getSimpleName()));
                break;
            case JavascriptClass.REQUEST_RECHARGE:
                if (resultCode == RESULT_OK){
                    if (mWebView != null){
                        mWebView.loadUrl("javascript:if(reload_h5){reload_h5()}");
                    }
                }
                break;
            case JavascriptClass.REQUEST_LOGIN:
                if (resultCode == RESULT_OK){
                    if (mWebView != null){
                        mWebView.loadUrl("javascript:if(go_rebate){go_rebate()}");
                    }
                }
                break;
            default:
                UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
                break;
        }


    }


    public void showAgreementDialog(String title, String content) {
        AgreementDialog agreementDialog = new AgreementDialog();
        Bundle args = new Bundle();
        args.putString(AgreementDialog.TITLE, title);
        args.putString(AgreementDialog.CONTENT, content);
        agreementDialog.setArguments(args);
        agreementDialog.show(getSupportFragmentManager(), "agree");

    }

    /**
     * 显示webView分享btn
     *
     * @param share_url
     * @param title
     * @param description
     * @param image_url
     */
    public void showShareBtn(final String share_url, final String title, final String description, final String image_url) {

//        if (layout_title_normal.getVisibility() != View.VISIBLE)
//            return;

        if (tv_share.getVisibility() != View.VISIBLE)
            tv_share.setVisibility(View.VISIBLE);

        tv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                javascriptClass.do_android_share(share_url, title, description, image_url);
            }
        });
    }


    public void updateWebviewHeader(String header1, String header2, boolean has_header) {
        if (has_header) {
            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, NormalUtils.getStatusBarHeight(this));
            if (TextUtils.isEmpty(header2)) {
                //普通
                layout_title_normal.setVisibility(View.VISIBLE);
                layout_title_platform.setVisibility(View.GONE);

                findViewById(R.id.tv_status_bar).setLayoutParams(params);

                findViewById(R.id.iv_back_normal).setOnClickListener(this);

                tv_title_normal.setText(header1);

                statusType = TYPE_NORMAL;

            } else {
                layout_title_normal.setVisibility(View.GONE);
                layout_title_platform.setVisibility(View.VISIBLE);

                findViewById(R.id.tv_status_bar).setLayoutParams(params);

                findViewById(R.id.layout_back).setOnClickListener(this);

                findViewById(R.id.tv_refresh).setOnClickListener(this);

                tv_platform_name.setText(header1);
                tv_bid_name.setText(header2);

                statusType = TYPE_PLATFORM;
            }
        } else {
            layout_title_normal.setVisibility(View.GONE);
            layout_title_platform.setVisibility(View.GONE);

            FrameLayout.LayoutParams p =
                    new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, NormalUtils.getStatusBarHeight(this));
            findViewById(R.id.view).setLayoutParams(p);

            statusType = TYPE_ACTIVITY;
        }
    }

    public void hide_share_btn() {
        if (tv_share == null)
            return;

        tv_share.setVisibility(View.GONE);
        tv_share.setOnClickListener(null);
    }

    /**
     * 返回icon//true==back icon执行返回上一个链接的操作
     *
     * @param isBack
     */
    public void change_back_action(boolean isBack) {
        change_back_action(isBack, "");
    }

    public void change_back_action(boolean isBack, final String jsonResult) {
        if (TextUtils.isEmpty(jsonResult))
            isFromCallBack = false;
        else {
            isFromCallBack = true;
        }
        this.jsonResult = jsonResult;
        isBackIconActionBack = isBack;


    }

    boolean isBackIconActionBack;//true==back icon执行返回上一个链接的操作

    boolean isFromCallBack;//是否是回调页

    private static final int IMAGE_PICKER = 10086;

    private void choosePicture() {
        startActivityForResult(new Intent(this, ImageGridActivity.class), IMAGE_PICKER);
    }

    private HomeActivityDialog activityDialog;

    public void show_html_content_dialog(String url_or_html) {

        activityDialog = new HomeActivityDialog();
        Bundle args = new Bundle();
        args.putString(HomeActivityDialog.URL, url_or_html);
        activityDialog.setArguments(args);
        activityDialog.show(getSupportFragmentManager(), "activity_from_h5");
    }

    public void dismiss_html_dialog() {
        if (activityDialog != null)
            activityDialog.dismiss();
    }

    public void showInvestRechargeBankStorageDialog(int bid_id){
        Bundle bundle = new Bundle();
        bundle.putString(RechargeBankStorageDialog.TITLE, "登录存管银行投资");
        bundle.putString(RechargeBankStorageDialog.CONTENT,
                "请在新打开的网上银行页面进行投资\n操作未完成之前请不要关闭该窗口");
        bundle.putString(RechargeBankStorageDialog.POSTIVE, "投资完成");
        bundle.putString(RechargeBankStorageDialog.TYPE, "InvestConfirm");
        bundle.putInt(RechargeBankStorageDialog.BID_ID, bid_id);
        rechargeBankStorageDialog = new RechargeBankStorageDialog();
        rechargeBankStorageDialog.setArguments(bundle);
        rechargeBankStorageDialog.show(getSupportFragmentManager(), "RechargeBankStorageDialog");
    }

    public void startToPurchaseResult(String flt_order_no,int bid_id) {
        Bundle args = new Bundle();
        args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, bid_id);
        args.putString(PurchaseResultActivity.FLT_ORDER, flt_order_no);
        startActivity(PurchaseResultActivity.class, args);
        finish();
    }

}
