package com.example.huoshangkou.jubowan.chat;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.ChatMessageListAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.chat.signature.Constants;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.message.MessageListAdapter;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationLayout;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationListLayout;
import com.tencent.qcloud.tim.uikit.modules.conversation.base.ConversationInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.chat
 * 类名：MessageListActivity
 * 描述：
 * 创建时间：2020-04-08  14:29
 */

public class MessageListActivity extends BaseActivity {
    @Bind(R.id.conversation_layout)
    ConversationLayout mConversationLayout;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Override
    public int initLayout() {
        return R.layout.activity_message_list_chat;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("消息列表");
        tvRight.setText("创建群聊");
        // 会话列表面板的默认UI和交互初始化
        mConversationLayout.initDefault();
        mConversationLayout.getConversationList().setOnItemClickListener(new ConversationListLayout.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, ConversationInfo conversationInfo) {
                startChatActivity(conversationInfo);
            }
        });

    }

    private void startChatActivity(ConversationInfo conversationInfo) {
        ChatInfo chatInfo = new ChatInfo();
        chatInfo.setType(conversationInfo.isGroup() ? TIMConversationType.Group : TIMConversationType.C2C);
        chatInfo.setId(conversationInfo.getId());
        chatInfo.setChatName(conversationInfo.getTitle());
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra(Constants.CHAT_INFO, chatInfo);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @OnClick({R.id.ll_back, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_right:
                IntentUtils.getInstance().toGroupAddActivity(this);
                break;
        }
    }

}
