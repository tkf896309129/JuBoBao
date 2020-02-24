package com.example.huoshangkou.jubowan.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.PjOrderDetailAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.AfterBean;
import com.example.huoshangkou.jubowan.bean.PjCarListBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：PjOrderActivity
 * 描述：
 * 创建时间：2017-11-30  10:41
 */

public class PjOrderActivity extends BaseActivity {
    @Bind(R.id.switch_key)
    Switch aSwitch;
    @Bind(R.id.ll_black)
    LinearLayout llBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.tv_address_name)
    TextView tvAddressName;
    @Bind(R.id.tv_address_content)
    TextView tvAddressContent;
    @Bind(R.id.rl_address)
    RelativeLayout rlAddress;
    @Bind(R.id.lv_order_pj)
    ScrollListView listView;
    @Bind(R.id.tv_all_price)
    TextView tvAllPrice;
    @Bind(R.id.et_fp_tt)
    EditText etInvoinceTop;
    @Bind(R.id.et_nsr_number)
    EditText etInvoinceNum;

    private String address = "";
    //地址id
    private String addressId = "";

    private float mDensity;
    private int mHiddenViewMeasuredHeight;

    PjOrderDetailAdapter detailAdapter;
    //是否需要开发票
    private boolean isNeedInvoince = false;

    private String productId = "";
    //发票抬头
    private String invoinceNise = "";
    //纳税人识别码
    private String socailCreCo = "";

    //地址选择
    private final int CHOOSE_ADDRESS = 1;
    //数量
    private int number;

    @Override
    public int initLayout() {
        return R.layout.activity_pj_order;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("立即下单");
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().orderList);
        ArrayList<PjCarListBean> listTo = getIntent().getParcelableArrayListExtra("listTo");


        if (listTo != null) {
            int num = listTo.size();
            for (int i = 0; i < num; i++) {
                productId += listTo.get(i).getProductID() + ",";
                number += Integer.parseInt(listTo.get(i).getNumber());
            }
            String allPrice = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
            tvAllPrice.setText("￥" + allPrice);

            detailAdapter = new PjOrderDetailAdapter(getContext(), listTo, R.layout.item_pj_order_detail);
            listView.setAdapter(detailAdapter);
            listView.setDividerHeight(0);
        }

        mDensity = getResources().getDisplayMetrics().density;
        mHiddenViewMeasuredHeight = (int) (mDensity * 150 + 0.5);
//        mHiddenViewMeasuredHeight = llBack.getMeasuredHeight();
        if (llBack.getVisibility() == View.GONE) {
            aSwitch.setChecked(false);
        } else {
            aSwitch.setChecked(true);
        }

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (llBack.getVisibility() == View.GONE) {
                    animateOpen(llBack);
                    isNeedInvoince = true;
                } else {
                    animateClose(llBack);
                    isNeedInvoince = false;
                }
            }
        });
    }

    @OnClick({R.id.ll_back, R.id.rl_address_click, R.id.tv_pay_now})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.rl_address_click:
                Intent intent = new Intent(this, AddressListActivity.class);
                intent.putExtra(IntentUtils.getInstance().TYPE, IntentUtils.getInstance().ADDRESS_CHOOSE);
                startActivityForResult(intent, CHOOSE_ADDRESS);
                break;
            case R.id.tv_pay_now:
                if (!StringUtils.isNoEmpty(addressId)) {
                    ToastUtils.getMineToast("请选择地址");
                    return;
                }
                if (isNeedInvoince) {
                    invoinceNise = etInvoinceTop.getText().toString().trim();
                    if (!StringUtils.isNoEmpty(invoinceNise)) {
                        ToastUtils.getMineToast("请输入发票抬头");
                        return;
                    }
                    socailCreCo = etInvoinceNum.getText().toString().trim();
                    if (!StringUtils.isNoEmpty(socailCreCo)) {
                        ToastUtils.getMineToast("请输入纳税人识别码");
                        return;
                    }
                }
                commitOrder(getContext(), productId, number + "", addressId, invoinceNise, socailCreCo);
                break;
        }
    }


    //立即下单接口
    public void commitOrder(Context context, String productId, String number, String addrId, String invoinceNise, String socialCreCo) {
        OkhttpUtil.getInstance().setUnCacheData(context, "下单中", UrlConstant.getInstance().URL
                + PostConstant.getInstance().SUBMITION_PJ
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().PRODUCT_IDS + "=" + productId + "&"
                + FieldConstant.getInstance().NUMBER + "=" + number + "&"
                + FieldConstant.getInstance().ADDR_ID + "=" + addrId + "&"
                + FieldConstant.getInstance().INVOINCE_ID + "=1" + "&"
                + FieldConstant.getInstance().INVOINCE_NISE + "=" + invoinceNise + "&"
                + FieldConstant.getInstance().SOCIAL_CODE + "=" + socialCreCo, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                AfterBean afterBean = JSON.parseObject(json, AfterBean.class);
                if (afterBean.getSuccess() == 1) {
                    IntentUtils.getInstance().toAfterOrder(getContext(), afterBean.getReObj().getYPOrderID(), afterBean.getReObj().getFlOrderID(), afterBean.getReObj().getCreateTime());
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CHOOSE_ADDRESS:
                if (data == null) {
                    return;
                }

                rlAddress.setVisibility(View.VISIBLE);
                address = data.getStringExtra(IntentUtils.getInstance().ADDRESS_NAME);
                String linkMan = data.getStringExtra(IntentUtils.getInstance().ADDRESS_LINK_MAN);
                String linkTel = data.getStringExtra(IntentUtils.getInstance().ADDRESS_LINK_TEL);
                tvAddressName.setText("联系人：" + linkMan + "   " + "手机号：" + linkTel);
                tvAddressContent.setText(address);
                tvAddress.setText("");
                addressId = data.getStringExtra(IntentUtils.getInstance().ADDRESS_ID);
                break;
        }
    }

    private void animateOpen(View v) {
        v.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(v, 0,
                mHiddenViewMeasuredHeight);
        animator.start();
    }

    private void animateClose(final View view) {
        int origHeight = view.getHeight();
        ValueAnimator animator = createDropAnimator(view, origHeight, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }

        });
        animator.start();
    }

    private ValueAnimator createDropAnimator(final View v, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator arg0) {
                int value = (int) arg0.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
                layoutParams.height = value;
                v.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }
}
