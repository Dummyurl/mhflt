package com.changju.fanlitou.activity.mine;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.changju.fanlitou.R;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.fragment.bank.BankAddFragment;
import com.changju.fanlitou.util.UserUtils;
import com.growingio.android.sdk.collection.GrowingIO;

/**
 * Created by chengww on 2017/6/6.
 */

public class ChangeBankActivity extends BaseActivity {
    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_change_bank;
    }

    @Override
    public void initView(View view) {
        findViewById(R.id.iv_back_my_account).setOnClickListener(this);

        UserUtils.checkLogin(this,ChangeBankActivity.class);

        GrowingIO.getInstance().setPageName(this, "我的-更换银行卡");
    }

    @Override
    public void doBusiness(Context mContext) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.layout_frame,
                BankAddFragment.newInstance(true)).commit();
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_my_account:
                finish();
                break;
        }
    }
}
