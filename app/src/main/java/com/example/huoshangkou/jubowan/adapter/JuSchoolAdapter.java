package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.JuSchoolHomeBean;
import com.example.huoshangkou.jubowan.utils.GlideUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：JuSchoolAdapter
 * 描述：
 * 创建时间：2019-04-09  09:50
 */

public class JuSchoolAdapter extends BaseAbstractAdapter<JuSchoolHomeBean.DBean.ResultBean.JuboSchoolPagesBean> {
    public JuSchoolAdapter(Context context, List<JuSchoolHomeBean.DBean.ResultBean.JuboSchoolPagesBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, JuSchoolHomeBean.DBean.ResultBean.JuboSchoolPagesBean bean, int position) {
        //图片类型
        ImageView imgType = holder.getView(R.id.iv_type);
        //文字类型
        TextView tvType = holder.getView(R.id.tv_type);

        GlideUtils.getInstance().displayFitImage(bean.getSmallPic(), context, imgType);
        tvType.setText(bean.getName());
    }
}
