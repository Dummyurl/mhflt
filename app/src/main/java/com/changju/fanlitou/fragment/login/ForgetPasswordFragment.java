package com.changju.fanlitou.fragment.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.LoginActivity;
import com.changju.fanlitou.base.AppFragment;
import com.changju.fanlitou.base.MyApplication;
import com.changju.fanlitou.bean.LoginBean;
import com.changju.fanlitou.ui.dialog.AnimDialog;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.CountDownTimerUtil;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chengww on 2017/5/15.
 */

public class ForgetPasswordFragment extends AppFragment {

    public static final String PHONE = "PHONE";
    private TextView getIDCodeButton;
    private Bundle args;
    private TextView btn_ensure;

    public static ForgetPasswordFragment newInstance(Bundle args) {
        ForgetPasswordFragment forgetPasswordFragment = new ForgetPasswordFragment();
        forgetPasswordFragment.args = args;
        return forgetPasswordFragment;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_forget_password;
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    private EditText et_phone_number, et_msg_code, et_password;

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "我的-忘记密码");

        et_phone_number = (EditText) findViewById(R.id.et_phone_number);
        et_msg_code = (EditText) findViewById(R.id.et_msg_code);
        et_password = (EditText) findViewById(R.id.et_password);
        getIDCodeButton = (TextView) findViewById(R.id.get_id_code);
        getIDCodeButton.setOnClickListener(this);

        findViewById(R.id.btn_ensure).setOnClickListener(this);
        btn_ensure = (TextView) findViewById(R.id.btn_ensure);

        findViewById(R.id.iv_back_forget).setOnClickListener(this);

        if (args != null) {
            String phone = args.getString(PHONE);
            if (!TextUtils.isEmpty(phone)) {
                et_phone_number.setText(phone);
            }
        }

        MyTextWatcher myTextWatcher = new MyTextWatcher();
        et_phone_number.addTextChangedListener(myTextWatcher);
        et_msg_code.addTextChangedListener(myTextWatcher);
        et_password.addTextChangedListener(myTextWatcher);
    }

    @Override
    public boolean onBackPressed() {
        removeFragment();
        return true;
    }

    private AnimDialog mAnimDialog;

    String phone_number, password, verify_code;

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ensure:

                phone_number = et_phone_number.getText().toString();
                password = et_password.getText().toString();
                verify_code = et_msg_code.getText().toString();

                if (TextUtils.isEmpty(phone_number) || phone_number.length() < 11 || !NormalUtils.isMobileNO(phone_number))
                    NormalUtils.showToast(getHoldingActivity(), "请输入合法的手机号");
                else if (verify_code.length() < 6)
                    NormalUtils.showToast(getHoldingActivity(), "短信验证码错误");
                else if (password.length() < 6)
                    NormalUtils.showToast(getHoldingActivity(), "密码至少6位");
                else {

                    if (mAnimDialog == null)
                        mAnimDialog = AnimDialog.showDialog(getActivity());
                    mAnimDialog.show();

                    String time = String.valueOf(System.currentTimeMillis());
                    String random = ApiUtils.getRandom();

                    OkGo.post(ApiUtils.postForgetPassword())
                            .params("phone_number", phone_number)
                            .params("password", password)
                            .params("verify_code", verify_code)
                            .params("t", time)
                            .params("random", random)
                            .params("sign", NormalUtils.md5(time + random
                                    + MyApplication.key))
                            .params("device_type", "android")
                            .params("device_token", MyApplication.deviceToken)
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    LoginBean login = ParseUtils.parseByGson(s, LoginBean.class);
                                    if (login.isSuccess()) {
//                                        UserUtils.setLogin(mActivity,
//                                                login.getLogin_token(), phone_number);
                                        LoginActivity activity = (LoginActivity) getHoldingActivity();
//                                        activity.startMyActivity();
                                        Bundle args = new Bundle();
                                        args.putString(ForgetPasswordFragment.PHONE, phone_number);
                                        Intent intent = new Intent();
                                        intent.putExtras(args);
                                        activity.setResult(Activity.RESULT_OK, intent);
                                        activity.finish();
                                    } else {
                                        NormalUtils.showToast(mActivity, login.getMsg());
                                    }
                                    mAnimDialog.dismiss();
                                }

                                @Override
                                public void onError(Call call, Response response, Exception e) {
                                    super.onError(call, response, e);
                                    NormalUtils.showToast(mActivity, R.string.net_error);
                                    mAnimDialog.dismiss();
                                }
                            });
                }
                break;
            case R.id.get_id_code:
                String phone = et_phone_number.getText().toString();
                if (TextUtils.isEmpty(phone) || phone.length() < 11 || !NormalUtils.isMobileNO(phone))
                    NormalUtils.showToast(mActivity, "请输入合法的手机号");
                else {
                    if (mAnimDialog == null)
                        mAnimDialog = AnimDialog.showDialog(mActivity);
                    mAnimDialog.show();

                    OkGo.get(ApiUtils.getSendSms(phone, "forget_pass"))
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    LoginBean bean = ParseUtils.parseByGson(s, LoginBean.class);
                                    if (bean.isSuccess()) {
                                        CountDownTimerUtil mCountDownTimerUtils =
                                                new CountDownTimerUtil(getIDCodeButton, 60000, 1000);
                                        mCountDownTimerUtils.start();
                                    } else {
                                        NormalUtils.showToast(
                                                mActivity, bean.getMsg());
                                    }
                                    mAnimDialog.dismiss();

                                }

                                @Override
                                public void onError(Call call, Response response, Exception e) {
                                    super.onError(call, response, e);
                                    NormalUtils.showToast(mActivity, R.string.net_error);
                                    mAnimDialog.dismiss();
                                }
                            });
                }

                break;

            case R.id.iv_back_forget:
                removeFragment();
                break;
        }
    }

    private class MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            boolean isReady = true;

            String phone = et_phone_number.getText().toString();

            if (TextUtils.isEmpty(phone) || phone.length() != 11)
                isReady = false;

            String msg_code = et_msg_code.getText().toString();
            if (TextUtils.isEmpty(msg_code))
                isReady = false;

            String text = et_password.getText().toString();
            if (TextUtils.isEmpty(text) || text.length() < 6)
                isReady = false;

            btn_ensure.setEnabled(isReady);

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
