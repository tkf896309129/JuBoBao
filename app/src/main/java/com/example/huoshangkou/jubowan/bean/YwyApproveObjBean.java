package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：YwyApproveObjBean
 * 描述：
 * 创建时间：2017-03-08  15:08
 */

public class YwyApproveObjBean {

    private List<AreaBean> AreaList;
    private List<BrandBean> BrandList;
    private List<ClassBean> ClassList;

    public List<AreaBean> getAreaList() {
        return AreaList;
    }

    public void setAreaList(List<AreaBean> areaList) {
        AreaList = areaList;
    }

    public List<BrandBean> getBrandList() {
        return BrandList;
    }

    public void setBrandList(List<BrandBean> brandList) {
        BrandList = brandList;
    }

    public List<ClassBean> getClassList() {
        return ClassList;
    }

    public void setClassList(List<ClassBean> classList) {
        ClassList = classList;
    }
}
