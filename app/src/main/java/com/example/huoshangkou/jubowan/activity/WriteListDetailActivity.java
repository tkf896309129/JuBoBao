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
 * 类名：WriteListDetailActivity
 * 描述：
 * 创建时间：2017-08-10  14:03
 */

public class WriteListDetailActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_content)
    EditText etContent;

    private String title = "";

    private int CODE = -1;

    String problem = "";

    @Override
    public int initLayout() {
        return R.layout.activity_write_detail;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        title = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        CODE = getIntent().getIntExtra(IntentUtils.getInstance().VALUE, -1);
        problem = getIntent().getStringExtra(IntentUtils.getInstance().STR);

        etContent.setText(problem);
        etContent.setHint(title);
        tvTitle.setText(title);


    }

    @OnClick({R.id.ll_back, R.id.tv_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_save:
                String content = etContent.getText().toString().trim();
                if (!StringUtils.isNoEmpty(content)) {
                    ToastUtils.getMineToast("请输入内容");
                    return;
                }
                Intent intent2 = new Intent();
                intent2.putExtra(IntentUtils.getInstance().TYPE, content);
                setResult(CODE, intent2);
                this.finish();
                break;
        }
    }

}