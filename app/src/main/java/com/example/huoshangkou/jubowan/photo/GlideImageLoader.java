package com.example.huoshangkou.jubowan.photo;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import com.example.huoshangkou.jubowan.utils.GlideUtils;

import cn.finalteam.galleryfinal.widget.GFImageView;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.photo
 * 类名：GlideImageLoader
 * 描述：
 * 创建时间：2017-01-04  11:05
 */

public class GlideImageLoader implements cn.finalteam.galleryfinal.ImageLoader {
    @Override
    public void displayImage(Activity activity, String path, GFImageView imageView, Drawable defaultDrawable, int width, int height) {
        GlideUtils.getInstance().displayLongImage(path, activity, imageView);
    }

    @Override
    public void clearMemoryCache() {

    }
}
