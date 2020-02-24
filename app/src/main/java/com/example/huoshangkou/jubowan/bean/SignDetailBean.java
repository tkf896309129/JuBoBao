package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：SignDetailBean
 * 描述：
 * 创建时间：2017-04-05  11:29
 */

public class SignDetailBean {

    private int Counts;
    private String CreateTime;
    private String LinkMan;
    private List<SignMonthBean> Month;

    public int getCounts() {
        return Counts;
    }

    public void setCounts(int counts) {
        Counts = counts;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getLinkMan() {
        return LinkMan;
    }

    public void setLinkMan(String linkMan) {
        LinkMan = linkMan;
    }

    public List<SignMonthBean> getMonth() {
        return Month;
    }

    public void setMonth(List<SignMonthBean> month) {
        Month = month;
    }
}
