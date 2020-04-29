package com.example.huoshangkou.jubowan.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.SuccessDBean;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnTimeCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.TimeDialogUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：VisitorRegisterActivity
 * 描述：
 * 创建时间：2019-09-05  14:15
 */

public class VisitorRegisterActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.et_content)
    EditText etContent;
    @Bind(R.id.et_common)
    EditText etCommon;


    private String name = "";
    private String id = "";
    private String time = "";
    private String content = "";
    private String eveluate = "";

    @Override
    public int initLayout() {
        return R.layout.activity_visitor_register;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("拜访登记");
        id = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        name = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);

        tvName.setText(name);

    }

    @OnClick({R.id.ll_back, R.id.tv_time, R.id.tv_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_time:
                TimeDialogUtils.getInstance().getTimeHour(this, new OnTimeCallBack() {
                    @Override
                    public void getYearTime(String year) {

                    }

                    @Override
                    public void getMinuteTime(String minute) {
                        time = minute;
                        tvTime.setText(minute+"  ");
                    }
                });
                break;
            case R.id.tv_commit:
                if (!StringUtils.isNoEmpty(time)) {
                    ToastUtils.getMineToast("请选择时间");
                    return;
                }
                content = etContent.getText().toString().trim();
                if (!StringUtils.isNoEmpty(content)) {
                    ToastUtils.getMineToast("请输入访问内容");
                    return;
                }
                eveluate = etCommon.getText().toString().trim();
                if (!StringUtils.isNoEmpty(eveluate)) {
                    ToastUtils.getMineToast("请输入访问评价");
                    return;
                }

                visitorRegister();
                break;
        }
    }

    //拜访登记
    public void visitorRegister() {
        Map<String, String> map = new HashMap<>();
        map.put("CustomerId", id);
        map.put("VisitDate", time);
        map.put("VisitContent", content);
        map.put("Evaluate", eveluate);
        String json = JSON.toJSONString(map);
        String putJson = "{\"model\":" + json + "}";
        OkhttpUtil.getInstance().basePostCall(this, putJson, UrlConstant.getInstance().CUSTOMER_MANAGE_URL + "VisitRegister", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                SuccessDBean dBean = JSON.parseObject(str,SuccessDBean.class);
                if(dBean.getD().getSuccess()==1){
                    ToastUtils.getMineToast("提交成功");
                    VisitorRegisterActivity.this.finish();
                }else {
                    ToastUtils.getMineToast("提交失败");
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

}
