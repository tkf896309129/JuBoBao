package com.example.huoshangkou.jubowan.chat.thirdpush;

import android.content.Context;
import android.content.Intent;

import com.example.huoshangkou.jubowan.chat.mine.MineMessageListActivity;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

import java.util.List;

public class XiaomiMsgReceiver extends PushMessageReceiver {
    private static final String TAG = "XiaomiMsgReceiver";
    private String mRegId;

    @Override
    public void onReceivePassThroughMessage(Context context, MiPushMessage miPushMessage) {
        LogUtils.d(TAG, "onReceivePassThroughMessage is called. ");
    }

    @Override
    public void onNotificationMessageClicked(Context context, MiPushMessage miPushMessage) {
        // 如果聊天消息，跳转到聊天界面
        LogUtils.e(TAG, "onNotificationMessageClicked miPushMessage " + miPushMessage.toString());
        Intent intent = new Intent(context, MineMessageListActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onNotificationMessageArrived(Context context, MiPushMessage miPushMessage) {
        LogUtils.e(TAG, "onNotificationMessageArrived is called. ");
    }

    @Override
    public void onReceiveRegisterResult(Context context, MiPushCommandMessage miPushCommandMessage) {
        String command = miPushCommandMessage.getCommand();
        List<String> arguments = miPushCommandMessage.getCommandArguments();
        String cmdArg1 = ((arguments != null && arguments.size() > 0) ? arguments.get(0) : null);
        if (MiPushClient.COMMAND_REGISTER.equals(command)) {
            if (miPushCommandMessage.getResultCode() == ErrorCode.SUCCESS) {
                mRegId = cmdArg1;
            }
        }
        ThirdPushTokenMgr.getInstance().setThirdPushToken(mRegId);
        ThirdPushTokenMgr.getInstance().setPushTokenToTIM();
    }

    @Override
    public void onCommandResult(Context context, MiPushCommandMessage miPushCommandMessage) {
        super.onCommandResult(context, miPushCommandMessage);

    }
}
