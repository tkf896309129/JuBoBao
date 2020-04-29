package com.example.huoshangkou.jubowan.chat.thirdpush;

import android.content.Context;

import com.vivo.push.model.UPSNotificationMessage;
import com.vivo.push.sdk.OpenClientPushMessageReceiver;


public class VIVOPushMessageReceiverImpl extends OpenClientPushMessageReceiver {

    private static final String TAG = VIVOPushMessageReceiverImpl.class.getSimpleName();

    @Override
    public void onNotificationMessageClicked(Context context, UPSNotificationMessage upsNotificationMessage) {
        // 如果聊天消息，跳转到聊天界面
    }

    @Override
    public void onReceiveRegId(Context context, String regId) {
        // vivo regId有变化会走这个回调。根据官网文档，获取regId需要在开启推送的回调里面调用PushClient.getInstance(getApplicationContext()).getRegId();参考LoginActivity
    }
}
