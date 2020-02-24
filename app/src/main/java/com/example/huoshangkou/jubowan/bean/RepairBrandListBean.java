package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：RepairBrandListBean
 * 描述：
 * 创建时间：2017-03-24  14:25
 */

public class RepairBrandListBean {

    private List<ReMaintainBrandBean> ReMaintainBrand;
    private List<ReMaintainTypeBean> ReMaintainType;

    public List<ReMaintainBrandBean> getReMaintainBrand() {
        return ReMaintainBrand;
    }

    public void setReMaintainBrand(List<ReMaintainBrandBean> reMaintainBrand) {
        ReMaintainBrand = reMaintainBrand;
    }

    public List<ReMaintainTypeBean> getReMaintainType() {
        return ReMaintainType;
    }

    public void setReMaintainType(List<ReMaintainTypeBean> reMaintainType) {
        ReMaintainType = reMaintainType;
    }
}
