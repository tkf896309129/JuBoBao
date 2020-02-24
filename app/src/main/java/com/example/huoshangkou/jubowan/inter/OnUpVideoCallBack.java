package com.example.huoshangkou.jubowan.inter;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.inter
 * 类名：OnUpVideoCallBack
 * 描述：
 * 创建时间：2017-09-15  08:41
 */

public interface OnUpVideoCallBack {

    void onProgress(float progress);

    void onStr(String str);

    void  onFail();

}
