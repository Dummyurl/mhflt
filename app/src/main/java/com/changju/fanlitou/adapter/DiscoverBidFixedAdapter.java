package com.changju.fanlitou.adapter;

import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.bean.discover.DiscoverBidFixed;
import com.changju.fanlitou.ui.RoundProgressBar;
import com.changju.fanlitou.util.TagUtils;

import java.util.List;

/**
 * Created by zm on 2017/5/9.
 */

public class DiscoverBidFixedAdapter extends CanBindTwiceAdapter<DiscoverBidFixed.BidListBean, BaseViewHolder> {

    private RoundProgressBar roundProgressBar;
    public DiscoverBidFixedAdapter(List<DiscoverBidFixed.BidListBean> listBean) {
        super(R.layout.recycler_item_bid_fixed,listBean);
    }

    @Override
    protected void convert(final BaseViewHolder helper, DiscoverBidFixed.BidListBean item) {
        helper.setText(R.id.platform_name,item.getPlatform_name());
        helper.setText(R.id.bid_name,item.getBid_name());
        helper.setText(R.id.bid_interest,item.getBid_interest());
        helper.setText(R.id.bonus_interest,"+" + item.getBonus_interest() + "%");
        helper.setText(R.id.duration,item.getDuration() + item.getDuration_unit());
        //自定义的progressbar
        int mTotalProgress = (int)item.getBid_progress_percent();
        roundProgressBar = helper.getView(R.id.roundProgressBar);
        roundProgressBar.setAnimProgress(mTotalProgress);

        LinearLayout rectangle_tag = helper.getView(R.id.tag);
        rectangle_tag.removeAllViews();
        List<DiscoverBidFixed.BidListBean.TagListBean> tag_list = item.getTag_list();
        if (tag_list == null)
            return;

        for (int i = 0; i < tag_list.size(); i++) {
            rectangle_tag.addView(
                    TagUtils.createTextTag(mContext,tag_list.get(i).getStyle(),tag_list.get(i).getName()));
        }

    }

}
