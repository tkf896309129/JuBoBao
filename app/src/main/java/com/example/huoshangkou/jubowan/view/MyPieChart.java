package com.example.huoshangkou.jubowan.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.utils.DensityUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chaohx on 2017/8/9.
 */

public class MyPieChart extends View {
    private List<PieEntry> pieEntries;

    private Paint paint;  //画笔

    private float centerX; //中心点坐标 x
    private float centerY; //中心点坐标 y
    private float radius;  //未选中状态的半径
    private float sRadius; //选中状态的半径

    private OnItemClickListener listener; //点击事件的回调

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public MyPieChart(Context context) {
        super(context);
        init();
    }

    public MyPieChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyPieChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        pieEntries = new ArrayList<>();
        paint = new Paint();
        paint.setTextSize(DensityUtils.sp2px(getContext(), 12));
        paint.setAntiAlias(true);
    }

    public void setRadius(float radius) {
        this.sRadius = radius;
    }

    /**
     * 设置数据并刷新
     *
     * @param pieEntries
     */
    public void setPieEntries(List<PieEntry> pieEntries) {
        this.pieEntries = pieEntries;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //计算总值
        float total = 0;
        for (int i = 0; i < pieEntries.size(); i++) {
            total += pieEntries.get(i).getNumber();
        }
        //刷新中心点 和半径
        centerX = getPivotX();
        centerY = getPivotY();
        if (sRadius == 0) {   //这里做个判断，如果没有通过setRadius方法设置半径，则半径为真个view最小边的一半
            sRadius = (getWidth() > getHeight() ? getHeight() / 2 : getWidth() / 2);
        }
        //计算出两个状态的半径，这里二者相差5dp.
        radius = sRadius - DensityUtils.dp2px(getContext(), 5);

        //其实角度设置为0,即x轴正方形
        float startC = -90;
        //遍历List<PieEntry> 开始画扇形
        for (int i = 0; i < pieEntries.size(); i++) {
            //计算当前扇形扫过的角度
            float sweep;
            if (total <= 0) {
                sweep = 360 / pieEntries.size();
            } else {
                sweep = 360 * (pieEntries.get(i).getNumber() / total);
            }
            //设置当前扇形的颜色
            paint.setColor(getResources().getColor(pieEntries.get(i).colorRes));
            //判断当前扇形是否被选中，确定用哪个半径
            float radiusT;
//            if (pieEntries.get(i).isSelected()) {
//                radiusT = sRadius;
//            } else {
//                radiusT = radius;
//            }
            radiusT = DensityUtils.dp2px(getContext(), pieEntries.get(i).getRadius());
            //画扇形的方法
            RectF rectF = new RectF(centerX - radiusT, centerY - radiusT, centerX + radiusT, centerY + radiusT);
            canvas.drawArc(rectF, startC, sweep, true, paint);

            if (((pieEntries.get(i).getNumber() > 0 && total > 0) || (total <= 0 && pieEntries.get(i).getNumber() <= 0)) && pieEntries.get(i).isSelected()) {
                Paint paintWhite = new Paint();
                paintWhite.setColor(getResources().getColor(R.color.white));
                canvas.drawCircle(centerX, centerY, 100, paintWhite);
                //下面是画扇形外围的 短线和百分数值。
                float arcCenterC = startC + sweep / 2; //当前扇形弧线的中间点和圆心的连线 与 起始角度的夹角
                float arcCenterX = 0;  //当前扇形弧线的中间点 的坐标 x  以此点作为短线的起始点
                float arcCenterY = 0;  //当前扇形弧线的中间点 的坐标 y

                float arcCenterX2 = 0; //这两个点作为短线的结束点
                float arcCenterY2 = 0;
                //百分百数字的格式
                String pecent = pieEntries.get(i).getRadio();
                paint.setColor(getResources().getColor(R.color.address_black_key));
                LogUtils.e("arcCenterC：" + arcCenterC);
                //分象限 利用三角函数 来求出每个短线的起始点和结束点，并画出短线和百分比。
                //具体的计算方法看下面图示介绍
                if (arcCenterC >= 0 && arcCenterC < 90) {
                    if (arcCenterC > 1) {
                        arcCenterX = (float) (centerX + radiusT * Math.cos(arcCenterC * Math.PI / 180));
                        arcCenterY = (float) (centerY + radiusT * Math.sin(arcCenterC * Math.PI / 180));
                        arcCenterX2 = (float) (arcCenterX + DensityUtils.dp2px(getContext(), 10) * Math.cos(arcCenterC * Math.PI / 180));
                        arcCenterY2 = (float) (arcCenterY + DensityUtils.dp2px(getContext(), 10) * Math.sin(arcCenterC * Math.PI / 180));

                        canvas.drawText(pieEntries.get(i).getContent(), arcCenterX2 + 45, arcCenterY2 + 15 + paint.getTextSize() / 2, paint);
                        canvas.drawText(pecent, arcCenterX2 + 45, arcCenterY2 +55 + paint.getTextSize() / 2, paint);

                        paint.setColor(getResources().getColor(pieEntries.get(i).colorRes));
                        canvas.drawLine(arcCenterX + 15, arcCenterY + 15, arcCenterX2 + 45, arcCenterY2 + 45, paint);
                        canvas.drawLine(arcCenterX2 + 45, arcCenterY2 + 45, arcCenterX2 + 205, arcCenterY2 + 45, paint);
                        canvas.drawCircle(arcCenterX + 15, arcCenterY + 15, 15, paint);
                    }
                } else if (arcCenterC >= 90 && arcCenterC < 180) {
                    arcCenterC = 180 - arcCenterC;
                    if (arcCenterC > 1) {
                        arcCenterX = (float) (centerX - radiusT * Math.cos(arcCenterC * Math.PI / 180));
                        arcCenterY = (float) (centerY + radiusT * Math.sin(arcCenterC * Math.PI / 180));
                        arcCenterX2 = (float) (arcCenterX - DensityUtils.dp2px(getContext(), 20) * Math.cos(arcCenterC * Math.PI / 180));
                        arcCenterY2 = (float) (arcCenterY + DensityUtils.dp2px(getContext(), 20) * Math.sin(arcCenterC * Math.PI / 180));
                        canvas.drawText(pieEntries.get(i).getContent(), (float) (arcCenterX2 - paint.getTextSize() * 3.5) - 55, arcCenterY2 + paint.getTextSize() / 2 - 10, paint);
                        canvas.drawText(pecent, (float) (arcCenterX2 - paint.getTextSize() * 3.5) - 55, arcCenterY2 + paint.getTextSize() / 2 + 30, paint);

                        paint.setColor(getResources().getColor(pieEntries.get(i).colorRes));
                        canvas.drawLine(arcCenterX - 25, arcCenterY + 15, arcCenterX2 - 25, arcCenterY2 + 15, paint);
                        canvas.drawLine(arcCenterX2 - 25, arcCenterY2 + 15, arcCenterX2 - 205, arcCenterY2 + 15, paint);
                        canvas.drawCircle(arcCenterX - 25, arcCenterY + 15, 15, paint);
                    }
                } else if (arcCenterC >= 180 && arcCenterC < 270) {
                    arcCenterC = 270 - arcCenterC;
                    if (arcCenterC > 1) {
                        arcCenterX = (float) (centerX - radiusT * Math.sin(arcCenterC * Math.PI / 180));
                        arcCenterY = (float) (centerY - radiusT * Math.cos(arcCenterC * Math.PI / 180));
                        arcCenterX2 = (float) (arcCenterX - DensityUtils.dp2px(getContext(), 20) * Math.sin(arcCenterC * Math.PI / 180));
                        arcCenterY2 = (float) (arcCenterY - DensityUtils.dp2px(getContext(), 20) * Math.cos(arcCenterC * Math.PI / 180 * Math.PI / 180));

                        canvas.drawText(pieEntries.get(i).getContent(), (float) (arcCenterX2 - paint.getTextSize() * 3.5) - 45, arcCenterY2 - 25, paint);
                        canvas.drawText(pecent, (float) (arcCenterX2 - paint.getTextSize() * 3.5) - 45, arcCenterY2 +25, paint);

                        paint.setColor(getResources().getColor(pieEntries.get(i).colorRes));
                        canvas.drawLine(arcCenterX - 25, arcCenterY - 15, arcCenterX2 - 25, arcCenterY2 - 15, paint);
                        canvas.drawLine(arcCenterX2 - 25, arcCenterY2 - 15, arcCenterX2 - 205, arcCenterY2 - 15, paint);
                        canvas.drawCircle(arcCenterX - 25, arcCenterY - 20, 15, paint);
                    }
                } else if (arcCenterC >= -90 && arcCenterC < 0) {
                    arcCenterC = 360 - arcCenterC;
                    if (arcCenterC > 1) {
                        arcCenterX = (float) (centerX + radiusT * Math.cos(arcCenterC * Math.PI / 180));
                        arcCenterY = (float) (centerY - radiusT * Math.sin(arcCenterC * Math.PI / 180));
                        arcCenterX2 = (float) (arcCenterX + DensityUtils.dp2px(getContext(), 20) * Math.cos(arcCenterC * Math.PI / 180));
                        arcCenterY2 = (float) (arcCenterY - DensityUtils.dp2px(getContext(), 20) * Math.sin(arcCenterC * Math.PI / 180));

                        canvas.drawText(pieEntries.get(i).getContent(), arcCenterX2 + 25, arcCenterY2 - 40, paint);
                        canvas.drawText(pecent, arcCenterX2 + 25, arcCenterY2 + 10, paint);

                        paint.setColor(getResources().getColor(pieEntries.get(i).colorRes));
                        canvas.drawLine(arcCenterX + 15, arcCenterY - 25, arcCenterX2 + 25, arcCenterY2 - 25, paint);
                        canvas.drawLine(arcCenterX2 + 25, arcCenterY2 - 25, arcCenterX2 + 205, arcCenterY2 - 25, paint);
                        canvas.drawCircle(arcCenterX + 15, arcCenterY - 25, 15, paint);
                    }
                }
            }
            //将每个扇形的起始角度 和 结束角度 放入对应的对象
            pieEntries.get(i).setStartC(startC);
            pieEntries.get(i).setEndC(startC + sweep);
            //将当前扇形的结束角度作为下一个扇形的起始角度
            startC += sweep;
        }
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        float touchX;
//        float touchY;
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                touchX = event.getX(); //touch点的坐标
//                touchY = event.getY();
//                //判断touch点到圆心的距离 是否小于半径
//                if (Math.pow(touchX - centerX, 2) + Math.pow(touchY - centerY, 2) <= Math.pow(radius, 2)) {
//                    //计算 touch点和圆心的连线 与 x轴正方向的夹角
//                    float touchC = getSweep(touchX, touchY);
//                    //遍历 List<PieEntry> 判断touch点在哪个扇形中
//                    for (int i = 0; i < pieEntries.size(); i++) {
//                        if (touchC >= pieEntries.get(i).getStartC() && touchC < pieEntries.get(i).getEndC()) {
//                            pieEntries.get(i).setSelected(true);
//                            if (listener != null)
//                                listener.onItemClick(i); //将被点击的扇形id回调出去
//                        } else {
//                            pieEntries.get(i).setSelected(false);
//                        }
//                    }
//                    invalidate();//刷新画布
//                }
//                break;
//        }
//
//        return super.onTouchEvent(event);
//    }

    /**
     * 获取  touch点/圆心连线  与  x轴正方向 的夹角
     *
     * @param touchX
     * @param touchY
     */
    private float getSweep(float touchX, float touchY) {
        float xZ = touchX - centerX;
        float yZ = touchY - centerY;
        float a = Math.abs(xZ);
        float b = Math.abs(yZ);
        double c = Math.toDegrees(Math.atan(b / a));
        if (xZ >= 0 && yZ >= 0) {//第一象限
            return (float) c;
        } else if (xZ <= 0 && yZ >= 0) {//第二象限
            return 180 - (float) c;
        } else if (xZ <= 0 && yZ <= 0) {//第三象限
            return (float) c + 180;
        } else {//第四象限
            return 360 - (float) c;
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(int position);
    }


    /**
     * 每个扇形的对象
     */
    public static class PieEntry {
        private float number;  //数值
        private int colorRes;  //颜色资源
        private boolean selected; //是否选中
        private float startC;     //对应扇形起始角度
        private float endC;       //对应扇形结束角度
        private int radius;
        private String content;
        private String radio;


        public PieEntry(float number, int colorRes, boolean selected, int radius, String content,String radio) {
            this.number = number; //防止分母为零
            this.colorRes = colorRes;
            this.selected = selected;
            this.radius = radius;
            this.content = content;
            this.radio = radio;
        }

        public String getRadio() {
            return radio;
        }

        public void setRadio(String radio) {
            this.radio = radio;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getRadius() {
            return radius;
        }

        public void setRadius(int radius) {
            this.radius = radius;
        }

        public float getStartC() {
            return startC;
        }

        public void setStartC(float startC) {
            this.startC = startC;
        }

        public float getEndC() {
            return endC;
        }

        public void setEndC(float endC) {
            this.endC = endC;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public float getNumber() {
            return number;
        }

        public void setNumber(float number) {
            this.number = number;
        }

        public int getColorRes() {
            return colorRes;
        }

        public void setColorRes(int colorRes) {
            this.colorRes = colorRes;
        }
    }
}