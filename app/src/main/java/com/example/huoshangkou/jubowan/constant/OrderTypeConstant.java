package com.example.huoshangkou.jubowan.constant;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.constant
 * 类名：OrderTypeConstant
 * 描述：
 * 创建时间：2017-04-06  10:26
 */

public class OrderTypeConstant {

    private static class OrderTypeHelper {
        private static OrderTypeConstant INSTANCE = new OrderTypeConstant();
    }

    public static OrderTypeConstant getInstance() {
        return OrderTypeHelper.INSTANCE;
    }


    //原片
    public final String YP = "yp";
    public final String FL = "fl";
    public final String WX = "wx";
    public final String PJ = "pj";


    //普通订单
    public final String COMMON_ORDER = "common_order";
    //维修订单
    public final String REPAIR_ORDER = "repair_order";


    //购物车添加
    public final String ADD_CAR = "add";

    //购物车减少
    public final String DES_CAR = "des";

    public final String DEC_CAR = "dec";


    //addCarType 0 原片   1 辅材

    public final String addYp = "0";
    public final String addFl = "1";


}
