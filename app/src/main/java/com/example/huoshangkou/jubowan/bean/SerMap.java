package com.example.huoshangkou.jubowan.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：SerMap
 * 描述：
 * 创建时间：2019-03-08  11:19
 */

public class SerMap implements Serializable {

    public Map<String,String> map;
    public  SerMap(){

    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;

    }

}
