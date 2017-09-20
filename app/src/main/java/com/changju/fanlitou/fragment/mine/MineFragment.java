package com.changju.fanlitou.fragment.mine;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.LoginActivity;
import com.changju.fanlitou.activity.MainActivity;
import com.changju.fanlitou.activity.WebActivity;
import com.changju.fanlitou.activity.fund.MyFundActivity;
import com.changju.fanlitou.activity.mine.FanlibaoActivity;
import com.changju.fanlitou.activity.mine.InvestRecordActivity;
import com.changju.fanlitou.activity.mine.MoneyDetailActivity;
import com.changju.fanlitou.activity.mine.PlatformManagerActivity;
import com.changju.fanlitou.activity.mine.RedBagActivity;
import com.changju.fanlitou.activity.mine.TakeMoneyRecordActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.ui.ImageTextButton;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.UserUtils;
import com.growingio.android.sdk.collection.GrowingIO;

/**
 * Created by chengww on 2017/5/4.
 */

public class MineFragment extends LazyFragment {

    private String my_activity_url;
    /**
     * 提现记录or我的活动
     */
    private ImageTextButton img7;

    private boolean isShowingMyActivity;

    public void setMy_activity_url(String my_activity_url) {
        this.my_activity_url = my_activity_url;
    }


    @Override
    public int bindLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView() {
        //不侵入状态栏 + 10dp 上下padding
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, NormalUtils.getStatusBarHeight(getActivity()));
            findViewById(R.id.tv_status_bar).setLayoutParams(params);
        }

        GrowingIO.getInstance().setPageName(this, "我的");

        ImageTextButton img1 = (ImageTextButton) findViewById(R.id.mine_1);
        img1.setOnClickListener(this);
        img1.setImageSize(getActivity(),28,28);
        img1.setTextMargin(23);

        ImageTextButton img2 = (ImageTextButton) findViewById(R.id.mine_2);
        img2.setOnClickListener(this);
        img2.setImageSize(getActivity(),28,28);
        img2.setTextMargin(23);

        ImageTextButton img3 = (ImageTextButton) findViewById(R.id.mine_3);
        img3.setOnClickListener(this);
        img3.setImageSize(getActivity(),28,28);
        img3.setTextMargin(23);

        ImageTextButton img4 = (ImageTextButton) findViewById(R.id.mine_4);
        img4.setOnClickListener(this);
        img4.setImageSize(getActivity(),28,28);
        img4.setTextMargin(23);

        ImageTextButton img5 = (ImageTextButton) findViewById(R.id.mine_5);
        img5.setOnClickListener(this);
        img5.setImageSize(getActivity(),28,28);
        img5.setTextMargin(23);

        ImageTextButton img6 = (ImageTextButton) findViewById(R.id.mine_6);
        img6.setOnClickListener(this);
        img6.setImageSize(getActivity(),28,28);
        img6.setTextMargin(23);

        img7 = (ImageTextButton) findViewById(R.id.mine_7);
        img7.setOnClickListener(this);
        img7.setImageSize(getActivity(),28,28);
        img7.setTextMargin(23);

        ImageTextButton img8 = (ImageTextButton) findViewById(R.id.mine_8);
        img8.setOnClickListener(this);
        img8.setImageSize(getActivity(),28,28);
        img8.setTextMargin(23);

        ImageTextButton img9 = (ImageTextButton) findViewById(R.id.mine_9);
        img9.setOnClickListener(this);
        img9.setImageSize(getActivity(),28,28);
        img9.setTextMargin(23);

        findViewById(R.id.tv_kfdh).setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        MainActivity activity = (MainActivity) getActivity();
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.mine_1:
                if (!UserUtils.isLogin(activity)) {
                    bundle.putSerializable(LoginActivity.CLASS_NAME,
                            FanlibaoActivity.class);
                    activity.startActivity(LoginActivity.class, bundle);
                } else
                    //startActivity(new Intent(getActivity(), MyFundActivity.class));
                    startActivity(new Intent(getActivity(), FanlibaoActivity.class));
                break;
            //投资记录
            case R.id.mine_2:
                if (!UserUtils.isLogin(activity)) {
                    bundle.putSerializable(LoginActivity.CLASS_NAME,
                            InvestRecordActivity.class);
                    activity.startActivity(LoginActivity.class, bundle);
                } else
                    activity.startActivity(InvestRecordActivity.class);
                break;
            //资金明细
            case R.id.mine_3:
                if (!UserUtils.isLogin(activity)) {
                    bundle.putSerializable(LoginActivity.CLASS_NAME,
                            MoneyDetailActivity.class);
                    activity.startActivity(LoginActivity.class, bundle);
                } else
                    activity.startActivity(MoneyDetailActivity.class);
                break;
            //我的基金
            case R.id.mine_4:
                if (!UserUtils.isLogin(activity)) {
                    bundle.putSerializable(LoginActivity.CLASS_NAME,
                            MyFundActivity.class);
                    activity.startActivity(LoginActivity.class, bundle);
                } else
                    activity.startActivity(MyFundActivity.class);

                break;
            //邀请有礼
            case R.id.mine_5:
                bundle.putString(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, ApiUtils.getInviteH5());
                bundle.putBoolean(WebActivity.class.getSimpleName(), false);
                bundle.putInt(WebActivity.STATUS_TYPE,WebActivity.TYPE_ACTIVITY);
                activity.startActivity(WebActivity.class,bundle);
                break;
                //平台管理
            case R.id.mine_6:
                if (!UserUtils.isLogin(activity)) {
                    bundle.putSerializable(LoginActivity.CLASS_NAME,
                            PlatformManagerActivity.class);
                    activity.startActivity(LoginActivity.class, bundle);
                } else {
                    activity.startActivity(PlatformManagerActivity.class);
                }

                break;
            //提现记录/我的活动
            case R.id.mine_7:
                if (isShowingMyActivity){
                    bundle.putString(LoginActivity.CLASS_NAME,
                            TextUtils.isEmpty(my_activity_url) ?
                                    ApiUtils.getMyselfActivityH5() : my_activity_url);
                    bundle.putString(WebActivity.TITLE,"我的活动");
                    activity.startActivity(WebActivity.class, bundle);
                }else {
                    if (!UserUtils.isLogin(activity)) {
                        bundle.putSerializable(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,
                                TakeMoneyRecordActivity.class);
                        activity.startActivity(LoginActivity.class, bundle);
                    } else
                        activity.startActivity(TakeMoneyRecordActivity.class);
                }
                break;
            //新手指南
            case R.id.mine_8:
                bundle.putString(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, ApiUtils.getBeginnerGuideH5());
//                bundle.putString(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, "file:///android_asset/example.html");
                bundle.putString(WebActivity.TITLE,"新手指南");
                activity.startActivity(WebActivity.class, bundle);
                break;
            //我的奖励
            case R.id.mine_9:
                if (!UserUtils.isLogin(activity)) {
                    bundle.putSerializable(LoginActivity.CLASS_NAME,
                            RedBagActivity.class);
                    activity.startActivity(LoginActivity.class, bundle);
                } else
                    activity.startActivity(RedBagActivity.class);
                break;

            //客服电话
            case R.id.tv_kfdh:
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                phoneIntent.setData(Uri.parse("tel:4006018132"));
                startActivity(phoneIntent);
                break;
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        bindData(mContext);
    }

    private void bindData(Context mContext) {

        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        if (UserUtils.isLogin(mContext)) {
            fragmentTransaction.replace(R.id.layout_not_login_mine,
                    UserFragment.newInstance()).commitAllowingStateLoss();
        } else {
            fragmentTransaction.replace(R.id.layout_not_login_mine,
                    UserNotLoginFragment.newInstance()).commitAllowingStateLoss();
        }
    }

    private boolean isLogin;

    @Override
    protected void onResumeLazy() {
        super.onResumeLazy();
        boolean login = UserUtils.isLogin(getActivity());
        //未登录：隐藏我的活动
        if (!login){
            hideMyActivity();
        }

        if (isLogin != login){
            bindData(getActivity());
            isLogin = login;
        }

    }

    /**
     * 显示我的活动
     */
    public void showMyActivity(){
        if (!isShowingMyActivity){
            img7.setTextViewText("我的活动");
            img7.setImageResource(R.mipmap.ic_my_activity);

        }
        isShowingMyActivity = true;

    }

    /**
     * 隐藏我的活动
     */
    public void hideMyActivity(){
        if (isShowingMyActivity){
            img7.setTextViewText(getString(R.string.presentation_record));
            img7.setImageResource(R.mipmap.img7);
        }
        isShowingMyActivity = false;
    }
}
