package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：TrustRecordBean
 * 描述：
 * 创建时间：2018-08-30  10:24
 */

public class TrustRecordBean {


    /**
     * ErrMsg :
     * IsShowJbServices : 0
     * ReObj : [{"DateTime":"2018-08-29","LinkMan":"末夏","Quota":"100000.00","State":"授信使用中","TimeLimit":"2018年08月29日 至 2019年08月29日"}]
     * Success : 1
     */

    private String ErrMsg;
    private int IsShowJbServices;
    private int Success;
    private List<ReObjBean> ReObj;

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String ErrMsg) {
        this.ErrMsg = ErrMsg;
    }

    public int getIsShowJbServices() {
        return IsShowJbServices;
    }

    public void setIsShowJbServices(int IsShowJbServices) {
        this.IsShowJbServices = IsShowJbServices;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int Success) {
        this.Success = Success;
    }

    public List<ReObjBean> getReObj() {
        return ReObj;
    }

    public void setReObj(List<ReObjBean> ReObj) {
        this.ReObj = ReObj;
    }

    public static class ReObjBean {
        /**
         * DateTime : 2018-08-29
         * LinkMan : 末夏
         * Quota : 100000.00
         * State : 授信使用中
         * TimeLimit : 2018年08月29日 至 2019年08月29日
         */

        private String DateTime;
        private String LinkMan;
        private String Quota;
        private String State;
        private String TimeLimit;

        public String getDateTime() {
            return DateTime;
        }

        public void setDateTime(String DateTime) {
            this.DateTime = DateTime;
        }

        public String getLinkMan() {
            return LinkMan;
        }

        public void setLinkMan(String LinkMan) {
            this.LinkMan = LinkMan;
        }

        public String getQuota() {
            return Quota;
        }

        public void setQuota(String Quota) {
            this.Quota = Quota;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }

        public String getTimeLimit() {
            return TimeLimit;
        }

        public void setTimeLimit(String TimeLimit) {
            this.TimeLimit = TimeLimit;
        }
    }
}
