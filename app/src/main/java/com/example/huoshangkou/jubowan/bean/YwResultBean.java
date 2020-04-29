package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：YwResultBean
 * 描述：
 * 创建时间：2019-03-07  08:35
 */

public class YwResultBean {


    /**
     * d : {"ReObj":null,"Success":-1,"ErrMsg":"当前审批人或申请人信息不存在或已注销！"}
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
         * ReObj : null
         * Success : -1
         * ErrMsg : 当前审批人或申请人信息不存在或已注销！
         */

        private Object ReObj;
        private int Success;
        private String ErrMsg;

        public Object getReObj() {
            return ReObj;
        }

        public void setReObj(Object ReObj) {
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
