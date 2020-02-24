package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：NotifyMessageBean
 * 描述：
 * 创建时间：2017-05-04  10:10
 */

public class NotifyMessageBean {

    private String msg_id;
    private String display_type;
    private int random_min;
    private NotifyMessageBodyBean body;

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getDisplay_type() {
        return display_type;
    }

    public void setDisplay_type(String display_type) {
        this.display_type = display_type;
    }

    public int getRandom_min() {
        return random_min;
    }

    public void setRandom_min(int random_min) {
        this.random_min = random_min;
    }

    public NotifyMessageBodyBean getBody() {
        return body;
    }

    public void setBody(NotifyMessageBodyBean body) {
        this.body = body;
    }
}
