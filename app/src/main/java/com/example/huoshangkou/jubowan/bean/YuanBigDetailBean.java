package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：YuanBigDetailBean
 * 描述：
 * 创建时间：2019-04-12  08:39
 */

public class YuanBigDetailBean {


    /**
     * d : {"ErrorCode":0,"Msg":"","Result":[{"__type":"AtJubo.Api.App.ReModel.JuboBao.BModel","BrandName":"台玻 (咸阳)","Pic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20160303/20160303150506_4206.png"},{"__type":"AtJubo.Api.App.ReModel.JuboBao.BModel","BrandName":"台玻 (成都)","Pic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20161209/20161209142736_5066.jpg"},{"__type":"AtJubo.Api.App.ReModel.JuboBao.BModel","BrandName":"台玻 (昆山)","Pic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20161223/20161223115516_7498.jpg"},{"__type":"AtJubo.Api.App.ReModel.JuboBao.BModel","BrandName":"台玻 (天津)","Pic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20180403/20180403094542_3437.png"},{"__type":"AtJubo.Api.App.ReModel.JuboBao.BModel","BrandName":"台玻 (东海)","Pic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20180426/20180426151915_8124.png"}]}
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
         * Result : [{"__type":"AtJubo.Api.App.ReModel.JuboBao.BModel","BrandName":"台玻 (咸阳)","Pic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20160303/20160303150506_4206.png"},{"__type":"AtJubo.Api.App.ReModel.JuboBao.BModel","BrandName":"台玻 (成都)","Pic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20161209/20161209142736_5066.jpg"},{"__type":"AtJubo.Api.App.ReModel.JuboBao.BModel","BrandName":"台玻 (昆山)","Pic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20161223/20161223115516_7498.jpg"},{"__type":"AtJubo.Api.App.ReModel.JuboBao.BModel","BrandName":"台玻 (天津)","Pic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20180403/20180403094542_3437.png"},{"__type":"AtJubo.Api.App.ReModel.JuboBao.BModel","BrandName":"台玻 (东海)","Pic":"http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20180426/20180426151915_8124.png"}]
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
             * __type : AtJubo.Api.App.ReModel.JuboBao.BModel
             * BrandName : 台玻 (咸阳)
             * Pic : http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20160303/20160303150506_4206.png
             */

            private String __type;
            private String BrandName;
            private String Pic;

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public String getBrandName() {
                return BrandName;
            }

            public void setBrandName(String BrandName) {
                this.BrandName = BrandName;
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
