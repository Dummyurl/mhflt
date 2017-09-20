package com.changju.fanlitou.adapter.platformbid;

import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.adapter.CanBindTwiceAdapter;
import com.changju.fanlitou.bean.discover.platformbid.DiscoverPlatformBidGold;
import com.changju.fanlitou.util.TagUtils;

import java.util.List;

/**
 * Created by zm on 2017/5/9.
 */

public class DiscoverPlatformBidGoldAdapter extends CanBindTwiceAdapter<DiscoverPlatformBidGold.PlatformBidsBean, BaseViewHolder> {

    public DiscoverPlatformBidGoldAdapter(List<DiscoverPlatformBidGold.PlatformBidsBean> data) {
        super(R.layout.recycler_item_bid_gold,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DiscoverPlatformBidGold.PlatformBidsBean item) {
        //normal_gold_bid：黄金管家新手+普通   g_banker_normal_bid：黄金钱包普通定期金
        if(item.getDisplay_type().equals("normal_gold_bid") || item.getDisplay_type().equals("g_banker_normal_bid") ){
            helper.getView(R.id.bonus_interest).setVisibility(View.VISIBLE);
            helper.setText(R.id.platform_name,item.getPlatform_name());
            helper.setText(R.id.bid_name,item.getBid_name());
            helper.setText(R.id.bid_interest,item.getBid_interest());
            helper.setText(R.id.bonus_interest,"+" + item.getBonus_interest() + "%");
            helper.setText(R.id.min_invest_amount,item.getMin_invest_amount() + "克")
                    .setText(R.id.duration,item.getDuration() + item.getDuration_unit())
                    .setText(R.id.share_name, "最小起投")
                    .setText(R.id.down1, "年化收益");
        }
        //au_current_bid： 黄金管家活期金    g_banker_current_bid： 黄金钱包流动金
        if(item.getDisplay_type().equals("au_current_bid") || item.getDisplay_type().equals("g_banker_current_bid")){
            helper.getView(R.id.bonus_interest).setVisibility(View.VISIBLE);
            helper.setText(R.id.platform_name,item.getPlatform_name());
            helper.setText(R.id.bid_name,item.getBid_name());
            helper.setText(R.id.bid_interest,item.getBid_interest());
            helper.setText(R.id.bonus_interest,"+" + item.getBonus_interest() + "%");
            helper.setText(R.id.min_invest_amount,item.getMin_invest_amount() + "克")
                    .setText(R.id.duration,"活期")
                    .setText(R.id.share_name, "最小起投")
                    .setText(R.id.down1, "年化收益");
        }
        //g_banker_bargin_bid：黄金钱包特价金
        if(item.getDisplay_type().equals("g_banker_bargin_bid")) {
            helper.getView(R.id.bonus_interest).setVisibility(View.GONE);
            helper.setText(R.id.platform_name, item.getPlatform_name());
            helper.setText(R.id.bid_name, item.getBid_name());
            helper.setText(R.id.bid_interest, item.getBonus_interest() + "%")
                    .setText(R.id.duration, item.getDuration() + item.getDuration_unit())
                    .setText(R.id.min_invest_amount, item.getCurrent_price() + "元/克")
                    .setText(R.id.share_name, "专享金价")
                    .setText(R.id.down1, "年化返利");
        }
        //g_banker_new_user：黄金钱包新手金
        if(item.getDisplay_type().equals("g_banker_new_user")){
            helper.getView(R.id.bonus_interest).setVisibility(View.VISIBLE);
            helper.setText(R.id.bid_interest,item.getBid_interest());
            helper.setText(R.id.bonus_interest,"+" + item.getBonus_interest() + "%");
            helper.setText(R.id.platform_name,item.getPlatform_name());
            helper.setText(R.id.bid_name,item.getBid_name());
            helper.setText(R.id.duration,item.getDuration() + item.getDuration_unit())
                    .setText(R.id.min_invest_amount,item.getCurrent_price()+"元/克")
                    .setText(R.id.share_name,"专享金价")
                    .setText(R.id.down1, "年化收益");
        }

        LinearLayout rectangle_tag = helper.getView(R.id.tag);
        rectangle_tag.removeAllViews();
        List<DiscoverPlatformBidGold.PlatformBidsBean.TagListBean> tag_list = item.getTag_list();
        if (tag_list == null)
            return;

        for (int i = 0; i < tag_list.size(); i++) {
            rectangle_tag.addView(
                    TagUtils.createTextTag(mContext,tag_list.get(i).getStyle(),tag_list.get(i).getName()));
        }

    }
}
