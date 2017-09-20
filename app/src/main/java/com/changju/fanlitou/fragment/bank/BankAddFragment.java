package com.changju.fanlitou.fragment.bank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.NumberKeyListener;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.mine.ManagerBankCardActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.MyApplication;
import com.changju.fanlitou.bean.LoginBean;
import com.changju.fanlitou.bean.mine.AddBankInfo;
import com.changju.fanlitou.ui.dialog.AddBankCardDialog;
import com.changju.fanlitou.ui.dialog.AddBankInfoDialog;
import com.changju.fanlitou.ui.dialog.AnimDialog;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.changju.fanlitou.util.UserUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chengww on 2017/5/31.
 */

public class BankAddFragment extends LazyFragment {

    private static AddBankInfo mBankInfo;

    private boolean isChange;

    //省份选择
//    private AddBankInfo.ProvinceBean one;

    //城市选择
//    private AddBankInfo.ProvinceBean.CityBean two;

    //银行选择
    private AddBankInfo.BanksBean bank_selected;

    private String card_no;

    public static BankAddFragment newInstance(boolean isChange) {

        Bundle args = new Bundle();
        args.putBoolean(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, false);
        BankAddFragment fragment = new BankAddFragment();
        fragment.setArguments(args);
        fragment.isChange = isChange;
        return fragment;
    }

    private EditText et_name, et_bank_num, et_id_num, tv_phone;

    private View include;
    private View include_load_error;

    private LinearLayout layout_real, layout_id_num;

    private TextView tv_province, tv_bank;

    @Override
    protected void initView() {

        GrowingIO.getInstance().setPageName(this, "我的-银行卡管理：无卡绑定");

        et_name = (EditText) findViewById(R.id.et_name);
        et_bank_num = (EditText) findViewById(R.id.et_bank_num);
        et_id_num = (EditText) findViewById(R.id.et_id_num);

        et_id_num.setKeyListener(new NumberKeyListener() {
            @Override
            public int getInputType() {
                return InputType.TYPE_CLASS_NUMBER;
            }

            protected char[] getAcceptedChars() {
                return new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'x', 'X'};
            }
        });

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
            public void onTextChanged(CharSequence s,
                                      int start, int before, int count) {
                onTextLength = s.length();
                buffer.append(s.toString());
                if (onTextLength == beforeTextLength || onTextLength <= 3 || isChanged) {
                    isChanged = false;
                    return;
                }
                isChanged = true;
            }

            @Override
            public void beforeTextChanged(CharSequence s,
                                          int start, int count, int after) {
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

        tv_phone = (EditText)

                findViewById(R.id.tv_phone);
//        tv_province = (TextView) findViewById(R.id.tv_province);
//        tv_province.setOnClickListener(this);
        tv_bank = (TextView)

                findViewById(R.id.tv_bank);
        tv_bank.setOnClickListener(this);

        include =

                findViewById(R.id.include_loading);
        include.setVisibility(View.VISIBLE);
        include_load_error =

                findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);

        layout_real = (LinearLayout)

                findViewById(R.id.layout_real);
        layout_real.setVisibility(View.GONE);

        layout_id_num = (LinearLayout)

                findViewById(R.id.layout_id_num);

        findViewById(R.id.btn_ok).

                setOnClickListener(this);
    }

    @Override
    protected void doBusiness(Context context) {
        OkGo.get(ApiUtils.getAddBankInfo(context))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        mBankInfo = ParseUtils.parseByGson(s, AddBankInfo.class);
                        if (mBankInfo != null) {
                            //已实名
                            if (mBankInfo.isIs_verified()) {
                                et_name.setText(mBankInfo.getReal_name());
                                et_name.setEnabled(false);
                                layout_id_num.setVisibility(View.GONE);
                            }

                            //已绑卡
                            if (mBankInfo.isIs_bound() && !isChange) {
                                //****
                                card_no = mBankInfo.getCard_num();
                                if (!TextUtils.isEmpty(card_no) && card_no.length() > 8) {
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
                                        if (!TextUtils.isEmpty(card_no)) {
                                            card_no = "";
                                            et_bank_num.setText("");
                                        }
                                    }

                                    @Override
                                    public void afterTextChanged(Editable s) {

                                    }
                                });

                                et_bank_num.setEnabled(false);

                                List<AddBankInfo.BanksBean> banks = mBankInfo.getBanks();

                                if (banks != null && banks.size() > 0) {
                                    setResultFinish(null, null, banks.get(0));
                                    tv_bank.setEnabled(false);
                                    tv_bank.setCompoundDrawables(null, null, null, null);
                                }
                            }


                            if (!TextUtils.isEmpty(mBankInfo.getPhone_num())) {
                                tv_phone.setText(mBankInfo.getPhone_num());
                            }

                        }

                        layout_real.setVisibility(View.VISIBLE);
                        include.setVisibility(View.GONE);
                        include_load_error.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        NormalUtils.showToast(getActivity(),
                                R.string.net_error);
                        layout_real.setVisibility(View.GONE);
                        include.setVisibility(View.GONE);
                        include_load_error.setVisibility(View.VISIBLE);
                    }
                });
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_bank_add;
    }

    private AnimDialog mAnimDialog;

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                doBusiness(getActivity());
                break;
            case R.id.tv_province:
                showCityChoose();
                break;
            case R.id.tv_bank:
                showBankChoose();
                break;
            case R.id.btn_ok:
                if (checkInput()) {
                    if (mAnimDialog == null)
                        mAnimDialog = AnimDialog.showDialog(getActivity());
                    mAnimDialog.show();

                    String time = String.valueOf(System.currentTimeMillis());
                    String random = ApiUtils.getRandom();

                    OkGo.post(ApiUtils.postAddBankCard())
                            .params("name", name)
                            .params("id_card_number", id_card_num)
                            .params("bank_phone_num", phone_num)
                            .params("account_number", TextUtils.isEmpty(card_no) ? et_bank_num.getText().toString().replaceAll(" ", "") : card_no)
                            .params("bank_id", bank_selected.getCode())
                            .params("province_name", "")
                            .params("province_id", 0)
                            .params("city_name", "")
                            .params("city_id", 0)
//                            .params("province_name", one.getName())
//                            .params("province_id", one.getId())
//                            .params("city_name", two.getName())
//                            .params("city_id", two.getId())
                            .params("t", time)
                            .params("random", random)
                            .params("sign", NormalUtils.md5(time +
                                    random + MyApplication.key))
                            .params("user_name", UserUtils.getUserName(getActivity()))
                            .params("login_token", UserUtils.getLoginToken(getActivity()))
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    LoginBean bean = ParseUtils.parseByGson(s, LoginBean.class);
                                    if (bean.isSuccess()) {
                                        NormalUtils.showToast(getActivity(), bean.getMsg());
                                        getActivity().startActivity(new Intent(getActivity(),
                                                ManagerBankCardActivity.class));
                                        getActivity().finish();
                                    } else {
                                        NormalUtils.showToast(getActivity(), bean.getMsg());
                                    }
                                    mAnimDialog.dismiss();
                                }

                                @Override
                                public void onError(Call call, Response response, Exception e) {
                                    super.onError(call, response, e);
                                    mAnimDialog.dismiss();
                                    NormalUtils.showToast(getActivity(), R.string.net_error);
                                }
                            });
                }
                break;
        }

    }

    private String name, id_card_num, bank_num, phone_num;

    /**
     * 检查用户输入
     *
     * @return
     */

    private boolean checkInput() {
        //name
        name = et_name.getText().toString();
        if (TextUtils.isEmpty(name)) {
            NormalUtils.showToast(getActivity(), "持卡人姓名不能为空!");
            return false;
        }

        //身份证
        if (layout_id_num.getVisibility() == View.VISIBLE) {
            id_card_num = et_id_num.getText().toString();
            if (TextUtils.isEmpty(id_card_num) ||
                    et_id_num.getText().toString().length() < 15) {
                NormalUtils.showToast(getActivity(), "身份证号有误!");
                return false;
            }
        } else {
            id_card_num = mBankInfo.getId_number();
        }

        //银行卡
        bank_num = et_bank_num.getText().toString();
        if (TextUtils.isEmpty(card_no) && (TextUtils.isEmpty(bank_num) ||
                bank_num.length() < 16)) {
            NormalUtils.showToast(getActivity(), "银行卡号有误!");
            return false;
        }


        //开户行
        if (bank_selected == null) {
            NormalUtils.showToast(getActivity(), "请选择开户行!");
            return false;
        }

        //省份
//        if (one == null) {
//            NormalUtils.showToast(getActivity(), "请选择省份!");
//            return false;
//        }

        //城市
//        if (two == null) {
//            NormalUtils.showToast(getActivity(), "请选择城市!");
//            return false;
//        }

        //手机号
        phone_num = tv_phone.getText().toString();
        if (TextUtils.isEmpty(phone_num) ||
                phone_num.length() < 11) {
            NormalUtils.showToast(getActivity(), "手机号有误!");
            return false;
        }

        return true;

    }

    private AddBankInfoDialog mInfoDialog;
    private AddBankCardDialog mCardDialog;

    private void showCityChoose() {
        if (mInfoDialog == null) {
            mInfoDialog = AddBankInfoDialog.newInstance(mBankInfo);
        }
        mInfoDialog.show(getChildFragmentManager(), "mInfoDialog");
    }

    private void showBankChoose() {
        if (mCardDialog == null) {
            mCardDialog = AddBankCardDialog.newInstance(mBankInfo);
        }
        mCardDialog.show(getChildFragmentManager(), "mCardDialog");
    }

    /**
     * 选择完毕
     *
     * @param one           省
     * @param two           市
     * @param bank_selected 选择的银行
     */
    public void setResultFinish(AddBankInfo.ProvinceBean one,
                                AddBankInfo.ProvinceBean.CityBean two,
                                AddBankInfo.BanksBean bank_selected) {

//        if (one != null) {
//            this.one = one;
//            tv_province.setText(one.getName());
//        }
//        if (two != null) {
//            this.two = two;
//            tv_province.append("  " + two.getName());
//        }
        if (bank_selected != null) {
            this.bank_selected = bank_selected;
            Glide.with(getApplicationContext()).load(bank_selected.getLogo()).into(new SimpleTarget<GlideDrawable>() {
                @Override
                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                    tv_bank.setCompoundDrawablesWithIntrinsicBounds(
                            resource, null, getResources().getDrawable(R.mipmap.ic_triangle), null);
                }
            });
            tv_bank.setText(bank_selected.getName());
        }
    }
}
