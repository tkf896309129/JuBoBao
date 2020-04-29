package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.HomeBrandListBean;
import com.example.huoshangkou.jubowan.bean.NewHomeBean;
import com.example.huoshangkou.jubowan.utils.GlideUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：RecyclerViewBrandAdapter
 * 描述：
 * 创建时间：2018-07-18  14:05
 */

public class RecyclerViewBrandAdapter extends RecyclerView.Adapter<RecyclerViewBrandAdapter.MyViewHolder> {
    private List<NewHomeBean.DBean.ResultBean.NavigationBarBean> list;
    //自定义点击事件
    private static OnRecycleItemClick itemClick;
    private Context context;

    public RecyclerViewBrandAdapter(Context context, List<NewHomeBean.DBean.ResultBean.NavigationBarBean> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_trade_price, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewBrandAdapter.MyViewHolder holder, final int position) {
        holder.setText(list.get(position));
        holder.tvBrands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.onRecyerViewClick(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgBrand;
        private TextView tvBrands;

        public MyViewHolder(final View itemView) {
            super(itemView);
            imgBrand = (ImageView) itemView.findViewById(R.id.img_brand);
            tvBrands = (TextView) itemView.findViewById(R.id.tv_brands);
        }

        public void setText(NewHomeBean.DBean.ResultBean.NavigationBarBean msg) {
            tvBrands.setText(msg.getName());
            GlideUtils.getInstance().displayImage(msg.getImage(), context, imgBrand);
        }
    }

    public interface OnRecycleItemClick {
        void onRecyerViewClick(int position);
    }

    public OnRecycleItemClick getItemClick() {
        return itemClick;
    }

    public void setItemClick(OnRecycleItemClick itemClick) {
        this.itemClick = itemClick;
    }

}
