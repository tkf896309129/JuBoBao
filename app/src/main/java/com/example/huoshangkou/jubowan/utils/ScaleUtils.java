package com.example.huoshangkou.jubowan.utils;

import android.content.res.Resources;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：ScaleUtils
 * 描述：
 * 创建时间：2020-03-11  09:00
 */

public class ScaleUtils {

    private ScaleUtils() {
    }

    public static int dip2px(float f) {
        return Math.round((Resources.getSystem().getDisplayMetrics().density * f) + 0.5f);
    }

    public static int px2dip(float f) {
        return Math.round((f / Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2sp(float f) {
        return Math.round((f / Resources.getSystem().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static int sp2px(float f) {
        return Math.round((Resources.getSystem().getDisplayMetrics().scaledDensity * f) + 0.5f);
    }


}
