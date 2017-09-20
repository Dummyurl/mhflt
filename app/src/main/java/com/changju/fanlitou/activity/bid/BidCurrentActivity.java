package com.changju.fanlitou.activity.bid;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.LoginActivity;
import com.changju.fanlitou.activity.WebActivity;
import com.changju.fanlitou.activity.platform.PlatformDetailActivity;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.bean.bid.BidCurrent;
import com.changju.fanlitou.ui.RoundProgressBar;
import com.changju.fanlitou.ui.dialog.BuyDialog;
import com.changju.fanlitou.ui.dialog.ImageDialog;
import com.changju.fanlitou.ui.dialog.NormalWhiteDialog;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.changju.fanlitou.util.UserUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.tencent.smtt.sdk.WebView;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chengww on 2017/5/31.
 */

public class BidCurrentActivity extends BaseActivity {

    private WebView mWebView;

    @Override
    public int bindLayout() {
        return R.layout.activity_bid_current;
    }

    private TabLayout tab_bid_current;
    private ScrollView mScrollView;

    private RoundProgressBar mRoundProgressBar;
    //三条线--执行动画
    private ImageView iv_current_top_line,iv_line_center,iv_current_bottom_line;
    //loading&error
    private View include;
    private View include_load_error;

    private TextView button;
    private TextView tv_buy_now, tv_title, tv_platform_name,
            bid_interest, tv_content, tv_nhfl,
            tv_dsfe, tv_dsfe_cn;

    private ImageView iv_platform_logo;

    private BidCurrent mBidCurrent;

    private LinearLayout layout_tag;

    private int bid_id;
    private String bid_name;

    @Override
    public void initParams(Bundle params) {

        bid_name = params.getString(BidFixedActivity.BID_NAME);

        String bid_id_str = params.getString(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
        if (!TextUtils.isEmpty(bid_id_str) && NormalUtils.isInteger(bid_id_str)){
            bid_id = Integer.valueOf(bid_id_str);
        }else {
            bid_id = params.getInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
        }
    }

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "标的详情页-活期");

        mScrollView = (ScrollView) findViewById(R.id.layout_scroll);

        tv_buy_now = (TextView) findViewById(R.id.tv_buy_now);
        tv_buy_now.setOnClickListener(this);
        button = (TextView) findViewById(R.id.button);
        button.setOnClickListener(this);
        tv_nhfl = (TextView) findViewById(R.id.tv_nhfl);
        tv_dsfe = (TextView) findViewById(R.id.tv_dsfe);
        tv_dsfe_cn = (TextView) findViewById(R.id.tv_dsfe_cn);

        findViewById(R.id.tv_ckxq).setOnClickListener(this);
        findViewById(R.id.iv_platform_logo).setOnClickListener(this);
        findViewById(R.id.iv_back_my_account).setOnClickListener(this);

        mWebView = (WebView) findViewById(R.id.web_view);

        tab_bid_current = (TabLayout) findViewById(R.id.tab_bid_current);
        mScrollView = (ScrollView) findViewById(R.id.layout_scroll);

        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);

        layout_tag = (LinearLayout) findViewById(R.id.layout_tag);


        tv_title = (TextView) findViewById(R.id.tv_title);
        if (!TextUtils.isEmpty(bid_name))
            tv_title.setText(bid_name);

        tv_platform_name = (TextView) findViewById(R.id.tv_platform_name);
        bid_interest = (TextView) findViewById(R.id.bid_interest);

        tv_content = (TextView) findViewById(R.id.tv_content);

        iv_platform_logo = (ImageView) findViewById(R.id.iv_platform_logo);

        mRoundProgressBar = (RoundProgressBar) findViewById(R.id.round_progress);
        iv_current_top_line = (ImageView) findViewById(R.id.iv_current_top_line);
        iv_line_center = (ImageView) findViewById(R.id.iv_line_center);
        iv_current_bottom_line = (ImageView) findViewById(R.id.iv_current_bottom_line);

        tab_bid_current = (TabLayout) findViewById(R.id.tab_bid_current);
        tab_bid_current.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //动画
                startAnim();
                int pos = tab.getPosition();
                List<BidCurrent.BidListBean> beans = mBidCurrent.getBid_list();
                BidCurrent.BidListBean bean = beans.get(pos);
                tv_title.setText(bean.getName());

                bid_interest.setText(bean.getInterest());
                //tag
                final List<BidCurrent.BidListBean.TagListBean> tag_list =
                        bean.getTag_list();

                layout_tag.removeAllViews();
                if (tag_list != null && tag_list.size() > 0) {
                    for (int i = 0; i < tag_list.size(); i++) {
                        final BidCurrent.BidListBean.TagListBean tag =
                                tag_list.get(i);
                        if (tag.isIs_show_dialog()) {
                            TextView textView = new TextView(BidCurrentActivity.this);
                            textView.setText(tag.getName());
                            textView.setTextSize(12);
                            textView.setTextColor(getResources().getColor(R.color.colorTextOrange));
                            textView.setPadding(NormalUtils.dp2px(BidCurrentActivity.this, 5),
                                    NormalUtils.dp2px(BidCurrentActivity.this, 2),
                                    NormalUtils.dp2px(BidCurrentActivity.this, 5),
                                    NormalUtils.dp2px(BidCurrentActivity.this, 2));

                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                    ViewGroup.LayoutParams.WRAP_CONTENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT);

                            params.setMargins(NormalUtils.dp2px(BidCurrentActivity.this, 3),
                                    0, NormalUtils.dp2px(BidCurrentActivity.this, 3), 0);
                            textView.setLayoutParams(params);

//                        textView.setGravity(Gravity.CENTER);
                            textView.setBackgroundResource(R.drawable.shape_tag_orange);

                            NormalUtils.initBidWeb(mWebView,bean.getIntroduction());

                            tv_nhfl.setText(bean.getPlatform_bonus_interest() + "%");
                            tv_dsfe_cn.setText(bean.getShare_name());
                            tv_dsfe.setText(String.valueOf(bean.getShare_amount()));


                            //点击事件
                            if (tag.isIs_show_dialog()) {
                                textView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        switch (tag.getType()){
                                            case "img":
                                                String tagContent = tag.getDialog_img();
                                                showDialog(tag.getTitle(), tagContent,tag.getType());
                                                break;
                                            case "text":
                                                List<String> contents = tag.getContent_list();
                                                StringBuilder sb = new StringBuilder();
                                                if (contents != null && contents.size() > 0){
                                                    for (int j = 0; j < contents.size(); j++) {
                                                        sb.append(contents.get(j));
                                                        if (j < contents.size() - 1){
                                                            sb.append("\n");
                                                        }
                                                    }
                                                }
                                                showDialog(tag.getTitle(), sb.toString(),tag.getType());
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

    private void startAnim() {
        iv_current_top_line.setVisibility(View.GONE);
        iv_line_center.setVisibility(View.GONE);
        iv_current_bottom_line.setVisibility(View.GONE);
        mRoundProgressBar.setAnimProgress(100);
        Animation appearAnimation = AnimationUtils.loadAnimation(this, R.anim.translate_anim);
        if (iv_current_top_line.getVisibility() == View.GONE) {
            iv_current_top_line.startAnimation(appearAnimation);
            iv_current_top_line.setVisibility(View.VISIBLE);
        }
        if (iv_line_center.getVisibility() == View.GONE) {
            iv_line_center.startAnimation(appearAnimation);
            iv_line_center.setVisibility(View.VISIBLE);
        }
        if (iv_current_bottom_line.getVisibility() == View.GONE) {
            iv_current_bottom_line.startAnimation(appearAnimation);
            iv_current_bottom_line.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void doBusiness(final Context mContext) {
    }

    private void initData(final Context mContext) {
        if (bid_id < 1) {
            NormalUtils.showToast(mContext,"标的ID有误！请返回重试！");
            finish();
        } else {
            OkGo.get(ApiUtils.getBidCurrentInfo(mContext, bid_id)).
                    execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {

                            boolean isFirst = mBidCurrent == null;

                            mBidCurrent = ParseUtils.parseByGson(s, BidCurrent.class);

                            if (isFirst){
                                BidCurrent.PlatformBean platform =
                                        mBidCurrent.getPlatform();

                                tv_content.setText("年化收益    " +
                                        platform.getPlatform_interest_min() + "%-" + platform.getPlatform_interest_max()
                                        + "%\n" + "投资周期    " +
                                        platform.getPlatform_duration_min() + platform.getPlatform_duration_min_unit() + "-"
                                        + platform.getPlatform_duration_max() + platform.getPlatform_duration_max_unit()
                                        + "\n注册资本    " + platform.getPlatform_registered_capital() + "万");

                                tv_platform_name.setText(platform.getPlatform_name());
                                Glide.with(getApplicationContext()).load(
                                        platform.getPlatform_logo_app()).
                                        into(iv_platform_logo);
                                //平台描述
                                /**
                                 * 年化收益    1.6%-9%\n投资周期    30天-5年\n注册资本    588万
                                 */

                                //tab
                                tab_bid_current.removeAllTabs();
                                List<BidCurrent.BidListBean> OtherBids = mBidCurrent.getBid_list();
                                if (OtherBids != null && OtherBids.size() > 0) {
                                    for (int i = 0; i < OtherBids.size(); i++) {
                                        tab_bid_current.addTab(
                                                tab_bid_current.newTab().setText(
                                                        OtherBids.get(i).getName()));
                                    }
                                }
                            }

                            //button文字
                            button.setText(mBidCurrent.getButton_name());
                            tv_buy_now.setText(mBidCurrent.getButton_name());

                            include.setVisibility(View.GONE);
                            include_load_error.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);
                            NormalUtils.showToast(mContext, R.string.net_error);
                            if (mBidCurrent == null){
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
                if (mBidCurrent != null) {
                    Bundle args = new Bundle();
                    args.putInt(PlatformDetailActivity.PLATFORM_ID,mBidCurrent.getPlatform().getPlatform_id());
                    args.putString(PlatformDetailActivity.PLATFORM_NAME,mBidCurrent.getPlatform().getPlatform_name());
                    startActivity(PlatformDetailActivity.class,args);
                }else {
                    NormalUtils.showToast(this,"错误，标的数据为空");
                }
                break;
        }
    }

    private void showDialog(String title, String content,String type) {
        Bundle bundle = new Bundle();
        bundle.putString(NormalWhiteDialog.TITLE, title);
        bundle.putString(NormalWhiteDialog.CONTENT, content);
        switch (type){
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

    public void showBuyDialog() {
        if (UserUtils.isLogin(this)) {
            if (mBidCurrent != null) {
                Bundle bundle = new Bundle();

                BidCurrent.BidListBean bidListBeen =
                        mBidCurrent.getBid_list().get(tab_bid_current.getSelectedTabPosition());

                bundle.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, bidListBeen.getBid_id());
                bundle.putString(BuyDialog.class.getSimpleName(),
                        mBidCurrent.getPlatform().getPlatform_logo_app_square());
                bundle.putString(BuyDialog.PLATFORM_NAME,
                        mBidCurrent.getPlatform().getPlatform_name());
                String userName = UserUtils.getUserName(this);

                bundle.putString(WebActivity.BID_NAME,bidListBeen.getName());

                bundle.putString(BuyDialog.PHONE, userName.substring(0, 3)
                        + "****" + userName.substring(7, userName.length()));
                bundle.putBoolean(BuyDialog.IS_AUTO,
                        mBidCurrent.isIs_show_register_dialog());
                bundle.putString(BuyDialog.URL, ApiUtils.getHeader() + "api/app/1.0/current_bid/" + bid_id +
                        "/invest/");
                bundle.putBoolean(BuyDialog.IS_REG, mBidCurrent.isIs_registered());
                BuyDialog fragment = new BuyDialog();
                fragment.setArguments(bundle);
                fragment.show(getSupportFragmentManager(), "buy");
            }
        } else {
            startActivity(LoginActivity.class);
        }
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
}
