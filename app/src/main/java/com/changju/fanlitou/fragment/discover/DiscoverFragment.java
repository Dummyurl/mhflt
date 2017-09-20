package com.changju.fanlitou.fragment.discover;

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
import java.util.List;

/**
 * Created by chengww on 2017/5/4.
 */

public class DiscoverFragment extends LazyFragment {

    public List<Fragment> getFragments() {
        return mFragments;
    }

    private List<Fragment> mFragments;

    private SegmentTabLayout tabLayout;
    private ViewPager viewPager;

    private MainActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    /**
     * 外部点击tab
     * @param pos
     */
    public void selectATab(int pos){
        if (pos < 0)
            pos = 0;

        if (pos > 1)
            pos = 1;

        viewPager.setCurrentItem(pos,false);
    }
    @Override
    public void initView() {

        tabLayout = (SegmentTabLayout) findViewById(R.id.tab_discover);
        viewPager = (ViewPager) findViewById(R.id.view_pager_discover);

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
        if (mFragments == null || mFragments.size() == 0){
            mFragments = new ArrayList<>();
        }

        PlatformFragment platformFragment = PlatformFragment.newInstance();
        mFragments.add(platformFragment);

        //改发现页面的标的页面为基金列表页面
        final FundListFragment fundListFragment = new FundListFragment();
        mFragments.add(fundListFragment);

        GrowingIO.getInstance().trackFragment(activity, platformFragment);
        GrowingIO.getInstance().trackFragment(activity, fundListFragment);

        //String[] mTitles = new String[]{"平台", "标的"};
        String[] mTitles = new String[]{"平台", "基金"};
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(
                getChildFragmentManager(), mFragments, mTitles);
        viewPager.setAdapter(myFragmentPagerAdapter);

        tabLayout.setTabData(mTitles);//给Tablayout设置标题
        //为tablayout设置选中监听
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
                fundListFragment.dismissPopWindow();
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        //同时，还要给Viewpager设置选中监听，才能使SegmentTablayout和ViewPager双向同步。
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

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_discover;
    }

    public void setCurrentItem(int top_index) {
        if (viewPager != null){
            viewPager.setCurrentItem(top_index);
        }
    }
}
