package com.example.huoshangkou.jubowan.utils;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.huoshangkou.jubowan.bean.InternalPhoneBean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：InternalPhoneUtils
 * 描述：
 * 创建时间：2019-08-23  08:32
 */

public class InternalPhoneUtils {

    public static List<InternalPhoneBean> getPhone(Context context) {
        List<InternalPhoneBean> phoneBeanList = null;
        try {
            InputStream inputStream = context.getAssets().open("InternationalePhone.txt");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String phone = new String(buffer);
            phoneBeanList = JSONArray.parseArray(phone, InternalPhoneBean.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return phoneBeanList;
    }

}
