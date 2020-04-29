package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.ImageAddFunction;
import com.example.huoshangkou.jubowan.adapter.CheckImgAdapter;
import com.example.huoshangkou.jubowan.adapter.ImageAddAdapter;
import com.example.huoshangkou.jubowan.adapter.LanImageShowAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.CheckImgBean;
import com.example.huoshangkou.jubowan.bean.MoneyMessageListBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.photo.PhotoCallBack;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.MoneyUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：QiJiaMoneyActivity
 * 描述：借款借据/信用额度
 * 创建时间：2017-07-11  16:30
 */

public class QiJiaMoneyActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_1)
    TextView tv_1;

    //图片
    List<String> imgList;
    //图片适配器
    ImageAddAdapter imageAddAdapter;
    @Bind(R.id.grid_view_apply)
    ScrollGridView scrollGridView;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.tv_apply_list)
    TextView tvApplyList;
    @Bind(R.id.tv_apply_check)
    TextView tvApplyCheck;
    @Bind(R.id.tv_apply_name)
    EditText tvApplyName;
    @Bind(R.id.tv_register_account)
    EditText tvRegisterAccount;
    @Bind(R.id.tv_rz_dw)
    EditText tvRzDw;
    @Bind(R.id.tv_xy_ed)
    EditText tvXyEd;
    @Bind(R.id.tv_extra_money)
    EditText tvExtraMoney;
    @Bind(R.id.tv_get_account)
    EditText tvGetAccount;
    @Bind(R.id.tv_open_account)
    EditText tvOpenAccount;
    @Bind(R.id.textView4)
    TextView textView4;
    @Bind(R.id.tv_money_use)
    EditText tvMoneyUse;
    @Bind(R.id.tv_loan_lv)
    EditText tvLoanLv;
    @Bind(R.id.et_borrow_price)
    EditText etBorrowPrice;
    @Bind(R.id.et_borrow_use)
    EditText etBorrowUse;
    @Bind(R.id.tv_money_time)
    EditText etMoneyTime;

    @Bind(R.id.tv_time_left)
    TextView tvTimeLeft;
    @Bind(R.id.tv_type)
    TextView tvType;
    @Bind(R.id.ll_money_use)
    LinearLayout llMoneyUse;
    @Bind(R.id.ll_borrow)
    LinearLayout llBorrow;
    @Bind(R.id.ll_xy_ed)
    LinearLayout llXyEd;

    @Bind(R.id.et_xy_name)
    EditText etXyName;
    @Bind(R.id.et_xy_type)
    EditText etXyType;
    @Bind(R.id.et_xy_link)
    EditText etXyLink;
    @Bind(R.id.et_xy_address)
    EditText etXyAddress;
    @Bind(R.id.et_xy_years)
    EditText etXyYears;
    @Bind(R.id.et_xy_phone)
    EditText etXyPhone;
    @Bind(R.id.et_xy_email)
    EditText etXyEmail;

    @Bind(R.id.recyc_check)
    RecyclerView recyclerView;
    @Bind(R.id.recyc_check_man)
    RecyclerView recyclerViewMan;

    //审批人
    CheckImgAdapter checkAdapter;
    //抄送人
    CheckImgAdapter checkImgAdapter;
    List<CheckImgBean> checkImgBeenList = new ArrayList<>();
    List<CheckImgBean> checkList = new ArrayList<>();

    private int imgNum = 9;
    //借款单位
    private String borrowName = "";
    //借款类型
    private String borrowType = "";
    //经营年限
    private String opperatingTime = "";
    //联系人
    private String linkMan = "";
    //通讯地址
    private String linkAddress = "";
    //E-Mail
    private String E_Mail = "";
    //借款金额
    private String borrow_money = "";
    //借款期限
    private String borrowTime = "一年";
    //借款用途
    private String borrowUse = "";
    //电话
    private String phone = "";
    private final int REQUEST_CODE = 1;
    //审批人id
    private String approveId = "";
    //信息合同号
    private String contractNo = "";
    //订单id
    private String orderId = "";
    //借据id
    private String id = "";
    //借款期限
    private ArrayList<String> dayList;
    //齐家借款 或者 信用额度
    private String checkType = "";
    //是否是信用额度
    private boolean isTrick = false;
    //抄送人
    private String csId = "";
    private String ApprovalTypeID = "";

    @Override
    public int initLayout() {
        return R.layout.activity_qi_jia;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("信用额度");
        tvRight.setText("保存");

        checkType = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);

        if (checkType.equals("借据审批")) {
            isTrick = false;
            ApprovalTypeID = "1009";
            tvTitle.setText("借据审批");

            tvApplyName.setClickable(false);
            tvRegisterAccount.setClickable(false);
            tvRzDw.setClickable(false);
            tvXyEd.setClickable(false);
            tvExtraMoney.setClickable(false);
            tvOpenAccount.setClickable(false);
            tvGetAccount.setClickable(false);
            tvMoneyUse.setClickable(false);
            tvLoanLv.setClickable(false);
            etMoneyTime.setClickable(false);
            etBorrowUse.setClickable(false);

            tvApplyName.setFocusable(false);
            tvRegisterAccount.setFocusable(false);
            tvRzDw.setFocusable(false);
            tvXyEd.setFocusable(false);
            tvExtraMoney.setFocusable(false);
            tvOpenAccount.setFocusable(false);
            tvGetAccount.setFocusable(false);
            tvMoneyUse.setFocusable(false);
            tvLoanLv.setFocusable(false);
            etMoneyTime.setFocusable(false);
            etBorrowUse.setFocusable(false);
            tvTimeLeft.setText("借款日期");
            tvType.setText("借款金额");
            llMoneyUse.setVisibility(View.VISIBLE);
            llXyEd.setVisibility(View.GONE);
            llBorrow.setVisibility(View.VISIBLE);

        } else if (checkType.equals("信用额度")) {
            etMoneyTime.setFocusable(false);
            etMoneyTime.setClickable(false);
            etMoneyTime.setText("一年");
            isTrick = true;
            ApprovalTypeID = "1007";
            tvTitle.setText("信用额度");
            tvTimeLeft.setText("授权期限");
            tvType.setText("信用额度");
            llMoneyUse.setVisibility(View.GONE);
            llXyEd.setVisibility(View.VISIBLE);
            llBorrow.setVisibility(View.GONE);
            tv_1.setText("信用申请列表");
        }

        imgList = new ArrayList<>();
        dayList = new ArrayList<>();

        for (int i = 1; i <= 90; i++) {
            dayList.add(i + "天");
        }

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

        //图片显示封装
        imageAddAdapter = new ImageAddAdapter(this, imgList, "");
        ImageAddFunction.addImageFun(imageAddAdapter, imgList, this, scrollGridView);
    }

    private StaggeredGridLayoutManager getLayoutManager() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL);
        return layoutManager;
    }


    @OnClick({R.id.rl_apply_list, R.id.ll_back, R.id.iv_photo, R.id.tv_right, R.id.tv_csr, R.id.rl_check_man})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.iv_photo:
                if (imgList.size() >= 9) {
                    ToastUtils.getMineToast("最多选择9张图片");
                    return;
                }
                int num = imgNum - imgList.size();

                PhotoUtils.getInstance().getMoreLocalPhoto(this, num, new PhotoCallBack() {
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
            case R.id.tv_right:
                if (!StringUtils.isNoEmpty(contractNo)) {
                    ToastUtils.getMineToast("请选择借款借据号");
                    return;
                }
                borrowName = tvRzDw.getText().toString().trim();
                if (!StringUtils.isNoEmpty(borrowName) && !isTrick) {
                    ToastUtils.getMineToast("请输入借款人名称");
                    return;
                }
                borrowTime = etMoneyTime.getText().toString().trim();
                if (!StringUtils.isNoEmpty(borrowTime)) {
                    ToastUtils.getMineToast("请选择借款期限");
                    return;
                }
                borrowUse = tvMoneyUse.getText().toString().trim();
                if (!isTrick && !StringUtils.isNoEmpty(borrowUse)) {
                    ToastUtils.getMineToast("请输入借款用途");
                    return;
                }
                borrowName = etXyName.getText().toString().trim();
                if (!StringUtils.isNoEmpty(borrowName) && isTrick) {
                    ToastUtils.getMineToast("请输入申请人姓名");
                    return;
                }
                borrowType = etXyType.getText().toString().trim();
                if (!StringUtils.isNoEmpty(borrowType) && isTrick) {
                    ToastUtils.getMineToast("请输入申请类型");
                    return;
                }
                linkMan = etXyLink.getText().toString().trim();
                if (!StringUtils.isNoEmpty(linkMan) && isTrick) {
                    ToastUtils.getMineToast("请输入联系人");
                    return;
                }
                opperatingTime = etXyYears.getText().toString().trim();
                if (!StringUtils.isNoEmpty(opperatingTime) && isTrick) {
                    ToastUtils.getMineToast("请输入经营年限");
                    return;
                }
                phone = etXyPhone.getText().toString().trim();
                if (!StringUtils.isNoEmpty(phone) && isTrick) {
                    ToastUtils.getMineToast("请输入联系人电话");
                    return;
                }
                linkAddress = etXyAddress.getText().toString().trim();
                if (!StringUtils.isNoEmpty(linkAddress) && isTrick) {
                    ToastUtils.getMineToast("请输入通讯地址");
                    return;
                }
//                E_Mail = etXyEmail.getText().toString().trim();
//                if (!StringUtils.isNoEmpty(E_Mail) && isTrick) {
//                    ToastUtils.getMineToast("请输入邮箱");
//                    return;
//                }
                borrow_money = MoneyUtils.getInstance().getNormPrice(etBorrowPrice.getText().toString().trim());
                if (!StringUtils.isNoEmpty(borrow_money)) {
                    ToastUtils.getMineToast("请输入借款金额");
                    return;
                }
                double borrowDouble = Double.parseDouble(borrow_money) % 100000;
                if (Double.parseDouble(borrow_money) < 100000 || borrowDouble != 0) {
                    ToastUtils.getMineToast("金额只能为十万的整数倍,并且大于十万");
                    return;
                }
                if (checkList.size() == 1) {
                    approveId = checkList.get(0).getId();
                } else {
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

                PhotoUtils.getInstance().mutilLocalImageUp(imgList, this, new StringCallBack() {
                    @Override
                    public void onSuccess(String str) {
                        addBorrow(borrowName, linkAddress, borrowType, linkMan, str, phone, E_Mail, borrow_money,
                                borrowTime, borrowUse, opperatingTime, orderId, id, csId);
                    }

                    @Override
                    public void onFail() {

                    }
                });


                break;
            //选择审批人
            case R.id.rl_check_man:
                Intent intent = new Intent(this, ChoosCheckManActivity.class);
                intent.putExtra(IntentUtils.getInstance().TITLE, "审批人");
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.rl_apply_list:
                Intent intent1 = new Intent(getContext(), MessageListActivity.class);
                intent1.putExtra(IntentUtils.getInstance().TYPE, checkType);
                startActivityForResult(intent1, 2);
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

    //提交齐家借款功能
    public void addBorrow(String borrowName, String address, String type, String contact, String dataPics,
                          String tel, String email, String loan, String loanPeriod, String loanPurpose,
                          String time, String contractNo, String id, String csId) {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().ADD_BORROWING + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().BORROWS + "=" + EncodeUtils.getInstance().getEncodeString(borrowName) + "&"
                + FieldConstant.getInstance().APPROVE_TYPE_ID + "=" + ApprovalTypeID + "&"
                + FieldConstant.getInstance().ADDR + "=" + EncodeUtils.getInstance().getEncodeString(address) + "&"
                + FieldConstant.getInstance().ID + "=" + id + "&"
                + FieldConstant.getInstance().BORROWS_TYPE + "=" + EncodeUtils.getInstance().getEncodeString(type) + "&"
                + FieldConstant.getInstance().CONTACT + "=" + EncodeUtils.getInstance().getEncodeString(contact) + "&"
                + FieldConstant.getInstance().DATA_PICS + "=" + dataPics + "&"
                + FieldConstant.getInstance().RZ_ORDER_ID + "=" + contractNo + "&"
                + FieldConstant.getInstance().TEL + "=" + tel + "&"
                + FieldConstant.getInstance().E_MAIL + "=" + email + "&"
                + FieldConstant.getInstance().LOAN + "=" + loan + "&"
                + FieldConstant.getInstance().LOAN_PERIOD + "=" + EncodeUtils.getInstance().getEncodeString(loanPeriod) + "&"
                + FieldConstant.getInstance().LOAN_PURPOSES + "=" + EncodeUtils.getInstance().getEncodeString(loanPurpose) + "&"
                + FieldConstant.getInstance().OPERATING_TIME + "=" + time + "&"
                + FieldConstant.getInstance().COPY_USER_ID + "=" + csId + "&"
                + FieldConstant.getInstance().APPROVE_USER_ID + "=" + approveId, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    ToastUtils.getMineToast("提交成功");
                    QiJiaMoneyActivity.this.finish();
                } else {
                    ToastUtils.getMineToast(successBean.getErrMsg());
                }
            }

            @Override
            public void onFail() {
                ToastUtils.getMineToast("提交失败");
            }
        });
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
            approveId = data.getStringExtra("id");
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
        } else if (requestCode == 2) {
            MoneyMessageListBean listBean = (MoneyMessageListBean) data.getSerializableExtra(IntentUtils.getInstance().BEAN_TYPE);
            contractNo = listBean.getContractNO();
            orderId = listBean.getID() + "";
            id = listBean.getID() + "";
            tvApplyList.setText(contractNo);

            if (!isTrick) {
                tvApplyName.setText(listBean.getLoanLinkMan());
                tvRegisterAccount.setText(listBean.getLoanTel());
                tvRzDw.setText(listBean.getAccountName());

                tvXyEd.setText(MoneyUtils.getInstance().getFormPrice(listBean.getCreditLimit() + ""));
                tvExtraMoney.setText(MoneyUtils.getInstance().getFormPrice(listBean.getBalance() + ""));
                tvOpenAccount.setText(listBean.getAccountNumber());
                tvGetAccount.setText(listBean.getOpeningBank());
                tvMoneyUse.setText(listBean.getUsageOfLoan());
                tvLoanLv.setText(listBean.getInterestRate());
                etMoneyTime.setText(listBean.getCreateTime());
                borrow_money = listBean.getLoanAmount() + "";

                etBorrowPrice.setText(MoneyUtils.getInstance().getFormPrice(listBean.getLoanAmount() + ""));


                etBorrowUse.setText(listBean.getLoanPeriod() + "");
            } else {
                etXyName.setText(listBean.getLinkMan());
                etXyLink.setText(listBean.getLinkMan());
                etXyAddress.setText(listBean.getAddress());
                etXyPhone.setText(listBean.getTel());
                etBorrowPrice.setText(listBean.getRzMoey());
                etBorrowUse.setText("一年");
            }
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
    }

    public boolean isHave(String id) {
        for (CheckImgBean checkBean : checkImgBeenList) {
            if (id.equals(checkBean.getId())) {
                return true;
            }
        }
        return false;
    }


}
