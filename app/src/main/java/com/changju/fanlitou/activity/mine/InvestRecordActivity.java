package com.changju.fanlitou.activity.mine;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.MainActivity;
import com.changju.fanlitou.adapter.BannerAdapter;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.mine.InvestRecord;
import com.changju.fanlitou.bean.mine.InvestRecordDone;
import com.changju.fanlitou.bean.mine.TicketBean;
import com.changju.fanlitou.ui.dialog.AnimDialog;
import com.changju.fanlitou.ui.dialog.NormalWhiteDialog;
import com.changju.fanlitou.ui.dialog.TicketsDialog;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.changju.fanlitou.util.UserUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chengww on 2017/5/26.
 * 我的--投资记录
 */

public class InvestRecordActivity extends BaseActivity {

    //默认进入的tab
    private int defaultTab = -1;

    private RecyclerView mOneRecycler;
    private RecyclerView mTwoRecycler;
    private RecyclerView mThreeRecycler;

    private SmartRefreshLayout refreshLayout;

    //loading&error
    private View include;
    private View include_load_error;

    //recyclerView上次偏移量
    private int lastOffset;
    private int lastPosition;

    //是否刷新
    private boolean isRefresh;

    @Override
    public void initParams(Bundle params) {
        String defaultTabStr = params.getString(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
        if (!TextUtils.isEmpty(defaultTabStr) && NormalUtils.isInteger(defaultTabStr)) {
            defaultTab = Integer.valueOf(defaultTabStr);
        } else {
            defaultTab = params.getInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
        }

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_invest_record;
    }

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private SimpleDoneAdapter mAdapter1;
    private SimpleAdapter mAdapter2, mAdapter3;
    private InvestRecordDone bean1;
    private InvestRecord bean2, bean3;

    private AnimDialog mAnimDialog;

    @Override
    public void initView(View view) {

        UserUtils.checkLogin(this, InvestRecordActivity.class);

        GrowingIO.getInstance().setPageName(this, "我的-投资记录");

        mViewPager = (ViewPager) findViewById(R.id.viewpage_invest_report);
        mTabLayout = (TabLayout) findViewById(R.id.tab_invest_report);

        findViewById(R.id.iv_back_my_account).setOnClickListener(this);

        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);

        List<View> lists = new ArrayList<>();
        mOneRecycler = new RecyclerView(this);
        mOneRecycler.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mOneRecycler.setLayoutManager(new WrapContentLinearLayoutManager(this));
        lists.add(mOneRecycler);

        mTwoRecycler = new RecyclerView(this);
        mTwoRecycler.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mTwoRecycler.setLayoutManager(new WrapContentLinearLayoutManager(this));
        lists.add(mTwoRecycler);

        mThreeRecycler = new RecyclerView(this);
        mThreeRecycler.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mThreeRecycler.setLayoutManager(new WrapContentLinearLayoutManager(this));
        lists.add(mThreeRecycler);

        mAdapter1 = new SimpleDoneAdapter(null);
        mAdapter2 = new SimpleAdapter(null);
        mAdapter3 = new SimpleAdapter(null);
        mAdapter1.bindToRecyclerView(mOneRecycler);

        mAdapter2.bindToRecyclerView(mTwoRecycler);

        mAdapter3.bindToRecyclerView(mThreeRecycler);

        pullToRefresh(mAdapter1, 1, mOneRecycler, "done");
        pullToRefresh(mAdapter2, 2, mTwoRecycler, "pending");
        pullToRefresh(mAdapter3, 3, mThreeRecycler, "dropped");


        mViewPager.setAdapter(new BannerAdapter(
                lists, new String[]{"已投标", "确认中", "未投标"}));
        mTabLayout.setupWithViewPager(mViewPager);


        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                if (pos == 1 && bean2 == null) {
                    if (mAnimDialog == null)
                        mAnimDialog = AnimDialog.showDialog(InvestRecordActivity.this);
                    mAnimDialog.show();
                    isRefresh = false;
                    initBean(pos, "pending");
                } else if (pos == 2 && bean3 == null) {
                    if (mAnimDialog == null)
                        mAnimDialog = AnimDialog.showDialog(InvestRecordActivity.this);
                    mAnimDialog.show();
                    isRefresh = false;
                    initBean(pos, "dropped");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        if (defaultTab > -1) {
            mViewPager.setCurrentItem(defaultTab, false);
            defaultTab = -1;
        }

        refreshLayout = (SmartRefreshLayout) findViewById(R.id.invest_refresh_layout);
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
                        if (mViewPager.getCurrentItem() == 0) {
                            page1 = 1;
                            initBean(0, "done");
                        } else if (mViewPager.getCurrentItem() == 1) {
                            page2 = 1;
                            initBean(1, "pending");
                        } else {
                            page3 = 1;
                            initBean(2, "dropped");
                        }
                    }
                }, 1000);
            }
        });
    }

    private void setEmptyView(BaseQuickAdapter adapter) {
        adapter.setEmptyView(R.layout.view_empty);
        TextView tv1 = (TextView) adapter.getEmptyView().
                findViewById(R.id.tv_content);
        tv1.setText("暂无投资记录");
    }

    private void initBean(final int pos, String type) {
        OkGo.get(ApiUtils.getInVestRecord(this, type, 1, 10))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        if (pos == 1) {
                            bean2 = ParseUtils.parseByGson(s, InvestRecord.class);
                            mAdapter2.setNewData(bean2.getInvest_record());
                            setEmptyView(mAdapter2);
                            page2++;
                        } else if (pos == 2) {
                            bean3 = ParseUtils.parseByGson(s, InvestRecord.class);
                            mAdapter3.setNewData(bean3.getInvest_record());
                            setEmptyView(mAdapter3);
                            page3++;
                        } else {
                            bean1 = ParseUtils.parseByGson(s, InvestRecordDone.class);
                            mAdapter1.setNewData(bean1.getInvest_record());
                            page1++;
                        }
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
                        NormalUtils.showToast(InvestRecordActivity.this, R.string.net_error);
                        if (mAnimDialog != null && mAnimDialog.isShowing()) {
                            mAnimDialog.dismiss();
                        }
                        if (isRefresh) {
                            refreshLayout.finishRefresh();
                        }
                    }
                });
    }

    int page1 = 1;
    int page2 = 1;
    int page3 = 1;

    @Override
    public void doBusiness(final Context mContext) {
        page1 = 1;
        /**
         * 查询类型
         * done:已投标
         * pending:确认中
         * dropped:未投标
         */
        OkGo.get(ApiUtils.getInVestRecord(mContext, "done", page1, 10)).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                bean1 = ParseUtils.parseByGson(s, InvestRecordDone.class);
                mAdapter1.setNewData(bean1.getInvest_record());
                setEmptyView(mAdapter1);
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.GONE);

                page1++;
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                NormalUtils.showToast(mContext, R.string.net_error);
                if (bean1 == null) {
                    include.setVisibility(View.GONE);
                    include_load_error.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_my_account:
                startToMinePage();
                break;
            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                include_load_error.setVisibility(View.GONE);
                doBusiness(this);
                break;
        }
    }

    class SimpleAdapter extends BaseQuickAdapter<InvestRecord.InvestRecordBean, BaseViewHolder> {

        public SimpleAdapter(@Nullable List<InvestRecord.InvestRecordBean> data) {
            super(R.layout.recycler_item_invest_record, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, InvestRecord.InvestRecordBean item) {
            ImageView logo = helper.getView(R.id.platform_list_logo_app);
            Glide.with(getApplicationContext()).
                    load(item.getPlatform_logo()).into(logo);
            helper.setText(R.id.platform_name, item.getPlatform_name())
                    .setText(R.id.bid_name, item.getBid_name())
                    .setText(R.id.tv_time, item.getOrder_time())
                    .setText(R.id.tv_clear_time, item.getSettlement_str());

            TextView tv_clear_time = helper.getView(R.id.tv_clear_time);

            /**
             * 问号显示
             * 问号标记类型
             * 0:没有标记
             * 1:等额本息
             * 2:非等额本息
             * 3:活期
             * 4:活期金
             * 5:维C理财
             * 6:直接投资
             */
            InvestRecord.InvestRecordBean.SettlementQuestionmarkInfoBean info =
                    item.getSettlement_questionmark_info();
            List<String> contens = info.getContent_list();
            StringBuilder sb = new StringBuilder();
            if (contens != null && contens.size() > 0) {
                for (int i = 0; i < contens.size(); i++) {
                    sb.append(contens.get(i));
                    if (i < contens.size() - 1)
                        sb.append("\n");
                }
            }
            initQuestion(info.isIs_show(), sb.toString(), info.getTitle(), tv_clear_time);

            /**
             * 标的类别
             1:固收
             2:众筹
             3:定期金
             4:活期金
             5:海外
             6:活期
             7:维C理财
             8:保险
             */
            switch (item.getBid_category()) {
                case 1:
                    helper.setText(R.id.tv_content, item.getInterest_rate() +
                            " | " + item.getBonus_rate() + " | " + item.getDuration());

                    break;
                case 2:
                    helper.setText(R.id.tv_content, item.getBonus_rate());
                    break;
                case 3:
                    helper.setText(R.id.tv_content, item.getInterest_rate() +
                            " | " + item.getBonus_rate() + " | " + item.getDuration());
                    break;
                case 4:
                    helper.setText(R.id.tv_content, item.getInterest_rate() +
                            " | " + item.getBonus_rate() + " | " + item.getDuration());
                    break;
                case 5:
                    helper.setText(R.id.tv_content, item.getInterest_rate() +
                            " | " + item.getBonus_rate() + " | " + item.getDuration());
                    break;
                case 6:
                    helper.setText(R.id.tv_content, item.getInterest_rate() +
                            " | " + item.getBonus_rate() + " | " + "活期");
                    break;
                case 7:
                    helper.setText(R.id.tv_content, item.getInterest_rate() +
                            " | " + item.getBonus_rate() + " | " + item.getDuration());
                    break;
                case 8:
                    helper.setText(R.id.tv_content, item.getBonus_rate() + " | " + item.getDuration());
                    break;
            }
        }
    }

    class SimpleDoneAdapter extends BaseQuickAdapter<InvestRecordDone.InvestRecordBean, BaseViewHolder> {

        public SimpleDoneAdapter(@Nullable List<InvestRecordDone.InvestRecordBean> data) {
            super(R.layout.recycler_item_invest_record_done, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final InvestRecordDone.InvestRecordBean item) {
            ImageView logo = helper.getView(R.id.platform_list_logo_app);
            Glide.with(getApplicationContext()).
                    load(item.getPlatform_logo()).into(logo);
            helper.setText(R.id.platform_name, item.getPlatform_name())
                    .setText(R.id.tv_time, item.getOrder_time())
                    .setText(R.id.tv_principal, "本金(元):" + item.getPrincipal());

            TextView tv_text_line2 = helper.getView(R.id.tv_text_line2);
            TextView tv_text_line3 = helper.getView(R.id.tv_text_line3);
            TextView tv_text_line4 = helper.getView(R.id.tv_text_line4);
            TextView tv_text_line5 = helper.getView(R.id.tv_text_line5);

            //加息券显示
            List<InvestRecordDone.InvestRecordBean.RaiseInterestTicketsBean> tickets =
                    item.getRaise_interest_tickets();
            TextView raise_interest_tickets = helper.getView(R.id.raise_interest_tickets);
            if (tickets == null || tickets.size() < 1) {
                raise_interest_tickets.setVisibility(View.GONE);
            } else {
                raise_interest_tickets.setVisibility(View.VISIBLE);

                int order_id = item.getOrder_id();
                final List<TicketBean> ticketBeans = new ArrayList<>();
                for (InvestRecordDone.InvestRecordBean.RaiseInterestTicketsBean t : tickets) {
                    TicketBean bean = new TicketBean(t.getId(), order_id, t.getName());
                    ticketBeans.add(bean);
                }

                //点击事件
                raise_interest_tickets.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //点击某个位置，使用加息券后复原
                        getPositionAndOffset(mOneRecycler);
                        showTickets(ticketBeans);
                    }
                });
            }

            TextView tv_clear_time = helper.getView(R.id.tv_clear_time);
            tv_clear_time.setTextColor(getResources().getColor(R.color.colorBidName));
            tv_clear_time.setText(item.getBid_name());
            /**
             * 标的类别
             1:固收
             2:众筹
             3:定期金
             4:活期金
             5:海外
             6:活期
             7:维C理财
             8:保险
             */
            switch (item.getBid_category()) {
                case 1:
                    tv_text_line2.setVisibility(View.VISIBLE);
                    helper.setText(R.id.tv_content, item.getInterest_and_bonus_rate()
                            + " | " + item.getDuration());
                    tv_text_line2.setText("利息(元):" + item.getInterest());
                    if (item.getIs_daily_bonus() == 1) {
                        tv_text_line3.setText(Html.fromHtml("日返(元):" + item.getBonus().replace("直接投资", "<font color='#f42c20'>直接投资</font>")));
                    } else {
                        tv_text_line3.setText(Html.fromHtml("返利(元):" + item.getBonus().replace("直接投资", "<font color='#f42c20'>直接投资</font>")));
                    }

                    break;
                case 2:
//                    tv_text_line2.setVisibility(View.VISIBLE);
                    tv_text_line2.setVisibility(View.GONE);
                    helper.setText(R.id.tv_content, item.getInterest_and_bonus_rate());
//                    tv_text_line2.setText("利息(元):" + item.getInterest());
                    tv_text_line3.setText(Html.fromHtml("返利(元):" + item.getBonus().replace("直接投资", "<font color='#f42c20'>直接投资</font>")));
                    break;
                case 3:
                    tv_text_line2.setVisibility(View.VISIBLE);
                    helper.setText(R.id.tv_content, item.getInterest_and_bonus_rate()
                            + " | " + item.getDuration());

                    tv_text_line2.setText("利息(元):" + item.getInterest());

                    if (item.getIs_daily_bonus() == 1) {
                        tv_text_line3.setText(Html.fromHtml("日返(元):" + item.getBonus().replace("直接投资", "<font color='#f42c20'>直接投资</font>")));
                    } else {
                        tv_text_line3.setText(Html.fromHtml("返利(元):" + item.getBonus().replace("直接投资", "<font color='#f42c20'>直接投资</font>")));
                    }
                    break;
                case 4:
                    tv_text_line2.setVisibility(View.VISIBLE);
                    helper.setText(R.id.tv_content, item.getInterest_and_bonus_rate()
                            + " | " + item.getDuration());
                    tv_text_line2.setText("日息(元):" + item.getInterest());
                    if (item.getIs_daily_bonus() == 1) {
                        tv_text_line3.setText(Html.fromHtml("日返(元):" + item.getBonus().replace("直接投资", "<font color='#f42c20'>直接投资</font>")));
                    } else {
                        tv_text_line3.setText(Html.fromHtml("返利(元):" + item.getBonus().replace("直接投资", "<font color='#f42c20'>直接投资</font>")));
                    }
                    break;
                case 5:
                    tv_text_line2.setVisibility(View.VISIBLE);
                    helper.setText(R.id.tv_content, item.getInterest_and_bonus_rate()
                            + " | " + item.getDuration());
                    tv_text_line2.setText("利息(元):" + item.getInterest());
                    if (item.getIs_daily_bonus() == 1) {
                        tv_text_line3.setText(Html.fromHtml("日返(元):" + item.getBonus().replace("直接投资", "<font color='#f42c20'>直接投资</font>")));
                    } else {
                        tv_text_line3.setText(Html.fromHtml("返利(元):" + item.getBonus().replace("直接投资", "<font color='#f42c20'>直接投资</font>")));
                    }
                    break;
                case 6:
                    tv_text_line2.setVisibility(View.VISIBLE);
                    helper.setText(R.id.tv_content, item.getInterest_and_bonus_rate()
                            + " | " + item.getDuration());
                    tv_text_line2.setText("日息(元):" + item.getInterest());
                    if (item.getIs_daily_bonus() == 1) {
                        tv_text_line3.setText(Html.fromHtml("日返(元):" + item.getBonus().replace("直接投资", "<font color='#f42c20'>直接投资</font>")));
                    } else {
                        tv_text_line3.setText(Html.fromHtml("返利(元):" + item.getBonus().replace("直接投资", "<font color='#f42c20'>直接投资</font>")));
                    }
                    break;
                case 7:
                    tv_text_line2.setVisibility(View.VISIBLE);
                    helper.setText(R.id.tv_content, item.getInterest_and_bonus_rate()
                            + " | " + item.getDuration());
                    tv_text_line2.setText("利息(元):" + item.getInterest());
                    if (item.getIs_daily_bonus() == 1) {
                        tv_text_line3.setText(Html.fromHtml("日返(元):" + item.getBonus().replace("直接投资", "<font color='#f42c20'>直接投资</font>")));
                    } else {
                        tv_text_line3.setText(Html.fromHtml("返利(元):" + item.getBonus().replace("直接投资", "<font color='#f42c20'>直接投资</font>")));
                    }
                    break;
                case 8:
                    tv_text_line2.setVisibility(View.GONE);
                    helper.setText(R.id.tv_content, item.getInterest_and_bonus_rate() + " | " + item.getDuration());
//                    tv_text_line2.setText("本金(元):" + item.getPrincipal());
                    if (item.getIs_daily_bonus() == 1) {
                        tv_text_line3.setText(Html.fromHtml("日返(元):" + item.getBonus().replace("直接投资", "<font color='#f42c20'>直接投资</font>")));
                    } else {
                        tv_text_line3.setText(Html.fromHtml("返利(元):" + item.getBonus().replace("直接投资", "<font color='#f42c20'>直接投资</font>")));
                    }
                    break;
            }

            /**
             * 问号显示
             * 问号标记类型
             * 0:没有标记
             * 1:等额本息
             * 2:非等额本息
             * 3:活期
             * 4:活期金
             * 5:维C理财
             * 6:直接投资
             */
            int tag = item.getQuestionmark_tag();
            InvestRecordDone.InvestRecordBean.DailyBonusQuestionmarkInfoBean info =
                    item.getDaily_bonus_questionmark_info();
            StringBuilder sb = new StringBuilder();
            List<String> contents = info.getContent_list();
            if (contents != null && contents.size() > 0) {
                for (int i = 0; i < contents.size(); i++) {
                    sb.append(contents.get(i));
                    sb.append("\n");
                }
            }
            initQuestion(info.isIs_show(), sb.toString(), info.getTitle(), tv_text_line3);

            //加息
            if (TextUtils.isEmpty(item.getRaise_interest_value()) && tv_text_line2.getVisibility() == View.GONE) {
                tv_text_line4.setVisibility(View.VISIBLE);
                tv_text_line4.setText("");
            } else if (TextUtils.isEmpty(item.getRaise_interest_value()) && tv_text_line2.getVisibility() != View.GONE) {
                tv_text_line4.setVisibility(View.GONE);
            } else if (item.isIs_show_raise_interest()) {
                tv_text_line4.setVisibility(View.VISIBLE);
                tv_text_line4.setText(item.getRaise_interest_name() + ":" + item.getRaise_interest_value());
            }

            //是否显示红包
            if (item.is_show_platform_redpacket()) {
                tv_text_line5.setVisibility(View.VISIBLE);
                tv_text_line5.setText(item.getPlatform_redpacket_name() + ":" + item.getPlatform_redpacket_value());
            } else {
                tv_text_line5.setVisibility(View.GONE);
            }
        }


    }

    private void showDialog(String title, String content) {
        Bundle bundle = new Bundle();
        bundle.putString(NormalWhiteDialog.TITLE, title);
        bundle.putString(NormalWhiteDialog.CONTENT, content);
        NormalWhiteDialog dialog = new NormalWhiteDialog();
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), "invest_record");
    }

    private void initQuestion(boolean isShow, String content, final String title, TextView tv_line3) {

        if (isShow) {
            tv_line3.setCompoundDrawablesWithIntrinsicBounds(null, null,
                    getResources().getDrawable(R.mipmap.ic_question_gray), null);
            final String finalContent = content;
            tv_line3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog(title, finalContent);
                }
            });
        } else {
            tv_line3.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            tv_line3.setOnClickListener(null);
        }
    }

    private void pullToRefresh(final BaseQuickAdapter adapter, final int finalI, final RecyclerView mRecyclerView, final String type) {

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                int page = 2;
                switch (finalI) {
                    case 1:
                        page = page1;
                        break;
                    case 2:
                        page = page2;
                        break;
                    case 3:
                        page = page3;
                        break;

                }
                OkGo.get(ApiUtils.getInVestRecord(
                        InvestRecordActivity.this, type, page, 10)).execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        switch (finalI) {
                            case 1:
                                page1++;
                                InvestRecordDone bean = ParseUtils.parseByGson(s, InvestRecordDone.class);
                                List<InvestRecordDone.InvestRecordBean> list = bean.getInvest_record();
                                if (list == null || list.size() < 1) {
                                    loadMoreEnd(adapter, mRecyclerView);
                                } else {
                                    adapter.addData(list);
                                    adapter.loadMoreComplete();
                                }
                                break;
                            case 2:
                                page2++;
                                InvestRecord bean2 = ParseUtils.parseByGson(s, InvestRecord.class);
                                List<InvestRecord.InvestRecordBean> list2 = bean2.getInvest_record();
                                if (list2 == null || list2.size() < 1) {
                                    loadMoreEnd(adapter, mRecyclerView);
                                } else {
                                    adapter.addData(list2);
                                    adapter.loadMoreComplete();
                                }
                                break;
                            case 3:
                                InvestRecord bean3 = ParseUtils.parseByGson(s, InvestRecord.class);
                                List<InvestRecord.InvestRecordBean> list3 = bean3.getInvest_record();
                                if (list3 == null || list3.size() < 1) {
                                    loadMoreEnd(adapter, mRecyclerView);
                                } else {
                                    adapter.addData(list3);
                                    adapter.loadMoreComplete();
                                }
                                page3++;
                                break;
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        NormalUtils.showToast(InvestRecordActivity.this, R.string.net_error);
                        adapter.loadMoreFail();
                    }
                });
            }
        }, mRecyclerView);
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

    private void showTickets(List<TicketBean> tickets) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, (ArrayList<TicketBean>) tickets);

        TicketsDialog dialog = new TicketsDialog();
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), "tickets");

    }

    @Override
    public void onBackPressed() {
        startToMinePage();
    }

    private void startToMinePage() {
        Bundle args = new Bundle();
        args.putBoolean(MainActivity.MINE_PAGE, true);
        startActivity(MainActivity.class, args);
    }


    public void useTicketAndSetData(final String msg) {

        if (mAnimDialog == null)
            mAnimDialog = AnimDialog.showDialog(this);

        mAnimDialog.show();


        OkGo.get(ApiUtils.getInVestRecord(this, "done", 1, mAdapter1.getItemCount())).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                bean1 = ParseUtils.parseByGson(s, InvestRecordDone.class);
                mAdapter1.setNewData(bean1.getInvest_record());

                scrollToPosition(mOneRecycler);

                NormalUtils.showToast(InvestRecordActivity.this, msg);

                page1 = 2;
                if (mAnimDialog != null && mAnimDialog.isShowing()) {
                    mAnimDialog.dismiss();
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                NormalUtils.showToast(InvestRecordActivity.this, R.string.net_error);
                if (mAnimDialog != null && mAnimDialog.isShowing()) {
                    mAnimDialog.dismiss();
                }
            }
        });
    }

    /**
     * 记录RecyclerView当前位置
     */
    private void getPositionAndOffset(RecyclerView mRecyclerView) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        //获取可视的第一个view
        View topView = layoutManager.getChildAt(0);
        if (topView != null) {
            //获取与该view的顶部的偏移量
            lastOffset = topView.getTop();
            //得到该View的数组位置
            lastPosition = layoutManager.getPosition(topView);
        }
    }

    /**
     * 让RecyclerView滚动到指定位置
     */
    private void scrollToPosition(RecyclerView mRecyclerView) {
        if (mRecyclerView.getLayoutManager() != null && lastPosition >= 0) {
            ((LinearLayoutManager) mRecyclerView.getLayoutManager()).scrollToPositionWithOffset(lastPosition, lastOffset);
        }
    }
}
