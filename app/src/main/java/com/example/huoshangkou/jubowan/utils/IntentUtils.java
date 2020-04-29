package com.example.huoshangkou.jubowan.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.AfterCommitOrderActivity;
import com.example.huoshangkou.jubowan.activity.AfterPayActivity;
import com.example.huoshangkou.jubowan.activity.BindPhoneActivity;
import com.example.huoshangkou.jubowan.activity.ImageShowActivity;
import com.example.huoshangkou.jubowan.activity.PayOrderActivity;
import com.example.huoshangkou.jubowan.activity.WebActivity;
import com.example.huoshangkou.jubowan.bean.ApproveListBean;
import com.example.huoshangkou.jubowan.bean.MemberBean;
import com.example.huoshangkou.jubowan.bean.MemberListBean;
import com.example.huoshangkou.jubowan.bean.OrderListsBean;
import com.example.huoshangkou.jubowan.bean.SelectManBean;
import com.example.huoshangkou.jubowan.chat.GroupAddActivity;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.widget.ContactItemInterface;
import com.example.huoshangkou.jubowan.widget.pinyin.PinYin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：IntentUtils
 * 描述：界面跳转工具类
 * 创建时间：2017-01-05  10:41
 */

public class IntentUtils {

    private static class IntentHelper {
        private static IntentUtils INSTANCE = new IntentUtils();
    }

    public static IntentUtils getInstance() {
        return IntentHelper.INSTANCE;
    }

    //通用的常量
    public final String TYPE = "type";
    public final String TYPE_CLICK = "typeClick";
    public final String BTN_TITLE = "btnTitle";
    public final String LEVEL = "level";
    public final String YW_TYPE = "yw_type";
    public final String MAP = "map";
    public final String ROLE_TYPE = "role_type";
    public final String ROLE_MANAGE_TYPE = "role_manage_type";
    public final String ROLE_MANAGE_ID = "role_manage_id";
    public final String PROVINCE = "province";
    public final String CITY = "city";
    public final String AREA = "area";
    public final String CURRENT_TIME = "current_time";
    public final String SECOND_TYPE = "second_type";
    public final String VALUE = "value";
    public final String ORDER_ID = "order_id";
    public final String STR = "str";
    public final String TXT = "txt";
    public final String TYPE_DETAILS = "type_details";
    public final String PEOPLE_TYPE = "poeple_type";
    public final String CSR = "csr";
    public final String SPR = "spr";
    public final String USER_ID = "user_id";
    public final String BT_ED = "bt_ed";
    public final String SY_COMPANY = "sy_company";
    public final String SY_ID = "sy_id";
    public final String MONEY_USE = "money_use";
    public final String BANK_ID = "bank_id";
    public final String IS_DA_CHU = "is_dachu";
    public final String TAB_MESSAGE = "tab_message";
    public final String TITLE = "title";
    public final String LIST = "list";
    public final String ID = "id";
    public final String APPROVE_TYPE_ID = "approve_type_id";
    public final String APPROVE_ID = "approve_id";
    public final String APPROVE_OVER = "approve_over";
    //支付宝相关
    public final String INTRO_NAME = "introName";
    public final String BODY = "body";
    public final String SCHEME = "scheme";
    //维修清单位置
    public final String POSITION = "position";
    //维修清单价格
    public final String PRICE = "price";
    //订单状态
    public final String STATE = "state";
    //原片订单
    public final String YP_ORDER = "yp_order";
    //辅料订单
    public final String FL_ORDER = "fl_order";
    public final String YL_ORDER = "yl_order";
    //时间
    public final String TIME = "time";
    //论坛帖子审核状态
    public String CHECK_TYPE = "check_type";
    //类
    public String BEAN_TYPE = "addressListBean";
    public String BEAN_TYPE_SECOND = "addressListBeanSecond";
    public String OLD_BEAN = "old_bean";
    //地址选择
    public final String ADDRESS_CHOOSE = "address_choose";
    public final String ADDRESS_NAME = "address_name";
    public final String ADDRESS_ID = "address_id";
    public final String ADDRESS_LINK_MAN = "address_link_man";
    public final String ADDRESS_LINK_TEL = "address_link_tel";
    //加工厂
    public final String FACTORY = "factory";
    //建筑商
    public final String BUILD_BUSINESS = "build_business";
    //机械配件厂
    public final String MACHINE_PRO = "machine";
    //审批、申请
    public final String APPROVE_TYPE = "apply_type";
    //认证界面银行信息
    public final String BANK_INFO = "bank_info";
    //同意审批
    public final String AGREE_APPROVE = "agree";
    public final String DISAGREE_APPROVE = "disAgree";
    //是否可以编辑
    public final String IS_EDIT = "is_edit";
    public final String CHECK_APPROVE = "check_approve";
    public final String ISAGREE = "is_agree";
    public final String IS_YW = "is_yw";
    public final String APPROVE_NAME = "approve_name";
    //打款客户
    public final String DK_CUNSTOMER = "dk_customer";
    //收款账户
    public final String SK_ACCOUNT = "sk_account";
    public final String MYS_TRADES = "mys_trades";
    public final String SK_COMPANY = "sk_company";
    //出账账户
    public final String CK_ACCOUNT = "ck_account";
    //收款公司
    public final String DK_COMPANY = "dk_company";
    //招标类型
    public final String ZB_TYPE = "zb_type";
    //光伏玻璃
    public final String GF_ZB = "gf_zb";
    //系统门窗
    public final String SYSTEM_ZB = "system_zb";
    //幕墙招标
    public final String MUQ_ZB = "mu_qiang_zb";
    //门窗定制
    public final String DOOR_WINDOW = "door_window";

    //内部往来款
    public final String IN_COMPANY_TYPE = "in_company_type";


    /**
     * 跳转到图片展示界面
     */
    public void toImageShowActivity(Context context, List<String> imgList, int position) {
        Intent intent = new Intent(context, ImageShowActivity.class);
        intent.putExtra("position", position);
        intent.putStringArrayListExtra("imgList", (ArrayList<String>) imgList);
        context.startActivity(intent);
    }


    /**
     * 第三方登录传值
     */
    public void toBindPhoneActivity(Context context, String nickName, String pic, int loginType, String openId) {
        Intent intent = new Intent(context, BindPhoneActivity.class);
        intent.putExtra("nick_name", nickName);
        intent.putExtra("pic", pic);
        intent.putExtra("login_type", loginType);
        intent.putExtra("open_id", openId);
        context.startActivity(intent);
    }

    /**
     * 界面跳转
     */
    public <T> void toActivity(Context context, Class<T> tClass) {
        Intent intent = new Intent(context, tClass);
        context.startActivity(intent);
    }


    public <T> void toActivity(Context context, Class<T> tClass, ArrayList<Parcelable> list) {
        Intent intent = new Intent(context, tClass);
        intent.putParcelableArrayListExtra(LIST, list);
        context.startActivity(intent);
    }

    /**
     * 带参数进行跳转
     */
    public <T> void toActivity(Context context, Class<T> tClass, String type) {
        Intent intent = new Intent(context, tClass);
        intent.putExtra(TYPE, type);
        context.startActivity(intent);
    }

    public <T> void toNextWebActivity(Context context, String url, String title, String typeClick, String btnTitle, String orderId) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(TYPE, url);
        intent.putExtra(VALUE, title);
        intent.putExtra(TYPE_CLICK, typeClick);
        intent.putExtra(BTN_TITLE, btnTitle);
        intent.putExtra(ORDER_ID, orderId);
        context.startActivity(intent);
    }

    public <T> void toRoleActivity(Context context, Class<T> tClass, String type, String depId) {
        Intent intent = new Intent(context, tClass);
        intent.putExtra(ROLE_MANAGE_TYPE, type);
        intent.putExtra(ROLE_MANAGE_ID, depId);
        context.startActivity(intent);
    }

    public <T> void toRoleTypeActivity(Context context, Class<T> tClass, String type) {
        Intent intent = new Intent(context, tClass);
        intent.putExtra(ROLE_TYPE, type);
        context.startActivity(intent);
    }

    public <T> void toRoleManageTypeActivity(Context context, Class<T> tClass, String type, String value, String roleType, String roleManage) {
        Intent intent = new Intent(context, tClass);
        intent.putExtra(TYPE, type);
        intent.putExtra(VALUE, value);
        intent.putExtra(ROLE_TYPE, roleType);
        intent.putExtra(ROLE_MANAGE_TYPE, roleManage);
        context.startActivity(intent);
    }

    public <T> void toRoleTypeActivity(Context context, Class<T> tClass, String type, String value, String str, String roleType) {
        Intent intent = new Intent(context, tClass);
        intent.putExtra(TYPE, type);
        intent.putExtra(VALUE, value);
        intent.putExtra(STR, str);
        intent.putExtra(ROLE_TYPE, roleType);
        context.startActivity(intent);
    }

    public <T> void toRoleTypeActivity(Context context, Class<T> tClass, String type, String value, String str, String roleType, String roleManage) {
        Intent intent = new Intent(context, tClass);
        intent.putExtra(TYPE, type);
        intent.putExtra(VALUE, value);
        intent.putExtra(STR, str);
        intent.putExtra(ROLE_TYPE, roleType);
        intent.putExtra(ROLE_MANAGE_TYPE, roleManage);
        context.startActivity(intent);
    }

    /**
     * 带参数进行跳转
     */
    public <T> void toActivity(Context context, Class<T> tClass, String type, String value) {
        Intent intent = new Intent(context, tClass);
        intent.putExtra(TYPE, type);
        intent.putExtra(VALUE, value);
        context.startActivity(intent);
    }

    public <T> void toActivity(Context context, Class<T> tClass, String type, String value, String str) {
        Intent intent = new Intent(context, tClass);
        intent.putExtra(TYPE, type);
        intent.putExtra(VALUE, value);
        intent.putExtra(STR, str);
        context.startActivity(intent);
    }

    public <T> void toBtDetailActivity(Context context, Class<T> tClass, String type, String value, String str, String approveName) {
        Intent intent = new Intent(context, tClass);
        intent.putExtra(TYPE, type);
        intent.putExtra(VALUE, value);
        intent.putExtra(STR, str);
        intent.putExtra(APPROVE_NAME, approveName);
        context.startActivity(intent);
    }

    public <T> void toLoanActivity(Context context, Class<T> tClass, String type, String value,
                                   String str, String ypCompnay, String ypId, String ids, String csId, ArrayList<ApproveListBean> approvalMsgList) {
        Intent intent = new Intent(context, tClass);
        intent.putExtra(TYPE, type);
        intent.putExtra(VALUE, value);
        intent.putExtra(STR, str);
        intent.putExtra(SY_COMPANY, ypCompnay);
        intent.putExtra(SY_ID, ypId);
        intent.putExtra(ID, ids);
        intent.putExtra(CSR, csId);
        intent.putParcelableArrayListExtra(LIST, approvalMsgList);
        context.startActivity(intent);
    }

    public <T> void toLoanActivity(Context context, Class<T> tClass, String type, String value, String str, String ypCompnay,
                                   String ypId, String moneyUse, String bankId, String ids, String csId,
                                   String userId, ArrayList<ApproveListBean> approvalMsgList) {
        Intent intent = new Intent(context, tClass);
        intent.putExtra(TYPE, type);
        intent.putExtra(VALUE, value);
        intent.putExtra(STR, str);
        intent.putExtra(SY_COMPANY, ypCompnay);
        intent.putExtra(SY_ID, ypId);
        intent.putExtra(MONEY_USE, moneyUse);
        intent.putExtra(BANK_ID, bankId);
        intent.putExtra(ID, ids);
        intent.putExtra(CSR, csId);
        intent.putExtra(USER_ID, userId);
        intent.putParcelableArrayListExtra(LIST, approvalMsgList);
        context.startActivity(intent);
    }

    public <T> void toLoanActivity(Context context, Class<T> tClass, String type, String value, String str, String ypCompnay,
                                   String ypId, String moneyUse, String bankId, String ids, String csId,
                                   String userId, String btEd, ArrayList<ApproveListBean> approvalMsgList) {
        Intent intent = new Intent(context, tClass);
        intent.putExtra(TYPE, type);
        intent.putExtra(VALUE, value);
        intent.putExtra(STR, str);
        intent.putExtra(SY_COMPANY, ypCompnay);
        intent.putExtra(SY_ID, ypId);
        intent.putExtra(MONEY_USE, moneyUse);
        intent.putExtra(BANK_ID, bankId);
        intent.putExtra(ID, ids);
        intent.putExtra(CSR, csId);
        intent.putExtra(USER_ID, userId);
        intent.putExtra(BT_ED, btEd);
        intent.putParcelableArrayListExtra(LIST, approvalMsgList);
        context.startActivity(intent);
    }

    public <T> void toApproveAgreeActivity(Context context, Class<T> tClass, String approveTypeId, String approveId, String approveOver, String csId, String checkId) {
        Intent intent = new Intent(context, tClass);
        intent.putExtra(APPROVE_TYPE_ID, approveTypeId);
        intent.putExtra(APPROVE_ID, approveId);
        intent.putExtra(APPROVE_OVER, approveOver);
        intent.putExtra(CSR, csId);
        intent.putExtra(SPR, checkId);
        context.startActivity(intent);
    }

    public <T> void toInCompanyActivity(Context context, Class<T> tClass, String type, String value, String typeCheck, ArrayList<ApproveListBean> approvalMsgList) {
        Intent intent = new Intent(context, tClass);
        intent.putExtra(TYPE, type);
        intent.putExtra(VALUE, value);
        intent.putExtra(CHECK_TYPE, typeCheck);
        intent.putParcelableArrayListExtra(LIST, approvalMsgList);
        context.startActivity(intent);
    }

    public <T> void toActivity(Context context, Class<T> tClass, String type, String value, String str, String txt) {
        Intent intent = new Intent(context, tClass);
        intent.putExtra(TYPE, type);
        intent.putExtra(VALUE, value);
        intent.putExtra(STR, str);
        intent.putExtra(TXT, txt);
        context.startActivity(intent);
    }

    public <T> void toActivity(Context context, Class<T> tClass, String type, String value, String str, String txt, String typeDetails) {
        Intent intent = new Intent(context, tClass);
        intent.putExtra(TYPE, type);
        intent.putExtra(VALUE, value);
        intent.putExtra(STR, str);
        intent.putExtra(TXT, txt);
        intent.putExtra(TYPE_DETAILS, typeDetails);
        context.startActivity(intent);
    }

    public <T> void toActivity(Context context, Class<T> tClass, String type, String value, String str, String txt, String typeDetails, int peopleType) {
        Intent intent = new Intent(context, tClass);
        intent.putExtra(TYPE, type);
        intent.putExtra(VALUE, value);
        intent.putExtra(STR, str);
        intent.putExtra(TXT, txt);
        intent.putExtra(TYPE_DETAILS, typeDetails);
        intent.putExtra(PEOPLE_TYPE, peopleType + "");
        context.startActivity(intent);
    }

    public <T> void toYwActivity(Context context, Class<T> tClass, String type, String value, String str, String txt, String ywType) {
        Intent intent = new Intent(context, tClass);
        intent.putExtra(TYPE, type);
        intent.putExtra(VALUE, value);
        intent.putExtra(STR, str);
        intent.putExtra(TXT, txt);
        intent.putExtra(YW_TYPE, ywType);
        context.startActivity(intent);
    }

    public <T> void toApproveCheck(Context context, Class<T> tClass, String type, String value, String str, String txt, String types) {
        Intent intent = new Intent(context, tClass);
        intent.putExtra(TYPE, type);
        intent.putExtra(VALUE, value);
        intent.putExtra(STR, str);
        intent.putExtra(TXT, txt);
        intent.putExtra(APPROVE_TYPE, types);
        context.startActivity(intent);
    }

    public <T> void toActivity(Context context, Class<T> tClass, String type, String value, String str, String txt, String ypCompnay, String ypId) {
        Intent intent = new Intent(context, tClass);
        intent.putExtra(TYPE, type);
        intent.putExtra(VALUE, value);
        intent.putExtra(STR, str);
        intent.putExtra(TXT, txt);
        intent.putExtra(SY_COMPANY, ypCompnay);
        intent.putExtra(SY_ID, ypId);
        context.startActivity(intent);
    }


    public <T> void toActivity(Context context, Class<T> tClass, String type, String value, String str,
                               String txt, String ypCompnay, String ypId, String fundWay, String bankId, String tableMessage,
                               String ids, String csId, String userId, ArrayList<ApproveListBean> approvalMsgList) {
        Intent intent = new Intent(context, tClass);
        intent.putExtra(TYPE, type);
        intent.putExtra(VALUE, value);
        intent.putExtra(STR, str);
        intent.putExtra(TXT, txt);
        intent.putExtra(SY_COMPANY, ypCompnay);
        intent.putExtra(SY_ID, ypId);
        intent.putExtra(MONEY_USE, fundWay);
        intent.putExtra(BANK_ID, bankId);
        intent.putExtra(TAB_MESSAGE, tableMessage);
        intent.putExtra(ID, ids);
        intent.putExtra(CSR, csId);
        intent.putExtra(USER_ID, userId);
        intent.putParcelableArrayListExtra(LIST, approvalMsgList);
        context.startActivity(intent);
    }


    public <T> void toDianActivity(Context context, Class<T> tClass, String type, String value, String str,
                                   String txt, String ypCompnay, String ypId, String fundWay, String bankId, String isDachu,
                                   String tableMessage, String ids, String csId, String userId, ArrayList<ApproveListBean> approvalMsgList) {
        Intent intent = new Intent(context, tClass);
        intent.putExtra(TYPE, type);
        intent.putExtra(VALUE, value);
        intent.putExtra(STR, str);
        intent.putExtra(TXT, txt);
        intent.putExtra(SY_COMPANY, ypCompnay);
        intent.putExtra(SY_ID, ypId);
        intent.putExtra(MONEY_USE, fundWay);
        intent.putExtra(BANK_ID, bankId);
        intent.putExtra(IS_DA_CHU, isDachu);
        intent.putExtra(TAB_MESSAGE, tableMessage);
        intent.putExtra(ID, ids);
        intent.putExtra(CSR, csId);
        intent.putExtra(USER_ID, userId);
        intent.putParcelableArrayListExtra(LIST, approvalMsgList);
        context.startActivity(intent);
    }

    public <T> void toActivityTitle(Context context, Class<T> tClass, String type, String value, String str, String txt, String title) {
        Intent intent = new Intent(context, tClass);
        intent.putExtra(TYPE, type);
        intent.putExtra(VALUE, value);
        intent.putExtra(STR, str);
        intent.putExtra(TXT, txt);
        intent.putExtra(TITLE, title);
        context.startActivity(intent);
    }


    public <T> void toYwActivity(Context context, Class<T> tClass, String type, String value, String str, String types, String isAgree, String approveName) {
        Intent intent = new Intent(context, tClass);
        intent.putExtra(TYPE, type);
        intent.putExtra(VALUE, value);
        intent.putExtra(STR, str);
        intent.putExtra(CHECK_APPROVE, types);
        intent.putExtra(ISAGREE, isAgree);
        intent.putExtra(APPROVE_NAME, approveName);
        context.startActivity(intent);
    }

    public <T> void toYwActivity(Context context, Class<T> tClass, String type, String value, String str, String types, String isAgree, String isYw, String approveName) {
        Intent intent = new Intent(context, tClass);
        intent.putExtra(TYPE, type);
        intent.putExtra(VALUE, value);
        intent.putExtra(STR, str);
        intent.putExtra(CHECK_APPROVE, types);
        intent.putExtra(ISAGREE, isAgree);
        intent.putExtra(IS_YW, isYw);
        intent.putExtra(APPROVE_NAME, approveName);
        context.startActivity(intent);
    }


    /**
     * 传类
     */
    public <T> void toActivity(Context context, Class<T> tClass, Serializable type) {
        Intent intent = new Intent(context, tClass);
        intent.putExtra(BEAN_TYPE, type);
        context.startActivity(intent);
    }

    public <T> void toActivity(Context context, Class<T> tClass, Serializable type, String value) {
        Intent intent = new Intent(context, tClass);
        intent.putExtra(BEAN_TYPE, type);
        intent.putExtra(TYPE, value);
        context.startActivity(intent);
    }


    public <T> void toActivity(Context context, Class<T> tClass, Serializable type, String value, String secondType) {
        Intent intent = new Intent(context, tClass);
        intent.putExtra(BEAN_TYPE, type);
        intent.putExtra(TYPE, value);
        intent.putExtra(SECOND_TYPE, secondType);
        context.startActivity(intent);
    }


    public <T> void toActivity(Context context, Class<T> tClass, Serializable type, String value, Serializable typeSecond) {
        Intent intent = new Intent(context, tClass);
        intent.putExtra(BEAN_TYPE, type);
        intent.putExtra(BEAN_TYPE_SECOND, typeSecond);
        intent.putExtra(TYPE, value);
        context.startActivity(intent);
    }

    public <T> void toActivity(Context context, Class<T> tClass, String value, Serializable typeSecond, String id) {
        Intent intent = new Intent(context, tClass);
        intent.putExtra(BEAN_TYPE_SECOND, typeSecond);
        intent.putExtra(TYPE, value);
        intent.putExtra(ID, id);
        context.startActivity(intent);
    }

    public <T> void toActivity(Context context, Class<T> tClass, Serializable type, String value, String id, Serializable typeSecond) {
        Intent intent = new Intent(context, tClass);
        intent.putExtra(BEAN_TYPE, type);
        intent.putExtra(BEAN_TYPE_SECOND, typeSecond);
        intent.putExtra(TYPE, value);
        intent.putExtra(ID, id);
        context.startActivity(intent);
    }

    //下单成功
    public void toAfterOrder(Context context, String orderId, String time) {
        Intent intent = new Intent(context, AfterPayActivity.class);
        intent.putExtra(ORDER_ID, orderId);
        intent.putExtra(TIME, time);
        context.startActivity(intent);
    }

    //跳转到支付界面  需要支付宝支付的
    public void toPayActivity(Context context, String price, String orderId, String introName, String body, String scheme, OrderListsBean orderListsBean) {
        Intent intent = new Intent(context, PayOrderActivity.class);
        intent.putExtra(IntentUtils.getInstance().TYPE, price);
        intent.putExtra(IntentUtils.getInstance().VALUE, orderId);
        intent.putExtra(IntentUtils.getInstance().INTRO_NAME, introName);
        intent.putExtra(IntentUtils.getInstance().BODY, body);
        intent.putExtra(IntentUtils.getInstance().SCHEME, scheme);
        intent.putExtra(IntentUtils.getInstance().BEAN_TYPE, orderListsBean);
        context.startActivity(intent);
    }


    public void toRepairPayActivity(Context context, String price, String orderId, String introName, String body, String scheme, OrderListsBean orderListsBean) {
        Intent intent = new Intent(context, PayOrderActivity.class);
        intent.putExtra(IntentUtils.getInstance().TYPE, price);
        intent.putExtra(IntentUtils.getInstance().VALUE, orderId);
        intent.putExtra(IntentUtils.getInstance().INTRO_NAME, introName);
        intent.putExtra(IntentUtils.getInstance().BODY, body);
        intent.putExtra(IntentUtils.getInstance().SCHEME, scheme);
        intent.putExtra(IntentUtils.getInstance().BEAN_TYPE, orderListsBean);
        context.startActivity(intent);
    }

    public void toPayActivity(Context context, String price, String orderId, String introName, String body, String scheme, String str, OrderListsBean orderListsBean) {
        Intent intent = new Intent(context, PayOrderActivity.class);
        intent.putExtra(IntentUtils.getInstance().TYPE, price);
        intent.putExtra(IntentUtils.getInstance().VALUE, orderId);
        intent.putExtra(IntentUtils.getInstance().INTRO_NAME, introName);
        intent.putExtra(IntentUtils.getInstance().BODY, body);
        intent.putExtra(IntentUtils.getInstance().SCHEME, scheme);
        intent.putExtra(IntentUtils.getInstance().STR, str);
        intent.putExtra(IntentUtils.getInstance().BEAN_TYPE, orderListsBean);
        context.startActivity(intent);
    }

    //不需要支付宝支付的
    public void toPayActivity(Context context, String price, String orderId, OrderListsBean orderListsBean) {
        Intent intent = new Intent(context, PayOrderActivity.class);
        intent.putExtra(IntentUtils.getInstance().TYPE, price);
        intent.putExtra(IntentUtils.getInstance().VALUE, orderId);
        intent.putExtra(IntentUtils.getInstance().BEAN_TYPE, orderListsBean);
        context.startActivity(intent);
    }

    public void toPayActivity(Context context, String price, String orderId, OrderListsBean orderListsBean, String str) {
        Intent intent = new Intent(context, PayOrderActivity.class);
        intent.putExtra(IntentUtils.getInstance().TYPE, price);
        intent.putExtra(IntentUtils.getInstance().VALUE, orderId);
        intent.putExtra(IntentUtils.getInstance().STR, str);
        intent.putExtra(IntentUtils.getInstance().BEAN_TYPE, orderListsBean);
        context.startActivity(intent);
    }

//    public void toPayActivity(Context context, String price, String orderId) {
//        Intent intent = new Intent(context, PayOrderActivity.class);
//        intent.putExtra(IntentUtils.getInstance().TYPE, price);
//        intent.putExtra(IntentUtils.getInstance().VALUE, orderId);
//        context.startActivity(intent);
//    }

    //跳转至webview 界面
    public void toWebActivity(Context context, String url, String title) {
        toActivity(context, WebActivity.class, url, title);
    }

    public void toWebActivity(Context context, String url, String title, String orderId) {
//        toActivity(context, WebActivity.class,url,title,orderId);
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(TYPE, url);
        intent.putExtra(VALUE, title);
        intent.putExtra(ORDER_ID, orderId);
        context.startActivity(intent);
    }

    public void toWeiTuoWebActivity(Context context, String url, String title, String orderId, String str) {
//        toActivity(context, WebActivity.class,url,title,orderId);
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(TYPE, url);
        intent.putExtra(VALUE, title);
        intent.putExtra(ORDER_ID, orderId);
        intent.putExtra(STR, str);
        context.startActivity(intent);
    }


    //hasPacket 是否有红包
    public void toWebActivity(Context context, String url, String title, String hasPacket, String time) {
        toActivity(context, WebActivity.class, url, title, hasPacket, time);
    }

    //跳转群聊添加界面
    public void toGroupAddActivity(Context context, String type,String groupId) {
        toActivity(context, GroupAddActivity.class, type,groupId);
    }
 public void toGroupAddActivity(Context context) {
        toActivity(context, GroupAddActivity.class);
    }


}
