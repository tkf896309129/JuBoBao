package com.example.huoshangkou.jubowan.chat.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.tencent.imsdk.TIMGroupMemberInfo;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.chat
 * 类名：ChatGroupMemberAdapter
 * 描述：
 * 创建时间：2020-04-20  08:31
 */

public class ChatGroupMemberAdapter extends BaseAbstractAdapter<TIMGroupMemberInfo> {
    public ChatGroupMemberAdapter(Context context, List<TIMGroupMemberInfo> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, TIMGroupMemberInfo bean, int position) {
        ImageView imgPic = holder.getView(R.id.img_pic);
        TextView tvName = holder.getView(R.id.tv_name);
//        GlideUtils.getInstance().displayRoundImage(context);
        tvName.setText(bean.getUser());

    }
}
