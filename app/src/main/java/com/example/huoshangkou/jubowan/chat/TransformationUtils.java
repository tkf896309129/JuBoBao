package com.example.huoshangkou.jubowan.chat;

import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.request.target.ImageViewTarget;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.chat
 * 类名：TransformationUtils
 * 描述：
 * 创建时间：2020-04-09  09:51
 */

public class TransformationUtils extends ImageViewTarget<Bitmap> {

    private ImageView target;
    private int height;
    private int width;

    public TransformationUtils(ImageView target, int height, int width) {
        super(target);
        this.target = target;
        this.height = height;
        this.width = width;
    }

    @Override
    protected void setResource(Bitmap resource) {
        view.setImageBitmap(resource);

        ViewGroup.LayoutParams params = target.getLayoutParams();
        params.height = height;
        params.width = width;
        target.setLayoutParams(params);
    }

}
