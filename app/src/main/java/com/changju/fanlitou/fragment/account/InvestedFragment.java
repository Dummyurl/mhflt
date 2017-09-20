package com.changju.fanlitou.fragment.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.LoginActivity;
import com.changju.fanlitou.activity.MainActivity;
import com.changju.fanlitou.adapter.RePayingAdapter;
import com.changju.fanlitou.adapter.RePayingCompleteAdapter;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.account.RePaying;
import com.changju.fanlitou.bean.account.RePayingComplete;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.changju.fanlitou.util.UserUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chengww on 2017/5/8.
 */

public class InvestedFragment extends LazyFragment {

    private SmartRefreshLayout refreshLayout;
    private RecyclerView recycler_invested;

    private BaseQuickAdapter thisAdapter;

    private boolean isLogin = false;

    private boolean isRefresh;

    public int getPage() {
        return page;
    }

    private MainActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    public static InvestedFragment newInstance() {

        Bundle args = new Bundle();
        args.putBoolean(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, false);
        InvestedFragment fragment = new InvestedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //页码
    private int page = 1;

    private TextView tv_invested_status;

    @Override
    protected void initView() {

        GrowingIO.getInstance().setPageName(this, "账本-已投项目");

        isLogin = UserUtils.isLogin(activity);
        recycler_invested = (RecyclerView) findViewById(R.id.recycler_invested);
        recycler_invested.setLayoutManager(new WrapContentLinearLayoutManager(activity));
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.invested_refresh_layout);
        refreshLayout.setRefreshHeader(new MaterialHeader(activity).setColorSchemeColors(0xfff95353));
        refreshLayout.setEnableHeaderTranslationContent(false);
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                refreshlayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isRefresh = true;
                        page = 1;
                        initData(page);
                    }
                }, 1000);
            }
        });

        tv_invested_status = (TextView) findViewById(R.id.tv_invested_status);
        tv_invested_status.setOnClickListener(this);
    }

    @Override
    protected void doBusiness(Context context) {
    }

    private boolean isRepayingComplete = false;

    private RePayingAdapter mRePayingAdapter;
    private List<RePaying.RecordsBean> records;
    private RePayingCompleteAdapter mRePayingCompleteAdapter;
    private List<RePayingComplete.RecordsBean> recordsComplete;

    public void setPage(int page) {
        this.page = page;
    }

    private void initData(int page) {
        if (isRepayingComplete) {
            OkGo.get(ApiUtils.getRepayingComplete(activity, page))
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            RePayingComplete bean = ParseUtils.parseByGson(s, RePayingComplete.class);
                            recordsComplete = bean.getRecords();
                            if (mRePayingCompleteAdapter == null) {
                                mRePayingCompleteAdapter = new RePayingCompleteAdapter(recordsComplete);
                                mRePayingCompleteAdapter.bindToRecyclerView(recycler_invested);
                                thisAdapter = mRePayingAdapter;
                                pullToRefresh(mRePayingCompleteAdapter);

                                //点击事件
//                                mRePayingCompleteAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//                                    @Override
//                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                                        RePayingComplete.RecordsBean recordsBean =
//                                                (RePayingComplete.RecordsBean) adapter.getData().get(position);
//                                        startToRepayDetail(recordsBean.getType(),
//                                                recordsBean.getBid_id());
//                                    }
//                                });

                            } else {
                                mRePayingCompleteAdapter.setNewData(recordsComplete);
                                mRePayingCompleteAdapter.bindToRecyclerView(recycler_invested);
                            }

                            mRePayingCompleteAdapter.setEmptyView(R.layout.view_empty);
                            TextView tv = (TextView) mRePayingCompleteAdapter.
                                    getEmptyView().findViewById(R.id.tv_content);
                            tv.setText("暂未投资，投资赚返利吧");
                            startToInvest(tv);
                            if (isRefresh) {
                                refreshLayout.finishRefresh();
                            }

                            refreshLayout.setEnabled(isLogin);
                            tv_invested_status.setVisibility(isLogin ? View.VISIBLE : View.GONE);

                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);
                            NormalUtils.showToast(activity, R.string.net_error);
                            //第一次加载网络错误
                            if (recordsComplete == null) {
                                mRePayingCompleteAdapter = new RePayingCompleteAdapter(null);
                                mRePayingCompleteAdapter.bindToRecyclerView(recycler_invested);
//                            recycler_invested.setAdapter(mRePayingCompleteAdapter);
                                mRePayingCompleteAdapter.setEmptyView(R.layout.view_load_error);
                                pullToRefresh(mRePayingCompleteAdapter);
                            }
                            if (isRefresh) {
                                refreshLayout.finishRefresh();
                            }
                            tv_invested_status.setVisibility(isLogin ? View.VISIBLE : View.GONE);

                        }
                    });
        } else {
            OkGo.get(ApiUtils.getRepaying(activity, page))
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            RePaying bean = ParseUtils.parseByGson(s, RePaying.class);
                            records = bean.getRecords();
                            if (mRePayingAdapter == null) {
                                mRePayingAdapter = new RePayingAdapter(records);
                                mRePayingAdapter.bindToRecyclerView(recycler_invested);
                                pullToRefresh(mRePayingAdapter);

                                //点击事件,已在适配器中设置
//                                mRePayingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//                                    @Override
//                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                                        RePaying.RecordsBean recordsBean = (RePaying.RecordsBean) adapter.getItem(position);
//                                        startToRepayDetail(recordsBean.getType(), recordsBean.getBid_id());
//                                    }
//                                });
                                mRePayingAdapter.setEmptyView(R.layout.view_empty);
                                TextView tv = (TextView) mRePayingAdapter.
                                        getEmptyView().findViewById(R.id.tv_content);
                                tv.setText("暂未投资，投资赚返利吧");
                                startToInvest(tv);
                            } else {
                                mRePayingAdapter.setNewData(records);
                                mRePayingAdapter.bindToRecyclerView(recycler_invested);
                            }

                            if (isRefresh) {
                                refreshLayout.finishRefresh();
                            }

                            refreshLayout.setEnabled(isLogin);
                            tv_invested_status.setVisibility(isLogin ? View.VISIBLE : View.GONE);
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);
                            NormalUtils.showToast(activity, R.string.net_error);
                            //第一次加载网络错误
                            if (records == null) {
                                mRePayingAdapter = new RePayingAdapter(null);
                                mRePayingAdapter.bindToRecyclerView(recycler_invested);
                                pullToRefresh(mRePayingAdapter);
                                mRePayingAdapter.setEmptyView(R.layout.view_load_error);
                            }
                            if (isRefresh) {
                                refreshLayout.finishRefresh();
                            }
                            tv_invested_status.setVisibility(isLogin ? View.VISIBLE : View.GONE);

                        }
                    });
        }


    }

    private void pullToRefresh(final BaseQuickAdapter adapter) {
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (adapter instanceof RePayingCompleteAdapter) {
                    OkGo.get(ApiUtils.getRepayingComplete(activity
                            , getPage() + 1))
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    RePayingComplete bean = ParseUtils.parseByGson(s, RePayingComplete.class);
                                    List<RePayingComplete.RecordsBean> recordsComplete = bean.getRecords();
                                    if (recordsComplete == null || recordsComplete.size() < 1) {
                                        loadMoreEnd(mRePayingCompleteAdapter, recycler_invested);
                                    } else {
                                        mRePayingCompleteAdapter.addData(recordsComplete);
                                        mRePayingCompleteAdapter.loadMoreComplete();
                                        setPage(getPage() + 1);
                                    }
                                }

                                @Override
                                public void onError(Call call, Response response, Exception e) {
                                    super.onError(call, response, e);
                                    NormalUtils.showToast(activity, R.string.net_error);
                                    mRePayingCompleteAdapter.loadMoreFail();
                                }
                            });
                } else if (adapter instanceof RePayingAdapter) {
                    OkGo.get(ApiUtils.getRepaying(activity, getPage() + 1))
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    RePaying bean = ParseUtils.parseByGson(s, RePaying.class);
                                    List<RePaying.RecordsBean> records = bean.getRecords();
                                    if (records == null || records.size() < 1) {
                                        loadMoreEnd(mRePayingAdapter, recycler_invested);
                                    } else {
                                        mRePayingAdapter.addData(records);
                                        mRePayingAdapter.loadMoreComplete();
                                        setPage(getPage() + 1);
                                    }

                                }

                                @Override
                                public void onError(Call call, Response response, Exception e) {
                                    super.onError(call, response, e);
                                    NormalUtils.showToast(activity, R.string.net_error);
                                    mRePayingAdapter.loadMoreFail();
                                }
                            });
                } else {
                    adapter.loadMoreFail();
                }
            }
        }, recycler_invested);
    }

    public void startToInvest(View tv) {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,
                        UserUtils.isLogin(activity) ?
                                MainActivity.class : LoginActivity.class);
                Bundle args = new Bundle();
                args.putBoolean(MainActivity.class.getSimpleName(), true);
                intent.putExtras(args);
                startActivity(intent);
            }
        });
    }


    @Override
    public int bindLayout() {
        return R.layout.fragment_invested;
    }


    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.tv_invested_status:
                if (tv_invested_status.getText().toString().equals("回款中")) {
                    tv_invested_status.setText("已完成");
                    isRepayingComplete = false;
                } else {
                    tv_invested_status.setText("回款中");
                    isRepayingComplete = true;
                }
                page = 1;
                isRefresh = false;
                initData(page);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isLogin != UserUtils.isLogin(activity)) {
            page = 1;
        }

        isLogin = UserUtils.isLogin(activity);
        isRefresh = false;
        initData(page);
    }

    private void loadMoreEnd(final BaseQuickAdapter adapter, final RecyclerView mRecyclerView) {
        adapter.loadMoreEnd();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.setEnableLoadMore(false);
                        }
                    });
                    RecyclerView.LayoutManager manager = mRecyclerView.getLayoutManager();
                    if (manager == null) return;
                    if (manager instanceof LinearLayoutManager) {
                        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) manager;
                        mRecyclerView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if ((linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1) != adapter.getItemCount()) {
                                    adapter.setEnableLoadMore(true);
                                }
                            }
                        }, 50);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
