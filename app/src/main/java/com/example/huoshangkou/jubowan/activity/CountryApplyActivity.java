package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.CountryCheckResultBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：CountryApplyActivity
 * 描述：
 * 创建时间：2019-04-12  14:11
 */

public class CountryApplyActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_company_name)
    EditText etCompanyName;
    @Bind(R.id.et_code)
    EditText etCode;

    private String unitName = "";
    private String repeortCode = "";
    //ChildType 0 CCC认证 1 玻璃检测查询
    private String type = "";
    private boolean isCCC = false;
    private boolean isGlass = false;

    @Override
    public int initLayout() {
        return R.layout.activity_country_apply;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        type = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        String title = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        tvTitle.setText(title);
        switch (type) {
            case "0":
                isCCC = true;
                break;
            case "1":
                isGlass = true;
                break;
        }
    }

    @OnClick({R.id.ll_back, R.id.tv_check})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_check:
                unitName = etCompanyName.getText().toString().trim();
                if (!StringUtils.isNoEmpty(unitName) && isGlass) {
                    ToastUtils.getMineToast("请输入公司名称");
                    return;
                }
                repeortCode = etCode.getText().toString().trim();
                if (!StringUtils.isNoEmpty(repeortCode) && isGlass) {
                    ToastUtils.getMineToast("请输入搜索编号");
                    return;
                }
                if ((!StringUtils.isNoEmpty(repeortCode) && !StringUtils.isNoEmpty(unitName)) && isCCC) {
                    ToastUtils.getMineToast("请输入公司名称或编号");
                    return;
                }
                //ChildType 0 CCC认证 1 玻璃检测查询
                switch (type) {
                    case "0":
                        getThreeCApprove();
                        break;
                    case "1":
                        getCheckUrl();
                        break;
                }
                break;
        }
    }

    //直通国检查询链接
    public void getCheckUrl() {
        Map<String, String> map = new HashMap<>();
        map.put("unitName", unitName);
        map.put("reportCode", repeortCode);
        OkhttpUtil.getInstance().basePostCall(this, map, UrlConstant.getInstance().COUNTRY_CHECK + "GetCtcReportQueryHtml", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                CountryCheckResultBean resultBean = JSON.parseObject(str, CountryCheckResultBean.class);
                if (resultBean.getD().getErrorCode() == 0) {
                    IntentUtils.getInstance().toWebActivity(getContext(), resultBean.getD().getResult().getUrl(), resultBean.getD().getResult().getTitle());
                } else {
                    ToastUtils.getMineToast(resultBean.getD().getMsg());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    //3c认证查询
    public void getThreeCApprove() {
        Map<String, String> map = new HashMap<>();
        map.put("unitName", unitName);
        map.put("reportCode", repeortCode);
        map.put("currentUserId", PersonConstant.getInstance().getUserId());
        OkhttpUtil.getInstance().basePostCall(this, map, UrlConstant.getInstance().COUNTRY_CHECK + "GetCCCauthResultHtml", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                CountryCheckResultBean resultBean = JSON.parseObject(str, CountryCheckResultBean.class);
                if (resultBean.getD().getErrorCode() == 0) {
                    IntentUtils.getInstance().toWebActivity(getContext(), resultBean.getD().getResult().getUrl(), resultBean.getD().getResult().getTitle());
                } else {
                    ToastUtils.getMineToast(resultBean.getD().getMsg());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }
}
