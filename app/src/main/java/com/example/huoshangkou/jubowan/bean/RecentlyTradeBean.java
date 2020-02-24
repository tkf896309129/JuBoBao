package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：RecentlyTradeBean
 * 描述：
 * 创建时间：2019-09-16  08:34
 */

public class RecentlyTradeBean {


    /**
     * d : {"Total":0,"PageIndex":1,"ErrorCode":1,"Msg":"获取成功","Result":[{"__type":"Model.ViewModel.Sale.JuBoApp.AppSixMonethStatisics","AnalyticalValue":1,"AmountOfTransacte":0,"CurrentYear":2019,"CurrentMonth":4},{"__type":"Model.ViewModel.Sale.JuBoApp.AppSixMonethStatisics","AnalyticalValue":1,"AmountOfTransacte":0,"CurrentYear":2019,"CurrentMonth":5},{"__type":"Model.ViewModel.Sale.JuBoApp.AppSixMonethStatisics","AnalyticalValue":0,"AmountOfTransacte":0,"CurrentYear":2019,"CurrentMonth":6},{"__type":"Model.ViewModel.Sale.JuBoApp.AppSixMonethStatisics","AnalyticalValue":0,"AmountOfTransacte":0,"CurrentYear":2019,"CurrentMonth":7},{"__type":"Model.ViewModel.Sale.JuBoApp.AppSixMonethStatisics","AnalyticalValue":1,"AmountOfTransacte":0,"CurrentYear":2019,"CurrentMonth":8},{"__type":"Model.ViewModel.Sale.JuBoApp.AppSixMonethStatisics","AnalyticalValue":1,"AmountOfTransacte":0,"CurrentYear":2019,"CurrentMonth":9}]}
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
         * Result : [{"__type":"Model.ViewModel.Sale.JuBoApp.AppSixMonethStatisics","AnalyticalValue":1,"AmountOfTransacte":0,"CurrentYear":2019,"CurrentMonth":4},{"__type":"Model.ViewModel.Sale.JuBoApp.AppSixMonethStatisics","AnalyticalValue":1,"AmountOfTransacte":0,"CurrentYear":2019,"CurrentMonth":5},{"__type":"Model.ViewModel.Sale.JuBoApp.AppSixMonethStatisics","AnalyticalValue":0,"AmountOfTransacte":0,"CurrentYear":2019,"CurrentMonth":6},{"__type":"Model.ViewModel.Sale.JuBoApp.AppSixMonethStatisics","AnalyticalValue":0,"AmountOfTransacte":0,"CurrentYear":2019,"CurrentMonth":7},{"__type":"Model.ViewModel.Sale.JuBoApp.AppSixMonethStatisics","AnalyticalValue":1,"AmountOfTransacte":0,"CurrentYear":2019,"CurrentMonth":8},{"__type":"Model.ViewModel.Sale.JuBoApp.AppSixMonethStatisics","AnalyticalValue":1,"AmountOfTransacte":0,"CurrentYear":2019,"CurrentMonth":9}]
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
             * __type : Model.ViewModel.Sale.JuBoApp.AppSixMonethStatisics
             * AnalyticalValue : 1
             * AmountOfTransacte : 0
             * CurrentYear : 2019
             * CurrentMonth : 4
             */

            private String __type;
            private int AnalyticalValue;
            private double AmountOfTransacte;
            private int CurrentYear;
            private int CurrentMonth;

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public int getAnalyticalValue() {
                return AnalyticalValue;
            }

            public void setAnalyticalValue(int AnalyticalValue) {
                this.AnalyticalValue = AnalyticalValue;
            }

            public double getAmountOfTransacte() {
                return AmountOfTransacte;
            }

            public void setAmountOfTransacte(double AmountOfTransacte) {
                this.AmountOfTransacte = AmountOfTransacte;
            }

            public int getCurrentYear() {
                return CurrentYear;
            }

            public void setCurrentYear(int CurrentYear) {
                this.CurrentYear = CurrentYear;
            }

            public int getCurrentMonth() {
                return CurrentMonth;
            }

            public void setCurrentMonth(int CurrentMonth) {
                this.CurrentMonth = CurrentMonth;
            }
        }
    }
}
