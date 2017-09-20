package com.changju.fanlitou.fragment.bank;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.mine.ChangeBankActivity;
import com.changju.fanlitou.activity.mine.ManagerBankCardActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.bean.mine.BankInfo;
import com.growingio.android.sdk.collection.GrowingIO;

import java.util.List;

/**
 * Created by chengww on 2017/5/31.
 */

public class BankManagerFragment extends LazyFragment {

    private List<BankInfo.BindBankListBean> bankList;

    public static BankManagerFragment newInstance(@NonNull BankInfo mBankInfo) {

        BankManagerFragment fragment = new BankManagerFragment();

        fragment.bankList = mBankInfo.getBind_bank_list();
        Bundle args = new Bundle();

        args.putBoolean(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, false);

        fragment.setArguments(args);
        return fragment;
    }

    private RelativeLayout layout_change_bank, layout_fund_bank;
    private LinearLayout layout_add_change_bank;

    @Override
    protected void initView() {

        GrowingIO.getInstance().setPageName(this, "我的-银行卡管理：已绑卡");

        layout_change_bank = (RelativeLayout) findViewById(R.id.layout_change_bank);
        layout_fund_bank = (RelativeLayout) findViewById(R.id.layout_fund_bank);
        layout_add_change_bank = (LinearLayout) findViewById(R.id.layout_add_change_bank);

        findViewById(R.id.tv_bind_bank).setOnClickListener(this);

        if (bankList != null){
            switch (bankList.size()) {
                case 1:
                    if (bankList.get(0).getCard_type().equals("fund")) {
                        layout_change_bank.setVisibility(View.GONE);
                        bindFundData(0);
                    } else {
                        layout_fund_bank.setVisibility(View.GONE);
                        layout_add_change_bank.setVisibility(View.GONE);
                        bindWithDrawData(0);
                    }
                    break;
                case 2:
                    layout_add_change_bank.setVisibility(View.GONE);
                    for (int i = 0; i < bankList.size(); i++) {
                        if (bankList.get(i).getCard_type().equals("fund")) {
                            bindFundData(i);
                        } else {
                            bindWithDrawData(i);
                        }
                    }
                    break;
            }

        }

    }

    private void bindWithDrawData(int index) {
        BankInfo.BindBankListBean bank = bankList.get(index);
        ImageView bank_logo_app = (ImageView) findViewById(R.id.bank_logo_app);
        Glide.with(getApplicationContext()).load(bank.getBank_logo()).into(bank_logo_app);

        TextView platform_name = (TextView) findViewById(R.id.platform_name);
        platform_name.setText(bank.getBank_name());

        TextView tv_card_num = (TextView) findViewById(R.id.tv_card_num);
        if (bank.getCard_num() != null && bank.getCard_num().length() > 15) {
            StringBuilder sb = new StringBuilder();
            sb.append(bank.getCard_num());
            sb.insert(12, "  ");
            sb.insert(8, "  ");
            sb.insert(4, "  ");
            tv_card_num.setText(sb.toString());
        } else {
            tv_card_num.setText(bank.getCard_num());
        }

        findViewById(R.id.tv_change).setOnClickListener(this);
    }

    private void bindFundData(int index) {
        BankInfo.BindBankListBean bank = bankList.get(index);
        ImageView bank_logo_app2 = (ImageView) findViewById(R.id.bank_logo_app2);
        Glide.with(getApplicationContext()).load(bank.getBank_logo()).into(bank_logo_app2);

        TextView platform_name2 = (TextView) findViewById(R.id.platform_name2);
        platform_name2.setText(bank.getBank_name());

        TextView tv_card_num2 = (TextView) findViewById(R.id.tv_card_num2);
        if (bank.getCard_num() != null && bank.getCard_num().length() > 16) {
            StringBuilder sb = new StringBuilder();
            sb.append(bank.getCard_num());
            sb.insert(15, "    ");
            sb.insert(12, "    ");
            sb.insert(8, "    ");
            sb.insert(4, "    ");
            tv_card_num2.setText(sb.toString());
        } else {
            tv_card_num2.setText(bank.getCard_num());
        }

    }

    @Override
    protected void doBusiness(Context context) {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_bank_manager;
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.tv_change:
                ((ManagerBankCardActivity)getActivity()).
                        startActivity(ChangeBankActivity.class);
                break;
            case R.id.tv_bind_bank:
                if (layout_change_bank.getVisibility() == View.VISIBLE){
                    ((ManagerBankCardActivity)getActivity()).
                            startActivity(ChangeBankActivity.class);
                }
                break;
        }
    }
}
