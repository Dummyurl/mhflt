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
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.bean.bid.BidInsurance;
import com.changju.fanlitou.ui.dialog.BuyDialog;
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
 * Created by chengww on 2017/5/25.
 */

public class BidInsuranceActivity extends BaseActivity {
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

    private ImageView iv_insurance_title_bid,wap_logo;

    private TextView tv_title,tv_percent_bid,tv_money_bid,
            tv_buy,tv_buy_now,tv_insurance_name_bid,tv_lplc;

    private LinearLayout layout_imgs;
    private ScrollView mScrollView;

    @Override
    public int bindLayout() {
        return R.layout.activity_bid_insurance;
    }

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "标的详情页-保险");

        findViewById(R.id.iv_back_my_account).setOnClickListener(this);

        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);

        tv_title = (TextView) findViewById(R.id.tv_title);
        if (!TextUtils.isEmpty(bid_name))
            tv_title.setText(bid_name);

        tv_percent_bid = (TextView) findViewById(R.id.tv_percent_bid);
        tv_money_bid = (TextView) findViewById(R.id.tv_money_bid);
        tv_buy = (TextView) findViewById(R.id.tv_buy);
        tv_buy_now = (TextView) findViewById(R.id.tv_buy_now);
        mScrollView = (ScrollView) findViewById(R.id.layout_scroll);
        wap_logo = (ImageView) findViewById(R.id.wap_logo);
        tv_lplc = (TextView) findViewById(R.id.tv_lplc);

        tv_buy.setOnClickListener(this);
        tv_buy_now.setOnClickListener(this);

        iv_insurance_title_bid = (ImageView)
                findViewById(R.id.iv_insurance_title_bid);
        tv_insurance_name_bid = (TextView) findViewById(R.id.tv_insurance_name_bid);

        layout_imgs = (LinearLayout) findViewById(R.id.layout_imgs);

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

    private BidInsurance insurance;

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
            NormalUtils.showToast(mContext,"标的ID有误！请返回重试！");
            include.setVisibility(View.GONE);
            include_load_error.setVisibility(View.VISIBLE);
        } else {
            OkGo.get(ApiUtils.getBidInsuranceInfo(mContext,bid_id))
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {

                            boolean isFirst = insurance == null;

                            insurance = ParseUtils.parseByGson(s,BidInsurance.class);

                            BidInsurance.BidDetailBean bid = insurance.getBid_detail();

                            if (isFirst){
                                tv_title.setText(bid.getName());
                                tv_percent_bid.setText(bid.getBonus_value() + "%");
                                tv_money_bid.setText(bid.getPrice() + "元起");

                                Glide.with(getApplicationContext()).load(bid.getProduct_img_url())
                                        .into(iv_insurance_title_bid);

                                Glide.with(getApplicationContext()).load(bid.getInsurance_company_wap_logo())
                                        .into(wap_logo);

                                List<BidInsurance.BidDetailBean.IntroductionBean> introduction =
                                        bid.getIntroduction();
                                if (introduction != null && introduction.size() > 0){
                                    for (int i = 0; i < introduction.size(); i++) {
                                        ImageView image = new ImageView(mContext);
                                        LinearLayout.LayoutParams paras =
                                                new LinearLayout.LayoutParams(
                                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                                        ViewGroup.LayoutParams.WRAP_CONTENT);
                                        image.setLayoutParams(paras);

                                        Glide.with(getApplicationContext()).load(introduction.get(i).
                                                getUrl()).into(image);

                                        layout_imgs.addView(image);
                                    }
                                }

                                tv_insurance_name_bid.setText(insurance.getBid_detail().getName());

                                tv_lplc.setText("保险理赔流程文案：理赔绿色通道，请咨询" + bid.getPlatform().getName()
                                        + "官方客服咨询理赔服务，或在" + bid.getPlatform().getName() +
                                        "APP订单页直接申请理赔。");
                            }

                            tv_buy.setText(insurance.getButton_name());
                            tv_buy_now.setText(insurance.getButton_name());

                            include.setVisibility(View.GONE);
                            include_load_error.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);
                            NormalUtils.showToast(mContext, R.string.net_error);
                            if (insurance == null){
                                include.setVisibility(View.GONE);
                                include_load_error.setVisibility(View.VISIBLE);
                            }
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
                initData(this);
                break;
            case R.id.tv_buy:
            case R.id.tv_buy_now:
                showBuyDialog();
                break;
        }
    }

    public void showBuyDialog() {
        if (UserUtils.isLogin(this)) {
            if (insurance != null) {
                Bundle bundle = new Bundle();
                bundle.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, bid_id);
                bundle.putString(BuyDialog.class.getSimpleName(),
                        insurance.getBid_detail().getPlatform().getLogo_app_square());
                bundle.putString(BuyDialog.PLATFORM_NAME,
                        insurance.getBid_detail().getPlatform().getName());
                String userName = UserUtils.getUserName(this);

                bundle.putString(WebActivity.BID_NAME,insurance.getBid_detail().getName());

                bundle.putString(BuyDialog.URL, ApiUtils.getHeader() + "api/app/1.0/insurance_bid/" + bid_id +
                        "/invest/");

                bundle.putString(BuyDialog.PHONE, userName.substring(0, 3)
                        + "****" + userName.substring(7, userName.length()));
                bundle.putBoolean(BuyDialog.IS_AUTO,
                        insurance.is_show_register_dialog());
                bundle.putBoolean(BuyDialog.IS_REG, insurance.getBid_detail().isIs_user_enjoy_bonus());
                BuyDialog fragment = new BuyDialog();
                fragment.setArguments(bundle);
                fragment.show(getSupportFragmentManager(), "buy");
            }
        } else {
            startActivity(LoginActivity.class);
        }
    }
}
