package com.example.huoshangkou.jubowan.inter;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.inter
 * 类名：OnAddCarCallBack
 * 描述：
 * 创建时间：2017-03-14  09:05
 */

public interface OnAddCarCallBack {
    //添加成功
    void onSuccess(String carCount);

    //添加失败
    void onFail();

}
