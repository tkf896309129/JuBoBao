package com.example.huoshangkou.jubowan.bean;

import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.TwoPointUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：ApproveDetailListBean
 * 描述：
 * 创建时间：2017-03-22  11:20
 */

public class ApproveDetailListBean implements Serializable {
    private String Address;
    private List<ApproveListBean> ApprovalMsgList;
    private int ApprovalState;
    private int ApprovalTypeID;
    private int AdCounts;
    private String OriginalTypePrice;
    private String FundWay;
    private String UpYuanpianID;
    private String UpYuanpian;
    private String Borrowers;
    private String Contact;
    private String Email;
    private String MoneyCurrency;
    private String CreateTime;
    private String EndTime;
    private String Endslot;
    private int ID;
    private int Invoice;
    private IousPayApprovalBean IousPayApproval;
    private IousUserApprovalBean IousUserApproval;
    private int IsSee_XiaoShou;
    private float Loan;
    private String LoanPeriod;
    private String LoanPurPose;
    private float OperatingTime;
    private String Pics;
    private String StartTime;
    private String Startslot;
    private String OrderTable;
    private String TEL;
    private String Time;
    private String TypeName;
    private double TypePrice;
    private int UserID;
    private String UserName;
    private String remark;
    private String TimeSpan;
    private String ContractNO;
    private String SellPrice;
    private String SellCashOrAccept;
    private String Advance;
    private String AdvanceOrderID;
    private String SellUserID;
    private String SpBankID;
    private String PurchasePrice;
    private String PurchaseCashOrAccept;
    private String RemitDircetion;
    private RzDueBean RzDueBill;
    private String RemitAccount;
    private String ProceedsAccount;
    private String Profit;
    private String FreightTotalPrice;
    private int StatusID;
    private String RuZhangCompany;
    private String RuKuanWay;
    private String RuZhangPrice;
    private String RuZhangXingZhi;
    private String KuanXiangWay;
    private String AccountType;
    private String IsDaChu;
    private String YYZX;

    public IousUserApprovalBean getIousUserApproval() {
        return IousUserApproval;
    }

    public void setIousUserApproval(IousUserApprovalBean iousUserApproval) {
        IousUserApproval = iousUserApproval;
    }

    public IousPayApprovalBean getIousPayApproval() {
        return IousPayApproval;
    }

    public void setIousPayApproval(IousPayApprovalBean iousPayApproval) {
        IousPayApproval = iousPayApproval;
    }

    public String getRuZhangCompany() {
        return RuZhangCompany;
    }

    public void setRuZhangCompany(String ruZhangCompany) {
        RuZhangCompany = ruZhangCompany;
    }

    public String getRuKuanWay() {
        return RuKuanWay;
    }

    public void setRuKuanWay(String ruKuanWay) {
        RuKuanWay = ruKuanWay;
    }

    public String getRuZhangPrice() {
        return RuZhangPrice;
    }

    public void setRuZhangPrice(String ruZhangPrice) {
        RuZhangPrice = ruZhangPrice;
    }

    public String getRuZhangXingZhi() {
        return RuZhangXingZhi;
    }

    public void setRuZhangXingZhi(String ruZhangXingZhi) {
        RuZhangXingZhi = ruZhangXingZhi;
    }

    public String getKuanXiangWay() {
        return KuanXiangWay;
    }

    public void setKuanXiangWay(String kuanXiangWay) {
        KuanXiangWay = kuanXiangWay;
    }

    public String getAccountType() {
        return AccountType;
    }

    public void setAccountType(String accountType) {
        AccountType = accountType;
    }

    public String getIsDaChu() {
        return IsDaChu;
    }

    public void setIsDaChu(String isDaChu) {
        IsDaChu = isDaChu;
    }

    public String getYYZX() {
        return YYZX;
    }

    public void setYYZX(String YYZX) {
        this.YYZX = YYZX;
    }

    public int getStatusID() {
        return StatusID;
    }

    public void setStatusID(int statusID) {
        StatusID = statusID;
    }

    public int getIsSee_XiaoShou() {
        return IsSee_XiaoShou;
    }

    public void setIsSee_XiaoShou(int isSee_XiaoShou) {
        IsSee_XiaoShou = isSee_XiaoShou;
    }

    public RzDueBean getRzDueBill() {
        return RzDueBill;
    }

    public void setRzDueBill(RzDueBean rzDueBill) {
        RzDueBill = rzDueBill;
    }

    public String getMoneyCurrency() {
        return MoneyCurrency;
    }

    public void setMoneyCurrency(String moneyCurrency) {
        MoneyCurrency = moneyCurrency;
    }

    public String getUpYuanpianID() {
        return UpYuanpianID;
    }

    public void setUpYuanpianID(String upYuanpianID) {
        UpYuanpianID = upYuanpianID;
    }

    public String getSpBankID() {
        return SpBankID;
    }

    public void setSpBankID(String spBankID) {
        SpBankID = spBankID;
    }

    public String getFundWay() {
        return FundWay;
    }

    public void setFundWay(String fundWay) {
        FundWay = fundWay;
    }

    public String getUpYuanpian() {
        return UpYuanpian;
    }

    public void setUpYuanpian(String upYuanpian) {
        UpYuanpian = upYuanpian;
    }

    public void setTypePrice(double typePrice) {
        TypePrice = typePrice;
    }

    public String getContractNO() {
        return ContractNO;
    }

    public void setContractNO(String contractNO) {
        ContractNO = contractNO;
    }

    public int getAdCounts() {
        return AdCounts;
    }

    public void setAdCounts(int adCounts) {
        AdCounts = adCounts;
    }

    public String getOriginalTypePrice() {
        return OriginalTypePrice;
    }

    public void setOriginalTypePrice(String originalTypePrice) {
        OriginalTypePrice = originalTypePrice;
    }

    public String getAdvanceOrderID() {
        return AdvanceOrderID;
    }

    public void setAdvanceOrderID(String advanceOrderID) {
        AdvanceOrderID = advanceOrderID;
    }

    public String getSellUserID() {
        return SellUserID;
    }

    public void setSellUserID(String sellUserID) {
        SellUserID = sellUserID;
    }

    public String getSellPrice() {
        return SellPrice;
    }

    public void setSellPrice(String sellPrice) {
        SellPrice = sellPrice;
    }

    public String getSellCashOrAccept() {
        return SellCashOrAccept;
    }

    public void setSellCashOrAccept(String sellCashOrAccept) {
        SellCashOrAccept = sellCashOrAccept;
    }

    public String getAdvance() {
        return Advance;
    }

    public void setAdvance(String advance) {
        Advance = advance;
    }

    public String getPurchasePrice() {
        return PurchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        PurchasePrice = purchasePrice;
    }

    public String getPurchaseCashOrAccept() {
        return PurchaseCashOrAccept;
    }

    public void setPurchaseCashOrAccept(String purchaseCashOrAccept) {
        PurchaseCashOrAccept = purchaseCashOrAccept;
    }

    public String getRemitDircetion() {
        return RemitDircetion;
    }

    public void setRemitDircetion(String remitDircetion) {
        RemitDircetion = remitDircetion;
    }

    public String getRemitAccount() {
        return RemitAccount;
    }

    public void setRemitAccount(String remitAccount) {
        RemitAccount = remitAccount;
    }

    public String getProceedsAccount() {
        return ProceedsAccount;
    }

    public void setProceedsAccount(String proceedsAccount) {
        ProceedsAccount = proceedsAccount;
    }

    public String getProfit() {
        return Profit;
    }

    public void setProfit(String profit) {
        Profit = profit;
    }

    public String getFreightTotalPrice() {
        return FreightTotalPrice;
    }

    public void setFreightTotalPrice(String freightTotalPrice) {
        FreightTotalPrice = freightTotalPrice;
    }

    public String getTEL() {
        return TEL;
    }

    public void setTEL(String TEL) {
        this.TEL = TEL;
    }

    public String getBorrowers() {
        return Borrowers;
    }

    public void setBorrowers(String borrowers) {
        Borrowers = borrowers;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public double getLoan() {
        return Loan;
    }


    public void setLoan(float loan) {
        Loan = loan;
    }

    public void setOperatingTime(float operatingTime) {
        OperatingTime = operatingTime;
    }

    public void setTypePrice(float typePrice) {
        TypePrice = typePrice;
    }

    public String getLoanPeriod() {
        return LoanPeriod;
    }

    public void setLoanPeriod(String loanPeriod) {
        LoanPeriod = loanPeriod;
    }

    public String getLoanPurPose() {
        return LoanPurPose;
    }

    public void setLoanPurPose(String loanPurPose) {
        LoanPurPose = loanPurPose;
    }

    public double getOperatingTime() {
        return OperatingTime;
    }


    public String getTimeSpan() {
        return TimeSpan;
    }

    public void setTimeSpan(String timeSpan) {
        TimeSpan = timeSpan;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public List<ApproveListBean> getApprovalMsgList() {
        return ApprovalMsgList;
    }

    public void setApprovalMsgList(List<ApproveListBean> approvalMsgList) {
        ApprovalMsgList = approvalMsgList;
    }

    public int getApprovalState() {
        return ApprovalState;
    }

    public void setApprovalState(int approvalState) {
        ApprovalState = approvalState;
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

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getEndslot() {
        return Endslot;
    }

    public void setEndslot(String endslot) {
        Endslot = endslot;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getInvoice() {
        return Invoice;
    }

    public void setInvoice(int invoice) {
        Invoice = invoice;
    }

    public String getPics() {
        return Pics;
    }

    public void setPics(String pics) {
        Pics = pics;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getStartslot() {
        return Startslot;
    }

    public void setStartslot(String startslot) {
        Startslot = startslot;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public double getTypePrice() {
        LogUtils.i(TypePrice + "");
        return Double.parseDouble(TwoPointUtils.getInstance().getTwoPoint(TypePrice));
    }


    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
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

    public String getOrderTable() {
        return OrderTable;
    }

    public void setOrderTable(String orderTable) {
        OrderTable = orderTable;
    }
}
