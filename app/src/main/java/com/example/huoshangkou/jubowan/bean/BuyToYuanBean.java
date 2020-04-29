package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：BuyToYuanBean
 * 描述：
 * 创建时间：2019-07-10  15:52
 */

public class BuyToYuanBean {
    /**
     * Message : 获取数据成功
     * ReturnData : [{"__type":"Task_ClassPurchaseLabel:#AtjuboSaaS.Common.SaasBizModel","BaoNumber":1,"BrandName":"明达","ClassName":"有色","ID":"L-Y201907101027690","IsOut":1,"IsPut":1,"LevelID":"一等品","Locator":"B","Number":4,"OutNumber":4,"Property":"茶色","PurchaseID":"HT2019071010275","PutNumber":4,"Remark":"","Spec":"4200*3400","TenantID":"3ed4c4ea-fcf5-42f1-8714-176ea831c126","Weight":"6mm"},{"__type":"Task_ClassPurchaseLabel:#AtjuboSaaS.Common.SaasBizModel","BaoNumber":1,"BrandName":"明达","ClassName":"白玻","ID":"L-Y2019071010275580","IsOut":0,"IsPut":0,"LevelID":"一等品","Locator":null,"Number":4,"OutNumber":0,"Property":null,"PurchaseID":"HT2019071010275","PutNumber":0,"Remark":null,"Spec":"4200*3400","TenantID":"3ed4c4ea-fcf5-42f1-8714-176ea831c126","Weight":"3.5mm"},{"__type":"Task_ClassPurchaseLabel:#AtjuboSaaS.Common.SaasBizModel","BaoNumber":1,"BrandName":"明达","ClassName":"超白","ID":"L-Y2019071010271870","IsOut":0,"IsPut":0,"LevelID":"一等品","Locator":null,"Number":4,"OutNumber":0,"Property":null,"PurchaseID":"HT2019071010275","PutNumber":0,"Remark":null,"Spec":"4200*3400","TenantID":"3ed4c4ea-fcf5-42f1-8714-176ea831c126","Weight":"8mm"}]
     * State : 1
     */

    private String Message;
    private String State;
    private List<ReturnDataBean> ReturnData;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public List<ReturnDataBean> getReturnData() {
        return ReturnData;
    }

    public void setReturnData(List<ReturnDataBean> ReturnData) {
        this.ReturnData = ReturnData;
    }

    public static class ReturnDataBean {
        /**
         * __type : Task_ClassPurchaseLabel:#AtjuboSaaS.Common.SaasBizModel
         * BaoNumber : 1
         * BrandName : 明达
         * ClassName : 有色
         * ID : L-Y201907101027690
         * IsOut : 1
         * IsPut : 1
         * LevelID : 一等品
         * Locator : B
         * Number : 4
         * OutNumber : 4
         * Property : 茶色
         * PurchaseID : HT2019071010275
         * PutNumber : 4
         * Remark :
         * Spec : 4200*3400
         * TenantID : 3ed4c4ea-fcf5-42f1-8714-176ea831c126
         * Weight : 6mm
         */

        private String __type;
        private int BaoNumber;
        private String BrandName;
        private String ClassName;
        private String ID;
        private int IsOut;
        private int IsPut;
        private String LevelID;
        private String Locator;
        private int Number;
        private int OutNumber;
        private String Property;
        private String PurchaseID;
        private int PutNumber;
        private String Remark;
        private String Spec;
        private String TenantID;
        private String Weight;

        public String get__type() {
            return __type;
        }

        public void set__type(String __type) {
            this.__type = __type;
        }

        public int getBaoNumber() {
            return BaoNumber;
        }

        public void setBaoNumber(int BaoNumber) {
            this.BaoNumber = BaoNumber;
        }

        public String getBrandName() {
            return BrandName;
        }

        public void setBrandName(String BrandName) {
            this.BrandName = BrandName;
        }

        public String getClassName() {
            return ClassName;
        }

        public void setClassName(String ClassName) {
            this.ClassName = ClassName;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public int getIsOut() {
            return IsOut;
        }

        public void setIsOut(int IsOut) {
            this.IsOut = IsOut;
        }

        public int getIsPut() {
            return IsPut;
        }

        public void setIsPut(int IsPut) {
            this.IsPut = IsPut;
        }

        public String getLevelID() {
            return LevelID;
        }

        public void setLevelID(String LevelID) {
            this.LevelID = LevelID;
        }

        public String getLocator() {
            return Locator;
        }

        public void setLocator(String Locator) {
            this.Locator = Locator;
        }

        public int getNumber() {
            return Number;
        }

        public void setNumber(int Number) {
            this.Number = Number;
        }

        public int getOutNumber() {
            return OutNumber;
        }

        public void setOutNumber(int OutNumber) {
            this.OutNumber = OutNumber;
        }

        public String getProperty() {
            return Property;
        }

        public void setProperty(String Property) {
            this.Property = Property;
        }

        public String getPurchaseID() {
            return PurchaseID;
        }

        public void setPurchaseID(String PurchaseID) {
            this.PurchaseID = PurchaseID;
        }

        public int getPutNumber() {
            return PutNumber;
        }

        public void setPutNumber(int PutNumber) {
            this.PutNumber = PutNumber;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public String getSpec() {
            return Spec;
        }

        public void setSpec(String Spec) {
            this.Spec = Spec;
        }

        public String getTenantID() {
            return TenantID;
        }

        public void setTenantID(String TenantID) {
            this.TenantID = TenantID;
        }

        public String getWeight() {
            return Weight;
        }

        public void setWeight(String Weight) {
            this.Weight = Weight;
        }
    }
}
