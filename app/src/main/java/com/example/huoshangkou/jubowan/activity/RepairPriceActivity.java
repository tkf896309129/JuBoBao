package com.example.huoshangkou.jubowan.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.OrderDetailBean;
import com.example.huoshangkou.jubowan.bean.OrderDetailObjBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.ChooseDialogCallBack;
import com.example.huoshangkou.jubowan.inter.OnAddWorkCallBack;
import com.example.huoshangkou.jubowan.inter.OnRepairPriceCallBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.EditDialogUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.PickDialogUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：RepairPriceActivity
 * 描述：维修预付款界面
 * 创建时间：2017-02-27  13:46
 */

public class RepairPriceActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;

    //交通工具
    ArrayList<String> transList;
    @Bind(R.id.tv_days)
    TextView tvDays;
    @Bind(R.id.tv_all_hour_days)
    TextView tvAllHourDays;
    @Bind(R.id.tv_trans_type)
    TextView tvTransType;
    @Bind(R.id.tv_back_price)
    TextView tvBackPrice;

    @Bind(R.id.tv_food_all_price)
    TextView tvFoodAllPrice;
    @Bind(R.id.tv_other_price)
    TextView tvOtherPrice;
    @Bind(R.id.tv_all_price)
    TextView tvAllPrice;
    @Bind(R.id.tv_price_unit)
    TextView tvPriceUnit;

    //食宿费用
    @Bind(R.id.rg_food_price)
    RadioGroup rgFoodPrice;
    @Bind(R.id.rb_provide)
    RadioButton rbProvide;
    @Bind(R.id.rb_no_provide)
    RadioButton rbNoProvide;

    private String days = "0";
    private String trans = "";
    private String hourPrice = "0";
    private String backPrice = "0";
    private String foodPrice = "0";
    private int dayFoodPrice = 0;
    private String otherPrice = "0";
    private String allPrice;

    private int dayPrice = 500;

    private String orderId = "";

    //获取维修清单连接
    private String url = "";
    //是否不可以编辑
    private boolean isNoEdit = false;

    private int position = -1;
    //订单状态
    private String orderState = "";

    //是否是首单  1是首单  0非首单
    private int isFirst = 0;

    @Override
    public int initLayout() {
        return R.layout.activity_repair_price;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("维修预付款");
        tvRight.setText("提交");


        url = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        orderId = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        position = getIntent().getIntExtra(IntentUtils.getInstance().POSITION, -1);
        orderState = getIntent().getStringExtra(IntentUtils.getInstance().STATE);

        String isEdit = getIntent().getStringExtra(IntentUtils.getInstance().IS_EDIT);
        if (StringUtils.isNoEmpty(isEdit)) {
            isNoEdit = true;
        }

        if (isNoEdit) {
            tvRight.setText("");
        }


        tvPriceUnit.setText(dayPrice + "元/天");

        transList = new ArrayList<>();
        transList.add("火车");
        transList.add("汽车");
        transList.add("飞机");
        transList.add("其他");

        //食宿费用
        rgFoodPrice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    //提供食宿费
                    case R.id.rb_provide:
                        dayFoodPrice = 0;
                        getPriceData();
                        break;
                    //不提供食宿费
                    case R.id.rb_no_provide:
                        dayFoodPrice = 200;
                        getPriceData();
                        break;
                }
            }
        });

        //获取维修清单
        getRepairPrice();
    }

    @OnClick({R.id.ll_back, R.id.tv_right, R.id.rl_days, R.id.rl_price_day,
            R.id.rl_all_hour_price, R.id.rl_trans, R.id.rl_back_price, R.id.rl_food_price, R.id.rl_other_price, R.id.rl_all_price})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_right:

                if (Double.parseDouble(days) <= 0) {
                    ToastUtils.getMineToast("请输入预计维修天数");
                    return;
                }

                if (!StringUtils.isNoEmpty(trans)) {
                    ToastUtils.getMineToast("请选择交通工具");
                    return;
                }

                if (!StringUtils.isNoEmpty(backPrice)) {
                    ToastUtils.getMineToast("请输入往来费用");
                    return;
                }

                CopyIosDialogUtils.getInstance().getIosDialog(getContext(), "是否提交维修预付款", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        commitRepairPrice(getContext(), orderId, days, trans, backPrice, foodPrice, otherPrice, allPrice);
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
                break;
            //预计维修天数
            case R.id.rl_days:
                if (isNoEdit) {
                    return;
                }
                getEditDialog("请输入维修天数", tvDays, "天", new OnRepairPriceCallBack() {
                    @Override
                    public void onGetPrice(String content) {
                        days = content;
                        getPriceData();
                    }
                });
                break;
            //超时工时费
            case R.id.rl_price_day:
                break;
            //工时费小计
            case R.id.rl_all_hour_price:
                break;
            //交通工具
            case R.id.rl_trans:
                if (isNoEdit) {
                    return;
                }
                PickDialogUtils.getInstance().getChooseDialog(getContext(), "请选择交通工具", transList, new ChooseDialogCallBack() {
                    @Override
                    public void onClickSuccess(String choose) {
                        trans = choose;
                        tvTransType.setText(choose);
                    }
                });
                break;
            //往来费用
            case R.id.rl_back_price:
                if (isNoEdit) {
                    return;
                }
                getEditDialog("请输入往来费用", tvBackPrice, "元", new OnRepairPriceCallBack() {
                    @Override
                    public void onGetPrice(String content) {
                        backPrice = content;
                        getPriceData();
                    }
                });
                break;
            //其他费用
            case R.id.rl_other_price:
                if (isNoEdit) {
                    return;
                }
                getEditDialog("请输入其他费用", tvOtherPrice, "元", new OnRepairPriceCallBack() {
                    @Override
                    public void onGetPrice(String content) {
                        otherPrice = content;
                        getPriceData();
                    }
                });
                break;
            //合计费用
            case R.id.rl_all_price:
                break;
        }
    }


    private void getEditDialog(String title, final TextView textView, final String unit, final OnRepairPriceCallBack callBack) {
        EditDialogUtils.getInstance().showEditTextDialog("num", getContext(), title, new OnAddWorkCallBack() {
            @Override
            public void addWorkExp(String content) {
                textView.setText(content + unit);
                callBack.onGetPrice(content);
            }
        });
    }


    /**
     * 计算工时费小计和食宿费用和总费用
     */
    public void getPriceData() {
        //工时费小计

        //首单
        if (isFirst == 1) {
            if (Double.parseDouble(days) > 1) {
                hourPrice = ((Double.parseDouble(days) - 1) * dayPrice) + "";
            } else {
                hourPrice = "0";
            }
            //非首单
        } else if (isFirst == 0) {
            hourPrice = ((Double.parseDouble(days)) * dayPrice) + "";
        }


        tvAllHourDays.setText(hourPrice + "元");

        //食宿费用
        if (dayFoodPrice > 0) {
            foodPrice = (Double.parseDouble(days) * dayFoodPrice) + "";
        } else {
            foodPrice = "0";
        }
        tvFoodAllPrice.setText(foodPrice + "元");

        //总费用
        allPrice = (Double.parseDouble(hourPrice) + Double.parseDouble(foodPrice) + Double.parseDouble(backPrice) + Double.parseDouble(otherPrice)) + "";
        if (allPrice.substring(allPrice.indexOf("."), allPrice.length()).length() > 3) {
            allPrice = allPrice.substring(0, allPrice.indexOf(".")) + allPrice.substring(allPrice.indexOf("."), allPrice.indexOf(".") + 3);
        }
        tvAllPrice.setText(allPrice + "元");
    }

    /**
     * 提交维修费用
     */
    public void commitRepairPrice(Context context, String id, String days, String trans,
                                  String transPrice, String foodPrice, String otherPrice, String totalPrice) {
        OkhttpUtil.getInstance().setUnCacheData(context, context.getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().ADD_MAINTAIN_PRICE + FieldConstant.getInstance().ORDER_ID + "=" + id + "&"
                + FieldConstant.getInstance().REPAIR_DAYS + "=" + days + "&"
                + FieldConstant.getInstance().TRANS_TYPE + "=" + EncodeUtils.getInstance().getEncodeString(trans) + "&"
                + FieldConstant.getInstance().TIES_PRICE + "=" + transPrice + "&"
                + FieldConstant.getInstance().FOOD_PRICE + "=" + foodPrice + "&"
                + FieldConstant.getInstance().OTHER_PRICE + "=" + otherPrice + "&"
                + FieldConstant.getInstance().TOTAL_PRICE + "=" + totalPrice, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    ToastUtils.getMineToast("提交成功");

                    Intent intent = new Intent();
                    intent.putExtra(IntentUtils.getInstance().TYPE, allPrice);
                    intent.putExtra(IntentUtils.getInstance().STATE, orderState);
                    intent.putExtra(IntentUtils.getInstance().POSITION, position);
                    setResult(101, intent);
                    RepairPriceActivity.this.finish();
                } else {
                    ToastUtils.getMineToast("提交失败");
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    //获取维修清单
    public void getRepairPrice() {
        if (!StringUtils.isNoEmpty(url)) {
            return;
        }
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), url, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                OrderDetailBean detailBean = JSON.parseObject(json, OrderDetailBean.class);
                if (detailBean.getReList() == null || detailBean.getReList().size() < 1) {
                    return;
                }
                OrderDetailObjBean orderDetailObjBean = detailBean.getReList().get(0);
                days = orderDetailObjBean.getMaintainDays() + "";
                tvDays.setText(days + "天");
                //首单
//                isFirst = orderDetailObjBean.getIsFirst();

                trans = orderDetailObjBean.getTiansportation();
                tvTransType.setText(trans);
                backPrice = orderDetailObjBean.getTiesPrice() + "";
                tvBackPrice.setText(backPrice + "元");
                foodPrice = orderDetailObjBean.getAccommodationPrice() + "";
                if (Double.parseDouble(foodPrice) > 0) {
                    rbNoProvide.setChecked(true);
                } else {
                    rbProvide.setChecked(true);
                }
                tvFoodAllPrice.setText(foodPrice + "元");
                otherPrice = orderDetailObjBean.getOtherPrice() + "";
                tvOtherPrice.setText(otherPrice + "元");
                allPrice = orderDetailObjBean.getTotalPrice() + "";
                tvAllPrice.setText(allPrice + "元");

                getPriceData();
            }

            @Override
            public void onFail() {

            }
        });
    }


}
