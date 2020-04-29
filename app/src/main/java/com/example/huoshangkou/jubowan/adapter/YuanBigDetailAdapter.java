package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.YuanBigDetailBean;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.StarUtils;
import com.example.huoshangkou.jubowan.view.ScrollGridView;

import java.util.List;
import java.util.Random;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：YuanBigDetailAdapter
 * 描述：
 * 创建时间：2019-03-25  14:18
 */

public class YuanBigDetailAdapter extends BaseAbstractAdapter<YuanBigDetailBean.DBean.ResultBean> {
    public YuanBigDetailAdapter(Context context, List<YuanBigDetailBean.DBean.ResultBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, YuanBigDetailBean.DBean.ResultBean bean, int position) {
//        RecyclerView gridView = holder.getView(R.id.grid_star);
//        StarUtils.setStars(context,new Random().nextInt(5),gridView);
        ImageView imgPic = holder.getView(R.id.iv_pic);
        TextView tvName = holder.getView(R.id.tv_name);
        tvName.setText(bean.getBrandName());
        GlideUtils.getInstance().displayFitImage(bean.getPic(),context,imgPic);

    }
}
