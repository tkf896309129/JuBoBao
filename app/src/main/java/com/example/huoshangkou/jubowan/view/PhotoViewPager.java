package com.example.huoshangkou.jubowan.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.view
 * 类名：PhotoViewPager
 * 描述：解决连续缩放photoview报错问题
 * 主要原因是viewpager和photoview冲突
 * 解决方法:重写viewpager的onInterceptTouchEvent()和onTouchEvent()方法,将其try{}catch(){}
 * 创建时间：2017-01-05  13:25
 */

public class PhotoViewPager extends ViewPager {

    private boolean isLocked;

    public PhotoViewPager(Context context) {
        super(context);
        isLocked = false;
    }

    public PhotoViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        isLocked = false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!isLocked) {
            try {
                return super.onInterceptTouchEvent(ev);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return !isLocked && super.onTouchEvent(event);
    }

    public void toggleLock() {
        isLocked = !isLocked;
    }

    public void setLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public boolean isLocked() {
        return isLocked;
    }

}
