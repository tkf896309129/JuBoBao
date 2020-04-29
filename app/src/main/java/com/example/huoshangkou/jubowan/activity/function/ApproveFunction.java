package com.example.huoshangkou.jubowan.activity.function;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.bean.SuccessDBean;
import com.example.huoshangkou.jubowan.bean.SuccessObjBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnApplyCommitCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity.function
 * 类名：ApproveListFunction
 * 描述：审批相关工具类
 * 创建时间：2017-03-16  10:43
 */

public class ApproveFunction {

    private static class ApproveHelper {
        private static ApproveFunction INSTANCE = new ApproveFunction();
    }

    public static ApproveFunction getInstance() {
        return ApproveHelper.INSTANCE;
    }

    /**
     * @param context       上下文
     * @param approveTypeId 申请类型id  这可是
     * @param typeName      申请类型
     * @param typePrice     申请价格
     * @param startTime     开始时间
     * @param endTime       结束时间
     * @param startTimes    开始时间段
     * @param endTimes      结束时间段
     * @param address       地址
     * @param invoince      是否有发票  0没有发票 1有发票
     * @param remark        说明
     * @param pics          图片
     * @param approveId     审批人id
     */
    public void applyToApprove(Context context, String approveTypeId, String typeName, String typePrice,
                               String startTime, String endTime, String startTimes, String endTimes, String address,
                               String invoince, String remark, String pics, String approveId, String timeSpan, String fundWay,
                               String spBankId, String csId, String time, String isChangeDevice, final OnApplyCommitCallBack commitCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put(FieldConstant.getInstance().APPROVE_TYPE_ID, approveTypeId);
        map.put(FieldConstant.getInstance().TYPE_NAME, typeName);
        map.put(FieldConstant.getInstance().TYPE_PRICE, typePrice);
        map.put(FieldConstant.getInstance().START_TIME, startTime);
        map.put(FieldConstant.getInstance().END_TIME, endTime);
        map.put(FieldConstant.getInstance().START_TIMES, startTimes);
        map.put(FieldConstant.getInstance().END_TIMES, endTimes);
        map.put(FieldConstant.getInstance().APPROVE_ADDRESS, address);

        map.put(FieldConstant.getInstance().INVOICE, invoince);
        map.put(FieldConstant.getInstance().REMARK, StringUtils.getNoEmptyStr(remark));
        map.put(FieldConstant.getInstance().PICS, pics);
        map.put(FieldConstant.getInstance().USER_ID, PersonConstant.getInstance().getUserId());
        map.put(FieldConstant.getInstance().TIME_SPAN, timeSpan);
        map.put(FieldConstant.getInstance().FUND_WAY, fundWay);
        map.put(FieldConstant.getInstance().SP_BANK_ID, spBankId);
        map.put(FieldConstant.getInstance().COPY_USER_ID, csId);
        map.put(FieldConstant.getInstance().APPROVE_USER_ID, approveId);
        map.put(FieldConstant.getInstance().TIME, time);
        map.put(FieldConstant.getInstance().IS_RESET_EQUIMENT, isChangeDevice);
        String json = "{\n" +
                "\"model\":" + JSON.toJSONString(map) + "}";
        OkhttpUtil.getInstance().basePostCall(context, json, UrlConstant.getInstance().POST_APPLY_CHECK, new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                SuccessDBean successBean = JSON.parseObject(str, SuccessDBean.class);
                if (successBean.getD() != null && successBean.getD().getSuccess() == 1) {
                    commitCallBack.onSuccess();
                    ToastUtils.getMineToast("提交成功");
                } else {
                    ToastUtils.getMineToast("提交失败");
                    commitCallBack.onFail();
                }
//                ToastUtils.getMineToast(successBean.getD().getErrMsg());
            }

            @Override
            public void onFail() {

            }
        });

//        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message),
//                UrlConstant.getInstance().URL + PostConstant.getInstance().ADD_APPLY
//                        + FieldConstant.getInstance().APPROVE_TYPE_ID + "=" + EncodeUtils.getInstance().getEncodeString(approveTypeId) + "&"
//                        + FieldConstant.getInstance().TYPE_NAME + "=" + EncodeUtils.getInstance().getEncodeString(typeName) + "&"
//                        + FieldConstant.getInstance().TYPE_PRICE + "=" + EncodeUtils.getInstance().getEncodeString(typePrice) + "&"
//                        + FieldConstant.getInstance().START_TIME + "=" + startTime + "&"
//                        + FieldConstant.getInstance().END_TIME + "=" + endTime + "&"
//                        + FieldConstant.getInstance().START_TIMES + "=" + EncodeUtils.getInstance().getEncodeString(startTimes) + "&"
//                        + FieldConstant.getInstance().END_TIMES + "=" + EncodeUtils.getInstance().getEncodeString(endTimes) + "&"
//                        + FieldConstant.getInstance().APPROVE_ADDRESS + "=" + EncodeUtils.getInstance().getEncodeString(address) + "&"
//                        + FieldConstant.getInstance().INVOICE + "=" + invoince + "&"
//                        + FieldConstant.getInstance().REMARK + "=" + EncodeUtils.getInstance().getEncodeString(remark) + "&"
//                        + FieldConstant.getInstance().PICS + "=" + EncodeUtils.getInstance().getEncodeString(pics) + "&"
//                        + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
//                        + FieldConstant.getInstance().TIME_SPAN + "=" + timeSpan + "&"
//                        + FieldConstant.getInstance().FUND_WAY + "=" + EncodeUtils.getInstance().getEncodeString(fundWay) + "&"
//                        + FieldConstant.getInstance().SP_BANK_ID + "=" + EncodeUtils.getInstance().getEncodeString(spBankId) + "&"
//                        + FieldConstant.getInstance().COPY_USER_ID + "=" + csId + "&"
//                        + FieldConstant.getInstance().APPROVE_USER_ID + "=" + approveId, new OkhttpCallBack() {
//                    @Override
//                    public void onSuccess(String json) {
//                        SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
//                        if (successBean.getSuccess() == 1) {
//                            commitCallBack.onSuccess();
//                        } else {
//                            commitCallBack.onFail();
//                        }
//                    }
//
//                    @Override
//                    public void onFail() {
//                        commitCallBack.onFail();
//                    }
//                });
    }


    public void applyToApprove(Context context, String approveTypeId, String typeName, String typePrice,
                               String startTime, String endTime, String startTimes, String endTimes, String address,
                               String invoince, String remark, String pics, String approveId, String timeSpan, String csId, final OnApplyCommitCallBack commitCallBack) {

        Map<String, String> map = new HashMap<>();
        map.put(FieldConstant.getInstance().APPROVE_TYPE_ID, approveTypeId);
        map.put(FieldConstant.getInstance().TYPE_NAME, typeName);
        map.put(FieldConstant.getInstance().TYPE_PRICE, StringUtils.getNullStr(typePrice));
        map.put(FieldConstant.getInstance().START_TIME, startTime);
        map.put(FieldConstant.getInstance().END_TIME, endTime);
        map.put(FieldConstant.getInstance().START_TIMES, startTimes);
        map.put(FieldConstant.getInstance().END_TIMES, endTimes);
        map.put(FieldConstant.getInstance().APPROVE_ADDRESS, address);
//        map.put(FieldConstant.getInstance().INVOICE, invoince);
        map.put(FieldConstant.getInstance().REMARK, StringUtils.getNoEmptyStr(remark));
        map.put(FieldConstant.getInstance().PICS, pics);
        map.put(FieldConstant.getInstance().USER_ID, PersonConstant.getInstance().getUserId());
        map.put(FieldConstant.getInstance().COPY_USER_ID, csId);
        map.put(FieldConstant.getInstance().TIME_SPAN, timeSpan);
        map.put(FieldConstant.getInstance().APPROVE_USER_ID, approveId);
        String json = "{\n" +
                "\"model\":" + JSON.toJSONString(map) + "}";
        OkhttpUtil.getInstance().basePostCall(context, json, UrlConstant.getInstance().POST_APPLY_CHECK, new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                SuccessDBean successBean = JSON.parseObject(str, SuccessDBean.class);
                if (successBean.getD() != null && successBean.getD().getSuccess() == 1) {
                    commitCallBack.onSuccess();
                    ToastUtils.getMineToast("提交成功");
                } else {
                    commitCallBack.onFail();
                    ToastUtils.getMineToast("提交失败");
                }
            }

            @Override
            public void onFail() {

            }
        });

//        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message),
//                UrlConstant.getInstance().URL + PostConstant.getInstance().ADD_APPLY
//                        + FieldConstant.getInstance().APPROVE_TYPE_ID + "=" + EncodeUtils.getInstance().getEncodeString(approveTypeId) + "&"
//                        + FieldConstant.getInstance().TYPE_NAME + "=" + EncodeUtils.getInstance().getEncodeString(typeName) + "&"
//                        + FieldConstant.getInstance().TYPE_PRICE + "=" + EncodeUtils.getInstance().getEncodeString(typePrice) + "&"
//                        + FieldConstant.getInstance().START_TIME + "=" + startTime + "&"
//                        + FieldConstant.getInstance().END_TIME + "=" + endTime + "&"
//                        + FieldConstant.getInstance().START_TIMES + "=" + EncodeUtils.getInstance().getEncodeString(startTimes) + "&"
//                        + FieldConstant.getInstance().END_TIMES + "=" + EncodeUtils.getInstance().getEncodeString(endTimes) + "&"
//                        + FieldConstant.getInstance().APPROVE_ADDRESS + "=" + EncodeUtils.getInstance().getEncodeString(address) + "&"
//                        + FieldConstant.getInstance().INVOICE + "=" + invoince + "&"
//                        + FieldConstant.getInstance().REMARK + "=" + EncodeUtils.getInstance().getEncodeString(remark) + "&"
//                        + FieldConstant.getInstance().PICS + "=" + EncodeUtils.getInstance().getEncodeString(pics) + "&"
//                        + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
//                        + FieldConstant.getInstance().COPY_USER_ID + "=" + csId + "&"
//                        + FieldConstant.getInstance().TIME_SPAN + "=" + timeSpan + "&"
//                        + FieldConstant.getInstance().APPROVE_USER_ID + "=" + approveId, new OkhttpCallBack() {
//                    @Override
//                    public void onSuccess(String json) {
//                        SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
//                        if (successBean.getSuccess() == 1) {
//                            commitCallBack.onSuccess();
//                        } else {
//                            commitCallBack.onFail();
//                            ToastUtils.getMineToast(successBean.getErrMsg());
//                        }
//                    }
//
//                    @Override
//                    public void onFail() {
//                        ToastUtils.getMineToast("提交失败");
//                        commitCallBack.onFail();
//                    }
//                });
    }


}
