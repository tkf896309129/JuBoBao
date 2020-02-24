package com.example.huoshangkou.jubowan.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.KeyBoardUtils;
import com.example.huoshangkou.jubowan.utils.TimeDialogUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：ApproveCheckActivity
 * 描述：
 * 创建时间：2018-03-09  08:21
 */

public class ApproveCheckActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_start_time)
    TextView tvStartTime;
    @Bind(R.id.tv_end_time)
    TextView tvEndTime;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_key_word)
    EditText etKeyWord;
    @Bind(R.id.ll_apply_man)
    LinearLayout llApplyMan;

    private String startTime = "";
    private String endTime = "";
    private String linkMan = "";
    private String keyWord = "";

    //1 审批 0 申请
    private String checkType = "";

    @Override
    public int initLayout() {
        return R.layout.activity_approve_check;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {


        checkType = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        //审批
        if (checkType.equals("1")) {
            tvTitle.setText("审批查询");
            //申请
        } else if (checkType.equals("0")) {
            tvTitle.setText("申请查询");
            llApplyMan.setVisibility(View.GONE);
        }else if(checkType.equals("2")){
            tvTitle.setText("知会查询");
        }
    }

    @OnClick({R.id.ll_back, R.id.tv_commit, R.id.tv_start_time, R.id.tv_end_time})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_commit:
                linkMan = etName.getText().toString().trim();
                keyWord = etKeyWord.getText().toString().trim();

                IntentUtils.getInstance().toApproveCheck(getContext(), ApproveCheckListActivity.class, linkMan, keyWord, startTime, endTime,checkType);
                break;
            case R.id.tv_start_time:
                TimeDialogUtils.getInstance().getMinuteTime(getContext(), new StringCallBack() {
                    @Override
                    public void onSuccess(String str) {
                        startTime = str;
                        tvStartTime.setText(str);
                    }

                    @Override
                    public void onFail() {

                    }
                });

                KeyBoardUtils.closeKeybord(etName, getContext());

                break;
            case R.id.tv_end_time:
                TimeDialogUtils.getInstance().getMinuteTime(getContext(), new StringCallBack() {
                    @Override
                    public void onSuccess(String str) {
                        endTime = str;
                        tvEndTime.setText(str);
                    }

                    @Override
                    public void onFail() {

                    }
                });
                KeyBoardUtils.closeKeybord(etName, getContext());
                break;
        }
    }

}
