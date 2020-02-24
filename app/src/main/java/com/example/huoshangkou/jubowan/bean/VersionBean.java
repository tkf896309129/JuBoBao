package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：VersionBean
 * 描述：版本说明
 * 创建时间：2017-02-22  15:47
 */

public class VersionBean {
    /**
     * d : {"Data":{"__type":"Atjubo.DAL.DataEntity.APPVersion.APPVersion","Id":2,"VersionNo":16,"DownloadUrl":"https://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/201 91210/2019-12-10-17-07-28-5192__%E4%BF%AE%E6%94%B9%E8%A7%86%E9%A2%91.txt","VersionName":"股份","Size":233,"VersionDescription":"哈哈哈","CreateTime":"/Date(1575968864637)/"},"StatusCode":1,"Error":null}
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
         * Data : {"__type":"Atjubo.DAL.DataEntity.APPVersion.APPVersion","Id":2,"VersionNo":16,"DownloadUrl":"https://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/201 91210/2019-12-10-17-07-28-5192__%E4%BF%AE%E6%94%B9%E8%A7%86%E9%A2%91.txt","VersionName":"股份","Size":233,"VersionDescription":"哈哈哈","CreateTime":"/Date(1575968864637)/"}
         * StatusCode : 1
         * Error : null
         */

        private DataBean Data;
        private int StatusCode;
        private Object Error;

        public DataBean getData() {
            return Data;
        }

        public void setData(DataBean Data) {
            this.Data = Data;
        }

        public int getStatusCode() {
            return StatusCode;
        }

        public void setStatusCode(int StatusCode) {
            this.StatusCode = StatusCode;
        }

        public Object getError() {
            return Error;
        }

        public void setError(Object Error) {
            this.Error = Error;
        }

        public static class DataBean {
            /**
             * __type : Atjubo.DAL.DataEntity.APPVersion.APPVersion
             * Id : 2
             * VersionNo : 16
             * DownloadUrl : https://atjubo.oss-cn-hangzhou.aliyuncs.com/0/image/201 91210/2019-12-10-17-07-28-5192__%E4%BF%AE%E6%94%B9%E8%A7%86%E9%A2%91.txt
             * VersionName : 股份
             * Size : 233.0
             * VersionDescription : 哈哈哈
             * CreateTime : /Date(1575968864637)/
             */

            private String __type;
            private int Id;
            private int VersionNo;
            private String DownloadUrl;
            private String VersionName;
            private double Size;
            private String VersionDescription;
            private String CreateTime;

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

            public int getVersionNo() {
                return VersionNo;
            }

            public void setVersionNo(int VersionNo) {
                this.VersionNo = VersionNo;
            }

            public String getDownloadUrl() {
                return DownloadUrl;
            }

            public void setDownloadUrl(String DownloadUrl) {
                this.DownloadUrl = DownloadUrl;
            }

            public String getVersionName() {
                return VersionName;
            }

            public void setVersionName(String VersionName) {
                this.VersionName = VersionName;
            }

            public double getSize() {
                return Size;
            }

            public void setSize(double Size) {
                this.Size = Size;
            }

            public String getVersionDescription() {
                return VersionDescription;
            }

            public void setVersionDescription(String VersionDescription) {
                this.VersionDescription = VersionDescription;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }
        }
    }
}
