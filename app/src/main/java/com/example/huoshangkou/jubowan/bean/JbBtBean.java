package com.example.huoshangkou.jubowan.bean;

import java.io.Serializable;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：JbBtBean
 * 描述：
 * 创建时间：2018-08-30  09:14
 */

public class JbBtBean implements Serializable {
    /**
     * ErrMsg :
     * IsShowJbServices : 1
     * ReObj : {"IousRemainingAmount":39155.16,"IousRemainingDays":364,"IousTotalAmount":100000,"TimeLimit":"2018-08-29至2019-08-28"}
     * Success : 1
     */

    private String ErrMsg;
    private int IsShowJbServices;
    private ReObjBean ReObj;
    private int Success;

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String ErrMsg) {
        this.ErrMsg = ErrMsg;
    }

    public int getIsShowJbServices() {
        return IsShowJbServices;
    }

    public void setIsShowJbServices(int IsShowJbServices) {
        this.IsShowJbServices = IsShowJbServices;
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

    public static class ReObjBean implements Serializable {
        /**
         * IousRemainingAmount : 39155.16
         * IousRemainingDays : 364
         * IousTotalAmount : 100000.0
         * TimeLimit : 2018-08-29至2019-08-28
         */

        private String IousRemainingAmount;
        private int IousRemainingDays;
        private String IousTotalAmount;
        private String TimeLimit;
        private String ContractNo;
        private String Tel;

        public String getContractNo() {
            return ContractNo;
        }

        public void setContractNo(String contractNo) {
            ContractNo = contractNo;
        }

        public String getTel() {
            return Tel;
        }

        public void setTel(String tel) {
            Tel = tel;
        }

        public String getIousRemainingAmount() {
            return IousRemainingAmount;
        }

        public void setIousRemainingAmount(String IousRemainingAmount) {
            this.IousRemainingAmount = IousRemainingAmount;
        }

        public int getIousRemainingDays() {
            return IousRemainingDays;
        }

        public void setIousRemainingDays(int IousRemainingDays) {
            this.IousRemainingDays = IousRemainingDays;
        }

        public String getIousTotalAmount() {
            return IousTotalAmount;
        }

        public void setIousTotalAmount(String IousTotalAmount) {
            this.IousTotalAmount = IousTotalAmount;
        }

        public String getTimeLimit() {
            return TimeLimit;
        }

        public void setTimeLimit(String TimeLimit) {
            this.TimeLimit = TimeLimit;
        }
    }
}
