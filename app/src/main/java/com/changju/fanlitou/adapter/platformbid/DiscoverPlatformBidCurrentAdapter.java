package com.changju.fanlitou.adapter.platformbid;

import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.adapter.CanBindTwiceAdapter;
import com.changju.fanlitou.bean.discover.platformbid.DiscoverPlatformBidCurrent;
import com.changju.fanlitou.util.TagUtils;

import java.util.List;

/**
 * Created by zm on 2017/5/9.
 */

public class DiscoverPlatformBidCurrentAdapter extends CanBindTwiceAdapter<DiscoverPlatformBidCurrent.CurrentBidsBean
        , BaseViewHolder> {

    public DiscoverPlatformBidCurrentAdapter(List<DiscoverPlatformBidCurrent.CurrentBidsBean> data) {
        super(R.layout.recycler_item_bid_current,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DiscoverPlatformBidCurrent.CurrentBidsBean item) {
        helper.setText(R.id.platform_name,item.getPlatform_name());
        helper.setText(R.id.bid_name,item.getBid_name());
        helper.setText(R.id.bid_interest,item.getBid_interest());
        helper.setText(R.id.bonus_interest,"+" + item.getBonus_interest() + "%");
        helper.setText(R.id.share_amount,item.getShare_amount() + "");
        helper.setText(R.id.share_name,item.getShare_name());

        LinearLayout rectangle_tag = helper.getView(R.id.tag);
        rectangle_tag.removeAllViews();
        List<DiscoverPlatformBidCurrent.CurrentBidsBean.TagListBeanX> tag_list = item.getTag_list();
        if (tag_list == null)
            return;

        for (int i = 0; i < tag_list.size(); i++) {
            rectangle_tag.addView(
                    TagUtils.createTextTag(mContext,tag_list.get(i).getStyle(),tag_list.get(i).getName()));
        }

    }
}
