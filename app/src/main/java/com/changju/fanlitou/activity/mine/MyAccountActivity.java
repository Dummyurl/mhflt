package com.changju.fanlitou.activity.mine;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.MainActivity;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.lock_pattern.util.cache.ACache;
import com.changju.fanlitou.util.MessageEvent;
import com.changju.fanlitou.util.UserUtils;
import com.growingio.android.sdk.collection.GrowingIO;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by chengww on 2017/5/22.
 */

public class MyAccountActivity extends BaseActivity {

    public static final String TAG = "tag";
    private String username;

    private boolean hasGesture;
    private TextView tv_status;
    private ACache aCache;

    @Override
    public void initParams(Bundle params) {
        username = params.getString(TAG);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_my_account;
    }

    @Override
    public void initView(View view) {

        UserUtils.checkLogin(this, MyAccountActivity.class);

        GrowingIO.getInstance().setPageName(this, "我的-我的账户");

        aCache = ACache.get(this);
        tv_status = (TextView) findViewById(R.id.tv_status);
//        TextView tv_username_my_account = (TextView) findViewById(R.id.tv_username_my_account);
//        tv_username_my_account.setText(username);
        findViewById(R.id.iv_back_my_account).setOnClickListener(this);
        findViewById(R.id.btn_log_off_my_account).setOnClickListener(this);
        findViewById(R.id.layout_gesture).setOnClickListener(this);
        findViewById(R.id.layout_change_pwd).setOnClickListener(this);
        findViewById(R.id.layout_manage_bank_card).setOnClickListener(this);

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_my_account:
                onBackPressed();
                break;
            case R.id.btn_log_off_my_account:
                UserUtils.clearLogin(this);
                startActivity(MainActivity.class);
                EventBus.getDefault().post(new MessageEvent("platform"));
                break;
            case R.id.layout_change_pwd:
                startActivity(ChangePwdActivity.class);
                break;
            case R.id.layout_gesture:
                startActivity(ConfirmPWDActivity.class);
                break;
            case R.id.layout_manage_bank_card:
                startActivity(ManagerBankCardActivity.class);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        hasGesture = aCache.hasGesture(UserUtils.getUserName(this));
        if (hasGesture) {
            tv_status.setText("修改");
            tv_status.setTextColor(getResources().getColor(R.color.colorBidName));
        } else {
            tv_status.setText("未开启");
            tv_status.setTextColor(getResources().getColor(R.color.colorTextRed));
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(0, R.anim.out_to_up);
    }
}
