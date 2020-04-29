package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.ApproveFunction;
import com.example.huoshangkou.jubowan.activity.function.ImageAddFunction;
import com.example.huoshangkou.jubowan.adapter.ApproveAgreeAdapter;
import com.example.huoshangkou.jubowan.adapter.CheckImgAdapter;
import com.example.huoshangkou.jubowan.adapter.ImageAddAdapter;
import com.example.huoshangkou.jubowan.adapter.LanImageShowAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ApproveBankListBean;
import com.example.huoshangkou.jubowan.bean.ApproveDetailListBean;
import com.example.huoshangkou.jubowan.bean.ApproveListBean;
import com.example.huoshangkou.jubowan.bean.CheckImgBean;
import com.example.huoshangkou.jubowan.constant.ApproveConstant;
import com.example.huoshangkou.jubowan.constant.PhotoConstant;
import com.example.huoshangkou.jubowan.inter.ChooseDialogPositionCallBack;
import com.example.huoshangkou.jubowan.inter.OnApplyCommitCallBack;
import com.example.huoshangkou.jubowan.inter.OnTimeCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.photo.PhotoCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.KeyBoardUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.MoneyUtils;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.PickDialogUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.TimeDialogUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollGridView;
import com.example.huoshangkou.jubowan.view.ScrollListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：EditApproveActivity
 * 描述：
 * 创建时间：2018-02-08  14:20
 */

public class EditApproveActivity extends BaseActivity {

    private final int REQUEST_CODE = 1;
    private final int REQUEST_CODE_BANK = 2;

    ApproveDetailListBean approveDetailListBean;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_use_type)
    EditText etUseType;
    @Bind(R.id.et_money)
    EditText etMoney;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.et_money_type)
    TextView etMoneyType;
    @Bind(R.id.et_address)
    EditText etAddress;
    @Bind(R.id.cb_invoice)
    CheckBox cbInvoice;
    @Bind(R.id.tv_apply_introduce)
    EditText tvApplyIntroduce;
    @Bind(R.id.grid_apply_img)
    ScrollGridView gridApplyImg;
    @Bind(R.id.ll_money_type)
    RelativeLayout llMoneyType;
    @Bind(R.id.tv_apply_check)
    TextView tvCheckMan;

    @Bind(R.id.recyc_check)
    RecyclerView recyclerView;
    @Bind(R.id.recyc_check_man)
    RecyclerView recyclerViewMan;

    private List<String> imgList;
    private List<String> localImgList;
    private int imgNum = 9;
    ImageAddAdapter imageAddAdapter;


    private String type = "";
    //用款类型
    private String moneyType = "";
    //用款金额
    private String moneyPrice = "";
    //使用日期
    private String time = "";
    //使用地点
    private String area = "";
    //是否有发票   0没有发票 1有发票  默认没有发票
    private String invoince = "0";
    //用款明细
    private String remak = "";
    //审批人
    private String approveId = "";
    private String pics = "";
    private String fundWay = "";
    private String bankId = "";
    private String csId = "";
    //是否是用款
    private boolean isYk = false;

    //审批人
    CheckImgAdapter checkAdapter;
    //抄送人
    CheckImgAdapter checkImgAdapter;

    List<CheckImgBean> checkImgBeenList = new ArrayList<>();
    List<CheckImgBean> checkList = new ArrayList<>();

    @Override
    public int initLayout() {
        return R.layout.activity_edit_approve;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().approveCheckList);
        approveDetailListBean = (ApproveDetailListBean) getIntent().getSerializableExtra(IntentUtils.getInstance().BEAN_TYPE);
        tvRight.setText("保存");
        imgList = new ArrayList<>();
        localImgList = new ArrayList<>();

        //报销
        if (approveDetailListBean.getApprovalTypeID() == 1) {
            tvTitle.setText("报销编辑");
            llMoneyType.setVisibility(View.GONE);
            type = ApproveConstant.getInstance().FEED_BX + "";
            //用款
        } else if (approveDetailListBean.getApprovalTypeID() == 2) {
            tvTitle.setText("用款编辑");
            type = ApproveConstant.getInstance().MONEY_USE + "";
            isYk = true;
        }

        etUseType.setText(approveDetailListBean.getTypeName());
        etMoney.setText(MoneyUtils.getInstance().getFormPrice(approveDetailListBean.getTypePrice() + ""));
        remak = approveDetailListBean.getRemark();
        tvApplyIntroduce.setText(approveDetailListBean.getRemark());
        fundWay = approveDetailListBean.getFundWay();
        etMoneyType.setText(approveDetailListBean.getFundWay());

        time = DateUtils.getInstance().getFormData(approveDetailListBean.getCreateTime());
        tvTime.setText(DateUtils.getInstance().getFormData(approveDetailListBean.getCreateTime()));
        etAddress.setText(approveDetailListBean.getAddress());

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

        if (approveDetailListBean.getInvoice() == 1) {
            cbInvoice.setChecked(true);
        } else {
            cbInvoice.setChecked(false);
        }

        //图片
        String[] split = approveDetailListBean.getPics().split(",");
        int num = split.length;
        for (int i = 0; i < num; i++) {
            if (split[i].length() > 5) {
                imgList.add(split[i]);
            }
        }

        imageAddAdapter = new ImageAddAdapter(this, imgList, "");
        ImageAddFunction.addImageFun(imageAddAdapter, imgList, this, gridApplyImg);

        cbInvoice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    invoince = "1";
                } else {
                    invoince = "0";
                }
            }
        });
    }

    @OnClick({R.id.rl_apply_check,R.id.tv_csr, R.id.tv_right, R.id.ll_back, R.id.tv_time, R.id.et_money_type, R.id.tv_choose_bank, R.id.iv_apply_camera})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_time:
                KeyBoardUtils.closeKeybord(etAddress, getContext());
                TimeDialogUtils.getInstance().getTime(this, new OnTimeCallBack() {
                    @Override
                    public void getYearTime(String year) {
                        time = year;
                        tvTime.setText(year);
                    }

                    @Override
                    public void getMinuteTime(String minute) {

                    }
                });
                break;
            case R.id.iv_apply_camera:
                if (imgList.size() >= 9) {
                    ToastUtils.getMineToast("最多选择9张图片");
                    return;
                }
                int nums = imgNum - imgList.size();

                PhotoUtils.getInstance().getMoreLocalPhoto(this, nums, new PhotoCallBack() {
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
            case R.id.et_money_type:
                ArrayList<String> list = new ArrayList<>();
                list.add("现金");
                list.add("承兑");
                list.add("其他");
                PickDialogUtils.getInstance().getChoosePositionDialog(getContext(), "请选择用款款项", list, new ChooseDialogPositionCallBack() {
                    @Override
                    public void onGetMessagePosition(String message, int position) {
                        fundWay = message;
                        etMoneyType.setText(message);
                    }
                });
                break;
            case R.id.tv_right:
                moneyType = etUseType.getText().toString().trim();
                if (!StringUtils.isNoEmpty(moneyType)) {
                    ToastUtils.getMineToast("请输入类型");
                    return;
                }
                moneyPrice = etMoney.getText().toString().trim();
                if (!StringUtils.isNoEmpty(moneyPrice)) {
                    ToastUtils.getMineToast("请输入金额");
                    return;
                }
                if (!StringUtils.isNoEmpty(time)) {
                    ToastUtils.getMineToast("请选择时间");
                    return;
                }
                area = etAddress.getText().toString().trim();
                if (!StringUtils.isNoEmpty(area)) {
                    ToastUtils.getMineToast("请输入地址");
                    return;
                }
                fundWay = etMoneyType.getText().toString().trim();
                if (!StringUtils.isNoEmpty(fundWay) && isYk) {
                    ToastUtils.getMineToast("请选择用款方式");
                    return;
                }
                if (!StringUtils.isNoEmpty(approveId)) {
                    ToastUtils.getMineToast("请选择审批人");
                    return;
                }
                for (CheckImgBean checkImgBean : checkImgBeenList) {
                    csId += checkImgBean.getId() + ";";
                }
                remak = tvApplyIntroduce.getText().toString().trim();
                PhotoUtils.getInstance().mutilLocalImageUp(imgList, this, new StringCallBack() {
                    @Override
                    public void onSuccess(String str) {
                        pics = str;
                        CopyIosDialogUtils.getInstance().getIosDialog(EditApproveActivity.this, getString(R.string.is_commit_apply), new CopyIosDialogUtils.iosDialogClick() {
                            @Override
                            public void confimBtn() {
                                ApproveFunction.getInstance().applyToApprove(getContext(), type, moneyType, moneyPrice,
                                        "", "", "", "", area, invoince, remak, pics, approveId, "", fundWay, bankId, csId,time,"",new OnApplyCommitCallBack() {
                                            @Override
                                            public void onSuccess() {
                                                ToastUtils.getMineToast(getString(R.string.commit_success) + ",请在申请列表中查看");
                                                ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().approveCheckList);
                                            }

                                            @Override
                                            public void onFail() {
                                                ToastUtils.getMineToast(getString(R.string.commit_fail));
                                            }
                                        });
                            }

                            @Override
                            public void cancelBtn() {

                            }
                        });
                    }

                    @Override
                    public void onFail() {

                    }
                });
                break;
            //选择银行信息
            case R.id.tv_choose_bank:
//                IntentUtils.getInstance().toActivity(this,ApproveBankActivity.class);
                Intent intentBank = new Intent(this, ApproveBankActivity.class);
                startActivityForResult(intentBank, REQUEST_CODE_BANK);
                break;
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
                startActivityForResult(intent2, 3);
                break;
        }
    }

    private StaggeredGridLayoutManager getLayoutManager() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL);
        return layoutManager;
    }


    //判断是否有改人
    public boolean isHave(String id) {
        for (CheckImgBean checkBean : checkImgBeenList) {
            if (id.equals(checkBean.getId())) {
                return true;
            }
        }
        return false;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }

        if (requestCode == REQUEST_CODE) {
            approveId = data.getStringExtra("id");
            if (isHave(approveId)) {
                ToastUtils.getMineToast("审批人不能与抄送人相同");
                approveId = "";
                return;
            }
            checkList.clear();
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

        } else if (requestCode == REQUEST_CODE_BANK) {
            ApproveBankListBean bankListBean = (ApproveBankListBean) data.getSerializableExtra("bean");
            bankId = bankListBean.getID() + "";
            tvApplyIntroduce.setText("公司名称：" + bankListBean.getCompany() + "\n"
                    + "开户银行：" + bankListBean.getBankName() + "\n"
                    + "银行账号：" + bankListBean.getBankAccount());
        } else if (requestCode == 3) {
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

//        if (requestCode == REQUEST_CODE) {
//            approveId = data.getStringExtra("id");
//            tvCheckMan.setText(data.getStringExtra("name"));
//
//        } else if (requestCode == REQUEST_CODE_BANK) {
//            ApproveBankListBean bankListBean = (ApproveBankListBean) data.getSerializableExtra("bean");
//            bankId = bankListBean.getID() + "";
//            tvApplyIntroduce.setText("公司名称：" + bankListBean.getCompany() + "\n"
//                    + "开户银行：" + bankListBean.getBankName() + "\n"
//                    + "银行账号：" + bankListBean.getBankAccount());
//        }
    }
}
