package com.changju.fanlitou.activity.bid;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.LoginActivity;
import com.changju.fanlitou.activity.WebActivity;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.bid.BidDct;
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
 * Created by chengww on 2017/6/15.
 */

public class BidCrowdFundingActivity extends BaseActivity {
    private int bid_id;

    @Override
    public void initParams(Bundle params) {
        String bid_id_str = params.getString(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
        if (!TextUtils.isEmpty(bid_id_str) && NormalUtils.isInteger(bid_id_str)){
            bid_id = Integer.valueOf(bid_id_str);
        }else {
            bid_id = params.getInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
        }

    }

    //loading&error
    private View include;
    private View include_load_error;

    private ScrollView mScrollView;

    private TextView tv_buy_now, button;

    private SimpleAdapter mAdapter;

    private ImageView bid_image_small;

    private RoundProgressBar mProgressBar;

    private LinearLayout layout_tag;

    private RecyclerView recycler_view;

    private WebView web_view;

    private TextView bid_interest, tv_tzqx, tv_zxqt, tv_ljjz;

    @Override
    public int bindLayout() {
        return R.layout.activity_bid_crowd_funding;
    }

    private Animation showAnim, hideAnim;

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "标的详情页-多彩投");

        //状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            FrameLayout.LayoutParams p =
                    new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, NormalUtils.getStatusBarHeight(this));
            findViewById(R.id.view).setLayoutParams(p);
        }
        findViewById(R.id.iv_back_my_account).setOnClickListener(this);

        bid_image_small = (ImageView) findViewById(R.id.bid_image_small);

        mProgressBar = (RoundProgressBar) findViewById(R.id.round_progress);
        layout_tag = (LinearLayout) findViewById(R.id.layout_tag);
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new WrapContentLinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        web_view = (WebView) findViewById(R.id.web_view);
        mAdapter = new SimpleAdapter(null);
        mAdapter.bindToRecyclerView(recycler_view);

        bid_interest = (TextView) findViewById(R.id.bid_interest);
        tv_tzqx = (TextView) findViewById(R.id.tv_tzqx);
        tv_zxqt = (TextView) findViewById(R.id.tv_zxqt);
        tv_ljjz = (TextView) findViewById(R.id.tv_ljjz);

        //初始化动画
        showAnim = AnimationUtils.loadAnimation(this, R.anim.invent_arrow_rotate_show);
        hideAnim = AnimationUtils.loadAnimation(this, R.anim.invent_arrow_rotate_hide);
        showAnim.setFillAfter(true);
        hideAnim.setFillAfter(true);

        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);

        button = (TextView) findViewById(R.id.button);
        tv_buy_now = (TextView) findViewById(R.id.tv_buy_now);
        button.setOnClickListener(this);
        tv_buy_now.setOnClickListener(this);

        mScrollView = (ScrollView) findViewById(R.id.layout_scroll);

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

    private void initWebView(String content) {
        StringBuilder sb = new StringBuilder();
        //拼接一段HTML代码
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>多彩投</title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append(content);
        sb.append("</body>");
        sb.append("</html>");
        //使用简单的loadData方法会导致乱码，可能是Android API的Bug
        //show.loadData(sb.toString(), "text/html", "utf-8");
        //加载、并显示HTML代码
        web_view.loadDataWithBaseURL(null, sb.toString(), "text/html", "utf-8", null);
    }

    private BidDct dct;
    @Override
    public void doBusiness(final Context mContext) {
    }

    private void initData(final Context mContext) {
        if (bid_id < 1) {
            NormalUtils.showToast(mContext,"标的ID有误！请返回重试！");
            finish();
        } else {
            OkGo.get(ApiUtils.getBidrowdfundingInfo(mContext, bid_id))
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {

                            boolean isFirst = dct == null;

                            dct = ParseUtils.parseByGson(s, BidDct.class);

                            if (isFirst){
                                BidDct.BidDetailBean bid = dct.getBid_detail();
                                layout_tag.removeAllViews();

                                ImageView logo_app_square = new ImageView(mContext);
                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                params.setMargins(NormalUtils.dp2px(mContext,25),0,NormalUtils.dp2px(mContext,17),0);
                                logo_app_square.setLayoutParams(params);
                                Glide.with(getApplicationContext()).load(bid.getBid_image_small())
                                        .into(bid_image_small);
                                Glide.with(getApplicationContext()).load(bid.getPlatform().
                                        getLogo_app_square()).into(logo_app_square);

                                layout_tag.addView(logo_app_square);
                                //progress
                                int progress = (int) bid.getFinancing_rate();
                                bid_interest.setText(progress + "%");
                                if (progress > 100) {
                                    progress = 100;
                                } else if (progress < 0) {
                                    progress = 0;
                                }
                                mProgressBar.setProgress(progress);

                                tv_tzqx.setText(bid.getTotal_amount());
                                tv_zxqt.setText(bid.getRemain_time());
                                tv_ljjz.setText(bid.getPlatform_bonus_interest() + "%");
                                //tag
                                final List<BidDct.BidDetailBean.TagListBean> tag_list =
                                        bid.getTag_list();


                                if (tag_list != null && tag_list.size() > 0) {
                                    for (int i = 0; i < tag_list.size(); i++) {
                                        final BidDct.BidDetailBean.TagListBean tag =
                                                tag_list.get(i);

                                        if (tag.isIs_show_dialog()) {
                                            TextView textView = new TextView(mContext);
                                            textView.setText(tag.getName());
                                            textView.setTextSize(12);
                                            textView.setTextColor(getResources().getColor(R.color.colorTextRed));
                                            textView.setPadding(NormalUtils.dp2px(mContext, 5),
                                                    NormalUtils.dp2px(mContext, 2),
                                                    NormalUtils.dp2px(mContext, 5),
                                                    NormalUtils.dp2px(mContext, 2));

                                            LinearLayout.LayoutParams para = new LinearLayout.LayoutParams(
                                                    ViewGroup.LayoutParams.WRAP_CONTENT,
                                                    ViewGroup.LayoutParams.WRAP_CONTENT);

                                            para.setMargins(NormalUtils.dp2px(mContext, 3),
                                                    0, NormalUtils.dp2px(mContext, 3), 0);
                                            textView.setLayoutParams(para);

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

                                List<BidDct.BidDetailBean.ProjectPlanBean> project =
                                        bid.getProject_plan();
                                mAdapter.setNewData(project);

                                initWebView(bid.getBid_detail());
                            }

                            button.setText(dct.getButton_name());
                            tv_buy_now.setText(dct.getButton_name());

                            include.setVisibility(View.GONE);
                            include_load_error.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);
                            NormalUtils.showToast(mContext, R.string.net_error);
                            if (dct == null){
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
        }
    }

    class SimpleAdapter extends BaseQuickAdapter<BidDct.BidDetailBean.ProjectPlanBean, BaseViewHolder> {
        private SparseBooleanArray isOpened;

        public SimpleAdapter(@Nullable List<BidDct.BidDetailBean.ProjectPlanBean> data) {
            super(R.layout.recycler_item_crowd_funding, data);
            isOpened = new SparseBooleanArray();
        }

        @Override
        protected void convert(final BaseViewHolder helper, BidDct.BidDetailBean.ProjectPlanBean item) {
            final LinearLayout layout_content = helper.getView(R.id.layout_content);
            helper.setText(R.id.price, item.getPrice() + "元")
                    .setText(R.id.limit_num, item.getLimit_num() + "份")
                    .setText(R.id.tv_content, item.getReward_plan_detail());

            final ImageView iv_arrow_right = helper.getView(R.id.iv_arrow_right);

            if (isOpened.get(helper.getAdapterPosition(), false)) {
                layout_content.setVisibility(View.VISIBLE);
            } else {
                layout_content.setVisibility(View.GONE);
            }


            LinearLayout layout = helper.getView(R.id.layout);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isOpened.get(helper.getAdapterPosition(), false)) {
                        layout_content.setVisibility(View.GONE);
                        iv_arrow_right.startAnimation(hideAnim);
                        isOpened.put(helper.getAdapterPosition(), false);
                    } else {
                        iv_arrow_right.startAnimation(showAnim);
                        layout_content.setVisibility(View.VISIBLE);
                        isOpened.put(helper.getAdapterPosition(), true);
                    }
                }
            });
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
            if (dct != null) {
                Bundle bundle = new Bundle();
                bundle.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, bid_id);
                bundle.putString(BuyDialog.class.getSimpleName(),
                        dct.getBid_detail().getPlatform().getLogo_app_square());
                bundle.putString(BuyDialog.URL, ApiUtils.getHeader() + "api/app/1.0/crowdfunding_bid/" + bid_id +
                        "/invest/");
                bundle.putString(BuyDialog.PLATFORM_NAME,
                        dct.getBid_detail().getPlatform().getName());
                String userName = UserUtils.getUserName(this);

                bundle.putString(WebActivity.BID_NAME,dct.getBid_detail().getName());

                bundle.putString(BuyDialog.PHONE, userName.substring(0, 3)
                        + "****" + userName.substring(7, userName.length()));
                bundle.putBoolean(BuyDialog.IS_AUTO,
                        dct.is_show_register_dialog());
                bundle.putBoolean(BuyDialog.IS_REG, dct.getBid_detail().isIs_user_enjoy_bonus());
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
