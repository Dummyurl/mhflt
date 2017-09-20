package com.changju.fanlitou.activity.fund;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.changju.fanlitou.R;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.bean.fund.FundManager;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zhangming on 2017/7/18.
 */

public class FundManagerActivity extends BaseActivity {

    //loading&error
    private View include;
    private View include_load_error;

    private int fund_id;
    private TextView tv_manager_name;
    private TextView tv_intruction;
    private LinearLayout ll_empty_manager;
    private FundManager fundManager;
    @Override
    public void initParams(Bundle params) {
        fund_id  = params.getInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_fund_manager;
    }

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "基金-基金经理");

        tv_intruction = (TextView)findViewById(R.id.tv_intruction);
        tv_manager_name = (TextView)findViewById(R.id.tv_manager_name);
        ll_empty_manager = (LinearLayout) findViewById(R.id.ll_empty_manager);
        findViewById(R.id.iv_back_manager).setOnClickListener(this);

        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);
    }

    @Override
    public void doBusiness(Context mContext) {
        OkGo.get(ApiUtils.getFundingManager(mContext,fund_id)).execute(new StringCallback() {

            @Override
            public void onSuccess(String s, Call call, Response response) {
                fundManager = ParseUtils.parseByGson(s, FundManager.class);
                tv_manager_name.setText(fundManager.getManager_name());
                tv_intruction.setText(fundManager.getInstruction());
                if(fundManager.getInstruction().isEmpty()){
                    ll_empty_manager.setVisibility(View.VISIBLE);
                }else{
                    ll_empty_manager.setVisibility(View.GONE);
                }
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.GONE);
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                NormalUtils.showToast(FundManagerActivity.this, R.string.net_error);
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_manager:
                finish();
                break;
            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                include_load_error.setVisibility(View.GONE);
                doBusiness(this);
                break;
        }
    }
}
