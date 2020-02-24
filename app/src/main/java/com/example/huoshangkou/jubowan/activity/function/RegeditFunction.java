package com.example.huoshangkou.jubowan.activity.function;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.MainActivity;
import com.example.huoshangkou.jubowan.bean.RegisterBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.SharedValueConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnCommonSuccessCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity.function
 * 类名：RegeditFunction
 * 描述：注册工具类
 * 创建时间：2017-02-23  15:36
 */

public class RegeditFunction {

    private static class RegeditHelper {
        private static RegeditFunction INSTANCE = new RegeditFunction();
    }

    public static RegeditFunction getInstance() {
        return RegeditHelper.INSTANCE;
    }

    //注册功能
    public void setRegeditFun(String phone, String psw,String areaCode, String deviceToken, final Context context, final OnCommonSuccessCallBack successCallBack) {
        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().URL + PostConstant.getInstance().REGISTER +
                FieldConstant.getInstance().TEL + "=" + phone + "&" +
                FieldConstant.getInstance().PSW + "=" + psw + "&" +
                FieldConstant.getInstance().AREA_COED + "=" + areaCode + "&" +
                FieldConstant.getInstance().UMENG_ID + "=" + deviceToken, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                RegisterBean registerBean = JSON.parseObject(json, RegisterBean.class);
                LogUtils.i(registerBean.toString());
                if (registerBean.getSuccess() == 1) {
                    ToastUtils.getMineToast("注册成功");
                    LoginFunction.getInstance().saveLoginMessage(registerBean.getReObj(), context);
                    SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().LOGIN_STATE, SharedValueConstant.getInstance().LOGIN_SUCCESS);
                    IntentUtils.getInstance().toActivity(context, MainActivity.class);
                    //登录成功
                    SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().IS_LOGIN_TO_MAIN, "yes");
                    successCallBack.onSuccess();
                } else {
                    ToastUtils.getMineToast(registerBean.getErrMsg());
                    successCallBack.onFail();
                }
            }

            @Override
            public void onFail() {
            }
        });
    }
}
