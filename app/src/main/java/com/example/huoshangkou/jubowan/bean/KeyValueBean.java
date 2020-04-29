package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：KeyValueBean
 * 描述：
 * 创建时间：2019-03-06  14:09
 */

public class KeyValueBean {


    /**
     * d : {"ReObj":[{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.FieldModel","Name":"电汇","Value":"电汇"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.FieldModel","Name":"纸质承兑","Value":"纸质承兑"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.FieldModel","Name":"电子承兑","Value":"电子承兑"}],"Success":1,"ErrMsg":"成功获取子节点字典数据！"}
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
         * ReObj : [{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.FieldModel","Name":"电汇","Value":"电汇"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.FieldModel","Name":"纸质承兑","Value":"纸质承兑"},{"__type":"AtJubo.Api.App.ReModel.ApprovalOfPayment.FieldModel","Name":"电子承兑","Value":"电子承兑"}]
         * Success : 1
         * ErrMsg : 成功获取子节点字典数据！
         */

        private int Success;
        private String ErrMsg;
        private List<ReObjBean> ReObj;

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

        public List<ReObjBean> getReObj() {
            return ReObj;
        }

        public void setReObj(List<ReObjBean> ReObj) {
            this.ReObj = ReObj;
        }

        public static class ReObjBean {
            /**
             * __type : AtJubo.Api.App.ReModel.ApprovalOfPayment.FieldModel
             * Name : 电汇
             * Value : 电汇
             */

            private String __type;
            private String Name;
            private String Value;

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getValue() {
                return Value;
            }

            public void setValue(String Value) {
                this.Value = Value;
            }
        }
    }
}
