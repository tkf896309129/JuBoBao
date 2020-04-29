package com.example.huoshangkou.jubowan.activity;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.BankBean;
import com.example.huoshangkou.jubowan.bean.BankObjBean;
import com.example.huoshangkou.jubowan.bean.BtXyBean;
import com.example.huoshangkou.jubowan.bean.RongZiInfoBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.ChooseDialogCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.PickDialogUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：CompanyMoneyActivity
 * 描述：
 * 创建时间：2017-03-20  15:41
 */

public class CompanyMoneyActivity extends BaseActivity {
    @Bind(R.id.et_money_man)
    EditText etMoneyMan;
    @Bind(R.id.et_card_no)
    EditText etCardNo;
    @Bind(R.id.et_company_name)
    EditText etCompanyName;
    private ArrayList<String> bankList;
    @Bind(R.id.tv_bank)
    TextView tvBank;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_borrow_money)
    TextView tvBorrowMoney;

    private String realName = "";
    private String idCard = "";
    private String companyName = "";
    private String price = "";
    private String bankName = "";


    @Override
    public int initLayout() {
        return R.layout.activity_company_money;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().orderList);
        bankList = new ArrayList<>();

        tvTitle.setText("融资支付");
        price = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        String prices = "￥" + price;
        int colorPosition = (prices).indexOf(".");
        int linePosition = prices.length();

        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append(prices);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.main_tab_blue_all)), 1, linePosition, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        //字体大小
        AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(28);
        AbsoluteSizeSpan sizeSpan2 = new AbsoluteSizeSpan(28);
        spannableString.setSpan(sizeSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        spannableString.setSpan(sizeSpan2, colorPosition, linePosition, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        tvBorrowMoney.setText(spannableString);
        getBank();
        getDefaultMessage();


    }


    //点击事件
    @OnClick({R.id.rl_choose_bank, R.id.ll_back, R.id.tv_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_choose_bank:
                PickDialogUtils.getInstance().getChooseDialog(getContext(), "—请选择银行—", bankList, new ChooseDialogCallBack() {
                    @Override
                    public void onClickSuccess(String choose) {
                        bankName = choose;
                        tvBank.setText(bankName + "  ");
                    }
                });
                break;
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_commit:
                if (!StringUtils.isNoEmpty(bankName)) {
                    ToastUtils.getMineToast("请选择借款银行");
                    return;
                }
                realName = etMoneyMan.getText().toString().trim();
                if (!StringUtils.isNoEmpty(realName)) {
                    ToastUtils.getMineToast("请输入借款人姓名");
                    return;
                }

                idCard = etCardNo.getText().toString().trim();
                if (!StringUtils.isNoEmpty(idCard)) {
                    ToastUtils.getMineToast("请输入身份证号");
                    return;
                }

                companyName = etCompanyName.getText().toString().trim();
                if (!StringUtils.isNoEmpty(companyName)) {
                    ToastUtils.getMineToast("请输入公司名称");
                    return;
                }

                companyMoney();
                break;

        }
    }


    //获取银行
    public void getBank() {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_BANK, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                BankBean bankBean = JSON.parseObject(json, BankBean.class);
                List<BankObjBean> reList = bankBean.getReList();
                int num = reList.size();
                for (int i = 0; i < num; i++) {
                    bankList.add(reList.get(i).getRealName());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    //企业融资贷款
    public void companyMoney() {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().ADD_RONGZI_APPLY + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().LOAN_MONEY + "=" + price + "&"
                + FieldConstant.getInstance().LINK_MAN + "=" + EncodeUtils.getInstance().getEncodeString(realName) + "&"
                + FieldConstant.getInstance().PIC_FAREN + "=" + EncodeUtils.getInstance().getEncodeString(idCard) + "&"
                + FieldConstant.getInstance().COMPANY + "=" + EncodeUtils.getInstance().getEncodeString(companyName) + "&"
                + FieldConstant.getInstance().LOCAN_BANK + "=" + EncodeUtils.getInstance().getEncodeString(bankName), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    ToastUtils.getMineToast("提交完成");
                    EventBus.getDefault().post("initOrder", "initOrder");
                    ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().orderList);
                } else {
                    CopyIosDialogUtils.getInstance().getErrorDialog(getContext(), successBean.getErrMsg(), new CopyIosDialogUtils.ErrDialogCallBack() {
                        @Override
                        public void confirm() {

                        }
                    });
                }
            }

            @Override
            public void onFail() {
                ToastUtils.getMineToast("提交失败");
            }
        });
    }

    //获取默认信息
    public void getDefaultMessage() {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().RZ_DEFAULT_MESSAGE + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                RongZiInfoBean infoBean = JSON.parseObject(json, RongZiInfoBean.class);
                RongZiInfoBean.ReObjBean reObj = infoBean.getReObj();
                etCompanyName.setText(reObj.getCompanyName());
                etMoneyMan.setText(reObj.getRealName());
            }

            @Override
            public void onFail() {

            }
        });
    }

}
