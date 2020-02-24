package com.example.huoshangkou.jubowan.activity.function;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnFindPswBack;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity.function
 * 类名：FindPswFunction
 * 描述：
 * 创建时间：2017-02-23  17:35
 */

public class FindPswFunction {

    private static class FindPswHelper {
        private static FindPswFunction INSTANCE = new FindPswFunction();
    }

    public static FindPswFunction getInstance() {
        return FindPswHelper.INSTANCE;
    }


    //找回密码
    public void getBackPsw(Context context,String tel, String newPsw, final OnFindPswBack findPswBack) {
        OkhttpUtil.getInstance().setUnCacheData(context,context.getString(R.string.loading_message),UrlConstant.getInstance().URL + PostConstant.getInstance().FIND_PSW +
                FieldConstant.getInstance().TEL + "=" + tel + "&" +
                FieldConstant.getInstance().NEW_PSW + "=" + newPsw, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    ToastUtils.getMineToast("找回成功");
                    findPswBack.onFindPswCallBack();

                } else {
                    ToastUtils.getMineToast("找回失败");
                }
            }

            @Override
            public void onFail() {
            }
        });
    }

}
