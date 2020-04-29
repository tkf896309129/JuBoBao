package com.example.huoshangkou.jubowan.activity.function;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.inter.SuccessCallBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity.function
 * 类名：OrderCommitFunction
 * 描述：
 * 创建时间：2017-05-12  09:08
 */

public class OrderCommitFunction {

    private static class OrderHelper {
        private static OrderCommitFunction INSTANCE = new OrderCommitFunction();
    }

    public static OrderCommitFunction getInstance() {
        return OrderHelper.INSTANCE;
    }

    //下单
    public void commitOrder(Context context, String ypId, String orderType, String proNum, String isPtWl, String addressId, String ywyPhone, final StringCallBack successCallBack) {
//        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().URL
//                + PostConstant.getInstance().SUBMIT_PRO + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
//                + FieldConstant.getInstance().YP_ID + "=" + ypId + "&"
//                + FieldConstant.getInstance().FL_ID + "=" + flId + "&"
//                + FieldConstant.getInstance().YL_ID + "=" + ylId + "&"
//                + FieldConstant.getInstance().BUY_COUNT + "=" + proNum + "&"
//                + FieldConstant.getInstance().IS_PT_WL + "=" + isPtWl + "&"
//                + FieldConstant.getInstance().ADDR_ID + "=" + addressId + "&"
//                + FieldConstant.getInstance().TO_YWY_TEL + "=" + ywyPhone, new OkhttpCallBack() {
//            @Override
//            public void onSuccess(String json) {
//                successCallBack.onSuccess(json);
//            }
//
//            @Override
//            public void onFail() {
//                successCallBack.onFail();
//            }
//        });
        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().RE_SUBMIT_PRO + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().ID + "=" + ypId + "&"
                + FieldConstant.getInstance().PRODUCT_TYPE + "=" + orderType + "&"
                + FieldConstant.getInstance().BUY_COUNT + "=" + proNum + "&"
                + FieldConstant.getInstance().IS_PT_WL + "=" + isPtWl + "&"
                + FieldConstant.getInstance().ADDR_ID + "=" + addressId + "&"
                + FieldConstant.getInstance().TO_YWY_TEL + "=" + ywyPhone, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                successCallBack.onSuccess(json);
            }

            @Override
            public void onFail() {
                successCallBack.onFail();
            }
        });
    }


    //删除订单
    public void deleteOrder(String orderId, final Context context, final SuccessCallBack successCallBack) {
        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().DELETE_ORDER + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().ORDER_ID + "=" + orderId, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    successCallBack.onSuccess();
                } else {
                    CopyIosDialogUtils.getInstance().getErrorDialog(context, successBean.getErrMsg(), new CopyIosDialogUtils.ErrDialogCallBack() {
                        @Override
                        public void confirm() {

                        }
                    });
                }
            }

            @Override
            public void onFail() {
                successCallBack.onFail();
            }
        });
    }

    //垫付款支付
    public void dianPay(String orderId, final Context context,String price, final SuccessCallBack successCallBack){
        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().MO_XI_ORDER_AD
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().TYPE_PRICES + "=" + price + "&"
                + FieldConstant.getInstance().ADVANCE_ORDER_ID + "=" + orderId, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    successCallBack.onSuccess();
                } else {
                    CopyIosDialogUtils.getInstance().getErrorDialog(context, successBean.getErrMsg(), new CopyIosDialogUtils.ErrDialogCallBack() {
                        @Override
                        public void confirm() {

                        }
                    });
                }
            }

            @Override
            public void onFail() {
                successCallBack.onFail();
            }
        });
    }

}
