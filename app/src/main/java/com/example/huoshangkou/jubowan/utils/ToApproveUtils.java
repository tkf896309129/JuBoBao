package com.example.huoshangkou.jubowan.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.ApproveSelectActivity;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.service.DownloadApkService;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：ToApproveUtils
 * 描述：
 * 创建时间：2017-06-26  14:44
 */

public class ToApproveUtils {

    private static Intent updataService;

    /**
     * 更新提示弹出框
     */
    public static void updataDialogShow(final Context context) {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        updataService = new Intent(context, DownloadApkService.class);

        final Window window = dialog.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        window.setContentView(R.layout.layout_to_approve);
        window.setBackgroundDrawable(context.getResources().getDrawable(R.color.alpha_all));
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);


        //立即更新
        TextView tvToApprove = (TextView) window.findViewById(R.id.tv_to_approve);
        tvToApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                //用户身份类型
                String type = (String) SharedPreferencesUtils.getInstance().get(context, SharedKeyConstant.getInstance().USER_TYPE, "");
                //审核状态
                String state = (String) SharedPreferencesUtils.getInstance().get(context, SharedKeyConstant.getInstance().USER_STATE, "");
                IntentUtils.getInstance().toActivity(context, ApproveSelectActivity.class, type, state);
            }
        });
        RelativeLayout rlDismiss = (RelativeLayout) window.findViewById(R.id.rl_dismiss);
        rlDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        //取消
        ImageView imgCancel = (ImageView) window.findViewById(R.id.iv_update_cancel);
        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
