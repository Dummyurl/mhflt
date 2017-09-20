package com.changju.fanlitou.activity.intelligent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.method.NumberKeyListener;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.WebActivity;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.JavascriptClass;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.MyApplication;
import com.changju.fanlitou.bean.intelligent.OpenAccountInfo;
import com.changju.fanlitou.bean.intelligent.SMS;
import com.changju.fanlitou.ui.EditTextWithDel;
import com.changju.fanlitou.ui.dialog.AddIntelligentBankCardDialog;
import com.changju.fanlitou.ui.dialog.AddIntelligentBankInfoDialog;
import com.changju.fanlitou.ui.dialog.AgreementDialog;
import com.changju.fanlitou.ui.dialog.AnimDialog;
import com.changju.fanlitou.ui.dialog.RechargeBankStorageDialog;
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

public class OpenAccountActivity extends BaseActivity {

    private EditTextWithDel et_name, et_id_num, et_bank_num,
            et_bank_name, et_phone, et_msg_code;
    private TextView tv_bank, tv_province, tv_phone, tv_change_phone;
    private CheckBox check_box;

    private FrameLayout layout_agreement;

    //loading&error
    private View include;
    private View include_load_error;

    private int bid_id;
    private OpenAccountInfo info;
    private List<OpenAccountInfo.BankListBean> infoBanks;
    private List<OpenAccountInfo.ProvinceListBean> infoProvince;
    private TextView btn_ok, tv_agreement_info;

    private MyTextWatcher watcher;
    private boolean isReady;
    private boolean isChecked = true;
    private TextView btn_get_msg_code, tv_top_hint;
    private String phone;
    private String sms_auth_code;
    private String bank_branch_name;
    private String card_no;
    private String id_no;
    private RechargeBankStorageDialog rechargeBankStorageDialog;

    @Override
    public void initParams(Bundle params) {
        bid_id = params.getInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_open_account;
    }

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "智能投顾-开户");

        include = findViewById(R.id.include_loading);
        include.setVisibility(View.VISIBLE);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);

        findViewById(R.id.iv_back_my_account).setOnClickListener(this);

        tv_top_hint = (TextView) findViewById(R.id.tv_top_hint);
        tv_agreement_info = (TextView) findViewById(R.id.tv_agreement_info);

        watcher = new MyTextWatcher();

        et_name = (EditTextWithDel) findViewById(R.id.et_name);
        et_id_num = (EditTextWithDel) findViewById(R.id.et_id_num);
        et_bank_num = (EditTextWithDel) findViewById(R.id.et_bank_num);
        et_bank_name = (EditTextWithDel) findViewById(R.id.et_bank_name);
        et_phone = (EditTextWithDel) findViewById(R.id.et_phone);
        et_msg_code = (EditTextWithDel) findViewById(R.id.et_msg_code);

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
        et_bank_name.addTextChangedListener(watcher);
        et_phone.addTextChangedListener(watcher);
        et_msg_code.addTextChangedListener(watcher);

        tv_bank = (TextView) findViewById(R.id.tv_bank);
        tv_bank.setOnClickListener(this);
        tv_province = (TextView) findViewById(R.id.tv_province);
        tv_province.setOnClickListener(this);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_change_phone = (TextView) findViewById(R.id.tv_change_phone);
        tv_change_phone.setOnClickListener(this);

        tv_bank.addTextChangedListener(watcher);
        tv_province.addTextChangedListener(watcher);
        tv_phone.addTextChangedListener(watcher);

        btn_get_msg_code = (TextView) findViewById(R.id.btn_get_msg_code);
        btn_get_msg_code.setOnClickListener(this);
        btn_ok = (TextView) findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(this);
        btn_ok.setEnabled(false);

        layout_agreement = (FrameLayout) findViewById(R.id.layout_agreement);

        check_box = (CheckBox) findViewById(R.id.check_box);
        check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                OpenAccountActivity.this.isChecked = isChecked
                        || check_box.getVisibility() != View.VISIBLE;
                btn_ok.setEnabled(isChecked && OpenAccountActivity.this.isReady);
            }
        });

    }

    @Override
    public void doBusiness(final Context mContext) {
        bindData(mContext);
    }

    private void bindData(final Context mContext) {
        OkGo.get(ApiUtils.getOpeanAccountInfo(mContext, bid_id))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        info = ParseUtils.parseByGson(s, OpenAccountInfo.class);
                        infoBanks = info.getBank_list();
                        infoProvince = info.getProvince_list();

                        if (!info.isIs_need_bank_branch_name())
                            findViewById(R.id.layout_bank_name).setVisibility(View.GONE);

                        if (!info.isIs_need_bank_phone_number())
                            findViewById(R.id.layout_phone_number).setVisibility(View.GONE);
                        else {
                            findViewById(R.id.layout_phone_number).setVisibility(View.VISIBLE);
                            if (TextUtils.isEmpty(info.getBank_phone_number())) {
                                et_phone.setVisibility(View.VISIBLE);
                                tv_change_phone.setVisibility(View.GONE);
                                tv_phone.setVisibility(View.GONE);
                            } else {
                                tv_phone.setText(info.getBank_phone_number());
                            }

                        }

                        if (!info.isIs_need_city_province())
                            findViewById(R.id.layout_province).setVisibility(View.GONE);

                        if (!info.isIs_need_openaccount_sms_code())
                            findViewById(R.id.layout_msg_code).setVisibility(View.GONE);

                        //实名认证
                        OpenAccountInfo.UserVerifyInfoBean verify =
                                info.getUser_verify_info();
                        if (verify.isIs_verified()) {
                            et_name.setText(verify.getVerified_info().getName());

                            id_no = verify.getVerified_info().getId_number();
                            if (!TextUtils.isEmpty(id_no) && id_no.length() > 8) {
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

                            //****
                            et_id_num.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                    if (!TextUtils.isEmpty(id_no)) {
                                        id_no = "";
                                        et_id_num.setText("");
                                    }
                                }

                                @Override
                                public void afterTextChanged(Editable s) {

                                }
                            });
                        }

                        //银行信息
                        OpenAccountInfo.UserFltBoundBankInfoBean bank =
                                info.getUser_flt_bound_bank_info();
                        //已绑卡
                        if (bank.isIs_already_bound_flt()) {
                            OpenAccountInfo.UserFltBoundBankInfoBean.BoundBankInfoBean boundBank
                                    = bank.getBound_bank_info();

                            if (boundBank != null
                                    && boundBank.getBank_id() > 0
                                    && !TextUtils.isEmpty(boundBank.getBank_name())) {
                                bank_selected = new OpenAccountInfo.BankListBean();
                                bank_selected.setBank_logo(boundBank.getBank_logo());
                                bank_selected.setBank_id(boundBank.getBank_id());
                                bank_selected.setBank_name(boundBank.getBank_name());

                                setResultFinish(null, null, bank_selected);

                                //****
                                card_no = boundBank.getCard_no();
                                if (!TextUtils.isEmpty(card_no) && card_no.length() > 8) {
                                    int len = card_no.length();
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(card_no.substring(0, 4));
                                    for (int i = 0; i < len - 8; i++) {
                                        sb.append("*");
                                    }
                                    sb.append(card_no.substring(len - 4));
                                    et_bank_num.setText(sb);

                                    //顶部提示信息消失
                                    tv_top_hint.setVisibility(View.GONE);
                                } else {
                                    et_bank_num.setText(card_no);
                                    //已绑卡，卡信息为空--顶部提示信息--
                                    tv_top_hint.setVisibility(View.VISIBLE);
                                    tv_top_hint.setText("您绑定的银行暂不支持开户，需重新绑定");
                                }

                                //****
                                et_bank_num.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                    }

                                    @Override
                                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                                        if (!TextUtils.isEmpty(card_no)) {
                                            card_no = "";
                                            et_bank_num.setText("");
                                        }
                                    }

                                    @Override
                                    public void afterTextChanged(Editable s) {

                                    }
                                });
                            } else {
                                //bundbank == null
                                //已绑卡，卡信息为空--顶部提示信息--
                                tv_top_hint.setVisibility(View.VISIBLE);
                                tv_top_hint.setText("您绑定的银行暂不支持开户，需重新绑定");
                            }

                        } else {
                            //顶部提示信息
                            tv_top_hint.setVisibility(View.VISIBLE);
                            tv_top_hint.setText("请确定绑定的银行卡为本人的储蓄卡");
                        }

                        //开户地区
                        OpenAccountInfo.UserProvinceInfoBean province =
                                info.getUser_province_info();
                        if (province.isIs_has_province_city()) {
                            OpenAccountInfo.UserProvinceInfoBean.ProvinceInfoBean bean =
                                    province.getProvince_info();
                            one = new OpenAccountInfo.ProvinceListBean();
                            two = new OpenAccountInfo.ProvinceListBean.CityBean();

                            one.setId(bean.getId());
                            one.setName(bean.getName());

                            two.setId(bean.getCity().getId());
                            two.setName(bean.getCity().getName());

                            setResultFinish(one, two, null);
                        }

                        OpenAccountInfo.UserAgreementInfoBean agreement = info.getUser_agreement_info();
                        List<OpenAccountInfo.UserAgreementInfoBean.AgreementListBean> agreements =
                                agreement.getAgreement_list();

                        if (agreements != null && agreements.size() > 0) {
                            layout_agreement.setVisibility(View.VISIBLE);
                            StringBuilder sb = new StringBuilder("        同意");
                            for (int i = 0; i < agreements.size(); i++) {
                                OpenAccountInfo.UserAgreementInfoBean.AgreementListBean agree = agreements.get(i);
                                String title = agree.getTitle();
                                sb.append("《");
                                sb.append(title);
                                sb.append("》");
                            }

                            SpannableString spanableInfo = new SpannableString(sb.toString());
                            ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(android.R.color.transparent));
                            spanableInfo.setSpan(colorSpan, 0, 10, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                            for (int i = 0; i < agreements.size(); i++) {

                                final OpenAccountInfo.UserAgreementInfoBean.AgreementListBean agree =
                                        agreements.get(i);
                                final String title = agree.getTitle();
                                spanableInfo.setSpan(new TextClickable(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Bundle args = new Bundle();
                                                args.putString(AgreementDialog.TITLE, agree.getTitle());
                                                args.putString(AgreementDialog.CONTENT, agree.getContent());
                                                AgreementDialog dialog = new AgreementDialog();
                                                dialog.setArguments(args);
                                                dialog.show(getSupportFragmentManager(), "agree");
                                            }
                                        }), sb.indexOf(title) - 1, sb.indexOf(title) + title.length() + 1, //设置需要监听的字符串位置
                                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            }
                            tv_agreement_info.setText(spanableInfo);  //将处理过的数据set到View里
                            tv_agreement_info.setMovementMethod(LinkMovementMethod.getInstance());
                            tv_agreement_info.setHighlightColor(getResources().getColor(android.R.color.transparent));

                        } else {
                            layout_agreement.setVisibility(View.GONE);
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
            case R.id.btn_get_msg_code:

                if (info != null) {
                    if (TextUtils.isEmpty(et_name.getText())) {
                        NormalUtils.showToast(this, "请输入真实姓名");
                        break;
                    }

                    if (TextUtils.isEmpty(et_id_num.getText())) {
                        NormalUtils.showToast(this, "请输入身份证号");
                        break;
                    }

                    if (TextUtils.isEmpty(et_bank_num.getText())) {
                        NormalUtils.showToast(this, "请输入银行卡号");
                        break;
                    }

                    if (bank_selected == null) {
                        NormalUtils.showToast(this, "请选择开户银行");
                        break;
                    }

                    if (info.isIs_need_city_province() && (one == null
                            || two == null)) {
                        NormalUtils.showToast(this, "请选择开户地区");
                        break;
                    }

                    if (info.isIs_need_bank_phone_number()) {
                        if (tv_phone.getVisibility() == View.VISIBLE
                                && TextUtils.isEmpty(tv_phone.getText())) {
                            NormalUtils.showToast(this, "请输入银行手机号");
                            break;
                        }

                        if (et_phone.getVisibility() == View.VISIBLE
                                && (TextUtils.isEmpty(et_phone.getText())
                                || et_phone.getText().length() != 11)) {
                            NormalUtils.showToast(this, "请输入11位银行手机号");
                            break;
                        }

                    }

                    if (info.isIs_need_bank_branch_name()
                            && TextUtils.isEmpty(et_bank_name.getText())) {
                        NormalUtils.showToast(this, "请输入支行名称");
                    }

                    if (!(isChecked || check_box.getVisibility() != View.VISIBLE)) {
                        NormalUtils.showToast(this, "请阅读并同意相关协议");
                        break;
                    }

                    //all check pass.link to internet
                    if (mAnimDialog == null)
                        mAnimDialog = AnimDialog.showDialog(this);
                    mAnimDialog.show();

                    updateParams();

                    String time = String.valueOf(System.currentTimeMillis());
                    String random = ApiUtils.getRandom();

                    OkGo.post(ApiUtils.postIntelligentSMS())
                            .params("t", time)
                            .params("random", random)
                            .params("sign", NormalUtils.md5(time + random + MyApplication.key))
                            .params("platform_id", info.getPlatform_id())
                            .params("user_name", UserUtils.getUserName(this))
                            .params("bid_id", bid_id)
                            .params("real_name", et_name.getText().toString())
                            .params("id_number", TextUtils.isEmpty(id_no) ? et_id_num.getText().toString() : id_no)
                            .params("bank_phone_num", phone)
                            .params("bank_card_number", TextUtils.isEmpty(card_no) ? et_bank_num.getText().toString().replaceAll(" ", "") : card_no)
                            .params("bank_id", bank_selected.getBank_id())
                            .params("sms_auth_code", sms_auth_code)
                            .params("bank_branch_name", bank_branch_name)
                            .params("province_name", one.getName())
                            .params("province_id", one.getId())
                            .params("city_name", two.getName())
                            .params("city_id", two.getId())
                            .params("amount", 0)
                            .params("sms_type", "open_account")
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
                                    } else {
                                        NormalUtils.showToast(OpenAccountActivity.this,
                                                bean.getMsg());
                                    }
                                    mAnimDialog.dismiss();

                                }

                                @Override
                                public void onError(Call call, Response response, Exception e) {
                                    super.onError(call, response, e);
                                    NormalUtils.showToast(OpenAccountActivity.this,
                                            R.string.net_error);
                                    mAnimDialog.dismiss();
                                }
                            });
                }
                break;
            case R.id.btn_ok:

                String t = String.valueOf(System.currentTimeMillis());
                String r = ApiUtils.getRandom();

                updateParams();

                switch (info.getPlatform_bank_interface_type()) {
                    case "bank_storage":

                        Bundle args = new Bundle();
                        args.putString(LazyFragment.INTENT_BOOLEAN_LAZYLOAD
                                , ApiUtils.getBankStorageOpenAccount(OpenAccountActivity.this,
                                        bid_id, et_name.getText().toString(),
                                        et_id_num.getText().toString(), phone,
                                        et_bank_num.getText().toString().replaceAll(" ", ""),
                                        bank_selected.getBank_id(), sms_auth_code,
                                        bank_branch_name, one.getName(),
                                        one.getId(), two.getName(),
                                        two.getId()));
                        args.putString(WebActivity.TITLE, "登录银行开通存管");
                        startActivityForResult(WebActivity.class, args, JavascriptClass.REQUEST_CALL_BACK);


                        Bundle bundle = new Bundle();
                        bundle.putString(RechargeBankStorageDialog.TITLE, "登录银行开通存管");
                        bundle.putString(RechargeBankStorageDialog.CONTENT,
                                "请在新打开的网上银行页面进行操作，操作未完成之前请不要关闭该窗口");
                        bundle.putString(RechargeBankStorageDialog.POSTIVE, "开通完成");
                        bundle.putString(RechargeBankStorageDialog.TYPE, "OpenAccount");
                        bundle.putInt(RechargeBankStorageDialog.PLATFORM_ID, info.getPlatform_id());
                        rechargeBankStorageDialog = new RechargeBankStorageDialog();
                        rechargeBankStorageDialog.setArguments(bundle);
                        rechargeBankStorageDialog.show(getSupportFragmentManager(), "RechargeBankStorageDialog");

                        break;
                    case "quick_payment":

                        if (mAnimDialog == null)
                            mAnimDialog = AnimDialog.showDialog(this);
                        mAnimDialog.show();

                        OkGo.post(ApiUtils.postOpenAccount())
                                .params("t", t)
                                .params("random", r)
                                .params("sign", NormalUtils.md5(t + r + MyApplication.key))
                                .params("bid_id", bid_id)
                                .params("user_name", UserUtils.getUserName(this))
                                .params("real_name", et_name.getText().toString())
                                .params("id_number", TextUtils.isEmpty(id_no) ? et_id_num.getText().toString() : id_no)
                                .params("login_token", UserUtils.getLoginToken(this))
                                .params("bank_phone_num", phone)
                                .params("bank_card_number", TextUtils.isEmpty(card_no) ? et_bank_num.getText().toString().replaceAll(" ", "") : card_no)
                                .params("bank_id", bank_selected.getBank_id())
                                .params("sms_auth_code", sms_auth_code)
                                .params("bank_branch_name", bank_branch_name)
                                .params("province_name", one.getName())
                                .params("province_id", one.getId())
                                .params("city_name", two.getName())
                                .params("city_id", two.getId())
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(String s, Call call, Response response) {
                                        SMS bean = ParseUtils.parseByGson(s, SMS.class);
                                        if (bean.isSuccess()) {
                                            NormalUtils.showToast(OpenAccountActivity.this, "开户成功");
                                            startToInvest();

                                        } else {
                                            NormalUtils.showToast(OpenAccountActivity.this,
                                                    bean.getMsg());
                                        }
                                        mAnimDialog.dismiss();

                                    }

                                    @Override
                                    public void onError(Call call, Response response, Exception e) {
                                        super.onError(call, response, e);
                                        NormalUtils.showToast(OpenAccountActivity.this,
                                                R.string.net_error);
                                        mAnimDialog.dismiss();
                                    }
                                });
                        break;
                    default:
                        NormalUtils.showToast(OpenAccountActivity.this, "类型错误0x13");
                        break;
                }


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
            case R.id.tv_change_phone:
                et_phone.setVisibility(View.VISIBLE);
                tv_change_phone.setVisibility(View.GONE);
                tv_phone.setVisibility(View.GONE);

                btn_ok.setEnabled(false);
                break;
        }
    }

    private void updateParams() {
        if (et_phone.getVisibility() == View.VISIBLE)
            phone = et_phone.getText().toString();

        if (tv_phone.getVisibility() == View.VISIBLE)
            phone = tv_phone.getText().toString();

        if (et_msg_code.getVisibility() == View.VISIBLE)
            sms_auth_code = et_msg_code.getText().toString();

        if (et_bank_name.getVisibility() == View.VISIBLE)
            bank_branch_name = et_bank_name.getText().toString();

        if (one == null)
            one = new OpenAccountInfo.ProvinceListBean();

        if (two == null)
            two = new OpenAccountInfo.ProvinceListBean.CityBean();
    }

    public void startToInvest() {
        //跳转
        Bundle bundle = new Bundle();
        bundle.putString(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, ApiUtils.getIntelligentInvestH5(bid_id));
        bundle.putString(WebActivity.TITLE, "确认购买");
        bundle.putBoolean(WebActivity.IS_FROM_INTELLIGENT, true);
        startActivity(WebActivity.class, bundle);
        finish();
    }

    private AddIntelligentBankInfoDialog mInfoDialog;
    private AddIntelligentBankCardDialog mCardDialog;

    private OpenAccountInfo.ProvinceListBean one;
    private OpenAccountInfo.ProvinceListBean.CityBean two;
    private OpenAccountInfo.BankListBean bank_selected;

    private void showCityChoose() {
        if (mInfoDialog == null) {
            mInfoDialog = AddIntelligentBankInfoDialog.newInstance(infoProvince);
        }
        mInfoDialog.show(getSupportFragmentManager(), "mInfoDialog");
    }

    private void showBankChoose() {
        if (mCardDialog == null) {
            mCardDialog = AddIntelligentBankCardDialog.newInstance(infoBanks);
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
    public void setResultFinish(OpenAccountInfo.ProvinceListBean one,
                                OpenAccountInfo.ProvinceListBean.CityBean two,
                                OpenAccountInfo.BankListBean bank_selected) {

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
                if (TextUtils.isEmpty(et_name.getText())
                        || TextUtils.isEmpty(et_id_num.getText())
                        || TextUtils.isEmpty(et_bank_num.getText())
                        || bank_selected == null) {
                    isReady = false;
                }

                if (info.isIs_need_openaccount_sms_code()
                        && TextUtils.isEmpty(et_msg_code.getText())) {
                    isReady = false;
                }

                if (info.isIs_need_city_province() && (one == null
                        || two == null)) {
                    isReady = false;
                }

                if (info.isIs_need_bank_phone_number()) {
                    if (tv_phone.getVisibility() == View.VISIBLE
                            && TextUtils.isEmpty(tv_phone.getText()))
                        isReady = false;

                    if (et_phone.getVisibility() == View.VISIBLE
                            && (TextUtils.isEmpty(et_phone.getText())
                            || et_phone.getText().length() != 11))
                        isReady = false;

                }

                if (info.isIs_need_bank_branch_name()
                        && TextUtils.isEmpty(et_bank_name.getText())) {
                    isReady = false;
                }

            } else
                isReady = false;


            btn_ok.setEnabled(isReady && isChecked);

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case JavascriptClass.REQUEST_CALL_BACK:
                if (rechargeBankStorageDialog == null || data == null)
                    break;
                rechargeBankStorageDialog.onResult(data.getStringExtra(JavascriptClass.class.getSimpleName()));
                break;

        }
    }
}
