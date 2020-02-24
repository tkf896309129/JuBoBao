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
 * 类名：AllPriceActivity
 * 描述：
 * 创建时间：2017-08-11  15:35
 */

public class AllPriceActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.et_tool_price)
    EditText etToolPrice;
    @Bind(R.id.et_hour_price)
    EditText etHourPrice;
    @Bind(R.id.et_trans_price)
    EditText etTransPrice;
    @Bind(R.id.et_other_price)
    EditText etOtherPrice;
    @Bind(R.id.et_remark_1)
    EditText etRemark1;
    @Bind(R.id.et_remark_2)
    EditText etRemark2;

    //材料费用
    private String toolPrice = "";
    //工时费
    private String hourPrice = "";
    //差旅费
    private String workPrice = "";
    //其他费用
    private String otherPrice = "";
    //电费
    private String powerPrice = "";
    //月厂说明
    private String monthRemark = "";

    String allPrice = "";
    String electPro = "";
    String monthPro = "";

    @Override
    public int initLayout() {
        return R.layout.activity_all_price;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("请详细描述服务费用合计");
        tvRight.setText("保存");

        allPrice = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        electPro = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        monthPro = getIntent().getStringExtra(IntentUtils.getInstance().STR);

        setData(allPrice);
    }

    private void setData(String str) {
        if (!StringUtils.isNoEmpty(str)) {
            return;
        }
        String[] prices = str.split(",");

        etToolPrice.setText(prices[0]);
        etHourPrice.setText(prices[1]);
        etTransPrice.setText(prices[2]);
        etOtherPrice.setText(prices[3]);

        etRemark1.setText(electPro);
        etRemark2.setText(monthPro);
    }


    @OnClick({R.id.ll_back, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_right:
                toolPrice = etToolPrice.getText().toString().trim();
                if (!StringUtils.isNoEmpty(toolPrice)) {
                    ToastUtils.getMineToast("请输入材料费用");
                    return;
                }

                hourPrice = etHourPrice.getText().toString().trim();
                if (!StringUtils.isNoEmpty(hourPrice)) {
                    ToastUtils.getMineToast("请输入工时费");
                    return;
                }

                workPrice = etTransPrice.getText().toString().trim();
                if (!StringUtils.isNoEmpty(workPrice)) {
                    ToastUtils.getMineToast("请输入差旅费");
                    return;
                }

                otherPrice = etOtherPrice.getText().toString().trim();
                if (!StringUtils.isNoEmpty(otherPrice)) {
                    ToastUtils.getMineToast("请输入其他费用");
                    return;
                }

                powerPrice = etRemark1.getText().toString().trim();
                if (!StringUtils.isNoEmpty(powerPrice)) {
                    ToastUtils.getMineToast("请输入电费说明");
                    return;
                }

                monthRemark = etRemark2.getText().toString().trim();
                if (!StringUtils.isNoEmpty(monthRemark)) {
                    ToastUtils.getMineToast("请输入月产能说明");
                    return;
                }

                String baseMessage = toolPrice
                        + ":" + hourPrice
                        + ":" + workPrice
                        + ":" + otherPrice
                        + ":" + powerPrice
                        + ":" + monthRemark;

                Intent intent2 = new Intent();
                intent2.putExtra(IntentUtils.getInstance().TYPE, baseMessage);
                setResult(5, intent2);
                this.finish();

                break;
        }
    }


}
