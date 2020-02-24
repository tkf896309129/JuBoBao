package com.example.huoshangkou.jubowan.activity.function;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.MessageTypeBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.inter.SuccessCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity.function
 * 类名：MessageFunction
 * 描述：
 * 创建时间：2017-05-12  17:01
 */

public class MessageFunction {

    private static class MessageHelper {
        private static MessageFunction INSTANCE = new MessageFunction();
    }

    public static MessageFunction getInstance() {
        return MessageHelper.INSTANCE;
    }

    //获取订单通知
    public void getOrderNotify(Context context, String messageType, int page, final StringCallBack successCallBack) {
        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_MESSAGE + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().MESSAGE_TYPE + "=" + messageType + "&"
                + FieldConstant.getInstance().PAGE + "=" + page, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                MessageTypeBean typeBean = JSON.parseObject(json, MessageTypeBean.class);
                successCallBack.onSuccess(json);
            }

            @Override
            public void onFail() {
                successCallBack.onFail();

            }
        });
    }

}
