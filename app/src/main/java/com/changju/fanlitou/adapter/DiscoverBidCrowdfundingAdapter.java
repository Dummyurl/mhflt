package com.changju.fanlitou.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.bean.discover.DiscoverBidCrowdfunding;

import java.util.List;

/**
 * Created by zm on 2017/5/9.
 */

public class DiscoverBidCrowdfundingAdapter extends CanBindTwiceAdapter<DiscoverBidCrowdfunding.BidListBean, BaseViewHolder> {

    private Context context;
    public DiscoverBidCrowdfundingAdapter(List<DiscoverBidCrowdfunding.BidListBean> data , Context context) {
        super(R.layout.recycler_item_bid_crowdfunding,data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DiscoverBidCrowdfunding.BidListBean item) {


        if(item.getType().equals("crowdfunding")){
            helper.getView(R.id.wuchou).setVisibility(View.GONE);
            helper.getView(R.id.status_str).setVisibility(View.VISIBLE);
            helper.getView(R.id.remain_time).setVisibility(View.VISIBLE);
            helper.getView(R.id.total_amount).setVisibility(View.VISIBLE);

            helper.setText(R.id.status_str,item.getStatus_str());
            TextView statusTv = helper.getView(R.id.status_str);
            Drawable statusDrawable = context.getResources().getDrawable(R.mipmap.crowd_funding_status);
            statusDrawable.setBounds(0, 0, statusDrawable.getMinimumWidth(), statusDrawable.getMinimumHeight());
            statusTv.setCompoundDrawables(statusDrawable,null,null,null);
            statusTv.setCompoundDrawablePadding(4);

            helper.setText(R.id.remain_time,item.getRemain_time());
            TextView remain_time = helper.getView(R.id.remain_time);
            Drawable remain_timeDrawable = context.getResources().getDrawable(R.mipmap.crowd_funding_remain_time);
            remain_timeDrawable.setBounds(0, 0, remain_timeDrawable.getMinimumWidth(), remain_timeDrawable.getMinimumHeight());
            remain_time.setCompoundDrawables(remain_timeDrawable,null,null,null);
            remain_time.setCompoundDrawablePadding(4);

            helper.setText(R.id.total_amount,item.getTotal_amount());
            TextView total_amount = helper.getView(R.id.total_amount);
            Drawable total_amountDrawable = context.getResources().getDrawable(R.mipmap.crowd_funding_total_amount);
            total_amountDrawable.setBounds(0, 0, total_amountDrawable.getMinimumWidth(), total_amountDrawable.getMinimumHeight());
            total_amount.setCompoundDrawables(total_amountDrawable,null,null,null);
            total_amount.setCompoundDrawablePadding(4);

            Glide.with(context).load(item.getProduct_img_url()).into((ImageView)helper.getView(R.id.bid_img));
            helper.setText(R.id.platform_name,item.getPlatform_name());
            helper.setText(R.id.name,item.getBid_name());
            helper.setText(R.id.bonus_interest, "返利" + item.getBonus_interest() + "%");
            helper.getView(R.id.max_invest_amount).setVisibility(View.VISIBLE);
            helper.setText(R.id.min_invest_amount, "￥" + item.getMin_invest_amount());
            helper.setText(R.id.progress_rate_number, (int)item.getBid_progress_percent()+"%");
            ((ProgressBar)helper.getView(R.id.progress_rate)).setProgress((int)item.getBid_progress_percent());
        }else if(item.getType().equals("wuchou")){

            helper.getView(R.id.wuchou).setVisibility(View.VISIBLE);
            helper.getView(R.id.status_str).setVisibility(View.GONE);
            helper.getView(R.id.remain_time).setVisibility(View.GONE);
            helper.getView(R.id.total_amount).setVisibility(View.GONE);

            helper.setText(R.id.wuchou,"期限:"+item.getMin_duration() +item.getDuration_unit()
                    +"-"+item.getMax_interest()+item.getDuration_unit()+" | 总额:"+item.getTotal_amount());
            Glide.with(context).load(item.getProduct_img_url()).into((ImageView)helper.getView(R.id.bid_img));
            helper.setText(R.id.platform_name,item.getPlatform_name());
            helper.setText(R.id.name,item.getBid_name());
            helper.setText(R.id.bonus_interest, "返利" + item.getBonus_interest() + "%");
            helper.setText(R.id.progress_rate_number, (int)item.getBid_progress_percent()+"%");
            ((ProgressBar)helper.getView(R.id.progress_rate)).setProgress((int)item.getBid_progress_percent());
            helper.getView(R.id.max_invest_amount).setVisibility(View.INVISIBLE);
            helper.setText(R.id.min_invest_amount,item.getMin_interest()+"%-"+item.getMax_interest()+"%");

        }
    }
}
