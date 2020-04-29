package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：CustomerDetailBean
 * 描述：
 * 创建时间：2019-09-03  10:11
 */

public class CustomerDetailBean {

    private String type;
    private String content;

    public CustomerDetailBean(String type,String content){
        this.type = type;
        this.content = content;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
