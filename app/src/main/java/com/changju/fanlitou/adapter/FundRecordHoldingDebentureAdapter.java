package com.changju.fanlitou.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.bean.fund.FundDetailProfileInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/7/19.
 */

public class FundRecordHoldingDebentureAdapter extends BaseQuickAdapter<FundDetailProfileInfo.BondFundDetailBean, BaseViewHolder> {
    private Context context;

    public FundRecordHoldingDebentureAdapter(List<FundDetailProfileInfo.BondFundDetailBean> data, Context context) {
        super(R.layout.recycler_item_fund_record_holding_debenture, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, FundDetailProfileInfo.BondFundDetailBean item) {
        helper.setText(R.id.holding_debenture_tv_stock_name, item.getBond_name())
                .setText(R.id.holding_debenture_tv_stock_percent, item.getTot_val_prop() + "%")
                .setText(R.id.holding_debenture_tv_stock_bond_sum,item.getTot_val());
//        if (!(item.getBond_sum() + "").contains("-")) {
//            helper.setText(R.id.holding_debenture_tv_stock_bond_sum, "+" + item.getBond_sum());
//            helper.setTextColor(R.id.holding_debenture_tv_stock_bond_sum, Color.parseColor("#f95353"));
//        } else {
//            helper.setTextColor(R.id.holding_debenture_tv_stock_bond_sum, Color.parseColor("#6eb53f"));
//        }
    }
}
