package com.example.huoshangkou.jubowan.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：AnimUtils
 * 描述：
 * 创建时间：2020-04-08  13:39
 */

public class AnimUtils {

    private static AnimUtils instance = null;

    private int mHiddenViewMeasuredHeight;
    private float mDensity;

    public static AnimUtils getInstance() {
        if (instance == null) {
            synchronized (AnimUtils.class) {
                if (instance == null) {
                    instance = new AnimUtils();
                }
            }
        }
        return instance;
    }


    public AnimUtils getHeight(Context context,int height) {
        mDensity = context.getResources().getDisplayMetrics().density;
        mHiddenViewMeasuredHeight = (int) (mDensity * height + 0.5);
        return instance;
    }


    public void animateOpen(View v) {
        v.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(v, 0,
                mHiddenViewMeasuredHeight);
        animator.start();
    }

    public void animateClose(final View view) {
        int origHeight = view.getHeight();
        ValueAnimator animator = createDropAnimator(view, origHeight, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }

        });
        animator.start();
    }

    private ValueAnimator createDropAnimator(final View v, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator arg0) {
                int value = (int) arg0.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
                layoutParams.height = value;
                v.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }

}
