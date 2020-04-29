package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：DataBtBean
 * 描述：
 * 创建时间：2019-09-09  10:41
 */

public class DataBtBean {


    /**
     * d : {"ErrorCode":1,"Msg":"获取成功","Result":{"__type":"Model.ViewModel.Sale.IousChartAnalysisModel","AnalysisType":0,"AnalysisName":"白条已授信额度","IousTotalAmount":1500000,"ShouldBackAmount":91475.37}}
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
         * ErrorCode : 1
         * Msg : 获取成功
         * Result : {"__type":"Model.ViewModel.Sale.IousChartAnalysisModel","AnalysisType":0,"AnalysisName":"白条已授信额度","IousTotalAmount":1500000,"ShouldBackAmount":91475.37}
         */

        private int ErrorCode;
        private String Msg;
        private ResultBean Result;

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

        public ResultBean getResult() {
            return Result;
        }

        public void setResult(ResultBean Result) {
            this.Result = Result;
        }

        public static class ResultBean {
            /**
             * __type : Model.ViewModel.Sale.IousChartAnalysisModel
             * AnalysisType : 0
             * AnalysisName : 白条已授信额度
             * IousTotalAmount : 1500000.0
             * ShouldBackAmount : 91475.37
             */

            private String __type;
            private int AnalysisType;
            private String AnalysisName;
            private double IousTotalAmount;
            private double ShouldBackAmount;

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public int getAnalysisType() {
                return AnalysisType;
            }

            public void setAnalysisType(int AnalysisType) {
                this.AnalysisType = AnalysisType;
            }

            public String getAnalysisName() {
                return AnalysisName;
            }

            public void setAnalysisName(String AnalysisName) {
                this.AnalysisName = AnalysisName;
            }

            public double getIousTotalAmount() {
                return IousTotalAmount;
            }

            public void setIousTotalAmount(double IousTotalAmount) {
                this.IousTotalAmount = IousTotalAmount;
            }

            public double getShouldBackAmount() {
                return ShouldBackAmount;
            }

            public void setShouldBackAmount(double ShouldBackAmount) {
                this.ShouldBackAmount = ShouldBackAmount;
            }
        }
    }
}
