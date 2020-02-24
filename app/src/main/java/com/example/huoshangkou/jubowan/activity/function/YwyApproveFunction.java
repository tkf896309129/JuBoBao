package com.example.huoshangkou.jubowan.activity.function;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.bean.YwyApproveBean;
import com.example.huoshangkou.jubowan.bean.YwyInfoBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.ApproveCallBack;
import com.example.huoshangkou.jubowan.inter.OnYwyApproveCallBack;
import com.example.huoshangkou.jubowan.inter.OnYwyApproveTypeCallBack;
import com.example.huoshangkou.jubowan.utils.DialogUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.MineLoadingDialogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity.function
 * 类名：YwyApproveFunction
 * 描述：业务员认证工具类
 * 创建时间：2017-03-08  13:22
 */

public class YwyApproveFunction {

    private static class YwyApproveHelper {
        private static YwyApproveFunction INSTANCE = new YwyApproveFunction();
    }

    public static YwyApproveFunction getInstance() {
        return YwyApproveHelper.INSTANCE;
    }


    //业务员认证功能
    public void YwyApproveFun(Context context, String roleId, String classType, String brandType,
                              String areaType, String trueName, String cardNo, String companyName,
                              String linkMail, String webUrl, String idCard, String workCard, final ApproveCallBack callBack) {

        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().URL + PostConstant.getInstance().YWY_APPROVE
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().CLASS_NAME + "=" + EncodeUtils.getInstance().getEncodeString(classType) + "&"
                + FieldConstant.getInstance().BRAND_NAME + "=" + EncodeUtils.getInstance().getEncodeString(brandType) + "&"
                + FieldConstant.getInstance().AREA_NAME + "=" + EncodeUtils.getInstance().getEncodeString(areaType) + "&"
                + FieldConstant.getInstance().REAL_NAME + "=" + EncodeUtils.getInstance().getEncodeString(trueName) + "&"
                + FieldConstant.getInstance().ROLE_ID + "=" + roleId + "&"
                + FieldConstant.getInstance().CARD_NO + "=" + EncodeUtils.getInstance().getEncodeString(cardNo) + "&"
                + FieldConstant.getInstance().COMPANY_NAME + "=" + EncodeUtils.getInstance().getEncodeString(companyName) + "&"
                + FieldConstant.getInstance().E_MAIL + "=" + EncodeUtils.getInstance().getEncodeString(linkMail) + "&"
                + FieldConstant.getInstance().WEB_URL + "=" + EncodeUtils.getInstance().getEncodeString(webUrl) + "&"
                + FieldConstant.getInstance().WORK_PIC + "=" + EncodeUtils.getInstance().getEncodeString(workCard) + "&"
                + FieldConstant.getInstance().CARD_PIC + "=" + EncodeUtils.getInstance().getEncodeString(idCard), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    callBack.onApproveSuccess();
                } else {
                    callBack.onApproveFail();
                }
            }

            @Override
            public void onFail() {
                callBack.onApproveFail();
            }
        });
    }


    //获取原片 类别、品牌、地区
    public void getYpCBA(Context context, final OnYwyApproveTypeCallBack callBack) {

        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().URL + PostConstant.getInstance().GET_YP_CBA
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                YwyApproveBean approveBean = JSON.parseObject(json, YwyApproveBean.class);

                callBack.onSuccess(approveBean);

            }

            @Override
            public void onFail() {

            }
        });
    }


    //获取业务员认证信息
    public void getYwyApproveData(Context context, final OnYwyApproveCallBack callBack) {
        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_YWY_INFO + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                YwyInfoBean infoBean = JSON.parseObject(json, YwyInfoBean.class);

                callBack.onSuccess(infoBean.getReObj());
            }

            @Override
            public void onFail() {

            }
        });
    }


}
