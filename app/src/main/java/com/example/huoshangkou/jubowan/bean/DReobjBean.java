package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：DReobjBean
 * 描述：
 * 创建时间：2019-09-19  10:51
 */

public class DReobjBean {


    /**
     * d : {"ReObj":{"__type":"AtJubo.Api.App.ReModel.JudgeRole","Type":3,"RoleId":3},"Success":1,"ErrMsg":""}
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
         * ReObj : {"__type":"AtJubo.Api.App.ReModel.JudgeRole","Type":3,"RoleId":3}
         * Success : 1
         * ErrMsg :
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
            /**
             * __type : AtJubo.Api.App.ReModel.JudgeRole
             * Type : 3
             * RoleId : 3
             */

            private String __type;
            private int Type;
            private int RoleId;

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public int getType() {
                return Type;
            }

            public void setType(int Type) {
                this.Type = Type;
            }

            public int getRoleId() {
                return RoleId;
            }

            public void setRoleId(int RoleId) {
                this.RoleId = RoleId;
            }
        }
    }
}
