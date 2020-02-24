package com.example.huoshangkou.jubowan.fragment.function;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnSetUserPicCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.fragment.function
 * 类名：MineFunciont
 * 描述：
 * 创建时间：2017-03-13  09:08
 */

public class MineFunciont {

    private static class MineHelper {
        private static MineFunciont INSTANCE = new MineFunciont();
    }

    public static MineFunciont getInstance() {
        return MineHelper.INSTANCE;
    }


    //设置头像
    public void setUserPic(Context context, String pic, final OnSetUserPicCallBack callBack) {
        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().SET_USER_PIC + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().PIC + "=" + pic, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                callBack.onSuccess();
            }

            @Override
            public void onFail() {
                callBack.onFail();
            }
        });
    }


}
