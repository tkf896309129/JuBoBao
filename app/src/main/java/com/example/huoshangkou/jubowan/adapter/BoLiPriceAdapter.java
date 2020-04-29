package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.inter.OnBoLiCallBack;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：BoLiPriceAdapter
 * 描述：
 * 创建时间：2017-04-07  11:49
 */

public class BoLiPriceAdapter extends RecyclerView.Adapter<BoLiPriceAdapter.MyViewHolder> {

    private List<String> list;
    private int itemClick = -1;
    private OnBoLiCallBack callBack;
    private Context context;

    public BoLiPriceAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public BoLiPriceAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_boli_standard, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(BoLiPriceAdapter.MyViewHolder holder, int position) {
        holder.setText(list.get(position), position);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvStandard;

        public MyViewHolder(final View itemView) {
            super(itemView);
            tvStandard = (TextView) itemView.findViewById(R.id.tv_standard);
        }

        public void setText(String msg, final int position) {
            tvStandard.setText(msg);

            if(position == itemClick){
                tvStandard.setBackground(context.getResources().getDrawable(R.drawable.blue_all_corner));
                tvStandard.setTextColor(context.getResources().getColor(R.color.white_all));
            }else{
                tvStandard.setBackground(context.getResources().getDrawable(R.drawable.gray_corner_bg));
                tvStandard.setTextColor(context.getResources().getColor(R.color.address_black_key));
            }

            tvStandard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callBack.onClickBack(position);
                }
            });
        }
    }

    public void setItemClick(int position) {
        itemClick = position;
    }

    public void setCallBack(OnBoLiCallBack callBack) {
        this.callBack = callBack;
    }
}
