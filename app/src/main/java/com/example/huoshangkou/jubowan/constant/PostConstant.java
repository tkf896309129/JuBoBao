package com.example.huoshangkou.jubowan.constant;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.constant
 * 类名：PostConstant
 * 描述：关键字常量
 * 创建时间：2017-02-22  10:32
 */

public class PostConstant {

    private static class PostHelper {
        private static PostConstant INSTANCE = new PostConstant();
    }

    public static PostConstant getInstance() {
        return PostHelper.INSTANCE;
    }

    //考勤管理获取子部门
    public final String GET_CHILD_GROUP = "GetSubordinateDepartmentList/?";
    public final String ADD_MANAGEMENT = "AddManagement/?";

    //知会已读
    public final String NOTIFY_ALERADY = "SetNotifyAlreadyRead/?";

    //设为管理员
    public final String SET_ADMINSTORS = "SetAdministrators/?";

    //组织架构
    public final String GROUP_PRO = "GetDepList/?";
    public final String GROUP_DESIGN_PRO = "GetDesignatedPersonnel/?";

    //免息券
    public final String GET_USING_COUPON = "GetUsingCoupon/?";
    public final String GET_All_USING_COUPON = "GetUserAllCoupon/?";

    //获取银行列表
    public final String GET_IOUS_BANK = "GetIousBank/?";
    public final String GET_IOUS_BAND_ADDRESS = "GetIousBandAddress/?";

    //获取协议
    public final String IOUS_AGREEMENT_URL = "GetIousAgreementUrl/?";

    //服务费与逾期费计算接口
    public final String COST_CALCULATION = "CostCalculation/?";

    //提额验证
    public final String TI_E_QUA = "RaiseTheAmount/?";

    //白条审批
    public final String BT_CHECK = "UpdateApproval_1/?";

    //白条支付
    public final String BT_PAY = "IousPay/?";

    //白条还款
    public final String REPAYMENT = "IousRepayment/?";

    //申请资料提交
    public final String SUBMISSION = "Submission/?";

    //还款记录
    public final String BACK_RECORD = "IousRepaymentHistory/?";

    //聚玻白条详情
    public final String BORROW_RECORD_DETAIL = "IousBorrowRecordDetail/?";

    //授信记录
    public final String APPLY_RECORD = "JBIousApplyRecord/?";

    //使用记录
    public final String BORROWING_RECORD = "IousBorrowingRecord/?";

    //白条资格获取
    public final String BT_QUA_GET = "IousQualificationsChecked/?";

    //获取维修设备首页
    public final String GET_REPAIR_INDEX = "GetWxMaintainIndex/?";

    //获取设备信息
    public final String GET_MAINTAIN_ALL_CLASS = "GetMaintainAllClass/?";

    //运营中心
    public final String GET_YYZX = "GetYYZX/?";

    //获取未审批个数
    public final String GETSP_NUMBER = "GetSP_Number/?";
    public final String GETSP_NUMBERS = "GetSP_Numbers/?";

    //提交委托书
    public final String DELEG_ED = "DelegEd/?";

    public final String SERVE_INDEX = "ServeIndex/?";

    //生成委托书
    public final String ADD_DELEG = "AddDeleg/?";

    //提交个体工商户认证信息
    public final String SET_PB = "SetPB/?";

    //获取个体工商户认证信息
    public final String GET_PB = "GetPB/?";

    //报警名单
    public final String GET_BAO_JING = "GetBaoJing/?";

    //获取原片厂
    public final String GET_YUAN_FACTORY = "GetYPfactory/?";
    public final String GET_JG_FACTORY = "GetJGfactory/?";

    public final String GET_PAYING_CUSTOMER = "GetPayingCustomerInfo";

    //获取信息申请列表
    public final String GET_RZ_ORDER = "GetRZOrder/?";
    public final String GET_RZ_DUE_BILL = "GetRZDueBill/?";

    //待支付订单列表
    public final String WAIT_PAY_ORDER_LSIT = "WaitPayOrderList/?";

    //单个产品下单
    public final String SUBMITION_PJ = "SubmitPeijian/?";

    //获取购物车列表数据
    public final String GET_PJ_CAR_LIST = "GetPeijianCatList/?";

    //添加配件购物车
    public final String ADD_PJ_CAR = "AddPeijianCat/?";

    //配件详情
    public final String GET_MODE_DETAIL = "GetModelDetail/?";

    //获取配件电压或电流列表
    public final String GET_VA_LIST = "GetModelVAList/?";

    //获取配件列表
    public final String GET_PJ_LIST = "GetModelList/?";

    //配件首页接口
    public final String GET_PJ_CLASS = "GetPJClass/?";

    //出款账户
    public final String BANK_LIST = "GetAccountBankList/?";

    //编辑垫付信息
    public final String ADD_ADVANCE = "AddAdvance/?";

    //垫付款
    public final String GET_ORDER_LIST_AD = "GetOrderListAd/?";

    //红包提现
    public final String RED_TI_XIAN = "PutTiXianRP/?";

    //明细列表
    public final String WALLET_LIST = "SelfRedWarsList/?";

    //账户余额
    public final String GET_PERSONAL_WALLET = "GetPersonalWallet/?";

    //红包列表接口
    public final String RED_PACKET_LIST = "RedWarsList/?";

    //抢红包接口
    public final String RED_WARS = "RedWars/?";

    //是否融资
    public final String IS_QUALIFICATION = "IsQualification/?";

    //人脸识别存储
    public final String ADD_FACE_INDENTIFY = "AddFaceIdentify/?";

    //借据详情
    public final String RZ_DUE_BILL_DETAIL = "RzDueBillDetail/?";

    //借款借据列表
    public final String GET_DUE_BILL_LIST = "GetDueBillList/?";

    //借款借据
    public final String ADD_DUE_BILL = "AddDueBill/?";

    //贷款详情接口
    public final String RZ_ORDER_DETAIL = "RzOrderDetail/?";

    //  聚玻贷列表接口
    public final String LOAN_LIST = "GetRzOrderList/?";

    //申请贷款
    public final String ADD_RZ_ORDER = "AddRzOrder/?";

    //获取身份证照片
    public final String GET_ID_PIC = "GetIdentityPic/?";

    //绑定手机号
    public final String BIND_PHONE_TYPE = "BindPhoneType/?";

    //获取服务表
    public final String GET_MAINTAIN_SERVE = "GetMaintainServe/?";

    //获取维修清单
    public final String GET_MAINTAIN_LIST_ENGINEER = "GetMaintainListEnginner/?";

    //提交服务表
    public final String ADD_MAINTAIN_SERVICE = "AddMaintainServe/?";

    //审批补签
    public final String RETROACTIVE_APPROVE = "RetroactiveApprove/?";

    //补签审批列表
    public final String SIGN_CHECK_LIST = "RetroactiveApprovalList/?";
    public final String SIGN_CHECK_LIST_ADMIN = "RetroactiveApprovalListAdmin/?";

    //补签记录列表
    public final String SIGN_RECORD_LIST = "RetroactiveList/?";

    //补签
    public final String ADD_SIGN = "Retroactive/?";

    //获取验证码
    public final String TWO_CODE = "RandNumber/?";

    //聚玻资讯
    public final String JUBO_NEWS = "atjubonews/?";

    //对主贴进行点赞
    public final String UPDATE_LT_POST_GIVEUP = "UpdateLTPostGiveUp/?";

    //检查是否有效
    public final String CHECK_OUT = "Checkout/?";

    //提交贷款审批
//    public final String ADD_BORROWING = "AddBorrowingToRZOrder/?";
    public final String ADD_BORROWING = "AddBorrowing/?";

    //微信支付
    public final String RETURN_APP_WXPAY = "ReturnAppWxpay/?";

    //改派链接
    public final String REDISTRIBUTE = "Redistribute/?";

    //对主贴进行点赞
    public final String ZAN_MAIN_TIE = "UpdateLTPostGiveUp/?";

    //搜索订单信息
    public final String SEARCH_ORDER_INFO = "GetAllOrderList/?";

    //搜索设备品类
    public final String GET_MAINTAIN_CLASS = "GetMaintainClass/?";

    //搜索设备品牌
    public final String GET_MAINTAIN_BRAND = "GetMaintainBrand/?";

    //搜索设备类型
    public final String GET_MAINTAIN_TYPE = "GetMaintainType/?";

    //搜索设备列表信息
    public final String GET_MAINTAIN_PRODUCT = "GetMaintainProduct/?";

    //确认收货
    public final String CONFIRM_RECEPIT = "ConfirmReceipt/?";

    //佣金提现
    public final String PUT_TI_XIAN = "PutTiXian/?";

    //设置昵称
    public final String SET_NICK = "UpdateNickName/?";

    //获取佣金
    public final String SHOW_MONEY = "ShowMoney/?";

    //提现记录
    public final String GET_TIXIAN_LIST = "GetTixianList/?";

    //添加子账号
    public final String ADD_CHILD_ACCOUNT = "AddUserChild/?";

    //添加新成员
    public final String ADD_EMPLOYEE = "AddEmployee/?";

    //获取当天考勤次数
    public final String GET_DAY_COUNT = "NewGetDayCount/?";

    //获取融资贷款个人默认信息
    public final String RZ_DEFAULT_MESSAGE = "GetInfor/?";

    //获取维修维保订单
    public final String GET_WX_REPAIR_ORDER = "GetWXOrderList/?";
    public final String GET_MAINTAIN_LIST = "GetMaintainList/?";

    //积分提现
    public final String CASH_POST_SCORE = "CashLTPostScore/?";

    //获取收货地址
    public final String GET_ADDRESS_LIST = "GetAddressList/?";

    //设置默认地址
    public final String SET_MOREN_ADDRESS = "SetDefAddr/?";

    //删除地址
    public final String DELETE_ADDRSS = "DelAddress/?";

    //编辑收货地址
    public final String EDIT_ADDRESS = "EdAddress/?";

    //关于聚玻宝
    public final String ABOUT_JUBO = "AboutJubobao?/";

    //发送验证码
    public final String SEND_CODE = "SendCode/?";
    public final String SEND_YQ_CODE = "SendMobileCode/?";

    //注册
    public final String REGISTER = "Register/?";

    //找回密码
    public final String FIND_PSW = "FindPwd/?";

    //登录功能
    public final String LOGIN = "Login/?";

    //意见反馈
    public final String COMMIT_FEED = "CommitFeed/?";

    //第三方登录
    public final String THREE_LOGIN = "OtherLogin/?";

    //重置密码
    public final String RESET_PSW = "SetPwd/?";

    //获取维修机械类别
    public final String GET_WX_TYPE = "GetWxMaintain/?";

    //搜索获取原片
    public final String GET_YUAN_DATA = "GetYuanPian/?";
    public final String GET_PRODUCT_DATA = "GetProductList/?";

    //搜索获取辅料
    public final String GET_FU_DATA = "GetFuliao/?";

    //获取品类
    public final String GET_YUAN_KIND = "GetYpClass/?";

    //获取银行
    public final String GET_BANK = "GetBank/?";

    //获取颜色
    public final String GET_YUAN_COLOR = "GetYpBrand/?";

    //企业融资贷款申请
    public final String ADD_RONGZI_APPLY = "AddRongZiApply/?";

    //获取品牌
    public final String GET_YUAN_BRAND = "GetYpBrand/?";
    public final String GET_YUAN_BRAND_NEW = "GetYpBrandNew/?";

    //获取膜系
    public final String GET_YUAN_MOXI = "GetYpMoxi/?";

    //获取级别
    public final String GET_YUAN_LEVEL = "GetYpLevel/?";
    public final String GET_YUAN_LEVEL_NEW = "GetYpLevelNew/?";

    //获取厚度
    public final String GET_YUAN_THICKNESS = "GetYpWeight/?";

    //获取规格
    public final String GET_YUAN_SATNDARD = "GetYpXy/?";

    //获取辅料类别
    public final String GET_FU_CLASS = "GetFuliaoClass/?";

    //获取辅料品牌
    public final String GET_FU_BRAND = "GetFuliaoBands/?";

    //获取辅料规格
    public final String GET_FU_STANDARD = "GetFuliaoGuiges/?";

    //维修工程师认证
    public final String REPAIR_APPROVE = "SetWXUserInfo/?";

    //获取用户信息
    public final String GET_USER_INFO = "GetUserInfo/?";

    //买家认证
    public final String BUY_APPROVE = "SetUserInfo/?";

    //业务员认证信息
    public final String YWY_APPROVE = "YwyRenzheng/?";

    //获取品牌、品类、地区
    public final String GET_YP_CBA = "GetYpCBA/?";

    //获取身份认证类型
    public final String SHOW_USER_TYPE = "ShowUserType/?";

    //获取业务员信息
    public final String GET_YWY_INFO = "GetYwyInfo/?";

    //获取维修工程师信息
    public final String GET_REPAIR_INFO = "GetWXUserInfo/?";

    //设置头像
    public final String SET_USER_PIC = "SetUserPic/?";

    //获取订单生产列表
    public final String GET_ORDER_LIST = "GetOrderListNew/?";

    //获取所有订单列表
    public final String GET_ALL_ORDER_LIST = "GetAllOrderList/?";

    //添加原片购物车
    public final String ADD_YUAN_CAR = "AddYpCat/?";

    //添加辅料购物车
    public final String ADD_FU_CAR = "AddFlCat/?";


    //获取 原片 辅材购物车
    public final String GET_SHOP_CAR = "GetYpCatList/?";
    public final String GET_CAR_LIST = "GetCatList/?";

    //提交申请
//    public final String ADD_APPLY = "AddApply/?";
    public final String ADD_APPLY = "AddApply_1/?";

    //获取审批人
    public final String GET_APPLY_MAN = "GetApprovalUser/?";
    public final String GET_EMPLOYEE_LIST = "GetEmployeeList/?";
    public final String GET_RELATE_COMPANY = "Get_Callback_Company/?";


    //获取审批信息
//    public final String GET_APPROVE_DATA = "GetApprovalListNew/?";
    public final String GET_APPROVE_DATA = "GetApprovalList_1/?";

    //获取申请列表
//    public final String GET_APPLY_LIST = "GetApplyList/?";
    public final String GET_APPLY_LIST = "GetApplyList_1/?";
    public final String GET_APPROVE_NOTIFY_LIST = "GetApprovalNotifyList/?";

    //获取银行列表
    public final String GET_BANK_LIST = "GetBankList/?";
    public final String GET_BANK_LIST_1 = "GetBankList_1/?";

    //删除银行信息
    public final String DELETE_BANK = "DelBank/?";

    //编辑银行信息
    public final String EDIT_BANK = "EdBank/?";

    //删除未审批订单
    public final String DELETE_APPLY = "DelApply/?";
    public final String DEL_BORROWING = "DelBorrowing/?";

    //获取审批详情数据
    public final String GET_APPROVE_DETAIL = "GetApprovalDetailNew/?";
    //    public final String GET_APPROVE_DETAIL_AD = "GetApprovalDetailAd/?";
    public final String GET_APPROVE_DETAIL_AD = "GetApprovalDetail_1/?";

    //获取申请信息
    public final String GET_APPLY_DETAIL = "GetApplyDetail_1/?";
//    public final String GET_APPLY_DETAIL = "GetApplyDetailNew/?";

    //进行审批
    public final String SET_CHECK = "UpdateApprovalNew/?";
    //    public final String SET_CHECK_AD = "UpdateApprovalAd/?";
    public final String SET_CHECK_AD = "UpdateApproval_1/?";

    //获取维修类别
    public final String GET_REPAIR_BRAND = "GetWxMaintainBrand/?";

    //获取维修类别
    public final String GET_REPAIR_CLASS = "GetWxMaintain/?";

    //提交故障申请
    public final String COMMIT_REPAIR_APPLY = "AddMaintain/?";

    //提交当前用户签到信息
    public final String PUT_USER_TRACK = "PutUserTrack/?";

    //获取当前用户签到信息
    public final String GET_USER_TRACK = "GetUserTrack/?";
    public final String VISIT_DETAIL = "VisitDetail/?";

    //获取当前用户下的子账号
    public final String GET_USER_CHILD = "GetUserChildByDep/?";

    //添加当前用户下的子账号
    public final String ADD_USER_CHILD = "AddUserChild/?";
    public final String ADD_DEPARTMENT = "AddDepartment/?";

    //添加当前用户（二级管理）下的子账号
    public final String ADD_USER_TWO_CHILD = "AddUserTwoChild/?";

    //删除二级菜单下的子成员
    public final String DEL_TWO_CHILD = "DelTwoChild/?";

    //获取论坛帖子
    public final String GET_FORUM_TIE = "GetLTPostList/?";

    //获取回帖
    public final String GET_REPLIES = "GetLTPostReplies/?";

    //获取回复的帖子
    public final String GET_REPLIES_TWO = "GetLTPostRepliesLists/?";

    //积分历史
    public final String SCORE_HISTORY = "GetLTIntegralHistory/?";

    //回帖
    public final String BACK_REPLIES = "ADDLTPostReplies/?";

    //收藏帖子
    public final String SAVE_TIE = "AddLTPostCollection/?";

    //对回帖进行点赞
    public final String SET_TIE_ZAN = "UpdateLTPostRepliesGiveUp/?";

    //获取品牌
    public final String GET_BRAND_LIST = "GetBrandList/?";


    //获取加工工艺
    public final String GET_GON_YI = "GetReGetJGGongyi/?";

    //获取工艺
    public final String GET_GY = "GetReGetGongyi/?";

    //获取膜系数据
    public final String GET_LOW_DATA = "GetBrandLowEMoxiList/?";

    //提交在线选型
    public final String PUT_ZB_ONLINE = "AddRequest/?";

    //帖子管理
    public final String TIE_MANAGER = "UpdateLTPostRepliesState/?";

    //删除回帖
    public final String DELETE_BACK_TIE = "DelLTPostReplies/?";

    //帖子搜索
    public final String SEARCH_TIE = "GetLTKeyWordList/?";

    //获取招标列表
    public final String GET_ZB_LIST = "GetRequestList/?";

    //搜索招标列表
    public final String SEARCH_ZB_LIST = "SearchRequestList/?";

    //获取招标信息
    public final String GET_ZB_DETAIL = "GetRequestDetail/?";

    //发表帖子
    public final String POST_TIE = "ADDLTPost/?";

    //Post请求发表帖子
    public final String POST_TYPE_TIE = "LuntanPost.ashx/?";


    //对回帖进行回帖
    public final String BACK_TIE_SECOND = "ADDLTRepliesReplies/?";

    //综合回帖
    public final String BACK_ALL_TIE = "ADDLTPostReplies/?";

    //获取我的帖子
    public final String GET_MINE_TIE = "GetMyLTPostList/?";

    //收藏帖子信息
    public final String GET_SAVE_TIE = "GetLTPostCollection/?";

    //添加收藏帖子
    public final String ADD_SAVE_TIE = "AddLTPostCollection/?";

    //改变论坛浏览量
    public final String CHANGE_VIEW_COUNT = "UpdateLTPostForPageView/?";

    //提交故障费用
    public final String ADD_MAINTAIN_PRICE = "AddMaintainPrice/?";

    //获取维修订单详情
    public final String GET_REPAIR_DETAIL = "GetMaintainDetail/?";

    public final String SHOW_MAINTAIN_PRICE = "ShowMaintainPrice/?";

    //获取订单详情
    public final String GET_ORDER_DETAIL = "GetOrderInfo/?";

    //根据订单号 获取垫付单位
    public final String GET_ORDER_DETAILS = "GetOrderDetails/?";

    //获取首页数据
    public final String INDEX = "Index/?";

    //新的首页数据
    public final String NEWINDEX = "NewIndex1/?";

    //提交评分
    public final String ADD_MAINTAIN_SCORE = "AddMaintainScore/?";

    //获取论坛积分
    public final String GET_POST_LEVEL = "GetLTPostLevel/?";

    //删除原贴
    public final String DELETE_TIE = "DelLTPost/?";

    //下单处理
    public final String SUBMIT_PRO = "SubmitProduct/?";
    public final String RE_SUBMIT_PRO = "ReSubmitProduct/?";

    //获取消息
    public final String GET_MESSAGE = "GetALLMessage/?";

    //删除订单
    public final String DELETE_ORDER = "DelOrder/?";

    //垫付款支付
    public final String MO_XI_ORDER_AD = "MoXiOrderAd/?";

    //与我相关--回复我的
    public final String GET_REPLY_SELF = "GetReplySelf/?";

    //与我相关--我的评论
    public final String GET_MY_COMMON = "GetMyComment/?";

    //删除消息
    public final String DELETE_MESSAGE = "DelMessageByID/?";

    //支付宝支付
    public final String ALIPAY = "ReturnAppAlipay/?";

    //线下付款
    public final String UPDATE_PAY_TRADE_NO = "UpdatePayTradeNo/?";

    //提交留言
    public final String ADD_MAIN_MESSAGE = "AddMaintainMessage/?";

}
