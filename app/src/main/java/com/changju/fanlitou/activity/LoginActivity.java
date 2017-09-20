package com.changju.fanlitou.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.changju.fanlitou.R;
import com.changju.fanlitou.base.AppActivity;
import com.changju.fanlitou.base.AppFragment;
import com.changju.fanlitou.base.BaseFragment;
import com.changju.fanlitou.fragment.login.ForgetPasswordFragment;
import com.changju.fanlitou.fragment.login.LoginFragment;
import com.changju.fanlitou.fragment.login.RegisterFragment;
import com.growingio.android.sdk.collection.GrowingIO;

/**
 * Created by chengww on 2017/5/15.
 * type 1 RegisterFragment
 * type 2 ForgetPasswordFragment
 * default LoginFragment
 */

public class LoginActivity extends AppActivity{

    public AppFragment appFragment;

    private int type;
    public static final String TYPE = "LOGIN_TYPE";
    public static final String CLASS_NAME = "CLASS_NAME";

    public static final int LOGIN = 0;
    public static final int REGISTER = 1;
    public static final int FORGET_PASSWORD = 2;

    private Bundle params;
    private String phone;

    public String getPhone() {
        return phone;
    }

    //跳转Activity class
    private Class clz;

    @Override
    protected BaseFragment getFirstFragment() {
        switch (type){
            case REGISTER:
                RegisterFragment registerFragment = RegisterFragment.newInstance();
                GrowingIO.getInstance().trackFragment(this, registerFragment);
                registerFragment.setArguments(params);
                return registerFragment;
            case FORGET_PASSWORD:
                ForgetPasswordFragment forgetPasswordFragment = ForgetPasswordFragment.newInstance(params);
                GrowingIO.getInstance().trackFragment(this, forgetPasswordFragment);
                return forgetPasswordFragment;
            default:
            case LOGIN:
                LoginFragment loginFragment = LoginFragment.newInstance();
                GrowingIO.getInstance().trackFragment(this, loginFragment);
                return loginFragment;
        }

    }

    @Override
    protected int bindFragmentId() {
        return R.id.fragment_container;
    }

    @Override
    public void initParams(Bundle params) {
        this.params = params;
        type = params.getInt(TYPE);
        clz = (Class) params.getSerializable(CLASS_NAME);
        phone = params.getString(LoginActivity.class.getSimpleName());
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_base;
    }

    @Override
    public void initView(View view) {
        GrowingIO.getInstance().setPageName(this, "我的-登录/注册/忘记密码");
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void widgetClick(View v) {

    }

    public void startMyActivity(){
        if (clz != null)
            startActivity(clz,params);

        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void setSelectedFragment(AppFragment appFragment) {
        this.appFragment = appFragment;
    }

    @Override
    public void onBackPressed() {
        if (appFragment.onBackPressed()) {
            return;
        }
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case RESULT_OK:
                Bundle b = data.getExtras(); //data为B中回传的Intent
                phone = b.getString(ForgetPasswordFragment.PHONE);//str即为回传的值
                break;
        }
    }
}
