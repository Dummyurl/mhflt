package com.changju.fanlitou.ui;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.changju.fanlitou.R;

/**
 * Created by chengww on 2017/6/10.
 */

public class EditTextWithSearchAndDel extends android.support.v7.widget.AppCompatEditText {
    private Drawable imgAble;
    private Drawable imaSearch;
    private Context mContext;

    public EditTextWithSearchAndDel(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public EditTextWithSearchAndDel(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        init();
    }

    public EditTextWithSearchAndDel(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        imgAble = mContext.getResources().getDrawable(R.mipmap.ic_delete);
        imaSearch = mContext.getResources().getDrawable(R.mipmap.ic_search);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable editable) {
                setDrawable();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        });
        setDrawable();
        setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                EditText editText = (EditText)v;
                String hint;
                if (hasFocus) {
                    hint = editText.getHint().toString();
                    editText.setTag(hint);
                    editText.setHint("");
                } else {
                    hint = editText.getTag().toString();
                    editText.setHint(hint);
                }

            }
        });
    }

    //设置删除图片
    private void setDrawable() {
        if(length() < 1)
            setCompoundDrawablesWithIntrinsicBounds(imaSearch, null, null, null);
        else
            setCompoundDrawablesWithIntrinsicBounds(imaSearch, null, imgAble, null);
    }

    // 处理删除事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (imgAble != null && event.getAction() == MotionEvent.ACTION_UP) {
            int eventX = (int) event.getRawX();
            int eventY = (int) event.getRawY();
            Rect rect = new Rect();
            getGlobalVisibleRect(rect);
            rect.left = rect.right - 50;
            if(rect.contains(eventX, eventY))
                setText("");
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

}
