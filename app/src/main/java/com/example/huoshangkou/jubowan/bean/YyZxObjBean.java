package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：YyZxObjBean
 * 描述：
 * 创建时间：2018-05-23  14:53
 */

public class YyZxObjBean {

    private String ErrMsg;
    private int PageCount;
    private int PageIndex;
    private List<YyZxBean> ReList;
    private int Success;
    private int TotalCount;
    private String TotalSum;

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

    public List<YyZxBean> getReList() {
        return ReList;
    }

    public void setReList(List<YyZxBean> reList) {
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
