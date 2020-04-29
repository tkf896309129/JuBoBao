package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.HomeDetailBean;
import com.example.huoshangkou.jubowan.bean.HomeForumListBean;
import com.example.huoshangkou.jubowan.bean.NewHomeBean;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：HomeDataAdapter
 * 描述：
 * 创建时间：2017-02-07  14:22
 */

public class HomeDataAdapter extends BaseAbstractAdapter<NewHomeBean.DBean.ResultBean.ProjectBiddingBean.ChildBeanX> {

    public HomeDataAdapter(Context context, List<NewHomeBean.DBean.ResultBean.ProjectBiddingBean.ChildBeanX> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, NewHomeBean.DBean.ResultBean.ProjectBiddingBean.ChildBeanX bean, int position) {
        ImageView imgZb = holder.getView(R.id.img_home_zb);
        TextView tvName = holder.getView(R.id.tv_name);
        tvName.setText(bean.getName());
        GlideUtils.getInstance().displayFitImage(bean.getImage(), context, imgZb);
    }
}
