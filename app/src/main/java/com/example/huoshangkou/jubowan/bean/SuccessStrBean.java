package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：SuccessStrBean
 * 描述：
 * 创建时间：2019-05-30  13:25
 */

public class SuccessStrBean {
    /**
     * Message : success
     * ReturnData : null
     * State : 1
     */

    private String Message;
    private Object ReturnData;
    private int State;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public Object getReturnData() {
        return ReturnData;
    }

    public void setReturnData(Object ReturnData) {
        this.ReturnData = ReturnData;
    }

    public int getState() {
        return State;
    }

    public void setState(int State) {
        this.State = State;
    }
}
