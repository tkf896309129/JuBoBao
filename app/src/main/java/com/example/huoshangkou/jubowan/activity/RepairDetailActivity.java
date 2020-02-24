package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.OrderCommitFunction;
import com.example.huoshangkou.jubowan.adapter.LanImageShowAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.OrderListTypeBean;
import com.example.huoshangkou.jubowan.bean.OrderListsBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.OrderTypeConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.SuccessCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.CommonStarUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.PhoneUtils;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollRecylerView;
import com.hyphenate.util.ImageUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：RepairDetailActivity
 * 描述：维修维保订单详情界面
 * 创建时间：2017-02-27  13:33
 */

public class RepairDetailActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_order_num)
    TextView tvOrderNum;
    @Bind(R.id.tv_order_time)
    TextView tvOrderTime;
    @Bind(R.id.iv_pic)
    ImageView ivPic;
    @Bind(R.id.tv_type)
    TextView tvType;
    @Bind(R.id.tv_brand)
    TextView tvBrand;
    @Bind(R.id.tv_buy_time)
    TextView tvBuyTime;
    @Bind(R.id.ll_brand)
    LinearLayout llBrand;
    @Bind(R.id.tv_intro)
    TextView tvIntro;

    @Bind(R.id.tv_receive_man)
    TextView tvReceiveMan;
    @Bind(R.id.tv_link_phone)
    TextView tvLinkPhone;
    @Bind(R.id.tv_receive_address)
    TextView tvReceiveAddress;

    private String url = "";

    @Bind(R.id.ll_all_common)
    LinearLayout llAll;
    @Bind(R.id.ll_service_attitude)
    LinearLayout llService;
    @Bind(R.id.ll_common_trans)
    LinearLayout llTrans;

    @Bind(R.id.ll_all)
    LinearLayout llAllOut;
    @Bind(R.id.ll_service)
    LinearLayout llServiceOut;
    @Bind(R.id.ll_trans)
    LinearLayout llTransOut;

    @Bind(R.id.tv_repair_price)
    TextView tvRepairPrice;

    @Bind(R.id.tv_delete)
    TextView tvDelete;
    @Bind(R.id.tv_pay)
    TextView tvPay;
    @Bind(R.id.tv_pay_list)
    TextView tvPayList;
    @Bind(R.id.tv_common)
    TextView tvCommon;
    @Bind(R.id.tv_link_kf)
    TextView tvLinkKf;

    @Bind(R.id.recyler_gon_yi)
    RecyclerView recylerView;
    LanImageShowAdapter lanImageShowAdapter;
    @Bind(R.id.tv_state)
    TextView tvPayType;
    @Bind(R.id.tv_check_serve_list)
    TextView tvCheckList;
    @Bind(R.id.tv_write_list)
    TextView tvWriteList;

    //附件
    List<String> imgList;

    OrderListsBean listsBean;
    private String orderId = "";
    private String repairPrice = "";

    private int position = -1;

    //维修预付款详情
    public final int REPAIR_PRICE = 1;
    //评论
    public final int COMMON = 2;

    private String isServiceList = "";

    @Override
    public int initLayout() {
        return R.layout.activity_repair_detail;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().orderList);
        tvTitle.setText("订单详情");
        listsBean = (OrderListsBean) getIntent().getSerializableExtra(IntentUtils.getInstance().BEAN_TYPE);
        isServiceList = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);

        position = getIntent().getIntExtra(IntentUtils.getInstance().POSITION, -1);

        imgList = new ArrayList<>();

        lanImageShowAdapter = new LanImageShowAdapter(imgList, getContext(), "no_delete");
        recylerView.setAdapter(lanImageShowAdapter);
        recylerView.setLayoutManager(getLayoutManager());

        getRepairDetail(listsBean);
    }

    @OnClick({R.id.ll_back, R.id.tv_pay_list, R.id.tv_pay, R.id.tv_delete,
            R.id.tv_link_kf, R.id.tv_common, R.id.tv_check_serve_list, R.id.tv_write_list})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                setBackData();
                break;
            case R.id.tv_pay_list:
                if (listsBean == null) {
                    ToastUtils.getMineToast("获取订单失败");
                    return;
                }
                if (StringUtils.isNoEmpty(isServiceList)) {
                    String urlRepair = UrlConstant.getInstance().URL + PostConstant.getInstance().SHOW_MAINTAIN_PRICE
                            + FieldConstant.getInstance().USER_ID + "=" + listsBean.getUserID() + "&"
                            + FieldConstant.getInstance().ORDER_ID + "=" + orderId;
                    IntentUtils.getInstance().toActivity(this, RepairDetailListActivity.class, urlRepair);
                } else {
                    String urlRepair = UrlConstant.getInstance().URL + PostConstant.getInstance().SHOW_MAINTAIN_PRICE
                            + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                            + FieldConstant.getInstance().ORDER_ID + "=" + orderId;

                    Intent intent = new Intent(this, RepairPriceActivity.class);
                    intent.putExtra(IntentUtils.getInstance().TYPE, urlRepair);
                    intent.putExtra(IntentUtils.getInstance().VALUE, listsBean.getOrderID());
                    startActivityForResult(intent, REPAIR_PRICE);
                }
                break;
            case R.id.tv_pay:
                if (listsBean == null) {
                    return;
                }
                if (listsBean.getPayWay() == 2 || listsBean.getPayWay() == 4) {
                    ToastUtils.getMineToast("线下订单订单审核中");
                    return;
                }

                //维修维保
                if (listsBean.getOrderListw() != null || listsBean.getOrderListwb() != null) {
                    String price = "";
                    String orderId = "";
                    double totalPrice = Double.parseDouble(listsBean.getTotalPrice());
                    double yfPrice = Double.parseDouble(listsBean.getYuFuPrice());
                    double syPrice = 0.0;
                    if (StringUtils.isNoEmpty(listsBean.getShengYuPrice())) {
                        syPrice = Double.parseDouble(listsBean.getShengYuPrice());
                    }

                    //没有支付
                    if (syPrice == totalPrice) {
                        price = yfPrice + "";
                        orderId = listsBean.getOrderID() + "WD";
                        //支付过一次
                    } else if (syPrice > 0 && syPrice < totalPrice) {
                        price = syPrice + "";
                        orderId = listsBean.getOrderID() + "WS";
                        //支付完成 但是有出现在待支付状态下
                    } else if (syPrice <= 0) {
                        price = totalPrice + "";
                        orderId = listsBean.getOrderID();
                    }

                    IntentUtils.getInstance().toRepairPayActivity(getContext(), price, orderId, "维系维保支付", "维系维保支付", "维系维保支付", listsBean);
                    //配件
                } else if (listsBean.getOrderListp() != null) {
                    IntentUtils.getInstance().toPayActivity(getContext(), listsBean.getTotalPrice(), listsBean.getOrderID(), "配件支付", "配件支付", "配件支付", OrderTypeConstant.getInstance().PJ, listsBean);
                    //其他
                } else {
                    IntentUtils.getInstance().toPayActivity(getContext(), listsBean.getTotalPrice(), listsBean.getOrderID(), listsBean);
                }
                break;
            //删除
            case R.id.tv_delete:
                if (listsBean == null) {
                    return;
                }
                CopyIosDialogUtils.getInstance().getIosDialog(getContext(), "是否删除订单：" + listsBean.getOrderID(), new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        OrderCommitFunction.getInstance().deleteOrder(listsBean.getOrderID(), getContext(), new SuccessCallBack() {
                            @Override
                            public void onSuccess() {
                                ToastUtils.getMineToast("删除成功");
                                //刷新数据
                                Intent intent1 = new Intent();
                                intent1.putExtra(IntentUtils.getInstance().POSITION, position);
                                intent1.putExtra(IntentUtils.getInstance().TYPE, "delete");
                                setResult(103, intent1);
                                RepairDetailActivity.this.finish();
                            }

                            @Override
                            public void onFail() {
                                ToastUtils.getMineToast("删除失败");
                            }
                        });
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
                break;
            case R.id.tv_link_kf:
                PhoneUtils.getInstance().callPhoe(getContext());
                break;
            case R.id.tv_common:
                if (listsBean == null) {
                    return;
                }
                IntentUtils.getInstance().toActivity(getContext(), CommonActivity.class, listsBean.getOrderID(), OrderTypeConstant.getInstance().REPAIR_ORDER);
                break;
            case R.id.tv_check_serve_list:
                IntentUtils.getInstance().toActivity(getContext(), CheckServerListActivity.class, listsBean.getServeID() + "");
                break;
            case R.id.tv_write_list:
                IntentUtils.getInstance().toActivity(getContext(), WriteListActivity.class, listsBean.getOrderID());
                break;
        }
    }

    public void getRepairDetail(OrderListsBean reObj) {
        if (reObj == null) {
            return;
        }
        orderId = reObj.getOrderID();
        tvOrderNum.setText(orderId);
        tvOrderTime.setText(reObj.getCreateTime());

        OrderListTypeBean orderListTypeBean = null;
        if (reObj.getOrderListw() != null) {
            orderListTypeBean = reObj.getOrderListw().get(0);
        }
        if (reObj.getOrderListwb() != null) {
            orderListTypeBean = reObj.getOrderListwb().get(0);
        }

        GlideUtils.getInstance().displayImage(orderListTypeBean.getPic(), getContext(), ivPic);
        if (StringUtils.isNoEmpty(orderListTypeBean.getGuiGe())) {
            tvType.setText("设备升级：" + orderListTypeBean.getMaintainName());
        } else {
            tvType.setText("故障类型：" + orderListTypeBean.getMaintainName());
        }

        tvBrand.setText("机械品牌：" + orderListTypeBean.getBrandName());
        if (StringUtils.isNoEmpty(reObj.getAddtime())) {
            tvBuyTime.setText("购买时间：" + DateUtils.getFormData(reObj.getAddtime()));
        }
        tvLinkPhone.setText(reObj.getLinkTel());
        tvReceiveMan.setText(reObj.getLinkMan());
        tvReceiveAddress.setText(reObj.getProvinces());

        if (StringUtils.isNoEmpty(orderListTypeBean.getGuiGe())) {
            tvIntro.setText("设备升级：" + orderListTypeBean.getDescript());
        } else {
            tvIntro.setText("故障描述：" + orderListTypeBean.getDescript());
        }

        if (StringUtils.isNoEmpty(reObj.getTotalPrice())) {
            tvRepairPrice.setText("￥" + reObj.getTotalPrice());
        } else {
            tvRepairPrice.setText("待确认");
        }

        if (reObj.getOrderListw() != null && reObj.getOrderListw().size() > 0) {
            imgList.addAll(PhotoUtils.getInstance().getListImage(reObj.getOrderListw().get(0).getPics()));
        }


        lanImageShowAdapter.notifyDataSetChanged();

        if (imgList.size() == 0) {
            recylerView.setVisibility(View.GONE);
        }

        if (reObj.getCommitScore() > 0) {
            llAllOut.setVisibility(View.VISIBLE);
            llServiceOut.setVisibility(View.VISIBLE);
            llTransOut.setVisibility(View.VISIBLE);
        } else {
            llAllOut.setVisibility(View.GONE);
            llServiceOut.setVisibility(View.GONE);
            llTransOut.setVisibility(View.GONE);
        }

        CommonStarUtils.getInstance().setCommonStarsNum(reObj.getCommitScore(), llAll);
        CommonStarUtils.getInstance().setCommonStarsNum(reObj.getServiceattitude(), llService);
        CommonStarUtils.getInstance().setCommonStarsNum(reObj.getCapability(), llTrans);

        //  0：待确认，1：待支付，2：待评价，3全部，4已完成
        //2 全部 ---  0 待受理 --- 1 待支付 ---  --- 4 已完成
        if (!reObj.isMyRepair()) {
            switch (reObj.getOrderState()) {
                case 0:
                    tvPay.setVisibility(View.GONE);
                    tvCommon.setVisibility(View.GONE);
                    tvLinkKf.setVisibility(View.GONE);

                    tvPayList.setVisibility(View.VISIBLE);

                    if (reObj.getPayTpye() != 0) {
                        tvLinkKf.setVisibility(View.VISIBLE);
                        tvDelete.setVisibility(View.GONE);
                    } else {
                        tvLinkKf.setVisibility(View.GONE);
                        tvDelete.setVisibility(View.VISIBLE);
                    }
                    break;
                case 1:
                    tvLinkKf.setVisibility(View.GONE);
                    tvDelete.setVisibility(View.GONE);
                    tvPay.setVisibility(View.VISIBLE);
                    tvCommon.setVisibility(View.GONE);
                    tvPayList.setVisibility(View.GONE);
                    break;
                case 3:
                    tvDelete.setVisibility(View.GONE);
                    tvPay.setVisibility(View.GONE);
                    tvCommon.setVisibility(View.GONE);
                    tvPayList.setVisibility(View.GONE);
                    tvLinkKf.setVisibility(View.GONE);
                    break;
                case 4:
                    if (reObj.getIsEvaluate() == 0) {
                        tvCommon.setVisibility(View.VISIBLE);
                    } else {
                        tvCommon.setVisibility(View.GONE);
                    }
                    tvDelete.setVisibility(View.GONE);
                    tvPay.setVisibility(View.GONE);
                    tvPayList.setVisibility(View.GONE);
                    tvLinkKf.setVisibility(View.VISIBLE);
                    break;
            }
        } else {
            //待维修
            if (reObj.getOrderState() == 1) {
                tvPayList.setVisibility(View.VISIBLE);
                tvWriteList.setVisibility(View.GONE);
                tvPayType.setText("用户已支付");
                //已维修
            } else if (reObj.getOrderState() == 4) {
                tvPayList.setVisibility(View.VISIBLE);
                tvWriteList.setVisibility(View.GONE);
                tvPayType.setText("用户已支付");
            } else {
                tvPayType.setText("用户已支付");
                tvWriteList.setVisibility(View.GONE);

                if (reObj.getServeID() == 0) {
                    tvCheckList.setVisibility(View.GONE);
                    tvWriteList.setVisibility(View.VISIBLE);
                } else {
                    tvCheckList.setVisibility(View.VISIBLE);
                    tvWriteList.setVisibility(View.GONE);
                }
            }
        }

        switch (reObj.getPayTpye()) {
            //未支付
            case 0:
                if (reObj.getOrderState() == 1) {
                    tvPayType.setText("");
                } else if (reObj.getOrderState() == 0) {
                    tvPayType.setText("订单审核中");
                } else if (reObj.getOrderState() == 4) {
                    tvPayType.setText("支付宝支付");
                }
                break;
            //线下支付
            case 1:
                if (reObj.getOrderState() != 0) {
                    tvPayType.setText("支付宝支付");
                }
                break;
            //融资支付
            case 2:
                if (reObj.getOrderState() != 0) {
                    tvPayType.setText("支付宝支付");
                }
                break;
        }
        switch (reObj.getPayWay()) {
            //微信支付
            case 1:
                if (reObj.getOrderState() == 3 || reObj.getOrderState() == 4) {
                    tvPayType.setText("微信支付");
                } else {
                    tvPayType.setText("");
                }
                break;
            //支付宝支付
            case 0:
                if (reObj.getOrderState() == 3 || reObj.getOrderState() == 4) {
                    tvPayType.setText("支付宝支付");
                } else {
                    tvPayType.setText("");
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        switch (requestCode) {
            case REPAIR_PRICE:
                repairPrice = data.getStringExtra(IntentUtils.getInstance().TYPE);
                tvRepairPrice.setText(repairPrice);
                listsBean.setTotalPrice(repairPrice);
                break;
        }
    }

    private void setBackData() {
        if (StringUtils.isNoEmpty(repairPrice)) {
            Intent intent = new Intent();
            intent.putExtra(IntentUtils.getInstance().POSITION, position);
            intent.putExtra(IntentUtils.getInstance().PRICE, repairPrice);
            setResult(102, intent);
        }
        RepairDetailActivity.this.finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            setBackData();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private StaggeredGridLayoutManager getLayoutManager() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL);
        return layoutManager;
    }


}
