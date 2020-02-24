package com.example.huoshangkou.jubowan.inter;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.inter
 * 类名：ApproveCallBack
 * 描述：维修工程师认证回调
 * 创建时间：2017-03-07  13:54
 */

public interface ApproveCallBack {

    //认证成功
    void onApproveSuccess();

    //认证失败
    void onApproveFail();

}
