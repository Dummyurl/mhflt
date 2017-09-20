package com.changju.fanlitou.activity.fund;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.method.NumberKeyListener;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.changju.fanlitou.R;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.MyApplication;
import com.changju.fanlitou.bean.fund.OpenFundAccountBean;
import com.changju.fanlitou.bean.fund.SmSBean;
import com.changju.fanlitou.ui.EditTextWithDel;
import com.changju.fanlitou.ui.dialog.AddOpenFundCardDialog;
import com.changju.fanlitou.ui.dialog.AddOpenFundInfoDialog;
import com.changju.fanlitou.ui.dialog.AgreementDialog;
import com.changju.fanlitou.ui.dialog.AnimDialog;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.CountDownTimerUtil;
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

import static android.text.TextUtils.isEmpty;

/**
 * Created by chengww on 2017/7/6.
 * 基金开户
 */

public class OpenFundActivity extends BaseActivity {

    private EditTextWithDel et_name, et_id_num, et_bank_num, et_phone, et_msg_code;
    private TextView tv_bank, tv_province;
    private CheckBox check_box;

    private LinearLayout layout_agreement;
    private TextView tv_agreement_info;

    //loading&error
    private View include;
    private View include_load_error;

    private int fund_id;
    private OpenFundAccountBean info;
    private List<OpenFundAccountBean.BanksBean> infoBanks;
    private List<OpenFundAccountBean.ProvinceListBean> infoProvince;
    private TextView btn_ok;

    private MyTextWatcher watcher;
    private boolean isReady;
    private boolean isChecked = true;
    private TextView btn_get_msg_code;
    private String phone;
    private String card_no;
    private String id_no;

    @Override
    public void initParams(Bundle params) {
        fund_id = params.getInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_fund_open_account;
    }

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "基金-基金开户");

        include = findViewById(R.id.include_loading);
        include.setVisibility(View.VISIBLE);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);

        findViewById(R.id.iv_back_my_account).setOnClickListener(this);
        watcher = new MyTextWatcher();
        et_name = (EditTextWithDel) findViewById(R.id.et_name);
        et_id_num = (EditTextWithDel) findViewById(R.id.et_id_num);
        et_bank_num = (EditTextWithDel) findViewById(R.id.et_bank_num);
        et_phone = (EditTextWithDel) findViewById(R.id.et_phone);
        et_msg_code = (EditTextWithDel) findViewById(R.id.et_msg_code);
        tv_agreement_info = (TextView) findViewById(R.id.tv_agreement_info);


        et_id_num.setKeyListener(new NumberKeyListener() {
            @Override
            public int getInputType() {
                return InputType.TYPE_CLASS_NUMBER;
            }

            protected char[] getAcceptedChars() {
                return new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'x', 'X'};
            }
        });


        et_name.addTextChangedListener(watcher);
        et_id_num.addTextChangedListener(watcher);
        et_bank_num.addTextChangedListener(watcher);
        //银行卡4位一隔
        et_bank_num.addTextChangedListener(new TextWatcher() {
            int beforeTextLength = 0;
            int onTextLength = 0;
            boolean isChanged = false;

            int location = 0;// 记录光标的位置
            private char[] tempChar;
            private StringBuffer buffer = new StringBuffer();
            int konggeNumberB = 0;

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                onTextLength = s.length();
                buffer.append(s.toString());
                if (onTextLength == beforeTextLength || onTextLength <= 3 || isChanged) {
                    isChanged = false;
                    return;
                }
                isChanged = true;
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                beforeTextLength = s.length();
                if (buffer.length() > 0) {
                    buffer.delete(0, buffer.length());
                }
                konggeNumberB = 0;
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == ' ') {
                        konggeNumberB++;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isChanged) {
                    location = et_bank_num.getSelectionEnd();
                    int index = 0;
                    while (index < buffer.length()) {
                        if (buffer.charAt(index) == ' ') {
                            buffer.deleteCharAt(index);
                        } else {
                            index++;
                        }
                    }

                    index = 0;
                    int konggeNumberC = 0;
                    while (index < buffer.length()) {
                        // if (index % 5 == 4) {
                        //      buffer.insert(index, ' ');
                        //      konggeNumberC++;
                        // }
                        if (index == 4 || index == 9 || index == 14 || index == 19) {
                            buffer.insert(index, ' ');
                            konggeNumberC++;
                        }
                        index++;
                    }

                    if (konggeNumberC > konggeNumberB) {
                        location += (konggeNumberC - konggeNumberB);
                    }

                    tempChar = new char[buffer.length()];
                    buffer.getChars(0, buffer.length(), tempChar, 0);
                    String str = buffer.toString();
                    if (location > str.length()) {
                        location = str.length();
                    } else if (location < 0) {
                        location = 0;
                    }

                    et_bank_num.setText(str);
                    Editable etable = et_bank_num.getText();
                    Selection.setSelection(etable, location);
                    isChanged = false;
                }
            }
        });
        et_phone.addTextChangedListener(watcher);
        et_msg_code.addTextChangedListener(watcher);

        tv_bank = (TextView) findViewById(R.id.tv_bank);
        tv_bank.setOnClickListener(this);
        tv_province = (TextView) findViewById(R.id.tv_province);
        tv_province.setOnClickListener(this);

        tv_bank.addTextChangedListener(watcher);
        tv_province.addTextChangedListener(watcher);

        btn_get_msg_code = (TextView) findViewById(R.id.btn_get_msg_code);
        btn_get_msg_code.setOnClickListener(this);
        btn_ok = (TextView) findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(this);
        btn_ok.setEnabled(false);

        layout_agreement = (LinearLayout) findViewById(R.id.layout_agreement);

        check_box = (CheckBox) findViewById(R.id.check_box);
        check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                OpenFundActivity.this.isChecked = isChecked
                        || check_box.getVisibility() != View.VISIBLE;
                btn_ok.setEnabled(isChecked && OpenFundActivity.this.isReady);
            }
        });

    }

    @Override
    public void doBusiness(Context mContext) {
        bindData(mContext);
    }

    private void bindData(final Context mContext) {
        OkGo.get(ApiUtils.getFundBankInfo(mContext))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        info = ParseUtils.parseByGson(s, OpenFundAccountBean.class);
                        infoBanks = info.getBanks();
                        infoProvince = info.getProvince_list();

                        //手机号
                        et_phone.setText(info.getPhone_number());

                        //实名认证
                        OpenFundAccountBean.VerifiedInfoBean verify = info.getVerified_info();
                        if (info.isIs_verified()) {
                            et_name.setText(verify.getName());
                            id_no = verify.getId_number();
                            if (!isEmpty(id_no) && id_no.length() > 8) {
                                int len = id_no.length();
                                StringBuilder sb = new StringBuilder();
                                sb.append(id_no.substring(0, 4));
                                for (int i = 0; i < len - 8; i++) {
                                    sb.append("*");
                                }
                                sb.append(id_no.substring(len - 4));
                                et_id_num.setText(sb);
                            } else {
                                et_id_num.setText(id_no);
                            }

                            et_name.setEnabled(false);
                            findViewById(R.id.layout_id_num).setVisibility(View.GONE);
                        }

                        //银行信息
                        if (info.isIs_already_bound_flt()) {

                            OpenFundAccountBean.FltBoundBankInfoBean boundBank = info.getFlt_bound_bank_info();

                            if (boundBank != null
                                    && boundBank.getBank_id() > 0
                                    && !isEmpty(boundBank.getBank_name())) {
                                bank_selected = new OpenFundAccountBean.BanksBean();
                                bank_selected.setBank_logo(boundBank.getBank_logo());
                                bank_selected.setBank_id(boundBank.getBank_id());
                                bank_selected.setBank_name(boundBank.getBank_name());

                                setResultFinish(null, null, bank_selected);

                                //****
                                card_no = boundBank.getCard_no();
                                if (!isEmpty(card_no) && card_no.length() > 8) {
                                    int len = card_no.length();
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(card_no.substring(0, 4));
                                    for (int i = 0; i < len - 8; i++) {
                                        sb.append("*");
                                    }
                                    sb.append(card_no.substring(len - 4));
                                    et_bank_num.setText(sb);
                                } else {
                                    et_bank_num.setText(card_no);
                                }

                                //****
                                et_bank_num.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                    }

                                    @Override
                                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                                        if (!isEmpty(card_no)) {
                                            card_no = "";
                                            et_bank_num.setText("");
                                        }
                                    }

                                    @Override
                                    public void afterTextChanged(Editable s) {

                                    }
                                });

                                //开户地区
                                if (info.isHas_city_province_info()) {

                                    one = new OpenFundAccountBean.ProvinceListBean();
                                    one.setName(boundBank.getProvincename());
                                    one.setProvinceno(boundBank.getProvinceno());

                                    two = new OpenFundAccountBean.ProvinceListBean.CitysBean();
                                    two.setName(boundBank.getCityname());
                                    two.setCityno(boundBank.getCityno());

                                    setResultFinish(one, two, null);
                                }

                            }

                        }



                        List<OpenFundAccountBean.AgreementListBean> agreements = info.getAgreement_list();
                        if (agreements == null || agreements.size() < 1) {
                            layout_agreement.setVisibility(View.GONE);
                        } else {
                            layout_agreement.setVisibility(View.VISIBLE);
                            StringBuilder sb = new StringBuilder("        同意");
                            for (int i = 0; i < agreements.size(); i++) {
                                final OpenFundAccountBean.AgreementListBean agree = agreements.get(i);
                                final String title = agree.getTitle() == null ? agree.getName() : agree.getTitle();
                                sb.append("《");
                                sb.append(title);
                                sb.append("》");
                            }

                            SpannableString spanableInfo = new SpannableString(sb.toString());
                            ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(android.R.color.transparent));
                            spanableInfo.setSpan(colorSpan, 0, 10, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                            for (int i = 0; i < agreements.size(); i++) {
                                final OpenFundAccountBean.AgreementListBean agree = agreements.get(i);
                                final String title = agree.getTitle() == null ? agree.getName() : agree.getTitle();
                                spanableInfo.setSpan(new Clickable(new View.OnClickListener() {
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

    private String order_id;


    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_msg_code:

                if (one == null)
                    one = new OpenFundAccountBean.ProvinceListBean();

                if (two == null)
                    two = new OpenFundAccountBean.ProvinceListBean.CitysBean();

                if (TextUtils.isEmpty(et_name.getText().toString())){
                    NormalUtils.showToast(this,"请输入姓名");
                    break;
                }

                String id_num = TextUtils.isEmpty(id_no) ? et_id_num.getText().toString() : id_no;
                if (TextUtils.isEmpty(id_num)){
                    NormalUtils.showToast(this,"请输入身份证号");
                    break;
                }

                String account_no = TextUtils.isEmpty(card_no) ? et_bank_num.getText().toString().replaceAll(" ", "") : card_no;

                if (TextUtils.isEmpty(account_no)){
                    NormalUtils.showToast(this,"请输入银行卡号");
                    break;
                }

                if (bank_selected.getBank_id() <= 0){
                    NormalUtils.showToast(this,"请选择开户行");
                    break;
                }

                if (one.getProvinceno() <= 0 || two.getCityno() <= 0 ){
                    NormalUtils.showToast(this,"请选择省市");
                    break;
                }

                phone = et_phone.getText().toString();

                if (isEmpty(phone)){
                    NormalUtils.showToast(this,"请输入手机号");
                    break;
                }

                if (mAnimDialog == null)
                    mAnimDialog = AnimDialog.showDialog(this);

                mAnimDialog.show();

                String time = String.valueOf(System.currentTimeMillis());
                String random = ApiUtils.getRandom();

                OkGo.post(ApiUtils.postBankSendSms())
                        .params("user_name", UserUtils.getUserName(this))
                        .params("login_token", UserUtils.getLoginToken(this))
                        .params("t", time)
                        .params("sign", NormalUtils.md5(time + random + MyApplication.key))
                        .params("random", random)
                        .params("bank_phone_num", phone)
                        .params("account_number", account_no)
                        .params("bank_id", bank_selected.getBank_id())
                        .params("real_name", et_name.getText().toString())
                        .params("id_card_number", id_num)
                        .params("provinceno", one.getProvinceno())
                        .params("cityno", two.getCityno())
                        .execute(new StringCallback() {

                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                SmSBean bean = ParseUtils.parseByGson(s, SmSBean.class);
                                if (bean.isSuccess()) {
                                    CountDownTimerUtil mCountDownTimerUtils =
                                            new CountDownTimerUtil(btn_get_msg_code
                                                    , 60000, 1000);
                                    mCountDownTimerUtils.start();
                                } else {
                                    NormalUtils.showToast(OpenFundActivity.this,
                                            bean.getMsg());
                                }

                                order_id = bean.getOrder_id();
                                mAnimDialog.dismiss();

                            }

                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                super.onError(call, response, e);
                                NormalUtils.showToast(OpenFundActivity.this,
                                        R.string.net_error);
                                mAnimDialog.dismiss();
                            }
                        });
                break;
            case R.id.btn_ok:
                String msg_code = et_msg_code.getText().toString().trim();
                if (TextUtils.isEmpty(msg_code)) {
                    NormalUtils.showToast(this, "请输入验证码");
                    break;
                }

                if (mAnimDialog == null)
                    mAnimDialog = AnimDialog.showDialog(this);

                mAnimDialog.show();

                String t = String.valueOf(System.currentTimeMillis());
                String r = ApiUtils.getRandom();
                OkGo.post(ApiUtils.postAddBank())
                        .params("t", t)
                        .params("random", r)
                        .params("sign", NormalUtils.md5(t + r + MyApplication.key))
                        .params("user_name", UserUtils.getUserName(this))
                        .params("login_token", UserUtils.getLoginToken(this))
                        .params("verify_code", msg_code)
                        .params("order_id", order_id)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                JSONObject gson = null;
                                try {
                                    gson = new JSONObject(s);
                                    if (gson.getBoolean("success")) {
                                        NormalUtils.showToast(OpenFundActivity.this, "开户成功");
                                        startToInvest();

                                    } else {
                                        NormalUtils.showToast(OpenFundActivity.this,
                                                gson.getString("msg"));
                                    }
                                    mAnimDialog.dismiss();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }

                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                super.onError(call, response, e);
                                NormalUtils.showToast(OpenFundActivity.this,
                                        R.string.net_error);
                                mAnimDialog.dismiss();
                            }
                        });


                break;
            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                include_load_error.setVisibility(View.GONE);
                bindData(this);
                break;
            case R.id.iv_back_my_account:
                finish();
                break;
            case R.id.tv_bank:
                showBankChoose();
                break;
            case R.id.tv_province:
                showCityChoose();
                break;
        }
    }

    public void startToInvest() {
        //跳转
        Bundle args = new Bundle();
        args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, fund_id);
        startActivity(FundPurchaseActivity.class, args);
        finish();
    }

    private AddOpenFundInfoDialog mInfoDialog;
    private AddOpenFundCardDialog mCardDialog;

    private OpenFundAccountBean.ProvinceListBean one;
    private OpenFundAccountBean.ProvinceListBean.CitysBean two;
    private OpenFundAccountBean.BanksBean bank_selected;

    private void showCityChoose() {
        if (mInfoDialog == null) {
            mInfoDialog = AddOpenFundInfoDialog.newInstance(infoProvince);
        }
        mInfoDialog.show(getSupportFragmentManager(), "mInfoDialog");
    }

    private void showBankChoose() {
        if (mCardDialog == null) {
            mCardDialog = AddOpenFundCardDialog.newInstance(infoBanks);
        }
        mCardDialog.show(getSupportFragmentManager(), "mCardDialog");
    }

    /**
     * 选择完毕
     *
     * @param one           省
     * @param two           市
     * @param bank_selected 选择的银行
     */
    public void setResultFinish(OpenFundAccountBean.ProvinceListBean one,
                                OpenFundAccountBean.ProvinceListBean.CitysBean two,
                                OpenFundAccountBean.BanksBean bank_selected) {

        if (one != null) {
            this.one = one;
            tv_province.setText(one.getName());
        }
        if (two != null) {
            this.two = two;
            tv_province.append("  " + two.getName());
        }
        if (bank_selected != null) {
            this.bank_selected = bank_selected;
            Glide.with(getApplicationContext()).load(bank_selected.getBank_logo()).into(new SimpleTarget<GlideDrawable>() {
                @Override
                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                    tv_bank.setCompoundDrawablesWithIntrinsicBounds(
                            resource, null, getResources().getDrawable(R.mipmap.ic_triangle), null);
                }
            });
            tv_bank.setText(bank_selected.getBank_name());
        }
    }

    private class MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            isReady = true;

            if (info != null) {
                if (isEmpty(et_name.getText())
                        || isEmpty(et_id_num.getText())
                        || et_id_num.getText().length() != 18
                        || isEmpty(et_bank_num.getText())
                        || bank_selected == null) {
                    isReady = false;
                }

                if (isEmpty(et_msg_code.getText())) {
                    isReady = false;
                }

                if ((one == null
                        || two == null)) {
                    isReady = false;
                }

                if ((isEmpty(et_phone.getText())
                        || et_phone.getText().length() != 11))
                    isReady = false;


            } else
                isReady = false;


            btn_ok.setEnabled(isReady && isChecked);

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    class Clickable extends ClickableSpan {
        private final View.OnClickListener mListener;

        public Clickable(View.OnClickListener l) {
            mListener = l;
        }

        /**
         * 重写父类点击事件
         */
        @Override
        public void onClick(View v) {
            mListener.onClick(v);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false);// 设置文字下划线不显示
            ds.setColor(Color.parseColor("#4a8fec"));// 设置字体颜色
            ds.clearShadowLayer();
        }
    }
}
