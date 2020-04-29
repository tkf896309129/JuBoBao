package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.SelectManBean;
import com.example.huoshangkou.jubowan.inter.OnPositionClick;
import com.example.huoshangkou.jubowan.utils.GlideUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：CheckByManTopAdapter
 * 描述：
 * 创建时间：2020-04-27  09:06
 */

public class CheckByManTopAdapter extends RecyclerView.Adapter<CheckByManTopAdapter.ViewHolder> {

    private List<SelectManBean> list;
    private Context context;
    private OnPositionClick positionClick;

    public CheckByManTopAdapter(List<SelectManBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_check_by_man_top, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        GlideUtils.getInstance().displayCricleImage(context, list.get(position).getHeadImagePic(), holder.imgPic);
        holder.tvName.setText(list.get(position).getNickName());
        holder.imgPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                positionClick.onPositionClick(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgPic;
        private TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            imgPic = itemView.findViewById(R.id.iv_img);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }

    public void setPositionClick(OnPositionClick positionClick) {
        this.positionClick = positionClick;
    }
}
