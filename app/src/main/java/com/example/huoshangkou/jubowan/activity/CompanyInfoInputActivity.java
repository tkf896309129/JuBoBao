package com.example.huoshangkou.jubowan.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.BtXyBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.DialogUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SendCodeUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：CompanyInfoInputActivity
 * 描述：
 * 创建时间：2018-08-23  16:10
 */

public class CompanyInfoInputActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_apply_man)
    EditText etApplyMan;
    @Bind(R.id.et_product_model)
    EditText etProductModel;
    @Bind(R.id.et_man_qua)
    EditText etManQua;
    @Bind(R.id.cb_agree)
    CheckBox cbAgree;

    @Bind(R.id.et_code)
    EditText etCode;
    @Bind(R.id.tv_send_code)
    TextView tvSendCode;

    private String applyMan = "";
    private String scGuiMo = "";
    private String humanQua = "";
    private String getCode = "";
    //是否同意协议
    private boolean isAgreeRule = false;
    private String ruleService = "";
    private String ruleCharge = "";
    private String ruleCredit = "";
    int codeTime = 0;

    @Override
    public int initLayout() {
        return R.layout.activity_company_info_input;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("企业信息输入");

        cbAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isAgreeRule = b;
            }
        });
        getXyRule("1", "");
    }

    @OnClick({R.id.tv_confirm_commit, R.id.ll_back, R.id.tv_xy_one, R.id.tv_xy_two, R.id.tv_xy_three,R.id.tv_send_code})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_confirm_commit:
//                applyCom = etApplyDw.getText().toString().trim();
//                if (!StringUtils.isNoEmpty(applyCom)) {
//                    ToastUtils.getMineToast("请输入申请单位");
//                    return;
//                }
                applyMan = etApplyMan.getText().toString().trim();
                if (!StringUtils.isNoEmpty(applyMan)) {
                    ToastUtils.getMineToast("请输入申请人");
                    return;
                }
                scGuiMo = etProductModel.getText().toString().trim();
                if (!StringUtils.isNoEmpty(scGuiMo)) {
                    ToastUtils.getMineToast("请输入生产规模");
                    return;
                }
                humanQua = etManQua.getText().toString().trim();
                if (!StringUtils.isNoEmpty(humanQua)) {
                    ToastUtils.getMineToast("请输入负债情况");
                    return;
                }
                getCode = etCode.getText().toString().trim();
                if (!StringUtils.isNoEmpty(getCode)) {
                    ToastUtils.getMineToast("请获取验证码");
                    return;
                }
                if (!isAgreeRule) {
                    ToastUtils.getMineToast("请阅读并同意相关协议");
                    return;
                }
                upWorkPic();
                break;
            //白条赊购服务协议
            case R.id.tv_xy_one:
                IntentUtils.getInstance().toWebActivity(this, ruleCredit, "白条赊购服务协议");
                break;
            //送达协议
            case R.id.tv_xy_two:
                IntentUtils.getInstance().toWebActivity(this, ruleService, "送达协议");
                break;
            //白条服务费用计算规则
            case R.id.tv_xy_three:
                IntentUtils.getInstance().toWebActivity(this, ruleCharge, "白条服务费用计算规则");
                break;
            case R.id.tv_send_code:
                codeTime = 60;
                SendCodeUtils.getInstance().setYiQianCode(this, tvSendCode, PersonConstant.getInstance().getUserId());
                break;
        }
    }


    //企业信息输入
    public void upWorkPic() {
        OkhttpUtil.getInstance().setUnCacheData(this, "资料提交中", UrlConstant.getInstance().URL
                + PostConstant.getInstance().SUBMISSION + FieldConstant.getInstance().TYPE + "=3" + "&"
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().LINK_MAN + "=" + EncodeUtils.getInstance().getEncodeString(applyMan) + "&"
                + FieldConstant.getInstance().MOBIE_CODE + "=" + EncodeUtils.getInstance().getEncodeString(getCode) + "&"
//                + FieldConstant.getInstance().APPLY_UNIT + "=" + EncodeUtils.getInstance().getEncodeString(applyCom) + "&"
                + FieldConstant.getInstance().SCALE + "=" + EncodeUtils.getInstance().getEncodeString(scGuiMo) + "&"
                + FieldConstant.getInstance().PERSON_SIT + "=" + EncodeUtils.getInstance().getEncodeString(humanQua) + "&", new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    ToastUtils.getMineToast("提交成功");
                    CompanyInfoInputActivity.this.finish();
                } else {
                    ToastUtils.getMineToast(successBean.getErrMsg());
                }
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
                    String rule = jsonObject.getString("ReObj");
                    BtXyBean btXyBean = com.alibaba.fastjson.JSONObject.parseObject(rule, BtXyBean.class);
                    ruleService = btXyBean.getServiceConfirmation();
                    ruleCharge = btXyBean.getChargeRules();
                    ruleCredit = btXyBean.getCreditService();
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
