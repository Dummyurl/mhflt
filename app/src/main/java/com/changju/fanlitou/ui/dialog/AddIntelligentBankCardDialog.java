package com.changju.fanlitou.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.intelligent.OpenAccountActivity;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.intelligent.OpenAccountInfo;

import java.util.List;

/**
 * Created by chengww on 2017/7/7.
 */

public class AddIntelligentBankCardDialog extends DialogFragment {

    private OpenAccountActivity context;

    private static List<OpenAccountInfo.BankListBean> infoBanks;

    public static AddIntelligentBankCardDialog newInstance(
            List<OpenAccountInfo.BankListBean> infoBanks) {
        AddIntelligentBankCardDialog.infoBanks = infoBanks;

        return new AddIntelligentBankCardDialog();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = (OpenAccountActivity) activity;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定

        dialog.setContentView(R.layout.dialog_add_bank_card);
        initView(dialog);

        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.gravity = Gravity.BOTTOM;
        window.setAttributes(lp);
        dialog.setCanceledOnTouchOutside(true);

        return dialog;
    }

    private void initView(Dialog dialog) {
        RecyclerView mRecyclerView = (RecyclerView)
                dialog.findViewById(R.id.recycler_add_bank);
        mRecyclerView.setLayoutManager(
                new WrapContentLinearLayoutManager(context));

        BankCardAdapter adapter = new BankCardAdapter(infoBanks);
        adapter.bindToRecyclerView(mRecyclerView);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                context.setResultFinish(null,null,infoBanks.get(position));
                dismiss();
            }
        });

    }

    private class BankCardAdapter extends BaseQuickAdapter<OpenAccountInfo.BankListBean,BaseViewHolder>{

        BankCardAdapter(@Nullable List<OpenAccountInfo.BankListBean> data) {
            super(R.layout.recycler_item_add_bank_card, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, OpenAccountInfo.BankListBean item) {
            helper.setText(R.id.tv_bank_title,item.getBank_name());
            ImageView logo = helper.getView(R.id.iv_logo);
            Glide.with(context).load(item.getBank_logo()).into(logo);
        }
    }
}