package com.example.huoshangkou.jubowan.constant;

import android.content.Context;

import com.example.huoshangkou.jubowan.base.BaseApp;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.LoginUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.constant
 * 类名：PersonConstant
 * 描述：18973374021
 * 创建时间：2017-02-22  13:42
 */

public class PersonConstant {
    private static class PersonHelper {
        private static PersonConstant INSTANCE = new PersonConstant();
    }
    public static PersonConstant getInstance() {
        return PersonHelper.INSTANCE;
    }
    //获取手机型号唯一标识
    public String getPhoneTypeId() {
        return (String) SharedPreferencesUtils.getInstance().get(BaseApp.getInstance(), SharedKeyConstant.getInstance().ONLY_ONE_DEVICE, "");
    }
    
    //获取手机型号
    public String getPhoneType() {
        return (String) SharedPreferencesUtils.getInstance().get(BaseApp.getInstance(), SharedKeyConstant.getInstance().PHOE_TYPE, "");
    }

    public String getCustomerManageUserId() {
        return (String) SharedPreferencesUtils.getInstance().get(BaseApp.getInstance(), SharedKeyConstant.getInstance().USER_MANAGE_ID, "");
    }

    public String getCustomerManagePhone() {
        return (String) SharedPreferencesUtils.getInstance().get(BaseApp.getInstance(), SharedKeyConstant.getInstance().USER_MANAGE_PHONE_ID, "");
    }

    //是否推送
    public String getIsPush() {
        return (String) SharedPreferencesUtils.getInstance().get(BaseApp.getInstance(), SharedKeyConstant.getInstance().PUSH, "");
    }

    //获取UserId
    public String getUserId() {
        return (String) SharedPreferencesUtils.getInstance().get(BaseApp.getInstance(), SharedKeyConstant.getInstance().ID, "0");
//       zy hcl
//        return "2194";
//        return "672";
//        return "11557";
//        return "19938";
//        return "9526";
//        return "1247";
//        return "4652";
//        return "132";
//        return "8124";
//        return "6861";
//        return "4447";
//        return "7444";
//        return "2885";
//        return "6740";
//        return "8874";
//        return "7837";
//        return "1673";
//                return "2901";
//        lyj
//        return "1298";
        //lch
//        return "413";
        //kz
//        return "670";
        // hsy
//        return "10597";
//                return "1232";
//                return "3241";
        //        return "1903";
//                return "281";
//                return "1204";
//                return "675";
        //        return "1104";
        //        return "6774";
//        return "1686";
//        return "3871";
//        hlj
//        return "161";
//        return "4883";
//        return "312";
//        return "3383";
//        return "3508";
//        return "8436";
//        return "1389";
//        return "2752";
//        return "11549";
//        return "19110";
//        return "2743";
    }

    //获取头像
    public String getHeadPic(Context context) {
        return (String) SharedPreferencesUtils.getInstance().get(context, SharedKeyConstant.getInstance().HEAD_PIC, "");
    }

    //获取昵称
    public String getNick(Context context) {
        return (String) SharedPreferencesUtils.getInstance().get(context, SharedKeyConstant.getInstance().NICK_NAME, "");
    }

    public String getRealName(Context context) {
        return (String) SharedPreferencesUtils.getInstance().get(context, SharedKeyConstant.getInstance().REAL_NAME, "");
    }

    //获取电话号码
    public String getPhone(Context context) {
        return (String) SharedPreferencesUtils.getInstance().get(context, SharedKeyConstant.getInstance().TEL, "");
    }

    //保存本地头像
    public void setHeadPic(Context context, String pic) {
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().HEAD_PIC, pic);
    }

    //获取身份类型  0=普通用户，1=建筑商，2=加工厂，3=原片厂商销售员,4=加工厂业务合伙人,5=辅料业务合伙人,6=建筑商业务合伙人,7=维修业务合伙人
    public String getRoleType(Context context) {
        return (String) SharedPreferencesUtils.getInstance().get(context, SharedKeyConstant.getInstance().USER_TYPE, "");
    }
}
