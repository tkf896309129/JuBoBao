package com.example.huoshangkou.jubowan.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.huoshangkou.jubowan.R;

public class ContactListViewImpl extends ContactListView {

    public ContactListViewImpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void createScroller() {

        mScroller = new IndexScroller(getContext(), this);
        mScroller.setAutoHide(autoHide);
        // style 1
        // mScroller.setShowIndexContainer(false);
        // mScroller.setIndexPaintColor(Color.argb(255, 49, 64, 91));
        // style 2
        mScroller.setShowIndexContainer(true);
        mScroller.setIndexPaintColor(getResources().getColor(R.color.main_tab_blue_gray));
        if (autoHide)
            mScroller.hide();
        else
            mScroller.show();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
