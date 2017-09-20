package com.changju.fanlitou.fragment.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.LoginActivity;
import com.changju.fanlitou.base.LazyFragment;

/**
 * Created by chengww on 2017/5/23.
 */

public class UserNotLoginFragment extends LazyFragment {

    public static UserNotLoginFragment newInstance() {

        Bundle args = new Bundle();
        args.putBoolean(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, false);
        UserNotLoginFragment fragment = new UserNotLoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        findViewById(R.id.tv_login_mine).setOnClickListener(this);
        findViewById(R.id.tv_register_mine).setOnClickListener(this);
    }

    @Override
    protected void doBusiness(Context context) {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_not_login;
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.tv_login_mine:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.tv_register_mine:
                Intent intent =  new Intent(getActivity(), LoginActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(LoginActivity.TYPE,LoginActivity.REGISTER);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }

}
