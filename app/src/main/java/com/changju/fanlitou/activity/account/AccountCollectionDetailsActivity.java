package com.changju.fanlitou.activity.account;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.adapter.CanBindTwiceAdapter;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.account.CurrentIncomeList;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chengww on 2017/5/25.
 */

public class AccountCollectionDetailsActivity extends BaseActivity {
    private String type;

    private int bid_id;

    private boolean isWeek = true;

    @Override
    public void initParams(Bundle params) {
        bid_id = params.getInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
        type = params.getString(AccountCollectionDetailsActivity.class.getSimpleName());
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_account_collection_details;
    }

    private RecyclerView mRecyclerView;

    private TextView tv_yzsy,ct_yysy,tv_title;

    //loading&error
    private View include;
    private View include_load_error;

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "活期回款明细");

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_collection_details);
        mRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(this));

        findViewById(R.id.iv_back_my_account).setOnClickListener(this);

        tv_title = (TextView) findViewById(R.id.tv_title);

        tv_yzsy = (TextView) findViewById(R.id.tv_yzsy);
        tv_yzsy.setOnClickListener(this);
        ct_yysy = (TextView) findViewById(R.id.ct_yysy);
        ct_yysy.setOnClickListener(this);

        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);


    }

    private CurrentIncomeList income;
    private CurrentIncomeList.MonthDataBean month;
    private CurrentIncomeList.WeekDataBean week;
    private CanBindTwiceAdapter weekAdapter,monthAdapter;
    @Override
    public void doBusiness(final Context mContext) {
        if (TextUtils.isEmpty(type) || bid_id < 1){
            NormalUtils.showToast(mContext,"非法的跳转\n错误代码0x01");
            finish();
        }else {
            OkGo.get(ApiUtils.getCurrentIncomeList(mContext,type,bid_id))
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            income = ParseUtils.parseByGson(s,
                                    CurrentIncomeList.class);
                            tv_title.setText(income.getBid_name());
                            month = income.getMonth_data();
                            week = income.getWeek_data();

                            tv_yzsy.setText(week.getTotal_income() + "\n\n近一周收益");
                            ct_yysy.setText(month.getTotal_income() + "\n\n近一月收益");

                            weekAdapter = new WeekAdapter(week.getData_list());
                            monthAdapter = new MonthAdapter(month.getData_list());
                            weekAdapter.bindToRecyclerView(mRecyclerView);
                            weekAdapter.setEmptyView(R.layout.view_empty);

                            include.setVisibility(View.GONE);
                            include_load_error.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);
                            NormalUtils.showToast(mContext, R.string.net_error);
                            include.setVisibility(View.GONE);
                            include_load_error.setVisibility(View.VISIBLE);
                        }
                    });
        }
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_my_account:
                finish();
                break;
            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                include_load_error.setVisibility(View.GONE);
                doBusiness(this);
                break;
            case R.id.tv_yzsy:
                if (!isWeek){
                    weekAdapter.bindToRecyclerView(mRecyclerView);
                    weekAdapter.setEmptyView(R.layout.view_empty);
                    tv_yzsy.setBackgroundColor(getResources().
                            getColor(R.color.colorTextRed));
                    tv_yzsy.setTextColor(getResources().getColor(android.R.color.white));
                    ct_yysy.setBackgroundColor(getResources().
                            getColor(android.R.color.white));
                    ct_yysy.setTextColor(getResources().
                            getColor(R.color.colorTextRed));
                    isWeek = true;
                }
                break;
            case R.id.ct_yysy:
                if (isWeek){
                    monthAdapter.bindToRecyclerView(mRecyclerView);
                    monthAdapter.setEmptyView(R.layout.view_empty);
                    tv_yzsy.setBackgroundColor(getResources().
                            getColor(android.R.color.white));
                    tv_yzsy.setTextColor(getResources().getColor(R.color.colorTextRed));
                    ct_yysy.setBackgroundColor(getResources().
                            getColor(R.color.colorTextRed));
                    ct_yysy.setTextColor(getResources().
                            getColor(android.R.color.white));
                    isWeek = false;
                }
                break;

        }
    }

    private class WeekAdapter extends CanBindTwiceAdapter<CurrentIncomeList.WeekDataBean.DataListBean, BaseViewHolder> {

        WeekAdapter(@Nullable List<CurrentIncomeList.WeekDataBean.DataListBean> data) {
            super(R.layout.recycler_item_collection_details, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, CurrentIncomeList.WeekDataBean.DataListBean item) {
            helper.setText(R.id.tv_time,item.getDate())
                    .setText(R.id.tv_income,"+" + item.getIncome())
                    .setText(R.id.tv_principal,"本金：" + item.getPrincipal() + "元");
        }
    }

    private class MonthAdapter extends CanBindTwiceAdapter<CurrentIncomeList.MonthDataBean.DataListBeanX, BaseViewHolder> {

        MonthAdapter(@Nullable List<CurrentIncomeList.MonthDataBean.DataListBeanX> data) {
            super(R.layout.recycler_item_collection_details, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, CurrentIncomeList.MonthDataBean.DataListBeanX item) {
            helper.setText(R.id.tv_time,item.getDate())
                    .setText(R.id.tv_income,"+" + item.getIncome())
                    .setText(R.id.tv_principal,"本金：" + item.getPrincipal() + "元");
        }
    }
}
