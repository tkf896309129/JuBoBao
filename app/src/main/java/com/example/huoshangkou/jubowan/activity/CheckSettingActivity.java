package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.CheckSettinBean;
import com.example.huoshangkou.jubowan.bean.MetSettingBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnDateTimeCallBack;
import com.example.huoshangkou.jubowan.inter.OnTimeCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.TimeDialogUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：CheckSettingActivity
 * 描述：
 * 创建时间：2019-07-01  08:40
 */

public class CheckSettingActivity extends BaseActivity {
    @Bind(R.id.switch_met)
    Switch switchMet;
    @Bind(R.id.switch_fly)
    Switch switchFly;
    @Bind(R.id.rl_fly)
    RelativeLayout rlFly;
    @Bind(R.id.rl_met)
    RelativeLayout rlMet;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_start_time)
    TextView tvStartTime;
    @Bind(R.id.tv_end_time)
    TextView tvEndTime;
    @Bind(R.id.tv_end_time_met)
    TextView tvEndMet;
    @Bind(R.id.tv_start_time_met)
    TextView tvStartTimeMet;
    @Bind(R.id.tv_right)
    TextView tvRight;//
    @Bind(R.id.tv_fly_time)
    TextView tvFlyTime;
    @Bind(R.id.tv_met_time)
    TextView tvMetTime;

    private String type = "0";
    private String state = "";
    private String stateFly = "";
    private String stateMet = "";

    private String startTime = "";
    private String endTime = "";
    private String timeSpan = "";

    private Date dateFlyStart;
    private Date dateFlyEnd;
    private Date dateMetStart;
    private Date dateMetEnd;

    //是否已有模式
    private boolean isHasMode = false;

    @Override
    public int initLayout() {
        return R.layout.activity_check_setting;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("设置");
        tvRight.setText("提交");
        switchMet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    stateMet = "0";
                    switchFly.setChecked(false);
                    rlMet.setVisibility(View.VISIBLE);
                } else {
                    rlMet.setVisibility(View.GONE);
                    if (!switchFly.isChecked() && isHasMode) {
                        CopyIosDialogUtils.getInstance().getIosDialog(getContext(), "是否关闭勿扰模式", new CopyIosDialogUtils.iosDialogClick() {
                            @Override
                            public void confimBtn() {
                                commitNoise();
                            }

                            @Override
                            public void cancelBtn() {
                                switchMet.setChecked(true);
                            }
                        });
                    }
                }
            }
        });
        switchFly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    type = "0";
                    switchMet.setChecked(false);
                    rlFly.setVisibility(View.VISIBLE);
                } else {
                    rlFly.setVisibility(View.GONE);
                    if (!switchMet.isChecked() && isHasMode) {
                        CopyIosDialogUtils.getInstance().getIosDialog(getContext(), "是否关闭勿扰模式", new CopyIosDialogUtils.iosDialogClick() {
                            @Override
                            public void confimBtn() {
                                commitNoise();
                            }

                            @Override
                            public void cancelBtn() {
                                switchFly.setChecked(true);
                            }
                        });
                    }
                }
            }
        });
        getSetting();
    }

    @OnClick({R.id.tv_right, R.id.ll_back, R.id.tv_start_time, R.id.tv_end_time, R.id.tv_start_time_met, R.id.tv_end_time_met})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_right:
                //飞行模式
                if (switchFly.isChecked()) {
                    type = "1";
                }
                //会议模式
                if (switchMet.isChecked()) {
                    type = "2";
                }
                if (switchMet.isChecked() || switchFly.isChecked()) {
                    state = "0";
                } else {
                    state = "1";
                }
                if (!StringUtils.isNoEmpty(startTime) && !type.equals("0")) {
                    ToastUtils.getMineToast("请选择开始时间");
                    return;
                }
                if (!StringUtils.isNoEmpty(endTime) && !type.equals("0")) {
                    ToastUtils.getMineToast("请选择结束时间");
                    return;
                }

                commitSetting(type, startTime, endTime, state);
                break;
            case R.id.tv_start_time:
                TimeDialogUtils.getInstance().getTimeHour(this, new OnDateTimeCallBack() {
                    @Override
                    public void onTimeDate(String time, Date date) {
                        startTime = time;
                        long timeMinuteCaculate = DateUtils.getInstance().getTimeMinuteCaculate(startTime, endTime);
                        if (timeMinuteCaculate <= 0) {
                            ToastUtils.getMineToast("结束时间要大于开始时间");
                            return;
                        }
                        tvStartTime.setText(time);
                        dateFlyStart = date;
                        timeSpan = DateUtils.getDatePoor(dateFlyStart, dateFlyEnd);
                        tvFlyTime.setText(DateUtils.getDatePoor(dateFlyStart, dateFlyEnd));

                    }
                });
                break;
            case R.id.tv_end_time:
                TimeDialogUtils.getInstance().getTimeHour(this, new OnDateTimeCallBack() {
                    @Override
                    public void onTimeDate(String time, Date date) {

                        endTime = time;
                        long timeMinuteCaculate = DateUtils.getInstance().getTimeMinuteCaculate(startTime, endTime);
                        if (timeMinuteCaculate <= 0) {
                            ToastUtils.getMineToast("结束时间要大于开始时间");
                            return;
                        }
                        tvEndTime.setText(time);
                        dateFlyEnd = date;
                        timeSpan = DateUtils.getDatePoor(dateFlyStart, dateFlyEnd);
                        tvFlyTime.setText(DateUtils.getDatePoor(dateFlyStart, dateFlyEnd));
                    }
                });
                break;
            case R.id.tv_start_time_met:
                TimeDialogUtils.getInstance().getTimeHour(this, new OnDateTimeCallBack() {
                    @Override
                    public void onTimeDate(String time, Date date) {

                        startTime = time;
                        long timeMinuteCaculate = DateUtils.getInstance().getTimeMinuteCaculate(startTime, endTime);
                        if (timeMinuteCaculate <= 0) {
                            ToastUtils.getMineToast("结束时间要大于开始时间");
                            return;
                        }
                        dateMetStart = date;
                        timeSpan = DateUtils.getDatePoor(dateMetStart, dateMetEnd);
                        tvFlyTime.setText(DateUtils.getDatePoor(dateMetStart, dateMetEnd));
                        tvStartTimeMet.setText(time);
                    }
                });//
                break;
            case R.id.tv_end_time_met:
                TimeDialogUtils.getInstance().getTimeHour(this, new OnDateTimeCallBack() {
                    @Override
                    public void onTimeDate(String time, Date date) {

                        endTime = time;
                        long timeMinuteCaculate = DateUtils.getInstance().getTimeMinuteCaculate(startTime, endTime);
                        if (timeMinuteCaculate <= 0) {
                            ToastUtils.getMineToast("结束时间要大于开始时间");
                            return;
                        }
                        tvEndMet.setText(time);
                        dateMetEnd = date;
                        timeSpan = DateUtils.getDatePoor(dateMetStart, dateMetEnd);
                        tvMetTime.setText(DateUtils.getDatePoor(dateMetStart, dateMetEnd));
                    }
                });
                break;
        }
    }

    //提交设置
    public void commitSetting(String type, String startTime, String endTime, String state) {
        Map<String, String> map = new HashMap<>();
        map.put("UserID", PersonConstant.getInstance().getUserId());
        map.put("PatternType", type);
        map.put("StartTime", startTime);
        map.put("EndTime", endTime);
        map.put("UpdateState", state);
        map.put("TimeSpan", timeSpan);

        OkhttpUtil.getInstance().basePostCall(this, map, UrlConstant.getInstance().CHECK_SETTING, new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                MetSettingBean settingBean = JSON.parseObject(str, MetSettingBean.class);
                if (settingBean.getD().getState() == 1) {
                    ToastUtils.getMineToast("设置成功");
                    CheckSettingActivity.this.finish();
                } else {
                    ToastUtils.getMineToast(settingBean.getD().getMessage());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    //获取设置
    public void getSetting() {
        Map<String, String> map = new HashMap<>();
        map.put("UserID", PersonConstant.getInstance().getUserId());

        OkhttpUtil.getInstance().basePostCall(this, map, UrlConstant.getInstance().CHECK_SETTING_GET, new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                CheckSettinBean settinBean = JSON.parseObject(str, CheckSettinBean.class);
                CheckSettinBean.DBean.ReObjBean reObj = settinBean.getD().getReObj();
                int patternType = settinBean.getD().getReObj().getPatternType();
                switch (patternType) {
                    //飞行模式
                    case 1:
                        switchFly.setChecked(true);
                        startTime = reObj.getStartTime();
                        endTime = reObj.getEndTime();
                        state = "0";
                        tvStartTime.setText(startTime);
                        tvEndTime.setText(endTime);
                        timeSpan = reObj.getTimeSpan();
                        tvFlyTime.setText(reObj.getTimeSpan());
                        isHasMode = true;
                        break;
                    //会议模式
                    case 2:
                        switchMet.setChecked(true);
                        state = "0";
                        startTime = reObj.getStartTime();
                        endTime = reObj.getEndTime();
                        tvStartTimeMet.setText(startTime);
                        tvEndMet.setText(endTime);
                        timeSpan = reObj.getTimeSpan();
                        tvMetTime.setText(reObj.getTimeSpan());
                        isHasMode = true;
                        break;
                }
            }

            @Override
            public void onFail() {

            }
        });
    }


    //提交勿扰模式
    public void commitNoise() {
        //飞行模式
        if (switchFly.isChecked()) {
            type = "1";
        }
        //会议模式
        if (switchMet.isChecked()) {
            type = "2";
        }
        if (switchMet.isChecked() || switchFly.isChecked()) {
            state = "0";
        } else {
            state = "1";
        }
        commitSetting(type, startTime, endTime, state);
    }

}
