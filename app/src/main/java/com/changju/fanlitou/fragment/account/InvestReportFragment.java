package com.changju.fanlitou.fragment.account;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.InvestReportChildActivity;
import com.changju.fanlitou.activity.LoginActivity;
import com.changju.fanlitou.activity.MainActivity;
import com.changju.fanlitou.activity.account.AccountCollectionDetailsActivity;
import com.changju.fanlitou.activity.account.DailyRebateActivity;
import com.changju.fanlitou.adapter.AccountInvestReportAdapter;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.account.investreport.AbroadInvestReport;
import com.changju.fanlitou.bean.account.investreport.CategoryInvestReport;
import com.changju.fanlitou.bean.account.investreport.CrowdfundingInvestReport;
import com.changju.fanlitou.bean.account.investreport.CurrentInvestReport;
import com.changju.fanlitou.bean.account.investreport.FixedInvestReport;
import com.changju.fanlitou.bean.account.investreport.GoldInvestReport;
import com.changju.fanlitou.bean.account.investreport.InsuranceInvestReport;
import com.changju.fanlitou.bean.account.investreport.TypeInvest;
import com.changju.fanlitou.ui.dialog.AnimDialog;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.changju.fanlitou.util.UserUtils;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by ZM on 2017/5/4.
 */

public class InvestReportFragment extends LazyFragment implements OnChartValueSelectedListener {

    private String lastS;
    //分级列表
    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;
    private String type;

    //第一种圆环中间的样式
    private LinearLayout type_1;
    private TextView text_child1, text_child2;
    private TextView receiving_principal, receiving_interest;

    //第二种圆环中间的样式
    private LinearLayout type_2;
    private TextView type2_title, type2_context;

    private PieChart mChart;
    private ImageView uparrow_divide_line;
    private TextView go_invest_tv;
    private RelativeLayout parent;
    private RelativeLayout highest_text;
    private TextView highest;
    private TextView highest_number;
    private RecyclerView recycler_fund_list;

    //存列表的数据
    ArrayList<MultiItemEntity> list;
    InvestReportFragment.ExpandableItemAdapter adapter;

    //返回品类类型数大于1
    private CategoryInvestReport investReport;
    private CategoryInvestReport.CategoryDataBean categoryDataBean;
    private List<CategoryInvestReport.CategoryDataBean.ChartDataBean> chartDataListBeanCategory;

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


    private AccountInvestReportAdapter accountInvestReportAdapter;
    private TypeInvest typeInvest;

    private MainActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public void initView() {

        GrowingIO.getInstance().setPageName(this, "账本-投资报表");

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(LAYOUT_INFLATER_SERVICE);
        parent = (RelativeLayout) inflater.inflate(R.layout.fund_list_piechart, null);
        mChart = (PieChart) parent.findViewById(R.id.fund_list_piechart);
        highest_text = (RelativeLayout) parent.findViewById(R.id.highest_text);
        highest = (TextView) parent.findViewById(R.id.highest);
        highest_number = (TextView) parent.findViewById(R.id.highest_number);
        uparrow_divide_line = (ImageView) parent.findViewById(R.id.uparrow_divide_line);
        go_invest_tv = (TextView) parent.findViewById(R.id.go_invest_tv);
        go_invest_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(!UserUtils.isLogin(activity)){
//                    ((MainActivity) activity).startActivity(LoginActivity.class);
//                }
                Intent intent = new Intent(activity,
                        UserUtils.isLogin(activity) ?
                                MainActivity.class : LoginActivity.class);
                Bundle args = new Bundle();
                args.putBoolean(MainActivity.class.getSimpleName(), true);
                intent.putExtras(args);
                startActivity(intent);
            }
        });
        mChart.setOnChartValueSelectedListener(this);

        receiving_interest = (TextView) parent.findViewById(R.id.receiving_interest);
        receiving_principal = (TextView) parent.findViewById(R.id.receiving_principal);

        type_1 = (LinearLayout) parent.findViewById(R.id.type_1);
        text_child1 = (TextView) parent.findViewById(R.id.text_child1);
        text_child2 = (TextView) parent.findViewById(R.id.text_child2);

        type_2 = (LinearLayout) parent.findViewById(R.id.type_2);
        type2_title = (TextView) parent.findViewById(R.id.type2_title);
        type2_context = (TextView) parent.findViewById(R.id.type2_context);


        //设置piechat的整体样式
        initPieChart();

        recycler_fund_list = (RecyclerView) findViewById(R.id.recycler_fund_list);
        recycler_fund_list.setLayoutManager(new WrapContentLinearLayoutManager(activity));

//        swipe_fund_list = (SwipeRefreshLayout) findViewById(swipe_fund_list);
//        swipe_fund_list.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW);
//        swipe_fund_list.setRefreshing(true);
//        //下拉刷新监听 刷新一次就请一次数据
//        swipe_fund_list.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                initData();
//            }
//        });
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
        mChart.animateY(800, Easing.EasingOption.EaseOutCirc);

    }

    //获取饼状图中 文字 数额 颜色等信息 将其存在PieData 中

    //当返回的是多余一种类型的返回结果
    private PieData getCategoryPieData(List<CategoryInvestReport.CategoryDataBean.ChartDataBean> chartDataListBeanCategory) {

        //valuesOfNameAndNumner用来表示封装每个饼块的实际数据
        ArrayList<PieEntry> valuesOfNameAndNumner = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList<>();

        //如果没有登录 或者 获取的数据为0 则平分饼状图
        if (chartDataListBeanCategory == null || !UserUtils.isLogin(activity) || chartDataListBeanCategory.size() == 0) {
            showEmptyView(valuesOfNameAndNumner, colors);
        } else {
            initCategoryDataChat(chartDataListBeanCategory, valuesOfNameAndNumner, colors);
            uparrow_divide_line.setVisibility(View.VISIBLE);
            go_invest_tv.setVisibility(View.GONE);
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

    private void showEmptyView(ArrayList<PieEntry> valuesOfNameAndNumner, ArrayList<Integer> colors) {
        initNoDataChat(valuesOfNameAndNumner, colors);
        uparrow_divide_line.setVisibility(View.GONE);
        go_invest_tv.setVisibility(View.VISIBLE);
    }

    private void initCategoryDataChat(List<CategoryInvestReport.CategoryDataBean.ChartDataBean> chartDataListBeanCategory,
                                      ArrayList<PieEntry> valuesOfNameAndNumner,
                                      ArrayList<Integer> colors) {
        for (int i = 0; i < chartDataListBeanCategory.size(); i++) {
            //如果每部分小于10%，则把每部分设为10%
            float percentOfPerPie = chartDataListBeanCategory.get(i).getPercent();
            if (percentOfPerPie < 10f) {
                percentOfPerPie = 10f;
            }
            valuesOfNameAndNumner.add(
                    new PieEntry(percentOfPerPie, chartDataListBeanCategory.get(i).getName()));

            //为不同的类型设置不同的圆环颜色
            if (chartDataListBeanCategory.get(i).getType().equals("p2p")) {
                colors.add(activity.getResources().getColor(R.color.chart_orange));
            }
            if (chartDataListBeanCategory.get(i).getType().equals("current")) {
                colors.add(activity.getResources().getColor(R.color.chart_green));
            }
            if (chartDataListBeanCategory.get(i).getType().equals("gold")) {
                colors.add(activity.getResources().getColor(R.color.chart_yellow));
            }
            if (chartDataListBeanCategory.get(i).getType().equals("crowdfunding")) {
                colors.add(activity.getResources().getColor(R.color.chart_orange));
            }
            if (chartDataListBeanCategory.get(i).getType().equals("abroad")) {
                colors.add(activity.getResources().getColor(R.color.chart_blue));
            }
            if (chartDataListBeanCategory.get(i).getType().equals("insurance")) {
                colors.add(activity.getResources().getColor(R.color.chart_yellow));
            }
        }
    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness(Context mContext) {
        if (mAnimDialog == null) {
            mAnimDialog = AnimDialog.showDialog(mContext);
        }

        mAnimDialog.show();

        initData();
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_fund_list;
    }

    //两个方法是Piechat接口里面 点击饼状图中的某个会回调
    //Highlight h 里的x 表示第几项 如1.0 2.0表示第一或第二项
    @Override
    public void onValueSelected(Entry e, Highlight h) {
        if (UserUtils.isLogin(activity) && chartDataListBeanCategory != null && chartDataListBeanCategory.size() != 0) {
            PieEntry pieEntry = (PieEntry) e;
            ringOnClick(pieEntry.getLabel());
        }
    }

    @Override
    public void onNothingSelected() {

    }

    public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

        public ExpandableItemAdapter(List<MultiItemEntity> data) {
            super(data);
            addItemType(TYPE_LEVEL_0, R.layout.recycler_item_investreport_child);
            //不同类型 二级标题不一样
            if (type.equals("p2p")) {
                addItemType(TYPE_LEVEL_1, R.layout.recycler_item_investreport_child2lines_noredtext);
            }
            if (type.equals("crowdfunding") || type.equals("abroad") || type.equals("insurance")) {
                addItemType(TYPE_LEVEL_1, R.layout.recycler_item_investreport_child2lines);
            }
            if (type.equals("current") || type.equals("gold")) {
                addItemType(TYPE_LEVEL_1, R.layout.recycler_item_investreport_child3lines);
            }
        }

        @Override
        protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {

            if (type.equals("p2p")) {
                fillFixedList(holder, item);
            } else if (type.equals("crowdfunding")) {
                fillCrowdfundingList(holder, item);
            } else if (type.equals("abroad")) {
                fillAbroadList(holder, item);
            } else if (type.equals("insurance")) {
                fillInsuranceList(holder, item);
            } else if (type.equals("current")) {
                fillCurrentList(holder, item);
            } else if (type.equals("gold")) {
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
                            .setText(R.id.tv_amount_investreport, "待收本息待收本息：" + chartDataBeanFixed.getAmount());
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
//                            Bundle args = new Bundle();
//                            args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,
//                                    ((FixedInvestReport.P2pDataBean.ChartDataBean.DetailListBean) item).getBid_id());
//                            args.putString(DailyRebateActivity.class.getSimpleName(),"p2p");
//                            Intent intent = new Intent(activity,DailyRebateActivity.class);
//                            startActivity(intent,args);
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

        /**
         * same as recyclerView.setAdapter(), and save the instance of recyclerView
         * 可以bind多次
         */
        public void bindToRecyclerView(RecyclerView recyclerView) {
            Method method = null;
            try {
                method = BaseQuickAdapter.class.getDeclaredMethod("setRecyclerView",
                        RecyclerView.class);
                //暴力破解权限
                method.setAccessible(true);
                method.invoke(this, recyclerView);
                getRecyclerView().setAdapter(this);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
//            setRecyclerView(recyclerView);
        }

    }

    private AnimDialog mAnimDialog;

    public void initData() {
        //请求网络
        //进入账本页面，type都是传入的Category，但是返回值里面的type有多种（Category，fixed，gold等等）
        //这里只有固收正常返回fixed 其他的好像还是返回category
        OkGo.get(ApiUtils.getInvestReport(activity, "category")).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {

                if (s.equals(lastS))
                    return;

                lastS = s;
                //判断返回的是什么情况 是多个类型还是只有某一种
                typeInvest = ParseUtils.parseByGson(s, TypeInvest.class);
                type = typeInvest.getType();

                //根据投资情况不一样 分情况显示页面
                switch (type) {
                    case "category":
                        highest_text.setVisibility(View.VISIBLE);
                        initCategoryData(s);
                        break;
                    case "p2p":
                        uparrow_divide_line.setVisibility(View.VISIBLE);
                        highest_text.setVisibility(View.GONE);
                        go_invest_tv.setVisibility(View.GONE);
                        initFixedData(activity);
                        break;
                    case "current":
                        uparrow_divide_line.setVisibility(View.VISIBLE);
                        highest_text.setVisibility(View.GONE);
                        go_invest_tv.setVisibility(View.GONE);
                        initCurrentData(activity);
                        break;
                    case "crowdfunding":
                        uparrow_divide_line.setVisibility(View.VISIBLE);
                        highest_text.setVisibility(View.GONE);
                        go_invest_tv.setVisibility(View.GONE);
                        initCrowdfundingData(activity);
                        break;
                    case "gold":
                        uparrow_divide_line.setVisibility(View.VISIBLE);
                        highest_text.setVisibility(View.GONE);
                        go_invest_tv.setVisibility(View.GONE);
                        initGoldData(activity);
                        break;
                    case "abroad":
                        uparrow_divide_line.setVisibility(View.VISIBLE);
                        highest_text.setVisibility(View.GONE);
                        go_invest_tv.setVisibility(View.GONE);
                        initAbroadData(activity);
                        break;
                    case "insurance":
                        uparrow_divide_line.setVisibility(View.VISIBLE);
                        highest_text.setVisibility(View.GONE);
                        go_invest_tv.setVisibility(View.GONE);
                        initInsuranceData(activity);
                        break;
                }

                mAnimDialog.dismiss();
            }


            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                NormalUtils.showToast(InvestReportFragment.this.activity
                        , R.string.net_error);
                highest_text.setVisibility(View.VISIBLE);
                initCategoryData("");

                mAnimDialog.dismiss();
            }
        });
    }

    private void initCategoryData(String s) {
        investReport = ParseUtils.parseByGson(s, CategoryInvestReport.class);
        if (investReport != null) {
            categoryDataBean = investReport.getCategory_data();
            if (investReport.isHas_insurance()) {
                highest_text.setVisibility(View.VISIBLE);
                highest_number.setText(investReport
                        .getCategory_data().getCategory_insurance_max_guarantee_amount() + "元");
            } else {
                highest_text.setVisibility(View.GONE);
            }
        }

        if (categoryDataBean != null)
            chartDataListBeanCategory = categoryDataBean.getChart_data();
        //逆序 服务器是从小到大 逆序后从大到小
        //Collections.reverse(chartDataListBeanCategory);

        //初始化 返回多余一种类型的页面

        //初始化圆环和圆环内的数据

        PieData pieData = getCategoryPieData(chartDataListBeanCategory);
        mChart.setData(pieData);
        //未登录时本金和利息都显示为0
        if (categoryDataBean == null || !UserUtils.isLogin(activity)) {
            receiving_interest.setText("0");
            receiving_principal.setText("0");
        } else {
            receiving_interest.setText(categoryDataBean.getReceiving_interest() + "");
            receiving_principal.setText(categoryDataBean.getReceiving_principal() + "");
        }

        //初始化列表的数据

        if (accountInvestReportAdapter == null) {
            accountInvestReportAdapter = new AccountInvestReportAdapter(chartDataListBeanCategory, getApplicationContext());
            accountInvestReportAdapter.bindToRecyclerView(recycler_fund_list);
            accountInvestReportAdapter.removeAllHeaderView();
            if (adapter != null) {
                adapter.removeAllHeaderView();
            }
            accountInvestReportAdapter.setHeaderView(parent);
            recycler_fund_list.setLayoutManager(new WrapContentLinearLayoutManager(activity));
            accountInvestReportAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    String name = (String) ((TextView) view.findViewById(R.id.tv_name_investreport)).getText();
                    ringOnClick(name);
                }
            });
        } else {
            accountInvestReportAdapter.removeAllHeaderView();
            if (adapter != null) {
                adapter.removeAllHeaderView();
            }
            accountInvestReportAdapter.setHeaderView(parent);
            accountInvestReportAdapter.bindToRecyclerView(recycler_fund_list);
            accountInvestReportAdapter.setNewData(chartDataListBeanCategory);
        }
        //下面这行注释后 当未登录时 饼状图显示为六等分 否则未登录时显示无数据
        //accountInvestReportAdapter.setEmptyView(R.layout.view_empty);

    }

    private void ringOnClick(String name) {
        if ("category".equals(type)) {
            Intent intent = new Intent(activity, InvestReportChildActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(InvestReportChildActivity.INVEST_TYPE, name);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    //返回的只有固收一种类型
    private void initFixedData(final Context context) {
        //请求网络  获取固收子页面数据
        OkGo.get(ApiUtils.getInvestReport(context, "p2p")).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {

                fixedInvestReport = ParseUtils.parseByGson(s, FixedInvestReport.class);
                p2pDataBean = fixedInvestReport.getP2p_data();
                fixedChartDataBeanList = p2pDataBean.getChart_data();
                //逆序 服务器是从小到大 逆序后从大到小
                Collections.reverse(fixedChartDataBeanList);

                //初始化 返回仅固收一种类型的页面

                //初始化圆环和圆环内的数据
                if (fixedChartDataBeanList != null) {
                    PieData pieData = getFixedOnlyPieData(fixedChartDataBeanList, context);
                    mChart.setData(pieData);
                    //未登录时本金和利息都显示为0
                    if (!UserUtils.isLogin(context)) {
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
                    adapter = new InvestReportFragment.ExpandableItemAdapter(list);
                    adapter.bindToRecyclerView(recycler_fund_list);
                    adapter.removeAllHeaderView();
                    if (accountInvestReportAdapter != null) {
                        accountInvestReportAdapter.removeAllHeaderView();
                    }
                    adapter.setHeaderView(parent);
                    // important! setLayoutManager should be called after setAdapter
                    recycler_fund_list.setLayoutManager(new WrapContentLinearLayoutManager(context));
                    //设置为全部打开分栏
                    //adapter.expandAll();
                } else {
                    adapter.removeAllHeaderView();
                    if (accountInvestReportAdapter != null) {
                        accountInvestReportAdapter.removeAllHeaderView();
                    }
                    adapter.setHeaderView(parent);
                    adapter.bindToRecyclerView(recycler_fund_list);
                    adapter.setNewData(list);
                }
                expandOneItem();
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
            try {
                initFixedDataChat(fixedChartDataBeanList, valuesOfNameAndNumner, colors);
            } catch (Exception e) {
            }
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

    private void initFixedDataChat(final List<FixedInvestReport.P2pDataBean.ChartDataBean> fixedChartDataBeanList,
                                   final ArrayList<PieEntry> valuesOfNameAndNumner,
                                   ArrayList<Integer> colors) throws MalformedURLException {
        for (int i = 0; i < fixedChartDataBeanList.size(); i++) {
            //如果每部分小于10%，则把每部分设为10%
            float percentOfPerPie = fixedChartDataBeanList.get(i).getPercent();
            if (percentOfPerPie < 10f) {
                percentOfPerPie = 10f;
            }
            if (i % 2 == 0) {
                colors.add(activity.getResources().getColor(R.color.chart_orange));
            } else {
                colors.add(activity.getResources().getColor(R.color.chart_orange2));
            }
            final PieEntry pieEntry = new PieEntry(percentOfPerPie, "", null);
            valuesOfNameAndNumner.add(pieEntry);
            Glide.with(getApplicationContext()).load(fixedChartDataBeanList.get(i).getLogo_white())
                    .into(new SimpleTarget<GlideDrawable>() {
                        @Override
                        public void onResourceReady(GlideDrawable resource,
                                                    GlideAnimation<? super GlideDrawable> glideAnimation) {
                            pieEntry.setIcon(resource);
                        }
                    });
        }
    }


    //返回的只有众筹一种类型
    private void initCrowdfundingData(final Context context) {
        //请求网络  获取固收子页面数据
        OkGo.get(ApiUtils.getInvestReport(context, "crowdfunding")).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {

                crowdfundingInvestReport = ParseUtils.parseByGson(s, CrowdfundingInvestReport.class);
                crowdfundingDataBean = crowdfundingInvestReport.getCrowdfunding_data();
                crowdfundingChartDataBeanList = crowdfundingDataBean.getChart_data();
                //逆序 服务器是从小到大 逆序后从大到小
                Collections.reverse(crowdfundingChartDataBeanList);

                //初始化 返回仅固收一种类型的页面

                //初始化圆环和圆环内的数据
                if (crowdfundingChartDataBeanList != null) {
                    PieData pieData = getCrowdfundingOnlyPieData(crowdfundingChartDataBeanList, context);
                    mChart.setData(pieData);
                    //众筹 黄金 的圆环内样式与其他四种不同 type2
                    type_1.setVisibility(View.GONE);
                    type_2.setVisibility(View.VISIBLE);
                    type2_title.setText("投资本金(元)");
                    if (UserUtils.isLogin(context)) {
                        type2_context.setText(crowdfundingDataBean.getInvest_principal() + "");
                    } else {
                        type2_context.setText("0");
                    }
                }
                //初始化列表的数据
                list = generateData(crowdfundingChartDataBeanList);
                if (adapter == null) {
                    adapter = new InvestReportFragment.ExpandableItemAdapter(list);
                    adapter.bindToRecyclerView(recycler_fund_list);
                    adapter.removeAllHeaderView();
                    if (accountInvestReportAdapter != null) {
                        accountInvestReportAdapter.removeAllHeaderView();
                    }
                    adapter.setHeaderView(parent);
                    // important! setLayoutManager should be called after setAdapter
                    recycler_fund_list.setLayoutManager(new WrapContentLinearLayoutManager(context));
                    //设置为全部打开分栏
                    //adapter.expandAll();
                } else {
                    adapter.removeAllHeaderView();
                    if (accountInvestReportAdapter != null) {
                        accountInvestReportAdapter.removeAllHeaderView();
                    }
                    adapter.setHeaderView(parent);
                    adapter.bindToRecyclerView(recycler_fund_list);
                    adapter.setNewData(list);
                }

                expandOneItem();
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
                colors.add(activity.getResources().getColor(R.color.chart_orange));
            } else {
                colors.add(activity.getResources().getColor(R.color.chart_orange2));
            }
            final PieEntry pieEntry = new PieEntry(percentOfPerPie, "", null);
            valuesOfNameAndNumner.add(pieEntry);
            Glide.with(getApplicationContext()).load(crowdfundingChartDataBeanList.get(i).getLogo_white())
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
        OkGo.get(ApiUtils.getInvestReport(context, "abroad")).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {

                abroadInvestReport = ParseUtils.parseByGson(s, AbroadInvestReport.class);
                abroadDataBean = abroadInvestReport.getAbroad_data();
                abroadChartDataBeanList = abroadDataBean.getChart_data();
                //逆序 服务器是从小到大 逆序后从大到小
                Collections.reverse(abroadChartDataBeanList);

                //初始化 返回仅固收一种类型的页面

                //初始化圆环和圆环内的数据
                if (abroadChartDataBeanList != null) {
                    PieData pieData = getAbroadOnlyPieData(abroadChartDataBeanList, context);
                    mChart.setData(pieData);
                    //未登录时本金和利息都显示为0
                    if (!UserUtils.isLogin(context)) {
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
                    adapter = new InvestReportFragment.ExpandableItemAdapter(list);
                    adapter.bindToRecyclerView(recycler_fund_list);
                    adapter.removeAllHeaderView();
                    if (accountInvestReportAdapter != null) {
                        accountInvestReportAdapter.removeAllHeaderView();
                    }
                    adapter.setHeaderView(parent);
                    // important! setLayoutManager should be called after setAdapter
                    recycler_fund_list.setLayoutManager(new WrapContentLinearLayoutManager(context));
                    //设置为全部打开分栏
                    //adapter.expandAll();
                } else {
                    adapter.removeAllHeaderView();
                    if (accountInvestReportAdapter != null) {
                        accountInvestReportAdapter.removeAllHeaderView();
                    }
                    adapter.setHeaderView(parent);
                    adapter.bindToRecyclerView(recycler_fund_list);
                    adapter.setNewData(list);
                }
                expandOneItem();
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
                colors.add(activity.getResources().getColor(R.color.chart_blue));
            } else {
                colors.add(activity.getResources().getColor(R.color.chart_blue2));
            }
            final PieEntry pieEntry = new PieEntry(percentOfPerPie, "", null);
            valuesOfNameAndNumner.add(pieEntry);
            Glide.with(getApplicationContext()).load(chartDataBeanList.get(i).getLogo_white())
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
        OkGo.get(ApiUtils.getInvestReport(context, "insurance")).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {

                insuranceInvestReport = ParseUtils.parseByGson(s, InsuranceInvestReport.class);
                insuranceDataBean = insuranceInvestReport.getInsurance_data();
                insuranceChartDataBeanList = insuranceDataBean.getChart_data();
                //逆序 服务器是从小到大 逆序后从大到小
                Collections.reverse(insuranceChartDataBeanList);

                //初始化 返回仅固收一种类型的页面

                //初始化圆环和圆环内的数据
                if (insuranceChartDataBeanList != null) {
                    PieData pieData = getInsuranceOnlyPieData(insuranceChartDataBeanList, context);
                    mChart.setData(pieData);
                    text_child1.setText("保费(元)");
                    text_child2.setText("最高保额(元)");
                    //未登录时本金和利息都显示为0
                    if (!UserUtils.isLogin(context)) {
                        receiving_interest.setText("0");
                        receiving_principal.setText("0");
                    } else {
                        receiving_principal.setText(insuranceDataBean.getInsurance_fee() + "");
                        receiving_interest.setText(insuranceDataBean.getMax_insurance_amount() + "");
                    }
                }
                //初始化列表的数据
                list = generateData(insuranceChartDataBeanList);
                if (adapter == null) {
                    adapter = new InvestReportFragment.ExpandableItemAdapter(list);
                    adapter.bindToRecyclerView(recycler_fund_list);
                    adapter.removeAllHeaderView();
                    if (accountInvestReportAdapter != null) {
                        accountInvestReportAdapter.removeAllHeaderView();
                    }
                    adapter.setHeaderView(parent);
                    // important! setLayoutManager should be called after setAdapter
                    recycler_fund_list.setLayoutManager(new WrapContentLinearLayoutManager(context));
                    //设置为全部打开分栏
                    //adapter.expandAll();
                } else {
                    adapter.removeAllHeaderView();
                    if (accountInvestReportAdapter != null) {
                        accountInvestReportAdapter.removeAllHeaderView();
                    }
                    adapter.setHeaderView(parent);
                    adapter.bindToRecyclerView(recycler_fund_list);
                    adapter.setNewData(list);
                }
                expandOneItem();
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
                colors.add(activity.getResources().getColor(R.color.chart_yellow));
            } else {
                colors.add(activity.getResources().getColor(R.color.chart_yellow2));
            }
            final PieEntry pieEntry = new PieEntry(percentOfPerPie, "", null);
            valuesOfNameAndNumner.add(pieEntry);
            Glide.with(getApplicationContext()).load(chartDataBeanList.get(i).getLogo_white())
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
        OkGo.get(ApiUtils.getInvestReport(context, "gold")).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {

                goldInvestReport = ParseUtils.parseByGson(s, GoldInvestReport.class);
                goldDataBean = goldInvestReport.getGold_data();
                goldChartDataBeanList = goldDataBean.getChart_data();
                //逆序 服务器是从小到大 逆序后从大到小
                Collections.reverse(goldChartDataBeanList);

                //初始化 返回仅固收一种类型的页面

                //初始化圆环和圆环内的数据
                if (goldChartDataBeanList != null) {
                    PieData pieData = getGoldOnlyPieData(goldChartDataBeanList, context);
                    mChart.setData(pieData);
                    //众筹 黄金 的圆环内样式与其他四种不同 type2
                    type_1.setVisibility(View.GONE);
                    type_2.setVisibility(View.VISIBLE);
                    type2_title.setText("持有克重(克)");
                    if (UserUtils.isLogin(context)) {
                        type2_context.setText(goldDataBean.getHold_weight());
                    } else {
                        type2_context.setText("0");
                    }
                }
                //初始化列表的数据
                list = generateData(goldChartDataBeanList);
                if (adapter == null) {
                    adapter = new InvestReportFragment.ExpandableItemAdapter(list);
                    adapter.bindToRecyclerView(recycler_fund_list);
                    adapter.removeAllHeaderView();
                    if (accountInvestReportAdapter != null) {
                        accountInvestReportAdapter.removeAllHeaderView();
                    }
                    adapter.setHeaderView(parent);
                    // important! setLayoutManager should be called after setAdapter
                    recycler_fund_list.setLayoutManager(new WrapContentLinearLayoutManager(context));
                    //设置为全部打开分栏
                    //adapter.expandAll();
                } else {
                    adapter.removeAllHeaderView();
                    if (accountInvestReportAdapter != null) {
                        accountInvestReportAdapter.removeAllHeaderView();
                    }
                    adapter.setHeaderView(parent);
                    adapter.bindToRecyclerView(recycler_fund_list);
                    adapter.setNewData(list);
                }
                expandOneItem();
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
            }
        });
    }

    private PieData getGoldOnlyPieData(List<GoldInvestReport.GoldDataBean.ChartDataBean> chartDataBeanList,
                                       Context context) {

        //valuesOfNameAndNumner用来表示封装每个饼块的实际数据
        ArrayList<PieEntry> valuesOfNameAndNumber = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList<>();

        //如果没有登录 或者 获取的数据为0 则平分饼状图
        if (!UserUtils.isLogin(context) || chartDataBeanList.size() == 0) {
            initNoDataChat(valuesOfNameAndNumber, colors);
        } else {
            initGoldDataChat(chartDataBeanList, valuesOfNameAndNumber, colors);
        }

        //将名字，数字，颜色 放入数据集合
        PieDataSet pieDataSet = new PieDataSet(valuesOfNameAndNumber, null);
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
                colors.add(activity.getResources().getColor(R.color.chart_yellow));
            } else {
                colors.add(activity.getResources().getColor(R.color.chart_yellow2));
            }
            final PieEntry pieEntry = new PieEntry(percentOfPerPie, "", null);
            valuesOfNameAndNumner.add(pieEntry);
            Glide.with(getApplicationContext()).load(chartDataBeanList.get(i).getLogo_white())
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
        OkGo.get(ApiUtils.getInvestReport(context, "current")).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {

                currentInvestReport = ParseUtils.parseByGson(s, CurrentInvestReport.class);
                currentDataBean = currentInvestReport.getCurrent_data();
                currentChartDataBeanList = currentDataBean.getChart_data();
                //逆序 服务器是从小到大 逆序后从大到小
                Collections.reverse(currentChartDataBeanList);

                //初始化 返回仅固收一种类型的页面

                //初始化圆环和圆环内的数据
                if (currentChartDataBeanList != null) {
                    PieData pieData = getCurrentOnlyPieData(currentChartDataBeanList, context);
                    mChart.setData(pieData);
                    text_child1.setText("持有本金(元)");
                    text_child2.setText("累计收益(元)");
                    //未登录时本金和利息都显示为0
                    if (!UserUtils.isLogin(context)) {
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
                    adapter = new InvestReportFragment.ExpandableItemAdapter(list);
                    adapter.bindToRecyclerView(recycler_fund_list);
                    adapter.removeAllHeaderView();
                    if (accountInvestReportAdapter != null) {
                        accountInvestReportAdapter.removeAllHeaderView();
                    }
                    adapter.setHeaderView(parent);
                    // important! setLayoutManager should be called after setAdapter
                    recycler_fund_list.setLayoutManager(new WrapContentLinearLayoutManager(context));
                    //设置为全部打开分栏
                    //adapter.expandAll();
                } else {
                    adapter.removeAllHeaderView();
                    if (accountInvestReportAdapter != null) {
                        accountInvestReportAdapter.removeAllHeaderView();
                    }
                    adapter.setHeaderView(parent);
                    adapter.bindToRecyclerView(recycler_fund_list);
                    adapter.setNewData(list);
                }
                expandOneItem();
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
            }
        });
    }

    private PieData getCurrentOnlyPieData(List<CurrentInvestReport.CurrentDataBean.ChartDataBean> chartDataBeanList,
                                          Context context) {

        //valuesOfNameAndNumner用来表示封装每个饼块的实际数据
        ArrayList<PieEntry> valuesOfNameAndNumber = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList<>();

        //如果没有登录 或者 获取的数据为0 则平分饼状图
        if (!UserUtils.isLogin(context) || chartDataBeanList.size() == 0) {
            initNoDataChat(valuesOfNameAndNumber, colors);
        } else {
            initCurrentDataChat(chartDataBeanList, valuesOfNameAndNumber, colors);
        }

        //将名字，数字，颜色 放入数据集合
        PieDataSet pieDataSet = new PieDataSet(valuesOfNameAndNumber, null);
        pieDataSet.setColors(colors);

        pieDataSet.setSelectionShift(0); // 选中态多出的长度
        pieDataSet.setSliceSpace(1f); //设置个饼状图之间的距离
        PieData pieData = new PieData(pieDataSet);
        //设置默认的每个色块上的百分比字体隐藏
        pieDataSet.setValueTextSize(0f);
        return pieData;
    }

    private void initCurrentDataChat(List<CurrentInvestReport.CurrentDataBean.ChartDataBean> chartDataBeanList,
                                     ArrayList<PieEntry> valuesOfNameAndNumber,
                                     ArrayList<Integer> colors) {
        for (int i = 0; i < chartDataBeanList.size(); i++) {
            //如果每部分小于10%，则把每部分设为10%
            float percentOfPerPie = chartDataBeanList.get(i).getPercent();
            if (percentOfPerPie < 10f) {
                percentOfPerPie = 10f;
            }
            if (i % 2 == 0) {
                colors.add(activity.getResources().getColor(R.color.chart_green));
            } else {
                colors.add(activity.getResources().getColor(R.color.chart_green2));
            }
            final PieEntry pieEntry = new PieEntry(percentOfPerPie, "", null);
            valuesOfNameAndNumber.add(pieEntry);
            Glide.with(getApplicationContext()).load(chartDataBeanList.get(i).getLogo_white())
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

        highest_text.setVisibility(View.GONE);

        if (valuesOfNameAndNumner == null)
            valuesOfNameAndNumner = new ArrayList<>();

        if (colors == null)
            colors = new ArrayList<>();

        valuesOfNameAndNumner.add(new PieEntry(1f, activity.getResources().getString(R.string.fixed)));
        valuesOfNameAndNumner.add(new PieEntry(1f, activity.getResources().getString(R.string.current)));
        valuesOfNameAndNumner.add(new PieEntry(1f, activity.getResources().getString(R.string.gold)));
        valuesOfNameAndNumner.add(new PieEntry(1f, activity.getResources().getString(R.string.crowd_funding)));
        valuesOfNameAndNumner.add(new PieEntry(1f, activity.getResources().getString(R.string.abroad)));
        valuesOfNameAndNumner.add(new PieEntry(1f, activity.getResources().getString(R.string.insurance)));

        colors.add(activity.getResources().getColor(R.color.chart_orange));
        colors.add(activity.getResources().getColor(R.color.chart_green));
        colors.add(activity.getResources().getColor(R.color.chart_yellow));
        colors.add(activity.getResources().getColor(R.color.chart_orange));
        colors.add(activity.getResources().getColor(R.color.chart_blue));
        colors.add(activity.getResources().getColor(R.color.chart_yellow));
    }

    private boolean isFirst = true;

    @Override
    public void onResume() {
        super.onResume();

        if (isFirst) {
            isFirst = false;
            return;
        }

        initData();
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
                        activity.runOnUiThread(new Runnable() {
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
