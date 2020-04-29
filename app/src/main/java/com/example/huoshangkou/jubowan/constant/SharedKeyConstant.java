package com.example.huoshangkou.jubowan.constant;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.constant
 * 类名：SharedKeyConstant
 * 描述：key常量类
 * 创建时间：2017-02-22  14:17
 */

public class SharedKeyConstant {

    private static class SharedHelper {
        private static SharedKeyConstant INSTANCE = new SharedKeyConstant();//pra
    }

    public static SharedKeyConstant getInstance() {
        return SharedHelper.INSTANCE;
    }
    //夜间模式
    public final String NIGHT_DEFAULT = "night_default";
    public final String MINE_DATA = "mine_data";
    public final String NIGHT_DEFAULT_CHANGE = "night_default_change";
    public final String MINE_NIGHT_DEFAULT_CHANGE = "mine_night_default_change";
    //地址删除
    public final String ADDRESS_DELETE = "address_delete";
    //请求公钥
    public final String PUB_POST_KEY ="public_post_key";
    //桌面角标
    public final String NOTIY_DIALOG = "notiy_dialog";
    //首页数据
    public final String FACE = "face";
    public final String HOME_DATA = "home_data";
    public final String DATA_VERSION = "data_version";
    //身份信息
    public final String KEY_MAN_ID = "key_man_id";
    //会议抄送人
    public final String METING_CS_MAN = "meting_cs_man";
    //客服人员
    public final String KF_MEMBER = "kf_member";

    public final String FACE_KEY = "app_key";

    public final String FACE_SECRECT = "app_secrect";

    //刷新借贷款
    public final String INIT_LOAN = "init_loan";

    //设备唯一标识
    public final String ONLY_ONE_DEVICE = "one_device";

    //服务清单
    public final String SERVICE_LIST = "service_list";

    //判断是否是登录进入首页
    public final String IS_LOGIN_TO_MAIN = "login_to_main";

    //设备刷新
    public final String TOOL_REFRESH = "tool_refresh";

    //ruleUrl
    public final String RULE_URL = "rule_url";

    //是否刷新买东西的界面
    public final String INIT_BUY = "init_buy";

    //刷新维修维保界面
    public final String REPAIR = "repair";

    //当前手机号
    public final String CURRENT_PHONE = "current_phone";


    //编辑、添加地址
    public final String ADD_ADDRESS = "addAddress";

    //版本号
    public final String VERSION_CODD = "version_code";

    //版本名称
    public final String VERSION_PATH = "version_path";

    //版本更新说明
    public final String VERSION_INTRO = "version_intro";

    //验证码时间
    public final String CODE_TIME = "code_time";

    //验证码保存
    public final String SAVE_CODE = "save_code";

    //设备号
    public final String DEVICE_TOKEN = "device_token";

    //保存发送验证码的类别
    public final String CODE_TYPE = "code_type";

    //记录登录状态
    public final String LOGIN_STATE = "login_state";

    //地址
    public final String ADDRESS = "Address";

    //公司名称
    public final String COMPANY_NAME = "CompanyName";
    //头像
    public final String HEAD_PIC = "HeadPic";
    //ID
    public final String ID = "ID";
    public final String USER_MANAGE_ID = "MANAGE_ID";
    public final String USER_MANAGE_PHONE_ID = "MANAGE_PHONE_ID";
    public final String PUSH = "push";
    //聚玻等级
    public final String JUBO_LEVEL = "JuboLevel";
    //聚玻币
    public final String JUBO_BI = "Jubobi";
    //联系人头像
    public final String LINK_MAN_CARD_PIC = "LinkManCardPic";
    //登录类型
    public final String LOGIN_TYPE = "LoginType";
    //昵称
    public final String NICK_NAME = "Nickname";
    //父ID
    public final String PARENT_ID = "ParentID";
    //营业执照
    public final String PIC_YYZZ = "PicYyzz";
    //QQOpenid
    public final String QQ_OPEN_ID = "QQOpenID";
    //真实姓名
    public final String REAL_NAME = "RealName";
    //介绍
    public final String REMARK = "Remark";
    //电话
    public final String TEL = "Tel";

    public final String TWO_PARENT_ID = "TwoParentID";
    public final String USER_CARD_NO = "UserCardNo";
    public final String USER_STATE = "UserState";
    public final String USER_TYPE = "UserType";
    //微博id
    public final String WB_OPEN_ID = "WbOpenID";
    //微信id
    public final String WX_OPEN_ID = "WxOpenID";
    public final String WX_JIQI_NAME = "WxjiqiName";
    public final String ZZ_JG_NO = "ZzjgNo";
    public final String REOLE_ID = "RoleID";

    //是否可以垫付款
    public final String IS_TRADE = "is_trade";
    public final String ON_CODE = "on_code";


    //保存厚度的位置
    public final String THICK_POSITION = "thick_position";
    public final String THICK_VALUE = "thick_value";

    //记录当前的语言
    public final String NOW_LANGUAGE = "current_language";


    //默认地址本地保存
    public final String DEFAULT_ADDRESS_ID = "default_address_id";
    public final String DEFAULT_ADDRESS = "default_address";
    public final String DEFAULT_RECEIVE = "default_receive";
    public final String DEFAULT_LINK_PHONE = "default_link_phone";


    //获取审批人id  和  名字
    public final String APPROVE_ID = "approve_id";
    public final String APPROVE_NAME = "approve_name";


    //编辑银行信息
    public final String BANK_EDIT = "bank_edit";

    //审批是否同意
    public final String APPROVE_AGREE = "approve_agree";

    //是否需要重新获取成员列表信息
    public final String GET_MEMBER_LIST = "get_member_list";

    //手机型号
    public final String PHOE_TYPE = "phone_type";

    //通知数
    public final String NOTIFY_NUMS = "notify_nums";

    //是否是第一次登录
    public final String FIRST_LOGIN = "first_login";

    //是否已经经过审核
    public final String PASS_CHECK = "pass_check";

    //维修清单获取数据
    public final String REPAIR_PRICE_DATE = "repair_price";

    //通知点击事件
    public final String NOTIFY_CLICK = "notify_click";

    //删除订单
    public final String DELETE_ORDER = "delete_order";

    //是否刷新订单列表数据
    public final String IS_INIT_ORDER = "is_init_order_list";

    //绑定银行卡信息
    public final String BIND_BANK_MSG = "bind_bank_message";

}
