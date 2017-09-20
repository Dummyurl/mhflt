package com.changju.fanlitou.activity.bid;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
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
import com.changju.fanlitou.activity.platform.PlatformDetailActivity;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.bean.bid.BidAbroad;
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
 * Created by chengww on 2017/6/14.
 */

public class BidAbroadActivity extends BaseActivity {
    private int bid_id;
    private String bid_name;

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

    private LinearLayout layout_tag;

    private ImageView iv_platform_logo;

    private ScrollView mScrollView;
    private TextView tv_buy_now,tv_title,tv_sy,
            tv_qtje,tv_tzqx,tv_hkfs,tv_platform_name,
            tv_content,button,tv_fl;

    private WebView mWebView;

    @Override
    public int bindLayout() {
        return R.layout.activity_bid_abroad;
    }

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "标的详情页-海外");

        findViewById(R.id.iv_back_my_account).setOnClickListener(this);

        layout_tag = (LinearLayout) findViewById(R.id.layout_tag);

        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);

        tv_sy = (TextView) findViewById(R.id.tv_sy);
        tv_title = (TextView) findViewById(R.id.tv_title);
        if (!TextUtils.isEmpty(bid_name))
            tv_title.setText(bid_name);

        tv_qtje = (TextView) findViewById(R.id.tv_qtje);
        tv_tzqx = (TextView) findViewById(R.id.tv_tzqx);
        tv_hkfs = (TextView) findViewById(R.id.tv_hkfs);
        tv_platform_name = (TextView) findViewById(R.id.tv_platform_name);
        tv_content = (TextView) findViewById(R.id.tv_content);
        button = (TextView) findViewById(R.id.button);
        findViewById(R.id.tv_ckxq).setOnClickListener(this);
        findViewById(R.id.iv_platform_logo).setOnClickListener(this);
        tv_fl = (TextView) findViewById(R.id.tv_fl);

//        real_price = (TextView) findViewById(R.id.real_price);
//        max_invest_amount = (TextView) findViewById(R.id.max_invest_amount);
//        invest_time = (TextView) findViewById(R.id.invest_time);
//        model = (TextView) findViewById(R.id.model);
//        brand = (TextView) findViewById(R.id.brand);


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

        mWebView = (WebView) findViewById(R.id.web_view);
    }

    private BidAbroad abroad;
    @Override
    public void doBusiness(final Context mContext) {
    }

    private void initData(final Context mContext) {
        if (bid_id < 1) {
            NormalUtils.showToast(mContext,"标的ID有误！请返回重试！");
            finish();
        } else {
            OkGo.get(ApiUtils.getBidAbroadInfo(mContext,bid_id))
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {

                            boolean isFirst = abroad == null;

                            abroad = ParseUtils.parseByGson(s,BidAbroad.class);

                            BidAbroad.BidDetailBean bid = abroad.getBid_detail();

                            if (isFirst){
                                tv_title.setText(bid.getName());
                                tv_sy.setText(String.valueOf(bid.getInterest()));
                                tv_fl.setText(String.valueOf(bid.getBonus_value()));

                                tv_qtje.setText(bid.getDuration() +
                                        bid.getDuration_unit_str());
                                tv_tzqx.setText(bid.getMin_amount_str() + "美金");
                                tv_hkfs.setText(bid.getTotal_amount_str() + "美金");

                                BidAbroad.BidDetailBean.PlatformBean platform =
                                        bid.getPlatform();
                                Glide.with(getApplicationContext()).load(platform.getLogo_app())
                                        .into(iv_platform_logo);
                                tv_content.setText("年化收益    " + platform.getInterest_min() +
                                        "%-" + platform.getInterest_max() + "%\n投资周期    " +
                                        platform.getDuration_min() + platform.getDuration_min_unit() +
                                        "-" + platform.getDuration_max() + platform.getDuration_max_unit()
                                        + "\n注册资本    " + platform.getRegistered_capital() + "万");

                                tv_platform_name.setText(platform.getName());

                                NormalUtils.initBidWeb(mWebView,bid.getWap_introduction());
                            }

                            //tag
                            final List<BidAbroad.BidDetailBean.TagListBean> tag_list =
                                    bid.getTag_list();
                            layout_tag.removeAllViews();
                            if (tag_list != null && tag_list.size() > 0) {
                                for (int i = 0; i < tag_list.size(); i++) {
                                    final BidAbroad.BidDetailBean.TagListBean tag =
                                            tag_list.get(i);

                                    if (tag.isIs_show_dialog()){
                                        TextView textView = new TextView(BidAbroadActivity.this);
                                        textView.setText(tag.getName());
                                        textView.setTextSize(12);
                                        textView.setTextColor(getResources().getColor(R.color.colorTextRed));
                                        textView.setPadding(NormalUtils.dp2px(BidAbroadActivity.this, 5),
                                                NormalUtils.dp2px(BidAbroadActivity.this, 2),
                                                NormalUtils.dp2px(BidAbroadActivity.this, 5),
                                                NormalUtils.dp2px(BidAbroadActivity.this, 2));

                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                                ViewGroup.LayoutParams.WRAP_CONTENT);

                                        params.setMargins(NormalUtils.dp2px(BidAbroadActivity.this, 3),
                                                0, NormalUtils.dp2px(BidAbroadActivity.this, 3), 0);
                                        textView.setLayoutParams(params);

                                        textView.setBackgroundResource(R.drawable.shape_tag_red);

                                        //点击事件
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

                            tv_buy_now.setText(abroad.getButton_name());
                            button.setText(abroad.getButton_name());

                            include.setVisibility(View.GONE);
                            include_load_error.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);
                            NormalUtils.showToast(mContext, R.string.net_error);
                            if (abroad == null){
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
                if (abroad != null) {
                    Bundle args = new Bundle();
                    args.putInt(PlatformDetailActivity.PLATFORM_ID,abroad.getBid_detail().getPlatform().getId());
                    args.putString(PlatformDetailActivity.PLATFORM_NAME,abroad.getBid_detail().getPlatform().getName());
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
        if (UserUtils.isLogin(this)){
            if (abroad != null){
                Bundle bundle = new Bundle();
                bundle.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, bid_id);
                bundle.putString(BuyDialog.URL, ApiUtils.getHeader() + "api/app/1.0/foreign_bid/" + bid_id +
                        "/invest/");
                bundle.putString(BuyDialog.class.getSimpleName(),
                        abroad.getBid_detail().getPlatform().getLogo_app_square());
                bundle.putString(BuyDialog.PLATFORM_NAME,
                        abroad.getBid_detail().getPlatform().getName());
                String userName = UserUtils.getUserName(this);

                bundle.putString(WebActivity.BID_NAME,abroad.getBid_detail().getName());

                bundle.putString(BuyDialog.PHONE,userName.substring(0,3)
                        + "****" + userName.substring(7,userName.length()));
                bundle.putBoolean(BuyDialog.IS_AUTO,
                        abroad.is_show_register_dialog());
                bundle.putBoolean(BuyDialog.IS_REG,abroad.getBid_detail().isIs_registered());
                BuyDialog fragment = new BuyDialog();
                fragment.setArguments(bundle);
                fragment.show(getSupportFragmentManager(), "buy");
            }
        }else {
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
