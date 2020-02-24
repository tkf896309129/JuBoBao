package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.ServiceFactoryListBean;
import com.example.huoshangkou.jubowan.bean.ServiceSheBeiListBean;
import com.example.huoshangkou.jubowan.inter.OnPositionClick;
import com.example.huoshangkou.jubowan.utils.BlueTxtUtils;
import com.example.huoshangkou.jubowan.utils.GlideUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：ServiceSecondAdapter
 * 描述：
 * 创建时间：2018-06-13  09:29
 */

public class ServiceSellerAdapter extends RecyclerView.Adapter<ServiceSellerAdapter.ServiceSecondViewHolder> {

    List<ServiceFactoryListBean> list;
    Context context;
    OnPositionClick positionClick;


    public ServiceSellerAdapter(List<ServiceFactoryListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public ServiceSecondViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载普通布局
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service_seller, parent, false);
        return new ServiceSecondViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(ServiceSecondViewHolder holder, int position) {
        //绑定数据
        holder.setData(list.get(position), position);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ServiceSecondViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCompany;
        TextView tvName;
        LinearLayout rlClick;

        public ServiceSecondViewHolder(View itemView) {
            super(itemView);
            imgCompany = (ImageView) itemView.findViewById(R.id.iv_hot_pj);
            tvName = (TextView) itemView.findViewById(R.id.tv_hot_name);
            rlClick = (LinearLayout) itemView.findViewById(R.id.rl_click);
        }

        public void setData(final ServiceFactoryListBean listBean, final int position) {
            GlideUtils.getInstance().displayImage(listBean.getPic(), context, imgCompany);
            tvName.setText(listBean.getName());
            rlClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    positionClick.onPositionClick(position);
                }
            });
        }
    }

    public void setPositionClick(OnPositionClick positionClick) {
        this.positionClick = positionClick;
    }
}
