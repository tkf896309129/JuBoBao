package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.ImageAddAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.LoanDetailBean;
import com.example.huoshangkou.jubowan.bean.LoanDetailObjBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PhotoConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.MoneyFormatUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.view.ScrollGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：LoanDetailActivity
 * 描述：
 * 创建时间：2017-08-30  13:46
 */

public class LoanDetailActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.tv_loan_id)
    TextView tvLoanId;
    @Bind(R.id.tv_loan_man)
    TextView tvLoanMan;
    @Bind(R.id.tv_borrow_man)
    TextView tvBorrowMan;
    @Bind(R.id.tv_card_no)
    TextView tvCardNo;
    @Bind(R.id.tv_company_name)
    TextView tvCompanyName;
    @Bind(R.id.tv_company_location)
    TextView tvCompanyLocation;
    @Bind(R.id.tv_company_house)
    TextView tvCompanyHouse;
    @Bind(R.id.tv_tools)
    TextView tvTools;
    @Bind(R.id.tv_standard)
    TextView tvStandard;
    @Bind(R.id.tv_loan_rest)
    TextView tvLoanRest;
    @Bind(R.id.tv_loan_extra)
    TextView tvLoanExtra;
    @Bind(R.id.tv_jq_money)
    TextView tvJqMoney;
    @Bind(R.id.tv_small_write)
    TextView tvSmallWrite;
    @Bind(R.id.tv_large_write)
    TextView tvLargeWrite;
    @Bind(R.id.tv_money_use)
    TextView tvMoneyUse;
    @Bind(R.id.tv_money_time)
    TextView tvMoneyTime;
    @Bind(R.id.tv_money_from)
    TextView tvMoneyFrom;
    @Bind(R.id.tv_money_type)
    TextView tvMoneyType;
    @Bind(R.id.iv_work_card)
    ImageView ivWorkCard;
    @Bind(R.id.grid_view)
    ScrollGridView gridView;

    private String id = "";
    //营业执照
    private String yyzz = "";

    @Override
    public int initLayout() {
        return R.layout.activity_loan_detail;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("贷款详情");
        id = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);

        getLoanDetail(id);
    }

    @OnClick({R.id.ll_back, R.id.iv_work_card})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.iv_work_card:
                List<String> imgWorkCard = new ArrayList<>();
                imgWorkCard.add(yyzz);
                IntentUtils.getInstance().toImageShowActivity(getContext(), imgWorkCard, 1);
                break;
        }
    }


    //贷款详情接口
    public void getLoanDetail(String id) {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().RZ_ORDER_DETAIL + FieldConstant.getInstance().ID + "=" + id, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                LoanDetailBean loanBean = JSON.parseObject(json, LoanDetailBean.class);
                initMoneyData(loanBean.getReObj());
            }

            @Override
            public void onFail() {

            }
        });
    }

    private void initMoneyData(LoanDetailObjBean loanBean) {

        yyzz = loanBean.getRzPicyyzz();

        tvPhone.setText(PersonConstant.getInstance().getPhone(getContext()));
        tvLoanId.setText(loanBean.getContractNO());
        tvLoanMan.setText(loanBean.getRzLender() + "");
        tvBorrowMan.setText(loanBean.getLinkMan() + "");
        tvCardNo.setText(loanBean.getLinkManCardNo() + "");
        tvCompanyName.setText(loanBean.getCompanyName() + "");
        tvCompanyLocation.setText(loanBean.getAddress() + "");
        tvCompanyHouse.setText(loanBean.getAreas() + "");
        tvTools.setText(loanBean.getShebeis() + "");
        tvStandard.setText(loanBean.getGuimo() + "");
        tvLoanRest.setText(loanBean.getOldDaikuan() + "");
        tvLoanExtra.setText(loanBean.getOldDaiyukuan() + "");
        tvJqMoney.setText(loanBean.getOldDaiLixi() + "");
        tvSmallWrite.setText(loanBean.getRzMoney() + "");

        tvLargeWrite.setText(MoneyFormatUtils.toChineseCharI(loanBean.getRzMoney()));

        tvMoneyUse.setText(loanBean.getRzYongtu() + "");
        tvMoneyTime.setText(loanBean.getAuditTimeYea() + "");
        tvMoneyFrom.setText(loanBean.getReFromMoney() + "");
        tvMoneyType.setText(loanBean.getReFromType() + "");

        GlideUtils.getInstance().displayImage(loanBean.getRzPicyyzz(), getContext(), ivWorkCard);
        List<String> imgList = PhotoUtils.getInstance().getListImage(loanBean.getRzWenjian());
        ImageAddAdapter imageAddAdapter = new ImageAddAdapter(getContext(), imgList, PhotoConstant.getInstance().IS_NO_DELETE);
        gridView.setAdapter(imageAddAdapter);


    }
}
