package com.example.huoshangkou.jubowan.constant;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.constant
 * 类名：FieldConstant
 * 描述：字段常量
 * 创建时间：2017-02-22  10:33
 */

public class FieldConstant {

    private static class FieldHelper {
        private static FieldConstant INSTANCE = new FieldConstant();
    }

    public static FieldConstant getInstance() {
        return FieldHelper.INSTANCE;
    }
    //是否打出
    public final String IS_DC = "NeedTransfer";
    public final String VERSION_NO = "VersionNo";
    //购物车类型
    public final String CAR_TYPE = "CatType";
    public final String CATEFORY_NAME = "CategoryName";
    //贸易商性质
    public final String SUPPLIER_NATURE = "SupplierNature";
    //收款公司
    public final String COLLECT_COMPANY = "CollectionCompany";
    //是否有发票
    public final String IS_HAVE_INVOINCE = "IsHaveInvoice";
    //打出日期
    public final String TRANSFER_DATE = "TransferDate";
    //运营人员
    public final String OPERATION_PERSONEL = "OperationPersonnel";

    //部门id
    public final String GROUP_ID = "DepId";

    //协议编号
    public final String AGREEMENT_CODE = "AgreementCode";

    //收款账户id
    public final String ACCOUNT_BANK_ID = "AccountBankID";

    public final String MOBIE_CODE = "MobileCode";
    public final String COUPON_ID = "CouponID";

    //还款金额
    public final String MONEY = "Money";
    public final String APPLY_UNIT = "ApplyUnit";
    public final String LEGAL_PERSON = "LegalPerson";
    public final String ID_CARD = "IdCard";
    public final String SCALE = "Scale";
    public final String PERSON_SIT = "PersonnelSituation";

    //本次还款金额
    public final String REPAY_PRINCIPAL = "RepaymentPrincipal";

    //签名照片地址
    public final String AUTH_PIC = "AutographPic";

    //支行
    public final String BRANCH = "Branch";

    public final String PHONE_NUMBER = "PhoneNumber";

    //运营中心
    public final String YYZX = "YYZX";

    //打款方向
    public final String REMIT_DIRECITON = "RemitDircetion";

    //是否打出  0 需要打出 1不打出（int）
    public final String IS_DACHU = "IsDaChu";
    public final String ORDER_TABLE = "OrderTable";
    public final String IOUS_AMOUNT = "IousAmount";

    //账户类型
    public final String ACCOUNT_TYPE = "AccountType";

    //款项用途
    public final String KX_YONGTU = "KuanXiangWay";

    //入账性质
    public final String RZ_XINGZHI = "RuZhangXingZhi";

    //入账金额
    public final String RZ_PRICE = "RuZhangPrice";

    //入款方式
    public final String RK_WAY = "RuKuanWay";

    //入账客户
    public final String RZ_COMPANY = "RuZhangCompany";


    public final String TOKEN = "token";

    //订单编号
    public final String DE_COMPANY = "DeCompany";

    //到货地址
    public final String DAOHUO = "DaoHuo";

    //提货地址
    public final String TIHUO = "TiHuo";

    //联系电话
    public final String DRIVER_TEL = "DriverTel";

    //车牌
    public final String DRIVER_CHE_PAI = "DriverChePai";

    //司机姓名
    public final String DRIVER_NAME = "DriverName";

    public final String VISIT_COMPANT_ID = "Visit_Company_ID";
    public final String VISIT_COMPANT = "Visit_Company";

    //纳税人识别码
    public final String SOCIAL_CODE = "SocialCreCo";

    //  发票抬头
    public final String INVOINCE_NISE = "InvoiceNise";

    //发票抬头id
    public final String INVOINCE_ID = "InvoiceID";

    //购买数量
    public final String NUMBER = "Number";

    //商品id
    public final String PRODUCT_ID = "ProductID";
    public final String PRODUCT_IDS = "Product_id";

    //规格id
    public final String GUI_GE_ID = "GuiGeID";

    //电流id
    public final String VAID = "VAID";

    //型号id
    public final String MODE_ID = "ModelID";

    //页码
    public final String PAGE_INDEX = "PageIndex";

    //shijian
    public final String TIMES = "Times";

    //抄送id
    public final String CS_ID = "CopyUserID";

    //重走流程
    public final String IS_OPEN = "IsReopen";
    //查看订单
    public final String ADVANCE_ORDER_ID = "AdvanceOrderID";
    //销售总额   一个币两千 我靠
    public final String SELL_PRICE = "SellPrice";
    //现金或承兑
    public final String SELL_CASH_OR_ACCEPT = "SellCashOrAccept";
    //是否开票
    public final String INVOINCE = "Invoice";
    //垫付情况
    public final String ADVANCE = "Advance";
    //采购总额
    public final String PURCHASE_PRICE = "PurchasePrice";
    //审批银行id
    public final String SP_BANK_ID = "SpBankID";
    //款项用途
    public final String FUND_WAY = "FundWay";
    //现金或者承兑
    public final String PURCHASE_CASH_OR_ACCEPT = "PurchaseCashOrAccept";
    //打款方向
    public final String REMIT_DIRCETION = "RemitDircetion";
    public final String UP_YAUN_ID = "UpYuanpianID";
    //出款账户
    public final String REMIT_ACCOUNT = "RemitAccount";
    //收款账户
    public final String PROCEEDS_ACCOUNT = "ProceedsAccount";
    //盈利
    public final String PROFIT = "Profit";
    public final String AMOUNT_OF_PAYMENT = "AmountOfPayment";
    //运费总额
    public final String FREIGHT_TOTAL_PRICE = "FreightTotalPrice";
    //销售id
    public final String SELL_USER_ID = "SellUserID";
    //相似度
    public final String SIMILARY = "Similarity";
    //人脸识别图片
    public final String FACE_PIC = "FacePic";
    //身份证照片
    public final String IDENTIFY_PIC = "IdentityPic";
    //视频缩略图
    public final String MEDIA_PREVIEW = "MediaPreview";
    //借款到日期
    public final String AUDIT_TIME_YEA = "AuditTimeYea";
    //视频附件
    public final String ENCLOSURE = "Enclosure";
    public final String ACCOUNT_NAME = "AccountName";
    public final String OPENING_BANK = "OpeningBank";
    public final String ACCOUNT_NUMBER = "AccountNumber";
    //入账时间
    public final String AUDIT_TIME = "AuditTime";
    //借款金额
    public final String LOAN_AMOUNT = "LoanAmount";

    //借款利率
    public final String LOAN_RATE = "InterestRate";

    //借款用途
    public final String USE_OF_LOAN = "UsageOfLoan";

    //电话
    public final String LOAN_TEL = "LoanTel";

    //借款人
    public final String LOAN_LINK_MAN = "LoanLinkMan";

    //合同号
    public final String CONTRACT_NO = "ContractNO";

    //融资附件
    public final String RZ_WENJIAN = "RzWenjian";

    //营业执照
    public final String RZ_PIC_YYZZ = "RzPicyyzz";

    //计划还款日
    public final String RZ_DATE_MONEY = "RzDateMoneyString";

    //还款方式
    public final String REFROM_TYPE = "ReFromType";

    //还款资金来源
    public final String REFROM_MONEY = "ReFromMoney";

    //借款用途
    public final String RZ_YONG_TU = "RzYongtu";

    //申请大写金额
    public final String RZ_LARGE_MONEY = "RzMoneyCapital";

    //申请借款金额
    public final String RZ_MONEY = "RzMoney";

    //结欠利息
    public final String OLD_LOAN_LIXI = "OldDaiLixi";

    //逾期贷款余额
    public final String OLD_YUQI_LOAN = "OldDaiyukuan";

    //原欠贷款余额
    public final String OLD_LOAN = "OldDaikuan";

    //规模
    public final String GUI_MO = "Guimo";

    //借款期限开始
    public final String BEGIN_DATE = "BeginDate";
    //借款期限结束
    public final String END_DATE = "EndDate";

    //公司现有设备
    public final String SHE_BEIS = "Shebeis";

    //出借人
    public final String RZLENDER = "RzLender";

    //服务表ID
    public final String SERVE_ID = "ServeID";

    //订单状态
    public final String ORDER_STATE = "orderState";

    //配件详情
    public final String ACCESSORY_DETAIL = "AccessoryDetail";

    //服务员出发时间
    public final String SERVE_SETUP_TIME = "ServeSetupTime";

    //售后服务人员
    public final String SERVE_STAFF = "ServeStaff";

    //费用明细
    public final String SERVE_MONEY = "ServeMoney";

    //服务项目
    public final String SERVE_ITEM = "ServeItem";

    //服务厂家名称
    public final String SERVE_COMPANY_NAME = "ServeCompanyName";

    //服务员到达时间
    public final String SERVICE_ARRIVE_TIME = "ServeArriveTime";


    //采购时间
    public final String PURCHASE_TIME = "PurchaseTime";

    //设备明细
    public final String PRODUCT_DETAIL = "ProductDetail";

    //设备品牌
    public final String PRODUCT_BRAND = "ProductBrand";

    //服务中出现的问题及解决方法
    public final String PROBLEMS_METHODS = "ProblemsMethods";

    //电费说明
    public final String ELECTRIC_EXPLAIN = "ElectricExplain";

    //月产能说明
    public final String CAPACITY_MON = "CapacityMon";

    //补签id
    public final String TRACK_ID = "TrackID";

    //审批状态
    public final String APPROVE_OVER = "ApprovalOver";

    //总结
    public final String OPINION_SIGNATURE = "OpinionSignature";

    //审批类型
    public final String APPROVAL_TYPE = "ApprovalTypeID";
    public final String APPROVAL_GAI_TYPE = "ApprovalType";

    //借款人
    public final String BORROWS = "Borrowers";
    public final String RZ_ORDER_ID = "RzOrderID";

    //经营时间
    public final String OPERATING_TIME = "OperatingTime";

    //借款用途
    public final String LOAN_PURPOSES = "LoanPurposes";

    //借款期限
    public final String LOAN_PERIOD = "LoanPeriod";

    //借款金额
    public final String LOAN = "Loan";

    //相关资料
    public final String DATA_PICS = "DataPics";

    //联系人
    public final String CONTACT = "Contact";

    //借款类型
    public final String BORROWS_TYPE = "BorrowersType";

    //地址
    public final String ADDR = "Adder";

    //支付类型
    public final String PAY_TYPE = "GetType";

    //微信支付id
    public final String WX_PAY_ID = "wx_out_trade_no";

    //微信支付金额
    public final String WX_TOTAL_FEE = "wx_total_fee";

    //类型id
    public final String CLASS_ID = "ClassID";

    //留言信息
    public final String MESSAGE_TXT = "MessageText";

    //产品id
    public final String PRODUCE_ID = "ProductID";

    //电话
    public final String LOGIN_NAME = "LoginName";

    //支付宝账号
    public final String BANK_NO = "BankNo";

    //角色
    public final String ROLE = "Role";
    public final String ROLE_IDS = "RoleId";


    //贷款银行
    public final String LOCAN_BANK = "LoanBank";

    //法人身份证
    public final String PIC_FAREN = "PicFaren";

    //贷款金额
    public final String LOAN_MONEY = "LoanAmounts";

    //发帖标题
    public final String POST_TIE_TITLE = "PostTitle";

    //发帖内容
    public final String POST_TEXT = "PostText";

    //维修维保分
    public final String JU_BOBI_SCORE = "JobobiScore";

    //用户ID
    public final String USER_ID = "UserID";
    public final String MANAGE_USER_ID = "ManageUserID";
    public final String ADMIN_ID = "AdminId";

    //地址ID
    public final String ADDRESS_ID = "AddressID";

    //设备规格
    public final String GUI_GE = "GuiGe";

    //详细地址
    public final String DETAIL_ADDRESS = "DetailAddress";

    //收货人
    public final String LINK_MAN = "linkman";

    //联系方式
    public final String LINK_TEL = "linktel";

    //省份地区
    public final String PROVINCE = "Provinces";
    public final String PROVINCE_NO_S = "Provice";
    public final String CITY = "City";

    //手机号
    public final String TEL = "tel";

    //验证码
    public final String CODE = "code";

    //密码
    public final String PSW = "pwd";
    public final String AREA_COED = "areacode";
    public final String MARK = "mark";

    //友盟ID
    public final String UMENG_ID = "umengid";

    //新密码
    public final String NEW_PSW = "newpwd";

    //反馈意见
    public final String FEED_TXT = "Feedtxt";

    //身份类型
    public final String TYPE_ID = "TypeID";

    //登录类型
    public final String LOGIN_TYPE = "LoginType";

    //登录获取的OpenId
    public final String OPEN_ID = "OpenID";

    //头像
    public final String HEAD_PIC = "HeadPic";

    //昵称
    public final String NICK_NAME = "nickname";

    //品牌名称
    public final String BRAND_NAME = "Brandname";

    //厚度
    public final String WEIGHT = "Weight";

    //是否有色
    public final String IS_YOUSE = "Isyouse";

    //类别名称
    public final String CLASS_NAME = "Classname";

    //等级
    public final String LEVEL = "Levelname";
    public final String LEVEL_NAME = "LevelName";
    public final String LEVELS = "Level";

    //厚度
    public final String THICK = "Weight";

    //规格
    public final String XY = "Xy";

    //颜色
    public final String COLOR_NAME = "Colorname";

    //辅料规格
    public final String STANDARD_FU = "guigename";
    public final String PRODUCT_TYPE_DEC = "ProductType";

    //购买数量
    public final String BUY_COUNT = "buycount";
    public final String PRODUCT_TYPE = "productType";

    //身份证号
    public final String CARD_NO = "Cardno";

    //个人工作证照片
    public final String WORK_PIC = "WorkPic";

    //维修机械类别
    public final String REPIAR_TYPE = "WxjiqiNameID";

    //维修天数
    public final String REPAIR_DAYS = "MaintainDays";

    //交通工具
    public final String TRANS_TYPE = "Tiansportation";

    //往来费用
    public final String TIES_PRICE = "TiesPrice";

    //食宿费用
    public final String FOOD_PRICE = "AccommodationPrice";

    //其他费用
    public final String OTHER_PRICE = "OtherPrice";

    //总费用
    public final String TOTAL_PRICE = "TotalPrice";

    //地址
    public final String REPAIR_ADDRESS_ID = "addressID";

    //地址id
    public final String ADDR_ID = "addrid";

    //特长
    public final String RESUME = "resume";

    //工作经历
    public final String WORK_EXP = "descript";

    //身份认证id
    public final String ROLE_ID = "Roleid";
    public final String DEP_ID = "DepId";
    public final String DEP_IDS = "DepIds";
    public final String DEP_NAME = "DepName";

    //公司名称
    public final String COMPANY = "company";

    //公司地址
    public final String ADDRESS = "address";

    //邮箱地址
    public final String E_MAIL = "email";
    public final String EMAIL = "Email";

    //公司官网
    public final String WEB_URL = "weburl";

    //用户身份证照片
    public final String LINK_MAN_CARD = "linkmancardpic";

    public final String LINK_CARD_NO = "linkmancardno";

    //工作证照片
    public final String YYZZ_PIC = "picyyzz";

    //负责区域
    public final String AREA_NAME = "Areaname";

    public final String AREAS = "areas";

    //公司名称
    public final String COMPANY_NAME = "Companyname";
    public final String COMPANY_BIG_NAME = "CompanyName";

    //真实姓名
    public final String REAL_NAME = "Realname";

    //身份证照片
    public final String CARD_PIC = "Cardpic";

    //组织机构代码
    public final String JG_CODE = "zzjgno";


    //搜索排序   0默认排序 1价格排序 2销量排序
    public final String SORT = "Sort";

    //设置头像
    public final String PIC = "pic";

    //订单状态
    public final String STATE = "OrderState";
    public final String STATES = "State";

    //订单id
    public final String ID = "Id";

    //添加数量
    public final String TO_NUM = "tonum";

    //类型
    public final String ADD_CAR_TYPE = "a";


    //审批类型
    public final String APPROVE_TYPE_ID = "ApprovalTypeID";
    public final String EDIT_TYPE_ID = "EditOrAddType";

    //  实际类型
    public final String TYPE_NAME = "TypeName";

    //金额
    public final String TYPE_PRICE = "TypePrice";
    public final String TYPE_PRICES = "TypePrices";

    //时间
    public final String TIME = "Time";
    //更换设备
    public final String IS_RESET_EQUIMENT = "IsResetEquipment";

    //开始时间
    public final String START_TIME = "StartTime";

    //结束时间
    public final String END_TIME = "EndTime";

    //开始时间段
    public final String START_TIMES = "Startslot";

    //结束时间段
    public final String END_TIMES = "Endslot";

    //地址
    public final String APPROVE_ADDRESS = "Address";

    //是否有发票
    public final String INVOICE = "Invoice";

    //说明
    public final String REMARK = "remark";
    public final String REMARK_BIG = "Remark";

    //图片
    public final String PICS = "Pics";

    //上游原片厂
    public final String UP_YUAN_ID = "UpYuanpianID";

    //请假时长
    public final String TIME_SPAN = "TimeSpan";

    //抄送id
    public final String COPY_USER_ID = "CopyUserId";

    //审批人id
    public final String APPROVE_USER_ID = "ApprovalUserID";

    //审批结果
    public final String APPROVE_RESULT = "ApprovalOver";

    //申请状态
    public final String APPLY_STATE = "ApprovalState";

    //银行名称
    public final String BANK_NAME = "BankName";

    //银行账号
    public final String BANK_ACCOUNT = "BankAccount";

    //审批id
    public final String APPROVE_ID = "ApprovalID";

    //新的审批人id
    public final String NEW_APPROVE_ID = "ApprovalUserID";

    //审批图片
    public final String APPROVE_PIC = "ApprovalPic";

    //审批说明
    public final String APPROVE_REMARK = "Approvalremark";

    //故障编号
    public final String MAINTAIN_ID = "MaintainID";

    public final String MAINTAINER_ID = "MaintainerID";

    //品牌编号
    public final String BRAND_ID = "BrandID";

    //购买日期
    public final String BUY_DATA = "BuyDate";


    //工程师等级
    public final String USER_GRADE = "UserGrade";

    //创建时间
    public final String CREATE_TIME = "createtime";

    //月数
    public final String MONTHS = "Months";

    //漏签时间
    public final String LEAK_TIME = "LeakTime";

    //x 个人所在位置的地球经纬度
    public final String X = "x";

    //y 个人所在位置的地球经纬度
    public final String Y = "y";

    //论坛分类id
    public final String POST_TIE_ID = "posttypeid";

    //页数
    public final String PAGE_SIZE = "PageSize";

    //帖子状态
    public final String POST_TIE_STATE = "PostState";

    //论坛排序
    public final String RANK = "Rank";

    //帖子id
    public final String POST_ID = "Postid";

    //回帖状态
    public final String REPLIES_STATE = "RepliesState";

    //回复内容
    public final String REPLIES_TXT = "RepliesText";


    //回帖id
    public final String REPLIES_ID = "RepliesID";

    // 取消点赞0，点赞1
    public final String TYPE = "Type";
    public final String AUTOGRAPH = "autograph";

    //父节点id
    public final String FATHER_ID = "FatherID";

    //项目名称
    public final String PROJECT_NAME = "Projectname";

    //项目类型
    public final String PROJECT_TYPE = "ProjectType";

    //范围小
    public final String SMALL_AREA = "Area";

    //范围大
    public final String MAX_AREA = "MaxArea";

    //周期
    public final String NEED_DAYS = "NeedDay";

    //项目地址
    public final String PROJECT_ADDRESS = "ProjectAddress";

    //需要案例
    public final String IS_NEED_ANLI = "IsNeedAnli";

    //需要原片
    public final String IS_NEED_YP = "IsNeedYp";

    //寄送地址
    public final String YP_ADDRESS = "YpAddress";

    //品牌
    public final String BRANDS = "Brands";

    //膜系
    public final String LOW_NAME = "MoxiName";

    //膜系id
    public final String LOW_ID = "MoxiID";

    //原片+辅材（格式:[名称,值;]）
    public final String YP_AND_FL = "Ypfuliaos";

    //工艺 （格式:[名称,值;]）
    public final String GONYIS = "Gongyis";

    //附件
    public final String IMAGS = "Imgs";

    //搜索关键字
    public final String KEY_WORD = "KeyWord";

    //状态
    public final String TO_STATE = "ToState";

    //规模最小
    public final String SMALL = "Small";

    //规模最大
    public final String LARGE = "Large";

    //RequestId
    public final String REQUEST_ID = "RequestID";

    public final String PARENT_ID = "ParentID";
    public final String PARENT_ID_SMALL = "ParentId";

    //子账号id
    public final String STR_USER_ID = "strUserid";

    public final String CHILD_ID = "ChildID";

    //订单id
    public final String ORDER_ID = "orderid";

    //兴趣点提交
    public final String POIN_NAME = "PoiName";
    public final String STR_WHERE = "strWhere";

    //手机类型
    public final String PHONE_TYPE = "PhoneType";

    //整体评价
    public final String ALL_SCORE = "Overallscore";

    //服务态度
    public final String SERVICE_SCORE = "Serviceattitude";

    //技术能力
    public final String CAPABILITY_SCORE = "Capability";

    //评价描述
    public final String DESCRIPT = "Descript";

    //原片订单
    public final String YP_ID = "yp_id";

    //辅料订单
    public final String FL_ID = "fl_id";
    //原料id
    public final String YL_ID = "yl_id";

    //是否是平台物流
    public final String IS_PT_WL = "isptwl";

    //业务员电话
    public final String TO_YWY_TEL = "Toywytel";

    //消息类型
    public final String MESSAGE_TYPE = "MessageType";

    //页码
    public final String PAGE = "Page";

    //价格
    public final String ALL_PRICE = "totalFee";
    //支付描述
    public final String PAY_SUBJECT = "subject";
    //支付订单号
    public final String PAY_ORDER_ID = "outTradeNO";
    //主体
    public final String BODY = "body";
    //APP签名
    public final String APP_SCHEME = "appScheme";

    //支付宝账号
    public final String ALIPAY = "Alipay";

    //支付宝姓名
    public final String ALIPAY_NAME = "AlipayName";

    //提现积分
    public final String SCORE = "Score";

    //流水号
    public final String PAY_TRADE_NO = "PayTradeNo";

}
