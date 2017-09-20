package com.changju.fanlitou.fragment.account;

import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.MainActivity;
import com.changju.fanlitou.adapter.MyFragmentPagerAdapter;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.util.NormalUtils;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.growingio.android.sdk.collection.GrowingIO;

import java.util.ArrayList;

/**
 * Created by chengww on 2017/5/4.
 */

public class AccountFragment extends LazyFragment {

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private SegmentTabLayout tabLayout;
    private ViewPager viewPager;

    private MainActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public void initView() {


        tabLayout = (SegmentTabLayout) findViewById(R.id.tab_account);
        viewPager = (ViewPager) findViewById(R.id.view_pager_account);

        //不侵入状态栏 + 10dp 上下padding
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, NormalUtils.getStatusBarHeight(activity));
            findViewById(R.id.tv_status_bar).setLayoutParams(params);
        }
    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness(Context mContext) {
        InvestedFragment investedFragment = InvestedFragment.newInstance();
        InvestReportFragment investReportFragment = new InvestReportFragment();
        MoneyBackFragment moneyBackFragment = new MoneyBackFragment();

        mFragments.add(investedFragment);
        mFragments.add(investReportFragment);
        mFragments.add(moneyBackFragment);

        GrowingIO.getInstance().trackFragment(activity, investedFragment);
        GrowingIO.getInstance().trackFragment(activity, investReportFragment);
        GrowingIO.getInstance().trackFragment(activity, moneyBackFragment);

        String[] mTitles = new String[]{activity.getResources().getString(R.string.invested), activity.getResources().getString(R.string.fund_list), activity.getResources().getString(R.string.money_back)};
        viewPager.setAdapter(new MyFragmentPagerAdapter(
                getChildFragmentManager(), mFragments, mTitles));
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setTabData(mTitles);//给Tablayout设置标题
        //为tablayout设置选中监听
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        //给Viewpager设置选中监听，才能使SegmentTablayout和ViewPager双向同步。
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //点账本默认进入“投资报表”tab页
        viewPager.setCurrentItem(1);
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_account;
    }

    public void setCurrentItem(int top_index) {
        if (viewPager != null) {
            viewPager.setCurrentItem(top_index);
            NormalUtils.showToast(activity, "top_index == " + top_index);
        } else {
            NormalUtils.showToast(activity, "acc_viewPager == null");
        }
    }
}
