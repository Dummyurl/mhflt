package com.changju.fanlitou.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
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
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.intelligent.RechargeActivity;
import com.changju.fanlitou.adapter.CanBindTwiceAdapter;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.MyApplication;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.fund.FundList;
import com.changju.fanlitou.bean.fund.FundRedeemBean;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.UserUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chengww on 2017/7/10.
 */

public class RedeemFeeDialog extends DialogFragment implements View.OnClickListener {

    private Activity activity;
    private FundRedeemBean.FeeInfoBean.RedeemDataBean redeem_data;

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

        dialog.setContentView(R.layout.dialog_redeem_fee);

        Bundle bundle = getArguments();
        if (bundle != null) {
            redeem_data =(FundRedeemBean.FeeInfoBean.RedeemDataBean) bundle.get("redeem_data");
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
        RecyclerView redeem_fee_rcv = (RecyclerView) dialog.findViewById(R.id.dialog_redeem_fee_rcv);
        dialog.findViewById(R.id.iv_close).setOnClickListener(this);
        redeem_fee_rcv.setLayoutManager(new WrapContentLinearLayoutManager(activity));
        SimplePopAdapter adapter = new SimplePopAdapter(redeem_data.getData_list());
        adapter.bindToRecyclerView(redeem_fee_rcv);
        TextView text = new TextView(activity);
        text.setText("暂无数据");
        text.setGravity(Gravity.CENTER);
        text.setPadding(40,40,40,40);
        text.setTextSize(12);
        adapter.setEmptyView(text);
    }

    class SimplePopAdapter extends CanBindTwiceAdapter<FundRedeemBean.FeeInfoBean.RedeemDataBean.DataListBeanX, BaseViewHolder> {

        public SimplePopAdapter(@Nullable List data) {
            super(R.layout.recycler_item_redeem_fee, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, FundRedeemBean.FeeInfoBean.RedeemDataBean.DataListBeanX item ) {
           helper.setText(R.id.redeem_rate_tv,item.getRedeem_rate()).setText(R.id.hold_duration_tv,item.getHold_duration());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close:
                dismiss();
                break;
        }
    }

}