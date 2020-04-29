package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：DkCustomerBean
 * 描述：
 * 创建时间：2019-03-05  09:18
 */

public class DkCustomerBean {


    private DBean d;

    public DBean getD() {
        return d;
    }

    public void setD(DBean d) {
        this.d = d;
    }

    public static class DBean {

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
             * __type : AtJubo.Api.App.ReModel.ApprovalOfPayment.CustomerUserInfo
             * ID : 19464
             * Company : 浙江火山口网络科技有限公司
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
