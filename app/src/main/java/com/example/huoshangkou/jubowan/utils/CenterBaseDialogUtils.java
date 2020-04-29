package com.example.huoshangkou.jubowan.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.BaseCenterAdapter;
import com.example.huoshangkou.jubowan.bean.AddDateTypeBean;
import com.example.huoshangkou.jubowan.bean.AdvBean;
import com.example.huoshangkou.jubowan.bean.BaseCenterBean;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.inter.StringPositionCallBack;
import com.example.huoshangkou.jubowan.service.DownloadApkService;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：CenterBaseDialogUtils
 * 描述：
 * 创建时间：2019-08-28  09:37
 */

public class CenterBaseDialogUtils {
    //节假日弹框
    public static void getBaseCenterDialog(final Context context, int checkPosition, final List<AddDateTypeBean> list, final StringPositionCallBack stringCallBack) {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();


        final Window window = dialog.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        window.setContentView(R.layout.dialog_base_center);
        window.setBackgroundDrawable(context.getResources().getDrawable(R.color.alpha_all));
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        dialog.setCanceledOnTouchOutside(true);


        //单选false
        setFalse(list, checkPosition);
        TextView tvHint = (TextView) window.findViewById(R.id.tv_hint);
        ListView lvBaseCenter = (ListView) window.findViewById(R.id.lv_base_center);
        LinearLayout llCancel = (LinearLayout) window.findViewById(R.id.ll_cancel);
        llCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        final BaseCenterAdapter centerAdapter = new BaseCenterAdapter(context, list, R.layout.item_base_center);
        lvBaseCenter.setAdapter(centerAdapter);

        lvBaseCenter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (!list.get(i).isCheck()) {
                    list.get(i).setCheck(true);
                    stringCallBack.onStringPosition(list.get(i).getType(),i);
                    dialog.dismiss();
                }
                //单选false
                setFalse(list, i);
                centerAdapter.notifyDataSetChanged();
            }
        });
    }

    private static void setFalse(List<AddDateTypeBean> list, int position) {
        for (int i = 0; i < list.size(); i++) {
            if (i != position) {
                list.get(i).setCheck(false);
            }else {
                list.get(i).setCheck(true);
            }
        }
    }
}
