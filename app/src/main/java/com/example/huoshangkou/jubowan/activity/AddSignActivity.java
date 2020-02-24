package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.ChooseDialogPositionCallBack;
import com.example.huoshangkou.jubowan.inter.OnTimeCallBack;
import com.example.huoshangkou.jubowan.inter.OnTimeDataCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.PickDialogUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.TimeDialogUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：AddSignActivity
 * 描述：
 * 创建时间：2017-04-18  10:25
 */

public class AddSignActivity extends BaseActivity {
    //审批人
    public final int CHECK_MAN = 1;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_approve_man)
    TextView tvApproveMan;
    @Bind(R.id.tv_check_man)
    TextView tvCheckMan;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_time_span)
    TextView tvTimeSpan;
    @Bind(R.id.et_remark)
    EditText etRemark;

    private String checkId = "";
    //证明人
    public final int APPROVE_MAN = 2;
    String remark = "";
    String time = "";
    //审批人
    String approveUserId = "";
    //时间段
    String timeSpan = "";
    ArrayList<String> timeSpanList;
    private String approveId = "";
    private String approveMan = "";

    @Override
    public int initLayout() {
        return R.layout.activity_add_sign;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        timeSpanList = new ArrayList<>();
        timeSpanList.add("上班");
        timeSpanList.add("下班");

        tvTitle.setText("补签");

        approveId = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        approveMan = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        tvCheckMan.setText(approveMan + " ");
    }

    @OnClick({R.id.rl_time, R.id.tv_commit, R.id.rl_time_span, R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.rl_check:
//                Intent intent = new Intent(this, ChoosCheckManActivity.class);
//                startActivityForResult(intent, CHECK_MAN);
//                break;
//            case R.id.rl_approve:
//                Intent intent2 = new Intent(this, ChoosCheckManActivity.class);
//                startActivityForResult(intent2, APPROVE_MAN);
//                break;
            case R.id.rl_time:
                TimeDialogUtils.getInstance().getTime(getContext(), new StringCallBack() {
                    @Override
                    public void onSuccess(String str) {
                        time = str;
                        tvTime.setText(time + "  ");
                    }

                    @Override
                    public void onFail() {

                    }
                });
                break;
            case R.id.rl_time_span:
                PickDialogUtils.getInstance().getChoosePositionDialog(getContext(), "选择时间段", timeSpanList, new ChooseDialogPositionCallBack() {
                    @Override
                    public void onGetMessagePosition(String message, int position) {
                        timeSpan = message;
                        tvTimeSpan.setText(timeSpan);
                    }
                });

                break;
            case R.id.tv_commit:

                if (!StringUtils.isNoEmpty(time)) {
                    ToastUtils.getMineToast("请选择漏签时间");
                    return;
                }

                remark = etRemark.getText().toString().trim();
                if (!StringUtils.isNoEmpty(remark)) {
                    ToastUtils.getMineToast("请输入补签备注");
                    return;
                }

                addSign(remark, time, approveId, timeSpan);
                break;
            case R.id.ll_back:
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
        if (requestCode == CHECK_MAN) {
            checkId = data.getStringExtra("id");
            tvCheckMan.setText(data.getStringExtra("name") + " ");
        } else if (requestCode == APPROVE_MAN) {
            approveUserId = data.getStringExtra("id");
            tvApproveMan.setText(data.getStringExtra("name") + " ");
        }
    }


    //提交补签
    public void addSign(String remark, String time, String approveUserId, String timeSpan) {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().ADD_SIGN
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().REMARK + "=" + EncodeUtils.getInstance().getEncodeString(remark) + "&"
                + FieldConstant.getInstance().LEAK_TIME + "=" + time + "&"
                + FieldConstant.getInstance().TIME_SPAN + "=" + EncodeUtils.getInstance().getEncodeString(timeSpan) + "&"
                + FieldConstant.getInstance().APPROVE_USER_ID + "=" + approveUserId, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    ToastUtils.getMineToast("提交成功");
                    AddSignActivity.this.finish();
                } else {
                    CopyIosDialogUtils.getInstance().getErrorDialog(getContext(), successBean.getErrMsg(), new CopyIosDialogUtils.ErrDialogCallBack() {
                        @Override
                        public void confirm() {

                        }
                    });
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

}
