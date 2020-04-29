package com.example.huoshangkou.jubowan.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.BaoJinAdapter;
import com.example.huoshangkou.jubowan.bean.BjBean;
import com.example.huoshangkou.jubowan.bean.BjListBean;
import com.example.huoshangkou.jubowan.bean.LoanBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.activity.FaceLivenessExpActivity;
import com.example.huoshangkou.jubowan.inter.ConfirmCallBack;
import com.example.huoshangkou.jubowan.view.ScrollListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：LoanAdviceDialogUtils
 * 描述：
 * 创建时间：2017-09-21  14:48
 */

public class LoanAdviceDialogUtils {

    private AlertDialog dialog;

    public static class LoanAdviceHelper {
        private static LoanAdviceDialogUtils INSTANCE = new LoanAdviceDialogUtils();
    }

    public static LoanAdviceDialogUtils getInstance() {
        return LoanAdviceHelper.INSTANCE;
    }

    public AlertDialog getLoanDialog(final Context context, LoanBean loanBean) {
        dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setContentView(R.layout.loan_advice_activity);
        dialog.getWindow().setBackgroundDrawableResource(R.color.alpha_all);
        dialog.setCanceledOnTouchOutside(false);

        Window window = dialog.getWindow();

        TextView tvConfirm = (TextView) window.findViewById(R.id.tv_confirm);
        TextView tvCancel = (TextView) window.findViewById(R.id.tv_cancel);

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtils.getInstance().toActivity(context, FaceLivenessExpActivity.class);
                dialog.dismiss();
//                context.startActivity(new Intent(context, OpenglActivity.class)
//                        .putExtra("faceSize", min_face_size).putExtra("interval", detection_interval)
//                        .putExtra("resolution", resolutionMap).putExtra("isFaceProperty", false));
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        if (loanBean != null) {


            //甲方授权人
            TextView tvTopOne = (TextView) window.findViewById(R.id.tv_top_one);
            tvTopOne.setText("甲方（授权人）：" + loanBean.getLinkMan());

            //身份证号
            TextView tvIdCard = (TextView) window.findViewById(R.id.tv_id_card);
            tvIdCard.setText("身份证号码：" + loanBean.getLinkManCardNo());

            //乙方授权人
            TextView tvTopTwo = (TextView) window.findViewById(R.id.tv_top_two);
            tvTopTwo.setText("乙方（被授权人）：" + loanBean.getCompanyed());

            //地址
            TextView tvLocation = (TextView) window.findViewById(R.id.tv_location);
            tvLocation.setText("住所地：" + loanBean.getAddress());

            //甲方授权人
            TextView tvOneSign = (TextView) window.findViewById(R.id.tv_one_sign);
            tvOneSign.setText("甲方（授权人）：" + loanBean.getLinkMan());

            //乙方授权人
            TextView tvTwoSign = (TextView) window.findViewById(R.id.tv_two_sign);
            tvTwoSign.setText("乙方（被授权人）：" + loanBean.getCompanyed());
        }
        return dialog;
    }

    public AlertDialog getBaoJinDialog(String id ,Context context, final ConfirmCallBack confirmCallBack) {
        dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setContentView(R.layout.item_bj_layout);
        dialog.getWindow().setBackgroundDrawableResource(R.color.alpha_all);
        dialog.setCanceledOnTouchOutside(false);
        ScrollListView listView = (ScrollListView) dialog.getWindow().findViewById(R.id.lv_jin_bao);

        TextView tvConfirm = (TextView) dialog.getWindow().findViewById(R.id.tv_confirm);
        TextView tvCancel = (TextView) dialog.getWindow().findViewById(R.id.tv_cancel);
        TextView tvTitle = (TextView) dialog.getWindow().findViewById(R.id.tv_title);

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                confirmCallBack.onCancel();
            }
        });

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmCallBack.onConfirm();
            }
        });

        List<BjListBean> list = new ArrayList<>();
        BaoJinAdapter baoJinAdapter = new BaoJinAdapter(context, list, R.layout.item_bj_list_layout);
        listView.setAdapter(baoJinAdapter);

        getBaoJin("943wski937", context, list, baoJinAdapter, tvTitle, id);
        return dialog;

    }


    //获取报警名单  http://192.168.10.120/webapi/atapi/GetBaoJing/?token=943wski937&UserID=3618
    public void getBaoJin(String token, Context context, final List<BjListBean> list,
                          final BaoJinAdapter baoJinAdapter, final TextView tvTitle,String id) {
//        String url = "http://192.168.10.120/webapi/atapi/GetBaoJing/?token=943wski937&UserID=3618";

        String url = UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_BAO_JING + FieldConstant.getInstance().TOKEN + "=" + token + "&"
                + FieldConstant.getInstance().USER_ID + "=" + id;


        OkhttpUtil.getInstance().setUnCacheData(context, "数据加载中", url, new OkhttpCallBack() {
            @Override//
            public void onSuccess(String json) {
                LogUtils.i(json);
                BjBean bjBean = JSON.parseObject(json, BjBean.class);
                list.addAll(bjBean.getData().getDetail_list());
                baoJinAdapter.notifyDataSetChanged();
                tvTitle.setText("[客户] " + bjBean.getData().getCustomer_name() + "  [笔数] " + bjBean.getData().getNums() + "  [金额] " + bjBean.getData().getSum_money());
            }

            @Override
            public void onFail() {

            }
        });
    }
}
