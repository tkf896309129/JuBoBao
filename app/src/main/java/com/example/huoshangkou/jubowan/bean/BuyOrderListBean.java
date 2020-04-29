package com.example.huoshangkou.jubowan.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：BuyOrderListBean
 * 描述：
 * 创建时间：2019-06-27  09:27
 */

public class BuyOrderListBean implements Serializable {


    /**
     * childern : [{"className":"信义/超白","classSpec":"3660*1100","classWeight":"6mm","createTime":"2019年06月25日 13时","levelID":"一等品","purchaseID":"HT20190625131727","purchaseNumber":"10","state":"已采购"},{"className":"信义/超白","classSpec":"3660*2440","classWeight":"6mm","createTime":"2019年06月25日 13时","levelID":"一等品","purchaseID":"HT20190625131727","purchaseNumber":"30","state":"已采购"},{"className":"信义/有色 (紫色)","classSpec":"3660*1200","classWeight":"6mm","createTime":"2019年06月25日 13时","levelID":"一等品","purchaseID":"HT20190625131727","purchaseNumber":"10","state":"已采购"},{"className":"信义/有色 (紫色)","classSpec":"3660*2440","classWeight":"6mm","createTime":"2019年06月25日 13时","levelID":"一等品","purchaseID":"HT20190625131727","purchaseNumber":"30","state":"已采购"}]
     * createTime : 2019年06月25日 13时
     * labelH5Url : http://192.168.1.175:11826//ClassPurchase/ClassPurchase/GetLabelH5?PurchaseID=HT20190625131727&&currentUser=20181212094123343317716FB4845B8&&LoginTenantCookie=3ed4c4ea-fcf5-42f1-8714-176ea831c126
     * projectName : 1
     * purchaseH5Url : http://192.168.1.175:11826//ClassPurchase/ClassPurchase/GetPurchaseH5?PurchaseID=HT20190625131727&&currentUser=20181212094123343317716FB4845B8&&LoginTenantCookie=3ed4c4ea-fcf5-42f1-8714-176ea831c126
     * purchaseID : HT20190625131727
     * state : 已采购
     */

    private String createTime;
    private String labelH5Url;
    private String projectName;
    private String purchaseH5Url;
    private String purchaseID;
    private String state;
    private List<ChildernBean> childern;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLabelH5Url() {
        return labelH5Url;
    }

    public void setLabelH5Url(String labelH5Url) {
        this.labelH5Url = labelH5Url;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPurchaseH5Url() {
        return purchaseH5Url;
    }

    public void setPurchaseH5Url(String purchaseH5Url) {
        this.purchaseH5Url = purchaseH5Url;
    }

    public String getPurchaseID() {
        return purchaseID;
    }

    public void setPurchaseID(String purchaseID) {
        this.purchaseID = purchaseID;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<ChildernBean> getChildern() {
        return childern;
    }

    public void setChildern(List<ChildernBean> childern) {
        this.childern = childern;
    }

    public static class ChildernBean {
        /**
         * className : 信义/超白
         * classSpec : 3660*1100
         * classWeight : 6mm
         * createTime : 2019年06月25日 13时
         * levelID : 一等品
         * purchaseID : HT20190625131727
         * purchaseNumber : 10
         * state : 已采购
         */

        private String className;
        private String classSpec;
        private String classWeight;
        private String createTime;
        private String levelID;
        private String purchaseID;
        private String purchaseNumber;
        private String state;
        private String brand;
        private String number;
        private String spec;
        private String type;


        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getSpec() {
            return spec;
        }

        public void setSpec(String spec) {
            this.spec = spec;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getClassSpec() {
            return classSpec;
        }

        public void setClassSpec(String classSpec) {
            this.classSpec = classSpec;
        }

        public String getClassWeight() {
            return classWeight;
        }

        public void setClassWeight(String classWeight) {
            this.classWeight = classWeight;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getLevelID() {
            return levelID;
        }

        public void setLevelID(String levelID) {
            this.levelID = levelID;
        }

        public String getPurchaseID() {
            return purchaseID;
        }

        public void setPurchaseID(String purchaseID) {
            this.purchaseID = purchaseID;
        }

        public String getPurchaseNumber() {
            return purchaseNumber;
        }

        public void setPurchaseNumber(String purchaseNumber) {
            this.purchaseNumber = purchaseNumber;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
