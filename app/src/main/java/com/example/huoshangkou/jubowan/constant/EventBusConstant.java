package com.example.huoshangkou.jubowan.constant;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.constant
 * 类名：EventBusConstant
 * 描述：EventBus常量类
 * 创建时间：2017-02-20  10:07
 */

public class EventBusConstant {//为什么我感觉

    private static class EventBusHelper {
        private static EventBusConstant INSTANCE = new EventBusConstant();
    }

    public static EventBusConstant getInstance() {
        return EventBusHelper.INSTANCE;
    }

    //首页跳转至维修维保
    public final String TO_REPAIR = "repairClick";

    //首页跳转至论坛
    public final String TO_FORUM = "forumClick";

    //论坛排序
    public final String FORUM_SORT = "forum_sort";

    //热度排序
    public final String HOT_SORT = "2";

    //时间排序
    public final String TIME_SORT = "1";

    //微信支付
    public final String WX_PAY = "wx_pay";

}
