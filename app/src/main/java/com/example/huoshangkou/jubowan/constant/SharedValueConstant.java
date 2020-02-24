package com.example.huoshangkou.jubowan.constant;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.constant
 * 类名：SharedValueConstant
 * 描述：value常量类
 * 创建时间：2017-02-22  14:20
 */

public class SharedValueConstant {

    private static class SharedValueHelper {
        private static SharedValueConstant INSTANCE = new SharedValueConstant();
    }

    public static SharedValueConstant getInstance() {
        return SharedValueHelper.INSTANCE;
    }

    //保存地址
    public final String SAVE_ADDRESS = "saveAddress";


    //保存发送验证码的类别
    public final String REGISTER = "register";
    public final String FIND_PSW = "find_psw";
    public final String BIND_PHONE = "bind_phone";


    //登录成功
    public final String LOGIN_SUCCESS = "login_success";
    //登录失败
    public final String LOGIN_FAIL = "login_fail";

    //中文
    public final String CHINESE = "Chinese";
    //英文
    public final String ENGLISH = "English";


    //审批同意
    public final String APPROVE_AGREE = "approve_agree";


    //需要重新获取列表
    public final String GET_MEMBER_LIST = "get_member_list";
    //不需要重新获取列表
    public final String NO_MEMBER_LIST = "no_member_list";

}
