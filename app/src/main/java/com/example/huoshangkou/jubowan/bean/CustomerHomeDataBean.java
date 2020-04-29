package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：CustomerHomeDataBean
 * 描述：
 * 创建时间：2019-09-09  11:38
 */

public class CustomerHomeDataBean {


    /**
     * d : {"ErrorCode":1,"Msg":"获取成功","Result":{"__type":"Model.ViewModel.Sale.JuBoApp.AppSaleStatisicsModel","Items":[{"SatatisType":0,"StatisValue":"0"},{"SatatisType":1,"StatisValue":"0"},{"SatatisType":2,"StatisValue":"0.00"},{"SatatisType":3,"StatisValue":"1500000.00"},{"SatatisType":4,"StatisValue":"0.00%"},{"SatatisType":5,"StatisValue":"0.00%"}]}}
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
         * ErrorCode : 1
         * Msg : 获取成功
         * Result : {"__type":"Model.ViewModel.Sale.JuBoApp.AppSaleStatisicsModel","Items":[{"SatatisType":0,"StatisValue":"0"},{"SatatisType":1,"StatisValue":"0"},{"SatatisType":2,"StatisValue":"0.00"},{"SatatisType":3,"StatisValue":"1500000.00"},{"SatatisType":4,"StatisValue":"0.00%"},{"SatatisType":5,"StatisValue":"0.00%"}]}
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
             * __type : Model.ViewModel.Sale.JuBoApp.AppSaleStatisicsModel
             * Items : [{"SatatisType":0,"StatisValue":"0"},{"SatatisType":1,"StatisValue":"0"},{"SatatisType":2,"StatisValue":"0.00"},{"SatatisType":3,"StatisValue":"1500000.00"},{"SatatisType":4,"StatisValue":"0.00%"},{"SatatisType":5,"StatisValue":"0.00%"}]
             */

            private String __type;
            private List<ItemsBean> Items;

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public List<ItemsBean> getItems() {
                return Items;
            }

            public void setItems(List<ItemsBean> Items) {
                this.Items = Items;
            }

            public static class ItemsBean {
                /**
                 * SatatisType : 0
                 * StatisValue : 0
                 */

                private int SatatisType;
                private String StatisValue;
                private String SatatisTypeName;
                private int img;
                private boolean isCheck = false;

                public int getImg() {
                    return img;
                }

                public void setImg(int img) {
                    this.img = img;
                }

                public String getSatatisTypeName() {
                    return SatatisTypeName;
                }

                public void setSatatisTypeName(String satatisTypeName) {
                    SatatisTypeName = satatisTypeName;
                }

                public boolean isCheck() {
                    return isCheck;
                }

                public void setCheck(boolean check) {
                    isCheck = check;
                }

                public int getSatatisType() {
                    return SatatisType;
                }

                public void setSatatisType(int SatatisType) {
                    this.SatatisType = SatatisType;
                }

                public String getStatisValue() {
                    return StatisValue;
                }

                public void setStatisValue(String StatisValue) {
                    this.StatisValue = StatisValue;
                }
            }
        }
    }
}
