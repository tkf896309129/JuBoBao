package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：VAListBean
 * 描述：
 * 创建时间：2017-12-01  13:41
 */

public class VAListBean {

    private String Electricity;
    private String VAID;
    private String Voltage;

    public String getElectricity() {
        return Electricity;
    }

    public void setElectricity(String electricity) {
        Electricity = electricity;
    }

    public String getVAID() {
        return VAID;
    }

    public void setVAID(String VAID) {
        this.VAID = VAID;
    }

    public String getVoltage() {
        return Voltage;
    }

    public void setVoltage(String voltage) {
        Voltage = voltage;
    }
}
