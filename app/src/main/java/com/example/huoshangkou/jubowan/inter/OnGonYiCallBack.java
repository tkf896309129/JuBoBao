package com.example.huoshangkou.jubowan.inter;

import com.example.huoshangkou.jubowan.bean.GonYiBean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.inter
 * 类名：OnGonYiCallBack
 * 描述：
 * 创建时间：2017-04-12  09:56
 */

public interface OnGonYiCallBack {

    void onSuccess(GonYiBean gonYiBean);

    void onFail();

}
