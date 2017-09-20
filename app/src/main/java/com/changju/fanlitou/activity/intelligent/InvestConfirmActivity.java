package com.changju.fanlitou.activity.intelligent;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
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
import com.changju.fanlitou.activity.WebActivity;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.MyApplication;
import com.changju.fanlitou.bean.intelligent.ConfirmApply;
import com.changju.fanlitou.bean.intelligent.IntelligentInvestPost;
import com.changju.fanlitou.bean.intelligent.InvestCalculator;
import com.changju.fanlitou.bean.intelligent.SMS;
import com.changju.fanlitou.ui.EditTextWithDel;
import com.changju.fanlitou.ui.dialog.AgreementDialog;
import com.changju.fanlitou.ui.dialog.AnimDialog;
import com.changju.fanlitou.ui.dialog.RechargeBankStorageDialog;
import com.changju.fanlitou.ui.dialog.RechargeDialog;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.CountDownTimerUtil;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.changju.fanlitou.util.TextClickable;
import com.changju.fanlitou.util.UserUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chengww on 2017/7/6.
 */

public class InvestConfirmActivity extends BaseActivity {

    public static final String BID_TYPE = "type";
    private int bid_id;
    private String bid_type;
    //loading&error
    private View include;
    private View include_load_error;

    private ImageView platform_logo_app;

    private TextView tv_content, tv_bid_name, tv_zhye, tv_ktje, tv_phone, tv_fanli, tv_agreement_info;

    private EditTextWithDel et_tzje, et_msg_code;

    private FrameLayout layout_agreement;
    private CheckBox check_box;
    private ConfirmApply confirmApply;
    private TextView btn_get_msg_code, btn_ok;

    //短信验证token
    private String sms_auth_token;

    private boolean isChecked = true;

    @Override
    public void initParams(Bundle params) {
        bid_id = params.getInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
        bid_type = params.getString(BID_TYPE);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_invest_confirm;
    }

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "智能投顾-投资确认");

        mClearDrawable = getResources().getDrawable(R.mipmap.ic_delete);
        include = findViewById(R.id.include_loading);
        include.setVisibility(View.VISIBLE);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);

        findViewById(R.id.iv_back_my_account).setOnClickListener(this);
        findViewById(R.id.btn_cz).setOnClickListener(this);
        btn_get_msg_code = (TextView) findViewById(R.id.btn_get_msg_code);
        btn_get_msg_code.setOnClickListener(this);
        btn_ok = (TextView) findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(this);

        platform_logo_app = (ImageView) findViewById(R.id.platform_logo_app);
        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_bid_name = (TextView) findViewById(R.id.tv_bid_name);
        tv_zhye = (TextView) findViewById(R.id.tv_zhye);
        tv_ktje = (TextView) findViewById(R.id.tv_ktje);
        tv_phone = (TextView) findViewById(R.id.tv_phone);

        tv_fanli = (TextView) findViewById(R.id.tv_fanli);
        tv_fanli.setText(Html.fromHtml(
                "预估利息" +
                        "<font color='#f42c20'>0</font>" +
                        "元，返利" +
                        "<font color='#f42c20'>0</font>" +
                        "元，具体金额以平台实际确认为准"));

        tv_agreement_info = (TextView) findViewById(R.id.tv_agreement_info);
        et_tzje = (EditTextWithDel) findViewById(R.id.et_tzje);
        et_msg_code = (EditTextWithDel) findViewById(R.id.et_msg_code);
        MyTextWatcher watcher = new MyTextWatcher();
        et_tzje.addTextChangedListener(watcher);
        et_msg_code.addTextChangedListener(watcher);
        layout_agreement = (FrameLayout) findViewById(R.id.layout_agreement);
        check_box = (CheckBox) findViewById(R.id.check_box);
        check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                InvestConfirmActivity.this.isChecked = isChecked
                        || check_box.getVisibility() != View.VISIBLE;
                btn_ok.setEnabled(isChecked && InvestConfirmActivity.this.isReady);
            }
        });

        et_tzje.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString();
                if (str.contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        et_tzje.setText(s);
                        et_tzje.setSelection(s.length());
                    }
                }
                if (str.trim().substring(0).equals(".")) {
                    s = "0" + s;
                    et_tzje.setText(s);
                    et_tzje.setSelection(et_tzje.getText().length());
                }
                if (str.startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        et_tzje.setText(s.subSequence(0, 1));
                        et_tzje.setSelection(et_tzje.getText().length());
                    }
                }

                if (!TextUtils.isEmpty(et_tzje.getText()))
                    investCalculator(et_tzje.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    /**
     * 输入投资金额计算收益
     *
     * @param bid_amount
     */
    private void investCalculator(String bid_amount) {
        OkGo.get(ApiUtils.getInvestCalculator(bid_id, bid_type, bid_amount))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        InvestCalculator calculator = ParseUtils.parseByGson(s, InvestCalculator.class);
                        float dailyBonus = Float.valueOf(calculator.getDaily_bonus());
                        if (dailyBonus > 0) {
                            tv_fanli.setText(Html.fromHtml(
                                    "预估利息" +
                                            "<font color='#f42c20'>" +
                                            calculator.getInvest_income() +
                                            "</font>" +
                                            "元，日返" +
                                            "<font color='#f42c20'>" +
                                            dailyBonus +
                                            "</font>" +
                                            "元，具体金额以平台实际确认为准"));
                        } else {
                            tv_fanli.setText(Html.fromHtml(
                                    "预估利息" +
                                            "<font color='#f42c20'>" +
                                            calculator.getInvest_income() +
                                            "</font>" +
                                            "元，返利" +
                                            "<font color='#f42c20'>" +
                                            calculator.getTotal_bonus() +
                                            "</font>" +
                                            "元，具体金额以平台实际确认为准"));
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        tv_fanli.setText(Html.fromHtml(
                                "预估利息" +
                                        "<font color='#f42c20'>0</font>" +
                                        "元，返利" +
                                        "<font color='#f42c20'>0</font>" +
                                        "元，具体金额以平台实际确认为准"));
                    }
                });
    }

    @Override
    public void doBusiness(final Context mContext) {
    }

    private void initData(final Context mContext) {
        OkGo.get(ApiUtils.getConfirmApply(mContext, bid_id))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        confirmApply = ParseUtils.parseByGson(s, ConfirmApply.class);
                        if (!confirmApply.isIs_need_invest_sms_code()) {
                            findViewById(R.id.layout_msg_code).setVisibility(View.GONE);
                            findViewById(R.id.layout_phone_number).setVisibility(View.GONE);
                        }


                        //平台信息
                        ConfirmApply.PlatformInfoBean platform =
                                confirmApply.getPlatform_info();
                        Glide.with(getApplicationContext())
                                .load(platform.getPlatform_logo())
                                .into(platform_logo_app);

                        ConfirmApply.BidDetailInfoBean detail =
                                confirmApply.getBid_detail_info();
                        tv_bid_name.setText(detail.getBid_name());
                        tv_content.setText("年化收益：" + detail.getBid_interest()
                                + "%+" + detail.getBonus_interest() + "%\n"
                                + "投资期限：" + detail.getDuration() + detail.getDuration_unit_str()
                                + "\n还款方式：" + detail.getPay_type_str());
                        tv_ktje.setText(detail.getRemain_amount() + "元");
                        et_tzje.setHint("起投" + detail.getMin_invest_amount() + "元");


                        tv_zhye.setText(confirmApply.getAccount_balance() + "元");
                        tv_phone.setText(confirmApply.getAccount_phone_number());

                        List<ConfirmApply.AgreementListBean> agreements =
                                confirmApply.getAgreement_list();

                        if (!confirmApply.isIs_need_user_agreement()
                                || agreements == null
                                || agreements.size() < 1) {
                            layout_agreement.setVisibility(View.GONE);
                        } else {
                            StringBuilder sb = new StringBuilder("        同意");
                            for (int i = 0; i < agreements.size(); i++) {
                                ConfirmApply.AgreementListBean agree = agreements.get(i);
                                String title = agree.getTitle();
                                sb.append("《");
                                sb.append(title);
                                sb.append("》");
                            }

                            sb.append("，并已知晓只有通过返利投确认购买才可获得返利");

                            SpannableString spanableInfo = new SpannableString(sb.toString());
                            ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(android.R.color.transparent));
                            spanableInfo.setSpan(colorSpan, 0, 10, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                            for (int i = 0; i < agreements.size(); i++) {

                                final ConfirmApply.AgreementListBean agree =
                                        agreements.get(i);
                                final String title = agree.getTitle();
                                spanableInfo.setSpan(new TextClickable(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                /**
                                                 * 协议内容展示类型
                                                 * url：协议内容来自于url字段
                                                 * content：协议内容来自于content字段
                                                 */
                                                Bundle args = new Bundle();
                                                switch (agree.getDisplay_type()) {
                                                    case "content":
                                                        args.putString(AgreementDialog.TITLE, agree.getTitle());
                                                        args.putString(AgreementDialog.CONTENT, agree.getContent());
                                                        AgreementDialog dialog = new AgreementDialog();
                                                        dialog.setArguments(args);
                                                        dialog.show(getSupportFragmentManager(), "content");
                                                        break;
                                                    case "url":
                                                        args.putString(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, agree.getUrl());
                                                        args.putString(WebActivity.TITLE, title);
                                                        InvestConfirmActivity.this.startActivity(WebActivity.class, args);
                                                        break;
                                                    default:
                                                        NormalUtils.showToast(InvestConfirmActivity.this, "错误0x12,请向客服反馈");
                                                        break;
                                                }
                                            }
                                        }), sb.indexOf(title) - 1, sb.indexOf(title) + title.length() + 1, //设置需要监听的字符串位置
                                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            }
                            tv_agreement_info.setText(spanableInfo);  //将处理过的数据set到View里
                            tv_agreement_info.setMovementMethod(LinkMovementMethod.getInstance());
                            tv_agreement_info.setHighlightColor(getResources().getColor(android.R.color.transparent));
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

    private AnimDialog mAnimDialog;

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_my_account:
                finish();
                break;
            case R.id.btn_ok:
                if (TextUtils.isEmpty(et_tzje.getText())) {
                    NormalUtils.showToast(this, "请输入投资金额");
                    break;
                }

                float tzje = Float.valueOf(et_tzje.getText().toString());

                if (tzje < Float.valueOf(confirmApply.getBid_detail_info().getMin_invest_amount())) {
                    NormalUtils.showToast(this, "投资金额至少" + confirmApply.getBid_detail_info().getMin_invest_amount() + "元");
                    break;
                }

                if (confirmApply.isIs_need_invest_sms_code()
                        && TextUtils.isEmpty(et_msg_code.getText())) {
                    NormalUtils.showToast(this, "请输入验证码");
                    break;
                }

                final float money = tzje - Float.valueOf(confirmApply.getAccount_balance());

                if (money > 0) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, bid_id);
                    bundle.putFloat(RechargeActivity.class.getSimpleName(), money);
                    RechargeDialog recharge = new RechargeDialog();
                    recharge.setArguments(bundle);
                    recharge.show(getSupportFragmentManager(), "recharge");
                    break;
                }

                if (!check_box.isChecked()) {
                    NormalUtils.showToast(this, "请阅读并同意相关协议");
                    break;
                }

                //进行投资

                switch (confirmApply.getPlatform_info().getPlatform_bank_interface_type()) {
                    case "bank_storage":

                        Bundle args = new Bundle();
                        args.putString(LazyFragment.INTENT_BOOLEAN_LAZYLOAD
                                , ApiUtils.getBankStorageInvest(InvestConfirmActivity.this,
                                        bid_id, et_tzje.getText().toString()));
                        args.putString(WebActivity.TITLE, "登录存管银行购买");
                        startActivity(WebActivity.class, args);

                        Bundle bundle = new Bundle();
                        bundle.putString(RechargeBankStorageDialog.TITLE, "登录存管银行购买");
                        bundle.putString(RechargeBankStorageDialog.CONTENT,
                                "请在新打开的网上银行页面进行操作，操作未完成之前请不要关闭该窗口");
                        bundle.putString(RechargeBankStorageDialog.POSTIVE, "购买完成");
                        bundle.putString(RechargeBankStorageDialog.TYPE, "InvestConfirm");
                        bundle.putInt(RechargeBankStorageDialog.BID_ID, bid_id);
                        RechargeBankStorageDialog dialog = new RechargeBankStorageDialog();
                        dialog.setArguments(bundle);
                        dialog.show(getSupportFragmentManager(), "RechargeBankStorageDialog");

                        break;
                    case "quick_payment":

                        String t = String.valueOf(System.currentTimeMillis());
                        String r = ApiUtils.getRandom();


                        String sms_auth_code = "";
                        if (confirmApply.isIs_need_invest_sms_code())
                            sms_auth_code = et_msg_code.getText().toString();

                        if (mAnimDialog == null)
                            mAnimDialog = AnimDialog.showDialog(this);
                        mAnimDialog.show();

                        OkGo.post(ApiUtils.postIntelligentInvest())
                                .params("t", t)
                                .params("random", r)
                                .params("sign", NormalUtils.md5(t + r + MyApplication.key))
                                .params("user_name", UserUtils.getUserName(this))
                                .params("login_token", UserUtils.getLoginToken(this))
                                .params("bid_id", bid_id)
                                .params("amount", et_tzje.getText().toString())
                                .params("sms_auth_code", sms_auth_code)
                                .params("sms_auth_token", sms_auth_token)
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(String s, Call call, Response response) {
                                        IntelligentInvestPost invest = ParseUtils.parseByGson(
                                                s, IntelligentInvestPost.class);
                                        if (invest.isSuccess()) {
                                            //跳转到结果页
                                            startToPurchaseResult(invest.getFlt_order_no());
                                        } else {
                                            NormalUtils.showToast(InvestConfirmActivity.this, invest.getMsg());
                                        }
                                        mAnimDialog.dismiss();
                                    }

                                    @Override
                                    public void onError(Call call, Response response, Exception e) {
                                        super.onError(call, response, e);
                                        NormalUtils.showToast(InvestConfirmActivity.this, R.string.net_error);
                                        mAnimDialog.dismiss();
                                    }
                                });

                        break;
                    default:
                        NormalUtils.showToast(InvestConfirmActivity.this, "类型错误0x13");
                        break;
                }

                break;
            case R.id.btn_get_msg_code:

                if (TextUtils.isEmpty(et_tzje.getText())) {
                    NormalUtils.showToast(this, "请输入投资金额");
                    break;
                }

                float tz = Float.valueOf(et_tzje.getText().toString());

                if (tz < Float.valueOf(confirmApply.getBid_detail_info().getMin_invest_amount())) {
                    NormalUtils.showToast(this, "投资金额至少" + confirmApply.getBid_detail_info().getMin_invest_amount() + "元");
                    break;
                }

                if (mAnimDialog == null)
                    mAnimDialog = AnimDialog.showDialog(this);
                mAnimDialog.show();

                String time = String.valueOf(System.currentTimeMillis());
                String random = ApiUtils.getRandom();

                String amount = et_tzje.getText().toString();

                if (TextUtils.isEmpty(amount))
                    amount = "0";

                OkGo.post(ApiUtils.postIntelligentSMS())
                        .params("t", time)
                        .params("random", random)
                        .params("sign", NormalUtils.md5(time + random + MyApplication.key))
                        .params("platform_id", confirmApply.getPlatform_info().getPlatform_id())
                        .params("user_name", UserUtils.getUserName(this))
                        .params("amount", amount)
                        .params("sms_type", "invest")
                        .params("login_token", UserUtils.getLoginToken(this))
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                SMS bean = ParseUtils.parseByGson(s, SMS.class);
                                if (bean.isSuccess()) {
                                    CountDownTimerUtil mCountDownTimerUtils =
                                            new CountDownTimerUtil(btn_get_msg_code
                                                    , 60000, 1000);
                                    mCountDownTimerUtils.start();

                                    sms_auth_token = bean.getSms_auth_token();

                                } else {
                                    NormalUtils.showToast(InvestConfirmActivity.this,
                                            bean.getMsg());
                                }
                                mAnimDialog.dismiss();

                            }

                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                super.onError(call, response, e);
                                NormalUtils.showToast(InvestConfirmActivity.this,
                                        R.string.net_error);
                                mAnimDialog.dismiss();
                            }
                        });
                break;
            case R.id.btn_cz:
                Bundle args = new Bundle();
                args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, bid_id);
                args.putInt(RechargeBankStorageDialog.PLATFORM_ID, confirmApply.getPlatform_info().getPlatform_id());
                startActivity(RechargeActivity.class, args);
                break;
            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                include_load_error.setVisibility(View.GONE);
                doBusiness(this);
                break;
        }
    }

    public void startToPurchaseResult(String flt_order_no) {
        Bundle args = new Bundle();
        args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, bid_id);
        args.putString(PurchaseResultActivity.FLT_ORDER, flt_order_no);
        startActivity(PurchaseResultActivity.class, args);
        finish();
    }

    /**
     * 删除按钮的引用
     */
    private Drawable mClearDrawable;

    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     *
     * @param visible
     */
    protected void setClearIconVisible(boolean visible) {
        Drawable right = visible ? mClearDrawable : null;
        et_tzje.setCompoundDrawablesWithIntrinsicBounds(
                et_tzje.getCompoundDrawables()[0],
                et_tzje.getCompoundDrawables()[1], right,
                et_tzje.getCompoundDrawables()[3]);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData(this);
    }

    private boolean isReady;

    private class MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            isReady = true;

            if (TextUtils.isEmpty(et_tzje.getText())) {
                isReady = false;
            }

            if (TextUtils.isEmpty(et_msg_code.getText()) &&
                    findViewById(R.id.layout_msg_code).getVisibility() == View.VISIBLE)
                isReady = false;

            btn_ok.setEnabled(isReady && isChecked);

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
