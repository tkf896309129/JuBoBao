package com.example.huoshangkou.jubowan.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseApp;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：ToastUtils
 * 描述：吐司工具类
 * 创建时间：2016-12-28  14:42
 */

public class ToastUtils {

    private static Toast toast;

    /**
     * 自定义Toast布局
     */
    public static void getMineToast(int msg) {
        if (toast == null) {
            toast = new Toast(BaseApp.getInstance());
        }
        LinearLayout ll = (LinearLayout) LayoutInflater.from(BaseApp.getInstance()).inflate(R.layout.mine_define_toast, null);
        TextView tvToast = (TextView) ll.findViewById(R.id.tv_toast);
        tvToast.setText(msg);
        toast.setView(ll);
        toast.setGravity(Gravity.CLIP_HORIZONTAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void getMineToast(String msg) {
        if (toast == null) {
            toast = new Toast(BaseApp.getInstance());
        }
        LinearLayout ll = (LinearLayout) LayoutInflater.from(BaseApp.getInstance()).inflate(R.layout.mine_define_toast, null);
        TextView tvToast = (TextView) ll.findViewById(R.id.tv_toast);
        tvToast.setText(msg);
        toast.setView(ll);
        toast.setGravity(Gravity.CLIP_HORIZONTAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }


    private static Toast toastSingle;

    /**
     * Toast一直都只会显示一个 还是挺厉害的耶
     *
     * @param content
     */
    public static void showToast(String content) {
        if (toastSingle == null) {
            toastSingle = Toast.makeText(BaseApp.getInstance(),
                    content,
                    Toast.LENGTH_SHORT);
        } else {
            toastSingle.setText(content);
        }
        toastSingle.show();
    }
}
