package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.OrderTypeBean;
import com.example.huoshangkou.jubowan.utils.GlideUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：ToolListAdapter
 * 描述：
 * 创建时间：2018-03-06  10:23
 */

public class ToolListAdapter extends BaseAbstractAdapter<OrderTypeBean> {

    private boolean isType = false;

    public ToolListAdapter(Context context, List<OrderTypeBean> listData, int resId,boolean isType ) {
        super(context, listData, resId);
        this.isType = isType;
    }

    @Override
    public void convert(ViewHolder holder, OrderTypeBean bean, int position) {

        ImageView imgTool = holder.getView(R.id.iv_pj_name);
        TextView tvTool = holder.getView(R.id.tv_pj_name);

        GlideUtils.getInstance().displayImage(bean.getPic(),context,imgTool);
        if(isType){
            tvTool.setText(bean.getName());
        }else{
            tvTool.setText(bean.getClassTitle());
        }
    }
}
