package com.example.huoshangkou.jubowan.utils;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：StringBuilderUtils
 * 描述：
 * 创建时间：2017-04-17  14:48
 */

public class StringBuilderUtils {

    private static class StringBuilderHelper {
        private static StringBuilderUtils INSTANCE = new StringBuilderUtils();
    }

    public static StringBuilderUtils getInstance(){
        return StringBuilderHelper.INSTANCE;
    }

    public SpannableStringBuilder setTextColor(String str, int color, int start, int end) {
        //字体颜色
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(str);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        return spannableStringBuilder;
    }


}
