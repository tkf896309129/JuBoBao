package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ZbResultBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.inter.StringPositionCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.DialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：ZbPriceActivity
 * 描述：
 * 创建时间：2019-04-03  14:09
 */

public class ZbPriceActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_type)
    TextView tvType;
    @Bind(R.id.cb_agree)
    CheckBox cbAgree;
    @Bind(R.id.et_price)
    EditText etPrice;
    @Bind(R.id.et_remark)
    EditText etRemark;

    private String id = "";
    private String remark = "";
    private String brand = "";
    private String price = "";
    private boolean isAgree = false;
    private ArrayList<String> listBrand = new ArrayList<>();

    @Override
    public int initLayout() {
        return R.layout.activity_zb_price;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this,ActivityUtils.getInstance().newZbList);
        tvTitle.setText("报价");
        listBrand.add("幕墙招标");
        listBrand.add("系统门窗");
        listBrand.add("光伏幕墙");
        listBrand.add("门窗定制招标");
        id = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        brand = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        tvType.setText(brand);
        cbAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isAgree = b;
            }
        });
    }

    @OnClick({R.id.ll_back, R.id.tv_rule_jb, R.id.rl_type, R.id.tv_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_rule_jb:
                IntentUtils.getInstance().toWebActivity(this, UrlConstant.getInstance().SERVICE_URL, "聚玻宝用户协议");
                break;
            case R.id.rl_type:
//                DialogUtils.getInstance().getBaseDialog(this, listBrand, new StringPositionCallBack() {
//                    @Override
//                    public void onStringPosition(String str, int position) {
//                        brand = str;
//                        tvType.setText(str);
//                    }
//                });
                break;
            case R.id.tv_commit:
                if (!StringUtils.isNoEmpty(brand)) {
                    ToastUtils.getMineToast("请选择项目类型");
                    return;
                }
                price = etPrice.getText().toString().trim();
                if (!StringUtils.isNoEmpty(price)) {
                    ToastUtils.getMineToast("请输入项目报价");
                    return;
                }
                remark = etRemark.getText().toString().trim();
                if (!isAgree) {
                    ToastUtils.getMineToast("请阅读并同意聚玻宝用户协议");
                    return;
                }
                getYuanFuData();
                break;
        }
    }


    //原片数据
    public void getYuanFuData() {
        Map<String, String> map = new HashMap<>();
        map.put("RequestID", id);
        map.put("FromUserID", PersonConstant.getInstance().getUserId());
        map.put("ToBrand", brand);
        map.put("ToBrandPrice", price);
        map.put("Intro", remark);
        map.put("IsSendYp", "0");
        String json = JSON.toJSONString(map);
        String putJson = "{\"model\":" + json + "}";
        LogUtils.e(putJson);
        OkhttpUtil.getInstance().basePostCall(this,putJson, UrlConstant.getInstance().YUAN_FU_URL + "AddRequestTo", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                ZbResultBean resultBean = JSON.parseObject(str,ZbResultBean.class);
                //成功
                if(resultBean.getD().getErrorCode()==0){
                    ToastUtils.getMineToast("提交成功");
                    ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().newZbList);
                }else {
                    ToastUtils.getMineToast(resultBean.getD().getMsg());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }
}
