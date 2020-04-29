package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：CheckSettinBean
 * 描述：
 * 创建时间：2019-07-03  14:25
 */

public class CheckSettinBean {


    /**
     * d : {"ReObj":{"__type":"AtJubo.Api.App.ReModel.JuboBao.UserPatternModel","UserID":10445,"PatternType":1,"StartTime":"2019-07-03 13:35","EndTime":"2019-07-03 16:35"},"Success":1,"ErrMsg":""}
     */

    private DBean d;

    public DBean getD() {
        return d;
    }

    public void setD(DBean d) {
        this.d = d;
    }

    public static class DBean {
        /**
         * ReObj : {"__type":"AtJubo.Api.App.ReModel.JuboBao.UserPatternModel","UserID":10445,"PatternType":1,"StartTime":"2019-07-03 13:35","EndTime":"2019-07-03 16:35"}
         * Success : 1
         * ErrMsg :
         */

        private ReObjBean ReObj;
        private int Success;
        private String ErrMsg;

        public ReObjBean getReObj() {
            return ReObj;
        }

        public void setReObj(ReObjBean ReObj) {
            this.ReObj = ReObj;
        }

        public int getSuccess() {
            return Success;
        }

        public void setSuccess(int Success) {
            this.Success = Success;
        }

        public String getErrMsg() {
            return ErrMsg;
        }

        public void setErrMsg(String ErrMsg) {
            this.ErrMsg = ErrMsg;
        }

        public static class ReObjBean {
            /**
             * __type : AtJubo.Api.App.ReModel.JuboBao.UserPatternModel
             * UserID : 10445
             * PatternType : 1
             * StartTime : 2019-07-03 13:35
             * EndTime : 2019-07-03 16:35
             */

            private String __type;
            private int UserID;
            private int PatternType;
            private String StartTime;
            private String EndTime;
            private String TimeSpan;


            public String getTimeSpan() {
                return TimeSpan;
            }

            public void setTimeSpan(String timeSpan) {
                TimeSpan = timeSpan;
            }

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public int getUserID() {
                return UserID;
            }

            public void setUserID(int UserID) {
                this.UserID = UserID;
            }

            public int getPatternType() {
                return PatternType;
            }

            public void setPatternType(int PatternType) {
                this.PatternType = PatternType;
            }

            public String getStartTime() {
                return StartTime;
            }

            public void setStartTime(String StartTime) {
                this.StartTime = StartTime;
            }

            public String getEndTime() {
                return EndTime;
            }

            public void setEndTime(String EndTime) {
                this.EndTime = EndTime;
            }
        }
    }
}
