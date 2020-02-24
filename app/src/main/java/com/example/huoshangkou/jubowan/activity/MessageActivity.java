package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.MessageAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.MessageBean;
import com.example.huoshangkou.jubowan.constant.MessageConstant;
import com.example.huoshangkou.jubowan.utils.IntentUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：MessageActivity
 * 描述：消息通知
 * 创建时间：2017-04-21  09:28
 */

public class MessageActivity extends BaseActivity {

    MessageAdapter messageAdapter;
    List<MessageBean> messageBeanList;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_refresh)
    ListView lvRefresh;
    @Bind(R.id.x_refresh)
    XRefreshView xRefresh;
    @Bind(R.id.ll_no_data)
    LinearLayout llNoData;

    @Override
    public int initLayout() {
        return R.layout.activity_message;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        messageBeanList = new ArrayList<>();
        tvTitle.setText("消息");
        initMessageData();

        messageAdapter = new MessageAdapter(getContext(), messageBeanList, R.layout.item_message_layout);
        lvRefresh.setAdapter(messageAdapter);
        lvRefresh.setDividerHeight(0);

        xRefresh.setPullRefreshEnable(false);

        lvRefresh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (messageBeanList.get(i).getMessageType()) {

                    case "系统通知":
                        IntentUtils.getInstance().toActivity(getContext(), OrderNotifyActivity.class, MessageConstant.getInstance().SYSTEM_MESSAGE);
                        break;
                    case "原片订单":
                        IntentUtils.getInstance().toActivity(getContext(), OrderNotifyActivity.class, MessageConstant.getInstance().YP_ORDER);
                        break;
                    case "辅料订单":
                        IntentUtils.getInstance().toActivity(getContext(), OrderNotifyActivity.class, MessageConstant.getInstance().FL_ORDER);
                        break;
                    case "维修订单":
                        IntentUtils.getInstance().toActivity(getContext(), OrderNotifyActivity.class, MessageConstant.getInstance().WX_ORDER);
                        break;
                    case "审批消息":
                        IntentUtils.getInstance().toActivity(getContext(), OrderNotifyActivity.class,MessageConstant.getInstance().CHECK_MESSAGE);
                        break;
                    case "论坛消息":
                        IntentUtils.getInstance().toActivity(getContext(), OrderNotifyActivity.class,MessageConstant.getInstance().FORUM_MESSAGE);
                        break;
                    case "其他通知":
                        IntentUtils.getInstance().toActivity(getContext(), OrderNotifyActivity.class, MessageConstant.getInstance().OTHER_ORDER);
                        break;
                }
            }
        });
    }

    @OnClick({R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
        }
    }


    private void initMessageData() {
        //回复我的
        MessageBean msgReBack = new MessageBean.Builder().setMessageType("论坛消息").setImg(R.mipmap.tz_huifu_icon).build();
        MessageBean msgSystem = new MessageBean.Builder().setMessageType("系统通知").setImg(R.mipmap.tz_xttz_icon).build();

        MessageBean msgYpOrder = new MessageBean.Builder().setMessageType("原片订单").setImg(R.mipmap.tz_ypdd_icon).build();
        MessageBean msgFuOrder = new MessageBean.Builder().setMessageType("辅料订单").setImg(R.mipmap.tz_fldd_icon).build();
        MessageBean msgRepairOrder = new MessageBean.Builder().setMessageType("维修订单").setImg(R.mipmap.tz_wxdd_icon).build();
        MessageBean msgCheck = new MessageBean.Builder().setMessageType("审批消息").setImg(R.mipmap.shenpi_icon_message).build();
        MessageBean msgOtherNotie = new MessageBean.Builder().setMessageType("其他通知").setImg(R.mipmap.tz_qttz_icon).build();

//        messageBeanList.add(msgReBack);
//        messageBeanList.add(msgZan);
        messageBeanList.add(msgSystem);
        messageBeanList.add(msgYpOrder);
        messageBeanList.add(msgFuOrder);
        messageBeanList.add(msgRepairOrder);
        messageBeanList.add(msgCheck);
        messageBeanList.add(msgReBack);
        messageBeanList.add(msgOtherNotie);
    }
}
