package com.changju.fanlitou.adapter.platformbid;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.adapter.CanBindTwiceAdapter;
import com.changju.fanlitou.bean.discover.platformbid.DiscoverPlatformBidCrowdfunding;
import com.changju.fanlitou.bean.discover.platformbid.DiscoverPlatformBidFixed;
import com.changju.fanlitou.util.TagUtils;

import java.util.List;

/**
 * Created by zm on 2017/5/9.
 */

public class DiscoverPlatformBidCrowdfundingAdapter extends CanBindTwiceAdapter<DiscoverPlatformBidCrowdfunding.PlatformBidsBean, BaseViewHolder> {

    private Context context;

    public DiscoverPlatformBidCrowdfundingAdapter(List<DiscoverPlatformBidCrowdfunding.PlatformBidsBean> data, Context context) {
        super(R.layout.recycler_item_bid_crowdfunding, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DiscoverPlatformBidCrowdfunding.PlatformBidsBean item) {

        helper.getView(R.id.wuchou).setVisibility(View.GONE);
        helper.getView(R.id.status_str).setVisibility(View.VISIBLE);
        helper.getView(R.id.remain_time).setVisibility(View.VISIBLE);
        helper.getView(R.id.total_amount).setVisibility(View.VISIBLE);

        helper.setText(R.id.status_str, item.getStatus_str());
        TextView statusTv = helper.getView(R.id.status_str);
        Drawable statusDrawable = context.getResources().getDrawable(R.mipmap.crowd_funding_status);
        statusDrawable.setBounds(0, 0, 24, 32);
        statusTv.setCompoundDrawables(statusDrawable, null, null, null);
        statusTv.setCompoundDrawablePadding(4);

        helper.setText(R.id.remain_time, "剩余" + item.getRemain_time());
        TextView remain_time = helper.getView(R.id.remain_time);
        Drawable remain_timeDrawable = context.getResources().getDrawable(R.mipmap.crowd_funding_remain_time);
        remain_timeDrawable.setBounds(0, 0, 32, 32);
        remain_time.setCompoundDrawables(remain_timeDrawable, null, null, null);
        remain_time.setCompoundDrawablePadding(4);

        helper.setText(R.id.total_amount, item.getTotal_amount());
        TextView total_amount = helper.getView(R.id.total_amount);
        Drawable total_amountDrawable = context.getResources().getDrawable(R.mipmap.crowd_funding_total_amount);
        total_amountDrawable.setBounds(0, 0, 32, 32);
        total_amount.setCompoundDrawables(total_amountDrawable, null, null, null);
        total_amount.setCompoundDrawablePadding(4);

        Glide.with(context).load(item.getProduct_img_url()).into((ImageView) helper.getView(R.id.bid_img));
        helper.setText(R.id.platform_name, item.getPlatform_name());
        helper.setText(R.id.name, item.getBid_name());
        helper.setText(R.id.bonus_interest, "返利" + item.getBonus_interest() + "%");
        helper.getView(R.id.max_invest_amount).setVisibility(View.VISIBLE);
        helper.setText(R.id.min_invest_amount, "￥" + item.getMin_invest_amount());
        helper.setText(R.id.progress_rate_number, (int) item.getBid_progress_percent() + "%");
        ((ProgressBar) helper.getView(R.id.progress_rate)).setProgress((int) item.getBid_progress_percent());

        LinearLayout rectangle_tag = helper.getView(R.id.tag);
        rectangle_tag.removeAllViews();
        List<DiscoverPlatformBidCrowdfunding.PlatformBidsBean.TagListBean> tag_list = item.getTag_list();
        if (tag_list == null)
            return;

        for (int i = 0; i < tag_list.size(); i++) {
            rectangle_tag.addView(
                    TagUtils.createTextTag(mContext,tag_list.get(i).getStyle(),tag_list.get(i).getName()));
        }

    }
}
