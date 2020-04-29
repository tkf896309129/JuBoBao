package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：CountryCheckResultBean
 * 描述：
 * 创建时间：2019-05-07  15:01
 */

public class CountryCheckResultBean {


    /**
     * d : {"ErrorCode":0,"Msg":"","Result":{"url":"192.168.10.120/webapi/pages/WebApp/jbCtc/CtcReportQuery.html?unitName=都匀开发区福田化工有限责任公司&reportCode=WT-2010-Z50-0236","title":"检测报告查询"}}
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
         * Result : {"url":"192.168.10.120/webapi/pages/WebApp/jbCtc/CtcReportQuery.html?unitName=都匀开发区福田化工有限责任公司&reportCode=WT-2010-Z50-0236","title":"检测报告查询"}
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
             * url : 192.168.10.120/webapi/pages/WebApp/jbCtc/CtcReportQuery.html?unitName=都匀开发区福田化工有限责任公司&reportCode=WT-2010-Z50-0236
             * title : 检测报告查询
             */

            private String url;
            private String title;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
