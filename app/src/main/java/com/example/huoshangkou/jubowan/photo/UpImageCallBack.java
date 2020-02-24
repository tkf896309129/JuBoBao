package com.example.huoshangkou.jubowan.photo;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.photo
 * 类名：UpImageCallBack
 * 描述：上传图片回调
 * 创建时间：2017-01-04  14:26
 */

public interface UpImageCallBack {

    //上传成功
    void onUpSuccess(String url);

    //上传失败
    void onUpFail();

}
