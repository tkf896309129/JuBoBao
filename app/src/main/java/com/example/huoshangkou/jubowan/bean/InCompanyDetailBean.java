package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：InCompanyDetailBean
 * 描述：
 * 创建时间：2019-10-31  14:45
 */

public class InCompanyDetailBean {


    /**
     * d : {"ReObj":{"__type":"Atjubo.DTO.Output.DealingPayment.ApprovalDetailsOutput","ApprovalOfMsgs":[{"ID":163621,"ApprovalID":15481,"ApprovalUserID":234,"ApprovalUserName":"唐凯峰","ApprovalOver":-1,"Approvalremark":"","ApprovalPic":"","CreateDate":"/Date(1572489936747)/","CreateTime":"2019-10-31 10:45","HeadPic":"http://tvax4.sinaimg.cn/default/images/default_avatar_male_50.gif"}],"BorrowingId":0,"ApprovalTypeID":1401,"PaymentUnitAccount":"河北万良玻璃科技有限公司((程良云)中国银行杭州萧山信息港支行6217886200001329514)","PaymentAmount":800,"PaymentMethod":null,"ReceiptUnitAccount":"上海凯晨玻璃科技有限公司(中国银行上海市航头支行455977964404)","Pics":"","Remark":"kfkfk"},"Success":1,"ErrMsg":"获取成功"}
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
         * ReObj : {"__type":"Atjubo.DTO.Output.DealingPayment.ApprovalDetailsOutput","ApprovalOfMsgs":[{"ID":163621,"ApprovalID":15481,"ApprovalUserID":234,"ApprovalUserName":"唐凯峰","ApprovalOver":-1,"Approvalremark":"","ApprovalPic":"","CreateDate":"/Date(1572489936747)/","CreateTime":"2019-10-31 10:45","HeadPic":"http://tvax4.sinaimg.cn/default/images/default_avatar_male_50.gif"}],"BorrowingId":0,"ApprovalTypeID":1401,"PaymentUnitAccount":"河北万良玻璃科技有限公司((程良云)中国银行杭州萧山信息港支行6217886200001329514)","PaymentAmount":800,"PaymentMethod":null,"ReceiptUnitAccount":"上海凯晨玻璃科技有限公司(中国银行上海市航头支行455977964404)","Pics":"","Remark":"kfkfk"}
         * Success : 1
         * ErrMsg : 获取成功
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
             * __type : Atjubo.DTO.Output.DealingPayment.ApprovalDetailsOutput
             * ApprovalOfMsgs : [{"ID":163621,"ApprovalID":15481,"ApprovalUserID":234,"ApprovalUserName":"唐凯峰","ApprovalOver":-1,"Approvalremark":"","ApprovalPic":"","CreateDate":"/Date(1572489936747)/","CreateTime":"2019-10-31 10:45","HeadPic":"http://tvax4.sinaimg.cn/default/images/default_avatar_male_50.gif"}]
             * BorrowingId : 0
             * ApprovalTypeID : 1401
             * PaymentUnitAccount : 河北万良玻璃科技有限公司((程良云)中国银行杭州萧山信息港支行6217886200001329514)
             * PaymentAmount : 800.0
             * PaymentMethod : null
             * ReceiptUnitAccount : 上海凯晨玻璃科技有限公司(中国银行上海市航头支行455977964404)
             * Pics :
             * Remark : kfkfk
             */

            private String __type;
            private int BorrowingId;
            private int ApprovalTypeID;
            private String PaymentUnitAccount;
            private double PaymentAmount;
            private String PaymentMethod;
            private String ReceiptUnitAccount;
            private String Pics;
            private String Remark;
            private List<ApproveListBean> ApprovalMsgList;

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

            public String getPaymentUnitAccount() {
                return PaymentUnitAccount;
            }

            public void setPaymentUnitAccount(String PaymentUnitAccount) {
                this.PaymentUnitAccount = PaymentUnitAccount;
            }

            public double getPaymentAmount() {
                return PaymentAmount;
            }

            public void setPaymentAmount(double PaymentAmount) {
                this.PaymentAmount = PaymentAmount;
            }

            public String getPaymentMethod() {
                return PaymentMethod;
            }

            public void setPaymentMethod(String PaymentMethod) {
                this.PaymentMethod = PaymentMethod;
            }

            public String getReceiptUnitAccount() {
                return ReceiptUnitAccount;
            }

            public void setReceiptUnitAccount(String ReceiptUnitAccount) {
                this.ReceiptUnitAccount = ReceiptUnitAccount;
            }

            public String getPics() {
                return Pics;
            }

            public void setPics(String Pics) {
                this.Pics = Pics;
            }

            public String getRemark() {
                return Remark;
            }

            public void setRemark(String Remark) {
                this.Remark = Remark;
            }

            public List<ApproveListBean> getApprovalOfMsgs() {
                return ApprovalMsgList;
            }

            public void setApprovalOfMsgs(List<ApproveListBean> ApprovalMsgList) {
                this.ApprovalMsgList = ApprovalMsgList;
            }


        }
    }
}
