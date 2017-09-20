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
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.MainActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.bean.home.MainMenu;

/**
 * Created by chengww on 2017/6/15.
 * 首页活动button
 */

public class DialogMainActivity extends DialogFragment implements View.OnClickListener {

    private MainMenu menu;

    private MainActivity context;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = (MainActivity) activity;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(context, R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定

        Bundle bundle = getArguments();
        if (bundle != null) {
            menu = bundle.getParcelable(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
        }

        dialog.setContentView(R.layout.dialog_activity_main);
        initView(dialog);

        dialog.setCanceledOnTouchOutside(true); // 外部点击取消

        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.gravity = Gravity.BOTTOM;
        window.setAttributes(lp);

        return dialog;
    }


    private ImageView iv_one,iv_two;
    private TextView tv_one,tv_two;
    private LinearLayout layout_one,layout_two;

    private void initView(Dialog dialog) {
        dialog.findViewById(R.id.iv_close).setOnClickListener(this);
        dialog.findViewById(R.id.layout_root).setOnClickListener(this);
        iv_one = (ImageView) dialog.findViewById(R.id.iv_one);
        iv_two = (ImageView) dialog.findViewById(R.id.iv_two);
        tv_one = (TextView) dialog.findViewById(R.id.tv_one);
        tv_two = (TextView) dialog.findViewById(R.id.tv_two);

        layout_one = (LinearLayout) dialog.findViewById(R.id.layout_one);
        layout_one.setOnClickListener(this);
        layout_two = (LinearLayout) dialog.findViewById(R.id.layout_two);
        layout_two.setOnClickListener(this);

        if (menu != null){
            if (menu.isIs_show()){
                switch (menu.getMenu_type()){
                    case "single":
                        layout_two.setVisibility(View.GONE);
                        Glide.with(context).
                                load(menu.getActivity_list().get(0).getActivity_logo())
                                .into(iv_one);
                        tv_one.setText(menu.getActivity_list().get(0).getName());
                        break;
                    case "multiple":
                        Glide.with(context).
                                load(menu.getActivity_list().get(0).getActivity_logo())
                                .into(iv_one);
                        tv_one.setText(menu.getActivity_list().get(0).getName());
                        Glide.with(context).
                                load(menu.getActivity_list().get(1).getActivity_logo()).into(iv_two);
                        tv_two.setText(menu.getActivity_list().get(1).getName());
                        break;
                }
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_root:
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.layout_one:

                if (menu == null)
                    return;

                context.showActivity(menu.getActivity_list().get(0).getActivity_url());
                dismiss();
                break;
            case R.id.layout_two:
                if (menu == null)
                    return;

                context.showActivity(menu.getActivity_list().get(1).getActivity_url());
                dismiss();
                break;
        }
    }
}
