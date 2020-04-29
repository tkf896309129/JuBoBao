package com.example.huoshangkou.jubowan.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.widget.LinearLayout;
import android.util.AttributeSet;

import com.example.huoshangkou.jubowan.R;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：BackgroundLayout
 * 描述：
 * 创建时间：2020-03-11  08:58
 */

public class BackgroundLayout extends LinearLayout{

    private float mCornerRadius;
    private int mBackgroundColor;

    public BackgroundLayout(Context context) {
        super(context);
        init();
    }

    public BackgroundLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public BackgroundLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressWarnings("deprecation")
    private void init() {
        int color = getContext().getResources().getColor(R.color.alpha_black);
        initBackground(color, mCornerRadius);
    }

    private void initBackground(int color, float cornerRadius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setColor(color);
        drawable.setCornerRadius(cornerRadius);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(drawable);
        } else {
            setBackgroundDrawable(drawable);
        }
    }

    public void setCornerRadius(float radius) {
        mCornerRadius = ScaleUtils.dip2px(radius);
        initBackground(mBackgroundColor, mCornerRadius);
    }

    public void setBaseColor(int color) {
        mBackgroundColor = color;
        initBackground(mBackgroundColor, mCornerRadius);
    }

    //正方形显示
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int size = Math.max(width, height);
        setMeasuredDimension(size, size);
    }


}
