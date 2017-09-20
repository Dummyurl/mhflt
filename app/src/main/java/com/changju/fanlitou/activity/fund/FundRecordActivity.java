package com.changju.fanlitou.activity.fund;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.changju.fanlitou.R;
import com.changju.fanlitou.adapter.FundRecordHeavyWarehouseStockAdapter;
import com.changju.fanlitou.adapter.FundRecordHoldingDebentureAdapter;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.fund.FundDetailProfileInfo;
import com.changju.fanlitou.ui.ObservableScrollView;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
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
 * Created by Administrator on 2017/7/19.
 * 基金档案
 */

public class FundRecordActivity extends BaseActivity {

    //loading&error
    private View include;
    private View include_load_error;

    private RelativeLayout titleLayout;
    private TextView fundOverviewName;
    private TextView fundOverviewRegisterDate;
    private TextView fundOverviewLatestScale;
    private TextView fundOverviewCompany;
    private TextView fundOverviewTrusteeshipBank;
    private TextView fundOverviewTradeState;
    private TextView fundOverviewSubscribeConfirm;
    private TextView fundOverviewRedemptionConfirm;
    private TextView assetDistributionDate;
    private PieChart mChart;
    private LinearLayout piechartLegendLayout;
    private TextView heavyWarehouseStockDate;
    private RecyclerView heavyWarehouseStockRcv;
    private TextView holdingDebentureDate;
    private RecyclerView holdingDebentureRcv;
    private ObservableScrollView scrollView;
    private FundRecordHeavyWarehouseStockAdapter fundRecordHeavyWarehouseStockAdapter;
    private List<FundDetailProfileInfo.StockFundDetailBean> stockFundDetailBeanList = new ArrayList<>();
    private FundRecordHoldingDebentureAdapter holdingDebentureAdapter;
    private List<FundDetailProfileInfo.BondFundDetailBean> bondFundDetailBeanList = new ArrayList<>();
    private int[] colors;//颜色集合
    private String[] labels;//标签文本
    private List<Float> datas = new ArrayList<>();//数据，可以是任何类型的数据，如String,int
    private FundDetailProfileInfo fundDetailProfileInfo;
    private LinearLayout stockLayout, bondLayout;
    private TextView assetTotalValueTv;
    private TextView piechart_empty_tv;
    private int fund_id;
    private ImageButton activity_fund_record_go_top_ib;
    private LinearLayout ll_in_scroll;
    private TextView ll_piechart_empty_layout;
    private LinearLayout activity_fund_record_piechart_layout;

    private int heightOfScrollView;

    @Override
    public void initParams(Bundle params) {
        fund_id = params.getInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_fund_record;
    }

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "基金-基金档案");

        activity_fund_record_piechart_layout = (LinearLayout)findViewById(R.id.activity_fund_record_piechart_layout);
        ll_in_scroll = (LinearLayout)findViewById(R.id.ll_in_scroll);
        ll_piechart_empty_layout = (TextView)findViewById(R.id.ll_piechart_empty_layout);
        titleLayout = (RelativeLayout) findViewById(R.id.activity_fund_record_title_layout);
        stockLayout = (LinearLayout) findViewById(R.id.activity_fund_record_stock_layout);
        bondLayout = (LinearLayout) findViewById(R.id.activity_fund_record_bond_layout);
        findViewById(R.id.activity_fund_record_iv_back).setOnClickListener(this);
        scrollView = (ObservableScrollView) findViewById(R.id.activity_fund_record_scrollview);
        scrollView.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                //Log.e("zm",y+"    "+oldy);
                if(oldy == 0 || y == 0){
                    activity_fund_record_go_top_ib.setVisibility(View.GONE);
                }else{
                    activity_fund_record_go_top_ib.setVisibility(View.VISIBLE);
                }
            }
        });
        fundOverviewName = (TextView) findViewById(R.id.activity_fund_record_fund_overview_name);
        fundOverviewRegisterDate = (TextView) findViewById(R.id.activity_fund_record_fund_overview_register_date);
        fundOverviewLatestScale = (TextView) findViewById(R.id.activity_fund_record_fund_overview_latest_scale);
        fundOverviewCompany = (TextView) findViewById(R.id.activity_fund_record_fund_overview_company);
        fundOverviewTrusteeshipBank = (TextView) findViewById(R.id.activity_fund_record_fund_overview_trusteeship_bank);
        fundOverviewTradeState = (TextView) findViewById(R.id.activity_fund_record_fund_overview_trade_state);
        fundOverviewSubscribeConfirm = (TextView) findViewById(R.id.activity_fund_record_fund_overview_subscribe_confirm);
        fundOverviewRedemptionConfirm = (TextView) findViewById(R.id.activity_fund_record_fund_overview_redemption_confirm);
        assetDistributionDate = (TextView) findViewById(R.id.activity_fund_record_asset_distribution_date);
        mChart = (PieChart) findViewById(R.id.activity_fund_record_piechart);
        piechartLegendLayout = (LinearLayout) findViewById(R.id.activity_fund_record_piechart_legend_layout);
        heavyWarehouseStockDate = (TextView) findViewById(R.id.activity_fund_record_heavy_warehouse_stock_date);
        heavyWarehouseStockRcv = (RecyclerView) findViewById(R.id.activity_fund_record_heavy_warehouse_stock_rcv);
        heavyWarehouseStockRcv.setLayoutManager(new WrapContentLinearLayoutManager(FundRecordActivity.this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        holdingDebentureDate = (TextView) findViewById(R.id.activity_fund_record_holding_debenture_date);
        assetTotalValueTv = (TextView) findViewById(R.id.activity_fund_record_asset_total_value);
        piechart_empty_tv = (TextView) findViewById(R.id.activity_fund_record_piechart_empty_tv);
        holdingDebentureRcv = (RecyclerView) findViewById(R.id.activity_fund_record_holding_debenture_rcv);
        holdingDebentureRcv.setLayoutManager(new WrapContentLinearLayoutManager(FundRecordActivity.this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        activity_fund_record_go_top_ib = (ImageButton) findViewById(R.id.activity_fund_record_go_top_ib);
        activity_fund_record_go_top_ib.setOnClickListener(this);

        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);
    }

    private void initData() {
        initPieChart();
        OkGo.get(ApiUtils.getFundDetailProfileInfo(fund_id)).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                analyzeData(s);
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.GONE);
            }



            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                NormalUtils.showToast(FundRecordActivity.this, R.string.net_error);
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * 分析后台获取数据
     */
    private void analyzeData(String s) {
        fundDetailProfileInfo = ParseUtils.parseByGson(s, FundDetailProfileInfo.class);
        setFundOverviewData();
        stockFundDetailBeanList = fundDetailProfileInfo.getStock_fund_detail();
        bondFundDetailBeanList = fundDetailProfileInfo.getBond_fund_detail();
        initPieData();
        setHeavyWarehouseStockAdapter();
        setHoldingDebentureAdapter();
        ViewTreeObserver viewTreeObserver = ll_in_scroll.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ll_in_scroll.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                heightOfScrollView = ll_in_scroll.getHeight();
                //整个屏幕高度
                int screenHeight = NormalUtils.getScreenHeight(FundRecordActivity.this);
                //标题栏高度是46dp
                int heightOfTitleLayout = NormalUtils.dp2px(FundRecordActivity.this,46);
                //之前以为是判断此页总高度，如果高度大于一页则显示向上按钮。后来需求改变为初始不显示，滑动后才显示。
                //代码仍保留
                //状态栏高度
                int statusHeight = NormalUtils.getStatusBarHeight(FundRecordActivity.this);

                if( screenHeight > (heightOfTitleLayout + heightOfScrollView + statusHeight) ){
                    activity_fund_record_go_top_ib.setVisibility(View.GONE);
                }else{
                    activity_fund_record_go_top_ib.setVisibility(View.GONE);
                }
            }
        });
    }

    /**
     * 设置基金概况数据
     */
    private void setFundOverviewData() {
        fundOverviewName.setText(fundDetailProfileInfo.getFund_abbr());
        fundOverviewRegisterDate.setText(fundDetailProfileInfo.getEstab_date());
        fundOverviewLatestScale.setText(fundDetailProfileInfo.getFund_size());
        fundOverviewCompany.setText(fundDetailProfileInfo.getFund_company());
        fundOverviewTrusteeshipBank.setText(fundDetailProfileInfo.getTrup_bank());
        int redemp_status = fundDetailProfileInfo.getRedemp_status();
        String redem_status_str = redemp_status == 0 ? "暂停赎回" : "可赎回";
        String purchase_status_str = "";
        int purchase_status = fundDetailProfileInfo.getPurchase_status();
        switch (purchase_status) {
            case 0:
                purchase_status_str = "禁止申购";
                break;
            case 1:
                purchase_status_str = "开放申购";
                break;
            case 2:
                purchase_status_str = "暂停申购";
                break;
            case 3:
                purchase_status_str = "限大额";
                break;
            case 4:
                purchase_status_str = "开放认购";
                break;
        }
        fundOverviewTradeState.setText(purchase_status_str + " " + redem_status_str);
        String subscribe_days = fundDetailProfileInfo.getSubscribe_days();
        if (TextUtils.isEmpty(subscribe_days)){
            $(R.id.layout_fund_record_fund_overview_subscribe_confirm).setVisibility(View.GONE);
        }else {
            fundOverviewSubscribeConfirm.setText(subscribe_days + "个工作日");
        }

        String redeem_days = fundDetailProfileInfo.getRedeem_days();
        if (TextUtils.isEmpty(redeem_days)){
            $(R.id.layout_fund_record_fund_overview_redemption_confirm).setVisibility(View.GONE);
        }else {
            fundOverviewRedemptionConfirm.setText(redeem_days + "个工作日");
        }

    }

    private void initPieData() {
        FundDetailProfileInfo.AssetConfigBean asset_config = fundDetailProfileInfo.getAsset_config();
        if (!fundDetailProfileInfo.getIs_show_asset_config()){
            ll_piechart_empty_layout.setVisibility(View.VISIBLE);
            activity_fund_record_piechart_layout.setVisibility(View.GONE);
        }else {
            ll_piechart_empty_layout.setVisibility(View.GONE);
            activity_fund_record_piechart_layout.setVisibility(View.VISIBLE);
            assetDistributionDate.setText(asset_config.getEnd_date());
            assetTotalValueTv.setText(fundDetailProfileInfo.getFund_size());
            PieData pieData = getAssetDistributionChat(asset_config, FundRecordActivity.this);
            mChart.setData(pieData);
            customizeLegend();
        }
    }

    private void customizeLegend() {
        colors = mChart.getLegend().getColors();
        labels = mChart.getLegend().getLabels();
        View v1 = new View(this);
        v1.setBackgroundResource(R.drawable.round_background_layout_stock);
        View v2 = new View(this);
        v2.setBackgroundResource(R.drawable.round_background_layout_bond);
        View v3 = new View(this);
        v3.setBackgroundResource(R.drawable.round_background_layout_cash);
        View v4 = new View(this);
        v4.setBackgroundResource(R.drawable.round_background_layout_other);
        List<View> views = new ArrayList<>();
        views.add(v1);
        views.add(v2);
        views.add(v3);
        views.add(v4);
        for (int i = 0; i < datas.size(); i++) {
            LinearLayout.LayoutParams lp = new LinearLayout.
                    LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            lp.weight = 1;//设置比重为1
            LinearLayout layout = new LinearLayout(this);//单个图例的布局
            layout.setOrientation(LinearLayout.HORIZONTAL);//水平排列
            layout.setGravity(Gravity.CENTER_VERTICAL);//垂直居中
            layout.setLayoutParams(lp);

            //添加color
            LinearLayout.LayoutParams colorLP = new LinearLayout.
                    LayoutParams(30, 30);
            colorLP.setMargins(0, 0, 20, 0);
            LinearLayout colorLayout = new LinearLayout(this);
            colorLayout.addView(views.get(i));
            colorLayout.setLayoutParams(colorLP);
            layout.addView(colorLayout);

            //添加label
            TextView labelTV = new TextView(this);
            labelTV.setWidth(140);
            labelTV.setTextSize(12f);
            labelTV.setText(labels[i] + " ");
            layout.addView(labelTV);

            //添加data
            TextView dataTV = new TextView(this);
            dataTV.setTextSize(12f);
            dataTV.setText(datas.get(i) + "%");
            layout.addView(dataTV);
            piechartLegendLayout.addView(layout);//legendLayout为外层布局即整个图例布局，是在xml文件中定义

        }
    }

    private void initPieChart() {
        mChart.setNoDataText("");
        // 圆环距离屏幕上下上下左右的距离
        mChart.setExtraOffsets(0f, 0f, 0f, 0f);
        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setDrawEntryLabels(false);
        mChart.setDragDecelerationFrictionCoef(0.95f);
        //是否显示圆环中间的洞
        mChart.setDrawHoleEnabled(true);
        //设置中间洞的颜色
        mChart.setHoleColor(Color.WHITE);
        //设置中间洞的半径 以下两者相同则内环无阴影效果
        mChart.setHoleRadius(77f);
        mChart.setTransparentCircleRadius(77f);
        //设置圆环透明度及半径
        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);
        //0-三点钟反向
        //90六点钟方向 修改为从底部开始
        //-90 12点钟方向
        mChart.setRotationAngle(-175);
        //触摸是否可以旋转以及松手后旋转的度数
        mChart.setRotationEnabled(false);
        mChart.setHighlightPerTapEnabled(true);
        //设置各色块说明 此处设为不显示
        mChart.getLegend().setEnabled(false);
        // 圆环动画效果
        mChart.animateY(800, Easing.EasingOption.EaseOutCirc);
    }


    private PieData getAssetDistributionChat(FundDetailProfileInfo.AssetConfigBean assetConfig, Context context) {
        //valuesOfNameAndNumner用来表示封装每个饼块的实际数据
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList<>();
        //如果没有登录 或者 获取的数据为0 则平分饼状图
        initAssetDistributionChat(assetConfig, pieEntries, colors);
        //将名字，数字，颜色 放入数据集合
        PieDataSet pieDataSet = new PieDataSet(pieEntries, null);
        pieDataSet.setColors(colors);
        pieDataSet.setSelectionShift(0); // 选中态多出的长度
        pieDataSet.setSliceSpace(1f); //设置个饼状图之间的距离
        PieData pieData = new PieData(pieDataSet);
        //设置默认的每个色块上的百分比字体隐藏
        pieDataSet.setValueTextSize(0f);
        return pieData;
    }

    private void initAssetDistributionChat(FundDetailProfileInfo.AssetConfigBean assetConfig,
                                           ArrayList<PieEntry> pieEntries,
                                           ArrayList<Integer> colors) {
        float percentOfStock = assetConfig.getStock();
        float percentOfBond = assetConfig.getBond();
        float percentOfCash = assetConfig.getCash();
        float percentOfOther = assetConfig.getOther();
        datas.add(percentOfStock);
        datas.add(percentOfBond);
        datas.add(percentOfCash);
        datas.add(percentOfOther);
        colors.add(Color.parseColor("#ff942b"));
        colors.add(Color.parseColor("#6eb53f"));
        colors.add(Color.parseColor("#f3c031"));
        colors.add(Color.parseColor("#4a8fec"));
        final PieEntry pieEntry1 = new PieEntry(percentOfStock, "股票", null);
        final PieEntry pieEntry2 = new PieEntry(percentOfBond, "债券", null);
        final PieEntry pieEntry3 = new PieEntry(percentOfCash, "现金", null);
        final PieEntry pieEntry4 = new PieEntry(percentOfOther, "其他", null);
        pieEntries.add(pieEntry1);
        pieEntries.add(pieEntry2);
        pieEntries.add(pieEntry3);
        pieEntries.add(pieEntry4);
    }

    /**
     * 设置重仓股票列表recyclerview的adapter
     */
    private void setHeavyWarehouseStockAdapter() {
        if (fundDetailProfileInfo.is_show_stock() && stockFundDetailBeanList != null && stockFundDetailBeanList.size()!=0 ) {
            stockLayout.setVisibility(View.VISIBLE);
            heavyWarehouseStockDate.setText(fundDetailProfileInfo.getStock_enddate());
            if (fundRecordHeavyWarehouseStockAdapter == null) {
                fundRecordHeavyWarehouseStockAdapter = new FundRecordHeavyWarehouseStockAdapter(stockFundDetailBeanList, FundRecordActivity.this);
                fundRecordHeavyWarehouseStockAdapter.bindToRecyclerView(heavyWarehouseStockRcv);
                fundRecordHeavyWarehouseStockAdapter.setEmptyView(R.layout.view_empty);
            } else {
                fundRecordHeavyWarehouseStockAdapter.setNewData(stockFundDetailBeanList);
            }
            heavyWarehouseStockRcv.setAdapter(fundRecordHeavyWarehouseStockAdapter);
        } else {
            stockLayout.setVisibility(View.GONE);
        }
    }

    /**
     * 设置持仓债券列表recyclerview的adapter
     */
    private void setHoldingDebentureAdapter() {
        if (fundDetailProfileInfo.is_show_bond()&& bondFundDetailBeanList != null && bondFundDetailBeanList.size()!=0 ) {
            bondLayout.setVisibility(View.VISIBLE);
            holdingDebentureDate.setText(fundDetailProfileInfo.getBond_enddate());
            if (holdingDebentureAdapter == null) {
                holdingDebentureAdapter = new FundRecordHoldingDebentureAdapter(bondFundDetailBeanList, FundRecordActivity.this);
                holdingDebentureAdapter.bindToRecyclerView(holdingDebentureRcv);
                holdingDebentureAdapter.setEmptyView(R.layout.view_empty);
            } else {
                holdingDebentureAdapter.setNewData(bondFundDetailBeanList);
            }
            holdingDebentureRcv.setAdapter(holdingDebentureAdapter);
        } else {
            bondLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        initData();
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.activity_fund_record_iv_back:
                finish();
                break;
            case R.id.activity_fund_record_go_top_ib:
                scrollView.scrollTo(0, 0);
//                scrollView.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        scrollView.fullScroll(ScrollView.FOCUS_UP);
//                    }
//                });
                break;
            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                include_load_error.setVisibility(View.GONE);
                doBusiness(this);
                break;
        }

    }
}

