package com.changju.fanlitou.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.fund.FundQuestionActivity;
import com.changju.fanlitou.activity.fund.PurchaseRedeemResultActivity;
import com.changju.fanlitou.base.BaseActivity;
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
 * Created by chengww on 2017/7/10.
 */

public class  RiskHintDialog extends DialogFragment implements View.OnClickListener {

    private BaseActivity activity;
    private String content;
    private int fund_id;
    private TextView button_left;
    private TextView button_right;
    private String amount;
    private String fund_code;
    private String button1;
    private String button2;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (BaseActivity) activity;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(activity, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定

        dialog.setContentView(R.layout.dialog_risk_hint);

        Bundle bundle = getArguments();
        if (bundle != null) {
            content = bundle.getString("content");
            fund_id = bundle.getInt("fund_id");
            amount = bundle.getString("amount");
            fund_code = bundle.getString("fund_code");
            button1 = bundle.getString("button1");
            button2 = bundle.getString("button2");
        }

        initView(dialog);

        dialog.setCanceledOnTouchOutside(true); // 允许外部点击取消

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


    private void initView(Dialog dialog) {
        TextView tv_content = (TextView) dialog.findViewById(R.id.tv_content);
        tv_content.setText(content);
        button_left = (TextView) dialog.findViewById(R.id.button_left);
        button_left.setOnClickListener(this);
        button_left.setText(button1);
        switch (button1) {
            case "取消":
                button_left.setBackgroundResource(R.drawable.shape_tag_gray_solid);
                break;
            case "风险测评":
                button_left.setBackgroundResource(R.drawable.shape_tag_blue_soild);
                break;
        }
        button_right = (TextView) dialog.findViewById(R.id.button_right);
        button_right.setOnClickListener(this);
        button_right.setText(button2);
        switch (button2) {
            case "确认购买":
            case "风险测评":
                button_right.setBackgroundResource(R.drawable.shape_tag_red_soild);
                break;
            case "重新测评":
                button_right.setBackgroundResource(R.drawable.shape_tag_blue_soild);
                break;
        }
        dialog.findViewById(R.id.iv_close).setOnClickListener(this);
    }

    private AnimDialog animDialog;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_left:
                switch (button1) {
                    case "取消":
                        dismiss();
                        break;
                    case "风险测评":
                        startToRiskPage();
                        break;

                }
                break;
            case R.id.button_right:
                switch (button2) {
                    case "重新测评":
                    case "风险测评":
                        startToRiskPage();
                        break;
                    case "确认购买":

                        if (animDialog == null)
                            animDialog = AnimDialog.showDialog(activity);

                        animDialog.show();

                        String time = String.valueOf(System.currentTimeMillis());
                        String random = ApiUtils.getRandom();
                        OkGo.post(ApiUtils.postWithConfirmPurchase())
                                .params("user_name", UserUtils.getUserName(activity))
                                .params("login_token", UserUtils.getLoginToken(activity))
                                .params("amount", amount)
                                .params("fund_code", fund_code)
                                .params("t", time)
                                .params("random", random)
                                .params("sign", NormalUtils.md5(time + random + MyApplication.key))
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(String s, Call call, Response response) {
                                        animDialog.dismiss();
                                        try {
                                            JSONObject jsonObject = new JSONObject(s);
                                            String flt_order_no = jsonObject.optString("flt_order_no");
                                            Bundle args1 = new Bundle();
                                            args1.putInt(PurchaseRedeemResultActivity.TYPE,
                                                    PurchaseRedeemResultActivity.TYPE_BUY);
                                            args1.putInt(PurchaseRedeemResultActivity.FUND_ID, fund_id);
                                            args1.putString(PurchaseRedeemResultActivity.ORDER_NO, flt_order_no);
                                            Intent intent1 = new Intent(activity,PurchaseRedeemResultActivity.class);
                                            intent1.putExtras(args1);
                                            activity.startActivity(intent1);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onError(Call call, Response response, Exception e) {
                                        super.onError(call, response, e);
                                        NormalUtils.showToast(activity,R.string.net_error);
                                        animDialog.dismiss();
                                    }
                                });
                        dismiss();
                        break;
                }
                break;
            case R.id.iv_close:
                dismiss();
                break;
        }
    }

    private void startToRiskPage() {
        activity.startActivity(FundQuestionActivity.class);
        dismiss();
    }

}