package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：ShopCarObjBean
 * 描述：主播骚男
 * 创建时间：2017-03-14  11:19
 */

public class ShopCarObjBean {

    private List<ShopCarListBean> FlList;
    private List<ShopCarListBean> YpList;

    public List<ShopCarListBean> getFlList() {
        return FlList;
    }

    public void setFlList(List<ShopCarListBean> flList) {
        FlList = flList;
    }

    public List<ShopCarListBean> getYpList() {
        return YpList;
    }

    public void setYpList(List<ShopCarListBean> ypList) {
        YpList = ypList;
    }
}