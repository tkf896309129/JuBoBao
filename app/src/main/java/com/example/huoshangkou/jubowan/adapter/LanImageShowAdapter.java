package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.constant.PhotoConstant;
import com.example.huoshangkou.jubowan.inter.OnCommonSuccessCallBack;
import com.example.huoshangkou.jubowan.utils.DeleteAnimUtils;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：LanImageShowAdapter
 * 描述：
 * 创建时间：2017-04-20  15:32
 */

public class LanImageShowAdapter extends RecyclerView.Adapter<LanImageShowAdapter.MyViewHolder> {

    private List<String> imgList;
    private Context context;
    private deleteClick deleteImg;

    private String type;

    public LanImageShowAdapter(List<String> list, Context context) {
        imgList = list;
        this.context = context;
    }

    public LanImageShowAdapter(List<String> list, Context context, String type) {
        imgList = list;
        this.context = context;
        this.type = type;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_delete_img, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.setImg(imgList.get(position));
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtils.getInstance().toImageShowActivity(context, imgList, position);
            }
        });
        if (StringUtils.isNoEmpty(type)) {
            holder.imgDelete.setVisibility(View.GONE);
        } else {
            holder.imgDelete.setVisibility(View.VISIBLE);
        }

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteImg.deleteImgClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private ImageView imgDelete;

        public MyViewHolder(final View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.iv_gonyi);
            imgDelete = (ImageView) itemView.findViewById(R.id.iv_delete_img);
        }

        public void setImg(String msg) {
            GlideUtils.getInstance().displayImage(msg, context, img);
        }
    }


    //删除照片
    public interface deleteClick {
        void deleteImgClick(int position);
    }

    public void setDeleteImg(deleteClick deleteImg) {
        this.deleteImg = deleteImg;
    }

}
