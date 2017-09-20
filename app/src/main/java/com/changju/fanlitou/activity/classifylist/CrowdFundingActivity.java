package com.changju.fanlitou.activity.classifylist;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.util.Util;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.bid.BidCrowdFundingActivity;
import com.changju.fanlitou.activity.bid.BidFixedActivity;
import com.changju.fanlitou.activity.bid.BidVcActivity;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.homeclassify.Banner;
import com.changju.fanlitou.bean.homeclassify.HomeCrowdFunding;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/5/25.
 */

public class CrowdFundingActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {

    public static final String CROWDFUNDING = "crowdfunding";
    private TextView tv_classify_crowdfunding_banner, tv_crowdfunding_platform_name, tv_crowdfunding_platform_description;
    private TextView crowdfunding_platform_name;

    //两个列表上面的banner+小banner 列表head+foot
    private LinearLayout parent, crowdfunding_list_head, crowdfunding_list_foot, wuchou_list_head, wuchou_list_foot;
    private RecyclerView crowdfunding_list, wuchou_list;
    private ImageView iv_crowdfunding_platform_logo;
    private List<HomeCrowdFunding.WuchouBean.BidListBeanX> wuchouBidListBeanXList = new ArrayList<>();
    private List<HomeCrowdFunding.WuchouBean.BidListBeanX> tempWuchouBidListBeanXList = new ArrayList<>();

    private List<HomeCrowdFunding.CrowdfundingBean.BidListBean> crowdFundingBidListBeanXList = new ArrayList<>();
    private List<HomeCrowdFunding.CrowdfundingBean.BidListBean> tempCrowdFundingBidListBeanXList = new ArrayList<>();

    private WuchouSimpleAdapter adapter = null;
    private CrowdFundingSimpleAdapter crowdFundingSimpleAdapter = null;

    private TextView tv_crowdfunding_list_more;
    private ImageView iv_crowdfunding_list_more;

    private TextView tv_wuchou_list_more;
    private ImageView iv_wuchou_list_more;

    private RelativeLayout crowdfunding_list_more_relativelayout;
    private TextView wuchou_platform_name;
    private RelativeLayout wuchou_list_more_relativelayout;
    private SmartRefreshLayout refreshLayout;

    //loading&error
    private View include;
    private View include_load_error;

    //是否刷新
    private boolean isRefresh;

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_classify_list_crowdfunding;
    }

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "众筹二级页");

        findViewById(R.id.iv_back_crowdfunding).setOnClickListener(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        parent = (LinearLayout) inflater.inflate(R.layout.classify_head_crowdfunding, null, false);

        wuchou_list_head = (LinearLayout) inflater.inflate(R.layout.classify_listitem_head_wuchou, null);
        wuchou_platform_name = (TextView) (wuchou_list_head.findViewById(R.id.tv_classify_wuchou_platform_name));
        wuchou_list_foot = (LinearLayout) inflater.inflate(R.layout.classify_listitem_foot_wuchou, null);
        wuchou_list_more_relativelayout = (RelativeLayout) wuchou_list_foot.findViewById(R.id.wuchou_list_more_relativelayout);
        tv_wuchou_list_more = (TextView) wuchou_list_foot.findViewById(R.id.wuchou_list_more);
        iv_wuchou_list_more = (ImageView) wuchou_list_foot.findViewById(R.id.wuchou_list_more_iv);
        wuchou_list_more_relativelayout.setOnClickListener(listener);

        crowdfunding_list_head = (LinearLayout) inflater.inflate(R.layout.classify_listitem_head_crowdfunding, null);
        crowdfunding_platform_name = (TextView) (crowdfunding_list_head.findViewById(R.id.tv_classify_crowdfunding_platform_name));
        crowdfunding_list_foot = (LinearLayout) inflater.inflate(R.layout.classify_listitem_foot_crowdfunding, null);
        crowdfunding_list_more_relativelayout = (RelativeLayout) crowdfunding_list_foot.findViewById(R.id.crowdfunding_list_more_relativelayout);
        tv_crowdfunding_list_more = (TextView) crowdfunding_list_foot.findViewById(R.id.crowdfunding_list_more);
        iv_crowdfunding_list_more = (ImageView) crowdfunding_list_foot.findViewById(R.id.crowdfunding_list_more_iv);
        crowdfunding_list_more_relativelayout.setOnClickListener(listener);

        tv_classify_crowdfunding_banner = (TextView) (parent.findViewById(R.id.tv_classify_crowdfunding_banner));
        tv_crowdfunding_platform_name = (TextView) (parent.findViewById(R.id.tv_crowdfunding_platform_name));
        tv_crowdfunding_platform_description = (TextView) (parent.findViewById(R.id.tv_crowdfunding_platform_description));
        iv_crowdfunding_platform_logo = (ImageView) (parent.findViewById(R.id.iv_crowdfunding_platform_logo));

        crowdfunding_list = (RecyclerView) findViewById(R.id.crowdfunding_list);
        wuchou_list = (RecyclerView) findViewById(R.id.wuchou_list);

        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);

        refreshLayout = (SmartRefreshLayout) findViewById(R.id.crowd_refresh_layout);
        refreshLayout.setRefreshHeader(new MaterialHeader(this).setColorSchemeColors(0xfff95353));
        refreshLayout.setEnableHeaderTranslationContent(false);
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                refreshlayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isRefresh = true;
                        initData();
                    }
                }, 1000);
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {
        isRefresh = false;
        initData();
    }


    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.crowdfunding_list_more_relativelayout:
                    if (crowdFundingBidListBeanXList.size() > 3) {
                        for (int i = 3; i < crowdFundingBidListBeanXList.size(); i++) {
                            tempCrowdFundingBidListBeanXList.add(crowdFundingBidListBeanXList.get(i));
                        }
                        crowdFundingSimpleAdapter.notifyDataSetChanged();
                        tv_crowdfunding_list_more.setText("收起");
                        iv_crowdfunding_list_more.setImageResource(R.mipmap.ic_up_blue);
                        crowdfunding_list_more_relativelayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tempCrowdFundingBidListBeanXList.clear();
                                if (crowdFundingBidListBeanXList.size() > 3) {
                                    for (int i = 0; i < 3; i++) {
                                        tempCrowdFundingBidListBeanXList.add(crowdFundingBidListBeanXList.get(i));
                                    }
                                    crowdFundingSimpleAdapter.notifyDataSetChanged();
                                    tv_crowdfunding_list_more.setText("更多");
                                    iv_crowdfunding_list_more.setImageResource(R.mipmap.ic_down_blue);
                                    crowdfunding_list_more_relativelayout.setOnClickListener(listener);
                                }
                            }
                        });
                    }
                    break;
                case R.id.wuchou_list_more_relativelayout:
                    if (wuchouBidListBeanXList.size() > 3) {
                        for (int i = 3; i < wuchouBidListBeanXList.size(); i++) {
                            tempWuchouBidListBeanXList.add(wuchouBidListBeanXList.get(i));
                        }
                        adapter.notifyDataSetChanged();
                        tv_wuchou_list_more.setText("收起");
                        iv_wuchou_list_more.setImageResource(R.mipmap.ic_up_blue);
                        wuchou_list_more_relativelayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tempWuchouBidListBeanXList.clear();
                                if (wuchouBidListBeanXList.size() > 3) {
                                    for (int i = 0; i < 3; i++) {
                                        tempWuchouBidListBeanXList.add(wuchouBidListBeanXList.get(i));
                                    }
                                    adapter.notifyDataSetChanged();
                                    tv_wuchou_list_more.setText("更多");
                                    iv_wuchou_list_more.setImageResource(R.mipmap.ic_down_blue);
                                    wuchou_list_more_relativelayout.setOnClickListener(listener);
                                }
                            }
                        });
                    }
                    break;
            }
        }
    };


    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_crowdfunding:
                finish();
                break;
            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                include_load_error.setVisibility(View.GONE);
                doBusiness(this);
                break;
        }

    }

    private void initData() {
        //已解决！！！！！！
        //第二次进入banner文字和图片无内容
        OkGo.get(ApiUtils.getClassifyBanner(getApplicationContext(), CROWDFUNDING)).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Banner banner = ParseUtils.parseByGson(s, Banner.class);
                tv_classify_crowdfunding_banner.setText(banner.getTitle());
                Glide.with(getApplicationContext()).load(banner.getUrl())
                        .into(new SimpleTarget<GlideDrawable>() {
                            @Override
                            public void onResourceReady(GlideDrawable resource,
                                                        GlideAnimation<? super GlideDrawable> glideAnimation) {
                                tv_classify_crowdfunding_banner.setBackground(resource);
                            }
                        });
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);

            }
        });

        OkGo.get(ApiUtils.getHomeCrowdfunding(CrowdFundingActivity.this)).execute(new StringCallback() {

            @Override
            public void onSuccess(String s, Call call, Response response) {
                HomeCrowdFunding homeCrowdFunding = ParseUtils.parseByGson(s, HomeCrowdFunding.class);
                tv_crowdfunding_platform_name.setText(homeCrowdFunding.getOperation_data().getPlatform_name());
                tv_crowdfunding_platform_description.setText(homeCrowdFunding.getOperation_data().getDescription());
                if (Util.isOnMainThread()) {
                    Glide.with(getApplicationContext()).load(homeCrowdFunding.getOperation_data().getPlatform_logo()).into(new SimpleTarget<GlideDrawable>() {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                            iv_crowdfunding_platform_logo.setImageDrawable(resource);
                        }
                    });
                }
                wuchouBidListBeanXList = homeCrowdFunding.getWuchou().getBid_list();

//                if (wuchouBidListBeanXList.size() > 3) {
//                    for (int i = 0; i < 3; i++) {
//                        tempWuchouBidListBeanXList.add(wuchouBidListBeanXList.get(i));
//                    }
//                    adapter = new WuchouSimpleAdapter(tempWuchouBidListBeanXList);
//                } else {
//                    adapter = new WuchouSimpleAdapter(wuchouBidListBeanXList);
//                }

                if (adapter == null) {
                    if (wuchouBidListBeanXList.size() > 3) {
                        for (int i = 0; i < 3; i++) {
                            tempWuchouBidListBeanXList.add(wuchouBidListBeanXList.get(i));
                        }
                        adapter = new WuchouSimpleAdapter(tempWuchouBidListBeanXList);
                    } else {
                        adapter = new WuchouSimpleAdapter(wuchouBidListBeanXList);
                    }
                    adapter.setOnItemClickListener(CrowdFundingActivity.this);
                    adapter.bindToRecyclerView(wuchou_list);
                } else {
                    if (wuchouBidListBeanXList.size() > 3) {
                        tempWuchouBidListBeanXList.clear();
                        for (int i = 0; i < 3; i++) {
                            tempWuchouBidListBeanXList.add(wuchouBidListBeanXList.get(i));
                        }
                        adapter.setNewData(tempWuchouBidListBeanXList);
                    } else {
                        adapter.setNewData(wuchouBidListBeanXList);
                    }
                }

                //解决srollview和recycleview滑动冲突
                wuchou_list.setNestedScrollingEnabled(false);
                wuchou_list.setLayoutManager(new WrapContentLinearLayoutManager(CrowdFundingActivity.this));
//                adapter.setOnItemClickListener(CrowdFundingActivity.this);
//                adapter.bindToRecyclerView(wuchou_list);
                adapter.removeAllHeaderView();
                adapter.setHeaderView(parent);
                wuchou_platform_name.setText("维C理财");
                adapter.addHeaderView(wuchou_list_head);
                crowdfunding_list_foot.setOnClickListener(listener);
                adapter.setFooterView(wuchou_list_foot);


                crowdFundingBidListBeanXList = homeCrowdFunding.getCrowdfunding().getBid_list();
//                if (wuchouBidListBeanXList.size() > 3) {
//                    for (int i = 0; i < 3; i++) {
//                        tempCrowdFundingBidListBeanXList.add(crowdFundingBidListBeanXList.get(i));
//                    }
//                    crowdFundingSimpleAdapter = new CrowdFundingSimpleAdapter(tempCrowdFundingBidListBeanXList);
//                } else {
//                    crowdFundingSimpleAdapter = new CrowdFundingSimpleAdapter(crowdFundingBidListBeanXList);
//                }

                if (crowdFundingSimpleAdapter == null) {
                    if (crowdFundingBidListBeanXList.size() > 3) {
                        for (int i = 0; i < 3; i++) {
                            tempCrowdFundingBidListBeanXList.add(crowdFundingBidListBeanXList.get(i));
                        }
                        crowdFundingSimpleAdapter = new CrowdFundingSimpleAdapter(tempCrowdFundingBidListBeanXList);
                    } else {
                        crowdFundingSimpleAdapter = new CrowdFundingSimpleAdapter(crowdFundingBidListBeanXList);
                    }
                    crowdFundingSimpleAdapter.setOnItemClickListener(CrowdFundingActivity.this);
                    crowdFundingSimpleAdapter.bindToRecyclerView(crowdfunding_list);
                } else {
                    if (crowdFundingBidListBeanXList.size() > 3) {
                        tempCrowdFundingBidListBeanXList.clear();
                        for (int i = 0; i < 3; i++) {
                            tempCrowdFundingBidListBeanXList.add(crowdFundingBidListBeanXList.get(i));
                        }
                        crowdFundingSimpleAdapter.setNewData(tempCrowdFundingBidListBeanXList);
                    } else {
                        crowdFundingSimpleAdapter.setNewData(crowdFundingBidListBeanXList);
                    }
                }

                //解决srollview和recycleview滑动冲突
                crowdfunding_list.setNestedScrollingEnabled(false);
                crowdfunding_list.setLayoutManager(new WrapContentLinearLayoutManager(CrowdFundingActivity.this));
//                crowdFundingSimpleAdapter.setOnItemClickListener(CrowdFundingActivity.this);
//                crowdFundingSimpleAdapter.bindToRecyclerView(crowdfunding_list);
                crowdfunding_platform_name.setText("多彩投");
                crowdFundingSimpleAdapter.removeAllHeaderView();
                crowdFundingSimpleAdapter.setHeaderView(crowdfunding_list_head);
                crowdfunding_list_foot.setOnClickListener(listener);
                crowdFundingSimpleAdapter.setFooterView(crowdfunding_list_foot);

                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.GONE);

                if (isRefresh) {
                    refreshLayout.finishRefresh();
                    tv_wuchou_list_more.setText("更多");
                    iv_wuchou_list_more.setImageResource(R.mipmap.ic_down_blue);
                    wuchou_list_more_relativelayout.setOnClickListener(listener);

                    tv_crowdfunding_list_more.setText("更多");
                    iv_crowdfunding_list_more.setImageResource(R.mipmap.ic_down_blue);
                    crowdfunding_list_more_relativelayout.setOnClickListener(listener);
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                NormalUtils.showToast(CrowdFundingActivity.this
                        , R.string.net_error);
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.VISIBLE);

                if (isRefresh) {
                    refreshLayout.finishRefresh();
                }
            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        //众筹
        if (adapter instanceof CrowdFundingSimpleAdapter) {
            Bundle args = new Bundle();
            args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,
                    ((CrowdFundingSimpleAdapter) adapter).getItem(position).getBid_id());
            startActivity(BidCrowdFundingActivity.class, args);
        }
        //Wuchou
        if (adapter instanceof WuchouSimpleAdapter) {
            Bundle args = new Bundle();
            args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,
                    ((WuchouSimpleAdapter) adapter).getItem(position).getBid_id());
            args.putString(BidFixedActivity.BID_NAME,
                    ((WuchouSimpleAdapter) adapter).getItem(position).getName());
            startActivity(BidVcActivity.class, args);
        }
    }

    class WuchouSimpleAdapter extends BaseQuickAdapter<HomeCrowdFunding.WuchouBean.BidListBeanX, BaseViewHolder> {

        public WuchouSimpleAdapter(@Nullable List<HomeCrowdFunding.WuchouBean.BidListBeanX> data) {
            super(R.layout.classify_listitem_wuchou, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeCrowdFunding.WuchouBean.BidListBeanX item) {
            helper.setText(R.id.wuchou_platform_bid_name, item.getPlatform().getName() + " | " + item.getName())
                    .setText(R.id.wuchou_bonus_interest, "返利" + item.getBonus_interest() + "%")
                    .setText(R.id.wuchou_progress_rate_number, item.getProgress() + "%")
                    .setText(R.id.wuchou_status_str, "期限:" + item.getMin_duration() + "-" + item.getMax_duration() + item.getDuration_unit() + "  |  " + "总额:" + item.getTotal_amount() + "元")
                    //.setText(R.id.wuchou_remain_time, "")
                    //.setText(R.id.wuchou_total_amount, "总额：" + item.getTotal_amount())
                    .setProgress(R.id.wuchou_progress_rate, (int) (Float.parseFloat(item.getProgress())));
            helper.getView(R.id.wuchou_remain_time).setVisibility(View.GONE);
            helper.getView(R.id.wuchou_total_amount).setVisibility(View.GONE);
            if (item.isIs_no_limit()) {
                helper.setText(R.id.wuchou_min_invest_amount, item.getMin_interest() + "%-" + "上不封顶");
            } else {
                helper.setText(R.id.wuchou_min_invest_amount, item.getMin_interest() + "%-" + item.getMax_interest() + "%");
            }

            ImageView bid_img = (helper.getView(R.id.wuchou_bid_img));
            Glide.with(getApplicationContext()).load(item.getBid_image()).into(bid_img);
        }
    }

    class CrowdFundingSimpleAdapter extends BaseQuickAdapter<HomeCrowdFunding.CrowdfundingBean.BidListBean, BaseViewHolder> {

        public CrowdFundingSimpleAdapter(@Nullable List<HomeCrowdFunding.CrowdfundingBean.BidListBean> data) {
            super(R.layout.classify_listitem_crowdfunding, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeCrowdFunding.CrowdfundingBean.BidListBean item) {
            helper.setText(R.id.status_str, item.getStatus_str());
            TextView statusTv = helper.getView(R.id.status_str);
            Drawable statusDrawable = CrowdFundingActivity.this.getResources().getDrawable(R.mipmap.crowd_funding_status);
            //statusDrawable.setBounds(0, 0, statusDrawable.getMinimumWidth(), statusDrawable.getMinimumHeight());
            statusDrawable.setBounds(0, 0, 24, 32);
            statusTv.setCompoundDrawables(statusDrawable, null, null, null);
            statusTv.setCompoundDrawablePadding(4);

            helper.setText(R.id.remain_time, "剩余" + item.getRemain_time());
            TextView remain_time = helper.getView(R.id.remain_time);
            Drawable remain_timeDrawable = CrowdFundingActivity.this.getResources().getDrawable(R.mipmap.crowd_funding_remain_time);
            //remain_timeDrawable.setBounds(0, 0, remain_timeDrawable.getMinimumWidth(), remain_timeDrawable.getMinimumHeight());
            remain_timeDrawable.setBounds(0, 0, 32, 32);
            remain_time.setCompoundDrawables(remain_timeDrawable, null, null, null);
            remain_time.setCompoundDrawablePadding(4);

            helper.setText(R.id.total_amount, item.getTotal_amount() + "元");
            TextView total_amount = helper.getView(R.id.total_amount);
            Drawable total_amountDrawable = CrowdFundingActivity.this.getResources().getDrawable(R.mipmap.crowd_funding_total_amount);
            //total_amountDrawable.setBounds(0, 0, total_amountDrawable.getMinimumWidth(), total_amountDrawable.getMinimumHeight());
            total_amountDrawable.setBounds(0, 0, 32, 32);
            total_amount.setCompoundDrawables(total_amountDrawable, null, null, null);
            total_amount.setCompoundDrawablePadding(4);

            helper.setText(R.id.platform_bid_name, item.getPlatform().getName() + " | " + item.getName())
                    .setText(R.id.min_invest_amount, "￥" + item.getMin_invest_amount() + "元起")
                    .setText(R.id.bonus_interest, "返利" + item.getPlatform_bonus_interest() + "%")
                    .setText(R.id.progress_rate_number, item.getFinancing_rate() + "%")
                    .setProgress(R.id.progress_rate, (int) (Float.parseFloat(item.getFinancing_rate())));
            ImageView bid_img = (helper.getView(R.id.bid_img));
            Glide.with(getApplicationContext()).load(item.getBid_image_small()).into(bid_img);
        }
    }

}



