package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：YwNewDetailBean
 * 描述：
 * 创建时间：2019-03-07  10:25
 */

public class YwNewDetailBean {


    /**
     * d : {"ReObj":{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.BusinessFunds.BusinessFundsDetails","ApprovalOfMsgs":[{"ID":65215,"ApprovalID":9740,"ApprovalUserID":5275,"ApprovalUserName":"大唐","ApprovalOver":-1,"Approvalremark":"名字","ApprovalPic":"","CreateTime":"2019-03-06","HeadPic":""}],"BorrowingId":0,"ApprovalTypeID":1201,"PayingCustomers":" 建筑商55","CustomerType":"0","ParentTrader":"阿坝县祥云装饰有限公司","CustomerAccountType":"1","Payer":"6666","CustomerPaymentMethod":"电汇","CustmerPaymentAmount":8000,"CustomerGoodsMoneyNature":"自付货款","CustomerRemark":"移民","PlatformInMoneyAccount":"浙江火山口网络科技有限公司(中国银行萧山城中支行3844693)","PlatformInMoneyMethod":"电汇","PlatformOutMoneyAccount":"浙江火山口网络科技有限公司(农商银行城北支行2010001559)","PlatformGoodsMoneyNature":"融资","GoodsMoneyPurpose":"移民","PayableAmount":8000,"PlatformOutMoneyMethod":"电汇","IsOutMoney":1,"SupplierNature":null,"InMoneySupplier":null,"IsOpenInvoice":0,"CreateTime":null,"Profit":0,"Operator":null,"Remark":"名字","Pics":""},"Success":1,"ErrMsg":"成功获取业务用款审批详情！"}
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
         * ReObj : {"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.BusinessFunds.BusinessFundsDetails","ApprovalOfMsgs":[{"ID":65215,"ApprovalID":9740,"ApprovalUserID":5275,"ApprovalUserName":"大唐","ApprovalOver":-1,"Approvalremark":"名字","ApprovalPic":"","CreateTime":"2019-03-06","HeadPic":""}],"BorrowingId":0,"ApprovalTypeID":1201,"PayingCustomers":" 建筑商55","CustomerType":"0","ParentTrader":"阿坝县祥云装饰有限公司","CustomerAccountType":"1","Payer":"6666","CustomerPaymentMethod":"电汇","CustmerPaymentAmount":8000,"CustomerGoodsMoneyNature":"自付货款","CustomerRemark":"移民","PlatformInMoneyAccount":"浙江火山口网络科技有限公司(中国银行萧山城中支行3844693)","PlatformInMoneyMethod":"电汇","PlatformOutMoneyAccount":"浙江火山口网络科技有限公司(农商银行城北支行2010001559)","PlatformGoodsMoneyNature":"融资","GoodsMoneyPurpose":"移民","PayableAmount":8000,"PlatformOutMoneyMethod":"电汇","IsOutMoney":1,"SupplierNature":null,"InMoneySupplier":null,"IsOpenInvoice":0,"CreateTime":null,"Profit":0,"Operator":null,"Remark":"名字","Pics":""}
         * Success : 1
         * ErrMsg : 成功获取业务用款审批详情！
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
             * __type : AtJubo.Api.App.ReModel.ApprovalOfPayment.BusinessFunds.BusinessFundsDetails
             * ApprovalOfMsgs : [{"ID":65215,"ApprovalID":9740,"ApprovalUserID":5275,"ApprovalUserName":"大唐","ApprovalOver":-1,"Approvalremark":"名字","ApprovalPic":"","CreateTime":"2019-03-06","HeadPic":""}]
             * BorrowingId : 0
             * ApprovalTypeID : 1201
             * PayingCustomers :  建筑商55
             * CustomerType : 0
             * ParentTrader : 阿坝县祥云装饰有限公司
             * CustomerAccountType : 1
             * Payer : 6666
             * CustomerPaymentMethod : 电汇
             * CustmerPaymentAmount : 8000.0
             * CustomerGoodsMoneyNature : 自付货款
             * CustomerRemark : 移民
             * PlatformInMoneyAccount : 浙江火山口网络科技有限公司(中国银行萧山城中支行3844693)
             * PlatformInMoneyMethod : 电汇
             * PlatformOutMoneyAccount : 浙江火山口网络科技有限公司(农商银行城北支行2010001559)
             * PlatformGoodsMoneyNature : 融资
             * GoodsMoneyPurpose : 移民
             * PayableAmount : 8000.0
             * PlatformOutMoneyMethod : 电汇
             * IsOutMoney : 1
             * SupplierNature : null
             * InMoneySupplier : null
             * IsOpenInvoice : 0
             * CreateTime : null
             * Profit : 0.0
             * Operator : null
             * Remark : 名字
             * Pics :
             */

            private String __type;
            private int BorrowingId;
            private int ApprovalTypeID;
            private String PayingCustomers;
            private String CustomerType;
            private String ParentTrader;
            private String CustomerAccountType;
            private String Payer;
            private String CustomerPaymentMethod;
            private double CustmerPaymentAmount;
            private String CustomerGoodsMoneyNature;
            private String CustomerRemark;
            private String PlatformInMoneyAccount;
            private String PlatformInMoneyMethod;
            private String PlatformOutMoneyAccount;
            private String PlatformGoodsMoneyNature;
            private String GoodsMoneyPurpose;
            private double PayableAmount;
            private String PlatformOutMoneyMethod;
            private String IsOutMoney;
            private String SupplierNature;
            private String InMoneySupplier;
            private String IsOpenInvoice;
            private String CreateTime;
            private double Profit;
            private String Operator;
            private String Remark;
            private String Pics;
            private String ApprovalPic;
            private String BackMoneyTime;
            private List<ApproveListBean> ApprovalOfMsgs;

            public String getBackMoneyTime() {
                return BackMoneyTime;
            }

            public void setBackMoneyTime(String backMoneyTime) {
                BackMoneyTime = backMoneyTime;
            }

            public String getApprovalPic() {
                return ApprovalPic;
            }

            public void setApprovalPic(String approvalPic) {
                ApprovalPic = approvalPic;
            }

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

            public String getPayingCustomers() {
                return PayingCustomers;
            }

            public void setPayingCustomers(String PayingCustomers) {
                this.PayingCustomers = PayingCustomers;
            }

            public String getCustomerType() {
                return CustomerType;
            }

            public void setCustomerType(String CustomerType) {
                this.CustomerType = CustomerType;
            }

            public String getParentTrader() {
                return ParentTrader;
            }

            public void setParentTrader(String ParentTrader) {
                this.ParentTrader = ParentTrader;
            }

            public String getCustomerAccountType() {
                return CustomerAccountType;
            }

            public void setCustomerAccountType(String CustomerAccountType) {
                this.CustomerAccountType = CustomerAccountType;
            }

            public String getPayer() {
                return Payer;
            }

            public void setPayer(String Payer) {
                this.Payer = Payer;
            }

            public String getCustomerPaymentMethod() {
                return CustomerPaymentMethod;
            }

            public void setCustomerPaymentMethod(String CustomerPaymentMethod) {
                this.CustomerPaymentMethod = CustomerPaymentMethod;
            }

            public double getCustmerPaymentAmount() {
                return CustmerPaymentAmount;
            }

            public void setCustmerPaymentAmount(double CustmerPaymentAmount) {
                this.CustmerPaymentAmount = CustmerPaymentAmount;
            }

            public String getCustomerGoodsMoneyNature() {
                return CustomerGoodsMoneyNature;
            }

            public void setCustomerGoodsMoneyNature(String CustomerGoodsMoneyNature) {
                this.CustomerGoodsMoneyNature = CustomerGoodsMoneyNature;
            }

            public String getCustomerRemark() {
                return CustomerRemark;
            }

            public void setCustomerRemark(String CustomerRemark) {
                this.CustomerRemark = CustomerRemark;
            }

            public String getPlatformInMoneyAccount() {
                return PlatformInMoneyAccount;
            }

            public void setPlatformInMoneyAccount(String PlatformInMoneyAccount) {
                this.PlatformInMoneyAccount = PlatformInMoneyAccount;
            }

            public String getPlatformInMoneyMethod() {
                return PlatformInMoneyMethod;
            }

            public void setPlatformInMoneyMethod(String PlatformInMoneyMethod) {
                this.PlatformInMoneyMethod = PlatformInMoneyMethod;
            }

            public String getPlatformOutMoneyAccount() {
                return PlatformOutMoneyAccount;
            }

            public void setPlatformOutMoneyAccount(String PlatformOutMoneyAccount) {
                this.PlatformOutMoneyAccount = PlatformOutMoneyAccount;
            }

            public String getPlatformGoodsMoneyNature() {
                return PlatformGoodsMoneyNature;
            }

            public void setPlatformGoodsMoneyNature(String PlatformGoodsMoneyNature) {
                this.PlatformGoodsMoneyNature = PlatformGoodsMoneyNature;
            }

            public String getGoodsMoneyPurpose() {
                return GoodsMoneyPurpose;
            }

            public void setGoodsMoneyPurpose(String GoodsMoneyPurpose) {
                this.GoodsMoneyPurpose = GoodsMoneyPurpose;
            }

            public double getPayableAmount() {
                return PayableAmount;
            }

            public void setPayableAmount(double PayableAmount) {
                this.PayableAmount = PayableAmount;
            }

            public String getPlatformOutMoneyMethod() {
                return PlatformOutMoneyMethod;
            }

            public void setPlatformOutMoneyMethod(String PlatformOutMoneyMethod) {
                this.PlatformOutMoneyMethod = PlatformOutMoneyMethod;
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
