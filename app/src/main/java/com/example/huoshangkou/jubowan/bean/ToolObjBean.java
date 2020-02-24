package com.example.huoshangkou.jubowan.bean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：ToolObjBean
 * 描述：
 * 创建时间：2018-06-13  13:41
 */

public class ToolObjBean {

    private List<ToolNewClassBean> NewClass;
    private List<ToolNewClassBean> OldClass;

    public List<ToolNewClassBean> getNewClass() {
        return NewClass;
    }

    public void setNewClass(List<ToolNewClassBean> newClass) {
        NewClass = newClass;
    }

    public List<ToolNewClassBean> getOldClass() {
        return OldClass;
    }

    public void setOldClass(List<ToolNewClassBean> oldClass) {
        OldClass = oldClass;
    }
}
