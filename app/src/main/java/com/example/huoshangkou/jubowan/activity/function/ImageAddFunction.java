package com.example.huoshangkou.jubowan.activity.function;

import android.content.Context;
import android.widget.GridView;

import com.example.huoshangkou.jubowan.activity.OutWorkActivity;
import com.example.huoshangkou.jubowan.adapter.ImageAddAdapter;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.view.ScrollGridView;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity.function
 * 类名：ImageAddFunction
 * 描述：图片选择工具类
 * 创建时间：2017-02-16  09:26
 */

public class ImageAddFunction {


    /**
     * 选择图片
     */
    public static void addImageFun(final ImageAddAdapter imageAddAdapter, final List<String> imgList, final Context context, ScrollGridView scrollGridView) {

        //图片显示封装

        scrollGridView.setAdapter(imageAddAdapter);

        //删除接口回调
        imageAddAdapter.setDeleteImg(new ImageAddAdapter.deleteClick() {
            @Override
            public void deleteImgClick(final int position) {
                CopyIosDialogUtils.getInstance().getIosDialog(context, "是否删除该照片", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        imgList.remove(position);
                        imageAddAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
            }
        });
    }

    public static void addImageFun(final ImageAddAdapter imageAddAdapter, final List<String> imgList, final Context context, GridView scrollGridView) {

        //图片显示封装

        scrollGridView.setAdapter(imageAddAdapter);

        //删除接口回调
        imageAddAdapter.setDeleteImg(new ImageAddAdapter.deleteClick() {
            @Override
            public void deleteImgClick(final int position) {
                CopyIosDialogUtils.getInstance().getIosDialog(context, "是否删除该照片", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        imgList.remove(position);
                        imageAddAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
            }
        });
    }


}
