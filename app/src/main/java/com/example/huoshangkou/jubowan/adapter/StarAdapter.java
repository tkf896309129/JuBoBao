package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：StarAdapter
 * 描述：
 * 创建时间：2019-03-25  14:40
 */

public class StarAdapter extends RecyclerView.Adapter<StarAdapter.ViewHolder> {

    private Context context;
    private List<Integer> list;

    public StarAdapter(Context context, List<Integer> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_star, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position < list.get(position)) {
            holder.imgStar.setImageResource(R.mipmap.icons_star_on);
        } else {
            holder.imgStar.setImageResource(R.mipmap.icons_star_off);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgStar;

        public ViewHolder(View itemView) {
            super(itemView);
            imgStar = (ImageView) itemView.findViewById(R.id.img_star);
        }
    }

}
