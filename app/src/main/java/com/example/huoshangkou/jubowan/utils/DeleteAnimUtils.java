package com.example.huoshangkou.jubowan.utils;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.inter.OnCommonSuccessCallBack;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：DeleteAnimUtils
 * 描述：
 * 创建时间：2017-04-20  16:07
 */

public class DeleteAnimUtils {


    private static class DeleteAnimHelper {
        private static DeleteAnimUtils INSTANCE = new DeleteAnimUtils();
    }

    public static DeleteAnimUtils getInstance() {
        return DeleteAnimHelper.INSTANCE;
    }

    public Animation deleteAnim(Context context, final OnCommonSuccessCallBack successCallBack) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.delete_trans_anim);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                successCallBack.onSuccess();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        return animation;
    }




}
