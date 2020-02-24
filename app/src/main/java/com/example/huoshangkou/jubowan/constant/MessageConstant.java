package com.example.huoshangkou.jubowan.constant;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.constant
 * 类名：MessageConstant
 * 描述：
 * 创建时间：2017-05-12  14:34
 */

public class MessageConstant {

    private static class MessageHelper {
        private static MessageConstant INSTANCE = new MessageConstant();
    }

    public static MessageConstant getInstance() {
        return MessageHelper.INSTANCE;
    }

    //0:系统 1:订单(原片订单) 2 辅料订单 3 维修维保通知 4 审批消息 5论坛消息 6其他通知
    public final String SYSTEM_MESSAGE = "0";
    public final String YP_ORDER = "1";
    public final String FL_ORDER = "2";
    public final String WX_ORDER = "3";
    public final String CHECK_MESSAGE = "4";
    public final String FORUM_MESSAGE = "5";
    public final String OTHER_ORDER = "6";


}
