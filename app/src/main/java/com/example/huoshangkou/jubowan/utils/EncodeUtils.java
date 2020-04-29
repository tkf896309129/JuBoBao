package com.example.huoshangkou.jubowan.utils;

import com.umeng.socialize.net.utils.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：EncodeUtils
 * 描述：转码工具类
 * 创建时间：2016-12-28  13:43
 */

public class EncodeUtils {

    private static class EncodeHelper {
        private static EncodeUtils INSTANCE = new EncodeUtils();
    }

    public static EncodeUtils getInstance() {
        return EncodeHelper.INSTANCE;
    }

    public String getEncodeString(String str) {
        String msg = "";
        if (!StringUtils.isNoEmpty(str)) {
            return "";
        }
        try {
            msg = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return msg;
    }

    public String getEncodeStringGBK(String str) {
        String msg = "";
        if (!StringUtils.isNoEmpty(str)) {
            return "";
        }
        try {
            msg = URLEncoder.encode(str, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return msg;
    }

    public String getDecodeString(String str) {
        String msg = "";
        if (!StringUtils.isNoEmpty(str)) {
            return "";
        }
        try {
            msg = URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return msg;
    }

    public String getBase64String(byte[] str) {
        Base64 base64 = new Base64();
        String encode = base64.encodeToString(str);
        return encode;
    }


    //base64 解码
    public static String decode(String bytes) {
        return new String(Base64.decodeBase64(bytes));
    }

    //base64 编码
    public static String encode(byte[] bytes) {
        return new String(Base64.encodeBase64(bytes,true));
    }
}
