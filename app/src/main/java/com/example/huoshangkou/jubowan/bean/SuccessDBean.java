package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：SuccessDBean
 * 描述：
 * 创建时间：2019-06-24  17:09
 */

public class SuccessDBean {


    /**
     * d : {"ReObj":{},"Success":1,"ErrMsg":""}
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
         * ReObj : {}
         * Success : 1
         * ErrMsg :
         */

        private ReObjBean ReObj;
        private int Success;
        private int ErrorCode;
        private int State;
        private String ErrMsg;
        private String Msg;
        private String Result;
        private String Message;

        public String getMessage() {
            return Message;
        }

        public void setMessage(String message) {
            Message = message;
        }

        public int getState() {
            return State;
        }

        public void setState(int state) {
            State = state;
        }

        public String getResult() {
            return Result;
        }

        public void setResult(String result) {
            Result = result;
        }

        public String getMsg() {
            return Msg;
        }

        public void setMsg(String msg) {
            Msg = msg;
        }

        public int getErrorCode() {
            return ErrorCode;
        }

        public void setErrorCode(int errorCode) {
            ErrorCode = errorCode;
        }

        public ReObjBean getReObj() {
            return ReObj;
        }

        public void setReObj(ReObjBean ReObj) {
            this.ReObj = ReObj;
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

        public static class ReObjBean {
        }
    }
}
