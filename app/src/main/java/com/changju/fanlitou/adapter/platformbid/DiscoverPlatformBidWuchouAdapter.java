package com.changju.fanlitou.adapter.platformbid;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.adapter.CanBindTwiceAdapter;
import com.changju.fanlitou.bean.discover.platformbid.DiscoverPlatformBidWuchou;
import com.changju.fanlitou.util.TagUtils;

import java.util.List;

/**
 * Created by zm on 2017/5/9.
 */

public class DiscoverPlatformBidWuchouAdapter extends CanBindTwiceAdapter<DiscoverPlatformBidWuchou.PlatformBidsBean, BaseViewHolder> {

    private Context context;

    public DiscoverPlatformBidWuchouAdapter(List<DiscoverPlatformBidWuchou.PlatformBidsBean> data, Context context) {
        super(R.layout.recycler_item_bid_crowdfunding, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DiscoverPlatformBidWuchou.PlatformBidsBean item) {

        helper.getView(R.id.wuchou).setVisibility(View.VISIBLE);
        helper.getView(R.id.status_str).setVisibility(View.GONE);
        helper.getView(R.id.remain_time).setVisibility(View.GONE);
        helper.getView(R.id.total_amount).setVisibility(View.GONE);

        helper.setText(R.id.wuchou, "期限:" + item.getMin_duration() + item.getDuration_unit()
                + "-" + item.getMax_duration() + item.getDuration_unit() + " | 总额:" + item.getTotal_amount());
        Glide.with(context).load(item.getProduct_img_url()).into((ImageView) helper.getView(R.id.bid_img));
        helper.setText(R.id.platform_name, item.getPlatform_name());
        helper.setText(R.id.name, item.getBid_name());
        helper.setText(R.id.bonus_interest, "返利" + item.getBonus_interest() + "%");
        helper.setText(R.id.progress_rate_number, (int) item.getBid_progress_percent() + "%");
        ((ProgressBar) helper.getView(R.id.progress_rate)).setProgress((int) item.getBid_progress_percent());
        helper.getView(R.id.max_invest_amount).setVisibility(View.INVISIBLE);
        if(item.isId_no_limit()){
            helper.setText(R.id.min_invest_amount, item.getMin_interest() + "%-" + "上不封顶");
        }else {
            helper.setText(R.id.min_invest_amount, item.getMin_interest() + "%-" + item.getMax_interest() + "%");
        }

        LinearLayout rectangle_tag = helper.getView(R.id.tag);
        rectangle_tag.removeAllViews();
        List<DiscoverPlatformBidWuchou.PlatformBidsBean.TagListBean> tag_list = item.getTag_list();
        if (tag_list == null)
            return;

        for (int i = 0; i < tag_list.size(); i++) {
            rectangle_tag.addView(
                    TagUtils.createTextTag(mContext,tag_list.get(i).getStyle(),tag_list.get(i).getName()));
        }
    }
}
