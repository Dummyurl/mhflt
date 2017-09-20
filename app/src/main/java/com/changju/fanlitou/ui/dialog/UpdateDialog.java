package com.changju.fanlitou.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NotificationCompat;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.MainActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.bean.Update;
import com.changju.fanlitou.util.NormalUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.request.BaseRequest;

import java.io.File;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chengww on 2017/6/26.
 */

public class UpdateDialog extends DialogFragment implements View.OnClickListener {

    private MainActivity activity;
    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder;

    private Update update;
    private TextView tv_content;
    private TextView tv_title;
    private TextView tv_update_now;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (MainActivity) activity;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(activity, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定

        dialog.setContentView(R.layout.dialog_update);

        Bundle bundle = getArguments();
        if (bundle != null) {
            update = bundle.getParcelable(
                    LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
        }

        initView(dialog);

        dialog.setCanceledOnTouchOutside(false); // 不允许外部点击取消
        //返回键不消失dialog
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return keyCode == KeyEvent.KEYCODE_BACK;
            }
        });

        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.gravity = Gravity.CENTER;
        window.setAttributes(lp);

        return dialog;
    }

    /**
     * 升级类型
     * 1:不需要升级
     * 2:可选升级
     * 3:必要升级
     */
    private int type;

    private LinearLayout layout_update_info;
    private TextView tv_update_info;
    private ProgressBar progressBar;

    private void initView(Dialog dialog) {
        tv_title = (TextView) dialog.findViewById(R.id.tv_title);
        tv_content = (TextView) dialog.findViewById(R.id.tv_content);
        TextView tv_exit = (TextView) dialog.findViewById(R.id.tv_exit);
        tv_exit.setOnClickListener(this);
        TextView tv_later_update = (TextView) dialog.findViewById(R.id.tv_later_update);
        tv_later_update.setOnClickListener(this);
        tv_update_now = (TextView) dialog.findViewById(R.id.tv_update_now);
        tv_update_now.setOnClickListener(this);

        if (update != null) {
            //标题
            tv_title.setText("发现新版本" + update.getVersion_name());
            //内容
            List<String> update_message_list = update.getUpdate_message_list();
            if (update_message_list != null && update_message_list.size() > 0) {
                tv_content.setText("");
                for (int i = 0; i < update_message_list.size(); i++) {
                    tv_content.append(update_message_list.get(i));
                    if (i < update_message_list.size() - 1)
                        tv_content.append("\n");
                }
            }

            //type
            type = update.getUpdate_type();
            //必要升级
            if (type == 3) {
                //必要升级进度
                layout_update_info = (LinearLayout) dialog.findViewById(R.id.layout_update_info);
                tv_update_info = (TextView) dialog.findViewById(R.id.tv_update_info);
                progressBar = (ProgressBar) dialog.findViewById(R.id.progress);

                tv_later_update.setVisibility(View.GONE);
                tv_exit.setVisibility(View.VISIBLE);

            } else {

                tv_later_update.setVisibility(View.VISIBLE);
                tv_exit.setVisibility(View.GONE);
            }
        }


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_exit:
                activity.finish();
                break;
            case R.id.tv_later_update:
                NormalUtils.setIgnoreUpdateTime(activity);
                NormalUtils.setIgnoreVersion(activity,update.getVersion_name());
                dismiss();
                break;
            case R.id.tv_update_now:
                doUpdate();
                break;
        }
    }

    private boolean isUpdateFirst = true;
    /**
     * 升级
     */
    private void doUpdate() {
        if (type == 3) {
            //必要升级 在弹窗内升级
            layout_update_info.setVisibility(View.VISIBLE);
            tv_content.setVisibility(View.GONE);

            tv_title.setText("正在更新返利投" + update.getVersion_name());
            tv_update_now.setText("正在升级");
            tv_update_now.setEnabled(false);
            OkGo.get(update.getUrl()).tag(update.getUrl()).execute(new FileCallback() {
                @Override
                public void onSuccess(final File file, Call call, Response response) {
                    installApk(file);
                    tv_update_info.setText("下载已完成，请点击安装。");
                    tv_update_info.setTextColor(getResources().getColor(R.color.colorTextBlue));
                    tv_update_info.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            installApk(file);
                        }
                    });
                }

                @Override
                public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                    super.downloadProgress(currentSize, totalSize, progress, networkSpeed);
                    int progressInt = ((int) progress) < 0 ? 0 : (int) progress;
                    progressBar.setProgress(progressInt);
                    tv_update_info.setText("正在升级" + progressInt + "%     " +
                            NormalUtils.getDataSize(currentSize) + "/" + NormalUtils.getDataSize(totalSize));
                }
            });
        } else {
            notificationDown();
            dismiss();
        }
    }

    private void notificationDown() {
        NormalUtils.showToast(activity,"开始下载");
        OkGo.get(update.getUrl()).tag(update.getUrl()).execute(new FileCallback() {
            @Override
            public void onBefore(BaseRequest request) {
                super.onBefore(request);
                //初始化notification
                mNotificationManager = (NotificationManager) activity.
                        getSystemService(android.content.Context.NOTIFICATION_SERVICE);
                mBuilder = new NotificationCompat.Builder(activity);
                mBuilder.setContentTitle("正在更新" + update.getVersion_name())
                        .setOngoing(true).setSmallIcon(R.mipmap.ic_launcher);
                mBuilder.setProgress(100,0,false);
                mBuilder.setContentText("开始下载");

                mNotificationManager.notify(0, mBuilder.build());
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                mBuilder.setContentText("下载失败，请稍后重试");
                mBuilder.setOngoing(false);
                mBuilder.setProgress(100,0,false);
//                    Intent i = new Intent(Intent.ACTION_VIEW);
//                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    i.setDataAndType(Uri.parse("file://" + file.toString()),
//                            "application/vnd.android.package-archive");
//                    PendingIntent pendingIntent = PendingIntent.getActivity(activity, 1000, i,
//                            PendingIntent.FLAG_UPDATE_CURRENT);
//                    mBuilder.setContentIntent(pendingIntent);
                Notification notification = mBuilder.build();
                notification.flags = Notification.FLAG_AUTO_CANCEL;
                mNotificationManager.notify(0, notification);
            }

            @Override
            public void onSuccess(File file, Call call, Response response) {
                mBuilder.setContentText("下载完成，请点击进行安装");
                mBuilder.setOngoing(false);
                mBuilder.setProgress(100,100,false);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setDataAndType(Uri.parse("file://" + file.toString()),
                        "application/vnd.android.package-archive");
                PendingIntent pendingIntent = PendingIntent.getActivity(activity, 1000, i,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                mBuilder.setContentIntent(pendingIntent);
                Notification notification = mBuilder.build();
                notification.flags = Notification.FLAG_ONLY_ALERT_ONCE;
                mNotificationManager.notify(0, notification);
                installApk(file);
            }

            @Override
            public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                super.downloadProgress(currentSize, totalSize, progress, networkSpeed);

                int progressInt = ((int) progress) < 0 ? 0 : (int) progress;
                if (isUpdateFirst && totalSize < 0) {
                    mBuilder.setProgress(100, 0, true);
                    isUpdateFirst = false;
                } else if (totalSize > 0){
                    mBuilder.setProgress(100, progressInt, false);
                }
                mBuilder.setContentText(NormalUtils.getDataSize(currentSize)
                        + "/" + NormalUtils.getDataSize(totalSize));
                mNotificationManager.notify(0, mBuilder.build());
            }
        });
    }

    /**
     * 安装APK文件
     */
    private void installApk(File apkFile) {
        if (!apkFile.exists()) {
            return;
        }
        // 通过Intent安装APK文件
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setDataAndType(Uri.parse("file://" + apkFile.toString()),
                "application/vnd.android.package-archive");
        activity.startActivity(i);
//        android.os.Process.killProcess(android.os.Process.myPid());
    }
}