package com.example.huoshangkou.jubowan.utils;

import com.example.huoshangkou.jubowan.base.BaseApp;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;

import okhttp3.Cache;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：OkhttuCacheUtils
 * 描述：
 * 创建时间：2019-03-29  13:29
 */

public class OkhttuCacheUtils {

    public static OkhttuCacheUtils instance;

    public OkHttpClient okHttpClient;


    private OkhttuCacheUtils() {
        //tip:获取OKHttp对象
        if (null == okHttpClient) {
            synchronized (OkHttpClient.class) {
                if (null == okHttpClient) {
                    //okHttpClient = new OkHttpClient();
                    //缓存文件
                    File file = new File(BaseApp.getInstance().getExternalCacheDir().getAbsolutePath(), "caches");
                    Cache cache = new Cache(file, 10 * 1024 * 1024);
                    //添加缓存拦截器
                    okHttpClient = new OkHttpClient
                            .Builder()
                            .addInterceptor(new CacheInterceptor())
                            .cache(cache)
                            .build();
                }
            }
        }
    }


    public static OkhttuCacheUtils getInstance() {
        //DCL双重检测锁获取实例对象
        if (null == instance) {
            //用锁防止多线程高并发的访问
            synchronized (OkhttuCacheUtils.class) {
                if (null == instance) {
                    instance = new OkhttuCacheUtils();
                }
            }
        }
        return instance;
    }


    public void get(String urlString, Callback callback) {
        Request request = new Request.Builder().url(urlString).build();
        okHttpClient.newCall(request).enqueue(callback);
    }


    public void post(String urlString, String  json, Callback callback) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder().post(body).url(urlString).build();
        okHttpClient.newCall(request).enqueue(callback);



    }







}
