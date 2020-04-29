package com.example.huoshangkou.jubowan.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.bean.SuccessDBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.SharedValueConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：SendCodeUtils
 * 描述：发送验证码工具类
 * 创建时间：2017-02-23  10:48
 */

public class SendCodeUtils {

    private static class SendCodeHelper {
        private static SendCodeUtils INSTANCE = new SendCodeUtils();
    }

    public static SendCodeUtils getInstance() {
        return SendCodeHelper.INSTANCE;
    }

    private TextView tvSend;
    private Context mContext;

    private int i = 0;

    private Thread thread;

    private final int SEND_SUCCESS = 2;
    private final int SEND_FAIL = 3;

    private int mLastTime;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    tvSend.setText(i + "秒后重发");
                    tvSend.setBackground(mContext.getResources().getDrawable(R.drawable.code_gray_bg));
                    tvSend.setClickable(false);
                    SharedPreferencesUtils.getInstance().put(mContext, SharedKeyConstant.getInstance().CODE_TIME, i);
                    if (i == 1) {
                        tvSend.setText("重新获取验证码");
                        tvSend.setBackground(mContext.getResources().getDrawable(R.drawable.blue_corner_btn));
                        tvSend.setClickable(true);
                    }
                    break;
                case SEND_SUCCESS:
                    setDownTime();
                    break;
                case SEND_FAIL:
                    ToastUtils.getMineToast("发送失败");
                    break;
            }
        }
    };


    public void sendCode(final String phone, TextView tvSendCode, final String areaCode, final Context context, int lastTime, boolean isSendCode, String type, final StringCallBack stringCallBack) {
        tvSend = tvSendCode;
        mContext = context;
        mLastTime = lastTime;

        if (type.equals(SharedValueConstant.getInstance().REGISTER)) {
            SharedPreferencesUtils.getInstance().put(mContext, SharedKeyConstant.getInstance().CODE_TYPE, SharedValueConstant.getInstance().REGISTER);
            //找回密码
        } else if (type.equals(SharedValueConstant.getInstance().FIND_PSW)) {
            SharedPreferencesUtils.getInstance().put(mContext, SharedKeyConstant.getInstance().CODE_TYPE, SharedValueConstant.getInstance().FIND_PSW);
            //绑定手机号
        } else if (type.equals(SharedValueConstant.getInstance().BIND_PHONE)) {
            SharedPreferencesUtils.getInstance().put(mContext, SharedKeyConstant.getInstance().CODE_TYPE, SharedValueConstant.getInstance().BIND_PHONE);
        }

        //发送验证码
        if (isSendCode) {
            MD5Utils.getInstance().getTwoCode(context, phone, areaCode, new StringCallBack() {
                @Override
                public void onSuccess(final String str) {
                    stringCallBack.onSuccess(str);
                    sendCodeWeb(phone, MD5Utils.getInstance().getLageKey(MD5Utils.getInstance().md5(str, MD5Utils.KEY)), areaCode);
                }

                @Override
                public void onFail() {

                }
            });
        }
    }


    public void setYiQianCode(Context context, TextView tvSendCode, String id) {
        tvSend = tvSendCode;
        mContext = context;
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(mContext, UrlConstant.getInstance().LOCAL_URL
                + PostConstant.getInstance().SEND_YQ_CODE
                + FieldConstant.getInstance().USER_ID + "=" + id, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                LogUtils.i(json);
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    ToastUtils.getMineToast("发送成功,请注意查收");
                    handler.sendEmptyMessage(SEND_SUCCESS);
                } else {
                    handler.sendEmptyMessage(SEND_FAIL);
                }
            }

            @Override
            public void onFail() {
                handler.sendEmptyMessage(SEND_FAIL);
            }
        });
    }

    //垫付款验证码
    public void dianFuCode(Context context, TextView tvSendCode) {
        tvSend = tvSendCode;
        mContext = context;
        Map<String, Object> map = new HashMap<>();
        map.put("userId", PersonConstant.getInstance().getUserId());
        String json = JSON.toJSONString(map);
        OkhttpUtil.getInstance().basePostCall(mContext, json, UrlConstant.getInstance().PADPAY_MANAGE + "AdvancePaymentPaySignleMobileCode", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                SuccessDBean dBean = JSON.parseObject(str, SuccessDBean.class);
                if (dBean.getD().getState() == 1) {
                    ToastUtils.getMineToast("发送成功,请注意查收");
                    handler.sendEmptyMessage(SEND_SUCCESS);
                } else {
                    handler.sendEmptyMessage(SEND_FAIL);
                }
            }

            @Override
            public void onFail() {
                handler.sendEmptyMessage(SEND_FAIL);
            }
        });
    }


    //开启倒计时
    private void setDownTime() {
        tvSend.setBackground(mContext.getResources().getDrawable(R.drawable.code_gray_bg));
        //返回60秒时间
        if (mLastTime > 1) {
            i = mLastTime;
        } else {
            i = 60;
        }
        thread = new Thread() {
            @Override
            public void run() {
                super.run();
                setLowTime();
            }
        };
        thread.start();
    }


    //倒计时方法  线程锁 进行同步
    private synchronized void setLowTime() {
        while (i > 1) {
            Message message = handler.obtainMessage();
            message.obj = i;
            message.what = 1;
            handler.sendMessage(message);
            i--;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //获取验证码
    private String getCode() {
        String code = new Random().nextInt(10) + "" +
                new Random().nextInt(10) + "" +
                new Random().nextInt(10) + "" +
                new Random().nextInt(10) + "" +
                new Random().nextInt(10) + "" +
                new Random().nextInt(10);
        return code;
    }


    /**
     * 发送验证码  http://api3.atjubo.com/atapiwuliu/SendCode/?tel=15172486580&code=681143D539CF17210EF0433C20
     */
    private void sendCodeWeb(final String phone, final String code, final String areaCode) {

        String url = UrlConstant.getInstance().LOCAL_URL
                + PostConstant.getInstance().SEND_CODE
                + FieldConstant.getInstance().TEL + "=" + phone + "&"
                + FieldConstant.getInstance().AREA_COED + "=" + areaCode + "&"
                + FieldConstant.getInstance().CODE + "=" + code;

        x.http().get(new RequestParams(url), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.i(result);
                SuccessBean successBean = JSON.parseObject(result, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    ToastUtils.getMineToast("发送成功,请注意查收");
                    handler.sendEmptyMessage(SEND_SUCCESS);
                    //验证码本地保存
                    SharedPreferencesUtils.getInstance().put(mContext, SharedKeyConstant.getInstance().SAVE_CODE, code);
                } else {
                    handler.sendEmptyMessage(SEND_FAIL);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    /**
     * get方式得到数据
     *
     * @return
     */
//    public void sendCodeWeb(String phone, final String code) {
//
//        String path = UrlConstant.getInstance().URL
//                + PostConstant.getInstance().SEND_CODE
//                + FieldConstant.getInstance().TEL + "=" + phone + "&"
//                + FieldConstant.getInstance().CODE + "=" + code;
//
//        URL url = null;
//        HttpURLConnection conn = null;
//        StringBuffer sb = new StringBuffer();
//        BufferedReader br = null;
//        try {
//            url = new URL(path);
//            conn = (HttpURLConnection) url.openConnection();
//            int status = conn.getResponseCode();
//            LogUtils.i(status + "  aa");
//            if (status == 200) {
//                br = new BufferedReader(new InputStreamReader(
//                        conn.getInputStream()));
//                String temp = "";
//                while ((temp = br.readLine()) != null) {
//                    sb.append(temp);
//                }
//            } else {
//                throw new Exception("网络异常");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (conn != null) {
//                    conn.disconnect();
//                }
//                if (br != null) {
//                    br.close();
//                }
//            } catch (Exception e2) {
//                e2.printStackTrace();
//            }
//        }
//
//        LogUtils.i(sb.toString() + "  hhaha ");
//        LogUtils.i(path);
////        SuccessBean successBean = JSON.parseObject(sb.toString(), SuccessBean.class);
////        if (successBean.getSuccess() == 1) {
////            ToastUtils.getMineToast("发送成功,请注意查收");
////            handler.sendEmptyMessage(SEND_SUCCESS);
////
////            //验证码本地保存
////            SharedPreferencesUtils.getInstance().put(mContext, SharedKeyConstant.getInstance().SAVE_CODE, code);
////
////        } else {
////            handler.sendEmptyMessage(SEND_FAIL);
////        }
//
////        return sb.toString();
//    }


    //获取验证码
    public String getSendCode(Context context) {
        String code = (String) SharedPreferencesUtils.getInstance().get(context, SharedKeyConstant.getInstance().SAVE_CODE, "no_code");
        return code;
    }


}
