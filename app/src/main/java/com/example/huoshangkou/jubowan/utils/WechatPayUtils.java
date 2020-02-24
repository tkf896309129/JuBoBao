package com.example.huoshangkou.jubowan.utils;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.WxPayBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：WechatPayUtils
 * 描述：微信支付工具类 
 * 创建时间：2017-07-17  13:19
 */

public class WechatPayUtils {

    private static class WechatHelper {
        private static WechatPayUtils INSTANCE = new WechatPayUtils();
    }

    public static WechatPayUtils getInstance() {
        return WechatHelper.INSTANCE;
    }

    //支付类型
    public String PAY_WX_TYPE = "维修维保";

    //微信支付
    public void wechatPay(final Context context, String orderId, String payPrice) {
        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message),
                UrlConstant.getInstance().URL
                        + PostConstant.getInstance().RETURN_APP_WXPAY
                        + FieldConstant.getInstance().PAY_TYPE + "=" + EncodeUtils.getInstance().getEncodeString(PAY_WX_TYPE) + "&"
                        + FieldConstant.getInstance().WX_PAY_ID + "=" + orderId + "&"
                        + FieldConstant.getInstance().WX_TOTAL_FEE + "=" + payPrice, new OkhttpCallBack() {
                    @Override
                    public void onSuccess(String json) {
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            String reobj = jsonObject.getString("ReObj");
                            LogUtils.e(reobj);
                            WxPayBean wxPayBean = JSON.parseObject(reobj, WxPayBean.class);
                            testWxPay(context, wxPayBean);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail() {

                    }
                });
    }

    private IWXAPI api;
    private PayReq req;

    public void testWxPay(Context context, final WxPayBean wxPayBean) {

        api = WXAPIFactory.createWXAPI(context, wxPayBean.getAppid());
        req = new PayReq();
        //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
        req.appId = wxPayBean.getAppid();
        req.partnerId = wxPayBean.getPartnerid();
        req.prepayId = wxPayBean.getPrepayid();
        req.nonceStr = wxPayBean.getNoncestr();
        req.timeStamp = wxPayBean.getTimestamp();
        req.packageValue = "Sign=WXPay";
        req.sign = wxPayBean.getSign();
        req.extData = "app data"; // optional
//                            ToastUtil.shortToastInBackgroundThread(getActivity(), "正常调起支付");


        // 传递的额外信息,字符串信息,自定义格式
//         req.extData = type +"#" + out_trade_no + "#" +total_fee;
        // 微信支付结果界面对调起支付Activity的处理
//         APPCache.payActivity.put("调起支付的Activity",(调起支付的Activity)context);
        api.registerApp(wxPayBean.getAppid());
        toPay();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String url = "http://wxpay.weixin.qq.com/pub_v2/app/app_pay.php?plat=android";
////                ToastUtil.shortToastInBackgroundThread(getActivity(), "获取订单中...");
//                try {
//                    byte[] buf = Util.httpGet(url);
//                    if (buf != null && buf.length > 0) {
//                        String content = new String(buf);
//                        Log.e("get server pay params:", content);
//                        JSONObject json = new JSONObject(content);
//                        if (null != json && !json.has("retcode")) {
//                            req = new PayReq();
//                            //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
//                            req.appId = json.getString(wxPayBean.getAppid());
//                            req.partnerId = json.getString(wxPayBean.getPartnerid());
//                            req.prepayId = json.getString(wxPayBean.getPrepayid());
//                            req.nonceStr = json.getString(wxPayBean.getApnoncestrpid());
//                            req.timeStamp = json.getString(wxPayBean.getTimestamp());
//                            req.packageValue = json.getString("Sign=WXPay");
//                            req.sign = json.getString(wxPayBean.getSign());
//                            req.extData = "app data"; // optional
////                            ToastUtil.shortToastInBackgroundThread(getActivity(), "正常调起支付");
//                            toPay();
//                        } else {
//                            Log.d("PAY_GET", "返回错误" + json.getString("retmsg"));
////                            ToastUtil.shortToastInBackgroundThread(getActivity(), "返回错误" + json.getString("retmsg"));
//                        }
//                    } else {
//                        Log.d("PAY_GET", "服务器请求错误");
////                        ToastUtil.shortToastInBackgroundThread(getActivity(), "服务器请求错误");
//                    }
//                } catch (Exception e) {
//                    Log.e("PAY_GET", "异常：" + e.getMessage());
////                    ToastUtil.shortToastInBackgroundThread(getActivity(), "异常：" + e.getMessage());
//                }
//            }
//        }).start();
    }

    private void toPay() {
        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        api.sendReq(req);
    }

}
