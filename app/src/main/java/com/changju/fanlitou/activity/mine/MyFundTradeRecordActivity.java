package com.changju.fanlitou.activity.mine;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.fund.PurchaseRedeemResultActivity;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.fund.FundTradeRecord;
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

public class MyFundTradeRecordActivity extends BaseActivity {

    private View include;
    private View include_load_error;
    private SimpleAdapter mAdapter1;
    private FundTradeRecord fundTradeRecord;
    private SmartRefreshLayout refreshLayout;

    private boolean isRefresh;

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_my_fund_trade_record;
    }


    @Override
    public void initView(View view) {

        UserUtils.checkLogin(this, MyFundTradeRecordActivity.class);

        GrowingIO.getInstance().setPageName(this, "我的-我的基金-交易记录");

        ImageView ivBack = (ImageView) findViewById(R.id.activity_my_fund_trade_record_iv_back);
        RecyclerView rcv = (RecyclerView) findViewById(R.id.activity_my_fund_trade_record_rcv);
        ivBack.setOnClickListener(this);
        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);

        rcv.setLayoutManager(new WrapContentLinearLayoutManager(this));

        mAdapter1 = new SimpleAdapter(null);
        mAdapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                FundTradeRecord.ResultBean fundTradeResult = mAdapter1.getData().get(position);
                int fund_id = fundTradeResult.getId();
                Bundle args = new Bundle();
                args.putInt(PurchaseRedeemResultActivity.FUND_ID, fund_id);
                args.putString(PurchaseRedeemResultActivity.ORDER_NO, fundTradeResult.getFlt_order_no());

                if (fundTradeResult.isIs_redem()) {
                    //赎回
                    args.putInt(PurchaseRedeemResultActivity.TYPE, PurchaseRedeemResultActivity.TYPE_SALE);
                } else {
                    //申购
                    args.putInt(PurchaseRedeemResultActivity.TYPE, PurchaseRedeemResultActivity.TYPE_BUY);
                }

                startActivity(PurchaseRedeemResultActivity.class, args);
            }
        });
        mAdapter1.bindToRecyclerView(rcv);
        mAdapter1.setEmptyView(R.layout.view_empty);

        pullToRefresh(mAdapter1, rcv);

        refreshLayout = (SmartRefreshLayout) findViewById(R.id.trade_record_refresh_layout);
        refreshLayout.setRefreshHeader(new MaterialHeader(this).setColorSchemeColors(0xfff95353));
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
                        getTradeList(MyFundTradeRecordActivity.this);
                    }
                }, 1000);
            }
        });

    }

    class SimpleAdapter extends BaseQuickAdapter<FundTradeRecord.ResultBean, BaseViewHolder> {
        public SimpleAdapter(@Nullable List<FundTradeRecord.ResultBean> data) {
            super(R.layout.recycler_item_my_fund_trade_record, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, FundTradeRecord.ResultBean item) {
            helper.setText(R.id.my_fund_trade_record_fund_name, item.getName())
                    .setText(R.id.my_fund_trade_record_money, item.isIs_redem() ? item.getInvest_share() + "份" : item.getInvest_amount() + "元")
                    .setText(R.id.my_fund_trade_record_time, item.getOrder_date())
                    .setText(R.id.my_fund_trade_record_state, item.getStatus_str());

            if (item.getStatus() == 1 || item.getStatus() == 2) {
                //状态为1 2 为"买入中"赎回中" 为蓝色
                helper.setTextColor(R.id.my_fund_trade_record_state, Color.parseColor("#538eeb"));
            } else if (item.getStatus() == 3 || item.getStatus() == 4) {
                //状态为3 4 为"已持有"已赎回" 为红色
                helper.setTextColor(R.id.my_fund_trade_record_state, Color.parseColor("#f94529"));
            } else if (item.getStatus() == 5 || item.getStatus() == 6) {
                //状态为5 6 为"买入失败"赎回失败" 为绿色
                helper.setTextColor(R.id.my_fund_trade_record_state, Color.parseColor("#6db247"));
            }

//            if ((item.getStatus_str() + "").contains("成功")) {
//                helper.setTextColor(R.id.my_fund_trade_record_state, Color.parseColor("#f94529"));
//            } else if((item.getStatus_str() + "").contains("中")){
//                helper.setTextColor(R.id.my_fund_trade_record_state, Color.parseColor("#538eeb"));
//            } else {
//                helper.setTextColor(R.id.my_fund_trade_record_state, Color.parseColor("#6eb53f"));
//            }
        }
    }


    int page = 1;

    private void pullToRefresh(final BaseQuickAdapter adapter, final RecyclerView mRecyclerView) {
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                OkGo.get(ApiUtils.getFundTradeRecord(
                        MyFundTradeRecordActivity.this, page + 1, 20)).execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        page++;
                        FundTradeRecord bean = ParseUtils.parseByGson(s, FundTradeRecord.class);
                        List<FundTradeRecord.ResultBean> list = bean.getResult();
                        if (list == null || list.size() < 1) {
                            loadMoreEnd(adapter, mRecyclerView);
                        } else {
                            adapter.addData(list);
                            adapter.loadMoreComplete();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        NormalUtils.showToast(MyFundTradeRecordActivity.this, R.string.net_error);
                        adapter.loadMoreFail();
                    }
                });
            }
        }, mRecyclerView);

    }

    @Override
    public void doBusiness(Context mContext) {
        isRefresh = false;
        getTradeList(mContext);
    }

    private void getTradeList(Context mContext) {
        OkGo.get(ApiUtils.getFundTradeRecord(mContext, page, 20)).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                fundTradeRecord = ParseUtils.parseByGson(s, FundTradeRecord.class);
                mAdapter1.setNewData(fundTradeRecord.getResult());
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.GONE);

                if (isRefresh) {
                    refreshLayout.finishRefresh();
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                NormalUtils.showToast(MyFundTradeRecordActivity.this, R.string.net_error);
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.VISIBLE);

                if (isRefresh) {
                    refreshLayout.finishRefresh();
                }
            }
        });
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.activity_my_fund_trade_record_iv_back:
                finish();
                break;
            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                include_load_error.setVisibility(View.GONE);
                doBusiness(this);
                break;
        }
    }

    private void loadMoreEnd(final BaseQuickAdapter adapter, final RecyclerView mRecyclerView) {
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
