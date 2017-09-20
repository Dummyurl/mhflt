package com.changju.fanlitou.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.WebActivity;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.MyApplication;
import com.changju.fanlitou.bean.bid.PostInvest;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.changju.fanlitou.util.UserUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chengww on 2017/6/19.
 * 非智能投顾弹窗
 */

public class BuyDialog extends DialogFragment implements View.OnClickListener {

    private BaseActivity context;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = (BaseActivity) activity;
    }

    private int bid_id;
    private String url;
    private String platformName;
    private String phone;
    private String bid_name;
    private boolean is_show_register_dialog;
    private String postURL;
    private boolean isIntelligent;
    public static final String PLATFORM_NAME = "PLATFORM_NAME";
    public static final String PHONE = "PHONE";
    public static final String IS_AUTO = "IS_AUTO";
    public static final String IS_REG = "IS_REG";
    public static final String URL = "URL";

    private String buyUrl;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(context, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定

        Bundle bundle = getArguments();
        if (bundle != null) {
            bid_id = bundle.getInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
            url = bundle.getString(BuyDialog.class.getSimpleName());
            platformName = bundle.getString(PLATFORM_NAME);
            phone = bundle.getString(PHONE);
            is_show_register_dialog = bundle.getBoolean(IS_AUTO);
            isIntelligent = bundle.getBoolean(IS_REG);
            bid_name = bundle.getString(WebActivity.BID_NAME);
            postURL = bundle.getString(URL);
        }

        dialog.setContentView(R.layout.dialog_buy);
        initView(dialog);

        dialog.setCanceledOnTouchOutside(true); // 外部点击取消

        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.gravity = Gravity.CENTER;
        window.setAttributes(lp);

        return dialog;
    }

    private LinearLayout button_layout;
    private TextView tv_title, tv_content, tv_button_content;
    private ImageView mImageView;
    private View layout_root;

    private void initView(Dialog dialog) {
        dialog.findViewById(R.id.iv_close).setOnClickListener(this);
        dialog.findViewById(R.id.positiveButton).setOnClickListener(this);
        dialog.findViewById(R.id.nativeButton).setOnClickListener(this);
        layout_root = dialog.findViewById(R.id.layout_root);
        layout_root.setOnClickListener(this);
        button_layout = (LinearLayout) dialog.findViewById(R.id.button_layout);


        tv_title = (TextView) dialog.findViewById(R.id.tv_title);
        mImageView = (ImageView) dialog.findViewById(R.id.iv_logo);
        Glide.with(context).load(url).into(mImageView);
        tv_content = (TextView) dialog.findViewById(R.id.tv_content);
        tv_button_content = (TextView) dialog.findViewById(R.id.tv_button_content);
        tv_button_content.setOnClickListener(this);
//        if (isReg){
//            tv_title.setText("正在加载中...");
//            tv_content.setText("正在加载平台信息，请稍候...");
//            tv_button_content.setText("正在加载中...");
//            tv_button_content.setVisibility(View.VISIBLE);
//            button_layout.setVisibility(View.GONE);
//            requestData();
//        }else {
//            bindData();
//        }
        bindData();

    }

    private void bindData() {
        if (is_show_register_dialog) {
            tv_title.setText("正在为您生成" + platformName + "账号");
            tv_content.setText(Html.fromHtml("<font color='#666666'>为您生成的" + platformName +
                    "账号为" + phone + "</font><br>" + "初始登录密码将以短信的形式发送给您"));
            tv_button_content.setVisibility(View.GONE);
            button_layout.setVisibility(View.VISIBLE);
        } else {
//            tv_button_content.setVisibility(View.GONE);
//            button_layout.setVisibility(View.VISIBLE);
//            tv_title.setText("即将前往");
//            tv_content.setText("跳转后请使用" + phone + "注册" +
//                    "\n注册" + platformName + "时，请不要填写验证码");
//            tv_button_content.setText("若您不是通过返利投注册，将不享受返利");
//            button_layout.setOnClickListener(this);
            tv_title.setText("正在加载中...");
            tv_content.setText("正在加载，请稍候...");
            tv_button_content.setText("正在加载中...");
            tv_button_content.setVisibility(View.VISIBLE);
            button_layout.setVisibility(View.GONE);
            requestData();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close:
            case R.id.nativeButton:
                dismiss();
                break;
            case R.id.positiveButton:
                tv_button_content.setVisibility(View.VISIBLE);
                tv_button_content.setText("正在生成账户，请稍后...");
                requestData();
                break;
            case R.id.tv_button_content:
            case R.id.layout_root:
                if (!TextUtils.isEmpty(buyUrl)){
                    Intent intent = new Intent(context,WebActivity.class);
                    Bundle args =  new Bundle();
                    args.putString(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,buyUrl);
                    args.putInt(WebActivity.STATUS_TYPE,WebActivity.TYPE_PLATFORM);
                    args.putString(WebActivity.PLATFORM_NAME,platformName);
                    args.putString(WebActivity.BID_NAME,bid_name);
                    intent.putExtras(args);
                    startActivity(intent,args);
                    dismiss();
                }
                break;

        }
    }

    private PostInvest invest;

    private void requestData() {
        if (TextUtils.isEmpty(postURL)){
            dismiss();
        }else {
            String time = String.valueOf(System.currentTimeMillis());
            String random = ApiUtils.getRandom();
            OkGo.post(postURL)
                    .params("t", time)
                    .params("random", random)
                    .params("sign", NormalUtils.md5(time + random + MyApplication.key))
                    .params("user_name", UserUtils.getUserName(context))
                    .params("login_token", UserUtils.getLoginToken(context))
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            invest = ParseUtils.parseByGson(s, PostInvest.class);
                            /**
                             * 返回类型
                             * auto_register：自动注册成功
                             * register_error：自动注册失败
                             * manual_register：手动注册
                             * registered_enjoy_bonus：已经注册享受返利
                             * registered_not_enjoy_bonus：已经注册不享受返利
                             * old_user_bound_enjoy_bonus：老账户已绑定享受返利
                             * old_user_bound_not_enjoy_bonus：老账户绑定不享受返利
                             * old_user_can_not_bound：老账户无法绑定
                             */
                            if (invest.isSuccess()) {

                                buyUrl = invest.getUrl();
                                switch (invest.getResult_type()) {
                                    case "auto_register":
                                        tv_title.setText("点击前往");
                                        tv_content.setText(Html.fromHtml("<font color='#666666'>为您生成的"+platformName +"账号为" + phone +
                                                "</font><br>初始登录密码已为您发送短信"));
                                        tv_button_content.setText("未收到请到" + platformName + "通过忘记密码重置");
                                        break;
                                    case "register_error":
                                        tv_title.setText("注册失败");
                                        tv_content.setText("抱歉！账户注册失败");
                                        tv_button_content.setText("请稍后重试或联系客服帮助处理");
                                        tv_button_content.setOnClickListener(null);
                                        layout_root.setOnClickListener(null);
                                        break;
                                    case "manual_register":
                                        tv_title.setText("点击前往");
                                        tv_content.setText(Html.fromHtml("<font color='#666666'>跳转后请使用" + phone + "注册" +
                                                "</font><br>注册" + platformName + "时，请不要填写邀请码"));
                                        tv_button_content.setText("若您不是通过返利投注册，将不享受返利");
                                        break;
                                    case "old_user_bound_enjoy_bonus":
                                    case "registered_enjoy_bonus":
                                        tv_title.setText("点击前往");
                                        tv_content.setText(Html.fromHtml("<font color='#666666'>每次投资均需通过返利投跳转" +
                                                "</font><br>跳转后需投资相同标的，才可获得返利"));
                                        tv_button_content.setText("投资后返利将在满标后2个工作日发放");
                                        break;
                                    case "registered_not_enjoy_bonus":
                                        tv_title.setText("点击前往");
                                        tv_content.setText("您为" + platformName +
                                                "老用户，不享受返利投返利");
                                        tv_button_content.setText("投资后将在满标后2个工作日内清算");
                                        break;
                                    case "old_user_bound_not_enjoy_bonus":
                                        tv_title.setText("点击前往");
                                        tv_content.setText("您为" + platformName + "老用户，不享受返利投返利");
                                        tv_button_content.setText("投资后返利将在满标后2个工作日发放");
                                        break;
                                    case "old_user_can_not_bound":
                                        tv_title.setText("温馨提示");
                                        tv_content.setText(Html.fromHtml("<font color='#666666'>该平台暂不支持老账户绑定" +
                                                "</font><br>将不享受记账功能"));
                                        tv_button_content.setText("您已拥有" + platformName + "账户，将不能享受返利");
                                        break;
                                    case "manual_register_app_invest":
                                        tv_title.setText("点击前往");
                                        tv_content.setText(Html.fromHtml("<font color='#666666'>跳转后请使用" + phone + "注册" +
                                                "</font><br>如已成功注册，请前往" + platformName + "APP投资"));
                                        tv_button_content.setText("若您不是通过返利投注册，将不享受返利");
                                        break;
                                    case "already_register_app_invest":
                                        tv_title.setText("暂不可跳转");
                                        tv_content.setText("请前往" + platformName + "APP进行购买");
                                        tv_button_content.setText("投资后返利将在满标后2个工作日发放");
                                        tv_button_content.setOnClickListener(null);
                                        layout_root.setOnClickListener(null);
                                        break;
                                    default:
//                                        if (MyApplication.isDebug){
                                            NormalUtils.showToast(context,"服务器错误，未知类型" + invest.getResult_type());
                                            dismiss();
//                                        }
                                        break;
                                }

                            } else {
                                NormalUtils.showToast(context, "注册失败！请重试");
                                dismiss();
                            }
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);
                            NormalUtils.showToast(context, R.string.net_error);
                            dismiss();
                        }
                    });
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        context.onActivityResume();
    }
}
