package com.changju.fanlitou.adapter.platformbid;

import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.adapter.CanBindTwiceAdapter;
import com.changju.fanlitou.bean.discover.platformbid.DiscoverPlatformBidFixed;
import com.changju.fanlitou.ui.RoundProgressBar;
import com.changju.fanlitou.util.TagUtils;

import java.util.List;

/**
 * Created by zm on 2017/5/9.
 */

public class DiscoverPlatformBidFixedAdapter extends CanBindTwiceAdapter<DiscoverPlatformBidFixed.PlatformBidsBean
        , BaseViewHolder> {

    private int mTotalProgress;
//    private int mCurrentProgress;
    private RoundProgressBar roundProgressBar;
    public DiscoverPlatformBidFixedAdapter(List<DiscoverPlatformBidFixed.PlatformBidsBean> listBean) {
        super(R.layout.recycler_item_bid_fixed,listBean);
    }

    @Override
    protected void convert(final BaseViewHolder helper, DiscoverPlatformBidFixed.PlatformBidsBean item) {
        helper.setText(R.id.platform_name,item.getPlatform_name());
        helper.setText(R.id.bid_name,item.getBid_name());
        helper.setText(R.id.bid_interest,item.getBid_interest());
        helper.setText(R.id.bonus_interest,"+" + item.getBonus_interest() + "%");
        helper.setText(R.id.duration,item.getDuration() + item.getDuration_unit());
        //自定义的progressbar
        mTotalProgress = (int)(Float.parseFloat(item.getBid_progress_percent()));
        roundProgressBar = helper.getView(R.id.roundProgressBar);
        roundProgressBar.setAnimProgress(mTotalProgress);

        LinearLayout rectangle_tag = helper.getView(R.id.tag);
        rectangle_tag.removeAllViews();
        List<DiscoverPlatformBidFixed.PlatformBidsBean.TagListBean> tag_list = item.getTag_list();
        if (tag_list == null)
            return;

        for (int i = 0; i < tag_list.size(); i++) {
            rectangle_tag.addView(
                    TagUtils.createTextTag(mContext,tag_list.get(i).getStyle(),tag_list.get(i).getName()));
        }
    }


}
