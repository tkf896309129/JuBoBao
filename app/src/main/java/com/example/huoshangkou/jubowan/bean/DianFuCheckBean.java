package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：DianFuCheckBean
 * 描述：
 * 创建时间：2019-10-10  15:13
 */

public class DianFuCheckBean  {


    /**
     * d : {"ReObj":{"__type":"Model.ApprovalOfPayment.PadPayment.PadPaymentDetails","ApprovalOfMsgs":[],"BorrowingId":28092,"ApprovalTypeID":1301,"OrderId":"20201903130758315186","ApplyUnit":"浙江mount单位","Legalperson":"测试","LoanUsage":"垫付","LoanAmount":1000,"LoanDate":null,"BackMoneyDate":"2019-10-01","PlatformOutMoneyAccount":null,"PlatformOutMoneyNature":null,"IsOutMoney":null,"SupplierNature":null,"InMoneySupplier":null,"IsOpenInvoice":null,"CreateTime":null,"Profit":0,"PayAmount":0,"Operator":null,"Remark":null,"Pics":null},"Success":1,"ErrMsg":"获取成功！"}
     */

    private DBean d;

    public DBean getD() {
        return d;
    }

    public void setD(DBean d) {
        this.d = d;
    }

    public static class DBean {
        /**
         * ReObj : {"__type":"Model.ApprovalOfPayment.PadPayment.PadPaymentDetails","ApprovalOfMsgs":[],"BorrowingId":28092,"ApprovalTypeID":1301,"OrderId":"20201903130758315186","ApplyUnit":"浙江mount单位","Legalperson":"测试","LoanUsage":"垫付","LoanAmount":1000,"LoanDate":null,"BackMoneyDate":"2019-10-01","PlatformOutMoneyAccount":null,"PlatformOutMoneyNature":null,"IsOutMoney":null,"SupplierNature":null,"InMoneySupplier":null,"IsOpenInvoice":null,"CreateTime":null,"Profit":0,"PayAmount":0,"Operator":null,"Remark":null,"Pics":null}
         * Success : 1
         * ErrMsg : 获取成功！
         */

        private ReObjBean ReObj;
        private int Success;
        private String ErrMsg;

        public ReObjBean getReObj() {
            return ReObj;
        }

        public void setReObj(ReObjBean ReObj) {
            this.ReObj = ReObj;
        }

        public int getSuccess() {
            return Success;
        }

        public void setSuccess(int Success) {
            this.Success = Success;
        }

        public String getErrMsg() {
            return ErrMsg;
        }

        public void setErrMsg(String ErrMsg) {
            this.ErrMsg = ErrMsg;
        }

        public static class ReObjBean {
            /**
             * __type : Model.ApprovalOfPayment.PadPayment.PadPaymentDetails
             * ApprovalOfMsgs : []
             * BorrowingId : 28092
             * ApprovalTypeID : 1301
             * OrderId : 20201903130758315186
             * ApplyUnit : 浙江mount单位
             * Legalperson : 测试
             * LoanUsage : 垫付
             * LoanAmount : 1000.0
             * LoanDate : null
             * BackMoneyDate : 2019-10-01
             * PlatformOutMoneyAccount : null
             * PlatformOutMoneyNature : null
             * IsOutMoney : null
             * SupplierNature : null
             * InMoneySupplier : null
             * IsOpenInvoice : null
             * CreateTime : null
             * Profit : 0.0
             * PayAmount : 0.0
             * Operator : null
             * Remark : null
             * Pics : null
             */

            private String __type;
            private int BorrowingId;
            private int ApprovalTypeID;
            private String OrderId;
            private String ApplyUnit;
            private String Legalperson;
            private String LoanUsage;
            private double LoanAmount;
            private String LoanDate;
            private String BackMoneyDate;
            private String PlatformOutMoneyAccount;
            private String PlatformOutMoneyNature;
            private String IsOutMoney;
            private String SupplierNature;
            private String InMoneySupplier;
            private String IsOpenInvoice;
            private String CreateTime;
            private double Profit;
            private double PayAmount;
            private String Operator;
            private String Remark;
            private String Pics;
            private List<ApproveListBean> ApprovalOfMsgs;

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public int getBorrowingId() {
                return BorrowingId;
            }

            public void setBorrowingId(int BorrowingId) {
                this.BorrowingId = BorrowingId;
            }

            public int getApprovalTypeID() {
                return ApprovalTypeID;
            }

            public void setApprovalTypeID(int ApprovalTypeID) {
                this.ApprovalTypeID = ApprovalTypeID;
            }

            public String getOrderId() {
                return OrderId;
            }

            public void setOrderId(String OrderId) {
                this.OrderId = OrderId;
            }

            public String getApplyUnit() {
                return ApplyUnit;
            }

            public void setApplyUnit(String ApplyUnit) {
                this.ApplyUnit = ApplyUnit;
            }

            public String getLegalperson() {
                return Legalperson;
            }

            public void setLegalperson(String Legalperson) {
                this.Legalperson = Legalperson;
            }

            public String getLoanUsage() {
                return LoanUsage;
            }

            public void setLoanUsage(String LoanUsage) {
                this.LoanUsage = LoanUsage;
            }

            public double getLoanAmount() {
                return LoanAmount;
            }

            public void setLoanAmount(double LoanAmount) {
                this.LoanAmount = LoanAmount;
            }

            public String getLoanDate() {
                return LoanDate;
            }

            public void setLoanDate(String LoanDate) {
                this.LoanDate = LoanDate;
            }

            public String getBackMoneyDate() {
                return BackMoneyDate;
            }

            public void setBackMoneyDate(String BackMoneyDate) {
                this.BackMoneyDate = BackMoneyDate;
            }

            public String getPlatformOutMoneyAccount() {
                return PlatformOutMoneyAccount;
            }

            public void setPlatformOutMoneyAccount(String PlatformOutMoneyAccount) {
                this.PlatformOutMoneyAccount = PlatformOutMoneyAccount;
            }

            public String getPlatformOutMoneyNature() {
                return PlatformOutMoneyNature;
            }

            public void setPlatformOutMoneyNature(String PlatformOutMoneyNature) {
                this.PlatformOutMoneyNature = PlatformOutMoneyNature;
            }

            public String getIsOutMoney() {
                return IsOutMoney;
            }

            public void setIsOutMoney(String IsOutMoney) {
                this.IsOutMoney = IsOutMoney;
            }

            public String getSupplierNature() {
                return SupplierNature;
            }

            public void setSupplierNature(String SupplierNature) {
                this.SupplierNature = SupplierNature;
            }

            public String getInMoneySupplier() {
                return InMoneySupplier;
            }

            public void setInMoneySupplier(String InMoneySupplier) {
                this.InMoneySupplier = InMoneySupplier;
            }

            public String getIsOpenInvoice() {
                return IsOpenInvoice;
            }

            public void setIsOpenInvoice(String IsOpenInvoice) {
                this.IsOpenInvoice = IsOpenInvoice;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public double getProfit() {
                return Profit;
            }

            public void setProfit(double Profit) {
                this.Profit = Profit;
            }

            public double getPayAmount() {
                return PayAmount;
            }

            public void setPayAmount(double PayAmount) {
                this.PayAmount = PayAmount;
            }

            public String getOperator() {
                return Operator;
            }

            public void setOperator(String Operator) {
                this.Operator = Operator;
            }

            public String getRemark() {
                return Remark;
            }

            public void setRemark(String Remark) {
                this.Remark = Remark;
            }

            public String getPics() {
                return Pics;
            }

            public void setPics(String Pics) {
                this.Pics = Pics;
            }

            public List<ApproveListBean> getApprovalOfMsgs() {
                return ApprovalOfMsgs;
            }

            public void setApprovalOfMsgs(List<ApproveListBean> ApprovalOfMsgs) {
                this.ApprovalOfMsgs = ApprovalOfMsgs;
            }
        }
    }
}
