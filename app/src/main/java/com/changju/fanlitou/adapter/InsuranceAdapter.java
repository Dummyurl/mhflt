package com.changju.fanlitou.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.bean.homeclassify.InsuranceBean;

import java.util.List;

/**
 * Created by zm on 2017/5/9.
 */

public class InsuranceAdapter extends CanBindTwiceAdapter<InsuranceBean.InsuranceBidListBean, BaseViewHolder> {

    private Context context;

    public InsuranceAdapter(List<InsuranceBean.InsuranceBidListBean> data, Context context) {
        super(R.layout.recycler_item_bid_insurance, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, InsuranceBean.InsuranceBidListBean item) {

        Glide.with(context).load(item.getProduct_img_url()).into((ImageView) helper.getView(R.id.product_img_url));
        helper.setText(R.id.sub_name, item.getSub_name());
        helper.setText(R.id.bid_name, item.getName());
        helper.setText(R.id.price, item.getPrice());
        helper.setText(R.id.bonus_interest, "返" + item.getBonus_value() + "%");

    }
}
