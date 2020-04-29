package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.ProjectGlassBean;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.view.EasyShadow;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：ProjectGlassAdapter
 * 描述：
 * 创建时间：2019-03-26  10:11
 */

public class ProjectGlassAdapter extends BaseAbstractAdapter<ProjectGlassBean.DBean.ResultBean> {
    public ProjectGlassAdapter(Context context, List<ProjectGlassBean.DBean.ResultBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, ProjectGlassBean.DBean.ResultBean bean, int position) {
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvJiaGon = holder.getView(R.id.tv_jia_gon);
        TextView tvService = holder.getView(R.id.tv_service);
        TextView tvAddress = holder.getView(R.id.tv_address);
        ImageView imgProject = holder.getView(R.id.img_project_glass);
        RelativeLayout rlProject = holder.getView(R.id.rl_project);
//        rlProject.setBackground(new EasyShadow(5,new int[R.color.main_tab_blue],5,R.color.main_tab_blue,5,5,5));

        GlideUtils.getInstance().displayImage(bean.getShowPic(), context, imgProject);
        tvName.setText(bean.getCompany());
        tvJiaGon.setText("加工产能：" + bean.getJiagongChangNeng() + "㎡");
        tvService.setText("服务满意度：" + bean.getManyiDuVal() + "%");
        tvAddress.setText(bean.getAddress());
    }
}
