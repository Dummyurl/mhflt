package com.changju.fanlitou.util;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.changju.fanlitou.bean.home.HomeBean;

/**
 * Created by chengww on 2017/8/29.
 */

public class NetworkImageHolderView implements Holder<HomeBean.BannersBean> {
    private ImageView imageView;

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }

    @Override
    public void UpdateUI(final Context context, int position, HomeBean.BannersBean data) {
        Glide.with(context).load(data.getBanner_url()).into(imageView);
    }
}
