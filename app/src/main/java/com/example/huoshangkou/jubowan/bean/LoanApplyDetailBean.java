package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：LoanApplyDetailBean
 * 描述：
 * 创建时间：2018-08-30  11:14
 */

public class LoanApplyDetailBean {


    /**
     * ErrMsg :
     * ReObj : {"AccountBankCard":"374070630793","AccountBankName":"浙江聚马物流有限公司","BuyMoney":"6000.00","ContractFile":"2018100059_0017","ContractNo":"2018100059_0017","CouponInfo":{"CouponType":"IousInterestFree","CouponValue":"15","Description":"每笔白条只能使用一张","Id":"8da63046-a06d-405b-bd90-f341f58ad5f9","Name":"15天免息券","PeriodOfValidity":"2018年09月01日 至 2018年12月31日","State":"未使用"},"CreateTime":"/Date(1544402158350+0800)/","Days":0,"EndTime":"/Date(1549555200000+0800)/","ID":14167,"LateMoney":"0.00","NeedRefundAmount":"6000.00","OrderCount":1,"OrderID":"20201809101533064217","OverdueCostTips":"(本金*0.06%*(逾期天数))","ServiceChargeTips":"(本金*0.04%*(借款天数-3))","ServiceMoney":"0.00","StartTime":"/Date(1544371200000+0800)/","State":-1}
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
         * AccountBankCard : 374070630793
         * AccountBankName : 浙江聚马物流有限公司
         * BuyMoney : 6000.00
         * ContractFile : 2018100059_0017
         * ContractNo : 2018100059_0017
         * CouponInfo : {"CouponType":"IousInterestFree","CouponValue":"15","Description":"每笔白条只能使用一张","Id":"8da63046-a06d-405b-bd90-f341f58ad5f9","Name":"15天免息券","PeriodOfValidity":"2018年09月01日 至 2018年12月31日","State":"未使用"}
         * CreateTime : /Date(1544402158350+0800)/
         * Days : 0
         * EndTime : /Date(1549555200000+0800)/
         * ID : 14167
         * LateMoney : 0.00
         * NeedRefundAmount : 6000.00
         * OrderCount : 1
         * OrderID : 20201809101533064217
         * OverdueCostTips : (本金*0.06%*(逾期天数))
         * ServiceChargeTips : (本金*0.04%*(借款天数-3))
         * ServiceMoney : 0.00
         * StartTime : /Date(1544371200000+0800)/
         * State : -1
         */

        private String AccountBankCard;
        private String AccountBankName;
        private String BuyMoney;
        private String ContractFile;
        private String ContractNo;
        private CouponInfoBean CouponInfo;
        private String CreateTime;
        private int Days;
        private String EndTime;
        private int ID;
        private String LateMoney;
        private String NeedRefundAmount;
        private int OrderCount;
        private String OrderID;
        private String OverdueCostTips;
        private String ServiceChargeTips;
        private String ServiceMoney;
        private String StartTime;
        private int State;

        public String getAccountBankCard() {
            return AccountBankCard;
        }

        public void setAccountBankCard(String AccountBankCard) {
            this.AccountBankCard = AccountBankCard;
        }

        public String getAccountBankName() {
            return AccountBankName;
        }

        public void setAccountBankName(String AccountBankName) {
            this.AccountBankName = AccountBankName;
        }

        public String getBuyMoney() {
            return BuyMoney;
        }

        public void setBuyMoney(String BuyMoney) {
            this.BuyMoney = BuyMoney;
        }

        public String getContractFile() {
            return ContractFile;
        }

        public void setContractFile(String ContractFile) {
            this.ContractFile = ContractFile;
        }

        public String getContractNo() {
            return ContractNo;
        }

        public void setContractNo(String ContractNo) {
            this.ContractNo = ContractNo;
        }

        public CouponInfoBean getCouponInfo() {
            return CouponInfo;
        }

        public void setCouponInfo(CouponInfoBean CouponInfo) {
            this.CouponInfo = CouponInfo;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public int getDays() {
            return Days;
        }

        public void setDays(int Days) {
            this.Days = Days;
        }

        public String getEndTime() {
            return EndTime;
        }

        public void setEndTime(String EndTime) {
            this.EndTime = EndTime;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getLateMoney() {
            return LateMoney;
        }

        public void setLateMoney(String LateMoney) {
            this.LateMoney = LateMoney;
        }

        public String getNeedRefundAmount() {
            return NeedRefundAmount;
        }

        public void setNeedRefundAmount(String NeedRefundAmount) {
            this.NeedRefundAmount = NeedRefundAmount;
        }

        public int getOrderCount() {
            return OrderCount;
        }

        public void setOrderCount(int OrderCount) {
            this.OrderCount = OrderCount;
        }

        public String getOrderID() {
            return OrderID;
        }

        public void setOrderID(String OrderID) {
            this.OrderID = OrderID;
        }

        public String getOverdueCostTips() {
            return OverdueCostTips;
        }

        public void setOverdueCostTips(String OverdueCostTips) {
            this.OverdueCostTips = OverdueCostTips;
        }

        public String getServiceChargeTips() {
            return ServiceChargeTips;
        }

        public void setServiceChargeTips(String ServiceChargeTips) {
            this.ServiceChargeTips = ServiceChargeTips;
        }

        public String getServiceMoney() {
            return ServiceMoney;
        }

        public void setServiceMoney(String ServiceMoney) {
            this.ServiceMoney = ServiceMoney;
        }

        public String getStartTime() {
            return StartTime;
        }

        public void setStartTime(String StartTime) {
            this.StartTime = StartTime;
        }

        public int getState() {
            return State;
        }

        public void setState(int State) {
            this.State = State;
        }

        public static class CouponInfoBean {
            /**
             * CouponType : IousInterestFree
             * CouponValue : 15
             * Description : 每笔白条只能使用一张
             * Id : 8da63046-a06d-405b-bd90-f341f58ad5f9
             * Name : 15天免息券
             * PeriodOfValidity : 2018年09月01日 至 2018年12月31日
             * State : 未使用
             */

            private String CouponType;
            private String CouponValue;
            private String Description;
            private String Id;
            private String Name;
            private String PeriodOfValidity;
            private String State;

            public String getCouponType() {
                return CouponType;
            }

            public void setCouponType(String CouponType) {
                this.CouponType = CouponType;
            }

            public String getCouponValue() {
                return CouponValue;
            }

            public void setCouponValue(String CouponValue) {
                this.CouponValue = CouponValue;
            }

            public String getDescription() {
                return Description;
            }

            public void setDescription(String Description) {
                this.Description = Description;
            }

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getPeriodOfValidity() {
                return PeriodOfValidity;
            }

            public void setPeriodOfValidity(String PeriodOfValidity) {
                this.PeriodOfValidity = PeriodOfValidity;
            }

            public String getState() {
                return State;
            }

            public void setState(String State) {
                this.State = State;
            }
        }
    }
}
