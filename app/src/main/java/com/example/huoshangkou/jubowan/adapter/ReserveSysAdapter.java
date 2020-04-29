package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.NewHomeBean;
import com.example.huoshangkou.jubowan.utils.GlideUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：ReserveSysAdapter
 * 描述：
 * 创建时间：2020-03-17  09:06
 */

public class ReserveSysAdapter extends BaseAbstractAdapter<NewHomeBean.DBean.ResultBean.FriendDealerBean> {
    public ReserveSysAdapter(Context context, List<NewHomeBean.DBean.ResultBean.FriendDealerBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, NewHomeBean.DBean.ResultBean.FriendDealerBean bean, int position) {
        ImageView imgBg = holder.getView(R.id.iv_bg);
        TextView tvContent = holder.getView(R.id.tv_content);
        TextView tvTitle = holder.getView(R.id.tv_title);

        GlideUtils.getInstance().displayImage(bean.getImage(),context,imgBg);
        tvContent.setText(bean.getDescription());
        tvTitle.setText(bean.getName());
    }
}
