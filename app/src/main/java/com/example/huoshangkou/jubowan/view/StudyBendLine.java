package com.example.huoshangkou.jubowan.view;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.view
 * 类名：StudyBendLine
 * 描述：
 * 创建时间：2019-09-10  15:24
 */

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.TwoPointUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangchang on 2019/4/29.
 */
public class StudyBendLine extends View {
    private float sumHeight;//总控件的高度
    private float sumWidth;//总空间的宽度
    private float maxTime;//最大的时间 用来划分单位的 最小就是20 X1.2是为了给上方和下方预留空间
    private Paint linePaint;//线的画笔
    //    private Paint shadowPaint;//阴影的画笔
    private Paint mPaint;//曲线画笔
    private Paint circlePaint;//圆点画笔
    private Paint circlePaint2;//圆点画笔
    private Paint textPaint;//文字的画笔
    private ArrayList<Long> timeList;//读书时间
    private ArrayList<String> dataList;//底部的时间
    private float oneHeight; //每一个小段所要分成的高
    private float oneWidth;//每一个小段所要分成的宽
    private float buttomHeiht; //给底部一排日期预留出的时间 一直是未付款啊
    private Path baseLinePath;//折线路径
    private float smoothness = 0.36f; //折线的弯曲率
    //    private Paint baseShadow;//折线下的阴影的画笔
    private ArrayList<PointF> xyList;//储存定好的坐标点的集合

    public StudyBendLine(Context context) {
        super(context);
        initPaint();
    }

    public StudyBendLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    /**
     * 初始化画笔 13004661850
     *
     * @linpaint 线条画笔
     * @shadowPaint 阴影画笔
     */
    private void initPaint() {
        //画线的画笔
        linePaint = new Paint();
        linePaint.setColor(getResources().getColor(R.color.main_tab_blue_white));
        linePaint.setAntiAlias(true);
        linePaint.setTextSize(dp2px(getContext(), 12));
        linePaint.setStrokeWidth(dp2px(getContext(), 1));
        //画背景的画笔
//        shadowPaint = new Paint();
//        shadowPaint.setColor(Color.parseColor("#CBF2ED"));
//        shadowPaint.setAntiAlias(true);
        //画最下方文字的画笔
        textPaint = new Paint();
        textPaint.setColor(getResources().getColor(R.color.main_tab_blue_white));
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(dp2px(getContext(), 11));

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(Color.parseColor("#E9F0F5"));
        circlePaint.setStrokeWidth(dp2px(getContext(), 2));
        circlePaint.setStyle(Paint.Style.FILL);

        circlePaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint2.setColor(Color.parseColor("#FF7272"));
        circlePaint2.setStyle(Paint.Style.FILL);

//        baseShadow = new Paint();
//        baseShadow.setAntiAlias(true);
//        baseShadow.setColor((Color.WHITE & 0x40FFFFFF) | 0x10000000);
//        baseShadow.setStyle(Paint.Style.STROKE);

        buttomHeiht = dp2px(35);//线距离底部高度

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(getResources().getColor(R.color.main_tab_blue_white));
        mPaint.setStrokeWidth(dp2px(getContext(), 2));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(getResources().getColor(R.color.main_tab_blue_white));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        sumHeight = getMeasuredHeight();
        sumWidth = getMeasuredWidth();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private void measure() {
        String text = "分";
        Rect rect = new Rect();
        textPaint.getTextBounds(text, 0, text.length(), rect);
        oneHeight = ((sumHeight - buttomHeiht - 2 * rect.height()) / maxTime);
        int num = timeList.size();
        oneWidth = sumWidth / (num * 4);
    }

    private long getMax(ArrayList<Long> arr) {
        long temp = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (temp < arr.get(i)) {
                temp = arr.get(i);
            }
        }
        return temp;
    }

    /**
     * 更新阅读时间
     */
    public void updateTime(ArrayList<Long> timeList, ArrayList<String> bottomList) {
        this.timeList = timeList;
        this.dataList = bottomList;
        if (this.timeList != null && this.timeList.size() > 0 && dataList != null && dataList.size() > 0) {
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (timeList == null || dataList == null) return;
        maxTime = getMax(timeList);
        measure();
        toGetXy();//获取x和y的坐标

        toDrawLine(canvas);
    }

    private void toGetXy() {
        int leftWidth = dp2px(13);//距离右边宽度
        xyList = new ArrayList<>();
        for (int i = 0; i < timeList.size(); i++) {
            float x = oneWidth + i * 4 * oneWidth;
            long time = timeList.get(i);//每点时间
            float y = (sumHeight - (oneHeight * time));
            xyList.add(new PointF(x + leftWidth, y - buttomHeiht));
        }
    }

    /**
     * 画线
     */
    private void toDrawLine(Canvas canvas) {
        if (xyList == null || xyList.size() == 0) {
            return;
        }
        mPaint.setColor(getResources().getColor(R.color.main_tab_blue_all));
        mPaint.setStrokeWidth(dp2px(getContext(), 2));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(getResources().getColor(R.color.main_tab_blue_all));
        List<PointF> NewPoints = new ArrayList<>();
        NewPoints.addAll(xyList);
        float lX = 0;
        float lY = 0;
        baseLinePath = new Path();
        baseLinePath.moveTo(NewPoints.get(0).x, NewPoints.get(0).y);
        for (int i = 1; i < NewPoints.size(); i++) {
            PointF p = NewPoints.get(i);
            PointF firstPointF = NewPoints.get(i - 1);
            float x1 = firstPointF.x + lX;
            float y1 = firstPointF.y + lY;

            PointF secondPointF = NewPoints.get(i + 1 < NewPoints.size() ? i + 1 : i);
            lX = (secondPointF.x - firstPointF.x) / 2 * smoothness;
            lY = (secondPointF.y - firstPointF.y) / 2 * smoothness;
            float x2 = p.x - lX;
            float y2 = p.y - lY;
            if (y1 == p.y) {
                y2 = y1;
            }
            baseLinePath.cubicTo(x1, y1, x2, y2, p.x, p.y);
        }
        canvas.drawPath(baseLinePath, mPaint);
        for (int i = 0; i < NewPoints.size(); i++) {
            float x = NewPoints.get(i).x;
            LogUtils.e(x + "横坐标");
            Rect rectf = new Rect();
            if (i == touchIndex) {
                textPaint.setColor(getResources().getColor(R.color.main_tab_blue_all));
                canvas.drawCircle(x - rectf.width() / 2, sumHeight - buttomHeiht + 60, 40, circlePaint);
            } else {
                textPaint.setColor(getResources().getColor(R.color.address_black_key));
            }
            textPaint.getTextBounds(dataList.get(i), 0, dataList.get(i).length(), rectf);

            canvas.drawText(dataList.get(i), x - rectf.width() / 2, sumHeight - buttomHeiht + 70, textPaint);//画最下方的字体
        }
        if (NewPoints.size() > 2) {
            startX = NewPoints.get(0).x;
            xDes = NewPoints.get(1).x - NewPoints.get(0).x;
        }
        if (touchIndex > (NewPoints.size() - 1)) {
            touchIndex = NewPoints.size() - 1;
        }
        if (touchIndex > -1 && touchIndex < NewPoints.size()) {
            //画线上的数据
            float time = timeList.get(touchIndex);
            float x = NewPoints.get(touchIndex).x;
            float y = NewPoints.get(touchIndex).y;
            String mThumbText = String.valueOf(time);
            Rect rect = new Rect();
            linePaint.getTextBounds(mThumbText, 0, mThumbText.length(), rect);
            int with = rect.width();//文本的宽度
            int height = rect.height();//文本的高度。
            linePaint.setColor(Color.parseColor("#FFD519"));
            canvas.drawRect(x + dp2px(5), (y - height - dp2px(10)), (x + with + dp2px(19)), y - dp2px(3), linePaint);
            linePaint.setColor(getResources().getColor(R.color.address_black_key));
            canvas.drawText(calPoint(timeList.get(touchIndex)), x + dp2px(12), y - dp2px(6), linePaint);//画最上面字体
            canvas.drawCircle(x, y, dp2px(3), circlePaint2);
        }
//            canvas.drawCircle(x, y, dp2px(3), circlePaint);

        drawArea(canvas, NewPoints);
        drawBottomLine(canvas);
        drawLine(canvas);
    }

    public String calPoint(double nums) {
        String showNum = "0";
        if (nums < 10000 && nums >= 1) {
            showNum = (int) nums + "";
        } else if (nums > 9999 && nums < 99999999) {
            showNum = (int) (nums / 10000) + "万";
        } else if (nums > 99999999) {
            showNum = TwoPointUtils.getInstance().getOnePoint(nums / 100000000) + "亿";
        }
        return showNum;
    }

    /**
     * 分割线
     *
     * @param canvas
     */
    private void drawLine(Canvas canvas) {
        mPaint.setColor(getResources().getColor(R.color.address_alpha_black));
        textPaint.setColor(getResources().getColor(R.color.address_black_key));
        mPaint.setStrokeWidth(dp2px(0.5f));
        int signleTime = 0;
        if (maxTime > 10) {
            signleTime = (int) (maxTime / 5);
        } else {
            signleTime = 2;
        }
        Rect rect = new Rect();
        String text = "0";
        mPaint.getTextBounds(text, 0, text.length(), rect);
        for (int i = 0; i < 5; i++) {
            float y = (sumHeight - (oneHeight * signleTime * (i + 1)));
            canvas.drawText(calPoint((i + 1) * signleTime), 0, y - buttomHeiht - rect.height() / 2 - dp2px(4), textPaint);
        }
        canvas.drawLine(startX, sumHeight - buttomHeiht, startX, (sumHeight - (oneHeight * signleTime * (4 + 1))) - buttomHeiht - rect.height() / 2 - dp2px(4), mPaint);
    }

    private int touchIndex = 1;
    private float xDes = 0;
    //起始x
    private float startX = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX;
        float touchY;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchX = event.getX(); //touch点的坐标
                touchY = event.getY();
                touchX = touchX - 100;

//                LogUtils.e(touchX + "   ,   " + mGridWidth);
                int xIndex = (int) (touchX / xDes);
                float xIndexModel = touchX % xDes;
                if (xIndexModel > xDes / 2) {
                    touchIndex = xIndex + 1;
                } else {
                    touchIndex = xIndex;
                }

                invalidate();
                //判断touch点到圆心的距离 是否小于半径
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
                break;
        }

        return super.onTouchEvent(event);
    }


    /**
     * 底部标线
     *
     * @param canvas
     */
    private void drawBottomLine(Canvas canvas) {
        mPaint.setColor(Color.parseColor("#EBEBEB"));
        mPaint.setStrokeWidth(dp2px(1));
        Rect rect = new Rect();
        mPaint.getTextBounds("0", 0, "0".length(), rect);
        canvas.drawLine(startX, sumHeight - buttomHeiht, sumWidth, sumHeight - buttomHeiht, mPaint);//画底部灰线
    }

    /**
     * 阴影层叠
     *
     * @param canvas
     * @param Points
     */

    private void drawArea(Canvas canvas, List<PointF> Points) {
//        LinearGradient mShader = new LinearGradient(0, 0, 0, getMeasuredHeight(), new int[]{Color.parseColor("#BAEFE6"), Color.parseColor("#D7F5F0"), Color.parseColor("#F9FEFD")}, new float[]{0.5f, 0.65f, 0.85f}, Shader.TileMode.REPEAT);
////        baseShadow.setShader(mShader);
//        if (Points.size() > 0 && xyList != null && xyList.size() > 0) {
//            baseLinePath.lineTo(xyList.get(Points.size() - 1).x, sumHeight - buttomHeiht);
//            baseLinePath.lineTo(xyList.get(0).x, sumHeight - buttomHeiht);
//            baseLinePath.close();
//            canvas.drawPath(baseLinePath, baseShadow);
//        }

    }

    public int dp2px(float dp) {
        final float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * 取出时间里面的最大的一个用来计算总长度
     *
     * @param timeList
     * @return
     */
    public float getMaxTime(List<Integer> timeList) {
        maxTime = 0;
        for (int i = 0; i < 7; i++) {
            if (maxTime < timeList.get(i)) {
                maxTime = timeList.get(i);
            }
        }
        if (maxTime <= 10) {
            maxTime = 10;
        }
        maxTime *= 1.2;
        return maxTime;
    }

    public int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public float dp2px(Resources resources, float dp) {
        final float scale = resources.getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    public float sp2px(Resources resources, float sp) {
        final float scale = resources.getDisplayMetrics().scaledDensity;
        return sp * scale;
    }
}
