package com.example.huoshangkou.jubowan.chat.helper;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.chat.ChatActivity;
import com.example.huoshangkou.jubowan.chat.mine.MineMessageBean;
import com.example.huoshangkou.jubowan.chat.signature.Constants;
import com.example.huoshangkou.jubowan.chat.signature.GenerateChatUserSig;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMElemType;
import com.tencent.imsdk.TIMGroupSystemElem;
import com.tencent.imsdk.TIMGroupSystemElemType;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.tencent.qcloud.tim.uikit.modules.conversation.base.ConversationInfo;
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfo;
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfoUtil;
import com.tencent.qcloud.tim.uikit.utils.SharedPreferenceUtils;
import com.tencent.qcloud.tim.uikit.utils.TUIKitLog;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.chat.helper
 * 类名：ChatHelper
 * 描述：
 * 创建时间：2020-04-15  13:39
 */

public class ChatHelper {

    private static ChatHelper INSTANCE;

    public static ChatHelper getInstance() {
        if (INSTANCE == null) {
            synchronized (ChatHelper.class) {
                INSTANCE = new ChatHelper();
            }
        }
        return INSTANCE;
    }

    public void startChatActivity(Context context, MineMessageBean conversationInfo) {
        ChatInfo chatInfo = new ChatInfo();
        chatInfo.setType(conversationInfo.isGroup() ? TIMConversationType.Group : TIMConversationType.C2C);
        chatInfo.setId(conversationInfo.getChatId());
        chatInfo.setChatName(conversationInfo.getTitle());
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra(Constants.CHAT_INFO, chatInfo);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void toPersonChat(Context context, String id, String nickName) {
        ChatInfo chatInfo = new ChatInfo();
        chatInfo.setType(TIMConversationType.C2C);
        chatInfo.setId(id);
        String chatName = StringUtils.getNoEmptyStr(nickName);
        chatInfo.setChatName(chatName);
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra(Constants.CHAT_INFO, chatInfo);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void toGroupChat(Context context, String id, String nickName){
        ChatInfo chatInfo = new ChatInfo();
        chatInfo.setType(TIMConversationType.Group);
        chatInfo.setId(id);
        chatInfo.setChatName(nickName);
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra(Constants.CHAT_INFO, chatInfo);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void loginOutChat() {
        TUIKit.unInit();
    }

    //即时通讯登录
    public void loginChat(String id, final String nickName, final String headPic) {
        String userSig = GenerateChatUserSig.genTestUserSig(id);

        TUIKit.login(id, userSig, new IUIKitCallBack() {
            @Override
            public void onError(String module, final int code, final String desc) {

            }

            @Override
            public void onSuccess(Object data) {
                UpdateMesssageHelper.getInstance().updateProfile(headPic, nickName);
            }
        });
    }

    //判断是否登录
    public void loginChatYON(Context context) {
        //判断是否退出登录
        String loginState = (String) SharedPreferencesUtils.getInstance().get(context, SharedKeyConstant.getInstance().LOGIN_STATE, "");
        //登录状态
        String loginUser = TIMManager.getInstance().getLoginUser();
        LogUtils.e("loginUser"+loginUser);
        if (StringUtils.isNoEmpty(loginState) && loginState.equals("login_success")) {
            //判断聊天是否登录
            if (loginUser == null) {
                ChatHelper.getInstance().loginChat(PersonConstant.getInstance().getUserId(), PersonConstant.getInstance().getRealName(context), PersonConstant.getInstance().getHeadPic(context));
            }
        }
    }

    public long getUnread() {
        long unRead = 0;
        List<TIMConversation> TIMConversations = TIMManager.getInstance().getConversationList();
        for (int i = 0; i < TIMConversations.size(); i++) {
            TIMConversation conversation = TIMConversations.get(i);
            //将imsdk TIMConversation转换为UIKit ConversationInfo
            unRead += conversation.getUnreadMessageNum();
        }
        return unRead;
    }

    public void unReadShow(long count, TextView tvUnRead){
        if (tvUnRead == null) {
            return;
        }
        if (count > 0) {
            tvUnRead.setVisibility(View.VISIBLE);
        } else {
            tvUnRead.setVisibility(View.GONE);
        }
        String unreadStr = "" + count;
        if (count > 100) {
            unreadStr = "99+";
        }
        tvUnRead.setText(unreadStr);
    }
}
