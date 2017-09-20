package com.changju.fanlitou.refreshview.view;

import android.content.Context;
import android.util.AttributeSet;
import com.tencent.smtt.sdk.WebView;

/**
 * Created by 2144 on 2017/4/25.
 */

public class XWebView extends WebView {

    public XWebView(Context context) {
        super(context);
    }

    public XWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public XWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean isBottom() {
        return computeVerticalScrollRange() == getHeight() + getScrollY();
    }

    @Override
    public int computeVerticalScrollRange() {
        return super.computeVerticalScrollRange();
    }
}
