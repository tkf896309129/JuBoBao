package com.example.huoshangkou.jubowan.activity.function;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.RepairBrandBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnCommonSuccessCallBack;
import com.example.huoshangkou.jubowan.inter.OnRepairRepliyCallBack;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity.function
 * 类名：RepairRepliyFunction
 * 描述：
 * 创建时间：2017-03-24  14:36
 */

public class RepairRepliyFunction {

    private static class RepairRepliyHelper {
        private static RepairRepliyFunction INSTANCE = new RepairRepliyFunction();
    }

    public static RepairRepliyFunction getInstance() {
        return RepairRepliyHelper.INSTANCE;
    }

    //获取维修品牌
    public void getRepairBrand(Context context, String id, final OnRepairRepliyCallBack repairRepliyCallBack) {
        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message),
                UrlConstant.getInstance().URL + PostConstant.getInstance().GET_REPAIR_BRAND
                        + FieldConstant.getInstance().ID + "=" + id, new OkhttpCallBack() {
                    @Override
                    public void onSuccess(String json) {
                        RepairBrandBean brandBean = JSON.parseObject(json, RepairBrandBean.class);
                        repairRepliyCallBack.onSuccess(brandBean);
                    }

                    @Override
                    public void onFail() {

                    }
                });
    }
    

    //提交故障申请
    public void commitRepairApply(Context context, String maintId, String brandId,
                                  String buyDate, String descript, String pics, String addressId,String standard, final OnCommonSuccessCallBack successCallBack) {
        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message),
                UrlConstant.getInstance().URL + PostConstant.getInstance().COMMIT_REPAIR_APPLY
                        + FieldConstant.getInstance().MAINTAINER_ID + "=" + maintId + "&"
                        + FieldConstant.getInstance().BRAND_ID + "=" + brandId + "&"
                        + FieldConstant.getInstance().BUY_DATA + "=" + buyDate + "&"
                        + FieldConstant.getInstance().DESCRIPT + "=" + EncodeUtils.getInstance().getEncodeString(descript) + "&"
                        + FieldConstant.getInstance().PICS + "=" + pics + "&"
                        + FieldConstant.getInstance().ADDRESS_ID + "=" + addressId + "&"
                        + FieldConstant.getInstance().GUI_GE + "=" + EncodeUtils.getInstance().getEncodeString(standard) + "&"
                        + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
                    @Override
                    public void onSuccess(String json) {
                        SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                        if (successBean.getSuccess() != -1) {
                            successCallBack.onSuccess();
                        } else {
                            successCallBack.onFail();
                        }
                    }

                    @Override
                    public void onFail() {
                        successCallBack.onFail();
                    }
                });
    }


}
