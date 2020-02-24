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

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：ProblemIntroActivity
 * 描述：故障描述
 * 创建时间：2017-03-24  15:51
 */

public class ProblemIntroActivity extends BaseActivity {

    @Bind(R.id.tv_save_detail)
    TextView tvSave;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.et_problem_detail)
    EditText etProblemIntro;


    private ArrayList<String> problemList;
    private String problemIntro = "";

    private final int INTENT_CODE = 1;
    private final int RESULT_CODE = 2;


    @Override
    public int initLayout() {
        return R.layout.activity_problem_intro;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvRight.setText("故障描述");
        tvTitle.setText("故障详情");

        problemList = getIntent().getStringArrayListExtra(IntentUtils.getInstance().BEAN_TYPE);
        problemIntro = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);

        etProblemIntro.setText(problemIntro);

    }

    @OnClick({R.id.ll_back, R.id.tv_right, R.id.tv_save_detail})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_right:
                Intent intent = new Intent(getContext(), ChooseTypeActivity.class);
                intent.putExtra(IntentUtils.getInstance().TYPE, etProblemIntro.getText().toString().trim());
                intent.putStringArrayListExtra(IntentUtils.getInstance().BEAN_TYPE, problemList);
                startActivityForResult(intent, INTENT_CODE);
                break;
            case R.id.tv_save_detail:

                String intro = etProblemIntro.getText().toString().trim();
                if (!StringUtils.isNoEmpty(intro)) {
                    ToastUtils.getMineToast("请输入故障详情");
                    return;
                }

                Intent intent1 = new Intent();
                intent1.putExtra(IntentUtils.getInstance().VALUE, etProblemIntro.getText().toString().trim());
                setResult(RESULT_CODE, intent1);
                this.finish();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null) {
            return;
        }

        switch (requestCode) {
            case INTENT_CODE:
                etProblemIntro.setText(data.getStringExtra(IntentUtils.getInstance().VALUE));
                break;
        }
    }
}
