package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.OrderCommitFunction;
import com.example.huoshangkou.jubowan.activity.function.ShopCarFunction;
import com.example.huoshangkou.jubowan.adapter.OrderCommitAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.AfterBean;
import com.example.huoshangkou.jubowan.bean.ShopCarListBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.inter.SuccessCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.AddressUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.PhoneFormatCheckUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：ShopOrderCommitActivity
 * 描述：
 * 创建时间：2017-03-13  13:56
 */

public class ShopOrderCommitActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.tv_yuan_order)
    TextView tvYuanOrder;
    @Bind(R.id.lv_shop_yuan)
    ScrollListView lvShopYuan;

//    @Bind(R.id.tv_fu_order)
//    TextView tvFuOrder;
//    @Bind(R.id.tv_yl_order)
//    TextView tvYlOrder;
//    @Bind(R.id.lv_shop_fu)
//    ScrollListView lvShopFu;
//    @Bind(R.id.lv_shop_yl)
//    ScrollListView lvShopYl;

    @Bind(R.id.tv_all_price)
    TextView tvAllPrice;
    @Bind(R.id.tv_pay_now)
    TextView tvPayNow;

    @Bind(R.id.tv_yuan_price)
    TextView tvYuanPrice;
    //    @Bind(R.id.tv_fu_price)
//    TextView tvFuPrice;
//    @Bind(R.id.tv_yl_price)
//    TextView tvYlPrice;
    @Bind(R.id.scroll_view)
    ScrollView scrollView;
    //物流方式
    @Bind(R.id.rg_trans_type)
    RadioGroup rgTransType;
    @Bind(R.id.ll_address_choose)
    LinearLayout llAddress;

    //地址选择
    private final int CHOOSE_ADDRESS = 1;
    private String address = "";
    private String addressID = "";
    private String linkMan = "";
    private String linkTel = "";

    //下单的地址id
    private String addressId = "";
    private ArrayList<ShopCarListBean> listYuan;
//    private ArrayList<ShopCarListBean> listFu;
//    private ArrayList<ShopCarListBean> listYl;

    OrderCommitAdapter commitYuanAdapter;
//    OrderCommitAdapter commitFuAdapter;
//    OrderCommitAdapter commitYlAdapter;

    @Bind(R.id.ll_yuan)
    LinearLayout llYuan;
    //    @Bind(R.id.ll_fu)
//    LinearLayout llFu;
//    @Bind(R.id.ll_yl)
//    LinearLayout llYl;
    @Bind(R.id.et_ywy_phone)
    EditText etYwyPhone;
    @Bind(R.id.rl_trans_type)
    RelativeLayout rlTransType;

    //是否是平台物流
    private boolean isPlatForm = true;
    private String orderId = "";
    private String ypId = "";
    private String flId = "";
    private String ylId = "";
    private String proCount = "";
    //0 自有物流 1平台物流
    private String isPtWl = "1";
    //业务员电话
    private String ywyPhone = "";
    //是否需要物流信息
    private boolean isNeddTrans = true;
    private String orderType = "";
    private double count = 0;

    @Override
    public int initLayout() {
        return R.layout.activity_shop_order;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().orderList);

        tvTitle.setText("确认订单");

        listYuan = (ArrayList<ShopCarListBean>) getIntent().getSerializableExtra("yuanList");
//        listFu = (ArrayList<ShopCarListBean>) getIntent().getSerializableExtra("fuList");
//        listYl = (ArrayList<ShopCarListBean>) getIntent().getSerializableExtra("ylList");

        String yPrice = ShopCarFunction.getInstance().caculateSinglePrice(listYuan);
//        String fPrice = ShopCarFunction.getInstance().caculateSinglePrice(listFu);
//        String ylPrice = ShopCarFunction.getInstance().caculateSinglePrice(listYl);

        for (int i = 0; i < listYuan.size(); i++) {
            count += Double.parseDouble(listYuan.get(i).getToNum());
        }
//        for (int i = 0; i < listFu.size(); i++) {
//            count += Double.parseDouble(listFu.get(i).getToNum());
//        }

        tvPayNow.setText("结算(" + count + ")");

//        if (listFu.size() == 0) {
//            llFu.setVisibility(View.GONE);
//        }
//        if (listYl.size() == 0) {
//            llYl.setVisibility(View.GONE);
//        }

        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.scrollTo(0, 0);
            }
        });
        if (listYuan.size() != 0) {
            orderType = listYuan.get(0).getType();
            switch (listYuan.get(0).getType()) {
                case "0":
                    tvYuanOrder.setText("原片订单  (" + listYuan.size() + ")");
                    break;
                case "1":
                    tvYuanOrder.setText("辅材订单  (" + listYuan.size() + ")");
                    break;
                case "2":
                    tvYuanOrder.setText("原料订单  (" + listYuan.size() + ")");
                    break;
            }
        }

//        tvFuOrder.setText("辅材订单  (" + listFu.size() + ")");
//        tvYlOrder.setText("原料订单  (" + listYl.size() + ")");

        String yuanPrice = "￥" + yPrice + " (运费待平台确认)";
//        String fuPrice = "￥" + fPrice + " (运费待平台确认)";
//        String yuanLiaoPrice = "￥" + ylPrice + " (运费待平台确认)";

        String allPrice = "合计：￥" + Double.parseDouble(yPrice);
//        String allPrice = "合计：￥" + (Double.parseDouble(yPrice) + Double.parseDouble(fPrice) + Double.parseDouble(ylPrice));

        //总价格
        SpannableStringBuilder spannableStringAll = new SpannableStringBuilder();
        spannableStringAll.append(allPrice);
        spannableStringAll.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.main_tab_blue_all)), 3, allPrice.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        tvAllPrice.setText(spannableStringAll);


        SpannableStringBuilder spannableStringYuan = new SpannableStringBuilder();
        spannableStringYuan.append(yuanPrice);
        spannableStringYuan.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.main_tab_blue_all)), 1, yuanPrice.indexOf("("), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

//        SpannableStringBuilder spannableStringFu = new SpannableStringBuilder();
//        spannableStringFu.append(fuPrice);
//        spannableStringFu.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.main_tab_blue)), 1, fuPrice.indexOf("("), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
//
//        SpannableStringBuilder spannableStringYl = new SpannableStringBuilder();
//        spannableStringYl.append(yuanLiaoPrice);
//        spannableStringYl.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.main_tab_blue)), 1, yuanLiaoPrice.indexOf("("), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        tvYuanPrice.setText(spannableStringYuan);
//        tvFuPrice.setText(spannableStringFu);
//        tvYlPrice.setText(spannableStringYl);

        commitYuanAdapter = new OrderCommitAdapter(this, listYuan, R.layout.item_commit_order);
//        commitFuAdapter = new OrderCommitAdapter(this, listFu, R.layout.item_commit_order);
//        commitYlAdapter = new OrderCommitAdapter(this, listYl, R.layout.item_commit_order);

        lvShopYuan.setAdapter(commitYuanAdapter);
//        lvShopFu.setAdapter(commitFuAdapter);
//        lvShopYl.setAdapter(commitYlAdapter);

        lvShopYuan.setDividerHeight(0);
//        lvShopFu.setDividerHeight(0);
//        lvShopYl.setDividerHeight(0);


        int ypNum = listYuan.size();
        for (int i = 0; i < ypNum; i++) {
            orderId += listYuan.get(i).getID() + ",";
        }
//        int flNum = listFu.size();
//        for (int i = 0; i < flNum; i++) {
//            flId += listFu.get(i).getID() + ",";
//        }
//        int ylNum = listYl.size();
//        for (int i = 0; i < ylNum; i++) {
//            ylId += listYl.get(i).getID() + ",";
//        }


//        address = AddressUtils.getInstance().getDefaultAddress();
//        addressID = AddressUtils.getInstance().getAddressId();
//        linkMan = AddressUtils.getInstance().getLinkMan();
//        linkTel = AddressUtils.getInstance().getLinkTel();
//        tvAddress.setText(address);

        //默认平台物流
        RadioButton childAt = (RadioButton) rgTransType.getChildAt(0);
        childAt.setChecked(true);

        rgTransType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    //平台物流
                    case R.id.rb_platform:
                        isPlatForm = true;
                        isPtWl = "1";
                        tvAddress.setTextColor(getResources().getColor(R.color.address_black_key));
                        llAddress.setVisibility(View.VISIBLE);
                        break;
                    //自由物流
                    case R.id.rb_feel:
                        isPlatForm = false;
                        isPtWl = "0";
                        tvAddress.setTextColor(getResources().getColor(R.color.delete_order_gray));
                        llAddress.setVisibility(View.GONE);
                        break;
                }
            }
        });
    }

    @OnClick({R.id.ll_back, R.id.ll_address_choose, R.id.tv_pay_now})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            //选择地址
            case R.id.ll_address_choose:
                if (!isPlatForm || !isNeddTrans) {
//                    ToastUtils.getMineToast("自有物流不需要填写地址");
                    return;
                }
                Intent intent = new Intent(this, AddressListActivity.class);
                intent.putExtra(IntentUtils.getInstance().TYPE, IntentUtils.getInstance().ADDRESS_CHOOSE);
                startActivityForResult(intent, CHOOSE_ADDRESS);
                break;
            case R.id.tv_pay_now:

                ywyPhone = etYwyPhone.getText().toString().trim();

                if (isPlatForm && isNeddTrans && !StringUtils.isNoEmpty(addressID)) {
                    ToastUtils.getMineToast("请选择地址");
                    return;
                }

                if (StringUtils.isNoEmpty(ywyPhone)) {
                    if (!PhoneFormatCheckUtils.getInstance().isPhoneLegal(ywyPhone)) {
                        ToastUtils.getMineToast("请输入正确的手机号码");
                        return;
                    }
                }

                CopyIosDialogUtils.getInstance().getIosDialog(getContext(), "是否提交订单", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        String putAddress = "";
                        if (isPlatForm) {
                            putAddress = addressID;
                        } else {
                            putAddress = "";
                        }
                        OrderCommitFunction.getInstance().commitOrder(getContext(), orderId, orderType, proCount, isPtWl, putAddress, ywyPhone, new StringCallBack() {
                            @Override
                            public void onSuccess(String json) {
                                AfterBean afterBean = JSON.parseObject(json, AfterBean.class);
                                if (afterBean.getSuccess() == 1) {
                                    IntentUtils.getInstance().toAfterOrder(getContext(), afterBean.getReObj().getOrderId(), afterBean.getReObj().getCreateTime());
                                    ToastUtils.getMineToast("下单成功");
                                    SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().INIT_BUY, "yes");
                                }
                            }

                            @Override
                            public void onFail() {

                            }
                        });
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //默认地址
        addressID = AddressUtils.getInstance().getAddressId();
        address = AddressUtils.getInstance().getDefaultAddress();
        //辅材
        tvAddress.setText(address);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CHOOSE_ADDRESS:
                if (data == null) {
                    return;
                }
                address = data.getStringExtra(IntentUtils.getInstance().ADDRESS_NAME);
                addressID = data.getStringExtra(IntentUtils.getInstance().ADDRESS_ID);
                tvAddress.setText(address);
//                AddressUtils.getInstance().saveDefaultAddress(addressID, address, linkMan, linkTel);
                break;
        }
    }
}
