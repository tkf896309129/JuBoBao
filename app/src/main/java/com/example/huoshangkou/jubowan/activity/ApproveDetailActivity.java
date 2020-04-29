package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.ApproveAgreeAdapter;
import com.example.huoshangkou.jubowan.adapter.ImageAddAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ApproveDetailBean;
import com.example.huoshangkou.jubowan.bean.ApproveDetailListBean;
import com.example.huoshangkou.jubowan.bean.ApproveListBean;
import com.example.huoshangkou.jubowan.bean.IousPayApprovalBean;
import com.example.huoshangkou.jubowan.bean.IousUserApprovalBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.ApproveConstant;
import com.example.huoshangkou.jubowan.constant.Constant;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PhotoConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.MoneyUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.utils.VersionUtils;
import com.example.huoshangkou.jubowan.view.ScrollGridView;
import com.example.huoshangkou.jubowan.view.ScrollListView;
import com.zero.smallvideorecord.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：ApproveDetailActivity
 * 描述：
 * 创建时间：2017-03-20  10:56
 */

public class ApproveDetailActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rl_apply_agree)
    RelativeLayout rlAgree;
    @Bind(R.id.tv_apply_type)
    TextView tvType;
    //金额
    @Bind(R.id.tv_apply_money)
    TextView tvPrice;
    //使用日期
    @Bind(R.id.tv_apply_time)
    TextView tvTime;
    //使用地点
    @Bind(R.id.tv_apply_position)
    TextView tvAddress;
    @Bind(R.id.tv_apply_intro)
    TextView tvIntroMark;
    //说明
    @Bind(R.id.tv_apply_introduce)
    TextView tvIntro;
    //审核结果
    @Bind(R.id.lv_check_result)
    ScrollListView lvApproveResult;
    //发票名字
    @Bind(R.id.tv_invoice_name)
    TextView tvInvoiceName;
    @Bind(R.id.tv_invoice_commit)
    TextView tvInvoiceCommit;
    //是否有发票
    @Bind(R.id.tv_has_invoice)
    TextView tvHasInvoice;
    @Bind(R.id.tv_type_left)
    TextView tvTypeLeft;
    @Bind(R.id.tv_money_left)
    TextView tvPriceLeft;
    @Bind(R.id.tv_time_left)
    TextView tvTimeLeft;
    @Bind(R.id.tv_position_left)
    TextView tvAreaLeft;
    @Bind(R.id.tv_money_type)
    TextView tvMoneyType;
    @Bind(R.id.tv_invoice_left)
    TextView tvInvoiceLeft;
    //类型
    @Bind(R.id.rl_apply_type)
    RelativeLayout rlType;
    @Bind(R.id.rl_money_type)
    RelativeLayout rlMoneyType;
    @Bind(R.id.grid_apply_img)
    ScrollGridView scrollGridView;
    //时间段时长
    @Bind(R.id.rl_time_span)
    RelativeLayout rlTimeSpan;
    @Bind(R.id.tv_time_span_left)
    TextView tvSpanLeft;
    @Bind(R.id.tv_time_span)
    TextView tvTimeSpan;
    @Bind(R.id.ll_check_invoince)
    LinearLayout llCheckVoince;
    @Bind(R.id.scroll_approve)
    ScrollView scrollView;
    @Bind(R.id.tv_right)
    TextView tvRight;
    //改派
    @Bind(R.id.tv_change_check)
    TextView tvChangeCheck;
    @Bind(R.id.et_borrow_name)
    TextView etBorrowName;
    @Bind(R.id.et_borrow_type)
    TextView etBorrowType;
    @Bind(R.id.et_time)
    TextView etTime;
    @Bind(R.id.et_link_man)
    TextView etLinkMan;
    @Bind(R.id.et_address)
    TextView etAddress;
    @Bind(R.id.et_e_mail)
    TextView etEMail;
    @Bind(R.id.et_borrow_price)
    TextView etBorrowPrice;
    @Bind(R.id.et_borrow_time)
    TextView etBorrowTime;
    @Bind(R.id.et_borrow_use)
    TextView etBorrowUse;
    @Bind(R.id.ll_qj)
    LinearLayout llQj;
    @Bind(R.id.ll_other)
    LinearLayout llOther;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.tv_contract_no)
    TextView tvContractNo;
    @Bind(R.id.tv_extra)
    TextView tvExtra;
    @Bind(R.id.ll_contract_no)
    LinearLayout llConctractNo;
    @Bind(R.id.ll_money_use)
    LinearLayout llMoneyUse;
    @Bind(R.id.ll_other_type)
    LinearLayout llOtherType;
    @Bind(R.id.ll_borrow)
    LinearLayout llBorrow;
    @Bind(R.id.tv_apply_name)
    TextView tvApplyName;
    @Bind(R.id.tv_register_account)
    TextView tvRegiAccount;
    @Bind(R.id.tv_rz_dw)
    TextView tvRzDw;
    @Bind(R.id.tv_xy_ed)
    TextView tvXyEd;
    @Bind(R.id.tv_extra_money)
    TextView tvExtraMoney;
    @Bind(R.id.tv_get_account)
    TextView tvGetAccount;
    @Bind(R.id.tv_open_account)
    TextView tvOpenAccount;
    @Bind(R.id.tv_money_use)
    TextView tvMoneyUse;
    @Bind(R.id.tv_loan_lv)
    TextView tvLoanLv;
    @Bind(R.id.tv_loan_money)
    TextView tvLoanMoney;
    @Bind(R.id.tv_loan_rq)
    TextView tvLoanRq;
    @Bind(R.id.tv_loan_qx)
    TextView tvLoanQx;
    @Bind(R.id.tv_apply_price)
    TextView tvApplyPrice;
    @Bind(R.id.tv_contract_left)
    TextView tvContractLeft;
    @Bind(R.id.ll_e_mail)
    LinearLayout llEMail;
    //业务用款
    @Bind(R.id.ll_ywyk)
    LinearLayout llYwYk;
    //不包含业务用款
    @Bind(R.id.ll_no_ywyk)
    LinearLayout llNoYwYk;
    @Bind(R.id.tv_rzkh)
    TextView tvRzkh;
    @Bind(R.id.tv_rkfs)
    TextView tvRkfs;
    @Bind(R.id.tv_rzje)
    TextView tvRzje;
    @Bind(R.id.tv_rzxz)
    TextView tvRzxz;
    @Bind(R.id.tv_kxyt)
    TextView tvKxyt;
    @Bind(R.id.tv_yfje)
    TextView tvYfje;
    @Bind(R.id.tv_ckzh)
    TextView tvCkzh;
    @Bind(R.id.tv_rzzh)
    TextView tvRzzh;
    @Bind(R.id.tv_zhlx)
    TextView tvZhlx;
    @Bind(R.id.tv_dkfs)
    TextView tvDkfs;
    @Bind(R.id.tv_sfdc)
    TextView tvSfdc;
    @Bind(R.id.tv_dpfx)
    TextView tvDpfx;
    @Bind(R.id.tv_yl)
    TextView tvYl;
    @Bind(R.id.tv_yyzx)
    TextView tvYyzx;
    @Bind(R.id.tv_sfyfp)
    TextView tvFp;
    @Bind(R.id.tv_apply_man_left)
    TextView tvApplyNameLeft;
    @Bind(R.id.textView4)
    TextView textView4;
    @Bind(R.id.tv_link_man_left)
    TextView tvLinkManLeft;
    @Bind(R.id.tv_link_type_left)
    TextView tvLinkTypeLeft;
    @Bind(R.id.tv_year_left)
    TextView tvYearLeft;
    @Bind(R.id.tv_phone_left)
    TextView tvPhoneLeft;
    @Bind(R.id.tv_address_left)
    TextView tvAddressLeft;
    @Bind(R.id.tv_apply_money_left)
    TextView tvApplyMoneyLeft;
    @Bind(R.id.tv_e_mail_left)
    TextView tvEMailLeft;
    @Bind(R.id.tv_sq_time_left)
    TextView tvSqTimeLeft;
    @Bind(R.id.tv_money_type_left)
    TextView tvMoneyTypeLeft;
    @Bind(R.id.tv_loan_time)
    TextView tvLoanTime;
    @Bind(R.id.ll_loan_time)
    LinearLayout llLoanTime;
    @Bind(R.id.ll_bt_ed)
    LinearLayout llBtEd;
    @Bind(R.id.ll_apply_name)
    LinearLayout llApplyMan;
    @Bind(R.id.ll_bt_ed_edit)
    LinearLayout llBtEdEdit;
    @Bind(R.id.et_bt_ed)
    EditText etBtEd;
    @Bind(R.id.ll_check_money)
    LinearLayout llCheckMoney;

    //款项用途
    private String moneyUse = "";
    //银行id
    private String bankId = "";
    //订单id
    private String orderId = "";
    //类型id
    private String approveTypeId = "";
    //是审批 还是申请
    private String approveType = "";
    //上游原片厂id
    private String syId = "";
    //上游原片厂名称
    private String syCompany = "";
    private List<String> imgList;
    ArrayList<ApproveListBean> approvalMsgList = new ArrayList<>();
    private String approveId = "";
    //判断是否是请假出差
    private boolean isHolidayWork = false;
    private final int REQUEST_CODE = 1;
    //同意审批
    private final String AGREE = "agree";
    //不同意审批
    private final String DISAGREE = "disAgree";
    //判断是审批还是申请
    private String type = "";
    //判断是否是同意
    private String isAgree = "";
    //是否是申请
    private boolean isApply = false;
    //是否是业务用款
    private boolean isYwYk = false;
    private String userId = "";
    //是否有白条额度填写
    private boolean isBtEdEdit = false;
    private String btEd = "";
    private String name = "";

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    changeCheckMan(orderId, approveId, approvalMsgList.get(num - 1).getApprovalUserID() + "");
                    break;
            }
        }
    };

    @Override
    public int initLayout() {
        return R.layout.activity_apply_details;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().approveCheckList);
        imgList = new ArrayList<>();
        orderId = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        approveType = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        approveTypeId = getIntent().getStringExtra(IntentUtils.getInstance().STR);
        type = getIntent().getStringExtra(IntentUtils.getInstance().CHECK_APPROVE);
        isAgree = getIntent().getStringExtra(IntentUtils.getInstance().ISAGREE);
        String isYw = getIntent().getStringExtra(IntentUtils.getInstance().IS_YW);
        name = StringUtils.getNoNullStr(getIntent().getStringExtra(IntentUtils.getInstance().APPROVE_NAME) + "的");

        //业务用款
        if (StringUtils.isNoEmpty(isYw)) {
            llYwYk.setVisibility(View.VISIBLE);
            llNoYwYk.setVisibility(View.GONE);
            isYwYk = true;
        }

        //是否是审批
        if (type.equals(Constant.APPROVE) && StringUtils.isNoEmpty(isAgree)) {
            //是否需要审核
            if (isAgree.equals(ApproveConstant.getInstance().UN_CHECK + "")) {
                rlAgree.setVisibility(View.VISIBLE);
            } else if (isAgree.equals(ApproveConstant.getInstance().CHECK + "")) {
                rlAgree.setVisibility(View.GONE);
                etBtEd.setFocusable(false);
            }
        } else {
            etBtEd.setFocusable(false);
        }

        switch (approveTypeId) {
            case 1 + "":
                name += "报销";
                break;
            case 2 + "":
                name += "普通用款";
                break;
            case 3 + "":
                name += "请假";
                break;
            case 4 + "":
                name += "出差";
                break;
            case 1006 + "":
                name += "承兑";
                break;
            case 1009 + "":
                name += "齐家借款";
                break;
            case 1007 + "":
                name += "信用额度";
                break;
            case 1008 + "":
                name += "垫付款";
                break;
            case 1100 + "":
                name += "其他";
                break;
            case 1101 + "":
                name += "业务用款";
                break;
            //白条额度审批
            case 1010 + "":
                name += "白条额度";
                break;
            //白条支付审批
            case 1011 + "":
                name += "白条审批";
                break;
            case 1201 + "":
                name += "业务用款";
                break;
        }

        //申请
        if (approveType.equals(ApproveConstant.getInstance().APPLY)) {
            tvTitle.setText(StringUtils.getNoNullStr(name) + "详情");
            getApplyDetail();
            isApply = true;
            //审批
        } else if (approveType.equals(ApproveConstant.getInstance().APPROVE)) {
            tvTitle.setText(StringUtils.getNoNullStr(name) + "详情");
            getApproveDetail();
            isApply = false;
        }

        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.scrollTo(0, 0);
            }
        });
    }

    @OnClick({R.id.ll_back, R.id.tv_apply_agree, R.id.tv_apply_disagree, R.id.tv_right, R.id.tv_change_check})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            //同意审批    
            case R.id.tv_apply_agree:
                String ids = "";
                String csId = "";
                for (ApproveListBean listBean : approvalMsgList) {
                    //抄送人
                    if (listBean.getApprovalOver() == 3) {
                        csId += listBean.getApprovalUserID() + ",";
                    } else {
                        ids += listBean.getApprovalUserID() + ",";
                    }
                }
                if (approveDetailListBean != null && approveDetailListBean.getApprovalTypeID() == 2) {
                    isChange(AGREE, orderId, ids, csId);
                    return;
                }
                btEd = etBtEd.getText().toString().trim();
                IntentUtils.getInstance().toLoanActivity(getContext(), ApproveAgreeActivity.class,
                        IntentUtils.getInstance().AGREE_APPROVE, orderId, approveTypeId, syCompany, syId, moneyUse,
                        bankId, ids, csId, userId, btEd, approvalMsgList);
                break;
            //不同意审批
            case R.id.tv_apply_disagree:
                String ids2 = "";
                String csId2 = "";
                for (ApproveListBean listBean : approvalMsgList) {
                    //抄送人
                    if (listBean.getApprovalOver() == 3) {
                        csId2 += listBean.getApprovalUserID() + ",";
                    } else {
                        ids2 += listBean.getApprovalUserID() + ",";
                    }
                }
                if (approveDetailListBean != null && approveDetailListBean.getApprovalTypeID() == 2) {
                    isChange(DISAGREE, orderId, ids2, csId2);
                    return;
                }
                btEd = etBtEd.getText().toString().trim();
                IntentUtils.getInstance().toLoanActivity(getContext(), ApproveAgreeActivity.class,
                        IntentUtils.getInstance().DISAGREE_APPROVE, orderId, approveTypeId, syCompany, syId,
                        moneyUse, bankId, ids2, csId2, userId, btEd, approvalMsgList);
                break;
            //更改审批人
            case R.id.tv_right:
                if (isYwYk) {
                    IntentUtils.getInstance().toActivity(getContext(), YwMoneyActivity.class, approveDetailListBean);
                } else {
                    IntentUtils.getInstance().toActivity(getContext(), EditApproveActivity.class, approveDetailListBean);
                }
                break;
            //改派
            case R.id.tv_change_check:
                Intent intent = new Intent(this, ChoosCheckManActivity.class);
                intent.putExtra(IntentUtils.getInstance().TITLE, "审批人");
                startActivityForResult(intent, REQUEST_CODE);
                break;
        }
    }

    //获取申请详情订单信息
    public void getApplyDetail() {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message),
                UrlConstant.getInstance().URL + PostConstant.getInstance().GET_APPLY_DETAIL
                        + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                        + FieldConstant.getInstance().APPROVAL_TYPE + "=" + approveTypeId + "&"
                        + FieldConstant.getInstance().APPROVE_ID + "=" + orderId + "&"
                        + FieldConstant.getInstance().VERSION_NO + "=" + VersionUtils.getInstance().getLocalVersionNo(), new OkhttpCallBack() {
                    @Override
                    public void onSuccess(String json) {
                        ApproveDetailBean detailBean = JSON.parseObject(json, ApproveDetailBean.class);
                        initDetailData(detailBean);
                    }

                    @Override
                    public void onFail() {

                    }
                });
    }

    //获取审批详情订单信息
    public void getApproveDetail() {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message),
                UrlConstant.getInstance().URL + PostConstant.getInstance().GET_APPROVE_DETAIL_AD
                        + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                        + FieldConstant.getInstance().APPROVAL_TYPE + "=" + approveTypeId + "&"
                        + FieldConstant.getInstance().APPROVE_ID + "=" + orderId + "&"
                        + FieldConstant.getInstance().VERSION_NO + "=470", new OkhttpCallBack() {
                    @Override
                    public void onSuccess(String json) {
                        ApproveDetailBean detailBean = JSON.parseObject(json, ApproveDetailBean.class);
                        initDetailData(detailBean);
                    }

                    @Override
                    public void onFail() {

                    }
                });
    }

    ApproveDetailListBean approveDetailListBean;

    //赋值
    public void initDetailData(ApproveDetailBean detailBean) {
        if (detailBean.getReList() == null || detailBean.getReList().size() == 0) {
            return;
        }
        approveDetailListBean = detailBean.getReList().get(0);
        if (approveDetailListBean.getIousUserApproval() != null && StringUtils.isNoEmpty(approveDetailListBean.getIousUserApproval().getIsEditAmount()) && approveDetailListBean.getIousUserApproval().getIsEditAmount().equals("1")) {
            isBtEdEdit = true;
        }
        userId = approveDetailListBean.getUserID() + "";
        moneyUse = approveDetailListBean.getFundWay();
        bankId = approveDetailListBean.getSpBankID();
        tvIntro.setText(StringUtils.getNoEmptyStr(approveDetailListBean.getRemark()));
        tvRzkh.setText(approveDetailListBean.getRuZhangCompany());
        tvRkfs.setText(approveDetailListBean.getRuKuanWay());
        tvRzje.setText(approveDetailListBean.getRuZhangPrice());
        tvYfje.setText(approveDetailListBean.getTypePrice() + "元");
        tvRzxz.setText(approveDetailListBean.getRuZhangXingZhi());
        tvKxyt.setText(approveDetailListBean.getKuanXiangWay());
        tvCkzh.setText(approveDetailListBean.getRemitAccount());
        tvRzzh.setText(approveDetailListBean.getProceedsAccount());
        tvZhlx.setText(approveDetailListBean.getAccountType());
        tvDkfs.setText(approveDetailListBean.getFundWay());
        tvRzDw.setText(approveDetailListBean.getRuZhangCompany());
        tvDpfx.setText(approveDetailListBean.getRemitDircetion());
        tvYl.setText(approveDetailListBean.getProfit());
        tvYyzx.setText(approveDetailListBean.getYYZX());
        if (StringUtils.isNoEmpty(approveDetailListBean.getIsDaChu())) {
            if (approveDetailListBean.getIsDaChu().equals("0")) {
                tvSfdc.setText("需打出");
            } else {
                tvSfdc.setText("不打出");
            }
        }

        //不同意
        if (isApply && approvalMsgList.size() > 0 && !isAgee()) {
            tvRight.setText("编辑");
        }
        if (approveDetailListBean.getApprovalMsgList().size() == 1 && approveDetailListBean.getApprovalMsgList().get(0).getApprovalOver() == -1) {
            tvChangeCheck.setVisibility(View.VISIBLE);//
        }
        //出差
        if (approveDetailListBean.getApprovalTypeID() == 4) {
            rlType.setVisibility(View.GONE);
            tvPriceLeft.setText("开始日期");
            tvTimeLeft.setText("时间段");
            tvAreaLeft.setText("结束时间");
            tvInvoiceLeft.setText("时间段");
            rlTimeSpan.setVisibility(View.VISIBLE);
            tvTimeLeft.setText("时间段");
            tvSpanLeft.setText("出差时长");
            isHolidayWork = true;
            llQj.setVisibility(View.GONE);
            llOther.setVisibility(View.VISIBLE);
            llCheckVoince.setVisibility(View.GONE);
            //请假  
        } else if (approveDetailListBean.getApprovalTypeID() == 3) {
            tvTypeLeft.setText("请假类型");
            tvPriceLeft.setText("开始日期");
            tvTimeLeft.setText("时间段");
            tvAreaLeft.setText("结束时间");
            tvInvoiceLeft.setText("时间段");
            rlTimeSpan.setVisibility(View.VISIBLE);
            tvTimeLeft.setText("时间段");
            tvSpanLeft.setText("请假时长");
            isHolidayWork = true;
            llCheckVoince.setVisibility(View.GONE);
            llQj.setVisibility(View.GONE);
            llOther.setVisibility(View.VISIBLE);
            //报销
        } else if (approveDetailListBean.getApprovalTypeID() == 1) {
            tvTypeLeft.setText("报销类型");
            tvPriceLeft.setText("报销金额(元)");
            tvTimeLeft.setText("使用日期");
            tvAreaLeft.setText("使用地点");
            tvInvoiceLeft.setText("发票");
            isHolidayWork = false;
            llQj.setVisibility(View.GONE);
            llOther.setVisibility(View.VISIBLE);
            //不同意
            if (isApply && approveDetailListBean.getApprovalMsgList().size() > 0 && approveDetailListBean.getApprovalMsgList().get(0) != null && approveDetailListBean.getApprovalMsgList().get(0).getApprovalOver() == 0) {
                tvRight.setText("编辑");
            }
        } else if (approveDetailListBean.getApprovalTypeID() == 1100) {
            tvTypeLeft.setText("审批类型");
            tvPriceLeft.setText("审批金额(元)");
            tvTimeLeft.setText("使用日期");
            tvAreaLeft.setText("使用地点");
            tvInvoiceLeft.setText("发票");
            isHolidayWork = false;
            llQj.setVisibility(View.GONE);
            llOther.setVisibility(View.VISIBLE);
            if (approveDetailListBean.getTypeName().equals("设备更换")) {
                llCheckMoney.setVisibility(View.GONE);
            }
        }//用款
        else if (approveDetailListBean.getApprovalTypeID() == 2) {
            tvTypeLeft.setText("用款类型");
            tvPriceLeft.setText("用款金额(元)");
            tvTimeLeft.setText("使用日期");
            tvAreaLeft.setText("使用地点");
            tvInvoiceLeft.setText("发票");
            tvMoneyType.setText(approveDetailListBean.getFundWay());
            isHolidayWork = false;
            llQj.setVisibility(View.GONE);
            llOther.setVisibility(View.VISIBLE);
            rlMoneyType.setVisibility(View.VISIBLE);
            if (approveDetailListBean.getApprovalState() == 0 && type.equals(Constant.APPLY)) {
                tvChangeCheck.setVisibility(View.VISIBLE);//
            }
            //不同意
            if (isApply && approveDetailListBean.getApprovalMsgList().size() > 0 && approveDetailListBean.getApprovalMsgList().get(0).getApprovalOver() == 0) {
                tvRight.setText("编辑");
            }
            //承兑
        } else if (approveDetailListBean.getApprovalTypeID() == 1006) {
            rlType.setVisibility(View.GONE);
            tvPriceLeft.setText("承兑份数");
            tvTimeLeft.setText("使用日期");
            tvAreaLeft.setText("使用地点");
            tvInvoiceLeft.setText("发票");
            isHolidayWork = false;
            llQj.setVisibility(View.GONE);
            llOther.setVisibility(View.VISIBLE);
            //if (approveDetailListBean.getApprovalTypeID() == 1007)
            //信用额度
        } else if (approveDetailListBean.getApprovalTypeID() == 1007) {
            tvTitle.setText(StringUtils.getNoNullStr(name) + "详情");
            tvContractLeft.setText("信用申请合同号");
            llQj.setVisibility(View.VISIBLE);
            llOther.setVisibility(View.GONE);
            llConctractNo.setVisibility(View.VISIBLE);
            tvExtra.setText("信用额度");
            llCheckVoince.setVisibility(View.GONE);
            etBorrowName.setText(approveDetailListBean.getBorrowers());
            etTime.setText(approveDetailListBean.getOperatingTime() + "年");
            etLinkMan.setText(approveDetailListBean.getContact());
            etAddress.setText(approveDetailListBean.getAddress());
            llEMail.setVisibility(View.GONE);
            if (StringUtils.isNoEmpty(approveDetailListBean.getEmail())) {
                etEMail.setText(approveDetailListBean.getEmail());
            } else {
                etEMail.setText("未填写邮箱");
            }
            etBorrowPrice.setText(MoneyUtils.getInstance().getFormPrice(approveDetailListBean.getLoan() + "") + "元");
            etBorrowTime.setText("一年");
            etBorrowUse.setText(approveDetailListBean.getLoanPurPose());
            tvPhone.setText(approveDetailListBean.getTEL());
            etBorrowType.setText(approveDetailListBean.getTypeName());
            tvContractNo.setText(approveDetailListBean.getContractNO());
            tvApplyPrice.setText(MoneyUtils.getInstance().getFormPrice(approveDetailListBean.getLoan() + ""));
            tvIntroMark.setVisibility(View.GONE);
            tvIntro.setVisibility(View.GONE);
            //白条额度审批
        } else if (approveDetailListBean.getApprovalTypeID() == 1010) {
            tvTitle.setText(StringUtils.getNoNullStr(name) + "详情");
            tvApplyNameLeft.setText("申请单位");
            tvLinkManLeft.setText("公司法人");
            tvLinkTypeLeft.setText("法人身份证号");
            tvYearLeft.setText("生产地址");
            tvPhoneLeft.setText("生产规模(年收入)");
            tvAddressLeft.setText("负债情况");
            llApplyMan.setVisibility(View.GONE);
            llBtEd.setVisibility(View.GONE);
            llConctractNo.setVisibility(View.GONE);
            llBtEdEdit.setVisibility(View.VISIBLE);
            etBtEd.setText(approveDetailListBean.getIousUserApproval().getIousAmount());
            IousUserApprovalBean iousPayApproval = approveDetailListBean.getIousUserApproval();
            if (iousPayApproval != null) {
                etBorrowName.setText(StringUtils.getNoEmptyStr(iousPayApproval.getApplyUnit()));
                etLinkMan.setText(StringUtils.getNoEmptyStr(iousPayApproval.getLegalPerson()));
                etBorrowType.setText(StringUtils.getNoEmptyStr(iousPayApproval.getIdCard()));
                etTime.setText(StringUtils.getNoEmptyStr(iousPayApproval.getAddress()));
                tvPhone.setText(StringUtils.getNoEmptyStr(iousPayApproval.getScale()));
                tvApplyPrice.setText(StringUtils.getNoEmptyStr(iousPayApproval.getPersonnelSituation()));
                etAddress.setText(StringUtils.getNoEmptyStr(iousPayApproval.getPersonnelSituation()));
            }
            //白条支付审批
        } else if (approveDetailListBean.getApprovalTypeID() == 1011) {
            tvTitle.setText(StringUtils.getNoNullStr(name) + "详情");
            tvContractLeft.setText("白条订单号");
            tvApplyNameLeft.setText("申请单位");
            tvLinkManLeft.setText("公司法人");
            tvLinkTypeLeft.setText("入账单位");
            tvYearLeft.setText("入款开户行");
            tvPhoneLeft.setText("开户行账号");
            tvAddressLeft.setText("借款用途");
            tvApplyMoneyLeft.setText("借款金额");
            tvEMailLeft.setText("白条额度");
            tvSqTimeLeft.setText("剩余额度");
            llLoanTime.setVisibility(View.VISIBLE);
            llApplyMan.setVisibility(View.GONE);
            IousPayApprovalBean iousPayApproval = approveDetailListBean.getIousPayApproval();
            tvContractNo.setText(StringUtils.getNoEmptyStr(iousPayApproval.getOrderId()));
            etBorrowName.setText(StringUtils.getNoEmptyStr(iousPayApproval.getApplyUnit()));
            etLinkMan.setText(StringUtils.getNoEmptyStr(iousPayApproval.getLegalPerson()));
            etBorrowType.setText(StringUtils.getNoEmptyStr(iousPayApproval.getAccountBank()));
            etTime.setText(StringUtils.getNoEmptyStr(iousPayApproval.getBankOpenAccount()));
            tvPhone.setText(StringUtils.getNoEmptyStr(iousPayApproval.getAccountNum()));
            tvApplyPrice.setText(MoneyUtils.getInstance().getFormPrice(iousPayApproval.getMoney()));
            etAddress.setText(StringUtils.getNoEmptyStr(iousPayApproval.getLoanPurposes()));
            etEMail.setText(MoneyUtils.getInstance().getFormPrice(iousPayApproval.getIousTotalAmount()));
            etBorrowTime.setText(MoneyUtils.getInstance().getFormPrice(iousPayApproval.getIousRemainingAmount()));
            tvLoanTime.setText(StringUtils.getNoEmptyStr(iousPayApproval.getCreateTime()));
            //借款借据
        } else if (approveDetailListBean.getApprovalTypeID() == 1009) {
            tvTitle.setText(StringUtils.getNoNullStr(name) + "详情");
            tvContractLeft.setText("借款借据号");
            llOtherType.setVisibility(View.GONE);
            llBorrow.setVisibility(View.VISIBLE);
            llCheckVoince.setVisibility(View.GONE);
            tvApplyName.setText(approveDetailListBean.getBorrowers());
            tvRegiAccount.setText(approveDetailListBean.getTEL());
            tvRzDw.setText(approveDetailListBean.getRzDueBill().getAccountName());
            tvXyEd.setText(MoneyUtils.getInstance().getFormPrice(approveDetailListBean.getRzDueBill().getCreditLimit() + ""));
            tvExtraMoney.setText(MoneyUtils.getInstance().getFormPrice(approveDetailListBean.getRzDueBill().getBalance() + ""));
            tvGetAccount.setText(approveDetailListBean.getRzDueBill().getOpeningBank());
            tvContractNo.setText(approveDetailListBean.getContractNO());
            tvOpenAccount.setText(approveDetailListBean.getRzDueBill().getAccountNumber());
            tvMoneyUse.setText(approveDetailListBean.getRzDueBill().getUsageOfLoan());
            tvLoanLv.setText(approveDetailListBean.getRzDueBill().getInterestRate());
            tvLoanMoney.setText(MoneyUtils.getInstance().getFormPrice(approveDetailListBean.getRzDueBill().getLoanAmount() + ""));
            tvLoanQx.setText(approveDetailListBean.getRzDueBill().getLoanPeriod());
            tvLoanRq.setText(approveDetailListBean.getRzDueBill().getCreateTime());
            tvIntroMark.setVisibility(View.GONE);
            tvIntro.setVisibility(View.GONE);
        }
        if (approveDetailListBean.getApprovalTypeID() == 4 || approveDetailListBean.getApprovalTypeID() == 3) {
            tvType.setText(approveDetailListBean.getTypeName());
            tvPrice.setText(DateUtils.getFormData(approveDetailListBean.getStartTime()));
            tvTime.setText(approveDetailListBean.getStartslot());
            tvAddress.setText(DateUtils.getFormData(approveDetailListBean.getEndTime()));
            tvIntro.setText(StringUtils.getNoEmptyStr(approveDetailListBean.getRemark()));
            tvHasInvoice.setText(approveDetailListBean.getEndslot());
        } else {
            tvType.setText(approveDetailListBean.getTypeName());
            tvPrice.setText(MoneyUtils.getInstance().getFormPrice(approveDetailListBean.getTypePrice() + ""));
            tvTime.setText(DateUtils.getInstance().getFormData(approveDetailListBean.getTime()));
            tvAddress.setText(approveDetailListBean.getAddress());
        }
        tvInvoiceName.setText(approveDetailListBean.getUserName());
        if (isHolidayWork) {
            tvHasInvoice.setText(approveDetailListBean.getEndslot());
        } else {
            if (approveDetailListBean.getInvoice() == 1) {
                tvHasInvoice.setText("有发票");
                tvFp.setText("有发票");
            } else {
                tvHasInvoice.setText("无发票");
                tvFp.setText("无发票");
            }
        }
        //待提交发票
        if (approveDetailListBean.getInvoice() == 1) {
            tvInvoiceCommit.setText("已提交发票");
            //未提交发票
        } else {
            tvInvoiceCommit.setText("无发票");
        }

        tvTimeSpan.setText(approveDetailListBean.getTimeSpan());
        approvalMsgList.clear();
        approvalMsgList.addAll(approveDetailListBean.getApprovalMsgList());

        ApproveAgreeAdapter agreeAdapter = new ApproveAgreeAdapter(getContext(), approvalMsgList, R.layout.item_approve_result);
        lvApproveResult.setAdapter(agreeAdapter);
        lvApproveResult.setDividerHeight(0);

        lvApproveResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                   @Override
                                                   public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                       if (approvalMsgList.get(i).getApprovalOver() == 3 || approvalMsgList.get(i).getApprovalOver() == 4) {
                                                           ToastUtils.getMineToast("暂无抄送详情");
                                                           return;
                                                       }
                                                       if (approvalMsgList.get(i).getApprovalOver() == -1) {
                                                           ToastUtils.getMineToast("暂时未审批");
                                                       } else {
                                                           IntentUtils.getInstance().toActivity(getContext(), ApproveAgreeDetailActivity.class, approvalMsgList.get(i));
                                                       }
                                                   }
                                               }
        );

        if (!StringUtils.isNoEmpty(approveDetailListBean.getPics())) {
            return;
        }

        //图片
        String[] split = approveDetailListBean.getPics().split(",");
        int num = split.length;
        for (int i = 0; i < num; i++) {
            LogUtils.i(split[i]);
            imgList.add(split[i]);
        }

        ImageAddAdapter imageAddAdapter = new ImageAddAdapter(this, imgList, PhotoConstant.getInstance().IS_NO_DELETE);
        scrollGridView.setAdapter(imageAddAdapter);
    }

    int num;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }

        if (requestCode == REQUEST_CODE) {
            approveId = data.getStringExtra("id");
            String name = data.getStringExtra("name");
            num = approvalMsgList.size();
            //1月1号
            if (approvalMsgList.get(num - 1).getApprovalUserName().equals(name)) {
                CopyIosDialogUtils.getInstance().getErrorDialog(getContext(), "改派的人不能为同一个人", new CopyIosDialogUtils.ErrDialogCallBack() {
                    @Override
                    public void confirm() {

                    }
                });
                return;
            }
            CopyIosDialogUtils.getInstance().getIosDialog(getContext(), "是否将 " + approvalMsgList.get(num - 1).getApprovalUserName() + " 改派成 " + name, new CopyIosDialogUtils.iosDialogClick() {
                @Override
                public void confimBtn() {
                    handler.sendEmptyMessage(1);
                }

                @Override
                public void cancelBtn() {

                }
            });
        }
    }

    //改派审批人
    public void changeCheckMan(String approveId, String toCheckId, String checkId) {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().REDISTRIBUTE + FieldConstant.getInstance().USER_ID + "=" + checkId + "&"
                + FieldConstant.getInstance().APPROVE_ID + "=" + approveId + "&"
                + FieldConstant.getInstance().APPROVAL_GAI_TYPE + "=2" + "&"
                + FieldConstant.getInstance().NEW_APPROVE_ID + "=" + toCheckId, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    ToastUtils.getMineToast("改派成功");
                    getApplyDetail();
                } else {
                    ToastUtils.getMineToast(successBean.getErrMsg());
                    getApplyDetail();
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    //是否有不同意
    public boolean isAgee() {
        int num = approvalMsgList.size();
        for (int i = 0; i < num; i++) {
            if (approvalMsgList.get(i).getApprovalOver() == -0) {
                return false;
            }
        }
        return true;
    }

    //判断是否已经被改
    public void isChange(final String isAgree, String approveId, final String ids, final String csId) {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(getContext(), UrlConstant.getInstance().URL
                + PostConstant.getInstance().CHECK_OUT
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().APPROVE_ID + "=" + approveId, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    //同意审批
                    if (isAgree.equals(AGREE)) {
                        IntentUtils.getInstance().toLoanActivity(getContext(), ApproveAgreeActivity.class,
                                IntentUtils.getInstance().AGREE_APPROVE, orderId, approveTypeId, syCompany, syId, moneyUse, bankId, ids, csId, userId, approvalMsgList);
                        //不同意审批
                    } else if (isAgree.equals(DISAGREE)) {
                        IntentUtils.getInstance().toLoanActivity(getContext(), ApproveAgreeActivity.class,
                                IntentUtils.getInstance().DISAGREE_APPROVE, orderId, approveTypeId, syCompany, syId, moneyUse, bankId, ids, csId, userId, approvalMsgList);
                    }
                } else {
                    ToastUtils.getMineToast(successBean.getErrMsg());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }
}
