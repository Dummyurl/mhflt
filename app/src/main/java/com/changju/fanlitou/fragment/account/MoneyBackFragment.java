package com.changju.fanlitou.fragment.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.LoginActivity;
import com.changju.fanlitou.activity.MainActivity;
import com.changju.fanlitou.activity.account.AccountCollectionDetailsActivity;
import com.changju.fanlitou.activity.account.DailyRebateActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.account.CalendarData;
import com.changju.fanlitou.ui.dialog.AnimDialog;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.changju.fanlitou.util.UserUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.jeek.calendar.widget.calendar.month.MonthCalendarView;
import com.jeek.calendar.widget.calendar.month.MonthView;
import com.jeek.calendar.widget.calendar.schedule.ScheduleLayout;
import com.jeek.calendar.widget.calendar.util.OnCalendarClickListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chengww on 2017/5/8.
 */

public class MoneyBackFragment extends LazyFragment {

    private boolean isLogin = false;

    private RecyclerView mRecyclerView;
    private ScheduleLayout slSchedule;
    private MonthCalendarView mcvCalendar;
    private TextView mTitle, tv_total, tv_total_repay_count;
    private SimpleAdapter simpleAdapter;

    private AnimDialog mAnimDialog;
    private MainActivity activity;
    private int[] data;
    private List<Integer> blues;
    private List<Integer> reds;

    //等待恢复的圆点列表
    private int resumeDay = 0;
    private boolean isRed;
    private List<CalendarData.CalendarDataBean.RepayListBean> repay_list;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    protected void initView() {

        GrowingIO.getInstance().setPageName(this, "账本-回款日历");

        //初始化红点、蓝点列表
        reds = new ArrayList<>();
        blues = new ArrayList<>();

        isLogin = UserUtils.isLogin(activity);
        tv_total = (TextView) findViewById(R.id.tv_total);
        tv_total_repay_count = (TextView) findViewById(R.id.tv_total_repay_count);
        findViewById(R.id.iv_left).setOnClickListener(this);
        findViewById(R.id.iv_right).setOnClickListener(this);
        mTitle = (TextView) findViewById(R.id.tv_title);
        slSchedule = (ScheduleLayout) findViewById(R.id.slSchedule);
        mcvCalendar = (MonthCalendarView) findViewById(R.id.mcvCalendar);
//        findViewById(R.id.layout_header).setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                v.dispatchTouchEvent(event);
//                return true;
//            }
//        });
        mTitle = (TextView) findViewById(R.id.tv_title);
        //初始化时间标题
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        currentMonth = month - 1;
        int year = calendar.get(Calendar.YEAR);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        data = new int[]{year, month, day};
        //月份 0-11
        String monthStr = month > 8 ? String.valueOf(month) : "0" + month;
        mTitle.setText(year + "年  " + monthStr + "月");

        mRecyclerView = (RecyclerView) findViewById(R.id.rvScheduleList);
        mRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(activity));

        slSchedule.setOnCalendarClickListener(new OnCalendarClickListener() {

            @Override
            public void onClickDate(int year, int month, int day) {
                String monthStr = month > 8 ? String.valueOf(month + 1) : "0" + (month + 1);
                mTitle.setText(year + "年  " + monthStr + "月");
                if (currentMonth == month && day > 0) {
                    isUpdate = false;
                    //红点恢复
                    if (resumeDay > 0) {
                        slSchedule.addTaskHint(resumeDay, isRed);
                        resumeDay = 0;
                    }
                    //红点移除
                    for (int i = 0; i < reds.size(); i++) {
                        if (reds.get(i) == day) {
                            isRed = true;
                            resumeDay = day;
                            break;
                        }
                    }

                    for (int i = 0; i < blues.size(); i++) {
                        if (blues.get(i) == day) {
                            isRed = false;
                            resumeDay = day;
                            break;
                        }
                    }

                    if (resumeDay > 0)
                        slSchedule.removeTaskHint(resumeDay);

                    //点击天,更新天视图回款
                    if (repay_list == null) {
                        simpleAdapter.setNewData(null);
                    } else {
                        List<CalendarData.CalendarDataBean.RepayListBean> repays =
                                new ArrayList<>();
                        for (int i = 0; i < repay_list.size(); i++) {
                            CalendarData.CalendarDataBean.RepayListBean repayListBean = repay_list.get(i);
                            if (repayListBean.getRepay_day() == day) {
                                repays.add(repayListBean);
                            }
                        }
                        simpleAdapter.setNewData(repays);
                    }

                } else {
                    isUpdate = true;
                    currentMonth = month;
                    initData(activity, year, month + 1, day);
                }
            }

            @Override
            public void onPageChange(int year, int month, int day) {
//                String monthStr = month > 9 ? String.valueOf(month) : "0" + month;
//                mTitle.setText(year + "年  " + monthStr + "月");
            }
        });
    }

    private int currentMonth = -1;
    private boolean isUpdate = true;

    @Override
    protected void doBusiness(final Context context) {
        initData(context, data[0], data[1], 0);
    }


    private void initData(final Context context, int year, int month, int day) {
        if (mAnimDialog == null)
            mAnimDialog = AnimDialog.showDialog(context);
        mAnimDialog.show();
        OkGo.get(ApiUtils.getRepayCalendar(context, year, month, day))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        CalendarData calendar = ParseUtils.parseByGson(s, CalendarData.class);

                        repay_list = calendar.getCalendar_data().getRepay_list();

                        if (simpleAdapter == null) {
                            simpleAdapter = new SimpleAdapter(repay_list);
                            simpleAdapter.bindToRecyclerView(mRecyclerView);
                            simpleAdapter.setEmptyView(R.layout.view_empty_top);

                            TextView tv = (TextView) simpleAdapter.
                                    getEmptyView().findViewById(R.id.tv_content);
                            startToInvest(tv);

                            //点击事件
                            simpleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    CalendarData.CalendarDataBean.RepayListBean recordsBean
                                            = (CalendarData.CalendarDataBean.RepayListBean) adapter.getItem(position);
                                    startToRepayDetail(recordsBean.getType(), recordsBean.getBid_id());
                                }
                            });


                        } else {
                            simpleAdapter.setNewData(repay_list);
                        }
                        //刷新红点 蓝点
                        if (isUpdate) {
                            //圆点已刷新，无需恢复
                            resumeDay = 0;

                            List<CalendarData.CalendarDataBean.RepayingDateListBean> repaying =
                                    calendar.getCalendar_data().getRepaying_date_list();
                            reds.clear();
                            if (repaying != null && repaying.size() > 0) {
                                for (int i = 0; i < repaying.size(); i++) {
                                    int day1 = repaying.get(i).getDay();
                                    if (!reds.contains(day1))
                                        reds.add(day1);
                                }
                                mcvCalendar.getCurrentMonthView().setRedHintList(reds);
//                                wcvCalendar.getCurrentWeekView().setRedHintList(reds);
                            } else {
                                MonthView currentMonthView = mcvCalendar.getCurrentMonthView();
                                if (currentMonthView != null)
                                    currentMonthView.setRedHintList(null);
                            }

                            List<CalendarData.CalendarDataBean.RepayedDateListBean> repayed =
                                    calendar.getCalendar_data().getRepayed_date_list();
                            blues.clear();
                            if (repayed != null && repayed.size() > 0) {
                                for (int i = 0; i < repayed.size(); i++) {
                                    int day1 = repayed.get(i).getDay();
                                    if (!blues.contains(day1))
                                        blues.add(day1);
                                }
                                mcvCalendar.getCurrentMonthView().setBlueHintList(blues);
//                                wcvCalendar.getCurrentWeekView().setBlueHintList(blues);
                            } else {
                                mcvCalendar.getCurrentMonthView().setBlueHintList(null);
                            }

                            //Header
                            tv_total.setText(String.valueOf(calendar.getCalendar_data().getTotal()));
                            tv_total_repay_count.setText(String.valueOf(
                                    calendar.getCalendar_data().getTotal_repay_count()));
                        }


                        mAnimDialog.dismiss();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        mAnimDialog.dismiss();
                        if (simpleAdapter == null) {
                            simpleAdapter = new SimpleAdapter(null);
                            simpleAdapter.bindToRecyclerView(mRecyclerView);
                            simpleAdapter.setEmptyView(R.layout.view_load_error_top);
                        }
                        NormalUtils.showToast(context, R.string.net_error);
                    }
                });
    }

    class SimpleAdapter extends BaseQuickAdapter<CalendarData.CalendarDataBean.RepayListBean, BaseViewHolder> {
        public SimpleAdapter(@Nullable List<CalendarData.CalendarDataBean.RepayListBean> data) {
            super(R.layout.recycler_item_repaying, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, CalendarData.CalendarDataBean.RepayListBean item) {
            helper.setText(R.id.tv_latest_repay_date, "最近回款日:" + item.getRepay_date())
                    .setText(R.id.tv_term, "第" + item.getTerm()
                            + "期/共" + item.getTotal_term() + "期")
                    .setText(R.id.tv_platform_name,
                            item.getPlatform_name() + " | " + item.getBid_name())
                    .setText(R.id.tv_receivable_principal, String.valueOf(item.getPrincipal()))
                    .setText(R.id.tv_receivable_interest, String.valueOf(item.getInterest()))
                    .setText(R.id.tv_principal, "回款本金(元)")
                    .setText(R.id.tv_receivable_interest_title, "回款利息(元)")
                    .setText(R.id.tv_payment_plan, item.getCondition())
                    .setText(R.id.tv_fanli, "当月累计返利:"
                            + item.getTotal_bonus() + "元" + "｜总计返利:"
                            + item.getTotal_bonus_count() + "笔");

            if (item.isIs_daily_bonus()) {
                helper.getView(R.id.tv_daily_bonus).setVisibility(View.VISIBLE);
            } else {
                helper.getView(R.id.tv_daily_bonus).setVisibility(View.GONE);
            }

            TextView tv_payment_plan = helper.getView(R.id.tv_payment_plan);
            if (item.getCondition().equals("已回款")) {
                tv_payment_plan.setTextColor(activity.getResources().getColor(R.color.colorTextBlue));
            } else {
                tv_payment_plan.setTextColor(activity.getResources().getColor(R.color.colorTextRed));
            }
        }
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_money_back;
    }

    @Override
    public void widgetClick(View v) {
        int year = slSchedule.getCurrentSelectYear();
        int month = slSchedule.getCurrentSelectMonth();

        switch (v.getId()) {
            case R.id.iv_left:
                if (month < 1) {
                    month = 11;
                    year = year - 1;
                } else {
                    month = month - 1;
                }
                slSchedule.initData(year, month, 0);
//                slSchedule.getMonthCalendar().onClickLastMonth(year,month,1);
                break;
            case R.id.iv_right:
                if (month > 10) {
                    month = 0;
                    year = year + 1;
                } else {
                    month = month + 1;
                }
                slSchedule.initData(year, month, 0);
//                slSchedule.getMonthCalendar().onClickNextMonth(year,month,1);
                break;
        }
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

    @Override
    public void onResume() {
        super.onResume();
        if (isLogin != UserUtils.isLogin(activity) && data != null) {
            isLogin = !isLogin;
            doBusiness(activity);
        }
    }

    public void startToInvest(View tv) {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,
                        UserUtils.isLogin(activity) ?
                                MainActivity.class : LoginActivity.class);
                Bundle args = new Bundle();
                args.putBoolean(MainActivity.class.getSimpleName(), true);
                intent.putExtras(args);
                startActivity(intent);
            }
        });
    }
}
