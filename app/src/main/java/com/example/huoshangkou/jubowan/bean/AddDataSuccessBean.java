package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：AddDataSuccessBean
 * 描述：
 * 创建时间：2019-09-02  15:41
 */

public class AddDataSuccessBean {


    /**
     * d : {"ErrorCode":0,"Msg":"","Result":"sucess"}
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
         * ErrorCode : 0
         * Msg :
         * Result : sucess
         */

        private int ErrorCode;
        private String Msg;
        private String Result;

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

        public String getResult() {
            return Result;
        }

        public void setResult(String Result) {
            this.Result = Result;
        }
    }
}
