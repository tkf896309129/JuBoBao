package com.example.huoshangkou.jubowan.utils;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：CopyIosDialogUtils
 * 描述：仿照iOS的弹出框进行封装
 * 创建时间：2017-01-06  14:24
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.OutWorkActivity;
import com.example.huoshangkou.jubowan.base.BaseApp;
import com.example.huoshangkou.jubowan.inter.StringCallBack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CopyIosDialogUtils {

    private static class CopyIosHelper {
        private static CopyIosDialogUtils INSTANCE = new CopyIosDialogUtils();
    }

    public static CopyIosDialogUtils getInstance() {
        return CopyIosHelper.INSTANCE;
    }

    private AlertDialog dialog;

    public void getIosDialog(final Context context, String title, final iosDialogClick dialogClick) {
        dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setContentView(R.layout.copy_ios_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.color.alpha_all);
        dialog.setCanceledOnTouchOutside(false);
        TextView btnUpdateNow = (TextView) dialog.getWindow().findViewById(R.id.btn_sure);
        TextView btnUpdateAfter = (TextView) dialog.getWindow().findViewById(R.id.btn_cancel);
        TextView tvTitle = (TextView) dialog.getWindow().findViewById(R.id.tv_alertdialog_title);
        tvTitle.setText(title);
        btnUpdateNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //确定
                dialogClick.confimBtn();
                //取消
                dialog.cancel();
            }
        });
        btnUpdateAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消
                dialog.cancel();
                dialogClick.cancelBtn();
            }
        });
    }


    public void getIosDialog(final Context context, String title, String str, final iosDialogClick dialogClick) {
        dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setContentView(R.layout.copy_ios_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.color.alpha_all);
        dialog.setCanceledOnTouchOutside(false);
        TextView btnUpdateNow = (TextView) dialog.getWindow().findViewById(R.id.btn_sure);
        TextView btnUpdateAfter = (TextView) dialog.getWindow().findViewById(R.id.btn_cancel);
        TextView tvTitle = (TextView) dialog.getWindow().findViewById(R.id.tv_alertdialog_title);
        tvTitle.setText(title);
        btnUpdateNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //确定
                dialogClick.confimBtn();
                //取消
                dialog.cancel();
            }
        });
        btnUpdateAfter.setText(str);
        btnUpdateAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消
                dialog.cancel();
                dialogClick.cancelBtn();
            }
        });
    }

    public void getIosDialog(final Context context, String title,  String confirm,String cancel,final iosDialogClick dialogClick) {
        dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setContentView(R.layout.copy_ios_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.color.alpha_all);
        dialog.setCanceledOnTouchOutside(false);
        TextView btnUpdateNow = (TextView) dialog.getWindow().findViewById(R.id.btn_sure);
        TextView btnUpdateAfter = (TextView) dialog.getWindow().findViewById(R.id.btn_cancel);
        TextView tvTitle = (TextView) dialog.getWindow().findViewById(R.id.tv_alertdialog_title);
        tvTitle.setText(title);

        btnUpdateNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //确定
                dialogClick.confimBtn();
                //取消
                dialog.cancel();
            }
        });
        btnUpdateNow.setText(confirm);
        btnUpdateAfter.setText(cancel);
        btnUpdateAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消
                dialog.cancel();
                dialogClick.cancelBtn();
            }
        });
    }

    public void getIosDialogNoCancel(String content, final Context context, String title, final StringCallBack callBack) {
        dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setContentView(R.layout.copy_ios_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.color.alpha_all);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        TextView btnUpdateNow = (TextView) dialog.getWindow().findViewById(R.id.btn_sure);
        TextView btnUpdateAfter = (TextView) dialog.getWindow().findViewById(R.id.btn_cancel);
        TextView tvTitle = (TextView) dialog.getWindow().findViewById(R.id.tv_alertdialog_title);
        final EditText etPhone = (EditText) dialog.getWindow().findViewById(R.id.et_alertdialog_phone);
        etPhone.setVisibility(View.VISIBLE);
        etPhone.setInputType(InputType.TYPE_CLASS_TEXT);
        etPhone.setText(content);
        tvTitle.setText(title);
        btnUpdateNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //确定
                callBack.onSuccess(etPhone.getText().toString().trim());
                //取消
                dialog.cancel();
            }
        });
        btnUpdateAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消
                dialog.cancel();
            }
        });
    }


    public void getNoCancelIosDialog(final Context context, String title, final iosDialogClick dialogClick) {

        dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setContentView(R.layout.copy_ios_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.color.alpha_all);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        TextView btnUpdateNow = (TextView) dialog.getWindow().findViewById(R.id.btn_sure);
        TextView btnUpdateAfter = (TextView) dialog.getWindow().findViewById(R.id.btn_cancel);
        TextView tvTitle = (TextView) dialog.getWindow().findViewById(R.id.tv_alertdialog_title);
        tvTitle.setText(title);
        btnUpdateNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //确定
                dialogClick.confimBtn();
                //取消
                dialog.cancel();
            }
        });
        btnUpdateAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消
                dialog.cancel();
                dialogClick.cancelBtn();
            }
        });
//        return dialog;
    }

    /**
     * 通用接口
     */
    public interface iosDialogClick {
        void confimBtn();

        void cancelBtn();
    }


    /**
     * 错误提示弹出框
     */
    private AlertDialog errorDialog;

    public void getErrorDialog(Context context, String title, final ErrDialogCallBack dialogClick) {
        if (context == null) {
            ToastUtils.getMineToast(title);
            return;
        }

        errorDialog = new AlertDialog.Builder(context).create();
        errorDialog.show();
        errorDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        errorDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        errorDialog.getWindow().setContentView(R.layout.error_hint_dialog);
        errorDialog.getWindow().setBackgroundDrawableResource(R.color.alpha_all);
        errorDialog.setCanceledOnTouchOutside(false);

        TextView tvSure = (TextView) errorDialog.getWindow().findViewById(R.id.btn_error_sure);
        TextView tvTitle = (TextView) errorDialog.getWindow().findViewById(R.id.tv_error_title);
        tvTitle.setText(title);
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //确定
                if (dialogClick != null) {
                    dialogClick.confirm();
                }
                //取消
                errorDialog.cancel();
            }
        });
    }

    public void getErrorDialogNoCancel(Context context, String title, final ErrDialogCallBack dialogClick) {

        if (context == null) {
            ToastUtils.getMineToast(title);
            return;
        }

        errorDialog = new AlertDialog.Builder(context).create();
        errorDialog.show();
        errorDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        errorDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        errorDialog.getWindow().setContentView(R.layout.error_hint_dialog);
        errorDialog.getWindow().setBackgroundDrawableResource(R.color.alpha_all);
        errorDialog.setCancelable(false);
        TextView tvSure = (TextView) errorDialog.getWindow().findViewById(R.id.btn_error_sure);
        TextView tvTitle = (TextView) errorDialog.getWindow().findViewById(R.id.tv_error_title);
        tvTitle.setText(title);
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //确定
                if (dialogClick != null) {
                    dialogClick.confirm();
                }
                //取消
                errorDialog.cancel();
            }
        });
    }

    AlertDialog.Builder builderLogin;
    AlertDialog dialogLogin;

    public void getNoCancelErrorDialog(final Context context, String title, final ErrDialogCallBack dialogClick) {
        String property = getSystemProperty("ro.miui.ui.version.name");
        LogUtils.i(property);
        builderLogin = new AlertDialog.Builder(context);
        builderLogin.setTitle("温馨提示");
        builderLogin.setMessage(title);
        builderLogin.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogClick.confirm();
                dialog.dismiss();
            }
        });
        builderLogin.setCancelable(false);
        dialogLogin = builderLogin.create();
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);//指定会全局,可以在后台弹出
        dialogLogin.setCanceledOnTouchOutside(false);
        if (dialogLogin != null && !dialogLogin.isShowing()) {
            dialogLogin.show();
        }
    }

    public static String getSystemProperty(String propName) {
        String line;
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + propName);
            input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
            line = input.readLine();
            input.close();
        } catch (IOException ex) {
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                }
            }
        }
        return line;
    }

    /**
     * 错误提示接口
     */
    public interface ErrDialogCallBack {
        void confirm();
    }


    public void setNickName(final Context context, final StringCallBack dialogClick) {
        dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setContentView(R.layout.item_set_nick);
        dialog.getWindow().setBackgroundDrawableResource(R.color.alpha_all);
        dialog.setCanceledOnTouchOutside(false);
        TextView tvConfirm = (TextView) dialog.getWindow().findViewById(R.id.tv_confirm);
        TextView tvCancel = (TextView) dialog.getWindow().findViewById(R.id.tv_cancel);
        final EditText etNickName = (EditText) dialog.getWindow().findViewById(R.id.et_nick_name);
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = etNickName.getText().toString().trim();
                //确定
                dialogClick.onSuccess(string);
                //取消
                dialog.cancel();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消
                dialog.cancel();
                dialogClick.onFail();
            }
        });
    }
}
