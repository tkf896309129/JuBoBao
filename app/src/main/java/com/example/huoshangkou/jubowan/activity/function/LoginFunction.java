package com.example.huoshangkou.jubowan.activity.function;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseApp;
import com.example.huoshangkou.jubowan.bean.LoginMessageBean;
import com.example.huoshangkou.jubowan.bean.LoginMessageObjBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnCommonSuccessCallBack;
import com.example.huoshangkou.jubowan.inter.OnLoginSuccessBack;
import com.example.huoshangkou.jubowan.inter.OnThreeLoginBack;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.MineLoadingDialogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;


/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity.function
 * 类名：LoginFunction
 * 描述：登录功能工具类
 * 创建时间：2017-02-24  08:57
 */

public class LoginFunction {

    private static class LoginHelper {
        private static LoginFunction INSTANCE = new LoginFunction();
    }

    public static LoginFunction getInstance() {
        return LoginHelper.INSTANCE;
    }

    //QQ
    public final int QQ = 0;
    //微信
    public final int WE_CHAT = 1;
    //微博
    public final int WEI_BO = 2;

    /**
     * @param phone
     * @param psw
     * @param loginSuccessBack
     */
    //登录功能
    public void setLogin(final Context context, String phone, String psw, final OnLoginSuccessBack loginSuccessBack) {
        OkhttpUtil.getInstance().setUnCacheData(context,"登录中...", UrlConstant.getInstance().URL + PostConstant.getInstance().LOGIN
                + FieldConstant.getInstance().TEL + "=" + phone + "&"
                + FieldConstant.getInstance().PSW + "=" + EncodeUtils.getInstance().getEncodeString(psw) + "&"
                + FieldConstant.getInstance().MARK + "=mark"  + "&"
                + FieldConstant.getInstance().UMENG_ID + "=android", new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                LogUtils.e(json);
                LoginMessageBean loginMessageObjBean = JSON.parseObject(json, LoginMessageBean.class);
                if (loginMessageObjBean.getSuccess() == 1) {
                    LoginFunction.getInstance().saveLoginMessage(loginMessageObjBean.getReObj(), BaseApp.getInstance());
                    LoginMessageObjBean reObj = loginMessageObjBean.getReObj();
                    loginSuccessBack.onLoginSuccess(reObj.getID()+"",reObj.getRealName(),reObj.getHeadPic());
                } else {
                    Toast.makeText(context,loginMessageObjBean.getErrMsg(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFail() {
            }
        });
    }

    public void threeLogin(Context context, int loginType, String openId, String phone, String headPic, String nickName, final OnThreeLoginBack threeLoginBack) {
        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().URL + PostConstant.getInstance().THREE_LOGIN
                + FieldConstant.getInstance().LOGIN_TYPE + "=" + loginType + "&"
                + FieldConstant.getInstance().OPEN_ID + "=" + openId + "&"
                + FieldConstant.getInstance().TEL + "=" + phone + "&"
                + FieldConstant.getInstance().HEAD_PIC + "=" + headPic + "&"
                + FieldConstant.getInstance().NICK_NAME + "=" + EncodeUtils.getInstance().getEncodeString(nickName) + "&"
                + FieldConstant.getInstance().UMENG_ID + "=android", new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                LogUtils.e(json);
                LoginMessageBean loginMessageBean = JSON.parseObject(json, LoginMessageBean.class);
                if (loginMessageBean.getSuccess() == -1) {
                    threeLoginBack.threeLoginFail(loginMessageBean.getErrMsg());
                } else if (loginMessageBean.getSuccess() == 1) {
                    threeLoginBack.threeLoginSuccess(loginMessageBean.getReObj());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    /**
     * 保存登录信息
     * "UserType":用户类型，0=普通用户，1=建筑商，2=加工厂，3=原片厂商销售员,4=加工厂业务合伙人,5=辅料业务合伙人,6=建筑商业务合伙人,7=维修业务合伙人,8.加工机械配件厂
     */
    public void saveLoginMessage(LoginMessageObjBean loginMessageObjBean, Context context) {
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().ADDRESS, loginMessageObjBean.getAddress());
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().COMPANY_NAME, loginMessageObjBean.getCompanyName());
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().HEAD_PIC, loginMessageObjBean.getHeadPic());
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().ID, loginMessageObjBean.getID() + "");
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().JUBO_LEVEL, loginMessageObjBean.getJuboLevel() + "");
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().JUBO_BI, loginMessageObjBean.getJubobi() + "");
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().LINK_MAN_CARD_PIC, loginMessageObjBean.getLinkManCardPic());
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().LOGIN_TYPE, loginMessageObjBean.getLoginType());
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().NICK_NAME, loginMessageObjBean.getNickname());
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().PARENT_ID, loginMessageObjBean.getParentID() + "");
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().PIC_YYZZ, loginMessageObjBean.getPicYyzz());
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().QQ_OPEN_ID, loginMessageObjBean.getQQOpenID());
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().REAL_NAME, loginMessageObjBean.getRealName());
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().REMARK, loginMessageObjBean.getRemark());
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().TEL, loginMessageObjBean.getTel());
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().TWO_PARENT_ID, loginMessageObjBean.getTwoParentID());
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().USER_CARD_NO, loginMessageObjBean.getUserCardNo());
        //审核状态
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().USER_STATE, loginMessageObjBean.getUserState() + "");
        //用户身份类型
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().USER_TYPE, loginMessageObjBean.getUserType() + "");
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().WB_OPEN_ID, loginMessageObjBean.getWbOpenID());
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().WX_OPEN_ID, loginMessageObjBean.getWxOpenID());
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().WX_JIQI_NAME, loginMessageObjBean.getWxjiqiName());
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().ZZ_JG_NO, loginMessageObjBean.getZzjgNo());
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().REOLE_ID, loginMessageObjBean.getRoleID()+"");

        //是否可以垫付款 0不可以 1可以
        if (loginMessageObjBean.isTrade()) {
            SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().IS_TRADE, "1");
        } else {
            SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().IS_TRADE, "0");
        }
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().ON_CODE, loginMessageObjBean.getOnCode());

    }


    public void unSaveLoginMessage(Context context) {
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().ADDRESS, "");
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().COMPANY_NAME, "");
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().HEAD_PIC, "");
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().ID, "");
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().JUBO_LEVEL, "");
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().JUBO_BI, "");
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().LINK_MAN_CARD_PIC, "");
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().LOGIN_TYPE, "");
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().NICK_NAME, "");
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().PARENT_ID, "");
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().PIC_YYZZ, "");
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().QQ_OPEN_ID, "");
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().REAL_NAME, "");
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().REMARK, "");
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().TEL, "");
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().TWO_PARENT_ID, "");
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().USER_CARD_NO, "");
        //审核状态
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().USER_STATE, "");
        //用户身份类型
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().USER_TYPE, "");
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().WB_OPEN_ID, "");
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().WX_OPEN_ID, "");
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().WX_JIQI_NAME, "");
        SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().ZZ_JG_NO, "");
    }

    //保存审核身份信息
    public void saveApproveInfo(String approveState, String approveType) {
        //审核状态
        SharedPreferencesUtils.getInstance().put(BaseApp.getInstance(), SharedKeyConstant.getInstance().USER_STATE, approveState);
        //用户身份类型
        SharedPreferencesUtils.getInstance().put(BaseApp.getInstance(), SharedKeyConstant.getInstance().USER_TYPE, approveType);
    }




    //登录环信服务器
    public void loginChat(Context context, String account, final OnCommonSuccessCallBack successCallBack) {
        final AlertDialog alertDialog = MineLoadingDialogUtils.updateDialog(context, context.getString(R.string.loading_message));

        EMClient.getInstance().login(account, "123456", new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                successCallBack.onSuccess();
                alertDialog.dismiss();
                LogUtils.i("环信登录成功");
            }

            @Override
            public void onProgress(int progress, String status) {
                alertDialog.dismiss();
            }

            @Override
            public void onError(int code, String message) {
                successCallBack.onFail();
                LogUtils.i("环信登录失败" + message);
            }
        });
    }
}
