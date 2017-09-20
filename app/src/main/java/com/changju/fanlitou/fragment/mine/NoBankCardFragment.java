package com.changju.fanlitou.fragment.mine;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.mine.EnchashmentActivity;
import com.changju.fanlitou.activity.mine.ManagerBankCardActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.growingio.android.sdk.collection.GrowingIO;

/**
 * Created by chengww on 2017/5/26.
 */

public class NoBankCardFragment extends LazyFragment {

    private static String content;
    private static String btn_go;

    public static NoBankCardFragment newInstance(String content,String btn_go) {
        NoBankCardFragment.content = content;
        NoBankCardFragment.btn_go = btn_go;
        Bundle args = new Bundle();
        args.putBoolean(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,false);
        NoBankCardFragment fragment = new NoBankCardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {

        GrowingIO.getInstance().setPageName(this, "我的-提现：无卡绑定");

        TextView tv_content = (TextView) findViewById(R.id.tv_content);
        tv_content.setText(content);
        TextView btn_add_bank_card = (TextView) findViewById(R.id.btn_add_bank_card);
        btn_add_bank_card.setText(btn_go);
        btn_add_bank_card.setOnClickListener(this);
    }

    @Override
    protected void doBusiness(Context context) {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_no_bank_card;
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_bank_card:
                EnchashmentActivity activity = (EnchashmentActivity) getActivity();
                activity.startActivity(ManagerBankCardActivity.class);
                break;
        }
    }
}
