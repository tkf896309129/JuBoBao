package com.example.huoshangkou.jubowan.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.view
 * 类名：ForeverMarque
 * 描述：
 * 创建时间：2017-11-16  10:21
 */

public class ForeverMarque extends TextView  {

    private int currentScrollX;//当前滚动的位置
    private boolean isStop = false;
    private int textWidth;
    private boolean isMeasure = false;

    public ForeverMarque(Context context) {
        super(context);
    }

    public ForeverMarque(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ForeverMarque(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction,
                                  Rect previouslyFocusedRect) {
    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        // TODO Auto-generated method stub
//        super.onDraw(canvas);
//        if (!isMeasure) {//文字宽度只需要获取一次就可以了
//            getTextWidth();
//            isMeasure = true;
//        }
//    }
//
//
//    /**
//     * 获取文字宽度
//     */
//    private void getTextWidth() {
//        Paint paint = this.getPaint();
//        String str = this.getText().toString();
//        textWidth = (int) paint.measureText(str);
//    }
//
//    @Override
//    public void run() {
//        // TODO Auto-generated method stub
//        currentScrollX -= 2;  // Rolling speed
//        scrollTo(currentScrollX, 0);
//        if (isStop) {
//            return;
//        }
//        if (getScaleX() <= -(this.getWidth())) {
//            scrollTo(textWidth, 0);
//            currentScrollX = textWidth;
//            //return;
//        }
//        postDelayed(this, 5);
//    }
//
//    // start
//    public void startScroll() {
//        isStop = false;
//        this.removeCallbacks(this);
//        post(this);
//    }
//
//    // stop
//    public void stopScroll() {
//        isStop = true;
//    }
//
//    // Start from scratch
//    public void startFor0() {
//        currentScrollX = 0;
//        startScroll();
//    }
}
