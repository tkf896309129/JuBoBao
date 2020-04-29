package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：MetSettingBean
 * 描述：
 * 创建时间：2019-07-03  13:36
 */

public class MetSettingBean {


    /**
     * d : {"__type":"AtJubo.Api.App.ReModel.OutPut","State":1,"Message":"成功","ReturnData":null}
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
         * __type : AtJubo.Api.App.ReModel.OutPut
         * State : 1
         * Message : 成功
         * ReturnData : null
         */

        private String __type;
        private int State;
        private String Message;
        private Object ReturnData;

        public String get__type() {
            return __type;
        }

        public void set__type(String __type) {
            this.__type = __type;
        }

        public int getState() {
            return State;
        }

        public void setState(int State) {
            this.State = State;
        }

        public String getMessage() {
            return Message;
        }

        public void setMessage(String Message) {
            this.Message = Message;
        }

        public Object getReturnData() {
            return ReturnData;
        }

        public void setReturnData(Object ReturnData) {
            this.ReturnData = ReturnData;
        }
    }
}
