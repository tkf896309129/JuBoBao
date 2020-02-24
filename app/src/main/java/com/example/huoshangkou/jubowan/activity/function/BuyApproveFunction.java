package com.example.huoshangkou.jubowan.activity.function;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.BuyInfoBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.ApproveCallBack;
import com.example.huoshangkou.jubowan.inter.OnBuyInfoCallBack;
import com.example.huoshangkou.jubowan.utils.DialogUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.MineLoadingDialogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity.function
 * 类名：BuyApproveFunction
 * 描述：买家认证工具类
 * 创建时间：2017-03-08  10:04
 */

public class BuyApproveFunction {

    private static class BuyApproveHelper {
        private static BuyApproveFunction INSTANCE = new BuyApproveFunction();
    }

    public static BuyApproveFunction getInstance() {
        return BuyApproveHelper.INSTANCE;
    }


    //买家认证
    public void onBuyApprove(Context context, String roleId, String trueName, String cardNo, String companyName,
                             String companyAddress, String eMail, String webUrl,
                             String onCard, String downCard, String work_1, String jgCode, final ApproveCallBack approveCallBack) {
        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().URL + PostConstant.getInstance().BUY_APPROVE
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().ROLE_ID + "=" + roleId + "&"
                + FieldConstant.getInstance().LINK_MAN + "=" + EncodeUtils.getInstance().getEncodeString(trueName) + "&"
                + FieldConstant.getInstance().LINK_CARD_NO + "=" + EncodeUtils.getInstance().getEncodeString(cardNo) + "&"
                + FieldConstant.getInstance().COMPANY + "=" + EncodeUtils.getInstance().getEncodeString(companyName) + "&"
                + FieldConstant.getInstance().ADDRESS + "=" + EncodeUtils.getInstance().getEncodeString(companyAddress) + "&"
                + FieldConstant.getInstance().E_MAIL + "=" + EncodeUtils.getInstance().getEncodeString(eMail) + "&"
                + FieldConstant.getInstance().WEB_URL + "=" + EncodeUtils.getInstance().getEncodeString(webUrl) + "&"
                + FieldConstant.getInstance().JG_CODE + "=" + EncodeUtils.getInstance().getEncodeString(jgCode) + "&"
                + FieldConstant.getInstance().LINK_MAN_CARD + "=" + onCard + "," + downCard + "&"
                + FieldConstant.getInstance().YYZZ_PIC + "=" + work_1, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    approveCallBack.onApproveSuccess();
                } else {
                    approveCallBack.onApproveFail();
                }
            }

            @Override
            public void onFail() {
                approveCallBack.onApproveSuccess();
            }
        });

    }


    //获取买家信息
    public void getBuyData(Context context, final OnBuyInfoCallBack callBack) {
        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().URL
                        + PostConstant.getInstance().GET_USER_INFO + FieldConstant.getInstance().USER_ID + "="
                        + PersonConstant.getInstance().getUserId(),
                new OkhttpCallBack() {
                    @Override
                    public void onSuccess(String json) {
                        BuyInfoBean infoBean = JSON.parseObject(json, BuyInfoBean.class);

                        callBack.onBuySuccess(infoBean.getReObj());
                    }

                    @Override
                    public void onFail() {

                    }
                });
    }


}
