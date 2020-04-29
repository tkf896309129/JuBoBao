package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.BuyFunction;
import com.example.huoshangkou.jubowan.activity.function.OrderCommitFunction;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.AfterBean;
import com.example.huoshangkou.jubowan.bean.BuyFuListBean;
import com.example.huoshangkou.jubowan.bean.BuyYaunListBean;
import com.example.huoshangkou.jubowan.bean.CarCountBean;
import com.example.huoshangkou.jubowan.bean.OrderWebDetailBean;
import com.example.huoshangkou.jubowan.bean.RawMaterialBean;
import com.example.huoshangkou.jubowan.constant.OrderTypeConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.inter.SuccessCallBack;
import com.example.huoshangkou.jubowan.photo.FileUtils;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.AddressUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.PhoneFormatCheckUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.utils.TwoPointUtils;

import java.io.File;
import java.io.IOException;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：SingleOrderCommitActivity
 * 描述：单个订单下单
 * 创建时间：2017-05-11  15:46
 */

public class SingleOrderCommitActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_buy_pic)
    ImageView ivBuyPic;
    @Bind(R.id.tv_brand)
    TextView tvBrand;
    @Bind(R.id.tv_level)
    TextView tvLevel;
    @Bind(R.id.tv_standard)
    TextView tvStandard;
    @Bind(R.id.tv_price)
    TextView tvPrice;
    @Bind(R.id.ll_1)
    LinearLayout ll1;
    @Bind(R.id.tv_type)
    TextView tvType;
    @Bind(R.id.tv_thick)
    TextView tvThick;
    @Bind(R.id.rl_car)
    RelativeLayout rlCar;
    @Bind(R.id.rb_platform)
    RadioButton rbPlatform;
    @Bind(R.id.rb_feel)
    RadioButton rbFeel;
    @Bind(R.id.rg_trans_type)
    RadioGroup rgTransType;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.rl_address_choose)
    LinearLayout rlAddressChoose;

    @Bind(R.id.rl_trans_type)
    RelativeLayout rlTransType;

    @Bind(R.id.tv_buy_unit)
    TextView tvUnit;
    @Bind(R.id.iv_decrease)
    ImageView imgDes;
    @Bind(R.id.iv_add)
    ImageView imgAdd;
    @Bind(R.id.et_num)
    EditText etNum;
    @Bind(R.id.et_ywy_phone)
    EditText etYwyPhone;
    @Bind(R.id.tv_store)
    TextView tvStore;


    private String type = "";
    //是否是原片
    private boolean isYp = false;
    //是否是原料
    private boolean isYl = false;
    //是否是辅料
    private boolean isFu = false;
    //是否需要地址
    private boolean isNeedAddress = false;

    BuyYaunListBean buyYaunListBean;
    BuyFuListBean buyFuListBean;
    RawMaterialBean.ReListBean buyYlListBean;

    //原片id
    private String ypId = "";
    //辅料id
    private String flId = "";
    private String ylId = "";
    //0 自有物流 1平台物流
    private String isPtWl = "1";
    //产品id
    private String id = "";
    //业务员电话
    private String ywyPhone = "";
    //产品数量
    private String proCount = "";
    //加入购物车类型
    private String addCarType = "";
    //加入购物车数量
    String addCarCount = "";

    @Bind(R.id.tv_all_price)
    TextView tvAllPrice;

    //地址选择
    private final int CHOOSE_ADDRESS = 1;
    private String address = "";
    //地址id
    private String addressId = "";

    //是否是平台物流
    private boolean isPlatForm = true;

    private String price = "";
    private String orderType = "";
    //库存
    private String restNum = "";
    private String detailData = "";

    @Override
    public int initLayout() {
        return R.layout.activity_single_order;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().orderList);
        tvTitle.setText("确认订单");
        type = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        //原片
        if (type.equals(OrderTypeConstant.getInstance().YP)) {
            orderType = "0";
            rbPlatform.setChecked(true);
            isYp = true;
            isNeedAddress = true;
            isYl = false;
            buyYaunListBean = (BuyYaunListBean) getIntent().getSerializableExtra(IntentUtils.getInstance().BEAN_TYPE);
            getData(buyYaunListBean.getPic(), buyYaunListBean.getBrandName(), buyYaunListBean.getLevelName(), buyYaunListBean.getXy(), buyYaunListBean.getClassName(), buyYaunListBean.getWeight() + "", buyYaunListBean.getPrice(), "/吨");
            ypId = buyYaunListBean.getID() + ",";
            price = buyYaunListBean.getPrice();

            id = buyYaunListBean.getID() + "";
            tvUnit.setText("购买数量 (吨)");
            addCarType = OrderTypeConstant.getInstance().addYp;
            tvStore.setText("库存：" + buyYaunListBean.getReserve() + buyYaunListBean.getNameUnit());
            restNum = buyYaunListBean.getReserve() + "";
            //辅材
        } else if (type.equals(OrderTypeConstant.getInstance().FL)) {
            orderType = "1";
            isNeedAddress = true;
            rbPlatform.setChecked(true);
            isYp = false;
            isFu = true;
            isYl = false;
            buyFuListBean = (BuyFuListBean) getIntent().getSerializableExtra(IntentUtils.getInstance().BEAN_TYPE);
            getData(buyFuListBean.getPic(), buyFuListBean.getBrandName(), "", buyFuListBean.getGuigeName(), buyFuListBean.getClassName(), "", buyFuListBean.getPrice(), "/" + buyFuListBean.getNameUnit());
            flId = buyFuListBean.getID() + ",";
            id = buyFuListBean.getID() + "";
            tvUnit.setText("购买数量 (" + buyFuListBean.getNameUnit() + ")");
            price = buyFuListBean.getPrice();
            addCarType = OrderTypeConstant.getInstance().addFl;
            tvStore.setText("库存：" + buyFuListBean.getReserve() + buyFuListBean.getNameUnit());
            restNum = buyFuListBean.getReserve();
        } else if (type.equals(OrderTypeConstant.getInstance().YL)) {
            orderType = "2";
            isYp = false;
            isYl = true;
            rbPlatform.setChecked(true);
            isNeedAddress = true;
            buyYlListBean = (RawMaterialBean.ReListBean) getIntent().getSerializableExtra(IntentUtils.getInstance().BEAN_TYPE);
            getData(buyYlListBean.getPic(), buyYlListBean.getBrandName(), buyYlListBean.getLevelName(), buyYlListBean.getCategoryName(), buyYlListBean.getClassName(), "", buyYlListBean.getPrice(), "/" + buyYlListBean.getNameUnit());
            ylId = buyYlListBean.getID() + ",";
            id = buyYlListBean.getID() + "";
            tvUnit.setText("购买数量 (" + buyYlListBean.getNameUnit() + ")");
            price = buyYlListBean.getPrice();
            addCarType = OrderTypeConstant.getInstance().addYl;
            tvStore.setText("库存：" + buyYlListBean.getReserve() + buyYlListBean.getNameUnit());
            restNum = buyYlListBean.getReserve();
        }
        rgTransType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_platform:
                        isPlatForm = true;
                        isPtWl = "1";
                        rlAddressChoose.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rb_feel:
                        isPlatForm = false;
                        isPtWl = "0";
                        rlAddressChoose.setVisibility(View.GONE);
                        break;
                }
            }
        });

        getPrice("1");

        etNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String str = charSequence.toString();
                getPrice(str);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        //设置默认地址
        //默认地址
        addressId = AddressUtils.getInstance().getAddressId();
        address = AddressUtils.getInstance().getDefaultAddress();
        if (!type.equals(OrderTypeConstant.getInstance().FL)) {
            tvAddress.setText(address);
        }
        getOrderDetail(orderType, id);
    }

    public void getData(String pic, String brandName, String levelName, String xy, String className, String weight, String prices, String unit) {
        GlideUtils.getInstance().displayImage(pic, getContext(), ivBuyPic);

        tvBrand.setText("品牌：" + brandName);
        tvStandard.setText("规格：" + xy);
        tvType.setText("品类：" + className);
        tvThick.setText("厚度：" + weight + "mm");
        if (className.equals("Low-E")) {
            tvLevel.setText("膜系：" + buyYaunListBean.getMoxiName());
        } else {
            tvLevel.setText("等级：" + levelName);
        }
        if (isFu) {
            tvLevel.setVisibility(View.GONE);
            tvThick.setVisibility(View.GONE);
//            rlTransType.setVisibility(View.GONE);
//            tvAddress.setText("自提");
        }
        if (isYl) {
            tvThick.setText("类别：" + buyYlListBean.getCategoryName());
            tvThick.setVisibility(View.VISIBLE);
            tvStandard.setVisibility(View.GONE);
        }

        //改变字体颜色以及大小
        String price = "￥" + prices + unit;
        int colorPosition = (price).indexOf(".");
        int linePosition = price.indexOf("/");

        if (!StringUtils.isNoEmpty(prices)) {
            return;
        }
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append(price);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.main_tab_blue_all)), 1, linePosition, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        //字体大小
        AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(28);
        AbsoluteSizeSpan sizeSpan2 = new AbsoluteSizeSpan(28);
        spannableString.setSpan(sizeSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        spannableString.setSpan(sizeSpan2, colorPosition, linePosition, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        tvPrice.setText(spannableString);
    }

    @OnClick({R.id.ll_back,R.id.tv_confirm_order, R.id.tv_add_car, R.id.iv_add, R.id.iv_decrease, R.id.rl_address_choose})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_confirm_order:
                proCount = etNum.getText().toString().trim();
                if (!StringUtils.isNoEmpty(proCount)) {
                    ToastUtils.getMineToast("请输入下单数量");
                    return;
                }

                if (Double.parseDouble(proCount) <= 0) {
                    ToastUtils.getMineToast("购买数量不能为0");
                    return;
                }

                if (isPlatForm && isNeedAddress) {
                    ywyPhone = etYwyPhone.getText().toString().trim();

                    if (StringUtils.isNoEmpty(ywyPhone)) {
                        if (!PhoneFormatCheckUtils.getInstance().isPhoneLegal(ywyPhone)) {
                            ToastUtils.getMineToast("请输入正确的手机号码");
                            return;
                        }
                    }

                    if (isPlatForm && !StringUtils.isNoEmpty(addressId)) {
                        ToastUtils.getMineToast("请选择地址");
                        return;
                    }
                }


                CopyIosDialogUtils.getInstance().getIosDialog(getContext(), "是否提交订单", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        String putAddress = "";
                        if (isPlatForm) {
                            putAddress = addressId;
                        } else {
                            putAddress = "";
                        }
                        OrderCommitFunction.getInstance().commitOrder(getContext(), id, orderType, proCount, isPtWl, putAddress, ywyPhone, new StringCallBack() {
                            @Override
                            public void onSuccess(String str) {
                                AfterBean afterBean = JSON.parseObject(str, AfterBean.class);
                                if (afterBean.getSuccess() == 1) {
                                    IntentUtils.getInstance().toAfterOrder(getContext(), afterBean.getReObj().getOrderId(), afterBean.getReObj().getCreateTime());
                                    ToastUtils.getMineToast("下单成功");
                                } else {
                                    ToastUtils.getMineToast(afterBean.getErrMsg());
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
            case R.id.tv_add_car:
                proCount = etNum.getText().toString().trim();
                //默认加1
                if (!StringUtils.isNoEmpty(proCount)) {
                    addCarCount = OrderTypeConstant.getInstance().ADD_CAR;
                    //否则根据数量相加
                } else {
                    addCarCount = proCount;
                }

                CopyIosDialogUtils.getInstance().getIosDialog(getContext(), "是否加入购物车", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        //加一个到购物车
                        BuyFunction.getInstance().addToYuanCar(addCarType, getContext(), id, addCarCount, new StringCallBack() {
                            @Override
                            public void onSuccess(String carCount) {
                                CarCountBean successBean = JSON.parseObject(carCount, CarCountBean.class);
                                if (successBean.getSuccess() == 1) {
                                    ToastUtils.getMineToast(getString(R.string.add_success));
                                    SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().INIT_BUY, "yes");
//                                    SingleOrderCommitActivity.this.finish();
                                } else {
                                    ToastUtils.getMineToast(successBean.getErrMsg());
                                }
                            }

                            @Override
                            public void onFail() {
                                ToastUtils.getMineToast(getString(R.string.add_fail));
                            }
                        });
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
                break;
            //增加
            case R.id.iv_add:
                String trim = etNum.getText().toString().trim();
                if (!StringUtils.isNoEmpty(trim)) {
                    trim = "0";
                }
                double num = Double.parseDouble(trim);
                double addNum = num + 1;
                etNum.setText(addNum + "");
                break;
            //减少
            case R.id.iv_decrease:
                String trim_2 = etNum.getText().toString().trim();
                if (!StringUtils.isNoEmpty(trim_2)) {
                    trim_2 = "0";
                }
                double num_2 = Double.parseDouble(trim_2);
                if (num_2 <= 1) {
                    return;
                }
                double addNum_2 = num_2 - 1;
                etNum.setText(addNum_2 + "");
                break;
            //地址选择
            case R.id.rl_address_choose:
                if (!isNeedAddress) {
                    return;
                }
                if (!isPlatForm) {
                    ToastUtils.getMineToast("自有物流不需要填写地址");
                    return;
                }
                Intent intent = new Intent(this, AddressListActivity.class);
                intent.putExtra(IntentUtils.getInstance().TYPE, IntentUtils.getInstance().ADDRESS_CHOOSE);
                startActivityForResult(intent, CHOOSE_ADDRESS);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CHOOSE_ADDRESS:
                if (data == null) {
                    return;
                }
                if (isNeedAddress) {
                    address = data.getStringExtra(IntentUtils.getInstance().ADDRESS_NAME);
                    tvAddress.setText(address);
                    addressId = data.getStringExtra(IntentUtils.getInstance().ADDRESS_ID);
                }
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        String isDelete = (String) SharedPreferencesUtils.getInstance().get(this, SharedKeyConstant.getInstance().ADDRESS_DELETE, "");
        if (StringUtils.isNoEmpty(isDelete)) {
            addressId = AddressUtils.getInstance().getAddressId();
            address = AddressUtils.getInstance().getDefaultAddress();
            if (!type.equals(OrderTypeConstant.getInstance().FL)) {
                tvAddress.setText(address);
                SharedPreferencesUtils.getInstance().put(this, SharedKeyConstant.getInstance().ADDRESS_DELETE, "");
            }
        }
    }

    //计算价格
    public void getPrice(String count) {
        if (!StringUtils.isNoEmpty(price) || !StringUtils.isNoEmpty(count)) {
            tvAllPrice.setText("");
            return;
        }
        if (count.equals(".") || count.equals("0")) {
            etNum.setText("1");
            return;
        }
        if (Double.parseDouble(count) > Double.parseDouble(restNum)) {
            ToastUtils.getMineToast("购买数量不能大于库存");
            etNum.setText(restNum);
            return;
        }
        //限制两位小数
//        if (count.indexOf(".")!=-1 && count.substring(count.indexOf("."), count.length()).length() > 2) {
//            etNum.setText(TwoPointUtils.getInstance().getTwoPoint(Double.parseDouble(count)));
//        }
        double allPrice = Double.parseDouble(count) * Double.parseDouble(price);
        tvAllPrice.setText("￥" + TwoPointUtils.getInstance().getTwoPoint(allPrice));
    }

     public void getOrderDetail(String proType, String id) {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(this, UrlConstant.getInstance().LOCAL_URL + "GetProductDetail/?ProductType=" + proType + "&Id=" + id, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                LogUtils.e(json);
                OrderWebDetailBean detailBean = JSON.parseObject(json, OrderWebDetailBean.class);
                detailData = detailBean.getReObj().getDescription();
                if(StringUtils.isNoEmpty(detailData)){

                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
