package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：KxTypeBean
 * 描述：
 * 创建时间：2019-10-31  09:55
 */

public class KxTypeBean {


    /**
     * d : {"ReObj":[{"__type":"Atjubo.DTO.Output.DealingPayment.MoneyNatureField","MoneyNatureType":0,"MoneyNature":"公账"},{"__type":"Atjubo.DTO.Output.DealingPayment.MoneyNatureField","MoneyNatureType":1,"MoneyNature":"私账"}],"Success":1,"ErrMsg":"获取成功"}
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
         * ReObj : [{"__type":"Atjubo.DTO.Output.DealingPayment.MoneyNatureField","MoneyNatureType":0,"MoneyNature":"公账"},{"__type":"Atjubo.DTO.Output.DealingPayment.MoneyNatureField","MoneyNatureType":1,"MoneyNature":"私账"}]
         * Success : 1
         * ErrMsg : 获取成功
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
             * __type : Atjubo.DTO.Output.DealingPayment.MoneyNatureField
             * MoneyNatureType : 0
             * MoneyNature : 公账
             */

            private String __type;
            private int MoneyNatureType;
            private String MoneyNature;

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public int getMoneyNatureType() {
                return MoneyNatureType;
            }

            public void setMoneyNatureType(int MoneyNatureType) {
                this.MoneyNatureType = MoneyNatureType;
            }

            public String getMoneyNature() {
                return MoneyNature;
            }

            public void setMoneyNature(String MoneyNature) {
                this.MoneyNature = MoneyNature;
            }
        }
    }
}
