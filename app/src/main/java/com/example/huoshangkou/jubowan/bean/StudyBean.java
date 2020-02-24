package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：StudyBean
 * 描述：
 * 创建时间：2019-11-05  09:47
 */

public class StudyBean {



    private DBean d;

    public DBean getD() {
        return d;
    }

    public void setD(DBean d) {
        this.d = d;
    }

    public static class DBean {


        private String TotalSum;
        private int TotalCount;
        private int PageCount;
        private int PageIndex;
        private int Success;
        private String ErrMsg;
        private List<ReListBean> ReList;

        public String getTotalSum() {
            return TotalSum;
        }

        public void setTotalSum(String TotalSum) {
            this.TotalSum = TotalSum;
        }

        public int getTotalCount() {
            return TotalCount;
        }

        public void setTotalCount(int TotalCount) {
            this.TotalCount = TotalCount;
        }

        public int getPageCount() {
            return PageCount;
        }

        public void setPageCount(int PageCount) {
            this.PageCount = PageCount;
        }

        public int getPageIndex() {
            return PageIndex;
        }

        public void setPageIndex(int PageIndex) {
            this.PageIndex = PageIndex;
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

        public List<ReListBean> getReList() {
            return ReList;
        }

        public void setReList(List<ReListBean> ReList) {
            this.ReList = ReList;
        }

        public static class ReListBean {
            /**
             * __type : Atjubo.DAL.DataEntity.Administrative.ADM_SkillLearning
             * Id : 11
             * Title : 标题
             * TxtContent : 内容1
             * Papers : http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20191029/2019-10-29-17-43-32-9709__%E6%96%B0%E5%BB%BA%E6%96%87%E6%9C%AC%E6%96%87%E6%A1%A3.txt
             * Operationmanual : null
             * Video : http://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20191029/2019-10-29-17-41-13-1791__1.mp4
             * Audio : null
             * CreateTime : /Date(1572342215640)/
             * CompanyId : 13
             */

            private String __type;
            private int Id;
            private String Title;
            private String TxtContent;
            private String Picture;
            private String Papers;
            private Object Operationmanual;
            private String Video;
            private Object Audio;
            private String CreateTime;
            private int CompanyId;

            public String getPicture() {
                return Picture;
            }

            public void setPicture(String picture) {
                Picture = picture;
            }

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
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

            public String getPapers() {
                return Papers;
            }

            public void setPapers(String Papers) {
                this.Papers = Papers;
            }

            public Object getOperationmanual() {
                return Operationmanual;
            }

            public void setOperationmanual(Object Operationmanual) {
                this.Operationmanual = Operationmanual;
            }

            public String getVideo() {
                return Video;
            }

            public void setVideo(String Video) {
                this.Video = Video;
            }

            public Object getAudio() {
                return Audio;
            }

            public void setAudio(Object Audio) {
                this.Audio = Audio;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public int getCompanyId() {
                return CompanyId;
            }

            public void setCompanyId(int CompanyId) {
                this.CompanyId = CompanyId;
            }
        }
    }
}
