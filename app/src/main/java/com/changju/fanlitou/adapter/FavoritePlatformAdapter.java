package com.changju.fanlitou.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.bean.home.FavoritePlatformList;
import com.changju.fanlitou.ui.ImageTextButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chengww on 2017/5/11.
 */

public class FavoritePlatformAdapter extends BaseQuickAdapter<FavoritePlatformList.FavoritePlatformBean,BaseViewHolder> {
    public FavoritePlatformAdapter(@Nullable List<FavoritePlatformList.FavoritePlatformBean> data) {
        super(R.layout.item_grid_favorite, data);
    }

    public Map<Integer, Boolean> getIsChecked() {
        return isChecked;
    }

    private Map<Integer,Boolean> isChecked = new HashMap<>();

    @Override
    protected void convert(BaseViewHolder helper, final FavoritePlatformList.FavoritePlatformBean item) {
        final ImageTextButton imageTextButton = helper.getView(R.id.grid_image_text);
        imageTextButton.setTextViewText(item.getPlatform_name());
        imageTextButton.setTextColor(mContext.getResources().getColor(R.color.colorTextPrimary));
        imageTextButton.setTextColor(mContext.getResources().getColor(R.color.colorTextPrimary));
        Glide.with(mContext).load(item.getLogo_url())
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource,
                                                GlideAnimation<? super GlideDrawable> glideAnimation) {
                        imageTextButton.setImageDrawable(resource);
                    }
                });
        final ImageView iv_checked = helper.getView(R.id.iv_checked);
        //第一次，map不包含ID，才加入map

            if (item.isIs_checked()){
                isChecked.put(item.getPlatform_id(), true);
                iv_checked.setVisibility(View.VISIBLE);
            }else {
                iv_checked.setVisibility(View.GONE);
            }


        imageTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setIs_checked(!item.isIs_checked());
                if (item.isIs_checked()) {
                    iv_checked.setVisibility(View.VISIBLE);
                } else {
                    iv_checked.setVisibility(View.GONE);
                }

                isChecked.put(item.getPlatform_id(), item.isIs_checked());
            }
        });

    }

}
