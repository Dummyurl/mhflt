package com.changju.fanlitou.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.changju.fanlitou.R;
import com.changju.fanlitou.util.NormalUtils;

/**
 * Created by chengww on 2017/6/24.
 *
 */

public class ImageDialog extends DialogFragment implements View.OnClickListener {

    private Activity context;

    public static final String TITLE = "title";
    public static final String CONTENT = "content";

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(context, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定

        dialog.setContentView(R.layout.dialog_img);
        initView(dialog);

        dialog.setCanceledOnTouchOutside(true); // 外部点击取消

        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT; // 宽度持平
        lp.gravity = Gravity.CENTER;
        window.setAttributes(lp);


        Bundle bundle = getArguments();

        if (bundle != null) {
            int width = (int) (NormalUtils.getScreenWidth(context)*0.8);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            iv_content.setLayoutParams(params);
            Glide.with(context).load(bundle.getString(CONTENT)).into(iv_content);
        }

        return dialog;
    }

    private ImageView iv_content;

    private void initView(Dialog dialog) {
        iv_content = (ImageView) dialog.findViewById(R.id.iv_content);
        LinearLayout layout_frame = (LinearLayout) dialog.findViewById(R.id.layout_frame);
        iv_content.setOnClickListener(this);
        layout_frame.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_content:
            case R.id.layout_frame:
                dismiss();
                break;
        }
    }
}
