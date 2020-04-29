package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：ViewData
 * 描述：
 * 创建时间：2019-08-26  13:32
 */

public class ViewData {
    public String name; //名字
    public int value;   //数值

    public int color;   //颜色
    public float percentage; //百分比
    public float angle; //角度

    public ViewData(int value, String name) {
        this.value = value;
        this.name = name;
    }
}
