package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：AdvBean
 * 描述：
 * 创建时间：2019-05-06  09:07
 */

public class AdvBean {


    /**
     * d : {"ReObj":{"__type":"AtJubo.Api.App.ReModel.JuboBao.AdvertisementModel","Title":"2121","TxtContent":" 发少放点水发的啥多舒服都是盛大","Pics":null,"PopupNum":10,"Link":null,"PopupType":0,"PopupTail":"发布人：火山口"},"Success":1,"ErrMsg":""}
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
         * ReObj : {"__type":"AtJubo.Api.App.ReModel.JuboBao.AdvertisementModel","Title":"2121","TxtContent":" 发少放点水发的啥多舒服都是盛大","Pics":null,"PopupNum":10,"Link":null,"PopupType":0,"PopupTail":"发布人：火山口"}
         * Success : 1
         * ErrMsg :
         */

        private ReObjBean ReObj;
        private int Success;
        private String ErrMsg;

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

        public String getErrMsg() {
            return ErrMsg;
        }

        public void setErrMsg(String ErrMsg) {
            this.ErrMsg = ErrMsg;
        }

        public static class ReObjBean {
            /**
             * __type : AtJubo.Api.App.ReModel.JuboBao.AdvertisementModel
             * Title : 2121
             * TxtContent :  发少放点水发的啥多舒服都是盛大
             * Pics : null
             * PopupNum : 10
             * Link : null
             * PopupType : 0
             * PopupTail : 发布人：火山口
             */

            private String __type;
            private String Title;
            private String TxtContent;
            private String Pics;
            private int PopupNum;
            private String Link;
            private int PopupType;
            private String PopupTail;

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

            public String getTxtContent() {
                return TxtContent;
            }

            public void setTxtContent(String TxtContent) {
                this.TxtContent = TxtContent;
            }

            public String getPics() {
                return Pics;
            }

            public void setPics(String Pics) {
                this.Pics = Pics;
            }

            public int getPopupNum() {
                return PopupNum;
            }

            public void setPopupNum(int PopupNum) {
                this.PopupNum = PopupNum;
            }

            public String getLink() {
                return Link;
            }

            public void setLink(String Link) {
                this.Link = Link;
            }

            public int getPopupType() {
                return PopupType;
            }

            public void setPopupType(int PopupType) {
                this.PopupType = PopupType;
            }

            public String getPopupTail() {
                return PopupTail;
            }

            public void setPopupTail(String PopupTail) {
                this.PopupTail = PopupTail;
            }
        }
    }
}
