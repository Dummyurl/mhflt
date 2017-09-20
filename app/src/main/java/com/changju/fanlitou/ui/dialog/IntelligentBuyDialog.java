package com.changju.fanlitou.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
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
import com.changju.fanlitou.activity.intelligent.InvestConfirmActivity;
import com.changju.fanlitou.activity.intelligent.OpenAccountActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.MyApplication;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.UserUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chengww on 2017/7/5.
 * 智能投顾购买弹窗
 */

public class IntelligentBuyDialog  extends DialogFragment implements View.OnClickListener {

    private Context context;

    private String investType;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

    private int bid_id;
    private String url;
    private String platformName;
    private String phone;
    private boolean is_show_register_dialog;
    public static final String PLATFORM_NAME = "PLATFORM_NAME";
    public static final String PHONE = "PHONE";
    public static final String IS_AUTO = "IS_AUTO";
    public static final String IS_REG = "IS_REG";
    private String bid_name;

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
            investType = bundle.getString(InvestConfirmActivity.BID_TYPE);
            buyUrl = bundle.getString(IntelligentBuyDialog.class.getSimpleName());
            bid_name = bundle.getString(WebActivity.BID_NAME);
        }

        dialog.setContentView(R.layout.dialog_intelltgent_buy);
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

    private TextView tv_title, tv_content, tv_button_content,tv_register_content;
    private ImageView mImageView;

    private void initView(Dialog dialog) {
        dialog.findViewById(R.id.iv_close).setOnClickListener(this);
        dialog.findViewById(R.id.positiveButton).setOnClickListener(this);
        dialog.findViewById(R.id.nativeButton).setOnClickListener(this);
        dialog.findViewById(R.id.layout_root).setOnClickListener(this);
        dialog.findViewById(R.id.tv_call_phone).setOnClickListener(this);


        tv_register_content = (TextView) dialog.findViewById(R.id.tv_register_content);

        if (is_show_register_dialog){
            bindData();
        }else {
            dialog.findViewById(R.id.layout_old_user).setVisibility(View.VISIBLE);
            dialog.findViewById(R.id.layout_register).setVisibility(View.GONE);

            tv_title = (TextView) dialog.findViewById(R.id.tv_title);
            mImageView = (ImageView) dialog.findViewById(R.id.iv_logo);
            Glide.with(context).load(url).into(mImageView);
            tv_content = (TextView) dialog.findViewById(R.id.tv_content);
            tv_button_content = (TextView) dialog.findViewById(R.id.tv_button_content);
            tv_button_content.setOnClickListener(this);

            tv_title.setText("温馨提示");
            tv_content.setText(Html.fromHtml("<font color='#666666'>该平台暂不支持老账户绑定</font><br>" +
                    "将不享受记账功能"));
            tv_button_content.setText("您已拥有" + platformName + "账户，将不能享受返利");
            tv_button_content.setVisibility(View.VISIBLE);
            tv_button_content.setOnClickListener(this);
        }


    }

    private void bindData() {
        tv_register_content.setText(Html.fromHtml("即日起，" +
                platformName +
                "注册、实名认证、充值、提现可在返利投直接进行，<font color='#f95353'><b>在返利投页面投资可获得返利，在" +
                platformName +
                "投资将不能得到返利。</b></font><br><br>" +
                "注册平台：账号、密码通过短信形式发送，且可用于登录" +
                platformName + "<br>充值提现：在返利投和" + platformName +
                "均可进行操作（实际交易金额仍然在" + platformName + "）<br>" +
                "账户余额：平台余额变化返利投也将进行更新，可在账户管理中查看<br><br>" +
                "正在注册" + platformName + "，账号为" + phone));
    }


    private AnimDialog mAnimDialog;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close:
            case R.id.nativeButton:
                dismiss();
                break;
            case R.id.positiveButton:
                String time = String.valueOf(System.currentTimeMillis());
                String random = ApiUtils.getRandom();

                if ("玉米理财".equals(platformName)){

                    if (mAnimDialog == null)
                        mAnimDialog = AnimDialog.showDialog(getActivity());
                    mAnimDialog.show();

                    OkGo.post(ApiUtils.postYMRegister())
                            .params("t",time)
                            .params("random",random)
                            .params("sign", NormalUtils.md5(time + random + MyApplication.key))
                            .params("user_name", UserUtils.getUserName(context))
                            .params("login_token",UserUtils.getLoginToken(context))
                            .params("bid_id",bid_id)
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(s);
                                        boolean success = jsonObject.optBoolean("success");
                                        if (success) {
                                            toOpenAccount();
                                            dismiss();
                                        }else {
                                            NormalUtils.showToast(context,jsonObject.optString("msg"));
                                            dismiss();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    mAnimDialog.dismiss();
                                }

                                @Override
                                public void onError(Call call, Response response, Exception e) {
                                    super.onError(call, response, e);
                                    NormalUtils.showToast(context,R.string.net_error);
                                    mAnimDialog.dismiss();
                                    dismiss();
                                }
                            });
                }else {
                    toOpenAccount();
                    dismiss();
                }

                break;
            case R.id.tv_button_content:
            case R.id.layout_root:
                if (!TextUtils.isEmpty(buyUrl)){
                    Intent intent2 = new Intent(context,WebActivity.class);
                    Bundle args =  new Bundle();
                    args.putString(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,buyUrl);
                    args.putInt(WebActivity.STATUS_TYPE,WebActivity.TYPE_PLATFORM);
                    args.putString(WebActivity.PLATFORM_NAME,platformName);
                    args.putString(WebActivity.BID_NAME,bid_name);
                    intent2.putExtras(args);
                    startActivity(intent2,args);
                    dismiss();
                }
                break;
            case R.id.tv_call_phone:
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                phoneIntent.setData(Uri.parse("tel:4006018132"));
                context.startActivity(phoneIntent);
                break;
        }
    }

    private void toOpenAccount() {
        Intent intent = new Intent(context, OpenAccountActivity.class);
        Bundle bundle =  new Bundle();
        bundle.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,bid_id);
        bundle.putString(InvestConfirmActivity.BID_TYPE,investType);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

}
