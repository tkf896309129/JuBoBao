package com.example.huoshangkou.jubowan.utils;

import java.text.DecimalFormat;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：MoneyUtils
 * 描述：
 * 创建时间：2018-01-24  13:20
 */

public class MoneyUtils {

    public static class MoneyHelper {
        private static MoneyUtils INSTANCE = new MoneyUtils();
    }

    public static MoneyUtils getInstance() {
        return MoneyHelper.INSTANCE;
    }

    public String getFormPrice(String price) {
        if(!StringUtils.isNoEmpty(price)){
            return "";
        }

        Double str = Double.parseDouble(price);
        if (str < 1) {
            DecimalFormat df = new DecimalFormat("0.00");
            return df.format(str);
        }
        if (!StringUtils.isNoEmpty(price) || str <= 0) {
            return "0.00";
        }

//        Double str = Double.parseDouble(price);

        DecimalFormat df = new DecimalFormat("#,###.00");
        LogUtils.e(df.format(str));
        return df.format(str);
    }

    public String getNormPrice(String price) {
        String priceNorm = price.replace(",", "");
        return priceNorm;
    }

}
