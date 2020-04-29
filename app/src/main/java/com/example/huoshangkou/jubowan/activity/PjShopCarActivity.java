package com.example.huoshangkou.jubowan.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.PjShopCarAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.PjCarBean;
import com.example.huoshangkou.jubowan.bean.PjCarListBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.OrderTypeConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnAddWorkCallBack;
import com.example.huoshangkou.jubowan.inter.OnPositionClick;
import com.example.huoshangkou.jubowan.inter.OnShopCarAddDecreaseCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.EditDialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：PjShopCarActivity
 * 描述：
 * 创建时间：2017-12-04  13:26
 */

public class PjShopCarActivity extends BaseActivity {
    PjShopCarAdapter carAdapter;
    List<PjCarListBean> list;
    @Bind(R.id.lv_shop_car)
    ListView lvShopCar;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_select_all)
    ImageView imgCheckAll;
    @Bind(R.id.tv_all_price)
    TextView tvAllPrice;

    //是否全选
    private boolean isCheckAll = false;

    @Override
    public int initLayout() {
        return R.layout.activity_pj_car;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().orderList);
        list = new ArrayList<>();
        carAdapter = new PjShopCarAdapter(getContext(), list, R.layout.item_pj_shop_car);
        lvShopCar.setAdapter(carAdapter);
        lvShopCar.setDividerHeight(0);
        getShopCarData();

        //购物车添加、减少点击事件
        carAdapter.setDecreaseCallBack(new OnShopCarAddDecreaseCallBack() {
            //单个点击添加、减少
            @Override
            public void onAddCar(final String type, final String id, final String num, final int groupPosition, final int childPosition) {
                if (list.get(groupPosition).getNumber().equals("1") && type.equals(OrderTypeConstant.getInstance().DEC_CAR)) {
                    CopyIosDialogUtils.getInstance().getIosDialog(getContext(), "是否删除该订单", new CopyIosDialogUtils.iosDialogClick() {
                        @Override
                        public void confimBtn() {
                            addToCar(getContext(), id, "0", groupPosition);
                        }

                        @Override
                        public void cancelBtn() {

                        }
                    });
                    return;
                }
                addToCar(getContext(), id, type, groupPosition);

            }

            //改变数量添加
            @Override
            public void onClickPosition(final String type, final String id, final double toNum, final int groupPosition, final int childPosition) {
                EditDialogUtils.getInstance().showEditTextDialog("num", PjShopCarActivity.this, "输入购买数量", new OnAddWorkCallBack() {
                    @Override
                    public void addWorkExp(final String content) {
                        addToCar(getContext(), id, content, groupPosition);
                    }
                });
            }

            //删除订单
            @Override
            public void deleteOrder(final String type, final int groupPosition, final String id) {
                CopyIosDialogUtils.getInstance().getIosDialog(getContext(), "是否删除该订单", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        addToCar(getContext(), id, "0", groupPosition);
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
            }
        });

        carAdapter.setPositionClick(new OnPositionClick() {
            @Override
            public void onPositionClick(int position) {
                if (list.get(position).isCheck()) {
                    list.get(position).setCheck(false);
                } else {
                    list.get(position).setCheck(true);
                }
                carAdapter.notifyDataSetChanged();

                int num = list.size();
                for (int i = 0; i < num; i++) {
                    if (!list.get(i).isCheck()) {
                        isCheckAll = false;
                        imgCheckAll.setImageResource(R.mipmap.gouxuany_icon_off);
                        calPrice();
                        return;
                    }
                }
                calPrice();
                isCheckAll = true;
                imgCheckAll.setImageResource(R.mipmap.gouxuany_icon_on);
            }
        });

        tvTitle.setText("购物车");



    }

    @OnClick({R.id.ll_back, R.id.ll_select_all, R.id.tv_pay_now})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.ll_select_all:
                if (isCheckAll) {
                    imgCheckAll.setImageResource(R.mipmap.gouxuany_icon_off);
                    isCheckAll = false;
                } else {
                    imgCheckAll.setImageResource(R.mipmap.gouxuany_icon_on);
                    isCheckAll = true;
                }
                int num = list.size();
                for (int i = 0; i < num; i++) {
                    if (isCheckAll) {
                        list.get(i).setCheck(true);
                    } else {
                        list.get(i).setCheck(false);
                    }
                }
                calPrice();
                carAdapter.notifyDataSetChanged();
                break;
            case R.id.tv_pay_now:

                ArrayList<PjCarListBean> listTo = new ArrayList<>();
                int numTo = list.size();
                for (int i = 0; i < numTo; i++) {
                    if (list.get(i).isCheck()) {
                        listTo.add(list.get(i));
                    }
                }
                if (listTo.size() == 0) {
                    ToastUtils.getMineToast("请先选择商品");
                    return;
                }
                Intent intent = new Intent(getContext(), PjOrderActivity.class);
                intent.putParcelableArrayListExtra("listTo", listTo);
                intent.putExtra(IntentUtils.getInstance().TYPE, allPrice + "");
                startActivity(intent);
                break;
        }
    }

    double allPrice = 0;

    public void calPrice() {
        int num = list.size();
        allPrice = 0;
        for (int i = 0; i < num; i++) {
            if (list.get(i).isCheck()) {
                allPrice += (Double.parseDouble(list.get(i).getPrice()) * Double.parseDouble(list.get(i).getNumber()));
            }
        }

        tvAllPrice.setText("￥" + allPrice);
    }

    //添加购物车
    public void addToCar(Context context, String productId, String number, final int position) {
        OkhttpUtil.getInstance().setUnCacheData(context, "加入购物车", UrlConstant.getInstance().URL
                + PostConstant.getInstance().ADD_PJ_CAR
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().PRODUCT_ID + "=" + productId + "&"
                + FieldConstant.getInstance().NUMBER + "=" + number, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                LogUtils.i(json);
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    try {
                        String proCount = new JSONObject(json).getJSONObject("ReObj").getString("ProCount");
                        if (proCount.equals("0")) {
                            getShopCarData();
                        } else {
                            list.get(position).setNumber(proCount);
                            carAdapter.notifyDataSetChanged();
                        }

                        calPrice();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    ToastUtils.getMineToast(successBean.getErrMsg());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    //获取购物车数据
    public void getShopCarData() {
        list.clear();
        OkhttpUtil.getInstance().setUnCacheData(getContext(), "购物车加载中", UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_PJ_CAR_LIST
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                PjCarBean carBean = JSON.parseObject(json, PjCarBean.class);
                list.addAll(carBean.getReList());
                carAdapter.notifyDataSetChanged();

                imgCheckAll.setImageResource(R.mipmap.gouxuany_icon_off);
                isCheckAll = false;
            }

            @Override
            public void onFail() {

            }
        });
    }

}
