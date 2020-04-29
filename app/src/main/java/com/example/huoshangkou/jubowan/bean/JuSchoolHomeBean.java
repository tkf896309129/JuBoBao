package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：JuSchoolHomeBean
 * 描述：
 * 创建时间：2019-04-04  16:49
 */

public class JuSchoolHomeBean {


    /**
     * d : {"ErrorCode":0,"Msg":"","Result":{"__type":"Atjubo.Areas.jbBaoApp.Models.JuboSchoolPageModel","Pics":["http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190403/20190403103833_6594.jpg","http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190409/20190409084554_7082.png","http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190409/20190409085333_8195.png"],"JuboSchoolPages":[{"Name":"学院介绍","SmallPic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190408/20190408132606_6556.png","Link":"1","Sort":"1"},{"Name":"招生计划","SmallPic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190408/20190408132616_4676.png","Link":"23333","Sort":"2"},{"Name":"学院风采","SmallPic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190408/20190408132854_7076.png","Link":"2121","Sort":"3"},{"Name":"其他","SmallPic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190409/20190409084420_7098.png","Link":"www.baidu.com","Sort":""}]}}
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
         * Result : {"__type":"Atjubo.Areas.jbBaoApp.Models.JuboSchoolPageModel","Pics":["http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190403/20190403103833_6594.jpg","http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190409/20190409084554_7082.png","http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190409/20190409085333_8195.png"],"JuboSchoolPages":[{"Name":"学院介绍","SmallPic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190408/20190408132606_6556.png","Link":"1","Sort":"1"},{"Name":"招生计划","SmallPic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190408/20190408132616_4676.png","Link":"23333","Sort":"2"},{"Name":"学院风采","SmallPic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190408/20190408132854_7076.png","Link":"2121","Sort":"3"},{"Name":"其他","SmallPic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190409/20190409084420_7098.png","Link":"www.baidu.com","Sort":""}]}
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
             * __type : Atjubo.Areas.jbBaoApp.Models.JuboSchoolPageModel
             * Pics : ["http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190403/20190403103833_6594.jpg","http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190409/20190409084554_7082.png","http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190409/20190409085333_8195.png"]
             * JuboSchoolPages : [{"Name":"学院介绍","SmallPic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190408/20190408132606_6556.png","Link":"1","Sort":"1"},{"Name":"招生计划","SmallPic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190408/20190408132616_4676.png","Link":"23333","Sort":"2"},{"Name":"学院风采","SmallPic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190408/20190408132854_7076.png","Link":"2121","Sort":"3"},{"Name":"其他","SmallPic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190409/20190409084420_7098.png","Link":"www.baidu.com","Sort":""}]
             */

            private String __type;
            private String CNTxt;
            private String ENTxt;
            private List<String> Pics;
            private List<JuboSchoolPagesBean> JuboSchoolPages;

            public String getCNTxt() {
                return CNTxt;
            }

            public void setCNTxt(String CNTxt) {
                this.CNTxt = CNTxt;
            }

            public String getENTxt() {
                return ENTxt;
            }

            public void setENTxt(String ENTxt) {
                this.ENTxt = ENTxt;
            }

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public List<String> getPics() {
                return Pics;
            }

            public void setPics(List<String> Pics) {
                this.Pics = Pics;
            }

            public List<JuboSchoolPagesBean> getJuboSchoolPages() {
                return JuboSchoolPages;
            }

            public void setJuboSchoolPages(List<JuboSchoolPagesBean> JuboSchoolPages) {
                this.JuboSchoolPages = JuboSchoolPages;
            }

            public static class JuboSchoolPagesBean {
                /**
                 * Name : 学院介绍
                 * SmallPic : http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190408/20190408132606_6556.png
                 * Link : 1
                 * Sort : 1
                 */

                private String Name;
                private String SmallPic;
                private String Link;
                private String Sort;

                public String getName() {
                    return Name;
                }

                public void setName(String Name) {
                    this.Name = Name;
                }

                public String getSmallPic() {
                    return SmallPic;
                }

                public void setSmallPic(String SmallPic) {
                    this.SmallPic = SmallPic;
                }

                public String getLink() {
                    return Link;
                }

                public void setLink(String Link) {
                    this.Link = Link;
                }

                public String getSort() {
                    return Sort;
                }

                public void setSort(String Sort) {
                    this.Sort = Sort;
                }
            }
        }
    }
}
