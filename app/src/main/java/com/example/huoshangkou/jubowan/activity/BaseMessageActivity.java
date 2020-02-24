package com.example.huoshangkou.jubowan.activity;

        import android.content.Intent;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.TextView;

        import com.example.huoshangkou.jubowan.R;
        import com.example.huoshangkou.jubowan.base.BaseActivity;
        import com.example.huoshangkou.jubowan.db.DBHelper;
        import com.example.huoshangkou.jubowan.inter.OnTimeCallBack;
        import com.example.huoshangkou.jubowan.utils.DateUtils;
        import com.example.huoshangkou.jubowan.utils.IntentUtils;
        import com.example.huoshangkou.jubowan.utils.KeyBoardUtils;
        import com.example.huoshangkou.jubowan.utils.LogUtils;
        import com.example.huoshangkou.jubowan.utils.StringUtils;
        import com.example.huoshangkou.jubowan.utils.TimeDialogUtils;
        import com.example.huoshangkou.jubowan.utils.ToastUtils;

        import butterknife.Bind;
        import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：BaseMessageActivity
 * 描述：
 * 创建时间：2017-08-10  14:25
 */

public class BaseMessageActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_pro_name)
    EditText etProName;
    @Bind(R.id.et_service_man)
    EditText etServiceMan;
    @Bind(R.id.tv_buy_time)
    TextView tvBuyTime;
    @Bind(R.id.et_tool_name)
    EditText etToolName;
    @Bind(R.id.tv_start_time)
    TextView tvStartTime;
    @Bind(R.id.tv_leave_time)
    TextView tvLeaveTime;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.et_repair_pro)
    EditText etRepairPro;

    //维修维修项目
    private String repairPro = "";
    //厂家名称
    private String productName = "";
    //售后服务人员
    private String serviceName = "";
    //采购时间
    private String buyTime = "";
    //设备品牌型号
    private String brandNum = "";
    //出发时间
    private String startTime = "";
    //离开时间
    private String leaveTime = "";

    //基础信息
    String str = "";

    @Override
    public int initLayout() {
        return R.layout.activity_base_message;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("基础信息");
        tvRight.setText("保存");
        str = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        setData(str);
    }

    private void setData(String str) {
        if (!StringUtils.isNoEmpty(str)) {
            return;
        }
        String[] descs = str.split(",");
        etProName.setText(descs[0]);
        etServiceMan.setText(descs[1]);
        etRepairPro.setText(descs[2]);
        buyTime = descs[3];
        tvBuyTime.setText(descs[3]);
        etToolName.setText(descs[4]);
        startTime = descs[5];
        tvStartTime.setText(descs[5]);
        leaveTime = descs[6];
        tvLeaveTime.setText(descs[6]);
    }

    @OnClick({R.id.ll_back, R.id.tv_buy_time,
            R.id.tv_start_time, R.id.tv_leave_time, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_buy_time:
                KeyBoardUtils.closeKeybord(etProName, getContext());
                TimeDialogUtils.getInstance().getTime(getContext(), new OnTimeCallBack() {
                    @Override
                    public void getYearTime(String year) {
                        buyTime = year;
                        tvBuyTime.setText(year);
                    }

                    @Override
                    public void getMinuteTime(String minute) {

                    }
                });
                break;
            case R.id.tv_start_time:
                KeyBoardUtils.closeKeybord(etProName, getContext());
                TimeDialogUtils.getInstance().getTimeHour(getContext(), new OnTimeCallBack() {
                    @Override
                    public void getYearTime(String year) {
                        if (DateUtils.getInstance().getTimeCaculateMinute(year) < 0) {
                            ToastUtils.getMineToast("出发时间不得超过当前时间");
                            return;
                        }
                        if (StringUtils.isNoEmpty(leaveTime) && DateUtils.getInstance().getTimeCaculateMinute(year, leaveTime) < 0) {
                            ToastUtils.getMineToast("出发时间不得超过离开时间");
                            return;
                        }
                        startTime = year;
                        tvStartTime.setText(year);
                    }

                    @Override
                    public void getMinuteTime(String minute) {

                    }
                });
                break;
            case R.id.tv_leave_time:
                KeyBoardUtils.closeKeybord(etProName, getContext());
                TimeDialogUtils.getInstance().getTimeHour(getContext(), new OnTimeCallBack() {
                    @Override
                    public void getYearTime(String year) {
                        if (DateUtils.getInstance().getTimeCaculateMinute(year) < 0) {
                            ToastUtils.getMineToast("出发时间不得超过当前时间");
                            return;
                        }
                        if (StringUtils.isNoEmpty(startTime) && DateUtils.getInstance().getTimeCaculate(startTime, year) < 0) {
                            ToastUtils.getMineToast("离开时间不得小于出发时间");
                            return;
                        }

                        leaveTime = year;
                        tvLeaveTime.setText(year);
                    }

                    @Override
                    public void getMinuteTime(String minute) {

                    }
                });
                break;
            case R.id.tv_right:

                productName = etProName.getText().toString().trim();
                if (!StringUtils.isNoEmpty(productName)) {
                    ToastUtils.getMineToast("请输入厂家名称");
                    return;
                }

                serviceName = etServiceMan.getText().toString().trim();
                if (!StringUtils.isNoEmpty(serviceName)) {
                    ToastUtils.getMineToast("请输入售后服务人员");
                    return;
                }

                repairPro = etRepairPro.getText().toString().trim();
                if (!StringUtils.isNoEmpty(repairPro)) {
                    ToastUtils.getMineToast("请输入维修项目");
                    return;
                }

                if (!StringUtils.isNoEmpty(buyTime)) {
                    ToastUtils.getMineToast("请选择采购时间");
                    return;
                }

                brandNum = etToolName.getText().toString().trim();
                if (!StringUtils.isNoEmpty(brandNum)) {
                    ToastUtils.getMineToast("请输入设备品牌型号");
                    return;
                }

                if (!StringUtils.isNoEmpty(startTime)) {
                    ToastUtils.getMineToast("请选择出发时间");
                    return;
                }

                if (!StringUtils.isNoEmpty(leaveTime)) {
                    ToastUtils.getMineToast("请选择离开时间");
                    return;
                }

                String baseMessage = productName
                        + ":" + serviceName
                        + ":" + repairPro
                        + ":" + buyTime
                        + ":" + brandNum
                        + ":" + startTime
                        + ":" + leaveTime;

                Intent intent2 = new Intent();
                intent2.putExtra(IntentUtils.getInstance().TYPE, baseMessage);
                setResult(1, intent2);
                this.finish();
                break;
        }
    }
}
