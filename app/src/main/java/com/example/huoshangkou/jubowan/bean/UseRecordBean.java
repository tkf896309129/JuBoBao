package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：UseRecordBean
 * 描述：
 * 创建时间：2018-08-30  10:21
 */

public class UseRecordBean {


    /**
     * ErrMsg :
     * PageCount : 1
     * PageIndex : 1
     * ReList : [{"BuyMoney":60844.84,"CreateTime":"/Date(1535472000000+0800)/","Diffdays":6,"ID":5041,"NeedRefundAmount":60530.24,"OrderID":"20201808291743123938","StartTime":"/Date(1529856000000+0800)/","State":2,"StateName":"已逾期"}]
     * Success : 1
     * TotalCount : 1
     * TotalSum : 0
     */

    private String ErrMsg;
    private int PageCount;
    private int PageIndex;
    private int Success;
    private int TotalCount;
    private String TotalSum;
    private List<ReListBean> ReList;

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String ErrMsg) {
        this.ErrMsg = ErrMsg;
    }

    public int getPageCount() {
        return PageCount;
    }

    public void setPageCount(int PageCount) {
        this.PageCount = PageCount;
    }

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int PageIndex) {
        this.PageIndex = PageIndex;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int Success) {
        this.Success = Success;
    }

    public int getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(int TotalCount) {
        this.TotalCount = TotalCount;
    }

    public String getTotalSum() {
        return TotalSum;
    }

    public void setTotalSum(String TotalSum) {
        this.TotalSum = TotalSum;
    }

    public List<ReListBean> getReList() {
        return ReList;
    }

    public void setReList(List<ReListBean> ReList) {
        this.ReList = ReList;
    }

    public static class ReListBean {
        /**
         * BuyMoney : 60844.84
         * CreateTime : /Date(1535472000000+0800)/
         * Diffdays : 6
         * ID : 5041
         * NeedRefundAmount : 60530.24
         * OrderID : 20201808291743123938
         * StartTime : /Date(1529856000000+0800)/
         * State : 2
         * StateName : 已逾期
         */

        private double BuyMoney;
        private String AccountName;
        private String AccountNum;
        private String CreateTime;
        private int Diffdays;
        private int ID;
        private double NeedRefundAmount;
        private String OrderID;
        private String StartTime;
        private int State;
        private String StateName;

        public String getAccountName() {
            return AccountName;
        }

        public void setAccountName(String accountName) {
            AccountName = accountName;
        }

        public String getAccountNum() {
            return AccountNum;
        }

        public void setAccountNum(String accountNum) {
            AccountNum = accountNum;
        }

        public double getBuyMoney() {
            return BuyMoney;
        }

        public void setBuyMoney(double BuyMoney) {
            this.BuyMoney = BuyMoney;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public int getDiffdays() {
            return Diffdays;
        }

        public void setDiffdays(int Diffdays) {
            this.Diffdays = Diffdays;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public double getNeedRefundAmount() {
            return NeedRefundAmount;
        }

        public void setNeedRefundAmount(double NeedRefundAmount) {
            this.NeedRefundAmount = NeedRefundAmount;
        }

        public String getOrderID() {
            return OrderID;
        }

        public void setOrderID(String OrderID) {
            this.OrderID = OrderID;
        }

        public String getStartTime() {
            return StartTime;
        }

        public void setStartTime(String StartTime) {
            this.StartTime = StartTime;
        }

        public int getState() {
            return State;
        }

        public void setState(int State) {
            this.State = State;
        }

        public String getStateName() {
            return StateName;
        }

        public void setStateName(String StateName) {
            this.StateName = StateName;
        }
    }
}
