package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：CustomerProportionBean
 * 描述：
 * 创建时间：2019-09-09  08:27
 */

public class CustomerProportionBean {

    /**
     * d : {"Total":0,"PageIndex":1,"ErrorCode":1,"Msg":"获取成功","Result":[{"__type":"Model.ViewModel.Sale.CustomerTypeRatioModel","CustomerType":0,"CustomerTypeName":"无意向客户","Ratio":"80.00%","Count":4},{"__type":"Model.ViewModel.Sale.CustomerTypeRatioModel","CustomerType":4,"CustomerTypeName":"重要客户","Ratio":"20.00%","Count":1},{"__type":"Model.ViewModel.Sale.CustomerTypeRatioModel","CustomerType":1,"CustomerTypeName":"风险客户","Ratio":"0.00%","Count":0},{"__type":"Model.ViewModel.Sale.CustomerTypeRatioModel","CustomerType":2,"CustomerTypeName":"一般客户","Ratio":"0.00%","Count":0},{"__type":"Model.ViewModel.Sale.CustomerTypeRatioModel","CustomerType":3,"CustomerTypeName":"主要客户","Ratio":"0.00%","Count":0},{"__type":"Model.ViewModel.Sale.CustomerTypeRatioModel","CustomerType":-1,"CustomerTypeName":"其它","Ratio":"0.00%","Count":0}]}
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
         * Total : 0
         * PageIndex : 1
         * ErrorCode : 1
         * Msg : 获取成功
         * Result : [{"__type":"Model.ViewModel.Sale.CustomerTypeRatioModel","CustomerType":0,"CustomerTypeName":"无意向客户","Ratio":"80.00%","Count":4},{"__type":"Model.ViewModel.Sale.CustomerTypeRatioModel","CustomerType":4,"CustomerTypeName":"重要客户","Ratio":"20.00%","Count":1},{"__type":"Model.ViewModel.Sale.CustomerTypeRatioModel","CustomerType":1,"CustomerTypeName":"风险客户","Ratio":"0.00%","Count":0},{"__type":"Model.ViewModel.Sale.CustomerTypeRatioModel","CustomerType":2,"CustomerTypeName":"一般客户","Ratio":"0.00%","Count":0},{"__type":"Model.ViewModel.Sale.CustomerTypeRatioModel","CustomerType":3,"CustomerTypeName":"主要客户","Ratio":"0.00%","Count":0},{"__type":"Model.ViewModel.Sale.CustomerTypeRatioModel","CustomerType":-1,"CustomerTypeName":"其它","Ratio":"0.00%","Count":0}]
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
             * __type : Model.ViewModel.Sale.CustomerTypeRatioModel
             * CustomerType : 0
             * CustomerTypeName : 无意向客户
             * Ratio : 80.00%
             * Count : 4
             */

            private String __type;
            private int CustomerType;
            private String CustomerTypeName;
            private String Ratio;
            private int Count;

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public int getCustomerType() {
                return CustomerType;
            }

            public void setCustomerType(int CustomerType) {
                this.CustomerType = CustomerType;
            }

            public String getCustomerTypeName() {
                return CustomerTypeName;
            }

            public void setCustomerTypeName(String CustomerTypeName) {
                this.CustomerTypeName = CustomerTypeName;
            }

            public String getRatio() {
                return Ratio;
            }

            public void setRatio(String Ratio) {
                this.Ratio = Ratio;
            }

            public int getCount() {
                return Count;
            }

            public void setCount(int Count) {
                this.Count = Count;
            }
        }
    }
}
