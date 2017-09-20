package com.changju.fanlitou.activity.fund;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.changju.fanlitou.R;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.MyApplication;
import com.changju.fanlitou.bean.fund.FundPurchaseBean;
import com.changju.fanlitou.bean.fund.PurchaseFeeBean;
import com.changju.fanlitou.ui.EditTextWithDel;
import com.changju.fanlitou.ui.dialog.AgreementDialog;
import com.changju.fanlitou.ui.dialog.AnimDialog;
import com.changju.fanlitou.ui.dialog.RiskHintDialog;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.changju.fanlitou.util.TextClickable;
import com.changju.fanlitou.util.UserUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class FundPurchaseActivity extends BaseActivity {


    private int fund_id = 0;
    private FundPurchaseBean fundPurchaseBean;
    private ImageView purchaseIvBack;
    private TextView purchaseTitleTv;
    private ImageView purchaseBankLogo;
    private TextView purchaseBankName;
    private TextView purchaseBankDescription;
    private TextView purchaseMoneyTv;
    private EditTextWithDel purchaseMoneyEt;
    private TextView purchaseSubscribeRate;
    private TextView purchaseFanlitouRate;
    private TextView purchaseConfirmDescription;
    private TextView purchaseBuyDescription;
    private TextView tv_agreement_info;
    private FrameLayout layoutAgreement;
    private TextView btnOk;
    private TextView feilvTv;
    private CheckBox check_box;
    private boolean canbuy = false;
    private View load_error_layout;
    private View include;
    private String money;
    private AnimDialog animDialog;
    private boolean resumeFlag = false;


    @Override
    public void initParams(Bundle params) {
        fund_id = params.getInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_fund_purchase;
    }


    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "基金-申购");

        purchaseIvBack = (ImageView) findViewById(R.id.activity_fund_purchase_iv_back);
        purchaseIvBack.setOnClickListener(this);
        purchaseTitleTv = (TextView) findViewById(R.id.fund_purchase_title_tv);
        purchaseBankLogo = (ImageView) findViewById(R.id.activity_fund_purchase_bank_logo);
        purchaseBankName = (TextView) findViewById(R.id.activity_fund_purchase_bank_name);
        purchaseBankDescription = (TextView) findViewById(R.id.activity_fund_purchase_bank_description);
        purchaseMoneyTv = (TextView) findViewById(R.id.activity_fund_purchase_money_tv);
        purchaseMoneyEt = (EditTextWithDel) findViewById(R.id.activity_fund_purchase_money_et);
        feilvTv = (TextView) findViewById(R.id.feilv);
        purchaseSubscribeRate = (TextView) findViewById(R.id.activity_fund_purchase_subscribe_rate);
        purchaseFanlitouRate = (TextView) findViewById(R.id.activity_fund_purchase_fanlitou_rate);
        purchaseConfirmDescription = (TextView) findViewById(R.id.activity_fund_purchase_confirm_description);
        tv_agreement_info = (TextView) findViewById(R.id.tv_agreement_info);
        layoutAgreement = (FrameLayout) findViewById(R.id.layout_agreement);
        btnOk = (TextView) findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(this);

        check_box = (CheckBox) findViewById(R.id.check_box);
        check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                btnOk.setEnabled(isChecked && isReady);
            }
        });
        include = findViewById(R.id.include_loading);
        load_error_layout = findViewById(R.id.load_error_layout);
        load_error_layout.setOnClickListener(this);
    }

    @Override
    public void doBusiness(final Context mContext) {
    }

    private void initData(final Context mContext) {
        OkGo.get(ApiUtils.getConfirmPurchase(mContext, fund_id)).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                load_error_layout.setVisibility(View.GONE);
                include.setVisibility(View.GONE);
                fundPurchaseBean = ParseUtils.parseByGson(s, FundPurchaseBean.class);
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
        String fund_name = fundPurchaseBean.getFund_name();
        purchaseTitleTv.setText(fund_name == null ? "----" : (fund_name + fundPurchaseBean.getFund_code()));
        Glide.with(getApplicationContext()).load(fundPurchaseBean.getBank_logo())
                .into(purchaseBankLogo);
        String bank_name = fundPurchaseBean.getBank_name();
        purchaseBankName.setText(bank_name == null ? "----" : (bank_name + " (" + fundPurchaseBean.getCard_num() + ")"));
        purchaseBankDescription.setText(bank_name == null ? "----" : "该银行卡将用于支付基金购买费用");
        String min_invest_amount = fundPurchaseBean.getMin_invest_amount();
        purchaseMoneyEt.setHint(min_invest_amount == null ? "----" : ("最低" + min_invest_amount + "元"));
        if (min_invest_amount != null) {
            if (resumeFlag) getAndSetPurchaseFee();
            purchaseMoneyEt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.toString().contains(".")) {
                        if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                            s = s.toString().subSequence(0,
                                    s.toString().indexOf(".") + 3);
                            purchaseMoneyEt.setText(s);
                            purchaseMoneyEt.setSelection(s.length());
                        }
                    }
                    if (s.toString().trim().substring(0).equals(".")) {
                        s = "0" + s;
                        purchaseMoneyEt.setText(s);
                        purchaseMoneyEt.setSelection(2);
                    }
                    if (s.toString().startsWith("0")
                            && s.toString().trim().length() > 1) {
                        if (!s.toString().substring(1, 2).equals(".")) {
                            purchaseMoneyEt.setText(s.subSequence(0, 1));
                            purchaseMoneyEt.setSelection(1);
                            return;
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    getAndSetPurchaseFee();
                }
            });

        }
        purchaseConfirmDescription.setText("• 预计确认份额时间: T+2 ；15:00后交易净值将按次日计算；申购费用由基金公司收取");

        purchaseSubscribeRate.setVisibility(View.VISIBLE);
        purchaseSubscribeRate.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        if (fundPurchaseBean.getFee_info() == null || fundPurchaseBean.getFee_info().getSubscribe_data().getData_list() == null || fundPurchaseBean.getFee_info().getSubscribe_data().getData_list().size() == 0) {
            purchaseSubscribeRate.setText("0%");
            purchaseFanlitouRate.setText("0%");
        } else {
            purchaseSubscribeRate.setText(fundPurchaseBean.getFee_info().getSubscribe_data().getData_list().get(0).getOriginal_rate());
            purchaseFanlitouRate.setVisibility(View.VISIBLE);
            purchaseFanlitouRate.setText(fundPurchaseBean.getFee_info().getSubscribe_data().getData_list().get(0).getFanlitou_rate());
            feilvTv.setVisibility(View.VISIBLE);
        }

        setAgreementInfo();

    }

    /**
     * 设置协议信息
     */
    private void setAgreementInfo() {
        List<FundPurchaseBean.AgreementListBean> agreements = fundPurchaseBean.getAgreement_list();
        if (agreements == null || agreements.size() < 1) {
            layoutAgreement.setVisibility(View.GONE);
        } else {
            layoutAgreement.setVisibility(View.VISIBLE);
            StringBuilder sb = new StringBuilder("        同意");
            for (int i = 0; i < agreements.size(); i++) {
                final FundPurchaseBean.AgreementListBean agree = agreements.get(i);
                final String title = agree.getTitle() == null ? agree.getName() : agree.getTitle();
                sb.append("《");
                sb.append(title);
                sb.append("》");
            }

            sb.append(",并开通理财账户");

            SpannableString spanableInfo = new SpannableString(sb.toString());
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(android.R.color.transparent));
            spanableInfo.setSpan(colorSpan, 0, 10, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            for (int i = 0; i < agreements.size(); i++) {
                final FundPurchaseBean.AgreementListBean agree = agreements.get(i);
                final String title = agree.getTitle() == null ? agree.getName() : agree.getTitle();
                spanableInfo.setSpan(new TextClickable(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                /**
                                 * 协议内容展示类型
                                 * url：协议内容来自于url字段
                                 * content：协议内容来自于content字段
                                 */
                                Bundle args = new Bundle();
                                args.putString(AgreementDialog.TITLE, title);
                                args.putString(AgreementDialog.CONTENT, agree.getContent());
                                AgreementDialog dialog = new AgreementDialog();
                                dialog.setArguments(args);
                                dialog.show(getSupportFragmentManager(), "content");
                            }
                        }), sb.indexOf(title) - 1, sb.indexOf(title) + title.length() + 1, //设置需要监听的字符串位置
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            tv_agreement_info.setText(spanableInfo);  //将处理过的数据set到View里
            tv_agreement_info.setMovementMethod(LinkMovementMethod.getInstance());
            tv_agreement_info.setHighlightColor(getResources().getColor(android.R.color.transparent));

        }
    }

    private boolean isReady;

    /**
     * 计算购买费率并显示在控件上
     */
    private void getAndSetPurchaseFee() {
        money = purchaseMoneyEt.getText().toString().trim();
        Float fmoney = Float.valueOf("".equals(money) ? "0.00" : money);
        String min_invest_amount = fundPurchaseBean.getMin_invest_amount();
        if (fmoney <= 0) {
            feilvTv.setVisibility(View.VISIBLE);
            feilvTv.setText("费率");
            purchaseSubscribeRate.setVisibility(View.VISIBLE);
            purchaseFanlitouRate.setVisibility(View.VISIBLE);
            purchaseSubscribeRate.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            if (fundPurchaseBean.getFee_info() == null || fundPurchaseBean.getFee_info().getSubscribe_data().getData_list() == null || fundPurchaseBean.getFee_info().getSubscribe_data().getData_list().size() == 0) {
                purchaseSubscribeRate.setText("0%");
                purchaseFanlitouRate.setText("0%");
            } else {
                purchaseSubscribeRate.setText(fundPurchaseBean.getFee_info().getSubscribe_data().getData_list().get(0).getOriginal_rate());
                purchaseFanlitouRate.setVisibility(View.VISIBLE);
                purchaseFanlitouRate.setText(fundPurchaseBean.getFee_info().getSubscribe_data().getData_list().get(0).getFanlitou_rate());
                feilvTv.setVisibility(View.VISIBLE);
            }
            isReady = false;
            btnOk.setEnabled(false);
            return;
        } else if (fmoney < Float.valueOf(min_invest_amount)) {
            feilvTv.setVisibility(View.GONE);
            purchaseSubscribeRate.setVisibility(View.GONE);
            purchaseFanlitouRate.setVisibility(View.VISIBLE);
            purchaseFanlitouRate.setText("*最低买入" + min_invest_amount + "元");
            isReady = false;
            btnOk.setEnabled(false);
            return;
        } else {
            String max_invest_amount = fundPurchaseBean.getMax_invest_amount();
            if (max_invest_amount != null && fmoney > Float.valueOf(max_invest_amount)) {
                purchaseMoneyEt.setText(max_invest_amount);
                purchaseMoneyEt.setSelection(purchaseMoneyEt.getText().length());
                NormalUtils.showToast(this, "最多可购买" + max_invest_amount + "元");
                money = max_invest_amount;
            }
            calcPurchaseFee();
            isReady = true;
            btnOk.setEnabled(check_box.isChecked());
        }
    }

    private void calcPurchaseFee() {
        OkGo.get(ApiUtils.getCalcPurchaseFee(this, fund_id, money)).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                PurchaseFeeBean purchaseFeeBean = ParseUtils.parseByGson(s, PurchaseFeeBean.class);
                feilvTv.setVisibility(View.VISIBLE);
                purchaseSubscribeRate.setVisibility(View.GONE);
                purchaseFanlitouRate.setVisibility(View.GONE);
                feilvTv.setText("预计申购费用" + purchaseFeeBean.getFee_amount() + "元，节省" + purchaseFeeBean.getSave_money() + "元");
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
            }
        });
    }


    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.activity_fund_purchase_iv_back:
                finish();
                break;
            case R.id.btn_ok:
                if (!check_box.isChecked() && check_box.getVisibility() == View.VISIBLE) {
                    NormalUtils.showToast(this, "请阅读并同意相关协议");
                    break;
                }
                showRiskHintDialog();
                break;
            case R.id.load_error_layout:
                load_error_layout.setVisibility(View.GONE);
                include.setVisibility(View.VISIBLE);
                initData(FundPurchaseActivity.this);
                break;
        }
    }

    private void showRiskHintDialog() {
        Bundle args = new Bundle();
        args.putInt("fund_id", fund_id);
        args.putString("amount", purchaseMoneyEt.getText().toString().trim());
        String fund_code = fundPurchaseBean.getFund_code();
        args.putString("fund_code", fund_code);
        String risk_level_class = fundPurchaseBean.getRisk_level_class();
        String risk_level = fundPurchaseBean.getRisk_level();
        switch (risk_level_class) {
            case "first_evaluated":
                args.putString(AgreementDialog.CONTENT, "根据监管要求，购买基金需进行风险测评，否则将无法继续购买。");
                args.putString("button1", "取消");
                args.putString("button2", "风险测评");
                RiskHintDialog dialog1 = new RiskHintDialog();
                dialog1.setArguments(args);
                dialog1.show(getSupportFragmentManager(), "content");
                break;
            case "force_evaluated":
                args.putString(AgreementDialog.CONTENT, "您的风险承受能力为" + risk_level + "，根据监管要求，您当前暂不可购买超出风险承受能力的产品。");
                args.putString("button1", "取消");
                args.putString("button2", "重新测评");
                RiskHintDialog dialog2 = new RiskHintDialog();
                dialog2.setArguments(args);
                dialog2.show(getSupportFragmentManager(), "content");
                break;
            case "notify":
                String fund_risk_level = fundPurchaseBean.getFund_risk_level();
                args.putString(AgreementDialog.CONTENT, "该产品为" + fund_risk_level + "风险基金，已超出您当前的风险承受能力（" + risk_level + "），是否确认购买？");
                args.putString("button1", "风险测评");
                args.putString("button2", "确认购买");
                RiskHintDialog dialog3 = new RiskHintDialog();
                dialog3.setArguments(args);
                dialog3.show(getSupportFragmentManager(), "content");
                break;
            case "direct_purchase":
                if (animDialog == null)
                    animDialog = AnimDialog.showDialog(this);

                animDialog.show();
                String time = String.valueOf(System.currentTimeMillis());
                String random = ApiUtils.getRandom();
                OkGo.post(ApiUtils.postWithConfirmPurchase())
                        .params("user_name", UserUtils.getUserName(this))
                        .params("login_token", UserUtils.getLoginToken(this))
                        .params("amount", money)
                        .params("fund_code", fund_code)
                        .params("t", time)
                        .params("random", random)
                        .params("sign", NormalUtils.md5(time + random + MyApplication.key))
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                animDialog.dismiss();
                                try {
                                    JSONObject jsonObject = new JSONObject(s);
                                    String flt_order_no = jsonObject.optString("flt_order_no");
                                    if (TextUtils.isEmpty(flt_order_no)){
                                        NormalUtils.showToast(FundPurchaseActivity.this,jsonObject.optString("msg"));
                                        return;
                                    }
                                    Bundle args1 = new Bundle();
                                    args1.putInt(PurchaseRedeemResultActivity.TYPE,
                                            PurchaseRedeemResultActivity.TYPE_BUY);
                                    args1.putInt(PurchaseRedeemResultActivity.FUND_ID, fund_id);
                                    args1.putString(PurchaseRedeemResultActivity.ORDER_NO, flt_order_no);
                                    Intent intent1 = new Intent(FundPurchaseActivity.this, PurchaseRedeemResultActivity.class);
                                    intent1.putExtras(args1);
                                    startActivity(intent1);
                                    finish();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                super.onError(call, response, e);
                                NormalUtils.showToast(FundPurchaseActivity.this, R.string.net_error);
                                animDialog.dismiss();
                            }
                        });
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData(this);
        resumeFlag = true;
    }
}
