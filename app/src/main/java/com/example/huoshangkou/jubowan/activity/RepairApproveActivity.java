package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.LoginFunction;
import com.example.huoshangkou.jubowan.activity.function.RepairApproveFunction;
import com.example.huoshangkou.jubowan.adapter.RepairApproveTypeAdapter;
import com.example.huoshangkou.jubowan.adapter.RepairWorkAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.RepairApproveObjBean;
import com.example.huoshangkou.jubowan.bean.RepairBean;
import com.example.huoshangkou.jubowan.bean.RepairObjBean;
import com.example.huoshangkou.jubowan.bean.RepairWorkBean;
import com.example.huoshangkou.jubowan.inter.ApproveCallBack;
import com.example.huoshangkou.jubowan.inter.OnAddWorkCallBack;
import com.example.huoshangkou.jubowan.inter.OnRepairApproveCallBack;
import com.example.huoshangkou.jubowan.inter.OnRepairApproveDataCallBack;
import com.example.huoshangkou.jubowan.inter.OnRepairWorkCallBack;
import com.example.huoshangkou.jubowan.inter.OnTimeCallBack;
import com.example.huoshangkou.jubowan.photo.PhotoCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.CardUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.EditDialogUtils;
import com.example.huoshangkou.jubowan.utils.EditTextUtils;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.InputUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.TimeDialogUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollGridView;
import com.example.huoshangkou.jubowan.view.ScrollListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：RepairApproveActivity
 * 描述：维修认证界面
 * 创建时间：2017-03-01  10:39
 */

public class RepairApproveActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.iv_work_image)
    ImageView imgWork;
    @Bind(R.id.grid_repair_approve)
    ScrollGridView scrollGridView;
    @Bind(R.id.lv_repair_work)
    ScrollListView scrollListView;

    List<RepairObjBean> list;
    List<RepairWorkBean> workBeanList;

    RepairApproveTypeAdapter approveTypeAdapter;
    RepairWorkAdapter workAdapter;
    @Bind(R.id.et_true_name)
    EditText etTrueName;
    @Bind(R.id.et_card_no)
    EditText etCardNo;

    @Bind(R.id.tv_repair_address)
    TextView tvAddress;
    @Bind(R.id.et_resume)
    EditText etResume;

    //维修机械类别被选中的下标
    private int repairClickPosition = -1;

    //是否审核完成
    private boolean isApproveFinish = false;

    //真实姓名
    private String trueName;
    //身份证号
    private String cardNo;
    //居住地址
    private String address;
    //地址id
    private String addressID;
    //维修机械类别
    private String repairType = "";
    //工作经历
    private String workExp = "";
    //工作证照片
    private String workPic;
    //特长
    private String resume;

    //地址选择
    private final int CHOOSE_ADDRESS = 1;

    //保存工作经历
    private final int SAVE_WORK = 2;


    //я经历分割符   ю每段经历分割符
    private final String EXP_1 = "я";
    private final String EXP_2 = "ю";

    String state;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SAVE_WORK:
                    trueName = etTrueName.getText().toString().trim();
                    if (!StringUtils.isNoEmpty(trueName)) {
                        ToastUtils.getMineToast("请输入真实姓名");
                        return;
                    }

                    cardNo = etCardNo.getText().toString().trim();
                    if (!StringUtils.isNoEmpty(cardNo)) {
                        ToastUtils.getMineToast("请输入身份证号");
                        return;
                    }

                    if (!CardUtils.isIdCard(cardNo)) {
                        ToastUtils.getMineToast("请输入正确的身份证号");
                        return;
                    }

                    if (!StringUtils.isNoEmpty(address)) {
                        ToastUtils.getMineToast("请输入居住地址");
                        return;
                    }

                    repairType = "";
                    int numType = list.size();
                    for (int i = 0; i < numType; i++) {
                        if (list.get(i).isCheck()) {
                            repairType += list.get(i).getName() + ",";
                        }
                    }

                    if (!StringUtils.isNoEmpty(repairType)) {
                        ToastUtils.getMineToast("请选择维修机械类别");
                        return;
                    }


                    int num = workBeanList.size();
                    for (int i = 0; i < num; i++) {
                        if (!StringUtils.isNoEmpty(workBeanList.get(i).getEndTime()) ||
                                !StringUtils.isNoEmpty(workBeanList.get(i).getStartTime()) ||
                                !StringUtils.isNoEmpty(workBeanList.get(i).getWorkCompany()) ||
                                !StringUtils.isNoEmpty(workBeanList.get(i).getWorkType())) {
                            ToastUtils.getMineToast("请完善工作经历");
                            return;
                        }
                    }

                    //я经历分割符   ю每段经历分割符
                    for (int i = 0; i < num; i++) {
                        workExp += workBeanList.get(i).getStartTime() + EXP_1
                                + workBeanList.get(i).getEndTime() + EXP_1
                                + workBeanList.get(i).getWorkCompany() + EXP_1
                                + workBeanList.get(i).getWorkType() + EXP_2;
                    }

                    if (!StringUtils.isNoEmpty(workPic)) {
                        ToastUtils.getMineToast("请选择工作证照片");
                        return;
                    }

                    resume = etResume.getText().toString().trim();


                    CopyIosDialogUtils.getInstance().getIosDialog(RepairApproveActivity.this, getString(R.string.is_commit_approve), new CopyIosDialogUtils.iosDialogClick() {
                        @Override
                        public void confimBtn() {
                            RepairApproveFunction.getInstance().repairApprove(RepairApproveActivity.this, trueName,
                                    cardNo, workPic, repairType, addressID, resume, workExp, new ApproveCallBack() {
                                        @Override
                                        public void onApproveSuccess() {
                                            ToastUtils.getMineToast(getString(R.string.approve_message));
                                            ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().approveList);
                                            LoginFunction.getInstance().saveApproveInfo("审核中", "7");
                                        }

                                        @Override
                                        public void onApproveFail() {
                                        }
                                    });
                        }

                        @Override
                        public void cancelBtn() {

                        }
                    });
                    break;
            }
        }
    };

    @Override
    public int initLayout() {
        return R.layout.activity_repair_approve;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().approveList);
        tvTitle.setText("维修工程师认证");
        list = new ArrayList<>();
        workBeanList = new ArrayList<>();
        tvRight.setText("保存");

        state = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        //已认证
        if (state.equals(getString(R.string.has_checking))) {
            isApproveFinish = true;
            tvRight.setText("");
            //审核中
        } else if (state.equals(getString(R.string.checking))) {
            isApproveFinish = false;
        }

        approveTypeAdapter = new RepairApproveTypeAdapter(this, list, R.layout.item_repair_approve);
        scrollGridView.setAdapter(approveTypeAdapter);

        //获取维修机械模块
        RepairApproveFunction.getInstance().getRepairMode(this, new OnRepairApproveCallBack() {
            @Override
            public void onSuccess(RepairBean repairBean) {
                List<RepairObjBean> repairObjBeanList = repairBean.getReList();
                int num = repairObjBeanList.size();
                for (int i = 0; i < num; i++) {
                    RepairObjBean repairObjBean = repairObjBeanList.get(i);
                    String type = repairObjBean.getName().substring(0, repairObjBean.getName().length() - 2);
                    repairObjBean.setName(type);
                    list.add(repairObjBean);
                }
                approveTypeAdapter.notifyDataSetChanged();

                getRepairApproveData();
            }
        });


        //单选事件
        scrollGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (isApproveFinish) {
                    return;
                }
                if (list.get(i).isCheck()) {
                    list.get(i).setCheck(false);
                } else {
                    list.get(i).setCheck(true);
                }
                approveTypeAdapter.notifyDataSetChanged();
            }
        });

        RepairWorkBean repairWorkBean = new RepairWorkBean();
        workBeanList.add(repairWorkBean);

        workAdapter = new RepairWorkAdapter(this, workBeanList, R.layout.item_work_exprince);
        scrollListView.setAdapter(workAdapter);
        scrollListView.setDividerHeight(0);

        //时间选择
        workAdapter.setWorkCallBack(new OnRepairWorkCallBack() {
            @Override
            public void onStartTime(final int position) {
                TimeDialogUtils.getInstance().getTime(RepairApproveActivity.this, new OnTimeCallBack() {
                    @Override
                    public void getYearTime(String year) {
                        if (isApproveFinish) {
                            return;
                        }
                        if (StringUtils.isNoEmpty(workBeanList.get(position).getEndTime())) {
                            long timeCaculate = DateUtils.getInstance().getTimeCaculate(year, workBeanList.get(position).getEndTime());
                            if (timeCaculate < 0) {
                                ToastUtils.getMineToast("开始时间不能大于结束时间");
                                return;
                            }
                        }

                        workBeanList.get(position).setStartTime(year);
                        workAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void getMinuteTime(String minute) {

                    }
                });
            }

            @Override
            public void onEndTime(final int position) {
                if (isApproveFinish) {
                    return;
                }
                TimeDialogUtils.getInstance().getTime(RepairApproveActivity.this, new OnTimeCallBack() {
                    @Override
                    public void getYearTime(String year) {
                        if (StringUtils.isNoEmpty(workBeanList.get(position).getStartTime())) {
                            long timeCaculate = DateUtils.getInstance().getTimeCaculate(workBeanList.get(position).getStartTime(), year);
                            if (timeCaculate < 0) {
                                ToastUtils.getMineToast("结束时间不能小于开始时间");
                                return;
                            }
                        }

                        workBeanList.get(position).setEndTime(year);
                        workAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void getMinuteTime(String minute) {

                    }
                });
            }

            @Override
            public void deleteWork(final int position) {
                if (isApproveFinish) {
                    return;
                }

                //删除
                CopyIosDialogUtils.getInstance().getIosDialog(RepairApproveActivity.this, "是否删除该工作经历", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        workBeanList.remove(position);
                        workAdapter.notifyDataSetChanged();
                        ToastUtils.getMineToast("删除成功");
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
            }

            @Override
            public void onAddWork() {
                if (isApproveFinish) {
                    return;
                }
                //删除
                CopyIosDialogUtils.getInstance().getIosDialog(RepairApproveActivity.this, "是否添加工作经历", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        RepairWorkBean repairWorkBean = new RepairWorkBean();
                        workBeanList.add(repairWorkBean);
                        workAdapter.notifyDataSetChanged();
                        ToastUtils.getMineToast("添加成功");
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });

            }

            //            添加公司
            @Override
            public void onAddCompany(final int position) {
                if (isApproveFinish) {
                    return;
                }
                EditDialogUtils.getInstance().showEditTextDialog("text", RepairApproveActivity.this, "请输入工作单位", new OnAddWorkCallBack() {
                    @Override
                    public void addWorkExp(String content) {
                        workBeanList.get(position).setWorkCompany(content);
                        workAdapter.notifyDataSetChanged();
                        InputUtils.getInstance().inputKeyUtils(RepairApproveActivity.this);
                    }
                });
            }

            //            添加职位  21   21,22  21,23,42
            @Override
            public void onAddWorkType(final int position) {
                if (isApproveFinish) {
                    return;
                }
                EditDialogUtils.getInstance().showEditTextDialog("text", RepairApproveActivity.this, "请输入工作职位", new OnAddWorkCallBack() {
                    @Override
                    public void addWorkExp(String content) {
                        workBeanList.get(position).setWorkType(content);
                        workAdapter.notifyDataSetChanged();
                        InputUtils.getInstance().inputKeyUtils(RepairApproveActivity.this);
                    }
                });
            }
        });


        //编辑完成输入框不可操作
        if (isApproveFinish) {
            EditTextUtils.getInstance().setUnEdit(etTrueName);
            EditTextUtils.getInstance().setUnEdit(etCardNo);
            EditTextUtils.getInstance().setUnEdit(etResume);
        }

    }


    //获取维修工程师认证信息
    public void getRepairApproveData() {
        RepairApproveFunction.getInstance().getRepairData(this, new OnRepairApproveDataCallBack() {
            @Override
            public void onApproveSuccess(RepairApproveObjBean approveBean) {
                trueName = approveBean.getRealName();
                etTrueName.setText(trueName);

                cardNo = approveBean.getUserCardNo();
                etCardNo.setText(cardNo);

                getWorkExp(approveBean.getDescript());

                workPic = approveBean.getWorkPic();
                GlideUtils.getInstance().displayImage(workPic, RepairApproveActivity.this, imgWork);

                resume = approveBean.getResume();
                etResume.setText(resume);

                //获取维修机械名称
                repairType = approveBean.getWxjiqiName();

                if (!StringUtils.isNoEmpty(repairType)) {
                    return;
                }
                String[] split = repairType.split(",");

                int num = list.size();
                int num2 = split.length;
                for (int i = 0; i < num; i++) {
                    for (int j = 0; j < num2; j++) {
                        if (split[j].equals(list.get(i).getName())) {
                            list.get(i).setCheck(true);
                        }
                    }
                }
                approveTypeAdapter.setItemClick(repairClickPosition);
                approveTypeAdapter.notifyDataSetChanged();
                address = approveBean.getAddress();
                addressID = approveBean.getAddressID() + "";
                tvAddress.setText(approveBean.getAddress() + "  ");
            }
        });
    }

    @OnClick({R.id.ll_back, R.id.iv_work_image, R.id.tv_right, R.id.rl_address})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                //退出该界面
                ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().approveList);
                break;
            case R.id.iv_work_image:
                if (!isApproveFinish) {
                    PhotoUtils.getInstance().getPhotoSelectUtils(this, new PhotoCallBack() {
                        @Override
                        public void getPhoto(String path) {
                            workPic = path;
                            GlideUtils.getInstance().displayImage(path, RepairApproveActivity.this, imgWork);
                        }
                    });
                } else {
                    List<String> imgList = new ArrayList<>();
                    imgList.add(workPic);
                    IntentUtils.getInstance().toImageShowActivity(RepairApproveActivity.this, imgList, 1);
                }
                break;
            case R.id.tv_right:
                workExp = "";
                handler.sendEmptyMessageDelayed(SAVE_WORK, 0);
                break;
            //居住地址
            case R.id.rl_address:
                if (isApproveFinish) {
                    return;
                }
                Intent intent = new Intent(this, AddressListActivity.class);
                intent.putExtra(IntentUtils.getInstance().TYPE, IntentUtils.getInstance().ADDRESS_CHOOSE);
                startActivityForResult(intent, CHOOSE_ADDRESS);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CHOOSE_ADDRESS:
                if (data == null) {
                    return;
                }
                address = data.getStringExtra(IntentUtils.getInstance().ADDRESS_NAME);
                tvAddress.setText(address + "  ");
                addressID = data.getStringExtra(IntentUtils.getInstance().ADDRESS_ID);
                break;
        }
    }

    //获取工作经历  格式：2017-03-10,2017-03-12,logo红,log哦iOS;
    public void getWorkExp(String workExp) {
        workBeanList.clear();

        if (!StringUtils.isNoEmpty(workExp)) {
            return;
        }

        String[] split = workExp.split(EXP_2);

        int num = split.length;
        for (int i = 0; i < num; i++) {
            RepairWorkBean workBean = new RepairWorkBean();
            String[] split1 = split[i].split(EXP_1);
            if(split1.length==4){
                workBean.setStartTime(split1[0]);
                workBean.setEndTime(split1[1]);
                workBean.setWorkCompany(split1[2]);
                workBean.setWorkType(split1[3]);

                workBeanList.add(workBean);
            }

        }
        workAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().approveList);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
