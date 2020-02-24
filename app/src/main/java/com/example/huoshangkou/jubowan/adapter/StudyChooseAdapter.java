package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：StudyChooseAdapter
 * 描述：
 * 创建时间：2019-11-05  13:07
 */

public class StudyChooseAdapter extends BaseAbstractAdapter<String> {
    public StudyChooseAdapter(Context context, List<String> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, String bean, int position) {
        TextView tvName = holder.getView(R.id.tv_name);
        ImageView imgBg = holder.getView(R.id.iv_bg);

        tvName.setText(bean);
        switch (bean) {
            case "通知制度区":
                imgBg.setImageResource(R.mipmap.inform);
                break;
            case "技能学习区":
                imgBg.setImageResource(R.mipmap.study);
                break;
        }


    }
}
