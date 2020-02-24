package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.MessageTypeDetailListBean;
import com.example.huoshangkou.jubowan.bean.MessageTypeListBean;
import com.example.huoshangkou.jubowan.constant.OrderTypeConstant;
import com.example.huoshangkou.jubowan.inter.OnDeleteCallBack;
import com.example.huoshangkou.jubowan.view.ScrollListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：OrderNotifyAdapter
 * 描述：
 * 创建时间：2017-04-24  09:38
 */

public class OrderNotifyAdapter extends BaseAbstractAdapter<MessageTypeListBean> {

    OnDeleteCallBack deleteCallBack;

    public OrderNotifyAdapter(Context context, List<MessageTypeListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, final MessageTypeListBean bean, final int position) {
        TextView tvOrderState = holder.getView(R.id.tv_state);
        ScrollListView lvMessage = holder.getView(R.id.lv_order_notify);
        TextView tvTime = holder.getView(R.id.tv_time);
        TextView tvOrderType = holder.getView(R.id.tv_order_type);
        LinearLayout llClick = holder.getView(R.id.ll_click);
        //通知类型
        switch (bean.getMessageType()){
            case "1":
                tvOrderState.setText("原片专区");
                break;
            case "2":
                tvOrderState.setText("辅料专区");
                break;
            case "3":
                tvOrderState.setText("维修专区");
                break;
        }

        tvOrderType.setText(bean.getMessageTitle());
        List<MessageTypeDetailListBean> detailList = new ArrayList<>();

        if (bean.getFLList() != null) {
            List<MessageTypeDetailListBean> flList = bean.getFLList();
            int num = flList.size();
            for (int i = 0; i < num; i++) {
                flList.get(i).setType(OrderTypeConstant.getInstance().FL);
                detailList.add(flList.get(i));
            }
        }

        if (bean.getWXList() != null) {
            List<MessageTypeDetailListBean> flList = bean.getWXList();
            int num = flList.size();
            for (int i = 0; i < num; i++) {
                flList.get(i).setType(OrderTypeConstant.getInstance().WX);
                detailList.add(flList.get(i));
            }
        }

        if (bean.getYPList() != null) {
            List<MessageTypeDetailListBean> flList = bean.getYPList();
            int num = flList.size();
            for (int i = 0; i < num; i++) {
                flList.get(i).setType(OrderTypeConstant.getInstance().YP);
                detailList.add(flList.get(i));
            }
        }

        tvTime.setText(bean.getCreatTime());

        MessageItemAdapter itemAdapter = new MessageItemAdapter(context, detailList, R.layout.item_order_message);
        lvMessage.setAdapter(itemAdapter);
        lvMessage.setDividerHeight(0);

        tvOrderState.setText(bean.getMessageContent());

        lvMessage.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                deleteCallBack.onDelete(bean.getID() + "", position);
                return false;
            }
        });

        llClick.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                deleteCallBack.onDelete(bean.getID() + "", position);
                return false;
            }
        });

    }

    public void setDeleteCallBack(OnDeleteCallBack deleteCallBack) {
        this.deleteCallBack = deleteCallBack;
    }
}
