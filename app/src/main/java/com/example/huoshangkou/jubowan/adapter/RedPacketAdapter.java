package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.RedListBean;
import com.example.huoshangkou.jubowan.utils.GlideUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：RedPacketAdapter
 * 描述：
 * 创建时间：2017-10-17  13:53
 */

public class RedPacketAdapter extends BaseAbstractAdapter<RedListBean> {
    public RedPacketAdapter(Context context, List<RedListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, RedListBean bean, int position) {
        ImageView imgPic = holder.getView(R.id.iv_red_packet);
        TextView tvTitle = holder.getView(R.id.tv_title);
        TextView tvTime = holder.getView(R.id.tv_time);
        TextView tvPrice = holder.getView(R.id.tv_price);

        GlideUtils.getInstance().displayCricleImage(context, bean.getLinkManPic(), imgPic);

        tvTime.setText(bean.getCreateTime());
        tvTitle.setText(bean.getLinkMan());
        tvPrice.setText(bean.getLuckyMoney());
    }
}
