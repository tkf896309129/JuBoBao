package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：FengKongListBean
 * 描述：
 * 创建时间：2019-07-23  09:40
 */

public class FengKongListBean {



    /**
     * d : {"ErrorCode":0,"Msg":"","Result":[{"__type":"AtJubo.Api.App.ReModel.JuboBao.UserAdditionalInfoModel+UserAdditionaTitle","Title":"财务报表","State":-2,"type":0},{"__type":"AtJubo.Api.App.ReModel.JuboBao.UserAdditionalInfoModel+UserAdditionaTitle","Title":"纳税申报表","State":-2,"type":1},{"__type":"AtJubo.Api.App.ReModel.JuboBao.UserAdditionalInfoModel+UserAdditionaTitle","Title":"电费发票","State":-2,"type":2},{"__type":"AtJubo.Api.App.ReModel.JuboBao.UserAdditionalInfoModel+UserAdditionaTitle","Title":"资产证明","State":-2,"type":3},{"__type":"AtJubo.Api.App.ReModel.JuboBao.UserAdditionalInfoModel+UserAdditionaTitle","Title":"企业设备情况","State":-2,"type":4},{"__type":"AtJubo.Api.App.ReModel.JuboBao.UserAdditionalInfoModel+UserAdditionaTitle","Title":"征信报告","State":-2,"type":5},{"__type":"AtJubo.Api.App.ReModel.JuboBao.UserAdditionalInfoModel+UserAdditionaTitle","Title":"资质证书","State":-2,"type":6},{"__type":"AtJubo.Api.App.ReModel.JuboBao.UserAdditionalInfoModel+UserAdditionaTitle","Title":"其它相关资料","State":-2,"type":7}]}
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
         * Result : [{"__type":"AtJubo.Api.App.ReModel.JuboBao.UserAdditionalInfoModel+UserAdditionaTitle","Title":"财务报表","State":-2,"type":0},{"__type":"AtJubo.Api.App.ReModel.JuboBao.UserAdditionalInfoModel+UserAdditionaTitle","Title":"纳税申报表","State":-2,"type":1},{"__type":"AtJubo.Api.App.ReModel.JuboBao.UserAdditionalInfoModel+UserAdditionaTitle","Title":"电费发票","State":-2,"type":2},{"__type":"AtJubo.Api.App.ReModel.JuboBao.UserAdditionalInfoModel+UserAdditionaTitle","Title":"资产证明","State":-2,"type":3},{"__type":"AtJubo.Api.App.ReModel.JuboBao.UserAdditionalInfoModel+UserAdditionaTitle","Title":"企业设备情况","State":-2,"type":4},{"__type":"AtJubo.Api.App.ReModel.JuboBao.UserAdditionalInfoModel+UserAdditionaTitle","Title":"征信报告","State":-2,"type":5},{"__type":"AtJubo.Api.App.ReModel.JuboBao.UserAdditionalInfoModel+UserAdditionaTitle","Title":"资质证书","State":-2,"type":6},{"__type":"AtJubo.Api.App.ReModel.JuboBao.UserAdditionalInfoModel+UserAdditionaTitle","Title":"其它相关资料","State":-2,"type":7}]
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
             * __type : AtJubo.Api.App.ReModel.JuboBao.UserAdditionalInfoModel+UserAdditionaTitle
             * Title : 财务报表
             * State : -2
             * type : 0
             */

            private String __type;
            private String Title;
            private String Url;
            private int State;
            private int type;

            public String getUrl() {
                return Url;
            }

            public void setUrl(String url) {
                Url = url;
            }

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

            public int getState() {
                return State;
            }

            public void setState(int State) {
                this.State = State;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}
