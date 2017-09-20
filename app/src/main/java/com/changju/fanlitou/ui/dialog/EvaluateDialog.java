package com.changju.fanlitou.ui.dialog;

import android.app.Dialog;
import android.content.Context;

import com.changju.fanlitou.R;

/**
 * Created by chengww on 2017/8/14.
 */

public class EvaluateDialog extends Dialog {


    public EvaluateDialog(Context context) {
        super(context);
    }

    public EvaluateDialog(Context context, int themeResId) {
        super(context, themeResId);
    }
    //显示dialog的方法
    public static EvaluateDialog showDialog(Context context){
        EvaluateDialog dialog = new EvaluateDialog(context, R.style.customDialog);
        dialog.setContentView(R.layout.dialog_evaluate);//dialog布局文件
        dialog.setCanceledOnTouchOutside(false);//点击外部不允许关闭dialog
        dialog.setOnCancelListener(null);
        return dialog;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

    }

}
