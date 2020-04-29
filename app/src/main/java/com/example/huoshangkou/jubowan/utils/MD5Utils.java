package com.example.huoshangkou.jubowan.utils;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.bean.CodeBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.umeng.socialize.net.utils.Base64;

import org.apache.commons.codec.binary.Hex;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：MD5Utils
 * 描述：
 * 创建时间：2017-07-28  14:49
 */

public class MD5Utils {

    public static final String KEY = "019de69e84b038c5e920c930a5233c9f";

    private static class MD5Helper {
        private static MD5Utils INSTANCE = new MD5Utils();
    }

    public static MD5Utils getInstance() {
        return MD5Helper.INSTANCE;
    }

    public static String MD5(String key) {
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        try {
            byte[] btInput = key.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static String encrypt(String str) {
        try {
            MessageDigest digester = MessageDigest.getInstance("md5");
            digester.update(str.getBytes("UTF-8"));
            String s1 = new BigInteger(1, digester.digest()).toString(16);
            //补齐BigInteger省略的前置0
            return new String(new char[32 - s1.length()]).replace("\0", "0") + s1;
        } catch (Exception e) {
            //一般不会有异常抛出， 该死的Java受检异常，导致丑陋的代码
        }
        return null;
    }

    public String encodePassword(String password) {
        //
        //password = "gxzcwefxcbergfd123456errttyyytytrnfzeczxcvertwqqcxvxb";
        //1:MD5  算法
        String algorithm = "MD5";
        char[] encodeHex = null;
        try {
            //MD5加密
            MessageDigest instance = MessageDigest.getInstance(algorithm);
            //加密后
            byte[] digest = instance.digest(password.getBytes());
            //
            //2:十六进制
            encodeHex = Hex.encodeHex(digest);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new String(encodeHex);
    }

    //MD5加盐 加密
    public String md5(String string, String slat) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest((string + slat).getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public byte[] md5Byte(String string) {
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest((string).getBytes());
            return bytes;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String md5NoreZero(String string, String slat) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest((string + slat).getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                String code = "";
                if (temp.length() == 1) {
                    temp = "0" + temp;
                    if (temp.substring(0, 1).equals("0")) {
                        code = temp.substring(1, 2);
                    }
                } else {
                    code = temp;
                }
                LogUtils.e("十六进制：" + temp + "   " + code);
                result += code;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * UrlConstant.getInstance().URL
     * + PostConstant.getInstance().TWO_CODE + FieldConstant.getInstance().TEL + "=" + tel
     */
    //从服务器获取验证码
    public void getTwoCode(final Context context, final String tel, String areaCode, final StringCallBack stringCallBack) {
//        OkhttpUtil.getInstance().setUnCacheDataNoDialog(context, UrlConstant.getInstance().LOCAL_URL
//                + PostConstant.getInstance().TWO_CODE
//                + FieldConstant.getInstance().TEL + "=" + tel, new OkhttpCallBack() {
//            @Override
//            public void onSuccess(String json) {
//                LogUtils.i(json);
//                CodeBean codeBean = JSON.parseObject(json, CodeBean.class);
//                if (codeBean.getSuccess() == 1) {
//                    stringCallBack.onSuccess(codeBean.getReObj() + "");
//                }
//            }
//
//            @Override
//            public void onFail() {
//
//            }
//        });

        x.http().get(new RequestParams(UrlConstant.getInstance().LOCAL_URL
                + PostConstant.getInstance().TWO_CODE
                + FieldConstant.getInstance().TEL + "=" + tel), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                CodeBean codeBean = JSON.parseObject(result, CodeBean.class);
                if (codeBean.getSuccess() == 1) {
                    stringCallBack.onSuccess(codeBean.getReObj() + "");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    //小写转换成大写字母
    public String getLageKey(String str) {
        return str.toUpperCase();
    }

    //获取SessionId
    public String getSessionId(String requrl) {
        URL url = null;
        HttpURLConnection con = null;
        String sessionid = "";
        try {
            url = new URL(requrl);
            con = (HttpURLConnection) url.openConnection();
            // 取得sessionid.
            String cookieval = con.getHeaderField("set-cookie");

            if (cookieval != null) {
                sessionid = cookieval.substring(0, cookieval.indexOf(";"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionid;
    }


    public static String encrypt( String str, String publicKeys ) throws Exception{

        // 对公钥解密
//        byte[] keyBytes = Base64.decodeBase64(publicKeys);
//        // 取得公钥
//        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        Key publicKey = keyFactory.generatePublic(x509KeySpec);
//        // 对数据加密
//        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
//        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//        return Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));

        //base64编码的公钥  java.security.spec.InvalidKeySpecException: com.android.org.conscrypt.OpenSSLX509CertificateFactory$ParsingException: Error parsing public key
        byte[] decoded = Base64.decodeBase64(publicKeys);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
        LogUtils.e(EncodeUtils.getInstance().getEncodeString(outStr)+" outStr");
        return outStr;
    }
}
