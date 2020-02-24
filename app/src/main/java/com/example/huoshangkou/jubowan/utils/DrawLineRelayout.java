package com.example.huoshangkou.jubowan.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.huoshangkou.jubowan.R;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：DrawLineRelayout
 * 描述：
 * 创建时间：2018-06-07  09:07
 */

public class DrawLineRelayout extends RelativeLayout {


    public DrawLineRelayout(Context context) {
        super(context);
    }

    public DrawLineRelayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        Paint paint = new Paint();
        Canvas canvas = new Canvas();
        paint.setColor(context.getResources().getColor(R.color.gray));
        int[] location = new int[2];
        getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        canvas.drawLine(110, 40, 190, 80, paint);// 斜线
        LogUtils.i("测试："+x + "  " + y + "  " + getLeft() + "  " + getTop() + "  " + getRight() + "  " + getBottom());
    }

}
