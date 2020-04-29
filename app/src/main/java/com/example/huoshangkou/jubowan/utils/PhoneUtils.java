package com.example.huoshangkou.jubowan.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;


/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：PhoneUtils
 * 描述：拨打电话工具类
 * 创建时间：2017-02-08  09:43
 */

public class PhoneUtils {

    private static class PhoneHelper {
        private static PhoneUtils INSTANCE = new PhoneUtils();
    }

    public static PhoneUtils getInstance() {
        return PhoneHelper.INSTANCE;
    }

    /**
     * 直接拨打电话
     */
    public void callSomeOne(String phone, Context context) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phone));
        context.startActivity(intent);
    }

    /**
     * 调用拨打电话的面板
     */
    public void callDialog(String phone, Context context) {
        if (!StringUtils.isNoEmpty(phone)) {
            ToastUtils.getMineToast("电话号码为空");
            return;
        }
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, 1);
        } else {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phone));
            context.startActivity(intent);
        }
    }

    //打电话
    public void callPhoe(final Context context) {
        CopyIosDialogUtils.getInstance().getIosDialog(context, "联系客服\n400-602-0128", new CopyIosDialogUtils.iosDialogClick() {
            @Override
            public void confimBtn() {
                callDialog("4006020128", context);
            }

            @Override
            public void cancelBtn() {

            }
        });
    }
}
