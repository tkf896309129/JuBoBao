package com.example.huoshangkou.jubowan.utils;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：DoubleUtils
 * 描述：
 * 创建时间：2018-06-08  14:41
 */

public class DoubleUtils {


    public static double getDouble(String douboles) {
        if (!StringUtils.isNoEmpty(douboles)) {
            return 0;
        }
        return Double.parseDouble(douboles);
    }

}
