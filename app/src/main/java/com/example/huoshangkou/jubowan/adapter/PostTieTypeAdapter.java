package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.TieTypeBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：PostTieTypeAdapter
 * 描述：
 * 创建时间：2017-05-05  10:53
 */

public class PostTieTypeAdapter extends BaseAbstractAdapter<TieTypeBean> {


    public PostTieTypeAdapter(Context context, List<TieTypeBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, TieTypeBean bean, int position) {
        ImageView imgType = holder.getView(R.id.iv_type);
        TextView tvType = holder.getView(R.id.tv_type);

        imgType.setImageResource(bean.getImage());
        tvType.setText(bean.getType());
    }
}
