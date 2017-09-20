package com.changju.fanlitou.adapter;


import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.bean.account.RePayingComplete;

import java.util.List;

/**
 * Created by chengww on 2017/5/23.
 */

public class RePayingCompleteAdapter extends CanBindTwiceAdapter<RePayingComplete.RecordsBean, BaseViewHolder> {

    public RePayingCompleteAdapter(List<RePayingComplete.RecordsBean> listBean) {
        super(R.layout.recycler_item_repaying_complete,listBean);
    }

    @Override
    protected void convert(BaseViewHolder helper, RePayingComplete.RecordsBean item) {
        String type;
        switch (item.getType()){
            case "p2p":
                type = "固收";
                initTypeInvest(helper, item);
                break;
            case "gold":
                type = "黄金";
                initTypeInvest(helper, item);
                break;
            case "abroad":
                type = "海外";
                initTypeInvest(helper, item);
                break;
            case "current":
                type = "活期";
                initTypeInvest(helper, item);
                break;
            case "wuchou":
                type = "维C理财";
                initTypeInvest(helper, item);
                break;
            case "gold_fix":
                type = "定期金";
                initTypeInvest(helper, item);
                break;
            case "gold_current":
                type = "活期金";
                initTypeInvest(helper, item);
                break;
            case "crowdfunding":
                type = "众筹";
                helper.setText(R.id.tv_line_1_2,"投资本金(元)")
                        .setText(R.id.tv_line_1_1,String.valueOf(item.getInvest_principle()))
                        .setText(R.id.tv_line_2_2,"投资方案")
                        .setText(R.id.tv_line_2_1,item.getPlan())
                        .setText(R.id.tv_line_4_2,"返利加息(元)")
                        .setText(R.id.tv_line_4_1,String.valueOf(item.getBonus()));
                break;

            case "insurance":
                type = "保险";
                helper.setText(R.id.tv_line_1_2,"投资本金(元)")
                        .setText(R.id.tv_line_1_1,String.valueOf(item.getInvest_principle()))
                        .setText(R.id.tv_line_2_2,"保险分类")
                        .setText(R.id.tv_line_2_1,item.getClassify())
                        .setText(R.id.tv_line_4_2,"返利加息(元)")
                        .setText(R.id.tv_line_4_1,String.valueOf(item.getBonus()));
                break;
            default:
                type = item.getType();
                initTypeInvest(helper, item);
                break;
        }
        helper.setText(R.id.tv_latest_repay_date,"投资日期:" + item.getBid_time())
                .setText(R.id.tv_term,"共" + item.getTotal_term() + "期")
                .setText(R.id.tv_platform_name,item.getPlatform_name() + " | " + item.getBid_name())
                .setText(R.id.tv_daily_bonus,type);


    }

    private void initTypeInvest(BaseViewHolder helper, RePayingComplete.RecordsBean item) {
        helper.setText(R.id.tv_line_1_2,"投资本金(元)")
                .setText(R.id.tv_line_1_1,String.valueOf(item.getInvest_principle()))
                .setText(R.id.tv_line_2_2,"投资利息(元)")
                .setText(R.id.tv_line_2_1,String.valueOf(item.getTotal_interest()))
                .setText(R.id.tv_line_4_2,"返利加息(元)")
                .setText(R.id.tv_line_4_1,String.valueOf(item.getBonus()));
    }

}
