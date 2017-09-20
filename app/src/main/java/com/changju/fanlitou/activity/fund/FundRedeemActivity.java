package com.changju.fanlitou.activity.fund;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.changju.fanlitou.R;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.MyApplication;
import com.changju.fanlitou.bean.fund.FundRedeemBean;
import com.changju.fanlitou.bean.fund.RedeemFeeBean;
import com.changju.fanlitou.ui.EditTextWithDel;
import com.changju.fanlitou.ui.dialog.AnimDialog;
import com.changju.fanlitou.ui.dialog.RedeemFeeDialog;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.changju.fanlitou.util.UserUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class FundRedeemActivity extends BaseActivity {


    private int fund_id = 0;
    private ImageView redeemIvBack;
    private TextView redeemTitleTv;
    private ImageView redeemBankLogo;
    private TextView redeemBankName;
    private TextView redeemBankDescription;
    private TextView redeemMoneyTv;
    private EditTextWithDel redeemMoneyEt;
    private TextView redeemRateTv;
    private TextView redeemConfirmDescription;
    private TextView btnOk;
    private TextView feilvTv;
    private CheckBox check_box;
    private View load_error_layout;
    private FundRedeemBean fundRedeemBean;
    private TextView fund_redeem_share_tv;
    private TextView redeemHintTv;
    private View include;
    private TextView all_redeem_bt, feilv_detail_bt;
    private String max_redem_share;
    private String min_redem_share;


    @Override
    public void initParams(Bundle params) {
        fund_id = params.getInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_fund_redeem;
    }


    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "基金-基金赎回");

        redeemIvBack = (ImageView) findViewById(R.id.activity_fund_redeem_iv_back);
        redeemIvBack.setOnClickListener(this);
        redeemTitleTv = (TextView) findViewById(R.id.fund_redeem_title_tv);
        redeemBankLogo = (ImageView) findViewById(R.id.activity_fund_redeem_bank_logo);
        redeemBankName = (TextView) findViewById(R.id.activity_fund_redeem_bank_name);
        fund_redeem_share_tv = (TextView) findViewById(R.id.fund_redeem_share_tv);
        redeemBankDescription = (TextView) findViewById(R.id.activity_fund_redeem_bank_description);
        redeemMoneyTv = (TextView) findViewById(R.id.activity_fund_redeem_money_tv);
        redeemMoneyEt = (EditTextWithDel) findViewById(R.id.activity_fund_redeem_money_et);
        feilvTv = (TextView) findViewById(R.id.feilv);
        redeemRateTv = (TextView) findViewById(R.id.activity_fund_redeem_rate);
        redeemHintTv = (TextView) findViewById(R.id.activity_fund_redeem_hint_tv);
        redeemHintTv.setText("预计到账      0元");
        all_redeem_bt = (TextView) findViewById(R.id.activity_fund_redeem_all_redeem_bt);
        all_redeem_bt.setOnClickListener(this);
        feilv_detail_bt = (TextView) findViewById(R.id.activity_fund_redeem_feilv_detail_bt);
        feilv_detail_bt.setOnClickListener(this);
        redeemConfirmDescription = (TextView) findViewById(R.id.activity_fund_redeem_confirm_description);
        btnOk = (TextView) findViewById(R.id.btn_ok);
        check_box = (CheckBox) findViewById(R.id.check_box);
        include = findViewById(R.id.include_loading);
        load_error_layout = findViewById(R.id.load_error_layout);
        load_error_layout.setOnClickListener(this);
    }

    @Override
    public void doBusiness(final Context mContext) {
        OkGo.get(ApiUtils.getConfirmRedeem(mContext, fund_id)).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                load_error_layout.setVisibility(View.GONE);
                include.setVisibility(View.GONE);
                fundRedeemBean = ParseUtils.parseByGson(s, FundRedeemBean.class);
                setData();
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                load_error_layout.setVisibility(View.VISIBLE);
                include.setVisibility(View.GONE);
                NormalUtils.showToast(mContext, R.string.net_error);
            }
        });
    }

    /**
     * 设置界面数据
     */
    private void setData() {
        String fund_name = fundRedeemBean.getFund_name();
        redeemTitleTv.setText(fund_name == null ? "----" : (fund_name + fundRedeemBean.getFund_code()));
        Glide.with(getApplicationContext()).load(fundRedeemBean.getBank_logo())
                .into(redeemBankLogo);
        String bank_name = fundRedeemBean.getBank_name();
        redeemBankName.setText(bank_name == null ? "----" : (bank_name + " (" + fundRedeemBean.getCard_num() + ")"));
        redeemBankDescription.setText(bank_name == null ? "----" : "该银行卡将用于支付基金购买费用");
        min_redem_share = fundRedeemBean.getMin_redem_share();
        redeemMoneyEt.setHint(min_redem_share == null ? "----" : ("至少" + min_redem_share + "份"));
        max_redem_share = fundRedeemBean.getMax_redem_share();
        fund_redeem_share_tv.setText(min_redem_share == null ? "----" : ("可赎回份额: " + max_redem_share + "份\n(最低赎回份额" + min_redem_share + "份，最低持有份额" + fundRedeemBean.getMin_hold() + "份)"));
        if (min_redem_share != null) {
            redeemMoneyEt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.toString().contains(".")) {
                        if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                            s = s.toString().subSequence(0,
                                    s.toString().indexOf(".") + 3);
                            redeemMoneyEt.setText(s);
                            redeemMoneyEt.setSelection(s.length());
                        }
                    }
                    if (s.toString().trim().substring(0).equals(".")) {
                        s = "0" + s;
                        redeemMoneyEt.setText(s);
                        redeemMoneyEt.setSelection(2);
                    }
                    if (s.toString().startsWith("0")
                            && s.toString().trim().length() > 1) {
                        if (!s.toString().substring(1, 2).equals(".")) {
                            redeemMoneyEt.setText(s.subSequence(0, 1));
                            redeemMoneyEt.setSelection(1);
                            return;
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    getAndSetRedeemFee();
                }
            });
        }
        redeemConfirmDescription.setText("• 预计确认份额时间:T+2；15:00后交易净值将按次日计算；申购费用由基金公司收取");

        FundRedeemBean.FeeInfoBean fee_info = fundRedeemBean.getFee_info();
        if (fee_info == null || fee_info.getSubscribe_data().getData_list() == null || fee_info.getSubscribe_data().getData_list().size() == 0) {
            redeemRateTv.setText("0%");
        } else {
            List<FundRedeemBean.FeeInfoBean.RedeemDataBean.DataListBeanX> data_list = fee_info.getRedeem_data().getData_list();
            if (data_list == null || data_list.size() < 1)
                return;

            redeemRateTv.setText(fee_info.getRedeem_data().getData_list().size() > 1 ?
                    (fee_info.getRedeem_data().getData_list().get(data_list.size() - 1).getRedeem_rate() + "~" + data_list.get(0).getRedeem_rate()) :
                    data_list.get(0).getRedeem_rate());
        }

    }

    private String calcRedeemFee(String amount) {
        OkGo.get(ApiUtils.getCalcRedeemFee(this, fund_id, amount)).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                RedeemFeeBean redeemFeeBean = ParseUtils.parseByGson(s, RedeemFeeBean.class);
                redeemHintTv.setText("预计到账      " + redeemFeeBean.getArrive_amount() + "元");
                redeemHintTv.setTextColor(getResources().getColor(R.color.colorBidName));
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
            }
        });
        return "";
    }

    /**
     * 计算赎回费率并显示在控件上
     */
    private void getAndSetRedeemFee() {
        String money = redeemMoneyEt.getText().toString().trim();
        Float fmoney = Float.valueOf(TextUtils.isEmpty(money) ? "0.00" : money);
        redeemHintTv.setTextColor(getResources().getColor(R.color.colorBidName));
        if (fmoney <= 0) {
            btnOk.setEnabled(false);
            redeemHintTv.setText("预计到账      0元");
        } else if (fmoney < Float.valueOf(min_redem_share) && !max_redem_share.equals(money)) {
            redeemHintTv.setText("*最低赎回" + min_redem_share + "份");
            redeemHintTv.setTextColor(getResources().getColor(R.color.colorTextRed));
            btnOk.setEnabled(false);
        } else if (max_redem_share.equals(money)) {
            btnOk.setEnabled(true);
            btnOk.setOnClickListener(FundRedeemActivity.this);
            calcRedeemFee(max_redem_share);
        } else {
            if ((Float.valueOf(max_redem_share) - fmoney) < Float.valueOf(min_redem_share) && fmoney < Float.valueOf(max_redem_share)) {

                redeemHintTv.setTextColor(getResources().getColor(R.color.fund_text_red));
                redeemHintTv.setText("*持有份额已小于最低赎回份额，需全部赎回");
            } else if (!TextUtils.isEmpty(max_redem_share) && fmoney > Float.valueOf(max_redem_share)) {
                redeemMoneyEt.setText(max_redem_share);
                redeemMoneyEt.setSelection(redeemMoneyEt.getText().length());
                calcRedeemFee(max_redem_share);
            } else {
                calcRedeemFee(money);
            }
            btnOk.setEnabled(true);
            btnOk.setOnClickListener(FundRedeemActivity.this);
        }
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.activity_fund_redeem_iv_back:
                finish();
                break;
            case R.id.btn_ok:
                confirmRedeem();
                break;
            case R.id.load_error_layout:
                doBusiness(FundRedeemActivity.this);
                break;
            case R.id.activity_fund_redeem_all_redeem_bt:
                if (max_redem_share != null && max_redem_share.length() > 0) {
                    redeemMoneyEt.setText(max_redem_share);
                    redeemMoneyEt.setSelection(redeemMoneyEt.getText().length());
                    btnOk.setEnabled(true);
                    btnOk.setOnClickListener(FundRedeemActivity.this);
                }
                break;
            case R.id.activity_fund_redeem_feilv_detail_bt:
                Bundle args = new Bundle();
                args.putSerializable("redeem_data", fundRedeemBean.getFee_info().getRedeem_data());
                RedeemFeeDialog dialog = new RedeemFeeDialog();
                dialog.setArguments(args);
                dialog.show(getSupportFragmentManager(), "redeem_fee");
                break;
        }
    }

    private AnimDialog mAnimDialog;

    /**
     * 确认赎回按钮点击后发送确认赎回请求
     */
    private void confirmRedeem() {
        if (mAnimDialog == null)
            mAnimDialog = AnimDialog.showDialog(this);
        mAnimDialog.show();
        String time = String.valueOf(System.currentTimeMillis());
        String random = ApiUtils.getRandom();
        OkGo.post(ApiUtils.postWithConfirmRedeem())
                .params("user_name", UserUtils.getUserName(this))
                .params("login_token", UserUtils.getLoginToken(this))
                .params("amount", redeemMoneyEt.getText().toString().trim())
                .params("fund_code", fundRedeemBean.getFund_code())
                .params("t", time)
                .params("random", random)
                .params("sign", NormalUtils.md5(time + random + MyApplication.key))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            String flt_order_no = jsonObject.optString("flt_order_no");
                            if (TextUtils.isEmpty(flt_order_no)){
                                NormalUtils.showToast(FundRedeemActivity.this,jsonObject.optString("msg"));
                                mAnimDialog.dismiss();
                                return;
                            }
                            Bundle args1 = new Bundle();
                            args1.putInt(PurchaseRedeemResultActivity.TYPE,
                                    PurchaseRedeemResultActivity.TYPE_SALE);
                            args1.putInt(PurchaseRedeemResultActivity.FUND_ID, fund_id);
                            args1.putString(PurchaseRedeemResultActivity.ORDER_NO, flt_order_no);
                            //结果进入我的基金--处理中
                            args1.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, 1);
                            Intent intent1 = new Intent(FundRedeemActivity.this, PurchaseRedeemResultActivity.class);
                            intent1.putExtras(args1);
                            startActivity(intent1);
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mAnimDialog.dismiss();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        NormalUtils.showToast(FundRedeemActivity.this, R.string.net_error);
                        mAnimDialog.dismiss();
                    }
                });
    }

}
