package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：BtCheckAdapter
 * 描述：
 * 创建时间：2019-03-08  19:25
 */

public class BtCheckAdapter extends BaseAbstractAdapter<String> {
    public BtCheckAdapter(Context context, List<String> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, String bean, int position) {
        TextView tvNameLeft = holder.getView(R.id.tv_man_left);
        TextView tvName = holder.getView(R.id.tv_name);

        String[] split = bean.split("、");
        if (split != null) {
            if (split.length == 1) {
                tvNameLeft.setText(split[0]);
            } else if (split.length == 2) {
                tvNameLeft.setText(split[0]);
                tvName.setText(split[1]);
            }

        }


    }
}
