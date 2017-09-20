package com.changju.fanlitou.activity.fund;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.fund.HistoryNav;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zhangming on 2017/7/18.
 */

public class FundPerformanceActivity extends BaseActivity {

    private LinearLayout footView;
    private HistoryNav historyNav;
    private SimpleAdapter simpleAdapter;

    @Bind(R.id.rv_performance)
    RecyclerView rv_performance;

    @Bind(R.id.performance_title)
    TextView performance_title;

    @Bind(R.id.iv_back_performance)
    ImageView iv_back_performance;

    //loading&error
    private View include;
    private View include_load_error;

    private int fund_id;
    @Override
    public void initParams(Bundle params) {
        fund_id = params.getInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_fund_performance;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this);

        GrowingIO.getInstance().setPageName(this, "基金-业绩表现");

        iv_back_performance.setOnClickListener(this);
        rv_performance.setLayoutManager(new WrapContentLinearLayoutManager(this));
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footView = (LinearLayout) inflater.inflate(R.layout.fund_listitem_foot_performance,null,false);
        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);
    }

    @Override
    public void doBusiness(Context mContext) {
        OkGo.get(ApiUtils.getFundingHistoryNav(mContext,fund_id)).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                historyNav = ParseUtils.parseByGson(s,HistoryNav.class);
                performance_title.setText(historyNav.getTitle());
                simpleAdapter = new SimpleAdapter(historyNav.getDatalist());
                simpleAdapter.bindToRecyclerView(rv_performance);
                simpleAdapter.addFooterView(footView);
                simpleAdapter.setEmptyView(R.layout.view_empty);
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.GONE);
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                NormalUtils.showToast(FundPerformanceActivity.this, R.string.net_error);
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_performance:
                finish();
                break;
            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                include_load_error.setVisibility(View.GONE);
                doBusiness(this);
                break;
        }

    }

    class SimpleAdapter extends BaseQuickAdapter<HistoryNav.DatalistBean, BaseViewHolder> {

        public SimpleAdapter( @Nullable List<HistoryNav.DatalistBean> data) {
            super(R.layout.fund_detail_listitem, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HistoryNav.DatalistBean item) {
            helper.setText(R.id.tv_1,item.getRange())
                    .setText(R.id.tv_2,item.getGr() + "%" )
                    .setText(R.id.tv_3,item.getHs300_gr() + "%" )
                    .setText(R.id.tv_4,item.getRank()+"/"+item.getRank_total());

            if(item.getGr().substring(0,1).equals("+")){
                helper.setTextColor(R.id.tv_2,getResources().getColor(R.color.fund_text_red));
            }else if(item.getGr().substring(0,1).equals("-")){
                helper.setTextColor(R.id.tv_2,getResources().getColor(R.color.fund_text_green));
            }

            if(item.getHs300_gr().substring(0,1).equals("+")){
                helper.setTextColor(R.id.tv_3,getResources().getColor(R.color.fund_text_red));
            }else if(item.getHs300_gr().substring(0,1).equals("-")){
                helper.setTextColor(R.id.tv_3,getResources().getColor(R.color.fund_text_green));
            }
        }
    }
}
