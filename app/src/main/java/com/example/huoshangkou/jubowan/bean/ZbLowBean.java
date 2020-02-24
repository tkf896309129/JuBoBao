package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：ZbLowBean
 * 描述：
 * 创建时间：2017-04-12  15:20
 */

public class ZbLowBean {

    private String ErrMsg;
    private int PageCount;
    private int PageIndex;
    private int Success;
    private int TotalCount;
    private String TotalSum;
    private List<ZbLowListBean> ReList;

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

    public List<ZbLowListBean> getReList() {
        return ReList;
    }

    public void setReList(List<ZbLowListBean> reList) {
        ReList = reList;
    }
}
