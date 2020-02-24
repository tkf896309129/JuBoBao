package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.AddDataOutBean;
import com.example.huoshangkou.jubowan.bean.AddDataSuccessBean;
import com.example.huoshangkou.jubowan.bean.AddDateTypeBean;
import com.example.huoshangkou.jubowan.bean.DataBean;
import com.example.huoshangkou.jubowan.bean.DateTypeBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnTimeCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.inter.StringPositionCallBack;
import com.example.huoshangkou.jubowan.utils.DialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.KeyBoardUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.TimeDialogUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：AddDataActivity
 * 描述：
 * 创建时间：2019-08-27  14:59
 */

public class AddDataActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_hint)
    TextView tvHint;
    @Bind(R.id.tv_repit)
    TextView tvRepit;
    @Bind(R.id.tv_finish_repit)
    TextView tvFinishRepit;
    @Bind(R.id.tv_start_time)
    TextView tvStartTime;
    @Bind(R.id.tv_end_time)
    TextView tvEndTime;
    @Bind(R.id.et_remark)
    EditText etRemark;
    @Bind(R.id.et_title)
    EditText etTitle;
    @Bind(R.id.ll_finish_repit)
    LinearLayout llFinishRepeat;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.ll_finish_time)
    LinearLayout llRepeatTime;
    @Bind(R.id.ll_repeat_times)
    LinearLayout llRepeatTimes;
    @Bind(R.id.tv_finish_time)
    TextView tvFinishTime;

    //标题
    private String dataTitle = "";
    //开始时间
    private String startTime = "";
    //结束时间
    private String endTime = "";
    //提醒方式
    private String hintType = "";
    //重复方式
    private String repitType = "";
    //结束重复
    private String finishRepit = "0";
    //备注
    private String remark = "";
    //重复时间
    private String repeatTime = "";
    //重复次数
    private String repeatTimes = "";

    private boolean isTime = false;
    private boolean isTimes = false;

    private int hintPosition = -1;
    private int repitPosition = -1;
    private int finishRepitPositin = -1;

    private List<DateTypeBean.RepeateBean> repeateList = new ArrayList<>();
    private List<DateTypeBean.ReminderBean> reminderList = new ArrayList<>();
    private List<DateTypeBean.EndRepeateBean> endRepeateList = new ArrayList<>();

    List<AddDateTypeBean> AddRepeateList = new ArrayList<>();
    List<AddDateTypeBean> AddReminderList = new ArrayList<>();
    List<AddDateTypeBean> AddEndRepeateList = new ArrayList<>();

    List<String> listHint = new ArrayList<>();
    List<String> listRepeat = new ArrayList<>();
    List<String> listFinishRepeat = new ArrayList<>();

    private String id = "";

    @Override
    public int initLayout() {
        return R.layout.activity_add_data;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        id = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        tvTitle.setText("新建日程");
        tvRight.setText("添加");
        getDateType();
        getDate();
    }

    @OnClick({R.id.ll_back, R.id.ll_hint, R.id.ll_finish_time, R.id.ll_repit, R.id.ll_finish_repit, R.id.ll_end_time, R.id.ll_start_time, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_right:
                dataTitle = etTitle.getText().toString().trim();
                if (!StringUtils.isNoEmpty(dataTitle)) {
                    ToastUtils.getMineToast("请输入标题");
                    return;
                }
                if (!StringUtils.isNoEmpty(startTime)) {
                    ToastUtils.getMineToast("请选择开始时间");
                    return;
                }
                if (!StringUtils.isNoEmpty(endTime)) {
                    ToastUtils.getMineToast("请选择结束时间");
                    return;
                }
                if (!StringUtils.isNoEmpty(hintType)) {
                    ToastUtils.getMineToast("请选择提醒方式");
                    return;
                }
                if (!StringUtils.isNoEmpty(repitType)) {
                    ToastUtils.getMineToast("请选择重复方式");
                    return;
                }
                if (!StringUtils.isNoEmpty(finishRepit)) {
                    ToastUtils.getMineToast("请选择结束重复方式");
                    return;
                }
//                if (isTimes && !StringUtils.isNoEmpty(repeatTimes)) {
//                    ToastUtils.getMineToast("请输入重复次数");
//                    return;
//                }
//                if (isTime && !StringUtils.isNoEmpty(repeatTime)) {
//                    ToastUtils.getMineToast("请选择重复时间");
//                    return;
//                }
                remark = etRemark.getText().toString().trim();
                addNewDate();
                break;
            case R.id.ll_hint:
//                CenterBaseDialogUtils.getBaseCenterDialog(this, hintPosition, AddReminderList, new StringPositionCallBack() {
//                    @Override
//                    public void onStringPosition(String str, int position) {
//                        hintType = AddReminderList.get(position).getId() + "";
//                        tvHint.setText(str);
//                        hintPosition = position;
//                    }
//                });
                KeyBoardUtils.closeKeybord(etRemark, this);
                DialogUtils.getInstance().getBaseDialog(this, listHint, new StringPositionCallBack() {
                    @Override
                    public void onStringPosition(String str, int position) {
                        hintType = AddReminderList.get(position).getId() + "";
                        tvHint.setText(str);
                        hintPosition = position;
                    }
                });
                break;
            case R.id.ll_repit:
                KeyBoardUtils.closeKeybord(etRemark, this);
                DialogUtils.getInstance().getBaseDialog(this, listRepeat, new StringPositionCallBack() {
                    @Override
                    public void onStringPosition(String str, int position) {
                        repitType = AddRepeateList.get(position).getId() + "";
                        tvRepit.setText(str);
//                        switch (str) {
//                            case "一次性":
//                                llFinishRepeat.setVisibility(View.GONE);
//                                break;
//                            default:
//                                llFinishRepeat.setVisibility(View.VISIBLE);
//                                break;
//                        }

                        repitPosition = position;
                    }
                });
//                CenterBaseDialogUtils.getBaseCenterDialog(this, repitPosition, AddRepeateList, new StringPositionCallBack() {
//                    @Override
//                    public void onStringPosition(String str, int position) {
//                        repitType = AddRepeateList.get(position).getId() + "";
//                        tvRepit.setText(str);
//                        switch (str) {
//                            case "一次性":
//                                llFinishRepeat.setVisibility(View.GONE);
//                                break;
//                            default:
//                                llFinishRepeat.setVisibility(View.VISIBLE);
//                                break;
//                        }
//
//                        repitPosition = position;
//                    }
//                });
                break;
            case R.id.ll_finish_repit:
                KeyBoardUtils.closeKeybord(etRemark, this);
                DialogUtils.getInstance().getBaseDialog(this, listFinishRepeat, new StringPositionCallBack() {
                    @Override
                    public void onStringPosition(String str, int position) {
                        finishRepit = AddEndRepeateList.get(position).getId() + "";
                        tvFinishRepit.setText(str);
                        switch (str) {
                            case "时间":
                                isTime = true;
                                isTimes = false;
                                llRepeatTime.setVisibility(View.VISIBLE);
                                llRepeatTimes.setVisibility(View.GONE);
                                break;
                            case "次数":
                                isTime = false;
                                isTimes = true;
                                llRepeatTimes.setVisibility(View.VISIBLE);
                                llRepeatTime.setVisibility(View.GONE);
                                break;
                            default:
                                isTime = false;
                                isTimes = false;
                                llRepeatTime.setVisibility(View.GONE);
                                llRepeatTimes.setVisibility(View.GONE);
                                break;
                        }
                        finishRepitPositin = position;
                    }
                });

//                CenterBaseDialogUtils.getBaseCenterDialog(this, finishRepitPositin, AddEndRepeateList, new StringPositionCallBack() {
//                    @Override
//                    public void onStringPosition(String str, int position) {
//
//                    }
//                });
                break;
            case R.id.ll_start_time:
                KeyBoardUtils.closeKeybord(etRemark, this);
                TimeDialogUtils.getInstance().getTimeHour(this, new OnTimeCallBack() {
                    @Override
                    public void getYearTime(String year) {

                    }

                    @Override
                    public void getMinuteTime(String minute) {
                        startTime = minute;
                        tvStartTime.setText(minute);
                    }
                });
                break;
            case R.id.ll_end_time:
                KeyBoardUtils.closeKeybord(etRemark, this);
                TimeDialogUtils.getInstance().getTimeHour(this, new OnTimeCallBack() {
                    @Override
                    public void getYearTime(String year) {

                    }

                    @Override
                    public void getMinuteTime(String minute) {
                        endTime = minute;
                        tvEndTime.setText(minute);
                    }
                });
                break;
            case R.id.ll_finish_time:
                KeyBoardUtils.closeKeybord(etRemark, this);
                TimeDialogUtils.getInstance().getTimeHour(this, new OnTimeCallBack() {
                    @Override
                    public void getYearTime(String year) {

                    }

                    @Override
                    public void getMinuteTime(String minute) {
                        repeatTime = minute;
                        tvFinishTime.setText(repeatTime);
                    }
                });
                break;
        }
    }


    //获取日程类型
    public void getDateType() {
        OkhttpUtil.getInstance().basePostCall(this, "", UrlConstant.getInstance().DATE_TYPE_URL, new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                AddDataOutBean outBean = JSON.parseObject(str, AddDataOutBean.class);
                if (outBean.getD() == null) {
                    ToastUtils.getMineToast("数据请求错误");
                    return;
                }
                DateTypeBean typeBean = JSON.parseObject(outBean.getD(), DateTypeBean.class);
                repeateList.addAll(typeBean.getRepeate());
                endRepeateList.addAll(typeBean.getEndRepeate());
                reminderList.addAll(typeBean.getReminder());
                for (int i = 0; i < repeateList.size(); i++) {
                    AddDateTypeBean addDateTypeBean = new AddDateTypeBean();
                    addDateTypeBean.setType(repeateList.get(i).getName());
                    listRepeat.add(repeateList.get(i).getName());
                    addDateTypeBean.setId(repeateList.get(i).getType());
                    AddRepeateList.add(addDateTypeBean);
                }
                for (int i = 0; i < endRepeateList.size(); i++) {
                    AddDateTypeBean addDateTypeBean = new AddDateTypeBean();
                    addDateTypeBean.setType(endRepeateList.get(i).getName());
                    listFinishRepeat.add(endRepeateList.get(i).getName());
                    addDateTypeBean.setId(endRepeateList.get(i).getType());
                    AddEndRepeateList.add(addDateTypeBean);
                }
                for (int i = 0; i < reminderList.size(); i++) {
                    AddDateTypeBean addDateTypeBean = new AddDateTypeBean();
                    addDateTypeBean.setType(reminderList.get(i).getName());
                    listHint.add(reminderList.get(i).getName());
                    addDateTypeBean.setId(reminderList.get(i).getType());
                    AddReminderList.add(addDateTypeBean);
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    //新增日程
    public void addNewDate() {
        Map<String, String> map = new HashMap<>();

        map.put("UserId", PersonConstant.getInstance().getUserId());
        map.put("Title", dataTitle);
        map.put("StartDate", startTime);
        map.put("EndDate", endTime);
        map.put("EndRepeateCount", repeatTimes);
        map.put("EndRepeateDate", repeatTime);
        map.put("EndRepeateType", finishRepit);
//        map.put("Frequency",hintType);
        map.put("Remark", remark);
        map.put("ReminderType", hintType);
        map.put("RepeateType", repitType);
        map.put("ScheduleLevel", "0");
        if (StringUtils.isNoEmpty(id)) {
            map.put("id", id);
        }
        String json = JSON.toJSONString(map);
        String putJson = "{\"model\":" + json + "}";

        OkhttpUtil.getInstance().basePostCall(this, putJson, UrlConstant.getInstance().ADD_DATE_TYPE_URL, new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                AddDataSuccessBean successBean = JSON.parseObject(str, AddDataSuccessBean.class);
                if (successBean.getD().getErrorCode() == 0) {
                    ToastUtils.getMineToast("添加成功");
                    AddDataActivity.this.finish();
                } else {
                    ToastUtils.getMineToast(successBean.getD().getMsg());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    //获取日程
    public void getDate() {
        if (!StringUtils.isNoEmpty(id)) {
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        String json = JSON.toJSONString(map);

        OkhttpUtil.getInstance().basePostCall(this, json, UrlConstant.getInstance().CUSTOMER_DATA_URL + "GetSchedulEdetail", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                DataBean dataBean = JSON.parseObject(str, DataBean.class);
                DataBean.DBean.ResultBean result = dataBean.getD().getResult();
                etTitle.setText(result.getTitle());
                startTime = result.getStartDate();
                tvStartTime.setText(startTime);
                endTime = result.getEndDate();
                tvEndTime.setText(endTime);
                hintType = result.getReminderType() + "";
                tvHint.setText(result.getReminder() + "");
                repitType = result.getRepeateType() + "";
                tvRepit.setText(result.getRepeate());
                finishRepit = result.getEndRepeateType() + "";
                tvFinishRepit.setText(result.getRepeate());

                etRemark.setText(result.getRemark());
            }

            @Override
            public void onFail() {

            }
        });
    }

}
