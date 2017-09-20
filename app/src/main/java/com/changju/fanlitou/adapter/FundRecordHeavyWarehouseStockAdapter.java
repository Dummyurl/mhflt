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

public class FundRecordHeavyWarehouseStockAdapter extends BaseQuickAdapter<FundDetailProfileInfo.StockFundDetailBean, BaseViewHolder> {
    private Context context;

    public FundRecordHeavyWarehouseStockAdapter(List<FundDetailProfileInfo.StockFundDetailBean> data, Context context) {
        super(R.layout.recycler_item_fund_record_heavy_warehouse_stock, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, FundDetailProfileInfo.StockFundDetailBean item) {
        helper.setText(R.id.heavy_warehouse_stock_tv_stock_name, item.getStock_name())
                .setText(R.id.heavy_warehouse_stock_tv_stock_percent, item.getNav_ratio() + "%")
                .setText(R.id.heavy_warehouse_stock_tv_stock_market_value,item.getHolding_val());
//        if (!(item.getHolding_val() + "").contains("-")) {
//            helper.setText(R.id.heavy_warehouse_stock_tv_stock_market_value, "+"+item.getHolding_val());
//            helper.setTextColor(R.id.heavy_warehouse_stock_tv_stock_market_value, Color.parseColor("#f95353"));
//        } else {
//            helper.setTextColor(R.id.heavy_warehouse_stock_tv_stock_market_value, Color.parseColor("#6eb53f"));
//        }
    }
}
