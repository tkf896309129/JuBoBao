package com.example.huoshangkou.jubowan.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.example.huoshangkou.jubowan.R;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：CallUtils
 * 描述：拨打电话工具类封装
 * 创建时间：2017-01-16  13:18
 */

public class CallUtils {

    public static void callMan(Context context, String tel) {
        if (!StringUtils.isNoEmpty(tel)) {
            ToastUtils.getMineToast(context.getString(R.string.empty_phone));
            return;
        }

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
//        intent.setAction(Intent.ACTION_DIAL);
//        intent.setAction("android.intent.action.CALL"); //以下各项皆如此，都有两种写法。
        intent.setData(Uri.parse("tel:" + tel));
        context.startActivity(intent);
    }


}
