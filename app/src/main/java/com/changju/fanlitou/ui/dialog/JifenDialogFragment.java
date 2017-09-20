package com.changju.fanlitou.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.mine.EnchashmentActivity;
import com.changju.fanlitou.activity.mine.FanlibaoActivity;
import com.changju.fanlitou.base.MyApplication;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.TextClickable;
import com.changju.fanlitou.util.UserUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chengww on 2017/8/11.
 */

public class JifenDialogFragment extends DialogFragment implements View.OnClickListener {

    private EnchashmentActivity context;

    public static final String YEAR_YLD = "year_yld";


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = (EnchashmentActivity) activity;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(context, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定

        dialog.setContentView(R.layout.dialog_jifen);
        initView(dialog);

        Bundle bundle = getArguments();
        if (bundle != null) {
            year_yld.setText(bundle.getString(YEAR_YLD));
        }

        dialog.setCanceledOnTouchOutside(false); // 外部点击取消

        //返回键退出页面
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK){
                    context.finish();
                }
                return false;
            }
        });

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

    private TextView year_yld;

    private void initView(Dialog dialog) {
        dialog.findViewById(R.id.iv_close).setOnClickListener(this);
        dialog.findViewById(R.id.btn_pos).setOnClickListener(this);
        dialog.findViewById(R.id.btn_neg).setOnClickListener(this);
        year_yld = (TextView) dialog.findViewById(R.id.year_yld);

        TextView tv_agreement = (TextView) dialog.findViewById(R.id.tv_agreement);
        initAgreement(tv_agreement);
    }

    private void initAgreement(TextView tv_agreement) {
        String str = "同意《用户须知及风险提示》《鸿积分财宝积分增值服务协议》，即可享受返利生息。";
        SpannableString spanableInfo = new SpannableString(str);
        spanableInfo.setSpan(new TextClickable(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.showAgreement(0);
                    }
                }), 2, 13, //设置需要监听的字符串位置
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanableInfo.setSpan(new TextClickable(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.showAgreement(1);
                    }
                }), 13, 28,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tv_agreement.setText(spanableInfo);  //将处理过的数据set到View里
        tv_agreement.setMovementMethod(LinkMovementMethod.getInstance());
        tv_agreement.setHighlightColor(getResources().getColor(android.R.color.transparent));//重新设置文字背景为透明色。

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close:
                context.finish();
                break;
            case R.id.btn_pos:
                initData(true);
                break;
            case R.id.btn_neg:
                initData(false);
                break;
        }
    }

    private AnimDialog mAnimDialog;

    private void initData(final boolean isOpenAccount){
        String time = String.valueOf(System.currentTimeMillis());
        String random = ApiUtils.getRandom();

        if (mAnimDialog == null)
            mAnimDialog = AnimDialog.showDialog(getActivity());
        mAnimDialog.show();

        OkGo.post(ApiUtils.postCreditOpen())
                .params("t",time)
                .params("random",random)
                .params("sign", NormalUtils.md5(time + random + MyApplication.key))
                .params("user_name", UserUtils.getUserName(context))
                .params("login_token",UserUtils.getLoginToken(context))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            boolean success = jsonObject.optBoolean("success");
                            if (success){
                                if (isOpenAccount){
                                    //进入返利宝
                                    context.startActivity(FanlibaoActivity.class);
                                    dismiss();
                                }else {
                                    //提现
                                    dismiss();
                                }
                            }else {
                                if (isOpenAccount){
                                    NormalUtils.showToast(context,"返利宝开启失败，请稍后重试");
                                }else {
                                    NormalUtils.showToast(context,"返利宝开启失败，暂不能提现");
                                }
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
                    }
                });
    }
}