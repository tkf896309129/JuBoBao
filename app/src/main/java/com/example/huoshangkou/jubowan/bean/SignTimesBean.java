package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：SignTimesBean
 * 描述：
 * 创建时间：2017-05-18  11:44
 */

public class SignTimesBean {


    /**
     * Administrator : 0
     * ApprovalUserID : 8446
     * ApprovalUserIDName : 胡震宇
     * Counts : 1
     * ErrMsg :
     * IsMatch : 1
     * Success : 1
     * userTrack : [{"Address":"中国浙江省杭州市萧山区建设一路","CreateTime":"2019/7/10 10:22:04","PoiName":"中栋国际-1期","remark":"测试"}]
     */

    private int Administrator;
    private int ApprovalUserID;
    private String ApprovalUserIDName;
    private int Counts;
    private String ErrMsg;
    private int IsMatch;
    private int Success;
    private List<UserTrackBean> userTrack;

    public int getAdministrator() {
        return Administrator;
    }

    public void setAdministrator(int Administrator) {
        this.Administrator = Administrator;
    }

    public int getApprovalUserID() {
        return ApprovalUserID;
    }

    public void setApprovalUserID(int ApprovalUserID) {
        this.ApprovalUserID = ApprovalUserID;
    }

    public String getApprovalUserIDName() {
        return ApprovalUserIDName;
    }

    public void setApprovalUserIDName(String ApprovalUserIDName) {
        this.ApprovalUserIDName = ApprovalUserIDName;
    }

    public int getCounts() {
        return Counts;
    }

    public void setCounts(int Counts) {
        this.Counts = Counts;
    }

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String ErrMsg) {
        this.ErrMsg = ErrMsg;
    }

    public int getIsMatch() {
        return IsMatch;
    }

    public void setIsMatch(int IsMatch) {
        this.IsMatch = IsMatch;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int Success) {
        this.Success = Success;
    }

    public List<UserTrackBean> getUserTrack() {
        return userTrack;
    }

    public void setUserTrack(List<UserTrackBean> userTrack) {
        this.userTrack = userTrack;
    }

    public static class UserTrackBean {
        /**
         * Address : 中国浙江省杭州市萧山区建设一路
         * CreateTime : 2019/7/10 10:22:04
         * PoiName : 中栋国际-1期
         * remark : 测试
         */

        private String Address;
        private String CreateTime;
        private String PoiName;
        private String remark;

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getPoiName() {
            return PoiName;
        }

        public void setPoiName(String PoiName) {
            this.PoiName = PoiName;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
