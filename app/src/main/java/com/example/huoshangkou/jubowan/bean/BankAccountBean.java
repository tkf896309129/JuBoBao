package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：BankAccountBean
 * 描述：
 * 创建时间：2019-03-08  10:17
 */

public class BankAccountBean {


    /**
     * d : {"TotalSum":"0","TotalCount":0,"PageCount":0,"PageIndex":0,"ReList":[{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.PlatformBankAccount","ID":1,"Company":"浙江火山口网络科技有限公司","BankName":"中国银行萧山城中支行","BankAccount":"384469364178","Msg":"浙江火山口网络科技有限公司(中国银行萧山城中支行3844693)"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.PlatformBankAccount","ID":2,"Company":"浙江火山口网络科技有限公司","BankName":"泰隆银行萧山支行","BankAccount":"3302050120100024288","Msg":"浙江火山口网络科技有限公司(泰隆银行萧山支行33020501201000)"}]}
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
         * TotalSum : 0
         * TotalCount : 0
         * PageCount : 0
         * PageIndex : 0
         * ReList : [{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.PlatformBankAccount","ID":1,"Company":"浙江火山口网络科技有限公司","BankName":"中国银行萧山城中支行","BankAccount":"384469364178","Msg":"浙江火山口网络科技有限公司(中国银行萧山城中支行3844693)"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.PlatformBankAccount","ID":2,"Company":"浙江火山口网络科技有限公司","BankName":"泰隆银行萧山支行","BankAccount":"3302050120100024288","Msg":"浙江火山口网络科技有限公司(泰隆银行萧山支行33020501201000)"}]
         */

        private String TotalSum;
        private int TotalCount;
        private int PageCount;
        private int PageIndex;
        private List<ReListBean> ReList;

        public String getTotalSum() {
            return TotalSum;
        }

        public void setTotalSum(String TotalSum) {
            this.TotalSum = TotalSum;
        }

        public int getTotalCount() {
            return TotalCount;
        }

        public void setTotalCount(int TotalCount) {
            this.TotalCount = TotalCount;
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

        public List<ReListBean> getReList() {
            return ReList;
        }

        public void setReList(List<ReListBean> ReList) {
            this.ReList = ReList;
        }

        public static class ReListBean {
            /**
             * __type : AtJubo.Api.App.ReModel.ApprovalOfPayment.PlatformBankAccount
             * ID : 1
             * Company : 浙江火山口网络科技有限公司
             * BankName : 中国银行萧山城中支行
             * BankAccount : 384469364178
             * Msg : 浙江火山口网络科技有限公司(中国银行萧山城中支行3844693)
             */

            private String __type;
            private int ID;
            private String Company;
            private String BankName;
            private String BankAccount;
            private String Msg;

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
            }

            public String getCompany() {
                return Company;
            }

            public void setCompany(String Company) {
                this.Company = Company;
            }

            public String getBankName() {
                return BankName;
            }

            public void setBankName(String BankName) {
                this.BankName = BankName;
            }

            public String getBankAccount() {
                return BankAccount;
            }

            public void setBankAccount(String BankAccount) {
                this.BankAccount = BankAccount;
            }

            public String getMsg() {
                return Msg;
            }

            public void setMsg(String Msg) {
                this.Msg = Msg;
            }
        }
    }
}
