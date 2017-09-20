package com.changju.fanlitou.activity.bid;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.LoginActivity;
import com.changju.fanlitou.activity.WebActivity;
import com.changju.fanlitou.activity.intelligent.InvestConfirmActivity;
import com.changju.fanlitou.activity.intelligent.OpenAccountActivity;
import com.changju.fanlitou.activity.platform.PlatformDetailActivity;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.bean.bid.BidFixed;
import com.changju.fanlitou.bean.intelligent.IntelligentInvest;
import com.changju.fanlitou.ui.RoundProgressBar;
import com.changju.fanlitou.ui.dialog.AnimDialog;
import com.changju.fanlitou.ui.dialog.BuyDialog;
import com.changju.fanlitou.ui.dialog.ImageDialog;
import com.changju.fanlitou.ui.dialog.IntelligentBuyDialog;
import com.changju.fanlitou.ui.dialog.NormalWhiteDialog;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.changju.fanlitou.util.UserUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chengww on 2017/6/8.
 */

public class BidFixedActivity extends BaseActivity {

    private int bid_id;
    private RoundProgressBar round_progress;
    //当前选中的标的
    private int pos;

    private String bid_name;
    public static final String BID_NAME = "BID_NAME";

    private ScrollView mScrollView;

    //loading&error
    private View include;
    private View include_load_error;

    private LinearLayout layout_tag;

    private TextView tv_percent;

    @Override
    public void initParams(Bundle params) {
        String bid_id_str = params.getString(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
        if (!TextUtils.isEmpty(bid_id_str) && NormalUtils.isInteger(bid_id_str)) {
            bid_id = Integer.valueOf(bid_id_str);
        } else {
            bid_id = params.getInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
        }

        bid_name = params.getString(BID_NAME);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_bid_fixed;
    }

    private TextView button;
    private TextView tv_buy_now, tv_title, tv_platform_name,
            bid_interest, raise_interest, tv_money, tv_time,
            tv_hkfs_content, tv_content, min_invest_amount,
            interest_start_date, bonus_date, pay_type,
            assign_info, interest_manage_fee;

    private ImageView iv_platform_logo;
    private List<BidFixed.BidListBean> mBidListBean;

    private TabLayout tab_bid_current;

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "标的详情页-固收");

        mScrollView = (ScrollView) findViewById(R.id.layout_scroll);

        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);

        layout_tag = (LinearLayout) findViewById(R.id.layout_tag);

        round_progress = (RoundProgressBar) findViewById(R.id.round_progress);

        findViewById(R.id.tv_ckxq).setOnClickListener(this);
        findViewById(R.id.iv_platform_logo).setOnClickListener(this);
        findViewById(R.id.iv_back_my_account).setOnClickListener(this);
        button = (TextView) findViewById(R.id.button);
        button.setOnClickListener(this);
        tv_buy_now = (TextView) findViewById(R.id.tv_buy_now);
        tv_buy_now.setOnClickListener(this);

        tv_title = (TextView) findViewById(R.id.tv_title);
        //初始化title名称
        if (!TextUtils.isEmpty(bid_name))
            tv_title.setText(bid_name);

        tv_platform_name = (TextView) findViewById(R.id.tv_platform_name);
        bid_interest = (TextView) findViewById(R.id.bid_interest);
        raise_interest = (TextView) findViewById(R.id.raise_interest);
        tv_money = (TextView) findViewById(R.id.tv_money);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_hkfs_content = (TextView) findViewById(R.id.tv_hkfs_content);
        tv_content = (TextView) findViewById(R.id.tv_content);
        min_invest_amount = (TextView) findViewById(R.id.min_invest_amount);
        interest_start_date = (TextView) findViewById(R.id.interest_start_date);
        bonus_date = (TextView) findViewById(R.id.bonus_date);
        pay_type = (TextView) findViewById(R.id.pay_type);
        assign_info = (TextView) findViewById(R.id.assign_info);
        interest_manage_fee = (TextView) findViewById(R.id.interest_manage_fee);
        tv_percent = (TextView) findViewById(R.id.tv_percent);

        iv_platform_logo = (ImageView) findViewById(R.id.iv_platform_logo);

        tab_bid_current = (TabLayout) findViewById(R.id.tab_bid_current);
        tab_bid_current.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pos = tab.getPosition();
                BidFixed.BidListBean bean = mBidListBean.get(pos);
                tv_title.setText(bean.getBid_detail().getBid_name());
                float bid_interest_f = bean.getBid_detail().getBid_interest();
                float num = (float) (Math.round(bid_interest_f * 100) / 100.0);
                bid_interest.setText(num + "% ");
                raise_interest.setText("+" +
                        bean.getBid_detail().getRaise_interest() + "%");
                int progress = (int) bean.getBid_detail().getBid_progress_percent();
                if (progress < 0)
                    progress = 0;
                if (progress > 100)
                    progress = 100;

                round_progress.setAnimProgress(progress);
                tv_percent.setText(progress + "%");


                tv_money.setText(bean.getBid_detail().getMin_invest_amount());
                tv_time.setText(bean.getBid_detail().getBid_duration());
                tv_hkfs_content.setText(bean.getBid_detail().getPay_type());
                BidFixed.BidListBean.BidDetailBean.MiniProgramIntroductionBean program =
                        bean.getBid_detail().getMini_program_introduction();
                min_invest_amount.setText(program.getMin_invest_amount());
                interest_start_date.setText(program.getInterest_start_date());
                bonus_date.setText(program.getBonus_date());
                pay_type.setText(program.getPay_type());
                assign_info.setText(program.getAssign_info());
                interest_manage_fee.setText(program.getInterest_manage_fee());
                //tag
                layout_tag.removeAllViews();
                final List<BidFixed.BidListBean.BidDetailBean.TagListBean> tag_list =
                        bean.getBid_detail().getTag_list();

                if (tag_list != null && tag_list.size() > 0) {
                    for (int i = 0; i < tag_list.size(); i++) {
                        final BidFixed.BidListBean.BidDetailBean.TagListBean tag =
                                tag_list.get(i);

                        if (tag.isIs_show_dialog()) {
                            TextView textView = new TextView(BidFixedActivity.this);
                            textView.setText(tag.getName());
                            textView.setTextSize(12);
                            textView.setTextColor(getResources().getColor(R.color.colorTextRed));
                            textView.setPadding(NormalUtils.dp2px(BidFixedActivity.this, 5),
                                    NormalUtils.dp2px(BidFixedActivity.this, 2),
                                    NormalUtils.dp2px(BidFixedActivity.this, 5),
                                    NormalUtils.dp2px(BidFixedActivity.this, 2));

                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                    ViewGroup.LayoutParams.WRAP_CONTENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT);

                            params.setMargins(NormalUtils.dp2px(BidFixedActivity.this, 3),
                                    0, NormalUtils.dp2px(BidFixedActivity.this, 3), 0);
                            textView.setLayoutParams(params);

//                        textView.setGravity(Gravity.CENTER);
                            textView.setBackgroundResource(R.drawable.shape_tag_red);

                            //点击事件
                            if (tag.isIs_show_dialog()) {
                                textView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        switch (tag.getType()) {
                                            case "img":
                                                String tagContent = tag.getDialog_img();
                                                showDialog(tag.getTitle(), tagContent, tag.getType());
                                                break;
                                            case "text":
                                                List<String> contents = tag.getContent_list();
                                                StringBuilder sb = new StringBuilder();
                                                if (contents != null && contents.size() > 0) {
                                                    for (int j = 0; j < contents.size(); j++) {
                                                        sb.append(contents.get(j));
                                                        if (j < contents.size() - 1) {
                                                            sb.append("\n");
                                                        }
                                                    }
                                                }
                                                showDialog(tag.getTitle(), sb.toString(), tag.getType());
                                                break;
                                        }
                                    }
                                });

                                layout_tag.addView(textView);
                            }

                        }
                    }


                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

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

    private BidFixed fixed;

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
            OkGo.get(ApiUtils.getBidFixInfo(mContext, bid_id)).
                    execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {

                            boolean isFirst = fixed == null;

                            fixed = ParseUtils.parseByGson(s, BidFixed.class);

                            //button文字
                            button.setText(fixed.getButton_name());
                            tv_buy_now.setText(fixed.getButton_name());
                            mBidListBean = fixed.getBid_list();

                            if (isFirst) {
                                final BidFixed.PlatformBean platform = fixed.getPlatform();
                                //平台
                                tv_platform_name.setText(platform.getPlatform_name());
                                Glide.with(getApplicationContext()).load(platform.getPlatform_logo_app()).
                                        into(iv_platform_logo);

                                if (platform.getSpecial_bonus_rule().is_show_bonus_rule()) {
                                    tv_content.setText("年化收益    " + platform.getPlatform_interest_min()
                                            + "%-" + platform.getPlatform_interest_max() + "%\n投资周期    " +
                                            platform.getPlatform_duration_min() +
                                            platform.getPlatform_duration_min_unit() + "-" +
                                            platform.getPlatform_duration_max() +
                                            platform.getPlatform_duration_max_unit() +
                                            "\n注册资本    " + platform.getPlatform_registered_capital() + "万" +
                                            "\n返利规则    " + platform.getSpecial_bonus_rule().getContent());
                                    tv_content.setLineSpacing(6, 1);
                                    tv_content.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            List<String> contents = platform.getSpecial_bonus_rule().getDialog_info().getContent_list();
                                            StringBuilder sb = new StringBuilder();
                                            if (contents != null && contents.size() > 0) {
                                                for (int j = 0; j < contents.size(); j++) {
                                                    sb.append(contents.get(j));
                                                    if (j < contents.size() - 1) {
                                                        sb.append("\n");
                                                    }
                                                }
                                            }
                                            showDialog(platform.getSpecial_bonus_rule().getDialog_info().getTitle(), sb.toString(), "text");
                                        }
                                    });
                                } else {
                                    //平台描述
                                    /**
                                     * 年化收益    1.6%－9%\n投资周期    30天－5年\n注册资本    588万
                                     */
                                    tv_content.setText("年化收益    " + platform.getPlatform_interest_min()
                                            + "%-" + platform.getPlatform_interest_max() + "%\n投资周期    " +
                                            platform.getPlatform_duration_min() +
                                            platform.getPlatform_duration_min_unit() + "-" +
                                            platform.getPlatform_duration_max() +
                                            platform.getPlatform_duration_max_unit() +
                                            "\n注册资本    " + platform.getPlatform_registered_capital() + "万");
                                    tv_content.setLineSpacing(16, 1);
                                }

                                //tab
                                tab_bid_current.removeAllTabs();
                                if (mBidListBean != null && mBidListBean.size() > 0) {
                                    for (int i = 0; i < mBidListBean.size(); i++) {
                                        tab_bid_current.addTab(
                                                tab_bid_current.newTab().setText(
                                                        mBidListBean.get(i).getBid_detail().getBid_duration()));
                                    }
                                }
                            }

                            include.setVisibility(View.GONE);
                            include_load_error.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);
                            NormalUtils.showToast(mContext, R.string.net_error);
                            if (fixed == null) {
                                include.setVisibility(View.GONE);
                                include_load_error.setVisibility(View.VISIBLE);
                            }

                        }
                    });
        }
    }

    private void showDialog(String title, String content, String type) {
        Bundle bundle = new Bundle();
        bundle.putString(NormalWhiteDialog.TITLE, title);
        bundle.putString(NormalWhiteDialog.CONTENT, content);
        switch (type) {
            case "img":
                ImageDialog dialog = new ImageDialog();
                dialog.setArguments(bundle);
                dialog.show(getSupportFragmentManager(), "img");
                break;
            case "text":
                NormalWhiteDialog textDialog = new NormalWhiteDialog();
                textDialog.setArguments(bundle);
                textDialog.show(getSupportFragmentManager(), "text");
                break;
        }
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.button:
            case R.id.tv_buy_now:
                showBuyDialog();
                break;
            case R.id.tv_ckxq:
            case R.id.iv_platform_logo:
                if (fixed != null) {
                    Bundle args = new Bundle();
                    args.putInt(PlatformDetailActivity.PLATFORM_ID, fixed.getPlatform().getPlatform_id());
                    args.putString(PlatformDetailActivity.PLATFORM_NAME, fixed.getPlatform().getPlatform_name());
                    startActivity(PlatformDetailActivity.class, args);
                } else {
                    NormalUtils.showToast(this, "错误，标的数据为空");
                }
                break;
            case R.id.iv_back_my_account:
                finish();
                break;
            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                include_load_error.setVisibility(View.GONE);
                initData(this);
                break;
        }
    }

    private AnimDialog animDialog;

    public void showBuyDialog() {
        if (UserUtils.isLogin(this)) {
            if (fixed != null) {
                final BidFixed.BidListBean bidListBean = fixed.getBid_list().get(pos);
                final Bundle bundle = new Bundle();
                final int id = bidListBean.getBid_detail().getBid_id();
                bundle.putString(BuyDialog.URL, ApiUtils.getHeader() + "api/app/1.0/bid/" + bid_id +
                        "/invest/");
                bundle.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,
                        id);
                bundle.putString(BuyDialog.class.getSimpleName(),
                        fixed.getPlatform().getPlatform_logo_app_square());
                bundle.putString(BuyDialog.PLATFORM_NAME,
                        fixed.getPlatform().getPlatform_name());
                String userName = UserUtils.getUserName(this);
                bundle.putString(BuyDialog.PHONE, userName.substring(0, 3)
                        + "****" + userName.substring(7, userName.length()));

                bundle.putString(WebActivity.BID_NAME, bidListBean.getBid_detail().getBid_name());

                switch (fixed.getInvest_type()) {
                    case "intelligent":
                        if (animDialog == null)
                            animDialog = AnimDialog.showDialog(this);
                        animDialog.show();
                        bundle.putString(InvestConfirmActivity.BID_TYPE, "p2p");
                        OkGo.get(ApiUtils.getIntelligentInvest(this, id))
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(String s, Call call, Response response) {
                                        IntelligentInvest intelligentInvest =
                                                ParseUtils.parseByGson(s, IntelligentInvest.class);
                                        switch (intelligentInvest.getResult_type()) {
                                            case "open_account":
                                                if (fixed.is_show_register_dialog()) {
                                                    bundle.putBoolean(BuyDialog.IS_AUTO,
                                                            true);
                                                    IntelligentBuyDialog dialog = new IntelligentBuyDialog();
                                                    dialog.setArguments(bundle);
                                                    dialog.show(BidFixedActivity.this.getSupportFragmentManager()
                                                            , "open_account");
                                                } else {
                                                    Intent intent = new Intent(BidFixedActivity.this, OpenAccountActivity.class);
                                                    Bundle bundle = new Bundle();
                                                    bundle.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, bid_id);
                                                    bundle.putString(InvestConfirmActivity.BID_TYPE, "p2p");
                                                    intent.putExtras(bundle);
                                                    BidFixedActivity.this.startActivity(intent);
                                                }

                                                break;
                                            case "invest":
                                                bundle.putString(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,
                                                        ApiUtils.getIntelligentInvestH5(bid_id));
                                                bundle.putString(WebActivity.TITLE, "确认购买");
                                                bundle.putBoolean(WebActivity.IS_FROM_INTELLIGENT, true);
                                                BidFixedActivity.this.startActivity(WebActivity.class, bundle);
                                                break;
                                            case "old_user_jump":
                                                bundle.putBoolean(BuyDialog.IS_AUTO,
                                                        false);
                                                bundle.putString(IntelligentBuyDialog.class.getSimpleName()
                                                        , intelligentInvest.getUrl());
                                                IntelligentBuyDialog dialog2 = new IntelligentBuyDialog();
                                                dialog2.setArguments(bundle);
                                                dialog2.show(BidFixedActivity.this.getSupportFragmentManager()
                                                        , "old_user_jump");
                                                break;
                                            default:
                                                NormalUtils.showToast(BidFixedActivity.this, "错误0x11，未知服务器响应");
                                                break;
                                        }
                                        animDialog.dismiss();
                                    }

                                    @Override
                                    public void onError(Call call, Response response, Exception e) {
                                        super.onError(call, response, e);
                                        NormalUtils.showToast(BidFixedActivity.this, R.string.net_error);
                                        animDialog.dismiss();
                                    }
                                });
                        break;
                    case "normal":
                        bundle.putBoolean(BuyDialog.IS_AUTO,
                                fixed.is_show_register_dialog());
                        BuyDialog fragment = new BuyDialog();
                        fragment.setArguments(bundle);
                        fragment.show(getSupportFragmentManager(), "normal");
                        break;
                    default:
                        NormalUtils.showToast(this, "错误0x10，未知投资类型");
                        break;
                }


            }
        } else {
            startActivity(LoginActivity.class);
        }

    }
}
