package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.chat.mine.MineMessageBean;
import com.example.huoshangkou.jubowan.inter.OnPositionCallBack;
import com.example.huoshangkou.jubowan.inter.OnPositionClick;
import com.example.huoshangkou.jubowan.inter.OnSwipPositionCallBack;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMFriendshipManager;
import com.tencent.imsdk.TIMUserProfile;
import com.tencent.qcloud.tim.uikit.component.gatherimage.UserIconView;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.input.InputLayout;
import com.tencent.qcloud.tim.uikit.modules.conversation.base.ConversationIconView;
import com.tencent.qcloud.tim.uikit.modules.conversation.base.ConversationInfo;
import com.tencent.qcloud.tim.uikit.modules.message.MessageInfo;
import com.tencent.qcloud.tim.uikit.utils.DateTimeUtil;
import com.tencent.qcloud.tim.uikit.utils.TUIKitConstants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：ChatMessageListAdapter
 * 描述：
 * 创建时间：2020-04-08  14:38
 */

public class ChatMessageListAdapter extends BaseAbstractAdapter<MineMessageBean> {
    private OnSwipPositionCallBack deleteClick;
    private OnPositionClick onPositionClick;

    public ChatMessageListAdapter(Context context, List<MineMessageBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, MineMessageBean bean, final int position) {
        ImageView imgHead = holder.getView(R.id.iv_head);
        TextView tvRedNum = holder.getView(R.id.tv_red_num);
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvContent = holder.getView(R.id.tv_content);
        TextView tvTime = holder.getView(R.id.tv_time);
        LinearLayout tvDelete = holder.getView(R.id.tv_delete);
        RelativeLayout rlClick = holder.getView(R.id.rl_message);
        final SwipeMenuLayout swipeMenuLayout = holder.getView(R.id.swip_chat);

        tvName.setText(bean.getTitle());
        String lastMsg = bean.getLastMessage();

        tvContent.setText(Html.fromHtml(lastMsg));
        tvTime.setText(DateTimeUtil.getTimeFormatText(new Date(bean.getTime() * 1000)));
        if (!bean.isGroup()) {
            GlideUtils.getInstance().displayRoundImage(context, bean.getPic(), imgHead);
        } else {
            GlideUtils.getInstance().displayRoundImage(context, R.mipmap.chat_group, imgHead);
        }
        if (bean.getUnRead() > 0) {
            tvRedNum.setVisibility(View.VISIBLE);
            if (bean.getUnRead() > 99) {
                tvRedNum.setText("99+");
            } else {
                tvRedNum.setText("" + bean.getUnRead());
            }
        } else {
            tvRedNum.setVisibility(View.GONE);
        }


        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteClick.onSwipPositionCallBack(position, swipeMenuLayout);
            }
        });
        rlClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPositionClick.onPositionClick(position);
            }
        });

    }

    public void setDeleteClick(OnSwipPositionCallBack deleteClick) {
        this.deleteClick = deleteClick;
    }

    public void setOnPositionClick(OnPositionClick onPositionClick) {
        this.onPositionClick = onPositionClick;
    }
}
