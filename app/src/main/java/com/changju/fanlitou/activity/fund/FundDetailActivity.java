package com.changju.fanlitou.activity.fund;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.LoginActivity;
import com.changju.fanlitou.activity.bid.BidFixedActivity;
import com.changju.fanlitou.adapter.MyFragmentPagerAdapter;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.bean.fund.FundBasicInfo;
import com.changju.fanlitou.fragment.fund.FundIncomeTabFragment;
import com.changju.fanlitou.fragment.fund.FundRealTabFragment;
import com.changju.fanlitou.ui.WrapContentViewPager;
import com.changju.fanlitou.ui.dialog.NormalWhiteDialog;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.changju.fanlitou.util.UserUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chengww on 2017/5/25.
 */

public class FundDetailActivity extends BaseActivity{

    private String title;
    private String code;
    FundBasicInfo fundBasicInfo;
    List<FundBasicInfo.BtnListBean> btnListBeen;
    //loading&error
    private View include;
    private View include_load_error;

    @Bind(R.id.fund_name)
    TextView fund_name;
    @Bind(R.id.fund_code)
    TextView fund_code;

    TextView unit_nav;
    TextView unit_nav_title;

    TextView day_gr;
    TextView day_gr_title;

    TextView invest_risk;
    TextView fund_type;

    @Bind(R.id.listview)
    ListView listView;

    @Bind(R.id.subscribe_rate)
    TextView subscribe_rate;
    @Bind(R.id.fanlitou_rate)
    TextView fanlitou_rate;
    @Bind(R.id.tv_redem)
    TextView tv_redem;
    @Bind(R.id.tv_purchase)
    TextView tv_purchase;

    TabLayout tab_fund_detail;
    WrapContentViewPager viewpager_fund;

    RelativeLayout rl_1;
    RelativeLayout rl_2;
    RelativeLayout rl_3;
    RelativeLayout rl_4;

    private ArrayList<Fragment> mFragments;
    private ArrayList<String> mTitles;
    private int fund_id;

    public static final String FUND_CODE = "FUND_CODE";
    @Override
    public void initParams(Bundle params) {
        String fund_id_str = params.getString(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
        if (!TextUtils.isEmpty(fund_id_str) && NormalUtils.isInteger(fund_id_str)){
            fund_id = Integer.valueOf(fund_id_str);
        }else {
            fund_id = params.getInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
        }

        title = params.getString(BidFixedActivity.BID_NAME);
        code = params.getString(FUND_CODE);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_fund_detail;
    }

    @Override
    public void initView(View view) {

        ButterKnife.bind(this);

        GrowingIO.getInstance().setPageName(this, "基金-基金详情页");

        if (!TextUtils.isEmpty(title)){
            fund_name.setText(title);
        }

        if (!TextUtils.isEmpty(code)){
            fund_code.setText(code);
        }

        listView.setVerticalScrollBarEnabled(false);
        View header = View.inflate(this, R.layout.item_fund_head, null);
        tab_fund_detail = (TabLayout)header.findViewById(R.id.tab_fund_detail);
        unit_nav = (TextView) header.findViewById(R.id.unit_nav);
        unit_nav_title = (TextView)header.findViewById(R.id.unit_nav_title);
        day_gr = (TextView)header.findViewById(R.id.day_gr);
        day_gr_title = (TextView)header.findViewById(R.id.day_gr_title);
        invest_risk = (TextView)header.findViewById(R.id.invest_risk);
        fund_type = (TextView)header.findViewById(R.id.fund_type);

        View foot = View.inflate(this, R.layout.item_fund_foot, null);
        rl_1 = (RelativeLayout) foot.findViewById(R.id.rl_1);
        rl_2 = (RelativeLayout) foot.findViewById(R.id.rl_2);
        rl_3 = (RelativeLayout) foot.findViewById(R.id.rl_3);
        rl_4 = (RelativeLayout) foot.findViewById(R.id.rl_4);

        listView.addHeaderView(header);
        listView.addFooterView(foot);

        findViewById(R.id.iv_back_fund_detail).setOnClickListener(this);
        rl_1.setOnClickListener(this);
        rl_2.setOnClickListener(this);
        rl_3.setOnClickListener(this);
        rl_4.setOnClickListener(this);
        tv_redem.setOnClickListener(this);
        tv_purchase.setOnClickListener(this);

        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);
    }

    @Override
    public void doBusiness(final Context mContext) {
        OkGo.get(ApiUtils.getFundingDetailBaseInfo(mContext,fund_id)).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                fundBasicInfo = ParseUtils.parseByGson(s,FundBasicInfo.class);
                listView.setAdapter(new CustomAdapter(mContext));
                //头部的三组数据
                fund_name.setText(fundBasicInfo.getFund_abbr());
                fund_code.setText(fundBasicInfo.getFund_code());
                unit_nav.setText(fundBasicInfo.getUnit_nav());
                unit_nav_title.setText(fundBasicInfo.getUnit_nav_title()
                        + "(" + fundBasicInfo.getUnit_nav_date() + ")");
                day_gr.setText(fundBasicInfo.getDay_gr() + "%");

                if(fundBasicInfo.getDay_gr().substring(0,1).equals("-")){
                    day_gr.setTextColor(getResources().getColor(R.color.fund_text_green));
                }else {
                    day_gr.setTextColor(getResources().getColor(R.color.fund_text_red));
                }
                day_gr_title.setText(fundBasicInfo.getDay_gr_title());
                invest_risk.setText(fundBasicInfo.getInvest_risk()+"风险");
                fund_type.setText(fundBasicInfo.getFund_type());

                btnListBeen = fundBasicInfo.getBtn_list();
                if(btnListBeen.size() == 1){
                    tv_redem.setVisibility(View.GONE);
                    tv_purchase.setText(btnListBeen.get(0).getBtn_str());
                    switch (btnListBeen.get(0).getBtn_class()){
                        //red字段 如果是最后一条 为红底白字 其他情况为白底红字
                        case "red":
                            tv_purchase.setBackgroundColor(getResources().getColor(R.color.colorTextRed));
                            tv_purchase.setTextColor(Color.WHITE);
                            break;
                        case "grey_font":
                            tv_purchase.setBackgroundColor(Color.WHITE);
                            tv_purchase.setTextColor(getResources().getColor(R.color.colorTextThird));
                            break;
                        case "grey":
                            tv_purchase.setBackgroundColor(getResources().getColor(R.color.colorHint));
                            tv_purchase.setTextColor(Color.WHITE);
                            break;
                    }
                    tv_purchase.setTag(btnListBeen.get(0).getOperate_type());
                }
                if(btnListBeen.size() == 2){
                    tv_redem.setVisibility(View.VISIBLE);
                    tv_redem.setText(btnListBeen.get(0).getBtn_str());
                    switch (btnListBeen.get(0).getBtn_class()){
                        case "red":
                            tv_redem.setBackgroundColor(Color.WHITE);
                            tv_redem.setTextColor(getResources().getColor(R.color.colorTextRed));
                            break;
                        case "grey_font":
                            tv_redem.setBackgroundColor(Color.WHITE);
                            tv_redem.setTextColor(getResources().getColor(R.color.colorTextThird));
                            break;
                        case "grey":
                            tv_redem.setBackgroundColor(getResources().getColor(R.color.colorHint));
                            tv_redem.setTextColor(Color.WHITE);
                            break;
                    }
                    tv_purchase.setText(btnListBeen.get(1).getBtn_str());
                    switch (btnListBeen.get(1).getBtn_class()){
                        //red字段 如果是最后一条 为红底白字 其他情况为白底红字
                        case "red":
                            tv_purchase.setBackgroundColor(getResources().getColor(R.color.colorTextRed));
                            tv_purchase.setTextColor(Color.WHITE);
                            break;
                        case "grey_font":
                            tv_purchase.setBackgroundColor(Color.WHITE);
                            tv_purchase.setTextColor(getResources().getColor(R.color.colorTextThird));
                            break;
                        case "grey":
                            tv_purchase.setBackgroundColor(getResources().getColor(R.color.colorHint));
                            tv_purchase.setTextColor(Color.WHITE);
                            break;
                    }

                    tv_redem.setTag(btnListBeen.get(0).getOperate_type());
                    tv_purchase.setTag(btnListBeen.get(1).getOperate_type());
                }

                subscribe_rate.setText(fundBasicInfo.getSubscribe_rate()+"%");
                subscribe_rate.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                fanlitou_rate.setText(fundBasicInfo.getFanlitou_rate()+"%");
                mTitles = new ArrayList<>();

                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.GONE);
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                NormalUtils.showToast(FundDetailActivity.this, R.string.net_error);
                include.setVisibility(View.GONE);
                include_load_error.setVisibility(View.VISIBLE);
            }
        });
    }

    public class CustomAdapter extends BaseAdapter {

        private View view;

        public CustomAdapter(Context context) {
            view = View.inflate(context, R.layout.item_fund_main, null);
            viewpager_fund = (WrapContentViewPager) view.findViewById(R.id.viewpager_fund);
            mTitles = new ArrayList<>();
            mTitles.add(fundBasicInfo.getIncome_tab_name());
            mTitles.add(fundBasicInfo.getReal_tab_name());

            mFragments = new ArrayList<>();
            FundIncomeTabFragment fundIncomeTabFragment = FundIncomeTabFragment.newInstance(fundBasicInfo, fund_id);
            FundRealTabFragment fundRealTabFragment = FundRealTabFragment.newInstance(fundBasicInfo, fund_id);

            GrowingIO.getInstance().trackFragment(FundDetailActivity.this, fundIncomeTabFragment);
            GrowingIO.getInstance().trackFragment(FundDetailActivity.this, fundRealTabFragment);

            mFragments.add(fundIncomeTabFragment);
            mFragments.add(fundRealTabFragment);

            viewpager_fund.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()
                    ,mFragments,mTitles.toArray(new String[mTitles.size()])));
            tab_fund_detail.setupWithViewPager(viewpager_fund);
        }

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return view;
        }
    }

    @Override
    public void widgetClick(View v) {
        Bundle args = new Bundle();
        args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,fund_id);
        switch (v.getId()){
            case R.id.iv_back_fund_detail:
                finish();
                break;
            case R.id.rl_1:
                startActivity(FundRecordActivity.class,args);
                break;
            case R.id.rl_2:
                startActivity(FundFeeRateActivity.class,args);
                break;
            case R.id.rl_3:
                startActivity(FundManagerActivity.class,args);
                break;
            case R.id.rl_4:
                startActivity(FundHisDividendsActivity.class,args);
                break;
            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                include_load_error.setVisibility(View.GONE);
                doBusiness(this);
                break;
            case R.id.tv_purchase:
            case R.id.tv_redem:
                if (UserUtils.isLogin(this)){
                    String operate_type = (String) v.getTag();
                    switch (operate_type){
                        //subscribe	点击去认购
                        case "subscribe":
                            startActivity(FundPurchaseActivity.class,args);
                            break;
                        //apply	点击去申购
                        case "apply":
                            startActivity(FundPurchaseActivity.class,args);
                            break;
                        //openaccount	点击去开户
                        case "openaccount":
                            startActivity(OpenFundActivity.class,args);
                            break;
                        //redeem	点击去赎回
                        case "redeem":
                            startActivity(FundRedeemActivity.class,args);
                            break;
                        //already_redeem_alert	点击弹出已全部赎回的弹窗
                        case "already_redeem_alert":
                            args.putString(NormalWhiteDialog.TITLE, "已全部赎回");
                            args.putString(NormalWhiteDialog.CONTENT, "您已全部赎回，处理确认后\n" +
                                    "金额将到您的基金银行卡中");
                            NormalWhiteDialog textDialog = new NormalWhiteDialog();
                            textDialog.setArguments(args);
                            textDialog.show(getSupportFragmentManager(), "already_redeem_alert");
                            break;
                        //stop_redeem_alert	暂停申购弹窗
                        case "stop_redeem_alert":
                            break;
                        //stop_purchase_alert	暂停购买弹窗
                        case "stop_purchase_alert":
                            break;
                    }
                }else{
                    startActivity(LoginActivity.class);
                }

                break;
        }

    }

}
