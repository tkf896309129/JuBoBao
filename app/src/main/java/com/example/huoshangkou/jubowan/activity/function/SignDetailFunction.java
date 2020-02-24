package com.example.huoshangkou.jubowan.activity.function;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.SignDetailBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnSignDetailCallBack;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity.function
 * 类名：SignDetailFunction
 * 描述：
 * 创建时间：2017-04-05  11:41
 */

public class SignDetailFunction {


    private static class SignDetailHelper {
        private static SignDetailFunction INSTANCE = new SignDetailFunction();
    }

    public static SignDetailFunction getInstance() {
        return SignDetailHelper.INSTANCE;
    }


    //获取考勤详情   "http://192.168.10.120/webapi/atapi/GetUserTrack/?UserID=1903&Time=2016-11-30%2003:02:01.000"
    public void getSignDetail(String company, String postConstant, String time, Context context, String id, final OnSignDetailCallBack detailCallBack) {
        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message),
                UrlConstant.getInstance().URL + postConstant
                        + FieldConstant.getInstance().USER_ID + "=" + id + "&"
                        + FieldConstant.getInstance().COMPANY + "=" + EncodeUtils.getInstance().getEncodeString(company) + "&"
                        + FieldConstant.getInstance().TIME + "=" + time, new OkhttpCallBack() {
                    @Override
                    public void onSuccess(String json) {
                        SignDetailBean detailBean = JSON.parseObject(json, SignDetailBean.class);
                        detailCallBack.onSuccess(detailBean);
                    }

                    @Override
                    public void onFail() {
                        detailCallBack.onFail();
                    }
                });
    }


}
