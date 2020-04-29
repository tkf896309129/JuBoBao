package com.example.huoshangkou.jubowan.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：OrderListsBean
 * 描述：
 * 创建时间：2017-05-08  17:16
 */

public class OrderListsBean implements Serializable {
    private int State;
    private int Capability;
    private int CommitScore;
    private String CommitTxt;
    private int Serviceattitude;
    private String CreateTime;
    private String IsShengChengWeiTuoShu;
    private int IsEvaluate;
    private String IsPtwl;
    private String TIHuoWeiTuoShuEdit;
    private String LinkMan;
    private String LinkTel;
    private String NeedPayAmount;
    private String OrderID;
    private String ShengYuPrice;
    private List<OrderListTypeBean> OrderListf;
    private List<OrderListTypeBean> OrderListw;
    private List<OrderListTypeBean> OrderListwb;
    private List<OrderListTypeBean> OrderListy;
    private List<OrderListTypeBean> OrderListp;
    private List<OrderListTypeBean> OrderListyl;
    private int OrderState;
    private int ServeID;
    private String OrderType;
    private String Number;
    private String Provinces;
    private String TotalPrice;
    private int UserID;
    private String WlPrice;
    private String YuFuPrice;
    private String addtime;
    private int PayTpye;
    private int PayWay;
    private int AdvanceState;
    private int IsAdvance;
    private int IsAllowPadPaymentPay;
    private boolean isMyRepair = false;


    public List<OrderListTypeBean> getOrderListyl() {
        return OrderListyl;
    }

    public void setOrderListyl(List<OrderListTypeBean> orderListyl) {
        OrderListyl = orderListyl;
    }

    public int getIsAllowPadPaymentPay() {
        return IsAllowPadPaymentPay;
    }

    public void setIsAllowPadPaymentPay(int isAllowPadPaymentPay) {
        IsAllowPadPaymentPay = isAllowPadPaymentPay;
    }

    public String getNeedPayAmount() {
        return NeedPayAmount;
    }

    public void setNeedPayAmount(String needPayAmount) {
        NeedPayAmount = needPayAmount;
    }

    public String getShengYuPrice() {
        return ShengYuPrice;
    }

    public void setShengYuPrice(String shengYuPrice) {
        ShengYuPrice = shengYuPrice;
    }

    public List<OrderListTypeBean> getOrderListwb() {
        return OrderListwb;
    }

    public void setOrderListwb(List<OrderListTypeBean> orderListwb) {
        OrderListwb = orderListwb;
    }

    public String getYuFuPrice() {
        return YuFuPrice;
    }

    public void setYuFuPrice(String yuFuPrice) {
        YuFuPrice = yuFuPrice;
    }

    public String getTIHuoWeiTuoShuEdit() {
        return TIHuoWeiTuoShuEdit;
    }

    public void setTIHuoWeiTuoShuEdit(String TIHuoWeiTuoShuEdit) {
        this.TIHuoWeiTuoShuEdit = TIHuoWeiTuoShuEdit;
    }

    public String getIsShengChengWeiTuoShu() {
        return IsShengChengWeiTuoShu;
    }

    public void setIsShengChengWeiTuoShu(String isShengChengWeiTuoShu) {
        IsShengChengWeiTuoShu = isShengChengWeiTuoShu;
    }

    public int getAdvanceState() {
        return AdvanceState;
    }

    public void setAdvanceState(int advanceState) {
        AdvanceState = advanceState;
    }

    public int getIsAdvance() {
        return IsAdvance;
    }

    public void setIsAdvance(int isAdvance) {
        IsAdvance = isAdvance;
    }

    public List<OrderListTypeBean> getOrderListp() {
        return OrderListp;
    }

    public void setOrderListp(List<OrderListTypeBean> orderListp) {
        OrderListp = orderListp;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public boolean isMyRepair() {
        return isMyRepair;
    }

    public void setMyRepair(boolean myRepair) {
        isMyRepair = myRepair;
    }

    public int getServeID() {
        return ServeID;
    }

    public void setServeID(int serveID) {
        ServeID = serveID;
    }

    public int getPayWay() {
        return PayWay;
    }

    public void setPayWay(int payWay) {
        PayWay = payWay;
    }

    public String getIsPtwl() {
        return IsPtwl;
    }

    public void setIsPtwl(String isPtwl) {
        IsPtwl = isPtwl;
    }

    public int getIsEvaluate() {
        return IsEvaluate;
    }

    public void setIsEvaluate(int isEvaluate) {
        IsEvaluate = isEvaluate;
    }

    public String getWlPrice() {
        return WlPrice;
    }

    public void setWlPrice(String wlPrice) {
        WlPrice = wlPrice;
    }

    public String getCommitTxt() {
        return CommitTxt;
    }

    public void setCommitTxt(String commitTxt) {
        CommitTxt = commitTxt;
    }

    public int getCapability() {
        return Capability;
    }

    public void setCapability(int capability) {
        Capability = capability;
    }

    public int getCommitScore() {
        return CommitScore;
    }

    public void setCommitScore(int commitScore) {
        CommitScore = commitScore;
    }

    public int getServiceattitude() {
        return Serviceattitude;
    }

    public void setServiceattitude(int serviceattitude) {
        Serviceattitude = serviceattitude;
    }

    public int getPayTpye() {
        return PayTpye;
    }

    public void setPayTpye(int payTpye) {
        PayTpye = payTpye;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getLinkMan() {
        return LinkMan;
    }

    public void setLinkMan(String linkMan) {
        LinkMan = linkMan;
    }

    public String getLinkTel() {
        return LinkTel;
    }

    public void setLinkTel(String linkTel) {
        LinkTel = linkTel;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public List<OrderListTypeBean> getOrderListf() {
        return OrderListf;
    }

    public void setOrderListf(List<OrderListTypeBean> orderListf) {
        OrderListf = orderListf;
    }

    public List<OrderListTypeBean> getOrderListw() {
        return OrderListw;
    }

    public void setOrderListw(List<OrderListTypeBean> orderListw) {
        OrderListw = orderListw;
    }

    public List<OrderListTypeBean> getOrderListy() {
        return OrderListy;
    }

    public void setOrderListy(List<OrderListTypeBean> orderListy) {
        OrderListy = orderListy;
    }

    public int getOrderState() {
        return OrderState;
    }

    public void setOrderState(int orderState) {
        OrderState = orderState;
    }

    public String getOrderType() {
        return OrderType;
    }

    public void setOrderType(String orderType) {
        OrderType = orderType;
    }

    public String getProvinces() {
        return Provinces;
    }

    public void setProvinces(String provinces) {
        Provinces = provinces;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }
}
