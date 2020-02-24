package com.example.huoshangkou.jubowan.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：DianTabBean
 * 描述：
 * 创建时间：2018-06-07  17:02
 */

public class DianTabBean {
    private String TableRemark;
    private String Weight;
    private String TiHuoPrice;
    private String ChengBenPrice;
    private String XiaoShouPrice;
    private String YingLiPrice;

    public String getTableRemark() {
        return TableRemark;
    }

    public void setTableRemark(String tableRemark) {
        this.TableRemark = tableRemark;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getTiHuoPrice() {
        return TiHuoPrice;
    }

    public void setTiHuoPrice(String tiHuoPrice) {
        TiHuoPrice = tiHuoPrice;
    }

    public String getChengBenPrice() {
        return ChengBenPrice;
    }

    public void setChengBenPrice(String chengBenPrice) {
        ChengBenPrice = chengBenPrice;
    }

    public String getXiaoShouPrice() {
        return XiaoShouPrice;
    }

    public void setXiaoShouPrice(String xiaoShouPrice) {
        XiaoShouPrice = xiaoShouPrice;
    }

    public String getYingLiPrice() {
        return YingLiPrice;
    }

    public void setYingLiPrice(String yingLiPrice) {
        YingLiPrice = yingLiPrice;
    }
}
