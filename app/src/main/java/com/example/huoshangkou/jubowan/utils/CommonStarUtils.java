package com.example.huoshangkou.jubowan.utils;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.inter.StringCallBack;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：CommonStarUtils
 * 描述：
 * 创建时间：2017-02-10  10:31
 */

public class CommonStarUtils {

    private static int starScore;


    private static class CommonStarHelper {
        private static CommonStarUtils INSTANCE = new CommonStarUtils();
    }

    public static CommonStarUtils getInstance() {
        return CommonStarHelper.INSTANCE;
    }

    /**
     * 给星星设置星星个数
     */
    public void setCommonStars(LinearLayout llCommon, int stars, StringCallBack stringCallBack) {
        int num = llCommon.getChildCount();
        starScore = stars;
        for (int i = 0; i < num; i++) {
            if (i < stars) {
                ImageView imgStar = (ImageView) llCommon.getChildAt(i);
                imgStar.setImageResource(R.mipmap.pj_star_y_icon);
                imgStar.setTag(true);
                changeStars(imgStar, i, llCommon,stringCallBack);
            } else {
                ImageView imgStar = (ImageView) llCommon.getChildAt(i);
                imgStar.setImageResource(R.mipmap.pj_star_g_icon);
                imgStar.setTag(false);
                changeStars(imgStar, i, llCommon,stringCallBack);
            }
        }
    }


    /**
     * 点击设置星星个数
     */
    public void changeStars(final ImageView checkBox, final int i, final LinearLayout linearLayout, final StringCallBack stringCallBack) {

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //如果被选中 并且当前位置小于总得分
                boolean isCheck = (boolean) checkBox.getTag();

                if (!isCheck) {
                    setCommonStarsNum(linearLayout, i);
                    starScore = i + 1;
                } else {
                    setCommonStarsNum(linearLayout, (i - 1));
                }
                int score = getAllScore(linearLayout);
                stringCallBack.onSuccess(score+"");
            }
        });
    }

    /**
     * 设置黄星星
     */
    public void setCommonStarsNum(LinearLayout llCommon, int stars) {
        int num = llCommon.getChildCount();
        for (int i = 0; i < num; i++) {
            if (i <= stars) {
                ImageView imgStar = (ImageView) llCommon.getChildAt(i);
                imgStar.setImageResource(R.mipmap.pj_star_y_icon);
                imgStar.setTag(true);
            } else {
                ImageView imgStar = (ImageView) llCommon.getChildAt(i);
                imgStar.setImageResource(R.mipmap.pj_star_g_icon);
                imgStar.setTag(false);
            }
        }
    }


    /**
     * 静态设置 不点击
     *
     * @param stars
     * @param llCommon
     */
    public void setCommonStarsNum(int stars, LinearLayout llCommon) {
        int num = llCommon.getChildCount();
        for (int i = 0; i < num; i++) {
            if (i < stars) {
                ImageView imgStar = (ImageView) llCommon.getChildAt(i);
                imgStar.setImageResource(R.mipmap.pj_star_y_icon);
                imgStar.setTag(true);
            } else {
                ImageView imgStar = (ImageView) llCommon.getChildAt(i);
                imgStar.setImageResource(R.mipmap.pj_star_g_icon);
                imgStar.setTag(false);
            }
        }
    }

    /**
     * 计算总分
     */
    private int getAllScore(LinearLayout llCommon) {
        int num = llCommon.getChildCount();
        int score = 0;
        for (int i = 0; i < num; i++) {
            ImageView imgStar = (ImageView) llCommon.getChildAt(i);
            boolean isCheck = (boolean) imgStar.getTag();
            if (isCheck) {
                score++;
            }
        }
        return score;
    }

}
