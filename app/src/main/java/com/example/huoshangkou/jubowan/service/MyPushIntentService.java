package com.example.huoshangkou.jubowan.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.icu.text.DateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.CheckWorkActivity;
import com.example.huoshangkou.jubowan.activity.MainActivity;
import com.example.huoshangkou.jubowan.activity.MessageActivity;
import com.example.huoshangkou.jubowan.activity.MineApproveActivity;
import com.example.huoshangkou.jubowan.activity.MineBackTieActivity;
import com.example.huoshangkou.jubowan.activity.MyStudyActivity;
import com.example.huoshangkou.jubowan.activity.WebActivity;
import com.example.huoshangkou.jubowan.bean.NotifyMessageBean;
import com.example.huoshangkou.jubowan.constant.ApproveConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.umeng.message.UmengMessageService;
import com.umeng.message.common.UmLog;
import com.umeng.message.entity.UMessage;

import org.android.agoo.common.AgooConstants;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import me.leolin.shortcutbadger.ShortcutBadger;


/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.service
 * 类名：MyPushIntentService
 * 描述：
 * 创建时间：2017-05-04  09:42
 */
public class MyPushIntentService extends UmengMessageService {
    private static final String TAG = MyPushIntentService.class.getName();
    private static final int PUSH_NOTIFICATION_ID = (0x001);
    private static final String PUSH_CHANNEL_ID = "PUSH_NOTIFY_ID";
    private static final String PUSH_CHANNEL_NAME = "PUSH_NOTIFY_NAME";
    private Context mContext;

    private int i = 0;
    private int NOTIFICATION_ID;

    @Override
    public void onMessage(Context context, Intent intent) {
        try {
            mContext = context;
            //可以通过MESSAGE_BODY取得消息体
            String message = intent.getStringExtra(AgooConstants.MESSAGE_BODY);
            LogUtils.e("通知：" + message);
            NotifyMessageBean bodyBean = JSON.parseObject(message, NotifyMessageBean.class);
            UMessage msg = new UMessage(new JSONObject(message));
            UmLog.d(TAG, "message=" + message);      //消息体
            UmLog.d(TAG, "custom=" + msg.custom);    //自定义消息的内容
            UmLog.d(TAG, "title=" + msg.title);      //通知标题
            UmLog.d(TAG, "text=" + msg.text);        //通知内容
            // code  to handle message here
            // ...
            i++;

            addNotification(context, bodyBean, msg);
        } catch (Exception e) {
            UmLog.e(TAG, e.getMessage());
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    public void addNotification(Context context, NotifyMessageBean bodyBean, UMessage msg) {
        //NOTIFICATION_ID = args.getInt(6);
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NOTIFICATION_ID = (int) (Math.random() * 10000);

        Intent intentTo = null;
        JSONObject object = null;
        try {
            object = new JSONObject(bodyBean.getBody().getCustom());
            int messageType = object.getInt("msgtype");
            String url = object.getString("url");
            LogUtils.e("url:" + url);
            if (StringUtils.isNoEmpty(url)) {
                intentTo = new Intent(context, WebActivity.class);
                SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().NOTIFY_CLICK, url);
            } else {
                //0:系统 1:订单(原片订单) 2 辅料订单 3 维修维保通知 4 审批消息 5论坛消息 6其他通知 7考勤通知 8抄送通知 9申请完成
                switch (messageType) {
                    case 0:
                        intentTo = new Intent(context, MessageActivity.class);
                        break;
                    case 1:
                    case 2:
                    case 3:
                        intentTo = new Intent(context, MainActivity.class);
                        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().NOTIFY_CLICK, "to_order");
                        break;
                    case 4:
                        intentTo = new Intent(context, MineApproveActivity.class);
                        intentTo.putExtra(IntentUtils.getInstance().TYPE, ApproveConstant.getInstance().APPROVE);
                        break;
                    case 5:
                        intentTo = new Intent(context, MineBackTieActivity.class);
                        break;
                    case 6:
                        intentTo = new Intent(context, MessageActivity.class);
                        break;
                    case 7:
                        intentTo = new Intent(context, CheckWorkActivity.class);
                        break;
                    case 8:
                        intentTo = new Intent(context, MineApproveActivity.class);
                        intentTo.putExtra(IntentUtils.getInstance().TYPE, ApproveConstant.getInstance().MINE_ZH);
                        break;
                    case 9:
                        intentTo = new Intent(context, MineApproveActivity.class);
                        intentTo.putExtra(IntentUtils.getInstance().TYPE, ApproveConstant.getInstance().APPLY);
                        break;
                    case 10:
                        intentTo = new Intent(context, MyStudyActivity.class);
                        intentTo.putExtra(IntentUtils.getInstance().TYPE, "通知制度区");
                        break;
                    case 11:
                        intentTo = new Intent(context, MyStudyActivity.class);
                        intentTo.putExtra(IntentUtils.getInstance().TYPE, "技能学习区");
                        break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (intentTo == null) {
            return;
        }
        showNotification(context, msg, intentTo);
    }

    // 通知栏显示当前播放信息，利用通知和 PendingIntent来启动对应的activity
    public void showNotification(Context context, UMessage msg, Intent intent) {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //设置推送渠道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "chat";
            String channelName = "聊天消息";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(mNotificationManager, channelId, channelName, importance);
        }
        int randomnum = (int) (Math.random() * 100);
        if (Build.VERSION.SDK_INT >= 26) {
            Notification notify = new Notification.Builder(context)
                    // 设置打开该通知，该通知自动消失
                    .setAutoCancel(true)
                    // 设置显示在状态栏的通知提示信息
                    .setTicker(msg.text)
                    // 设置通知的图标
                    .setChannelId("chat")
                    .setSmallIcon(R.mipmap.new_icon)
                    .setDefaults(Notification.DEFAULT_VIBRATE)
                    // 设置通知内容的标题
                    .setContentTitle(msg.title)
                    // 设置通知内容
                    .setContentText(msg.text)
                    .setWhen(System.currentTimeMillis())
                    // 设改通知将要启动程序的Intent
                    .setContentIntent(PendingIntent.getActivity(context, 0, intent, 0)).build();
            try {
                //华为桌面红点
                Bundle bunlde = new Bundle();
                bunlde.putString("package", context.getPackageName()); // 这个是包名
                bunlde.putString("class", context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()).getComponent().getClassName()); // 这个是应用启动的页面路径
                bunlde.putInt("badgenumber", i);
                context.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", null, bunlde);
            } catch (Exception e) {

            }
            try {
                Field field = notify.getClass().getDeclaredField("extraNotification");
                Object extraNotification = field.get(notify);
                Method method = extraNotification.getClass().getDeclaredMethod("setMessageCount", int.class);
                method.invoke(extraNotification, i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            mNotificationManager.notify(randomnum, notify);
        } else {
            Notification notification = new Notification.Builder(context)
                    .setContentTitle(msg.title)
                    .setContentText(msg.text)
                    .setAutoCancel(true)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.new_icon)
                    .setNumber(i++)
                    .setContentIntent(PendingIntent.getActivity(context, 0, intent, 0))
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.new_icon))
                    .build();
            mNotificationManager.notify(randomnum, notification);
        }
        //8.0以下版本桌面红点显示
        ShortcutBadger.applyCount(context, i);
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(NotificationManager notificationManager, String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        channel.setShowBadge(true);
        channel.enableLights(true);
        channel.setBypassDnd(true);
        channel.enableVibration(true);
        channel.setLightColor(Color.RED);
        notificationManager.createNotificationChannel(channel);
    }
}
