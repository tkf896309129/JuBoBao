package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.StudyBean;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：MyStudyAdapter
 * 描述：
 * 创建时间：2019-11-04  09:42
 */

public class MyStudyAdapter extends BaseAbstractAdapter<StudyBean.DBean.ReListBean> {
    public MyStudyAdapter(Context context, List<StudyBean.DBean.ReListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, StudyBean.DBean.ReListBean bean, int position) {
        TextView tvTitle = holder.getView(R.id.tv_title);
        TextView tvContent = holder.getView(R.id.tv_content);
        TextView tvFileType = holder.getView(R.id.tv_file_type);
        ImageView imgPic = holder.getView(R.id.iv_img);
        TextView tvTime = holder.getView(R.id.tv_time);
        if (StringUtils.isNoEmpty(bean.getPicture())) {
            imgPic.setVisibility(View.VISIBLE);
        } else {
            imgPic.setVisibility(View.GONE);
        }
        List<String> listImage = PhotoUtils.getInstance().getListImage(bean.getPicture());
        if (listImage.size() > 0) {
            GlideUtils.getInstance().displayImage(listImage.get(0), context, imgPic);
        }
        tvTime.setText(DateUtils.getFormDesData(bean.getCreateTime()));
        tvTitle.setText(bean.getTitle());
        tvContent.setText(bean.getTxtContent());
//        tvFileType.setTextColor(Color.parseColor("255 161 115"));
        if (StringUtils.isNoEmpty(bean.getColor())) {
            String[] split = bean.getColor().split(" ");
            if (split.length == 3) {
                tvFileType.setTextColor(Color.rgb(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2])));
            }
        }
        switch (bean.getFileLevel()) {
            case 0:
                tvFileType.setText("一般文件");
                break;
            case 1:
                tvFileType.setText("重要文件");
                break;
            case 2:
                tvFileType.setText("私密文件");
                break;
        }
    }
}
