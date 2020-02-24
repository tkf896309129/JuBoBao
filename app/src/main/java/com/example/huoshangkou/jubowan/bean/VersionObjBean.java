package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：VersionObjBean
 * 描述：
 * 创建时间：2017-02-22  15:48
 */

public class VersionObjBean {

    private String Path;
    private String VersionNo;
    private String VersionUpdate;

    public String getVersionUpdate() {
        return VersionUpdate;
    }

    public void setVersionUpdate(String versionUpdate) {
        VersionUpdate = versionUpdate;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    public String getVersionNo() {
        return VersionNo;
    }

    public void setVersionNo(String versionNo) {
        VersionNo = versionNo;
    }
}
