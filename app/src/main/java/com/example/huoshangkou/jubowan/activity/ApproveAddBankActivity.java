package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ApproveBankListBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：ApproveAddBankActivity
 * 描述：审批界面增加银行信息
 * 创建时间：2017-03-21  11:37
 */

public class ApproveAddBankActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_company_name)
    EditText etCompanyName;
    @Bind(R.id.et_bank_account)
    EditText etBankAccount;
    @Bind(R.id.et_bank_name)
    EditText etBankName;

    private ApproveBankListBean bankListBean;

    //是否是添加银行信
    private boolean isAddBank = true;

    private String companyName = "";
    private String bankName = "";
    private String bankAccount = "";

    @Override
    public int initLayout() {
        return R.layout.activity_add_bank_info;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("增加银行信息");

        bankListBean = (ApproveBankListBean) getIntent().getSerializableExtra(IntentUtils.getInstance().BANK_INFO);
        if (bankListBean != null) {
            etBankAccount.setText(bankListBean.getBankAccount());
            etBankName.setText(bankListBean.getBankName());
            etCompanyName.setText(bankListBean.getCompany());
            etCompanyName.setSelection(bankListBean.getCompany().length());
            isAddBank = false;
            tvTitle.setText("编辑银行信息");
        }
    }

    //点击事件
    @OnClick({R.id.ll_back, R.id.tv_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            //保存
            case R.id.tv_save:

                companyName = etCompanyName.getText().toString().trim();
                if (!StringUtils.isNoEmpty(companyName)) {
                    ToastUtils.getMineToast("请输入公司名称");
                    return;
                }

                bankAccount = etBankAccount.getText().toString().trim();
                if (!StringUtils.isNoEmpty(bankAccount)) {
                    ToastUtils.getMineToast("请输入银行账号");
                    return;
                }

                bankName = etBankName.getText().toString().trim();
                if (!StringUtils.isNoEmpty(bankName)) {
                    ToastUtils.getMineToast("请输入银行名称");
                    return;
                }
                String hint = isAddBank ? "是否添加" : "是否修改";
                CopyIosDialogUtils.getInstance().getIosDialog(getContext(),hint, new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        onAddBankInfo(companyName, bankName, bankAccount);
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
                break;
        }
    }

    //修改信息
    private void onAddBankInfo(String company, String bankName, String bankAccount) {
        String id = "";
        if (isAddBank) {
            id = "";
        } else {
            id = bankListBean.getID() + "";
        }
        OkhttpUtil.getInstance().setUnCacheData(this, getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().EDIT_BANK + FieldConstant.getInstance().ID + "=" + id + "&"
                + FieldConstant.getInstance().COMPANY + "=" + EncodeUtils.getInstance().getEncodeString(company) + "&"
                + FieldConstant.getInstance().BANK_NAME + "=" + EncodeUtils.getInstance().getEncodeString(bankName) + "&"
                + FieldConstant.getInstance().BANK_ACCOUNT + "=" + EncodeUtils.getInstance().getEncodeString(bankAccount) + "&"
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    String hint = isAddBank ? "添加成功" : "修改成功";
                    ToastUtils.getMineToast(hint);
                    ApproveAddBankActivity.this.finish();
                    SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().BANK_EDIT, "yes");
                }
            }

            @Override
            public void onFail() {

            }
        });
    }
}
