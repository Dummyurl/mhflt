package com.changju.fanlitou.fragment.mine;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.MainActivity;
import com.changju.fanlitou.activity.mine.EnchashmentActivity;
import com.changju.fanlitou.activity.mine.MyAccountActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.bean.mine.UserInfo;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.changju.fanlitou.util.UserUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chengww on 2017/5/22.
 */

public class UserFragment extends LazyFragment {

    public static UserFragment newInstance() {

        Bundle args = new Bundle();
        args.putBoolean(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, false);
        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private TextView tv_username_user,tv_money_user
            ,tv_available_balance_user,tv_accumulated_rebates_user
            ,tv_freezing_amount_user,tv_year_yld;

    @Override
    protected void initView() {
        tv_username_user = (TextView) findViewById(R.id.tv_username_user);
        String userName = UserUtils.getUserName(getActivity());
        tv_username_user.setText(userName.substring(0, 3)
                + "****" + userName.substring(7, userName.length()));

        tv_available_balance_user = (TextView) findViewById(R.id.tv_available_balance_user);
        tv_money_user = (TextView) findViewById(R.id.tv_money_user);
        tv_accumulated_rebates_user = (TextView) findViewById(R.id.tv_accumulated_rebates_user);
        tv_freezing_amount_user = (TextView) findViewById(R.id.tv_freezing_amount_user);
        tv_year_yld = (TextView) findViewById(R.id.tv_year_yld);
        findViewById(R.id.btn_withdrawals_user).setOnClickListener(this);
        findViewById(R.id.iv_user_user).setOnClickListener(this);
        tv_username_user.setOnClickListener(this);
    }

    @Override
    protected void doBusiness(Context context) {
        bindData();
    }

    private void bindData() {
        OkGo.get(ApiUtils.getAccountInfo(getActivity()))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        UserInfo info = ParseUtils.parseByGson(s,UserInfo.class);
                        tv_username_user.setText(info.getUser_name());

                        username = info.getUser_name();
                        tv_money_user.setText("今日返利" +
                                info.getToday_bonus() + "元");
                        tv_available_balance_user.setText(info.getJifen_balance());
                        tv_accumulated_rebates_user.setText(info.getBonus_amount());
                        tv_freezing_amount_user.setText(info.getBalance());
                        tv_year_yld.setText("七日年化收益：" + info.getYear_yld() +
                                "%");

                        //显示隐藏我的活动
                        MineFragment mineFragment = (MineFragment) getParentFragment();
                        if (mineFragment != null){
                            //传递url
                            mineFragment.setMy_activity_url(info.getMy_activity_url());

                            if (info.is_show_my_activity_menu()){
                                mineFragment.showMyActivity();
                            }else {
                                mineFragment.hideMyActivity();
                            }
                        }

                    }
                });
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_user;
    }

    private String username;
    @Override
    public void widgetClick(View v) {
        MainActivity activity = (MainActivity) getActivity();
        switch (v.getId()){
            case R.id.iv_user_user:
            case R.id.tv_username_user:
                Bundle bundle = new Bundle();
                bundle.putString(MyAccountActivity.TAG, username);
                activity.startActivity(MyAccountActivity.class,bundle);
                activity.overridePendingTransition(R.anim.in_from_down,R.anim.activity_stay);
                break;
            case R.id.btn_withdrawals_user:
                activity.startActivity(EnchashmentActivity.class);
                break;
        }

    }


}
