package com.example.huoshangkou.jubowan.activity.function;

import android.content.Context;
import android.view.View;
import android.widget.AbsListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.ShopCarBean;
import com.example.huoshangkou.jubowan.bean.ShopCarListBean;
import com.example.huoshangkou.jubowan.bean.ShopCarListNewBean;
import com.example.huoshangkou.jubowan.bean.ShopGroupBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnShopCarCallBack;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.TwoPointUtils;
import com.example.huoshangkou.jubowan.view.AnimatedExpandableListView;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity.function
 * 类名：ShopCarFunction
 * 描述：购物车工具类
 * 创建时间：2017-03-14  09:29
 */

public class ShopCarFunction {

    private static class ShopCarHelper {
        private static ShopCarFunction INSTANCE = new ShopCarFunction();
    }

    public static ShopCarFunction getInstance() {
        return ShopCarHelper.INSTANCE;
    }

    //原片
    public final String YUAN = "0";
    //辅材
    public final String FU = "1";
    //原料
    public final String YUAN_LIAO = "2";

    //获取购物车数据
    public void getShopCarData(final Context context, String carType, final OnShopCarCallBack carCallBack) {
        getData(context, carType, carCallBack);
    }

    private void getData(Context context, String carType, final OnShopCarCallBack carCallBack) {
        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.add_shop_car), UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_CAR_LIST
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().CAR_TYPE + "=" + carType, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                ShopCarListNewBean carBean = JSON.parseObject(json, ShopCarListNewBean.class);
                carCallBack.onSuccess(carBean);
            }

            @Override
            public void onFail() {
                carCallBack.onFail();

            }
        });
    }

    private double allPrice;

    public String caculatePrice(List<ShopCarListBean> list_child, TextView tvNum) {
        allPrice = 0;
        double count = 0;
        int num = list_child.size();
//        for (int i = 0; i < num; i++) {
//            List<ShopCarListBean> priceList = list_child.get(i);
            int num2 = list_child.size();
            for (int j = 0; j < num2; j++) {
                if (list_child.get(j).isCheck()) {
                    allPrice += Double.parseDouble(list_child.get(j).getPrice()) * Double.parseDouble(list_child.get(j).getToNum());
                    count += Double.parseDouble(list_child.get(j).getToNum());
                }
//            }
        }

        tvNum.setText("结算(" + count + ")");

        return TwoPointUtils.getInstance().getTwoPoint(allPrice) + "";
    }

    public String caculateSinglePrice(List<ShopCarListBean> list_child) {
        allPrice = 0;
        int num = list_child.size();
        for (int i = 0; i < num; i++) {
            if (list_child.get(i).isCheck()) {
                allPrice += Double.parseDouble(list_child.get(i).getPrice()) * Double.parseDouble(list_child.get(i).getToNum());
            }
        }

        return allPrice + "";
    }

    //悬停功能
    public void setHeadStop(AnimatedExpandableListView expandableListView, List<ShopGroupBean> list_group, List<List<ShopCarListBean>> list_child) {
        expandableListView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                LogUtils.i(i + "  " + i1 + "  " + i2 + "  " + i3 + "  " + i4 + i5 + i6 + i7);
            }
        });
    }
}
