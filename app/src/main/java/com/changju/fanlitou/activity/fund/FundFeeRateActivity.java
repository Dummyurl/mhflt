package com.changju.fanlitou.activity.fund;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.fund.FundFeeRate;
import com.changju.fanlitou.ui.ObservableScrollView;
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

public class FundFeeRateActivity extends BaseActivity {

    @Bind(R.id.apply_rv)
    RecyclerView apply_rv;
    @Bind(R.id.subscribe_rv)
    RecyclerView subscribe_rv;
    @Bind(R.id.redem_rv)
    RecyclerView redem_rv;
    @Bind(R.id.other_rv)
    RecyclerView other_rv;
    @Bind(R.id.scrollview)
    ObservableScrollView scrollview;
    @Bind(R.id.ll_scroll_in_fee)
    LinearLayout ll_scroll_in_fee;
    @Bind(R.id.iv_backtop)
    ImageView iv_backtop;

    private int heightOfScrollView;

    @Bind(R.id.ll_1)
    LinearLayout ll_1;
    @Bind(R.id.ll_2)
    LinearLayout ll_2;
    @Bind(R.id.ll_3)
    LinearLayout ll_3;
    @Bind(R.id.ll_4)
    LinearLayout ll_4;

    private ApplySimpleAdapter applySimpleAdapter;
    private SubscribeSimpleAdapter subscribeSimpleAdapter;
    private RedeemSimpleAdapter redeemSimpleAdapter;
    private OtherSimpleAdapter otherSimpleAdapter;

    private int fund_id;
    private FundFeeRate fundFeeRate;

    //loading&error
    private View include;
    private View include_load_error;

    @Override
    public void initParams(Bundle params) {
        fund_id = params.getInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_fund_fee_rate;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this);

        GrowingIO.getInstance().setPageName(this, "基金费率");

        findViewById(R.id.iv_back_feerate).setOnClickListener(this);
        findViewById(R.id.iv_backtop).setOnClickListener(this);
        scrollview.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                //Log.e("zm",y+"    "+oldy);
                if(oldy == 0 || y == 0){
                    iv_backtop.setVisibility(View.GONE);
                }else{
                    iv_backtop.setVisibility(View.VISIBLE);
                }
            }
        });

        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);
    }

    @Override
    public void doBusiness(Context mContext) {

        OkGo.get(ApiUtils.getFundingFeeRate(mContext,fund_id)).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                fundFeeRate = ParseUtils.parseByGson(s,FundFeeRate.class);

                apply_rv.setLayoutManager(new WrapContentLinearLayoutManager(FundFeeRateActivity.this));
                applySimpleAdapter = new ApplySimpleAdapter(fundFeeRate.getApply_data());
                applySimpleAdapter.bindToRecyclerView(apply_rv);
                applySimpleAdapter.setEmptyView(R.layout.view_empty);
                if(fundFeeRate.getApply_data() == null || fundFeeRate.getApply_data().size() == 0){
                    ll_1.setVisibility(View.GONE);
                }

                subscribe_rv.setLayoutManager(new WrapContentLinearLayoutManager(FundFeeRateActivity.this));
                subscribeSimpleAdapter = new SubscribeSimpleAdapter(fundFeeRate.getSubscribe_data().getData_list());
                subscribeSimpleAdapter.bindToRecyclerView(subscribe_rv);
                subscribeSimpleAdapter.setEmptyView(R.layout.view_empty);
                if(fundFeeRate.getSubscribe_data().getData_list() == null || fundFeeRate.getSubscribe_data().getData_list().size() == 0){
                    ll_2.setVisibility(View.GONE);
                }

                redem_rv.setLayoutManager(new WrapContentLinearLayoutManager(FundFeeRateActivity.this));
                redeemSimpleAdapter = new RedeemSimpleAdapter(fundFeeRate.getRedeem_data().getData_list());
                redeemSimpleAdapter.bindToRecyclerView(redem_rv);
                redeemSimpleAdapter.setEmptyView(R.layout.view_empty);
                if(fundFeeRate.getRedeem_data().getData_list() == null || fundFeeRate.getRedeem_data().getData_list().size() == 0){
                    ll_3.setVisibility(View.GONE);
                }

                other_rv.setLayoutManager(new WrapContentLinearLayoutManager(FundFeeRateActivity.this));
                otherSimpleAdapter = new OtherSimpleAdapter(fundFeeRate.getOther_data().getData_list());
                otherSimpleAdapter.bindToRecyclerView(other_rv);
                otherSimpleAdapter.setEmptyView(R.layout.view_empty);
                if(fundFeeRate.getOther_data().getData_list() == null || fundFeeRate.getOther_data().getData_list().size() == 0){
                    ll_4.setVisibility(View.GONE);
                }

//                applySimpleAdapter.bindToRecyclerView(apply_rv);
//                subscribeSimpleAdapter.bindToRecyclerView(subscribe_rv);
//                redeemSimpleAdapter.bindToRecyclerView(redem_rv);
//                otherSimpleAdapter.bindToRecyclerView(other_rv);

                //解决srollview和recycleview滑动冲突
                apply_rv.setNestedScrollingEnabled(false);
                subscribe_rv.setNestedScrollingEnabled(false);
                redem_rv.setNestedScrollingEnabled(false);
                other_rv.setNestedScrollingEnabled(false);

                ViewTreeObserver viewTreeObserver = ll_scroll_in_fee.getViewTreeObserver();
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        ll_scroll_in_fee.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        heightOfScrollView = ll_scroll_in_fee.getHeight();
                        //整个屏幕高度
                        int screenHeight = NormalUtils.getScreenHeight(FundFeeRateActivity.this);
                        //标题栏高度是46dp
                        int heightOfTitleLayout = NormalUtils.dp2px(FundFeeRateActivity.this,46);
                        //状态栏高度
                        int statusHeight = NormalUtils.getStatusBarHeight(FundFeeRateActivity.this);

                        if( screenHeight > (heightOfTitleLayout + heightOfScrollView + statusHeight) ){
                            iv_backtop.setVisibility(View.GONE);
                        }else{
                            iv_backtop.setVisibility(View.GONE);
                        }
                    }
                });

                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.GONE);
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                NormalUtils.showToast(FundFeeRateActivity.this, R.string.net_error);
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_feerate:
                finish();
                break;
            case R.id.iv_backtop:
                //返回页面顶部
                scrollview.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollview.fullScroll(ScrollView.FOCUS_UP);
                    }
                });
                break;
            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                include_load_error.setVisibility(View.GONE);
                doBusiness(this);
                break;
        }
    }

    class ApplySimpleAdapter extends BaseQuickAdapter<FundFeeRate.ApplyDataBean, BaseViewHolder> {

        public ApplySimpleAdapter(@Nullable List<FundFeeRate.ApplyDataBean> data) {
            super(R.layout.recycle_item_feerate_three, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, FundFeeRate.ApplyDataBean item) {
            helper.setText(R.id.tv_1, item.getApply_min() + "-" + item.getApply_max())
                    .setText(R.id.tv_2, item.getOriginal_rate())
                    .setText(R.id.tv_3, item.getFanlitou_rate());
        }
    }

    class SubscribeSimpleAdapter extends BaseQuickAdapter<FundFeeRate.SubscribeDataBean.DataListBeanXX, BaseViewHolder> {

        public SubscribeSimpleAdapter(@Nullable List<FundFeeRate.SubscribeDataBean.DataListBeanXX> data) {
            super(R.layout.recycle_item_feerate_three, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, FundFeeRate.SubscribeDataBean.DataListBeanXX item) {
            helper.setText(R.id.tv_1, item.getPert_val_min() + "≤金额＜" + item.getPert_val_max())
                    .setText(R.id.tv_2, item.getOriginal_rate())
                    .setText(R.id.tv_3, item.getFanlitou_rate());
        }
    }

    class RedeemSimpleAdapter extends BaseQuickAdapter<FundFeeRate.RedeemDataBean.DataListBeanX, BaseViewHolder> {

        public RedeemSimpleAdapter(@Nullable List<FundFeeRate.RedeemDataBean.DataListBeanX> data) {
            super(R.layout.recycle_item_feerate_two, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, FundFeeRate.RedeemDataBean.DataListBeanX item) {
            helper.setText(R.id.tv_1, item.getHold_duration())
                    .setText(R.id.tv_2, item.getRedeem_rate());
        }
    }

    class OtherSimpleAdapter extends BaseQuickAdapter<FundFeeRate.OtherDataBean.DataListBean, BaseViewHolder> {

        public OtherSimpleAdapter(@Nullable List<FundFeeRate.OtherDataBean.DataListBean> data) {
            super(R.layout.recycle_item_feerate_two, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, FundFeeRate.OtherDataBean.DataListBean item) {
            helper.setText(R.id.tv_1, item.getName())
                    .setText(R.id.tv_2, item.getRedeem_rate());
        }
    }
}
