package com.changju.fanlitou.activity.intelligent;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.MainActivity;
import com.changju.fanlitou.activity.mine.PlatformManagerActivity;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.bean.intelligent.WithdrawResult;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chengww on 2017/7/13.
 */

public class WithdrawResultActivity extends BaseActivity {
    private WithdrawResult result;

    public static final String FLT_ORDER = "flt_order_no";

    private ImageView ivInvestStatus;
    private TextView tvInvestStatus;
    private TextView tvInvestStatusContent;
    private TextView tvOrderNumber;
    private ImageView ivPlatformLogo;
    private TextView tvBidName;
    private TextView tvBidAmount;
    private TextView btnDetail;
    private TextView btnWithdrawOneMoreTime;

    //loading&error
    private View include;
    private View include_load_error;

    private int platform_id;
    private String flt_order_no;


    @Override
    public void initParams(Bundle params) {
        platform_id = params.getInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
        flt_order_no = params.getString(FLT_ORDER);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_withdraw_result;
    }

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "智能投顾-提现结果");

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
        tvOrderNumber = (TextView) findViewById(R.id.tv_order_number);
        ivPlatformLogo = (ImageView) findViewById(R.id.iv_platform_logo);
        tvBidName = (TextView) findViewById(R.id.tv_bid_name);
        tvBidAmount = (TextView) findViewById(R.id.tv_bid_amount);
        btnDetail = (TextView) findViewById(R.id.btn_detail);
        btnWithdrawOneMoreTime = (TextView) findViewById(R.id.btn_withdraw_one_more_time);
        btnDetail.setOnClickListener(this);
        btnWithdrawOneMoreTime.setOnClickListener(this);

        include = findViewById(R.id.include_loading);
        include.setVisibility(View.VISIBLE);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);
    }

    @Override
    public void doBusiness(final Context mContext) {
        OkGo.get(ApiUtils.getResultWithdraw(mContext, platform_id, flt_order_no))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        result = ParseUtils.parseByGson(s, WithdrawResult.class);
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
        if (status.equals("FAIL") || status.equals("fail")) {

            ivInvestStatus.setImageResource(R.mipmap.ic_invest_fail);
            tvInvestStatus.setText("很抱歉，您的提现申请失败");
            tvInvestStatusContent.setText(result.getMsg());

            btnDetail.setVisibility(View.GONE);
            bindOrderData();
        }

        //成功&&处理中
        if (status.equals("SUCCESS") || status.equals("success")) {
            ivInvestStatus.setImageResource(R.mipmap.ic_invest_success);
            tvInvestStatus.setText("提现申请完成，等待平台审核");
            tvInvestStatusContent.setText(
                    "预计审核完成时间T+" +
                            result.getWithdraw_success_days() +
                            "日，到账时间以银行为准");
            //订单部分
            bindOrderData();

            //btn-->去投资
            btnDetail.setText("去投资");
            btnDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle args = new Bundle();
                    args.putBoolean(MainActivity.class.getSimpleName(), true);
                    WithdrawResultActivity.this.startActivity(MainActivity.class, args);
                }
            });
        }

        if (status.equals("HANDLING") || status.equals("handling")) {
            ivInvestStatus.setImageResource(R.mipmap.ic_invest_handling);
            tvInvestStatus.setText("提现申请处理中");
            tvInvestStatusContent.setText("请稍后在提现记录中查看提现结果");
            //订单部分
            bindOrderData();
        }
    }

    /**
     * 加载订单部分信息
     */
    private void bindOrderData() {
        tvOrderNumber.setText(result.getOrder_number());
        WithdrawResult.BoundBankInfoBean bank = result.getBound_bank_info();

        //到账银行
        String sb = bank.getBank_name() +
                "(" +
                bank.getCard_no() +
                ")";

        tvBidName.setText(sb);
        Glide.with(getApplicationContext()).load(bank.getBank_logo()).into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                tvBidName.setCompoundDrawables(resource, null, null, null);
            }
        });

        //提现金额
        tvBidAmount.setText(result.getWithdraw_amount() + "元");

        Glide.with(getApplicationContext()).load(result.getPlatform_info().getPlatform_logo())
                .into(ivPlatformLogo);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_my_account:
                onBackPressed();
                break;
            case R.id.btn_detail:
                onBackPressed();
                break;
            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                include_load_error.setVisibility(View.GONE);

                doBusiness(this);
                break;
            case R.id.btn_withdraw_one_more_time:
                finish();
                break;
        }
    }

    /**
     * 去平台管理
     */
    @Override
    public void onBackPressed() {
        Bundle args = new Bundle();
        args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, 1);
        startActivity(PlatformManagerActivity.class, args);
    }
}
