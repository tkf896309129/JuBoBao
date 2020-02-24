package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：BaseCheckResultBean
 * 描述：
 * 创建时间：2019-12-16  15:54
 */

public class BaseCheckResultBean {


    /**
     * d : {"ReObj":"申请成功","Success":1,"ErrMsg":""}
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
         * ReObj : 申请成功
         * Success : 1
         * ErrMsg :
         */

        private String ReObj;
        private int Success;
        private String ErrMsg;

        public String getReObj() {
            return ReObj;
        }

        public void setReObj(String ReObj) {
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
    }
}
