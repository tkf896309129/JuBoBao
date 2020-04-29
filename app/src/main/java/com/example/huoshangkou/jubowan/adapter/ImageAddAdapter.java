package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.constant.PhotoConstant;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.utils.WindowsUtils;
import com.example.huoshangkou.jubowan.view.ScrollGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：ImageAddAdapter
 * 描述：
 * 创建时间：2017-02-14  09:40
 */

public class ImageAddAdapter extends BaseAdapter {


    private deleteClick deleteImg;
    private detailClick detalisImg;
    private addPhotoClick addPhotoImg;
    private String type;
    private Context context;
    private List<String> listData;
    private int scrollGridViewWidth = 0;

    //考勤界面
    private String signType = "";

    public ImageAddAdapter(Context context, List<String> listData) {
        //默认不可以删除
        type = "no_delete";
        this.context = context;
        this.listData = listData;
        scrollGridViewWidth = WindowsUtils.getWidth(context);
    }

    //如果type不为空 就表示不可以删除
    public ImageAddAdapter(Context context, List<String> listData, String type) {
        this.type = type;
        this.context = context;
        this.listData = listData;
        scrollGridViewWidth = WindowsUtils.getWidth(context);
    }


    public ImageAddAdapter(Context context, List<String> listData, String type, String signType) {
        this.type = type;
        this.context = context;
        this.listData = listData;
        this.signType = signType;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int i) {
        return listData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_add_img, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (!StringUtils.isNoEmpty(signType)) {
            int height = (WindowsUtils.getWidth(context) - 60) / 3;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, height);
            holder.imageView.setLayoutParams(layoutParams);
        } else {
            int height = (WindowsUtils.getWidth(context) - 250) / 3;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, height);
            holder.imageView.setLayoutParams(layoutParams);
        }

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加照片
                if (listData.get(position).equals(PhotoConstant.getInstance().TIE_ADD_PHOTOT)) {
                    if (addPhotoImg != null) {
                        addPhotoImg.onAddPthotoClick();
                    }
                } else {
                    //照片详情
                    if (StringUtils.isNoEmpty(type) && type.equals(PhotoConstant.getInstance().TIE_TYPE)) {
                        List<String> imgList = new ArrayList<>();
                        int num = listData.size();
                        for (int i = 0; i < num; i++) {
                            if (!listData.get(i).equals(PhotoConstant.getInstance().TIE_ADD_PHOTOT)) {
                                imgList.add(listData.get(i));
                            }
                        }
                        IntentUtils.getInstance().toImageShowActivity(context, imgList, position);
                    } else {
                        IntentUtils.getInstance().toImageShowActivity(context, listData, position);
                    }
                }
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteImg.deleteImgClick(position);
            }
        });

        //发帖的时候需要传一个type进行判断 fatu_icon
        if (StringUtils.isNoEmpty(type) && !type.equals("tiezi")) {
            holder.imgDelete.setVisibility(View.GONE);
        }

        if (listData.get(position).equals("addPhoto")) {
            GlideUtils.getInstance().displayImage(context, R.mipmap.fatu_icon, holder.imageView);
            holder.imgDelete.setVisibility(View.GONE);
        } else {
            GlideUtils.getInstance().displayLongImage(listData.get(position), context, holder.imageView);
            if (StringUtils.isNoEmpty(type) && type.equals("tiezi")) {
                holder.imgDelete.setVisibility(View.VISIBLE);
            }
        }
        return convertView;
    }


    class ViewHolder {
        ImageView imageView;
        ImageView imgDelete;

        public ViewHolder(View convertView) {
            imageView = (ImageView) convertView.findViewById(R.id.iv_gonyi);
            imgDelete = (ImageView) convertView.findViewById(R.id.iv_delete_img);
        }

    }

    //删除照片
    public interface deleteClick {
        void deleteImgClick(int position);
    }

    public void setDeleteImg(deleteClick deleteImg) {
        this.deleteImg = deleteImg;
    }

    //点击详情
    public interface detailClick {
        void longImgClick(int position);
    }

    //添加照片
    public interface addPhotoClick {
        void onAddPthotoClick();
    }

    public void setAddPhotoImg(addPhotoClick addPhotoImg) {
        this.addPhotoImg = addPhotoImg;
    }

}
