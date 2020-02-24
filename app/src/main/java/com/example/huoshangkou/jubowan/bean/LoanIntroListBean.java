package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：LoanIntroListBean
 * 描述：
 * 创建时间：2017-09-19  09:41
 */

public class LoanIntroListBean {



    private String CreateTime;
    private int ID;
    private String InterestRate;
    private String LoanAmount;
    private String LoanTel;
    private String NO;
    private String ToState;

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getInterestRate() {
        return InterestRate;
    }

    public void setInterestRate(String interestRate) {
        InterestRate = interestRate;
    }

    public String getLoanAmount() {
        return LoanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        LoanAmount = loanAmount;
    }

    public String getLoanTel() {
        return LoanTel;
    }

    public void setLoanTel(String loanTel) {
        LoanTel = loanTel;
    }

    public String getNO() {
        return NO;
    }

    public void setNO(String NO) {
        this.NO = NO;
    }

    public String getToState() {
        return ToState;
    }

    public void setToState(String toState) {
        ToState = toState;
    }
}
