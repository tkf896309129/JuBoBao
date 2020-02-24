package com.example.huoshangkou.jubowan.inter;

import com.example.huoshangkou.jubowan.bean.ZbLowBean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.inter
 * 类名：OnZbLowCallBack
 * 描述：
 * 创建时间：2017-04-12  15:28
 */

public interface OnZbLowCallBack {

    void onZbLowCallBack(ZbLowBean lowBean);

    void onFail();


}
