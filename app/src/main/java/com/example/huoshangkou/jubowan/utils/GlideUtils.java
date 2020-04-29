package com.example.huoshangkou.jubowan.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.chat.GlideRoundTransform;
import com.example.huoshangkou.jubowan.view.GlideCircleTransform;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：GlideUtils
 * 描述：
 * 创建时间：2016-12-29  15:41
 */

public class GlideUtils {

    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    private static class ImageLoaderHolder {
        private static final GlideUtils INSTANCE = new GlideUtils();
    }

    private GlideUtils() {
    }

    public static final GlideUtils getInstance() {
        return ImageLoaderHolder.INSTANCE;
    }

    //直接加载网络图片
    private void displayImage(String url, Context context, ImageView imageView, int placeholderImage, int failureImage) {
        Glide.with(context)
                .load(url)
                .placeholder(placeholderImage)
                .error(failureImage)
                .centerCrop()
                .into(imageView);
    }

    public void displayImage(String url, Context context, ImageView imageView) {
//        if (!StringUtils.isNoEmpty(url)) {
//            return;
//        }
        Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.default_img)
                .error(R.mipmap.default_img)
                .centerCrop()
                .into(imageView);
    }


    public void displayFitImage(String url, Context context, ImageView imageView) {

//        if (!StringUtils.isNoEmpty(url)) {
//            return;
//        }

        Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.default_img)
                .error(R.mipmap.default_img)
                .fitCenter()
                .into(imageView);
    }

    public void displayLongImage(String url, Context context, ImageView imageView) {
        displayImage(url, context, imageView, R.mipmap.default_img, R.mipmap.default_img);
    }

    //加载SD卡图片
    public void displayImage(Context context, File file, ImageView imageView) {
        Glide
                .with(context)
                .load(file)
                .centerCrop()
                .into(imageView);
    }

    //加载SD卡图片并设置大小
    public void displayImage(Context context, File file, ImageView imageView, int width, int height) {
        Glide
                .with(context)
                .load(file)
                .override(width, height)
                .centerCrop()
                .into(imageView);
    }

    //加载网络图片并设置大小
    public void displayImage(Context context, String url, ImageView imageView, int width, int height) {
        Glide
                .with(context)
                .load(url)
                .centerCrop()
                .override(width, height)
                .into(imageView);
    }

    //加载drawable图片
    public void displayImage(Context context, int resId, ImageView imageView) {
        Glide.with(context)
                .load(resourceIdToUri(context, resId))
                .fitCenter()
                .into(imageView);
    }

    //加载drawable图片显示为圆形图片
    public void displayCricleImage(Context context, int resId, ImageView imageView) {
        Glide.with(context)
                .load(resourceIdToUri(context, resId))
                .placeholder(R.mipmap.touxiang_icon)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(imageView);
    }

    //加载网络图片显示为圆形图片
    public void displayCricleImage(Context context, String url, ImageView imageView) {
        Glide
                .with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.mipmap.touxiang_icon)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(imageView);
    }

    //加载SD卡图片显示为圆形图片
    public void displayCricleImage(Context context, File file, ImageView imageView) {
        Glide.with(context)
                .load(file)
                //.centerCrop()
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(imageView);
    }

    public void displayRoundImage(Context context, String img, ImageView imageView) {
        RoundedCorners roundedCorners = new RoundedCorners(10);
        Glide.with(context)
                .load(img)
                //.centerCrop()
                .placeholder(R.mipmap.default_img)
                .apply(RequestOptions.bitmapTransform(roundedCorners))
                .into(imageView);
    }

    public void displayRoundImage(Context context, int img, ImageView imageView) {
        RoundedCorners roundedCorners = new RoundedCorners(10);
        Glide.with(context)
                .load(img)
                //.centerCrop()
                .placeholder(R.mipmap.default_img)
                .apply(RequestOptions.bitmapTransform(roundedCorners))
                .into(imageView);
    }


    //将资源ID转为Uri
    public Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
    }


    public static Bitmap getBitmap(String path) throws IOException {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == 200) {
            InputStream inputStream = conn.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        }
        return null;
    }
}
