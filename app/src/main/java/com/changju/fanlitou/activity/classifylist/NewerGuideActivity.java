package com.changju.fanlitou.activity.classifylist;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.changju.fanlitou.R;
import com.changju.fanlitou.base.BaseActivity;

/**
 * Created by Administrator on 2017/5/25.
 */

public class NewerGuideActivity extends BaseActivity{
    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_classify_list_new_guide;
    }

    @Override
    public void initView(View view) {
//        findViewById(R.id.iv_back_abroad).setOnClickListener(this);
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_abroad:
                finish();
                break;
        }

    }
}
