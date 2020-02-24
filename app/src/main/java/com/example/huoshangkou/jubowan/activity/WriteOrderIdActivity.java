package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：WriteOrderIdActivity
 * 描述：
 * 创建时间：2018-06-12  15:34
 */

public class WriteOrderIdActivity extends BaseActivity {

    @Bind(R.id.et_input_id)
    EditText etOrderId;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;

    @Override
    public int initLayout() {
        return R.layout.activity_write_order;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("填写订单号");
        tvRight.setText("提交");

    }

    @OnClick({R.id.ll_back, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_right:
                String orderid = etOrderId.getText().toString().trim();
                if (!StringUtils.isNoEmpty(orderid)) {
                    ToastUtils.getMineToast("请输入订单号");
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra(IntentUtils.getInstance().ORDER_ID, orderid);
                setResult(2, intent);
                this.finish();
                break;
        }
    }
}
