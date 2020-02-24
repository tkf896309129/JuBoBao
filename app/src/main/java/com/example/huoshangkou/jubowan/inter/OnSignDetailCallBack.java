package com.example.huoshangkou.jubowan.inter;

import com.example.huoshangkou.jubowan.bean.SignDetailBean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.inter
 * 类名：OnSignDetailCallBack
 * 描述：
 * 创建时间：2017-04-05  11:45
 */

public interface OnSignDetailCallBack {

    void onSuccess(SignDetailBean detailBean);

    void onFail();

}
