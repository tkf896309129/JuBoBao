package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：LoanIntroBean
 * 描述：
 * 创建时间：2017-09-19  09:40
 */

public class LoanIntroBean {

    private String ErrMsg;
    private String LinkMan;
    private String LoanTel;
    private int PageCount;
    private int PageIndex;
    private List<LoanIntroListBean> ReList;
    private int Success;
    private int TotalCount;
    private String TotalSum;

    public String getLinkMan() {
        return LinkMan;
    }

    public void setLinkMan(String linkMan) {
        LinkMan = linkMan;
    }

    public String getLoanTel() {
        return LoanTel;
    }

    public void setLoanTel(String loanTel) {
        LoanTel = loanTel;
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

    public List<LoanIntroListBean> getReList() {
        return ReList;
    }

    public void setReList(List<LoanIntroListBean> reList) {
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
