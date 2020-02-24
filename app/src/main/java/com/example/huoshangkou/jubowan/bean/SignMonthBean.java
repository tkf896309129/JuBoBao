package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：SignMonthBean
 * 描述：
 * 创建时间：2017-04-05  11:31
 */

public class SignMonthBean {

    private String CreateTime;
    private List<SignDayBean> Day;

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public List<SignDayBean> getDay() {
        return Day;
    }

    public void setDay(List<SignDayBean> day) {
        Day = day;
    }
}
