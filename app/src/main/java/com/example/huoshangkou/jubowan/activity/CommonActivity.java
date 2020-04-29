package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.OrderTypeConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.CommonStarUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import org.simple.eventbus.EventBus;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：CommonActivity
 * 描述：
 * 创建时间：2017-05-09  10:44
 */

public class CommonActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Bind(R.id.ll_all_common)
    LinearLayout llAll;
    @Bind(R.id.ll_service_attitude)
    LinearLayout llService;
    @Bind(R.id.ll_common_trans)
    LinearLayout llTrans;
    @Bind(R.id.ll_service)
    LinearLayout llSer;
    @Bind(R.id.ll_trans)
    LinearLayout llTran;

    @Bind(R.id.et_common_detail)
    EditText etCommonDetail;

    private String allScore = "";
    private String serviceScore = "";
    private String capScore = "";

    private String orderId = "";

    //评价类别
    private String type = "";
    //是否是普通订单
    private boolean isCommonOrder = false;

    //描述
    private String descript = "";
    int position = -1;

    @Override
    public int initLayout() {
        return R.layout.activity_common;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this,ActivityUtils.getInstance().orderList);
        orderId = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        type = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        position = getIntent().getIntExtra(IntentUtils.getInstance().POSITION, -1);

        if (!StringUtils.isNoEmpty(type)) {
            return;
        }

        //普通订单
        if (type.equals(OrderTypeConstant.getInstance().COMMON_ORDER)) {
            isCommonOrder = true;
            llSer.setVisibility(View.GONE);
            llTran.setVisibility(View.GONE);
        } else {
            isCommonOrder = false;
        }

        tvTitle.setText("评价订单");

        CommonStarUtils.getInstance().setCommonStars(llAll, 0, new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                allScore = str;
            }

            @Override
            public void onFail() {

            }
        });
        CommonStarUtils.getInstance().setCommonStars(llService, 0, new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                serviceScore = str;
            }

            @Override
            public void onFail() {

            }
        });
        CommonStarUtils.getInstance().setCommonStars(llTrans, 0, new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                capScore = str;
            }

            @Override
            public void onFail() {

            }
        });
    }

    @OnClick({R.id.ll_back, R.id.tv_commit_common})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_commit_common:
                if (!StringUtils.isNoEmpty(allScore) && !allScore.equals("0")) {
                    ToastUtils.getMineToast("请给整体评价进行评分");
                    return;
                }
                if (!isCommonOrder && !StringUtils.isNoEmpty(serviceScore) && !serviceScore.equals("0")) {
                    ToastUtils.getMineToast("请给服务态度进行评分");
                    return;
                }
                if (!isCommonOrder && !StringUtils.isNoEmpty(capScore) && !capScore.equals("0")) {
                    ToastUtils.getMineToast("请给物流速度进行评分");
                    return;
                }

                descript = etCommonDetail.getText().toString().trim();

                CopyIosDialogUtils.getInstance().getIosDialog(this, "是否提交", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        commitOrderCommon(orderId, allScore, serviceScore, capScore, descript);
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
                break;
        }
    }

    //提交订单评价
    public void commitOrderCommon(String orderId, final String allScore, final String serviceScore, final String capScore, String descript) {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().ADD_MAINTAIN_SCORE + FieldConstant.getInstance().ORDER_ID + "=" + orderId + "&"
                + FieldConstant.getInstance().ALL_SCORE + "=" + allScore + "&"
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().SERVICE_SCORE + "=" + serviceScore + "&"
                + FieldConstant.getInstance().CAPABILITY_SCORE + "=" + capScore + "&"
                + FieldConstant.getInstance().DESCRIPT + "=" + EncodeUtils.getInstance().getEncodeString(descript), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().orderList);
                    EventBus.getDefault().post("initOrder", "initOrder");
                    CommonActivity.this.finish();
                } else {
                    ToastUtils.getMineToast("提交失败");
                }
            }

            @Override
            public void onFail() {
                ToastUtils.getMineToast("提交失败");
            }
        });
    }

}
