package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.VAListBean;
import com.example.huoshangkou.jubowan.inter.OnPositionClick;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：PjStandardAdapter
 * 描述：我家的宝贝一直带我去干这个干囊额
 * 创建时间：2017-11-30  08:51
 */

public class PjStandardAdapter extends RecyclerView.Adapter<PjStandardAdapter.MyViewHolder> {

    private List<VAListBean> list;
    private Context context;
    private int mSelectedItem = -1;

    private OnPositionClick positionClick;

    public void setPosition(int position) {
        mSelectedItem = position;
    }

    public PjStandardAdapter(List<VAListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_standard_recylerview, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        if (position == mSelectedItem) {
            holder.rbStandard.setBackground(context.getResources().getDrawable(R.drawable.blue_circle_bg));
            holder.rbStandard.setTextColor(context.getResources().getColor(R.color.white));
        } else {
            holder.rbStandard.setBackground(context.getResources().getDrawable(R.drawable.white_circle_bg));
            holder.rbStandard.setTextColor(context.getResources().getColor(R.color.address_black_key));
        }

        holder.rbStandard.setText(list.get(position).getVoltage());

        holder.rbStandard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                positionClick.onPositionClick(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    /**
     * Recylerview的ViewHolder
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView rbStandard;

        public MyViewHolder(final View itemView) {
            super(itemView);
            rbStandard = (TextView) itemView.findViewById(R.id.rb_fu_standard);
//            rbStandard.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    itemClick.onRecyerViewClick(getAdapterPosition());
//                    notifyItemRangeChanged(0, list.size());
//                    mSelectedItem = getAdapterPosition();
//                }
//            });


        }

        public void setText(String msg) {
            rbStandard.setText(msg);
        }
    }

    public void setPositionClick(OnPositionClick positionClick) {
        this.positionClick = positionClick;
    }
}
