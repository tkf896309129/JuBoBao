package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：BjDataBean
 * 描述：
 * 创建时间：2018-01-05  13:40
 */

public class BjDataBean {

    private String customer_id;
    private String customer_name;
    private List<BjListBean> detail_list;
    private int nums;
    private double sum_money;

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public List<BjListBean> getDetail_list() {
        return detail_list;
    }

    public void setDetail_list(List<BjListBean> detail_list) {
        this.detail_list = detail_list;
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    public double getSum_money() {
        return sum_money;
    }

    public void setSum_money(double sum_money) {
        this.sum_money = sum_money;
    }
}
