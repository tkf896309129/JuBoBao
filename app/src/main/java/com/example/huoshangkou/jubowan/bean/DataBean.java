package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：DataBean
 * 描述：
 * 创建时间：2019-09-04  16:04
 */

public class DataBean {


    /**
     * d : {"ErrorCode":0,"Msg":"","Result":{"__type":"AtJubo.Api.App.ReModel.JuboBao.SchedulEdetailModel","Id":3031,"UserId":5275,"Title":"下班","CreateTime":"/Date(1567416861700)/","StartDate":"/Date(1567503180000)/","Frequency":0,"ReminderType":60,"Reminder":"1小时前","EndDate":"/Date(1567503180000)/","RepeateType":3,"Repeate":"每月","EndRepeateType":1,"EndRepeate":"时间","EndRepeateDate":null,"EndRepeateCount":null,"ScheduleLevel":0,"Remark":""}}
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
         * ErrorCode : 0
         * Msg :
         * Result : {"__type":"AtJubo.Api.App.ReModel.JuboBao.SchedulEdetailModel","Id":3031,"UserId":5275,"Title":"下班","CreateTime":"/Date(1567416861700)/","StartDate":"/Date(1567503180000)/","Frequency":0,"ReminderType":60,"Reminder":"1小时前","EndDate":"/Date(1567503180000)/","RepeateType":3,"Repeate":"每月","EndRepeateType":1,"EndRepeate":"时间","EndRepeateDate":null,"EndRepeateCount":null,"ScheduleLevel":0,"Remark":""}
         */

        private int ErrorCode;
        private String Msg;
        private ResultBean Result;

        public int getErrorCode() {
            return ErrorCode;
        }

        public void setErrorCode(int ErrorCode) {
            this.ErrorCode = ErrorCode;
        }

        public String getMsg() {
            return Msg;
        }

        public void setMsg(String Msg) {
            this.Msg = Msg;
        }

        public ResultBean getResult() {
            return Result;
        }

        public void setResult(ResultBean Result) {
            this.Result = Result;
        }

        public static class ResultBean {
            /**
             * __type : AtJubo.Api.App.ReModel.JuboBao.SchedulEdetailModel
             * Id : 3031
             * UserId : 5275
             * Title : 下班
             * CreateTime : /Date(1567416861700)/
             * StartDate : /Date(1567503180000)/
             * Frequency : 0
             * ReminderType : 60
             * Reminder : 1小时前
             * EndDate : /Date(1567503180000)/
             * RepeateType : 3
             * Repeate : 每月
             * EndRepeateType : 1
             * EndRepeate : 时间
             * EndRepeateDate : null
             * EndRepeateCount : null
             * ScheduleLevel : 0
             * Remark :
             */

            private String __type;
            private int Id;
            private int UserId;
            private String Title;
            private String CreateTime;
            private String StartDate;
            private int Frequency;
            private int ReminderType;
            private String Reminder;
            private String EndDate;
            private int RepeateType;
            private String Repeate;
            private int EndRepeateType;
            private String EndRepeate;
            private Object EndRepeateDate;
            private Object EndRepeateCount;
            private int ScheduleLevel;
            private String Remark;

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public int getUserId() {
                return UserId;
            }

            public void setUserId(int UserId) {
                this.UserId = UserId;
            }

            public String getTitle() {
                return Title;
            }

            public void setTitle(String Title) {
                this.Title = Title;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public String getStartDate() {
                return StartDate;
            }

            public void setStartDate(String StartDate) {
                this.StartDate = StartDate;
            }

            public int getFrequency() {
                return Frequency;
            }

            public void setFrequency(int Frequency) {
                this.Frequency = Frequency;
            }

            public int getReminderType() {
                return ReminderType;
            }

            public void setReminderType(int ReminderType) {
                this.ReminderType = ReminderType;
            }

            public String getReminder() {
                return Reminder;
            }

            public void setReminder(String Reminder) {
                this.Reminder = Reminder;
            }

            public String getEndDate() {
                return EndDate;
            }

            public void setEndDate(String EndDate) {
                this.EndDate = EndDate;
            }

            public int getRepeateType() {
                return RepeateType;
            }

            public void setRepeateType(int RepeateType) {
                this.RepeateType = RepeateType;
            }

            public String getRepeate() {
                return Repeate;
            }

            public void setRepeate(String Repeate) {
                this.Repeate = Repeate;
            }

            public int getEndRepeateType() {
                return EndRepeateType;
            }

            public void setEndRepeateType(int EndRepeateType) {
                this.EndRepeateType = EndRepeateType;
            }

            public String getEndRepeate() {
                return EndRepeate;
            }

            public void setEndRepeate(String EndRepeate) {
                this.EndRepeate = EndRepeate;
            }

            public Object getEndRepeateDate() {
                return EndRepeateDate;
            }

            public void setEndRepeateDate(Object EndRepeateDate) {
                this.EndRepeateDate = EndRepeateDate;
            }

            public Object getEndRepeateCount() {
                return EndRepeateCount;
            }

            public void setEndRepeateCount(Object EndRepeateCount) {
                this.EndRepeateCount = EndRepeateCount;
            }

            public int getScheduleLevel() {
                return ScheduleLevel;
            }

            public void setScheduleLevel(int ScheduleLevel) {
                this.ScheduleLevel = ScheduleLevel;
            }

            public String getRemark() {
                return Remark;
            }

            public void setRemark(String Remark) {
                this.Remark = Remark;
            }
        }
    }
}
