package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.MineTypeBean;
import com.example.huoshangkou.jubowan.bean.QuaMenuBean;
import com.example.huoshangkou.jubowan.chat.helper.ChatHelper;
import com.example.huoshangkou.jubowan.inter.OnGetViewCallBack;
import com.example.huoshangkou.jubowan.inter.OnListViewDeleteCallBack;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.ListViewUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：MineTypeAdapter
 * 描述：个人中心类适配器
 * 创建时间：2017-02-06  09:25
 */

public class MineTypeAdapter extends BaseAbstractAdapter<QuaMenuBean.DBean.ReListBean> {

    private OnGetViewCallBack viewCallBack;

    private int itemClickPosition = -1;

    public MineTypeAdapter(Context context, List<QuaMenuBean.DBean.ReListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, QuaMenuBean.DBean.ReListBean bean, final int position) {
        //图片类型
        ImageView imgType = holder.getView(R.id.iv_type);
        //文字类型
        TextView tvType = holder.getView(R.id.tv_type);
        TextView tvUnread = holder.getView(R.id.tv_unread);
        imgType.setImageResource(bean.getImg());
        tvType.setText(bean.getName());
        if(bean.getUnRead()==0){
            tvUnread.setVisibility(View.GONE);
        }else {
            tvUnread.setVisibility(View.VISIBLE);
        }

    }

    public OnGetViewCallBack getViewCallBack() {
        return viewCallBack;
    }

    public void setViewCallBack(OnGetViewCallBack viewCallBack) {
        this.viewCallBack = viewCallBack;
    }

    public int getItemClickPosition() {
        return itemClickPosition;
    }

    public void setItemClickPosition(int itemClickPosition) {
        this.itemClickPosition = itemClickPosition;
    }
}
