package com.changju.fanlitou.util;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.changju.fanlitou.R;

/**
 * Created by chengww on 2017/8/28.
 */

public class TagUtils {
    public static TextView createTextTag(Context mContext,String style, String name) {
        TextView textView = new TextView(mContext);
        textView.setText(name);
        textView.setTextSize(12);
        textView.setPadding(NormalUtils.dp2px(mContext, 5),
                NormalUtils.dp2px(mContext, 2),
                NormalUtils.dp2px(mContext, 5),
                NormalUtils.dp2px(mContext, 2));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        params.setMargins(NormalUtils.dp2px(mContext, 3),
                0, NormalUtils.dp2px(mContext, 3), 0);
        textView.setLayoutParams(params);

        switch (style){
            default:
            case "blue":
                textView.setTextColor(mContext.getResources().getColor(R.color.colorTextBlue));
                textView.setBackgroundResource(R.drawable.shape_tag_blue);
                break;
            case "red":
                textView.setTextColor(mContext.getResources().getColor(R.color.colorTextRed));
                textView.setBackgroundResource(R.drawable.shape_tag_red);
                break;
            case "yellow":
                textView.setTextColor(mContext.getResources().getColor(R.color.colorTextYellow));
                textView.setBackgroundResource(R.drawable.shape_tag_yellow);
                break;
        }

        return textView;
    }
}
