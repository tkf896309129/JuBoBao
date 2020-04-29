package com.example.huoshangkou.jubowan.chat.helper;

import android.text.TextUtils;

import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMFriendshipManager;
import com.tencent.imsdk.TIMUserProfile;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;

import java.util.HashMap;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.chat.helper
 * 类名：UpdateMesssageHelper
 * 描述：
 * 创建时间：2020-04-15  13:20
 */

public class UpdateMesssageHelper {

    private static UpdateMesssageHelper INSTANCE;

    public static UpdateMesssageHelper getInstance() {
        if (INSTANCE == null) {
            synchronized (UpdateMesssageHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UpdateMesssageHelper();
                }
            }
        }
        return INSTANCE;
    }

    public void updateProfile(String iconUrl, String nickName) {
        HashMap<String, Object> hashMap = new HashMap<>();
        // 头像
        if (!TextUtils.isEmpty(iconUrl)) {
            hashMap.put(TIMUserProfile.TIM_PROFILE_TYPE_KEY_FACEURL, iconUrl);
        }
        // 昵称
        hashMap.put(TIMUserProfile.TIM_PROFILE_TYPE_KEY_NICK, nickName);
        // 个性签名
//        hashMap.put(TIMUserProfile.TIM_PROFILE_TYPE_KEY_SELFSIGNATURE, signature);
        // 地区
//        hashMap.put(TIMUserProfile.TIM_PROFILE_TYPE_KEY_LOCATION, "sz");
        // 加我验证方式
//        hashMap.put(TIMUserProfile.TIM_PROFILE_TYPE_KEY_ALLOWTYPE, allowType);

        TIMFriendshipManager.getInstance().modifySelfProfile(hashMap, new TIMCallBack() {
            @Override
            public void onError(int i, String s) {
                ToastUtil.toastShortMessage("聊天信息初始化失败");
            }

            @Override
            public void onSuccess() {

            }
        });
    }

}
