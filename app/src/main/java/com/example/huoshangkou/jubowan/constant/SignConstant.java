package com.example.huoshangkou.jubowan.constant;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.constant
 * 类名：SignConstant
 * 描述：
 * 创建时间：2017-04-19  09:03
 */

public class SignConstant {

    private static class SignHelper {
        private static SignConstant INSTANCE = new SignConstant();
    }

    public static SignConstant getInstance() {
        return SignHelper.INSTANCE;
    }

    //已审核补签
    public final String CHECK_SIGN = "check";

    //待审核补签
    public final String UN_CHECK_SIGN = "unCheck";


}
