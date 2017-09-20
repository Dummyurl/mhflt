package com.changju.fanlitou.ui;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.changju.fanlitou.R;

/**
 * Created by chengww on 2017/6/18.
 */

public class DrawableAlignedButton extends RelativeLayout {

    private View view;
    private TextView button;

    /**
     * @param context
     *          used to inflate the View.
     * @param attrs
     *          XML defined attributes.
     */
    public DrawableAlignedButton(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /**
     * @param context
     *          used to inflate the View.
     */
    public DrawableAlignedButton(final Context context) {
        super(context);
        init(context, null);
    }

    /**
     * @param context
     *          used to inflate the View.
     * @param attrs
     *          XML defined attributes.
     * @param style
     *          the style for the View.
     */
    public DrawableAlignedButton(final Context context, final AttributeSet attrs, final int style) {
        super(context, attrs, style);
        init(context, attrs);
    }

    private void init(final Context context, final AttributeSet attributeSet) {
        view = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.button, this, true);
        button = (TextView) view.findViewById(R.id.custom_button);

        String buttonText = null;
        int drawableEnd = 0;

        if (attributeSet != null) {
            final TypedArray a = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.CustomButtonStyle, 0, 0);
            buttonText = a.getString(R.styleable.CustomButtonStyle_Text);
            drawableEnd = a.getResourceId(R.styleable.CustomButtonStyle_DrawableEnd, 0);
            a.recycle();
        }


        if (buttonText != null) {
            button.setText(buttonText);
        }

        button.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, drawableEnd, 0);
    }

    /**
     * Sets the button text.
     *
     * @param text
     *          the text to be set.
     */
    public void setButtonText(final String text) {
        if (button != null) {
            button.setText(text);
        }
    }

    /**
     * Sets the drawable to the button.
     * @param drawableEnd
     *          the drawable to set at the end of the text.
     */
    public void setDrawableEnd(final int drawableEnd) {
        if (button != null) {
            button.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, drawableEnd, 0);
        }
    }

    public void setText(CharSequence text){
        if (button != null) {
            button.setText(text);
        }
    }

    public void setTextColor(int colorResId){
        if (button != null) {
            button.setTextColor(getResources().getColor(colorResId));
        }
    }

    public String getText(){
        if (button != null)
            return button.getText().toString();
        return "";
    }
}
