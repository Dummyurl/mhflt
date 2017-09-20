package com.changju.fanlitou.fragment.platform;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.adapter.CanBindTwiceAdapter;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.mine.PlatformDetailsBean;
import com.changju.fanlitou.ui.dialog.CustomDialogFragment;

import java.util.List;

/**
 * Created by chengww on 2017/8/5.
 */

public class PlatformBannerViewUtils implements BaseQuickAdapter.OnItemClickListener{

    public PlatformBannerViewUtils(PlatformDetailsBean banner, FragmentActivity context) {
        this.banner = banner;
        this.context = context;
    }

    private PlatformDetailsBean banner;
    private FragmentActivity context;
    private HotActivityAdapter hotActivityAdapter;

    public View initPlatformRules() {
        View view1 =  View.inflate(context, R.layout.item_rules_platform,null);
        ImageView pl_yhgc = (ImageView) view1.findViewById(R.id.pl_yhgc);
        ImageView pl_zfzt = (ImageView) view1.findViewById(R.id.pl_zfzt);
        ImageView pl_zjzr = (ImageView) view1.findViewById(R.id.pl_zjzr);
        ImageView pl_arfl = (ImageView) view1.findViewById(R.id.pl_arfl);
        ImageView pl_txsxf = (ImageView) view1.findViewById(R.id.pl_txsxf);
        ImageView pl_pthd = (ImageView) view1.findViewById(R.id.pl_pthd);
        ImageView pl_lxglf = (ImageView) view1.findViewById(R.id.pl_lxglf);
        if (!banner.isIs_bank_storage_tube()) {
            pl_yhgc.setImageDrawable(null);
        }
        if (!banner.isIs_can_auto_register()) {
            pl_zfzt.setImageDrawable(null);
        }
        if (!banner.isIs_can_assign()) {
            pl_zjzr.setImageDrawable(null);
        }
        if (!banner.isIs_daily_bonus()) {
            pl_arfl.setImageDrawable(null);
        }
        if (!banner.isIs_free_withdraw()) {
            pl_txsxf.setImageDrawable(null);
        }
        if (!banner.isIs_in_activity()) {
            pl_pthd.setImageDrawable(null);
        }
        if (!banner.isInterest_manage_fee()) {
            pl_lxglf.setImageDrawable(null);
        }
        return view1;
    }

    @NonNull
    public View initPlatformIntroduction() {
        View view1 = View.inflate(context, R.layout.item_platform_introduction,null);
        TextView pl_nhsy = (TextView) view1.findViewById(R.id.pl_nhsy);
        TextView pl_sxsj = (TextView) view1.findViewById(R.id.pl_sxsj);
        TextView pl_zczb = (TextView) view1.findViewById(R.id.pl_zczb);
        TextView pl_add = (TextView) view1.findViewById(R.id.pl_add);
        TextView pl_zyyw = (TextView) view1.findViewById(R.id.pl_zyyw);
        TextView pl_bzfs = (TextView) view1.findViewById(R.id.pl_bzfs);
        pl_nhsy.setText(banner.getInterest_min()+"%-"+banner.getInterest_max()+"%");
        pl_sxsj.setText(banner.getOnline_date());
        pl_zczb.setText(banner.getRegistered_capital());
        pl_add.setText(banner.getLocation_city());
        pl_zyyw .setText(banner.getMain_bussiness());
        pl_bzfs.setText(banner.getBonding_method());
        return view1;
    }

    public View initHotEvent() {
        View view1 = View.inflate(context, R.layout.item_hot_evevts,null);
        if (banner.getPlatform_activity() != null) {
            RecyclerView recyclerView  = (RecyclerView) view1.findViewById(R.id.hot_event_rv);
            recyclerView.setNestedScrollingEnabled(false);
            LinearLayoutManager layoutManagerInActivity = new WrapContentLinearLayoutManager(context);
            recyclerView.setLayoutManager(layoutManagerInActivity);
            hotActivityAdapter = new HotActivityAdapter(banner.getPlatform_activity());
            hotActivityAdapter.bindToRecyclerView(recyclerView);
            hotActivityAdapter.setOnItemClickListener(this);
        }
        return view1;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Bundle args = new Bundle();
        args.putInt(CustomDialogFragment.TYPE,
                CustomDialogFragment.HUOACTIVITY);
        args.putSerializable("banner",banner);
        args.putInt("position",position);
        CustomDialogFragment customDialogFragment = new CustomDialogFragment();
        customDialogFragment.setArguments(args);
        customDialogFragment.show(context.getSupportFragmentManager(),"huo_activity");
    }

    private class HotActivityAdapter extends CanBindTwiceAdapter<PlatformDetailsBean.PlatformActivityBean, BaseViewHolder> {
        HotActivityAdapter(List<PlatformDetailsBean.PlatformActivityBean> listBean) {
            super(R.layout.item_platform_detail_huoacticity,listBean);
        }

        @Override
        protected void convert(final BaseViewHolder helper, PlatformDetailsBean.PlatformActivityBean item) {
            helper.setText(R.id.title,item.getTitle())
                    .setText(R.id.content,item.getContent());
            Glide.with(context.getApplicationContext()).load(item.getActivity_logo_url()).into((ImageView) helper.getView(R.id.activity_iv));
        }
    }
}
