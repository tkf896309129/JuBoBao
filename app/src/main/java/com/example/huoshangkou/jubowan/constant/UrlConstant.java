package com.example.huoshangkou.jubowan.constant;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.constant
 * 类名：UrlConstant
 * 描述：
 * 创建时间：2017-01-04  14:12
 */

public class UrlConstant {
    private static class UrlHelper {
        private static UrlConstant INSTANCE = new UrlConstant();
    }
    public static UrlConstant getInstance() {
        return UrlHelper.INSTANCE;
    }
    //图片上传地址
    public final String PHOTO_PATH = "https://api3.atjubo.com/uppic.ashx/?";
    //上传帖子
    public final String POST_TIE = "https://api3.atjubo.com/LuntanPost.ashx/?";
    //    public final String POST_SIGN = "https://192.168.10.115:25248/PutUserTrack_1.ashx/?";
    //上传服务单
    public final String POST_SERVICE = "https://api3.atjubo.com/MaintainServe.ashx/?";
    //域名链接
    public final String WEB_URL = "https://api3.atjubo.com/";
//        public final String WEB_URL = "http://192.168.1.120:83/api/";
//    public final String WEB_URL = "http://192.168.1.120/webapi/";
//                    public final String WEB_URL = "https://192.168.1.120/JuboAPI/";
    public final String HOME_URL = WEB_URL + "ServiceInterface/Home";

    //        public final String LOCAL_URL = "https://192.168.1.120/JuboAPI/atapi/";
//    public final String LOCAL_URL = "http://192.168.1.120/webapi/atapi/";
//    public final String POST_SIGN = "http://192.168.1.120/webapi/PutUserTrack_1.ashx/?";
//    public final String VISITOR_URL = "http://192.168.1.120:8800/WebService/WebAtjubo.asmx/VisitUrlList";
    public final String VISITOR_URL = "https://api3.atjubo.com/ServiceInterface/VisitUrlName.asmx/VisitUrlList";
    public final String POST_SIGN = "https://api3.atjubo.com/PutUserTrack_1.ashx/?";
    public final String LOCAL_URL = "https://api3.atjubo.com/atapi/";
    public final String URL = LOCAL_URL;
    //业务用款地址
    //    public final String YW_URL = "https://api3.atjubo.com/ServiceInterface/PaymentApproval.asmx/ ";
    public final String YW_URL = WEB_URL + "ServiceInterface/PaymentApproval.asmx/";
    //    public final String YW_URL = "https://192.168.10.120/JuboAPI/ServiceInterface/PaymentApproval.asmx/";
    public final String SERVICE_URL = "https://www.atjubo.com/serviceagree/webapp/views/JBW.aspx";
    //分享链接
    public final String SHARE_URL = "https://www.atjubo.com/serviceagree/webapp/views/BBS.aspx?id=";
    //设备详情连接
    public final String TOOL_UEL = WEB_URL + "pages/WebApp/views/MaintainProduct.aspx?id=";
    //音频文件上传
    public final String UP_VOICE_URL = WEB_URL + "ServiceInterface/VisitUrlName.asmx/UploadAudioFile";
    //聚玻学院
    public final String JU_SCHOOL_URL = WEB_URL + "ServiceInterface/JuboBao/JbCollege.asmx/";
    //图书内容
    public final String BOOK_DETAIL = WEB_URL + "pages/WebApp/jbschool/DigitalBooks.html?id=";
    //聚玻宝大牌专卖热线
    public final String JU_BIG_BRAND = WEB_URL + "ServiceInterface/JuboBao/HotSale.asmx/";
    //项目招标
    //    public final String NEW_ZB_URL = "https://api3.atjubo.com/ServiceInterface/Home/ProjectRequest.asmx/";
    //原片+辅材
    public final String YUAN_FU_URL = WEB_URL + "ServiceInterface/JuboBao/ProjectRequest.asmx/";
    public final String COUNTRY_URL = WEB_URL + "ServiceInterface/JuboBao/ThroughCTC.asmx/LoadJuboCTCIndex";
    //广告弹窗
    public final String ADV_URL = WEB_URL + "ServiceInterface/JuboBao/Advertisement.asmx/GetAdvertisement";
    //直通国检查询
    public final String COUNTRY_CHECK = WEB_URL + "ServiceInterface/JuboBao/ThroughCTC.asmx/";
    //审批Post
    public final String POST_APPLY_CHECK = WEB_URL + "ServiceInterface/JuboBao/ApprovalApply.asmx/AddApply";
    //通用审批
    public final String POST_COMMON_CHECK = WEB_URL + "ServiceInterface/JuboBao/ApprovalApply.asmx/";
    //通用审批详情
    public final String POST_COMMON_CHECK_DETAIL = WEB_URL + "ServiceInterface/JuboBao/ApprovalApply.asmx/QueryApprovalDetailApi";
    //审批设置
    public final String CHECK_SETTING = WEB_URL + "ServiceInterface/JuboBao/UserPattern.asmx/AddUserPattern";
    public final String CHECK_SETTING_GET = WEB_URL + "ServiceInterface/JuboBao/UserPattern.asmx/GetUserPattern";
    //风控报表
    public final String FENG_KONG_URL = WEB_URL + "ServiceInterface/JuboBao/UserAdditionalInfo.asmx/GetUserAdditionalTitle";
    //添加用户信息
    public final String ADD_FENG_KONG_PIC_URL = WEB_URL + "ServiceInterface/JuboBao/UserAdditionalInfo.asmx/AddUserAdditionalInfo";
    public final String GET_FENG_KONG_PIC_URL = WEB_URL + "ServiceInterface/JuboBao/UserAdditionalInfo.asmx/GetUserAdditionalInfo";
    //日程类型
    public final String DATE_TYPE_URL = WEB_URL + "ServiceInterface/JuboBao/ScheduleManage.asmx/GetScheduleType";
    //新增日程
    public final String ADD_DATE_TYPE_URL = WEB_URL + "ServiceInterface/JuboBao/ScheduleManage.asmx/AddSchedule";
    //获取日程
    public final String GET_DATE_TYPE_URL = WEB_URL + "ServiceInterface/JuboBao/ScheduleManage.asmx/GetScheduleList";
    public final String DELETE_DATA = WEB_URL + "ServiceInterface/JuboBao/ScheduleManage.asmx/RemoveSchedule";
    public final String GET_CUSTOMER = WEB_URL + "ServiceInterface/JuboBao/SaleManage.asmx/GetCustomerByCustomerId";
    //获取所有客户
    public final String GET_ALL_CUSTOMER = WEB_URL + "ServiceInterface/JuboBao/SaleManage.asmx/GetCutomers";
    //客户管理
    public final String CUSTOMER_MANAGE_URL = WEB_URL + "ServiceInterface/JuboBao/SaleManage.asmx/";
    public final String CUSTOMER_DATA_URL = WEB_URL + "ServiceInterface/JuboBao/ScheduleManage.asmx/";
    //数据中心
//    public final String CUSTOMER_DATA_CENTER_URL = WEB_URL + "ServiceInterface/JuboBao/SaleDataAnalysis.asmx/";
    public final String CUSTOMER_DATA_CENTER_URL = WEB_URL + "ServiceInterface/JuboBao/CRMSaleDataAnalysis.asmx/";
    //垫付款
    public final String PADPAY_MANAGE = WEB_URL + "ServiceInterface/PadPaymentManage.asmx/";
    public final String ABOUT_US_MANAGE = WEB_URL + "ServiceInterface/AtjuboUserManager/UserJurisdictionService.asmx/";
    //内部往来款
    public final String IN_DOOR_MONEY = WEB_URL + "ServiceInterface/DealingPayment.asmx/";
    //制度通知 https://192.168.1.120/JuboAPI/ServiceInterface/JuboBao
    public final String COMPANY_INFORM = WEB_URL + "ServiceInterface/JuboBao/AdministrationManager.asmx/";
    //通知详情  https://www.atjubo.com/serviceagree/NoticeLearning/SkillsLearning.html?id=4&userid=10445
    public final String INFORM_DETAIL = WEB_URL + "serviceagree/NoticeLearning/";
    //权限管理
    public final String QUA_MENU = WEB_URL + "ServiceInterface/JuboBao/AppFunctionRight.asmx/";
    //获取版本信息
    public final String GET_VERSION = WEB_URL + "ServiceInterface/JuboBao/APPVersions.asmx/GetAPPVersion";
    //获取整合审批列表
    public final String APPROVE_LIST_URL = WEB_URL + "ServiceInterface/JuboBao/ApprovalApply.asmx/QueryApprovalDataApi";
    //筛选
    public final String CHOOSE_YL = WEB_URL + "ServiceInterface/JuboBao/RawmaterialManager.asmx/";
    //确认收货签章
    public final String CONFIRM_SIGN = "http://www.atjubo.com/JBOrderAfterSale/JBOrderAfterSale/AppConfirmContract?orderid=";
}
