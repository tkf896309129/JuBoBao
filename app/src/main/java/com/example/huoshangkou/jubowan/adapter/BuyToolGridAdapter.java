package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.ToolNewClassBean;
import com.example.huoshangkou.jubowan.utils.GlideUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：BuyToolGridAdapter
 * 描述：
 * 创建时间：2018-06-07  15:33
 */

public class BuyToolGridAdapter extends BaseAbstractAdapter<ToolNewClassBean> {
    public BuyToolGridAdapter(Context context, List<ToolNewClassBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, ToolNewClassBean bean, int position) {
        ImageView imgTool = holder.getView(R.id.iv_buy_tool);
        TextView tvName = holder.getView(R.id.tv_name);

        tvName.setText(bean.getClassTitle());
        GlideUtils.getInstance().displayImage(bean.getPic(), context, imgTool);


    }
}
