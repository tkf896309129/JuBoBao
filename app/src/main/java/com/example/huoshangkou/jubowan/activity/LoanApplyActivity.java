package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.inter.ChooseDialogCallBack;
import com.example.huoshangkou.jubowan.inter.CityNameInterface;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.CityUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.KeyBoardUtils;
import com.example.huoshangkou.jubowan.utils.PickDialogUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：LoanApplyActivity
 * 描述：获取指纹
 * 创建时间：2017-08-30  13:46
 */

public class LoanApplyActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Bind(R.id.et_borrow_name)
    TextView etBorrowName;
    @Bind(R.id.et_loan_name)
    EditText etLoanName;
    @Bind(R.id.et_company_name)
    EditText etCompanyName;
    @Bind(R.id.tv_location)
    TextView tvLocation;
    @Bind(R.id.et_company_place)
    TextView etCompanyPlace;
    @Bind(R.id.et_company_tool)
    EditText etCompanyTool;
    @Bind(R.id.et_company_large)
    EditText etCompanyLarge;
    @Bind(R.id.et_loan_start)
    EditText etLoanStart;
    @Bind(R.id.et_loan_out)
    EditText etLoanOut;
    @Bind(R.id.et_loan_money)
    EditText etLoanMoney;
    @Bind(R.id.et_card_id)
    EditText etCardId;
    @Bind(R.id.tv_next)
    TextView tvNext;
    @Bind(R.id.ll_next)
    LinearLayout llNext;


    //出借人
    private String borrowName = "";
    //借款人
    private String loanName = "";
    //身份证号
    private String idCard = "";
    //公司名称
    private String companyName = "";
    //公司地址
    private String companyAddress = "";
    //公司厂房
    private String companyHouse = "";
    //现有设备
    private String tools = "";
    //公司规模
    private String companyStandard = "";
    //原欠贷款余额
    private String loanExtra = "";
    //逾期贷款余额
    private String loanOut = "";
    //结欠利息
    private String loanMoney = "";

    ArrayList<String> list;


    @Override
    public int initLayout() {
        return R.layout.activity_loan_apply;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().activityLoan);
        tvTitle.setText("贷款申请");
        list = new ArrayList<>();
        list.add("自有");
        list.add("租赁");

    }

    @OnClick({R.id.ll_back, R.id.tv_next, R.id.tv_location, R.id.rl_company_place})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_next:
                borrowName = etBorrowName.getText().toString().trim();
                if (!StringUtils.isNoEmpty(borrowName)) {
                    ToastUtils.getMineToast("请输入出借人");
                    return;
                }
                loanName = etLoanName.getText().toString().trim();
                if (!StringUtils.isNoEmpty(loanName)) {
                    ToastUtils.getMineToast("请输入借款人");
                    return;
                }
                idCard = etCardId.getText().toString().trim();
                if (!StringUtils.isNoEmpty(idCard)) {
                    ToastUtils.getMineToast("请输入身份证号");
                    return;
                }
//                companyAddress = etLoanId.getText().toString().trim();
                companyName = etCompanyName.getText().toString().trim();
                if (!StringUtils.isNoEmpty(companyName)) {
                    ToastUtils.getMineToast("请输入公司名称");
                    return;
                }
                if (!StringUtils.isNoEmpty(companyAddress)) {
                    ToastUtils.getMineToast("请输入公司所在地");
                    return;
                }
//                if (!StringUtils.isNoEmpty(companyHouse)) {
//                    ToastUtils.getMineToast("请输入公司所在地");
//                    return;
//                }
//                companyHouse = etCompanyPlace.getText().toString().trim();
                if (!StringUtils.isNoEmpty(companyHouse)) {
                    ToastUtils.getMineToast("请选择公司厂房");
                    return;
                }
                tools = etCompanyTool.getText().toString().trim();
                if (!StringUtils.isNoEmpty(tools)) {
                    ToastUtils.getMineToast("请输入公司现有设备");
                    return;
                }
                companyStandard = etCompanyLarge.getText().toString().trim();
                if (!StringUtils.isNoEmpty(companyStandard)) {
                    ToastUtils.getMineToast("请输入公司规模");
                    return;
                }
                loanExtra = etLoanStart.getText().toString().trim();
                if (!StringUtils.isNoEmpty(loanExtra)) {
                    ToastUtils.getMineToast("请输入原欠贷款余额");
                    return;
                }
                loanOut = etLoanOut.getText().toString().trim();
                if (!StringUtils.isNoEmpty(loanOut)) {
                    ToastUtils.getMineToast("请输入预期贷款金额");
                    return;
                }
                loanMoney = etLoanMoney.getText().toString().trim();
                if (!StringUtils.isNoEmpty(loanMoney)) {
                    ToastUtils.getMineToast("请输入结欠利息");
                    return;
                }
                String str = borrowName + ","
                        + loanName + "," + idCard + "," + companyName + "," + companyHouse + "," + companyStandard
                        + "," + loanExtra + "," + loanOut + "," + loanMoney + "," + companyAddress + "," + tools;

                IntentUtils.getInstance().toActivity(getContext(), LoanApplyNextActivity.class, str);
                break;
            case R.id.tv_location:
                KeyBoardUtils.closeKeybord(etCardId, getContext());
                CityUtils.getInstance().getAllCity(getContext(), new CityNameInterface() {
                    @Override
                    public void getCityName(String area) {
                        companyAddress = area;
                        tvLocation.setText(area);
                    }
                });
                break;
            case R.id.rl_company_place:
                KeyBoardUtils.closeKeybord(etCardId, getContext());
                PickDialogUtils.getInstance().getChooseDialog(getContext(), "选择公司厂房性质", list, new ChooseDialogCallBack() {
                    @Override
                    public void onClickSuccess(String choose) {
//                        switch (choose) {
//                            case "自有":
//                                companyHouse = "1";
//                                break;
//                            case "租赁":
//                                companyHouse = "2";
//                                break;
//                        }
                        companyHouse = choose;
                        etCompanyPlace.setText(choose);
                    }
                });
                break;
        }
    }

}
