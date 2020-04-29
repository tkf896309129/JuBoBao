package com.example.huoshangkou.jubowan.utils;

/**
 * Created by kaifeng on 2016/9/21.
 * 字符串封装类
 */
public class StringUtils {

    //字符串不为空
    public static boolean isNoEmpty(String str) {
        return (str != null && !str.trim().equals("")&& !str.trim().equals("暂无") && !str.trim().equals("null"));
    }

    //去除字符串中所有的空格
    public static String getNoSapceStr(String str) {
        String noSpace = str.replaceAll(" ", "");
        return noSpace;
    }

    //去除null字段
    public static String getNoNullStr(String str) {
        if (StringUtils.isNoEmpty(str) && str.equals("null")) {
            return "";
        }
        if (!StringUtils.isNoEmpty(str)) {
            return "";
        }
        return str.replaceAll("[<br>]{0,}", "").replaceAll("(?m)^\\s*$(\\n|\\r\\n)", "   ");
    }

    public static String getNoNullDeStr(String str) {
        if (StringUtils.isNoEmpty(str) && str.equals("null")) {
            return "";
        }
        if (!StringUtils.isNoEmpty(str)) {
            return "";
        }
        return str.replaceAll("[<br>]{0,}", "").replaceAll("(?m)^\\s*$(\\n|\\r\\n)", "   ") + "的";
    }


    public static String getNoNullChooseStr(String str) {
        if (StringUtils.isNoEmpty(str) && str.equals("null")) {
            return "请选择";
        }
        if (!StringUtils.isNoEmpty(str)) {
            return "请选择";
        }
        return str.replaceAll("[<br>]{0,}", "").replaceAll("(?m)^\\s*$(\\n|\\r\\n)", "   ");
    }

    public static String getNoEmptyStr(String str) {
        if (StringUtils.isNoEmpty(str) && str.equals("null")) {
            return "暂无";
        }

        if (!StringUtils.isNoEmpty(str)) {
            return "暂无";
        }
        return str.replaceAll("[<br>]{0,}", "").replaceAll("(?m)^\\s*$(\\n|\\r\\n)", "   ");
    }

    public static String getZeroStr(String str) {
        if (StringUtils.isNoEmpty(str) && str.equals("null")) {
            return "0.0";
        }
        if (!StringUtils.isNoEmpty(str)) {
            return "0.0";
        }
        return str;
    }

    public static String getNullStr(String str) {
        if (!isNoEmpty(str)) {
            return null;
        }
        return str.replaceAll("[<br>]{0,}", "").replaceAll("(?m)^\\s*$(\\n|\\r\\n)", "   ");
    }
}
