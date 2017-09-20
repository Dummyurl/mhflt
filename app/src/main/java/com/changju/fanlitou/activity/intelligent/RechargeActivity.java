package com.changju.fanlitou.activity.intelligent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.WebActivity;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.JavascriptClass;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.MyApplication;
import com.changju.fanlitou.bean.intelligent.Recharge;
import com.changju.fanlitou.bean.intelligent.RechargeConfirm;
import com.changju.fanlitou.bean.intelligent.SMS;
import com.changju.fanlitou.ui.EditTextWithDel;
import com.changju.fanlitou.ui.dialog.AgreementDialog;
import com.changju.fanlitou.ui.dialog.AnimDialog;
import com.changju.fanlitou.ui.dialog.RechargeBankStorageDialog;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.CountDownTimerUtil;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.changju.fanlitou.util.TextClickable;
import com.changju.fanlitou.util.UserUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chengww on 2017/7/10.
 */

public class RechargeActivity extends BaseActivity {

    private ImageView platform_logo_app;
    private TextView tv_bank_name, tv_bank_num, tv_zhye, tv_phone, btn_get_msg_code, tv_top_hint;
    private EditTextWithDel et_czje, et_msg_code;
    private CheckBox check_box;
    private TextView btn_ok;
    private FrameLayout layout_agreement;

    private int bid_id, platform_id;

    //短信验证token
    private String sms_auth_token;

    //loading&error
    private View include;
    private View include_load_error;

    private RechargeConfirm recharge;
    private TextView tv_agreement_info;

    private String platform_bank_interface_type = "";
    private RechargeBankStorageDialog rechargeBankStorageDialog;

    @Override
    public void initParams(Bundle params) {
        bid_id = params.getInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
        platform_id = params.getInt(RechargeBankStorageDialog.PLATFORM_ID);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_recharge;
    }

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "智能投顾-充值");

        btn_ok = (TextView) findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(this);
        findViewById(R.id.iv_back_my_account).setOnClickListener(this);

        platform_logo_app = (ImageView) findViewById(R.id.platform_logo_app);

        tv_bank_name = (TextView) findViewById(R.id.tv_bank_name);
        tv_bank_num = (TextView) findViewById(R.id.tv_bank_num);
        tv_zhye = (TextView) findViewById(R.id.tv_zhye);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        btn_get_msg_code = (TextView) findViewById(R.id.btn_get_msg_code);
        tv_agreement_info = (TextView) findViewById(R.id.tv_agreement_info);
        btn_get_msg_code.setOnClickListener(this);

        tv_top_hint = (TextView) findViewById(R.id.tv_top_hint);

        et_czje = (EditTextWithDel) findViewById(R.id.et_czje);

        et_czje.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString();
                if (str.contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        et_czje.setText(s);
                        et_czje.setSelection(s.length());
                    }
                }
                if (str.trim().substring(0).equals(".")) {
                    s = "0" + s;
                    et_czje.setText(s);
                    et_czje.setSelection(et_czje.getText().length());
                }
                if (str.startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        et_czje.setText(s.subSequence(0, 1));
                        et_czje.setSelection(et_czje.getText().length());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_msg_code = (EditTextWithDel) findViewById(R.id.et_msg_code);

        MyTextWatcher watcher = new MyTextWatcher();
        et_czje.addTextChangedListener(watcher);
        et_msg_code.addTextChangedListener(watcher);

        check_box = (CheckBox) findViewById(R.id.check_box);

        check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                RechargeActivity.this.isChecked = isChecked
                        || check_box.getVisibility() != View.VISIBLE;
                btn_ok.setEnabled(isChecked && RechargeActivity.this.isReady);
            }
        });

        layout_agreement = (FrameLayout) findViewById(R.id.layout_agreement);

        include = findViewById(R.id.include_loading);
        include.setVisibility(View.VISIBLE);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);
    }

    @Override
    public void doBusiness(final Context mContext) {
    }

    private void initAndBindData(final Context mContext) {
        OkGo.get(ApiUtils.getRechargeConfirm(mContext, bid_id))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        recharge = ParseUtils.parseByGson(s, RechargeConfirm.class);

                        tv_top_hint.setText("！您的资金，将直接充值到" +
                                recharge.getPlatform_info().getPlatform_name() +
                                "平台");

                        RechargeConfirm.BoundBankInfoBean bank = recharge.getBound_bank_info();
                        Glide.with(getApplicationContext()).load(bank.getBank_logo())
                                .into(platform_logo_app);
                        tv_bank_name.setText(bank.getBank_name());
                        tv_bank_num.setText(bank.getCard_no());

                        tv_zhye.setText(recharge.getAccount_balance() + "元");

                        et_czje.setHint("至少" + recharge.getMin_recharge_amount() + "元");

                        tv_phone.setText(recharge.getBank_phone_number());

                        if (!recharge.isIs_need_recharge_sms_code()) {
                            findViewById(R.id.layout_msg_code).setVisibility(View.GONE);
                        }

                        if (!recharge.is_need_bank_phone_number()) {
                            findViewById(R.id.layout_phone_number).setVisibility(View.GONE);
                        }

                        List<RechargeConfirm.AgreementListBean> agreements =
                                recharge.getAgreement_list();
                        if (!recharge.isIs_need_recharge_agreement()
                                || agreements == null || agreements.size() < 1)
                            layout_agreement.setVisibility(View.GONE);
                        else {
                            layout_agreement.setVisibility(View.VISIBLE);
                            StringBuilder sb = new StringBuilder("        同意");
                            for (int i = 0; i < agreements.size(); i++) {
                                RechargeConfirm.AgreementListBean agree = agreements.get(i);
                                String title = agree.getTitle();
                                sb.append("《");
                                sb.append(title);
                                sb.append("》");
                            }

                            SpannableString spanableInfo = new SpannableString(sb.toString());
                            ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(android.R.color.transparent));
                            spanableInfo.setSpan(colorSpan, 0, 10, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

                            for (int i = 0; i < agreements.size(); i++) {
                                final RechargeConfirm.AgreementListBean agree =
                                        agreements.get(i);
                                String title = agree.getTitle();
                                spanableInfo.setSpan(new TextClickable(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                /**
                                                 * 协议内容展示类型
                                                 * url：协议内容来自于url字段
                                                 * content：协议内容来自于content字段
                                                 */
                                                Bundle args = new Bundle();
                                                args.putString(AgreementDialog.TITLE, agree.getTitle());
                                                args.putString(AgreementDialog.CONTENT, agree.getContent());
                                                AgreementDialog dialog = new AgreementDialog();
                                                dialog.setArguments(args);
                                                dialog.show(getSupportFragmentManager(), "agree");
                                            }
                                        }), sb.indexOf(title) - 1, sb.indexOf(title) + title.length() + 1, //设置需要监听的字符串位置
                                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            }
                            tv_agreement_info.setText(spanableInfo);  //将处理过的数据set到View里
                            tv_agreement_info.setMovementMethod(LinkMovementMethod.getInstance());
                            tv_agreement_info.setHighlightColor(getResources().getColor(android.R.color.transparent));
                        }

                        platform_bank_interface_type = recharge.getPlatform_info().getPlatform_bank_interface_type();

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
                if (TextUtils.isEmpty(et_czje.getText())) {
                    NormalUtils.showToast(this, "请输入充值金额");
                    break;
                }

                float czje = Float.valueOf(et_czje.getText().toString());

                if (czje < Float.valueOf(recharge.getMin_recharge_amount())) {
                    NormalUtils.showToast(this, "充值金额至少" + recharge.getMin_recharge_amount() + "元");
                    break;
                }

                if (recharge.isIs_need_recharge_sms_code() && TextUtils.isEmpty(et_msg_code.getText())) {
                    NormalUtils.showToast(this, "请输入验证码");
                    break;
                }

                if (layout_agreement.getVisibility() == View.VISIBLE && !check_box.isChecked()) {
                    NormalUtils.showToast(this, "请阅读并同意相关协议");
                    break;
                }

                //充值
                String sms_auth_code = "";
                if (recharge.isIs_need_recharge_sms_code())
                    sms_auth_code = et_msg_code.getText().toString();

                switch (platform_bank_interface_type) {
                    case "bank_storage":
                        Bundle args = new Bundle();
                        args.putString(WebActivity.TITLE, "登录存管银行充值");
                        args.putString(LazyFragment.INTENT_BOOLEAN_LAZYLOAD
                                , ApiUtils.getBankstorageRecharge(RechargeActivity.this,
                                        platform_id, et_czje.getText().toString(), bid_id));
                        startActivityForResult(WebActivity.class, args, JavascriptClass.REQUEST_CALL_BACK);

                        Bundle bundle = new Bundle();
                        bundle.putString(RechargeBankStorageDialog.TITLE, "登录存管银行充值");
                        bundle.putString(RechargeBankStorageDialog.CONTENT,
                                "请在新打开的网上银行页面进行充值，充值未完成之前请不要关闭该窗口");
                        bundle.putString(RechargeBankStorageDialog.POSTIVE, "充值完成");
                        bundle.putString(RechargeBankStorageDialog.TYPE, "recharge");
                        bundle.putInt(RechargeBankStorageDialog.PLATFORM_ID, platform_id);
                        bundle.putInt(RechargeBankStorageDialog.BID_ID, bid_id);
                        rechargeBankStorageDialog = new RechargeBankStorageDialog();
                        rechargeBankStorageDialog.setArguments(bundle);
                        rechargeBankStorageDialog.show(getSupportFragmentManager(), "RechargeBankStorageDialog");

                        break;
                    case "quick_payment":
                        if (mAnimDialog == null)
                            mAnimDialog = AnimDialog.showDialog(this);
                        mAnimDialog.show();

                        String time = String.valueOf(System.currentTimeMillis());
                        String random = ApiUtils.getRandom();

                        OkGo.post(ApiUtils.postRecharge())
                                .params("t", time)
                                .params("random", random)
                                .params("sign", NormalUtils.md5(time + random + MyApplication.key))
                                .params("user_name", UserUtils.getUserName(this))
                                .params("login_token", UserUtils.getLoginToken(this))
                                .params("platform_id", recharge.getPlatform_info().getPlatform_id())
                                .params("bid_id", bid_id)
                                .params("amount", et_czje.getText().toString())
                                .params("sms_auth_code", sms_auth_code)
                                .params("sms_auth_token", sms_auth_token)
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(String s, Call call, Response response) {
                                        final Recharge mRecharge = ParseUtils.parseByGson(s, Recharge.class);

                                        startToRechargeResult(mRecharge.isSuccess(), mRecharge.getMsg());

                                        mAnimDialog.dismiss();

                                    }

                                    @Override
                                    public void onError(Call call, Response response, Exception e) {
                                        super.onError(call, response, e);
                                        mAnimDialog.dismiss();
                                        NormalUtils.showToast(RechargeActivity.this, R.string.net_error);
                                    }
                                });

                        break;
                    default:
                        NormalUtils.showToast(RechargeActivity.this, "平台类型错误0x13");
                        break;
                }

                break;
            case R.id.btn_get_msg_code:


                String t = String.valueOf(System.currentTimeMillis());
                String r = ApiUtils.getRandom();

                String amount = et_czje.getText().toString();

                float cz;
                if (TextUtils.isEmpty(amount))
                    cz = 0;
                else
                    cz = Float.valueOf(amount);

                if (cz < Float.valueOf(recharge.getMin_recharge_amount())) {
                    NormalUtils.showToast(this, "充值金额至少" + recharge.getMin_recharge_amount() + "元");
                    break;
                }

                if (mAnimDialog == null)
                    mAnimDialog = AnimDialog.showDialog(this);
                mAnimDialog.show();

                OkGo.post(ApiUtils.postIntelligentSMS())
                        .params("t", t)
                        .params("random", r)
                        .params("sign", NormalUtils.md5(t + r + MyApplication.key))
                        .params("platform_id", recharge.getPlatform_info().getPlatform_id())
                        .params("user_name", UserUtils.getUserName(this))
                        .params("amount", amount)
                        .params("sms_type", "recharge")
                        .params("login_token", UserUtils.getLoginToken(this))
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                SMS bean = ParseUtils.parseByGson(s, SMS.class);
                                if (bean.isSuccess()) {
                                    CountDownTimerUtil mCountDownTimerUtils =
                                            new CountDownTimerUtil(btn_get_msg_code
                                                    , 60000, 1000);
                                    mCountDownTimerUtils.start();

                                    sms_auth_token = bean.getSms_auth_token();

                                } else {
                                    NormalUtils.showToast(RechargeActivity.this,
                                            bean.getMsg());
                                }
                                mAnimDialog.dismiss();

                            }

                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                super.onError(call, response, e);
                                NormalUtils.showToast(RechargeActivity.this,
                                        R.string.net_error);
                                mAnimDialog.dismiss();
                            }
                        });
                break;
            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                include_load_error.setVisibility(View.GONE);
                initAndBindData(this);
                break;
        }
    }

    public void startToRechargeResult(boolean isSuccess, String msg) {
        if (isSuccess) {
            NormalUtils.showToast(this, msg);
            setResult(RESULT_OK);
            finish();
        } else {
            NormalUtils.showToast(this, msg);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initAndBindData(this);
    }

    private boolean isReady;
    private boolean isChecked = true;

    private class MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            isReady = true;

            if (TextUtils.isEmpty(et_czje.getText())) {
                isReady = false;
            }

            if (recharge.isIs_need_recharge_sms_code() && TextUtils.isEmpty(et_msg_code.getText()))
                isReady = false;

            btn_ok.setEnabled(isReady && isChecked);

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
