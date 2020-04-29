package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：RecordAnyBean
 * 描述：
 * 创建时间：2019-08-22  11:06
 */

public class RecordAnyBean {


    /**
     * log_id : 1886767665419902710
     * text : %E4%B8%8B%E5%8E%BB%E5%86%B2%E5%8A%A8%E5%9B%BD%E9%99%85
     * items : [{"loc_details":[],"byte_offset":0,"uri":"","pos":"m","ne":"","item":"%E4%B8%8B%E5%8E%BB%E5%86%B2%E5%8A%A8%E5%9B%BD%E9%99%85","basic_words":["%E","4","%B","8","%","8","B","%","E","5","%","8","E","%","BB","%","E","5","%","8","6","%","B2","%","E","5","%","8","A","%","A8","%","E","5","%","9","B","%","BD","%","E","9","%","99","%","85"],"byte_length":54,"formal":""}]
     */

    private long log_id;
    private String text;
    private List<ItemsBean> items;

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * loc_details : []
         * byte_offset : 0
         * uri :
         * pos : m
         * ne :
         * item : %E4%B8%8B%E5%8E%BB%E5%86%B2%E5%8A%A8%E5%9B%BD%E9%99%85
         * basic_words : ["%E","4","%B","8","%","8","B","%","E","5","%","8","E","%","BB","%","E","5","%","8","6","%","B2","%","E","5","%","8","A","%","A8","%","E","5","%","9","B","%","BD","%","E","9","%","99","%","85"]
         * byte_length : 54
         * formal :
         */

        private int byte_offset;
        private String uri;
        private String pos;
        private String ne;
        private String item;
        private int byte_length;
        private String formal;
        private List<?> loc_details;
        private List<String> basic_words;

        public int getByte_offset() {
            return byte_offset;
        }

        public void setByte_offset(int byte_offset) {
            this.byte_offset = byte_offset;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public String getPos() {
            return pos;
        }

        public void setPos(String pos) {
            this.pos = pos;
        }

        public String getNe() {
            return ne;
        }

        public void setNe(String ne) {
            this.ne = ne;
        }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public int getByte_length() {
            return byte_length;
        }

        public void setByte_length(int byte_length) {
            this.byte_length = byte_length;
        }

        public String getFormal() {
            return formal;
        }

        public void setFormal(String formal) {
            this.formal = formal;
        }

        public List<?> getLoc_details() {
            return loc_details;
        }

        public void setLoc_details(List<?> loc_details) {
            this.loc_details = loc_details;
        }

        public List<String> getBasic_words() {
            return basic_words;
        }

        public void setBasic_words(List<String> basic_words) {
            this.basic_words = basic_words;
        }
    }
}
