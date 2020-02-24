package com.example.huoshangkou.jubowan.activity.function;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.ApproveTypeBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnApproveTypeCallBack;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity.function
 * 类名：ApproveSelectFunction
 * 描述：身份选择界面
 * 创建时间：2017-03-09  14:42
 */

public class ApproveSelectFunction {

    private static class ApproveSelectHelper {
        private static ApproveSelectFunction INSTANCE = new ApproveSelectFunction();
    }

    public static ApproveSelectFunction getInstance() {
        return ApproveSelectHelper.INSTANCE;
    }


    //获取身份类型
    public void getApproveType(Context context, final OnApproveTypeCallBack typeCallBack) {

        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().SHOW_USER_TYPE , new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                ApproveTypeBean typeBean = JSON.parseObject(json, ApproveTypeBean.class);
                typeCallBack.onSuccess(typeBean);
            }

            @Override
            public void onFail() {
                typeCallBack.onFail();
            }
        });
    }


}
