package com.example.huoshangkou.jubowan.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：ProvinceListBean
 * 描述：
 * 创建时间：2017-02-21  09:18
 */

public class ProvinceListBean implements Serializable {

    private String name;
    private List<CityListBean> city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityListBean> getCity() {
        return city;
    }

    public void setCity(List<CityListBean> city) {
        this.city = city;
    }

}
