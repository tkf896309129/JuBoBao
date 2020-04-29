package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：HomeTypeBean
 * 描述：
 * 创建时间：2017-05-05  15:15
 */

public class HomeTypeBean {

    private String type;
    private List<NewHomeBean.DBean.ResultBean.ProjectBiddingBean> list;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<NewHomeBean.DBean.ResultBean.ProjectBiddingBean> getList() {
        return list;
    }

    public void setList(List<NewHomeBean.DBean.ResultBean.ProjectBiddingBean> list) {
        this.list = list;
    }
}
