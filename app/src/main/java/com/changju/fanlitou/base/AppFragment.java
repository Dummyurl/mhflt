package com.changju.fanlitou.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

/**
 * Created by chengww on 2017/5/15.
 */

public abstract class AppFragment extends BaseFragment {

    protected BackHandledInterface mBackHandledInterface;

    protected AppActivity mActivity;

    //获取宿主Activity
    protected AppActivity getHoldingActivity() {
        return mActivity;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (AppActivity) activity;
    }

    //添加fragment
    protected void addFragment(BaseFragment fragment) {
        if (null != fragment) {
            getHoldingActivity().addFragment(fragment);
        }
    }

    //移除fragment
    protected void removeFragment() {
        getHoldingActivity().removeFragment();
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(bindLayout());
        if (!(getActivity() instanceof BackHandledInterface)) {
            throw new ClassCastException("Activity must implement BackHandledInterface");
        } else {
            this.mBackHandledInterface = (BackHandledInterface) getActivity();
        }
        initView(getContentView());
        doBusiness(mActivity);
    }

    /**
     * 绑定布局
     *
     * @return
     */
    public abstract int bindLayout();

    /**
     * 业务操作
     *
     * @param mContext
     */
    public abstract void doBusiness(Context mContext);

    /**
     * 初始化控件
     *
     * @param view
     */
    public abstract void initView(final View view);

    @Override
    public void onStart() {
        super.onStart();
        mBackHandledInterface.setSelectedFragment(this);
    }

    /**
     * 所有继承BackHandledFragment的子类都将在这个方法中实现物理Back键按下后的逻辑
     * FragmentActivity捕捉到物理返回键点击事件后会首先询问Fragment是否消费该事件
     * 如果没有Fragment消息时FragmentActivity自己才会消费该事件
     */
    public abstract boolean onBackPressed();
}
