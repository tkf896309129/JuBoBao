package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：CustomerMessageBean
 * 描述：
 * 创建时间：2019-09-05  13:36
 */

public class CustomerMessageBean {


    /**
     * d : {"ErrorCode":0,"Msg":"","Result":[{"__type":"AtJubo.Api.App.ReModel.JuboBao.CustomerModel","Company":"南晶玻璃科技有限公司","Profession":"经理","CustomerType":"无意向客户","Name":"肖玥","State":null},{"__type":"AtJubo.Api.App.ReModel.JuboBao.CustomerModel","Company":"南晶玻璃科技有限公司","Profession":"经理","CustomerType":"无意向客户","Name":"钱五","State":null}]}
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
         * Result : [{"__type":"AtJubo.Api.App.ReModel.JuboBao.CustomerModel","Company":"南晶玻璃科技有限公司","Profession":"经理","CustomerType":"无意向客户","Name":"肖玥","State":null},{"__type":"AtJubo.Api.App.ReModel.JuboBao.CustomerModel","Company":"南晶玻璃科技有限公司","Profession":"经理","CustomerType":"无意向客户","Name":"钱五","State":null}]
         */

        private int ErrorCode;
        private String Msg;
        private List<ResultBean> Result;

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

        public List<ResultBean> getResult() {
            return Result;
        }

        public void setResult(List<ResultBean> Result) {
            this.Result = Result;
        }

        public static class ResultBean {
            /**
             * __type : AtJubo.Api.App.ReModel.JuboBao.CustomerModel
             * Company : 南晶玻璃科技有限公司
             * Profession : 经理
             * CustomerType : 无意向客户
             * Name : 肖玥
             * State : null
             */

            private String __type;
            private String Company;
            private String Profession;
            private String CustomerType;
            private String Name;
            private int Id;
            private Object State;

            public int getId() {
                return Id;
            }

            public void setId(int id) {
                Id = id;
            }

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public String getCompany() {
                return Company;
            }

            public void setCompany(String Company) {
                this.Company = Company;
            }

            public String getProfession() {
                return Profession;
            }

            public void setProfession(String Profession) {
                this.Profession = Profession;
            }

            public String getCustomerType() {
                return CustomerType;
            }

            public void setCustomerType(String CustomerType) {
                this.CustomerType = CustomerType;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public Object getState() {
                return State;
            }

            public void setState(Object State) {
                this.State = State;
            }
        }
    }
}
