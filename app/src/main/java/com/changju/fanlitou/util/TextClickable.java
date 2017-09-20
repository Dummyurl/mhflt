package com.changju.fanlitou.util;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * Created by chengww on 2017/8/13.
 */

public class TextClickable extends ClickableSpan {
    private final View.OnClickListener mListener;

    public TextClickable(View.OnClickListener l) {
        mListener = l;
    }

    /**
     * 重写父类点击事件
     */
    @Override
    public void onClick(View v) {
        mListener.onClick(v);
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setUnderlineText(false);// 设置文字下划线不显示
        ds.setColor(Color.parseColor("#4a8fec"));// 设置字体颜色
        ds.clearShadowLayer();
    }
}
