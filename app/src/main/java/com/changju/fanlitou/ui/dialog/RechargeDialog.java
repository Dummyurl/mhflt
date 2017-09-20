package com.changju.fanlitou.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.intelligent.RechargeActivity;
import com.changju.fanlitou.base.JavascriptClass;
import com.changju.fanlitou.base.LazyFragment;

/**
 * Created by chengww on 2017/7/10.
 */

public class RechargeDialog extends DialogFragment implements View.OnClickListener {

    private Activity activity;

    private String rechargeCount;
    private int bid_id;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(activity, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定

        dialog.setContentView(R.layout.dialog_recharge);

        Bundle bundle = getArguments();
        if (bundle != null) {
            rechargeCount = bundle.getString(RechargeActivity.class.getSimpleName());
            bid_id = bundle.getInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
        }

        initView(dialog);

        dialog.setCanceledOnTouchOutside(true); // 允许外部点击取消

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


    private void initView(Dialog dialog) {
        TextView tv_content = (TextView) dialog.findViewById(R.id.tv_content);
        tv_content.setText(rechargeCount);
        TextView tv_cancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        dialog.findViewById(R.id.tv_charge).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_charge:
                Bundle args = new Bundle();
                args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,bid_id);
                Intent intent = new Intent(activity,RechargeActivity.class);
                intent.putExtras(args);
                startActivityForResult(intent, JavascriptClass.REQUEST_RECHARGE);
                dismiss();
                break;
        }
    }

}