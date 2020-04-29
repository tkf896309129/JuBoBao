package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：JylBuyResultBean
 * 描述：
 * 创建时间：2019-07-04  09:58
 */

public class JylBuyResultBean {


    /**
     * d : {"ErrorCode":0,"Msg":"","Result":{"__type":"AtJubo.Api.App.ReModel.AtjuboWeb.ReAddShoppingCarModel","satate":"error","LoginUrl":null,"returnMsg":"用户名或密码错误"}}
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
         * Result : {"__type":"AtJubo.Api.App.ReModel.AtjuboWeb.ReAddShoppingCarModel","satate":"error","LoginUrl":null,"returnMsg":"用户名或密码错误"}
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
             * __type : AtJubo.Api.App.ReModel.AtjuboWeb.ReAddShoppingCarModel
             * satate : error
             * LoginUrl : null
             * returnMsg : 用户名或密码错误
             */

            private String __type;
            private String satate;
            private Object LoginUrl;
            private String returnMsg;

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public String getSatate() {
                return satate;
            }

            public void setSatate(String satate) {
                this.satate = satate;
            }

            public Object getLoginUrl() {
                return LoginUrl;
            }

            public void setLoginUrl(Object LoginUrl) {
                this.LoginUrl = LoginUrl;
            }

            public String getReturnMsg() {
                return returnMsg;
            }

            public void setReturnMsg(String returnMsg) {
                this.returnMsg = returnMsg;
            }
        }
    }
}
