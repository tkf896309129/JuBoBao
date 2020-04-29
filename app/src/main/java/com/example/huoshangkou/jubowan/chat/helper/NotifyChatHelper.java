package com.example.huoshangkou.jubowan.chat.helper;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.baidu.speech.utils.LogUtil;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.MainActivity;
import com.example.huoshangkou.jubowan.chat.ChatActivity;
import com.example.huoshangkou.jubowan.chat.signature.Constants;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMCustomElem;
import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMElemType;
import com.tencent.imsdk.TIMLocationElem;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMTextElem;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.chat.helper
 * 类名：NotifyChatHelper
 * 描述：
 * 创建时间：2020-04-16  16:42
 */

public class NotifyChatHelper {

    private static NotifyChatHelper INSTANCE = new NotifyChatHelper();

    public static NotifyChatHelper getInstance() {
        return INSTANCE;
    }

    public void notifyChat(Context context, TIMMessage msg) {
        String content = "";
        for (int var11 = 0; var11 < msg.getElementCount(); ++var11) {
            TIMElem elem = msg.getElement(var11);
            if (elem.getType() == TIMElemType.Sound) {
                content = content + "[语音]";
            } else if (elem.getType() == TIMElemType.File) {
                content = content + "[文件]";
            } else if (elem.getType() == TIMElemType.Text) {
                TIMTextElem e = (TIMTextElem) elem;
                content = content + e.getText();
            } else if (elem.getType() == TIMElemType.Image) {
                content = content + "[图片]";
            } else if (elem.getType() == TIMElemType.Face) {
                content = content + "[表情]";
            } else if (elem.getType() == TIMElemType.Custom) {
                TIMCustomElem var12 = (TIMCustomElem) elem;
                if (!TextUtils.isEmpty(var12.getDesc())) {
                    content = content + "[" + var12.getDesc() + "]";
                }
            } else if (elem.getType() == TIMElemType.Location) {
                TIMLocationElem var13 = (TIMLocationElem) elem;
                content = content + "[位置信息]" + var13.getDesc();
            } else if (elem.getType() == TIMElemType.Video) {
                content = content + "[视频]";
            } else {
                content = "您的账号已在其他设备登录";
            }
        }

        ChatInfo chatInfo = new ChatInfo();
        chatInfo.setType(msg.getConversation().getType());
        chatInfo.setId(msg.getConversation().getPeer());
        String title = (msg.getConversation().getType().toString().equals("Group")) ? msg.getConversation().getGroupName() : msg.getSenderNickname();
        String sendContent = (msg.getConversation().getType().toString().equals("Group")) ? msg.getSenderNickname() + ":" + content : content;
        chatInfo.setChatName(title);
        Intent intent;
        if (content.equals("您的账号已在其他设备登录")) {
            title="异地登录通知";
            intent = new Intent(context, MainActivity.class);
        } else {
            intent = new Intent(context, ChatActivity.class);
            intent.putExtra(Constants.CHAT_INFO, chatInfo);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }


        int notifyId = (int) System.currentTimeMillis();
        PendingIntent pendingIntent = PendingIntent.getActivity(context, notifyId, intent, PendingIntent.FLAG_UPDATE_CURRENT);//PendingIntent.FLAG_UPDATE_CURREN
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
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
                    .setChannelId("chat")
                    .setSmallIcon(R.mipmap.new_icon)
                    .setDefaults(Notification.DEFAULT_VIBRATE)
                    // 设置通知内容的标题
                    .setContentTitle(title)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setVisibility(Notification.VISIBILITY_PUBLIC)
                    .setDefaults(~0)
                    // 设置通知内容
                    .setContentText(sendContent)
                    .setWhen(System.currentTimeMillis())
                    .setContentIntent(pendingIntent).build();
            int unread = (int) ChatHelper.getInstance().getUnread();
            try {
                //华为桌面红点
                Bundle bunlde = new Bundle();
                bunlde.putString("package", context.getPackageName()); // 这个是包名
                bunlde.putString("class", context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()).getComponent().getClassName()); // 这个是应用启动的页面路径
                bunlde.putInt("badgenumber", unread);
                context.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", null, bunlde);
            } catch (Exception e) {
            }
            try {
                Field field = notify.getClass().getDeclaredField("extraNotification");
                Object extraNotification = field.get(notify);
                Method method = extraNotification.getClass().getDeclaredMethod("setMessageCount", int.class);
                method.invoke(extraNotification, 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            mNotificationManager.notify(randomnum, notify);
        } else {
            int unread = (int) ChatHelper.getInstance().getUnread();
            Notification notification = new Notification.Builder(context)
                    .setContentTitle(title)
                    .setContentText(sendContent)
                    .setAutoCancel(true)
                    .setWhen(System.currentTimeMillis())
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.mipmap.new_icon)
                    .setNumber(unread)
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.new_icon))
                    .build();
            mNotificationManager.notify(randomnum, notification);
            try {
                Field field = notification.getClass().getDeclaredField("extraNotification");
                Object extraNotification = field.get(notification);
                Method method = extraNotification.getClass().getDeclaredMethod("setMessageCount", int.class);
                method.invoke(extraNotification, 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            mNotificationManager.notify(randomnum, notification);
        }
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
