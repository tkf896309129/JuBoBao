package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：ShopCarListNewBean
 * 描述：
 * 创建时间：2020-03-05  15:26
 */

public class ShopCarListNewBean {


    /**
     * ErrMsg :
     * PageCount : 0
     * PageIndex : 0
     * ReList : [{"BrandName":"普罗菲尔","CategoryName":null,"ClassName":"不可折弯铝条","GuigeName":"12A","HaveCount":"100000米","ID":"39","LevelName":null,"MoxiName":null,"NameUnit":"米","Pic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20160323/20160323143154_4988.jpg","Price":"0.42","SaleCount":null,"ToNum":"30.00","Type":2,"Weight":null,"Xy":null},{"BrandName":"上海锐格","CategoryName":null,"ClassName":"不可折弯铝条","GuigeName":"6A","HaveCount":"98827米","ID":"47","LevelName":null,"MoxiName":null,"NameUnit":"米","Pic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20160323/20160323143457_0300.jpg","Price":"0.36","SaleCount":null,"ToNum":"5.00","Type":2,"Weight":null,"Xy":null},{"BrandName":"浙江多力","CategoryName":null,"ClassName":"PVB中间膜","GuigeName":"0.5mm","HaveCount":"12200平方米","ID":"381","LevelName":null,"MoxiName":null,"NameUnit":"平方米","Pic":"https://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20191227/20191227134520_1270.jpg","Price":"10.00","SaleCount":null,"ToNum":"1.00","Type":2,"Weight":null,"Xy":null},{"BrandName":"浙江多力","CategoryName":null,"ClassName":"PVB中间膜","GuigeName":"0.38mm","HaveCount":"11200平方米","ID":"382","LevelName":null,"MoxiName":null,"NameUnit":"平方米","Pic":"https://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20191227/20191227134551_1739.jpg","Price":"7.50","SaleCount":null,"ToNum":"4.00","Type":2,"Weight":null,"Xy":null}]
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
    private List<ShopCarListBean> ReList;

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

    public List<ShopCarListBean> getReList() {
        return ReList;
    }

    public void setReList(List<ShopCarListBean> ReList) {
        this.ReList = ReList;
    }


}
