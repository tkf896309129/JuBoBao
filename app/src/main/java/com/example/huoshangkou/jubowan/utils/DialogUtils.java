package com.example.huoshangkou.jubowan.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.JuBoBtActivity;
import com.example.huoshangkou.jubowan.adapter.BaseDialogAdapter;
import com.example.huoshangkou.jubowan.bean.BtXyBean;
import com.example.huoshangkou.jubowan.bean.JbBtBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.BtBeanCallBack;
import com.example.huoshangkou.jubowan.inter.ChooseCheckMan;
import com.example.huoshangkou.jubowan.inter.ChooseCheckTwoMan;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.inter.StringPositionCallBack;
import com.example.huoshangkou.jubowan.inter.SuccessCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


/**
 * Created by tang on 2016/2/27.
 */
public class DialogUtils {

    private static class SearchHelper {
        private static final DialogUtils INSTANCE = new DialogUtils();
    }

    public static DialogUtils getInstance() {
        return SearchHelper.INSTANCE;
    }

    private ProgressDialog alertDialog;

    private AlertDialog.Builder builder;


    /**
     * 选择审批人
     */
    public void chooseCheckMan(Context context, String title, String message, final ChooseCheckMan checkMan) {

        CopyIosDialogUtils.getInstance().getIosDialog(context, message, new CopyIosDialogUtils.iosDialogClick() {
            @Override
            public void confimBtn() {
                checkMan.onCheck();
            }

            @Override
            public void cancelBtn() {
            }
        });
    }

    public void chooseCheckMan(Context context, String title, String message, String cancel, String confirm, final ChooseCheckMan checkMan) {

        CopyIosDialogUtils.getInstance().getIosDialog(context, title, new CopyIosDialogUtils.iosDialogClick() {
            @Override
            public void confimBtn() {
                checkMan.onCheck();
            }

            @Override
            public void cancelBtn() {
            }
        });
    }

    public void chooseCheckMan(Context context, String title, String message, String cancel, String confirm, final ChooseCheckTwoMan checkMan) {
        CopyIosDialogUtils.getInstance().getIosDialog(context, title, new CopyIosDialogUtils.iosDialogClick() {
            @Override
            public void confimBtn() {
                checkMan.onCheck();
            }

            @Override
            public void cancelBtn() {
                checkMan.onCancel();
            }
        });

    }


    /**
     * 数据加载中弹出框
     * 30  325w
     *
     * @param context
     * @return
     */
    public ProgressDialog loadingDialog(Context context) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage(context.getString(R.string.StringLoading));
        return dialog;
    }


    /**
     * 联系我们弹出框
     */
    public AlertDialog.Builder LinkUsDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("联系客服:400-6020-128");
        return builder;
    }




    public void juBoBtXy(final Context context, String contractNo) {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        window.setContentView(R.layout.item_bt_xy);
        window.setBackgroundDrawableResource(R.color.alpha_all);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setWindowAnimations(R.style.PopupAnimation);
        LinearLayout llCancel = (LinearLayout) window.findViewById(R.id.ll_cancel);
        llCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        TextView tvXyOne = (TextView) window.findViewById(R.id.tv_xy_one);
        TextView tvXyTwo = (TextView) window.findViewById(R.id.tv_xy_two);
        TextView tvXyThree = (TextView) window.findViewById(R.id.tv_xy_three);
        tvXyOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtils.getInstance().toWebActivity(context, ruleCredit, "白条赊购服务协议");
            }
        });
        tvXyTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtils.getInstance().toWebActivity(context, ruleService, "送达协议");
            }
        });
        tvXyThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtils.getInstance().toWebActivity(context, ruleCharge, "白条服务费用计算规则");
            }
        });

        getXyRule("1", contractNo, context);
    }

    private String ruleService = "";
    private String ruleCharge = "";
    private String ruleCredit = "";

    //获取相关协议
    public void getXyRule(String type, String code, Context context) {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(context, UrlConstant.getInstance().URL
                + PostConstant.getInstance().IOUS_AGREEMENT_URL
                + FieldConstant.getInstance().TYPE + "=" + type + "&"
                + FieldConstant.getInstance().AGREEMENT_CODE + "=" + code, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String rule = jsonObject.getString("ReObj");
                    if (!StringUtils.isNoEmpty(rule)) {
                        return;
                    }
                    BtXyBean btXyBean = com.alibaba.fastjson.JSONObject.parseObject(rule, BtXyBean.class);
                    if (btXyBean == null) {
                        return;
                    }
                    ruleService = btXyBean.getServiceConfirmation();
                    ruleCharge = btXyBean.getChargeRules();
                    ruleCredit = btXyBean.getCreditService();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    //提交成功弹出框
    public void commitSuccess(final Context context) {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        window.setContentView(R.layout.dialog_bank_commit_success);
        window.setBackgroundDrawableResource(R.color.alpha_all);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ImageView imgCancel = (ImageView) window.findViewById(R.id.iv_cancel);
        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        TextView tvConfirm = (TextView) window.findViewById(R.id.tv_confirm);
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().btList);
            }
        });
    }

    public void priceCheckDialog(final Context context, final StringCallBack stringCallBack) {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();//子弹短信
        Window window = dialog.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        window.setContentView(R.layout.dialog_price_check);
        window.setBackgroundDrawableResource(R.color.alpha_all);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ImageView imgCancel = (ImageView) window.findViewById(R.id.iv_cancel);
        final EditText etPrice = (EditText) window.findViewById(R.id.et_price);
        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        TextView tvCommit = (TextView) window.findViewById(R.id.tv_commit_check);
        tvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String price = etPrice.getText().toString().trim();
                if (!StringUtils.isNoEmpty(price)) {
                    ToastUtils.getMineToast("请输入验证金额");
                    return;
                }
                dialog.dismiss();
                stringCallBack.onSuccess(price);
            }
        });
    }

    //使用白条认证
    public void btUseCheck(final Context context, String title) {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        window.setContentView(R.layout.item_use_jbbt);
        window.setBackgroundDrawableResource(R.color.alpha_all);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ImageView imgCancel = (ImageView) window.findViewById(R.id.iv_cancel);
        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        TextView tvHint = (TextView) window.findViewById(R.id.tv_price_hint);
        tvHint.setText(title);
        TextView tvUseBt = (TextView) window.findViewById(R.id.tv_use_bt);
        TextView tvCancel = (TextView) window.findViewById(R.id.tv_cancel);
        tvUseBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                IntentUtils.getInstance().toActivity(context, JuBoBtActivity.class);
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    //聚玻贷还款
    public void backJbdDialog(final Context context) {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        window.setContentView(R.layout.dialog_back_jbd);
        window.setBackgroundDrawableResource(R.color.alpha_all);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ImageView imgCancel = (ImageView) window.findViewById(R.id.iv_cancel);
        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        TextView tvBackJbd = (TextView) window.findViewById(R.id.tv_back_jbd);
        tvBackJbd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                IntentUtils.getInstance().toActivity(context, JuBoBtActivity.class);
            }
        });
    }

    //聚玻贷还款
    public void hadKnow(Context context, String hint) {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        window.setContentView(R.layout.dialog_alerady_konow);
        window.setBackgroundDrawableResource(R.color.alpha_all);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ImageView imgCancel = (ImageView) window.findViewById(R.id.iv_cancel);
        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        TextView tvBackJbd = (TextView) window.findViewById(R.id.tv_alerdy_know);
        TextView tvHint = (TextView) window.findViewById(R.id.tv_price_hint);
        tvBackJbd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        if (StringUtils.isNoEmpty(hint)) {
            tvHint.setText(hint);
        }
    }

//    int codeTime = 0;
//    String getCode = "";
//    String phone = "";

    //白条支付
//    public void btPayDialog(final Context context, final String price, final String orderId, final SuccessCallBack successCallBack) {
//        final AlertDialog dialog = new AlertDialog.Builder(context).create();
//        dialog.show();
//        Window window = dialog.getWindow();
//        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//        window.setContentView(R.layout.dialog_bt_pay);
//        window.setBackgroundDrawableResource(R.color.alpha_all);
//        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//
//        final TextView tvPrice = (TextView) window.findViewById(R.id.tv_price);
//        final TextView tvTotalPrice = (TextView) window.findViewById(R.id.tv_total_price);
//        final TextView tvRestPrice = (TextView) window.findViewById(R.id.tv_rest_price);
//        final TextView tvSendCode = (TextView) window.findViewById(R.id.tv_send_code);
//        final TextView tvRestDays = (TextView) window.findViewById(R.id.tv_rest_days);
//        final EditText etCode = (EditText) window.findViewById(R.id.et_code);
//        TextView tvPay = (TextView) window.findViewById(R.id.tv_pay);
//        tvPrice.setText(price);
//        ImageView imgCancel = (ImageView) window.findViewById(R.id.iv_cancel);
//        imgCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//        tvSendCode.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (!StringUtils.isNoEmpty(phone)) {
//                    ToastUtils.getMineToast("验证码发送失败");
//                    return;
//                }
//                codeTime = 60;
//                SendCodeUtils.getInstance().sendCode(phone, tvSendCode, context, codeTime, true,
//                        SharedValueConstant.getInstance().BIND_PHONE, new StringCallBack() {
//                            @Override
//                            public void onSuccess(String str) {
//                                etCode.setText(str);
//                                getCode = str;
//                            }
//
//                            @Override
//                            public void onFail() {
//
//                            }
//                        });
//            }
//        });
//        tvPay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String code = etCode.getText().toString().trim();
//                if (!StringUtils.isNoEmpty(code)) {
//                    ToastUtils.getMineToast("请输入验证码");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(getCode)) {
//                    ToastUtils.getMineToast("请获取验证码");
//                    return;
//                }
//                if (!getCode.equals(code)) {
//                    ToastUtils.getMineToast("请输入正确的验证码");
//                    return;
//                }
//                btPay(context, orderId, price, successCallBack);
//                dialog.dismiss();
//            }
//        });
//        btQua(context, new BtBeanCallBack() {
//            @Override
//            public void getBtBean(JbBtBean btBean) {
//                if (btBean.getReObj() == null) {
//                    tvTotalPrice.setText("白条没有额度，请前往申请");
//                    return;
//                }
//
//                tvRestDays.setText(btBean.getReObj().getIousRemainingDays() + "天");
//                tvRestPrice.setText(btBean.getReObj().getIousRemainingAmount() + "");
//                tvTotalPrice.setText(btBean.getReObj().getIousTotalAmount() + "");
//            }
//        });
//    }

    //白条支付
//    public void btPay(Context context, String orderId, String money, final SuccessCallBack successCallBack) {
//        OkhttpUtil.getInstance().setUnCacheData(context, "白条支付中", UrlConstant.getInstance().URL
//                + PostConstant.getInstance().BT_PAY
//                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
//                + FieldConstant.getInstance().ORDER_ID + "=" + orderId + "&"
//                + FieldConstant.getInstance().MONEY + "=" + money, new OkhttpCallBack() {
//            @Override
//            public void onSuccess(String json) {
//                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
//                if (successBean.getSuccess() == 1) {
//                    ToastUtils.getMineToast("支付成功");
//                    successCallBack.onSuccess();
//                } else {
//                    ToastUtils.getMineToast(successBean.getErrMsg());
//                }
//            }
//
//            @Override
//            public void onFail() {
//
//            }
//        });
//    }

    //判断当前用户的白条资格
    public void btQua(Context context, final BtBeanCallBack callBack) {
        OkhttpUtil.getInstance().setUnCacheData(context, "数据加载中", UrlConstant.getInstance().URL + PostConstant.getInstance().BT_QUA_GET
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                JbBtBean btBean = JSON.parseObject(json, JbBtBean.class);
                callBack.getBtBean(btBean);
            }

            @Override
            public void onFail() {

            }
        });
    }


    //前往人脸识别
    //白条支付
    public void toFaceCheck(final Context context, String hint, final SuccessCallBack successCallBack) {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        window.setContentView(R.layout.dialog_to_face);
        window.setBackgroundDrawableResource(R.color.alpha_all);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ImageView imgCancel = (ImageView) window.findViewById(R.id.iv_cancel);
        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                successCallBack.onFail();
            }
        });
        TextView tvToFace = (TextView) window.findViewById(R.id.tv_to_face);
        TextView tvHint = (TextView) window.findViewById(R.id.tv_price_hint);
        tvHint.setText(hint);
        tvToFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                successCallBack.onSuccess();
                dialog.dismiss();
            }
        });
    }

    AlertDialog dialog = null;

    public void getBaseDialog(Context context, final List<String> list, final StringPositionCallBack stringCallBack) {
        if (dialog != null) {
            dialog.cancel();
        }
        if (context == null) {
            return;
        }
        dialog = new AlertDialog.Builder(context).create();
        LogUtils.e(context.getClass() + " --- " + dialog.getContext().getPackageManager().getClass() + dialog.getWindow().getContext().getClass());
        dialog.show();
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setContentView(R.layout.base_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.color.alpha_all);
        dialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setWindowAnimations(R.style.PopupAnimation);

        Window view = dialog.getWindow();
        WindowManager.LayoutParams lp = view.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        view.setAttributes(lp);
        BaseDialogAdapter dialogAdapter = new BaseDialogAdapter(context, list, R.layout.item_base_dialog);
        ListView lvBase = (ListView) view.findViewById(R.id.lv_base_dialog);
        View cancelView = view.findViewById(R.id.view_cancel);
        TextView tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        RelativeLayout rlCancel = (RelativeLayout) view.findViewById(R.id.rl_cancel);
        lvBase.setAdapter(dialogAdapter);
        lvBase.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                stringCallBack.onStringPosition(list.get(i), i);
                dialog.dismiss();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        cancelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        rlCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
