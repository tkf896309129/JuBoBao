package com.example.huoshangkou.jubowan.activity.function;

import com.example.huoshangkou.jubowan.bean.CalculatorBean;
import com.example.huoshangkou.jubowan.inter.OnCommonSuccessCallBack;

import java.text.DecimalFormat;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity.function
 * 类名：CalculatorFunction
 * 描述：
 * 创建时间：2017-04-14  14:24
 */

public class CalculatorFunction {

    //厚度
    public int[] thick = {4, 8, 5, 6, 10, 12, 15, 19, 21};

    //参数
    public double[] thickKey = {100, 50, 80, 66.67, 40, 33.33, 26.67, 21.05, 19.05};

    //规格
    public String[] standard = {"6A", "9A", "12A", "16A", "0.38", "0.52", "0.76", "1.14", "1.52"};

    //规格参数
    public double[] standardKye = {3, 5, 9, 18, 1, 1.5, 3, 5, 6};


    private static class CalcultorHelper {
        private static CalculatorFunction INSTANCE = new CalculatorFunction();
    }

    public static CalculatorFunction getInstance() {
        return CalcultorHelper.INSTANCE;
    }

    //重量转面积
    public void weightToArea(double ton) {

    }

    //面积转重量
    public double areaToWeight(double area, double thick) {
        double kg = area * thick * 2500 / 1000;
        return kg;
    }

    //价格计算
    public double calculatorPrice(double price, double value) {
        double priceResult = price / value;
        DecimalFormat df = new DecimalFormat("######0.00");
        double format = Double.parseDouble(df.format(priceResult));
        return format;
    }

    public void calculatorPriceList(List<CalculatorBean> priceList, double price, OnCommonSuccessCallBack successCallBack) {
        int num = thickKey.length;
        for (int i = 0; i < num; i++) {
            CalculatorBean calculatorBean = new CalculatorBean();
            calculatorBean.setThick(thick[i] + "mm");
            calculatorBean.setPrice(calculatorPrice(price, thickKey[i]) + "元/㎡");
            priceList.add(calculatorBean);
        }
        successCallBack.onSuccess();
    }


    //建筑玻璃核价  (面积 *　厚度 * 2500 / 1000) * 2 + 面积 * 参数值
    public double buildPrice(double area, double thick, double canValue) {
        double ton = (area * thick * 2500 / 1000) * 2 + area * canValue;
        return ton;
    }


    //计算获取面积
    public double getArea(double ton, double thick) {
        double area = ton * 1000 * 1000 / 2500 / thick;
        DecimalFormat df = new DecimalFormat("######0.00");
        double format = Double.parseDouble(df.format(area));
        return format;
    }

    //获取面积集合
    public void getAreaList(double ton, List<CalculatorBean> areaList, OnCommonSuccessCallBack successCallBack) {
        areaList.clear();
        int num = thick.length;
        for (int i = 0; i < num; i++) {
            CalculatorBean calculatorBean = new CalculatorBean();
            calculatorBean.setThick(thick[i] + "mm");
            calculatorBean.setArea(getArea(ton, thick[i]) + "㎡");
            areaList.add(calculatorBean);
        }

        successCallBack.onSuccess();
    }


}
