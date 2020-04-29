package com.example.huoshangkou.jubowan.utils;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.OptionsPickerView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.AllAreaBean;
import com.example.huoshangkou.jubowan.bean.ProvinceBean;
import com.example.huoshangkou.jubowan.bean.ProvinceListBean;
import com.example.huoshangkou.jubowan.inter.ChooseDialogCallBack;
import com.example.huoshangkou.jubowan.inter.CityNameInterface;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static com.example.huoshangkou.jubowan.R.id.tv;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：CityUtils
 * 描述：
 * 创建时间：2017-02-21  09:06
 */

public class CityUtils {


    ArrayList<String> options1Items = new ArrayList<>();
    ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    private static class CityHelper {
        private static CityUtils INSTANCE = new CityUtils();
    }

    public static CityUtils getInstance() {
        return CityHelper.INSTANCE;
    }

    //获取全国所有城市列表
    public void getAllCity(Context context, final CityNameInterface cityNameInterface) {

        AllAreaBean allAreaBean = null;

        try {
            InputStream inputStream = context.getAssets().open("AllCity.txt");
            int size = inputStream.available();
            if (size == 0) {
                return;
            }
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String city = new String(buffer);
            allAreaBean = JSON.parseObject(city, AllAreaBean.class);
            //获取所有的省份名字
            for (int i = 0; i < allAreaBean.getProvince().size(); i++) {
                ProvinceBean provinceBean = new ProvinceBean();
                provinceBean.setId(i);
                provinceBean.setName(allAreaBean.getProvince().get(i).getName());
                options1Items.add(allAreaBean.getProvince().get(i).getName());

                //存放地区
                ArrayList<ArrayList<String>> listArea = new ArrayList<>();
                //存放城市名字
                ArrayList<String> listCity = new ArrayList<>();
                for (int j = 0; j < allAreaBean.getProvince().get(i).getCity().size(); j++) {
                    listCity.add(allAreaBean.getProvince().get(i).getCity().get(j).getName());
                    listArea.add((ArrayList<String>) allAreaBean.getProvince().get(i).getCity().get(j).getArea());
                }
                options3Items.add(listArea);
                options2Items.add(listCity);
            }

            PickDialogUtils.getInstance().getThreePick(context, "请选择城市",
                    options1Items, options2Items, options3Items, new ChooseDialogCallBack() {
                        @Override
                        public void onClickSuccess(String choose) {
//                    //获取所有的城市名称
                            cityNameInterface.getCityName(choose);
                        }
                    });
            LogUtils.i(city);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
