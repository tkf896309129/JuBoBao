package com.example.huoshangkou.jubowan.activity.function;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.adapter.BaseCheckAdapter;
import com.example.huoshangkou.jubowan.bean.BaseCheckBean;
import com.example.huoshangkou.jubowan.bean.BaseCheckDetailBean;
import com.example.huoshangkou.jubowan.bean.BaseCheckResultBean;
import com.example.huoshangkou.jubowan.bean.SuccessDBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity.function
 * 类名：BaseCheckFunction
 * 描述：
 * 创建时间：2019-12-06  14:34
 */

public class BaseCheckFunction {
    private final int TXT = 1;
    private final int EDIT = 2;
    private final int CHOOSE = 3;
    private final int INVOINCE = 4;
    private final int TITLE = 6;
    private final int DEFAULT = 7;

    private static class BaseChcekHelper {
        private static BaseCheckFunction INSTANCE = new BaseCheckFunction();
    }

    public static BaseCheckFunction getInstance() {
        return BaseChcekHelper.INSTANCE;
    }

    //费用报销
    public List<BaseCheckBean> getFeetBaoList(List<BaseCheckBean> listNew) {
        List<BaseCheckBean> list = new ArrayList<>();
        BaseCheckBean bean1 = new BaseCheckBean();
        bean1.setType(EDIT);
        bean1.setInterfaceKey("TypeName");
        bean1.setMust(true);
        bean1.setHintType("报销类型");

        BaseCheckBean bean2 = new BaseCheckBean();
        bean2.setType(EDIT);
        bean2.setInterfaceKey("TypePrice");
        bean2.setHintType("报销金额");
        bean2.setEditType(1);
        bean2.setMust(true);

        BaseCheckBean bean3 = new BaseCheckBean();
        bean3.setType(CHOOSE);
        bean3.setInterfaceKey("Time");
        bean3.setHintType("使用日期");
        bean3.setChooseType("时间");
        bean3.setMust(true);

        BaseCheckBean bean4 = new BaseCheckBean();
        bean4.setType(EDIT);
        bean4.setInterfaceKey("Address");
        bean4.setHintType("使用地点");
        bean4.setMust(true);

        BaseCheckBean bean5 = new BaseCheckBean();
        bean5.setType(INVOINCE);
        bean5.setInterfaceKey("Invoice");
        bean5.setHintType("发票");
        bean5.setContent("0");
        list.add(new BaseCheckBean(TITLE, "类型", "费用报销", "", "", false, 2, false, ""));
        list.add(bean1);
        list.add(bean2);
        list.add(bean3);
        list.add(bean4);
        list.add(bean5);
        list.add(new BaseCheckBean());
        if (listNew != null) {
            listNew.addAll(list);
        }
        return list;
    }

    //普通用款
    public List<BaseCheckBean> getCommonMoney(List<BaseCheckBean> listNew) {
        List<BaseCheckBean> list = new ArrayList<>();
        BaseCheckBean bean1 = new BaseCheckBean();
        bean1.setType(EDIT);
        bean1.setInterfaceKey("TypeName");
        bean1.setMust(true);
        bean1.setHintType("用款类型");

        BaseCheckBean bean2 = new BaseCheckBean();
        bean2.setType(EDIT);
        bean2.setMust(true);
        bean2.setInterfaceKey("TypePrice");
        bean2.setHintType("用款金额");
        bean2.setEditType(1);

        BaseCheckBean bean3 = new BaseCheckBean();
        bean3.setType(CHOOSE);
        bean3.setMust(true);
        bean3.setInterfaceKey("Time");
        bean3.setHintType("使用日期");
        bean3.setChooseType("时间");

        BaseCheckBean beanType = new BaseCheckBean();
        beanType.setType(CHOOSE);
        beanType.setMust(true);
        beanType.setHintType("用款方式");
        beanType.setInterfaceKey("FundWay");
        beanType.setChooseType("用款方式");

        BaseCheckBean bean4 = new BaseCheckBean();
        bean4.setType(EDIT);
        bean4.setMust(true);
        bean4.setInterfaceKey("Address");
        bean4.setHintType("使用地点");

        BaseCheckBean bean5 = new BaseCheckBean();
        bean5.setType(INVOINCE);
        bean5.setHintType("发票");
        bean5.setInterfaceKey("Invoice");
        bean5.setContent("0");

        list.add(bean1);
        list.add(bean2);
        list.add(bean3);
        list.add(beanType);
        list.add(bean4);
        list.add(bean5);
        list.add(new BaseCheckBean());
        if (listNew != null) {
            listNew.addAll(list);
        }
        return list;
    }

    //请假
    public List<BaseCheckBean> getHolidayData(List<BaseCheckBean> listNew, String type) {
        List<BaseCheckBean> list = new ArrayList<>();
        BaseCheckBean bean1 = new BaseCheckBean();
        bean1.setType(CHOOSE);
        bean1.setInterfaceKey("TypeName");
        bean1.setMust(true);
        bean1.setChooseType("请假类型");
        bean1.setHintType("请假类型");

        BaseCheckBean bean2 = new BaseCheckBean();
        bean2.setType(CHOOSE);
        bean2.setMust(true);
        bean2.setInterfaceKey("StartTime");
        bean2.setHintType("开始日期");
        bean2.setChooseType("时间");

        BaseCheckBean bean3 = new BaseCheckBean();
        bean3.setType(CHOOSE);
        bean3.setMust(true);
        bean3.setInterfaceKey("Startslot");
        bean3.setHintType("开始时间段");
        bean3.setChooseType("时间段");

        BaseCheckBean beanType = new BaseCheckBean();
        beanType.setType(CHOOSE);
        beanType.setMust(true);
        beanType.setHintType("结束日期");
        beanType.setInterfaceKey("EndTime");
        beanType.setChooseType("时间");

        BaseCheckBean bean4 = new BaseCheckBean();
        bean4.setType(CHOOSE);
        bean4.setMust(true);
        bean4.setInterfaceKey("Endslot");
        bean4.setHintType("结束时间段");
        bean4.setChooseType("时间段");

        BaseCheckBean bean5 = new BaseCheckBean();
        bean5.setType(TXT);
        switch (type) {
            case "holiday":
                bean5.setHintType("请假时长");
                break;
            case "work":
                bean5.setHintType("出差时长");
                break;
        }
        bean5.setInterfaceKey("TimeSpan");

        if (type.equals("holiday")) {
            list.add(bean1);
        }
        list.add(bean2);
        list.add(bean3);
        list.add(beanType);
        list.add(bean4);
        list.add(bean5);
        list.add(new BaseCheckBean());
        if (listNew != null) {
            listNew.addAll(list);
        }
        return list;
    }

    public List<BaseCheckBean> getChenDuiMoney(List<BaseCheckBean> listNew) {
        List<BaseCheckBean> list = new ArrayList<>();
        BaseCheckBean bean1 = new BaseCheckBean();
        bean1.setType(EDIT);
        bean1.setInterfaceKey("TypePrice");
        bean1.setMust(true);
        bean1.setEditType(1);
        bean1.setHintType("承兑份数");

        BaseCheckBean bean3 = new BaseCheckBean();
        bean3.setType(CHOOSE);
        bean3.setMust(true);
        bean3.setInterfaceKey("Time");
        bean3.setHintType("使用日期");
        bean3.setChooseType("时间");

        BaseCheckBean bean4 = new BaseCheckBean();
        bean4.setType(EDIT);
        bean4.setMust(true);
        bean4.setInterfaceKey("Address");
        bean4.setHintType("使用地点");

        BaseCheckBean bean5 = new BaseCheckBean();
        bean5.setType(INVOINCE);
        bean5.setHintType("发票");
        bean5.setInterfaceKey("Invoice");
        bean5.setContent("0");

        list.add(bean1);
        list.add(bean3);
        list.add(bean4);
        list.add(bean5);
        list.add(new BaseCheckBean());
        if (listNew != null) {
            listNew.addAll(list);
        }
        return list;
    }


    //业务用款
    public List<BaseCheckBean> ywMoney(List<BaseCheckBean> listNew, Context context, LinearLayout llCheck, String peopleType, String approveType, RelativeLayout llImg) {
        List<BaseCheckBean> list = new ArrayList<>();
        boolean isSaler = false;
        String isOK = (String) SharedPreferencesUtils.getInstance().get(context, SharedKeyConstant.getInstance().KEY_MAN_ID, "");
        LogUtils.e("isOK：" + isOK);
        //运营
        if (isOK.equals("2")) {
            isSaler = false;
        } else {
            if (llCheck != null) {
                llCheck.setVisibility(View.GONE);
            }
            if (llImg != null) {
                llImg.setVisibility(View.GONE);
            }
            isSaler = true;
        }
        BaseCheckBean beanTitleCus = new BaseCheckBean();
        beanTitleCus.setType(TITLE);
        beanTitleCus.setHintType("客户信息");

        BaseCheckBean bean1 = new BaseCheckBean();
        bean1.setType(CHOOSE);
        bean1.setInterfaceKey("PayingCustomers");
        bean1.setMust(false);
        bean1.setChooseType("账户");
        bean1.setHintType("打款客户");
        bean1.setAccountKey(IntentUtils.getInstance().DK_CUNSTOMER);

        BaseCheckBean bean2 = new BaseCheckBean();
        bean2.setType(CHOOSE);
        bean2.setMust(false);
        bean2.setInterfaceKey("CustomerType");
        bean2.setHintType("客户类型");
        bean2.setChooseType("类型");
        bean2.setAccountKey("customerKey");

        BaseCheckBean bean3 = new BaseCheckBean();
        bean3.setType(CHOOSE);
        bean3.setMust(false);
        bean3.setInterfaceKey("ParentTrader");
        bean3.setHintType("所属贸易商");
        bean3.setChooseType("账户");
        bean3.setAccountKey(IntentUtils.getInstance().DK_CUNSTOMER);
        bean3.setHide(true);

        BaseCheckBean beanType = new BaseCheckBean();
        beanType.setType(CHOOSE);
        beanType.setMust(false);
        beanType.setHintType("账户类型");
        beanType.setInterfaceKey("CustomerAccountType");
        beanType.setChooseType("类型");

        BaseCheckBean beanIn = new BaseCheckBean();
        beanIn.setType(CHOOSE);
        beanIn.setMust(false);
        beanIn.setHintType("打款方式");
        beanIn.setChooseType("类型");
        beanIn.setInterfaceKey("CustomerPaymentMethod");

        BaseCheckBean beanOut = new BaseCheckBean();
        beanOut.setType(EDIT);
        beanOut.setEditType(1);
        beanOut.setMust(false);
        beanOut.setHintType("打款金额");
        beanOut.setInterfaceKey("CustmerPaymentAmount");

        BaseCheckBean beanHkXZ = new BaseCheckBean();
        beanHkXZ.setType(CHOOSE);
        beanHkXZ.setMust(false);
        beanHkXZ.setHintType("客户货款性质");
        beanHkXZ.setChooseType("类型");
        beanHkXZ.setInterfaceKey("CustomerGoodsMoneyNature");

        BaseCheckBean beanHkTime = new BaseCheckBean();
        beanHkTime.setType(CHOOSE);
        beanHkTime.setMust(false);
        beanHkTime.setHintType("回款时间");
        beanHkTime.setChooseType("时间");
        beanHkTime.setInterfaceKey("BackMoneyTime");
        beanHkTime.setHide(true);

        BaseCheckBean beanTitlePlat = new BaseCheckBean();
        beanTitlePlat.setType(TITLE);
        beanTitlePlat.setHintType("平台信息");

        BaseCheckBean beanRecAccount = new BaseCheckBean();
        beanRecAccount.setType(CHOOSE);
        beanRecAccount.setHintType("收款账户");
        beanRecAccount.setMust(false);
        beanRecAccount.setChooseType("账户");
        beanRecAccount.setAccountKey(IntentUtils.getInstance().SK_ACCOUNT);
        beanRecAccount.setInterfaceKey("PlatformInMoneyAccount");

        BaseCheckBean beanRecType = new BaseCheckBean();
        beanRecType.setType(CHOOSE);
        beanRecType.setHintType("收款方式");
        beanRecType.setMust(false);
        beanRecType.setChooseType("类型");
        beanRecType.setInterfaceKey("PlatformInMoneyMethod");

        BaseCheckBean beanKeFu = new BaseCheckBean();
        beanKeFu.setType(CHOOSE);
        beanKeFu.setMust(false);
        beanKeFu.setHintType("推送所属客服");
        beanKeFu.setChooseType("客服");
        beanKeFu.setInterfaceKey("");

        BaseCheckBean beanPayMan = new BaseCheckBean();
        beanPayMan.setType(EDIT);
        beanPayMan.setHintType("打款人");
        beanPayMan.setMust(false);
        beanPayMan.setHide(true);
        beanPayMan.setChooseType("类型");
        beanPayMan.setInterfaceKey("Payer");

        list.add(beanTitleCus);
        list.add(bean1);
        list.add(bean2);
        list.add(bean3);
        list.add(beanType);
        list.add(beanPayMan);
        list.add(beanIn);
        list.add(beanOut);
        list.add(beanHkXZ);
        list.add(beanHkTime);
        list.add(beanTitlePlat);
        list.add(beanRecAccount);
        list.add(beanRecType);
        if (isSaler) {
            list.add(beanKeFu);
        }
        if ((StringUtils.isNoEmpty(approveType) && approveType.equals("2")) || isOK.equals("2") || (StringUtils.isNoEmpty(peopleType) && Integer.parseInt(peopleType) >= 1)) {
            list.add(new BaseCheckBean(CHOOSE, "账户", "出账账户", "", "PlatformOutMoneyAccount", false, 2, false, IntentUtils.getInstance().CK_ACCOUNT));
            list.add(new BaseCheckBean(CHOOSE, "类型", "平台货款性质", "", "PlatformGoodsMoneyNature", false, 2, false, IntentUtils.getInstance().CK_ACCOUNT));
            list.add(new BaseCheckBean(EDIT, "", "货款用途", "", "GoodsMoneyPurpose", false, 2, false, ""));
            list.add(new BaseCheckBean(EDIT, "", "应付金额", "", "PayableAmount", false, 1, false, ""));
            list.add(new BaseCheckBean(CHOOSE, "类型", "出款方式", "", "PlatformOutMoneyMethod", false, 2, false, IntentUtils.getInstance().CK_ACCOUNT));
            list.add(new BaseCheckBean(CHOOSE, "类型", "是否打出", "", "IsOutMoney", false, 2, false, IntentUtils.getInstance().CK_ACCOUNT));
            list.add(new BaseCheckBean(TITLE, "类型", "供应商信息", "", "", false, 2, false, ""));
            list.add(new BaseCheckBean(CHOOSE, "类型", "供应商性质", "", "SupplierNature", false, 2, false, IntentUtils.getInstance().CK_ACCOUNT));
            list.add(new BaseCheckBean(CHOOSE, "账户", "收款公司", "", "InMoneySupplier", false, 2, false, IntentUtils.getInstance().CK_ACCOUNT));
            list.add(new BaseCheckBean(TITLE, "类型", "其他信息", "", "", false, 2, false, ""));
            list.add(new BaseCheckBean(INVOINCE, "类型", "发票", "0", "IsOpenInvoice", false, 2, false, ""));
            list.add(new BaseCheckBean(CHOOSE, "时间", "日期", "", "CreateTime", false, 2, false, ""));
            list.add(new BaseCheckBean(EDIT, "时间", "盈利", "", "Profit", false, 1, false, ""));
            list.add(new BaseCheckBean(CHOOSE, "客服", "运营人员", "", "Operator", false, 2, false, ""));
        }
        list.add(new BaseCheckBean());
        if (listNew != null) {
            listNew.addAll(list);
        }
        return list;
    }

    //垫付款
    public List<BaseCheckBean> dianFuMoney(List<BaseCheckBean> listNew, Context context, LinearLayout llCheck, String peopleType, RelativeLayout llImg) {
        List<BaseCheckBean> list = new ArrayList<>();
        boolean isSaler = false;
        String isOK = (String) SharedPreferencesUtils.getInstance().get(context, SharedKeyConstant.getInstance().KEY_MAN_ID, "");
        //运营
        if (isOK.equals("2")) {
            isSaler = false;
        } else {
            if (llCheck != null) {
                llCheck.setVisibility(View.GONE);
            }
            if (llImg != null) {
                llImg.setVisibility(View.GONE);
            }
            isSaler = true;
        }

        list.add(new BaseCheckBean(TITLE, "账户", "垫资信息", "", "", false, 2, false, ""));
        list.add(new BaseCheckBean(TXT, "", "垫资订单号", "", "OrderId", false, 2, false, ""));
        list.add(new BaseCheckBean(TXT, "", "申请单位", "", "ApplyUnit", false, 2, false, ""));
        list.add(new BaseCheckBean(TXT, "", "公司法人", "", "Legalperson", false, 2, false, ""));
        list.add(new BaseCheckBean(TXT, "", "借款用途", "", "LoanUsage", false, 2, false, ""));
        list.add(new BaseCheckBean(TXT, "", "借款金额", "", "LoanAmount", false, 2, false, ""));
        list.add(new BaseCheckBean(TXT, "", "借款日期", "", "LoanDate", false, 2, false, ""));
        list.add(new BaseCheckBean(TXT, "", "回款日期", "", "BackMoneyDate", false, 2, false, ""));
        if (llCheck != null && isSaler) {
            list.add(new BaseCheckBean(CHOOSE, "客服", "推送所属客服", "", "ApprovalUserID", true, 2, false, ""));
        }
        if (StringUtils.isNoEmpty(peopleType) && Integer.parseInt(peopleType) >= 1) {
            list.add(new BaseCheckBean(TITLE, "账户", "平台信息", "", "", false, 2, false, IntentUtils.getInstance().CK_ACCOUNT));
            list.add(new BaseCheckBean(CHOOSE, "账户", "出款账户", "", "PlatformOutMoneyAccount", false, 2, false, IntentUtils.getInstance().CK_ACCOUNT));
            list.add(new BaseCheckBean(CHOOSE, "类型", "出款性质", "", "PlatformOutMoneyNature", false, 2, false, ""));
            list.add(new BaseCheckBean(CHOOSE, "类型", "是否打出", "", "IsOutMoney", false, 2, false, IntentUtils.getInstance().CK_ACCOUNT));
            list.add(new BaseCheckBean(TITLE, "类型", "供应商信息", "", "", false, 2, false, ""));
            list.add(new BaseCheckBean(CHOOSE, "类型", "供应商性质", "", "SupplierNature", false, 2, false, IntentUtils.getInstance().SK_ACCOUNT));
            list.add(new BaseCheckBean(CHOOSE, "账户", "收款公司", "", "InMoneySupplier", false, 2, false, IntentUtils.getInstance().CK_ACCOUNT));
            list.add(new BaseCheckBean(TITLE, "类型", "其他信息", "", "", false, 2, false, ""));
            list.add(new BaseCheckBean(INVOINCE, "类型", "发票", "0", "IsOpenInvoice", false, 2, false, ""));
            list.add(new BaseCheckBean(EDIT, "", "盈利", "", "Profit", false, 1, false, ""));
            list.add(new BaseCheckBean(EDIT, "", "打款金额", "", "PayAmount", false, 1, false, ""));
            list.add(new BaseCheckBean(CHOOSE, "客服", "运营中心人员", "", "Operator", false, 2, false, ""));
        }
        list.add(new BaseCheckBean());
        if (listNew != null) {
            listNew.addAll(list);
        }
        return list;
    }

    //内部往来款
    public List<BaseCheckBean> inMoneyData(List<BaseCheckBean> listNew) {
        List<BaseCheckBean> list = new ArrayList<>();
        list.add(new BaseCheckBean(TITLE, "类型", "出款", "", "", false, 2, false, ""));
        list.add(new BaseCheckBean(CHOOSE, "账户", "出账账户", "", "PaymentUnitAccount", true, 2, false, IntentUtils.getInstance().CK_ACCOUNT));
        list.add(new BaseCheckBean(EDIT, "类型", "出款金额", "", "PaymentAmount", true, 1, false, ""));
        list.add(new BaseCheckBean(CHOOSE, "类型", "款项类型", "", "PaymentMethod", true, 2, false, ""));
        list.add(new BaseCheckBean(TITLE, "类型", "入款", "", "", false, 2, false, ""));
        list.add(new BaseCheckBean(CHOOSE, "账户", "入款账户", "", "ReceiptUnitAccount", true, 2, false, IntentUtils.getInstance().SK_ACCOUNT));
        list.add(new BaseCheckBean());
        if (listNew != null) {
            listNew.addAll(list);
        }
        return list;
    }

    public List<BaseCheckBean> inBtData(List<BaseCheckBean> listNew, String peopleType) {
        List<BaseCheckBean> list = new ArrayList<>();
        list.add(new BaseCheckBean(TITLE, "类型", "白条信息", "", "", false, 2, false, ""));
        list.add(new BaseCheckBean(TXT, "账户", "白条订单号", "", "OrderId", true, 2, false, ""));
        list.add(new BaseCheckBean(TXT, "账户", "申请单位", "", "ApplyUnit", true, 2, false, ""));
        list.add(new BaseCheckBean(TXT, "账户", "公司法人", "", "LegalPerson", true, 2, false, ""));
        list.add(new BaseCheckBean(TXT, "账户", "入账单位", "", "AccountBank", true, 2, false, ""));
        list.add(new BaseCheckBean(TXT, "账户", "入款开户行", "", "BankOpenAccount", true, 2, false, ""));
        list.add(new BaseCheckBean(TXT, "账户", "开户行账号", "", "AccountNum", true, 2, false, ""));
        list.add(new BaseCheckBean(TXT, "账户", "借款用途", "", "LoanPurposes", true, 2, false, ""));
        list.add(new BaseCheckBean(TXT, "账户", "借款金额", "", "Money", true, 1, false, ""));
        list.add(new BaseCheckBean(TXT, "账户", "白条额度", "", "IousTotalAmount", true, 1, false, ""));
        list.add(new BaseCheckBean(TXT, "账户", "剩余额度", "", "IousRemainingAmount", true, 1, false, ""));
        list.add(new BaseCheckBean(TXT, "时间", "借款日期", "", "CreateTime", true, 2, false, ""));
        if (StringUtils.isNoEmpty(peopleType) && Integer.parseInt(peopleType) >= 1) {
            list.add(new BaseCheckBean(CHOOSE, "类型", "是否打出", "", "NeedTransfer", true, 2, false, ""));
            list.add(new BaseCheckBean(TITLE, "类型", "供应商信息", "", "", false, 2, false, ""));
            list.add(new BaseCheckBean(CHOOSE, "类型", "供应商性质", "", "SupplierNature", true, 2, false, ""));
            list.add(new BaseCheckBean(CHOOSE, "账户", "收款公司", "", "CollectionCompany", true, 2, false, IntentUtils.getInstance().SK_ACCOUNT));

            list.add(new BaseCheckBean(TITLE, "类型", "其他信息", "", "", false, 2, false, ""));
            list.add(new BaseCheckBean(INVOINCE, "类型", "发票", "0", "IsOpenInvoice", true, 2, false, ""));
            list.add(new BaseCheckBean(CHOOSE, "时间", "日期", "", "TransferDate", true, 2, false, ""));
            list.add(new BaseCheckBean(EDIT, "", "盈利", "", "Profit", true, 1, false, ""));
            list.add(new BaseCheckBean(EDIT, "", "打款金额", "", "AmountOfPayment", true, 1, false, ""));
            list.add(new BaseCheckBean(CHOOSE, "客服", "运营人员", "", "OperationPersonnel", false, 2, false, ""));
        }

        list.add(new BaseCheckBean());
        if (listNew != null) {
            listNew.addAll(list);
        }
        return list;
    }

    //白条额度
    public List<BaseCheckBean> inBtEdData(List<BaseCheckBean> listNew) {
        List<BaseCheckBean> list = new ArrayList<>();
        list.add(new BaseCheckBean(TITLE, "类型", "白条额度", "", "", false, 2, false, ""));
        list.add(new BaseCheckBean(TXT, "账户", "申请单位", "", "ApplyUnit", true, 2, false, ""));
        list.add(new BaseCheckBean(TXT, "账户", "公司法人", "", "LegalPerson", true, 2, false, ""));
        list.add(new BaseCheckBean(TXT, "账户", "法人身份证号", "", "IdCard", true, 2, false, ""));
        list.add(new BaseCheckBean(TXT, "账户", "生产地址", "", "Address", true, 2, false, ""));
        list.add(new BaseCheckBean(TXT, "账户", "生产规模(年收入)", "", "Scale", true, 2, false, ""));
        list.add(new BaseCheckBean(TXT, "账户", "人员情况", "", "PersonnelSituation", true, 2, false, ""));
        list.add(new BaseCheckBean(DEFAULT, "账户", "授权期限", "365天", "", true, 2, false, ""));

        list.add(new BaseCheckBean(TITLE, "类型", "额度", "", "", false, 2, false, ""));
        //金融可编辑
        list.add(new BaseCheckBean(EDIT, "类型", "白条额度", "", "IousAmount", false, 1, false, ""));

        list.add(new BaseCheckBean());
        if (listNew != null) {
            listNew.addAll(list);
        }
        return list;
    }

    //其他审批
    public List<BaseCheckBean> otherCheck(List<BaseCheckBean> listNew) {
        List<BaseCheckBean> list = new ArrayList<>();
        list.add(new BaseCheckBean(CHOOSE, "类型", "审批类型", "", "TypeName", true, 2, false, ""));
        list.add(new BaseCheckBean(EDIT, "账户", "审批金额", "", "TypePrice", true, 1, false, IntentUtils.getInstance().CK_ACCOUNT));
        list.add(new BaseCheckBean(CHOOSE, "时间", "使用日期", "", "Time", true, 2, false, ""));
        list.add(new BaseCheckBean(EDIT, "类型", "使用地点", "", "Address", true, 2, false, ""));
        list.add(new BaseCheckBean(INVOINCE, "类型", "发票", "0", "IsOpenInvoice", false, 2, false, ""));
        list.add(new BaseCheckBean());
        if (listNew != null) {
            listNew.addAll(list);
        }
        return list;
    }

    //获取集合类型
    public List<BaseCheckBean> getListType(int type, Context context, TextView tvTitle, String name, String peopleType, String approveType) {
        switch (type) {
            //其他审批
            case 1100:
                tvTitle.setText(name + "其他审批详情");
                return otherCheck(null);
            //费用报销
            case 1:
                tvTitle.setText(name + "费用报销详情");
                return getFeetBaoList(null);
            //普通用款
            case 2:
                tvTitle.setText(name + "普通用款详情");
                return getCommonMoney(null);
            //请假
            case 3:
                tvTitle.setText(name + "请假详情");
                return getHolidayData(null, "holiday");
            //出差
            case 4:
                tvTitle.setText(name + "出差详情");
                return getHolidayData(null, "work");
            //承兑
            case 1006:
                tvTitle.setText(name + "承兑详情");
                return getChenDuiMoney(null);
            //业务用款
            case 1201:
                tvTitle.setText(name + "业务用款详情");
                return ywMoney(null, context, null, peopleType, approveType, null);
            //内部往来款
            case 1401:
                tvTitle.setText(name + "内部往来款详情");
                return inMoneyData(null);
            case 1011:
                tvTitle.setText(name + "白条详情");
                return inBtData(null, peopleType);
            //白条额度
            case 1010:
                tvTitle.setText(name + "白条额度详情");
                return inBtEdData(null);
            case 1301:
                tvTitle.setText(name + "垫付款");
                return dianFuMoney(null, context, null, peopleType, null);

        }
        List<BaseCheckBean> list = new ArrayList<>();
        return list;
    }


    //获取集合
    public List<BaseCheckBean> getDetailList(List<BaseCheckBean> listType, BaseCheckDetailBean.DBean.ReObjBean approveDetailListBean) {
        List<BaseCheckBean> list = new ArrayList<>();
        int num = listType.size();
        for (int i = 0; i < num; i++) {
            if (StringUtils.isNoEmpty(listType.get(i).getInterfaceKey())) {
                BaseCheckBean baseCheckBean = new BaseCheckBean();
                baseCheckBean.setHintType(listType.get(i).getHintType());
                if (approveDetailListBean.getIousPayApproval() != null) {
                    baseCheckBean.setContent(StringUtils.getNoEmptyStr("" + getFieldValueByName(approveDetailListBean.getIousPayApproval(), listType.get(i).getInterfaceKey())));
                } else if (approveDetailListBean.getIousUserApproval() != null) {
                    baseCheckBean.setContent(StringUtils.getNoEmptyStr("" + getFieldValueByName(approveDetailListBean.getIousUserApproval(), listType.get(i).getInterfaceKey())));
                } else {
                    baseCheckBean.setContent(StringUtils.getNoEmptyStr("" + getFieldValueByName(approveDetailListBean, listType.get(i).getInterfaceKey())));
                }
                baseCheckBean.setMust(listType.get(i).isMust());
                baseCheckBean.setChooseType(listType.get(i).getChooseType());
                baseCheckBean.setEditType(listType.get(i).getEditType());
                baseCheckBean.setAccountKey(listType.get(i).getAccountKey());
                baseCheckBean.setInterfaceKey(listType.get(i).getInterfaceKey());
                if (StringUtils.isNoEmpty(baseCheckBean.getContent())) {
                    baseCheckBean.setHide(false);
                } else {
                    baseCheckBean.setHide(true);
                }
//                baseCheckBean.setHide(listType.get(i).isHide());
                baseCheckBean.setType(listType.get(i).getType());
                list.add(baseCheckBean);
            }
        }
        if (list.size() > 0 && StringUtils.isNoEmpty(list.get(0).getContent()) && list.get(0).getContent().equals("设备更换")) {
            list.get(1).setHide(true);
            list.get(2).setHide(true);
            list.get(3).setHide(true);
        }
        return list;
    }

    public List<BaseCheckBean> getBaseDetailList(List<BaseCheckBean> listType, BaseCheckDetailBean.DBean.ReObjBean approveDetailListBean) {
        List<BaseCheckBean> list = new ArrayList<>();
        int num = listType.size();
        for (int i = 0; i < num; i++) {
            if (StringUtils.isNoEmpty(listType.get(i).getInterfaceKey())) {
                BaseCheckBean baseCheckBean = new BaseCheckBean();
                baseCheckBean.setHintType(listType.get(i).getHintType());
                if (approveDetailListBean.getIousPayApproval() != null) {
                    baseCheckBean.setContent(StringUtils.getNoEmptyStr("" + getFieldValueByName(approveDetailListBean.getIousPayApproval(), listType.get(i).getInterfaceKey())));
                } else if (approveDetailListBean.getIousUserApproval() != null) {
                    baseCheckBean.setContent(StringUtils.getNoEmptyStr("" + getFieldValueByName(approveDetailListBean.getIousUserApproval(), listType.get(i).getInterfaceKey())));
                } else {
                    baseCheckBean.setContent(StringUtils.getNoEmptyStr("" + getFieldValueByName(approveDetailListBean, listType.get(i).getInterfaceKey())));
                }
                baseCheckBean.setMust(listType.get(i).isMust());
                baseCheckBean.setChooseType(listType.get(i).getChooseType());
                baseCheckBean.setEditType(listType.get(i).getEditType());
                baseCheckBean.setAccountKey(listType.get(i).getAccountKey());
                baseCheckBean.setInterfaceKey(listType.get(i).getInterfaceKey());
                if (StringUtils.isNoEmpty(baseCheckBean.getContent())) {
                    baseCheckBean.setHide(false);
                } else {
                    baseCheckBean.setHide(listType.get(i).isHide());
                }
//                baseCheckBean.setHide(listType.get(i).isHide());
                baseCheckBean.setType(listType.get(i).getType());
                list.add(baseCheckBean);
            }
            if (listType.get(i).getType() == TITLE) {
                BaseCheckBean baseCheckBean = new BaseCheckBean();
                baseCheckBean.setType(TITLE);
                baseCheckBean.setHintType(listType.get(i).getHintType());
                list.add(baseCheckBean);
            }
            if (listType.get(i).getType() == DEFAULT) {
                BaseCheckBean baseCheckBean = new BaseCheckBean();
                baseCheckBean.setType(TXT);
                baseCheckBean.setHintType(listType.get(i).getHintType());
                baseCheckBean.setContent(listType.get(i).getContent());
                list.add(baseCheckBean);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            if (StringUtils.isNoEmpty(list.get(i).getContent()) && list.get(i).getContent().equals("贸易商")) {
                list.get(i + 1).setHide(false);
            }
            if (StringUtils.isNoEmpty(list.get(i).getContent()) && list.get(i).getContent().equals("私账")) {
                list.get(i + 1).setHide(false);
            }
            if (StringUtils.isNoEmpty(list.get(i).getContent()) && list.get(i).getContent().equals("垫付")) {
                list.get(i + 1).setHide(false);
            }
        }
        if (list.size() > 0 && StringUtils.isNoEmpty(list.get(0).getContent()) && list.get(0).getContent().equals("设备更换")) {
            list.get(1).setHide(true);
            list.get(2).setHide(true);
            list.get(3).setHide(true);
        }

        return list;
    }


    public void getBtDetailList(List<BaseCheckBean> listType, BaseCheckDetailBean.DBean.ReObjBean.IousPayApprovalBean approveDetailListBean, BaseCheckAdapter checkBaseAdapter) {
        int num = listType.size();
        for (int i = 0; i < num; i++) {
            if (StringUtils.isNoEmpty(listType.get(i).getInterfaceKey())) {
                listType.get(i).setContent(StringUtils.getNoNullStr("" + getFieldValueByName(approveDetailListBean, listType.get(i).getInterfaceKey())));
            }
        }
        checkBaseAdapter.notifyDataSetChanged();
    }

    public void getYwDetailList(List<BaseCheckBean> listType, BaseCheckDetailBean.DBean.ReObjBean approveDetailListBean, BaseCheckAdapter checkBaseAdapter) {
        int num = listType.size();
        for (int i = 0; i < num; i++) {
            if (StringUtils.isNoEmpty(listType.get(i).getInterfaceKey())) {
                listType.get(i).setContent(StringUtils.getNoNullStr("" + getFieldValueByName(approveDetailListBean, listType.get(i).getInterfaceKey())));
            }
            if (StringUtils.isNoEmpty(listType.get(i).getContent())) {
                listType.get(i).setHide(false);
            }
        }
        checkBaseAdapter.notifyDataSetChanged();
    }

    public static Object getFieldValueByName(Object o, String fieldName) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(o, new Object[]{});
            return value;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //数据初始化
    public void initData(Context context, int id, List<BaseCheckBean> list, RelativeLayout rlChooseChcek, LinearLayout llCheck, boolean isCheck, String peopleType, RelativeLayout llImg, TextView tvIntro, TextView tvChoose) {
        switch (id) {
            //报销申请
            case 1:
                getFeetBaoList(list);
                tvIntro.setVisibility(View.GONE);
                break;
            //用款申请
            case 2:
                getCommonMoney(list);
                tvIntro.setVisibility(View.GONE);
                break;
            //请假申请
            case 3:
                getHolidayData(list, "holiday");
                tvChoose.setVisibility(View.GONE);
                break;
            //出差申请
            case 4:
                getHolidayData(list, "work");
                tvChoose.setVisibility(View.GONE);
                break;
            //承兑申请
            case 1006:
                getChenDuiMoney(list);
                tvIntro.setVisibility(View.GONE);
                break;
            //其他审批
            case 1100:
                otherCheck(list);
                tvChoose.setVisibility(View.GONE);
                break;
            //业务用款
            case 16:
            case 1201:
                ywMoney(list, context, llCheck, peopleType, "", llImg);
                tvChoose.setVisibility(View.GONE);
                isCheck = false;
                break;
            //内部往来款
            case 1401:
                tvChoose.setVisibility(View.GONE);
                inMoneyData(list);
                break;
            //白条用款
            case 1011:
                inBtData(list, peopleType);
                tvChoose.setVisibility(View.GONE);
                break;
            case 1010:
                inBtEdData(list);
                tvChoose.setVisibility(View.GONE);
                break;
            case 1301:
                dianFuMoney(list, context, llCheck, peopleType, llImg);
                tvChoose.setVisibility(View.GONE);
                break;
        }
    }

    //提交数据
    public void commitData(final AppCompatActivity context, List<BaseCheckBean> list, String typeId,
                           String remark, String pics, String approveUserId, String copyUserId, String bankId, int editType, String approveId) {
        Map<String, Object> map = new HashMap<>();
        for (BaseCheckBean bean : list) {
            if (StringUtils.isNoEmpty(bean.getContent()) && !bean.isHide()) {
                map.put(bean.getInterfaceKey(), bean.getContent());
            }
        }
        map.put(FieldConstant.getInstance().EDIT_TYPE_ID, editType);
        map.put(FieldConstant.getInstance().APPROVE_TYPE_ID, typeId);
        map.put(FieldConstant.getInstance().COPY_USER_ID, copyUserId);
        map.put(FieldConstant.getInstance().APPROVE_USER_ID, approveUserId);
        if (StringUtils.isNoEmpty(approveId)) {
            map.put(FieldConstant.getInstance().APPROVE_ID, approveId);
            map.put(FieldConstant.getInstance().APPROVE_OVER, 1);
        }
        map.put(FieldConstant.getInstance().PICS, pics);
        map.put(FieldConstant.getInstance().REMARK, StringUtils.getNoEmptyStr(remark));
        map.put(FieldConstant.getInstance().USER_ID, PersonConstant.getInstance().getUserId());
        map.put(FieldConstant.getInstance().SP_BANK_ID, bankId);
        String json = "{\n" +
                "\"model\":" + JSON.toJSONString(map) + "}";
        OkhttpUtil.getInstance().basePostCallDialog(context, json, UrlConstant.getInstance().POST_COMMON_CHECK + "AddApprovalCommonApi", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                BaseCheckResultBean successBean = JSON.parseObject(str, BaseCheckResultBean.class);
                if (successBean.getD() != null && successBean.getD().getSuccess() == 1) {
                    ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().approveCheckList);
                }
                ToastUtils.getMineToast(successBean.getD().getErrMsg());
            }

            @Override
            public void onFail() {

            }
        });
    }
}
