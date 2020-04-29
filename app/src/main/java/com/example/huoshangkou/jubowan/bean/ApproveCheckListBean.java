package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：ApproveCheckListBean
 * 描述：
 * 创建时间：2017-03-20  13:22
 */

public class ApproveCheckListBean {
    private int AdCounts;
    private int ApprovalID;
    private int BorrowID;
    private int ApprovalOver;
    private String CreateTime;
    private String SellUserID;
    private int StatusID;
    private int IsDelet;
    private String ContractNO;
    private String RoleName;
    private String Loan;
    private String TypeName;
    private String UserName;
    private String Remark;
    private int ApprovalTypeID;
    private int ApprovalPeopleType;

    public int getIsDelet() {
        return IsDelet;
    }

    public void setIsDelet(int isDelet) {
        IsDelet = isDelet;
    }

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

    public int getApprovalID() {
        return ApprovalID;
    }

    public void setApprovalID(int approvalID) {
        ApprovalID = approvalID;
    }

    public int getApprovalTypeID() {
        return ApprovalTypeID;
    }

    public void setApprovalTypeID(int approvalTypeID) {
        ApprovalTypeID = approvalTypeID;
    }

    public int getApprovalOver() {
        return ApprovalOver;
    }

    public void setApprovalOver(int approvalOver) {
        ApprovalOver = approvalOver;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
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
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
