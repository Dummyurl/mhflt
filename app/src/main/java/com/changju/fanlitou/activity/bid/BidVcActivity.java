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
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.LoginActivity;
import com.changju.fanlitou.activity.WebActivity;
import com.changju.fanlitou.activity.platform.PlatformDetailActivity;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.bean.bid.BidVc;
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

import static com.changju.fanlitou.R.id.web_view;

/**
 * Created by chengww on 2017/6/13.
 */

public class BidVcActivity extends BaseActivity {
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

    private ProgressBar mProgressBar;
    private ScrollView mScrollView;
    private TextView tv_buy_now, tv_title, tv_sy, tv_jx,
            tv_qtje, tv_tzqx, tv_hkfs, tv_progress, tv_platform_name,
            tv_content, button;

    private WebView mWebView;

    @Override
    public int bindLayout() {
        return R.layout.fragment_bid_vc;
    }

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "标的详情页-维C理财");

        findViewById(R.id.iv_back_my_account).setOnClickListener(this);

        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        layout_tag = (LinearLayout) findViewById(R.id.layout_tag);

        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);

        tv_sy = (TextView) findViewById(R.id.tv_sy);
        tv_title = (TextView) findViewById(R.id.tv_title);
        if (!TextUtils.isEmpty(bid_name))
            tv_title.setText(bid_name);

        tv_jx = (TextView) findViewById(R.id.tv_jx);
        tv_qtje = (TextView) findViewById(R.id.tv_qtje);
        tv_tzqx = (TextView) findViewById(R.id.tv_tzqx);
        tv_hkfs = (TextView) findViewById(R.id.tv_hkfs);
        tv_platform_name = (TextView) findViewById(R.id.tv_platform_name);
        tv_content = (TextView) findViewById(R.id.tv_content);
        button = (TextView) findViewById(R.id.button);
        findViewById(R.id.tv_ckxq).setOnClickListener(this);
        findViewById(R.id.iv_platform_logo).setOnClickListener(this);

        tv_progress = (TextView) findViewById(R.id.tv_progress);

        iv_platform_logo = (ImageView) findViewById(R.id.iv_platform_logo);

        mScrollView = (ScrollView) findViewById(R.id.layout_scroll);
        tv_buy_now = (TextView) findViewById(R.id.tv_buy_now);
        button.setOnClickListener(this);
        tv_buy_now.setOnClickListener(this);

        mWebView = (WebView) findViewById(web_view);

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

    private BidVc vc;

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
            NormalUtils.showToast(mContext, "标的ID有误！请返回重试");
            finish();
        } else {
            OkGo.get(ApiUtils.getBidVcInfo(mContext, bid_id))
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {

                            boolean isFirst = vc == null;

                            vc = ParseUtils.parseByGson(s, BidVc.class);

                            BidVc.BidDetailBean bid = vc.getBid_detail();

                            if (isFirst){
                                tv_title.setText(bid.getName());
                                if (bid.isIs_no_limit()) {
                                    tv_sy.setText(bid.getMin_interest() +
                                            "%~" + "上不封顶");
                                } else {
                                    tv_sy.setText(bid.getMin_interest() +
                                            "%~" + bid.getMax_interest() + "%");
                                }

                                tv_jx.setText("+" + bid.getBonus_interest() + "%");

                                tv_qtje.setText(bid.getPiece_amount() + "元/份");
                                tv_tzqx.setText(bid.getMin_duration() + bid.getDuration_unit() + "-" +
                                        bid.getMax_duration() + bid.getDuration_unit());
                                tv_hkfs.setText(bid.getPay_type());

                                mProgressBar.setProgress((int) (Float.parseFloat(bid.getProgress())));
                                tv_progress.setText(bid.getProgress() + "%");

                                BidVc.BidDetailBean.PlatformBean platform =
                                        bid.getPlatform();
                                Glide.with(getApplicationContext()).load(platform.getLogo_app_square())
                                        .into(iv_platform_logo);
//                                if (bid.isIs_no_limit()) {
//                                    tv_content.setText("年化收益    " + platform.getInterest_min() +
//                                            "%-上不封顶" + "\n投资周期    " +
//                                            platform.getDuration_min() + platform.getDuration_min_unit() +
//                                            "-" + platform.getDuration_max() + platform.getDuration_max_unit()
//                                            + "\n注册资本    " + platform.getRegistered_capital() + "万");
//                                } else {
                                    tv_content.setText("年化收益    " + platform.getInterest_min() +
                                            "%-" + platform.getInterest_max() + "%\n投资周期    " +
                                            platform.getDuration_min() + platform.getDuration_min_unit() +
                                            "-" + platform.getDuration_max() + platform.getDuration_max_unit()
                                            + "\n注册资本    " + platform.getRegistered_capital() + "万");
//                                }

                                tv_platform_name.setText(platform.getName());

                                NormalUtils.initBidWeb(mWebView,bid.getIntroduction());

                            }


                            //tag
                            final List<BidVc.BidDetailBean.TagListBean> tag_list =
                                    bid.getTag_list();

                            layout_tag.removeAllViews();
                            if (tag_list != null && tag_list.size() > 0) {
                                for (int i = 0; i < tag_list.size(); i++) {
                                    final BidVc.BidDetailBean.TagListBean tag =
                                            tag_list.get(i);

                                    if (tag.isIs_show_dialog()) {
                                        TextView textView = new TextView(BidVcActivity.this);
                                        textView.setText(tag.getName());
                                        textView.setTextSize(12);
                                        textView.setTextColor(getResources().getColor(R.color.colorTextRed));
                                        textView.setPadding(NormalUtils.dp2px(BidVcActivity.this, 5),
                                                NormalUtils.dp2px(BidVcActivity.this, 2),
                                                NormalUtils.dp2px(BidVcActivity.this, 5),
                                                NormalUtils.dp2px(BidVcActivity.this, 2));

                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                                ViewGroup.LayoutParams.WRAP_CONTENT);

                                        params.setMargins(NormalUtils.dp2px(BidVcActivity.this, 3),
                                                0, NormalUtils.dp2px(BidVcActivity.this, 3), 0);
                                        textView.setLayoutParams(params);

                                        textView.setBackgroundResource(R.drawable.shape_tag_red);


                                        //点击事件
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


                            include.setVisibility(View.GONE);
                            include_load_error.setVisibility(View.GONE);

//                            BidVc.BidDetailBean.MiniProgramIntroductionBean introduct =
//                                    bid.getMini_program_introduction();
//                            total_amount.setText("众筹金额         " +
//                                    introduct.getTotal_amount());
//                            real_price.setText("预计售价         " +
//                                    introduct.getReal_price());
//                            max_invest_amount.setText("最大限额         " +
//                                    introduct.getMax_invest_amount());
//
//                            //替换多个空格为换行符
//                            String x = "(起.*)(\\s+)(止.*)";
//                            Pattern pattern = Pattern.compile(x);
//                            String loop = "";
//                            String time = introduct.getInvest_time();
//                            Matcher matcher = pattern.matcher(time);
//                            if (matcher.find()){
//                                for (int i = 0; i < matcher.group(2).length(); i++) {
//                                    loop = loop + "\n";
//                                }
//                                time = matcher.group(1) + loop + matcher.group(3);
//                            }
//                            invest_time.setText(time);
//                            model.setText("车辆类型         " +introduct.getModel());
//                            brand.setText("车辆品牌         " +introduct.getBrand());

                            tv_buy_now.setText(vc.getButton_name());
                            button.setText(vc.getButton_name());
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);
                            NormalUtils.showToast(mContext, R.string.net_error);
                            if (vc == null){
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
                if (vc != null) {
                    Bundle args = new Bundle();
                    args.putInt(PlatformDetailActivity.PLATFORM_ID, vc.getBid_detail().getPlatform().getId());
                    args.putString(PlatformDetailActivity.PLATFORM_NAME, vc.getBid_detail().getPlatform().getName());
                    startActivity(PlatformDetailActivity.class, args);
                } else {
                    NormalUtils.showToast(this, "错误，标的数据为空");
                }
                break;
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

    public void showBuyDialog() {
        if (UserUtils.isLogin(this)) {
            if (vc != null) {
                Bundle bundle = new Bundle();
                bundle.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, bid_id);
                bundle.putString(BuyDialog.class.getSimpleName(),
                        vc.getBid_detail().getPlatform().getLogo_app_square());
                bundle.putString(BuyDialog.PLATFORM_NAME,
                        vc.getBid_detail().getPlatform().getName());
                String userName = UserUtils.getUserName(this);

                bundle.putString(WebActivity.BID_NAME, vc.getBid_detail().getName());

                bundle.putString(BuyDialog.URL, ApiUtils.getHeader() + "api/app/1.0/wuchou_bid/" + bid_id +
                        "/invest/");

                bundle.putString(BuyDialog.PHONE, userName.substring(0, 3)
                        + "****" + userName.substring(7, userName.length()));
                bundle.putBoolean(BuyDialog.IS_AUTO,
                        vc.is_show_register_dialog());
                bundle.putBoolean(BuyDialog.IS_REG, vc.getBid_detail().isIs_user_enjoy_bonus());
                BuyDialog fragment = new BuyDialog();
                fragment.setArguments(bundle);
                fragment.show(getSupportFragmentManager(), "buy");
            }
        } else {
            startActivity(LoginActivity.class);
        }
    }


}
