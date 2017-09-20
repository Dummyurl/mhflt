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

import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.mine.InvestRecordActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.MyApplication;
import com.changju.fanlitou.bean.mine.TicketBean;
import com.changju.fanlitou.bean.mine.TicketsResultBean;
import com.changju.fanlitou.ui.WheelView;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.changju.fanlitou.util.UserUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chengww on 2017/6/22.
 */

public class TicketsDialog extends DialogFragment implements View.OnClickListener {

    private Activity context;

    private AnimDialog mAnimDialog;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

    private List<TicketBean> tickets;

    private WheelView wheelView;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定

        Bundle bundle = getArguments();

        if (bundle != null) {
            tickets = bundle.getParcelableArrayList(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
        }

        dialog.setContentView(R.layout.dialog_ticket_layout);
        initView(dialog);

        dialog.setCanceledOnTouchOutside(false); // 外部点击取消

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
        dialog.findViewById(R.id.close_dialog).setOnClickListener(this);
        dialog.findViewById(R.id.nativeButton).setOnClickListener(this);
        dialog.findViewById(R.id.positiveButton).setOnClickListener(this);
        wheelView = (WheelView) dialog.findViewById(R.id.loop_view);

        List<String> names = new ArrayList<>();

        if (tickets != null && tickets.size() > 0) {
            for (TicketBean bean : tickets) {
                names.add(bean.getName());
            }
        }

        wheelView.setItems(names);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close_dialog:
            case R.id.nativeButton:
                dismiss();
                break;
            case R.id.positiveButton:
                if (mAnimDialog == null)
                    mAnimDialog = AnimDialog.showDialog(context);

                mAnimDialog.show();

                int pos = wheelView.getSeletedIndex();

                String time = String.valueOf(System.currentTimeMillis());
                String random = ApiUtils.getRandom();

                OkGo.post(ApiUtils.postApplyRaiseInterestTicket())
                        .params("t", time)
                        .params("random", random)
                        .params("sign", NormalUtils.md5(time + random + MyApplication.key))
                        .params("raise_interest_para",
                                String.valueOf(tickets.get(pos).getTicket_id()) +
                                        ":" + String.valueOf(tickets.get(pos).getOrder_id()))
                        .params("user_name", UserUtils.getUserName(context))
                        .params("login_token", UserUtils.getLoginToken(context))
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                TicketsResultBean result = ParseUtils.parseByGson(s,TicketsResultBean.class);

                                if (result.isSuccess()){
                                    ((InvestRecordActivity)context).useTicketAndSetData(result.getMessage());
                                }else {
                                    NormalUtils.showToast(context,result.getMessage());
                                }
                                mAnimDialog.dismiss();
                                dismiss();
                            }

                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                super.onError(call, response, e);
                                NormalUtils.showToast(context,R.string.net_error);
                                mAnimDialog.dismiss();
                                dismiss();
                            }
                        });
                break;

        }
    }
}
