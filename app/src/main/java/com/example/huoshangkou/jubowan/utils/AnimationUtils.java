package com.example.huoshangkou.jubowan.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import org.simple.eventbus.EventBus;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：AnimationUtils
 * 描述：
 * 创建时间：2017-04-27  15:37
 */

public class AnimationUtils {

    private static TranslateAnimation animation;
    private static int num = 0;
    /**
     * 引导界面的动画
     */
    public static TranslateAnimation getTransAnimation(int fromx, int fromy, int tox, int toy) {
        animation = new TranslateAnimation(fromx, tox, fromy, toy);
        animation.setDuration(2000);
        return animation;
    }
    /**
     * 购物车图片滚动效果
     */
    private static View addViewToAnimLayout(ViewGroup vg, View view, int[] location, Context context) {
        int x = location[0];
        int y = location[1];
        vg.addView(view);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                dip2px(context, 90), dip2px(context, 90));
        lp.leftMargin = x;
        lp.topMargin = y;
        view.setPadding(5, 5, 5, 5);
        view.setLayoutParams(lp);
        return view;
    }
    /**
     * @param
     * @return void
     * @throws
     * @Description: 创建动画层
     * ViewGroup rootView = (ViewGroup) getActivity().getWindow().getDecorView();
     * FrameLayout animLayout = new FrameLayout(getActivity());
     */
    public static FrameLayout createAnimLayout(ViewGroup rootView, Context context) {
        FrameLayout animLayout = new FrameLayout(context);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }
    /**
     * dip，dp转化成px 用来处理不同分辨路的屏幕
     *
     * @param context
     * @param dpValue
     * @return
     */
    private static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    public static void setAnim(Drawable drawable, int[] start_location, int animationDuration, final Context context, FrameLayout animation_viewGroup, ImageView edImg) {
        Animation mScaleAnimation = new ScaleAnimation(1.5f, 0.0f, 1.5f, 0.0f, Animation.RELATIVE_TO_SELF, 0.1f, Animation.RELATIVE_TO_SELF, 0.1f);
        mScaleAnimation.setDuration(animationDuration);
        mScaleAnimation.setFillAfter(true);
        final ImageView iview = new ImageView(context);
        iview.setImageDrawable(drawable);
        final View view = addViewToAnimLayout(animation_viewGroup, iview, start_location, context);
        view.setAlpha(0.6f);
        int[] end_location = new int[2];
        edImg.getLocationInWindow(end_location);
        int endX = end_location[0];
        int endY = end_location[1] - start_location[1];
        Animation mTranslateAnimation = new TranslateAnimation(0, endX , 0, endY );
        Animation mRotateAnimation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateAnimation.setDuration(animationDuration);
        mTranslateAnimation.setDuration(animationDuration);
        AnimationSet mAnimationSet = new AnimationSet(true);
        mAnimationSet.setFillAfter(true);
        mAnimationSet.addAnimation(mRotateAnimation);
        mAnimationSet.addAnimation(mScaleAnimation);
        mAnimationSet.addAnimation(mTranslateAnimation);
        mAnimationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
                num++;
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                num--;
                if (num == 0) {
//                    SharedPreferencesUtils.getPhone(context).edit().putString("isClean", "yes").commit();
//                    EventBus.getDefault().post("cleanAnim", "cleanAnim");
                }
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }
        });
        iview.setAnimation(mAnimationSet);
        iview.startAnimation(mAnimationSet);
    }



}
