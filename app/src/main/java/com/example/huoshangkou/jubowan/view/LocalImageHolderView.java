package com.example.huoshangkou.jubowan.view;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.example.huoshangkou.jubowan.utils.GlideUtils;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.view
 * 类名：LocalImageHolderView
 * 描述：banner图的ImageHolderView
 * 创建时间：2016-12-29  15:38
 */

public class LocalImageHolderView implements Holder<String> {

    private ImageView imageView;

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        GlideUtils.getInstance().displayLongImage(data, context, imageView);
    }

}
