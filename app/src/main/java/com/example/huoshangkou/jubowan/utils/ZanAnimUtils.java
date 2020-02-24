package com.example.huoshangkou.jubowan.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：ZanAnimUtils
 * 描述：
 * 创建时间：2017-04-11  10:15
 */

public class ZanAnimUtils {


    public static void setZanAnimation(TextView tvZan, Context context, int[] start_location) {
//        TextView tvAnim = new TextView(context);
//        tvAnim.setTextColor(context.getResources().getColor(R.color.blue));
//        tvAnim.setText("+1");

        tvZan.setVisibility(View.VISIBLE);
        int x = start_location[0];
        int y = start_location[1];


        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, -150);
        translateAnimation.setDuration(1500);

        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setDuration(1300);

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setFillAfter(true);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);

        tvZan.setAnimation(animationSet);
        tvZan.startAnimation(animationSet);
    }

}
