package com.changju.fanlitou.adapter.platformbid;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.adapter.CanBindTwiceAdapter;
import com.changju.fanlitou.bean.discover.DiscoverBidInsurance;
import com.changju.fanlitou.bean.discover.platformbid.DiscoverPlatformBidInsurance;

import java.util.List;

/**
 * Created by zm on 2017/5/9.
 */

public class DiscoverPlatformBidInsuranceAdapter extends CanBindTwiceAdapter<DiscoverPlatformBidInsurance.PlatformBidsBean, BaseViewHolder> {

    private Context context;

    public DiscoverPlatformBidInsuranceAdapter(List<DiscoverPlatformBidInsurance.PlatformBidsBean> data, Context context) {
        super(R.layout.recycler_item_bid_insurance, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DiscoverPlatformBidInsurance.PlatformBidsBean item) {

        Glide.with(context).load(item.getProduct_img_url()).into((ImageView) helper.getView(R.id.product_img_url));
        helper.setText(R.id.sub_name, item.getSub_name())
                .setText(R.id.bid_name, item.getBid_name())
                .setText(R.id.price, item.getPrice())
                .setText(R.id.bonus_interest, "返利" + item.getBonus_interest() + "%");

    }
}
