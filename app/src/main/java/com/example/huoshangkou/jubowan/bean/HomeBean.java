package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：HomeBean
 * 描述：
 * 创建时间：2017-05-05  14:00
 */

public class HomeBean {
    private String ErrMsg;
    private String FuLiaoDeal;
    private int IsPopup;
    private String JbwUrl;

    private List<HomeBrandListBean> BrandPics;
    private List<HomeImgListBean> HomePageList;
    private List<HomeForumListBean> LunTanList;
    private List<HomeForumListBean> RequestList;

    private String TotalDeal;
    private String YesterdayDeal;
    private String YesterdayMuQiang;
    private String YesterdayNeed;
    private String YesterdayServe;
    private String UserState;

    public int getIsPopup() {
        return IsPopup;
    }

    public void setIsPopup(int isPopup) {
        IsPopup = isPopup;
    }

    public List<HomeBrandListBean> getBrandPics() {
        return BrandPics;
    }

    public void setBrandPics(List<HomeBrandListBean> brandPics) {
        BrandPics = brandPics;
    }

    public String getJbwUrl() {
        return JbwUrl;
    }

    public void setJbwUrl(String jbwUrl) {
        JbwUrl = jbwUrl;
    }

    public String getUserState() {
        return UserState;
    }

    public void setUserState(String userState) {
        UserState = userState;
    }

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public String getFuLiaoDeal() {
        return FuLiaoDeal;
    }

    public void setFuLiaoDeal(String fuLiaoDeal) {
        FuLiaoDeal = fuLiaoDeal;
    }

    public List<HomeImgListBean> getHomePageList() {
        return HomePageList;
    }

    public void setHomePageList(List<HomeImgListBean> homePageList) {
        HomePageList = homePageList;
    }

    public List<HomeForumListBean> getLunTanList() {
        return LunTanList;
    }

    public void setLunTanList(List<HomeForumListBean> lunTanList) {
        LunTanList = lunTanList;
    }

    public List<HomeForumListBean> getRequestList() {
        return RequestList;
    }

    public void setRequestList(List<HomeForumListBean> requestList) {
        RequestList = requestList;
    }

    public String getTotalDeal() {
        return TotalDeal;
    }

    public void setTotalDeal(String totalDeal) {
        TotalDeal = totalDeal;
    }

    public String getYesterdayDeal() {
        return YesterdayDeal;
    }

    public void setYesterdayDeal(String yesterdayDeal) {
        YesterdayDeal = yesterdayDeal;
    }

    public String getYesterdayMuQiang() {
        return YesterdayMuQiang;
    }

    public void setYesterdayMuQiang(String yesterdayMuQiang) {
        YesterdayMuQiang = yesterdayMuQiang;
    }

    public String getYesterdayNeed() {
        return YesterdayNeed;
    }

    public void setYesterdayNeed(String yesterdayNeed) {
        YesterdayNeed = yesterdayNeed;
    }

    public String getYesterdayServe() {
        return YesterdayServe;
    }

    public void setYesterdayServe(String yesterdayServe) {
        YesterdayServe = yesterdayServe;
    }
}
