package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.HomeMenuBean;
import com.example.huoshangkou.jubowan.bean.NewHomeBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.utils.GlideUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：HomeMenuAdapter
 * 描述：
 * 创建时间：2019-03-21  08:53
 */

public class HomeMenuAdapter extends BaseAbstractAdapter<NewHomeBean.DBean.ResultBean.NavigationBarBean> {
    public HomeMenuAdapter(Context context, List<NewHomeBean.DBean.ResultBean.NavigationBarBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, NewHomeBean.DBean.ResultBean.NavigationBarBean bean, int position) {
        ImageView imgHomeMenu = holder.getView(R.id.img_home_menu);
        TextView tvName = holder.getView(R.id.tv_name);
        tvName.setText(bean.getName());
//        Glide.with(context).load(bean.getImage()).centerCrop().into(imgHomeMenu);
        GlideUtils.getInstance().displayCricleImage(context, bean.getImage(), imgHomeMenu);
    }
}
