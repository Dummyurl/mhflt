package com.changju.fanlitou.activity.mine;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.changju.fanlitou.R;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.MyApplication;
import com.changju.fanlitou.bean.LoginBean;
import com.changju.fanlitou.lock_pattern.activity.CreateGestureActivity;
import com.changju.fanlitou.ui.EditTextWithDel;
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
 * Created by chengww on 2017/6/30.
 */

public class ConfirmPWDActivity extends BaseActivity {

    private EditTextWithDel et_pwd;
    private TextView btn_next;

    private AnimDialog mAnimDialog;

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_confirm_pwd;
    }

    @Override
    public void initView(View view) {
        UserUtils.checkLogin(this, ConfirmPWDActivity.class);

        GrowingIO.getInstance().setPageName(this, "我的-修改手势密码-确认密码");

        et_pwd = (EditTextWithDel) findViewById(R.id.et_pwd);
        btn_next = (TextView) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);

        findViewById(R.id.iv_back_my_account).setOnClickListener(this);

        et_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s) && s.length() > 5) {
                    btn_next.setEnabled(true);
                } else {
                    btn_next.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_my_account:
                finish();
                break;
            case R.id.btn_next:
                if (mAnimDialog == null)
                    mAnimDialog = AnimDialog.showDialog(this);
                mAnimDialog.show();

                String time = String.valueOf(System.currentTimeMillis());
                String random = ApiUtils.getRandom();
                final String phone = UserUtils.getUserName(this);
                OkGo.post(ApiUtils.getLogin())
                        .params("phone_number", phone)
                        .params("password", et_pwd.getText().toString())
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
                                    UserUtils.setLogin(ConfirmPWDActivity.this, login.getLogin_token(), phone);
                                    ConfirmPWDActivity.this.startActivity(CreateGestureActivity.class);
                                    finish();
                                } else {
                                    NormalUtils.showToast(ConfirmPWDActivity.this, login.getMsg());
                                }
                                mAnimDialog.dismiss();
                            }

                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                super.onError(call, response, e);
                                NormalUtils.showToast(ConfirmPWDActivity.this, R.string.net_error);
                                mAnimDialog.dismiss();
                            }
                        });
                break;
        }
    }
}
