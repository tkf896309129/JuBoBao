package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：RawCateroyBean
 * 描述：
 * 创建时间：2020-03-04  10:35
 */

public class RawCateroyBean {


    /**
     * d : {"TotalSum":"0","TotalCount":0,"PageCount":0,"PageIndex":0,"ReList":[{"__type":"Atjubo.DTO.Output.Stock.C_CategoryOutPut","CategoryId":17,"CategoryName":"aaa"},{"__type":"Atjubo.DTO.Output.Stock.C_CategoryOutPut","CategoryId":1,"CategoryName":"纯碱"},{"__type":"Atjubo.DTO.Output.Stock.C_CategoryOutPut","CategoryId":12,"CategoryName":"测试删除类别"},{"__type":"Atjubo.DTO.Output.Stock.C_CategoryOutPut","CategoryId":13,"CategoryName":"aaaa"},{"__type":"Atjubo.DTO.Output.Stock.C_CategoryOutPut","CategoryId":14,"CategoryName":"123@abc阿萨德"},{"__type":"Atjubo.DTO.Output.Stock.C_CategoryOutPut","CategoryId":15,"CategoryName":" 123 333 444"},{"__type":"Atjubo.DTO.Output.Stock.C_CategoryOutPut","CategoryId":2,"CategoryName":"石英砂222333"},{"__type":"Atjubo.DTO.Output.Stock.C_CategoryOutPut","CategoryId":11,"CategoryName":"新新原料2"}],"Success":1,"ErrMsg":""}
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
         * ReList : [{"__type":"Atjubo.DTO.Output.Stock.C_CategoryOutPut","CategoryId":17,"CategoryName":"aaa"},{"__type":"Atjubo.DTO.Output.Stock.C_CategoryOutPut","CategoryId":1,"CategoryName":"纯碱"},{"__type":"Atjubo.DTO.Output.Stock.C_CategoryOutPut","CategoryId":12,"CategoryName":"测试删除类别"},{"__type":"Atjubo.DTO.Output.Stock.C_CategoryOutPut","CategoryId":13,"CategoryName":"aaaa"},{"__type":"Atjubo.DTO.Output.Stock.C_CategoryOutPut","CategoryId":14,"CategoryName":"123@abc阿萨德"},{"__type":"Atjubo.DTO.Output.Stock.C_CategoryOutPut","CategoryId":15,"CategoryName":" 123 333 444"},{"__type":"Atjubo.DTO.Output.Stock.C_CategoryOutPut","CategoryId":2,"CategoryName":"石英砂222333"},{"__type":"Atjubo.DTO.Output.Stock.C_CategoryOutPut","CategoryId":11,"CategoryName":"新新原料2"}]
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
             * __type : Atjubo.DTO.Output.Stock.C_CategoryOutPut
             * CategoryId : 17
             * CategoryName : aaa
             */

            private String __type;
            private int CategoryId;
            private String CategoryName;
            private String BrandName;
            private String ClassName;
            private String LevelName;

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public int getCategoryId() {
                return CategoryId;
            }

            public void setCategoryId(int CategoryId) {
                this.CategoryId = CategoryId;
            }

            public String getCategoryName() {
                return CategoryName;
            }

            public void setCategoryName(String CategoryName) {
                this.CategoryName = CategoryName;
            }


            public String getBrandName() {
                return BrandName;
            }

            public void setBrandName(String brandName) {
                BrandName = brandName;
            }

            public String getClassName() {
                return ClassName;
            }

            public void setClassName(String className) {
                ClassName = className;
            }

            public String getLevelName() {
                return LevelName;
            }

            public void setLevelName(String levelName) {
                LevelName = levelName;
            }
        }
    }
}
