package com.example.huoshangkou.jubowan.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：CardUtils
 * 描述：
 * 创建时间：2017-06-28  10:54
 */

public class CardUtils {


    //判断身份证号是否合法
    public static boolean isIdCard(String str) {
        String rgx = "^\\d{15}|^\\d{17}([0-9]|X|x)$";

        return isCorrect(rgx, str);
    }


    //正则验证
    private static boolean isCorrect(String rgx, String res) {
        Pattern p = Pattern.compile(rgx);

        Matcher m = p.matcher(res);

        return m.matches();
    }
}
