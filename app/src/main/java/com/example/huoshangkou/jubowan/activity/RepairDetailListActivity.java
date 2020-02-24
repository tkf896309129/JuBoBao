package com.example.huoshangkou.jubowan.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.OrderDetailBean;
import com.example.huoshangkou.jubowan.bean.OrderDetailObjBean;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：RepairDetailListActivity
 * 描述：
 * 创建时间：2017-08-18  14:06
 */

public class RepairDetailListActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_repair_days)
    TextView tvRepairDays;
    @Bind(R.id.tv_extra_hour_price)
    TextView tvExtraHourPrice;
    @Bind(R.id.tv_hour_price)
    TextView tvHourPrice;
    @Bind(R.id.tv_trans_price)
    TextView tvTransPrice;
    @Bind(R.id.tv_start_price)
    TextView tvStartPrice;
    @Bind(R.id.tv_is_provide_food)
    TextView tvIsProvideFood;
    @Bind(R.id.tv_food_price)
    TextView tvFoodPrice;
    @Bind(R.id.tv_other_price)
    TextView tvOtherPrice;
    @Bind(R.id.tv_all_price)
    TextView tvAllPrice;

    private String url = "";
    private String isServiceList = "";
    //HMS
    @Override
    public int initLayout() {
        return R.layout.activity_repair_list;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        url = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        isServiceList = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        getRepairPrice();

        tvTitle.setText("维修清单");
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
                tvRepairDays.setText(orderDetailObjBean.getMaintainDays() + "天");

                tvExtraHourPrice.setText("500元/天");
                tvHourPrice.setText(Double.parseDouble(getHourPrice(orderDetailObjBean.getIsFirst(), orderDetailObjBean.getMaintainDays() + "")) + "元");
                tvTransPrice.setText(orderDetailObjBean.getTiansportation());
                tvStartPrice.setText(orderDetailObjBean.getTiesPrice() + "元");

                if (Double.parseDouble(orderDetailObjBean.getAccommodationPrice() + "") > 0) {
                    tvIsProvideFood.setText("提供");
                } else {
                    tvIsProvideFood.setText("不提供");
                }

                tvFoodPrice.setText(orderDetailObjBean.getAccommodationPrice() + "元");
                tvOtherPrice.setText(orderDetailObjBean.getOtherPrice() + "元");

//                double totalPrice = Double.parseDouble(getHourPrice(orderDetailObjBean.getIsFirst(), orderDetailObjBean.getMaintainDays() + "")) + orderDetailObjBean.getTotalPrice();
//                tvAllPrice.setText(totalPrice + "元");
                tvAllPrice.setText(orderDetailObjBean.getTotalPrice() + "元");
            }

            @Override
            public void onFail() {

            }
        });
    }

    //计算工时费
    private String getHourPrice(int isFirst, String days) {
        String hourPrice = "";
        //首单
        if (isFirst == 1) {
            if (Double.parseDouble(days) > 1) {
                hourPrice = ((Double.parseDouble(days) - 1) * 500) + "";
            } else {
                hourPrice = "0";
            }
            //非首单
        } else if (isFirst == 0) {
            hourPrice = ((Double.parseDouble(days)) * 500) + "";
        }
        return hourPrice;
    }

    @OnClick({R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
        }
    }

}
