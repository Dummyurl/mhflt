package com.changju.fanlitou.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.bean.discover.DiscoverBidCurrent;

import java.util.List;

/**
 * Created by zm on 2017/5/9.
 */

public class DiscoverBidCurrentAdapter extends CanBindTwiceAdapter<DiscoverBidCurrent.BidListBean, BaseViewHolder> {

    public DiscoverBidCurrentAdapter(List<DiscoverBidCurrent.BidListBean> data) {
        super(R.layout.recycler_item_bid_current,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DiscoverBidCurrent.BidListBean item) {
        helper.setText(R.id.platform_name,item.getPlatform_name());
        helper.setText(R.id.bid_name,item.getBid_name());
        helper.setText(R.id.bid_interest,item.getBid_interest());
        helper.setText(R.id.bonus_interest,"+" + item.getBonus_interest() + "%");
        helper.setText(R.id.share_amount,item.getShare_amount() + "");
        helper.setText(R.id.share_name,item.getShare_name());
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
