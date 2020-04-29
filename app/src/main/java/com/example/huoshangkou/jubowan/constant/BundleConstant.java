package com.example.huoshangkou.jubowan.constant;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.constant
 * 类名：BundleConstant
 * 描述：
 * 创建时间：2017-03-13  10:21
 */

public class BundleConstant {

    private static class BundlerHelper {
        private static BundleConstant INSTANCE = new BundleConstant();
    }

    public static BundleConstant getInstance() {
        return BundlerHelper.INSTANCE;
    }

    //订单状态
    public final String ORDER_STATE = "order_state";

}
