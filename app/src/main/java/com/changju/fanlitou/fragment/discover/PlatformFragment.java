package com.changju.fanlitou.fragment.discover;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.MainActivity;
import com.changju.fanlitou.activity.platform.PlatformDetailActivity;
import com.changju.fanlitou.activity.platform.PlatformSearchActivity;
import com.changju.fanlitou.adapter.PlatformSimpleAdapter;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.discover.Platform;
import com.changju.fanlitou.ui.dialog.AnimDialog;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.FileUtils;
import com.changju.fanlitou.util.MessageEvent;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chengww on 2017/5/8.
 */

public class PlatformFragment extends LazyFragment {

    private AnimDialog mAnimDialog;

    public void showAnimDialog() {
        mAnimDialog.show();
    }

    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSwipeRefreshLayout;
    private PlatformSimpleAdapter adapter;

    private Platform platform;
    private List<Platform.PlatformListBean> listBean;
//    private PlatformSearchFragment platformSearchFragment;

    //平台页面数据
//    private View layout_platform;
//    private FragmentManager childFragmentManager;

    private MainActivity activity;

    //是否刷新
    private boolean isRefresh;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    public static PlatformFragment newInstance() {

        Bundle args = new Bundle();
        args.putBoolean(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, false);
        PlatformFragment fragment = new PlatformFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {

        GrowingIO.getInstance().setPageName(this, "发现-平台");
        EventBus.getDefault().register(this);

        mAnimDialog = AnimDialog.showDialog(activity);
        findViewById(R.id.et_search).setOnClickListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view_pla);
        mRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(activity));
        mSwipeRefreshLayout = (SmartRefreshLayout) findViewById(R.id.swipe_refresh_pla);
        mSwipeRefreshLayout.setRefreshHeader(new MaterialHeader(activity).setColorSchemeColors(0xfff95353));
        mSwipeRefreshLayout.setEnableHeaderTranslationContent(false);
        mSwipeRefreshLayout.setEnableLoadmore(false);
        mSwipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                refreshlayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isRefresh = true;
                        initData(activity.getPlatform_paras());
                    }
                }, 1000);
            }
        });
        showAnimDialog();

        ImageView iv_filter_discover = (ImageView) findViewById(R.id.iv_filter_discover);
        iv_filter_discover.setOnClickListener(this);

//        layout_platform = findViewById(R.id.layout_platform);

//        childFragmentManager = getChildFragmentManager();

    }

    public void initData(int[] platform_paras) {
//        int[] platform_paras = ((MainActivity) activity).getPlatform_paras();
        OkGo.get(ApiUtils.getDiscoverPlatforms(activity, "",
                String.valueOf(platform_paras[0])
                , String.valueOf(platform_paras[1]), String.valueOf(platform_paras[2])
                , String.valueOf(platform_paras[3])))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        platform = ParseUtils.parseByGson(s, Platform.class);

                        //保存缓存数据
                        FileUtils.saveData(activity, "DiscoverPlatforms", s);

                        listBean = platform.getPlatform_list();

                        //数据加载完毕
                        if (adapter == null) {
                            adapter = new PlatformSimpleAdapter(listBean);
                            //这里设置点击发现-平台列表跳转到平台详情页面

                            adapter.bindToRecyclerView(mRecyclerView);
                            adapter.setEmptyView(R.layout.view_empty);
                            setEmptyFooter(adapter);
                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Bundle bundle = new Bundle();
                                    List<Platform.PlatformListBean> platformListBeen = adapter.getData();

                                    //平台名字 "抱财网"等    平台类型"固收"等  平台id 数字
                                    String name = platformListBeen.get(position).getName();
//                                    String platform_type = platformListBeen.get(position).getPlatform_type();
                                    int platform_id = platformListBeen.get(position).getPlatform_id();

                                    bundle.putInt(PlatformDetailActivity.PLATFORM_ID, platform_id);
                                    //bundle.putString(PlatformDetailActivity.PLATFORM_TYPE, platform_type);
                                    bundle.putString(PlatformDetailActivity.PLATFORM_NAME, name);
                                    activity.startActivity(PlatformDetailActivity.class, bundle);
                                }
                            });
                        } else {
                            adapter.setNewData(listBean);
                        }
                        if (isRefresh) {
                            mSwipeRefreshLayout.finishRefresh();
                        }
                        if (mAnimDialog != null && mAnimDialog.isShowing()) {
                            mAnimDialog.dismiss();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        NormalUtils.showToast(activity
                                , R.string.net_error);
                        //第一次加载网络错误
                        if (listBean == null) {
                            String discoverPlatforms = FileUtils.getData(activity, "DiscoverPlatforms");
                            platform = ParseUtils.parseByGson(discoverPlatforms, Platform.class);
                            if (platform != null) {
                                listBean = platform.getPlatform_list();
                            }
                            adapter = new PlatformSimpleAdapter(listBean);
                            adapter.bindToRecyclerView(mRecyclerView);
                            adapter.setEmptyView(R.layout.view_load_error);
                            adapter.getEmptyView().setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    isRefresh = false;
                                    initData(activity.clearFilter());
                                }
                            });
                        }
                        if (isRefresh) {
                            mSwipeRefreshLayout.finishRefresh();
                        }
                        if (mAnimDialog != null && mAnimDialog.isShowing()) {
                            mAnimDialog.dismiss();
                        }
                    }

                });
    }

    private void setEmptyFooter(BaseQuickAdapter adapter) {
        View view = new View(activity);
        ViewGroup.LayoutParams para = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 50);
        view.setLayoutParams(para);
        adapter.setFooterView(view);
    }

    @Override
    protected void doBusiness(Context context) {
        isRefresh = false;
        initData(activity.clearFilter());
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_platform;
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.iv_filter_discover:
                activity.openDrawableLayout(MainActivity.TYPE_PLATFORM);
                break;
            //搜索Fragment
            case R.id.et_search:
                if (platform == null) {
                    NormalUtils.showToast(activity, "请在平台数据加载完成后重试");
                } else {
//                    childFragmentManager.beginTransaction().
//                            show(platformSearchFragment).commitAllowingStateLoss();
//                    layout_platform.setVisibility(View.GONE);
//                    platformSearchFragment.showSoftInput();
                    Intent intent = new Intent(activity, PlatformSearchActivity.class);
                    Bundle args = new Bundle();
                    args.putSerializable(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, platform);
                    intent.putExtras(args);
                    startActivity(intent);
                }
                break;
        }
    }

//    public void hideSearchFragment() {
//        childFragmentManager.beginTransaction().
//                hide(platformSearchFragment).commitAllowingStateLoss();
//        layout_platform.setVisibility(View.VISIBLE);
//    }

    @Override
    public void onResume() {
        super.onResume();
//        isRefresh = false;
//        initData(activity.clearFilter());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowMessageEvent(MessageEvent messageEvent) {
        if (messageEvent.getMessage().equals("platform")) {
            isRefresh = true;
            initData(activity.getPlatform_paras());
        }
    }
}
