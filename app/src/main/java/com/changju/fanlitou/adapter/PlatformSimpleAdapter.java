package com.changju.fanlitou.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.LoginActivity;
import com.changju.fanlitou.base.MyApplication;
import com.changju.fanlitou.bean.LoginBean;
import com.changju.fanlitou.bean.discover.Platform;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.changju.fanlitou.util.TagUtils;
import com.changju.fanlitou.util.UserUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

import static com.changju.fanlitou.R.id.expticket_list;

/**
 * Created by chengww on 2017/5/9.
 */

public class PlatformSimpleAdapter extends BaseQuickAdapter<Platform.PlatformListBean, BaseViewHolder> {

    private int width;

    public PlatformSimpleAdapter(List<Platform.PlatformListBean> listBean) {
        super(R.layout.recycler_item_platform, listBean);
    }

    @Override
    protected void convert(BaseViewHolder helper, final Platform.PlatformListBean item) {
        String platform_bonus_value = item.getPlatform_bonus_value();
        //type
        String profit_info_type = item.getProfit_info().getType();
        //红点总数量
        int total = item.getProfit_info().getTotal();
        List<Platform.PlatformListBean.ProfitInfoBean.ProfitListBean> list =
                item.getProfit_info().getProfit_list();

        //recycler item 宽度
        if (MyApplication.ScreenWidth <= 0) {
            MyApplication.ScreenWidth = NormalUtils.getScreenWidth(mContext);
        }

        if (width <= 0)
            width = MyApplication.ScreenWidth - NormalUtils.dp2px(mContext, 22);

        if (item.getProfit_info().isIs_show()) {
            initProfitList(helper, profit_info_type, total, list, width);
        } else {
            helper.getView(expticket_list).setVisibility(View.GONE);
            helper.getView(R.id.include_item_profit).setVisibility(View.GONE);
        }


        //是否被收藏
        final ImageView iv_check = helper.getView(R.id.icon_favorite);

        if (item.isIs_checked()) {
            iv_check.setImageResource(R.mipmap.platform_favored2);
//                    holder.icon_favorite.setImageResource();
        } else {
            iv_check.setImageResource(R.mipmap.icon_favorite);
        }

        iv_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserUtils.isLogin(mContext)) {
                    String time = String.valueOf(System.currentTimeMillis());
                    String random = ApiUtils.getRandom();
                    OkGo.post(ApiUtils.postSaveFavoritePlatformFromDetail())
                            .params("t", time)
                            .params("random", random)
                            .params("sign", NormalUtils.md5(time + random + MyApplication.key))
                            .params("user_name", UserUtils.getUserName(mContext))
                            .params("login_token", UserUtils.getLoginToken(mContext))
                            .params("platform_id", item.getPlatform_id() + "")
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    LoginBean bean = ParseUtils.parseByGson(s, LoginBean.class);
                                    if (bean.isSuccess()) {
                                        if (item.isIs_checked()) {
                                            iv_check.setImageResource(R.mipmap.icon_favorite);
                                        } else {
                                            iv_check.setImageResource(R.mipmap.platform_favored2);
                                        }
                                        item.setIs_checked(!item.isIs_checked());
                                    }
                                }

                                @Override
                                public void onError(Call call, Response response, Exception e) {
                                    super.onError(call, response, e);
                                    NormalUtils.showToast(mContext, R.string.net_error);
                                }
                            });
                } else {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    mContext.startActivity(intent);
                }
            }

        });
        helper.setText(R.id.platform_list_name, item.getName())
                .setText(R.id.platform_bonus_value, "+" + platform_bonus_value + "%");
        //平台logo
        Glide.with(mContext).load(item.getLogo_app()).crossFade()
                .into((ImageView) helper.getView(R.id.platform_list_logo_app));
        ImageView first_invest_redpacket_award_info = helper.getView(R.id.first_invest_redpacket_award_info);
        if (item.getFirst_invest_redpacket_award_info() == null || !item.getFirst_invest_redpacket_award_info().isIs_show()) {
            first_invest_redpacket_award_info.setVisibility(View.GONE);
        } else {
            first_invest_redpacket_award_info.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(item.getFirst_invest_redpacket_award_info().getImg_url())
                    .into(first_invest_redpacket_award_info);
        }

        //tag
        List<Platform.PlatformListBean.TagListBean> tag_list = item.getTag_list();
        LinearLayout rectangle_tag = helper.getView(R.id.rectangle_tag);
        rectangle_tag.removeAllViews();
        for (int i = 0; i < tag_list.size(); i++) {
            rectangle_tag.addView(
                    TagUtils.createTextTag(mContext,tag_list.get(i).getStyle(),tag_list.get(i).getTag_name()));
        }

    }




    private void initProfitList(BaseViewHolder helper, String profit_info_type, int total,
                                List<Platform.PlatformListBean.ProfitInfoBean.ProfitListBean> list,
                                int width) {
        TextView red_line = helper.getView(R.id.tv_red_line);

        //红线padding 除以个数 除以2
        int padding = width / total / 2;
        switch (profit_info_type) {
            case "profit":
                helper.getView(expticket_list).setVisibility(View.GONE);
                helper.getView(R.id.include_item_profit).setVisibility(View.VISIBLE);

                TextView text_1 = helper.getView(R.id.text_1);
                TextView text_2 = helper.getView(R.id.text_2);
                TextView text_3 = helper.getView(R.id.text_3);
                TextView text_4 = helper.getView(R.id.text_4);

                TextView num_1 = helper.getView(R.id.number_1);
                TextView num_2 = helper.getView(R.id.number_2);
                TextView num_3 = helper.getView(R.id.number_3);
                TextView num_4 = helper.getView(R.id.number_4);


                switch (total) {

                    case 2:
                        helper.getView(R.id.layout_item_1).setVisibility(View.VISIBLE);
                        helper.getView(R.id.layout_item_2).setVisibility(View.INVISIBLE);
                        helper.getView(R.id.layout_item_3).setVisibility(View.INVISIBLE);
                        helper.getView(R.id.layout_item_4).setVisibility(View.VISIBLE);

                        red_line.setPadding(padding / 2, 0, padding / 2, 0);

                        text_1.setText(list.get(0).getName());
                        text_4.setText(list.get(1).getName());

                        num_1.setText(list.get(0).getValue());
                        num_4.setText(list.get(1).getValue());

                        break;
                    case 3:

                        helper.getView(R.id.layout_item_1).setVisibility(View.VISIBLE);
                        helper.getView(R.id.layout_item_2).setVisibility(View.VISIBLE);
                        helper.getView(R.id.layout_item_3).setVisibility(View.VISIBLE);
                        helper.getView(R.id.layout_item_4).setVisibility(View.GONE);

                        red_line.setPadding(padding, 0, padding, 0);

                        text_1.setText(list.get(0).getName());
                        text_2.setText(list.get(1).getName());
                        text_3.setText(list.get(2).getName());

                        num_1.setText(list.get(0).getValue());
                        num_2.setText(list.get(1).getValue());
                        num_3.setText(list.get(2).getValue());
                        break;
                    case 4:
                        helper.getView(R.id.layout_item_1).setVisibility(View.VISIBLE);
                        helper.getView(R.id.layout_item_2).setVisibility(View.VISIBLE);
                        helper.getView(R.id.layout_item_3).setVisibility(View.VISIBLE);
                        helper.getView(R.id.layout_item_4).setVisibility(View.VISIBLE);

                        red_line.setPadding(padding, 0, padding, 0);

                        text_1.setText(list.get(0).getName());
                        text_2.setText(list.get(1).getName());
                        text_3.setText(list.get(2).getName());
                        text_4.setText(list.get(3).getName());

                        num_1.setText(list.get(0).getValue());
                        num_2.setText(list.get(1).getValue());
                        num_3.setText(list.get(2).getValue());
                        num_4.setText(list.get(3).getValue());
                        break;
                    default:
                        helper.getView(R.id.include_item_profit).setVisibility(View.GONE);
                        break;
                }
                break;
            case "ticket":
                helper.getView(R.id.include_item_profit).setVisibility(View.GONE);
                helper.getView(expticket_list).setVisibility(View.VISIBLE);
                TextView expticket_person = helper.getView(R.id.expticket_person);
                TextView expticket_invest = helper.getView(R.id.expticket_invest);

                LinearLayout expticket_list = helper.getView(R.id.expticket_list);

                // TODO: 2017/6/22 优惠券显示
                switch (total) {
                    case 1:
                        expticket_person.setVisibility(View.VISIBLE);
                        expticket_invest.setVisibility(View.GONE);
                        expticket_person.setText(list.get(0).getValue());
                        expticket_list.setPadding(NormalUtils.dp2px(mContext, 48), 0, 0, 0);
                        break;
                    case 2:
                        expticket_person.setVisibility(View.VISIBLE);
                        expticket_invest.setVisibility(View.VISIBLE);
                        expticket_person.setText(list.get(0).getValue());
                        expticket_invest.setText(list.get(1).getValue());
                        expticket_list.setPadding(0, 0, 0, 0);
                        break;
                    default:
                        expticket_person.setVisibility(View.GONE);
                        expticket_invest.setVisibility(View.GONE);
                        break;
                }

                break;
            default:
                break;
        }
    }
}
