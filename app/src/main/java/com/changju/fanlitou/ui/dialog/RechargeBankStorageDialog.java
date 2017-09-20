package com.changju.fanlitou.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
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
import com.changju.fanlitou.activity.WebActivity;
import com.changju.fanlitou.activity.intelligent.IntelligentWithdrawActivity;
import com.changju.fanlitou.activity.intelligent.OpenAccountActivity;
import com.changju.fanlitou.activity.intelligent.RechargeActivity;
import com.changju.fanlitou.base.MyApplication;
import com.changju.fanlitou.bean.intelligent.AccountQuery;
import com.changju.fanlitou.bean.intelligent.CallBackH5;
import com.changju.fanlitou.bean.intelligent.InvestResult;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.changju.fanlitou.util.UserUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chengww on 2017/7/10.
 */

public class RechargeBankStorageDialog extends DialogFragment implements View.OnClickListener {

    private Activity activity;

    public static final String TITLE = "TITLE";
    public static final String CONTENT = "CONTENT";
    public static final String TYPE = "TYPE";
    public static final String POSTIVE = "POSTIVE";
    public static final String BID_ID = "BID_ID";


    private String title, content, type, postive;

    private int platform_id;

    private int bid_id;

    public static final String PLATFORM_ID = "platform_id";

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(activity, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定

        dialog.setContentView(R.layout.dialog_recharge_bank_storage);

        Bundle bundle = getArguments();
        if (bundle != null) {
            title = bundle.getString(TITLE);
            content = bundle.getString(CONTENT);
            type = bundle.getString(TYPE);
            postive = bundle.getString(POSTIVE);
            platform_id = bundle.getInt(PLATFORM_ID);
            bid_id = bundle.getInt(BID_ID);
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

    private TextView tv_title, tv_content;

    private void initView(Dialog dialog) {
        dialog.findViewById(R.id.btn_charge_ok).setOnClickListener(this);
        dialog.findViewById(R.id.btn_charge_problem).setOnClickListener(this);

        tv_title = (TextView) dialog.findViewById(R.id.tv_title);
        tv_content = (TextView) dialog.findViewById(R.id.tv_content);
        TextView btn_charge_ok = (TextView) dialog.findViewById(R.id.btn_charge_ok);
        btn_charge_ok.setText(postive);
        tv_title.setText(title);
        tv_content.setText(content);
    }

    private AnimDialog mAnimDialog;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_charge_ok:
            case R.id.btn_charge_problem:
                onResult("");
                break;
        }
    }

    public void onResult(String jsonResult) {

        CallBackH5 callBack = ParseUtils.parseByGson(jsonResult,CallBackH5.class);

        if ("withdraw".equals(type)) {

            if (callBack != null){
                ((IntelligentWithdrawActivity) activity).checkWithdrawResult(callBack.getFlt_order_no());
                dismiss();
            }else {
                if (mAnimDialog == null)
                    mAnimDialog = AnimDialog.showDialog(activity);
                mAnimDialog.show();

                String time = String.valueOf(System.currentTimeMillis());
                String random = ApiUtils.getRandom();

                OkGo.post(ApiUtils.postWithdrawQuery())
                        .params("t", time)
                        .params("random", random)
                        .params("sign", NormalUtils.md5(time + random + MyApplication.key))
                        .params("user_name", UserUtils.getUserName(activity))
                        .params("login_token", UserUtils.getLoginToken(activity))
                        .params("platform_id", platform_id)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                InvestResult result = ParseUtils.parseByGson(s, InvestResult.class);
                                if (result != null) {
                                    ((IntelligentWithdrawActivity) activity).checkWithdrawResult(result.getFlt_order_no());
                                } else {
                                    if (MyApplication.isDebug)
                                        NormalUtils.showToast(activity, "服务器数据返回有误：" + s);
                                    else {
                                        NormalUtils.showToast(activity, "服务器数据返回有误，请稍后重试");
                                    }
                                }

                                mAnimDialog.dismiss();
                                dismiss();
                            }

                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                super.onError(call, response, e);
                                NormalUtils.showToast(activity, R.string.net_error);
                                mAnimDialog.dismiss();
                            }
                        });
            }

        } else if ("OpenAccount".equals(type)) {

            final OpenAccountActivity accountActivity = (OpenAccountActivity) activity;

            if (callBack != null){
                if (callBack.isSuccess()){
                    NormalUtils.showToast(accountActivity, "开户成功");
                    accountActivity.startToInvest();
                }else {
                    NormalUtils.showToast(activity, callBack.getContent());
                }
            }else {
                if (mAnimDialog == null)
                    mAnimDialog = AnimDialog.showDialog(activity);
                mAnimDialog.show();

                String time = String.valueOf(System.currentTimeMillis());
                String random = ApiUtils.getRandom();

                OkGo.post(ApiUtils.postAccountQuery())
                        .params("t", time)
                        .params("random", random)
                        .params("sign", NormalUtils.md5(time + random + MyApplication.key))
                        .params("user_name", UserUtils.getUserName(activity))
                        .params("login_token", UserUtils.getLoginToken(activity))
                        .params("platform_id", platform_id)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                AccountQuery query = ParseUtils.parseByGson(s, AccountQuery.class);
                                if (query.isIs_able_to_invest()) {
                                    NormalUtils.showToast(accountActivity, "开户成功");
                                    accountActivity.startToInvest();
                                } else {
                                    NormalUtils.showToast(activity, query.getMsg());
                                }

                                mAnimDialog.dismiss();
                                dismiss();
                            }

                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                super.onError(call, response, e);
                                NormalUtils.showToast(activity, R.string.net_error);
                                mAnimDialog.dismiss();
                            }
                        });
            }
        } else if ("InvestConfirm".equals(type)) {
            if (callBack != null){
                ((WebActivity) activity).startToPurchaseResult(callBack.getFlt_order_no(), bid_id);
                dismiss();
            }else {
                if (mAnimDialog == null)
                    mAnimDialog = AnimDialog.showDialog(activity);
                mAnimDialog.show();

                String time = String.valueOf(System.currentTimeMillis());
                String random = ApiUtils.getRandom();

                OkGo.post(ApiUtils.postInvestQuery())
                        .params("t", time)
                        .params("random", random)
                        .params("sign", NormalUtils.md5(time + random + MyApplication.key))
                        .params("user_name", UserUtils.getUserName(activity))
                        .params("login_token", UserUtils.getLoginToken(activity))
                        .params("bid_id", bid_id)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                InvestResult result = ParseUtils.parseByGson(s, InvestResult.class);
                                ((WebActivity) activity).startToPurchaseResult(result.getFlt_order_no(), bid_id);
                                mAnimDialog.dismiss();
                                dismiss();
                            }

                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                super.onError(call, response, e);
                                NormalUtils.showToast(activity, R.string.net_error);
                                mAnimDialog.dismiss();
                            }
                        });
            }

        } else if ("recharge".equals(type)) {

            if (callBack != null){
                ((RechargeActivity) activity).startToRechargeResult(callBack.isSuccess(),
                        callBack.getContent());

                dismiss();
            }else {
                if (mAnimDialog == null)
                    mAnimDialog = AnimDialog.showDialog(activity);
                mAnimDialog.show();

                String time = String.valueOf(System.currentTimeMillis());
                String random = ApiUtils.getRandom();

                OkGo.post(ApiUtils.postDepositQuery())
                        .params("t", time)
                        .params("random", random)
                        .params("sign", NormalUtils.md5(time + random + MyApplication.key))
                        .params("user_name", UserUtils.getUserName(activity))
                        .params("login_token", UserUtils.getLoginToken(activity))
                        .params("platform_id", platform_id)
                        .params("bid_id", bid_id)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                InvestResult result = ParseUtils.parseByGson(s, InvestResult.class);
                                boolean isSuccess;
                                String status = result.getStatus();

                                isSuccess = !(status.equals("FAIL") || status.equals("fail"));

                                ((RechargeActivity) activity).startToRechargeResult(isSuccess, result.getMsg());
                                mAnimDialog.dismiss();
                                dismiss();
                            }

                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                super.onError(call, response, e);
                                NormalUtils.showToast(activity, R.string.net_error);
                                mAnimDialog.dismiss();
                            }
                        });
            }

        }
    }

}