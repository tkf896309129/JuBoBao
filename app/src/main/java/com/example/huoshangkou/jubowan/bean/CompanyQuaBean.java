package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：CompanyQuaBean
 * 描述：
 * 创建时间：2019-10-23  15:01
 */

public class CompanyQuaBean {


    /**
     * d : {"Data":{"CompanyNature":-1,"UserJurisdiction":0},"StatusCode":1,"Error":null}
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
         * Data : {"CompanyNature":-1,"UserJurisdiction":0}
         * StatusCode : 1
         * Error : null
         */

        private DataBean Data;
        private int StatusCode;
        private Object Error;

        public DataBean getData() {
            return Data;
        }

        public void setData(DataBean Data) {
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

        public static class DataBean {
            /**
             * CompanyNature : -1
             * UserJurisdiction : 0
             */

            private int CompanyNature;
            private int UserJurisdiction;

            public int getCompanyNature() {
                return CompanyNature;
            }

            public void setCompanyNature(int CompanyNature) {
                this.CompanyNature = CompanyNature;
            }

            public int getUserJurisdiction() {
                return UserJurisdiction;
            }

            public void setUserJurisdiction(int UserJurisdiction) {
                this.UserJurisdiction = UserJurisdiction;
            }
        }
    }
}
