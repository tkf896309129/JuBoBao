package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：FengKongPicBean
 * 描述：
 * 创建时间：2019-07-23  15:16
 */

public class FengKongPicBean {

    /**
     * d : {"ErrorCode":0,"Msg":"","Result":{"__type":"AtJubo.Api.App.ReModel.AdditionViewModel","list":[],"model":{"FileItems":["http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20190723/20190723141919_9518.png","http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20190723/20190723141920_0299.png","http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20190723/20190723141920_1237.png","http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20190723/20190723141920_1862.png","http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20190723/20190723141920_2487.png","http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20190723/20190723141920_3424.png"],"ID":0,"State":0,"CreateTime":"/Date(1563862768073)/","InfoType":0,"InfoItem":null,"Equipments":null}}}
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
         * Result : {"__type":"AtJubo.Api.App.ReModel.AdditionViewModel","list":[],"model":{"FileItems":["http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20190723/20190723141919_9518.png","http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20190723/20190723141920_0299.png","http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20190723/20190723141920_1237.png","http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20190723/20190723141920_1862.png","http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20190723/20190723141920_2487.png","http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20190723/20190723141920_3424.png"],"ID":0,"State":0,"CreateTime":"/Date(1563862768073)/","InfoType":0,"InfoItem":null,"Equipments":null}}
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
             * __type : AtJubo.Api.App.ReModel.AdditionViewModel
             * list : []
             * model : {"FileItems":["http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20190723/20190723141919_9518.png","http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20190723/20190723141920_0299.png","http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20190723/20190723141920_1237.png","http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20190723/20190723141920_1862.png","http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20190723/20190723141920_2487.png","http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20190723/20190723141920_3424.png"],"ID":0,"State":0,"CreateTime":"/Date(1563862768073)/","InfoType":0,"InfoItem":null,"Equipments":null}
             */

            private String __type;
            private ModelBean model;
            private List<ModelBean> list;

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public ModelBean getModel() {
                return model;
            }

            public void setModel(ModelBean model) {
                this.model = model;
            }

            public List<ModelBean> getList() {
                return list;
            }

            public void setList(List<ModelBean> list) {
                this.list = list;
            }

            public static class ModelBean {
                /**
                 * FileItems : ["http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20190723/20190723141919_9518.png","http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20190723/20190723141920_0299.png","http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20190723/20190723141920_1237.png","http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20190723/20190723141920_1862.png","http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20190723/20190723141920_2487.png","http://atjubo.oss-cn-hangzhou.aliyuncs.com/lbltoc/image/20190723/20190723141920_3424.png"]
                 * ID : 0
                 * State : 0
                 * CreateTime : /Date(1563862768073)/
                 * InfoType : 0
                 * InfoItem : null
                 * Equipments : null
                 */

                private int ID;
                private int State;
                private String CreateTime;
                private int InfoType;
                private Object InfoItem;
                private Object Equipments;
                private List<String> FileItems;

                public int getID() {
                    return ID;
                }

                public void setID(int ID) {
                    this.ID = ID;
                }

                public int getState() {
                    return State;
                }

                public void setState(int State) {
                    this.State = State;
                }

                public String getCreateTime() {
                    return CreateTime;
                }

                public void setCreateTime(String CreateTime) {
                    this.CreateTime = CreateTime;
                }

                public int getInfoType() {
                    return InfoType;
                }

                public void setInfoType(int InfoType) {
                    this.InfoType = InfoType;
                }

                public Object getInfoItem() {
                    return InfoItem;
                }

                public void setInfoItem(Object InfoItem) {
                    this.InfoItem = InfoItem;
                }

                public Object getEquipments() {
                    return Equipments;
                }

                public void setEquipments(Object Equipments) {
                    this.Equipments = Equipments;
                }

                public List<String> getFileItems() {
                    return FileItems;
                }

                public void setFileItems(List<String> FileItems) {
                    this.FileItems = FileItems;
                }
            }
        }
    }
}
