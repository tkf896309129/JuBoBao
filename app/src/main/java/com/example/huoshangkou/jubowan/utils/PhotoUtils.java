package com.example.huoshangkou.jubowan.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseApp;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.FileCallBack;
import com.example.huoshangkou.jubowan.inter.OnImageUpCallBack;
import com.example.huoshangkou.jubowan.inter.OnUpVideoCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.photo.PhotoCallBack;
import com.example.huoshangkou.jubowan.photo.bean.ImageUrl;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileCallback;


import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：PhotoUtils
 * 描述：照片选择工具类
 * 创建时间：2017-01-04  10:35
 */

public class PhotoUtils {
    private static class PhotoHelper {
        private static PhotoUtils INSTANCE = new PhotoUtils();
    }

    public static PhotoUtils getInstance() {
        return PhotoHelper.INSTANCE;
    }

    //图片名称
    private String name = "JuBoBao";

    private AlertDialog dialog;
    public final int OPEN_GALLERY = 1;
    public final int OPEN_CAMERA = 2;
    public final int POST_SUCCESS = 3;
    public final int POST_FAIL = 4;

    private PhotoCallBack callBackPhoto;
    private Context contexts;

    //是否是多选
    private boolean isMutilChoose = false;

    public void getPhotoSelectUtils(final Activity context, PhotoCallBack callBack) {
        dialog = new AlertDialog.Builder(context).create();
        callBackPhoto = callBack;
        contexts = context;

        dialog.show();
        dialog.getWindow().setContentView(R.layout.utils_photo_selelct);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setBackgroundDrawableResource(R.color.alpha_all);
        dialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setWindowAnimations(R.style.PopupAnimation);
        Window view = dialog.getWindow();

        WindowManager.LayoutParams lp = view.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        view.setAttributes(lp);

        RelativeLayout rlCancel = (RelativeLayout) view.findViewById(R.id.rl_cancel);
        TextView tvCancel = (TextView) view.findViewById(R.id.tv_photo_cancel);
        TextView tvOpenCamera = (TextView) view.findViewById(R.id.tv_open_camera);
        TextView tvOpenGallery = (TextView) view.findViewById(R.id.tv_open_gallery);
        //取消点击事件
        rlCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        //打开相机
        tvOpenCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                } else {
                    GalleryFinal.openCamera(OPEN_CAMERA, hanlderResultCallback);
                }
                dialog.dismiss();
            }
        });
        //打开相册
        tvOpenGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GalleryFinal.openGallerySingle(OPEN_GALLERY, hanlderResultCallback);
                dialog.dismiss();
            }
        });
    }

    //多图片选择
    public void getMorePhoto(final Activity context, final int num, PhotoCallBack callBack) {
        dialog = new AlertDialog.Builder(context).create();
        callBackPhoto = callBack;
        contexts = context;

        dialog.show();
        dialog.getWindow().setContentView(R.layout.utils_photo_selelct);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setBackgroundDrawableResource(R.color.alpha_all);
        dialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setWindowAnimations(R.style.PopupAnimation);
        Window view = dialog.getWindow();

        WindowManager.LayoutParams lp = view.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        view.setAttributes(lp);

        RelativeLayout rlCancel = (RelativeLayout) view.findViewById(R.id.rl_cancel);
        TextView tvCancel = (TextView) view.findViewById(R.id.tv_photo_cancel);
        TextView tvOpenCamera = (TextView) view.findViewById(R.id.tv_open_camera);
        TextView tvOpenGallery = (TextView) view.findViewById(R.id.tv_open_gallery);
        //取消点击事件
        rlCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        //打开相机
        tvOpenCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                } else {
                    isMutilChoose = true;
                    GalleryFinal.openCamera(OPEN_CAMERA, hanlderResultCallback);
                }
//                GalleryFinal.openCamera();
                dialog.dismiss();
            }
        });

        //打开相册
        tvOpenGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                GalleryFinal.openGallerySingle(OPEN_GALLERY, hanlderResultCallback);
                isMutilChoose = true;
                GalleryFinal.openGalleryMuti(OPEN_GALLERY, num, hanlderResultCallback);
                dialog.dismiss();
            }
        });
    }

    //多图片选择
    public void getMoreLocalPhoto(final Activity context, final int num, PhotoCallBack callBack) {
        dialog = new AlertDialog.Builder(context).create();
        callBackPhoto = callBack;
        contexts = context;

        dialog.show();
        dialog.getWindow().setContentView(R.layout.utils_photo_selelct);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setBackgroundDrawableResource(R.color.alpha_all);
        dialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setWindowAnimations(R.style.PopupAnimation);
        Window view = dialog.getWindow();

        WindowManager.LayoutParams lp = view.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        view.setAttributes(lp);

        RelativeLayout rlCancel = (RelativeLayout) view.findViewById(R.id.rl_cancel);
        TextView tvCancel = (TextView) view.findViewById(R.id.tv_photo_cancel);
        TextView tvOpenCamera = (TextView) view.findViewById(R.id.tv_open_camera);
        TextView tvOpenGallery = (TextView) view.findViewById(R.id.tv_open_gallery);
        //取消点击事件
        rlCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        //打开相机
        tvOpenCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                } else {
                    GalleryFinal.openCamera(OPEN_CAMERA, hanlderResultCallback);
                    isMutilChoose = true;
                }
//                GalleryFinal.openCamera();
                dialog.dismiss();
            }
        });

        //打开相册
        tvOpenGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                GalleryFinal.openGallerySingle(OPEN_GALLERY, hanlderResultCallback);
                isMutilChoose = true;
                GalleryFinal.openGalleryMuti(OPEN_GALLERY, num, hanlderResultCallback);
                dialog.dismiss();
            }
        });
    }


    public void getCameraPhoto(Activity context, PhotoCallBack callBack) {
        callBackPhoto = callBack;
        contexts = context;
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        } else {
            isMutilChoose = true;
            GalleryFinal.openCamera(OPEN_CAMERA, hanlderResultCallback);
        }
    }

    public void getCameraSinglePhoto(Activity context, PhotoCallBack callBack) {
        callBackPhoto = callBack;
        contexts = context;
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        } else {
            isMutilChoose = false;
            GalleryFinal.openCamera(OPEN_CAMERA, hanlderResultCallback);
        }
    }

    public void getGalleryPhoto(Activity context, PhotoCallBack callBack) {
        callBackPhoto = callBack;
        contexts = context;
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        } else {
            isMutilChoose = false;
            GalleryFinal.openGallerySingle(OPEN_GALLERY, hanlderResultCallback);
        }
    }


    /**
     * 发表帖子的时候选择照片
     */
    public void updateDialog(final Activity context, PhotoCallBack callBack) {
        callBackPhoto = callBack;
        contexts = context;
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
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
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                } else {
                    isMutilChoose = true;
                    GalleryFinal.openCamera(OPEN_CAMERA, hanlderResultCallback);
                }
                dialog.dismiss();
            }
        });


        TextView tvGallery = (TextView) window.findViewById(R.id.tv_gallery);
        tvGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                GalleryFinal.openGallerySingle(OPEN_GALLERY, hanlderResultCallback);
                isMutilChoose = true;
                GalleryFinal.openGalleryMuti(OPEN_GALLERY, 9, hanlderResultCallback);
                dialog.dismiss();
            }
        });
    }

    public void updateSingleDialog(final Activity context, PhotoCallBack callBack) {
        callBackPhoto = callBack;
        contexts = context;
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
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
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                } else {
                    isMutilChoose = false;
                    GalleryFinal.openCamera(OPEN_CAMERA, hanlderResultCallback);
                }
                dialog.dismiss();
            }
        });


        TextView tvGallery = (TextView) window.findViewById(R.id.tv_gallery);
        tvGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                GalleryFinal.openGallerySingle(OPEN_GALLERY, hanlderResultCallback);
                isMutilChoose = false;
                GalleryFinal.openGallerySingle(OPEN_GALLERY, hanlderResultCallback);
                dialog.dismiss();
            }
        });
    }

    /**
     * 照相返回
     */
    private GalleryFinal.OnHanlderResultCallback hanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            if (!StringUtils.isNoEmpty(resultList.get(0).getPhotoPath())) {
                return;
            }
            if (isMutilChoose) {
                String photo = "";
                int num = resultList.size();
                for (int i = 0; i < num; i++) {
                    photo += resultList.get(i).getPhotoPath() + ",";
                }
                callBackPhoto.getPhoto(photo);
            } else {
                compressImage(resultList.get(0).getPhotoPath());
            }
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            ToastUtils.getMineToast("添加失败，请重新添加");
        }
    };

    private String photoPath = "";

    /**
     * 图片压缩
     */
    private void compressImage(String path) {
        crompressImage(path, new FileCallBack() {
            @Override
            public void onFile(File file) {
                String imgName = DateUtils.getInstance().getTime() + ".png";
//                loadingDialog = MineLoadingDialogUtils.updateDialog(contexts, contexts.getString(R.string.up_image));
                //上传图片
                OkHttpUtils.post()
                        .addFile("imgFile", imgName, file)
                        .url(UrlConstant.getInstance().PHOTO_PATH)
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.i(call.toString() + "  " + e.toString());
                        ToastUtils.getMineToast("上传失败，请重新上传");
//                        if (loadingDialog.isShowing()) {
//                            loadingDialog.dismiss();
//                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ImageUrl imageUrl = JSON.parseObject(response, ImageUrl.class);
                        if (imageUrl.getReObj().getPicUrl() == null) {
                            callBackPhoto.getPhoto("");
                        } else {
                            callBackPhoto.getPhoto(imageUrl.getReObj().getPicUrl());
                        }
//                        if (loadingDialog.isShowing()) {
//                            loadingDialog.dismiss();
//                        }
                    }
                });
            }
        });
    }

    List<File> mutilLocalList = new ArrayList<>();

    public void mutilLocalImageUp(List<String> resultList, Context context, final StringCallBack stringCallBack) {
        final int num = resultList.size();
//        final AlertDialog loadingDialog = MineLoadingDialogUtils.updateDialog(context,"数据加载中");
        mutilLocalList.clear();
        if (num == 0) {
            stringCallBack.onSuccess("");
            return;
        }
        for (int i = 0; i < num; i++) {
            crompressImage(resultList.get(i), new FileCallBack() {
                @Override
                public void onFile(File file) {
                    mutilLocalList.add(file);
                    LogUtils.i("outFile:" + file.getAbsolutePath());

                    if (mutilLocalList.size() == num) {
                        //上传图片
                        PostFormBuilder post = OkHttpUtils.post();
                        String imgName = DateUtils.getInstance().getTime();
//                        loadingDialog.dismiss();
                        int fileSize = mutilLocalList.size();
                        for (int i = 0; i < fileSize; i++) {
                            post.addFile("imgFile", imgName + i + ".png", mutilLocalList.get(i));
                        }
                        post.url(UrlConstant.getInstance().PHOTO_PATH)
                                .build().execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                stringCallBack.onFail();
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                LogUtils.i(response);
                                ImageUrl imageUrl = JSON.parseObject(response, ImageUrl.class);
                                if (imageUrl.getReObj().getPicUrl() == null) {
                                    stringCallBack.onFail();
                                } else {
                                    stringCallBack.onSuccess(imageUrl.getReObj().getPicUrl());
                                }
                            }
                        });
                    }
                }
            });
        }
    }


    //图片压缩
    private void crompressImage(String path, final FileCallBack fileCallBack) {
        Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
        Tiny.getInstance().source(path).asFile().withOptions(options).compress(new FileCallback() {
            @Override
            public void callback(boolean isSuccess, String outfile) {
                if (!StringUtils.isNoEmpty(outfile)) {
                    return;
                }
                //return the compressed file path
                File fileCrompress = new File(outfile);
                fileCallBack.onFile(fileCrompress);
            }
        });
    }

    //图片压缩
    private File crompressImage(String path, String name) {
        return new File(path);
    }


    /**
     * 设置最后一张图片是添加照片的图片
     */
    public void setLastAddPhoto(List<String> imgList) {
        int num = imgList.size();
        imgList.remove(num - 1);

//        imgList.add(imgUrl);
        if (imgList.size() == 9) {
            return;
        }
        imgList.add("addPhoto");
    }

    /**
     * 获取到不是addPhoto的图片集合
     */
    public List<String> getImageList(List<String> imgList) {
        int num = imgList.size();
        List<String> picList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            if (!imgList.get(i).equals("addPhoto")) {
                picList.add(imgList.get(i));
            }
        }
        return picList;
    }


    //通用的图片上传
    public void commonImageUp(File files, final OnImageUpCallBack callBack) {
        crompressImage(files.getAbsolutePath(), new FileCallBack() {
            @Override
            public void onFile(File file) {
                //上传图片
                OkHttpUtils.post()
                        .addFile("imgFile", DateUtils.getInstance().getTime() + ".png", file)
                        .url(UrlConstant.getInstance().PHOTO_PATH)
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.getMineToast("上传失败，请重新上传");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.i(response);
                        ImageUrl imageUrl = JSON.parseObject(response, ImageUrl.class);
                        if (imageUrl.getReObj().getPicUrl() == null) {
                            callBack.onUpSuccess("");
                        } else {
                            callBack.onUpSuccess(imageUrl.getReObj().getPicUrl());
                        }
//                loadingDialog.dismiss();
                    }
                });
            }
        });

    }


    //通用的视频上传上传
    public void commonVideoUp(Context context, File file, final OnUpVideoCallBack callBack) {
        String imgName = DateUtils.getInstance().getTime() + ".mp4";
        //上传图片赚了
        OkHttpUtils.post()
                .addFile("imgFile", imgName, file)
                .url(UrlConstant.getInstance().PHOTO_PATH)
                .build().execute(new Callback() {
            @Override
            public Object parseNetworkResponse(Response response, int id) throws Exception {
                ImageUrl imageUrl = JSON.parseObject(response.body().string(), ImageUrl.class);
                LogUtils.i(imageUrl.getReObj().getPicUrl());
                if (!StringUtils.isNoEmpty(imageUrl.getReObj().getPicUrl())) {
                    callBack.onStr("");
                } else {
                    callBack.onStr(imageUrl.getReObj().getPicUrl());
                }
                return null;
            }

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(Object response, int id) {

            }

            @Override
            public void inProgress(float progress, long total, int id) {
                super.inProgress(progress, total, id);
                callBack.onProgress(progress);
                LogUtils.i(progress + "");
            }

            @Override
            public String toString() {
                return super.toString();
            }
        });
    }

    //返回带逗号的字符串
    public String getImageStr(List<String> imgList) {
        int num = imgList.size();
        String pics = "";
        for (int i = 0; i < num; i++) {//
            if (imgList.get(i).length() > 10) {
                if (i == (imgList.size() - 1)) {
                    pics += imgList.get(i);
                } else {
                    pics += imgList.get(i) + ",";
                }
            }
        }
        return pics;
    }

    //分割逗号获取集合
    public List<String> getListImage(String pic) {

        List<String> imgList = new ArrayList<>();
        if (!StringUtils.isNoEmpty(pic)) {
            return imgList;
        }
        String[] split = pic.split(",");

        int num = split.length;
        for (int i = 0; i < num; i++) {
            if (StringUtils.isNoEmpty(split[i]) && split[i].length() > 5) {
                imgList.add(split[i]);
            }
        }
        return imgList;
    }

    File file = null;
    File files = null;
    String imgName = DateUtils.getInstance().getTime() + ".png";

    /**
     * 图片压缩
     */
    public void onlyUpImage(Bitmap bSyMap, final StringCallBack stringCallBack) {
        // TODO Auto-generated method stub
        Bitmap bMap;
        Bitmap bMapRotate;
        // data为完整数据
        file = new File("/sdcard/photo.png");
        bMap = BitmapUtils.AddTimeWatermark(bSyMap);
//        String imgName = DateUtils.getInstance().getTime() + ".png";
        try {
            Matrix matrix = new Matrix();
            matrix.reset();
//            matrix.postRotate(-90);
            bMapRotate = Bitmap.createBitmap(bMap, 0, 0, bMap.getWidth(),
                    bMap.getHeight(), matrix, true);
            bMap = bMapRotate;

            // Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length);
            BufferedOutputStream bos =
                    new BufferedOutputStream(new FileOutputStream(file));
            bMap.compress(Bitmap.CompressFormat.JPEG, 100, bos);//将图片压缩到流中
            bos.flush();//输出·
            bos.close();//关闭
        } catch (IOException e) {
            e.printStackTrace();
        }

        files = crompressImage(file.getAbsolutePath(), name);

        crompressImage(files.getAbsolutePath(), new FileCallBack() {
            @Override
            public void onFile(File file) {
                //上传图片
                OkHttpUtils.post()
                        .addFile("imgFile", imgName, file)
                        .url(UrlConstant.getInstance().PHOTO_PATH)
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.i(call.toString() + "  " + e.toString());
                        if (files != null) {
                            files.delete();
                        }
                        stringCallBack.onFail();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ImageUrl imageUrl = JSON.parseObject(response, ImageUrl.class);
                        LogUtils.i(imageUrl.getReObj().getPicUrl() + "   www");
                        if (imageUrl.getReObj().getPicUrl() == null) {
                            stringCallBack.onSuccess("");
                        } else {
                            stringCallBack.onSuccess(imageUrl.getReObj().getPicUrl());
                        }
                        if (files != null) {
                            files.delete();
                        }
                    }
                });
            }
        });
    }
}
