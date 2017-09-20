package com.changju.fanlitou.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.bean.account.investreport.CategoryInvestReport;

import java.util.List;

/**
 * Created by zm on 2017/5/9.
 */

public class AccountInvestReportAdapter extends CanBindTwiceAdapter<CategoryInvestReport.CategoryDataBean.ChartDataBean, BaseViewHolder> {

    private Context context;
    public AccountInvestReportAdapter(List<CategoryInvestReport.CategoryDataBean.ChartDataBean> data, Context context) {
        super(R.layout.recycler_item_investreport,data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, CategoryInvestReport.CategoryDataBean.ChartDataBean item) {

        String nameOfAmount = "";
        if(item.getType().equals("p2p")){
            nameOfAmount = "待收本息:";
        }else if(item.getType().equals("current")){
            nameOfAmount = "持有本金:";
        }else if(item.getType().equals("gold")){
            nameOfAmount = "持有克重:";
        } else if(item.getType().equals("crowdfunding")){
            nameOfAmount = "投资本金:";
        }else if(item.getType().equals("abroad")){
            nameOfAmount = "待收本息:";
        } else if(item.getType().equals("insurance")){
            nameOfAmount = "投保费用:";
        }
        
        helper.setText(R.id.tv_name_investreport,item.getName())
                .setText(R.id.tv_amount_investreport,nameOfAmount + item.getAmount())
                .setText(R.id.tv_bid_count_investreport,"投资产品：" + item.getBid_count() + "个");
        Glide.with(context).load(item.getLogo()).into( (ImageView) helper.getView(R.id.iv_platform_icon));
    }
}
