package com.example.huoshangkou.jubowan.utils;

import android.text.Html;
import android.text.Spanned;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：BlueTxtUtils
 * 描述：
 * 创建时间：2017-05-12  17:39
 */

public class BlueTxtUtils {

    private static class BlueTxtHelper {
        private static BlueTxtUtils INSTANCE = new BlueTxtUtils();
    }

    public static BlueTxtUtils getInstance() {
        return BlueTxtHelper.INSTANCE;
    }


    public String getBlueTxt(String str) {
        String nickName = "<font color=\"#1C86EE\">" + str + "</font>";
        return nickName;
    }

}
