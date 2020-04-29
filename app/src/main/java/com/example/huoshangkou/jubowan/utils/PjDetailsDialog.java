package com.example.huoshangkou.jubowan.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.PjOrderActivity;
import com.example.huoshangkou.jubowan.adapter.PjStandardAdapter;
import com.example.huoshangkou.jubowan.bean.PjCarListBean;
import com.example.huoshangkou.jubowan.bean.PjOrderBean;
import com.example.huoshangkou.jubowan.bean.PjOrderListBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.bean.VAListBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnPjDetailCallBack;
import com.example.huoshangkou.jubowan.inter.OnPositionClick;
import com.example.huoshangkou.jubowan.view.LiuShiLayoutView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.utils
 * 类名：PjDetailsDialog
 * 描述：
 * 创建时间：2017-11-24  08:49
 */

public class PjDetailsDialog {

    //商品id
    private String productId = "";
    //规格id
    private String standardIds = "0";
    //商品数量
    private String number = "";
    //规格
    private String guiGe = "";
    //描述
    private String votagl = "";
    List<VAListBean> listStandard;

    //是否刷新规格
    private boolean isInitStandard = true;

    //是否刷新电压
    private boolean isInitValue = true;

    //电压id
    private String vaIds = "";

    private static class PjDetailHelper {
        private static PjDetailsDialog INSTANCE = new PjDetailsDialog();
    }

    public static PjDetailsDialog getInstance() {
        return PjDetailHelper.INSTANCE;
    }

    private AlertDialog dialog;
    private ViewGroup.MarginLayoutParams lp;

    public void getPjDetailsDialog(final Context context, String classId, String modeId) {
        dialog = new AlertDialog.Builder(context).create();
        standardIds = "0";
        vaIds = "";

        lp = new ViewGroup.MarginLayoutParams(
                ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
        lp.leftMargin = 5;
        lp.rightMargin = 5;
        lp.topMargin = 5;
        lp.bottomMargin = 5;
        //145321 个eos
        dialog.show();
        dialog.getWindow().setContentView(R.layout.item_pj_detail);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setBackgroundDrawableResource(R.color.alpha_all);
        dialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setWindowAnimations(R.style.PopupAnimation);
        Window view = dialog.getWindow();
        ImageView imgDismiss = (ImageView) view.findViewById(R.id.iv_dismiss);
        final ImageView imgPj = (ImageView) view.findViewById(R.id.iv_pj);
        ImageView imgDecrease = (ImageView) view.findViewById(R.id.iv_decrease);
        ImageView imgAdd = (ImageView) view.findViewById(R.id.iv_add);
        TextView tvDismiss = (TextView) view.findViewById(R.id.tv_dismiss);
        final TextView tvName = (TextView) view.findViewById(R.id.tv_name);
        final TextView tvPjPrice = (TextView) view.findViewById(R.id.tv_pj_price);
        TextView tvShopCar = (TextView) view.findViewById(R.id.tv_shop_car);
        final TextView tvOrderLimit = (TextView) view.findViewById(R.id.tv_order_limit);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recy_pj_detail);
        final EditText etNum = (EditText) view.findViewById(R.id.tv_num);
        //11月24号的时候
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = etNum.getText().toString().trim();
                if (!StringUtils.isNoEmpty(content)) {
                    content = "0";
                }

                int add = Integer.parseInt(content);
                etNum.setText((add + 1) + "");
            }
        });
        imgDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = etNum.getText().toString().trim();
                if (!StringUtils.isNoEmpty(content)) {
                    content = "0";
                }

                int add = Integer.parseInt(content);
                if (add > 1) {
                    etNum.setText((add - 1) + "");
                }
            }
        });

        tvDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        tvShopCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCar(context, productId, "add");
            }
        });

        tvOrderLimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (orderBeans != null) {
                    number = etNum.getText().toString().trim();
                    if (!StringUtils.isNoEmpty(number)) {
                        ToastUtils.getMineToast("请输入购买数量");
                        return;
                    }
                    ArrayList<PjCarListBean> listTo = new ArrayList<>();
                    PjCarListBean listBean = new PjCarListBean();
                    listBean.setNumber(number);
                    listBean.setName(orderBeans.getName());
                    listBean.setModelTitle(votagl);
                    listBean.setPic(orderBeans.getPic());
                    listBean.setPrice(orderBeans.getPrice());
                    listBean.setGuiGeVal(guiGe);
                    listBean.setProductID(orderBeans.getProductID());
                    listTo.add(listBean);

                    Intent intent = new Intent(context, PjOrderActivity.class);
                    intent.putParcelableArrayListExtra("listTo", listTo);
                    if (StringUtils.isNoEmpty(number) && StringUtils.isNoEmpty(orderBeans.getPrice())) {
                        double allPrice = (Double.parseDouble(orderBeans.getPrice()) * Double.parseDouble(number));
                        intent.putExtra(IntentUtils.getInstance().TYPE, allPrice + "");
                    }

                    context.startActivity(intent);

                    dialog.dismiss();
                }

            }
        });

        //10月25号的时候
        imgDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        final LiuShiLayoutView layoutView = (LiuShiLayoutView) view.findViewById(R.id.layout_pj_buy);
        isInitStandard = true;
        isInitValue = true;

        getStandardDataView(context, classId, modeId, standardIds, tvName, tvPjPrice, imgPj, layoutView, vaIds, recyclerView);

        WindowManager.LayoutParams lp = view.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        view.setAttributes(lp);
    }

    PjOrderListBean orderBeans;

    //规格数据解析
    public void getStandardDataView(final Context context, final String classId, final String modeId, final String standardId,
                                    final TextView tvName, final TextView tvPjPrice, final ImageView imgPj,
                                    final LiuShiLayoutView layoutView, String vaId, final RecyclerView recyclerView) {
        getStandardData(context, classId, modeId, standardId, vaId, new OnPjDetailCallBack() {
            @Override
            public void onPjDeatilCallBack(PjOrderListBean orderBean) {
                orderBeans = orderBean;
                guiGe = orderBean.getGuiGeVal();
                votagl = orderBean.getModelTitle();

                productId = orderBean.getProductID();

                tvName.setText(orderBean.getName());
                tvPjPrice.setText("￥" + orderBean.getPrice());
                GlideUtils.getInstance().displayImage(orderBean.getPic(), context, imgPj);
                final List<String> standardList = new ArrayList<>();
                standardList.clear();
                if (orderBean.getGuiGeList() != null) {
                    int num1 = orderBean.getGuiGeList().size();
                    for (int i = 0; i < num1; i++) {
                        standardList.add(orderBean.getGuiGeList().get(i).getGuiGeVal());
                    }
                }


                if (isInitStandard) {
                    layoutView.removeAllViews();

                    int num = standardList.size();
                    for (int i = 0; i < num; i++) {
                        TextView tvStandard = new TextView(context);
                        tvStandard.setText(standardList.get(i));
                        tvStandard.setBackground(context.getResources().getDrawable(R.drawable.white_circle_bg));
                        tvStandard.setTextColor(context.getResources().getColor(R.color.calculator_text_color));
                        tvStandard.setPadding(20, 5, 20, 5);
                        layoutView.addView(tvStandard, lp);
                    }

                    final int num2 = layoutView.getChildCount();
                    for (int i = 0; i < num2; i++) {
                        final int finalI = i;
                        final TextView childAt = (TextView) layoutView.getChildAt(i);

                        try {

                            childAt.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    int num3 = layoutView.getChildCount();
                                    for (int j = 0; j < num3; j++) {
                                        TextView childAt = (TextView) layoutView.getChildAt(j);
                                        if (j == finalI) {
                                            isInitStandard = false;
                                            if (orderBeans.getGuiGeList() != null && orderBeans.getGuiGeList().size() > j) {
                                                standardIds = orderBeans.getGuiGeList().get(j).getGuiGeID();
                                            }
                                            getStandardDataView(context, classId, modeId, standardIds, tvName, tvPjPrice, imgPj, layoutView, vaIds, recyclerView);
                                            childAt.setBackground(context.getResources().getDrawable(R.drawable.blue_circle_bg));
                                            childAt.setTextColor(context.getResources().getColor(R.color.white_all));
                                        } else {
                                            childAt.setBackground(context.getResources().getDrawable(R.drawable.white_circle_bg));
                                            childAt.setTextColor(context.getResources().getColor(R.color.address_black_key));
                                        }
                                    }
                                }
                            });
                        } catch (Exception e) {

                        }
                    }
                }

                if (isInitValue) {
                    listStandard = new ArrayList<>();
                    listStandard.clear();
                    if (orderBean.getVAList() != null) {
                        listStandard.addAll(orderBean.getVAList());
                    }
                    //规格
                    final PjStandardAdapter standardAdapter = new PjStandardAdapter(listStandard, context);
                    recyclerView.setAdapter(standardAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

                    standardAdapter.setPositionClick(new OnPositionClick() {
                        @Override
                        public void onPositionClick(int position) {

                            isInitValue = false;
                            vaIds = listStandard.get(position).getVAID();
                            getStandardDataView(context, classId, modeId, standardId, tvName, tvPjPrice, imgPj, layoutView, vaIds, recyclerView);

                            standardAdapter.setPosition(position);
                            standardAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
    }

    //获取规格数据
    public void getStandardData(Context context, String classId, String modeId, String standardId, String vaId, final OnPjDetailCallBack callBack) {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(context, UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_VA_LIST
                + FieldConstant.getInstance().CLASS_ID + "=" + classId + "&"
                + FieldConstant.getInstance().MODE_ID + "=" + modeId + "&"
                + FieldConstant.getInstance().GUI_GE_ID + "=" + standardId + "&"
                + FieldConstant.getInstance().VAID + "=" + vaId, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                PjOrderBean orderBean = JSON.parseObject(json, PjOrderBean.class);
                callBack.onPjDeatilCallBack(orderBean.getReObj());
            }

            @Override
            public void onFail() {

            }
        });
    }

    //添加购物车
    public void addToCar(Context context, String productId, String number) {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(context, UrlConstant.getInstance().URL
                + PostConstant.getInstance().ADD_PJ_CAR
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().PRODUCT_ID + "=" + productId + "&"
                + FieldConstant.getInstance().NUMBER + "=" + number, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    ToastUtils.getMineToast("添加成功");
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                }
            }

            @Override
            public void onFail() {

            }
        });
    }
}
