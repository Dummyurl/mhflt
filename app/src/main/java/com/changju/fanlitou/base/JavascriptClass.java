package com.changju.fanlitou.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;

import com.changju.fanlitou.activity.LoginActivity;
import com.changju.fanlitou.activity.MainActivity;
import com.changju.fanlitou.activity.WebActivity;
import com.changju.fanlitou.activity.bid.BidFixedActivity;
import com.changju.fanlitou.activity.fund.FundDetailActivity;
import com.changju.fanlitou.activity.intelligent.PurchaseResultActivity;
import com.changju.fanlitou.activity.intelligent.RechargeActivity;
import com.changju.fanlitou.activity.intelligent.WithdrawResultActivity;
import com.changju.fanlitou.activity.mine.FanlibaoActivity;
import com.changju.fanlitou.activity.mine.ManagerBankCardActivity;
import com.changju.fanlitou.activity.mine.RedBagActivity;
import com.changju.fanlitou.activity.platform.PlatformDetailActivity;
import com.changju.fanlitou.fragment.WebFragment;
import com.changju.fanlitou.ui.dialog.AnimDialog;
import com.changju.fanlitou.ui.dialog.CustomDialogFragment;
import com.changju.fanlitou.ui.dialog.RechargeBankStorageDialog;
import com.changju.fanlitou.ui.dialog.RechargeDialog;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ShareUtils;
import com.changju.fanlitou.util.UserUtils;

/**
 * Created by chengww on 2017/7/31.
 */

public class JavascriptClass {

    public static final int REQUEST_RECHARGE = 1314;

    private CustomDialogFragment fragment;

    private BaseActivity context;

    public JavascriptClass(@NonNull BaseActivity context) {
        this.context = context;
    }

    private JavascriptClass() {
    }

    /**
     * 显示app弹窗
     *
     * @param title   标题
     * @param content 内容
     */
    @JavascriptInterface
    public void show_native_dialog(final String title, final String content) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (fragment == null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(CustomDialogFragment.TYPE, 0);
                    bundle.putString("title", title);
                    bundle.putString("content", content);
                    fragment = new CustomDialogFragment();
                    fragment.setArguments(bundle);
                }
                fragment.show(context.getSupportFragmentManager(), "dialog");
            }
        });

    }

    /**
     * 是否为安卓端
     *
     * @return always true
     */
    @JavascriptInterface
    public boolean is_android() {
        return true;
    }

    /**
     * 是否为ios端
     *
     * @return always false
     */
    @JavascriptInterface
    public boolean is_ios() {
        return false;
    }


    /**
     * 用户是否登录
     *
     * @return boolean
     */
    @JavascriptInterface
    public boolean is_login() {
        return UserUtils.isLogin(context);
    }

    /**
     * 获取设备状态栏高度（px）
     *
     * @return @px int
     */
    @JavascriptInterface
    public int get_status_height() {
        return NormalUtils.getStatusBarHeight(context);
    }

    public static final int REQUEST_LOGIN = 2222;
    /**
     * 去app登录页
     */
    @JavascriptInterface
    public void to_login_page() {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                context.startActivityForResult(LoginActivity.class,null, REQUEST_LOGIN);
            }
        });

    }

    @JavascriptInterface
    public void toLoginPage() {
        to_login_page();
    }

    /**
     * 返回该webView上一个链接地址
     * 当前链接已经是wenView第一个时会关闭webview
     */
    @JavascriptInterface
    public void back() {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                context.onBackPressed();
            }
        });

    }

    /**
     * 分享
     *
     * @param share_url   分享地址
     * @param title       分享标题
     * @param description 分享描述
     * @param image_url   分享图片url
     */
    @JavascriptInterface
    public void do_android_share(final String share_url, final String title, final String description, final String image_url) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ShareUtils.startShare(context, share_url, title, description, image_url);
            }
        });
    }


    /**
     * 打开活动类型的webview
     * 注：在该webview打开下一个链接，状态栏还是当前活动链接类型
     * 如要在此页面打开普通header页面url，请使用
     *
     * @param activity_url url地址，必须合法
     *                     支持tel:// mailto://等链接
     * @link advertisement(String ad_url, String title)
     * 方法
     */
    @JavascriptInterface
    public void activity(final String activity_url) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Bundle args = new Bundle();
                args.putString(WebFragment.class.getSimpleName(), activity_url);
                context.startActivity(MainActivity.class, args);
            }
        });

    }

    /**
     * 打开我的奖励页面
     * 注：无需判断登录逻辑，登录由app进行维护
     */
    @JavascriptInterface
    public void myreward() {
        Bundle args = new Bundle();
        myreward(0);
    }

    @JavascriptInterface
    public void myreward(int default_tab) {
        Bundle args = new Bundle();
        args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,default_tab);
        startNeedLoginActivity(RedBagActivity.class,args);
    }

    /**
     * 打开发现--平台页面
     */
    @JavascriptInterface
    public void findplatform() {
        Bundle args = new Bundle();
        args.putBoolean(MainActivity.class.getSimpleName(), true);
        context.startActivity(MainActivity.class, args);
    }

    /**
     * bindbank()
     * 银行卡管理页面
     * 注：无需判断登录逻辑，登录由app进行维护
     */
    @JavascriptInterface
    public void bindbank() {
        startNeedLoginActivity(ManagerBankCardActivity.class,new Bundle());
    }

    /**
     * platformdetail()
     * 平台详情页面
     *
     * @param name        @nullable 可空，未加载出数据是header未空白
     * @param platform_id @nonull 不可空
     */
    @JavascriptInterface
    public void platformdetail(final int platform_id, final String name) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Bundle bundle = new Bundle();
                bundle.putInt(PlatformDetailActivity.PLATFORM_ID, platform_id);
                bundle.putString(PlatformDetailActivity.PLATFORM_NAME, name);
                context.startActivity(PlatformDetailActivity.class, bundle);
            }
        });

    }

    /**
     * 打开我的返利宝页面
     * 注：无需判断登录逻辑，登录由app进行维护
     */
    @JavascriptInterface
    public void myfanlibao() {
        myfanlibao(0);
    }

    /**
     * 打开我的返利宝页面
     * 注：无需判断登录逻辑，登录由app进行维护
     */
    @JavascriptInterface
    public void myfanlibao(int default_tab) {
        Bundle args = new Bundle();
        args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,default_tab);
        startNeedLoginActivity(FanlibaoActivity.class,args);
    }

    /**
     * 获取用户名
     * 未登录返回：""
     */
    @JavascriptInterface
    public String get_user_name() {
        return UserUtils.getUserName(context);
    }

    /**
     * 获取login_token
     * 未登录返回：""
     */
    @JavascriptInterface
    public String get_login_token() {
        return UserUtils.getLoginToken(context);
    }

    /**
     * 新开webview打开链接
     */
    @JavascriptInterface
    public void advertisement(final String ad_url, final String title) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Bundle args = new Bundle();
                args.putInt(WebActivity.STATUS_TYPE, WebActivity.TYPE_NORMAL);
                args.putString(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, ad_url);
                args.putString(WebActivity.TITLE, title);
                context.startActivity(WebActivity.class, args);
            }
        });

    }

    /**
     * 关闭当前webView
     */
    @JavascriptInterface
    public void finish() {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                context.finish();
            }
        });

    }

    /**
     * 跳转到需要判断登录的页面
     *
     * @param clazz 目标页面
     */
    private void startNeedLoginActivity(final Class clazz, final Bundle args) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!UserUtils.isLogin(context)) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(LoginActivity.CLASS_NAME,
                            clazz);
                    context.startActivity(LoginActivity.class, bundle);
                } else
                    context.startActivity(clazz,args);
            }
        });

    }


    private AnimDialog mAnimDialog;

    /**
     * 显示原生loading弹窗，加载完成后记得隐藏
     *
     * @link dismiss_loading_dialog()
     */
    @JavascriptInterface
    public void show_loading_dialog() {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mAnimDialog == null)
                    mAnimDialog = AnimDialog.showDialog(context);

                mAnimDialog.show();
            }
        });

    }

    /**
     * 隐藏原生loading弹窗
     */
    @JavascriptInterface
    public void dismiss_loading_dialog() {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mAnimDialog != null && mAnimDialog.isShowing())
                    mAnimDialog.dismiss();
            }
        });

    }

    /**
     * 展示原生协议弹窗
     *
     * @param title   弹窗标题
     * @param content 弹窗内容 支持html标签和样式
     */
    @JavascriptInterface
    public void show_agreement_dialog(final String title, final String content) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (context instanceof WebActivity) {
                    WebActivity activity = (WebActivity) context;
                    activity.showAgreementDialog(title, content);
                }
            }
        });

    }

    /**
     * 展示webview分享按钮
     *
     * @param share_url
     * @param title
     * @param description
     * @param image_url
     */
    @JavascriptInterface
    public void show_share_btn(final String share_url, final String title, final String description, final String image_url) {
        if (context instanceof WebActivity) {
            final WebActivity activity = (WebActivity) context;
            activity.runOnUiThread(
                    new Runnable() {
                        @Override
                        public void run() {
                            activity.showShareBtn(share_url, title, description, image_url);
                        }
                    });
        }
    }

    /**
     * 显示智能投顾-充值-还需充值多少钱的弹窗
     * 用于可用余额小于购买金额时显示
     * 该弹窗会自行维护其相关的 取消和充值button的逻辑
     *
     * @param bid_id         @number 标的ID
     * @param recharge_money @number 传入描述字符串
     */
    @JavascriptInterface
    public void recharge_alert(final String recharge_money, final int bid_id) {

        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!is_login()) {
                    context.startActivity(LoginActivity.class);
                    return;
                }

                Bundle bundle = new Bundle();
                bundle.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, bid_id);
                bundle.putString(RechargeActivity.class.getSimpleName(), recharge_money);
                RechargeDialog recharge = new RechargeDialog();
                recharge.setArguments(bundle);
                recharge.show(context.getSupportFragmentManager(), "recharge");
            }
        });

    }


    /**
     * 跳转到智能投顾-充值页面
     *
     * @param bid_id      @number 标的ID
     * @param platform_id @number 平台ID
     */
    @JavascriptInterface
    public void recharge_confirm(final int bid_id, final int platform_id) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!is_login()) {
                    context.startActivity(LoginActivity.class);
                    return;
                }

                Bundle args = new Bundle();
                args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, bid_id);
                args.putInt(RechargeBankStorageDialog.PLATFORM_ID, platform_id);
                context.startActivityForResult(RechargeActivity.class, args,REQUEST_RECHARGE);
            }
        });

    }

    /**
     * 跳转到智能投顾-购买结果页
     * 包含成功、失败、处理中状态
     *
     * @param bid_id       @number 标的ID
     * @param flt_order_no 返利投订单号
     */
    @JavascriptInterface
    public void purchase_result(final String flt_order_no, final int bid_id) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Bundle args = new Bundle();
                args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, bid_id);
                args.putString(PurchaseResultActivity.FLT_ORDER, flt_order_no);
                context.startActivity(PurchaseResultActivity.class, args);
                context.finish();
            }
        });

    }

    /**
     * 新开webview打开链接
     *
     * @param url        地址，必须合法
     * @param header1    上方平台名或普通title
     * @param header2    下方标的名
     * @param has_header 是否有header
     *                   <p>
     *                   if (!has_header) == 为无header，活动样式状态栏，此时设置header1 header2 无效
     *                   else if (header2.isEmpty()) 普通header样式，标题显示为header1
     *                   else 平台显示样式，header1==上方平台名，header2==下方标的名
     */
    @JavascriptInterface
    public void open_webview(String url,
                             String header1,
                             String header2,
                             boolean has_header) {
        open_webview(url, header1, header2, has_header, 0);
    }

    public static final int REQUEST_CALL_BACK = 65535;

    /**
     *  跳转到含回调页的链接
     *  在诸如从智投购买页到对方平台然后回到回调页的情况，请使用本方法跳转
     *  is_to_call_back 传 true
     * @param url
     * @param header1
     * @param header2
     * @param has_header
     * @param bid_id 标的id
     */
    @JavascriptInterface
    public void open_webview(final String url,
                             final String header1,
                             final String header2,
                             final boolean has_header,
                             final int bid_id) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Bundle args = new Bundle();
                args.putString(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, url);
                if (has_header) {
                    if (TextUtils.isEmpty(header2)) {
                        args.putInt(WebActivity.STATUS_TYPE, WebActivity.TYPE_NORMAL);
                        args.putString(WebActivity.TITLE, header1);
                    } else {
                        args.putInt(WebActivity.STATUS_TYPE, WebActivity.TYPE_PLATFORM);
                        args.putString(WebActivity.PLATFORM_NAME, header1);
                        args.putString(WebActivity.BID_NAME, header2);
                    }
                } else {
                    args.putInt(WebActivity.STATUS_TYPE, WebActivity.TYPE_ACTIVITY);
                }

                if (bid_id < 1) {
                    context.startActivity(WebActivity.class, args);
                } else {
                    if (context instanceof WebActivity){
                        context.startActivityForResult(WebActivity.class, args, REQUEST_CALL_BACK);
                        ((WebActivity)context).showInvestRechargeBankStorageDialog(bid_id);
                    }
                }
            }
        });
    }

    /**
     * 在当前webview页面更新header样式
     * //     * @see open_webview()
     *
     * @param header1
     * @param header2
     * @param has_header
     */
    @JavascriptInterface
    public void update_webview_header(final String header1,
                                      final String header2,
                                      final boolean has_header) {
        if (context instanceof WebActivity) {
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ((WebActivity) context).updateWebviewHeader(header1, header2, has_header);
                }
            });
        }
    }

    /**
     * 隐藏webView分享btn
     */
    @JavascriptInterface
    public void hide_share_btn() {
        if (context instanceof WebActivity) {
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ((WebActivity) context).hide_share_btn();
                }
            });
        }
    }

    /**
     * 改变webview左上角返回按钮为返回上一个链接（非回调页）
     *
     * @param is_back
     */
    @JavascriptInterface
    public void change_back_action(boolean is_back) {
        change_back_action(is_back, "");
    }

    /**
     * 改变webview左上角返回按钮功能（回调页）
     * 对前一个页面进行回调处理
     * @param is_back
     * @param jsonResult
     */
    @JavascriptInterface
    public void change_back_action(boolean is_back, String jsonResult) {

        if (context instanceof WebActivity) {
            if (!jsonResult.startsWith("{") || !jsonResult.endsWith("}"))
                jsonResult = "";

            ((WebActivity) context).change_back_action(is_back, NormalUtils.htmlReplace(jsonResult));
        }
    }

    /**
     * 隐藏首页活动弹窗，非首页活动弹窗调用该方法无效
     * 或 隐藏在webview中调用
     *
     * @link show_html_content_dialog(final String url_or_html)
     * 方法产生的弹窗
     */
    @JavascriptInterface
    public void dismiss_holding_dialog() {

        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (context instanceof MainActivity) {
                    ((MainActivity) context).dismissActivityDialog();
                }
                if (context instanceof WebActivity) {
                    ((WebActivity) context).dismiss_html_dialog();
                }
            }
        });


    }

    /**
     * 调用原生toast
     *
     * @param msg toast信息
     */
    @JavascriptInterface
    public void toast(final String msg) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                NormalUtils.showToast(context, msg);
            }
        });
    }

    /**
     * 在Webview（H5页面）显示类似于首页活动弹窗的弹窗
     * 此弹窗为全屏webView
     *
     * @param url_or_html url地址或html内容，必须符合html规范
     */
    @JavascriptInterface
    public void show_html_content_dialog(final String url_or_html) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (context instanceof WebActivity) {
                    ((WebActivity) context).show_html_content_dialog(url_or_html);
                }
            }
        });
    }

    /**
     * 跳转到提现结果页面
     *
     * @param platform_id  @number 平台ID
     * @param flt_order_no @string 订单号
     */
    @JavascriptInterface
    public void withdraw_result(final int platform_id, final String flt_order_no) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Bundle args = new Bundle();
                args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, platform_id);
                args.putString(WithdrawResultActivity.FLT_ORDER, flt_order_no);
                context.startActivity(WithdrawResultActivity.class, args);
            }
        });

    }


    /**
     * 跳转到基金详情页，调用该方法在加载时header标题会为空，加载后才会显示
     * 有条件的话请调用
     * @link fund_detail(final String fund_id, final String title, final String fund_code)
     * @param fund_id
     */
    @JavascriptInterface
    public void fund_detail(String fund_id){
        fund_detail(fund_id,"","");
    }

    /**
     *
     * @param fund_id
     * @param title   标题：上方基金abbr
     * @param fund_code  标题：下方基金code
     */
    @JavascriptInterface
    public void fund_detail(final String fund_id, final String title, final String fund_code){
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Bundle args = new Bundle();
                args.putString(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, fund_id);
                args.putString(BidFixedActivity.BID_NAME, title);
                args.putString(FundDetailActivity.FUND_CODE, fund_code);
                context.startActivity(FundDetailActivity.class, args);
            }
        });
    }

    /**
     * 去注册页面
     */
    @JavascriptInterface
    public void register(){
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Bundle args = new Bundle();
                args.putInt(LoginActivity.TYPE,LoginActivity.REGISTER);
                context.startActivityForResult(LoginActivity.class,args, REQUEST_LOGIN);
            }
        });
    }

    /**
     * 进入到app任意界面
     * 和推送用法一样
     * @link https://shimo.im/doc/DIZB74v7PBkoMyjx?r=3GY9EN
     * 此方法具有其固有的不安全性，跳转页面请优先使用之前已有方法
     * @param package_name   Activity名称
     * @param key_and_value  键值成对出现，使用：[key,value,key,value]
     */
    @JavascriptInterface
    public void start_to_activity(final String package_name, final String[] key_and_value){
        if (TextUtils.isEmpty(package_name))
            return;

        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Class clz;
                try {
                    clz = Class.forName(package_name);
                    if (clz != null){
                        if (key_and_value == null || key_and_value.length < 1){
                            context.startActivity(clz);
                        }else {
                            Bundle args = new Bundle();
                            for (int i = 0; i < key_and_value.length; i++) {
                                if ((i % 2 != 1) && (i + 1 < key_and_value.length)){
                                    args.putString(key_and_value[i],key_and_value[i + 1]);
                                }

                            }

                            context.startActivity(clz,args);
                        }
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
