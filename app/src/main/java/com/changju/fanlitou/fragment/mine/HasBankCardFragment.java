package com.changju.fanlitou.fragment.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.mine.ChangeBankActivity;
import com.changju.fanlitou.activity.mine.EnchashmentActivity;
import com.changju.fanlitou.activity.mine.FanlibaoActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.MyApplication;
import com.changju.fanlitou.bean.LoginBean;
import com.changju.fanlitou.bean.mine.WithDrawInfo;
import com.changju.fanlitou.ui.dialog.AnimDialog;
import com.changju.fanlitou.ui.dialog.JifenDialogFragment;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.CountDownTimerUtil;
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
 * Created by chengww on 2017/5/26.
 */

public class HasBankCardFragment extends LazyFragment {

    private WithDrawInfo withDrawInfo;
    private float mCash = 0;


    public static HasBankCardFragment newInstance(WithDrawInfo withDrawInfo) {

        Bundle args = new Bundle();
        args.putBoolean(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, false);
        HasBankCardFragment fragment = new HasBankCardFragment();
        fragment.setArguments(args);
        fragment.withDrawInfo = withDrawInfo;

        return fragment;
    }

    private EditText et_txje, et_msg_code;

    private TextView btn_get_msg_code;

    private TextView tv_sjdz, tv_phone;

    private float txje = 0;
    //手续费
    private float fee = 0;

    @Override
    protected void initView() {

        GrowingIO.getInstance().setPageName(this, "我的-提现：有卡可提现");

        findViewById(R.id.tv_change).setOnClickListener(this);
        findViewById(R.id.btn_qbtx).setOnClickListener(this);
        findViewById(R.id.btn_tx).setOnClickListener(this);
        et_txje = (EditText) findViewById(R.id.et_txje);
        et_msg_code = (EditText) findViewById(R.id.et_msg_code);
        btn_get_msg_code = (TextView) findViewById(R.id.btn_get_msg_code);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        btn_get_msg_code.setOnClickListener(this);
        tv_sjdz = (TextView) findViewById(R.id.tv_sjdz);
        et_txje.addTextChangedListener(new TextWatcher() {
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
                        et_txje.setText(s);
                        et_txje.setSelection(s.length());
                    }
                }
                if (str.trim().substring(0).equals(".")) {
                    s = "0" + s;
                    et_txje.setText(s);
                    et_txje.setSelection(2);
                }
                if (str.startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        et_txje.setText(s.subSequence(0, 1));
                        et_txje.setSelection(1);
                        return;
                    }
                }

                if (TextUtils.isEmpty(s.toString())) {
                    tv_sjdz.setText("0");
                } else {
                    txje = Float.valueOf(s.toString());
                    if (txje - fee >= 0) {
                        tv_sjdz.setText(NormalUtils.bigFloat2String((txje - fee)));
                    } else {
                        tv_sjdz.setText("0");
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void doBusiness(Context context) {
        ImageView bank_logo_app = (ImageView) findViewById(R.id.bank_logo_app);
        TextView platform_name = (TextView) findViewById(R.id.platform_name);
        TextView tv_content = (TextView) findViewById(R.id.tv_content);
        TextView tv_kyye = (TextView) findViewById(R.id.tv_kyye);
//        TextView tv_hint = (TextView) findViewById(R.id.tv_hint);

        if (withDrawInfo != null) {
            WithDrawInfo.WithdrawInfoBean bean = withDrawInfo.getWithdraw_info();
            if (bean.is_show_jifen_alert()) {
                Bundle args = new Bundle();
                args.putString(JifenDialogFragment.YEAR_YLD, bean.getYear_yld());
                JifenDialogFragment dialog = new JifenDialogFragment();
                dialog.setArguments(args);
                dialog.show(getActivity().getSupportFragmentManager(), "jifen");
            }
            //银行信息
            List<WithDrawInfo.WithdrawInfoBean.BankAccountsBean> accounts =
                    bean.getBank_accounts();

            Glide.with(getApplicationContext()).load(accounts.
                    get(0).getBank_logo()).into(bank_logo_app);

            platform_name.setText(accounts.get(0).getName());

            tv_content.setText("尾号" + accounts.get(0).getNumber());

            String userName = UserUtils.getUserName(getActivity());
            tv_phone.setText(userName.substring(0, 3)
                    + "****" + userName.substring(7, userName.length()));
            //可用余额
            mCash = Float.parseFloat(bean.getAvailable_cash());
            tv_kyye.setText(bean.getAvailable_cash());

            //手续费
            TextView tv_fee = (TextView) findViewById(R.id.tv_fee);
            fee = Float.parseFloat(bean.getFee());
            if (fee > 0) {
                tv_fee.setText("手续费:" + bean.getFee() + "元");
            } else {
                if (bean.getWcount() > 3) {
                    tv_fee.setText("限免手续费");
                } else {
                    tv_fee.setText("免手续费");
                }
            }

//            tv_hint.setText("每月前" + (bean.getWcount() +
//                    bean.getFree_withdraw_count()) +
//                    "笔提现免收手续费，您本月已提现" +
//                    +bean.getWcount() + "笔");

        }
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_has_bank_card;
    }

    private AnimDialog mAnimDialog;

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.tv_change:
                EnchashmentActivity activity = (EnchashmentActivity) getActivity();
                activity.startActivity(ChangeBankActivity.class);
                break;
            //全部提现
            case R.id.btn_qbtx:
                et_txje.setText(String.valueOf(mCash));
                et_txje.setSelection(et_txje.getText().length());
                break;
            case R.id.btn_get_msg_code:
                if (mAnimDialog == null)
                    mAnimDialog = AnimDialog.showDialog(getActivity());
                mAnimDialog.show();
                OkGo.get(ApiUtils.getSendSms(
                        withDrawInfo.getWithdraw_info().getPhone(),
                        "withdraw"))
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                LoginBean bean = ParseUtils.parseByGson(s, LoginBean.class);
                                if (bean.isSuccess()) {
                                    CountDownTimerUtil mCountDownTimerUtils =
                                            new CountDownTimerUtil(btn_get_msg_code
                                                    , 60000, 1000);
                                    mCountDownTimerUtils.start();
                                } else {
                                    NormalUtils.showToast(HasBankCardFragment.this.getActivity(),
                                            bean.getMsg());
                                }
                                mAnimDialog.dismiss();

                            }

                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                super.onError(call, response, e);
                                NormalUtils.showToast(HasBankCardFragment.this.
                                                getActivity(),
                                        R.string.net_error);
                                mAnimDialog.dismiss();
                            }
                        });

                break;
            //提现
            case R.id.btn_tx:
                if (checkInput()) {
                    if (mAnimDialog == null)
                        mAnimDialog = AnimDialog.showDialog(getActivity());
                    mAnimDialog.show();

                    String time = String.valueOf(System.currentTimeMillis());
                    String random = ApiUtils.getRandom();

                    if (checkInput()) {
                        OkGo.post(ApiUtils.postWithDraw())
                                .params("user_name", UserUtils.getUserName(getActivity()))
                                .params("withdraw_amount", withdraw_amount)
                                .params("card", withDrawInfo.getWithdraw_info().
                                                getBank_accounts().get(0).getId())
                                .params("verify_code", msg_code_int)
                                .params("t", time)
                                .params("random", random)
                                .params("sign", NormalUtils.md5(time +
                                        random + MyApplication.key))
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(String s, Call call, Response response) {
                                        LoginBean bean = ParseUtils.parseByGson(s, LoginBean.class);
                                        if (bean.isSuccess()) {
                                            NormalUtils.showToast(getActivity(), bean.getMsg());
                                            btn_get_msg_code.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    if (isVisible()){
                                                        Bundle args = new Bundle();
                                                        args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, 1);
                                                        Intent intent = new Intent(getActivity(),
                                                                FanlibaoActivity.class);
                                                        intent.putExtras(args);
                                                        getActivity().startActivity(intent);
                                                        getActivity().finish();
                                                    }
                                                }
                                            },1000);

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
                }
                break;
        }
    }

    //提现金额
    private float withdraw_amount;
    //短信验证码
    private int msg_code_int;

    private boolean checkInput() {
        String txje = et_txje.getText().toString();
        if (TextUtils.isEmpty(txje)) {
            NormalUtils.showToast(getActivity(), "请输入提现金额!");
            return false;
        }

        withdraw_amount = Float.valueOf(txje);
        if (withdraw_amount < 50) {
            NormalUtils.showToast(getActivity(), "单笔提现需满50元");
            return false;
        }

        if (withdraw_amount > mCash) {
            NormalUtils.showToast(getActivity(), "提现金额不能超过可用余额!");
            return false;
        }

        String msg_code = et_msg_code.getText().toString();

        if (TextUtils.isEmpty(msg_code)) {
            NormalUtils.showToast(getActivity(), "请输入短信验证码!");
            return false;
        }

        msg_code_int = Integer.valueOf(msg_code);

        return true;
    }
}
