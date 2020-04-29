package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：DatePlanBean
 * 描述：
 * 创建时间：2019-09-02  16:24
 */

public class DatePlanBean {
    /**
     * d : {"ErrorCode":0,"Msg":"","Result":[{"__type":"AtJubo.Api.App.ReModel.JuboBao.ScheduleModel","id":3030,"Title":"吃饱了","Timeslot":"15:41-15:41","Type":2}]}
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
         * Result : [{"__type":"AtJubo.Api.App.ReModel.JuboBao.ScheduleModel","id":3030,"Title":"吃饱了","Timeslot":"15:41-15:41","Type":2}]
         */

        private int ErrorCode;
        private String Msg;
        private List<ResultBean> Result;

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

        public List<ResultBean> getResult() {
            return Result;
        }

        public void setResult(List<ResultBean> Result) {
            this.Result = Result;
        }

        public static class ResultBean {
            /**
             * __type : AtJubo.Api.App.ReModel.JuboBao.ScheduleModel
             * id : 3030
             * Title : 吃饱了
             * Timeslot : 15:41-15:41
             * Type : 2
             */

            private String __type;
            private int id;
            private String Title;
            private String Timeslot;
            private int Type;

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return Title;
            }

            public void setTitle(String Title) {
                this.Title = Title;
            }

            public String getTimeslot() {
                return Timeslot;
            }

            public void setTimeslot(String Timeslot) {
                this.Timeslot = Timeslot;
            }

            public int getType() {
                return Type;
            }

            public void setType(int Type) {
                this.Type = Type;
            }
        }
    }
}
