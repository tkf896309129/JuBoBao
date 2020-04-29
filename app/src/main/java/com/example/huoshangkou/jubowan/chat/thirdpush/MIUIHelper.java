package com.example.huoshangkou.jubowan.chat.thirdpush;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;

import com.example.huoshangkou.jubowan.chat.helper.NotifyDialogHelper;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.chat.thirdpush
 * 类名：MIUIHelper
 * 描述：
 * 创建时间：2020-04-27  08:39
 */

public class MIUIHelper {


    private static MIUIHelper INSTANCE;

    public static MIUIHelper getInstance(){
        if(INSTANCE == null){
            INSTANCE = new MIUIHelper();
        }
        return INSTANCE;
    }

    /**
     * 获取小米 rom 版本号，获取失败返回 -1
     *
     * @return miui rom version code, if fail , return -1
     */
    public  int getMiuiVersion() {
        String version = NotifyDialogHelper.getInstance().getSystemProperty("ro.miui.ui.version.name");
        if (version != null) {
            try {
                return Integer.parseInt(version.substring(1));
            } catch (Exception e) {
                Log.e("this", "get miui version code error, version : " + version);
                Log.e("this", Log.getStackTraceString(e));
            }
        }
        return -1;
    }

    /**
     * 小米 ROM 权限申请
     */
    public  void applyMiuiPermission(Context context) {
        int versionCode = getMiuiVersion();
        if (versionCode == 5) {
            goToMiuiPermissionActivity_V5(context);
        } else if (versionCode == 6) {
            goToMiuiPermissionActivity_V6(context);
        } else if (versionCode == 7) {
            goToMiuiPermissionActivity_V7(context);
        } else if (versionCode == 8) {
            goToMiuiPermissionActivity_V8(context);
        } else {
            Log.e("this", "this is a special MIUI rom version, its version code " + versionCode);
        }
    }

    private  boolean isIntentAvailable(Intent intent, Context context) {
        if (intent == null) {
            return false;
        }
        return context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).size() > 0;
    }

    /**
     * 小米 V5 版本 ROM权限申请
     */
    private  void goToMiuiPermissionActivity_V5(Context context) {
        Intent intent = null;
        String packageName = context.getPackageName();
        intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", packageName, null);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (isIntentAvailable(intent, context)) {
            context.startActivity(intent);
        } else {
            Log.e("this", "intent is not available!");
        }
    }

    /**
     * 小米 V6 版本 ROM权限申请
     */
    private  void goToMiuiPermissionActivity_V6(Context context) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        intent.putExtra("extra_pkgname", context.getPackageName());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (isIntentAvailable(intent, context)) {
            context.startActivity(intent);
        } else {
            Log.e("this", "Intent is not available!");
        }
    }

    /**
     * 小米 V7 版本 ROM权限申请
     */
    private  void goToMiuiPermissionActivity_V7(Context context) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        intent.putExtra("extra_pkgname", context.getPackageName());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (isIntentAvailable(intent, context)) {
            context.startActivity(intent);
        } else {
            Log.e("this", "Intent is not available!");
        }
    }

    /**
     * 小米 V8 版本 ROM权限申请
     */
    private   void goToMiuiPermissionActivity_V8(Context context) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
        intent.putExtra("extra_pkgname", context.getPackageName());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (isIntentAvailable(intent, context)) {
            context.startActivity(intent);
        } else {
            Log.e("this", "Intent is not available!");
        }
    }

}
