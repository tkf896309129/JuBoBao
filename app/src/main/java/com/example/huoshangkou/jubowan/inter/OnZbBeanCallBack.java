package com.example.huoshangkou.jubowan.inter;

import com.example.huoshangkou.jubowan.bean.ZbBean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.inter
 * 类名：OnZbBeanCallBack
 * 描述：
 * 创建时间：2017-04-17  13:34
 */

public interface OnZbBeanCallBack {

     void onSuccess(ZbBean zbBean);

    void onFail();
}
