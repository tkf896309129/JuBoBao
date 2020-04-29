package com.example.huoshangkou.jubowan.activity;

import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.JbBtBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.DialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.MoneyUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.CircleBarView;

import java.text.DecimalFormat;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：JuBoBtEdActivity
 * 描述：
 * 创建时间：2018-08-21  10:49
 */

public class JuBoBtEdActivity extends BaseActivity {
    @Bind(R.id.cb_bt)
    CircleBarView circleBarView;
    @Bind(R.id.tv_price)
    TextView tvPrice;
    @Bind(R.id.tv_all_price)
    TextView tvAllPrice;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_days)
    TextView tvDays;
    @Bind(R.id.tv_time_limit)
    TextView tvTimeLimit;

    JbBtBean btBean;
    private float allPrice = 0;
    private float currentPrice = 0;
    private String contractNo = "";

    @Override
    public int initLayout() {
        return R.layout.activity_jb_ed;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("聚玻白条");
        btBean = (JbBtBean) getIntent().getSerializableExtra(IntentUtils.getInstance().BEAN_TYPE);
        if (btBean != null) {
            allPrice = Float.parseFloat(btBean.getReObj().getIousTotalAmount());
            currentPrice =  Float.parseFloat(btBean.getReObj().getIousRemainingAmount());
            LogUtils.e(btBean.getReObj().getIousRemainingAmount()+"allPrice");
            contractNo = btBean.getReObj().getContractNo();
            tvDays.setText(btBean.getReObj().getIousRemainingDays() + "");
            tvTimeLimit.setText(btBean.getReObj().getTimeLimit());
        }
        //额度进度条动画
        circleBarView.setOnAnimationListener(new CircleBarView.OnAnimationListener() {
            @Override
            public String howToChangeText(float interpolatedTime, float progressNum, float maxNum) {
                return "";
            }

            @Override
            public void howTiChangeProgressColor(Paint paint, float interpolatedTime, float updateNum, float maxNum) {
                if (updateNum < 0.00) {
                    tvPrice.setText(updateNum + "");
                    return;
                }
                paint.setColor(getResources().getColor(R.color.main_tab_blue_all));
                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                String s = decimalFormat.format(allPrice * interpolatedTime * updateNum / maxNum);
                tvPrice.setText(MoneyUtils.getInstance().getFormPrice(s));
                if (interpolatedTime >= 1.0) {
                    tvPrice.setText(MoneyUtils.getInstance().getFormPrice(btBean.getReObj().getIousRemainingAmount()) );
                }
                LogUtils.e(MoneyUtils.getInstance().getFormPrice(s)+"   "+MoneyUtils.getInstance().getFormPrice(currentPrice+ ""));
            }
        });
        LogUtils.e(allPrice+"");
        tvAllPrice.setText(MoneyUtils.getInstance().getFormPrice(allPrice + ""));
        circleBarView.setProgressNum( currentPrice, 2500,  allPrice);
    }

    @OnClick({R.id.ll_back, R.id.rl_check_bt, R.id.tv_sx_jl,R.id.rl_more_money, R.id.tv_use_record, R.id.rl_up_ed})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.rl_check_bt:
                //聚玻白条协议
                DialogUtils.getInstance().juBoBtXy(this, contractNo);
                break;
            //授信记录
            case R.id.tv_sx_jl:
                IntentUtils.getInstance().toActivity(this, TrustRecordActivity.class);
                break;
            //使用记录
            case R.id.tv_use_record:
                IntentUtils.getInstance().toActivity(this, UseRecordActivity.class);
                break;
            //提交额度
            case R.id.rl_up_ed:
                getTiEQua();
                break;
            //我的优惠券
            case R.id.rl_more_money:
                IntentUtils.getInstance().toActivity(getContext(),BtYhjActivity.class);
                break;
        }
    }

    //yaohi
    //提额资格验证
    public void getTiEQua() {
        OkhttpUtil.getInstance().setUnCacheData(this, "提额验证中", UrlConstant.getInstance().URL
                + PostConstant.getInstance().TI_E_QUA
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    IntentUtils.getInstance().toActivity(JuBoBtEdActivity.this, JuBoBtActivity.class,"提额");
                } else {
                    ToastUtils.getMineToast(successBean.getErrMsg());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }
}
