package com.changju.fanlitou.activity.classifylist;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.bid.BidFixedActivity;
import com.changju.fanlitou.activity.bid.BidInsuranceActivity;
import com.changju.fanlitou.adapter.CanBindTwiceAdapter;
import com.changju.fanlitou.adapter.InsuranceAdapter;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.QueryConditionBean;
import com.changju.fanlitou.bean.homeclassify.Banner;
import com.changju.fanlitou.bean.homeclassify.InsuranceBean;
import com.changju.fanlitou.ui.dialog.AnimDialog;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/5/25.
 */

public class InsuranceActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {

    public static final String INSURANCE = "insurance";

    //private ImageView insurance_head_img;
    private RecyclerView insurance_list;
    private InsuranceAdapter adapter;
    private float invisY;
    private InsuranceBean insuranceBean;
    private TextView tv_classify_insurance_banner;
    private TabLayout ins_rv_t;
    private ImageButton ins_ib;
    private boolean isPerson;
    private QueryConditionBean bean;
    private SmartRefreshLayout refreshLayout;

    //loading&error
    private View include;
    private View include_load_error;
    private AnimDialog mAnimDialog;

    private boolean isFirst = true;

    private boolean isRefresh;

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_classify_list_insurance;
    }

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "保险二级页");

        findViewById(R.id.iv_back_insurance).setOnClickListener(this);
        tv_classify_insurance_banner = (TextView) findViewById(R.id.tv_classify_insurance_banner);
        insurance_list = (RecyclerView) findViewById(R.id.insurance_list);
        ins_rv_t = (TabLayout) findViewById(R.id.ins_rv_t);
        ins_ib = (ImageButton) findViewById(R.id.ins_ib);
        ins_ib.setOnClickListener(this);

        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);

        mAnimDialog = AnimDialog.showDialog(this);

        final String[] key = new String[1];

        ins_rv_t.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                key[0] = (String) tab.getTag();

                if (!isFirst) {
                    mAnimDialog.show();
                } else {
                    isFirst = false;
                }

                isRefresh = false;
                if (isPerson) {
                    getData("", key[0]);
                } else {
                    getData(key[0], "");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        refreshLayout = (SmartRefreshLayout) findViewById(R.id.insurance_refresh_layout);
        refreshLayout.setRefreshHeader(new MaterialHeader(this).setColorSchemeColors(0xfff95353));
        refreshLayout.setEnableHeaderTranslationContent(false);
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                refreshlayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isRefresh = true;
                        if (isPerson) {
                            getData("", key[0]);
                        } else {
                            getData(key[0], "");
                        }
                    }
                }, 1000);
            }
        });
    }


    @Override
    public void doBusiness(Context mContext) {
        initQueryConditionData();
        initClassifyBannerData();
//        initListData();
    }

    private void initQueryConditionData() {
        OkGo.get(ApiUtils.getQueryCondition(this)).execute(new StringCallback() {

            @Override
            public void onSuccess(String s, Call call, Response response) {
                bean = ParseUtils.parseByGson(s, QueryConditionBean.class);

                List<QueryConditionBean.ClassifyInsuranceBean> classifyInsuranceBeen =
                        bean.getClassify_insurance();
                setNewFilterTab(null, classifyInsuranceBeen);
            }
        });
    }

    private void initClassifyBannerData() {
        OkGo.get(ApiUtils.getClassifyBanner(this, INSURANCE)).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Banner banner = ParseUtils.parseByGson(s, Banner.class);
                tv_classify_insurance_banner.setText(banner.getTitle());
                Glide.with(getApplicationContext()).load(banner.getUrl())
                        .into(new SimpleTarget<GlideDrawable>() {
                            @Override
                            public void onResourceReady(GlideDrawable resource,
                                                        GlideAnimation<? super GlideDrawable> glideAnimation) {
                                tv_classify_insurance_banner.setBackground(resource);
                            }
                        });
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initListData() {
        getData("all", "");
    }

    private void getData(String classify_insurance_key, String classify_person_key) {
        OkGo.get(ApiUtils.getBidInsuranceInfo2(classify_insurance_key, classify_person_key)).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                insuranceBean = ParseUtils.parseByGson(s, InsuranceBean.class);
                final WrapContentLinearLayoutManager wrapContentLinearLayoutManager =
                        new WrapContentLinearLayoutManager(InsuranceActivity.this);
                insurance_list.setLayoutManager(wrapContentLinearLayoutManager);

                if (adapter == null) {
                    adapter = new InsuranceAdapter(insuranceBean.getInsurance_bid_list(), getApplicationContext());
                    adapter.setOnItemClickListener(InsuranceActivity.this);
                    adapter.bindToRecyclerView(insurance_list);
                } else {
                    adapter.setNewData(insuranceBean.getInsurance_bid_list());
                }
                adapter.setEmptyView(R.layout.view_empty_top);
                ImageView imageView = (ImageView) adapter.getEmptyView().findViewById(R.id.iv_content);
                imageView.setPadding(0, 160, 0, 0);
                TextView textView = (TextView) adapter.getEmptyView().findViewById(R.id.tv_content);
                textView.setText("暂无数据");
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.GONE);

                if (mAnimDialog != null && mAnimDialog.isShowing()) {
                    mAnimDialog.dismiss();
                }

                if (isRefresh) {
                    refreshLayout.finishRefresh();
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                NormalUtils.showToast(InsuranceActivity.this, R.string.net_error);

                if (mAnimDialog != null && mAnimDialog.isShowing()) {
                    mAnimDialog.dismiss();
                }

                if (isRefresh) {
                    refreshLayout.finishRefresh();
                }
            }
        });
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_insurance:
                finish();
                break;
            case R.id.ins_ib:
                isRefresh = false;
                mAnimDialog.show();
                if (!isPerson) {
                    isPerson = true;
                    getData("", "all");
                    ins_ib.setImageResource(R.mipmap.insurance_img_protect);
                    setNewFilterTab(bean.getClassify_person(), null);
                } else {
                    isPerson = false;
                    getData("all", "");
                    ins_ib.setImageResource(R.mipmap.insurance_img_user);
                    setNewFilterTab(null, bean.getClassify_insurance());
                }
                break;
            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                include_load_error.setVisibility(View.GONE);
                doBusiness(this);
                break;
        }

    }

    private void setNewFilterTab(List<QueryConditionBean.ClassifyPersonBean> classify_person,
                                 List<QueryConditionBean.ClassifyInsuranceBean> classify_insurance
    ) {
        if (classify_insurance != null && classify_insurance.size() > 0) {
            ins_rv_t.removeAllTabs();
            for (int i = 0; i < classify_insurance.size(); i++) {
                TabLayout.Tab tab = ins_rv_t.newTab().setText(classify_insurance.get(i).getName());
                ins_rv_t.addTab(tab.setTag(classify_insurance.get(i).getKey()));
            }
        }

        if (classify_person != null && classify_person.size() > 0) {
            ins_rv_t.removeAllTabs();
            for (int i = 0; i < classify_person.size(); i++) {
                TabLayout.Tab tab = ins_rv_t.newTab().setText(classify_person.get(i).getName());
                ins_rv_t.addTab(tab.setTag(classify_person.get(i).getKey()));
            }
        }

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        InsuranceActivity activity = InsuranceActivity.this;
        if (adapter instanceof InsuranceAdapter) {
            Bundle args = new Bundle();
            args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,
                    ((InsuranceAdapter) adapter).getItem(position).getBid_id());
            args.putString(BidFixedActivity.BID_NAME,
                    ((InsuranceAdapter) adapter).getItem(position).getName());
            activity.startActivity(BidInsuranceActivity.class, args);
        }
    }

    class InsuranceItemAdapterP extends CanBindTwiceAdapter<QueryConditionBean.ClassifyInsuranceBean, BaseViewHolder> {

        private InsuranceActivity context;
        SparseBooleanArray isChecked;

        public InsuranceItemAdapterP(List<QueryConditionBean.ClassifyInsuranceBean> data, Context context) {
            super(R.layout.insurance_item_text, data);
            this.context = (InsuranceActivity) context;
            isChecked = new SparseBooleanArray();
            isChecked.put(0, true);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final QueryConditionBean.ClassifyInsuranceBean item) {

            TextView textView = helper.getView(R.id.tv);
            if (isChecked.get(helper.getAdapterPosition(), false)) {
                textView.setTextColor(context.getResources().getColor(R.color.chart_orange));
            } else {
                textView.setTextColor(context.getResources().getColor(R.color.colorTextSecondary));
            }
            helper.setText(R.id.tv, item.getName());

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isChecked.clear();
                    isChecked.put(helper.getAdapterPosition(), true);
                    String key = item.getKey();
                    String name = item.getName();
                    mAnimDialog.show();
                    context.getData(key, "");
                    notifyDataSetChanged();
                }
            });
        }
    }

    class InsuranceItemAdapter extends CanBindTwiceAdapter<QueryConditionBean.ClassifyPersonBean, BaseViewHolder> {

        private InsuranceActivity context;
        SparseBooleanArray isChecked;

        public InsuranceItemAdapter(List<QueryConditionBean.ClassifyPersonBean> data, Context context) {
            super(R.layout.insurance_item_text, data);
            this.context = (InsuranceActivity) context;
            isChecked = new SparseBooleanArray();
            isChecked.put(0, true);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final QueryConditionBean.ClassifyPersonBean item) {
            TextView textView = helper.getView(R.id.tv);
            if (isChecked.get(helper.getAdapterPosition(), false)) {
                textView.setTextColor(context.getResources().getColor(R.color.chart_orange));
            } else {
                textView.setTextColor(context.getResources().getColor(R.color.colorTextSecondary));
            }
            helper.setText(R.id.tv, item.getName());

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isChecked.clear();
                    isChecked.put(helper.getAdapterPosition(), true);
                    String key = item.getKey();
                    String name = item.getName();
                    mAnimDialog.show();
                    context.getData("", key);
                    notifyDataSetChanged();
                }
            });
        }
    }
}