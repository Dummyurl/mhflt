package com.changju.fanlitou.activity.mine;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.SectionEntity;
import com.changju.fanlitou.R;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.mine.WithDrawRecord;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.changju.fanlitou.util.UserUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chengww on 2017/5/26.
 */

public class TakeMoneyRecordActivity extends BaseActivity {
    private RecyclerView recyclerView;

    //loading&error
    private View include;
    private View include_load_error;

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_takemoney_record;
    }

    private SectionAdapter sectionAdapter;

    @Override
    public void initView(View view) {

        UserUtils.checkLogin(this,TakeMoneyRecordActivity.class);

        GrowingIO.getInstance().setPageName(this, "我的-提现记录");

        findViewById(R.id.iv_money_detail_back).setOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycle_money_detail);
        recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(this));
        sectionAdapter = new SectionAdapter(R.layout.recycler_item_takemoney_record,
                R.layout.recycler_item_money_detail_head, null);
        sectionAdapter.bindToRecyclerView(recyclerView);
        sectionAdapter.setEmptyView(R.layout.view_empty);
        TextView tv = (TextView) sectionAdapter.getEmptyView().
                findViewById(R.id.tv_content);
        tv.setText("暂无记录");
        sectionAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener(){

            @Override
            public void onLoadMoreRequested() {
                initData();
            }
        },recyclerView);

        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);

    }

    //页码
    private int page = 1;

    @Override
    public void doBusiness(Context mContext) {
        initData();
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.iv_money_detail_back:
                finish();
                break;
            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                include_load_error.setVisibility(View.GONE);
                initData();
                break;
        }
    }


    public class SectionAdapter extends BaseSectionQuickAdapter<MySection, BaseViewHolder> {

        public SectionAdapter(int layoutResId, int sectionHeadResId, List data) {
            super(layoutResId, sectionHeadResId, data);
        }

        @Override
        protected void convertHead(BaseViewHolder helper, MySection item) {
            helper.setText(R.id.year_month, item.header);
        }


        @Override
        protected void convert(BaseViewHolder helper, MySection item) {
                helper.setText(R.id.tv_money_detail_time, item.t.getSubmit_time())
                        .setText(R.id.tv_balance, "(手续费" + item.t.getFee() + "元）")
                        .setText(R.id.tv_desc, item.t.getBank_name()
                                + " 尾号" + item.t.getAccount_num());
                TextView tv_money_detail_status = helper.getView(R.id.tv_money_detail_status);
                tv_money_detail_status.setText(item.t.getStatus());
                switch (item.t.getStatus_style()) {
                    case "red":
                        tv_money_detail_status.setTextColor(getResources().getColor(R.color.colorTextRed));
                        break;
                    case "blue":
                        tv_money_detail_status.setTextColor(getResources().getColor(R.color.colorTextBlue));
                        break;
                    case "gray":
                    default:
                        tv_money_detail_status.setTextColor(getResources().getColor(R.color.colorBidName));
                        break;
                }


                ImageView logo = helper.getView(R.id.img);
                Glide.with(TakeMoneyRecordActivity.this.getApplicationContext()).
                        load(item.t.getBank_logo()).into(logo);

                /**
                 * 展示样式
                 * red：红色
                 * pos：正值
                 * nag：负值
                 */
                TextView yue = helper.getView(R.id.yue);
                switch (item.t.getAmount_style()) {
                    case "red":
                        yue.setTextColor(getResources().getColor(R.color.colorTextRed));
                        yue.setText(item.t.getAmount() + "元");
                        break;
                    case "black":
                        yue.setTextColor(getResources().getColor(R.color.colorTextSecondary));
                        yue.setText(item.t.getAmount() + "元");
                        break;
                    default:
                        yue.setTextColor(getResources().getColor(R.color.colorTextSecondary));
                        yue.setText(item.t.getAmount() + "元");
                        break;
                }
            }

    }


    public class MySection extends SectionEntity<WithDrawRecord.WithdrawRecordBean.RecordBean> {
        public MySection(boolean isHeader, String header) {
            super(isHeader, header);
        }

        public MySection(WithDrawRecord.WithdrawRecordBean.RecordBean t) {
            super(t);
        }

    }

    private void initData() {

        final Context context = this;
        OkGo.get(ApiUtils.getWithDrawRecord(context, page, 10))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        WithDrawRecord record = ParseUtils.parseByGson(s, WithDrawRecord.class);
                        List<WithDrawRecord.WithdrawRecordBean> recordBeans = record.getWithdraw_record();
                        List<MySection> moneySectionList = new ArrayList<>();
                        for (WithDrawRecord.WithdrawRecordBean recordBean : recordBeans) {
                            List<WithDrawRecord.WithdrawRecordBean.RecordBean> records =
                                    recordBean.getRecord();
                            if (records != null){
                                for (int i = 0; i < records.size(); i++) {
                                    if (i == 0){
                                        MySection section = new MySection(true, recordBean.getMonth());
                                        moneySectionList.add(section);
                                        MySection child = new MySection(records.get(0));
                                        moneySectionList.add(child);
                                    }else {
                                        MySection child = new MySection(records.get(i));
                                        moneySectionList.add(child);
                                    }
                                }
                            }
                        }

                        if (page == 1)
                            sectionAdapter.setNewData(moneySectionList);
                        else {
                            if (moneySectionList.size() < 1) {
                                loadMoreEnd(sectionAdapter,recyclerView);
                            } else {
                                sectionAdapter.addData(moneySectionList);
                                sectionAdapter.loadMoreComplete();
                            }
                        }

                        page++;

                        include.setVisibility(View.GONE);
                        include_load_error.setVisibility(View.GONE);

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        NormalUtils.showToast(context, R.string.net_error);

                        if (page > 1)
                            sectionAdapter.loadMoreFail();

                        include.setVisibility(View.GONE);
                        include_load_error.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void loadMoreEnd(final BaseQuickAdapter adapter, final RecyclerView mRecyclerView) {
        adapter.loadMoreEnd();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.setEnableLoadMore(false);
                        }
                    });
                    RecyclerView.LayoutManager manager = mRecyclerView.getLayoutManager();
                    if (manager == null) return;
                    if (manager instanceof LinearLayoutManager) {
                        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) manager;
                        mRecyclerView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if ((linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1) != adapter.getItemCount()) {
                                    adapter.setEnableLoadMore(true);
                                }
                            }
                        }, 50);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
