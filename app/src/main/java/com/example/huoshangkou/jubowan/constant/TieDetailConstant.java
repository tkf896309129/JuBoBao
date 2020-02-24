package com.example.huoshangkou.jubowan.constant;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.constant
 * 类名：TieDetailConstant
 * 描述：
 * 创建时间：2017-04-11  14:01
 */

public class TieDetailConstant {

    private static class TieDetailHelper {
        private static TieDetailConstant INSTANCE = new TieDetailConstant();
    }

    public static TieDetailConstant getInstance() {
        return TieDetailHelper.INSTANCE;
    }


    //论坛详情的三种状态

    //分享帖子
    public final String SHARE = "detail";
    //论坛管理
    public final String TIE_MANAGER = "manager";
    //待审帖子
    public final String TIE_APPLY = "apply_tie";
    //待审回帖
    public final String APPLY_BACK_TIE = "tie_clock";
    //锁定中
    public final String IS_CLOCK = "open_clock";


    //1=主贴审核通过，2锁定主贴,3=回帖审核通过，4=解锁帖子
    public final String CHECK_MAIN_TIE = "1";
    public final String CLOCK_TIE = "2";
    public final String CHECK_BACK_TIE = "3";
    public final String UN_CLOCK_TIE = "4";

    //我的评论
    public final String MINE_COMMON = "my_common";

    //回复我的
    public final String BACK_MINE = "back_mine";

}
