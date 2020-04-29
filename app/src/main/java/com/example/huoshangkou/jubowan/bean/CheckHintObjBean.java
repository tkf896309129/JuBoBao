package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：CheckHintObjBean
 * 描述：
 * 创建时间：2018-06-14  17:46
 */

public class CheckHintObjBean {
    //1:销售部    2：运营部   3：其它
    private String IsOK;
    private String Number;
    private String CopyNumber;

    public String getCopyNumber() {
        return CopyNumber;
    }

    public void setCopyNumber(String copyNumber) {
        CopyNumber = copyNumber;
    }

    public String getIsOK() {
        return IsOK;
    }

    public void setIsOK(String isOK) {
        IsOK = isOK;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }
}
