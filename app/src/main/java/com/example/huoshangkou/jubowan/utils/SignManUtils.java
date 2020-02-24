package com.example.huoshangkou.jubowan.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.CheckWorkActivity;
import com.example.huoshangkou.jubowan.activity.SignDetailsActivity;
import com.example.huoshangkou.jubowan.activity.SignManActivity;
import com.example.huoshangkou.jubowan.bean.MemberBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnCommonCallBack;
import com.example.huoshangkou.jubowan.inter.OnCommonSuccessCallBack;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：SignManUtils
 * 描述：成员列表工具类
 * 创建时间：2017-04-24  15:04
 */

public class SignManUtils {

    private static class SignManHelper {
        private static SignManUtils INSTANCE = new SignManUtils();
    }

    public static SignManUtils getInstance() {
        return SignManHelper.INSTANCE;
    }


    //第三个列表界面
    public final String THIRD = "third";

    /**
     * 错误提示弹出框
     */
    private AlertDialog errorDialog;

    //选择弹出框事件
    public void getChooseDialog(final Context context, final String fatherId, final String id, final String name,
                                final String childiId, final String pic, final String roleType, final OnCommonSuccessCallBack onCommonCallBack) {
        errorDialog = new AlertDialog.Builder(context).create();
        errorDialog.show();
        Window window = errorDialog.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        window.setContentView(R.layout.layout_sign_man);
        window.setBackgroundDrawableResource(R.color.alpha_all);
        errorDialog.setCanceledOnTouchOutside(true);

        final TextView tvManList = (TextView) window.findViewById(R.id.tv_man_list);
        TextView tvDeleteMan = (TextView) window.findViewById(R.id.tv_delete_man);
        TextView tvVisitor = (TextView) window.findViewById(R.id.tv_visitor);

        tvManList.setText("成员列表(" + name + ")");
        tvDeleteMan.setText("删除成员(" + name + ")");
        tvVisitor.setText("拜访对象(" + name + ")");

//        if (isSecond) {
//            tvManList.setVisibility(View.GONE);
//        }

        tvManList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtils.getInstance().toRoleTypeActivity(context, SignManActivity.class, id, name, "child", roleType);
                errorDialog.dismiss();
            }
        });

        tvDeleteMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteMember(context, id, fatherId, onCommonCallBack);
                errorDialog.dismiss();
            }
        });

        tvVisitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtils.getInstance().toActivity(context, SignDetailsActivity.class, childiId, pic, "visitor");
                errorDialog.dismiss();
            }
        });

    }


    //选择弹出框事件
    public void getChooseDialog(final Context context, final String id, final String title, boolean isSecond, final OnCommonSuccessCallBack onCommonCallBack) {
        errorDialog = new AlertDialog.Builder(context).create();
        errorDialog.show();
        Window window = errorDialog.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        window.setContentView(R.layout.layout_sign_man);
        window.setBackgroundDrawableResource(R.color.alpha_all);
        errorDialog.setCanceledOnTouchOutside(true);

        TextView tvManList = (TextView) window.findViewById(R.id.tv_man_list);
        TextView tvDeleteMan = (TextView) window.findViewById(R.id.tv_delete_man);

        tvManList.setText(title);
        tvDeleteMan.setText(title);

        if (isSecond) {
            tvManList.setVisibility(View.GONE);
        }

        tvManList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                errorDialog.dismiss();
            }
        });

        tvDeleteMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCommonCallBack.onSuccess();
                errorDialog.dismiss();
            }
        });

    }


    //成员列表
    private void getChildManList(final Context context, final String id, final String name, final MemberBean oldMemberBean) {
        LogUtils.i(UrlConstant.getInstance().URL + PostConstant.getInstance().GET_USER_CHILD
                + FieldConstant.getInstance().USER_ID + "=" + id);
        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message),
                UrlConstant.getInstance().URL + PostConstant.getInstance().GET_USER_CHILD
                        + FieldConstant.getInstance().USER_ID + "=" + id, new OkhttpCallBack() {
                    @Override
                    public void onSuccess(String json) {
                        MemberBean memberBean = JSON.parseObject(json, MemberBean.class);
                        IntentUtils.getInstance().toActivity(context, SignManActivity.class, memberBean, name, id, oldMemberBean);
                    }

                    @Override
                    public void onFail() {

                    }
                });
    }


    //删除成员
    public static void deleteMember(Context context, String id, String fatherId, final OnCommonSuccessCallBack callBack) {
        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().DEL_TWO_CHILD
                + FieldConstant.getInstance().USER_ID + "=" + fatherId + "&"
                + FieldConstant.getInstance().CHILD_ID + "=" + id, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    ToastUtils.getMineToast("删除成功");
                    callBack.onSuccess();
                } else {
                    callBack.onFail();
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

}
