package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：MonthSaleDetailBean
 * 描述：
 * 创建时间：2019-09-26  11:34
 */

public class MonthSaleDetailBean {


    /**
     * d : {"Total":12,"PageIndex":1,"ErrorCode":1,"Msg":"","Result":[{"__type":"Model.ViewModel.Sale.DayOfSalesAmountAnalysis","SalesMan":"陈芳","CustomerName":"桐乡市安桐玻璃制品有限公司","RemittedTime":"0001-01-01","SumTotalPrice":109701.57},{"__type":"Model.ViewModel.Sale.DayOfSalesAmountAnalysis","SalesMan":"马莎","CustomerName":"武汉天华元玻璃有限公司","RemittedTime":"0001-01-01","SumTotalPrice":50050},{"__type":"Model.ViewModel.Sale.DayOfSalesAmountAnalysis","SalesMan":"马莎","CustomerName":"武汉天亚玻璃有限公司","RemittedTime":"0001-01-01","SumTotalPrice":95995.9},{"__type":"Model.ViewModel.Sale.DayOfSalesAmountAnalysis","SalesMan":"马岩文","CustomerName":"宁夏瑞鸿鑫玻璃有限公司","RemittedTime":"0001-01-01","SumTotalPrice":44071.17},{"__type":"Model.ViewModel.Sale.DayOfSalesAmountAnalysis","SalesMan":"马岩文","CustomerName":"宁夏鑫翔辉玻璃有限公司","RemittedTime":"0001-01-01","SumTotalPrice":94500},{"__type":"Model.ViewModel.Sale.DayOfSalesAmountAnalysis","SalesMan":"沈永嘉","CustomerName":"巢湖市程氏玻璃有限公司","RemittedTime":"0001-01-01","SumTotalPrice":52604.64},{"__type":"Model.ViewModel.Sale.DayOfSalesAmountAnalysis","SalesMan":"沈永嘉","CustomerName":"杭州雨宝实业有限公司","RemittedTime":"0001-01-01","SumTotalPrice":156142.88},{"__type":"Model.ViewModel.Sale.DayOfSalesAmountAnalysis","SalesMan":"汤玖海","CustomerName":"浙江中力节能玻璃制造有限公司","RemittedTime":"0001-01-01","SumTotalPrice":480147.56},{"__type":"Model.ViewModel.Sale.DayOfSalesAmountAnalysis","SalesMan":"汪琼","CustomerName":"浙江众康玻璃科技有限公司","RemittedTime":"0001-01-01","SumTotalPrice":36837.9},{"__type":"Model.ViewModel.Sale.DayOfSalesAmountAnalysis","SalesMan":"谢东霞","CustomerName":"徐州旭航玻璃科技有限公司","RemittedTime":"0001-01-01","SumTotalPrice":74671},{"__type":"Model.ViewModel.Sale.DayOfSalesAmountAnalysis","SalesMan":"臧焕春","CustomerName":"陕西金鹰玻璃有限公司","RemittedTime":"0001-01-01","SumTotalPrice":70237.7},{"__type":"Model.ViewModel.Sale.DayOfSalesAmountAnalysis","SalesMan":"臧焕春","CustomerName":"陕西新世纪钢化玻璃有限公司","RemittedTime":"0001-01-01","SumTotalPrice":71299.84}]}
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
         * Total : 12
         * PageIndex : 1
         * ErrorCode : 1
         * Msg :
         * Result : [{"__type":"Model.ViewModel.Sale.DayOfSalesAmountAnalysis","SalesMan":"陈芳","CustomerName":"桐乡市安桐玻璃制品有限公司","RemittedTime":"0001-01-01","SumTotalPrice":109701.57},{"__type":"Model.ViewModel.Sale.DayOfSalesAmountAnalysis","SalesMan":"马莎","CustomerName":"武汉天华元玻璃有限公司","RemittedTime":"0001-01-01","SumTotalPrice":50050},{"__type":"Model.ViewModel.Sale.DayOfSalesAmountAnalysis","SalesMan":"马莎","CustomerName":"武汉天亚玻璃有限公司","RemittedTime":"0001-01-01","SumTotalPrice":95995.9},{"__type":"Model.ViewModel.Sale.DayOfSalesAmountAnalysis","SalesMan":"马岩文","CustomerName":"宁夏瑞鸿鑫玻璃有限公司","RemittedTime":"0001-01-01","SumTotalPrice":44071.17},{"__type":"Model.ViewModel.Sale.DayOfSalesAmountAnalysis","SalesMan":"马岩文","CustomerName":"宁夏鑫翔辉玻璃有限公司","RemittedTime":"0001-01-01","SumTotalPrice":94500},{"__type":"Model.ViewModel.Sale.DayOfSalesAmountAnalysis","SalesMan":"沈永嘉","CustomerName":"巢湖市程氏玻璃有限公司","RemittedTime":"0001-01-01","SumTotalPrice":52604.64},{"__type":"Model.ViewModel.Sale.DayOfSalesAmountAnalysis","SalesMan":"沈永嘉","CustomerName":"杭州雨宝实业有限公司","RemittedTime":"0001-01-01","SumTotalPrice":156142.88},{"__type":"Model.ViewModel.Sale.DayOfSalesAmountAnalysis","SalesMan":"汤玖海","CustomerName":"浙江中力节能玻璃制造有限公司","RemittedTime":"0001-01-01","SumTotalPrice":480147.56},{"__type":"Model.ViewModel.Sale.DayOfSalesAmountAnalysis","SalesMan":"汪琼","CustomerName":"浙江众康玻璃科技有限公司","RemittedTime":"0001-01-01","SumTotalPrice":36837.9},{"__type":"Model.ViewModel.Sale.DayOfSalesAmountAnalysis","SalesMan":"谢东霞","CustomerName":"徐州旭航玻璃科技有限公司","RemittedTime":"0001-01-01","SumTotalPrice":74671},{"__type":"Model.ViewModel.Sale.DayOfSalesAmountAnalysis","SalesMan":"臧焕春","CustomerName":"陕西金鹰玻璃有限公司","RemittedTime":"0001-01-01","SumTotalPrice":70237.7},{"__type":"Model.ViewModel.Sale.DayOfSalesAmountAnalysis","SalesMan":"臧焕春","CustomerName":"陕西新世纪钢化玻璃有限公司","RemittedTime":"0001-01-01","SumTotalPrice":71299.84}]
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
             * __type : Model.ViewModel.Sale.DayOfSalesAmountAnalysis
             * SalesMan : 陈芳
             * CustomerName : 桐乡市安桐玻璃制品有限公司
             * RemittedTime : 0001-01-01
             * SumTotalPrice : 109701.57
             */

            private String __type;
            private String SalesMan;
            private String CustomerName;
            private String RemittedTime;
            private double SumTotalPrice;

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public String getSalesMan() {
                return SalesMan;
            }

            public void setSalesMan(String SalesMan) {
                this.SalesMan = SalesMan;
            }

            public String getCustomerName() {
                return CustomerName;
            }

            public void setCustomerName(String CustomerName) {
                this.CustomerName = CustomerName;
            }

            public String getRemittedTime() {
                return RemittedTime;
            }

            public void setRemittedTime(String RemittedTime) {
                this.RemittedTime = RemittedTime;
            }

            public double getSumTotalPrice() {
                return SumTotalPrice;
            }

            public void setSumTotalPrice(double SumTotalPrice) {
                this.SumTotalPrice = SumTotalPrice;
            }
        }
    }
}
