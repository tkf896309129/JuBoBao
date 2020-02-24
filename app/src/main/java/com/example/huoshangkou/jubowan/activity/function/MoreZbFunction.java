package com.example.huoshangkou.jubowan.activity.function;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.ZbDetailActivity;
import com.example.huoshangkou.jubowan.bean.ZbBean;
import com.example.huoshangkou.jubowan.bean.ZbMessageBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnZbBeanCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity.function
 * 类名：MoreZbFunction
 * 描述：
 * 创建时间：2017-04-17  13:27
 */

public class MoreZbFunction {

    private static class MoreZbHelper {
        private static MoreZbFunction INSTANCE = new MoreZbFunction();
    }

    public static MoreZbFunction getInstance() {
        return MoreZbHelper.INSTANCE;
    }


    //获取招标列表
    public void getZbList(Context context, int pageSize, final OnZbBeanCallBack callBack) {
        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_ZB_LIST
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
//                + FieldConstant.getInstance().TO_STATE + "=" + toState + "&"
                + FieldConstant.getInstance().PAGE_SIZE + "=" + pageSize, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                ZbBean zbBean = JSON.parseObject(json, ZbBean.class);
                callBack.onSuccess(zbBean);
            }

            @Override
            public void onFail() {
                callBack.onFail();
            }
        });
    }


    public void getZbList(Context context, String toState, int pageSize, final OnZbBeanCallBack callBack) {
        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_ZB_LIST
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().TO_STATE + "=" + toState + "&"
                + FieldConstant.getInstance().PAGE_SIZE + "=" + pageSize, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                ZbBean zbBean = JSON.parseObject(json, ZbBean.class);
                callBack.onSuccess(zbBean);
            }

            @Override
            public void onFail() {
                callBack.onFail();
            }
        });
    }


    //搜索项目列表
    public void searchZbList(Context context, String keyWord, String projectType, String small, String large, int pageSize, final OnZbBeanCallBack callBack) {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(context, UrlConstant.getInstance().URL
                + PostConstant.getInstance().SEARCH_ZB_LIST + FieldConstant.getInstance().KEY_WORD + "=" + EncodeUtils.getInstance().getEncodeString(keyWord) + "&"
                + FieldConstant.getInstance().PROJECT_TYPE + "=" + EncodeUtils.getInstance().getEncodeString(projectType) + "&"
                + FieldConstant.getInstance().SMALL + "=" + EncodeUtils.getInstance().getEncodeString(small) + "&"
                + FieldConstant.getInstance().LARGE + "=" + EncodeUtils.getInstance().getEncodeString(large) + "&"
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().PAGE_SIZE + "=" + pageSize, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                ZbBean zbBean = JSON.parseObject(json, ZbBean.class);
                callBack.onSuccess(zbBean);
            }

            @Override
            public void onFail() {

            }
        });
    }


    //获取招标详情
    public void getZbDetail(Context context, String requestId, final StringCallBack stringCallBack) {
        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().URL
                        + PostConstant.getInstance().GET_ZB_DETAIL
                        +FieldConstant.getInstance().USER_ID+"="+PersonConstant.getInstance().getUserId()+"&"
                        + FieldConstant.getInstance().REQUEST_ID + "=" + requestId, new OkhttpCallBack() {
                    @Override
                    public void onSuccess(String json) {
                        stringCallBack.onSuccess(json);
                    }

                    @Override
                    public void onFail() {
                        stringCallBack.onFail();
                    }
                }
        );
    }

    //跳转到招标详情
    public void toZbDetail(final Context context, String id) {
        MoreZbFunction.getInstance().getZbDetail(context, id, new StringCallBack() {
            @Override
            public void onSuccess(String str) {
//                ZbMessageBean zbMessageBean = JSON.parseObject(str, ZbMessageBean.class);
//                if (zbMessageBean.getReList().get(0) != null) {
//                    IntentUtils.getInstance().toActivity(context, ZbDetailActivity.class, zbMessageBean.getReList().get(0));
//                } else {
//                    ToastUtils.getMineToast("获取信息失败");
//                }
            }

            @Override
            public void onFail() {

            }
        });
    }

}
