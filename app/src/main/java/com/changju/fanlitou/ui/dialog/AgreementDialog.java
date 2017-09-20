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
import android.widget.TextView;

import com.changju.fanlitou.R;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

/**
 * Created by chengww on 2017/7/7.
 */

public class AgreementDialog extends DialogFragment implements View.OnClickListener {

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

        dialog.setContentView(R.layout.dialog_agreement);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消



        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.gravity = Gravity.CENTER;
        window.setAttributes(lp);

        initView(dialog);

        Bundle bundle = getArguments();
        if (bundle != null) {
            tv_title.setText(bundle.getString(TITLE));
            initWebView(bundle.getString(CONTENT));
        }

        return dialog;
    }

    private TextView tv_title;
    private WebView webView;

    private void initView(Dialog dialog) {
        dialog.findViewById(R.id.iv_close).setOnClickListener(this);
        tv_title = (TextView) dialog.findViewById(R.id.tv_title);

        webView = (WebView) dialog.findViewById(R.id.web_view);
    }

    private void initWebView(String content) {
        StringBuilder sb = new StringBuilder();
        //拼接一段HTML代码
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>协议</title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append(content.replace("\n","<br>"));
        sb.append("</body>");
        sb.append("</html>");
        //使用简单的loadData方法会导致乱码，可能是Android API的Bug
        //show.loadData(sb.toString(), "text/html", "utf-8");
        webView.getSettings().setTextSize(WebSettings.TextSize.SMALLER);
        //加载、并显示HTML代码
        webView.loadDataWithBaseURL(null, sb.toString(), "text/html", "utf-8", null);
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