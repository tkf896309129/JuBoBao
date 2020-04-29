package com.example.huoshangkou.jubowan.chat.helper;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import com.example.huoshangkou.jubowan.utils.LogUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;


import static com.tencent.liteav.TXCRenderAndDec.TAG;
import static com.umeng.socialize.utils.ContextUtil.getPackageName;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.chat.helper
 * 类名：NotifyDialogHelper
 * 描述：
 * 创建时间：2020-04-27  08:30
 */

public class NotifyDialogHelper {

    private static NotifyDialogHelper INSTANCE;

    public static NotifyDialogHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NotifyDialogHelper();
        }
        return INSTANCE;
    }


    /**
     * 获取 emui 版本号
     *
     * @return
     */
    public double getEmuiVersion() {
        try {
            String emuiVersion = getSystemProperty("ro.build.version.emui");
            String version = emuiVersion.substring(emuiVersion.indexOf("_") + 1);
            return Double.parseDouble(version);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 4.0;
    }

    /**
     * 获取小米 rom 版本号，获取失败返回 -1
     *
     * @return miui rom version code, if fail , return -1
     */
    public int getMiuiVersion() {
        String version = getSystemProperty("ro.miui.ui.version.name");
        if (version != null) {
            try {
                return Integer.parseInt(version.substring(1));
            } catch (Exception e) {
                Log.e(TAG, "get miui version code error, version : " + version);
            }
        }
        return -1;
    }

    public String getSystemProperty(String propName) {
        String line;
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + propName);
            input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
            line = input.readLine();
            input.close();
        } catch (IOException ex) {
            Log.e(TAG, "Unable to read sysprop " + propName, ex);
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    Log.e(TAG, "Exception while closing InputStream", e);
                }
            }
        }
        return line;
    }

    public boolean checkIsHuaweiRom() {
        return Build.MANUFACTURER.contains("HUAWEI");
    }

    /**
     * check if is miui ROM
     */
    public boolean checkIsMiuiRom() {
        return !TextUtils.isEmpty(getSystemProperty("ro.miui.ui.version.name"));
    }

    public  boolean checkIsMeizuRom() {
        //return Build.MANUFACTURER.contains("Meizu");
        String meizuFlymeOSFlag = getSystemProperty("ro.build.display.id");
        if (TextUtils.isEmpty(meizuFlymeOSFlag)) {
            return false;
        } else if (meizuFlymeOSFlag.contains("flyme") || meizuFlymeOSFlag.toLowerCase().contains("flyme")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * check if is 360 ROM
     */
    public boolean checkIs360Rom() {
        return Build.MANUFACTURER.contains("QiKU");
    }


    /**
     * 悬浮窗权限判断
     *
     * @param context 上下文
     * @return [ true, 有权限 ][ false, 无权限 ]
     */
    public boolean checkPermission(Context context) {
        Boolean hasPermission = false;
        if (Build.VERSION.SDK_INT < 19) {
            hasPermission = true;
        } else if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 23) {
            if (checkIsMiuiRom() || checkIsMeizuRom() || checkIsHuaweiRom() || checkIs360Rom()) {// 特殊机型
                hasPermission = opPermissionCheck(context, 24);
            } else {// 其他机型
                hasPermission = true;
            }
        } else if (Build.VERSION.SDK_INT >= 23) {// 6.0 版本之后由于 google 增加了对悬浮窗权限的管理，所以方式就统一了
            hasPermission = highVersionPermissionCheck(context);
        }
        return hasPermission;
    }

    public boolean opPermissionCheck(Context context, int op) {
        try {
            AppOpsManager manager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            Class clazz = AppOpsManager.class;
            Method method = clazz.getDeclaredMethod("checkOp", int.class, int.class, String.class);
            return AppOpsManager.MODE_ALLOWED == (int) method.invoke(manager, op, Binder.getCallingUid(), context.getPackageName());
        } catch (Exception e) {
            LogUtils.e(Log.getStackTraceString(e));
        }
        return false;
    }

    public boolean highVersionPermissionCheck(Context context) {
        try {
            Class clazz = Settings.class;
            Method canDrawOverlays = clazz.getDeclaredMethod("canDrawOverlays", Context.class);
            return (Boolean) canDrawOverlays.invoke(null, context);
        } catch (Exception e) {
            LogUtils.e(Log.getStackTraceString(e));
        }
        return false;
    }



}

