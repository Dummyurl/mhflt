package com.changju.fanlitou.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.bean.discover.DiscoverBidAbroad;

import java.util.List;

/**
 * Created by zm on 2017/5/9.
 */

public class DiscoverBidAbroadAdapter extends CanBindTwiceAdapter<DiscoverBidAbroad.BidListBean, BaseViewHolder> {

    public DiscoverBidAbroadAdapter(List<DiscoverBidAbroad.BidListBean> data) {
        super(R.layout.recycler_item_bid_abroad,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DiscoverBidAbroad.BidListBean item) {
        helper.setText(R.id.platform_name,item.getPlatform_name());
        helper.setText(R.id.bid_name,item.getBid_name());
        helper.setText(R.id.bid_interest,item.getBid_interest());
        helper.setText(R.id.bonus_interest,"+" + item.getBonus_interest() + "%");
        helper.setText(R.id.duration,item.getDuration() + "" + item.getDuration_unit());
        helper.setText(R.id.min_amount,item.getMin_amount() + "美金");
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
