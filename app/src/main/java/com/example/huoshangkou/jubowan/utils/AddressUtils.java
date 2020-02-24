package com.example.huoshangkou.jubowan.utils;

import com.example.huoshangkou.jubowan.base.BaseApp;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：AddressUtils
 * 描述：地址管理工具
 * 创建时间：2017-03-13  14:52
 */

public class AddressUtils {

    private static class AddressHelper {
        private static AddressUtils INSTANCE = new AddressUtils();
    }

    public static AddressUtils getInstance() {
        return AddressHelper.INSTANCE;
    }

    //保存默认地址
    public void saveDefaultAddress(String addressId, String address, String linkMan, String linkTel) {
        SharedPreferencesUtils.getInstance().put(BaseApp.getInstance(), SharedKeyConstant.getInstance().DEFAULT_ADDRESS_ID, addressId);
        SharedPreferencesUtils.getInstance().put(BaseApp.getInstance(), SharedKeyConstant.getInstance().DEFAULT_ADDRESS, address);
        SharedPreferencesUtils.getInstance().put(BaseApp.getInstance(), SharedKeyConstant.getInstance().DEFAULT_RECEIVE, linkMan);
        SharedPreferencesUtils.getInstance().put(BaseApp.getInstance(), SharedKeyConstant.getInstance().DEFAULT_LINK_PHONE, linkTel);
    }


    //获取地址id
    public String getAddressId() {
        return (String) SharedPreferencesUtils.getInstance().get(BaseApp.getInstance(), SharedKeyConstant.getInstance().DEFAULT_ADDRESS_ID, "");
    }

    //获取默认地址
    public String getDefaultAddress() {
        return (String) SharedPreferencesUtils.getInstance().get(BaseApp.getInstance(), SharedKeyConstant.getInstance().DEFAULT_ADDRESS, "请选择地址");
    }

    //获取联系人
    public String getLinkMan() {
        return (String) SharedPreferencesUtils.getInstance().get(BaseApp.getInstance(), SharedKeyConstant.getInstance().DEFAULT_RECEIVE, "请选择联系人");
    }

    //获取联系电话
    public String getLinkTel() {
        return (String) SharedPreferencesUtils.getInstance().get(BaseApp.getInstance(), SharedKeyConstant.getInstance().DEFAULT_LINK_PHONE, "请选择联系方式");
    }

}
