package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.MessageBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：MessageAdapter
 * 描述：
 * 创建时间：2017-04-21  09:37
 */

public class MessageAdapter extends BaseAbstractAdapter<MessageBean> {
    public MessageAdapter(Context context, List<MessageBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, MessageBean bean, int position) {
        ImageView img = holder.getView(R.id.iv_message_type);
        TextView tvMessage = holder.getView(R.id.tv_message_type);

        img.setImageResource(bean.getImage());
        tvMessage.setText(bean.getMessageType());

    }
}
