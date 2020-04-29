package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：RongZiInfoBean
 * 描述：
 * 创建时间：2020-03-12  08:47
 */

public class RongZiInfoBean {


    /**
     * ErrMsg :
     * ReObj : {"CompanyName":"浙江火山口网络科技有限公司","PicFaren":"","RealName":"唐凯丰"}
     * Success : 1
     */

    private String ErrMsg;
    private ReObjBean ReObj;
    private int Success;

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String ErrMsg) {
        this.ErrMsg = ErrMsg;
    }

    public ReObjBean getReObj() {
        return ReObj;
    }

    public void setReObj(ReObjBean ReObj) {
        this.ReObj = ReObj;
    }

    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int Success) {
        this.Success = Success;
    }

    public static class ReObjBean {
        /**
         * CompanyName : 浙江火山口网络科技有限公司
         * PicFaren :
         * RealName : 唐凯丰
         */

        private String CompanyName;
        private String PicFaren;
        private String RealName;

        public String getCompanyName() {
            return CompanyName;
        }

        public void setCompanyName(String CompanyName) {
            this.CompanyName = CompanyName;
        }

        public String getPicFaren() {
            return PicFaren;
        }

        public void setPicFaren(String PicFaren) {
            this.PicFaren = PicFaren;
        }

        public String getRealName() {
            return RealName;
        }

        public void setRealName(String RealName) {
            this.RealName = RealName;
        }
    }
}
