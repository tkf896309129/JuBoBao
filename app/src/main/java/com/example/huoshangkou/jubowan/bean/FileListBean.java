package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：FileListBean
 * 描述：
 * 创建时间：2020-03-10  11:34
 */

public class FileListBean {


    /**
     * d : {"TotalSum":"0","TotalCount":0,"PageCount":0,"PageIndex":0,"ReList":[{"__type":"Atjubo.DTO.Output.MyStudy.ReturnFileInfo","FileUrl":"https://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20191212/2019-12-12-08-51-10-6142__%E4%B8%93%E4%B8%9A%E6%8A%80%E6%9C%AF%E3%80%81%E7%BB%8F%E6%B5%8E%E4%BA%BA%E5%91%98%E7%AD%89%E7%BA%A7%E8%AF%84%E5%AE%9A%E6%99%8B%E5%8D%87%E7%AE%A1%E7%90%86%E5%8A%9E%E6%B3%95%EF%BC%88%E8%AF%95%E8%A1%8C%29_20191212084906.pdf","FileName":"专业技术、经济人员等级评定晋升管理办法（试行)_20191212084906.pdf","FileType":"pdf","FileSize":"20"},{"__type":"Atjubo.DTO.Output.MyStudy.ReturnFileInfo","FileUrl":"https://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20191206/2019-12-06-08-44-04-7960__1.pdf","FileName":"1.pdf","FileType":"pdf","FileSize":"23"}],"Success":1,"ErrMsg":""}
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
         * TotalSum : 0
         * TotalCount : 0
         * PageCount : 0
         * PageIndex : 0
         * ReList : [{"__type":"Atjubo.DTO.Output.MyStudy.ReturnFileInfo","FileUrl":"https://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20191212/2019-12-12-08-51-10-6142__%E4%B8%93%E4%B8%9A%E6%8A%80%E6%9C%AF%E3%80%81%E7%BB%8F%E6%B5%8E%E4%BA%BA%E5%91%98%E7%AD%89%E7%BA%A7%E8%AF%84%E5%AE%9A%E6%99%8B%E5%8D%87%E7%AE%A1%E7%90%86%E5%8A%9E%E6%B3%95%EF%BC%88%E8%AF%95%E8%A1%8C%29_20191212084906.pdf","FileName":"专业技术、经济人员等级评定晋升管理办法（试行)_20191212084906.pdf","FileType":"pdf","FileSize":"20"},{"__type":"Atjubo.DTO.Output.MyStudy.ReturnFileInfo","FileUrl":"https://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20191206/2019-12-06-08-44-04-7960__1.pdf","FileName":"1.pdf","FileType":"pdf","FileSize":"23"}]
         * Success : 1
         * ErrMsg :
         */

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
             * __type : Atjubo.DTO.Output.MyStudy.ReturnFileInfo
             * FileUrl : https://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/20191212/2019-12-12-08-51-10-6142__%E4%B8%93%E4%B8%9A%E6%8A%80%E6%9C%AF%E3%80%81%E7%BB%8F%E6%B5%8E%E4%BA%BA%E5%91%98%E7%AD%89%E7%BA%A7%E8%AF%84%E5%AE%9A%E6%99%8B%E5%8D%87%E7%AE%A1%E7%90%86%E5%8A%9E%E6%B3%95%EF%BC%88%E8%AF%95%E8%A1%8C%29_20191212084906.pdf
             * FileName : 专业技术、经济人员等级评定晋升管理办法（试行)_20191212084906.pdf
             * FileType : pdf
             * FileSize : 20
             */

            private String __type;
            private String FileUrl;
            private String FileName;
            private String FileType;
            private String FileSize;
            private String filePath;
            private float progress;

            public String getFilePath() {
                return filePath;
            }

            public void setFilePath(String filePath) {
                this.filePath = filePath;
            }

            public float getProgress() {
                return progress;
            }

            public void setProgress(float progress) {
                this.progress = progress;
            }

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public String getFileUrl() {
                return FileUrl;
            }

            public void setFileUrl(String FileUrl) {
                this.FileUrl = FileUrl;
            }

            public String getFileName() {
                return FileName;
            }

            public void setFileName(String FileName) {
                this.FileName = FileName;
            }

            public String getFileType() {
                return FileType;
            }

            public void setFileType(String FileType) {
                this.FileType = FileType;
            }

            public String getFileSize() {
                return FileSize;
            }

            public void setFileSize(String FileSize) {
                this.FileSize = FileSize;
            }
        }
    }
}
