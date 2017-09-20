package com.changju.fanlitou.activity.bid;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.LoginActivity;
import com.changju.fanlitou.activity.WebActivity;
import com.changju.fanlitou.activity.platform.PlatformDetailActivity;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.bean.bid.BidGold;
import com.changju.fanlitou.bean.bid.GoldHistoryPrice;
import com.changju.fanlitou.fragment.bid.BidGolCurrentFragment;
import com.changju.fanlitou.fragment.bid.BidRegularFragment;
import com.changju.fanlitou.ui.dialog.AnimDialog;
import com.changju.fanlitou.ui.dialog.BuyDialog;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.changju.fanlitou.util.UserUtils;
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
import com.growingio.android.sdk.collection.GrowingIO;
import com.jeek.calendar.widget.calendar.util.CalendarUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chengww on 2017/6/14.
 *
 */

public class BidGoldActivity extends BaseActivity {
    private int bid_id;
    private String bid_name;

    private SegmentTabLayout tabLayout;
    private LineChart mChart;

    @Override
    public void initParams(Bundle params) {
        String bid_id_str = params.getString(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
        if (!TextUtils.isEmpty(bid_id_str) && NormalUtils.isInteger(bid_id_str)){
            bid_id = Integer.valueOf(bid_id_str);
        }else {
            bid_id = params.getInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
        }

        bid_name = params.getString(BidFixedActivity.BID_NAME);
    }

    //loading&error
    private View include;
    private View include_load_error;

    private AnimDialog mAnimDialog;


    private ImageView iv_platform_logo;

    private ScrollView mScrollView;
    private TextView tv_buy_now, tv_title, tv_platform_name,
            tv_content, button;

    @Override
    public int bindLayout() {
        return R.layout.activity_bid_gold;
    }

    private TextView tv1,tv2,tv3;
    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "标的详情页-黄金");

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        mChart = (LineChart) findViewById(R.id.chart);
        initChart(mChart);

        mAnimDialog = AnimDialog.showDialog(this);

        findViewById(R.id.iv_back_my_account).setOnClickListener(this);

        String[] mTitles = new String[]{"今天", "近7日", "近30日"};
        tabLayout = (SegmentTabLayout) findViewById(R.id.tab_layout);
        tabLayout.setTabData(mTitles);//给Tablayout设置标题
        //为tablayout设置选中监听
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(final int position) {
                mAnimDialog.show();
                bindGoldPrice(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);

        tv_title = (TextView) findViewById(R.id.tv_title);
        if (!TextUtils.isEmpty(bid_name))
            tv_title.setText(bid_name);

        tv_platform_name = (TextView) findViewById(R.id.tv_platform_name);
        tv_content = (TextView) findViewById(R.id.tv_content);
        button = (TextView) findViewById(R.id.button);
        findViewById(R.id.tv_ckxq).setOnClickListener(this);
        findViewById(R.id.iv_platform_logo).setOnClickListener(this);


        iv_platform_logo = (ImageView) findViewById(R.id.iv_platform_logo);

        mScrollView = (ScrollView) findViewById(R.id.layout_scroll);
        tv_buy_now = (TextView) findViewById(R.id.tv_buy_now);
        button.setOnClickListener(this);
        tv_buy_now.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    int height = v.getHeight();
                    int scrollViewMeasuredHeight = mScrollView.getChildAt(0).getMeasuredHeight();
                    if ((scrollY + height) >= scrollViewMeasuredHeight - 200) {
                        if (tv_buy_now.getVisibility() == View.VISIBLE)
                            tv_buy_now.setVisibility(View.GONE);
                    } else {
                        if (tv_buy_now.getVisibility() == View.GONE)
                            tv_buy_now.setVisibility(View.VISIBLE);
                    }
                }
            });
        } else {
            mScrollView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent event) {

                    int scrollY = view.getScrollY();
                    int height = view.getHeight();
                    int scrollViewMeasuredHeight = mScrollView.getChildAt(0).getMeasuredHeight();
                    if ((scrollY + height) >= scrollViewMeasuredHeight - 200) {
                        if (tv_buy_now.getVisibility() == View.VISIBLE)
                            tv_buy_now.setVisibility(View.GONE);
                    } else {
                        if (tv_buy_now.getVisibility() == View.GONE)
                            tv_buy_now.setVisibility(View.VISIBLE);
                    }

                    return false;
                }
            });
        }
    }

    private GoldHistoryPrice price;

    private void bindGoldPrice(final int position) {
        String[] times = getTimeByIndex(position);
        if (times != null && times.length == 2) {
            OkGo.get(ApiUtils.getGoldHistoryPrice(times[0], times[1]))
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            price = ParseUtils.parseByGson(s, GoldHistoryPrice.class);
                            if (price != null) {
                                List<String> prices = price.getPrice();
                                if (prices != null && prices.size() > 0) {
                                    List<Entry> datas = new ArrayList<>();
                                    for (int i = 0; i < prices.size(); i++) {
                                        Entry entry = new Entry(i, Float.valueOf(prices.get(i)));
                                        datas.add(entry);
                                    }
                                    notifyDataSetChanged(mChart, datas);
                                    String[] bottom = xValuesProcess();
                                    tv1.setText(bottom[0]);
                                    tv2.setText(bottom[1]);
                                    tv3.setText(bottom[2]);
                                }else {
                                    notifyDataSetChanged(mChart, null);
                                    tv1.setText("");
                                    tv2.setText("");
                                    tv3.setText("");
                                }

                            }else {
                                notifyDataSetChanged(mChart, null);
                                tv1.setText("");
                                tv2.setText("");
                                tv3.setText("");
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
    }

    private BidGold gold;

    @Override
    public void doBusiness(final Context mContext) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        onActivityResume();
    }

    @Override
    public void onActivityResume() {
        super.onActivityResume();
        initData(this);
    }

    private void initData(final Context mContext) {
        if (bid_id < 1) {
            NormalUtils.showToast(mContext, "标的ID有误！请返回重试！");
            finish();
        } else {
            bindGoldPrice(0);
            OkGo.get(ApiUtils.getBidGoldInfo(mContext, bid_id))
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {

                            boolean isFirst = gold == null;

                            gold = ParseUtils.parseByGson(s, BidGold.class);

                            if (isFirst){
                                BidGold.BidDetailBean bid = gold.getBid_detail();

                                tv_title.setText(bid.getName());

                                Bundle args = new Bundle();
                                /**
                                 * au_current_bid： 黄金管家活期金
                                 * g_banker_current_bid： 黄金钱包流动金
                                 * normal_gold_bid：黄金管家新手+普通
                                 * g_banker_new_user：黄金钱包新手金
                                 * g_banker_bargin_bid：黄金钱包特价金
                                 * g_banker_normal_bid：黄金钱包普通定期金
                                 */
                                switch (gold.getBid_detail().getDisplay_type()) {
                                    case "au_current_bid":
                                    case "g_banker_current_bid":
                                        BidGolCurrentFragment bean2 =
                                                BidGolCurrentFragment.newInstance(bid,true);
                                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,
                                                bean2).commitAllowingStateLoss();
                                        break;
                                    case "g_banker_bargin_bid":
                                        BidGolCurrentFragment bean =
                                                BidGolCurrentFragment.newInstance(bid,false);
                                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,
                                                bean).commitAllowingStateLoss();
                                        break;
                                    case "g_banker_new_user":
                                        BidRegularFragment regular = BidRegularFragment.newInstance(bid);
                                        args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,
                                                BidRegularFragment.OTHER);
                                        regular.setArguments(args);
                                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, regular).
                                                commitAllowingStateLoss();
                                        break;
                                    case "g_banker_normal_bid":
                                    case "normal_gold_bid":
                                        BidRegularFragment regular2 = BidRegularFragment.newInstance(bid);
                                        args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,
                                                BidRegularFragment.REGULAR);
                                        args.putString(BidRegularFragment.DISPLAY_TYPE,gold.getBid_detail().getDisplay_type());
                                        regular2.setArguments(args);
                                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, regular2).
                                                commitAllowingStateLoss();
                                        break;
                                    default:
                                        NormalUtils.showToast(mContext, "返回信息有误，请返回重试!");
                                        finish();
                                        break;

                                }
                                BidGold.BidDetailBean.PlatformBean platform =
                                        bid.getPlatform();
                                Glide.with(getApplicationContext()).load(platform.getLogo_app())
                                        .into(iv_platform_logo);
                                tv_content.setText("年化收益    " + platform.getInterest_min() +
                                        "%-" + platform.getInterest_max() + "%\n投资周期    " +
                                        platform.getDuration_min() + platform.getDuration_min_unit() +
                                        "-" + platform.getDuration_max() + platform.getDuration_max_unit()
                                        + "\n注册资本    " + platform.getRegistered_capital() + "万");

                                tv_platform_name.setText(platform.getName());
                            }

                            include.setVisibility(View.GONE);
                            include_load_error.setVisibility(View.GONE);

                            tv_buy_now.setText(gold.getButton_name());
                            button.setText(gold.getButton_name());
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);
                            NormalUtils.showToast(mContext, R.string.net_error);

                            if (gold == null){
                                include.setVisibility(View.GONE);
                                include_load_error.setVisibility(View.VISIBLE);
                            }

                        }
                    });
        }
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_my_account:
                finish();
                break;
            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                include_load_error.setVisibility(View.GONE);
                initData(this);
                break;
            case R.id.button:
            case R.id.tv_buy_now:
                showBuyDialog();
                break;
            case R.id.tv_ckxq:
            case R.id.iv_platform_logo:
                if (gold != null) {
                    Bundle args = new Bundle();
                    args.putInt(PlatformDetailActivity.PLATFORM_ID,gold.getBid_detail().getPlatform().getId());
                    args.putString(PlatformDetailActivity.PLATFORM_NAME,gold.getBid_detail().getPlatform().getName());
                    startActivity(PlatformDetailActivity.class,args);
                }else {
                    NormalUtils.showToast(this,"错误，标的数据为空");
                }
                break;
        }
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
        chart.setNoDataTextColor(getResources().getColor(R.color.colorBidName));
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
        xAxis.setTextColor(getResources().getColor(android.R.color.transparent));
        xAxis.setTextSize(10);
        xAxis.setDrawGridLines(false);

        // 设置x轴数据偏移量
//        xAxis.setYOffset(-12);

        YAxis yAxis = chart.getAxisLeft();
        // 显示y轴
        yAxis.setDrawAxisLine(true);
        // 设置y轴数据的位置
        yAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        // 从y轴发出横向直线
        yAxis.setDrawGridLines(true);
        yAxis.setTextColor(getResources().getColor(R.color.colorBidName));
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
        if (!chasrtInit){
            initChart(chart);
        }

        if (values == null){
            chart.setData(null);
            return;
        }

        if (chart.getData() != null && chart.getData().getDataSetCount() > 0) {

            lineDataSet = (LineDataSet) chart.getData().getDataSetByIndex(0);
            lineDataSet.clear();
            lineDataSet.setValues(values);

            //格式化Y轴
            YAxis yAxis = chart.getAxisLeft();
            yAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    DecimalFormat decimalFormat = new DecimalFormat("0.00");
                    return decimalFormat.format(value);
                }
            });

            yAxis.setAxisMaximum(lineDataSet.getYMax());
            yAxis.setAxisMinimum(lineDataSet.getYMin());

            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            lineDataSet = new LineDataSet(values, "");
            // 设置曲线颜色
            lineDataSet.setColor(getResources().getColor(R.color.colorTextRed));
            // 设置平滑曲线
//            lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            // 不显示坐标点的小圆点
            lineDataSet.setDrawCircles(false);
            // 不显示坐标点的数据
            lineDataSet.setDrawValues(false);
            // 不显示定位线
            lineDataSet.setHighlightEnabled(false);

            LineData data = new LineData(lineDataSet);
            chart.setData(data);

            //格式化Y轴
            YAxis yAxis = chart.getAxisLeft();
            yAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    DecimalFormat decimalFormat = new DecimalFormat("0.00");
                    return decimalFormat.format(value);
                }
            });

            yAxis.setAxisMaximum(lineDataSet.getYMax());
            yAxis.setAxisMinimum(lineDataSet.getYMin());
        }

    }

    public String[] getTimeByIndex(int index) {
        switch (index) {
            case 0:
                return calcPreDay(0);
            case 1:
                return calcPreDay(7);
            case 2:
                return calcPreDay(30);
        }
        return null;
    }

    private String[] calcPreDay(int index) {
        if (index <= 0){
            return new String[]{"",""};
        }
        //今天
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        if (index <= 0) {
            return new String[]{year + "-" + month + "-" + day,
                    year + "-" + month + "-" + day};
        }
        //昨天到今天
        int preDay = day;
        int preMonth = month;
        int preYear = year;
        for (int i = 0; i < index; i++) {
            preDay = preDay - 1;
            if (preDay < 1) {
                preMonth = preMonth - 1;
                if (preMonth < 1) {
                    preMonth = 12;
                    preYear = preYear - 1;
                }
                preDay = CalendarUtils.getMonthDays(preYear, preMonth);
            }
        }

        return new String[]{preYear + "-" + preMonth + "-" + preDay,
                year + "-" + month + "-" + day};
    }

    /**
     * 更新图表
     *
     * @param chart     图表
     * @param values    数据
     */
    public void notifyDataSetChanged(LineChart chart, List<Entry> values) {

        setChartData(chart, values);
        chart.invalidate();

//        chart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return xValuesProcess()[(int) value];
//            }
//        });
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
        List<String> times = price.getTime();
        for (int i = 0; i < time.length; i++) {
            time[i] = "";
        }
        if (price != null && price.getTime() != null && price.getTime().size() > 0) {
            if (price.getTime().size() < 2) {
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

    public void showBuyDialog() {
        if (UserUtils.isLogin(this)) {
            if (gold != null) {
                Bundle bundle = new Bundle();
                bundle.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, bid_id);
                bundle.putString(BuyDialog.class.getSimpleName(),
                        gold.getBid_detail().getPlatform().getLogo_app_square());
                bundle.putString(BuyDialog.PLATFORM_NAME,
                        gold.getBid_detail().getPlatform().getName());
                String userName = UserUtils.getUserName(this);

                bundle.putString(WebActivity.BID_NAME,gold.getBid_detail().getName());
                bundle.putString(BuyDialog.URL, ApiUtils.getHeader() + "api/app/1.0/gold_bid/" + bid_id +
                        "/invest/");
                bundle.putString(BuyDialog.PHONE, userName.substring(0, 3)
                        + "****" + userName.substring(7, userName.length()));
                bundle.putBoolean(BuyDialog.IS_AUTO,
                        gold.is_show_register_dialog());
                bundle.putBoolean(BuyDialog.IS_REG, gold.getBid_detail().isIs_user_enjoy_bonus());
                BuyDialog fragment = new BuyDialog();
                fragment.setArguments(bundle);
                fragment.show(getSupportFragmentManager(), "buy");
            }
        } else {
            startActivity(LoginActivity.class);
        }
    }


}
