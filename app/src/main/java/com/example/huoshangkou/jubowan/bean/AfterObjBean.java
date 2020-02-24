package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：AfterObjBean
 * 描述：
 * 创建时间：2017-05-16  16:09
 */

public class AfterObjBean {
    private String CreateTime;
    private String FlOrderID;
    private String PcPayUrl;
    private String YPOrderID;

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getFlOrderID() {
        return FlOrderID;
    }

    public void setFlOrderID(String flOrderID) {
        FlOrderID = flOrderID;
    }

    public String getPcPayUrl() {
        return PcPayUrl;
    }

    public void setPcPayUrl(String pcPayUrl) {
        PcPayUrl = pcPayUrl;
    }

    public String getYPOrderID() {
        return YPOrderID;
    }

    public void setYPOrderID(String YPOrderID) {
        this.YPOrderID = YPOrderID;
    }
}
