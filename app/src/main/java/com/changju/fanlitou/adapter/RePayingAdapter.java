package com.changju.fanlitou.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.account.AccountCollectionDetailsActivity;
import com.changju.fanlitou.activity.account.DailyRebateActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.MyApplication;
import com.changju.fanlitou.bean.account.RePaying;
import com.changju.fanlitou.util.NormalUtils;

import java.util.List;

/**
 * Created by chengww on 2017/5/23.
 */

public class RePayingAdapter extends CanBindTwiceAdapter<RePaying.RecordsBean, BaseViewHolder> {

    public RePayingAdapter(List<RePaying.RecordsBean> listBean) {
        super(R.layout.recycler_item_repaying,listBean);
    }

    @Override
    protected void convert(BaseViewHolder helper, final RePaying.RecordsBean item) {
        helper.setText(R.id.tv_latest_repay_date,"最近回款日:" + item.getLatest_repay_date())
        .setText(R.id.tv_term,"第" + item.getTerm() + "期" + "/共" + item.getTotal_term() + "期")
        .setText(R.id.tv_platform_name,item.getPlatform_name() + " | " + item.getBid_name())
        .setText(R.id.tv_receivable_principal,item.getReceivable_principal())
        .setText(R.id.tv_receivable_interest,item.getReceivable_interest())
        .setText(R.id.tv_fanli,"预计返:" + item.getExpect_bonus() +
                "元 | 已返利:" + item.getReceived_bonus()+ "元");

        if (item.isIs_daily_bonus()){
            helper.getView(R.id.tv_daily_bonus).setVisibility(View.VISIBLE);
            TextView fanli = helper.getView(R.id.tv_fanli);
            fanli.append(" | 昨日返:" + item.getToday_bonus() + "元");
        }else {
            helper.getView(R.id.tv_daily_bonus).setVisibility(View.GONE);
        }

        helper.getView(R.id.layout_root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startToRepayDetail(item.getType(), item.getBid_id());
            }
        });
//        helper.addOnClickListener(R.id.tv_payment_plan);
    }

    /**
     * 跳转回款明细
     *
     * @param type
     * @param bid_id
     */
    public void startToRepayDetail(String type, int bid_id) {
        Bundle args = new Bundle();
        args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, bid_id);
        /**
         * 固收：p2p
         * 黄金：gold
         * 海外：abroad
         * p2p_current：固收活期
         * gold_current：黄金活期
         */
        switch (type) {
            case "p2p":
            case "gold":
            case "abroad":
                args.putString(DailyRebateActivity.class.getSimpleName(), type);
                Intent i = new Intent(mContext,DailyRebateActivity.class);
                i.putExtras(args);
                mContext.startActivity(i);
                break;
            case "p2p_current":
            case "gold_current":
                args.putString(AccountCollectionDetailsActivity.class.getSimpleName(), type);
                Intent intent = new Intent(mContext,AccountCollectionDetailsActivity.class);
                intent.putExtras(args);
                mContext.startActivity(intent);
                break;
            default:
                if (MyApplication.isDebug)
                    NormalUtils.showToast(mContext,"错误，未知标的类型:" + type);
                break;
        }
    }

}
