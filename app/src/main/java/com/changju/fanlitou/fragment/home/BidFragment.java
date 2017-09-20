package com.changju.fanlitou.fragment.home;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.MainActivity;
import com.changju.fanlitou.activity.bid.BidAbroadActivity;
import com.changju.fanlitou.activity.bid.BidCrowdFundingActivity;
import com.changju.fanlitou.activity.bid.BidCurrentActivity;
import com.changju.fanlitou.activity.bid.BidFixedActivity;
import com.changju.fanlitou.activity.bid.BidGoldActivity;
import com.changju.fanlitou.activity.bid.BidVcActivity;
import com.changju.fanlitou.activity.fund.FundDetailActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.bean.home.Abroad;
import com.changju.fanlitou.bean.home.Crowdfunding;
import com.changju.fanlitou.bean.home.Current;
import com.changju.fanlitou.bean.home.Fixed;
import com.changju.fanlitou.bean.home.Fund;
import com.changju.fanlitou.bean.home.Gold;
import com.changju.fanlitou.bean.home.GoldCurrent;
import com.changju.fanlitou.bean.home.Vc;
import com.changju.fanlitou.util.ParseUtils;
import com.growingio.android.sdk.collection.GrowingIO;

/**
 * Created by chengww on 2017/5/7.
 */

public class BidFragment extends LazyFragment {

    private String jsonData;

    private String bid_type;

    private MainActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    public static BidFragment newInstance(String jsonData, String bid_type, boolean isLazyLoad) {

        Bundle args = new Bundle();
        args.putBoolean(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, isLazyLoad);
        BidFragment fragment = new BidFragment();
        fragment.setArguments(args);
        fragment.jsonData = jsonData;
        fragment.bid_type = bid_type;
        return fragment;
    }

    @Override
    protected void initView() {
        switch (bid_type == null ? "" : bid_type){
            case "fixed":
                initFixed();
                break;
            case "gold":
                initGold();
                break;
            case "abroad":
                initAbroad();
                break;
            case "crowdfunding":
                initCrowdfunding();
                break;
            case "current":
                initCurrent();
                break;
            case "gold_current":
                initGoldCurrent();
                break;
            case "vc":
                initVc();
                break;
            case "fund":
                initFund();
                break;

        }
        findViewById(R.id.layout_root).setOnClickListener(this);

        GrowingIO.getInstance().setPageName(this, "首页-推荐标的");
    }

    private int bid_id;
    private String title;

    private void initFixed() {
        Fixed fixed = ParseUtils.parseByGson(jsonData, Fixed.class);
        Fixed.RecommendBidBean bid = fixed.getRecommend_bid();
        Fixed.RecommendBidBean.BidDetailBean detail = bid.getBid_detail();

        bid_id = detail.getBid_id();
        title = detail.getBid_name();
        String Bid_name = detail.getBid_name();
        //标的利率
        //float bid_interest = detail.getBid_interest();
        //int bid_interest_format = (int) bid_interest;
        ((TextView) findViewById(R.id.bid_interest)).setText(detail.getBid_interest());
        //标的加息利率
        //float raise_interest = detail.getRaise_interest();
        //int raise_interest_format = (int) raise_interest;
        ((TextView) findViewById(R.id.raise_interest)).setText(detail.getRaise_interest());
        //标的期限
        String bid_duration = detail.getBid_duration();
        ((TextView) findViewById(R.id.bid_duration)).setText(bid_duration);
        //剩余金额
        String remain_amount = detail.getRemain_amount();
        String remain_format = "剩余" + remain_amount;
        ((TextView) findViewById(R.id.remain_amount)).setText(remain_format);

        //投资进度
        String bid_progress_percent = detail.getBid_progress_percent();
        ((ProgressBar) findViewById(R.id.progress_fixed)).setProgress((int) Float.parseFloat(bid_progress_percent));

        Fixed.RecommendBidBean.BidDetailBean.PlatformBean platform = detail.getPlatform();
        //平台名称
        String platform_name = platform.getPlatform_name();
        ((TextView) findViewById(R.id.platform_bid_name)).setText(platform_name + "|" + Bid_name);
    }

    private void initGold() {
        Gold gold = ParseUtils.parseByGson(jsonData,Gold.class);
        Gold.RecommendBidBean bid = gold.getRecommend_bid();
        Gold.RecommendBidBean.BidDetailBean detail = bid.getBid_detail();
        String bid_name = detail.getBid_name();
        title = detail.getBid_name();
        //标的利率
        //float bid_interest = detail.getBid_interest();
        //int bid_interest_format = (int) bid_interest;
        ((TextView)findViewById(R.id.bid_interest)).setText(detail.getBid_interest());
        //标的加息利率
        //float raise_interest = detail.getRaise_interest();
        ((TextView)findViewById(R.id.raise_interest)).setText(detail.getRaise_interest());
        //标的期限
        String bid_duration = detail.getBid_duration();
        ((TextView)findViewById(R.id.bid_duration)).setText(bid_duration);
        //剩余金额
        //float percent = detail.getBid_progress_percent();
        ((ProgressBar)findViewById(R.id.progress_fixed)).setProgress((int) Float.parseFloat(detail.getBid_progress_percent()));
        String remain_format = "剩余" + detail.getBid_progress_percent()+ "%";
        ((TextView)findViewById(R.id.remain_amount)).setText(remain_format);

        Gold.RecommendBidBean.BidDetailBean.PlatformBean platform = detail.getPlatform();
        //平台名称
        String platform_name = platform.getPlatform_name();
        ((TextView)findViewById(R.id.platform_bid_name)).setText(platform_name + " | " + bid_name);
    }

    private void initAbroad() {
        Abroad abroad = ParseUtils.parseByGson(jsonData,Abroad.class);
        Abroad.RecommendBidBean bid = abroad.getRecommend_bid();
        Abroad.RecommendBidBean.BidDetailBean detail = bid.getBid_detail();
        bid_id = detail.getBid_id();
        String Bid_name = detail.getBid_name();
        title = detail.getBid_name();
        //标的利率
        //float bid_interest = detail.getBid_interest();
        //int bid_interest_format = (int) bid_interest;
        ((TextView)findViewById(R.id.bid_interest)).setText(detail.getBid_interest());
        //标的加息利率
        //float raise_interest = detail.getRaise_interest();
        //int raise_interest_format = (int) raise_interest;
        ((TextView)findViewById(R.id.raise_interest)).setText(detail.getRaise_interest());
        //标的期限
        String bid_duration = detail.getBid_duration();
        ((TextView)findViewById(R.id.bid_duration)).setText(bid_duration);
        //剩余金额
        //float percent = detail.getBid_progress_percent();
        ((ProgressBar)findViewById(R.id.progress_fixed)).setProgress((int) Float.parseFloat(detail.getBid_progress_percent()));
        String remain_format = "剩余" + detail.getBid_progress_percent() + "%";
        ((TextView)findViewById(R.id.remain_amount)).setText(remain_format);

        Abroad.RecommendBidBean.BidDetailBean.PlatformBean platform = detail.getPlatform();
        //平台名称
        String platform_name = platform.getPlatform_name();
        ((TextView)findViewById(R.id.platform_bid_name)).setText(platform_name + "|" + Bid_name);
    }

    private void initCrowdfunding() {

        Crowdfunding crowdfunding = ParseUtils.parseByGson(jsonData,Crowdfunding.class);
        Crowdfunding.RecommendBidBean bid = crowdfunding.getRecommend_bid();
        Crowdfunding.RecommendBidBean.BidDetailBean detail = bid.getBid_detail();

        bid_id = detail.getBid_id();
        String platform_name = detail.getPlatform_name();
        String name  = detail.getName();
        //float min_invest_amount = detail.getMin_invest_amount();
        //float progress_rate = detail.getProgress_rate();
        String bid_img = detail.getBid_img();
        String status = detail.getStatus_str();
        String remain_time = detail.getRemain_time();
        String total_amount = detail.getTotal_amount();

        ((TextView)findViewById(R.id.platform_bid_name)).setText(platform_name + " | " + name);
        ((TextView)findViewById(R.id.min_invest_amount)).setText(detail.getMin_invest_amount());
        ((ProgressBar)findViewById(R.id.progress_rate)).setProgress( (int) Float.parseFloat(detail.getProgress_rate()) );
        ((TextView)findViewById(R.id.progress_rate_number)).setText( detail.getProgress_rate() + "%" );

        ImageView bidImgIv = (ImageView)findViewById(R.id.bid_img);
        Glide.with(getApplicationContext()).load(bid_img).into(bidImgIv);

        TextView statusTv = ((TextView)findViewById(R.id.status));
        statusTv.setText(status);
        Drawable statusDrawable = activity.getResources().getDrawable(R.mipmap.crowd_funding_status);
        statusDrawable.setBounds(0, 0, statusDrawable.getMinimumWidth(), statusDrawable.getMinimumHeight());
        statusTv.setCompoundDrawables(statusDrawable,null,null,null);
        statusTv.setCompoundDrawablePadding(4);

        TextView remainTimeTv = ((TextView)findViewById(R.id.remain_time));
        remainTimeTv.setText(remain_time);
        Drawable remianTimedrawable = activity.getResources().getDrawable(R.mipmap.crowd_funding_remain_time);
        remianTimedrawable.setBounds(0, 0, remianTimedrawable.getMinimumWidth(), remianTimedrawable.getMinimumHeight());
        remainTimeTv.setCompoundDrawables(remianTimedrawable,null,null,null);
        remainTimeTv.setCompoundDrawablePadding(4);

        TextView totalAmountTv = ((TextView)findViewById(R.id.total_amount));
        totalAmountTv.setText(total_amount);
        Drawable totalAmountDrawable = activity.getResources().getDrawable(R.mipmap.crowd_funding_total_amount);
        totalAmountDrawable.setBounds(0, 0, totalAmountDrawable.getMinimumWidth(), totalAmountDrawable.getMinimumHeight());
        totalAmountTv.setCompoundDrawables(totalAmountDrawable,null,null,null);
        totalAmountTv.setCompoundDrawablePadding(4);

    }

    private void initCurrent() {
        Current current = ParseUtils.parseByGson(jsonData,Current.class);
        Current.RecommendBidBean bid = current.getRecommend_bid();
        Current.RecommendBidBean.BidDetailBean detail = bid.getBid_detail();

        bid_id = detail.getBid_id();
        String platform_name = detail.getPlatform_name();
        String name  = detail.getName();
        title = detail.getName();

        ((TextView)findViewById(R.id.platform_bid_name)).setText(platform_name + " | " + name);
        ((TextView)findViewById(R.id.interest)).setText(detail.getInterest());
        ((TextView)findViewById(R.id.bonus_interest)).setText(detail.getBonus_interest());
        ((TextView)findViewById(R.id.remain_shares)).setText(detail.getRemain_shares());

    }

    private void initGoldCurrent() {
        GoldCurrent goldCurrent = ParseUtils.parseByGson(jsonData,GoldCurrent.class);
        GoldCurrent.RecommendBidBean bid = goldCurrent.getRecommend_bid();
        GoldCurrent.RecommendBidBean.BidDetailBean detail = bid.getBid_detail();
        GoldCurrent.RecommendBidBean.BidDetailBean.PlatformBean platformBean = detail.getPlatform();

        bid_id = detail.getBid_id();
        String platform_name = platformBean.getPlatform_name();
        String name  = detail.getBid_name();
        title = detail.getBid_name();

        //此处黄金价格api未给出
        ((TextView)findViewById(R.id.platform_name)).setText(platform_name+"|"+name);
        ((TextView)findViewById(R.id.now_gold_price)).setText(detail.getCurrent_price());
        ((TextView)findViewById(R.id.bid_interest)).setText("年化收益：" + detail.getBid_interest()+"%");
        ((TextView)findViewById(R.id.raise_interest)).setText("年化返利：" + detail.getRaise_interest()+"%");

    }

    private void initVc() {

        Vc vc = ParseUtils.parseByGson(jsonData,Vc.class);
        Vc.RecommendBidBean bid = vc.getRecommend_bid();
        Vc.RecommendBidBean.BidDetailBean detail = bid.getBid_detail();

        bid_id = detail.getBid_id();
        String platform_name = detail.getPlatform_name();
        String name  = detail.getName();
        title = detail.getName();
        //float min_invest_amount = detail.getMin_invest_amount();
        //float progress_rate = detail.getProgress_rate();
        String bid_img = detail.getBid_img();
        String min_duration = detail.getMin_duration() + "";
        String max_duration = detail.getMax_duration() + "";
        String total_amount = detail.getTotal_amount() + "";

        ((TextView) findViewById(R.id.platform_bid_name)).setText(platform_name + "|" + name);
        ((TextView)findViewById(R.id.min_invest_amount)).setText(detail.getMin_invest_amount());
        ((ProgressBar)findViewById(R.id.progress_rate)).setProgress( (int) Float.parseFloat(detail.getProgress_rate()) );
        ((TextView)findViewById(R.id.progress_rate_number)).setText( (int) Float.parseFloat(detail.getProgress_rate()) + "%" );
        ((TextView)findViewById(R.id.duration)).setText("期限"+min_duration+"天"+"-"+max_duration+"天" + " | " + "总额"+total_amount+"元");
        ImageView bidImgIv = (ImageView)findViewById(R.id.bid_img);
        Glide.with(getApplicationContext()).load(bid_img).into(bidImgIv);
    }

    private String fund_code;
    private void initFund(){
        Fund fund = ParseUtils.parseByGson(jsonData,Fund.class);
        Fund.RecommendBidBean bid = fund.getRecommend_bid();
        Fund.RecommendBidBean.BidDetailBean detail = bid.getBid_detail();
        title = detail.getFund_name();
        fund_code = detail.getFund_code();

        bid_id = detail.getFund_id();
        ((TextView)findViewById(R.id.year_gr)).setText(detail.getYear_gr());
        ((TextView)findViewById(R.id.fund_name)).setText(detail.getFund_name());
        ((TextView)findViewById(R.id.risk_invest_type)).setText(detail.getRisk() + " | " +detail.getInvest_type());
    }

    @Override
    protected void doBusiness(Context context) {

    }

    @Override
    public int bindLayout() {
        if (!TextUtils.isEmpty(bid_type)){
            switch (bid_type) {
                case "fixed":
                case "gold":
                case "abroad":
                    return R.layout.item_recommend_bid_fixed;
                case "vc":
                    return R.layout.item_recommend_bid_vc;
                case "crowdfunding":
                case "crowd_funding":
                    return R.layout.item_recommend_bid_crowd_funding;
                case "current":
                    return R.layout.item_recommend_bid_current;
                case "gold_current":
                    return R.layout.item_recommend_bid_gold_current;
                case "fund":
                    return R.layout.item_recommend_bid_fund;
//                case "insurance":
//                    return 1;
//                //基金的bid_detail没有bid_id字段？？？
//                case "fund":
//                    return R.layout.item_recommend_bid_fund;

            }
        }

        return R.layout.item_recommend_bid_error;
    }

    @Override
    public void widgetClick(View v) {
        Bundle args = new Bundle();
        args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,bid_id);
        args.putString(BidFixedActivity.BID_NAME,title);
        switch (bid_type){
            case "fixed":
                activity.startActivity(BidFixedActivity.class,args);
                break;
            case "gold":
            case "gold_current":
                activity.startActivity(BidGoldActivity.class,args);
                break;
            case "abroad":
                activity.startActivity(BidAbroadActivity.class,args);
                break;
            case "vc":
                activity.startActivity(BidVcActivity.class,args);
                break;
            case "crowdfunding":
            case "crowd_funding":
                activity.startActivity(BidCrowdFundingActivity.class,args);
                break;
            case "current":
                activity.startActivity(BidCurrentActivity.class,args);
                break;
            case "fund":
                args.putString(FundDetailActivity.FUND_CODE,fund_code);
                activity.startActivity(FundDetailActivity.class,args);
                break;
        }
    }
}
