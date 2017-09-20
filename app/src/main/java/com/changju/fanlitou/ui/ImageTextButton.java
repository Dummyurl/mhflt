package com.changju.fanlitou.ui;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.changju.fanlitou.R;
import com.changju.fanlitou.util.NormalUtils;

public class ImageTextButton extends LinearLayout {

    private View root;
    // 上面的img
    private ImageView iv;
    // img下面的text
    private TextView tv;
    // 上面的图像资源Id
    private Drawable img;
    // 文字内容
    private String text;

    private LinearLayout layout;

    public ImageTextButton(Context context) {
        super(context);
        initView(context);
    }

    public ImageTextButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.imageTextButton);
        img = ta.getDrawable(R.styleable.imageTextButton_img);
        text = ta.getString(R.styleable.imageTextButton_text);
        initView(context);
        ta.recycle();
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        root = inflater.inflate(R.layout.image_text_buttton, this, true);

        iv = (ImageView) root.findViewById(R.id.iv);
        tv = (TextView) root.findViewById(R.id.tv);

        layout = (LinearLayout) root.findViewById(R.id.layout_image_text);
        //将自定义属性的值传递给对应View
        iv.setImageDrawable(img);
        tv.setText(text);
    }

    /**
     * @param resId
     */
    public void setImageResource(int resId) {
        iv.setImageResource(resId);
    }

    public void setImageDrawable(Drawable drawable){
        iv.setImageDrawable(drawable);
    }

    /**
     * @param text
     */
    public void setTextViewText(String text) {
        tv.setText(text);
    }

    /**
     * @param color
     */
    public void setTextColor(int color) {
        tv.setTextColor(color);
    }

    /**
     * 文字大小
     * @param size 单位sp
     */
    public void setTextSize(float size){
//        tv.setTextSize(NormalUtils.sp2px(context,size));
        tv.setTextSize(size);
    }

    public void isHot(boolean isHot){
        if (isHot){
            //左侧热门标签
            Drawable drawable = getResources().getDrawable(R.mipmap.favorite_hot_tag);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tv.setCompoundDrawables(drawable,null,null,null);
            tv.setCompoundDrawablePadding(4);
        }
    }

    /**
     * 图片宽高
     * @param context
     * @param width 单位dp
     * @param height 单位dp
     */
    public void setImageSize(Context context,float width,float height){
        LayoutParams para = (LayoutParams) iv.getLayoutParams();
        para.width = NormalUtils.dp2px(context,width);
        para.height = NormalUtils.dp2px(context,height);
        iv.setLayoutParams(para);
    }

    public void setLayoutPxSize(int width){
        LayoutParams para = (LayoutParams) layout.getLayoutParams();
        para.width = width;
        para.height = LayoutParams.MATCH_PARENT;
        layout.setLayoutParams(para);
    }

    public void setTextMargin(int marginTop){
        LayoutParams para = (LayoutParams) tv.getLayoutParams();
        para.setMargins(0,marginTop,0,0);
        tv.setLayoutParams(para);
    }

}

