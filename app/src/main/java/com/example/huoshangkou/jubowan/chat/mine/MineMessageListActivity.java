package com.example.huoshangkou.jubowan.chat.mine;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.AddGroupActivity;
import com.example.huoshangkou.jubowan.adapter.ChatMessageListAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.chat.ChatActivity;
import com.example.huoshangkou.jubowan.chat.GroupAddActivity;
import com.example.huoshangkou.jubowan.chat.helper.ChatHelper;
import com.example.huoshangkou.jubowan.chat.signature.Constants;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.inter.OnPositionCallBack;
import com.example.huoshangkou.jubowan.inter.OnPositionClick;
import com.example.huoshangkou.jubowan.inter.OnSwipPositionCallBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageListener;
import com.tencent.imsdk.ext.group.TIMGroupManagerExt;
import com.tencent.imsdk.group.GroupBaseManager;
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationManagerKit;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationProvider;
import com.tencent.qcloud.tim.uikit.modules.conversation.base.ConversationInfo;
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfo;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.chat.mine
 * 类名：MineMessageListActivity
 * 描述：
 * 创建时间：2020-04-14  15:03
 */

public class MineMessageListActivity extends BaseActivity implements TIMMessageListener {
    @Bind(R.id.lv_message)
    ListView lvMessage;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    public List<MineMessageBean> conversationList = new ArrayList<>();
    private ChatMessageListAdapter messageListAdapter;

    @Override
    public int initLayout() {
        return R.layout.activity_message_mine_list;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ivRight.setImageResource(R.mipmap.chat_add_group);
        messageListAdapter = new ChatMessageListAdapter(this, conversationList, R.layout.item_chat_message_list);
        lvMessage.setAdapter(messageListAdapter);
        lvMessage.setDividerHeight(0);
        //判断聊天是否登录
        ChatHelper.getInstance().loginChatYON(this);
        //设置消息监听器，收到新消息时，通过此监听器回调
        TIMManager.getInstance().addMessageListener(this);
        messageListAdapter.setDeleteClick(new OnSwipPositionCallBack() {
            @Override
            public void onSwipPositionCallBack(final int position, final SwipeMenuLayout swipeMenuLayout) {
                CopyIosDialogUtils.getInstance().getIosDialog(getContext(), "是否删除该消息", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        MineMessageBean conversationInfo = conversationList.get(position);
                        boolean status = TIMManager.getInstance().deleteConversationAndLocalMsgs(conversationInfo.isGroup() ? TIMConversationType.Group : TIMConversationType.C2C, conversationInfo.getChatId());
                        if (status) {
                            ToastUtil.toastLongMessage("删除成功");
                            conversationList.remove(position);
                            messageListAdapter.notifyDataSetChanged();
                        }
                        swipeMenuLayout.quickClose();
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
            }
        });
        messageListAdapter.setOnPositionClick(new OnPositionClick() {
            @Override
            public void onPositionClick(int position) {
                ChatHelper.getInstance().startChatActivity(getContext(), conversationList.get(position));
            }
        });

        getMessage();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA}, 1);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getMessage();
    }


    public void getMessage() {
        ConversationManagerKit.getInstance().loadConversation(new IUIKitCallBack() {
            @Override
            public void onSuccess(Object data) {
                conversationList.clear();
                ConversationProvider conversationProvider = (ConversationProvider) data;
                List<ConversationInfo> dataSource = conversationProvider.getDataSource();
                for (ConversationInfo info : dataSource) {
                    String message = info.getLastMessage().getExtra().toString();
                    if (message.contains("创建群组")) {
                        if (message.contains(PersonConstant.getInstance().getUserId())) {
                            MessageInfo messageInfo = new MessageInfo();
                            String changeMessage = message.replaceAll(PersonConstant.getInstance().getUserId(), PersonConstant.getInstance().getRealName(getContext()));
                            messageInfo.setExtra(changeMessage);
                            info.setLastMessage(messageInfo);
                            conversationList.add(new MineMessageBean.Builder()
                                    .setChatId(info.getId())
                                    .setGroup(info.isGroup())
                                    .setTitle(info.getTitle())
                                    .setLastMessage("")
                                    .setPic(info.getIconUrlList().size() == 0 ? "" : info.getIconUrlList().get(0).toString())
                                    .setTime(info.getLastMessageTime())
                                    .setUnRead(info.getUnRead())
                                    .build());
                        }
                    } else {
                        conversationList.add(new MineMessageBean.Builder()
                                .setChatId(info.getId())
                                .setGroup(info.isGroup())
                                .setTitle(info.getTitle())
                                .setLastMessage(info.getLastMessage().getExtra().toString())
                                .setPic(info.getIconUrlList().size() == 0 ? "" : info.getIconUrlList().get(0).toString())
                                .setTime(info.getLastMessageTime())
                                .setUnRead(info.getUnRead())
                                .build());
                    }
                }
                messageListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                ToastUtil.toastLongMessage("加载消息失败");
            }
        });
    }

    @Override
    public boolean onNewMessages(List<TIMMessage> list) {
        getMessage();
        String str = ChatHelper.getInstance().getUnread() == 0 ? "" : "(" + ChatHelper.getInstance().getUnread() + ")";
        if (tvTitle != null) {
            tvTitle.setText("消息列表" + str);
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        String str = ChatHelper.getInstance().getUnread() == 0 ? "" : "(" + ChatHelper.getInstance().getUnread() + ")";
        tvTitle.setText("消息列表" + str);
    }

    @OnClick({R.id.ll_back, R.id.iv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.iv_right:
                IntentUtils.getInstance().toGroupAddActivity(this);
                break;
        }
    }
}
