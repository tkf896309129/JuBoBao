package com.example.huoshangkou.jubowan.activity.function;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.SuccessCallBack;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity.function
 * 类名：SignFunction
 * 描述：
 * 创建时间：2017-04-05  08:54
 */

public class SignFunction {

    private static class SignHelper {
        private static SignFunction INSTANCE = new SignFunction();
    }

    public static SignFunction getInstance() {
        return SignHelper.INSTANCE;
    }

    //提交考勤
    public void putSignTrack(Context context, String pic, String remark, String address, String creatTime, String x, String y, String poinName,
                             final SuccessCallBack successCallBack) {
        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message),
                UrlConstant.getInstance().URL + PostConstant.getInstance().PUT_USER_TRACK
                        + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                        + FieldConstant.getInstance().ADDRESS + "=" + EncodeUtils.getInstance().getEncodeString(address) + "&"
                        + FieldConstant.getInstance().PIC + "=" + pic + "&"
                        + FieldConstant.getInstance().REMARK + "=" + EncodeUtils.getInstance().getEncodeString(remark) + "&"
                        + FieldConstant.getInstance().CREATE_TIME + "=" + creatTime + "&"
                        + FieldConstant.getInstance().X + "=" + x + "&"
                        + FieldConstant.getInstance().Y + "=" + y + "&"
                        + FieldConstant.getInstance().POIN_NAME + "=" + EncodeUtils.getInstance().getEncodeString(poinName), new OkhttpCallBack() {
                    @Override
                    public void onSuccess(String json) {
                        SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                        if (successBean.getSuccess() == 1) {
                            successCallBack.onSuccess();
                        } else {
                            successCallBack.onFail();
                        }
                    }

                    @Override
                    public void onFail() {
                        successCallBack.onFail();
                    }
                });
    }


}
