package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：PnBankBean
 * 描述：
 * 创建时间：2020-01-14  13:55
 */

public class PnBankBean {


    /**
     * d : {"Data":"https://api3.atjubo.com/pages/H5/PingAnBank.html","StatusCode":1,"Error":null}
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
         * Data : https://api3.atjubo.com/pages/H5/PingAnBank.html
         * StatusCode : 1
         * Error : null
         */

        private String Data;
        private int StatusCode;
        private Object Error;

        public String getData() {
            return Data;
        }

        public void setData(String Data) {
            this.Data = Data;
        }

        public int getStatusCode() {
            return StatusCode;
        }

        public void setStatusCode(int StatusCode) {
            this.StatusCode = StatusCode;
        }

        public Object getError() {
            return Error;
        }

        public void setError(Object Error) {
            this.Error = Error;
        }
    }
}
