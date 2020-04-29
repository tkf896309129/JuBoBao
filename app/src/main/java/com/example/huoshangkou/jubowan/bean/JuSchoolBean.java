package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：JuSchoolBean
 * 描述：
 * 创建时间：2019-04-04  10:02
 */

public class JuSchoolBean {


    /**
     * d : {"ErrorCode":0,"Msg":"","Result":{"__type":"Model.ViewModel.AtjuboApp.GetBookTypeVM","Img":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190404/20190404134011_8826.jpg","EnTxt":"hsk","CNTxt":"火山口","BookType":[{"ID":3,"TypeName":"政策篇","TypeSort":1,"CreateTime":"/Date(1554185012517)/","Pic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190402/20190402140330_5454.jpg"},{"ID":4,"TypeName":"品牌展厅","TypeSort":2,"CreateTime":"/Date(1554185034827)/","Pic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190402/20190402140351_5344.jpg"}]}}
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
         * Result : {"__type":"Model.ViewModel.AtjuboApp.GetBookTypeVM","Img":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190404/20190404134011_8826.jpg","EnTxt":"hsk","CNTxt":"火山口","BookType":[{"ID":3,"TypeName":"政策篇","TypeSort":1,"CreateTime":"/Date(1554185012517)/","Pic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190402/20190402140330_5454.jpg"},{"ID":4,"TypeName":"品牌展厅","TypeSort":2,"CreateTime":"/Date(1554185034827)/","Pic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190402/20190402140351_5344.jpg"}]}
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
             * __type : Model.ViewModel.AtjuboApp.GetBookTypeVM
             * Img : http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190404/20190404134011_8826.jpg
             * EnTxt : hsk
             * CNTxt : 火山口
             * BookType : [{"ID":3,"TypeName":"政策篇","TypeSort":1,"CreateTime":"/Date(1554185012517)/","Pic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190402/20190402140330_5454.jpg"},{"ID":4,"TypeName":"品牌展厅","TypeSort":2,"CreateTime":"/Date(1554185034827)/","Pic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190402/20190402140351_5344.jpg"}]
             */

            private String __type;
            private String Img;
            private String EnTxt;
            private String CNTxt;
            private List<BookTypeBean> BookType;

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public String getImg() {
                return Img;
            }

            public void setImg(String Img) {
                this.Img = Img;
            }

            public String getEnTxt() {
                return EnTxt;
            }

            public void setEnTxt(String EnTxt) {
                this.EnTxt = EnTxt;
            }

            public String getCNTxt() {
                return CNTxt;
            }

            public void setCNTxt(String CNTxt) {
                this.CNTxt = CNTxt;
            }

            public List<BookTypeBean> getBookType() {
                return BookType;
            }

            public void setBookType(List<BookTypeBean> BookType) {
                this.BookType = BookType;
            }

            public static class BookTypeBean {
                /**
                 * ID : 3
                 * TypeName : 政策篇
                 * TypeSort : 1
                 * CreateTime : /Date(1554185012517)/
                 * Pic : http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20190402/20190402140330_5454.jpg
                 */

                private int ID;
                private String TypeName;
                private int TypeSort;
                private String CreateTime;
                private String Pic;

                public int getID() {
                    return ID;
                }

                public void setID(int ID) {
                    this.ID = ID;
                }

                public String getTypeName() {
                    return TypeName;
                }

                public void setTypeName(String TypeName) {
                    this.TypeName = TypeName;
                }

                public int getTypeSort() {
                    return TypeSort;
                }

                public void setTypeSort(int TypeSort) {
                    this.TypeSort = TypeSort;
                }

                public String getCreateTime() {
                    return CreateTime;
                }

                public void setCreateTime(String CreateTime) {
                    this.CreateTime = CreateTime;
                }

                public String getPic() {
                    return Pic;
                }

                public void setPic(String Pic) {
                    this.Pic = Pic;
                }
            }
        }
    }
}
