package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：LibraryListBean
 * 描述：
 * 创建时间：2019-04-09  13:57
 */

public class LibraryListBean {


    /**
     * d : {"ErrorCode":0,"Msg":"","Result":[{"__type":"Model.ViewModel.AtjuboApp.JuboBook","ID":1,"Title":"玻璃","Img":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190402/20190402174255_6544.jpg","Author":"穆连奎","ContentTxt":"                            <strong>11121212<\/strong>\n                        ","CreateTime":"/Date(1554196511473)/","IsUpper":false,"Type":3,"BriefIntroduction":"这是一本神奇的书"},{"__type":"Model.ViewModel.AtjuboApp.JuboBook","ID":2,"Title":"原片","Img":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190402/20190402174746_4514.jpg","Author":"於凡","ContentTxt":"                            <strong>1111213131111<\/strong>\n                        ","CreateTime":"/Date(1554198487803)/","IsUpper":true,"Type":3,"BriefIntroduction":"23333333333333"}]}
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
         * Result : [{"__type":"Model.ViewModel.AtjuboApp.JuboBook","ID":1,"Title":"玻璃","Img":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190402/20190402174255_6544.jpg","Author":"穆连奎","ContentTxt":"                            <strong>11121212<\/strong>\n                        ","CreateTime":"/Date(1554196511473)/","IsUpper":false,"Type":3,"BriefIntroduction":"这是一本神奇的书"},{"__type":"Model.ViewModel.AtjuboApp.JuboBook","ID":2,"Title":"原片","Img":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190402/20190402174746_4514.jpg","Author":"於凡","ContentTxt":"                            <strong>1111213131111<\/strong>\n                        ","CreateTime":"/Date(1554198487803)/","IsUpper":true,"Type":3,"BriefIntroduction":"23333333333333"}]
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
             * __type : Model.ViewModel.AtjuboApp.JuboBook
             * ID : 1
             * Title : 玻璃
             * Img : http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190402/20190402174255_6544.jpg
             * Author : 穆连奎
             * ContentTxt :                             <strong>11121212</strong>

             * CreateTime : /Date(1554196511473)/
             * IsUpper : false
             * Type : 3
             * BriefIntroduction : 这是一本神奇的书
             */

            private String __type;
            private int ID;
            private String Title;
            private String Img;
            private String Author;
            private String ContentTxt;
            private String CreateTime;
            private boolean IsUpper;
            private int Type;
            private String BriefIntroduction;

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
            }

            public String getTitle() {
                return Title;
            }

            public void setTitle(String Title) {
                this.Title = Title;
            }

            public String getImg() {
                return Img;
            }

            public void setImg(String Img) {
                this.Img = Img;
            }

            public String getAuthor() {
                return Author;
            }

            public void setAuthor(String Author) {
                this.Author = Author;
            }

            public String getContentTxt() {
                return ContentTxt;
            }

            public void setContentTxt(String ContentTxt) {
                this.ContentTxt = ContentTxt;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public boolean isIsUpper() {
                return IsUpper;
            }

            public void setIsUpper(boolean IsUpper) {
                this.IsUpper = IsUpper;
            }

            public int getType() {
                return Type;
            }

            public void setType(int Type) {
                this.Type = Type;
            }

            public String getBriefIntroduction() {
                return BriefIntroduction;
            }

            public void setBriefIntroduction(String BriefIntroduction) {
                this.BriefIntroduction = BriefIntroduction;
            }
        }
    }
}
