package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：YwSuccessBean
 * 描述：
 * 创建时间：2019-03-08  16:38
 */

public class YwSuccessBean {


    /**
     * d : {"ReObj":{},"Success":1,"ErrMsg":"业务用款审批已提交!"}
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
         * ErrMsg : 业务用款审批已提交!
         */

        private ReObjBean ReObj;
        private int Success;
        private String ErrMsg;

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
