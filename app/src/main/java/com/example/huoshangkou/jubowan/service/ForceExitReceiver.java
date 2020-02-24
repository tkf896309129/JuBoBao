package com.example.huoshangkou.jubowan.service;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.example.huoshangkou.jubowan.activity.LoginActivity;
import com.example.huoshangkou.jubowan.base.BaseApp;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.SharedValueConstant;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.hyphenate.chat.EMClient;
import com.umeng.message.UTrack;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.service
 * 类名：ForceExitReceiver
 * 描述：
 * 创建时间：2017-05-23  15:21
 */

public class ForceExitReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(final Context context, Intent intent) {
        final Activity activity = ActivityUtils.getInstance().getCurrentActivity();
        Log.d("activity_bind_blank", activity + "");
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        dialog.setTitle("温馨提示")
                .setMessage("您已经被强制退出登录，请重新登录")
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        IntentUtils.getInstance().toActivity(activity, LoginActivity.class);
//                        ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().activityLoginList);
                    }
                });
//        dialog.create().show();
    }

}
