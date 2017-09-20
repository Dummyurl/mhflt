package com.changju.fanlitou.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.bean.discover.DiscoverBidInsurance;

import java.util.List;

/**
 * Created by zm on 2017/5/9.
 */

public class DiscoverBidInsuranceAdapter extends CanBindTwiceAdapter<DiscoverBidInsurance.BidListBean, BaseViewHolder> {

    private Context context;

    public DiscoverBidInsuranceAdapter(List<DiscoverBidInsurance.BidListBean> data, Context context) {
        super(R.layout.recycler_item_bid_insurance, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DiscoverBidInsurance.BidListBean item) {

        Glide.with(context).load(item.getProduct_img_url()).into((ImageView) helper.getView(R.id.product_img_url));
        helper.setText(R.id.sub_name, item.getSub_name());
        helper.setText(R.id.bid_name, item.getBid_name());
        helper.setText(R.id.price, item.getPrice());
        helper.setText(R.id.bonus_interest, "返利" + item.getBonus_interest() + "%");

    }
}
