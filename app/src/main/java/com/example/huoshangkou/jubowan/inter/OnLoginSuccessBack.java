package com.example.huoshangkou.jubowan.inter;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.inter
 * 类名：OnLoginSuccessBack
 * 描述：登录成功接口回调
 * 创建时间：2017-02-24  09:03
 */

public interface OnLoginSuccessBack {
    //登录成功
    void onLoginSuccess(String id,String name,String pic);

    //登录失败
    void onLoginFail( );


}
