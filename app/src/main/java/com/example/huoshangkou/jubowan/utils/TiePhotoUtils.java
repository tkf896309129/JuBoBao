package com.example.huoshangkou.jubowan.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：TiePhotoUtils
 * 描述：发表帖子专用的图片选择
 * 创建时间：2017-02-13  15:48
 */

public class TiePhotoUtils {

    /**
     * 更新进度框
     */
    public static void updateDialog(final Context context) {
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        window.setContentView(R.layout.tie_select_photo);
        window.setBackgroundDrawableResource(R.color.alpha_all);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        TextView tvCamera = (TextView) window.findViewById(R.id.tv_take_photo);
        tvCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.getMineToast("拍照选择");
            }
        });


        TextView tvGallery = (TextView) window.findViewById(R.id.tv_gallery);
        tvGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.getMineToast("相册选择");
            }
        });

    }


}
