package com.example.huoshangkou.jubowan.utils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.inter.OnListViewDeleteCallBack;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：ListViewUtils
 * 描述：
 * 创建时间：2017-03-01  15:45
 */

public class ListViewUtils {

    private static class ListViewHelper {
        private static ListViewUtils INSTANCE = new ListViewUtils();
    }

    public static ListViewUtils getInstance() {
        return ListViewHelper.INSTANCE;
    }


    public void deletePattern(final View view, final OnListViewDeleteCallBack callBack) {

        Animation.AnimationListener al = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ToastUtils.getMineToast("动画结束");
                callBack.onDelete();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
        collapse(view, al);
    }

    private void collapse(final View view, Animation.AnimationListener al) {
        final int originHeight = view.getMeasuredHeight();

        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                LogUtils.i(interpolatedTime + "");
                if (interpolatedTime == 1.0) {
//                    view.getLayoutParams().height = 0;
//                    view.requestLayout();
                    view.setVisibility(View.GONE);
                } else {
                    view.getLayoutParams().height = originHeight - (int) (originHeight * interpolatedTime);
                    view.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        if (al != null) {
            animation.setAnimationListener(al);
        }
        animation.setDuration(300);
        view.startAnimation(animation);
    }


    public void expand(final View view) {
        final int originHeight = view.getMeasuredHeight();

        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                LogUtils.i(interpolatedTime + "");
                if (interpolatedTime == 1.0) {
                    view.getLayoutParams().height = originHeight;
                    view.requestLayout();
                } else {
                    view.getLayoutParams().height = (int) (originHeight * interpolatedTime);
                    view.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        animation.setDuration(300);
//        view.startAnimation(animation);
    }

}
