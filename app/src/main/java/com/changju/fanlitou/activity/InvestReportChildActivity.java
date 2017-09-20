package com.changju.fanlitou.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.account.AccountCollectionDetailsActivity;
import com.changju.fanlitou.activity.account.DailyRebateActivity;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.account.investreport.AbroadInvestReport;
import com.changju.fanlitou.bean.account.investreport.CrowdfundingInvestReport;
import com.changju.fanlitou.bean.account.investreport.CurrentInvestReport;
import com.changju.fanlitou.bean.account.investreport.FixedInvestReport;
import com.changju.fanlitou.bean.account.investreport.GoldInvestReport;
import com.changju.fanlitou.bean.account.investreport.InsuranceInvestReport;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.changju.fanlitou.util.UserUtils;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/2.
 */

public class InvestReportChildActivity extends BaseActivity {

    //loading&error
    private View include;
    private View include_load_error;
    //分级列表
    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;

    private PieChart mChart;
    private RelativeLayout parent;
    private RecyclerView recycler_fund_list;

    //第二种圆环中间的样式
    private LinearLayout type_1;
    private TextView text_child1, text_child2;
    private TextView receiving_principal, receiving_interest;

    //第二种圆环中间的样式
    private LinearLayout type_2;
    private TextView type2_title, type2_context;

    private TextView child_title;
    //存列表的数据
    ArrayList<MultiItemEntity> list;
    ExpandableItemAdapter adapter;

    //返回品类类型只有一种 固收
    private FixedInvestReport fixedInvestReport;
    private FixedInvestReport.P2pDataBean p2pDataBean;
    private List<FixedInvestReport.P2pDataBean.ChartDataBean> fixedChartDataBeanList;

    //返回品类类型只有一种 黄金
    private GoldInvestReport goldInvestReport;
    private GoldInvestReport.GoldDataBean goldDataBean;
    private List<GoldInvestReport.GoldDataBean.ChartDataBean> goldChartDataBeanList;

    //返回品类类型只有一种 海外
    private AbroadInvestReport abroadInvestReport;
    private AbroadInvestReport.AbroadDataBean abroadDataBean;
    private List<AbroadInvestReport.AbroadDataBean.ChartDataBean> abroadChartDataBeanList;

    //返回品类类型只有一种 众筹
    private CrowdfundingInvestReport crowdfundingInvestReport;
    private CrowdfundingInvestReport.CrowdfundingDataBean crowdfundingDataBean;
    private List<CrowdfundingInvestReport.CrowdfundingDataBean.ChartDataBean> crowdfundingChartDataBeanList;

    //返回品类类型只有一种 活期
    private CurrentInvestReport currentInvestReport;
    private CurrentInvestReport.CurrentDataBean currentDataBean;
    private List<CurrentInvestReport.CurrentDataBean.ChartDataBean> currentChartDataBeanList;

    //返回品类类型只有一种 保险
    private InsuranceInvestReport insuranceInvestReport;
    private InsuranceInvestReport.InsuranceDataBean insuranceDataBean;
    private List<InsuranceInvestReport.InsuranceDataBean.ChartDataBean> insuranceChartDataBeanList;

    //这里的type是从投资报表fragment里面传过来，"固收" "保险"。。。汉字
    private String type;
    public static String INVEST_TYPE = "invest_type";

    @Override
    public void initParams(Bundle params) {
        type = params.getString(INVEST_TYPE);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_investreport_child;
    }

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "账本-投资报表-二级页");

        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        parent = (RelativeLayout) inflater.inflate(R.layout.fund_list_piechart_child, null);
        mChart = (PieChart) parent.findViewById(R.id.fund_list_piechart);


        type_1 = (LinearLayout) parent.findViewById(R.id.type_1);
        text_child1 = (TextView) parent.findViewById(R.id.text_child1);
        text_child2 = (TextView) parent.findViewById(R.id.text_child2);
        receiving_interest = (TextView) parent.findViewById(R.id.receiving_interest);
        receiving_principal = (TextView) parent.findViewById(R.id.receiving_principal);

        type_2 = (LinearLayout) parent.findViewById(R.id.type_2);
        type2_title = (TextView) parent.findViewById(R.id.type2_title);
        type2_context = (TextView) parent.findViewById(R.id.type2_context);

        //根据跳转过来的列表项不同，标题栏显示不同的标题
        child_title = (TextView) findViewById(R.id.child_title);
        child_title.setText(type);
        ((ImageView) findViewById(R.id.iv_back_invest_child)).setOnClickListener(this);
        recycler_fund_list = (RecyclerView) findViewById(R.id.recycler_investreport_child);
        //设置piechat的整体样式
        initPieChart();
    }

    @Override
    public void doBusiness(Context mContext) {
        initData(InvestReportChildActivity.this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_invest_child:
                finish();
                break;
            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                include_load_error.setVisibility(View.GONE);
                doBusiness(this);
                break;
        }
    }

    //设置piechat的整体样式
    private void initPieChart() {
        /**
         * 圆环距离屏幕上下上下左右的距离
         */
        mChart.setExtraOffsets(10f, 10f, 10f, 10f);

        mChart.setUsePercentValues(true);

        mChart.getDescription().setEnabled(false);
        mChart.setDragDecelerationFrictionCoef(0.95f);
        /**
         * 是否显示圆环中间的洞
         */
        mChart.setDrawHoleEnabled(true);
        /**
         * 设置中间洞的颜色
         */
        mChart.setHoleColor(Color.WHITE);
        /**
         * 设置中间洞的半径 以下两者相同着内环无阴影效果
         */
        mChart.setHoleRadius(66f);
        mChart.setTransparentCircleRadius(66f);
        /**
         * 设置圆环透明度及半径
         */
        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);

        //0-三点钟反向
        //90六点钟方向 修改为从底部开始
        //-90 12点钟方向
        mChart.setRotationAngle(90);
        /**
         *触摸是否可以旋转以及松手后旋转的度数
         */
        mChart.setRotationEnabled(false);

        mChart.setHighlightPerTapEnabled(true);
        /**
         *设置各色块说明 此处设为不显示
         */
        mChart.getLegend().setEnabled(false);
        /**
         * 圆环动画效果
         */
        //这里当圆环内数据较多时 圆环内有空白显示 已解决
        mChart.animateY(800, Easing.EasingOption.EaseOutCirc);
        //mChart.animateY(1200, Easing.EasingOption.EaseInCubic);

    }

    public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

        public ExpandableItemAdapter(List<MultiItemEntity> data) {
            super(data);
            addItemType(TYPE_LEVEL_0, R.layout.recycler_item_investreport_child);
            //不同类型 二级标题不一样
            if (type.equals("固收")) {
                addItemType(TYPE_LEVEL_1, R.layout.recycler_item_investreport_child2lines_noredtext);
            }
            if (type.equals("众筹") || type.equals("海外") || type.equals("保险")) {
                addItemType(TYPE_LEVEL_1, R.layout.recycler_item_investreport_child2lines);
            }
            if (type.equals("活期") || type.equals("黄金")) {
                addItemType(TYPE_LEVEL_1, R.layout.recycler_item_investreport_child3lines);
            }
        }

        @Override
        protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {

            if (type.equals("固收")) {
                fillFixedList(holder, item);
            } else if (type.equals("众筹")) {
                fillCrowdfundingList(holder, item);
            } else if (type.equals("海外")) {
                fillAbroadList(holder, item);
            } else if (type.equals("保险")) {
                fillInsuranceList(holder, item);
            } else if (type.equals("活期")) {
                fillCurrentList(holder, item);
            } else if (type.equals("黄金")) {
                fillGoldList(holder, item);
            }
        }

        private void fillFixedList(final BaseViewHolder holder, final Object item) {
            switch (holder.getItemViewType()) {
                case TYPE_LEVEL_0:
                    final FixedInvestReport.P2pDataBean.ChartDataBean chartDataBeanFixed
                            = (FixedInvestReport.P2pDataBean.ChartDataBean) item;
                    holder.setText(R.id.tv_name_investreport, chartDataBeanFixed.getName())
                            .setImageResource(R.id.invest_report_arrow, chartDataBeanFixed.isExpanded()
                                    ? R.mipmap.ic_up_black : R.mipmap.ic_down_black)
                            .setText(R.id.tv_bid_count_investreport, "投资产品：" + chartDataBeanFixed.getBid_count() + "个")
                            .setText(R.id.tv_amount_investreport, "待收本息：" + chartDataBeanFixed.getAmount());
                    Glide.with(getApplicationContext()).load(chartDataBeanFixed.getLogo()).into((ImageView) holder.getView(R.id.iv_platform_icon));
                    holder.getView(R.id.rc_invest_report_child).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int pos = holder.getAdapterPosition();
                            if (chartDataBeanFixed.isExpanded()) {
                                collapse(pos);
                            } else {
                                expand(pos);
                                recycler_fund_list.smoothScrollToPosition(pos + 1);
                            }
                        }
                    });
                    break;
                case TYPE_LEVEL_1:
                    final FixedInvestReport.P2pDataBean.ChartDataBean.DetailListBean detailListBean
                            = (FixedInvestReport.P2pDataBean.ChartDataBean.DetailListBean) item;
                    holder.setText(R.id.start_date, "投资时间：" + detailListBean.getStart_date())
                            .setText(R.id.receiving_interest, detailListBean.getReceiving_interest() + "")
                            .setText(R.id.receiving_principal, detailListBean.getReceiving_principal() + "")
                            .setText(R.id.bid_name, detailListBean.getBid_name())
                            .setText(R.id.order_number, String.valueOf(detailListBean.getIndex() + 1));

                    holder.getView(R.id.root_view).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startToRepayDetail(fixedInvestReport.getType(), detailListBean.getBid_id());
                        }
                    });
                    break;
            }
        }

        private void fillCrowdfundingList(final BaseViewHolder holder, final Object item) {
            switch (holder.getItemViewType()) {
                case TYPE_LEVEL_0:
                    final CrowdfundingInvestReport.CrowdfundingDataBean.ChartDataBean chartDataBean
                            = (CrowdfundingInvestReport.CrowdfundingDataBean.ChartDataBean) item;
                    holder.setText(R.id.tv_name_investreport, chartDataBean.getName())
                            .setImageResource(R.id.invest_report_arrow, chartDataBean.isExpanded()
                                    ? R.mipmap.ic_up_black : R.mipmap.ic_down_black)
                            .setText(R.id.tv_bid_count_investreport, "投资产品：" + chartDataBean.getBid_count() + "个")
                            .setText(R.id.tv_amount_investreport, "投资本金：" + chartDataBean.getAmount());
                    Glide.with(getApplicationContext()).load(chartDataBean.getLogo()).into((ImageView) holder.getView(R.id.iv_platform_icon));
                    holder.getView(R.id.rc_invest_report_child).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int pos = holder.getAdapterPosition();
                            if (chartDataBean.isExpanded()) {
                                collapse(pos);
                            } else {
                                expand(pos);
                                recycler_fund_list.smoothScrollToPosition(pos + 1);
                            }
                        }
                    });
                    break;
                case TYPE_LEVEL_1:
                    final CrowdfundingInvestReport.CrowdfundingDataBean.ChartDataBean.DetailListBean detailListBean
                            = (CrowdfundingInvestReport.CrowdfundingDataBean.ChartDataBean.DetailListBean) item;
                    holder.setText(R.id.tv_time, "投资时间：" + detailListBean.getStart_date())
                            .setText(R.id.t1, detailListBean.getBid_name())
                            .setText(R.id.t2, detailListBean.getInvest_bonus() + "")
                            .setText(R.id.t3, "返利")
                            .setText(R.id.t4, detailListBean.getInvest_principal() + "")
                            .setText(R.id.t5, "本金")
                            .setText(R.id.order_number, String.valueOf(detailListBean.getIndex() + 1));

                    //多彩投不可点击
                    holder.getView(R.id.arrow_right).setVisibility(View.INVISIBLE);
                    holder.getView(R.id.root_view).setBackground(null);
                    break;
            }
        }

        private void fillAbroadList(final BaseViewHolder holder, final Object item) {
            switch (holder.getItemViewType()) {
                case TYPE_LEVEL_0:
                    final AbroadInvestReport.AbroadDataBean.ChartDataBean chartDataBean
                            = (AbroadInvestReport.AbroadDataBean.ChartDataBean) item;
                    holder.setText(R.id.tv_name_investreport, chartDataBean.getName())
                            .setImageResource(R.id.invest_report_arrow, chartDataBean.isExpanded()
                                    ? R.mipmap.ic_up_black : R.mipmap.ic_down_black)
                            .setText(R.id.tv_bid_count_investreport, "投资产品：" + chartDataBean.getBid_count() + "个")
                            .setText(R.id.tv_amount_investreport, "待收本息：" + chartDataBean.getAmount());
                    Glide.with(getApplicationContext()).load(chartDataBean.getLogo()).into((ImageView) holder.getView(R.id.iv_platform_icon));
                    holder.getView(R.id.rc_invest_report_child).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int pos = holder.getAdapterPosition();
                            if (chartDataBean.isExpanded()) {
                                collapse(pos);
                            } else {
                                expand(pos);
                                recycler_fund_list.smoothScrollToPosition(pos + 1);
                            }
                        }
                    });
                    break;
                case TYPE_LEVEL_1:
                    final AbroadInvestReport.AbroadDataBean.ChartDataBean.DetailListBean detailListBean
                            = (AbroadInvestReport.AbroadDataBean.ChartDataBean.DetailListBean) item;
                    holder.setText(R.id.tv_time, "投资时间：" + detailListBean.getStart_date())
                            .setText(R.id.t1, detailListBean.getBid_name())
                            .setText(R.id.t2, detailListBean.getReceiving_interest() + "")
                            .setText(R.id.t3, "待收利息")
                            .setText(R.id.t4, detailListBean.getReceiving_principal() + "")
                            .setText(R.id.t5, "待收本金")
                            .setText(R.id.order_number, String.valueOf(detailListBean.getIndex() + 1));

                    holder.getView(R.id.root_view).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            Bundle args = new Bundle();
//                            args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,
//                                    ((AbroadInvestReport.AbroadDataBean.ChartDataBean.DetailListBean) item).getBid_id());
//                            args.putString(DailyRebateActivity.class.getSimpleName(), "crowdfunding");
//                            InvestReportChildActivity.this.startActivity(DailyRebateActivity.class, args);

                            startToRepayDetail(abroadInvestReport.getType(), detailListBean.getBid_id());
                        }
                    });
                    break;
            }

        }

        private void fillInsuranceList(final BaseViewHolder holder, final Object item) {
            switch (holder.getItemViewType()) {
                case TYPE_LEVEL_0:
                    final InsuranceInvestReport.InsuranceDataBean.ChartDataBean chartDataBean
                            = (InsuranceInvestReport.InsuranceDataBean.ChartDataBean) item;
                    holder.setText(R.id.tv_name_investreport, chartDataBean.getName())
                            .setImageResource(R.id.invest_report_arrow, chartDataBean.isExpanded()
                                    ? R.mipmap.ic_up_black : R.mipmap.ic_down_black)
                            .setText(R.id.tv_bid_count_investreport, "投资产品：" + chartDataBean.getBid_count() + "个")
                            .setText(R.id.tv_amount_investreport, "投保费用：" + chartDataBean.getInsurance_fee());
                    Glide.with(getApplicationContext()).load(chartDataBean.getLogo()).into((ImageView) holder.getView(R.id.iv_platform_icon));
                    holder.getView(R.id.rc_invest_report_child).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int pos = holder.getAdapterPosition();
                            if (chartDataBean.isExpanded()) {
                                collapse(pos);
                            } else {
                                expand(pos);
                                recycler_fund_list.smoothScrollToPosition(pos + 1);
                            }
                        }
                    });
                    break;
                case TYPE_LEVEL_1:
                    final InsuranceInvestReport.InsuranceDataBean.ChartDataBean.DetailListBean detailListBean
                            = (InsuranceInvestReport.InsuranceDataBean.ChartDataBean.DetailListBean) item;
                    holder.setText(R.id.tv_time, "投资时间：" + detailListBean.getStart_date())
                            .setText(R.id.t1, detailListBean.getBid_name())
                            .setText(R.id.t2, detailListBean.getInsurance_fee())
                            .setText(R.id.t3, "保费")
                            .setText(R.id.t4, detailListBean.getInsurance_duration())
                            .setText(R.id.t5, "保期")
                            .setText(R.id.order_number, String.valueOf(detailListBean.getIndex() + 1));

                    holder.getView(R.id.arrow_right).setVisibility(View.INVISIBLE);
                    holder.getView(R.id.root_view).setBackground(null);
                    break;
            }
        }

        private void fillCurrentList(final BaseViewHolder holder, final Object item) {
            switch (holder.getItemViewType()) {
                case TYPE_LEVEL_0:
                    final CurrentInvestReport.CurrentDataBean.ChartDataBean chartDataBean
                            = (CurrentInvestReport.CurrentDataBean.ChartDataBean) item;
                    holder.setText(R.id.tv_name_investreport, chartDataBean.getName())
                            .setImageResource(R.id.invest_report_arrow, chartDataBean.isExpanded()
                                    ? R.mipmap.ic_up_black : R.mipmap.ic_down_black)
                            .setText(R.id.tv_bid_count_investreport, "投资产品：" + chartDataBean.getBid_count() + "个")
                            .setText(R.id.tv_amount_investreport, "持有本金：" + chartDataBean.getTotal_principal());
                    Glide.with(getApplicationContext()).load(chartDataBean.getLogo()).into((ImageView) holder.getView(R.id.iv_platform_icon));
                    holder.getView(R.id.rc_invest_report_child).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int pos = holder.getAdapterPosition();
                            if (chartDataBean.isExpanded()) {
                                collapse(pos);
                            } else {
                                expand(pos);
                                recycler_fund_list.smoothScrollToPosition(pos + 1);
                            }
                        }
                    });
                    break;
                case TYPE_LEVEL_1:
                    final CurrentInvestReport.CurrentDataBean.ChartDataBean.DetailListBean detailListBean
                            = (CurrentInvestReport.CurrentDataBean.ChartDataBean.DetailListBean) item;
                    holder.setText(R.id.tv_time, "投资时间：" + detailListBean.getStart_date())
                            .setText(R.id.t1, detailListBean.getBid_name())
                            .setText(R.id.t2, "持有本金：" + detailListBean.getTotal_principal())
                            .setText(R.id.t4, detailListBean.getYesterday_income() + "")
                            .setText(R.id.t5, "昨日收益")
                            .setText(R.id.t6, detailListBean.getTotal_income() + "")
                            .setText(R.id.t7, "累计收益")
                            .setText(R.id.order_number, String.valueOf(detailListBean.getIndex() + 1));

                    holder.getView(R.id.root_view).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startToRepayDetail("current".equals(currentInvestReport.getType()) ?
                                            "p2p_current" : currentInvestReport.getType(),
                                    detailListBean.getBid_id());
                        }
                    });
                    break;
            }
        }

        private void fillGoldList(final BaseViewHolder holder, final Object item) {
            switch (holder.getItemViewType()) {
                case TYPE_LEVEL_0:
                    final GoldInvestReport.GoldDataBean.ChartDataBean chartDataBean
                            = (GoldInvestReport.GoldDataBean.ChartDataBean) item;
                    holder.setText(R.id.tv_name_investreport, chartDataBean.getName())
                            .setImageResource(R.id.invest_report_arrow, chartDataBean.isExpanded()
                                    ? R.mipmap.ic_up_black : R.mipmap.ic_down_black)
                            .setText(R.id.tv_bid_count_investreport, "投资产品：" + chartDataBean.getBid_count() + "个")
                            .setText(R.id.tv_amount_investreport, "持有克重：" + chartDataBean.getHold_weight());
                    Glide.with(getApplicationContext()).load(chartDataBean.getLogo()).into((ImageView) holder.getView(R.id.iv_platform_icon));
                    holder.getView(R.id.rc_invest_report_child).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int pos = holder.getAdapterPosition();
                            if (chartDataBean.isExpanded()) {
                                collapse(pos);
                            } else {
                                expand(pos);
                                recycler_fund_list.smoothScrollToPosition(pos + 1);
                            }
                        }
                    });
                    break;
                case TYPE_LEVEL_1:
                    final GoldInvestReport.GoldDataBean.ChartDataBean.DetailListBean detailListBean
                            = (GoldInvestReport.GoldDataBean.ChartDataBean.DetailListBean) item;
                    if (detailListBean.getType().equals("gold_current")) {
                        holder.setText(R.id.tv_time, "投资时间：" + detailListBean.getStart_date())
                                .setText(R.id.t1, detailListBean.getBid_name())
                                .setText(R.id.t2, "持有克重：" + detailListBean.getHold_weight())
                                .setText(R.id.t4, detailListBean.getYesterday_income() + "")
                                .setText(R.id.t5, "昨日收益")
                                .setText(R.id.t6, detailListBean.getTotal_income() + "")
                                .setText(R.id.t7, "累计收益")
                                .setText(R.id.order_number, String.valueOf(detailListBean.getIndex() + 1));
                    } else if (detailListBean.getType().equals("gold_fix")) {
                        holder.setText(R.id.tv_time, "投资时间：" + detailListBean.getStart_date())
                                .setText(R.id.t1, detailListBean.getBid_name())
                                .setText(R.id.t2, "持有克重：" + detailListBean.getHold_weight())
                                .setText(R.id.t4, detailListBean.getReceiving_interest() + "")
                                .setText(R.id.t5, "待收利息")
                                .setText(R.id.t6, detailListBean.getReceiving_principal() + "")
                                .setText(R.id.t7, "待收本金")
                                .setText(R.id.order_number, String.valueOf(detailListBean.getIndex() + 1));
                    }

                    holder.getView(R.id.root_view).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startToRepayDetail("gold_current".equals(detailListBean.getType()) ?
                                            "gold_current" : goldInvestReport.getType(),
                                    detailListBean.getBid_id());
                        }
                    });
                    break;
            }
        }

    }

    public void initData(final Context context) {
        //子项为两行 无红色
        if (type.equals("固收")) {
            initFixedData(context);
        }
        //子项为两行 有红色
        else if (type.equals("众筹")) {
            initCrowdfundingData(context);
        } else if (type.equals("海外")) {
            initAbroadData(context);
        } else if (type.equals("保险")) {
            initInsuranceData(context);
        }
        //子项为三行
        else if (type.equals("活期")) {
            initCurrentData(context);
        } else if (type.equals("黄金")) {
            initGoldData(context);
        }
    }

    //返回的只有固收一种类型
    private void initFixedData(final Context context) {
        //请求网络  获取固收子页面数据
        OkGo.get(ApiUtils.getInvestReport(InvestReportChildActivity.this, "p2p")).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {

                fixedInvestReport = ParseUtils.parseByGson(s, FixedInvestReport.class);
                p2pDataBean = fixedInvestReport.getP2p_data();
                fixedChartDataBeanList = p2pDataBean.getChart_data();

                //初始化 返回仅固收一种类型的页面

                //初始化圆环和圆环内的数据
                if (fixedChartDataBeanList != null) {
                    PieData pieData = getFixedOnlyPieData(fixedChartDataBeanList, InvestReportChildActivity.this);
                    mChart.setData(pieData);
                    //未登录时本金和利息都显示为0
                    if (!UserUtils.isLogin(InvestReportChildActivity.this)) {
                        receiving_interest.setText("0");
                        receiving_principal.setText("0");
                    } else {
                        receiving_interest.setText(p2pDataBean.getReceiving_interest() + "");
                        receiving_principal.setText(p2pDataBean.getReceiving_principal() + "");
                    }
                }
                //初始化列表的数据
                list = generateData(fixedChartDataBeanList);
                if (adapter == null) {
                    adapter = new ExpandableItemAdapter(list);
                    adapter.setHeaderView(parent);
                    adapter.bindToRecyclerView(recycler_fund_list);
                    // important! setLayoutManager should be called after setAdapter
                    recycler_fund_list.setLayoutManager(new WrapContentLinearLayoutManager(context));
                    //设置为全部打开分栏
                    //adapter.expandAll();
                }
                expandOneItem();
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.GONE);
            }


            private ArrayList<MultiItemEntity> generateData(List<FixedInvestReport.P2pDataBean.ChartDataBean> fixedChartDataBeanList) {
                ArrayList<MultiItemEntity> res = new ArrayList<>();
                for (int i = 0; i < fixedChartDataBeanList.size(); i++) {
                    FixedInvestReport.P2pDataBean.ChartDataBean chartDataBean = fixedChartDataBeanList.get(i);
                    for (int j = 0; j < chartDataBean.getDetail_list().size(); j++) {
                        FixedInvestReport.P2pDataBean.ChartDataBean.DetailListBean child = chartDataBean.getDetail_list().get(j);
                        chartDataBean.addSubItem(child);
                        child.setIndex(j);
                    }
                    res.add(chartDataBean);
                }
                return res;
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                NormalUtils.showToast(InvestReportChildActivity.this, R.string.net_error);
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.VISIBLE);
            }
        });
    }

    private PieData getFixedOnlyPieData(List<FixedInvestReport.P2pDataBean.ChartDataBean> fixedChartDataBeanList,
                                        Context context) {

        //valuesOfNameAndNumner用来表示封装每个饼块的实际数据
        ArrayList<PieEntry> valuesOfNameAndNumner = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList<>();

        //如果没有登录 或者 获取的数据为0 则平分饼状图
        if (!UserUtils.isLogin(context) || fixedChartDataBeanList.size() == 0) {
            initNoDataChat(valuesOfNameAndNumner, colors);
        } else {
            initFixedDataChat(fixedChartDataBeanList, valuesOfNameAndNumner, colors);
        }

        //将名字，数字，颜色 放入数据集合
        PieDataSet pieDataSet = new PieDataSet(valuesOfNameAndNumner, null);
        pieDataSet.setColors(colors);

        pieDataSet.setSelectionShift(0); // 选中态多出的长度
        pieDataSet.setSliceSpace(1f); //设置个饼状图之间的距离
        PieData pieData = new PieData(pieDataSet);
        //设置默认的每个色块上的百分比字体隐藏
        pieDataSet.setValueTextSize(0f);
        return pieData;
    }

    private void initFixedDataChat(List<FixedInvestReport.P2pDataBean.ChartDataBean> fixedChartDataBeanList,
                                   ArrayList<PieEntry> valuesOfNameAndNumner,
                                   ArrayList<Integer> colors) {
        for (int i = 0; i < fixedChartDataBeanList.size(); i++) {
            //如果每部分小于10%，则把每部分设为10%
            float percentOfPerPie = fixedChartDataBeanList.get(i).getPercent();
            if (percentOfPerPie < 10f) {
                percentOfPerPie = 10f;
            }
            if (i % 2 == 0) {
                colors.add(getResources().getColor(R.color.chart_orange));
            } else {
                colors.add(getResources().getColor(R.color.chart_orange2));
            }
//            valuesOfNameAndNumner.add(
//                    new PieEntry(percentOfPerPie, fixedChartDataBeanList.get(i).getName()));
            //在这里显示固收时 可能因为种类数较多 圆环显示有bug bug已修复 原因见下面几行代码
            final PieEntry pieEntry = new PieEntry(percentOfPerPie, "", null);
            //fix bug 是因为返回的图片大小不一 要加上一个参数
            String logo_white = fixedChartDataBeanList.get(i).getLogo_white() + "?imageView2/2/w/72/h/72";
            valuesOfNameAndNumner.add(pieEntry);
            Glide.with(getApplicationContext()).load(logo_white)
                    .into(new SimpleTarget<GlideDrawable>() {
                        @Override
                        public void onResourceReady(GlideDrawable resource,
                                                    GlideAnimation<? super GlideDrawable> glideAnimation) {
                            //resource.setBounds(0,0,1,1);
                            pieEntry.setIcon(resource);
                        }
                    });
        }
    }

    //返回的只有众筹一种类型
    private void initCrowdfundingData(final Context context) {
        //请求网络  获取固收子页面数据
        OkGo.get(ApiUtils.getInvestReport(InvestReportChildActivity.this, "crowdfunding")).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {

                crowdfundingInvestReport = ParseUtils.parseByGson(s, CrowdfundingInvestReport.class);
                crowdfundingDataBean = crowdfundingInvestReport.getCrowdfunding_data();
                crowdfundingChartDataBeanList = crowdfundingDataBean.getChart_data();

                //初始化 返回仅固收一种类型的页面

                //初始化圆环和圆环内的数据
                if (crowdfundingChartDataBeanList != null) {
                    PieData pieData = getCrowdfundingOnlyPieData(crowdfundingChartDataBeanList, InvestReportChildActivity.this);
                    mChart.setData(pieData);
                    //众筹 黄金 的圆环内样式与其他四种不同 type2
                    type_1.setVisibility(View.GONE);
                    type_2.setVisibility(View.VISIBLE);
                    type2_title.setText("投资本金(元)");
                    if (UserUtils.isLogin(InvestReportChildActivity.this)) {
                        type2_context.setText(crowdfundingDataBean.getInvest_principal() + "");
                    } else {
                        type2_context.setText("0");
                    }
                }
                //初始化列表的数据
                list = generateData(crowdfundingChartDataBeanList);
                if (adapter == null) {
                    adapter = new ExpandableItemAdapter(list);
                    adapter.setHeaderView(parent);
                    adapter.bindToRecyclerView(recycler_fund_list);
                    recycler_fund_list.setAdapter(adapter);
                    // important! setLayoutManager should be called after setAdapter
                    recycler_fund_list.setLayoutManager(new WrapContentLinearLayoutManager(context));
                    //设置为全部打开分栏
                    //adapter.expandAll();
                }
                expandOneItem();
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.GONE);
            }


            private ArrayList<MultiItemEntity> generateData(List<CrowdfundingInvestReport.CrowdfundingDataBean.ChartDataBean> crowdfundingChartDataBeanList) {
                ArrayList<MultiItemEntity> res = new ArrayList<>();
                for (int i = 0; i < crowdfundingChartDataBeanList.size(); i++) {
                    CrowdfundingInvestReport.CrowdfundingDataBean.ChartDataBean chartDataBean = crowdfundingChartDataBeanList.get(i);
                    for (int j = 0; j < chartDataBean.getDetail_list().size(); j++) {
                        CrowdfundingInvestReport.CrowdfundingDataBean.ChartDataBean.DetailListBean child = chartDataBean.getDetail_list().get(j);
                        chartDataBean.addSubItem(child);
                        child.setIndex(j);
                    }
                    res.add(chartDataBean);
                }
                return res;
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                NormalUtils.showToast(InvestReportChildActivity.this, R.string.net_error);
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.VISIBLE);
            }
        });
    }

    private PieData getCrowdfundingOnlyPieData(List<CrowdfundingInvestReport.CrowdfundingDataBean.ChartDataBean> crowdfundingChartDataBeanList,
                                               Context context) {

        //valuesOfNameAndNumner用来表示封装每个饼块的实际数据
        ArrayList<PieEntry> valuesOfNameAndNumner = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList<>();

        //如果没有登录 或者 获取的数据为0
        if (!UserUtils.isLogin(context) || crowdfundingChartDataBeanList.size() == 0) {
            initNoDataChat(valuesOfNameAndNumner, colors);
        } else {
            initCrowdfundingDataChat(crowdfundingChartDataBeanList, valuesOfNameAndNumner, colors);
        }

        //将名字，数字，颜色 放入数据集合
        PieDataSet pieDataSet = new PieDataSet(valuesOfNameAndNumner, null);
        pieDataSet.setColors(colors);

        pieDataSet.setSelectionShift(0); // 选中态多出的长度
        pieDataSet.setSliceSpace(1f); //设置个饼状图之间的距离
        PieData pieData = new PieData(pieDataSet);
        //设置默认的每个色块上的百分比字体隐藏
        pieDataSet.setValueTextSize(0f);
        return pieData;
    }

    private void initCrowdfundingDataChat(List<CrowdfundingInvestReport.CrowdfundingDataBean.ChartDataBean> crowdfundingChartDataBeanList,
                                          ArrayList<PieEntry> valuesOfNameAndNumner,
                                          ArrayList<Integer> colors) {
        for (int i = 0; i < crowdfundingChartDataBeanList.size(); i++) {
            //如果每部分小于10%，则把每部分设为10%
            float percentOfPerPie = crowdfundingChartDataBeanList.get(i).getPercent();
            if (percentOfPerPie < 10f) {
                percentOfPerPie = 10f;
            }
            if (i % 2 == 0) {
                colors.add(getResources().getColor(R.color.chart_orange));
            } else {
                colors.add(getResources().getColor(R.color.chart_orange2));
            }
            final PieEntry pieEntry = new PieEntry(percentOfPerPie, "", null);
            valuesOfNameAndNumner.add(pieEntry);
            Glide.with(getApplicationContext()).load(crowdfundingChartDataBeanList.get(i).getLogo_white() + "?imageView2/2/w/72/h/72")
                    .into(new SimpleTarget<GlideDrawable>() {
                        @Override
                        public void onResourceReady(GlideDrawable resource,
                                                    GlideAnimation<? super GlideDrawable> glideAnimation) {
                            pieEntry.setIcon(resource);
                        }
                    });
        }
    }

    //返回的只有海外一种类型
    private void initAbroadData(final Context context) {
        //请求网络  获取固收子页面数据
        OkGo.get(ApiUtils.getInvestReport(InvestReportChildActivity.this, "abroad")).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {

                abroadInvestReport = ParseUtils.parseByGson(s, AbroadInvestReport.class);
                abroadDataBean = abroadInvestReport.getAbroad_data();
                abroadChartDataBeanList = abroadDataBean.getChart_data();

                //初始化 返回仅固收一种类型的页面

                //初始化圆环和圆环内的数据
                if (abroadChartDataBeanList != null) {
                    PieData pieData = getAbroadOnlyPieData(abroadChartDataBeanList, InvestReportChildActivity.this);
                    mChart.setData(pieData);
                    //未登录时本金和利息都显示为0
                    if (!UserUtils.isLogin(InvestReportChildActivity.this)) {
                        receiving_interest.setText("0");
                        receiving_principal.setText("0");
                    } else {
                        receiving_interest.setText(abroadDataBean.getReceiving_interest() + "");
                        receiving_principal.setText(abroadDataBean.getReceiving_principal() + "");
                    }
                }
                //初始化列表的数据
                list = generateData(abroadChartDataBeanList);
                if (adapter == null) {
                    adapter = new ExpandableItemAdapter(list);
                    adapter.setHeaderView(parent);
                    adapter.bindToRecyclerView(recycler_fund_list);
                    recycler_fund_list.setAdapter(adapter);
                    // important! setLayoutManager should be called after setAdapter
                    recycler_fund_list.setLayoutManager(new WrapContentLinearLayoutManager(context));
                    //设置为全部打开分栏
                    //adapter.expandAll();
                }
                expandOneItem();
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.GONE);
            }


            private ArrayList<MultiItemEntity> generateData(List<AbroadInvestReport.AbroadDataBean.ChartDataBean> chartDataBeanList) {
                ArrayList<MultiItemEntity> res = new ArrayList<>();
                for (int i = 0; i < chartDataBeanList.size(); i++) {
                    AbroadInvestReport.AbroadDataBean.ChartDataBean chartDataBean = chartDataBeanList.get(i);
                    for (int j = 0; j < chartDataBean.getDetail_list().size(); j++) {
                        AbroadInvestReport.AbroadDataBean.ChartDataBean.DetailListBean child = chartDataBean.getDetail_list().get(j);
                        chartDataBean.addSubItem(child);
                        child.setIndex(j);
                    }
                    res.add(chartDataBean);
                }
                return res;
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                NormalUtils.showToast(InvestReportChildActivity.this, R.string.net_error);
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.VISIBLE);
            }
        });
    }

    private PieData getAbroadOnlyPieData(List<AbroadInvestReport.AbroadDataBean.ChartDataBean> chartDataBeanList,
                                         Context context) {

        //valuesOfNameAndNumner用来表示封装每个饼块的实际数据
        ArrayList<PieEntry> valuesOfNameAndNumner = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList<>();

        //如果没有登录 或者 获取的数据为0 则平分饼状图
        if (!UserUtils.isLogin(context) || chartDataBeanList.size() == 0) {
            initNoDataChat(valuesOfNameAndNumner, colors);
        } else {
            initAbroadDataChat(chartDataBeanList, valuesOfNameAndNumner, colors);
        }

        //将名字，数字，颜色 放入数据集合
        PieDataSet pieDataSet = new PieDataSet(valuesOfNameAndNumner, null);
        pieDataSet.setColors(colors);

        pieDataSet.setSelectionShift(0); // 选中态多出的长度
        pieDataSet.setSliceSpace(1f); //设置个饼状图之间的距离
        PieData pieData = new PieData(pieDataSet);
        //设置默认的每个色块上的百分比字体隐藏
        pieDataSet.setValueTextSize(0f);
        return pieData;
    }

    private void initAbroadDataChat(List<AbroadInvestReport.AbroadDataBean.ChartDataBean> chartDataBeanList,
                                    ArrayList<PieEntry> valuesOfNameAndNumner,
                                    ArrayList<Integer> colors) {
        for (int i = 0; i < chartDataBeanList.size(); i++) {
            //如果每部分小于10%，则把每部分设为10%
            float percentOfPerPie = chartDataBeanList.get(i).getPercent();
            if (percentOfPerPie < 10f) {
                percentOfPerPie = 10f;
            }
            if (i % 2 == 0) {
                colors.add(getResources().getColor(R.color.chart_blue));
            } else {
                colors.add(getResources().getColor(R.color.chart_blue2));
            }
            final PieEntry pieEntry = new PieEntry(percentOfPerPie, "", null);
            valuesOfNameAndNumner.add(pieEntry);
            Glide.with(getApplicationContext()).load(chartDataBeanList.get(i).getLogo_white() + "?imageView2/2/w/72/h/72")
                    .into(new SimpleTarget<GlideDrawable>() {
                        @Override
                        public void onResourceReady(GlideDrawable resource,
                                                    GlideAnimation<? super GlideDrawable> glideAnimation) {
                            pieEntry.setIcon(resource);
                        }
                    });
        }
    }

    //返回的只有保险一种类型
    private void initInsuranceData(final Context context) {
        //请求网络  获取固收子页面数据
        OkGo.get(ApiUtils.getInvestReport(InvestReportChildActivity.this, "insurance")).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {

                insuranceInvestReport = ParseUtils.parseByGson(s, InsuranceInvestReport.class);
                insuranceDataBean = insuranceInvestReport.getInsurance_data();
                insuranceChartDataBeanList = insuranceDataBean.getChart_data();

                //初始化 返回仅固收一种类型的页面

                //初始化圆环和圆环内的数据
                if (insuranceChartDataBeanList != null) {
                    PieData pieData = getInsuranceOnlyPieData(insuranceChartDataBeanList, InvestReportChildActivity.this);
                    mChart.setData(pieData);
                    text_child1.setText("保费(元)");
                    text_child2.setText("最高保额(元)");
                    //未登录时本金和利息都显示为0
                    if (!UserUtils.isLogin(InvestReportChildActivity.this)) {
                        receiving_interest.setText("0");
                        receiving_principal.setText("0");
                    } else {
                        receiving_interest.setText(insuranceDataBean.getInsurance_fee() + "");
                        receiving_principal.setText(insuranceDataBean.getMax_insurance_amount() + "");
                    }
                }
                //初始化列表的数据
                list = generateData(insuranceChartDataBeanList);
                if (adapter == null) {
                    adapter = new ExpandableItemAdapter(list);
                    adapter.setHeaderView(parent);
                    adapter.bindToRecyclerView(recycler_fund_list);
                    recycler_fund_list.setAdapter(adapter);
                    // important! setLayoutManager should be called after setAdapter
                    recycler_fund_list.setLayoutManager(new WrapContentLinearLayoutManager(context));
                    //设置为全部打开分栏
                    //adapter.expandAll();
                }
                expandOneItem();
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.GONE);
            }


            private ArrayList<MultiItemEntity> generateData(List<InsuranceInvestReport.InsuranceDataBean.ChartDataBean> chartDataBeanList) {
                ArrayList<MultiItemEntity> res = new ArrayList<>();
                for (int i = 0; i < chartDataBeanList.size(); i++) {
                    InsuranceInvestReport.InsuranceDataBean.ChartDataBean chartDataBean = chartDataBeanList.get(i);
                    for (int j = 0; j < chartDataBean.getDetail_list().size(); j++) {
                        InsuranceInvestReport.InsuranceDataBean.ChartDataBean.DetailListBean child = chartDataBean.getDetail_list().get(j);
                        chartDataBean.addSubItem(child);
                        child.setIndex(j);
                    }
                    res.add(chartDataBean);
                }
                return res;
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                NormalUtils.showToast(InvestReportChildActivity.this, R.string.net_error);
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.VISIBLE);
            }
        });
    }

    private PieData getInsuranceOnlyPieData(List<InsuranceInvestReport.InsuranceDataBean.ChartDataBean> chartDataBeanList,
                                            Context context) {

        //valuesOfNameAndNumner用来表示封装每个饼块的实际数据
        ArrayList<PieEntry> valuesOfNameAndNumner = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList<>();

        //如果没有登录 或者 获取的数据为0 则平分饼状图
        if (!UserUtils.isLogin(context) || chartDataBeanList.size() == 0) {
            initNoDataChat(valuesOfNameAndNumner, colors);
        } else {
            initInsuranceDataChat(chartDataBeanList, valuesOfNameAndNumner, colors);
        }

        //将名字，数字，颜色 放入数据集合
        PieDataSet pieDataSet = new PieDataSet(valuesOfNameAndNumner, null);
        pieDataSet.setColors(colors);

        pieDataSet.setSelectionShift(0); // 选中态多出的长度
        pieDataSet.setSliceSpace(1f); //设置个饼状图之间的距离
        PieData pieData = new PieData(pieDataSet);
        //设置默认的每个色块上的百分比字体隐藏
        pieDataSet.setValueTextSize(0f);
        return pieData;
    }

    private void initInsuranceDataChat(List<InsuranceInvestReport.InsuranceDataBean.ChartDataBean> chartDataBeanList,
                                       ArrayList<PieEntry> valuesOfNameAndNumner,
                                       ArrayList<Integer> colors) {
        for (int i = 0; i < chartDataBeanList.size(); i++) {
            //如果每部分小于10%，则把每部分设为10%
            float percentOfPerPie = chartDataBeanList.get(i).getPercent();
            if (percentOfPerPie < 10f) {
                percentOfPerPie = 10f;
            }
            if (i % 2 == 0) {
                colors.add(getResources().getColor(R.color.chart_yellow));
            } else {
                colors.add(getResources().getColor(R.color.chart_yellow2));
            }
            final PieEntry pieEntry = new PieEntry(percentOfPerPie, "", null);
            valuesOfNameAndNumner.add(pieEntry);
            Glide.with(getApplicationContext()).load(chartDataBeanList.get(i).getLogo_white() + "?imageView2/2/w/72/h/72")
                    .into(new SimpleTarget<GlideDrawable>() {
                        @Override
                        public void onResourceReady(GlideDrawable resource,
                                                    GlideAnimation<? super GlideDrawable> glideAnimation) {
                            pieEntry.setIcon(resource);
                        }
                    });
        }
    }

    //返回的只有黄金一种类型
    private void initGoldData(final Context context) {
        //请求网络  获取固收子页面数据
        OkGo.get(ApiUtils.getInvestReport(InvestReportChildActivity.this, "gold")).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {

                goldInvestReport = ParseUtils.parseByGson(s, GoldInvestReport.class);
                goldDataBean = goldInvestReport.getGold_data();
                goldChartDataBeanList = goldDataBean.getChart_data();

                //初始化 返回仅固收一种类型的页面

                //初始化圆环和圆环内的数据
                if (goldChartDataBeanList != null) {
                    PieData pieData = getGoldOnlyPieData(goldChartDataBeanList, InvestReportChildActivity.this);
                    mChart.setData(pieData);
                    //众筹 黄金 的圆环内样式与其他四种不同 type2
                    type_1.setVisibility(View.GONE);
                    type_2.setVisibility(View.VISIBLE);
                    type2_title.setText("持有克重(克)");
                    if (UserUtils.isLogin(InvestReportChildActivity.this)) {
                        type2_context.setText(goldDataBean.getHold_weight() + "");
                    } else {
                        type2_context.setText("0");
                    }
                }
                //初始化列表的数据
                list = generateData(goldChartDataBeanList);
                if (adapter == null) {
                    adapter = new ExpandableItemAdapter(list);
                    adapter.setHeaderView(parent);
                    adapter.bindToRecyclerView(recycler_fund_list);
                    recycler_fund_list.setAdapter(adapter);
                    // important! setLayoutManager should be called after setAdapter
                    recycler_fund_list.setLayoutManager(new WrapContentLinearLayoutManager(context));
                    //设置为全部打开分栏
                    //adapter.expandAll();
                }
                expandOneItem();
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.GONE);
            }


            private ArrayList<MultiItemEntity> generateData(List<GoldInvestReport.GoldDataBean.ChartDataBean> chartDataBeanList) {
                ArrayList<MultiItemEntity> res = new ArrayList<>();
                for (int i = 0; i < chartDataBeanList.size(); i++) {
                    GoldInvestReport.GoldDataBean.ChartDataBean chartDataBean = chartDataBeanList.get(i);
                    for (int j = 0; j < chartDataBean.getDetail_list().size(); j++) {
                        GoldInvestReport.GoldDataBean.ChartDataBean.DetailListBean child = chartDataBean.getDetail_list().get(j);
                        chartDataBean.addSubItem(child);
                        child.setIndex(j);
                    }
                    res.add(chartDataBean);
                }
                return res;
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                NormalUtils.showToast(InvestReportChildActivity.this, R.string.net_error);
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.VISIBLE);
            }
        });
    }

    private PieData getGoldOnlyPieData(List<GoldInvestReport.GoldDataBean.ChartDataBean> chartDataBeanList,
                                       Context context) {

        //valuesOfNameAndNumner用来表示封装每个饼块的实际数据
        ArrayList<PieEntry> valuesOfNameAndNumner = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList<>();

        //如果没有登录 或者 获取的数据为0 则平分饼状图
        if (!UserUtils.isLogin(context) || chartDataBeanList.size() == 0) {
            initNoDataChat(valuesOfNameAndNumner, colors);
        } else {
            initGoldDataChat(chartDataBeanList, valuesOfNameAndNumner, colors);
        }

        //将名字，数字，颜色 放入数据集合
        PieDataSet pieDataSet = new PieDataSet(valuesOfNameAndNumner, null);
        pieDataSet.setColors(colors);

        pieDataSet.setSelectionShift(0); // 选中态多出的长度
        pieDataSet.setSliceSpace(1f); //设置个饼状图之间的距离
        PieData pieData = new PieData(pieDataSet);
        //设置默认的每个色块上的百分比字体隐藏
        pieDataSet.setValueTextSize(0f);
        return pieData;
    }

    private void initGoldDataChat(List<GoldInvestReport.GoldDataBean.ChartDataBean> chartDataBeanList,
                                  ArrayList<PieEntry> valuesOfNameAndNumner,
                                  ArrayList<Integer> colors) {
        for (int i = 0; i < chartDataBeanList.size(); i++) {
            //如果每部分小于10%，则把每部分设为10%
            float percentOfPerPie = chartDataBeanList.get(i).getPercent();
            if (percentOfPerPie < 10f) {
                percentOfPerPie = 10f;
            }
            if (i % 2 == 0) {
                colors.add(getResources().getColor(R.color.chart_yellow));
            } else {
                colors.add(getResources().getColor(R.color.chart_yellow2));
            }
            final PieEntry pieEntry = new PieEntry(percentOfPerPie, "", null);
            valuesOfNameAndNumner.add(pieEntry);
            Glide.with(getApplicationContext()).load(chartDataBeanList.get(i).getLogo_white() + "?imageView2/2/w/72/h/72")
                    .into(new SimpleTarget<GlideDrawable>() {
                        @Override
                        public void onResourceReady(GlideDrawable resource,
                                                    GlideAnimation<? super GlideDrawable> glideAnimation) {
                            pieEntry.setIcon(resource);
                        }
                    });
        }
    }

    //返回的只有活期一种类型
    private void initCurrentData(final Context context) {
        //请求网络  获取固收子页面数据
        OkGo.get(ApiUtils.getInvestReport(InvestReportChildActivity.this, "current")).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {

                currentInvestReport = ParseUtils.parseByGson(s, CurrentInvestReport.class);
                currentDataBean = currentInvestReport.getCurrent_data();
                currentChartDataBeanList = currentDataBean.getChart_data();

                //初始化 返回仅固收一种类型的页面

                //初始化圆环和圆环内的数据
                if (currentChartDataBeanList != null) {
                    PieData pieData = getCurrentOnlyPieData(currentChartDataBeanList, InvestReportChildActivity.this);
                    mChart.setData(pieData);
                    text_child1.setText("持有本金(元)");
                    text_child2.setText("累计收益(元)");
                    //未登录时本金和利息都显示为0
                    if (!UserUtils.isLogin(InvestReportChildActivity.this)) {
                        receiving_principal.setText("0");
                        receiving_interest.setText("0");
                    } else {
                        receiving_principal.setText(currentDataBean.getTotal_principal() + "");
                        receiving_interest.setText(currentDataBean.getTotal_income() + "");
                    }
                }

                //初始化列表的数据
                list = generateData(currentChartDataBeanList);
                if (adapter == null) {
                    adapter = new ExpandableItemAdapter(list);
                    adapter.setHeaderView(parent);
                    adapter.bindToRecyclerView(recycler_fund_list);
                    recycler_fund_list.setAdapter(adapter);
                    // important! setLayoutManager should be called after setAdapter
                    recycler_fund_list.setLayoutManager(new WrapContentLinearLayoutManager(context));
                    //设置为全部打开分栏
                    //adapter.expandAll();
                }
                expandOneItem();
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.GONE);
            }


            private ArrayList<MultiItemEntity> generateData(List<CurrentInvestReport.CurrentDataBean.ChartDataBean> chartDataBeanList) {
                ArrayList<MultiItemEntity> res = new ArrayList<>();
                for (int i = 0; i < chartDataBeanList.size(); i++) {
                    CurrentInvestReport.CurrentDataBean.ChartDataBean chartDataBean = chartDataBeanList.get(i);
                    for (int j = 0; j < chartDataBean.getDetail_list().size(); j++) {
                        CurrentInvestReport.CurrentDataBean.ChartDataBean.DetailListBean child = chartDataBean.getDetail_list().get(j);
                        chartDataBean.addSubItem(child);
                        child.setIndex(j);
                    }
                    res.add(chartDataBean);
                }
                return res;
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                NormalUtils.showToast(InvestReportChildActivity.this, R.string.net_error);
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.VISIBLE);
            }
        });
    }

    private PieData getCurrentOnlyPieData(List<CurrentInvestReport.CurrentDataBean.ChartDataBean> chartDataBeanList,
                                          Context context) {

        //valuesOfNameAndNumner用来表示封装每个饼块的实际数据
        ArrayList<PieEntry> valuesOfNameAndNumner = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList<>();

        //如果没有登录 或者 获取的数据为0 则平分饼状图
        if (!UserUtils.isLogin(context) || chartDataBeanList.size() == 0) {
            initNoDataChat(valuesOfNameAndNumner, colors);
        } else {
            initCurrentDataChat(chartDataBeanList, valuesOfNameAndNumner, colors);
        }

        //将名字，数字，颜色 放入数据集合
        PieDataSet pieDataSet = new PieDataSet(valuesOfNameAndNumner, null);
        pieDataSet.setColors(colors);

        pieDataSet.setSelectionShift(0); // 选中态多出的长度
        pieDataSet.setSliceSpace(1f); //设置个饼状图之间的距离
        PieData pieData = new PieData(pieDataSet);
        //设置默认的每个色块上的百分比字体隐藏
        pieDataSet.setValueTextSize(0f);
        return pieData;
    }

    private void initCurrentDataChat(List<CurrentInvestReport.CurrentDataBean.ChartDataBean> chartDataBeanList,
                                     ArrayList<PieEntry> valuesOfNameAndNumner,
                                     ArrayList<Integer> colors) {
        for (int i = 0; i < chartDataBeanList.size(); i++) {
            //如果每部分小于10%，则把每部分设为10%
            float percentOfPerPie = chartDataBeanList.get(i).getPercent();
            if (percentOfPerPie < 10f) {
                percentOfPerPie = 10f;
            }
            if (i % 2 == 0) {
                colors.add(getResources().getColor(R.color.chart_green));
            } else {
                colors.add(getResources().getColor(R.color.chart_green2));
            }
            final PieEntry pieEntry = new PieEntry(percentOfPerPie, "", null);
            valuesOfNameAndNumner.add(pieEntry);
            Glide.with(getApplicationContext()).load(chartDataBeanList.get(i).getLogo_white() + "?imageView2/2/w/72/h/72")
                    .into(new SimpleTarget<GlideDrawable>() {
                        @Override
                        public void onResourceReady(GlideDrawable resource,
                                                    GlideAnimation<? super GlideDrawable> glideAnimation) {
                            pieEntry.setIcon(resource);
                        }
                    });
        }
    }


    private void initNoDataChat(ArrayList<PieEntry> valuesOfNameAndNumner, ArrayList<Integer> colors) {
        valuesOfNameAndNumner.add(new PieEntry(1f, getResources().getString(R.string.fixed)));
        valuesOfNameAndNumner.add(new PieEntry(1f, getResources().getString(R.string.current)));
        valuesOfNameAndNumner.add(new PieEntry(1f, getResources().getString(R.string.gold)));
        valuesOfNameAndNumner.add(new PieEntry(1f, getResources().getString(R.string.crowd_funding)));
        valuesOfNameAndNumner.add(new PieEntry(1f, getResources().getString(R.string.abroad)));
        valuesOfNameAndNumner.add(new PieEntry(1f, getResources().getString(R.string.insurance)));

        colors.add(getResources().getColor(R.color.chart_orange));
        colors.add(getResources().getColor(R.color.chart_green));
        colors.add(getResources().getColor(R.color.chart_yellow));
        colors.add(getResources().getColor(R.color.chart_orange));
        colors.add(getResources().getColor(R.color.chart_blue));
        colors.add(getResources().getColor(R.color.chart_yellow));
    }


    /**
     * 跳转回款明细
     *
     * @param type
     * @param bid_id
     */
    public void startToRepayDetail(String type, int bid_id) {
        if (TextUtils.isEmpty(type))
            return;

        InvestReportChildActivity activity = InvestReportChildActivity.this;
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
                activity.startActivity(DailyRebateActivity.class, args);
                break;
            case "p2p_current":
            case "gold_current":
                args.putString(AccountCollectionDetailsActivity.class.getSimpleName(), type);
                activity.startActivity(AccountCollectionDetailsActivity.class, args);
                break;
//            default:
//                NormalUtils.showToast(activity,"错误，未知标的类型:" + type);
//                break;
        }
    }

    private void expandOneItem() {
        if (list != null && list.size() == 1) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(500);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.expand(1);
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

}
