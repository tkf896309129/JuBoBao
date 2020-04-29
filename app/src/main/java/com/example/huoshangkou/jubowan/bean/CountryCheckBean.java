package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：CountryCheckBean
 * 描述：
 * 创建时间：2019-05-05  13:10
 */

public class CountryCheckBean {


    /**
     * d : {"ErrorCode":0,"Msg":"","Result":{"__type":"Model.ViewModel.AtjuboApp.JuboCTC","child":[{"ChildTitle":"代码查询","ChildDescribe":"全国认证认可信息 公共服务平台","ChildUrl":""},{"ChildTitle":"玻璃检测查询","ChildDescribe":"公正准确务实求新","ChildUrl":""}],"Title":"中国建材领域权威检测机构，国家 玻璃质量监督检验中心","TitleEn":"Authoritative Inspection Institutions in the Field of BuildingMaterials in China, National Glass Quality Supervision and Inspection Center","Img":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/Test/image/20190505/20190505113056_5910.png"}}
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
         * Result : {"__type":"Model.ViewModel.AtjuboApp.JuboCTC","child":[{"ChildTitle":"代码查询","ChildDescribe":"全国认证认可信息 公共服务平台","ChildUrl":""},{"ChildTitle":"玻璃检测查询","ChildDescribe":"公正准确务实求新","ChildUrl":""}],"Title":"中国建材领域权威检测机构，国家 玻璃质量监督检验中心","TitleEn":"Authoritative Inspection Institutions in the Field of BuildingMaterials in China, National Glass Quality Supervision and Inspection Center","Img":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/Test/image/20190505/20190505113056_5910.png"}
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
             * __type : Model.ViewModel.AtjuboApp.JuboCTC
             * child : [{"ChildTitle":"代码查询","ChildDescribe":"全国认证认可信息 公共服务平台","ChildUrl":""},{"ChildTitle":"玻璃检测查询","ChildDescribe":"公正准确务实求新","ChildUrl":""}]
             * Title : 中国建材领域权威检测机构，国家 玻璃质量监督检验中心
             * TitleEn : Authoritative Inspection Institutions in the Field of BuildingMaterials in China, National Glass Quality Supervision and Inspection Center
             * Img : http://atjubo.oss-cn-hangzhou.aliyuncs.com/Test/image/20190505/20190505113056_5910.png
             */

            private String __type;
            private String Title;
            private String TitleEn;
            private String Img;
            private List<ChildBean> child;

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public String getTitle() {
                return Title;
            }

            public void setTitle(String Title) {
                this.Title = Title;
            }

            public String getTitleEn() {
                return TitleEn;
            }

            public void setTitleEn(String TitleEn) {
                this.TitleEn = TitleEn;
            }

            public String getImg() {
                return Img;
            }

            public void setImg(String Img) {
                this.Img = Img;
            }

            public List<ChildBean> getChild() {
                return child;
            }

            public void setChild(List<ChildBean> child) {
                this.child = child;
            }

            public static class ChildBean {
                /**
                 * ChildTitle : 代码查询
                 * ChildDescribe : 全国认证认可信息 公共服务平台
                 * ChildUrl :
                 */

                private String ChildTitle;
                private String ChildDescribe;
                private String ChildUrl;
                private int ChildType;

                public int getChildType() {
                    return ChildType;
                }

                public void setChildType(int childType) {
                    ChildType = childType;
                }

                public String getChildTitle() {
                    return ChildTitle;
                }

                public void setChildTitle(String ChildTitle) {
                    this.ChildTitle = ChildTitle;
                }

                public String getChildDescribe() {
                    return ChildDescribe;
                }

                public void setChildDescribe(String ChildDescribe) {
                    this.ChildDescribe = ChildDescribe;
                }

                public String getChildUrl() {
                    return ChildUrl;
                }

                public void setChildUrl(String ChildUrl) {
                    this.ChildUrl = ChildUrl;
                }
            }
        }
    }
}
