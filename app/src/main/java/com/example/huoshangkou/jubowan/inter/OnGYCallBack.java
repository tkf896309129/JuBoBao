package com.example.huoshangkou.jubowan.inter;

import com.example.huoshangkou.jubowan.bean.GYBean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.inter
 * 类名：OnGYCallBack
 * 描述：
 * 创建时间：2017-04-12  11:52
 */

public interface OnGYCallBack {

    void onSuccess(GYBean gyBean);

    void onFail();
}
