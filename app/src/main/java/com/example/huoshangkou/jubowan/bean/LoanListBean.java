package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：LoanListBean
 * 描述：
 * 创建时间：2017-09-15  15:19
 */

public class LoanListBean {

    private String AuditTimeYea;
    private String ContractNO;
    private int ID;
    private String RzMoey;
    private String RzState;

    public String getAuditTimeYea() {
        return AuditTimeYea;
    }

    public void setAuditTimeYea(String auditTimeYea) {
        AuditTimeYea = auditTimeYea;
    }

    public String getContractNO() {
        return ContractNO;
    }

    public void setContractNO(String contractNO) {
        ContractNO = contractNO;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getRzMoey() {
        return RzMoey;
    }

    public void setRzMoey(String rzMoey) {
        RzMoey = rzMoey;
    }

    public String getRzState() {
        return RzState;
    }

    public void setRzState(String rzState) {
        RzState = rzState;
    }
}
