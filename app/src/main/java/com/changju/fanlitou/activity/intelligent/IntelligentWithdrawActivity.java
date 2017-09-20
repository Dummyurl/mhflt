package com.changju.fanlitou.activity.intelligent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.WebActivity;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.JavascriptClass;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.MyApplication;
import com.changju.fanlitou.bean.intelligent.ConfirmWithdraw;
import com.changju.fanlitou.bean.intelligent.Recharge;
import com.changju.fanlitou.bean.intelligent.SMS;
import com.changju.fanlitou.ui.EditTextWithDel;
import com.changju.fanlitou.ui.dialog.AnimDialog;
import com.changju.fanlitou.ui.dialog.RechargeBankStorageDialog;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.CountDownTimerUtil;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.changju.fanlitou.util.UserUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

import static com.changju.fanlitou.R.id.et_czje;
import static com.changju.fanlitou.R.id.et_msg_code;
import static com.changju.fanlitou.R.id.tv_zhye;

/**
 * Created by chengww on 2017/7/12.
 */

public class IntelligentWithdrawActivity extends BaseActivity {

    private ImageView ivBackMyAccount;
    private ImageView bankLogoApp;
    private TextView tvBankName;
    private TextView tvBankNum;
    private ImageView ivPlatformLogo;
    private TextView tvZhye;
    private TextView btnWithdrawAll;
    private EditTextWithDel etCzje;
    private TextView tvFee;
    private TextView tvActualAppropriation;
    private TextView tvPhone;
    private LinearLayout layoutMsgCode;
    private EditTextWithDel etMsgCode;
    private TextView btnGetMsgCode;
    private TextView btnOk;

    private TextView tvWithdrawSuccessDays;

    //loading&error
    private View include;
    private View include_load_error;

    private int platform_id;
    private String sms_auth_token;
    private ConfirmWithdraw withdraw;

    //提现金额
    private float withdrawAppropriation;
    private float fee;
    private float minRechargeAmount;
    private float balance;
    private CountDownTimerUtil mCountDownTimerUtils;
    private RechargeBankStorageDialog rechargeBankStorageDialog;


    @Override
    public void initParams(Bundle params) {
        platform_id = params.getInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_intelligent_withdraw;
    }

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "智能投顾-提现");

        ivBackMyAccount = (ImageView) findViewById(R.id.iv_back_my_account);
        ivBackMyAccount.setOnClickListener(this);

        bankLogoApp = (ImageView) findViewById(R.id.bank_logo_app);
        tvBankName = (TextView) findViewById(R.id.tv_bank_name);
        tvBankNum = (TextView) findViewById(R.id.tv_bank_num);
        ivPlatformLogo = (ImageView) findViewById(R.id.iv_platform_logo);
        tvZhye = (TextView) findViewById(tv_zhye);

        btnWithdrawAll = (TextView) findViewById(R.id.btn_withdraw_all);
        btnWithdrawAll.setOnClickListener(this);

        //提现金额
        etCzje = (EditTextWithDel) findViewById(et_czje);
        etCzje.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s))
                    withdrawAppropriation = 0;
                else {
                    withdrawAppropriation = Float.valueOf(s.toString());
                }

                if (withdrawAppropriation > balance) {
                    withdrawAppropriation = balance;
                    String format = format2(withdraw.getAccount_balance());
                    etCzje.setText(format);
                    etCzje.setSelection(etCzje.getText().length());
                }


                //实际到账
                float actualAppropriation = withdrawAppropriation - fee < 0 ? 0 : (withdrawAppropriation - fee);
                tvActualAppropriation.setText(format2(String.valueOf(actualAppropriation)) + "元");

                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        etCzje.setText(s);
                        etCzje.setSelection(s.length());
                    }
                }

                if (s.toString().trim().equals(".")) {
                    s = "0" + s;
                    etCzje.setText(s);
                    etCzje.setSelection(etCzje.getText().length());
                }

                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        etCzje.setText(s.subSequence(0, 1));
                        etCzje.setSelection(etCzje.getText().length());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        tvFee = (TextView) findViewById(R.id.tv_fee);
        tvActualAppropriation = (TextView) findViewById(R.id.tv_actual_appropriation);
        tvPhone = (TextView) findViewById(R.id.tv_phone);
        layoutMsgCode = (LinearLayout) findViewById(R.id.layout_msg_code);
        etMsgCode = (EditTextWithDel) findViewById(et_msg_code);

        MyTextWatcher watcher = new MyTextWatcher();
        etCzje.addTextChangedListener(watcher);
        etMsgCode.addTextChangedListener(watcher);

        btnGetMsgCode = (TextView) findViewById(R.id.btn_get_msg_code);
        btnGetMsgCode.setOnClickListener(this);
        btnOk = (TextView) findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(this);

        tvWithdrawSuccessDays = (TextView) findViewById(R.id.tv_withdraw_success_days);

        include = findViewById(R.id.include_loading);
        include.setVisibility(View.VISIBLE);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);
    }

    /**
     * 全部提现
     */
    private String format2(String old) {
        if (old.contains(".")) {
            if (old.length() - 1 - old.indexOf(".") == 1) {
                return old + "0";
            } else if (old.length() - 1 - old.indexOf(".") == 0) {
                return old + "00";
            } else {
                return old;
            }
        } else {
            return old + ".00";
        }
    }

    @Override
    public void doBusiness(final Context mContext) {
    }

    private void bindData(final Context mContext) {
        OkGo.get(ApiUtils.getConfirmWithdraw(mContext, platform_id))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        withdraw = ParseUtils.parseByGson(s, ConfirmWithdraw.class);

                        //bank相关
                        ConfirmWithdraw.BoundBankInfoBean bank = withdraw.getBound_bank_info();
                        Glide.with(getApplicationContext()).load(bank.getBank_logo()).into(bankLogoApp);
                        tvBankName.setText(bank.getBank_name());
                        tvBankNum.setText(bank.getCard_no());

                        //平台
                        ConfirmWithdraw.PlatformInfoBean platform = withdraw.getPlatform_info();
                        Glide.with(getApplicationContext()).load(platform.getPlatform_logo()).into(ivPlatformLogo);
                        //平台余额
                        tvZhye.setText(withdraw.getAccount_balance() + "元");
                        balance = Float.valueOf(withdraw.getAccount_balance());

                        //最小提现金额
                        if (TextUtils.isEmpty(withdraw.getMin_withdraw_amount()))
                            minRechargeAmount = 0;
                        else
                            minRechargeAmount = Float.valueOf(withdraw.getMin_withdraw_amount());

                        etCzje.setHint("最少提现" + minRechargeAmount + "元");

                        //手续费
                        String withdrawFees = withdraw.getExpect_withdraw_fees();
                        fee = TextUtils.isEmpty(withdrawFees) ?
                                0 : Float.valueOf(withdrawFees);
                        tvFee.setText(fee <= 0 ?
                                "免手续费" : "手续费" + withdrawFees + "元");

                        //实际到账
                        float actualAppropriation = withdrawAppropriation - fee < 0 ? 0 : (withdrawAppropriation - fee);
                        tvActualAppropriation.setText(actualAppropriation + "元");

                        //手机号
                        tvPhone.setText(withdraw.getBank_phone_number());

                        //验证码
                        if (!withdraw.isIs_need_withdraw_sms_code()) {
                            findViewById(R.id.layout_msg_code).setVisibility(View.GONE);
                        }

                        if (!withdraw.is_need_bank_phone_number()) {
                            findViewById(R.id.layout_phone_number).setVisibility(View.GONE);
                        }

                        tvWithdrawSuccessDays.setText("免费提现不包含充值未投资资金\n");
                        tvWithdrawSuccessDays.append("预计T+" +
                                withdraw.getWithdraw_success_days() +
                                "日到账，具体时间以到账时间为准");

                        include.setVisibility(View.GONE);
                        include_load_error.setVisibility(View.GONE);

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        NormalUtils.showToast(mContext, R.string.net_error);
                        include.setVisibility(View.GONE);
                        include_load_error.setVisibility(View.VISIBLE);
                    }
                });
    }

    private AnimDialog mAnimDialog;

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_my_account:
                finish();
                break;
            case R.id.btn_ok:
                if (TextUtils.isEmpty(etCzje.getText())) {
                    NormalUtils.showToast(this, "请输入提现金额");
                    break;
                }

                if (withdrawAppropriation < minRechargeAmount) {
                    NormalUtils.showToast(this, "最少提现" + withdraw.getMin_withdraw_amount() + "元");
                    break;
                }

                if (withdraw.isIs_need_withdraw_sms_code() && TextUtils.isEmpty(etMsgCode.getText())) {
                    NormalUtils.showToast(this, "请输入验证码");
                    break;
                }

                //提现
                if (mAnimDialog == null)
                    mAnimDialog = AnimDialog.showDialog(this);
                mAnimDialog.show();

                String time = String.valueOf(System.currentTimeMillis());
                String random = ApiUtils.getRandom();

                String sms_auth_code = "";
                if (withdraw.isIs_need_withdraw_sms_code())
                    sms_auth_code = etMsgCode.getText().toString();

                OkGo.post(ApiUtils.postIntelligentWithdraw())
                        .params("t", time)
                        .params("random", random)
                        .params("sign", NormalUtils.md5(time + random + MyApplication.key))
                        .params("user_name", UserUtils.getUserName(this))
                        .params("login_token", UserUtils.getLoginToken(this))
                        .params("platform_id", withdraw.getPlatform_info().getPlatform_id())
                        .params("amount", withdrawAppropriation)
                        .params("sms_auth_code", sms_auth_code)
                        .params("sms_auth_token", sms_auth_token)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                final Recharge mRecharge = ParseUtils.parseByGson(s, Recharge.class);
                                switch (withdraw.getPlatform_info().getPlatform_bank_interface_type()) {
                                    case "bank_storage":
                                        if (mRecharge.isSuccess()) {

                                            Bundle args = new Bundle();
                                            args.putString(LazyFragment.INTENT_BOOLEAN_LAZYLOAD
                                                    , ApiUtils.getBankStorageWithdraw(IntelligentWithdrawActivity.this,
                                                            platform_id, withdrawAppropriation));
                                            args.putString(WebActivity.TITLE, "登录存管银行提现");
                                            startActivityForResult(WebActivity.class, args, JavascriptClass.REQUEST_CALL_BACK);

                                            Bundle bundle = new Bundle();
                                            bundle.putString(RechargeBankStorageDialog.TITLE, "登录存管银行提现");
                                            bundle.putString(RechargeBankStorageDialog.CONTENT,
                                                    "请在新打开的网上银行页面进行提现，提现未完成之前请不要关闭该窗口");
                                            bundle.putString(RechargeBankStorageDialog.POSTIVE, "提现成功");
                                            bundle.putInt(RechargeBankStorageDialog.PLATFORM_ID, platform_id);
                                            bundle.putString(RechargeBankStorageDialog.TYPE, "withdraw");
                                            bundle.putParcelable(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, mRecharge);

                                            rechargeBankStorageDialog = new RechargeBankStorageDialog();
                                            rechargeBankStorageDialog.setArguments(bundle);
                                            rechargeBankStorageDialog.show(getSupportFragmentManager(), "RechargeBankStorageDialog");

                                        } else {
                                            NormalUtils.showToast(IntelligentWithdrawActivity.this, mRecharge.getMsg());
                                        }
                                        break;
                                    case "quick_payment":
                                        if (mRecharge.is_jump()) {
                                            //提现结果页
                                            checkWithdrawResult(mRecharge.getFlt_order_no());

                                        } else {
                                            NormalUtils.showToast(IntelligentWithdrawActivity.this, mRecharge.getMsg());
                                        }
                                        break;
                                    default:
                                        NormalUtils.showToast(IntelligentWithdrawActivity.this, "类型错误0x13");
                                        break;
                                }

                                mAnimDialog.dismiss();

                            }

                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                super.onError(call, response, e);
                                mAnimDialog.dismiss();
                            }
                        });

                break;
            case R.id.btn_get_msg_code:
                if (mAnimDialog == null)
                    mAnimDialog = AnimDialog.showDialog(this);
                mAnimDialog.show();

                String t = String.valueOf(System.currentTimeMillis());
                String r = ApiUtils.getRandom();

                String amount = etCzje.getText().toString();

                if (TextUtils.isEmpty(amount))
                    amount = "0";

                OkGo.post(ApiUtils.postIntelligentSMS())
                        .params("t", t)
                        .params("random", r)
                        .params("sign", NormalUtils.md5(t + r + MyApplication.key))
                        .params("platform_id", withdraw.getPlatform_info().getPlatform_id())
                        .params("user_name", UserUtils.getUserName(this))
                        .params("amount", amount)
                        .params("sms_type", "withdraw")
                        .params("login_token", UserUtils.getLoginToken(this))
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                SMS bean = ParseUtils.parseByGson(s, SMS.class);
                                if (bean.isSuccess()) {
                                    mCountDownTimerUtils = new CountDownTimerUtil(btnGetMsgCode
                                            , 60000, 1000);
                                    mCountDownTimerUtils.start();

                                    sms_auth_token = bean.getSms_auth_token();

                                } else {
                                    NormalUtils.showToast(IntelligentWithdrawActivity.this,
                                            bean.getMsg());
                                }
                                mAnimDialog.dismiss();

                            }

                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                super.onError(call, response, e);
                                NormalUtils.showToast(IntelligentWithdrawActivity.this,
                                        R.string.net_error);
                                mAnimDialog.dismiss();
                            }
                        });
                break;

            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                include_load_error.setVisibility(View.GONE);
                doBusiness(this);
                break;
            case R.id.btn_withdraw_all:
                etCzje.setText(format2(withdraw.getAccount_balance()));
                etCzje.setSelection(etCzje.getText().length());
                break;
        }

    }

    public void checkWithdrawResult(String flt_order_no) {
        Bundle args = new Bundle();
        args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, platform_id);
        args.putString(WithdrawResultActivity.FLT_ORDER, flt_order_no);
        startActivity(WithdrawResultActivity.class, args);
        etCzje.setText("");
        etMsgCode.setText("");
        if (mCountDownTimerUtils != null) {
            mCountDownTimerUtils.cancel();
            mCountDownTimerUtils.onClear();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        bindData(this);
    }

    private class MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            boolean isReady = true;

            if (TextUtils.isEmpty(etCzje.getText())) {
                isReady = false;
            }

            if (TextUtils.isEmpty(etMsgCode.getText()) && layoutMsgCode.getVisibility() == View.VISIBLE)
                isReady = false;

            btnOk.setEnabled(isReady);

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case JavascriptClass.REQUEST_CALL_BACK:
                if (rechargeBankStorageDialog == null || data == null)
                    break;
                rechargeBankStorageDialog.onResult(data.getStringExtra(JavascriptClass.class.getSimpleName()));
                break;

        }
    }
}
