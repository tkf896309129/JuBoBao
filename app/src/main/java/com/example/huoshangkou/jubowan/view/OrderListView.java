package com.example.huoshangkou.jubowan.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.view
 * 类名：OrderListView
 * 描述：
 * 创建时间：2017-04-07  09:55
 */

public class OrderListView extends ListView {
    public OrderListView(Context context) {
        super(context);
    }

    public OrderListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OrderListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }


    float x;
    float y;
    float offsetX;
    float offsetY;

    private float mDownX;
    private float mDownY;

    int mHeaderViewWidth;
    int mHeaderViewHeight;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        mHeaderViewWidth = PinnedHeaderExpandableListView.getmHeaderViewWidth();
        mHeaderViewHeight = PinnedHeaderExpandableListView.getmHeaderViewHeight();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = ev.getX();
                mDownY = ev.getY();
                if (mDownX <= mHeaderViewWidth && mDownY <= mHeaderViewHeight) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                x = ev.getX();
                y = ev.getY();
                offsetX = Math.abs(x - mDownX);
                offsetY = Math.abs(y - mDownY);

                LogUtils.i( x + "  " + y + "  " + offsetX + "  " + offsetY + "  " + mHeaderViewWidth + "  " + mHeaderViewHeight);
                // 如果 HeaderView 是可见的 , 点击在 HeaderView 内 , 那么触发 headerClick()
                if (x <= mHeaderViewWidth && y <= mHeaderViewHeight
                        && offsetX <= mHeaderViewWidth && offsetY <= mHeaderViewHeight) {
                    ToastUtils.getMineToast("点击");
                    return false;
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
//        return false;
    }


}
