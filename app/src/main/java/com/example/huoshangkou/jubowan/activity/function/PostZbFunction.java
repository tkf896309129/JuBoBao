package com.example.huoshangkou.jubowan.activity.function;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.GYBean;
import com.example.huoshangkou.jubowan.bean.GonYiBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.bean.ZbBrandBean;
import com.example.huoshangkou.jubowan.bean.ZbLowBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnCommonSuccessCallBack;
import com.example.huoshangkou.jubowan.inter.OnGYCallBack;
import com.example.huoshangkou.jubowan.inter.OnGonYiCallBack;
import com.example.huoshangkou.jubowan.inter.OnZbBrandCallBack;
import com.example.huoshangkou.jubowan.inter.OnZbLowCallBack;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity.function
 * 类名：PostZbFunction
 * 描述：
 * 创建时间：2017-04-12  09:09
 */

public class PostZbFunction {

    private static class PostZbHelper {
        public static PostZbFunction INSTANCE = new PostZbFunction();
    }

    public static PostZbFunction getInstance() {
        return PostZbHelper.INSTANCE;
    }


    /**
     * 墙体选择
     */
    public String qiang[] = {"山东白麻", "爵士白", "金碧辉煌", "天山红", "贝金沙", "巴兰珠", "芝麻灰", "卡拉麦里金", "承德绿", "黄金钻B", "黄锈石", "黄金钻",
            "黄金麻", "皇室咖", "黑金花", "深啡网", "山东鲁灰", "吓红", "漳浦锈", "世纪冰花", "燕山红", "天山红", "帝皇金", "古典金麻", "大白花", "白玉兰",
            "宝金石", "贵妃白麻", "黑金花", "黑金沙", "黄金钻A", "维纳斯白麻", "白玫瑰", "白洞石", "白城堡", "紫金灰麻", "漳浦锈", "英国棕", "美国白麻"};


    /**
     * 对应图片
     */
    public int[] qiangImg = {R.mipmap.sdbm, R.mipmap.jue_shi_bai, R.mipmap.jbhh, R.mipmap.tian_shan_hong, R.mipmap.bjs, R.mipmap.ba_lan_zhu, R.mipmap.zmh, R.mipmap.klmlj
            , R.mipmap.cdl, R.mipmap.haung_jin_zuanb, R.mipmap.haung_you_shi, R.mipmap.huang_jin_zuan, R.mipmap.huang_jin_ma, R.mipmap.huang_shi_ka, R.mipmap.black_jin_hua, R.mipmap.shen_ka_wang, R.mipmap.shang_dong_hui,
            R.mipmap.xia_hong, R.mipmap.zhang_pu_xiu, R.mipmap.shi_ji_icehua, R.mipmap.yan_shan_hong, R.mipmap.tian_shan_hong, R.mipmap.di_huang_jin, R.mipmap.old_jin_ma, R.mipmap.da_bai_hua,
            R.mipmap.bai_yu_lan, R.mipmap.bao_jin_shi, R.mipmap.guife_bai_ma, R.mipmap.black_jin_hua, R.mipmap.black_jin_sha, R.mipmap.golad_a, R.mipmap.vns_ba_ma,
            R.mipmap.bai_mei_gui, R.mipmap.bai_dong_shi, R.mipmap.bai_cheng_bao, R.mipmap.zi_jing_huima, R.mipmap.zhang_pu_xiu, R.mipmap.ying_guo_zong, R.mipmap.ameracia_ba_ma};

    //获取品牌
    public void getBrandData(Context context, final OnZbBrandCallBack brandCallBack) {
        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_BRAND_LIST, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                ZbBrandBean zbBrandBean = JSON.parseObject(json, ZbBrandBean.class);
                brandCallBack.onSuccess(zbBrandBean);

            }

            @Override
            public void onFail() {

            }
        });
    }

    //获取加工工艺
    public void getGonYiData(Context context, final OnGonYiCallBack gonYiCallBack) {
        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_GON_YI, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                GonYiBean yiBean = JSON.parseObject(json, GonYiBean.class);
                gonYiCallBack.onSuccess(yiBean);

            }

            @Override
            public void onFail() {
                gonYiCallBack.onFail();
            }
        });
    }


    //获取工艺  0为原片工艺，2为辅料工艺
    public void getGYiData(Context context, String type, String fatherId, final OnGYCallBack callBack) {
        LogUtils.i(UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_GY + FieldConstant.getInstance().TYPE + "=" + type + "&"
                + FieldConstant.getInstance().FATHER_ID + "=" + fatherId);
        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_GY + FieldConstant.getInstance().TYPE + "=" + type + "&"
                + FieldConstant.getInstance().FATHER_ID + "=" + fatherId, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                GYBean gyBean = JSON.parseObject(json, GYBean.class);
                callBack.onSuccess(gyBean);

            }

            @Override
            public void onFail() {
                callBack.onFail();
            }
        });
    }

    //获取膜系
    public void getLowData(Context context, String brandId, final OnZbLowCallBack callBack) {
        LogUtils.i(UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_LOW_DATA + FieldConstant.getInstance().BRAND_ID + "=" + brandId);
        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_LOW_DATA + FieldConstant.getInstance().BRAND_ID + "=" + brandId, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                ZbLowBean lowBean = JSON.parseObject(json, ZbLowBean.class);
                callBack.onZbLowCallBack(lowBean);
            }

            @Override
            public void onFail() {
                callBack.onFail();
            }
        });
    }

    //在线选型提交
    public void putOnLineZb(Context context, String proName, String proType, String minArea, String maxArea, String linkMan, String linkTel,
                            String needDay, String proAddress,
                            String idNeedAnLi, String isNeedYp, String ypAddress, String brands, String lowName,
                            String lowId, String ypAndFl, String gonYis, String pics, final OnCommonSuccessCallBack commonSuccessCallBack) {
        LogUtils.i(UrlConstant.getInstance().URL
                + PostConstant.getInstance().PUT_ZB_ONLINE
                + FieldConstant.getInstance().PROJECT_NAME + "=" + EncodeUtils.getInstance().getEncodeString(proName) + "&"
                + FieldConstant.getInstance().PROJECT_TYPE + "=" + EncodeUtils.getInstance().getEncodeString(proType) + "&"
                + FieldConstant.getInstance().SMALL_AREA + "=" + EncodeUtils.getInstance().getEncodeString(minArea) + "&"
                + FieldConstant.getInstance().MAX_AREA + "=" + EncodeUtils.getInstance().getEncodeString(maxArea) + "&"
                + FieldConstant.getInstance().LINK_MAN + "=" + EncodeUtils.getInstance().getEncodeString(linkMan) + "&"
                + FieldConstant.getInstance().LINK_TEL + "=" + EncodeUtils.getInstance().getEncodeString(linkTel) + "&"
                + FieldConstant.getInstance().NEED_DAYS + "=" + EncodeUtils.getInstance().getEncodeString(needDay) + "&"
                + FieldConstant.getInstance().PROJECT_ADDRESS + "=" + EncodeUtils.getInstance().getEncodeString(proAddress) + "&"
                + FieldConstant.getInstance().IS_NEED_ANLI + "=" + EncodeUtils.getInstance().getEncodeString(idNeedAnLi) + "&"
                + FieldConstant.getInstance().IS_NEED_YP + "=" + EncodeUtils.getInstance().getEncodeString(isNeedYp) + "&"
                + FieldConstant.getInstance().YP_ADDRESS + "=" + EncodeUtils.getInstance().getEncodeString(ypAddress) + "&"
                + FieldConstant.getInstance().BRANDS + "=" + EncodeUtils.getInstance().getEncodeString(brands) + "&"
                + FieldConstant.getInstance().LOW_NAME + "=" + EncodeUtils.getInstance().getEncodeString(lowName) + "&"
                + FieldConstant.getInstance().LOW_ID + "=" + EncodeUtils.getInstance().getEncodeString(lowId) + "&"
                + FieldConstant.getInstance().YP_AND_FL + "=" + EncodeUtils.getInstance().getEncodeString(ypAndFl) + "&"
                + FieldConstant.getInstance().GONYIS + "=" + EncodeUtils.getInstance().getEncodeString(gonYis) + "&"
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId());

        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().PUT_ZB_ONLINE
                + FieldConstant.getInstance().PROJECT_NAME + "=" + EncodeUtils.getInstance().getEncodeString(proName) + "&"
                + FieldConstant.getInstance().PROJECT_TYPE + "=" + EncodeUtils.getInstance().getEncodeString(proType) + "&"
                + FieldConstant.getInstance().SMALL_AREA + "=" + EncodeUtils.getInstance().getEncodeString(minArea) + "&"
                + FieldConstant.getInstance().MAX_AREA + "=" + EncodeUtils.getInstance().getEncodeString(maxArea) + "&"
                + FieldConstant.getInstance().LINK_MAN + "=" + EncodeUtils.getInstance().getEncodeString(linkMan) + "&"
                + FieldConstant.getInstance().LINK_TEL + "=" + EncodeUtils.getInstance().getEncodeString(linkTel) + "&"
                + FieldConstant.getInstance().NEED_DAYS + "=" + EncodeUtils.getInstance().getEncodeString(needDay) + "&"
                + FieldConstant.getInstance().PROJECT_ADDRESS + "=" + EncodeUtils.getInstance().getEncodeString(proAddress) + "&"
                + FieldConstant.getInstance().IS_NEED_ANLI + "=" + EncodeUtils.getInstance().getEncodeString(idNeedAnLi) + "&"
                + FieldConstant.getInstance().IS_NEED_YP + "=" + EncodeUtils.getInstance().getEncodeString(isNeedYp) + "&"
                + FieldConstant.getInstance().YP_ADDRESS + "=" + EncodeUtils.getInstance().getEncodeString(ypAddress) + "&"
                + FieldConstant.getInstance().BRANDS + "=" + EncodeUtils.getInstance().getEncodeString(brands) + "&"
                + FieldConstant.getInstance().LOW_NAME + "=" + EncodeUtils.getInstance().getEncodeString(lowName) + "&"
                + FieldConstant.getInstance().LOW_ID + "=" + EncodeUtils.getInstance().getEncodeString(lowId) + "&"
                + FieldConstant.getInstance().YP_AND_FL + "=" + EncodeUtils.getInstance().getEncodeString(ypAndFl) + "&"
                + FieldConstant.getInstance().GONYIS + "=" + EncodeUtils.getInstance().getEncodeString(gonYis) + "&"
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().IMAGS + "=" + pics, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() != -1) {
                    commonSuccessCallBack.onSuccess();
                } else {
                    commonSuccessCallBack.onFail();
                }
            }

            @Override
            public void onFail() {
                commonSuccessCallBack.onFail();
            }
        });
    }


}
