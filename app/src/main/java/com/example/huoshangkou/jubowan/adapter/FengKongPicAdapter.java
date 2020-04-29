package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.ImageAddFunction;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.FengKongPicBean;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.view.ScrollGridView;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：FengKongPicAdapter
 * 描述：
 * 创建时间：2019-07-23  10:26
 */

public class FengKongPicAdapter extends BaseAbstractAdapter<FengKongPicBean.DBean.ResultBean.ModelBean> {
    public FengKongPicAdapter(Context context, List<FengKongPicBean.DBean.ResultBean.ModelBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, FengKongPicBean.DBean.ResultBean.ModelBean bean, int position) {
        TextView tvTime = holder.getView(R.id.tv_time);
        ScrollGridView gridView = holder.getView(R.id.grid_fk_pic);
        List<String> imgList = bean.getFileItems();

        //图片显示封装
        ImageAddAdapter imageAddAdapter = new ImageAddAdapter(context, imgList, "FengKong");
        ImageAddFunction.addImageFun(imageAddAdapter, imgList, context, gridView);
        tvTime.setText(bean.getCreateTime());
    }
}
