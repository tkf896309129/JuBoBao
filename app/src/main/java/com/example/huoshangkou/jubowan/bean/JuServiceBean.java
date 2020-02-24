package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：JuServiceBean
 * 描述：
 * 创建时间：2018-06-13  10:25
 */

public class JuServiceBean {
    private String ErrMsg;
    private List<ServiceFactoryListBean> FactoryList;
    private List<ServicePjListBean> PeiJianList;
    private List<HomeImgListBean> HomePageList;
    private List<ServiceSheBeiListBean> SheBeiList;

    public List<HomeImgListBean> getHomePageList() {
        return HomePageList;
    }

    public void setHomePageList(List<HomeImgListBean> homePageList) {
        HomePageList = homePageList;
    }

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public List<ServiceFactoryListBean> getFactoryList() {
        return FactoryList;
    }

    public void setFactoryList(List<ServiceFactoryListBean> factoryList) {
        FactoryList = factoryList;
    }

    public List<ServicePjListBean> getPeiJianList() {
        return PeiJianList;
    }

    public void setPeiJianList(List<ServicePjListBean> peiJianList) {
        PeiJianList = peiJianList;
    }

    public List<ServiceSheBeiListBean> getSheBeiList() {
        return SheBeiList;
    }

    public void setSheBeiList(List<ServiceSheBeiListBean> sheBeiList) {
        SheBeiList = sheBeiList;
    }
}
