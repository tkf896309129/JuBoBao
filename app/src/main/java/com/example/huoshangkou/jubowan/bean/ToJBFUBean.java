package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：ToJBFUBean
 * 描述：
 * 创建时间：2019-07-09  16:11
 */

public class ToJBFUBean {

    /**
     * addModel : {"userName":"18319429586","password":"4QrcOUm6Wau+VuBX8g+IPg==","listGoods":[{"BrandName":"普罗菲尔","ClassName":"不可折弯铝条","Val":"6A","Number":4}]}
     */

    private AddModelBean addModel;

    public AddModelBean getAddModel() {
        return addModel;
    }

    public void setAddModel(AddModelBean addModel) {
        this.addModel = addModel;
    }

    public static class AddModelBean {
        /**
         * userName : 18319429586
         * password : 4QrcOUm6Wau+VuBX8g+IPg==
         * listGoods : [{"BrandName":"普罗菲尔","ClassName":"不可折弯铝条","Val":"6A","Number":4}]
         */

        private String userName;
        private String password;
        private List<ListGoodsBean> listGoods;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public List<ListGoodsBean> getListGoods() {
            return listGoods;
        }

        public void setListGoods(List<ListGoodsBean> listGoods) {
            this.listGoods = listGoods;
        }

        public static class ListGoodsBean {
            /**
             * BrandName : 普罗菲尔
             * ClassName : 不可折弯铝条
             * Val : 6A
             * Number : 4
             */
            private String BrandName;
            private String ClassName;
            private String Val;
            private int Number;

            public String getBrandName() {
                return BrandName;
            }

            public void setBrandName(String BrandName) {
                this.BrandName = BrandName;
            }

            public String getClassName() {
                return ClassName;
            }

            public void setClassName(String ClassName) {
                this.ClassName = ClassName;
            }

            public String getVal() {
                return Val;
            }

            public void setVal(String Val) {
                this.Val = Val;
            }

            public int getNumber() {
                return Number;
            }

            public void setNumber(int Number) {
                this.Number = Number;
            }
        }
    }
}
