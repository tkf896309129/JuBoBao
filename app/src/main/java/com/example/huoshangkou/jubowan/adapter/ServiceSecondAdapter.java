package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.ServiceSheBeiListBean;
import com.example.huoshangkou.jubowan.inter.OnPositionClick;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
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

public class ServiceSecondAdapter extends RecyclerView.Adapter<ServiceSecondAdapter.ServiceSecondViewHolder> {

    List<ServiceSheBeiListBean> list;
    Context context;
    OnPositionClick positionClick;


    public ServiceSecondAdapter(List<ServiceSheBeiListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public ServiceSecondViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载普通布局
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service_second_tool, parent, false);
        return new ServiceSecondViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(ServiceSecondViewHolder holder, int position) {
        //绑定数据
        holder.setData(list.get(position),position);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ServiceSecondViewHolder extends RecyclerView.ViewHolder {
        ImageView imgService;
        TextView tvName;
        TextView tvBrand;
        TextView tvTime;
        TextView tvPrice;
        RelativeLayout rlClick;

        public ServiceSecondViewHolder(View itemView) {
            super(itemView);
            imgService = (ImageView) itemView.findViewById(R.id.iv_service_second);
            tvName = (TextView) itemView.findViewById(R.id.tv_second_name);
            tvBrand = (TextView) itemView.findViewById(R.id.tv_second_brand);
            tvTime = (TextView) itemView.findViewById(R.id.tv_second_time);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_second_price);
            rlClick = (RelativeLayout) itemView.findViewById(R.id.rl_click);
        }

        public void setData(final ServiceSheBeiListBean listBean, final int position) {
            GlideUtils.getInstance().displayImage(listBean.getPic(), context, imgService);

            tvName.setText("名称：" + listBean.getName());
            tvBrand.setText("品牌：" + listBean.getBrand());
            tvTime.setText("出厂日期：" + listBean.getTime());
            tvPrice.setText(Html.fromHtml("参考价：" + BlueTxtUtils.getInstance().getBlueTxt("￥" + listBean.getPrices()) + "元"));

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
