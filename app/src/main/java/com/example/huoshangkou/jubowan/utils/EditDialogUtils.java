package com.example.huoshangkou.jubowan.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.RepairApproveActivity;
import com.example.huoshangkou.jubowan.inter.OnAddWorkCallBack;
import com.example.huoshangkou.jubowan.inter.OnDialogCallBack;
import com.example.huoshangkou.jubowan.inter.OnZbStandardCallBack;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：EditDialogUtils
 * 描述：
 * 创建时间：2017-03-10  11:30
 */

public class EditDialogUtils {

    private static class EditDialogHelper {
        private static EditDialogUtils INSTANCE = new EditDialogUtils();
    }

    public static EditDialogUtils getInstance() {
        return EditDialogHelper.INSTANCE;
    }

    //弹出输入框
    public void showEditTextDialog(final String type, Context context, String title, final OnAddWorkCallBack callBack) {
        InputUtils.getInstance().inputKeyUtils(context);


        final AlertDialog errorDialog = new AlertDialog.Builder(context).create();
        errorDialog.show();
        errorDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        errorDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        errorDialog.getWindow().setContentView(R.layout.item_edit_dialog);
        errorDialog.getWindow().setBackgroundDrawableResource(R.color.alpha_all);
        errorDialog.setCancelable(false);
        Window window = errorDialog.getWindow();

        TextView tvConfirm = window.findViewById(R.id.tv_sure);
        TextView tvCancel = window.findViewById(R.id.tv_cancel);
        final EditText etContent = window.findViewById(R.id.et_text);
        if (type.equals("num")) {
//            etContent.setInputType(InputType.TYPE_CLASS_NUMBER);
        } else {
            etContent.setInputType(InputType.TYPE_CLASS_TEXT);
        }
        //设置标题
        TextView tvTitle = window.findViewById(R.id.tv_title);
        tvTitle.setText(title);


        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                errorDialog.dismiss();
            }
        });

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = etContent.getText().toString().trim();
                if (!StringUtils.isNoEmpty(content)) {
                    ToastUtils.getMineToast("请输入内容");
                    return;
                }

                if (!type.equals("text") && Double.parseDouble(content) <= 0) {
                    ToastUtils.getMineToast("输入必须大于0");
                    return;
                }

                callBack.addWorkExp(content);
                errorDialog.dismiss();
            }
        });
    }


    //弹出输入框
    public void showEditTextCancelDialog(final String type, Context context, View v, String title, final OnDialogCallBack callBack) {
        InputUtils.getInstance().inputKeyUtils(context);
        final AlertDialog errorDialog = new AlertDialog.Builder(context).create();
        errorDialog.show();
        errorDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        errorDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        errorDialog.getWindow().setContentView(R.layout.item_edit_dialog);
        errorDialog.getWindow().setBackgroundDrawableResource(R.color.alpha_all);
        errorDialog.setCancelable(false);
        Window view = errorDialog.getWindow();
        TextView tvConfirm = (TextView) view.findViewById(R.id.tv_sure);
        TextView tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        final EditText etContent = (EditText) view.findViewById(R.id.et_text);
        if (type.equals("num")) {
//            etContent.setInputType(InputType.TYPE_CLASS_NUMBER);
        } else {
            etContent.setInputType(InputType.TYPE_CLASS_TEXT);
        }
        //设置标题
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvTitle.setText(title);


        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                errorDialog.dismiss();
            }
        });

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = etContent.getText().toString().trim();
                if (!StringUtils.isNoEmpty(content)) {
                    ToastUtils.getMineToast("请输入");
                    return;
                }

                if (!type.equals("text") && Double.parseDouble(content) <= 0) {
                    ToastUtils.getMineToast("输入必须大于0");
                    return;
                }

                callBack.addWorkExp(content, errorDialog);

            }
        });
    }


    public void showPostZbEdit(Context context, View v, final OnZbStandardCallBack callBack) {
        InputUtils.getInstance().inputKeyUtils(context);
        View view = LayoutInflater.from(context).inflate(R.layout.item_pro_standard, null);
        TextView tvConfirm = (TextView) view.findViewById(R.id.tv_sure);
        TextView tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        LinearLayout llDismiss = (LinearLayout) view.findViewById(R.id.ll_dismiss);

        final EditText etMinArea = (EditText) view.findViewById(R.id.et_min_area);
        final EditText etMaxArea = (EditText) view.findViewById(R.id.et_max_area);

        final PopupWindow popupSearchWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);

        //让Popupwindow上面的控件获取焦点
        popupSearchWindow.setFocusable(true);
        //防止其他的控件没有焦点
        popupSearchWindow.setBackgroundDrawable(new BitmapDrawable());
        popupSearchWindow.setOutsideTouchable(true);
        //在底部显示Popupwindow
        popupSearchWindow.showAtLocation(v, Gravity.CENTER, 0, 10);

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupSearchWindow.dismiss();
            }
        });

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String minArea = etMinArea.getText().toString().trim();
                String maxArea = etMaxArea.getText().toString().trim();
                if (!StringUtils.isNoEmpty(minArea)) {
                    ToastUtils.getMineToast("请输入最小面积");
                    return;
                }
                if (!StringUtils.isNoEmpty(maxArea)) {
                    ToastUtils.getMineToast("请输入最大面积");
                    return;
                }
                if (Double.parseDouble(minArea) > Double.parseDouble(maxArea)) {
                    ToastUtils.getMineToast("最小的面积不能大于最大的面积");
                    return;
                }


                callBack.onGetArea(minArea, maxArea);
                popupSearchWindow.dismiss();
            }
        });

        llDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupSearchWindow.dismiss();
            }
        });


    }


}
