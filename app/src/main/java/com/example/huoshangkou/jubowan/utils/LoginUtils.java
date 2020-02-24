package com.example.huoshangkou.jubowan.utils;

import android.content.Context;

import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.SharedValueConstant;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：LoginUtils
 * 描述：
 * 创建时间：2017-07-04  13:42
 */

public class LoginUtils {


    public static class LoginHelper {
        private static LoginUtils INSTANCE = new LoginUtils();
    }

    public static LoginUtils getInstance() {
        return LoginHelper.INSTANCE;
    }

    //判断用户是否登录
    public boolean isLogin(Context context) {
        String loginState = (String) SharedPreferencesUtils.getInstance().get(context, SharedKeyConstant.getInstance().LOGIN_STATE, "");
        return (StringUtils.isNoEmpty(loginState) && loginState.equals(SharedValueConstant.getInstance().LOGIN_SUCCESS));
    }


}
