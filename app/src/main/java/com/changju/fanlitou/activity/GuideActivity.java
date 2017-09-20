package com.changju.fanlitou.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.changju.fanlitou.R;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.MyApplication;
import com.growingio.android.sdk.collection.GrowingIO;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends BaseActivity {

    private ViewPager mViewPager;
    //容器
    private List<View> mList = new ArrayList<>();
    private View view1, view2, view3, view4;
    //小圆点
    private ImageView point1, point2, point3, point4;
    //跳过
    private ImageView iv_back;

    private Bundle args;

    @Override
    public void initParams(Bundle params) {
        args = params;
    }

    @Override
    public int bindLayout() {
        setAllowFullScreen(true);
        return R.layout.activity_guide;
    }

    @Override
    public void initView(View view) {

        point1 = (ImageView) findViewById(R.id.point1);
        point2 = (ImageView) findViewById(R.id.point2);
        point3 = (ImageView) findViewById(R.id.point3);
        point4 = (ImageView) findViewById(R.id.point4);

        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        //设置默认图片
        setPointImg(true, false, false, false);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        view1 = View.inflate(this, R.layout.pager_item_one, null);
        view2 = View.inflate(this, R.layout.pager_item_two, null);
        view3 = View.inflate(this, R.layout.pager_item_three, null);
        view4 = View.inflate(this, R.layout.pager_item_four, null);


        view4.findViewById(R.id.btn_start).setOnClickListener(this);

        mList.add(view1);
        mList.add(view2);
        mList.add(view3);
        mList.add(view4);

        // 禁止唤起手势页
        disablePatternLock(false);

    }

    @Override
    public void doBusiness(Context mContext) {

        //设置适配器
        mViewPager.setAdapter(new GuideAdapter());

        //监听ViewPager滑动
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //pager切换
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        setPointImg(true, false, false, false);
                        iv_back.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        setPointImg(false, true, false, false);
                        iv_back.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        setPointImg(false, false, true, false);
                        iv_back.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        setPointImg(false, false, false, true);
                        iv_back.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }

    private class GuideAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mList.get(position));
            return mList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mList.get(position));
            //super.destroyItem(container, position, object);
        }
    }

    //设置小圆点的选中效果
    private void setPointImg(boolean isCheck1, boolean isCheck2, boolean isCheck3, boolean isCheck4) {
        if (isCheck1) {
            point1.setBackgroundResource(R.mipmap.point_on);
        } else {
            point1.setBackgroundResource(R.mipmap.point_off);
        }

        if (isCheck2) {
            point2.setBackgroundResource(R.mipmap.point_on);
        } else {
            point2.setBackgroundResource(R.mipmap.point_off);
        }

        if (isCheck3) {
            point3.setBackgroundResource(R.mipmap.point_on);
        } else {
            point3.setBackgroundResource(R.mipmap.point_off);
        }

        if (isCheck4) {
            point4.setBackgroundResource(R.mipmap.point_on);
        } else {
            point4.setBackgroundResource(R.mipmap.point_off);
        }
    }

    @Override
    public void onBackPressed() {
        //修正为已导航
        SharedPreferences sp = getSharedPreferences(MyApplication.CONFIG, Context.MODE_PRIVATE);
        sp.edit().putBoolean(MyApplication.IS_FIRST, false).apply();
        startActivity(MainActivity.class, args);
        finishNotJump();
    }
}
