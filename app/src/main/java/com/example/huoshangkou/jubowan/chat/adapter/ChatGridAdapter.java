package com.example.huoshangkou.jubowan.chat.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.chat.ChatGridBean;
import com.example.huoshangkou.jubowan.utils.GlideUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.chat
 * 类名：ChatGridAdapter
 * 描述：
 * 创建时间：2020-04-09  09:10
 */

public class ChatGridAdapter extends BaseAbstractAdapter<ChatGridBean> {
    public ChatGridAdapter(Context context, List<ChatGridBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, ChatGridBean bean, int position) {
        ImageView imgType = holder.getView(R.id.iv_type);
        TextView tvType = holder.getView(R.id.tv_type);

        GlideUtils.getInstance().displayImage(context,bean.getTypeImg(),imgType);
        tvType.setText(bean.getType());

    }
}
