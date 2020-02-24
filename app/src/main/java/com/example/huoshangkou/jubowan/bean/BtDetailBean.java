package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：BtDetailBean
 * 描述：
 * 创建时间：2019-09-29  13:30
 */

public class BtDetailBean {


    /**
     * d : {"Total":3,"PageIndex":1,"ErrorCode":1,"Msg":"","Result":[{"__type":"Model.ViewModel.Sale.SalaStatistics.IousCreditedModel","UserID":10445,"LinkMan":"吴济时","ApplyUnit":"浙江火山口网络科技有限公司","IousTotalAmount":500000,"IousRemainingAmount":465000,"UsedAmount":35000,"IousStartTime":"/Date(1548916549407)/","IousStartDate":"2019-01-31","IousEndTime":"/Date(1580452549407)/","IousEndDate":"2020-01-31"},{"__type":"Model.ViewModel.Sale.SalaStatistics.IousCreditedModel","UserID":20469,"LinkMan":"yf","ApplyUnit":"浙江火山口网络科技有限公司","IousTotalAmount":500000,"IousRemainingAmount":459421.13,"UsedAmount":40578.87,"IousStartTime":"/Date(1548916549407)/","IousStartDate":"2019-01-31","IousEndTime":"/Date(1580452549407)/","IousEndDate":"2020-01-31"},{"__type":"Model.ViewModel.Sale.SalaStatistics.IousCreditedModel","UserID":29482,"LinkMan":"任鹤","ApplyUnit":"浙江火山口网络科技有限公司","IousTotalAmount":500000,"IousRemainingAmount":484103.5,"UsedAmount":15896.5,"IousStartTime":"/Date(1548916549407)/","IousStartDate":"2019-01-31","IousEndTime":"/Date(1580452549407)/","IousEndDate":"2020-01-31"}]}
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
         * Total : 3
         * PageIndex : 1
         * ErrorCode : 1
         * Msg :
         * Result : [{"__type":"Model.ViewModel.Sale.SalaStatistics.IousCreditedModel","UserID":10445,"LinkMan":"吴济时","ApplyUnit":"浙江火山口网络科技有限公司","IousTotalAmount":500000,"IousRemainingAmount":465000,"UsedAmount":35000,"IousStartTime":"/Date(1548916549407)/","IousStartDate":"2019-01-31","IousEndTime":"/Date(1580452549407)/","IousEndDate":"2020-01-31"},{"__type":"Model.ViewModel.Sale.SalaStatistics.IousCreditedModel","UserID":20469,"LinkMan":"yf","ApplyUnit":"浙江火山口网络科技有限公司","IousTotalAmount":500000,"IousRemainingAmount":459421.13,"UsedAmount":40578.87,"IousStartTime":"/Date(1548916549407)/","IousStartDate":"2019-01-31","IousEndTime":"/Date(1580452549407)/","IousEndDate":"2020-01-31"},{"__type":"Model.ViewModel.Sale.SalaStatistics.IousCreditedModel","UserID":29482,"LinkMan":"任鹤","ApplyUnit":"浙江火山口网络科技有限公司","IousTotalAmount":500000,"IousRemainingAmount":484103.5,"UsedAmount":15896.5,"IousStartTime":"/Date(1548916549407)/","IousStartDate":"2019-01-31","IousEndTime":"/Date(1580452549407)/","IousEndDate":"2020-01-31"}]
         */

        private int Total;
        private int PageIndex;
        private int ErrorCode;
        private String Msg;
        private List<ResultBean> Result;

        public int getTotal() {
            return Total;
        }

        public void setTotal(int Total) {
            this.Total = Total;
        }

        public int getPageIndex() {
            return PageIndex;
        }

        public void setPageIndex(int PageIndex) {
            this.PageIndex = PageIndex;
        }

        public int getErrorCode() {
            return ErrorCode;
        }

        public void setErrorCode(int ErrorCode) {
            this.ErrorCode = ErrorCode;
        }

        public String getMsg() {
            return Msg;
        }

        public void setMsg(String Msg) {
            this.Msg = Msg;
        }

        public List<ResultBean> getResult() {
            return Result;
        }

        public void setResult(List<ResultBean> Result) {
            this.Result = Result;
        }

        public static class ResultBean {
            /**
             * __type : Model.ViewModel.Sale.SalaStatistics.IousCreditedModel
             * UserID : 10445
             * LinkMan : 吴济时
             * ApplyUnit : 浙江火山口网络科技有限公司
             * IousTotalAmount : 500000.0
             * IousRemainingAmount : 465000.0
             * UsedAmount : 35000.0
             * IousStartTime : /Date(1548916549407)/
             * IousStartDate : 2019-01-31
             * IousEndTime : /Date(1580452549407)/
             * IousEndDate : 2020-01-31
             */

            private String __type;
            private int UserID;
            private String LinkMan;
            private String ApplyUnit;
            private double IousTotalAmount;
            private double IousRemainingAmount;
            private double UsedAmount;
            private String IousStartTime;
            private String IousStartDate;
            private String IousEndTime;
            private String IousEndDate;

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public int getUserID() {
                return UserID;
            }

            public void setUserID(int UserID) {
                this.UserID = UserID;
            }

            public String getLinkMan() {
                return LinkMan;
            }

            public void setLinkMan(String LinkMan) {
                this.LinkMan = LinkMan;
            }

            public String getApplyUnit() {
                return ApplyUnit;
            }

            public void setApplyUnit(String ApplyUnit) {
                this.ApplyUnit = ApplyUnit;
            }

            public double getIousTotalAmount() {
                return IousTotalAmount;
            }

            public void setIousTotalAmount(double IousTotalAmount) {
                this.IousTotalAmount = IousTotalAmount;
            }

            public double getIousRemainingAmount() {
                return IousRemainingAmount;
            }

            public void setIousRemainingAmount(double IousRemainingAmount) {
                this.IousRemainingAmount = IousRemainingAmount;
            }

            public double getUsedAmount() {
                return UsedAmount;
            }

            public void setUsedAmount(double UsedAmount) {
                this.UsedAmount = UsedAmount;
            }

            public String getIousStartTime() {
                return IousStartTime;
            }

            public void setIousStartTime(String IousStartTime) {
                this.IousStartTime = IousStartTime;
            }

            public String getIousStartDate() {
                return IousStartDate;
            }

            public void setIousStartDate(String IousStartDate) {
                this.IousStartDate = IousStartDate;
            }

            public String getIousEndTime() {
                return IousEndTime;
            }

            public void setIousEndTime(String IousEndTime) {
                this.IousEndTime = IousEndTime;
            }

            public String getIousEndDate() {
                return IousEndDate;
            }

            public void setIousEndDate(String IousEndDate) {
                this.IousEndDate = IousEndDate;
            }
        }
    }
}
