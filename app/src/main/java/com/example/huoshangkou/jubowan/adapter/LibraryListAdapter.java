package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.LibraryListBean;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：LibraryListAdapter
 * 描述：
 * 创建时间：2019-04-09  13:49
 */

public class LibraryListAdapter extends BaseAbstractAdapter<LibraryListBean.DBean.ResultBean> {
    public LibraryListAdapter(Context context, List<LibraryListBean.DBean.ResultBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, LibraryListBean.DBean.ResultBean bean, int position) {
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvTime = holder.getView(R.id.tv_time);
        TextView tvLibraryName= holder.getView(R.id.tv_library_name);
        TextView tvIntro = holder.getView(R.id.tv_intro);
        ImageView imgPic = holder.getView(R.id.iv_pic);

        GlideUtils.getInstance().displayFitImage(bean.getImg(),context,imgPic);
        tvName.setText(StringUtils.getNoEmptyStr(bean.getAuthor()));
        tvLibraryName.setText(bean.getTitle());
        tvTime.setText(DateUtils.getFormData(bean.getCreateTime()));
        tvIntro.setText("简介："+bean.getBriefIntroduction());
    }
}
