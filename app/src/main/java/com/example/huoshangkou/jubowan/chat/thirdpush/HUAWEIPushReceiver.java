package com.example.huoshangkou.jubowan.chat.thirdpush;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.huoshangkou.jubowan.chat.mine.MineMessageListActivity;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.huawei.hms.support.api.push.PushReceiver;

import java.nio.charset.StandardCharsets;

public class HUAWEIPushReceiver extends PushReceiver {

    private static final String TAG = HUAWEIPushReceiver.class.getSimpleName();

    @Override
    public boolean onPushMsg(Context context, byte[] msgBytes, Bundle extras) {
        try {
            //CP可以自己解析消息内容，然后做相应的处理
            String content = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                content = new String(msgBytes, StandardCharsets.UTF_8);
                LogUtils.e(content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(context, MineMessageListActivity.class);
        context.startActivity(intent);
        return false;
    }

    @Override
    public void onToken(Context context, String token, Bundle extras) {
        ThirdPushTokenMgr.getInstance().setThirdPushToken(token);
        ThirdPushTokenMgr.getInstance().setPushTokenToTIM();
    }

}
