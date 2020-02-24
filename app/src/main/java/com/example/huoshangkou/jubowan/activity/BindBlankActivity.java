package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.BankHangBean;
import com.example.huoshangkou.jubowan.bean.BtXyBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.CityNameInterface;
import com.example.huoshangkou.jubowan.inter.OnSignImageCallBack;
import com.example.huoshangkou.jubowan.inter.PickPositonCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.CityUtils;
import com.example.huoshangkou.jubowan.utils.DialogUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.KeyBoardUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.PickDialogUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.SignDialogUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：BindBlankActivity
 * 描述：
 * 创建时间：2018-08-21  10:37
 */

public class BindBlankActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_account_name)
    EditText etAccountName;
    @Bind(R.id.et_account)
    EditText etAccount;
    @Bind(R.id.et_account_bank)
    TextView etAccountBank;
    @Bind(R.id.et_account_hang)
    TextView etAccountHang;
    @Bind(R.id.tv_account_address)
    TextView tvAccountAddress;
    @Bind(R.id.et_fa_man)
    EditText etFaMan;
    @Bind(R.id.et_card_no)
    EditText etCardNo;
    @Bind(R.id.et_mail_address)
    EditText etMailAddress;
    @Bind(R.id.cb_agree)
    CheckBox cbAgree;

    //账户名
    private String accountName = "";
    //账号
    private String account = "";
    //开户银行
    private String accountBank = "";
    //开户支行
    private String accountHang = "";
    //企业统一识别码
    private String code = "";
    //营业执照上传
    private String yyCodePic = "";
    //手机号
    private String phone = "";
    //邮箱
    private String eMail = "";
    //开户地址
    private String city = "";
    //城市
    private String province = "";
    //法人身份证号
    private String faManId = "";
    //法人姓名
    private String comFaMan = "";
    //邮寄地址
    private String mailAddress = "";
    //是否勾选协议阅读
    private boolean isAgree = false;

    private String addressTag = "";
    private String bankTag = "";

    private ArrayList<String> listBank = new ArrayList<>();
    private ArrayList<String> listHang = new ArrayList<>();

    private String ruleService = "";
    private String ruleCredit = "";

    @Override
    public int initLayout() {
        return R.layout.activity_bind_blank;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().btList.add(this);
        tvTitle.setText("绑定银行卡");
        code = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        yyCodePic = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        phone = getIntent().getStringExtra(IntentUtils.getInstance().STR);
        eMail = getIntent().getStringExtra(IntentUtils.getInstance().TXT);

        cbAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isAgree = b;
            }
        });

        //设置默认信息
        String bankMessage = (String) SharedPreferencesUtils.getInstance().get(this, SharedKeyConstant.getInstance().BIND_BANK_MSG, "");
        LogUtils.i(bankMessage);
        if (StringUtils.isNoEmpty(bankMessage) && !bankMessage.contains("message")) {
            bankMessage += ",message";
        }
        if (StringUtils.isNoEmpty(bankMessage)) {
            String[] split = bankMessage.split(",");
            if (split.length == 0) {
                return;
            }

            etAccountName.setText(split[0]);
            etAccount.setText(split[1]);
            city = split[2];
            province = split[3];
            tvAccountAddress.setText(split[3] + " " + split[2]);
            accountBank = split[4];
            etAccountBank.setText(split[4]);
            accountHang = split[5];
            etAccountHang.setText(split[5]);
            etCardNo.setText(split[7]);
            etFaMan.setText(split[6]);
            etMailAddress.setText(split[8]);
        }
    }

    @OnClick({R.id.tv_next, R.id.ll_back, R.id.ll_address, R.id.et_account_hang, R.id.et_account_bank, R.id.tv_xy_two, R.id.tv_xy_one})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                saveMessage();
                this.finish();
                break;
            case R.id.tv_next:
                accountName = etAccountName.getText().toString().trim();
                if (!StringUtils.isNoEmpty(accountName)) {
                    ToastUtils.getMineToast("请输入账户名");
                    return;
                }
                account = etAccount.getText().toString().trim();
                if (!StringUtils.isNoEmpty(account)) {
                    ToastUtils.getMineToast("请输入账号");
                    return;
                }
                accountBank = etAccountBank.getText().toString().trim();
                if (!StringUtils.isNoEmpty(accountBank)) {
                    ToastUtils.getMineToast("请输入开户银行");
                    return;
                }
                faManId = etCardNo.getText().toString().trim();
                if (!StringUtils.isNoEmpty(faManId)) {
                    ToastUtils.getMineToast("请输入法人身份证号");
                    return;
                }
                comFaMan = etFaMan.getText().toString().trim();
                if (!StringUtils.isNoEmpty(comFaMan)) {
                    ToastUtils.getMineToast("请输入公司法人");
                    return;
                }
                if (!StringUtils.isNoEmpty(city)) {
                    ToastUtils.getMineToast("请选择开户地址");
                    return;
                }
                accountHang = etAccountHang.getText().toString().trim();
                if (!StringUtils.isNoEmpty(accountHang)) {
                    ToastUtils.getMineToast("请输入开户支行");
                    return;
                }
                mailAddress = etMailAddress.getText().toString().trim();
                if (!StringUtils.isNoEmpty(mailAddress)) {
                    ToastUtils.getMineToast("请输入邮寄地址");
                    return;
                }
                if (!isAgree) {
                    ToastUtils.getMineToast("请阅读并同意相关协议");
                    return;
                }
                SignDialogUtils.getInstance().updateDialog(getContext(), new OnSignImageCallBack() {
                    @Override
                    public void onSignSuccess(final String path) {
                        //签名地址
                        upWorkPic(code, yyCodePic, phone, accountName, account, accountBank, accountHang, path, eMail);
                    }
                });
                break;
            case R.id.ll_address:
                IntentUtils.getInstance().toActivity(this, AreaActivity.class);
                break;
            case R.id.et_account_hang:
                if (!StringUtils.isNoEmpty(province) || !StringUtils.isNoEmpty(city)) {
                    ToastUtils.getMineToast("请先选择开户地址");
                    return;
                }
                if (!StringUtils.isNoEmpty(accountBank)) {
                    ToastUtils.getMineToast("请先选择开户银行");
                    return;
                }
                getBankList(province, city, "2", accountBank);


                break;
            case R.id.et_account_bank:
                if (!StringUtils.isNoEmpty(province) || !StringUtils.isNoEmpty(city)) {
                    ToastUtils.getMineToast("请先选择开户地址");
                    return;
                }

                getBankList(province, city, "1", "");

//                PickDialogUtils.getInstance().getChooseDialog(this, "开户银行", listBank, new PickPositonCallBack() {
//                    @Override
//                    public void onClickSuccess(String choose, int position) {
//                        accountBank = choose;
//                        etAccountBank.setText(accountBank);
//                        getBankList(province, city, "2", accountBank);
//                    }
//                });
                break;
            //白条赊购服务协议
            case R.id.tv_xy_one:
                accountName = etAccountName.getText().toString().trim();
                if (!StringUtils.isNoEmpty(accountName)) {
                    ToastUtils.getMineToast("请输入账户名");
                    return;
                }
                faManId = etCardNo.getText().toString().trim();
                if (!StringUtils.isNoEmpty(faManId)) {
                    ToastUtils.getMineToast("请输入法人身份证号");
                    return;
                }
                getXyRule("3", "1");
                break;
            //送达协议
            case R.id.tv_xy_two:
                accountName = etAccountName.getText().toString().trim();
                if (!StringUtils.isNoEmpty(accountName)) {
                    ToastUtils.getMineToast("请输入账户名");
                    return;
                }
                faManId = etCardNo.getText().toString().trim();
                if (!StringUtils.isNoEmpty(faManId)) {
                    ToastUtils.getMineToast("请输入法人身份证号");
                    return;
                }
                getXyRule("3", "2");
                break;
        }
    }

    //执照资料上传
    public void upWorkPic(String code, String pic, String phone, String account, String accountNum, String openBank, String branch, String authPic, String email) {
        OkhttpUtil.getInstance().setUnCacheData(this, "资料提交中", UrlConstant.getInstance().URL
                + PostConstant.getInstance().SUBMISSION + FieldConstant.getInstance().TYPE + "=1" + "&"
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().CODE + "=" + EncodeUtils.getInstance().getEncodeString(code) + "&"
                + FieldConstant.getInstance().PIC + "=" + EncodeUtils.getInstance().getEncodeString(pic) + "&"
                + FieldConstant.getInstance().ACCOUNT_NAME + "=" + EncodeUtils.getInstance().getEncodeString(account) + "&"
                + FieldConstant.getInstance().PHONE_NUMBER + "=" + EncodeUtils.getInstance().getEncodeString(phone) + "&"
                + FieldConstant.getInstance().ACCOUNT_NUMBER + "=" + EncodeUtils.getInstance().getEncodeString(accountNum) + "&"
                + FieldConstant.getInstance().BRANCH + "=" + EncodeUtils.getInstance().getEncodeString(branch) + "&"
                + FieldConstant.getInstance().OPENING_BANK + "=" + EncodeUtils.getInstance().getEncodeString(openBank) + "&"
                + FieldConstant.getInstance().EMAIL + "=" + EncodeUtils.getInstance().getEncodeString(email) + "&"
                + FieldConstant.getInstance().PROVINCE_NO_S + "=" + EncodeUtils.getInstance().getEncodeString(province) + "&"
                + FieldConstant.getInstance().CITY + "=" + EncodeUtils.getInstance().getEncodeString(city) + "&"
                + FieldConstant.getInstance().LEGAL_PERSON + "=" + EncodeUtils.getInstance().getEncodeString(comFaMan) + "&"
                + FieldConstant.getInstance().ADDRESS + "=" + EncodeUtils.getInstance().getEncodeString(mailAddress) + "&"
                + FieldConstant.getInstance().ID_CARD + "=" + EncodeUtils.getInstance().getEncodeString(faManId) + "&"
                + FieldConstant.getInstance().AUTH_PIC + "=" + EncodeUtils.getInstance().getEncodeString(authPic), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                LogUtils.i("upWorkPic   " + json);
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    SharedPreferencesUtils.getInstance().put(BindBlankActivity.this, SharedKeyConstant.getInstance().BIND_BANK_MSG, "");
                    DialogUtils.getInstance().commitSuccess(BindBlankActivity.this);
                } else {
                    ToastUtils.getMineToast(successBean.getErrMsg());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    //获取银行列表或支行列表
    public void getBankList(String province, String city, String type, final String bankName) {
        OkhttpUtil.getInstance().setUnCacheData(this, "数据加载中", UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_IOUS_BANK
                + FieldConstant.getInstance().PROVINCE_NO_S + "=" + EncodeUtils.getInstance().getEncodeString(province) + "&"
                + FieldConstant.getInstance().TYPE + "=" + EncodeUtils.getInstance().getEncodeString(type) + "&"
                + FieldConstant.getInstance().BANK_NAME + "=" + EncodeUtils.getInstance().getEncodeString(bankName) + "&"
                + FieldConstant.getInstance().CITY + "=" + EncodeUtils.getInstance().getEncodeString(city), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                BankHangBean bankHangBean = JSON.parseObject(json, BankHangBean.class);
                if (!StringUtils.isNoEmpty(bankName)) {
//                    if(listBank.size()!=0){
                    listBank.clear();
//                    }
                    listBank.addAll(bankHangBean.getReObj());
                    Intent intent2 = new Intent(BindBlankActivity.this, AreaActivity.class);
                    intent2.putExtra(IntentUtils.getInstance().BEAN_TYPE, listBank);
                    intent2.putExtra(IntentUtils.getInstance().VALUE, "开户银行");
                    startActivityForResult(intent2, 2);
                } else {
//                    if(listHang.size()!=0){
                    listHang.clear();
//                    }
                    listHang.addAll(bankHangBean.getReObj());
                    Intent intent = new Intent(BindBlankActivity.this, AreaActivity.class);
                    intent.putExtra(IntentUtils.getInstance().BEAN_TYPE, listHang);
                    intent.putExtra(IntentUtils.getInstance().VALUE, "开户支行");
                    startActivityForResult(intent, 1);
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    //获取相关协议
    public void getXyRule(final String type, final String types) {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(this, UrlConstant.getInstance().URL
                + PostConstant.getInstance().IOUS_AGREEMENT_URL
                + FieldConstant.getInstance().TYPE + "=" + type + "&"
                + FieldConstant.getInstance().COMPANY_BIG_NAME + "=" + EncodeUtils.getInstance().getEncodeString(accountName) + "&"
                + FieldConstant.getInstance().ID_CARD + "=" + EncodeUtils.getInstance().getEncodeString(faManId) + "&"
                + FieldConstant.getInstance().AGREEMENT_CODE + "=", new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String rule = jsonObject.getString("ReObj");
                    BtXyBean btXyBean = com.alibaba.fastjson.JSONObject.parseObject(rule, BtXyBean.class);
                    ruleService = btXyBean.getDigitalCertificateUrl();
                    ruleCredit = btXyBean.getESingleUserUrl();

                    switch (types) {
                        case "1":
                            IntentUtils.getInstance().toWebActivity(BindBlankActivity.this, ruleCredit, "e签宝用户协议");
                            break;
                        case "2":
                            IntentUtils.getInstance().toWebActivity(BindBlankActivity.this, ruleService, "数字证书服务协议");
                            break;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
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
        if (data == null) {
            return;
        }
        switch (requestCode) {
            case 1:
                accountHang = data.getStringExtra(IntentUtils.getInstance().TYPE);
                etAccountHang.setText(accountHang);
                break;
            case 2:
                accountBank = data.getStringExtra(IntentUtils.getInstance().TYPE);
                etAccountBank.setText(accountBank);
                if (!bankTag.equals(accountBank)) {
                    accountHang = "";
                    etAccountHang.setText(accountHang);
                }
                bankTag = accountBank;

                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
            saveMessage();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    //是否保存该草稿信息
    public void saveMessage() {
        accountName = etAccountName.getText().toString().trim();
        account = etAccount.getText().toString().trim();
        accountBank = etAccountBank.getText().toString().trim();
        faManId = etCardNo.getText().toString().trim();
        comFaMan = etFaMan.getText().toString().trim();
        accountHang = etAccountHang.getText().toString().trim();
        mailAddress = etMailAddress.getText().toString().trim();

        String bankMessage = accountName + "," + account + "," + city + "," + province + "," + accountBank + "," + accountHang + "," + comFaMan + "," + faManId + "," + mailAddress + ",message";
        SharedPreferencesUtils.getInstance().put(this, SharedKeyConstant.getInstance().BIND_BANK_MSG, bankMessage);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String jbArea = (String) SharedPreferencesUtils.getInstance().get(this, "jbArea", "");
        if (StringUtils.isNoEmpty(jbArea)) {
            String[] split = jbArea.split(",");
            if (!addressTag.equals(jbArea)) {
                accountBank = "";
                etAccountBank.setText("");
                accountHang = "";
                etAccountHang.setText(accountHang);
            }
            addressTag = jbArea;
            if (split.length == 2) {
                province = split[0];
                city = split[1];
                listBank.clear();
                tvAccountAddress.setText(province + " " + city);
            }
        }
        SharedPreferencesUtils.getInstance().put(this, "jbArea", "");
    }
}
