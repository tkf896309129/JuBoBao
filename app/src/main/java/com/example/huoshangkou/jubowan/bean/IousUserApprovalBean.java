package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：IousUserApprovalBean
 * 描述：
 * 创建时间：2018-09-07  18:18
 */

public class IousUserApprovalBean {
    /**
     * Address : 浙江省杭州市萧山区
     * ApplyUnit : 浙江火山口网络科技有限公司
     * IdCard : 330726199604111510
     * LegalPerson : 胡震宇
     * LinkMan : 王浩
     * PersonnelSituation : 😁
     * Scale : 嘻嘻
     */

    private String Address;
    private String ApplyUnit;
    private String IdCard;
    private String LegalPerson;
    private String LinkMan;
    private String PersonnelSituation;
    private String Scale;
    private String IousAmount;
    private String IsEditAmount;

    public String getIousAmount() {
        return IousAmount;
    }

    public void setIousAmount(String iousAmount) {
        IousAmount = iousAmount;
    }

    public String getIsEditAmount() {
        return IsEditAmount;
    }

    public void setIsEditAmount(String isEditAmount) {
        IsEditAmount = isEditAmount;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getApplyUnit() {
        return ApplyUnit;
    }

    public void setApplyUnit(String ApplyUnit) {
        this.ApplyUnit = ApplyUnit;
    }

    public String getIdCard() {
        return IdCard;
    }

    public void setIdCard(String IdCard) {
        this.IdCard = IdCard;
    }

    public String getLegalPerson() {
        return LegalPerson;
    }

    public void setLegalPerson(String LegalPerson) {
        this.LegalPerson = LegalPerson;
    }

    public String getLinkMan() {
        return LinkMan;
    }

    public void setLinkMan(String LinkMan) {
        this.LinkMan = LinkMan;
    }

    public String getPersonnelSituation() {
        return PersonnelSituation;
    }

    public void setPersonnelSituation(String PersonnelSituation) {
        this.PersonnelSituation = PersonnelSituation;
    }

    public String getScale() {
        return Scale;
    }

    public void setScale(String Scale) {
        this.Scale = Scale;
    }
}
