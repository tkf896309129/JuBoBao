package com.example.huoshangkou.jubowan.utils;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：EqualUtils
 * 描述：
 * 创建时间：2018-05-31  16:07
 */

public class EqualUtils {

    private static class EqualHelper {
        private static EqualUtils INSTANCE = new EqualUtils();
    }

    public EqualUtils getInstance() {
        return EqualHelper.INSTANCE;
    }

    public static boolean noNullEqual(String str_1, String str_2) {
        if (!StringUtils.isNoEmpty(str_1) || !StringUtils.isNoEmpty(str_2)) {
            return false;
        }
        return str_1.equals(str_2);
    }


}
