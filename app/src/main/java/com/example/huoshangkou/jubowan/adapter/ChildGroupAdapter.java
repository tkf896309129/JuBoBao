package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.ChildGroupBean;
import com.example.huoshangkou.jubowan.inter.OnPositionCallBack;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：ChildGroupAdapter
 * 描述：
 * 创建时间：2019-05-29  10:14
 */

public class ChildGroupAdapter extends BaseAbstractAdapter<ChildGroupBean.ReObjBean> {
    private OnPositionCallBack positionCallBack;

    public ChildGroupAdapter(Context context, List<ChildGroupBean.ReObjBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, ChildGroupBean.ReObjBean bean, final int position) {
        TextView tvGroup = holder.getView(R.id.tv_group);
        ImageView imgRight = holder.getView(R.id.img_right);
        ImageView imgChild = holder.getView(R.id.iv_child_group);

        tvGroup.setText(bean.getRoleName().trim());
        if(bean.getIsManager()==1){
            imgRight.setImageResource(R.mipmap.gouxuany_icon_on);
        }else {
            imgRight.setImageResource(R.mipmap.gouxuany_icon_off);
        }
        if(bean.getIsHaveChildChecked()==1){
            imgChild.setVisibility(View.VISIBLE);
        }else {
            imgChild.setVisibility(View.GONE);
        }
        imgRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                positionCallBack.onPositionClick(position);
            }
        });
    }

    public void setPositionCallBack(OnPositionCallBack positionCallBack) {
        this.positionCallBack = positionCallBack;
    }
}
