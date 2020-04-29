package com.example.huoshangkou.jubowan.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseApp;
import com.example.huoshangkou.jubowan.bean.VersionBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：VersionUtils
 * 描述：
 * 创建时间：2017-01-18  11:02
 */

public class VersionUtils {

    private static class VersionHelper {
        private static VersionUtils INSTANCE = new VersionUtils();
    }

    public static VersionUtils getInstance() {
        return VersionHelper.INSTANCE;
    }

    /**
     * 从manifest里面拿出版本号
     */
    public String getVersonFromXml() {
        Context context = BaseApp.getInstance();
        PackageManager manager = context.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = info.versionName;
        int version_code = info.versionCode;
        return String.valueOf(version) + "," + version_code;
    }

    /**
     * 从服务器获取版本信息
     */
    public void getVersionFromWeb(final Context context) {
        OkhttpUtil.getInstance().basePostCall(context, "", UrlConstant.getInstance().GET_VERSION, new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                VersionBean versionBean = JSON.parseObject(str, VersionBean.class);
                if (versionBean.getD().getData() == null) {
                    return;
                }
                //下载地址
                SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().VERSION_PATH, versionBean.getD().getData().getDownloadUrl());
                //更新说明
                SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().VERSION_INTRO, versionBean.getD().getData().getVersionDescription());

                String version = VersionUtils.getInstance().getVersonFromXml();
                int versionCode = Integer.parseInt(version.substring(version.indexOf(",") + 1));
                int lastVersion = versionBean.getD().getData().getVersionNo();
                //版本号
                SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().VERSION_CODD, versionBean.getD().getData().getVersionNo() + "");
                if (lastVersion > versionCode) {
                    UpdateDialogUtils.updataDialogShow(context, VersionUtils.getInstance().getVersionIntro(context));
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    //获取版本号
    public int getVersionNo(Context context) {
        String no = (String) SharedPreferencesUtils.getInstance().get(context, SharedKeyConstant.getInstance().VERSION_CODD, "");
        int code = Integer.parseInt(no);
        return code;
    }

    //获取版本号
    public String getLocalVersionNo() {
//        String no = (String) SharedPreferencesUtils.getInstance().get(context, SharedKeyConstant.getInstance().VERSION_CODD, "");
//        int code = Integer.parseInt(no);
        return getVersonNoFromXml();
    }

    //获取下载路径
    public String getVersionPath() {
        String path = (String) SharedPreferencesUtils.getInstance().get(BaseApp.getInstance(), SharedKeyConstant.getInstance().VERSION_PATH, "");
        return path;
    }

    //获取更新说明
    public String getVersionIntro(Context context) {
        String intro = (String) SharedPreferencesUtils.getInstance().get(context, SharedKeyConstant.getInstance().VERSION_INTRO, "");
        return intro;
    }

    public String getVersonNoFromXml() {
        Context context = BaseApp.getInstance();
        PackageManager manager = context.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        int version_code = info.versionCode;
        return "" + version_code;
    }
}
