package com.example.huoshangkou.jubowan.utils;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：NoDoubbleUtils
 * 描述：
 * 创建时间：2020-03-17  11:17
 */

public class NoDoubbleUtils {

    private final static int SPACE_TIME = 500;//2次点击的间隔时间，单位ms
    private static long lastClickTime;

    public synchronized static boolean isDoubleClick() {
        long currentTime = System.currentTimeMillis();
        boolean isClick;
        if (currentTime - lastClickTime > SPACE_TIME) {
            isClick = false;
        } else {
            isClick = true;
        }
        lastClickTime = currentTime;
        return isClick;
    }

}
