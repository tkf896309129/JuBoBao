package com.example.huoshangkou.jubowan.bean;

import java.io.Serializable;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：WalletListBean
 * 描述：
 * 创建时间：2017-10-19  14:15
 */

public class WalletListBean implements Serializable {

    private String AddorSub;
    private String CreateTime;
    private String BankNo;
    private String LuckyMoney;
    private String RealName;
    private String Tel;

    public String getBankNo() {
        return BankNo;
    }

    public void setBankNo(String bankNo) {
        BankNo = bankNo;
    }

    public String getAddorSub() {
        return AddorSub;
    }

    public void setAddorSub(String addorSub) {
        AddorSub = addorSub;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getLuckyMoney() {
        return LuckyMoney;
    }

    public void setLuckyMoney(String luckyMoney) {
        LuckyMoney = luckyMoney;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }
}
