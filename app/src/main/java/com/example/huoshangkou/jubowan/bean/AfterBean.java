package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：AfterBean
 * 描述：
 * 创建时间：2017-05-16  16:07
 */

public class AfterBean {

    /**
     * ErrMsg :
     * ReObj : {"CreateTime":"2020-03-11 11:13:04 ","OrderId":"10202015838963843466","PcPayUrl":"http://192.168.1.120:88/order/OrderToPayPage.aspx?orderid=10202015838963843466","Type":1}
     * Success : 1
     */

    private String ErrMsg;
    private ReObjBean ReObj;
    private int Success;

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String ErrMsg) {
        this.ErrMsg = ErrMsg;
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

    public static class ReObjBean {
        /**
         * CreateTime : 2020-03-11 11:13:04
         * OrderId : 10202015838963843466
         * PcPayUrl : http://192.168.1.120:88/order/OrderToPayPage.aspx?orderid=10202015838963843466
         * Type : 1
         */

        private String CreateTime;
        private String OrderId;
        private String PcPayUrl;
        private int Type;

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getOrderId() {
            return OrderId;
        }

        public void setOrderId(String OrderId) {
            this.OrderId = OrderId;
        }

        public String getPcPayUrl() {
            return PcPayUrl;
        }

        public void setPcPayUrl(String PcPayUrl) {
            this.PcPayUrl = PcPayUrl;
        }

        public int getType() {
            return Type;
        }

        public void setType(int Type) {
            this.Type = Type;
        }
    }
}
