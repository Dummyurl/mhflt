package com.changju.fanlitou.fragment.drawable_layout_item;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.MainActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.fragment.discover.DiscoverFragment;
import com.changju.fanlitou.fragment.discover.PlatformFragment;
import com.changju.fanlitou.ui.MyRadioGroup;
import com.changju.fanlitou.util.NormalUtils;

/**
 * Created by chengww on 2017/5/24.
 */

public class PlatformDrawableFragment extends LazyFragment{

    public static PlatformDrawableFragment newInstance() {
        Bundle args = new Bundle();
        args.putBoolean(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, false);
        PlatformDrawableFragment fragment = new PlatformDrawableFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        //不侵入状态栏 + 10dp 上下padding
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, NormalUtils.getStatusBarHeight(getActivity()));
            findViewById(R.id.tv_status_bar).setLayoutParams(params);
        }

        initMyRadioGroup();
    }

    @Override
    protected void doBusiness(Context context) {

    }

    @Override
    public int bindLayout() {
        return R.layout.item_drawable_layout_main;
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.btn_clear_platform:
                clearFilter();
                break;
            case R.id.btn_ok_platform:
                platform_paras[0] = getIndexWithId(platform_type_platform.getCheckedRadioButtonId());
                platform_paras[1] = getIndexWithId(platform_fanlitou.getCheckedRadioButtonId());
                platform_paras[2] = getIndexWithId(platform_activity_platform.getCheckedRadioButtonId());
                platform_paras[3] = getIndexWithId(platform_background_platform.getCheckedRadioButtonId());
                MainActivity activity = (MainActivity) getActivity();
                DrawerLayout drawer_layout_platform = activity.getDrawableLayout();
                drawer_layout_platform.closeDrawer(Gravity.END);
                DiscoverFragment discover = (DiscoverFragment) activity.getFragments().get(1);
                PlatformFragment platform = (PlatformFragment) discover.getFragments().get(0);
                platform.showAnimDialog();
                platform.initData(getPlatform_paras());
                break;
        }
    }

    public int[] clearFilter() {
        initRadioButtonChecked();
        platform_paras = new int[]{0,0,0,0};
        return platform_paras;
    }

    /**
     * 抽屉相关内容
     */
    //平台筛选条件 PlatformFragment 分别对应下面4
    private int[] platform_paras = new int[]{0,0,0,0};

    public int[] getPlatform_paras() {
        return platform_paras;
    }

    private MyRadioGroup platform_type_platform,platform_fanlitou,platform_activity_platform
            ,platform_background_platform;

    private void initMyRadioGroup() {
        platform_type_platform = (MyRadioGroup) findViewById(R.id.platform_type_platform);
        platform_fanlitou = (MyRadioGroup) findViewById(R.id.platform_fanlitou);
        platform_activity_platform = (MyRadioGroup) findViewById(R.id.platform_activity_platform);
        platform_background_platform = (MyRadioGroup) findViewById(R.id.platform_background_platform);
        findViewById(R.id.btn_ok_platform).setOnClickListener(this);
        findViewById(R.id.btn_clear_platform).setOnClickListener(this);
        initRadioButtonChecked();
    }

    /**
     * 初始化RadioButton checked
     */
    private void initRadioButtonChecked() {
        if (platform_type_platform == null)
            return;

        platform_type_platform.setCheckWithoutNotif(R.id.platform_type_platform_0);
        platform_fanlitou.setCheckWithoutNotif(R.id.platform_fanlitou_0);
        platform_activity_platform.setCheckWithoutNotif(R.id.platform_activity_platform_0);
        platform_background_platform.setCheckWithoutNotif(R.id.platform_background_platform_0);
    }

    /**
     * 根据radiobutton id获取index
     *
     * @return index
     */
    private int getIndexWithId(int id) {
        switch (id){
            case R.id.platform_type_platform_0:
            case R.id.platform_fanlitou_0:
            case R.id.platform_activity_platform_0:
            case R.id.platform_background_platform_0:
                return 0;
            case R.id.platform_type_platform_1:
            case R.id.platform_fanlitou_1:
            case R.id.platform_activity_platform_1:
            case R.id.platform_background_platform_1:
                return 1;
            case R.id.platform_type_platform_2:
            case R.id.platform_fanlitou_2:
            case R.id.platform_activity_platform_2:
            case R.id.platform_background_platform_2:
                return 2;
            case R.id.platform_type_platform_3:
            case R.id.platform_activity_platform_3:
            case R.id.platform_background_platform_3:
                return 3;
            case R.id.platform_type_platform_4:
            case R.id.platform_activity_platform_4:
            case R.id.platform_background_platform_4:
                return 4;
            case R.id.platform_type_platform_5:
                return 5;
            case R.id.platform_type_platform_6:
                return 6;
        }
        return 0;
    }
}
