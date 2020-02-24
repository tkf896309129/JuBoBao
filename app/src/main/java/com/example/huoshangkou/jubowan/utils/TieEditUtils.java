package com.example.huoshangkou.jubowan.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.constant.TieDetailConstant;
import com.example.huoshangkou.jubowan.inter.TieEditInterface;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.businessmarket
 * 类名：TieEditUtils
 * 描述：
 * 创建时间：2016-11-03  08:48
 */
public class TieEditUtils {

    private static AlertDialog dialog;

    private String isShare;

    /**
     * 更新进度框
     */
    public static void updateDialog(String type, int postateState, final String id,
                                    final String tieId, final Context context,
                                    String isShare,
                                    final TieEditInterface tieEditClick) {
        dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setContentView(R.layout.item_tie_edit);
        dialog.getWindow().setBackgroundDrawableResource(R.color.alpha_all);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.setCanceledOnTouchOutside(true);
        TextView tvSuccess = (TextView) dialog.getWindow().findViewById(R.id.tv_commit_success);
        TextView tvClockTie = (TextView) dialog.getWindow().findViewById(R.id.tv_clock_tie);
        TextView tvDeleteTie = (TextView) dialog.getWindow().findViewById(R.id.tv_delete_tie);
        TextView tvUnClockTie = (TextView) dialog.getWindow().findViewById(R.id.tv_unclock_tie);
        RelativeLayout rlDismiss = (RelativeLayout) dialog.getWindow().findViewById(R.id.rl_dismiss);
        TextView tvDeleteBackTie = (TextView) dialog.getWindow().findViewById(R.id.tv_delete_backTie);

        TextView tvShare = (TextView) dialog.getWindow().findViewById(R.id.tv_share);
        TextView tvSave = (TextView) dialog.getWindow().findViewById(R.id.tv_save);

        LinearLayout llManager = (LinearLayout) dialog.getWindow().findViewById(R.id.ll_manager);
        LinearLayout llShare = (LinearLayout) dialog.getWindow().findViewById(R.id.ll_share);


        //分享帖子
        if (type.equals(TieDetailConstant.getInstance().SHARE)) {
            llShare.setVisibility(View.GONE);
            llManager.setVisibility(View.GONE);
            //待审核
        } else if (type.equals(TieDetailConstant.getInstance().TIE_APPLY)) {
            tvUnClockTie.setVisibility(View.GONE);
            tvClockTie.setVisibility(View.GONE);
            tvDeleteBackTie.setVisibility(View.GONE);
            //锁定中
        } else if (type.equals(TieDetailConstant.getInstance().IS_CLOCK)) {
            tvSuccess.setVisibility(View.GONE);
            tvClockTie.setVisibility(View.GONE);
            tvDeleteTie.setVisibility(View.GONE);
            tvDeleteBackTie.setVisibility(View.GONE);
            //待审回帖
        } else if (type.equals(TieDetailConstant.getInstance().APPLY_BACK_TIE)) {
            tvUnClockTie.setVisibility(View.GONE);
            tvDeleteTie.setVisibility(View.GONE);
        }


        rlDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        tvSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tieEditClick.checkTie(id, tieId);
            }
        });

        tvClockTie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tieEditClick.clockTie(tieId);
                dialog.dismiss();
            }
        });

        tvDeleteTie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tieEditClick.deleteTie(tieId);
                dialog.dismiss();
            }
        });

        tvUnClockTie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tieEditClick.unClockTie(tieId);
                dialog.dismiss();
            }
        });

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tieEditClick.saveTie(tieId);
                dialog.dismiss();
            }
        });

        tvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tieEditClick.shareTie(tieId);
                dialog.dismiss();
            }
        });

        tvDeleteBackTie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tieEditClick.deleteBackTie(id);
                dialog.dismiss();
            }
        });
    }

}
