package com.changju.fanlitou.activity.account;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.account.BidRepayDetail;
import com.changju.fanlitou.ui.dialog.NormalWhiteDialog;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chengww on 2017/6/3.
 * 按日返利 界面
 */

public class DailyRebateActivity extends BaseActivity{

    private String bid_type;
    private int bid_id;

    //loading&error
    private View include;
    private View include_load_error;

    @Override
    public void initParams(Bundle params) {
        bid_type = params.getString(DailyRebateActivity.class.getSimpleName());
        bid_id = params.getInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_daily_rebate;
    }

    private RecyclerView mRecyclerView;
    private TextView tv_title;

    private TextView tv_tag,tv_ysze,tv_ysfl,tv_fanli;
    private ImageView iv_question;

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "固收等回款明细页");

        findViewById(R.id.iv_back_my_account).setOnClickListener(this);

        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_daily_rebate);
        mRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(this));

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_tag = (TextView) findViewById(R.id.tv_tag);
        tv_ysze = (TextView) findViewById(R.id.tv_ysze);
        tv_ysfl = (TextView) findViewById(R.id.tv_ysfl);
        tv_fanli = (TextView) findViewById(R.id.tv_fanli);

        iv_question = (ImageView) findViewById(R.id.iv_question);

//        SimpleAdapter adapter = new SimpleAdapter(tests);
//        adapter.bindToRecyclerView(mRecyclerView);
    }

    private int tag_type;
    private boolean isCollected_flag = false;

    private BidRepayDetail repayDetail;
    @Override
    public void doBusiness(final Context mContext) {
        if (TextUtils.isEmpty(bid_type) || bid_id < 1){
            NormalUtils.showToast(mContext,"非法跳转\n错误代码0x01");
            finish();
        }else {
            OkGo.get(ApiUtils.getBidRepayDetail(mContext,3,bid_type,bid_id))
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            repayDetail = ParseUtils.parseByGson(s,BidRepayDetail.class);
                            tv_title.setText(repayDetail.getBid_name());
                            isCollected_flag = repayDetail.isIs_daily_bonus();
                            if (isCollected_flag){
                                iv_question.setVisibility(View.VISIBLE);
                                iv_question.setOnClickListener(DailyRebateActivity.this);
                                tv_fanli.setText("按日返利(元)\n\n" +
                                        repayDetail.getAlready_repay_total()
                                        + "/" + repayDetail.getExpect_repay_total());
                            }else {
                                iv_question.setVisibility(View.GONE);
                                tv_fanli.setText("返利(元)\n\n" +
                                        repayDetail.getTotal_interest());
                            }
                            tv_ysze.setText("应收总额(元)\n\n"
                                    + repayDetail.getCollecting_total());
                            tv_ysfl.setText("待收总额(元)\n\n"
                                    + repayDetail.getCollected_total());

                            BidRepayDetail.TagBean tag = repayDetail.getTag();
                            if (tag != null && !TextUtils.isEmpty(tag.getName())){
                                tv_tag.setVisibility(View.VISIBLE);
                                tv_tag.setText(tag.getName());
                                tag_type = tag.getTag_type();
                            }else {
                                tv_tag.setVisibility(View.GONE);
                            }
                            SimpleAdapter adapter = new SimpleAdapter(
                                    repayDetail.getBid_replay_list());
                            adapter.bindToRecyclerView(mRecyclerView);
                            adapter.setEmptyView(R.layout.view_empty);
                            include.setVisibility(View.GONE);
                            include_load_error.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);
                            NormalUtils.showToast(mContext, R.string.net_error);
                            include.setVisibility(View.GONE);
                            include_load_error.setVisibility(View.VISIBLE);
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
            case R.id.iv_question:
                String title = "";
                String content = "";
                /**
                 * tag类型
                 * 1:现金券
                 * 2:加息
                 * 3:日加息
                 * 4:奖励
                 * 5:奖励
                 */
                switch (tag_type){
                    case 1:
                        title = "现金券";
                        if (isCollected_flag){
                            content = "您在该标使用了现金券，返利金额：\n" +
                                    "返利金额+现金券";
                        }
                        break;
                    case 2:
                        title = "加息";
                        if (isCollected_flag){
                            content ="您在该标使用了加息券，返利金额：\n" +
                                    "返利金额+加息金额";
                        }
                        break;
                    case 3:
                        title = "日加息";
                        if (isCollected_flag){
                            content = "您在该标使用了现金券，返利金额：\n（已返利金额+已加息金额）/（预计返利总金额+预计加息总金额）";
                        }
                        break;
                    default:
                        title = "按日返利";
                        if (isCollected_flag){
                            content = "已返利金额/预计返利总金额";
                        }
                        break;
                }
                showDialog(title,content);
                break;
            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                include_load_error.setVisibility(View.GONE);
                doBusiness(this);
                break;
        }
    }

    class SimpleAdapter extends BaseQuickAdapter<BidRepayDetail.BidReplayListBean, BaseViewHolder> {

        public SimpleAdapter(List<BidRepayDetail.BidReplayListBean> data) {
            super(R.layout.recycler_item_daily_rebate, data);
        }

        @Override
        protected void convert(BaseViewHolder helper,
                               BidRepayDetail.BidReplayListBean item) {
            helper.setText(R.id.tv_latest_repay_date,"回款日期:" + item.getRepay_date())
            .setText(R.id.tv_receivable_principal,String.valueOf(item.getPrincipal()))
            .setText(R.id.tv_receivable_interest,String.valueOf(item.getInterest()))
            .setText(R.id.tv_dqhkq,item.getTerm() + "/" + item.getTotal_term());

            TextView tv_term = helper.getView(R.id.tv_term);
            if (item.isCollected_flag()){
                tv_term.setText("已回款");
                tv_term.setTextColor(getResources().getColor(R.color.colorTextBlue));
            }else {
                tv_term.setText("待回款");
                tv_term.setTextColor(getResources().getColor(R.color.colorTextRed));
            }

            ImageView iv_dqhkq = helper.getView(R.id.iv_dqhkq);
            iv_dqhkq.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DailyRebateActivity.this.showDialog("回款期","当前回款期/回款总期数");
                }
            });
        }
    }

    private void showDialog(String title, String content) {
        Bundle bundle = new Bundle();
        bundle.putString(NormalWhiteDialog.TITLE, title);
        bundle.putString(NormalWhiteDialog.CONTENT, content);
        NormalWhiteDialog dialog = new NormalWhiteDialog();
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), "daily_rebate");
    }
}
