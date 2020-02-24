package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：BtXyBean
 * 描述：
 * 创建时间：2018-09-11  08:41
 */

public class BtXyBean {


    /**
     * ServiceConfirmation : http://192.168.10.120:88/serviceagree/JbIous/ServiceConfirmation.aspx
     * ChargeRules : http://192.168.10.120:88/serviceagree/JbIous/ChargeRules.aspx
     * CreditService : http://192.168.10.120:88/serviceagree/JbIous/CreditService.aspx
     */

    private String ServiceConfirmation;
    private String ChargeRules;
    private String CreditService;
    private String ESingleUserUrl;
    private String DigitalCertificateUrl;

    public String getESingleUserUrl() {
        return ESingleUserUrl;
    }

    public void setESingleUserUrl(String ESingleUserUrl) {
        this.ESingleUserUrl = ESingleUserUrl;
    }

    public String getDigitalCertificateUrl() {
        return DigitalCertificateUrl;
    }

    public void setDigitalCertificateUrl(String digitalCertificateUrl) {
        DigitalCertificateUrl = digitalCertificateUrl;
    }

    public String getServiceConfirmation() {
        return ServiceConfirmation;
    }

    public void setServiceConfirmation(String ServiceConfirmation) {
        this.ServiceConfirmation = ServiceConfirmation;
    }

    public String getChargeRules() {
        return ChargeRules;
    }

    public void setChargeRules(String ChargeRules) {
        this.ChargeRules = ChargeRules;
    }

    public String getCreditService() {
        return CreditService;
    }

    public void setCreditService(String CreditService) {
        this.CreditService = CreditService;
    }
}
