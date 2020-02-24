package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：CheckSignListBean
 * 描述：
 * 创建时间：2017-08-02  10:22
 */

public class CheckSignListBean {

    private String ApprovalName;
    private int ApprovalOver;
    private String CreateTime;
    private int ID;
    private String LeakTime;
    private String LinkMan;
    private String Pic;
    private String Remark;
    private String TimeSpan;
    private boolean isNeedCheck = false;

    public boolean isNeedCheck() {
        return isNeedCheck;
    }

    public void setNeedCheck(boolean needCheck) {
        isNeedCheck = needCheck;
    }

    public String getApprovalName() {
        return ApprovalName;
    }

    public void setApprovalName(String approvalName) {
        ApprovalName = approvalName;
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLeakTime() {
        return LeakTime;
    }

    public void setLeakTime(String leakTime) {
        LeakTime = leakTime;
    }

    public String getLinkMan() {
        return LinkMan;
    }

    public void setLinkMan(String linkMan) {
        LinkMan = linkMan;
    }

    public String getPic() {
        return Pic;
    }

    public void setPic(String pic) {
        Pic = pic;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getTimeSpan() {
        return TimeSpan;
    }

    public void setTimeSpan(String timeSpan) {
        TimeSpan = timeSpan;
    }
}
