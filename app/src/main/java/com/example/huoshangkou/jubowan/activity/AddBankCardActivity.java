package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.utils.IntentUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：AddBankCardActivity
 * 描述：添加银行卡界面
 * 创建时间：2017-02-21  10:24
 */

public class AddBankCardActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_bank_card)
    EditText etBankCard;

    @Override
    public int initLayout() {
        return R.layout.activity_add_bank;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        //设置标题
        tvTitle.setText("添加银行卡");
    }

    //点击事件
    @OnClick({R.id.iv_delete, R.id.ll_back, R.id.tv_next})
    public void onClick(View view) {
        switch (view.getId()) {
            //清除银行卡的内容
            case R.id.iv_delete:
                etBankCard.setText("");
                break;
            //返回按键
            case R.id.ll_back:
                this.finish();
                break;
            //下一步
            case R.id.tv_next:
                IntentUtils.getInstance().toActivity(this, CheckBankActivity.class);
                break;
        }
    }
}
