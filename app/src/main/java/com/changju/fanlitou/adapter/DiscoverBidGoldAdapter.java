package com.changju.fanlitou.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.bean.discover.DiscoverBidGold;

import java.util.List;

/**
 * Created by zm on 2017/5/9.
 */

public class DiscoverBidGoldAdapter extends CanBindTwiceAdapter<DiscoverBidGold.BidListBean, BaseViewHolder> {

    public DiscoverBidGoldAdapter(List<DiscoverBidGold.BidListBean> data) {
        super(R.layout.recycler_item_bid_gold,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DiscoverBidGold.BidListBean item) {
        helper.setText(R.id.platform_name,item.getPlatform_name());
        helper.setText(R.id.bid_name,item.getBid_name());
        helper.setText(R.id.bid_interest,item.getBid_interest());
        helper.setText(R.id.bonus_interest,"+" + item.getBonus_interest() + "%");
        helper.setText(R.id.min_invest_amount,item.getMin_invest_amount() + "克")
        .setText(R.id.duration,item.getDuration() + "天");
//        if(!item.isIs_new_user())
//            helper.getView(R.id.newer).setVisibility(View.GONE);
//        else
//            helper.getView(R.id.newer).setVisibility(View.VISIBLE);
//
//        if(!item.isIs_daily_bonus())
//            helper.getView(R.id.dayback).setVisibility(View.GONE);
//        else
//            helper.getView(R.id.dayback).setVisibility(View.VISIBLE);

    }
}
