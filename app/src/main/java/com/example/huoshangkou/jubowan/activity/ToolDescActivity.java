package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：ToolDescActivity
 * 描述：
 * 创建时间：2017-08-11  15:36
 */

public class ToolDescActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.et_tool_name)
    EditText etToolName;
    @Bind(R.id.et_tool_num)
    EditText etToolNum;
    @Bind(R.id.et_tool_company)
    EditText etToolCompany;
    @Bind(R.id.et_num)
    EditText etNum;
    @Bind(R.id.et_price)
    EditText etPrice;
    @Bind(R.id.et_all_price)
    EditText etAllPrice;

    //配件名称
    private String toolName = "";
    //配件型号
    private String toolNum = "";
    //单位
    private String company = "";
    //数量
    private String num = "";
    //单价
    private String price = "";
    //总价
    private String allPrice = "";

    private String accDetail = "";

    @Override
    public int initLayout() {
        return R.layout.activity_tool_desc;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvRight.setText("保存");
        tvTitle.setText("配件情况");

        accDetail = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);

        setData(accDetail);
    }

    private void setData(String accDetail) {
        if (!StringUtils.isNoEmpty(accDetail)) {
            return;
        }
        String[] desc_2s = accDetail.split(",");

        etToolName.setText( desc_2s[0]);
        etToolNum.setText( desc_2s[1]);
        etToolCompany.setText( desc_2s[2]);
        etNum.setText( desc_2s[3]);
        etPrice.setText( desc_2s[4]);
        etAllPrice.setText( desc_2s[5]);
    }


    @OnClick({R.id.ll_back, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_right:
                toolName = etToolName.getText().toString().trim();
                if (!StringUtils.isNoEmpty(toolName)) {
                    ToastUtils.getMineToast("请输入配件名称");
                    return;
                }

                toolNum = etToolNum.getText().toString().trim();
                if (!StringUtils.isNoEmpty(toolNum)) {
                    ToastUtils.getMineToast("请输入配件型号");
                    return;
                }

                company = etToolCompany.getText().toString().trim();
                if (!StringUtils.isNoEmpty(company)) {
                    ToastUtils.getMineToast("请输入单位名称");
                    return;
                }

                num = etNum.getText().toString().trim();
                if (!StringUtils.isNoEmpty(num)) {
                    ToastUtils.getMineToast("请输入数量");
                    return;
                }

                price = etPrice.getText().toString().trim();
                if (!StringUtils.isNoEmpty(price)) {
                    ToastUtils.getMineToast("请输入单价");
                    return;
                }

                allPrice = etAllPrice.getText().toString().trim();
                if (!StringUtils.isNoEmpty(allPrice)) {
                    ToastUtils.getMineToast("请输入总价");
                    return;
                }


                String baseMessage = toolName
                        + ":" + toolNum
                        + ":" + company
                        + ":" + num
                        + ":" + price
                        + ":" + allPrice;

                Intent intent2 = new Intent();
                intent2.putExtra(IntentUtils.getInstance().TYPE, baseMessage);
                setResult(3, intent2);
                this.finish();

                break;
        }
    }


}
