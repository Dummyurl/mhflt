package com.changju.fanlitou.fragment.login;

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
import com.changju.fanlitou.util.MessageEvent;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.changju.fanlitou.util.UserUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chengww on 2017/5/15.
 */

public class LoginFragment extends AppFragment {

    private TextView btn_login_login;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    private String phone;

    @Override
    public int bindLayout() {
        return R.layout.fragment_login;
    }

    @Override
    public void doBusiness(Context mContext) {

        if (UserUtils.isLogin(mContext)){
            NormalUtils.showToast(mContext,"非法访问，用户已经登录无法进入登录页");
            getHoldingActivity().finish();
        }

        phone = ((LoginActivity)getHoldingActivity()).getPhone();
        if (!TextUtils.isEmpty(phone)){
            et_phone_login.setText(phone);
            et_phone_login.setSelection(phone.length());
        }
    }

    private EditText et_pwd_login,et_phone_login;

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "我的-登录");

        et_phone_login = (EditText) findViewById(R.id.et_phone_login);
        et_pwd_login = (EditText) findViewById(R.id.et_pwd_login);
        findViewById(R.id.iv_back_login).setOnClickListener(this);
        findViewById(R.id.btn_login_login).setOnClickListener(this);
        findViewById(R.id.password_register).setOnClickListener(this);
        findViewById(R.id.forget_password).setOnClickListener(this);

        btn_login_login = (TextView) findViewById(R.id.btn_login_login);

        MyTextWatcher myTextWatcher = new MyTextWatcher();
        et_phone_login.addTextChangedListener(myTextWatcher);
        et_pwd_login.addTextChangedListener(myTextWatcher);


    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    private AnimDialog mAnimDialog;

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.btn_login_login:
                final String phone = et_phone_login.getText().toString();
                String pwd = et_pwd_login.getText().toString();
                if (TextUtils.isEmpty(phone) || phone.length() < 11 || !NormalUtils.isMobileNO(phone))
                    NormalUtils.showToast(getHoldingActivity(),"请输入合法的手机号");
                else if (TextUtils.isEmpty(pwd))
                    NormalUtils.showToast(getHoldingActivity(),"请输入密码");
                else {
                    String time = String.valueOf(System.currentTimeMillis());
                    String random = ApiUtils.getRandom();

                    if (mAnimDialog == null)
                        mAnimDialog = AnimDialog.showDialog(getActivity());
                    mAnimDialog.show();

                    OkGo.post(ApiUtils.getLogin())
                            .params("phone_number",phone)
                            .params("password",pwd)
                            .params("t",time)
                            .params("random",random)
                            .params("sign", NormalUtils.md5(time + random + MyApplication.key))
                            .params("device_type","android")
                            .params("device_token",MyApplication.deviceToken)
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    LoginBean login = ParseUtils.parseByGson(s,LoginBean.class);
                                    if (login.isSuccess()){
                                        //统计登录行为
                                        Map loginMap = new HashMap();
                                        loginMap.put("userid", phone);
                                        MobclickAgent.onEvent(mActivity, "__login", loginMap);


                                        UserUtils.setLogin(mActivity,login.getLogin_token(),phone);
                                        ((LoginActivity)getHoldingActivity()).startMyActivity();

                                    }else {
                                        NormalUtils.showToast(mActivity,login.getMsg());
                                    }
                                    mAnimDialog.dismiss();
                                    EventBus.getDefault().post(new MessageEvent("platform"));
                                }

                                @Override
                                public void onError(Call call, Response response, Exception e) {
                                    super.onError(call, response, e);
                                    NormalUtils.showToast(mActivity,R.string.net_error);
                                    mAnimDialog.dismiss();
                                }
                            });
                }
                break;
            case R.id.password_register:
                addFragment(RegisterFragment.newInstance());
                break;
            case R.id.forget_password:
                String phoneNumber = et_phone_login.getText().toString();
                Bundle args = new Bundle();
                if (NormalUtils.isMobileNO(phoneNumber)){
                    args.putString(ForgetPasswordFragment.PHONE,phoneNumber);
                }
                args.putInt(LoginActivity.TYPE,LoginActivity.FORGET_PASSWORD);
                Intent intent = new Intent(getHoldingActivity(),LoginActivity.class);
                intent.putExtras(args);
                startActivity(intent);
                break;
            case R.id.iv_back_login:
                getHoldingActivity().finish();
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

            String phone = et_phone_login.getText().toString();
            if (TextUtils.isEmpty(phone) || phone.length() < 11)
                isReady = false;

            String pwd = et_pwd_login.getText().toString();
            if (TextUtils.isEmpty(pwd) || pwd.length() < 6)
                isReady = false;

            btn_login_login.setEnabled(isReady);

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
        }else {
            doBusiness(getHoldingActivity());
        }
    }
}
