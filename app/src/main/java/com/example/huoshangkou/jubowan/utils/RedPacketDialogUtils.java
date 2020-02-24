package com.example.huoshangkou.jubowan.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.JuBoNewActivity;
import com.example.huoshangkou.jubowan.activity.ToolDescActivity;
import com.example.huoshangkou.jubowan.activity.WebActivity;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：RedPacketDialogUtils
 * 描述：
 * 创建时间：2017-10-11  10:25
 */

public class RedPacketDialogUtils {

    /**
     * 更新提示弹出框
     */
    public static void updataDialogShow(final Context context) {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();

        final Window window = dialog.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        window.setContentView(R.layout.layout_red_pacekt);
        window.setBackgroundDrawable(context.getResources().getDrawable(R.color.alpha_all));
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

//        window.setWindowAnimations(R.anim.push_bottom_out);

        TextView tvCancel = (TextView) window.findViewById(R.id.tv_cancel);
        TextView tvCheck = (TextView) window.findViewById(R.id.tv_check_packet);

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        tvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtils.getInstance().toActivity(context, JuBoNewActivity.class);
                dialog.dismiss();
            }
        });
    }
}
