package com.example.huoshangkou.jubowan.activity.function;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnCommonSuccessCallBack;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity.function
 * 类名：ChangePswFunction
 * 描述：修改密码工具类
 * 创建时间：2017-02-27  16:54
 */

public class ChangePswFunction {

    private static class ChangePswHepler {
        private static ChangePswFunction INSTANCE = new ChangePswFunction();
    }

    public static ChangePswFunction getInstance() {
        return ChangePswHepler.INSTANCE;
    }


    /**
     * 修改密码
     */
    public void changePsw(Context context, String oldPsw, String newPsw, final OnCommonSuccessCallBack successCallBack) {
        OkhttpUtil.getInstance().setUnCacheData(context,context.getString(R.string.loading_message),UrlConstant.getInstance().URL + PostConstant.getInstance().RESET_PSW
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().PSW + "=" + oldPsw + "&"
                + FieldConstant.getInstance().NEW_PSW + "=" + newPsw, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    ToastUtils.getMineToast("修改成功");
                    successCallBack.onSuccess();
                }else{
                    successCallBack.onFail();
                }
            }

            @Override
            public void onFail() {
            }
        });
    }


}
