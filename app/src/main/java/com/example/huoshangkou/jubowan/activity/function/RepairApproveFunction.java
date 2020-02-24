package com.example.huoshangkou.jubowan.activity.function;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.RepairApproveBean;
import com.example.huoshangkou.jubowan.bean.RepairBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.ApproveCallBack;
import com.example.huoshangkou.jubowan.inter.OnRepairApproveCallBack;
import com.example.huoshangkou.jubowan.inter.OnRepairApproveDataCallBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity.function
 * 类名：RepairApproveFunction
 * 描述：
 * 创建时间：2017-03-02  13:56
 */

public class RepairApproveFunction {

    private static class RepairApproveHelper {
        private static RepairApproveFunction INSTANCE = new RepairApproveFunction();
    }

    public static RepairApproveFunction getInstance() {
        return RepairApproveHelper.INSTANCE;
    }

    /**
     * 获取维修机械模块
     */
    public void getRepairMode(Context context, final OnRepairApproveCallBack approveCallBack) {
        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().URL + PostConstant.getInstance().GET_WX_TYPE, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                LogUtils.i(json);
                RepairBean repairBean = JSON.parseObject(json, RepairBean.class);
                approveCallBack.onSuccess(repairBean);
            }

            @Override
            public void onFail() {

            }
        });
    }

    public void repairApprove(final Context context, String linkMan, String cardNo, String workPic, String repairId,
                              String addressId, String resume, String descript, final ApproveCallBack callBack) {

        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().URL + PostConstant.getInstance().REPAIR_APPROVE
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().LINK_MAN + "=" + EncodeUtils.getInstance().getEncodeString(linkMan) + "&"
                + FieldConstant.getInstance().LINK_CARD_NO + "=" + EncodeUtils.getInstance().getEncodeString(cardNo) + "&"
                + FieldConstant.getInstance().WORK_PIC + "=" + workPic + "&"
                + FieldConstant.getInstance().ROLE_ID + "=7" + "&"
                + FieldConstant.getInstance().REPIAR_TYPE + "=" + repairId + "&"
                + FieldConstant.getInstance().REPAIR_ADDRESS_ID + "=" + addressId + "&"
                + FieldConstant.getInstance().RESUME + "=" + EncodeUtils.getInstance().getEncodeString(resume) + "&"
                + FieldConstant.getInstance().WORK_EXP + "=" + EncodeUtils.getInstance().getEncodeString(descript), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    callBack.onApproveSuccess();
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
                callBack.onApproveFail();
            }
        });
    }


    //获取维修机械数据
    public void getRepairData(Context context, final OnRepairApproveDataCallBack callBack) {

        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message),
                UrlConstant.getInstance().URL + PostConstant.getInstance().GET_REPAIR_INFO + FieldConstant.getInstance().USER_ID + "="
                        + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
                    @Override
                    public void onSuccess(String json) {
                        RepairApproveBean approveBean = JSON.parseObject(json, RepairApproveBean.class);
                        callBack.onApproveSuccess(approveBean.getReObj());
                    }

                    @Override
                    public void onFail() {

                    }
                });
    }
}
