package com.changju.fanlitou.fragment.fund;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.fund.FundIncomeActivity;
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
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;


public class FundRealTabFragment extends LazyFragment implements BaseQuickAdapter.OnItemClickListener {

    private SimpleAdapter2 adapter;
    private FundBasicInfo fundBasicInfo;
    RecyclerView recyclerView;
    private RelativeLayout rl_1;
    private AnimDialog mAnimDialog;

    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    private LineChart chart;
    private TextView tv1, tv2, tv3, line_chart_tv1, line_chart_tv2,line_chart_tv3, line_chart_tv4;
    private FundTrade fundTrade;
    private SegmentTabLayout tab_layout;
    private LinearLayout line_chart_layout;
    private RelativeLayout line_chart_rl;
    private LinearLayout line_chart_tv_ll_left,line_chart_tv_ll_right;
    private View line_chart_dash;
    private TextView tv_performance_title;

    private int fund_id;

    private TextView tv_1;
    private TextView tv_2;
    private TextView tv_3;
    private TextView tv_4;

    public static FundRealTabFragment newInstance(FundBasicInfo fundBasicInfo, int fund_id) {
        FundRealTabFragment fragment = new FundRealTabFragment();
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

        GrowingIO.getInstance().setPageName(this, "基金详情-净值走势");

        if(fundBasicInfo.getHistory_unit_nav().getDatalist() != null && fundBasicInfo.getHistory_unit_nav().getDatalist().size() != 0){
            findViewById(R.id.ll).setVisibility(View.VISIBLE);
            tv_1 = (TextView)findViewById(R.id.tv_1);
            tv_2 = (TextView)findViewById(R.id.tv_2);
            tv_3 = (TextView)findViewById(R.id.tv_3);
            tv_4 = (TextView)findViewById(R.id.tv_4);
            tv_1.setText(fundBasicInfo.getHistory_unit_nav().getData_list_title().getCol_1());
            tv_2.setText(fundBasicInfo.getHistory_unit_nav().getData_list_title().getCol_2());
            tv_3.setText(fundBasicInfo.getHistory_unit_nav().getData_list_title().getCol_3());
            tv_4.setText(fundBasicInfo.getHistory_unit_nav().getData_list_title().getCol_4());
        }else{
            findViewById(R.id.ll).setVisibility(View.GONE);
        }

        mAnimDialog = AnimDialog.showDialog(context);

        tv_performance_title = (TextView) findViewById(R.id.tv_performance_title);
        tv_performance_title.setText(fundBasicInfo.getHistory_unit_nav().getTitle());
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        chart = (LineChart) findViewById(R.id.chart);
        initChart(chart);
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
        adapter = new SimpleAdapter2(fundBasicInfo.getHistory_unit_nav().getDatalist());
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getActivity()));
        recyclerView.setNestedScrollingEnabled(false);
        adapter.bindToRecyclerView(recyclerView);

        line_chart_layout = (LinearLayout) findViewById(R.id.line_chart_layout_fund_income);
        line_chart_tv_ll_left = (LinearLayout) findViewById(R.id.ll_left);
        line_chart_tv_ll_right = (LinearLayout) findViewById(R.id.ll_right);
        line_chart_dash =  findViewById(R.id.line_chart_layout_fund_dash);
        line_chart_tv1 = (TextView) findViewById(R.id.line_chart_tv1);
        line_chart_tv2 = (TextView) findViewById(R.id.line_chart_tv2);
        line_chart_tv3 = (TextView) findViewById(R.id.line_chart_tv3);
        line_chart_tv4 = (TextView) findViewById(R.id.line_chart_tv4);
        line_chart_layout.setVisibility(View.GONE);
        line_chart_dash.setVisibility(View.GONE);

    }

    private float eventX;
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
                        if (moveX > NormalUtils.dp2px(getApplicationContext(), 18) && moveX < (NormalUtils.getScreenWidth(getApplicationContext()) - NormalUtils.dp2px(getApplicationContext(), 49))) {
                            line_chart_layout.setVisibility(View.VISIBLE);
                            line_chart_dash.setVisibility(View.VISIBLE);
                            if (event.getX() > NormalUtils.getScreenWidth(getApplicationContext()) / 2) {
                                line_chart_tv_ll_right.setVisibility(View.GONE);
                                line_chart_tv_ll_left.setVisibility(View.VISIBLE);
                                line_chart_layout.setX(event.getX() - line_chart_tv_ll_right.getWidth() - NormalUtils.dp2px(getApplicationContext(), 5));

                            } else {
                                line_chart_tv_ll_right.setVisibility(View.VISIBLE);
                                line_chart_tv_ll_left.setVisibility(View.GONE);
                                line_chart_layout.setX(event.getX());
                            }
                        }
                    }

                    break;
                case MotionEvent.ACTION_UP:
                    line_chart_tv_ll_right.setVisibility(View.GONE);
                    line_chart_tv_ll_left.setVisibility(View.GONE);
                    line_chart_dash.setVisibility(View.GONE);
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
        OkGo.get(ApiUtils.getFundingIncomeRate(getActivity(), fund_id, "real", duration_type)).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                line_chart_layout.setVisibility(View.VISIBLE);
                fundTrade = ParseUtils.parseByGson(s, FundTrade.class);
                List<Float> rateList = fundTrade.getRate();
                List<Entry> datas = new ArrayList<>();
                if(rateList != null && !rateList.isEmpty()){
                    for (int i = 0; i < rateList.size(); i++) {
                        Entry entry = new Entry(i, rateList.get(i));
                        datas.add(entry);
                    }
                    chart.setOnTouchListener(listener);
                    chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                        @Override
                        public void onValueSelected(Entry e, Highlight h) {
                            int i = 0;
                            if (e.getX() > fundTrade.getRate().size() / 2) {
                                i = (int) e.getX();
                            } else {
                                i = (int) e.getX() + 1;
                            }
                            String date = fundTrade.getDate().get(i);
                            float s = fundTrade.getRate().get(i);
                            line_chart_tv1.setText(numberFormat(s));
                            line_chart_tv2.setText(date);
                            line_chart_tv3.setText(numberFormat(s));
                            line_chart_tv4.setText(date);
                        }

                        @Override
                        public void onNothingSelected() {

                        }

                    });
                }
                setChartData(chart, datas);
                chart.invalidate();
                String[] bottom = xValuesProcess();
                tv1.setText(bottom[0]);
                tv2.setText(bottom[1]);
                tv3.setText(bottom[2]);

                mAnimDialog.dismiss();
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                mAnimDialog.dismiss();
            }
        });
    }

    @Override
    protected void doBusiness(Context context) {
        bindData(fund_id, 0);
    }

    private boolean chasrtInit = false;

    /**
     * 初始化图表
     *
     * @param chart 原始图表
     * @return 初始化后的图表
     */
    public LineChart initChart(LineChart chart) {
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
        // 设置y轴数据偏移量
//        yAxis.setXOffset(30);
//        yAxis.setYOffset(-3);
//        yAxis.setAxisMinimum(0);

        //Matrix matrix = new Matrix();
        // x轴缩放1.5倍
        //matrix.postScale(1.5f, 1f);
        // 在图表动画显示之前进行缩放
        //chart.getViewPortHandler().refresh(matrix, chart, false);
        // x轴执行动画
//        chart.animateX(2000);
        chart.invalidate();
        chasrtInit = true;
        return chart;
    }

    /**
     * 设置图表数据
     *
     * @param chart  图表
     * @param values 数据
     */
    public void setChartData(LineChart chart, List<Entry> values) {
        LineDataSet lineDataSet;
        if (!chasrtInit) {
            initChart(chart);
            chasrtInit = true;
        }
        if (values!=null && values.size()>0) {
            lineDataSet = new LineDataSet(values, "");
            // 设置曲线颜色
            lineDataSet.setColor(Color.parseColor("#f94529"));
            // 显示坐标点的小圆点
            lineDataSet.setDrawCircles(true);
            // 不显示坐标点的数据
            lineDataSet.setDrawValues(false);
            // 显示定位线
            lineDataSet.setHighlightEnabled(true);
            lineDataSet.setHighLightColor(Color.TRANSPARENT);
            lineDataSet.setCircleRadius(1.5f);
            lineDataSet.setDrawCircleHole(false);
            lineDataSet.setCircleColor(Color.parseColor("#f94529"));
            lineDataSet.setDrawFilled(true);
            Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.fade_red);
            lineDataSet.setFillDrawable(drawable);
            lineDataSet.setFillAlpha(100);

            LineData data = new LineData(lineDataSet);
            data.setValueTextSize(9f);
            data.setDrawValues(false);

            //格式化Y轴
            YAxis yAxis = chart.getAxisLeft();
            yAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return numberFormat(value);
                }
            });

            yAxis.setAxisMaximum(data.getYMax());
            yAxis.setAxisMinimum(data.getYMin());

            chart.setData(data);
        }else {
            chart.setData(null);
        }
    }

    private String numberFormat(float value) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0000");
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
        return R.layout.fragment_fund_real_tab;
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.rl_1:
                Bundle bundle = new Bundle();
                bundle.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,fund_id);
                Intent i = new Intent(getActivity(), FundIncomeActivity.class);
                i.putExtras(bundle);
                startActivity(i);
                break;
        }
    }

    class SimpleAdapter2 extends BaseQuickAdapter<FundBasicInfo.HistoryUnitNavBean.DatalistBean, BaseViewHolder> {

        public SimpleAdapter2(@Nullable List<FundBasicInfo.HistoryUnitNavBean.DatalistBean> data) {
            super(R.layout.fund_detail_listitem, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, FundBasicInfo.HistoryUnitNavBean.DatalistBean item) {
            helper.setText(R.id.tv_1, item.getDate())
                    .setText(R.id.tv_2, item.getUnit_nav())
                    .setText(R.id.tv_3, item.getAccum_nav())
                    .setText(R.id.tv_4, item.getDay_gr());

            if (item.getDay_gr().substring(0, 1).equals("-")) {
                helper.setTextColor(R.id.tv_4, getResources().getColor(R.color.fund_text_green));
            }else if (item.getDay_gr().substring(0, 1).equals("+")){
                helper.setTextColor(R.id.tv_4, getResources().getColor(R.color.fund_text_red));
            }else {
                helper.setTextColor(R.id.tv_2, getResources().getColor(R.color.colorBidName));
            }

            if(helper.getAdapterPosition() == getData().size() - 1){
                helper.getView(R.id.line).setBackgroundColor(Color.WHITE);
            } else {
                helper.getView(R.id.line).setBackgroundColor(getResources().getColor(R.color.colorLine));
            }

        }
    }
}
