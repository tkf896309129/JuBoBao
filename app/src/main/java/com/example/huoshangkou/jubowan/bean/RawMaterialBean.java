package com.example.huoshangkou.jubowan.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：RawMaterialBean
 * 描述：
 * 创建时间：2020-03-02  15:00
 */

public class RawMaterialBean {

    /**
     * ErrMsg :
     * PageCount : 0
     * PageIndex : 0
     * ReList : [{"BrandName":"test","CategoryName":"新新原料2","ClassName":"bhtd","ID":"49","LevelName":"石英砂二等品22","Pic":" ","Price":"989.00","Reserve":"15.00","SaleNum":"0.00"},{"BrandName":"cdfc","CategoryName":"石英砂","ClassName":"石英砂3","ID":"47","LevelName":"石英砂一等品","Pic":"https://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20200226/20200226131455_2033.jpg","Price":"1260.00","Reserve":"2999.00","SaleNum":"1.00"},{"BrandName":"青海昆仑","CategoryName":"纯碱","ClassName":"工业纯碱","ID":"44","LevelName":"工业品","Pic":"https://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20200225/20200225141347_4161.jpg","Price":"1649.50","Reserve":"999.00","SaleNum":"0.00"},{"BrandName":"重庆和友","CategoryName":"纯碱","ClassName":"工业纯碱","ID":"43","LevelName":"优等品","Pic":"https://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20200225/20200225141230_9394.jpg","Price":"1660.50","Reserve":"1000.00","SaleNum":"0.00"},{"BrandName":"cdfc","CategoryName":"石英砂","ClassName":"石英砂2","ID":"42","LevelName":"石英砂二等品22","Pic":"https://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20200225/20200225114522_1687.jpg","Price":"2080.00","Reserve":"9997.00","SaleNum":"3.00"},{"BrandName":"重庆宜化","CategoryName":"纯碱","ClassName":"重质纯碱","ID":"41","LevelName":"一等品","Pic":"https://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20191217/20191217112633_2209.jpg","Price":"1923.00","Reserve":"7893.00","SaleNum":"1002.00"},{"BrandName":"江苏实联","CategoryName":"纯碱","ClassName":"重质纯碱","ID":"40","LevelName":"一等品","Pic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20191129/20191129163802_5903.jpg","Price":"1680.00","Reserve":"5134.00","SaleNum":"2754.00"},{"BrandName":"五彩碱业","CategoryName":"纯碱","ClassName":"重质纯碱","ID":"39","LevelName":"一等品","Pic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20191126/20191126142109_7729.jpg","Price":"1850.00","Reserve":"4599.00","SaleNum":"3989.00"},{"BrandName":"河南金大地","CategoryName":"纯碱","ClassName":"轻质纯碱","ID":"38","LevelName":"一等品","Pic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20191023/20191023113340_8236.jpg","Price":"1750.00","Reserve":"8075.70","SaleNum":"2804.30"},{"BrandName":"四川辉泰","CategoryName":"纯碱","ClassName":"重质纯碱","ID":"37","LevelName":"一等品","Pic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20191009/20191009112601_3251.jpg","Price":"1800.00","Reserve":"8800.00","SaleNum":"0.00"},{"BrandName":"新都","CategoryName":"纯碱","ClassName":"轻质纯碱","ID":"36","LevelName":"一等品","Pic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190912/20190912163219_5746.jpg","Price":"1663.00","Reserve":"7910.50","SaleNum":"974.50"},{"BrandName":"湖北双环","CategoryName":"纯碱","ClassName":"重质纯碱","ID":"35","LevelName":"一等品","Pic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190827/20190827105429_4954.jpg","Price":"1924.00","Reserve":"2976.00","SaleNum":"2024.00"},{"BrandName":"苏盐井神","CategoryName":"纯碱","ClassName":"重质纯碱","ID":"34","LevelName":"一等品","Pic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190711/20190711152649_0053.jpg","Price":"1630.00","Reserve":"1928.00","SaleNum":"12938.00"},{"BrandName":"连云港碱业","CategoryName":"纯碱","ClassName":"重质纯碱","ID":"33","LevelName":"一等品","Pic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190711/20190711152516_6615.jpg","Price":"1630.00","Reserve":"972.00","SaleNum":"12913.00"},{"BrandName":"中盐昆山","CategoryName":"纯碱","ClassName":"重质纯碱","ID":"32","LevelName":"一等品","Pic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190711/20190711092233_9271.jpg","Price":"1790.00","Reserve":"9917.00","SaleNum":"1665.00"},{"BrandName":"重庆宜化","CategoryName":"纯碱","ClassName":"轻质纯碱","ID":"31","LevelName":"一等品","Pic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190709/20190709145713_7084.jpg","Price":"1923.00","Reserve":"4918.50","SaleNum":"6666.50"},{"BrandName":"山东海天","CategoryName":"纯碱","ClassName":"重质纯碱","ID":"30","LevelName":"一等品","Pic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190628/20190628094143_3178.jpg","Price":"1670.00","Reserve":"7908.90","SaleNum":"976.10"},{"BrandName":"和邦","CategoryName":"纯碱","ClassName":"轻质纯碱","ID":"29","LevelName":"一等品","Pic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190530/20190530162513_1064.jpg","Price":"1837.00","Reserve":"8660.00","SaleNum":"4682.00"},{"BrandName":"重庆和友","CategoryName":"纯碱","ClassName":"轻质纯碱","ID":"28","LevelName":"优等品","Pic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190524/20190524143135_8088.jpg","Price":"1650.00","Reserve":"3710.95","SaleNum":"8177.05"},{"BrandName":"河南金大地","CategoryName":"纯碱","ClassName":"重质纯碱","ID":"27","LevelName":"一等品","Pic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190517/20190517132234_2329.jpg","Price":"1910.00","Reserve":"4196.00","SaleNum":"11844.50"}]
     * Success : 1
     * TotalCount : 0
     * TotalSum : 0
     */

    private String ErrMsg;
    private int PageCount;
    private int PageIndex;
    private int Success;
    private int TotalCount;
    private String TotalSum;
    private List<ReListBean> ReList;

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String ErrMsg) {
        this.ErrMsg = ErrMsg;
    }

    public int getPageCount() {
        return PageCount;
    }

    public void setPageCount(int PageCount) {
        this.PageCount = PageCount;
    }

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int PageIndex) {
        this.PageIndex = PageIndex;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int Success) {
        this.Success = Success;
    }

    public int getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(int TotalCount) {
        this.TotalCount = TotalCount;
    }

    public String getTotalSum() {
        return TotalSum;
    }

    public void setTotalSum(String TotalSum) {
        this.TotalSum = TotalSum;
    }

    public List<ReListBean> getReList() {
        return ReList;
    }

    public void setReList(List<ReListBean> ReList) {
        this.ReList = ReList;
    }

    public static class ReListBean implements Serializable {
        /**
         * BrandName : test
         * CategoryName : 新新原料2
         * ClassName : bhtd
         * ID : 49
         * LevelName : 石英砂二等品22
         * Pic :
         * Price : 989.00
         * Reserve : 15.00
         * SaleNum : 0.00
         */

        private String BrandName;
        private String CategoryName;
        private String ClassName;
        private String ID;
        private String LevelName;
        private String Pic;
        private String Price;
        private String Reserve;
        private String SaleNum;
        private String NameUnit;

        public String getNameUnit() {
            return NameUnit;
        }

        public void setNameUnit(String nameUnit) {
            NameUnit = nameUnit;
        }

        public String getBrandName() {
            return BrandName;
        }

        public void setBrandName(String BrandName) {
            this.BrandName = BrandName;
        }

        public String getCategoryName() {
            return CategoryName;
        }

        public void setCategoryName(String CategoryName) {
            this.CategoryName = CategoryName;
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

        public String getLevelName() {
            return LevelName;
        }

        public void setLevelName(String LevelName) {
            this.LevelName = LevelName;
        }

        public String getPic() {
            return Pic;
        }

        public void setPic(String Pic) {
            this.Pic = Pic;
        }

        public String getPrice() {
            return Price;
        }

        public void setPrice(String Price) {
            this.Price = Price;
        }

        public String getReserve() {
            return Reserve;
        }

        public void setReserve(String Reserve) {
            this.Reserve = Reserve;
        }

        public String getSaleNum() {
            return SaleNum;
        }

        public void setSaleNum(String SaleNum) {
            this.SaleNum = SaleNum;
        }
    }
}
