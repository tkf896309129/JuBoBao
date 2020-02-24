package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：HomeTitleBean
 * 描述：
 * 创建时间：2017-02-07  11:24
 */

public class HomeTitleBean {

    private int imageType;
    private String type;
    private String intro;
    private List<HomeDetailBean> detailList;

    public List<HomeDetailBean> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<HomeDetailBean> detailList) {
        this.detailList = detailList;
    }

    public int getImageType() {
        return imageType;
    }

    public void setImageType(int imageType) {
        this.imageType = imageType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
