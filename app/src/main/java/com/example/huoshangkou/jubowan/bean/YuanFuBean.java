package com.example.huoshangkou.jubowan.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：YuanFuBean
 * 描述：
 * 创建时间：2019-04-28  09:06
 */

public class YuanFuBean {



    private DBean d;

    public DBean getD() {
        return d;
    }

    public void setD(DBean d) {
        this.d = d;
    }

    public static class DBean {

        private int ErrorCode;
        private String Msg;
        private List<ResultBean> Result;

        public int getErrorCode() {
            return ErrorCode;
        }

        public void setErrorCode(int ErrorCode) {
            this.ErrorCode = ErrorCode;
        }

        public String getMsg() {
            return Msg;
        }

        public void setMsg(String Msg) {
            this.Msg = Msg;
        }

        public List<ResultBean> getResult() {
            return Result;
        }

        public void setResult(List<ResultBean> Result) {
            this.Result = Result;
        }

        public static class ResultBean {
            private String __type;
            private int Id;
            private String Value;
            private ArrayList<String> listChild;

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

            public String getValue() {
                return Value;
            }

            public void setValue(String Value) {
                this.Value = Value;
            }

            public ArrayList<String> getListChild() {
                return listChild;
            }

            public void setListChild(ArrayList<String> listChild) {
                this.listChild = listChild;
            }
        }
    }
}
