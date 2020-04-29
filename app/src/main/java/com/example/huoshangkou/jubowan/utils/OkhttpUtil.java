package com.example.huoshangkou.jubowan.utils;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AlertDialog;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;


import java.io.File;
import java.util.Map;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：OkhttpUtil
 * 描述：网络请求封装类
 * 创建时间：2016-12-27  14:26
 */

public class OkhttpUtil {

    private static class OkhttpHelper {
        private static OkhttpUtil INSTANCE = new OkhttpUtil();
    }

    public static OkhttpUtil getInstance() {
        return OkhttpHelper.INSTANCE;
    }

    public String cachedirectory = Environment.getExternalStorageDirectory() + "/lancoo/caches";
    public Cache cache = new Cache(new File(cachedirectory), 10 * 1024 * 1024);

    public void setUnCacheData(final Context context, String message, String url, final OkhttpCallBack callBacks) {
        final AlertDialog alertDialog = MineLoadingDialogUtils.updateDialog(context, message);
        LogUtils.i(url);
        OkHttpUtils
                .get()
                .addHeader("VERSION", VersionUtils.getInstance().getLocalVersionNo() + "")
                .addHeader("userId ", PersonConstant.getInstance().getUserId())
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBacks.onFail();
                        alertDialog.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        alertDialog.dismiss();
                        callBacks.onSuccess(response);

                    }
                });
    }

    public void setUnCacheDataNoDialog(final Context context, String url, final OkhttpCallBack callBacks) {
        LogUtils.e("公钥：" + url);

//       OkHttpClient client =  new OkHttpClient.Builder()
//                .cookieJar(new CookieJar() {
//                    private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();
//
//                    @Override
//                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
//                        cookieStore.put(url.host(), cookies);
//                    }
//
//                    @Override
//                    public List<Cookie> loadForRequest(HttpUrl url) {
//                        List<Cookie> cookies = cookieStore.get(url.host());
//                        return cookies != null ? cookies : new ArrayList<Cookie>();
//                    }
//                }).build();


        OkHttpUtils
                .get()
                .addHeader("VERSION", VersionUtils.getInstance().getLocalVersionNo() + "")
                .addHeader("userId ", PersonConstant.getInstance().getUserId())
//                .addHeader("cookie",sessionid)
                .tag(context)
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        callBacks.onFail();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        callBacks.onSuccess(response);
                    }
                });
    }

//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case 1:
//                    String obj = (String) msg.obj;
//                    stringCallBackAll.onSuccess(obj);
//                    break;
//            }
//        }
//    };


    //post请求
    public void basePostCall(final Context context, Map<String, String> map, String url, final StringCallBack stringCallBack) {
//        String pubKey = (String) SharedPreferencesUtils.getInstance().get(context, SharedKeyConstant.getInstance().PUB_POST_KEY, "");
//        String pubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqgkESLVMN5QyecVCBjn8\n" +
//                "BOuGgShNw26rnE83sb7HbIp3SwXQHtfvwdMBKiUc0nC+TTUjrhOr7S77/sgKHv6f\n" +
//                "QfKCftPICKm+NIAV6KO2XLFm871PZCTcOoZPke7MvXiO7LkTsDPGLn7FDsJwR57A\n" +
//                "qeFyNqHfj2xvX8sowHTBsw0ZzpMhx9iJKdyJGh0nTopxCIUjmRpF2GLb8cTCoueO\n" +
//                "Jne0S9/H9TSaP0Jz/TjbX5xXuO/Be6RnTQjWJaLh5FNnD09yI1OzutLK5KLTIW0s\n" +
//                "bGo5+YA5HdKy+jgJZAN7NLr2/5mKiRJJ3dilHE3zWIamzfYgsTzyATb4YqKyaaus\n" +
//                "/wIDAQAB";
//        String md5Json = MD5Utils.MD5(JSON.toJSONString(map));
//        String rsaJson = "";
//        try {
//            rsaJson = MD5Utils.encrypt(md5Json, pubKey);
//        } catch (Exception e) {
//            e.printStackTrace();
//            LogUtils.e(e.toString());
//        }
        OkHttpUtils.getInstance().postString()
                .addHeader("VERSION", VersionUtils.getInstance().getLocalVersionNo() + "")
                .addHeader("userId ", PersonConstant.getInstance().getUserId())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .url(url).content(JSON.toJSONString(map))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        stringCallBack.onSuccess(response.toString());
                    }
                });
    }

    public void basePostCall(final Context context, String json, String url, final StringCallBack stringCallBack) {
        LogUtils.e(json + "\n" + url);
//        String rsaJson = "";
//        try {
//            InputStream inPublic = context.getResources().getAssets().open("rsa_public_key.pem");
//            PublicKey publicKey = RSAEncrypt.loadPublicKey(inPublic);
//            byte[] encryptByte = RSAEncrypt.encryptData(json.getBytes(), publicKey);
////
////            // 为了方便观察吧加密后的数据用base64加密转一下
//            LogUtils.e("rsaJson：" + EncodeUtils.getInstance().getDecodeString(rsaJson));
//            rsaJson = EncodeUtils.getInstance().getEncodeString(Base64Utils.encode(encryptByte));
//            LogUtils.e("rsaJson：" + EncodeUtils.getInstance().getDecodeString(rsaJson));

//            InputStream inPrivate = context.getResources().getAssets().open("api_private_key.pem");
//            PrivateKey privateKey = RSAEncrypt.loadPrivateKey(inPrivate);
//            // 因为RSA加密后的内容经Base64再加密转换了一下，所以先Base64解密回来再给RSA解密
//            byte[] decryptByte = RSAEncrypt.decryptData(Base64Utils.decode(rsaJson), privateKey);
//            String decryptStr = new String(decryptByte);
//            LogUtils.e("rsaJson：解密  " +decryptStr);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        OkHttpUtils.getInstance().postString()
                .url(url)
                .content(json)
                .addHeader("userId ", PersonConstant.getInstance().getUserId())
                .addHeader("VERSION", VersionUtils.getInstance().getLocalVersionNo() + "")
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        stringCallBack.onSuccess(response.toString());
                    }
                });
    }

    AlertDialog alertDialog;

    public void basePostCallDialog(final Context context, String json, String url, final StringCallBack stringCallBack) {
        LogUtils.e(json + "\n" + url);
//        String pubKey = (String) SharedPreferencesUtils.getInstance().get(context, SharedKeyConstant.getInstance().PUB_POST_KEY, "");
//        String md5Json = MD5Utils.MD5(json);
//        String rsaJson = "";
//        try {
//            rsaJson = MD5Utils.encrypt(md5Json, pubKey);
//        } catch (Exception e) {
//            e.printStackTrace();
//            LogUtils.e(e.toString());
//        }
        alertDialog = MineLoadingDialogUtils.updateDialog(context, "数据加载中");
        OkHttpUtils.getInstance().postString()
                .url(url)
                .content(json)
                .addHeader("VERSION", VersionUtils.getInstance().getLocalVersionNo() + "")
                .addHeader("userId ", PersonConstant.getInstance().getUserId())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        alertDialog.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        alertDialog.dismiss();
                        stringCallBack.onSuccess(response.toString());
                    }
                });
    }

    public void basePostCall(final Context context, String url, final StringCallBack stringCallBack) {
        final AlertDialog alertDialog = MineLoadingDialogUtils.updateDialog(context, "数据加载中");
        OkHttpUtils.getInstance().postString()
                .url(url)
                .content("")
                .addHeader("VERSION", VersionUtils.getInstance().getLocalVersionNo() + "")
                .addHeader("userId ", PersonConstant.getInstance().getUserId())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        alertDialog.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        alertDialog.dismiss();
                        stringCallBack.onSuccess(response.toString());
                    }
                });
    }
}
