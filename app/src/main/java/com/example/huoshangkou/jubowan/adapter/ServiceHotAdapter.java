package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.ServicePjListBean;
import com.example.huoshangkou.jubowan.inter.OnPositionClick;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.GlideUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：ServiceSecondAdapter
 * 描述：
 * 创建时间：2018-06-13  09:29
 */

public class ServiceHotAdapter extends RecyclerView.Adapter<ServiceHotAdapter.ServiceSecondViewHolder> {

    private List<ServicePjListBean> peiJianList;
    OnPositionClick positionClick;
    Context context;

    public ServiceHotAdapter(List<ServicePjListBean> peiJianList, Context context) {
        this.peiJianList = peiJianList;
        this.context = context;
    }


    @Override
    public ServiceSecondViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载普通布局
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hot_pj, parent, false);
        return new ServiceSecondViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(ServiceSecondViewHolder holder, int position) {
        holder.setData(peiJianList.get(position),position);
    }

    @Override
    public int getItemCount() {
        return peiJianList == null ? 0 : peiJianList.size();
    }

    class ServiceSecondViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHot;
        TextView tvName;
        TextView tvPrice;
        TextView tvBy;
        RelativeLayout rlClick;

        public ServiceSecondViewHolder(View itemView) {
            super(itemView);
            imgHot = (ImageView) itemView.findViewById(R.id.iv_hot_pj);
            tvName = (TextView) itemView.findViewById(R.id.tv_hot_name);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_hot_price);
            tvBy = (TextView) itemView.findViewById(R.id.tv_hot_by);
            rlClick = (RelativeLayout) itemView.findViewById(R.id.rl_click);
        }


        public void setData(final ServicePjListBean listBean, final int positon) {
            GlideUtils.getInstance().displayImage(listBean.getPic(), context, imgHot);
            tvName.setText(listBean.getName());
            tvPrice.setText("￥" + listBean.getPrices());

            rlClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    positionClick.onPositionClick(positon);
                }
            });
        }
    }

    public void setPositionClick(OnPositionClick positionClick) {
        this.positionClick = positionClick;
    }
}
