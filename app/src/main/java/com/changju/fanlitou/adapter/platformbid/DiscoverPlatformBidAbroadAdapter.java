package com.changju.fanlitou.adapter.platformbid;

import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.adapter.CanBindTwiceAdapter;
import com.changju.fanlitou.bean.discover.DiscoverBidAbroad;
import com.changju.fanlitou.bean.discover.platformbid.DiscoverPlatformBidAbroad;
import com.changju.fanlitou.util.TagUtils;

import java.util.List;

/**
 * Created by zm on 2017/5/9.
 */

public class DiscoverPlatformBidAbroadAdapter extends CanBindTwiceAdapter<DiscoverPlatformBidAbroad.PlatformBidsBean,
        BaseViewHolder> {

    public DiscoverPlatformBidAbroadAdapter(List<DiscoverPlatformBidAbroad.PlatformBidsBean> data) {
        super(R.layout.recycler_item_bid_abroad,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DiscoverPlatformBidAbroad.PlatformBidsBean item) {
        helper.setText(R.id.platform_name,item.getPlatform_name());
        helper.setText(R.id.bid_name,item.getBid_name());
        helper.setText(R.id.bid_interest,item.getBid_interest());
        helper.setText(R.id.bonus_interest,"+" + item.getBonus_interest() + "%");
        helper.setText(R.id.duration,item.getDuration() + "" + item.getDuration_unit());
        helper.setText(R.id.min_amount,item.getMin_amount() + "美金");

        LinearLayout rectangle_tag = helper.getView(R.id.rectangle_tag);
        List<DiscoverPlatformBidAbroad.PlatformBidsBean.TagListBean> tag_list = item.getTag_list();

        if (tag_list == null)
            return;

        for (int i = 0; i < tag_list.size(); i++) {
            rectangle_tag.addView(
                    TagUtils.createTextTag(mContext,tag_list.get(i).getStyle(),tag_list.get(i).getName()));
        }
    }
}
