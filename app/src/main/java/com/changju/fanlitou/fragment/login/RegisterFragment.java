package com.changju.fanlitou.fragment.login;

import android.content.Context;
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
import com.changju.fanlitou.util.UserUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by ZM on 2017/5/16.
 */

public class RegisterFragment extends AppFragment {

    private TextView getIDCodeButton;
    private TextView do_register;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_register;
    }

    @Override
    public void doBusiness(Context mContext) {
    }

    private EditText register_phone_number, et_msg_code_register, et_pwd_register, et_invent_register;

    private AnimDialog mAnimDialog;

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "我的-注册");

        if (UserUtils.isLogin(getHoldingActivity())){
            NormalUtils.showToast(getHoldingActivity(),"非法访问，用户已经登录无法进入注册页");
            getHoldingActivity().finish();
        }

        register_phone_number = (EditText) findViewById(R.id.register_phone_number);
        et_msg_code_register = (EditText) findViewById(R.id.et_msg_code_register);
        register_phone_number = (EditText) findViewById(R.id.register_phone_number);
        et_invent_register = (EditText) findViewById(R.id.et_invent_register);
        et_pwd_register = (EditText) findViewById(R.id.et_pwd_register);

        getIDCodeButton = (TextView) findViewById(R.id.get_id_code);
        getIDCodeButton.setOnClickListener(this);

        do_register = (TextView) findViewById(R.id.do_register);
        findViewById(R.id.do_register).setOnClickListener(this);
        findViewById(R.id.iv_back_register).setOnClickListener(this);
        findViewById(R.id.have_account_go_load).setOnClickListener(this);

        MyTextWatcher myTextWatcher = new MyTextWatcher();
        register_phone_number.addTextChangedListener(myTextWatcher);
        et_msg_code_register.addTextChangedListener(myTextWatcher);
        et_pwd_register.addTextChangedListener(myTextWatcher);
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.get_id_code:
                String phone = register_phone_number.getText().toString();
                if (TextUtils.isEmpty(phone) || phone.length() < 11 || !NormalUtils.isMobileNO(phone))
                    NormalUtils.showToast(getHoldingActivity(), "请输入合法的手机号");
                else {

                    if (mAnimDialog == null)
                        mAnimDialog = AnimDialog.showDialog(getActivity());
                    mAnimDialog.show();
                    OkGo.get(ApiUtils.getSendSms(phone, "register"))
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    LoginBean bean = ParseUtils.parseByGson(s, LoginBean.class);
                                    if (bean.isSuccess()){
                                        CountDownTimerUtil mCountDownTimerUtils =
                                                new CountDownTimerUtil(getIDCodeButton, 60000, 1000);
                                        mCountDownTimerUtils.start();
                                    }else {
                                        NormalUtils.showToast(mActivity, bean.getMsg());
                                    }
                                    mAnimDialog.dismiss();
                                }

                                @Override
                                public void onError(Call call, Response response, Exception e) {
                                    super.onError(call, response, e);
                                    NormalUtils.showToast(mActivity,
                                            R.string.net_error);
                                    mAnimDialog.dismiss();
                                }
                            });
                }

                break;
            case R.id.do_register:

                final String phone_number = register_phone_number.getText().toString();
                if (TextUtils.isEmpty(phone_number) || phone_number.length() < 11 || !NormalUtils.isMobileNO(phone_number)) {
                    NormalUtils.showToast(getHoldingActivity(), "请输入合法的手机号");
                    break;
                }

                String msg_code = et_msg_code_register.getText().toString();

                String pwd = et_pwd_register.getText().toString();


                String invent = "";

                if (et_invent_register.getVisibility() == View.VISIBLE){
                    invent = et_invent_register.getText().toString();
                }


                String time = String.valueOf(System.currentTimeMillis());
                String random = ApiUtils.getRandom();

                if (mAnimDialog == null)
                    mAnimDialog = AnimDialog.showDialog(getActivity());

                OkGo.post(ApiUtils.getRegister())
                        .params("phone_number", phone_number)
                        .params("password", pwd)
                        .params("verify_code", msg_code)
                        .params("invite_code", invent)
                        .params("t", time)
                        .params("random", random)
                        .params("sign", NormalUtils.md5(time + random + MyApplication.key))
                        .params("device_type", "android")
                        .params("device_token", MyApplication.deviceToken)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                LoginBean login = ParseUtils.parseByGson(s, LoginBean.class);
                                if (login.isSuccess()) {
                                    LoginActivity activity = (LoginActivity) getHoldingActivity();

                                    // 注册，请将示例中的userid-safei替换成用户注册的ID。
                                    Map regMap = new HashMap();
                                    regMap.put("userid", phone_number);
                                    MobclickAgent.onEvent(activity, "__register", regMap);

                                    MobclickAgent.onEvent(activity, "__login", regMap);

                                    UserUtils.setLogin(RegisterFragment.this.getHoldingActivity(), login.getLogin_token(), phone_number);
                                    activity.startMyActivity();

                                } else {
                                    NormalUtils.showToast(RegisterFragment.this.
                                            getHoldingActivity(), login.getMsg());
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

                break;

            case R.id.iv_back_register:
                removeFragment();
                break;

            case R.id.have_account_go_load:
                addFragment(LoginFragment.newInstance());
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private class MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            boolean isReady = true;

            if (TextUtils.isEmpty(register_phone_number.getText()))
                isReady = false;

            if (TextUtils.isEmpty(et_msg_code_register.getText()))
                isReady = false;

            if (TextUtils.isEmpty(et_pwd_register.getText()))
                isReady = false;


            do_register.setEnabled(isReady);

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (UserUtils.isLogin(getHoldingActivity())){
            removeFragment();
        }
    }
}
