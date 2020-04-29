package com.example.huoshangkou.jubowan.chat.helper;

import android.content.Context;
import android.content.Intent;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.chat.helper
 * 类名：MeiZuHelper
 * 描述：
 * 创建时间：2020-04-27  08:48
 */

public class MeiZuHelper  {

    private static MeiZuHelper instance;

    public static MeiZuHelper getInstance(){
        if(instance ==null){
            instance = new MeiZuHelper();
        }
        return instance;
    }

    /**
     * 去魅族权限申请页面
     */
    public static void applyPermission(Context context){
        Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
        intent.setClassName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity");
        intent.putExtra("packageName", context.getPackageName());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }



}
