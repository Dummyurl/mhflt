package com.changju.fanlitou.activity.fund;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.changju.fanlitou.R;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.util.NormalUtils;
import com.growingio.android.sdk.collection.GrowingIO;

/**
 * Created by chengww on 2017/8/4.
 */

public class FundQuestionResultActivity extends BaseActivity {

    private String risk_level;
    private boolean isShowHint;


    @Override
    public void initParams(Bundle params) {
        risk_level = params.getString(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
        isShowHint = params.getBoolean(FundQuestionResultActivity.class.getSimpleName());
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_fund_question_result;
    }

    @Override
    public void initView(View view) {
        findViewById(R.id.activity_question_iv_back).setOnClickListener(this);
        findViewById(R.id.bt_fund_question_oncemore).setOnClickListener(this);

        GrowingIO.getInstance().setPageName(this, "基金-风险测评结果");

    }

    @Override
    public void doBusiness(Context mContext) {
        if (TextUtils.isEmpty(risk_level)) {
            NormalUtils.showToast(this, "评测结果为空，请返回重试");
            finish();
        }
        TextView tv_risk_result = (TextView) findViewById(R.id.tv_risk_result);
        tv_risk_result.setText(risk_level);

        findViewById(R.id.tv_hint).setVisibility(isShowHint ? View.VISIBLE : View.GONE);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.activity_question_iv_back:
                finish();
                break;
            case R.id.bt_fund_question_oncemore:
                startActivity(FundQuestionActivity.class);
                break;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Bundle bundle = intent.getExtras();
        initParams(bundle);
        doBusiness(this);
    }
}
