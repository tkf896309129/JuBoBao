package com.example.huoshangkou.jubowan.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.AdvBean;
import com.example.huoshangkou.jubowan.service.DownloadApkService;


/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：UpdateDialogUtils
 * 描述：更新弹出框
 * 创建时间：2017-01-18  08:54
 */

public class UpdateDialogUtils {

    private static Intent updataService;

    /**
     * 更新提示弹出框
     */
    public static void updataDialogShow(final Context context, final String content) {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        final Window window = dialog.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        window.setContentView(R.layout.dialog_update);
        window.setBackgroundDrawable(context.getResources().getDrawable(R.color.alpha_all));
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        //更新内容
        TextView tvContent = (TextView) window.findViewById(R.id.tv_update_content);
        tvContent.setText(content);

        //立即更新
        TextView tvUpDate = (TextView) window.findViewById(R.id.tv_update_now);
        tvUpDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                downLoadApk(context);
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

    public static void downLoadApk(final Context context) {
        updataService = new Intent(context, DownloadApkService.class);

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1010);
            return;
        }

        if (!NetUtils.isConnected(context)) {
            CopyIosDialogUtils.getInstance().getErrorDialog(context, "网络连接异常，无法下载", new CopyIosDialogUtils.ErrDialogCallBack() {
                @Override
                public void confirm() {

                }
            });
            return;
        }

        if (!NetUtils.isWifi(context)) {
            CopyIosDialogUtils.getInstance().getIosDialog(context, "不是Wifi下，确定下载吗", new CopyIosDialogUtils.iosDialogClick() {
                @Override
                public void confimBtn() {
                    context.startService(updataService);
                }

                @Override
                public void cancelBtn() {

                }
            });
        } else {
            context.startService(updataService);
        }
    }





    //节假日弹框
    public static void getHolidayDialog(final Context context, final AdvBean.DBean.ReObjBean resultBean) {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        updataService = new Intent(context, DownloadApkService.class);
        if (resultBean == null) {
            return;
        }

        final Window window = dialog.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        window.setContentView(R.layout.item_holiday_dialog);
        window.setBackgroundDrawable(context.getResources().getDrawable(R.color.alpha_all));
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        //取消
        ImageView imgCancel = (ImageView) window.findViewById(R.id.iv_update_cancel);
        ImageView imgAdv = (ImageView) window.findViewById(R.id.iv_adv);
        TextView tvTitle = (TextView) window.findViewById(R.id.tv_title);
        TextView tvContent = (TextView) window.findViewById(R.id.tv_update_content);
        TextView tvEnd = (TextView) window.findViewById(R.id.tv_end);
        TextView tvConfirm = (TextView) window.findViewById(R.id.tv_confirm);
        tvTitle.setText(resultBean.getTitle());
        tvContent.setText(resultBean.getTxtContent());
        tvEnd.setText(resultBean.getPopupTail());
        if (StringUtils.isNoEmpty(resultBean.getPics())) {
//            GlideUtils.getInstance().displayFitImage(resultBean.getPics(), context, imgAdv);
            Glide.with(context).load(resultBean.getPics()).fitCenter().into(imgAdv);
        }
        if (!StringUtils.isNoEmpty(resultBean.getTxtContent())) {
            tvContent.setVisibility(View.GONE);
        }
        if (!StringUtils.isNoEmpty(resultBean.getPopupTail())) {
            tvEnd.setVisibility(View.GONE);
        }
        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (StringUtils.isNoEmpty(resultBean.getLink())) {
                    IntentUtils.getInstance().toWebActivity(context, resultBean.getLink(), resultBean.getTitle());
                    return;
                }
                dialog.dismiss();
            }
        });
    }

}
