package com.example.huoshangkou.jubowan.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：BitmapUtils
 * 描述：
 * 创建时间：2019-05-21  16:08
 */

public class BitmapUtils {

    /**
     * 给图片加水印，网上
     * 右下角
     *
     * @param src       原图
     * @param watermark 水印
     * @return 加水印的原图
     */
    public static Bitmap WaterMask(Context context, Bitmap src, Bitmap watermark) {
        int w = src.getWidth();
        int h = src.getHeight();
        Log.i("WaterMask", "原图宽: " + w);
        Log.i("WaterMask", "原图高: " + h);
        // 设置原图想要的大小
        float newWidth = ScreenUtils.getScreenWidth(context);
        float newHeight = h * (newWidth / w);
        // 计算缩放比例
        float scaleWidth = (newWidth) / w;
        float scaleHeight = (newHeight) / h;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        src = Bitmap.createBitmap(src, 0, 0, w, h, matrix, true);

        //根据bitmap缩放水印图片
        float w1 = w / 5;
        float h1 = (float) (w1 / 5);
        //获取原始水印图片的宽、高
        int w2 = watermark.getWidth();
        int h2 = watermark.getHeight();

        //计算缩放的比例
        float scalewidth = ((float) w1) / w2;
        float scaleheight = ((float) h1) / h2;

        Matrix matrix1 = new Matrix();
        matrix1.postScale((float) 0.4, (float) 0.4);

        watermark = Bitmap.createBitmap(watermark, 0, 0, w2, h2, matrix1, true);
        //获取新的水印图片的宽、高
        w2 = watermark.getWidth();
        h2 = watermark.getHeight();

        Bitmap result = Bitmap.createBitmap(src.getWidth(), src.getHeight(), Bitmap.Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
        Canvas cv = new Canvas(result);
        //在canvas上绘制原图和新的水印图
        cv.drawBitmap(src, 0, 0, null);
        //水印图绘制在画布的右下角，距离右边和底部都为20
        cv.drawBitmap(watermark, src.getWidth() - w2 - 20, src.getHeight() - h2 - 20, null);
        cv.save(Canvas.ALL_SAVE_FLAG);
        cv.restore();

        return result;
    }

    /**
     * 添加时间水印
     *
     * @param mBitmap
     * @return mNewBitmap
     */
    public static Bitmap AddTimeWatermark(Bitmap mBitmap) {
        //获取原始图片与水印图片的宽与高
        int mBitmapWidth = mBitmap.getWidth();
        int mBitmapHeight = mBitmap.getHeight();
        Bitmap mNewBitmap = Bitmap.createBitmap(mBitmapWidth, mBitmapHeight, Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(mNewBitmap);
        //向位图中开始画入MBitmap原始图片
        mCanvas.drawBitmap(mBitmap, 0, 0, null);
        //添加文字
        Paint mPaint = new Paint();
        String mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //String mFormat = TingUtils.getTime()+"\n"+" 纬度:"+GpsService.latitude+"  经度:"+GpsService.longitude;
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(20);
        // 获取更清晰的图像采样，防抖动
        mPaint.setDither(true);
        // 过滤一下，抗剧齿
        mPaint.setFilterBitmap(true);
        //水印的位置坐标
//        mCanvas.rotate(90,(mBitmapWidth * 1) / 20, (mBitmapHeight * 9) / 15);
        mCanvas.drawText(mFormat, (mBitmapWidth * 1) / 20, (mBitmapHeight * 14) / 15, mPaint);
        mCanvas.save(Canvas.CLIP_SAVE_FLAG);
        mCanvas.restore();
        return mNewBitmap;
    }
}
