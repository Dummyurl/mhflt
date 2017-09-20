package com.changju.fanlitou.activity.mine;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.changju.fanlitou.R;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.bean.mine.BankInfo;
import com.changju.fanlitou.fragment.bank.BankAddFragment;
import com.changju.fanlitou.fragment.bank.BankManagerFragment;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.changju.fanlitou.util.UserUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chengww on 2017/5/31.
 */

public class ManagerBankCardActivity extends BaseActivity {

    private TextView tv_title;

    /**
     * 上一次加载的字符串
     */
    private String lastS;

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_manager_bank;
    }

    private View include;
    private View include_load_error;

    @Override
    public void initView(View view) {

        UserUtils.checkLogin(this,ManagerBankCardActivity.class);

        GrowingIO.getInstance().setPageName(this, "我的-银行卡管理");

        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);
        tv_title = (TextView) findViewById(R.id.tv_title);
        findViewById(R.id.iv_back_my_account).setOnClickListener(this);
    }

    @Override
    public void doBusiness(final Context mContext) {
    }

    public void initData(Context mContext) {
        OkGo.get(ApiUtils.getBankInfo(mContext))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        if (s == null || s.equals(lastS))
                            return;

                        lastS = s;

                        BankInfo mBankInfo = ParseUtils.parseByGson(s, BankInfo.class);
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        //实名认证
                        if (mBankInfo.isIs_verified()) {
                            //是否绑定银行卡
                            if (mBankInfo.isIs_bind_flt_card()) {
                                List<BankInfo.BindBankListBean> bindBankListBean =
                                        mBankInfo.getBind_bank_list();
                                if (bindBankListBean != null && bindBankListBean.size() > 0
                                        && mBankInfo.isAlready_has_province_city_flag()) {

                                    tv_title.setText("我的银行卡");

                                    BankManagerFragment bankManagerFragment = BankManagerFragment.newInstance(mBankInfo);
                                    GrowingIO.getInstance().trackFragment(ManagerBankCardActivity.this, bankManagerFragment);

                                    fragmentTransaction.replace(R.id.layout_frame,
                                            bankManagerFragment).commit();
                                } else {
                                    addBank(fragmentTransaction);
                                }
                            } else {
                                //实名未绑卡
                                addBank(fragmentTransaction);
                            }
                        } else {
                            //未实名 未绑卡
                            addBank(fragmentTransaction);
                        }

                        include.setVisibility(View.GONE);
                        include_load_error.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        NormalUtils.showToast(ManagerBankCardActivity.this,
                                R.string.net_error);
                        include.setVisibility(View.GONE);
                        include_load_error.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void addBank(FragmentTransaction fragmentTransaction) {

        BankAddFragment bankAddFragment = BankAddFragment.newInstance(false);
        GrowingIO.getInstance().trackFragment(this, bankAddFragment);

        fragmentTransaction.replace(R.id.layout_frame,
                bankAddFragment).commit();
        tv_title.setText("添加银行卡");
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_my_account:
                finish();
                break;
            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                include_load_error.setVisibility(View.GONE);
                doBusiness(this);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData(this);
    }
}
