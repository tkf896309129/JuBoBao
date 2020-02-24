package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.ChooseTypeBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：ChooseTypeAdapter
 * 描述：
 * 创建时间：2017-03-24  16:33
 */

public class ChooseTypeAdapter extends BaseAbstractAdapter<ChooseTypeBean> {

    private int itemClick = -1;

    public void setItemClick(int positoin) {
        itemClick = positoin;
    }

    public ChooseTypeAdapter(Context context, List<ChooseTypeBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, ChooseTypeBean bean, int position) {
        TextView tvType = holder.getView(R.id.tv_type);
        ImageView imgType = holder.getView(R.id.iv_check);

        tvType.setText(bean.getType());

        if (bean.isCheck()) {
            imgType.setImageResource(R.mipmap.gouxuany_icon_on);
        } else {
            imgType.setImageResource(R.mipmap.gouxuany_icon_off);
        }
    }
}
