package com.changju.fanlitou.ui.dialog;

/**
 * Created by chengww on 2017/5/19.
 * 加载动画弹窗
 */

import android.app.Dialog;
import android.content.Context;

import com.changju.fanlitou.R;


/**
 * 自定义加载动画dialog
 */
public class AnimDialog extends Dialog {


    public AnimDialog(Context context) {
        super(context);
    }

    public AnimDialog(Context context, int themeResId) {
        super(context, themeResId);
    }
    //显示dialog的方法
    public static AnimDialog showDialog(Context context){
        AnimDialog dialog = new AnimDialog(context, R.style.transparentDialog);
        dialog.setContentView(R.layout.view_loading);//dialog布局文件
        dialog.setCanceledOnTouchOutside(false);//点击外部不允许关闭dialog
        dialog.setOnCancelListener(null);
        return dialog;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

    }

}
