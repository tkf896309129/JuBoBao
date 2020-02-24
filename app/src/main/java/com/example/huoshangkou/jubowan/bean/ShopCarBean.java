package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：ShopCarBean
 * 描述：
 * 创建时间：2017-03-14  10:12
 */

public class ShopCarBean {

    private String ErrMsg;
    private ShopCarObjBean ReObj;

    private int Success;

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public ShopCarObjBean getReObj() {
        return ReObj;
    }

    public void setReObj(ShopCarObjBean reObj) {
        ReObj = reObj;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }
}
