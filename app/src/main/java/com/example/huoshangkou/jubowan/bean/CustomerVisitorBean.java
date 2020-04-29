package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：CustomerVisitorBean
 * 描述：
 * 创建时间：2019-09-03  14:02
 */

public class CustomerVisitorBean {


    /**
     * d : {"TotalSum":"0","TotalCount":5,"PageCount":0,"PageIndex":0,"ReList":[{"__type":"Atjubo.DAL.DataEntity.Sale.Sale_VisitRecord","Id":1014,"CustomerId":3023,"CreateTime":"/Date(1567056160000)/","VisitDate":"/Date(1567008000000)/","VisitContent":"发财否","Evaluate":"还未"},{"__type":"Atjubo.DAL.DataEntity.Sale.Sale_VisitRecord","Id":1015,"CustomerId":3023,"CreateTime":"/Date(1567056190157)/","VisitDate":"/Date(1567094400000)/","VisitContent":"发财否？","Evaluate":"还未!"},{"__type":"Atjubo.DAL.DataEntity.Sale.Sale_VisitRecord","Id":1016,"CustomerId":3023,"CreateTime":"/Date(1567056222073)/","VisitDate":"/Date(1567180800000)/","VisitContent":"发财了否？？","Evaluate":"还未发！！！"},{"__type":"Atjubo.DAL.DataEntity.Sale.Sale_VisitRecord","Id":1017,"CustomerId":3023,"CreateTime":"/Date(1567056252430)/","VisitDate":"/Date(1567267200000)/","VisitContent":"买彩票了吗¿","Evaluate":"还没买。。。"},{"__type":"Atjubo.DAL.DataEntity.Sale.Sale_VisitRecord","Id":1018,"CustomerId":3023,"CreateTime":"/Date(1567056295237)/","VisitDate":"/Date(1567353600000)/","VisitContent":"不买彩票发什么财¿？","Evaluate":"。。。。。。"}],"Success":1,"ErrMsg":""}
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
         * TotalCount : 5
         * PageCount : 0
         * PageIndex : 0
         * ReList : [{"__type":"Atjubo.DAL.DataEntity.Sale.Sale_VisitRecord","Id":1014,"CustomerId":3023,"CreateTime":"/Date(1567056160000)/","VisitDate":"/Date(1567008000000)/","VisitContent":"发财否","Evaluate":"还未"},{"__type":"Atjubo.DAL.DataEntity.Sale.Sale_VisitRecord","Id":1015,"CustomerId":3023,"CreateTime":"/Date(1567056190157)/","VisitDate":"/Date(1567094400000)/","VisitContent":"发财否？","Evaluate":"还未!"},{"__type":"Atjubo.DAL.DataEntity.Sale.Sale_VisitRecord","Id":1016,"CustomerId":3023,"CreateTime":"/Date(1567056222073)/","VisitDate":"/Date(1567180800000)/","VisitContent":"发财了否？？","Evaluate":"还未发！！！"},{"__type":"Atjubo.DAL.DataEntity.Sale.Sale_VisitRecord","Id":1017,"CustomerId":3023,"CreateTime":"/Date(1567056252430)/","VisitDate":"/Date(1567267200000)/","VisitContent":"买彩票了吗¿","Evaluate":"还没买。。。"},{"__type":"Atjubo.DAL.DataEntity.Sale.Sale_VisitRecord","Id":1018,"CustomerId":3023,"CreateTime":"/Date(1567056295237)/","VisitDate":"/Date(1567353600000)/","VisitContent":"不买彩票发什么财¿？","Evaluate":"。。。。。。"}]
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
             * __type : Atjubo.DAL.DataEntity.Sale.Sale_VisitRecord
             * Id : 1014
             * CustomerId : 3023
             * CreateTime : /Date(1567056160000)/
             * VisitDate : /Date(1567008000000)/
             * VisitContent : 发财否
             * Evaluate : 还未
             */

            private String __type;
            private int Id;
            private int CustomerId;
            private String CreateTime;
            private String RemittedTime;
            private double AmountSum;
            private String VisitDate;
            private String VisitContent;
            private String Evaluate;

            public String getRemittedTime() {
                return RemittedTime;
            }

            public void setRemittedTime(String remittedTime) {
                RemittedTime = remittedTime;
            }

            public double getAmountSum() {
                return AmountSum;
            }

            public void setAmountSum(double amountSum) {
                AmountSum = amountSum;
            }

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public int getCustomerId() {
                return CustomerId;
            }

            public void setCustomerId(int CustomerId) {
                this.CustomerId = CustomerId;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public String getVisitDate() {
                return VisitDate;
            }

            public void setVisitDate(String VisitDate) {
                this.VisitDate = VisitDate;
            }

            public String getVisitContent() {
                return VisitContent;
            }

            public void setVisitContent(String VisitContent) {
                this.VisitContent = VisitContent;
            }

            public String getEvaluate() {
                return Evaluate;
            }

            public void setEvaluate(String Evaluate) {
                this.Evaluate = Evaluate;
            }
        }
    }
}
