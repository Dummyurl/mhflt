package com.changju.fanlitou.fragment.fund;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.fund.FundPerformanceActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.fund.FundBasicInfo;
import com.changju.fanlitou.bean.fund.FundTrade;
import com.changju.fanlitou.ui.dialog.AnimDialog;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;


public class FundIncomeTabFragment extends LazyFragment implements BaseQuickAdapter.OnItemClickListener {

    private SimpleAdapter1 adapter1;
    private FundBasicInfo fundBasicInfo;
    RecyclerView recyclerView;
    private RelativeLayout rl_1;
    private LineChart chart;
    private TextView tv1, tv2, tv3;
    private FundTrade fundTrade;
    private SegmentTabLayout tab_layout;
    private LinearLayout line_chart_layout;
    private RelativeLayout line_chart_rl;
    private TextView line_chart_tv, line_chart_tv1;
    private TextView fund_rate_tv;
    private TextView hs300_rate_tv;
    private TextView tv_performance_title;
    private int fund_id;

    private AnimDialog mAnimDialog;

    private Context context;
    private float eventX;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    private TextView tv_1;
    private TextView tv_2;
    private TextView tv_3;
    private TextView tv_4;

    private boolean isFirst = true;
    private List<Float> hs300RateList;
    private List<Float> rateList;
    private List<Entry> datas;
    private List<Entry> datas1;

    LineDataSet lineDataSet1;
    LineDataSet lineDataSet2;
    private ArrayList<ILineDataSet> lineDataSets;

    private boolean isDetach;

    public static FundIncomeTabFragment newInstance(FundBasicInfo fundBasicInfo, int fund_id) {
        FundIncomeTabFragment fragment = new FundIncomeTabFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, false);
        fragment.setArguments(bundle);
        fragment.fundBasicInfo = fundBasicInfo;
        fragment.fund_id = fund_id;
        return fragment;
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    protected void initView() {

        GrowingIO.getInstance().setPageName(this, "基金详情-收益率走势");

        if (fundBasicInfo.getHistory_nav().getDatalist() != null && fundBasicInfo.getHistory_nav().getDatalist().size() != 0) {
            findViewById(R.id.ll).setVisibility(View.VISIBLE);
            tv_1 = (TextView) findViewById(R.id.tv_1);
            tv_2 = (TextView) findViewById(R.id.tv_2);
            tv_3 = (TextView) findViewById(R.id.tv_3);
            tv_4 = (TextView) findViewById(R.id.tv_4);
            tv_1.setText(fundBasicInfo.getHistory_nav().getData_list_title().getCol_1());
            tv_2.setText(fundBasicInfo.getHistory_nav().getData_list_title().getCol_2());
            tv_3.setText(fundBasicInfo.getHistory_nav().getData_list_title().getCol_3());
            tv_4.setText(fundBasicInfo.getHistory_nav().getData_list_title().getCol_4());
        } else {
            findViewById(R.id.ll).setVisibility(View.GONE);
        }

        mAnimDialog = AnimDialog.showDialog(context);

        tv_performance_title = (TextView) findViewById(R.id.tv_performance_title);
        tv_performance_title.setText(fundBasicInfo.getHistory_nav().getTitle());
        tab_layout = (SegmentTabLayout) findViewById(R.id.tab_layout);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        fund_rate_tv = (TextView) findViewById(R.id.fund_rate_tv);
        hs300_rate_tv = (TextView) findViewById(R.id.hs300_rate_tv);
        chart = (LineChart) findViewById(R.id.chart);
        initChart();
        String[] mTitles = new String[]{"近一月", "近三月", "近一年", "成立至今"};
        tab_layout = (SegmentTabLayout) findViewById(R.id.tab_layout);
        tab_layout.setTabData(mTitles);//给Tablayout设置标题
        //为tablayout设置选中监听
        tab_layout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mAnimDialog.show();
                bindData(fund_id, position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        rl_1 = (RelativeLayout) findViewById(R.id.rl_1);
        rl_1.setOnClickListener(this);
        adapter1 = new SimpleAdapter1(fundBasicInfo.getHistory_nav().getDatalist());
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getActivity()));
        recyclerView.setNestedScrollingEnabled(false);
        adapter1.bindToRecyclerView(recyclerView);
        line_chart_layout = (LinearLayout) findViewById(R.id.line_chart_layout);
        line_chart_rl = (RelativeLayout) findViewById(R.id.line_chart_rl);
        line_chart_tv = (TextView) findViewById(R.id.line_chart_tv);
        line_chart_tv1 = (TextView) findViewById(R.id.line_chart_tv1);
        line_chart_layout.setVisibility(View.GONE);
    }


    private View.OnTouchListener listener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    eventX = event.getX();
                case MotionEvent.ACTION_MOVE:
                    float moveX = event.getX();
                    float dX = moveX - eventX;
                    if (Math.abs(dX) >= 15) {
                        v.getParent().getParent().requestDisallowInterceptTouchEvent(true);
                        if (moveX > NormalUtils.dp2px(getApplicationContext(), 10) && moveX < (NormalUtils.getScreenWidth(getApplicationContext()) - NormalUtils.dp2px(getApplicationContext(), 49))) {
                            line_chart_layout.setVisibility(View.VISIBLE);

                            if (event.getX() > NormalUtils.getScreenWidth(getApplicationContext()) / 2) {
                                line_chart_tv.setVisibility(View.GONE);
                                line_chart_tv1.setVisibility(View.VISIBLE);
                                line_chart_layout.setX(event.getX() - line_chart_tv1.getWidth() - NormalUtils.dp2px(getApplicationContext(), 5));

                            } else {
                                line_chart_tv.setVisibility(View.VISIBLE);
                                line_chart_tv1.setVisibility(View.GONE);
                                line_chart_layout.setX(event.getX());
                            }
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:

                    line_chart_layout.setVisibility(View.GONE);
                    break;
            }
            return false;
        }

    };

    private void bindData(int fund_id, int position) {
        String duration_type = "";
        switch (position) {
            case 0:
                duration_type = "1m";
                break;
            case 1:
                duration_type = "3m";
                break;
            case 2:
                duration_type = "1y";
                break;
            case 3:
                duration_type = "now";
                break;
        }
        OkGo.get(ApiUtils.getFundingIncomeRate(getApplicationContext(), fund_id, "income", duration_type)).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                if (!isDetach) {
                    fundTrade = ParseUtils.parseByGson(s, FundTrade.class);
                    hs300RateList = fundTrade.getHs300_rate();
                    rateList = fundTrade.getRate();
                    datas = new ArrayList<>();
                    datas1 = new ArrayList<>();
                    if (hs300RateList != null && hs300RateList.size() > 0) {
                        for (int i = 0; i < hs300RateList.size(); i++) {
                            Entry entry = new Entry(i, hs300RateList.get(i));
                            datas.add(entry);
                        }
                    }
                    if (rateList != null && rateList.size() > 0) {
                        for (int i = 0; i < rateList.size(); i++) {
                            Entry entry = new Entry(i, rateList.get(i));
                            datas1.add(entry);
                        }
                        setChartTouchListener();
                    }
                    if (datas.size() > 0 || datas1.size() > 0) {
                        lineDataSets = new ArrayList<>();
                        lineDataSet1 = new LineDataSet(datas, "沪深300");
                        lineDataSet2 = new LineDataSet(datas1, "本基金");
                        lineDataSets.add(lineDataSet1);
                        lineDataSets.add(lineDataSet2);
                        setChartData(chart, lineDataSets);
                        chart.invalidate();
                        String[] bottom = xValuesProcess();
                        tv1.setText(bottom[0]);
                        tv2.setText(bottom[1]);
                        tv3.setText(bottom[2]);
                        fund_rate_tv.setText(fundTrade.getIncrease_till_now() + "%");
                        hs300_rate_tv.setText(fundTrade.getHs_300_increase() + "%");
                    }

                    if (!fundTrade.isIs_show_hs300() || datas.size() <= 0) {
                        findViewById(R.id.hs300_rate_tv).setVisibility(View.GONE);
                        findViewById(R.id.tv_hs300_cn).setVisibility(View.GONE);
                    } else {
                        findViewById(R.id.hs300_rate_tv).setVisibility(View.VISIBLE);
                        findViewById(R.id.tv_hs300_cn).setVisibility(View.VISIBLE);
                    }
                }

                mAnimDialog.dismiss();
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                mAnimDialog.dismiss();
            }
        });
    }

    private void setChartTouchListener() {
        chart.setOnTouchListener(listener);
        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                if (e == null) {
                    line_chart_layout.setVisibility(View.VISIBLE);
                } else {
                    int i = 0;
                    if (e.getX() > fundTrade.getRate().size() / 2) {
                        i = (int) e.getX();
                    } else {
                        i = (int) e.getX() + 1;
                    }
                    String date = fundTrade.getDate().get(i);
                    List<Float> hs300_rate = fundTrade.getHs300_rate();

                    float s = fundTrade.getRate().get(i);

                    if (hs300_rate != null && hs300_rate.size() > 0) {
                        float f = fundTrade.getHs300_rate().get(i);
                        line_chart_tv.setText("本基金: " + numberFormat(s) + "%\n" + "沪深300: " + numberFormat(f) + "%\n" + date);
                        line_chart_tv1.setText("本基金: " + numberFormat(s) + "%\n" + "沪深300: " + numberFormat(f) + "%\n" + date);
                        line_chart_tv.setTextColor(Color.WHITE);
                        line_chart_tv1.setTextColor(Color.WHITE);
                    } else {
                        line_chart_tv.setText("本基金: " + numberFormat(s) + "%\n" + date);
                        line_chart_tv1.setText("本基金: " + numberFormat(s) + "%\n" + date);
                        line_chart_tv.setTextColor(Color.WHITE);
                        line_chart_tv1.setTextColor(Color.WHITE);
                    }


                }
            }

            @Override
            public void onNothingSelected() {

            }

        });
    }

    @Override
    protected void doBusiness(Context context) {
        bindData(fund_id, 0);

    }

    private boolean chasrtInit = false;
    private boolean flag = true;

    /**
     * 初始化图表
     */
    public void initChart() {
        // 不显示数据描述
        chart.getDescription().setEnabled(false);
        // 没有数据的时候，显示“暂无数据”
        chart.setNoDataText("暂无数据");
        chart.setNoDataTextColor(Color.parseColor("#5e5e5e"));
        // 不显示表格颜色
        chart.setDrawGridBackground(false);
        // 不可以缩放
        chart.setScaleEnabled(false);
        // 不显示y轴右边的值
        chart.getAxisRight().setEnabled(false);
        // 不显示图例
        Legend legend = chart.getLegend();
        legend.setEnabled(false);
        // 向左偏移15dp，抵消y轴向右偏移的30dp
//        chart.setExtraLeftOffset(-15);

        XAxis xAxis = chart.getXAxis();
        // 显示x轴
        xAxis.setDrawAxisLine(true);
        // 设置x轴数据的位置

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.TRANSPARENT);
        xAxis.setTextSize(10);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisLineColor(Color.parseColor("#ebebeb"));

        // 设置x轴数据偏移量
//        xAxis.setYOffset(-12);

        YAxis yAxis = chart.getAxisLeft();
        // 显示y轴
        yAxis.setDrawAxisLine(true);
        // 设置y轴数据的位置
        yAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        // 从y轴发出横向直线
        yAxis.setDrawGridLines(true);
        yAxis.setGridColor(Color.parseColor("#ebebeb"));
        yAxis.setAxisLineColor(Color.TRANSPARENT);
        yAxis.setTextColor(Color.parseColor("#a5a5a5"));
        yAxis.setLabelCount(5,true);
        yAxis.setTextSize(10);
        chart.invalidate();

        chasrtInit = true;
    }

    /**
     * 设置图表数据
     *
     * @param chart  图表
     * @param values 数据
     */
    public void setChartData(LineChart chart, @NonNull ArrayList<ILineDataSet> values) {
        LineDataSet lineDataSet;
        LineDataSet lineDataSet1;

        if (values.size() > 0) {
            if (!chasrtInit) {
                initChart();
                chasrtInit = true;
            }
            lineDataSet = (LineDataSet) values.get(1);
            lineDataSet1 = (LineDataSet) values.get(0);
            // 设置曲线颜色
            lineDataSet.setColor(Color.parseColor("#f94529"));
            lineDataSet1.setColor(Color.parseColor("#538eeb"));
            // 设置平滑曲线
//            lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            // 设置轴线的最大最小值


            lineDataSet.setCircleRadius(1.5f);
            lineDataSet1.setCircleRadius(1.5f);
            lineDataSet.setDrawCircles(true);
            lineDataSet1.setDrawCircles(true);
            lineDataSet.setDrawCircleHole(false);
            lineDataSet1.setDrawCircleHole(false);
            lineDataSet.setCircleColor(Color.parseColor("#f94529"));
            lineDataSet1.setCircleColor(Color.parseColor("#538eeb"));
            // 不显示坐标点的数据
            lineDataSet.setDrawValues(false);
            lineDataSet1.setDrawValues(false);
            // 不显示定位线
            lineDataSet.setHighlightEnabled(true);
            lineDataSet1.setHighlightEnabled(false);
            lineDataSet.setHighLightColor(Color.TRANSPARENT);
            LineData data = new LineData(values);
            //格式化Y轴
            YAxis yAxis = chart.getAxisLeft();
            yAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    String p = numberFormat(value);
                    return p + "%";
                }
            });

            yAxis.setAxisMaximum(data.getYMax());
            yAxis.setAxisMinimum(data.getYMin());


            chart.setData(data);
            chart.notifyDataSetChanged();
        } else {
            chart.setData(null);
            chart.notifyDataSetChanged();
        }


    }

    private String numberFormat(float value) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(value);
    }

    /**
     * x轴数据处理
     *
     * @return x轴数据
     */
    private String[] xValuesProcess() {

        //0 3 6
        //x轴最多显示7个
        String[] time = new String[3];
        List<String> times = fundTrade.getDate();
        for (int i = 0; i < time.length; i++) {
            time[i] = "";
        }
        if (fundTrade != null && fundTrade.getDate() != null && fundTrade.getDate().size() > 0) {
            if (fundTrade.getDate().size() < 2) {
                time[0] = times.get(0);
            } else if (times.size() < 3) {
                time[0] = times.get(0);
                time[2] = times.get(1);
            } else {
                int halfSize = times.size() / 2;
                time[0] = times.get(0);
                time[1] = times.get(halfSize);
                time[2] = times.get(times.size() - 1);
            }
        }
        return time;

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_fund_income_tab;
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.rl_1:
                Bundle bundle = new Bundle();
                bundle.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, fund_id);
                Intent i = new Intent(getActivity(), FundPerformanceActivity.class);
                i.putExtras(bundle);
                startActivity(i);
                break;
        }
    }

    private class SimpleAdapter1 extends BaseQuickAdapter<FundBasicInfo.HistoryNavBean.DatalistBeanX, BaseViewHolder> {

        public SimpleAdapter1(@Nullable List<FundBasicInfo.HistoryNavBean.DatalistBeanX> data) {
            super(R.layout.fund_detail_listitem, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, FundBasicInfo.HistoryNavBean.DatalistBeanX item) {
            helper.setText(R.id.tv_1, item.getRange())
                    .setText(R.id.tv_2, item.getGr() + "%")
                    .setText(R.id.tv_3, item.getHs300_gr() + "%")
                    .setText(R.id.tv_4, item.getRank() + "/" + item.getRank_total());

            if (item.getGr().substring(0, 1).equals("+")) {
                helper.setTextColor(R.id.tv_2, getResources().getColor(R.color.fund_text_red));
            } else if (item.getGr().substring(0, 1).equals("-")) {
                helper.setTextColor(R.id.tv_2, getResources().getColor(R.color.fund_text_green));
            }else {
                helper.setTextColor(R.id.tv_2, getResources().getColor(R.color.colorBidName));
            }

            if (item.getHs300_gr().substring(0, 1).equals("+")) {
                helper.setTextColor(R.id.tv_3, getResources().getColor(R.color.fund_text_red));
            } else if (item.getHs300_gr().substring(0, 1).equals("-")) {
                helper.setTextColor(R.id.tv_3, getResources().getColor(R.color.fund_text_green));
            }else {
                helper.setTextColor(R.id.tv_2, getResources().getColor(R.color.colorBidName));
            }

            if (helper.getAdapterPosition() == getData().size() - 1) {
                helper.getView(R.id.line).setBackgroundColor(Color.WHITE);
            } else {
                helper.getView(R.id.line).setBackgroundColor(getResources().getColor(R.color.colorLine));
            }

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        isDetach = true;
    }
}
