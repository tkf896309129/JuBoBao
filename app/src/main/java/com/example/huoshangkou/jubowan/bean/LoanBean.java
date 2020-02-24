package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：LoanBean
 * 描述：
 * 创建时间：2017-09-15  15:19
 */

public class LoanBean  {
    private String ErrMsg;
    private String Android_key;
    private String ios_key;
    private String Address;
    private String AuthenticationState;
    private String Companyed;
    private int IsQualification;
    private String LinkMan;
    private String LinkManCardNo;

    private int PageCount;
    private int PageIndex;
    private List<LoanListBean> ReList;
    private int Success;
    private int TotalCount;
    private String TotalSum;

    public String getIos_key() {
        return ios_key;
    }

    public void setIos_key(String ios_key) {
        this.ios_key = ios_key;
    }

    public int getIsQualification() {
        return IsQualification;
    }

    public void setIsQualification(int isQualification) {
        IsQualification = isQualification;
    }

    public String getAndroid_key() {
        return Android_key;
    }

    public void setAndroid_key(String android_key) {
        Android_key = android_key;
    }

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public int getPageCount() {
        return PageCount;
    }

    public void setPageCount(int pageCount) {
        PageCount = pageCount;
    }

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int pageIndex) {
        PageIndex = pageIndex;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getAuthenticationState() {
        return AuthenticationState;
    }

    public void setAuthenticationState(String authenticationState) {
        AuthenticationState = authenticationState;
    }

    public String getCompanyed() {
        return Companyed;
    }

    public void setCompanyed(String companyed) {
        Companyed = companyed;
    }

    public String getLinkMan() {
        return LinkMan;
    }

    public void setLinkMan(String linkMan) {
        LinkMan = linkMan;
    }

    public String getLinkManCardNo() {
        return LinkManCardNo;
    }

    public void setLinkManCardNo(String linkManCardNo) {
        LinkManCardNo = linkManCardNo;
    }

    public List<LoanListBean> getReList() {
        return ReList;
    }

    public void setReList(List<LoanListBean> reList) {
        ReList = reList;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }

    public int getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(int totalCount) {
        TotalCount = totalCount;
    }

    public String getTotalSum() {
        return TotalSum;
    }

    public void setTotalSum(String totalSum) {
        TotalSum = totalSum;
    }
}
