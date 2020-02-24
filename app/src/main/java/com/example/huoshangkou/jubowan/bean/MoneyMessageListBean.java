package com.example.huoshangkou.jubowan.bean;

import java.io.Serializable;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：MoneyMessageListBean
 * 描述：
 * 创建时间：2017-12-15  10:54
 */

public class MoneyMessageListBean implements Serializable{

    private String AuditTimeYea;

    private String Company;

    private String RzMoey;

    private String AccountName;
    private String AccountNumber;
    private int Balance;
    private String ContractNO;
    private String CreateTime;
    private int CreditLimit;
    private int ID;
    private String InterestRate;
    private int LoanAmount;
    private String LoanLinkMan;
    private String LoanPeriod;
    private String LoanTel;
    private String LoginName;
    private String OpeningBank;
    private String Orderid;
    private String UsageOfLoan;
    private String RzState;

    private String Address;
    private String AuditTimeYear;
    private String LinkMan;
    private String Tel;

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getAuditTimeYear() {
        return AuditTimeYear;
    }

    public void setAuditTimeYear(String auditTimeYear) {
        AuditTimeYear = auditTimeYear;
    }

    public String getLinkMan() {
        return LinkMan;
    }

    public void setLinkMan(String linkMan) {
        LinkMan = linkMan;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        AccountName = accountName;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    public int getBalance() {
        return Balance;
    }

    public void setBalance(int balance) {
        Balance = balance;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public int getCreditLimit() {
        return CreditLimit;
    }

    public void setCreditLimit(int creditLimit) {
        CreditLimit = creditLimit;
    }

    public String getInterestRate() {
        return InterestRate;
    }

    public void setInterestRate(String interestRate) {
        InterestRate = interestRate;
    }

    public int getLoanAmount() {
        return LoanAmount;
    }

    public void setLoanAmount(int loanAmount) {
        LoanAmount = loanAmount;
    }

    public String getLoanLinkMan() {
        return LoanLinkMan;
    }

    public void setLoanLinkMan(String loanLinkMan) {
        LoanLinkMan = loanLinkMan;
    }

    public String getLoanPeriod() {
        return LoanPeriod;
    }

    public void setLoanPeriod(String loanPeriod) {
        LoanPeriod = loanPeriod;
    }

    public String getLoanTel() {
        return LoanTel;
    }

    public void setLoanTel(String loanTel) {
        LoanTel = loanTel;
    }

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String loginName) {
        LoginName = loginName;
    }

    public String getOpeningBank() {
        return OpeningBank;
    }

    public void setOpeningBank(String openingBank) {
        OpeningBank = openingBank;
    }

    public String getOrderid() {
        return Orderid;
    }

    public void setOrderid(String orderid) {
        Orderid = orderid;
    }

    public String getUsageOfLoan() {
        return UsageOfLoan;
    }

    public void setUsageOfLoan(String usageOfLoan) {
        UsageOfLoan = usageOfLoan;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

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
