package com.example.huoshangkou.jubowan.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseApp;
import com.example.huoshangkou.jubowan.base.BaseFragment;
import com.example.huoshangkou.jubowan.chat.helper.ChatLayoutHelper;
import com.example.huoshangkou.jubowan.chat.signature.Constants;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.qcloud.tim.uikit.component.AudioPlayer;
import com.tencent.qcloud.tim.uikit.component.TitleBarLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.ChatLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatManagerKit;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.message.MessageLayout;
import com.tencent.qcloud.tim.uikit.modules.group.info.GroupInfo;
import com.tencent.qcloud.tim.uikit.modules.group.info.GroupInfoActivity;
import com.tencent.qcloud.tim.uikit.modules.group.member.GroupMemberDeleteFragment;
import com.tencent.qcloud.tim.uikit.modules.group.member.GroupMemberInviteFragment;
import com.tencent.qcloud.tim.uikit.modules.group.member.GroupMemberManagerFragment;
import com.tencent.qcloud.tim.uikit.modules.group.member.IGroupMemberRouter;
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfo;
import com.tencent.qcloud.tim.uikit.utils.TUIKitConstants;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;

import butterknife.Bind;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.chat
 * 类名：ChatFragment
 * 描述：
 * 创建时间：2020-04-14  11:26
 */

public class ChatFragment extends BaseFragment {
    @Bind(R.id.chat_layout)
    ChatLayout mChatLayout;

    private ChatInfo mChatInfo;
    private TitleBarLayout mTitleBar;
    private boolean isGroup = true;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_chat;
    }

    @Override
    public void initData() {
        Bundle bundle = getArguments();
        mChatInfo = (ChatInfo) bundle.getSerializable(Constants.CHAT_INFO);
        if (mChatInfo == null) {
            return;
        }
        initView();
        // TODO 通过api设置ChatLayout各种属性的样例
        ChatLayoutHelper helper = new ChatLayoutHelper(getActivity());
        helper.customizeChatLayout(mChatLayout);
        if (mChatInfo.getType() == TIMConversationType.C2C) {
            isGroup = false;
            mChatLayout.getTitleBar().getRightIcon().setVisibility(View.GONE);
        }
        mChatLayout.getTitleBar().setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isGroup) {
                    return;
                }
                if (mChatInfo != null) {
                    Intent intent = new Intent(getContext(), ChatGroupDetailActivity.class);
                    intent.putExtra(TUIKitConstants.Group.GROUP_ID, mChatInfo.getId());
                    intent.putExtra(TUIKitConstants.Group.GROUP_NAME, mChatInfo.getChatName());
                    getContext().startActivity(intent);
                } else {
                    ToastUtil.toastLongMessage("请稍后再试试~");
                }
            }
        });
    }

    private void initView() {

        //单聊组件的默认UI和交互初始化
        mChatLayout.initDefault();

        /*
         * 需要聊天的基本信息
         */
        mChatLayout.setChatInfo(mChatInfo);

        //获取单聊面板的标题栏
        mTitleBar = mChatLayout.getTitleBar();

        //单聊面板标记栏返回按钮点击事件，这里需要开发者自行控制
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        mChatLayout.getMessageLayout().setOnItemClickListener(new MessageLayout.OnItemClickListener() {
            @Override
            public void onMessageLongClick(View view, int position, MessageInfo messageInfo) {
                //因为adapter中第一条为加载条目，位置需减1
                mChatLayout.getMessageLayout().showItemPopMenu(position - 1, messageInfo, view);
            }

            @Override
            public void onUserIconClick(View view, int position, MessageInfo messageInfo) {
                if (null == messageInfo) {
                    return;
                }
//                ChatInfo info = new ChatInfo();
//                info.setId(messageInfo.getFromUser());
//                Intent intent = new Intent(BaseApp.getInstance(), FriendProfileActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra(TUIKitConstants.ProfileType.CONTENT, info);
//                BaseApp.getInstance().startActivity(intent);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        AudioPlayer.getInstance().stopPlay();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mChatLayout != null) {
            mChatLayout.exitChat();
        }
    }

}
