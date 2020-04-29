package com.example.huoshangkou.jubowan.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.inter.OnImageUpCallBack;
import com.example.huoshangkou.jubowan.inter.OnSignImageCallBack;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：SignDialogUtils
 * 描述：手写签名工具类
 * 创建时间：2017-03-17  15:14
 */

public class SignDialogUtils {
    private AlertDialog dialog;
    private ImageView iv_canvas;
    private Bitmap baseBitmap;
    private Canvas canvas;
    private Paint paint;
    private Context mContext;
    //是否签字
    private boolean isSign = false;
    private OnSignImageCallBack mImageCallBack;
    private AlertDialog loadingDialog;

    private static class SignDialogHelper {
        private static SignDialogUtils INSTANCE = new SignDialogUtils();
    }

    public static SignDialogUtils getInstance() {
        return SignDialogHelper.INSTANCE;
    }

    /**
     * 更新进度框
     */
    public void updateDialog(final Context context, OnSignImageCallBack imageCallBack) {
        mImageCallBack = imageCallBack;
        mContext = context;

        dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setContentView(R.layout.item_sign_name);
        dialog.getWindow().setBackgroundDrawableResource(R.color.alpha_black);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        // 初始化一个画笔，笔触宽度为5，颜色为黑色
        paint = new Paint();
        paint.setStrokeWidth(15);
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        TextView tvRetry = (TextView) dialog.getWindow().findViewById(R.id.tv_sign_retry);
        TextView tvSave = (TextView) dialog.getWindow().findViewById(R.id.tv_save_sign);
        TextView tvTitle = (TextView) dialog.getWindow().findViewById(R.id.tv_title);
        LinearLayout llBack = (LinearLayout) dialog.getWindow().findViewById(R.id.ll_back);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        tvTitle.setText("请您签字");

        //每次进来清空画板
        resumeCanvas(context);
        iv_canvas = (ImageView) dialog.getWindow().findViewById(R.id.iv_canves);
        iv_canvas.setOnTouchListener(touch);

        tvRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSign =false;
                resumeCanvas(context);
            }
        });
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CopyIosDialogUtils.getInstance().getIosDialog(context, context.getString(R.string.save_sign_name), new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        if (!isSign) {
                            ToastUtils.getMineToast("请您签字");
                            return;
                        }
                        saveBitmap();
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
            }
        });
    }

    private View.OnTouchListener touch = new View.OnTouchListener() {
        // 定义手指开始触摸的坐标
        float startX;
        float startY;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                // 用户按下动作
                case MotionEvent.ACTION_DOWN:
                    isSign = true;
                    // 第一次绘图初始化内存图片，指定背景为白色
                    if (baseBitmap == null) {
                        baseBitmap = Bitmap.createBitmap(iv_canvas.getWidth(),
                                iv_canvas.getHeight(), Bitmap.Config.ARGB_8888);
                        canvas = new Canvas(baseBitmap);
                        canvas.drawColor(Color.WHITE);
                    }
                    // 记录开始触摸的点的坐标
                    startX = event.getX();
                    startY = event.getY();
                    break;
                // 用户手指在屏幕上移动的动作
                case MotionEvent.ACTION_MOVE:
                    // 记录移动位置的点的坐标
                    float stopX = event.getX();
                    float stopY = event.getY();
                    //根据两点坐标，绘制连线
                    canvas.drawLine(startX, startY, stopX, stopY, paint);
                    // 更新开始点的位置
                    startX = event.getX();
                    startY = event.getY();
                    // 把图片展示到ImageView中
                    iv_canvas.setImageBitmap(baseBitmap);
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                default:
                    break;
            }
            return true;
        }
    };

    /**
     * 保存图片到SD卡上
     */
    protected void saveBitmap() {
        try {
            // 保存图片到SD卡上
            File file = new File(Environment.getExternalStorageDirectory(),
                    System.currentTimeMillis() + ".png");
            FileOutputStream stream = new FileOutputStream(file);
            baseBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            if (file == null) {
                ToastUtils.getMineToast("请您签名保存");
                return;
            }
            //上传图片到服务器
            upImage(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清除画板
     */
    protected void resumeCanvas(Context context) {
        // 手动清除画板的绘图，重新创建一个画板
        if (baseBitmap != null) {
            baseBitmap = Bitmap.createBitmap(iv_canvas.getWidth(),
                    iv_canvas.getHeight(), Bitmap.Config.ARGB_8888);
            canvas = new Canvas(baseBitmap);
            canvas.drawColor(Color.WHITE);
            iv_canvas.setImageBitmap(baseBitmap);
        }
    }

    private void upImage(final File file) {
        PhotoUtils.getInstance().commonImageUp(file, new OnImageUpCallBack() {
            @Override
            public void onUpSuccess(String path) {
                mImageCallBack.onSignSuccess(path);
                file.delete();
            }
        });
    }

}
