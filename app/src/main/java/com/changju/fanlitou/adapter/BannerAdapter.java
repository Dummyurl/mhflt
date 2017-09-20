package com.changju.fanlitou.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by chengww on 2017/5/4.
 */

public class BannerAdapter extends PagerAdapter {
    public List<View> getViewArrayList() {
        return viewArrayList;
    }

    public void setViewArrayList(List<View> viewArrayList) {
        this.viewArrayList = viewArrayList;
    }

    public String[] getmTitleList() {
        return mTitleList;
    }

    public void setmTitleList(String[] mTitleList) {
        this.mTitleList = mTitleList;
    }

    private List<View> viewArrayList;
    private String[] mTitleList;

    public BannerAdapter(List<View> viewArrayList) {
        this.viewArrayList = viewArrayList;
    }

    public BannerAdapter(List<View> viewArrayList, String[] mTitleList) {
        this.viewArrayList = viewArrayList;
        this.mTitleList = mTitleList;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewArrayList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = viewArrayList.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return viewArrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitleList == null)
            return null;
        return mTitleList[position];//页卡标题
    }
}
