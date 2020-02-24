package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.LoanIntroDetailBean;
import com.example.huoshangkou.jubowan.bean.LoanIntroDetailObjBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.MoneyFormatUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：LoanIntroDetailActivity
 * 描述：
 * 创建时间：2017-09-19  10:08
 */

public class LoanIntroDetailActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;

    @Bind(R.id.tv_order_num)
    TextView tvOrderNum;
    @Bind(R.id.tv_loan_name)
    TextView tvLoanName;
    @Bind(R.id.tv_phone_num)
    TextView tvPhoneNum;
    @Bind(R.id.tv_loan_use)
    TextView tvLoanUse;
    @Bind(R.id.tv_loan_money)
    TextView tvLoanMoney;

    @Bind(R.id.tv_small_write)
    TextView tvSmallWrite;
    @Bind(R.id.tv_large_write)
    TextView tvLargeWrite;

    @Bind(R.id.tv_loan_time)
    TextView tvLoanTime;
    @Bind(R.id.tv_arrive_time)
    TextView tvArriveTime;
    @Bind(R.id.tv_month_price)
    TextView tvMonthPrice;

    @Bind(R.id.tv_rzdw)
    TextView tvRzdw;
    @Bind(R.id.tv_khh)
    TextView tvKhh;
    @Bind(R.id.tv_zh)
    TextView tvZh;


    @Bind(R.id.iv_video)
    ImageView ivVideo;


    //借据表id
    private String id = "";

    private String path = "";


    @Override
    public int initLayout() {
        return R.layout.activity_loan_intro_detail;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        id = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);


        tvTitle.setText("借款借据");
        getLoanIntroDetail();
    }

    @OnClick({R.id.ll_back, R.id.iv_video})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.iv_video:

                if (!StringUtils.isNoEmpty(path)) {
                    ToastUtils.getMineToast("暂无视频");
                    return;
                }

                Uri uri = Uri.parse(path);
                Intent intent = new Intent(this, VideoActivity.class);
                intent.putExtra("uri", uri);
                startActivity(intent);
                break;
        }
    }

    //获取借款借据详情
    public void getLoanIntroDetail() {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().RZ_DUE_BILL_DETAIL
                + FieldConstant.getInstance().ID + "=" + id + "&"
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                LoanIntroDetailBean detailBean = JSON.parseObject(json, LoanIntroDetailBean.class);

                initViewData(detailBean.getReObj());
            }

            @Override
            public void onFail() {

            }
        });
    }

    private void initViewData(LoanIntroDetailObjBean reObj) {
        tvOrderNum.setText(reObj.getOrderID() + "");
        tvLoanName.setText(reObj.getLoanLinkMan() + "");
        tvPhoneNum.setText(reObj.getLoanTel() + "");
        tvLoanUse.setText(reObj.getUsageOfLoan() + "");
        tvLoanMoney.setText(reObj.getInterestRate() + "");
        tvSmallWrite.setText(reObj.getLoanAmount() + "");
        tvLargeWrite.setText(reObj.getLoanAmountCapital());
        tvLoanTime.setText(reObj.getCreateTime() + "");
        tvArriveTime.setText(reObj.getAuditTime() + "");
        tvMonthPrice.setText(reObj.getAuditTimeYea() + "");
        path = reObj.getEnclosure();
        GlideUtils.getInstance().displayImage(reObj.getMediaPreview(), getContext(), ivVideo);

        tvKhh.setText(reObj.getOpeningBank());
        tvRzdw.setText(reObj.getAccountName());
        tvZh.setText(reObj.getAccountNumber());


    }
}
