package com.example.huoshangkou.jubowan.inter;

import com.example.huoshangkou.jubowan.bean.ShopCarBean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.inter
 * 类名：OnShopCarCallBack
 * 描述：
 * 创建时间：2017-03-14  10:21
 */

public interface OnShopCarCallBack {

    void onSuccess(ShopCarBean shopCarBean);

    void onFail();

}
