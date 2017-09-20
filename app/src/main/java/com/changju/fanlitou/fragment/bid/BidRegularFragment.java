package com.changju.fanlitou.fragment.bid;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.changju.fanlitou.R;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.bean.bid.BidGold;
import com.changju.fanlitou.ui.dialog.ImageDialog;
import com.changju.fanlitou.ui.dialog.NormalWhiteDialog;
import com.changju.fanlitou.util.NormalUtils;

import java.util.List;

/**
 * Created by chengww on 2017/5/27.
 */

public class BidRegularFragment extends LazyFragment {

    public static final String DISPLAY_TYPE = "display_type";

    private String display_type;

    public static final int REGULAR = 0;
    public static final int OTHER = 1;

    private int type;
    private static BidGold.BidDetailBean bid;

    private LinearLayout layout_tag;

    private TextView tv_dqjj, fl, tv_tzqx, tv_zxqt, tv_ljjz, tv_ljjz_cn,tv_zxqt_cn;

    public static BidRegularFragment newInstance(BidGold.BidDetailBean bid) {

        BidRegularFragment.bid = bid;

        Bundle args = new Bundle();
        args.putBoolean(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, false);
        BidRegularFragment fragment = new BidRegularFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initView() {
        layout_tag = (LinearLayout) findViewById(R.id.layout_tag);

        tv_dqjj = (TextView) findViewById(R.id.tv_dqjj);
        fl = (TextView) findViewById(R.id.fl);
        tv_tzqx = (TextView) findViewById(R.id.tv_tzqx);
        tv_zxqt = (TextView) findViewById(R.id.tv_zxqt);
        tv_ljjz = (TextView) findViewById(R.id.tv_ljjz);
        tv_ljjz_cn = (TextView) findViewById(R.id.tv_ljjz_cn);
        tv_zxqt_cn = (TextView) findViewById(R.id.tv_zxqt_cn);

        Bundle args = getArguments();
        if (args != null){
            type = args.getInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
            display_type = args.getString(DISPLAY_TYPE);
        }

    }

    @Override
    protected void doBusiness(Context context) {
        if (bid != null) {
            tv_dqjj.setText(bid.getInterest());
            fl.setText(bid.getPlatform_bonus_interest());
            tv_tzqx.setText(bid.getDuration() + bid.getDuration_unit_str());

            if ("g_banker_normal_bid".equals(display_type)){
                tv_zxqt.setText(bid.getMin_invest_amount() + "元");
            }else {
                tv_zxqt.setText(bid.getMin_invest_amount() + "g");
            }


            if (type == REGULAR) {
                tv_ljjz_cn.setText("累计金重");
                tv_ljjz.setText(bid.getTotal_invest_weight() + "g");
            } else if (type == OTHER) {
                tv_ljjz_cn.setText("专享金价");
                tv_ljjz.setText(bid.getCurrent_price() + "元/克");

                tv_zxqt_cn.setText("只可投");
            }
            //tag
            final List<BidGold.BidDetailBean.TagListBean> tag_list =
                    bid.getTag_list();

            if (tag_list != null && tag_list.size() > 0) {
                for (int i = 0; i < tag_list.size(); i++) {
                    final BidGold.BidDetailBean.TagListBean tag =
                            tag_list.get(i);

                    if (tag.isIs_show_dialog()) {
                        TextView textView = new TextView(context);
                        textView.setText(tag.getName());
                        textView.setTextSize(12);
                        textView.setTextColor(getResources().getColor(R.color.colorTextRed));
                        textView.setPadding(NormalUtils.dp2px(context, 5),
                                NormalUtils.dp2px(context, 2),
                                NormalUtils.dp2px(context, 5),
                                NormalUtils.dp2px(context, 2));

                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT);

                        params.setMargins(NormalUtils.dp2px(context, 3),
                                0, NormalUtils.dp2px(context, 3), 0);
                        textView.setLayoutParams(params);

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
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_bid_gold_regular;
    }

    @Override
    public void widgetClick(View v) {

    }

    private void showDialog(String title, String content,String type) {
        Bundle bundle = new Bundle();
        bundle.putString(NormalWhiteDialog.TITLE, title);
        bundle.putString(NormalWhiteDialog.CONTENT, content);
        switch (type){
            case "img":
                ImageDialog dialog = new ImageDialog();
                dialog.setArguments(bundle);
                dialog.show(getActivity().getSupportFragmentManager(), "img");
                break;
            case "text":
                NormalWhiteDialog textDialog = new NormalWhiteDialog();
                textDialog.setArguments(bundle);
                textDialog.show(getActivity().getSupportFragmentManager(), "text");
                break;
        }
    }
}
