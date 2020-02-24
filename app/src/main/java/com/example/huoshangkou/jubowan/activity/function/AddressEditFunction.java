package com.example.huoshangkou.jubowan.activity.function;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.SharedValueConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnEditAddressBack;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity.function
 * 类名：AddressEditFunction
 * 描述：添加地址
 * 创建时间：2017-02-22  14:05
 */

public class AddressEditFunction {


    private static class AddressEditHelper {
        private static AddressEditFunction INSTANCE = new AddressEditFunction();
    }

    public static AddressEditFunction getInstance() {
        return AddressEditHelper.INSTANCE;
    }


    //添加地址
    public void editAddress(final Context context, final String addressId, String detailAddress, String linkMan, String linkPhone, String province, final OnEditAddressBack editAddressBack) {

        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().URL +
                PostConstant.getInstance().EDIT_ADDRESS + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().ID + "=" + addressId + "&"
                + FieldConstant.getInstance().DETAIL_ADDRESS + "=" + EncodeUtils.getInstance().getEncodeString(detailAddress) + "&"
                + FieldConstant.getInstance().LINK_MAN + "=" + EncodeUtils.getInstance().getEncodeString(linkMan) + "&"
                + FieldConstant.getInstance().LINK_TEL + "=" + EncodeUtils.getInstance().getEncodeString(linkPhone) + "&"
                + FieldConstant.getInstance().PROVINCE + "=" + EncodeUtils.getInstance().getEncodeString(province), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    //保存地址标记
                    SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().ADD_ADDRESS, SharedValueConstant.getInstance().SAVE_ADDRESS);
                    if (StringUtils.isNoEmpty(addressId)) {
                        ToastUtils.getMineToast("编辑成功");
                    } else {
                        ToastUtils.getMineToast("添加成功");
                    }
                    editAddressBack.onEditAddress();
                } else {
                    if (StringUtils.isNoEmpty(addressId)) {
                        ToastUtils.getMineToast("编辑失败");
                    } else {
                        ToastUtils.getMineToast("添加失败");
                    }
                }
            }

            @Override
            public void onFail() {

            }
        });

    }


}
