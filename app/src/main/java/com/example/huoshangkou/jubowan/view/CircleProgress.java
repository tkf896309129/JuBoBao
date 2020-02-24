package com.example.huoshangkou.jubowan.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.huoshangkou.jubowan.R;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.view
 * 类名：CircleProgress
 * 描述：
 * 创建时间：2017-03-28  17:21
 */

public class CircleProgress extends View {


    private int max = 100;
    private int progress = 50;
    private Paint mPaint;

    private int cirlceWidth;
    private int circleBgColor;
    private int circleColor;


    public CircleProgress(Context context) {
        this(context, null);
    }

    public CircleProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void init(AttributeSet attrs) {
        if (attrs != null) {

        }
        cirlceWidth = 5;
        circleBgColor = getResources().getColor(R.color.gray_bg);
        circleColor = getResources().getColor(R.color.main_tab_blue);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(cirlceWidth);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int radiu = Math.min(width, height) / 2;
        mPaint.setColor(circleBgColor);
        canvas.drawCircle(width / 2, height / 2, radiu - cirlceWidth / 2, mPaint);
        mPaint.setColor(circleColor);
        canvas.drawArc(new RectF(width / 2 - radiu + cirlceWidth / 2, height / 2 - radiu + cirlceWidth / 2, width / 2 + radiu - cirlceWidth / 2, height / 2 + radiu - cirlceWidth / 2), -90, progress * 360 / max, false, mPaint);
    }
    public void setMax(int max) {
        this.max = max;
    }
    public synchronized void setProgress(int progress) {
        this.progress = progress;
        postInvalidate();
    }


}
