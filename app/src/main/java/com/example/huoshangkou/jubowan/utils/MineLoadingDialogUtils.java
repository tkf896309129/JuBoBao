package com.example.huoshangkou.jubowan.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：MineLoadingDialogUtils
 * 描述：自定义进度弹出框
 * 创建时间：2017-03-09  08:40
 */

public class MineLoadingDialogUtils {

    private static AlertDialog dialog;

    /**
     * 更新进度框
     */
    public static AlertDialog updateDialog(final Context context, String message) {
        dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.FLAG_LOCAL_FOCUS_MODE);
        window.setContentView(R.layout.mine_loading_dialog);
        window.setBackgroundDrawableResource(R.color.alpha_all);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        TextView tvShow = (TextView) window.findViewById(R.id.tv_show_message);
        tvShow.setText(message);
        return dialog;
    }

}
