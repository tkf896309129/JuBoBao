package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：ToJBBuyBean
 * 描述：
 * 创建时间：2019-06-27  09:26
 */

public class ToJBBuyBean {


    /**
     * addModel : {"userName":"18319429586","password":"123456","listGoods":[{"BrandName":"寿光","ClassName":"白玻","Level":"一等品1","Width":"1900","Height":"2440","Weight":"3.20","Number":4},{"BrandName":"寿光","ClassName":"白玻","Level":"一等品","Width":"1900","Height":"2440","Weight":"3.20","Number":4}]}
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
         * password : 123456
         * listGoods : [{"BrandName":"寿光","ClassName":"白玻","Level":"一等品1","Width":"1900","Height":"2440","Weight":"3.20","Number":4},{"BrandName":"寿光","ClassName":"白玻","Level":"一等品","Width":"1900","Height":"2440","Weight":"3.20","Number":4}]
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
             * BrandName : 寿光
             * ClassName : 白玻
             * Level : 一等品1
             * Width : 1900
             * Height : 2440
             * Weight : 3.20
             * Number : 4
             */

            private String BrandName;
            private String ClassName;
            private String Level;
            private String Width;
            private String Height;
            private String Weight;
            private String Property;
            private int Number;

            public String getProperty() {
                return Property;
            }

            public void setProperty(String property) {
                Property = property;
            }

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

            public String getLevel() {
                return Level;
            }

            public void setLevel(String Level) {
                this.Level = Level;
            }

            public String getWidth() {
                return Width;
            }

            public void setWidth(String Width) {
                this.Width = Width;
            }

            public String getHeight() {
                return Height;
            }

            public void setHeight(String Height) {
                this.Height = Height;
            }

            public String getWeight() {
                return Weight;
            }

            public void setWeight(String Weight) {
                this.Weight = Weight;
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
