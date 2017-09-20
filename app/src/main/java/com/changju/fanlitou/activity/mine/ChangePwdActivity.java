package com.changju.fanlitou.activity.mine;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.changju.fanlitou.R;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.MyApplication;
import com.changju.fanlitou.bean.LoginBean;
import com.changju.fanlitou.ui.dialog.AnimDialog;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.changju.fanlitou.util.UserUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;


/**
 * Created by chengww on 2017/5/26.
 */

public class ChangePwdActivity extends BaseActivity {

    private TextView btn_ok_chang_pwd;

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_change_pwd;
    }

    private EditText et_pwd,et_new_pwd,et_new_pwd_twice;
    @Override
    public void initView(View view) {

        UserUtils.checkLogin(this,ChangePwdActivity.class);

        GrowingIO.getInstance().setPageName(this, "我的-修改密码");

        findViewById(R.id.iv_back_my_account).setOnClickListener(this);
        btn_ok_chang_pwd = (TextView) findViewById(R.id.btn_ok_chang_pwd);
        btn_ok_chang_pwd.setOnClickListener(this);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        et_new_pwd = (EditText) findViewById(R.id.et_new_pwd);
        et_new_pwd_twice = (EditText) findViewById(R.id.et_new_pwd_twice);

        MyTextWatcher myTextWatcher = new MyTextWatcher();
        et_pwd.addTextChangedListener(myTextWatcher);
        et_new_pwd.addTextChangedListener(myTextWatcher);
        et_new_pwd_twice.addTextChangedListener(myTextWatcher);
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    private AnimDialog mAnimDialog;

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_my_account:
                finish();
                break;
            case R.id.btn_ok_chang_pwd:
                String pwd = et_pwd.getText().toString();
                String new_pwd = et_new_pwd.getText().toString();
                String new_pwd_twice = et_new_pwd_twice.getText().toString();
                if (pwd.length() > 12 || pwd.length() < 6){
                    NormalUtils.showToast(this,"密码长度有误!");
                }else if (new_pwd.length() > 12 || new_pwd.length() < 6){
                    NormalUtils.showToast(this,"请输入6-12位密码");
                }else if (!new_pwd_twice.equals(new_pwd)){
                    NormalUtils.showToast(this,"新密码两次输入不一致");
                }else {
                    if (mAnimDialog == null)
                        mAnimDialog = AnimDialog.showDialog(this);
                    mAnimDialog.show();

                    String time = String.valueOf(System.currentTimeMillis());
                    String random = ApiUtils.getRandom();

                    final String user_name = UserUtils.getUserName(this);
                    OkGo.post(ApiUtils.postChangePWD())
                            .params("old_password", pwd)
                            .params("new_password", new_pwd)
                            .params("new_password_confirm", new_pwd_twice)
                            .params("user_name",user_name)
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
                                        UserUtils.setLogin(ChangePwdActivity.this,
                                                login.getLogin_token(), user_name);
                                        NormalUtils.showToast(ChangePwdActivity.this, "密码修改成功!");
                                        ChangePwdActivity.this.finish();
                                    } else {
                                        NormalUtils.showToast(ChangePwdActivity.this,
                                                login.getMsg());
                                    }
                                    mAnimDialog.dismiss();
                                }

                                @Override
                                public void onError(Call call, Response response, Exception e) {
                                    super.onError(call, response, e);
                                    NormalUtils.showToast(ChangePwdActivity.this,
                                            R.string.net_error);
                                    mAnimDialog.dismiss();
                                }
                            });
                }
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

            String pwd = et_pwd.getText().toString();
            if (TextUtils.isEmpty(pwd) || pwd.length() < 6)
                isReady = false;

            String newPwd = et_new_pwd.getText().toString();
            if (TextUtils.isEmpty(newPwd) || newPwd.length() < 6)
                isReady = false;

            String newPwdTwice = et_new_pwd_twice.getText().toString();
            if (TextUtils.isEmpty(newPwdTwice) || newPwdTwice.length() < 6)
                isReady = false;

            btn_ok_chang_pwd.setEnabled(isReady);

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
