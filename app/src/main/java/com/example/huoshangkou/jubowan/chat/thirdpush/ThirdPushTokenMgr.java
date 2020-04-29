package com.example.huoshangkou.jubowan.chat.thirdpush;

import android.text.TextUtils;
import android.util.Log;

import com.example.huoshangkou.jubowan.chat.signature.PrivateConstants;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMOfflinePushToken;
import com.tencent.imsdk.utils.IMFunc;

/**
 * 用来保存厂商注册离线推送token的管理类示例，当登陆im后，通过 setOfflinePushToken 上报证书 ID 及设备 token 给im后台。开发者可以根据自己的需求灵活实现
 */

public class ThirdPushTokenMgr {

    public static final boolean USER_GOOGLE_FCM = false;
    private static final String TAG = ThirdPushTokenMgr.class.getSimpleName();
    private String mThirdPushToken;

    public static ThirdPushTokenMgr getInstance() {
        return ThirdPushTokenHolder.instance;
    }

    public String getThirdPushToken() {
        return mThirdPushToken;
    }

    public void setThirdPushToken(String mThirdPushToken) {
        this.mThirdPushToken = mThirdPushToken;
    }

    public void setPushTokenToTIM() {
        String token = ThirdPushTokenMgr.getInstance().getThirdPushToken();
        if (TextUtils.isEmpty(token)) {
            return;
        }
        TIMOfflinePushToken param = null;
        if (USER_GOOGLE_FCM) {
            param = new TIMOfflinePushToken(PrivateConstants.GOOGLE_FCM_PUSH_BUZID, token);
        } else if (IMFunc.isBrandXiaoMi()) {
            param = new TIMOfflinePushToken(PrivateConstants.XM_PUSH_BUZID, token);
        } else if (IMFunc.isBrandHuawei()) {
            param = new TIMOfflinePushToken(PrivateConstants.HW_PUSH_BUZID, token);
        } else if (IMFunc.isBrandMeizu()) {
            param = new TIMOfflinePushToken(PrivateConstants.MZ_PUSH_BUZID, token);
        } else if (IMFunc.isBrandOppo()) {
            param = new TIMOfflinePushToken(PrivateConstants.OPPO_PUSH_BUZID, token);
        } else if (IMFunc.isBrandVivo()) {
            param = new TIMOfflinePushToken(PrivateConstants.VIVO_PUSH_BUZID, token);
        } else {
            return;
        }
        TIMManager.getInstance().setOfflinePushToken(param, new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {
                Log.d(TAG, "setOfflinePushToken err code = " + code);
            }

            @Override
            public void onSuccess() {
                Log.d(TAG, "setOfflinePushToken success");
            }
        });
    }

    private static class ThirdPushTokenHolder {
        private static final ThirdPushTokenMgr instance = new ThirdPushTokenMgr();
    }
}
