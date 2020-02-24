package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：BackMoneyBean
 * 描述：
 * 创建时间：2018-08-30  13:34
 */

public class BackMoneyBean {


    /**
     * ErrMsg :
     * ReObj : [{"BuyMoney":2000,"CreateTime":"/Date(1535472000000+0800)/","NowMoney":314.6,"OverDueMoney":298.14,"ServiceMoney":1387.26,"State":0}]
     * Success : 1
     */

    private String ErrMsg;
    private int Success;
    private List<ReObjBean> ReObj;

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String ErrMsg) {
        this.ErrMsg = ErrMsg;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int Success) {
        this.Success = Success;
    }

    public List<ReObjBean> getReObj() {
        return ReObj;
    }

    public void setReObj(List<ReObjBean> ReObj) {
        this.ReObj = ReObj;
    }

    public static class ReObjBean {
        /**
         * BuyMoney : 2000.0
         * CreateTime : /Date(1535472000000+0800)/
         * NowMoney : 314.6
         * OverDueMoney : 298.14
         * ServiceMoney : 1387.26
         * State : 0
         */

        private double BuyMoney;
        private String CreateTime;
        private double NowMoney;
        private double OverDueMoney;
        private double ServiceMoney;
        private int State;

        public double getBuyMoney() {
            return BuyMoney;
        }

        public void setBuyMoney(double BuyMoney) {
            this.BuyMoney = BuyMoney;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public double getNowMoney() {
            return NowMoney;
        }

        public void setNowMoney(double NowMoney) {
            this.NowMoney = NowMoney;
        }

        public double getOverDueMoney() {
            return OverDueMoney;
        }

        public void setOverDueMoney(double OverDueMoney) {
            this.OverDueMoney = OverDueMoney;
        }

        public double getServiceMoney() {
            return ServiceMoney;
        }

        public void setServiceMoney(double ServiceMoney) {
            this.ServiceMoney = ServiceMoney;
        }

        public int getState() {
            return State;
        }

        public void setState(int State) {
            this.State = State;
        }
    }
}
