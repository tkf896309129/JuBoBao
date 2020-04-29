package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.LoanApplyDetailBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：BtLoanDetailActivity
 * 描述：
 * 创建时间：2018-08-22  10:59
 */

public class BtLoanDetailActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.tv_price_unit)
    TextView tvPriceUnit;
    @Bind(R.id.tv_price)
    TextView tvPrice;
    @Bind(R.id.tv_apply_time)
    TextView tvApplyTime;
    @Bind(R.id.tv_back_time)
    TextView tvBackTime;
    @Bind(R.id.tv_loan_price)
    TextView tvLoanPrice;
    @Bind(R.id.tv_order_id)
    TextView tvOrderId;
    @Bind(R.id.tv_ton_num)
    TextView tvTonNum;
    @Bind(R.id.tv_service_price)
    TextView tvServicePrice;
    @Bind(R.id.tv_yq_days)
    TextView tvYqDays;
    @Bind(R.id.tv_yq_price)
    TextView tvYqPrice;
    @Bind(R.id.tv_back_now)
    TextView tvBackNow;
    @Bind(R.id.tv_alerady_yq)
    TextView tvAlreadyYq;
    @Bind(R.id.tv_hint_service)
    TextView tvHintService;
    @Bind(R.id.tv_hint_yq)
    TextView tvHintYq;
    @Bind(R.id.tv_bt_yhj)
    TextView tvBtYhj;

    private String id = "";
    private String backMoney = "";
    private String backBjMoney = "";
    private String servicePrice = "";
    private String latePrice = "";
    private String account = "";
    private String accountNum = "";
    //支付协议
    String rulePay = "";
    private String name = "";


    @Override
    public int initLayout() {
        return R.layout.activity_bt_loan_detail;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("借款详情");
        id = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getBtDetail(id);
    }

    @OnClick({R.id.rl_back_record, R.id.tv_back_now, R.id.ll_back, R.id.rl_check_xy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back_record:
                IntentUtils.getInstance().toActivity(this, BtRecordBackActivity.class, id);
                break;
            case R.id.tv_back_now:
                String accountValue = account + "," + accountNum + "," + latePrice;
                IntentUtils.getInstance().toYwActivity(this, BackMoneyActivity.class, id, backMoney, servicePrice, backBjMoney, accountValue,"");
                break;
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.rl_check_xy:
                IntentUtils.getInstance().toWebActivity(this, rulePay, "聚玻白条支付协议");
                break;
        }
    }


    //聚玻白条详情
    public void getBtDetail(String id) {
        OkhttpUtil.getInstance().setUnCacheData(this, "数据加载中", UrlConstant.getInstance().URL + PostConstant.getInstance().BORROW_RECORD_DETAIL
                + FieldConstant.getInstance().ID + "=" + id, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                LoanApplyDetailBean detailBean = JSON.parseObject(json, LoanApplyDetailBean.class);
                LoanApplyDetailBean.ReObjBean reObj = detailBean.getReObj();
                LoanApplyDetailBean.ReObjBean.CouponInfoBean couponInfo = detailBean.getReObj().getCouponInfo();
                tvPrice.setText(reObj.getNeedRefundAmount() + "(元)");
                tvApplyTime.setText("申请时间：" + DateUtils.getFormData(reObj.getCreateTime()));
                tvBackTime.setText("还款时间：" + DateUtils.getFormData(reObj.getStartTime()) + " 至 " + DateUtils.getFormData(reObj.getEndTime()));
                tvLoanPrice.setText(reObj.getBuyMoney() + "(元)");
                tvOrderId.setText(reObj.getOrderID());
                tvTonNum.setText(reObj.getOrderCount() + "(吨)");
                tvServicePrice.setText(reObj.getServiceMoney() + "(元)");
                tvYqDays.setText(reObj.getDays() + "天");
                tvAlreadyYq.setText("");
                latePrice = reObj.getLateMoney() + "";
                tvYqPrice.setText(reObj.getLateMoney() + "(元)");
                backMoney = reObj.getNeedRefundAmount() + "";
                servicePrice = reObj.getServiceMoney() + "";
                backBjMoney = (Double.parseDouble(reObj.getNeedRefundAmount()) - Double.parseDouble(reObj.getServiceMoney())) + "";
                tvHintService.setText(reObj.getServiceChargeTips());
                tvHintYq.setText(reObj.getOverdueCostTips());
                account = reObj.getAccountBankName();
                accountNum = reObj.getAccountBankCard();
                if (reObj.getState() == 0 || reObj.getState() == 3 || reObj.getState() == -1 || reObj.getState() == 4) {
                    tvBackNow.setVisibility(View.GONE);
                }
                getXyRule("2", reObj.getContractNo());

                if (couponInfo == null) {
                    tvBtYhj.setText("未使用优惠券");
                    return;
                }

                tvBtYhj.setText(couponInfo.getName());


            }

            @Override
            public void onFail() {

            }
        });
    }


    //获取相关协议
    public void getXyRule(String type, String code) {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(this, UrlConstant.getInstance().URL
                + PostConstant.getInstance().IOUS_AGREEMENT_URL
                + FieldConstant.getInstance().TYPE + "=" + type + "&"
                + FieldConstant.getInstance().AGREEMENT_CODE + "=" + code, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    rulePay = jsonObject.getString("ReObj");
                    LogUtils.i(rulePay);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail() {

            }
        });
    }
}
