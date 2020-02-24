package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.CheckImgBean;
import com.example.huoshangkou.jubowan.inter.ChooseDialogPositionCallBack;
import com.example.huoshangkou.jubowan.utils.GlideUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：CheckImgAdapter
 * 描述：
 * 创建时间：2018-08-13  14:39
 */

public class CheckImgAdapter extends RecyclerView.Adapter<CheckImgAdapter.MyViewHolder> {

    private Context context;
    private List<CheckImgBean> checkImgBeanList;
    private LanImageShowAdapter.deleteClick deleteImg;
    private ChooseDialogPositionCallBack changePositionCallBack;


    public CheckImgAdapter(Context context, List<CheckImgBean> checkImgBeanList) {
        this.context = context;
        this.checkImgBeanList = checkImgBeanList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_zh_check, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tvName.setText(checkImgBeanList.get(position).getName());
        GlideUtils.getInstance().displayCricleImage(context, checkImgBeanList.get(position).getImg(), holder.img);

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteImg.deleteImgClick(position);
            }
        });
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (changePositionCallBack != null) {
                    changePositionCallBack.onGetMessagePosition("", position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return checkImgBeanList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private ImageView imgDelete;
        private TextView tvName;

        public MyViewHolder(final View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.iv_gonyi);
            imgDelete = (ImageView) itemView.findViewById(R.id.iv_delete_img);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);

        }

        public void setImg(String msg) {
            GlideUtils.getInstance().displayImage(msg, context, img);
        }
    }

    //删除照片
    public interface deleteClick {
        void deleteImgClick(int position);
    }

    public void setDeleteImg(LanImageShowAdapter.deleteClick deleteImg) {
        this.deleteImg = deleteImg;
    }

    public void setChangePositionCallBack(ChooseDialogPositionCallBack changePositionCallBack) {
        this.changePositionCallBack = changePositionCallBack;
    }
}
