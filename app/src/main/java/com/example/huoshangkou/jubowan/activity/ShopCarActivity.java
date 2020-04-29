package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.BuyFunction;
import com.example.huoshangkou.jubowan.activity.function.ShopCarFunction;
import com.example.huoshangkou.jubowan.adapter.ShopAnimAdapter;
import com.example.huoshangkou.jubowan.adapter.ShopCarAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.CarCountBean;
import com.example.huoshangkou.jubowan.bean.ShopCarBean;
import com.example.huoshangkou.jubowan.bean.ShopCarListBean;
import com.example.huoshangkou.jubowan.bean.ShopCarListNewBean;
import com.example.huoshangkou.jubowan.bean.ShopGroupBean;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.inter.NewShopActionCallBack;
import com.example.huoshangkou.jubowan.inter.OnAddWorkCallBack;
import com.example.huoshangkou.jubowan.inter.OnPositionClick;
import com.example.huoshangkou.jubowan.inter.OnShopCarAddDecreaseCallBack;
import com.example.huoshangkou.jubowan.inter.OnShopCarCallBack;
import com.example.huoshangkou.jubowan.inter.OnShopCarChooseCallBack;
import com.example.huoshangkou.jubowan.inter.ShopCarCloseClick;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.EditDialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.utils.TwoPointUtils;
import com.example.huoshangkou.jubowan.view.AnimatedExpandableListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：ShopCarActivity
 * 描述：购物车界面
 * 创建时间：2017-02-20  11:30
 */
public class ShopCarActivity extends BaseActivity {
//    ShopAnimAdapter shopCarAdapter;

    List<ShopGroupBean> list_group;
    List<List<ShopCarListBean>> list_child;

    //    @Bind(R.id.er_shop_car)
//    AnimatedExpandableListView expandableListView;
    @Bind(R.id.lv_shop_car)
    ListView lvCar;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_select_all)
    ImageView ivSeletAll;

    @Bind(R.id.tv_all_price)
    TextView tvAllPrice;

    //悬停部分
    @Bind(R.id.ll_no_data)
    LinearLayout llNoData;
    @Bind(R.id.tv_pay_now)
    TextView tvPayNow;
    @Bind(R.id.rl_bottom)
    RelativeLayout rlBottom;
    private ShopCarAdapter carAdapter;
    //是否滑动到底部
    private boolean isBottom = false;

    //是否全选
    private boolean isSelectAll = false;
    //要删除的id集合
    private String ids = "";
    private String carType = "";
    private List<ShopCarListBean> listCar = new ArrayList<>();

    @Override
    public int initLayout() {
        return R.layout.activity_shop_car;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().orderList);
        carType = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        list_group = new ArrayList<>();
        list_child = new ArrayList<>();

        carAdapter = new ShopCarAdapter(this, listCar, R.layout.shop_car_second);
        lvCar.setAdapter(carAdapter);
        lvCar.setDividerHeight(0);
        //设置标题
        tvTitle.setText("购物车");

//        shopCarAdapter = new ShopAnimAdapter(list_group, list_child, this, new ShopCarCloseClick() {
//            @Override
//            public void onCloseClick(int position, TextView textView) {
//                //如果是打开的就关掉  如果是关掉的就打开
//                if (expandableListView.isGroupExpanded(position)) {
//                    list_group.get(position).setStr(getString(R.string.open));
//                    expandableListView.collapseGroupWithAnimation(position);
//                } else {
//                    list_group.get(position).setStr(getString(R.string.close));
//                    expandableListView.expandGroupWithAnimation(position);
//                }
//                shopCarAdapter.notifyDataSetChanged();
//            }
//        });
//        expandableListView.setAdapter(shopCarAdapter);
//        expandableListView.setDividerHeight(0);
//        int width = getWindowManager().getDefaultDisplay().getWidth();
//        expandableListView.setIndicatorBounds(width, width);
//
//        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//            @Override
//            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
//                return true;
//            }
//        });

        //获取购物车数据
        getShopCarData();


        carAdapter.setChooseCallBack(new OnPositionClick() {
            @Override
            public void onPositionClick(int position) {
                ShopCarListBean carListBean = listCar.get(position);
                if (carListBean.isCheck()) {
                    carListBean.setCheck(false);
                    isSelectAll = false;
                    ivSeletAll.setImageResource(R.mipmap.gouxuany_icon_off);
                } else {
                    carListBean.setCheck(true);
                }
                if (setAllSelect()) {
                    isSelectAll = true;
                    ivSeletAll.setImageResource(R.mipmap.gouxuany_icon_on);
                } else {
                    isSelectAll = false;
                    ivSeletAll.setImageResource(R.mipmap.gouxuany_icon_off);
                }

                tvAllPrice.setText("合计：￥" + ShopCarFunction.getInstance().caculatePrice(listCar, tvPayNow));
                carAdapter.notifyDataSetChanged();
            }
        });


//        carAdapter.setChooseCallBack(new OnShopCarChooseCallBack() {
//            @Override
//            public void onGroupClick(int groupPosition) {
//                shopCarChooseClick(groupPosition);
//            }
//
//            @Override
//            public void onChildClick(int groupPosition, int childPosition) {
//                List<ShopCarListBean> listChild = list_child.get(groupPosition);
//                if (listChild.get(childPosition).isCheck()) {
//                    list_group.get(groupPosition).setCheck(false);
//                    listChild.get(childPosition).setCheck(false);
//                    isSelectAll = false;
//                    ivSeletAll.setImageResource(R.mipmap.gouxuany_icon_off);
//                } else {
//                    listChild.get(childPosition).setCheck(true);
//                    if (isCheckAll(listChild)) {
//                        list_group.get(groupPosition).setCheck(true);
//                    }
//                }
//                if (setAllSelect()) {
//                    isSelectAll = true;
//                    ivSeletAll.setImageResource(R.mipmap.gouxuany_icon_on);
//                } else {
//                    isSelectAll = false;
//                    ivSeletAll.setImageResource(R.mipmap.gouxuany_icon_off);
//                }
//
//                tvAllPrice.setText("￥" + ShopCarFunction.getInstance().caculatePrice(list_child, tvPayNow));
//                shopCarAdapter.notifyDataSetChanged();
//            }
//        });

        //购物车添加、减少点击事件
        carAdapter.setDecreaseCallBack(new NewShopActionCallBack() {
            //单个点击添加
            @Override
            public void onAddCar(String type, String id, final String num, final int position) {
                BuyFunction.getInstance().addToYuanCar(type, ShopCarActivity.this, id, num, new StringCallBack() {
                    @Override
                    public void onSuccess(String carCount) {
                        CarCountBean successBean = JSON.parseObject(carCount, CarCountBean.class);
                        if (successBean.getSuccess() == 1) {
                            listCar.get(position).setToNum(successBean.getReObj().getProCount());
                            carAdapter.notifyDataSetChanged();
                            tvAllPrice.setText("合计：￥" + ShopCarFunction.getInstance().caculatePrice(listCar, tvPayNow));
                            if (StringUtils.isNoEmpty(successBean.getErrMsg())) {
//                                ToastUtils.getMineToast(successBean.getErrMsg());
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

            //改变数量添加
            @Override
            public void onClickPosition(final String type, final String id, final double toNum, final int position) {
                EditDialogUtils.getInstance().showEditTextDialog("num", ShopCarActivity.this, "输入购买数量", new OnAddWorkCallBack() {
                    @Override
                    public void addWorkExp(final String content) {
                        BuyFunction.getInstance().addToYuanCar(type, ShopCarActivity.this, id, content, new StringCallBack() {
                            @Override
                            public void onSuccess(String carCount) {
                                CarCountBean successBean = JSON.parseObject(carCount, CarCountBean.class);
                                if (successBean.getSuccess() == 1) {
                                    listCar.get(position).setToNum(successBean.getReObj().getProCount());
                                    carAdapter.notifyDataSetChanged();
                                    tvAllPrice.setText("合计：￥" + ShopCarFunction.getInstance().caculatePrice(listCar, tvPayNow));
                                } else {
                                    ToastUtils.getMineToast(successBean.getErrMsg());
                                }
                            }

                            @Override
                            public void onFail() {

                            }
                        });
                    }
                });
            }

            //删除订单
            @Override
            public void deleteOrder(int groupPosition) {
                deleteShopOrder(carType, groupPosition);
            }
        });
    }

    //获取购物车数据
    public void getShopCarData() {
        //获取购物车数据
        ShopCarFunction.getInstance().getShopCarData(this, carType, new OnShopCarCallBack() {
            @Override
            public void onSuccess(ShopCarListNewBean shopCarBean) {
                listCar.clear();
                List<ShopCarListBean> reList = shopCarBean.getReList();
                listCar.addAll(reList);
                if (listCar.size() == 0) {
                    llNoData.setVisibility(View.VISIBLE);
                    rlBottom.setVisibility(View.GONE);
                } else {
                    llNoData.setVisibility(View.GONE);
                    rlBottom.setVisibility(View.VISIBLE);
                }
                if (setAllSelect()) {
                    isSelectAll = true;
                    ivSeletAll.setImageResource(R.mipmap.gouxuany_icon_on);
                } else {
                    isSelectAll = false;
                    ivSeletAll.setImageResource(R.mipmap.gouxuany_icon_off);
                }
                carAdapter.notifyDataSetChanged();
//                List<ShopCarListBean> ypList = shopCarBean.getReObj().getYpList();
//                int num1 = ypList.size();
//                for (int i = 0; i < num1; i++) {
//                    ypList.get(i).setType(ShopCarFunction.getInstance().YUAN);
//                }
//
//                List<ShopCarListBean> flList = shopCarBean.getReObj().getFlList();
//                int num2 = flList.size();
//                for (int i = 0; i < num2; i++) {
//                    flList.get(i).setType(ShopCarFunction.getInstance().FU);
//                }
//
//                List<ShopCarListBean> ylList = shopCarBean.getReObj().getYlList();
//                if(ylList!=null){
//                    int num3 = ylList.size();
//                    for (int i = 0; i < num3; i++) {
//                        ylList.get(i).setType(ShopCarFunction.getInstance().YUAN_LIAO);
//                    }
//                }
//
//
//                ShopGroupBean groupBean = new ShopGroupBean();
//                groupBean.setStr(getString(R.string.close));
//                groupBean.setTitle("原片购物车 (" + ypList.size() + ")");
//                groupBean.setType(ShopCarFunction.getInstance().YUAN);
//
//                ShopGroupBean groupBeanFu = new ShopGroupBean();
//                groupBeanFu.setStr(getString(R.string.close));
//                groupBeanFu.setTitle("辅材购物车 (" + flList.size() + ")");
//                groupBeanFu.setType(ShopCarFunction.getInstance().FU);
//
//                ShopGroupBean groupBeanYl = new ShopGroupBean();
//                groupBeanYl.setStr(getString(R.string.close));
//                if(ylList!=null){
//                    groupBeanYl.setTitle("原料购物车 (" + ylList.size() + ")");
//                }
//                groupBeanYl.setType(ShopCarFunction.getInstance().YUAN_LIAO);
//
//                if (ypList != null && ypList.size() != 0) {
//                    list_group.add(groupBean);
//                    list_child.add(ypList);
//                }
//                if (flList != null && flList.size() != 0) {
//                    list_group.add(groupBeanFu);
//                    list_child.add(flList);
//                }
//                if (ylList != null && ylList.size() != 0) {
//                    list_group.add(groupBeanYl);
//                    list_child.add(ylList);
//                }
//
//                if (list_group.size() == 0) {
//                    llNoData.setVisibility(View.VISIBLE);
//                    rlBottom.setVisibility(View.GONE);
//                } else {
//                    llNoData.setVisibility(View.GONE);
//                    rlBottom.setVisibility(View.VISIBLE);
//                }
//
//
//                if (setAllSelect()) {
//                    isSelectAll = true;
//                    ivSeletAll.setImageResource(R.mipmap.gouxuany_icon_on);
//                } else {
//                    isSelectAll = false;
//                    ivSeletAll.setImageResource(R.mipmap.gouxuany_icon_off);
//                }
//
//                shopCarAdapter.notifyDataSetChanged();
//
//
//                //全部展开
//                for (int i = 0; i < shopCarAdapter.getGroupCount(); i++) {
//                    expandableListView.expandGroup(i);
//                }
            }

            @Override
            public void onFail() {
                llNoData.setVisibility(View.VISIBLE);
            }
        });
    }

    //判读是否所有的都被选中
    public boolean isCheckAll(List<ShopCarListBean> listChild) {
        int num2 = listChild.size();
        for (int i = 0; i < num2; i++) {
            if (!listChild.get(i).isCheck()) {
                return false;
            }
        }
        return true;
    }

    //全选或者全取消
    public void setAllOrCancel(boolean select) {
//        int num1 = list_group.size();
//        for (int i = 0; i < num1; i++) {
//            list_group.get(i).setCheck(select);
//        }
//
//        int num2 = list_child.size();
//        for (int i = 0; i < num2; i++) {
//            int num3 = list_child.get(i).size();
//            for (int j = 0; j < num3; j++) {
//                list_child.get(i).get(j).setCheck(select);
//            }
//        }

        int num = listCar.size();
        for (int i = 0; i < num; i++) {
            listCar.get(i).setCheck(select);
        }
        carAdapter.notifyDataSetChanged();
    }

    //判断所有的是否被选上
    public boolean setAllSelect() {
//        int num1 = list_group.size();
//        for (int i = 0; i < num1; i++) {
//            if (!list_group.get(i).isCheck()) {
//                return false;
//            }
//        }
//
//        int num2 = list_child.size();
//        for (int i = 0; i < num2; i++) {
//            int num3 = list_child.get(i).size();
//            for (int j = 0; j < num3; j++) {
//                if (!list_child.get(i).get(j).isCheck()) {
//                    return false;
//                }
//            }
//        }
        int num = listCar.size();
        for (int i = 0; i < num; i++) {
            if (!listCar.get(i).isCheck()) {
                return false;
            }
        }
        return true;
    }

    //判断是否有订单被选上
    public boolean setOrderSelect() {
        int num1 = listCar.size();
        for (int i = 0; i < num1; i++) {
            if (listCar.get(i).isCheck()) {
                return true;
            }
        }

//        int num2 = list_child.size();
//        for (int i = 0; i < num2; i++) {
//            int num3 = list_child.get(i).size();
//            for (int j = 0; j < num3; j++) {
//                if (list_child.get(i).get(j).isCheck()) {
//                    return true;
//                }
//            }
//        }
        return false;
    }

    //点击事件
    @OnClick({R.id.ll_back, R.id.tv_pay_now, R.id.ll_select_all,})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_pay_now:
                //如果没有被选中的 不进行下单
                if (setOrderSelect()) {
                    int num = listCar.size();
                    ArrayList<ShopCarListBean> listYuan = new ArrayList<>();
//                    ArrayList<ShopCarListBean> listFu = new ArrayList<>();
//                    ArrayList<ShopCarListBean> listYl = new ArrayList<>();
//                    for (int i = 0; i < num; i++) {
//                        List<ShopCarListBean> shopCarListBeen = list_child.get(i);
//                        int num2 = shopCarListBeen.size();
                        for (int i = 0; i < num; i++) {
                            if ( listCar.get(i).isCheck()) {
                                listYuan.add(listCar.get(i));
                            }
//                            else if (listCar.get(i).getType().equals(ShopCarFunction.getInstance().FU) && listCar.get(i).isCheck()) {
//                                listFu.add(listCar.get(i));
//                            } else if (listCar.get(i).getType().equals(ShopCarFunction.getInstance().YUAN_LIAO) && listCar.get(i).isCheck()) {
//                                listYl.add(listCar.get(i));
//                            }
                        }
//                    }

                    Intent intent = new Intent(this, ShopOrderCommitActivity.class);
                    intent.putExtra("yuanList", listYuan);
//                    intent.putExtra("fuList", listFu);
//                    intent.putExtra("ylList", listYl);
                    startActivity(intent);
                } else {
                    ToastUtils.getMineToast("请选择下单产品");
                }
                break;
            //全选
            case R.id.ll_select_all:
                if (isSelectAll) {
                    isSelectAll = false;
                    ivSeletAll.setImageResource(R.mipmap.gouxuany_icon_off);
                    setAllOrCancel(false);
                } else {
                    isSelectAll = true;
                    ivSeletAll.setImageResource(R.mipmap.gouxuany_icon_on);
                    setAllOrCancel(true);
                }

                tvAllPrice.setText("合计：￥" + ShopCarFunction.getInstance().caculatePrice(listCar, tvPayNow));
                break;
        }
    }


    //删除订单
    private void deleteShopOrder(final String type, int groupPosition) {
        ids = "";
//        List<ShopCarListBean> shopCarListBeen = list_child.get(groupPosition);
//        int num = shopCarListBeen.size();
//
//        for (int i = 0; i < num; i++) {
//            if (shopCarListBeen.get(i).isCheck()) {
//                ids += shopCarListBeen.get(i).getID() + ",";
//            }
//        }
        ids = listCar.get(groupPosition).getID();
        if (!StringUtils.isNoEmpty(ids)) {
            ToastUtils.getMineToast("请选择删除的订单");
            return;
        }

        CopyIosDialogUtils.getInstance().getIosDialog(ShopCarActivity.this, getString(R.string.is_delete_order), new CopyIosDialogUtils.iosDialogClick() {
            @Override
            public void confimBtn() {
                BuyFunction.getInstance().addToYuanCar(type, ShopCarActivity.this, ids, "0", new StringCallBack() {
                    @Override
                    public void onSuccess(String carCount) {
                        ToastUtils.getMineToast(getString(R.string.delete_shop_success));
                        getShopCarData();
                        SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().INIT_BUY, "yes");
                    }

                    @Override
                    public void onFail() {
                        ToastUtils.getMineToast(getString(R.string.delete_shop_fail));
                    }
                });

            }

            @Override
            public void cancelBtn() {

            }
        });
    }

    //购物车选择点击
    public void shopCarChooseClick(int groupPosition) {
        if (list_group.get(groupPosition).isCheck()) {
            list_group.get(groupPosition).setCheck(false);
            List<ShopCarListBean> listChild = list_child.get(groupPosition);
            isSelectAll = false;
            ivSeletAll.setImageResource(R.mipmap.gouxuany_icon_off);
            int num = listChild.size();
            for (int i = 0; i < num; i++) {
                listChild.get(i).setCheck(false);
            }
        } else {
            list_group.get(groupPosition).setCheck(true);
            List<ShopCarListBean> listChild = list_child.get(groupPosition);
            int num = listChild.size();
            for (int i = 0; i < num; i++) {
                listChild.get(i).setCheck(true);
            }
        }
        if (setAllSelect()) {
            isSelectAll = true;
            ivSeletAll.setImageResource(R.mipmap.gouxuany_icon_on);
        }
        tvAllPrice.setText("合计：￥" + TwoPointUtils.getInstance().getTwoPoint(Double.parseDouble(ShopCarFunction.getInstance().caculatePrice(listCar, tvPayNow))));

        carAdapter.notifyDataSetChanged();
    }
}
