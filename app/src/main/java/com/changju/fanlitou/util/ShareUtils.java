package com.changju.fanlitou.util;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

/**
 * Created by chengww on 2017/8/25.
 */

public class ShareUtils {
    public static void startShare(final Activity context, final String shareUrl, String title, String description, final String image_url) {
        final UMWeb web = new UMWeb(shareUrl);

        web.setTitle(title);//标题
        UMImage image = new UMImage(context, image_url);//网络图片
        web.setThumb(image);
        web.setDescription(description);//描述

        final UMShareAPI mShareAPI = UMShareAPI.get(context);

        ShareBoardConfig config = new ShareBoardConfig();//新建ShareBoardConfig               config.setShareboardPostion(ShareBoardConfig.SHAREBOARD_POSITION_CENTER);//设置位置
        config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_ROUNDED_SQUARE);
        config.setCancelButtonVisibility(true).setTitleVisibility(false)
                .setIndicatorVisibility(false)
                .setShareboardBackgroundColor(Color.parseColor("#FFFFFF"))
                .setMenuItemTextColor(Color.parseColor("#666666"))
                .setCancelButtonVisibility(false);

        new ShareAction(context).setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.SINA)
                .addButton("umeng_sharebutton_custom", "umeng_sharebutton_custom", "ic_copy_url", "ic_copy_url")// 自定义按钮
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {

                        //根据key来区分自定义按钮的类型，并进行对应的操作
                        if (snsPlatform.mKeyword.equals("umeng_sharebutton_custom")) {
                            //获取剪贴板管理器：
                            ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                            // 创建普通字符型ClipData
                            ClipData mClipData = ClipData.newPlainText("shareUrl", shareUrl);
                            // 将ClipData内容放到系统剪贴板里。
                            cm.setPrimaryClip(mClipData);
                            NormalUtils.showToast(context, "复制链接成功");
                        } else if ((share_media.equals(SHARE_MEDIA.WEIXIN) ||
                                share_media.equals(SHARE_MEDIA.WEIXIN_CIRCLE)) &&
                                        !mShareAPI.isInstall(context, SHARE_MEDIA.WEIXIN)) {
                            NormalUtils.showToast(context, "您暂未安装微信");
                        } else if (share_media.equals(SHARE_MEDIA.QQ) &&
                                !mShareAPI.isInstall(context, SHARE_MEDIA.QQ)) {
                            NormalUtils.showToast(context, "您暂未安装QQ");

                        } else {//社交平台的分享行为
                            new ShareAction(context)
                                    .setPlatform(share_media)
                                    .setCallback(new UMShareListener() {
                                        @Override
                                        public void onStart(SHARE_MEDIA share_media) {

                                        }

                                        @Override
                                        public void onResult(SHARE_MEDIA share_media) {

                                        }

                                        @Override
                                        public void onError(SHARE_MEDIA share_media, Throwable throwable) {

                                        }

                                        @Override
                                        public void onCancel(SHARE_MEDIA share_media) {

                                        }
                                    })
                                    .withMedia(web)
                                    .share();
                        }

                    }
                })//面板点击监听器;
                .open(config);//传入分享面板中);
    }
}
