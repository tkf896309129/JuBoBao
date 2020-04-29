package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：DateTypeBean
 * 描述：
 * 创建时间：2019-09-02  14:23
 */

public class DateTypeBean {

    private List<RepeateBean> repeate;
    private List<ReminderBean> reminder;
    private List<EndRepeateBean> endRepeate;

    public List<RepeateBean> getRepeate() {
        return repeate;
    }

    public void setRepeate(List<RepeateBean> repeate) {
        this.repeate = repeate;
    }

    public List<ReminderBean> getReminder() {
        return reminder;
    }

    public void setReminder(List<ReminderBean> reminder) {
        this.reminder = reminder;
    }

    public List<EndRepeateBean> getEndRepeate() {
        return endRepeate;
    }

    public void setEndRepeate(List<EndRepeateBean> endRepeate) {
        this.endRepeate = endRepeate;
    }

    public static class RepeateBean {
        /**
         * Type : 0
         * Name : 一次性
         */

        private int Type;
        private String Name;

        public int getType() {
            return Type;
        }

        public void setType(int Type) {
            this.Type = Type;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }
    }

    public static class ReminderBean {
        /**
         * Type : 0
         * Name : 准时
         */

        private int Type;
        private String Name;

        public int getType() {
            return Type;
        }

        public void setType(int Type) {
            this.Type = Type;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }
    }

    public static class EndRepeateBean {
        /**
         * Type : 0
         * Name : 永不
         */

        private int Type;
        private String Name;

        public int getType() {
            return Type;
        }

        public void setType(int Type) {
            this.Type = Type;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }
    }
}
