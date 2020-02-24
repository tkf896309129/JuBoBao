package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：BackMoneyPriceBean
 * 描述：
 * 创建时间：2018-09-05  13:55
 */

public class BackMoneyPriceBean {


    /**
     * ErrMsg :
     * ReObj : {"OverdueCost":"0.00","ServiceCharge":"0.00","TotalMoney":"56653.43"}
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
         * OverdueCost : 0.00
         * ServiceCharge : 0.00
         * TotalMoney : 56653.43
         */

        private String OverdueCost;
        private String ServiceCharge;
        private String TotalMoney;

        public String getOverdueCost() {
            return OverdueCost;
        }

        public void setOverdueCost(String OverdueCost) {
            this.OverdueCost = OverdueCost;
        }

        public String getServiceCharge() {
            return ServiceCharge;
        }

        public void setServiceCharge(String ServiceCharge) {
            this.ServiceCharge = ServiceCharge;
        }

        public String getTotalMoney() {
            return TotalMoney;
        }

        public void setTotalMoney(String TotalMoney) {
            this.TotalMoney = TotalMoney;
        }
    }
}
