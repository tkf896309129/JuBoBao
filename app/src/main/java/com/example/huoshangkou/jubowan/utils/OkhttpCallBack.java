package com.example.huoshangkou.jubowan.utils;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：OkhttpCallBack
 * 描述：网络请求的接口回调
 * 创建时间：2016-12-27  14:37
 */

public interface OkhttpCallBack {
    void onSuccess(String json);
    void onFail();
}
