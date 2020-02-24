package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.Date;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：AddRepairScoreActivity
 * 描述：积分充值界面
 * 创建时间：2017-05-02  11:29
 */

public class AddRepairScoreActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_set_score)
    EditText etSetScore;
    @Bind(R.id.tv_score)
    TextView tvScore;

    private String tradeNo = "";
    private String score = "";
    private String currentScore = "";
    private double price;

    @Override
    public int initLayout() {
        return R.layout.activity_add_repair_score;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this,ActivityUtils.getInstance().orderList);
        tvTitle.setText("积分充值");
        currentScore = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        tvScore.setText("当前积分：" + currentScore);
    }

    @OnClick({R.id.ll_back, R.id.tv_add_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_add_pay:
                Date date = new Date();
                tradeNo = date.getTime() + "_" + PersonConstant.getInstance().getUserId();
                score = etSetScore.getText().toString().trim();
                if (!StringUtils.isNoEmpty(score)) {
                    ToastUtils.getMineToast("请输入充值积分");
                    return;
                }
                price = Double.parseDouble(score) / 10;

                CopyIosDialogUtils.getInstance().getErrorDialog(getContext(), "充值金额:" + price + "元", new CopyIosDialogUtils.ErrDialogCallBack() {
                    @Override
                    public void confirm() {
                        IntentUtils.getInstance().toPayActivity(getContext(), price + "", tradeNo, "积分充值", "积分充值", "积分充值",null);
                    }
                });
                break;
        }
    }

}
