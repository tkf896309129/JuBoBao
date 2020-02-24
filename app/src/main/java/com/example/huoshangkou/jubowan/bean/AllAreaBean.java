package com.example.huoshangkou.jubowan.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：AllAreaBean
 * 描述：
 * 创建时间：2017-02-21  09:18
 */

public class AllAreaBean implements Serializable {

    private List<ProvinceListBean> province;

    public List<ProvinceListBean> getProvince() {
        return province;
    }

    public void setProvince(List<ProvinceListBean> province) {
        this.province = province;
    }


}
