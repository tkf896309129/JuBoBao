package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：TradesBean
 * 描述：
 * 创建时间：2019-03-06  13:37
 */

public class TradesBean {


    /**
     * d : {"TotalSum":"0","TotalCount":0,"PageCount":0,"PageIndex":0,"ReList":[{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":7270,"Company":" 建筑商55"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":7255,"Company":"1"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":7258,"Company":"1"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":13453,"Company":"11"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":13452,"Company":"12"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":5962,"Company":"123"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":6119,"Company":"55847"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":1903,"Company":"company"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":3602,"Company":"Company name"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":6729,"Company":"d"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":328,"Company":"HK你"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":5891,"Company":"test"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":6118,"Company":"wwwpwpw"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":13449,"Company":"wxxcx"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":5352,"Company":"zack"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":4,"Company":"zack.LG"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":3735,"Company":"阿坝县祥云装饰有限公司"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":3238,"Company":"阿坝州缘喜建筑装饰装修工程有限公司"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":5580,"Company":"阿水玻璃店"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":7225,"Company":"安化县梅城镇玉嫂玻璃店"}],"Success":1,"ErrMsg":""}
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
         * ReList : [{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":7270,"Company":" 建筑商55"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":7255,"Company":"1"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":7258,"Company":"1"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":13453,"Company":"11"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":13452,"Company":"12"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":5962,"Company":"123"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":6119,"Company":"55847"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":1903,"Company":"company"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":3602,"Company":"Company name"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":6729,"Company":"d"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":328,"Company":"HK你"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":5891,"Company":"test"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":6118,"Company":"wwwpwpw"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":13449,"Company":"wxxcx"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":5352,"Company":"zack"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":4,"Company":"zack.LG"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":3735,"Company":"阿坝县祥云装饰有限公司"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":3238,"Company":"阿坝州缘喜建筑装饰装修工程有限公司"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":5580,"Company":"阿水玻璃店"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader","ID":7225,"Company":"安化县梅城镇玉嫂玻璃店"}]
         * Success : 1
         * ErrMsg :
         */

        private String TotalSum;
        private int TotalCount;
        private int PageCount;
        private int PageIndex;
        private int Success;
        private String ErrMsg;
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

        public List<ReListBean> getReList() {
            return ReList;
        }

        public void setReList(List<ReListBean> ReList) {
            this.ReList = ReList;
        }

        public static class ReListBean {
            /**
             * __type : AtJubo.Api.App.ReModel.ApprovalOfPayment.ParentTrader
             * ID : 7270
             * Company :  建筑商55
             */

            private String __type;
            private int ID;
            private String Company;

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
        }
    }
}
