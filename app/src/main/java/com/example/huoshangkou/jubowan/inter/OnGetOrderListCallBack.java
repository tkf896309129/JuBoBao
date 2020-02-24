package com.example.huoshangkou.jubowan.inter;

import com.example.huoshangkou.jubowan.bean.OrderListBean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.inter
 * 类名：OnGetOrderListCallBack
 * 描述：
 * 创建时间：2017-03-13  10:10
 */

public interface OnGetOrderListCallBack {

    //获取订单成功
    void onSuccess(OrderListBean listBean);


    //获取订单失败
    void onFail();

}
