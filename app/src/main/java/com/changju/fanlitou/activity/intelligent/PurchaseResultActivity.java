package com.changju.fanlitou.activity.intelligent;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.bid.BidFixedActivity;
import com.changju.fanlitou.activity.mine.InvestRecordActivity;
import com.changju.fanlitou.adapter.CanBindTwiceAdapter;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.intelligent.PurchaseResult;
import com.changju.fanlitou.ui.RoundProgressBar;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chengww on 2017/7/11.
 */

public class PurchaseResultActivity extends BaseActivity {

    private PurchaseResult result;

    public static final String FLT_ORDER = "flt_order_no";

    private ImageView ivInvestStatus;
    private TextView tvInvestStatus;
    private TextView tvInvestStatusContent;
    private LinearLayout layoutOrderInfo;
    private TextView tvOrderNumber;
    private ImageView ivPlatformLogo;
    private TextView tvBidName;
    private TextView tvBidAmount;
    private TextView btnDetail;
    private RecyclerView recyclerOtherBids;

    private LinearLayout layout_root;

    //loading&error
    private View include;
    private View include_load_error;


    private SimpleAdapter simpleAdapter;

    private int bid_id;
    private String flt_order_no;

    @Override
    public void initParams(Bundle params) {
        bid_id = params.getInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
        flt_order_no = params.getString(FLT_ORDER);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_purchase_result;
    }

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "智能投顾-投资结果");

        //不侵入状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            NormalUtils.getStatusBarHeight(this));
            findViewById(R.id.tv_status_bar).setLayoutParams(params);
        }

        findViewById(R.id.iv_back_my_account).setOnClickListener(this);
        ivInvestStatus = (ImageView) findViewById(R.id.iv_invest_status);
        tvInvestStatus = (TextView) findViewById(R.id.tv_invest_status);
        tvInvestStatusContent = (TextView) findViewById(R.id.tv_invest_status_content);
        layoutOrderInfo = (LinearLayout) findViewById(R.id.layout_order_info);
        tvOrderNumber = (TextView) findViewById(R.id.tv_order_number);
        ivPlatformLogo = (ImageView) findViewById(R.id.iv_platform_logo);
        tvBidName = (TextView) findViewById(R.id.tv_bid_name);
        tvBidAmount = (TextView) findViewById(R.id.tv_bid_amount);
        btnDetail = (TextView) findViewById(R.id.btn_detail);
        recyclerOtherBids = (RecyclerView) findViewById(R.id.recycler_other_bids);
        layout_root = (LinearLayout) findViewById(R.id.layout_root);

        btnDetail.setOnClickListener(this);

        include = findViewById(R.id.include_loading);
        include.setVisibility(View.VISIBLE);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);

    }


    @Override
    public void doBusiness(final Context mContext) {

        OkGo.get(ApiUtils.getPurchaseResult(mContext,bid_id,flt_order_no))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        result = ParseUtils.parseByGson(s,PurchaseResult.class);
                        bindData();
                        include.setVisibility(View.GONE);
                        include_load_error.setVisibility(View.GONE);

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        NormalUtils.showToast(mContext, R.string.net_error);
                        include.setVisibility(View.GONE);
                        include_load_error.setVisibility(View.VISIBLE);
                    }
                });



    }

    private void bindData() {
        String status = result.getStatus();
        //失败
        if (status.equalsIgnoreCase("FAIL")) {
            layout_root.removeView(layoutOrderInfo);

            ivInvestStatus.setImageResource(R.mipmap.ic_invest_fail);
            tvInvestStatus.setText("购买失败");
            tvInvestStatusContent.setText(result.getMsg());

            recyclerOtherBids.setLayoutManager(new WrapContentLinearLayoutManager(this){
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
            simpleAdapter = new SimpleAdapter(result.getOther_available_bid_list());
            simpleAdapter.bindToRecyclerView(recyclerOtherBids);

            View view = new View(this);
            ViewGroup.LayoutParams paras = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    NormalUtils.dp2px(this,15));
            view.setLayoutParams(paras);
            simpleAdapter.setFooterView(view);

            simpleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    PurchaseResult.OtherAvailableBidListBean bean =
                            (PurchaseResult.OtherAvailableBidListBean) adapter.getData().get(position);
                    Bundle args = new Bundle();
                    args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,bean.getBid_id());
                    args.putString(BidFixedActivity.BID_NAME,
                            bean.getBid_name());
                    PurchaseResultActivity.this.startActivity(BidFixedActivity.class,args);
                }
            });

            layoutOrderInfo.setVisibility(View.GONE);
        }

        //成功&&处理中
        if (status.equalsIgnoreCase("SUCCESS")) {
            layout_root.removeView(recyclerOtherBids);
            ivInvestStatus.setImageResource(R.mipmap.ic_invest_success);
            tvInvestStatus.setText("购买成功");
            tvInvestStatusContent.setText(Html.fromHtml(
                    "预计返利时间为满标后<font color='#f95353'>" +
                            + result.getSettle_date_after_bid_fulled()
                            + "</font>" + "天"));
            //订单部分
            bindOrderData();
        }

        if (status.equalsIgnoreCase("HANDLING")) {
            layout_root.removeView(recyclerOtherBids);
            ivInvestStatus.setImageResource(R.mipmap.ic_invest_handling);
            tvInvestStatus.setText("申请处理中");
            tvInvestStatusContent.setText("请稍后在投资记录中查看购买结果");
            //订单部分
            bindOrderData();
        }
    }

    /**
     * 加载订单部分信息
     */
    private void bindOrderData() {
        tvOrderNumber.setText(result.getOrder_number());
        tvBidName.setText(result.getBid_name());
        tvBidAmount.setText(result.getBid_amount() + "元");

        Glide.with(getApplicationContext()).load(result.getPlatform_info().getPlatform_logo())
                .into(ivPlatformLogo);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_my_account:
                finish();
                break;
            case R.id.btn_detail:
                Bundle args = new Bundle();
                args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,1);
                startActivity(InvestRecordActivity.class,args);
                break;
            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                include_load_error.setVisibility(View.GONE);
                doBusiness(this);
        }
    }

    class SimpleAdapter extends CanBindTwiceAdapter<PurchaseResult.OtherAvailableBidListBean
                , BaseViewHolder> {

        private int mTotalProgress;
        private RoundProgressBar roundProgressBar;

        public SimpleAdapter(List<PurchaseResult.OtherAvailableBidListBean> listBean) {
            super(R.layout.recycler_intelligent_other_bids, listBean);
        }

        @Override
        protected void convert(final BaseViewHolder helper, PurchaseResult.OtherAvailableBidListBean item) {
            helper.setText(R.id.platform_name, item.getPlatform_name());
            helper.setText(R.id.bid_name, item.getBid_name());
            helper.setText(R.id.bid_interest, item.getBid_interest());
            helper.setText(R.id.bonus_interest, "+" + item.getBonus_interest() + "%");
            helper.setText(R.id.duration, item.getDuration() + item.getDuration_unit_str());
            //自定义的progressbar
            mTotalProgress = (int) item.getProgress_percent();
            roundProgressBar = helper.getView(R.id.roundProgressBar);
            roundProgressBar.setAnimProgress(mTotalProgress);


        }
    }
}
