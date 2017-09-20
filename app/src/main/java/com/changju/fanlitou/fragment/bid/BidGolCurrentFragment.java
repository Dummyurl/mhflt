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
 * Created by chengww on 2017/6/14.
 */

public class BidGolCurrentFragment extends LazyFragment {

    private BidGold.BidDetailBean bid;

    private boolean isShow;
    private LinearLayout layout_tag;
    private TextView tv_dqjj, tv_nhsy, tv_nhfl,tv_hint;

    public static BidGolCurrentFragment newInstance(BidGold.BidDetailBean gold,boolean isShow) {
        BidGolCurrentFragment fragment = new BidGolCurrentFragment();
        fragment.isShow = isShow;
        fragment.bid = gold;
        return fragment;
    }

    @Override
    protected void initView() {
        layout_tag = (LinearLayout) findViewById(R.id.layout_tag);
        tv_dqjj = (TextView) findViewById(R.id.tv_dqjj);
        tv_nhsy = (TextView) findViewById(R.id.tv_nhsy);
        tv_nhfl = (TextView) findViewById(R.id.tv_nhfl);
        tv_hint = (TextView) findViewById(R.id.tv_hint);


    }

    @Override
    protected void doBusiness(Context context) {
        if (bid != null) {
            if (isShow){
                tv_hint.setVisibility(View.VISIBLE);
                tv_hint.setText("具体金价以" + bid.getPlatform().getName() + "为准");
            }else {
                tv_hint.setVisibility(View.GONE);
            }

            tv_nhsy.setText(bid.getInterest() + "%");
            tv_nhfl.setText(bid.getPlatform_bonus_interest() + "%");

            TextView tv_hint_1 = (TextView) findViewById(R.id.tv_hint_1);
            TextView tv_hint_2 = (TextView) findViewById(R.id.tv_hint_2);
            TextView tv_hint_3 = (TextView) findViewById(R.id.tv_hint_3);
            switch (bid.getDisplay_type()){
                case "g_banker_current_bid":
                    tv_hint_1.setText("1元起投");
                    tv_hint_2.setText("免买入费");
                    tv_hint_3.setText("全天交易");
                    break;
                case "g_banker_bargin_bid":
                    tv_hint_1.setText("5克起投");
                    tv_hint_2.setText("全天交易");
                    tv_hint_3.setText("免买入费");
                    //黄金钱包--特价金 ————》年化收益==投资期限
                    tv_nhsy.setText(bid.getDuration() + bid.getDuration_unit_str());
                    TextView text = (TextView) findViewById(R.id.tv_nhsy_cn);
                    text.setText("投资期限");

                    break;

            }
            tv_dqjj.setText(String.valueOf(bid.getCurrent_price()));
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
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_bid_gold_current;
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
