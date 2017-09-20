package com.changju.fanlitou.activity.fund;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.adapter.CanBindTwiceAdapter;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.fund.FundHisDiv;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zhangming on 2017/7/18.
 */

public class FundHisDividendsActivity extends BaseActivity {

    @Bind(R.id.rv_dividends)
    RecyclerView rv_dividends;

    private int fund_id;

    private SimpleAdapter1 simpleAdapter1;
    private FundHisDiv fundHisDiv;
    private List<FundHisDiv.DivInfoBean> divInfoBeanList;

    //loading&error
    private View include;
    private View include_load_error;
    private int page = 1;

    @Override
    public void initParams(Bundle params) {
        fund_id = params.getInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_fund_history_dividends;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this);

        GrowingIO.getInstance().setPageName(this, "基金-历史分红");

        findViewById(R.id.iv_back_dividents).setOnClickListener(this);
        rv_dividends.setLayoutManager(new WrapContentLinearLayoutManager(FundHisDividendsActivity.this));
        rv_dividends.setOverScrollMode(View.OVER_SCROLL_NEVER);
        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);
    }

    @Override
    public void doBusiness(Context mContext) {

        OkGo.get(ApiUtils.getFundingHistoryDiv(mContext,fund_id,page,20)).execute(new StringCallback() {

            @Override
            public void onSuccess(String s, Call call, Response response) {
                fundHisDiv = ParseUtils.parseByGson(s, FundHisDiv.class);
                divInfoBeanList = fundHisDiv.getDiv_info();
                if(simpleAdapter1 == null){
                    simpleAdapter1 = new SimpleAdapter1(divInfoBeanList);
                    simpleAdapter1.bindToRecyclerView(rv_dividends);
                    simpleAdapter1.setEmptyView(R.layout.view_empty);
                    pullToRefresh(simpleAdapter1);
                    simpleAdapter1.disableLoadMoreIfNotFullPage();
                }else{
                    simpleAdapter1.setNewData(divInfoBeanList);
                }
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.GONE);
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                NormalUtils.showToast(FundHisDividendsActivity.this, R.string.net_error);
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * 上拉加载
     *
     * @param adapter 进行上拉加载的adapter
     */
    private void pullToRefresh(final CanBindTwiceAdapter adapter) {
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                final Activity activity = FundHisDividendsActivity.this;
                OkGo.get(ApiUtils.getFundingHistoryDiv(activity,fund_id, page + 1, 20)).execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        page++;
                        fundHisDiv = ParseUtils.parseByGson(s, FundHisDiv.class);
                        divInfoBeanList = fundHisDiv.getDiv_info();
                        if (divInfoBeanList == null || divInfoBeanList.size() < 1) {
                            adapter.loadMoreEnd();
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(500);
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                adapter.setEnableLoadMore(false);
                                            }
                                        });
                                        RecyclerView.LayoutManager manager = rv_dividends.getLayoutManager();
                                        if (manager == null) return;
                                        if (manager instanceof LinearLayoutManager) {
                                            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) manager;
                                            rv_dividends.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    if ((linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1)
                                                            != adapter.getItemCount()) {
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
                        } else {
                            adapter.addData(divInfoBeanList);
                            adapter.loadMoreComplete();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        NormalUtils.showToast(activity, R.string.net_error);
                        adapter.loadMoreFail();
                    }
                });
            }
        }, rv_dividends);
    }


    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_dividents:
                finish();
                break;
            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                include_load_error.setVisibility(View.GONE);
                doBusiness(this);
                break;
        }
    }

    class SimpleAdapter1 extends CanBindTwiceAdapter<FundHisDiv.DivInfoBean, BaseViewHolder> {

        public SimpleAdapter1(@Nullable List<FundHisDiv.DivInfoBean> data) {
            super(R.layout.recycle_item_history_dividends, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, FundHisDiv.DivInfoBean item) {
            helper.setText(R.id.tv_1, item.getRecord_date())
                    .setText(R.id.tv_2, item.getPay_date())
                    .setText(R.id.tv_3, item.getDiv_remv_tax());
        }
    }
}
