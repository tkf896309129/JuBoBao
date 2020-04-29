package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：GetApplyListBean
 * 描述：
 * 创建时间：2017-03-21  10:17
 */

public class GetApplyListBean {
    private int AdCounts;
    private int ApprovalID;
    private int BorrowID;
    private int ApprovalTypeID;
    private String CreateTime;
    private int Invoice;
    private String SellUserID;
    private int StatusID;
    private String ContractNO;
    private String Loan;
    private String RoleName;
    private int IsDelet;
    private String TypeName;
    private String UserName;
    private String remark;
    private int ApprovalOver;
    private int ApprovalPeopleType = -10;

    public int getApprovalPeopleType() {
        return ApprovalPeopleType;
    }

    public void setApprovalPeopleType(int approvalPeopleType) {
        ApprovalPeopleType = approvalPeopleType;
    }

    public int getBorrowID() {
        return BorrowID;
    }

    public void setBorrowID(int borrowID) {
        BorrowID = borrowID;
    }

    public int getStatusID() {
        return StatusID;
    }

    public void setStatusID(int statusID) {
        StatusID = statusID;
    }

    public String getContractNO() {
        return ContractNO;
    }

    public void setContractNO(String contractNO) {
        ContractNO = contractNO;
    }

    public String getLoan() {
        return Loan;
    }

    public void setLoan(String loan) {
        Loan = loan;
    }

    public int getAdCounts() {
        return AdCounts;
    }

    public void setAdCounts(int adCounts) {
        AdCounts = adCounts;
    }

    public String getSellUserID() {
        return SellUserID;
    }

    public void setSellUserID(String sellUserID) {
        SellUserID = sellUserID;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }

    public int getApprovalOver() {
        return ApprovalOver;
    }

    public void setApprovalOver(int approvalOver) {
        ApprovalOver = approvalOver;
    }

    public int getApprovalID() {
        return ApprovalID;
    }

    public void setApprovalID(int approvalID) {
        ApprovalID = approvalID;
    }

    public int getIsDelet() {
        return IsDelet;
    }

    public void setIsDelet(int isDelet) {
        IsDelet = isDelet;
    }

    public int getApprovalTypeID() {
        return ApprovalTypeID;
    }

    public void setApprovalTypeID(int approvalTypeID) {
        ApprovalTypeID = approvalTypeID;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public int getInvoice() {
        return Invoice;
    }

    public void setInvoice(int invoice) {
        Invoice = invoice;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
