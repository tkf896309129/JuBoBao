package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.view.BasePickerView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.ApproveFunction;
import com.example.huoshangkou.jubowan.activity.function.ImageAddFunction;
import com.example.huoshangkou.jubowan.adapter.CheckImgAdapter;
import com.example.huoshangkou.jubowan.adapter.ImageAddAdapter;
import com.example.huoshangkou.jubowan.adapter.LanImageShowAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.base.HideBaseActivity;
import com.example.huoshangkou.jubowan.bean.CheckImgBean;
import com.example.huoshangkou.jubowan.constant.ApproveConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.inter.ChooseCheckMan;
import com.example.huoshangkou.jubowan.inter.ChooseDialogCallBack;
import com.example.huoshangkou.jubowan.inter.OnApplyCommitCallBack;
import com.example.huoshangkou.jubowan.inter.OnTimeCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.photo.PhotoCallBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.PickDialogUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.TimeDialogUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollGridView;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：OutWorkActivity
 * 描述：出差界面
 * 创建时间：2017-02-15  11:13
 */

public class OutWorkActivity extends HideBaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    //开始时间段
    @Bind(R.id.tv_start_times)
    TextView tvStartTimes;
    //开始日期
    @Bind(R.id.tv_start_time)
    TextView tvStartTime;
    //结束日期
    @Bind(R.id.tv_end_time)
    TextView tvEndTime;
    //结束时间段
    @Bind(R.id.tv_end_times)
    TextView tvEndTimes;
    //工作时间
    @Bind(R.id.tv_work_time)
    TextView tvWorkTime;
    //图片显示GridView
    @Bind(R.id.grid_view_apply)
    ScrollGridView scrollGridView;
    @Bind(R.id.tv_apply_check)
    TextView tvCheckMan;
    //时间段
    ArrayList<String> dayList;
    @Bind(R.id.et_post_text)
    EditText etRemark;
    @Bind(R.id.recyc_check)
    RecyclerView recyclerView;
    @Bind(R.id.recyc_check_man)
    RecyclerView recyclerViewMan;

    //图片
    public String pics = "";
    //开始日期
    private String startTime = "";
    //开始时间段
    private String startTimes = "";
    //结束日期
    private String endTime = "";
    //结束时间段
    private String endTimes = "";
    //说明
    private String remarks = "";
    //时间差
    private double timeCaculate = 0;
    //时间选择错误提示弹出框
    private AlertDialog.Builder builderError;
    //图片
    List<String> imgList;
    //图片适配器
    ImageAddAdapter imageAddAdapter;
    private int REQUEST_CODE = 1;
    //审批人id
    private String approveId = "";
    //类型
    private int type;
    private String timeSpan = "";
    private String csId = "";
    private int imgNum = 9;

    //审批人
    CheckImgAdapter checkAdapter;
    //抄送人
    CheckImgAdapter checkImgAdapter;

    List<CheckImgBean> checkImgBeenList = new ArrayList<>();
    List<CheckImgBean> checkList = new ArrayList<>();



    @Override
    public int initLayout() {
        return R.layout.activity_out_work;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
        //时间段
        dayList = new ArrayList<>();
        //图片
        imgList = new ArrayList<>();
        dayList.add("上午");
        dayList.add("下午");

        builderError = new AlertDialog.Builder(this);
        builderError.setTitle("温馨提示");
        builderError.setMessage("结束时间不能小于开始时间");
        builderError.setPositiveButton("确定", null);
        builderError.setNegativeButton("取消", null);

        type = ApproveConstant.getInstance().OUT_WORK;

        tvTitle.setText("出差");
        tvRight.setText("提交");

        //图片显示封装
        imageAddAdapter = new ImageAddAdapter(this, imgList, "");
        ImageAddFunction.addImageFun(imageAddAdapter, imgList, this, scrollGridView);

        checkImgAdapter = new CheckImgAdapter(this, checkImgBeenList);
        recyclerView.setAdapter(checkImgAdapter);
        recyclerView.setLayoutManager(getLayoutManager());
        checkImgAdapter.setDeleteImg(new LanImageShowAdapter.deleteClick() {
            @Override
            public void deleteImgClick(int position) {
                checkImgBeenList.remove(position);
                checkImgAdapter.notifyDataSetChanged();
            }
        });

        checkAdapter = new CheckImgAdapter(this, checkList);
        recyclerViewMan.setAdapter(checkAdapter);
        recyclerViewMan.setLayoutManager(getLayoutManager());
        checkAdapter.setDeleteImg(new LanImageShowAdapter.deleteClick() {
            @Override
            public void deleteImgClick(int position) {
                checkList.remove(position);
                checkAdapter.notifyDataSetChanged();
            }
        });
    }

    private StaggeredGridLayoutManager getLayoutManager() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL);
        return layoutManager;
    }

    //点击事件
    @OnClick({R.id.rl_end_times, R.id.ll_back, R.id.rl_start_time, R.id.rl_start_times,
            R.id.rl_end_time,R.id.tv_csr, R.id.iv_apply_camera, R.id.rl_apply_check, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            //提交按钮
            case R.id.tv_right:
                if (!StringUtils.isNoEmpty(startTime)) {
                    ToastUtils.getMineToast("请选择开始时间");
                    return;
                }
                if (!StringUtils.isNoEmpty(startTimes)) {
                    ToastUtils.getMineToast("请选择开始时间段");
                    return;
                }
                if (!StringUtils.isNoEmpty(endTime)) {
                    ToastUtils.getMineToast("请选择结束时间");
                    return;
                }

                if (!StringUtils.isNoEmpty(endTimes)) {
                    ToastUtils.getMineToast("请选择结束时间段");
                    return;
                }

                remarks = etRemark.getText().toString().trim();
                if (!StringUtils.isNoEmpty(remarks)) {
                    ToastUtils.getMineToast("请输入出差事由");
                    return;
                }
                if(checkList.size()==1){
                    approveId = checkList.get(0).getId();
                }else {
                    approveId = "";
                }
                if (!StringUtils.isNoEmpty(approveId)) {
                    ToastUtils.getMineToast("请选择审批人");
                    return;
                }
                csId = "";
                for (CheckImgBean checkImgBean : checkImgBeenList) {
                    if (checkImgBeenList.size() > 1) {
                        csId += checkImgBean.getId() + ";";
                    } else {
                        csId += checkImgBean.getId();
                    }
                }

                CopyIosDialogUtils.getInstance().getIosDialog(this, getString(R.string.is_commit_apply), new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        PhotoUtils.getInstance().mutilLocalImageUp(imgList, OutWorkActivity.this, new StringCallBack() {
                            @Override
                            public void onSuccess(String str) {
                                pics = str;
                                ApproveFunction.getInstance().applyToApprove(OutWorkActivity.this, type + "", "", "",
                                        startTime, endTime, startTimes, endTimes, "", "",
                                        remarks, pics, approveId, timeSpan, csId, new OnApplyCommitCallBack() {
                                            @Override
                                            public void onSuccess() {
                                                ToastUtils.getMineToast("提交成功");
                                                OutWorkActivity.this.finish();
                                            }

                                            @Override
                                            public void onFail() {
                                            }
                                        });

                            }
                            
                            @Override
                            public void onFail() {

                            }
                        });
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });

                break;
            case R.id.ll_back:
                this.finish();
                break;
            //结束时间段
            case R.id.rl_end_times:
                PickDialogUtils.getInstance().getChooseDialog(this, "选择开始时间段", dayList, new ChooseDialogCallBack() {
                    @Override
                    public void onClickSuccess(String choose) {
                        endTimes = choose;
                        long timeCaculate2 = DateUtils.getInstance().getTimeCaculate(startTime, endTime);
                        if ((timeCaculate2 == 0 && startTimes.equals("下午") && endTimes.equals("上午"))) {
                            builderError.show();
                            endTimes = "";
                            tvEndTimes.setText("请选择");
                            return;
                        }
                        tvEndTimes.setText(choose);
                        timeSpan = DateUtils.getInstance().getTimeData(startTimes, endTimes, startTime, endTime, timeCaculate);
                        tvWorkTime.setText(timeSpan);
                    }
                });
                break;
            //开始日期
            case R.id.rl_start_time:
                TimeDialogUtils.getInstance().getTime(this, new OnTimeCallBack() {
                    @Override
                    public void getYearTime(String year) {
                        startTime = year;
                        long timeCaculate1 = DateUtils.getInstance().getTimeCaculate(startTime, endTime);
                        if (timeCaculate1 < 0 || (timeCaculate1 == 0 && startTimes.equals("下午") && endTimes.equals("上午"))) {
                            builderError.show();
                            startTime = "";
                            tvStartTime.setText("请选择");
                            return;
                        }
                        tvStartTime.setText(year);

                        timeSpan = DateUtils.getInstance().getTimeData(startTimes, endTimes, startTime, endTime, timeCaculate);
                        tvWorkTime.setText(timeSpan);
                    }

                    @Override
                    public void getMinuteTime(String minute) {

                    }
                });

                break;
            //开始时间段
            case R.id.rl_start_times:

                PickDialogUtils.getInstance().getChooseDialog(this, "选择开始时间段", dayList, new ChooseDialogCallBack() {
                    @Override
                    public void onClickSuccess(String choose) {
                        startTimes = choose;
                        long timeCaculate1 = DateUtils.getInstance().getTimeCaculate(startTime, endTime);
                        if ((timeCaculate1 == 0 && startTimes.equals("下午") && endTimes.equals("上午"))) {
                            builderError.show();
                            startTimes = "";
                            tvStartTimes.setText("请选择");
                            return;
                        }
                        tvStartTimes.setText(choose);
                        timeSpan = DateUtils.getInstance().getTimeData(startTimes, endTimes, startTime, endTime, timeCaculate);
                        tvWorkTime.setText(timeSpan);
                    }
                });

                break;
            //结束日期
            case R.id.rl_end_time:

                TimeDialogUtils.getInstance().getTime(this, new OnTimeCallBack() {
                    @Override
                    public void getYearTime(String year) {
                        endTime = year;
                        long timeCaculate2 = DateUtils.getInstance().getTimeCaculate(startTime, endTime);
                        if (timeCaculate2 < 0 || (timeCaculate2 == 0 && startTimes.equals("下午") && endTimes.equals("上午"))) {
                            builderError.show();
                            endTime = "";
                            tvEndTime.setText("请选择");
                            return;
                        }
                        tvEndTime.setText(year);

                        timeSpan = DateUtils.getInstance().getTimeData(startTimes, endTimes, startTime, endTime, timeCaculate);
                        tvWorkTime.setText(timeSpan);
                    }

                    @Override
                    public void getMinuteTime(String minute) {

                    }
                });

                break;
            //照片选择
            case R.id.iv_apply_camera:

                if (imgList.size() >= 9) {
                    ToastUtils.getMineToast("最多选择9张图片");
                    return;
                }
                int numImg = imgNum - imgList.size();

                PhotoUtils.getInstance().getMoreLocalPhoto(this, numImg, new PhotoCallBack() {
                    @Override
                    public void getPhoto(String path) {
                        String[] split = path.split(",");
                        if (split == null) {
                            return;
                        }

                        int num = split.length;
                        for (int i = 0; i < num; i++) {
                            imgList.add(split[i]);
                        }

                        imageAddAdapter.notifyDataSetChanged();
                    }
                });
                break;
            //选择审批人
            case R.id.rl_apply_check:
                Intent intent = new Intent(this, ChoosCheckManActivity.class);
                intent.putExtra(IntentUtils.getInstance().TITLE, "审批人");
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.tv_csr:
                if (checkImgBeenList.size() >= 3) {
                    ToastUtils.getMineToast("最多选择3个抄送人");
                    return;
                }
                Intent intent2 = new Intent(this, ChoosCheckManActivity.class);
                intent2.putExtra(IntentUtils.getInstance().TITLE, "抄送人");
                startActivityForResult(intent2, 2);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }

        if (requestCode == REQUEST_CODE) {

            if (isHave(approveId)) {
                ToastUtils.getMineToast("审批人不能与抄送人相同");
                approveId = "";
                return;
            }
            checkList.clear();
            approveId = data.getStringExtra("id");
//            tvCheckMan.setText(data.getStringExtra("name"));
            String approveId = data.getStringExtra("id");
            String name = data.getStringExtra("name");
            String img = data.getStringExtra("img");
            CheckImgBean checkImgBean = new CheckImgBean();
            checkImgBean.setName(name);
            checkImgBean.setImg(img);
            checkImgBean.setId(approveId);
            checkList.add(checkImgBean);

            checkAdapter.notifyDataSetChanged();
        }else if(requestCode == 2){
            csMan(data);
        }
    }

    @Subscriber(tag = "checkManIdMetting")
    public void onCheckMan(String info) {
        String id = "";
        String name = "";
        String img = "";
        String type = "";

        String[] check = info.split(",");
        id = check[0];
        name = check[1];
        img = check[2];
        if(check.length==4){
            type = check[3];
        }
        LogUtils.i(id + "   " + name + "  " + img);
        Intent intent = new Intent();
        intent.putExtra("id", id);
        intent.putExtra("name", name);
        intent.putExtra("img", img);
        intent.putExtra("type",type);
        csMan(intent);
    }

    public void csMan(Intent data){
        String approveIds = data.getStringExtra("id");
        if (approveIds.equals(approveId)) {
            ToastUtils.getMineToast("审批人不能与抄送人相同");
            return;
        }
        if (isHave(approveIds)) {
            ToastUtils.getMineToast("抄送人不能重复");
            return;
        }
        String name = data.getStringExtra("name");
        String img = data.getStringExtra("img");
        CheckImgBean checkImgBean = new CheckImgBean();
        checkImgBean.setName(name);
        checkImgBean.setImg(img);
        checkImgBean.setId(approveIds);
        checkImgBeenList.add(checkImgBean);
        checkImgAdapter.notifyDataSetChanged();
    }

    public boolean isHave(String id) {
        for (CheckImgBean checkBean : checkImgBeenList) {
            if (id.equals(checkBean.getId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
