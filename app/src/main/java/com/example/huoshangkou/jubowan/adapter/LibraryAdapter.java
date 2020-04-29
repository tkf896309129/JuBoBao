package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.JuSchoolBean;
import com.example.huoshangkou.jubowan.utils.GlideUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：LibraryAdapter
 * 描述：
 * 创建时间：2019-04-04  10:12
 */

public class LibraryAdapter extends BaseAbstractAdapter<JuSchoolBean.DBean.ResultBean.BookTypeBean> {
    public LibraryAdapter(Context context, List<JuSchoolBean.DBean.ResultBean.BookTypeBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, JuSchoolBean.DBean.ResultBean.BookTypeBean bean, int position) {
        //图片类型
        ImageView imgType = holder.getView(R.id.iv_type);
        //文字类型
        TextView tvType = holder.getView(R.id.tv_type);

        GlideUtils.getInstance().displayImage(bean.getPic(), context, imgType);
        tvType.setText(bean.getTypeName());
    }
}
