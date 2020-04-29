package com.example.huoshangkou.jubowan.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：TwoPointUtils
 * 描述：
 * 创建时间：2017-04-17  11:16
 */

public class TwoPointUtils {

    private static class TwoPointHelper {
        private static TwoPointUtils INSTANCE = new TwoPointUtils();
    }

    public static TwoPointUtils getInstance() {
        return TwoPointHelper.INSTANCE;
    }

    //保留两位有效数字
    public String getTwoPoint(double str) {
        DecimalFormat df = new DecimalFormat("######0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);//四舍五入必须加这段；*********
        String format = df.format(str);
        return format;
    }

    public String getOnePoint(double str) {
        DecimalFormat df = new DecimalFormat("######0.0");
        df.setRoundingMode(RoundingMode.HALF_UP);//四舍五入必须加这段；*********
        String format = df.format(str);
        return format;
    }

}
