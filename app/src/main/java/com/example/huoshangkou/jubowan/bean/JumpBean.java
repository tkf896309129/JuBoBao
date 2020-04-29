package com.example.huoshangkou.jubowan.bean;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.bean
 * 类名：JumpBean
 * 描述：
 * 创建时间：2020-04-07  10:11
 */

public class JumpBean {


    /**
     * pointName : com.example.tang.tangdemo
     * pointActivity : com.example.tang.tangdemo.activity.StartPageActivity
     * downUrl : https://appstore.huawei.com/app/C10892156
     * appName : 聚马物流
     * ios_schemes : jumawuliu
     * ios_appid : 1118297919
     */

    private String pointName;
    private String pointActivity;
    private String downUrl;
    private String appName;
    private String ios_schemes;
    private String ios_appid;
    private String android_class;
    private String android_package;
    private String android_category;

    public String getAndroid_category() {
        return android_category;
    }

    public void setAndroid_category(String android_category) {
        this.android_category = android_category;
    }

    public String getAndroid_package() {
        return android_package;
    }

    public void setAndroid_package(String android_package) {
        this.android_package = android_package;
    }

    public String getAndroid_class() {
        return android_class;
    }

    public void setAndroid_class(String android_class) {
        this.android_class = android_class;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public String getPointActivity() {
        return pointActivity;
    }

    public void setPointActivity(String pointActivity) {
        this.pointActivity = pointActivity;
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getIos_schemes() {
        return ios_schemes;
    }

    public void setIos_schemes(String ios_schemes) {
        this.ios_schemes = ios_schemes;
    }

    public String getIos_appid() {
        return ios_appid;
    }

    public void setIos_appid(String ios_appid) {
        this.ios_appid = ios_appid;
    }
}
