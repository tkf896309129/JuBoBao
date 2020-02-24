package com.example.huoshangkou.jubowan.constant;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.constant
 * 类名：ApproveConstant
 * 描述：
 * 创建时间：2017-03-17  11:25
 */

public class ApproveConstant {

    private static class ApproveHelper {
        private static ApproveConstant INSTANCE = new ApproveConstant();
    }

    public static ApproveConstant getInstance() {
        return ApproveHelper.INSTANCE;
    }

    //知会
    public final String MINE_ZH = "mineZh";

    //审批
    public final String APPROVE = "approve";

    //申请
    public final String APPLY = "apply";

    //用款
    public final String USE_MONEY = "money_use";

    //报销
    public final String BX_MONEY = "money_bx";


    //其他
    public final String OTHER = "other";

    //承兑
    public final String CD_MONEY = "money_cd";

    //提交申请类型  1=费用报销 2=用款申请 3=请假 4=出差 1006=承兑 1101=业务用款
    public final int FEED_BX = 1;
    public final int MONEY_USE = 2;
    public final int HOLIDAY = 3;
    public final int OUT_WORK = 4;
    public final int CD_TEAM = 1006;
    public final int OTHER_APPLY = 1100;
    public final int YW_MONEY = 1101;


    //审批参数
    public final int UN_CHECK = -1;
    public final int CHECK = 3;

    //申请参数

    //未申请完成
    public final int UN_APPLY = 0;
    //已申请完成
    public final int HAS_APPLY = 1;

    //我的知会

    //未申请完成
    public final int UN_ZH = -1;
    //已申请完成
    public final int HAS_ZH = 1;

    //查询信息类型1审批2知会3申请
    public final String CHECK_TYPE = "1";
    public final String ZH_TYPE = "2";
    public final String APPLY_TYPE = "3";

}
