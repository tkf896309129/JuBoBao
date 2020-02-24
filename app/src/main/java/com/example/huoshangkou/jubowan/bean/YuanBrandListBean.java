package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：YuanBrandListBean
 * 描述：
 * 创建时间：2017-03-03  11:15
 */

public class YuanBrandListBean {

    private String BrandName;
    private String BrandPic;
    private int BrandID;

    public int getBrandID() {
        return BrandID;
    }

    public void setBrandID(int brandID) {
        BrandID = brandID;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String brandName) {
        BrandName = brandName;
    }

    public String getBrandPic() {
        return BrandPic;
    }

    public void setBrandPic(String brandPic) {
        BrandPic = brandPic;
    }
}
