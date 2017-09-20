package com.changju.fanlitou.activity.fund;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.changju.fanlitou.R;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.bean.fund.PurchaseRedeemResult;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chengww on 2017/8/3.
 */

public class PurchaseRedeemResultActivity extends BaseActivity implements View.OnClickListener {

    private String order_no;
    private int fund_id;
    private int type;

    public static final String ORDER_NO = "ORDER_NO";
    public static final String FUND_ID = "FUND_ID";

    public static final int TYPE_BUY = 0;
    public static final int TYPE_SALE = 1;

    public static final String TYPE = "TYPE";

    //默认进入我的基金tab
    private int defaultTab;

    //loading&error
    private View include;
    private View include_load_error;

    private TextView tvFundName;
    private RelativeLayout layoutApplication;
    private ImageView ivApplication;
    private TextView tvApplicationStatus;
    private TextView tvApplicationTime;
    private RelativeLayout layoutPayment;
    private ImageView ivPayment;
    private TextView tvPaymentStatus;
    private TextView tvPaymentTime;
    private RelativeLayout layoutCalculation;
    private ImageView ivCalculation;
    private TextView tvCalculationStatus;
    private TextView tvCalculationTime;
    private RelativeLayout layoutIncome;
    private ImageView ivIncome;
    private TextView tvIncomeStatus;
    private TextView tvIncomeTime;
    private TextView tvOrderNo;
    private TextView btnViewFund;

    @Override
    public void initParams(Bundle params) {
        String fund_id_str = params.getString(FUND_ID);
        if (!TextUtils.isEmpty(fund_id_str) && NormalUtils.isInteger(fund_id_str)){
            fund_id = Integer.valueOf(fund_id_str);
        }else {
            fund_id = params.getInt(FUND_ID);
        }
        order_no = params.getString(ORDER_NO);
        type = params.getInt(TYPE);

        String defaultTab_str = params.getString(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
        if (!TextUtils.isEmpty(defaultTab_str) && NormalUtils.isInteger(defaultTab_str)){
            defaultTab = Integer.valueOf(defaultTab_str);
        }else {
            defaultTab = params.getInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
        }
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_purchase_redeem_result;
    }

    @Override
    public void initView(View view) {
        //不侵入状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            NormalUtils.getStatusBarHeight(this));
            findViewById(R.id.tv_status_bar).setLayoutParams(params);
        }

        GrowingIO.getInstance().setPageName(this, "基金-基金申购/赎回结果");

        findViewById(R.id.iv_back_my_account).setOnClickListener(this);

        include = findViewById(R.id.include_loading);
        include.setVisibility(View.VISIBLE);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);


        tvFundName = (TextView) findViewById(R.id.tv_fund_name);
        layoutApplication = (RelativeLayout) findViewById(R.id.layout_application);
        ivApplication = (ImageView) findViewById(R.id.iv_application);
        tvApplicationStatus = (TextView) findViewById(R.id.tv_application_status);
        tvApplicationTime = (TextView) findViewById(R.id.tv_application_time);
        layoutPayment = (RelativeLayout) findViewById(R.id.layout_payment);
        ivPayment = (ImageView) findViewById(R.id.iv_payment);
        tvPaymentStatus = (TextView) findViewById(R.id.tv_payment_status);
        tvPaymentTime = (TextView) findViewById(R.id.tv_payment_time);
        layoutCalculation = (RelativeLayout) findViewById(R.id.layout_calculation);
        ivCalculation = (ImageView) findViewById(R.id.iv_calculation);
        tvCalculationStatus = (TextView) findViewById(R.id.tv_calculation_status);
        tvCalculationTime = (TextView) findViewById(R.id.tv_calculation_time);
        layoutIncome = (RelativeLayout) findViewById(R.id.layout_income);
        ivIncome = (ImageView) findViewById(R.id.iv_income);
        tvIncomeStatus = (TextView) findViewById(R.id.tv_income_status);
        tvIncomeTime = (TextView) findViewById(R.id.tv_income_time);
        tvOrderNo = (TextView) findViewById(R.id.tv_order_no);
        btnViewFund = (TextView) findViewById(R.id.btn_view_fund);
        btnViewFund.setOnClickListener(this);
    }

    @Override
    public void doBusiness(final Context mContext) {
        OkGo.get(ApiUtils.getPurchaseRedeemResult(mContext, fund_id, order_no))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        PurchaseRedeemResult result = ParseUtils.parseByGson(s, PurchaseRedeemResult.class);

                        //基金名
                        tvFundName.setText(Html.fromHtml(result.getFund_name() +
                                "<font color='#a5a5a5'>(" + result.getFund_code() + ")</font>"));

                        tvOrderNo.setText("订单号：" + result.getOrder_no());

                        int classX = result.getClassX();

                        if (type == TYPE_BUY) {
                            //申购

                            tvApplicationStatus.setText("申请已受理");
                            tvApplicationTime.setText(result.getCreate_time());

                            tvPaymentStatus.setText("确认成功支付" + result.getAmount() + "元");
                            tvPaymentTime.setText(classX > 1 ? result.getSuccess_pay_date() :
                                    "预计" + result.getSuccess_pay_date());

                            tvCalculationStatus.setText("份额确认，开始计算收益");
                            tvCalculationTime.setText(classX > 2 ? result.getConfirm_date() :
                                    "预计" + result.getConfirm_date());

                            tvIncomeStatus.setText("查看收益");
                            tvIncomeTime.setText(classX > 3 ? result.getInterest_start_date() :
                                    "预计" + result.getInterest_start_date());

                            if (!result.isSuccess()) {

                                layoutCalculation.setVisibility(View.GONE);
                                layoutPayment.setVisibility(View.GONE);
                                findViewById(R.id.line_income_1).setBackgroundColor(
                                        getResources().getColor(R.color.colorTextRed));
                                ivIncome.setImageResource(R.mipmap.ic_unpayment_checked);
                                String faild_msg = result.getFaild_msg();
                                tvIncomeStatus.setText(Html.fromHtml(TextUtils.isEmpty(faild_msg) ? "" : faild_msg));
                                tvIncomeTime.setText(result.getSuccess_pay_date());
                            }

                            initPurchaseStatus(classX);
                        }else {
                            //赎回
                            layoutPayment.setVisibility(View.GONE);

                            tvApplicationStatus.setText("申请已受理");
                            tvApplicationTime.setText(result.getCreate_time());

                            tvCalculationStatus.setText("赎回金额确认");
                            tvCalculationTime.setText(classX > 1 ? result.getConfirm_date() :
                                    "预计" + result.getConfirm_date());

                            tvIncomeStatus.setText("赎回金额到账（" + result.getBank_name() + ")");
                            tvIncomeTime.setText(classX > 2 ? result.getArrive_date() :
                                    "预计" + result.getArrive_date());

                            if (!result.isSuccess()) {
                                layoutCalculation.setVisibility(View.GONE);
                                findViewById(R.id.line_income_1).setBackgroundColor(
                                        getResources().getColor(R.color.colorTextRed));
                                ivIncome.setImageResource(R.mipmap.ic_unpayment_checked);
                                tvIncomeStatus.setText(Html.fromHtml(result.getFaild_msg()));
                                tvIncomeTime.setText(result.getConfirm_date());
                            }

                            initRedeemStatus(classX);
                        }

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

    private void initPurchaseStatus(int classX) {


        if (classX < 2) {
            defaultTab = 1;
            return;
        }


        $(R.id.line_payment_1).setBackgroundColor(getResources().getColor(R.color.colorTextRed));
        $(R.id.line_payment).setBackgroundColor(getResources().getColor(R.color.colorTextRed));
        ivPayment.setImageResource(R.mipmap.ic_payment_checked);

        if (classX < 3) {
            defaultTab = 1;
            return;
        }

        $(R.id.line_calculation_1).setBackgroundColor(getResources().getColor(R.color.colorTextRed));
        $(R.id.line_calculation).setBackgroundColor(getResources().getColor(R.color.colorTextRed));
        ivCalculation.setImageResource(R.mipmap.ic_calculation_checked);

        if (classX < 4) {
            defaultTab = 0;
            return;
        }

        $(R.id.line_income_1).setBackgroundColor(getResources().getColor(R.color.colorTextRed));
        ivIncome.setImageResource(R.mipmap.ic_income_checked);

        defaultTab = 0;
    }


    private void initRedeemStatus(int classX) {


        if (classX < 2) {
            defaultTab = 1;
            return;
        }


        $(R.id.line_calculation_1).setBackgroundColor(getResources().getColor(R.color.colorTextRed));
        $(R.id.line_calculation).setBackgroundColor(getResources().getColor(R.color.colorTextRed));
        ivCalculation.setImageResource(R.mipmap.ic_calculation_checked);

        if (classX < 3) {
            defaultTab = 1;
            return;
        }


        $(R.id.line_income_1).setBackgroundColor(getResources().getColor(R.color.colorTextRed));
        ivIncome.setImageResource(R.mipmap.ic_income_checked);

        defaultTab = 0;




    }
    @Override
    public void widgetClick(View v) {
        Bundle args = new Bundle();
        switch (v.getId()) {
            case R.id.iv_back_my_account:
                finish();
                break;
            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                include_load_error.setVisibility(View.GONE);
                doBusiness(this);
                break;
            case R.id.btn_view_fund:
                args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, defaultTab);
                startActivity(MyFundActivity.class,args);
                break;
        }
    }
}
