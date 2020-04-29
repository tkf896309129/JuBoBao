package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.ImageAddFunction;
import com.example.huoshangkou.jubowan.adapter.ApproveAgreeAdapter;
import com.example.huoshangkou.jubowan.adapter.CheckImgAdapter;
import com.example.huoshangkou.jubowan.adapter.ImageAddAdapter;
import com.example.huoshangkou.jubowan.adapter.LanImageShowAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ApproveBankListBean;
import com.example.huoshangkou.jubowan.bean.ApproveListBean;
import com.example.huoshangkou.jubowan.bean.CheckImgBean;
import com.example.huoshangkou.jubowan.bean.KeyValueBean;
import com.example.huoshangkou.jubowan.bean.SerMap;
import com.example.huoshangkou.jubowan.bean.SyYuanListBean;
import com.example.huoshangkou.jubowan.bean.YwNewDetailBean;
import com.example.huoshangkou.jubowan.bean.YwResultBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PhotoConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnTimeCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.inter.StringPositionCallBack;
import com.example.huoshangkou.jubowan.photo.PhotoCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.DialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.KeyBoardUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.TimeDialogUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollGridView;
import com.example.huoshangkou.jubowan.view.ScrollListView;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：YwMoneyNewActivity
 * 描述：
 * 创建时间：2019-02-27  09:53
 */

public class YwMoneyNewActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ll_other_message)
    LinearLayout llOtherMessage;
    @Bind(R.id.ll_other_yy)
    LinearLayout llOtherYy;
    @Bind(R.id.tv_dk_customer)
    TextView tvDkCustomer;
    @Bind(R.id.tv_mys)
    TextView tvMys;
    @Bind(R.id.rg_zh_type)
    RadioGroup rgZhType;
    @Bind(R.id.et_dkr)
    EditText etDkr;
    @Bind(R.id.tv_dk_type)
    TextView tvDkType;
    @Bind(R.id.et_dk_money)
    EditText etDkMoney;
    @Bind(R.id.tv_dk_xz)
    TextView tvDkXz;
    @Bind(R.id.tv_dk_xz_pt)
    TextView tvDkXzPt;
    @Bind(R.id.et_remark)
    EditText etRemark;
    @Bind(R.id.tv_sk_zh)
    TextView tvSkZh;
    @Bind(R.id.tv_sk_type)
    TextView tvSkType;
    @Bind(R.id.tv_pay_type)
    TextView tvPayType;
    @Bind(R.id.tv_ck_zh)
    TextView tvCkZh;
    @Bind(R.id.et_dk_yt)
    EditText etDkYt;
    @Bind(R.id.et_yf_money)
    EditText etYfMoney;
    @Bind(R.id.rg_is_dc)
    RadioGroup rgIsDc;
    @Bind(R.id.rg_customer_type)
    RadioGroup rgCustomerType;
    @Bind(R.id.cb_invoice)
    CheckBox cbInvoice;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.et_yl)
    EditText etYl;
    @Bind(R.id.tv_yy_ry)
    TextView tvYyRy;
    @Bind(R.id.et_other_remark)
    EditText etOtherRemark;
    @Bind(R.id.iv_apply_camera)
    ImageView ivApplyCamera;
    @Bind(R.id.tv_commit)
    TextView tvCommit;
    @Bind(R.id.tv_kf_member)
    TextView tvKfMember;
    @Bind(R.id.ll_operator_gone)
    LinearLayout llOperatorGone;
    @Bind(R.id.ll_apply_agree)
    LinearLayout llAgree;
    @Bind(R.id.ll_check_apply)
    LinearLayout llCheckApply;
    @Bind(R.id.lv_check_result)
    ScrollListView lvCheckResult;
    @Bind(R.id.grid_view_apply)
    ScrollGridView gridApplyImg;
    @Bind(R.id.tv_gys_xz)
    TextView tvGysXz;
    @Bind(R.id.tv_sk_company)
    TextView tvSkCompany;
    @Bind(R.id.ll_choose_kf)
    LinearLayout llChooseKf;
    @Bind(R.id.ll_operator_visible)
    LinearLayout llOperatorVisible;
    @Bind(R.id.recyc_check)
    RecyclerView recyclerView;
    @Bind(R.id.recyc_check_man)
    RecyclerView recyclerViewMan;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.rl_mys)
    RelativeLayout rlMys;
    @Bind(R.id.ll_dkr)
    RelativeLayout llDkr;
    @Bind(R.id.rl_back_time)
    RelativeLayout rlBackTime;
    @Bind(R.id.tv_back_time)
    TextView tvBackTime;
    @Bind(R.id.rb_mys)
    RadioButton cbMys;
    @Bind(R.id.rb_small)
    RadioButton cbSmall;
    @Bind(R.id.rb_gz)
    RadioButton cbGonZhan;
    @Bind(R.id.rb_sz)
    RadioButton cbSiZhan;

    private String keyWord = "";
    //销售id  默认为0
    private String borrowId = "";
    //审批类型
    private String approveTypeId = "1201";
    //打款客户
    private String dkCustomer = "";
    //客户类型
    private String customerType = "";
    //所属贸易商
    private String trades = "";
    //账户类型
    private String accountType = "";
    //打款人
    private String payMan = "";
    //打款方式
    private String payType = "";
    //打款金额
    private String payMoney = "";
    //贷款性质客户
    private String dkXz = "";
    //备注
    private String remark = "";
    //收款账户
    private String skZh = "";
    //收款方式
    private String receiveType = "";
    //出账账户
    private String czZh = "";
    //贷款性质平台
    private String dkXzPt = "";
    //贷款用途
    private String loanUse = "";
    //应付金额
    private String hasPayMoney = "";
    //是否打出
    private String isDc = "";
    //发票
    private String invoince = "";
    //日期
    private String time = "";
    //盈利
    private String ylMoney = "";
    //营运人员
    private String operateMember = "";
    //客服人员
    private String kfMember = "";
    //客户备注
    private String customerRemark = "";
    //出款方式
    private String outMoneyType = "";
    //贸易商性质
    private String gysXz = "";
    //收款公司
    private String receiveCompany = "";
    //回款时间
    private String backTime = "";
    //加工单位
    ArrayList<SyYuanListBean> yuanList = new ArrayList<>();
    private int imgNum = 20;
    //审批人id
    private String approveId = "";
    //抄送人id
    private String csId = "";
    //图片
    List<String> imgGetList = new ArrayList<>();
    List<String> imgList = new ArrayList<>();
    //图片适配器
    ImageAddAdapter imageAddAdapter;
    List<CheckImgBean> checkImgBeenList = new ArrayList<>();
    List<CheckImgBean> checkList = new ArrayList<>();
    //审批人
    CheckImgAdapter checkAdapter;
    //抄送人
    CheckImgAdapter checkImgAdapter;
    //收汇款方式
    List<String> listTradeType = new ArrayList<>();
    //业务用款_客户_货款性质
    List<String> listCustomer = new ArrayList<>();
    //业务用款_平台_货款性质
    List<String> listPinTai = new ArrayList<>();
    //getKeyData("业务用款_贸易商性质");
    List<String> listGysXz = new ArrayList<>();
    //是否是销售
    private boolean isSale = false;
    //审批人
    ArrayList<ApproveListBean> approvalMsgList = new ArrayList<>();
    //是否是审批
    private boolean isCheck = false;
    //是否是私账
    private boolean isPrivateMoney = false;
    //是否是贸易商
    private boolean isMys = false;
    //是否选择客服
    private boolean isChooseKf = false;
    //是否是选择运营人员
    private boolean isOperator = false;
    //是否是编辑
    private boolean isEdit = false;
    //是否是垫付
    private boolean isDianFu = false;

    @Override
    public int initLayout() {
        return R.layout.activity_yw_money_new;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().approveCheckList);
        borrowId = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        String value = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        String name = getIntent().getStringExtra(IntentUtils.getInstance().STR);
        tvTitle.setText(StringUtils.getNoNullStr(name) + "业务用款");
//        value = "operator";
        if (StringUtils.isNoEmpty(value)) {
            isCheck = true;
            tvCommit.setVisibility(View.GONE);
            //运营人员提交
            if (value.equals("operator") || value.equals("operatorEdit")) {
                isOperator = true;
                llAgree.setVisibility(View.GONE);
                llOperatorGone.setVisibility(View.VISIBLE);
                llChooseKf.setVisibility(View.GONE);
                llOperatorVisible.setVisibility(View.VISIBLE);
                tvRight.setText("提交");
                if (value.equals("operatorEdit")) {
                    isEdit = true;
                }
            }
            if (value.equals("sale")) {
                isSale = true;
                llAgree.setVisibility(View.GONE);
                llOperatorGone.setVisibility(View.GONE);
                llChooseKf.setVisibility(View.VISIBLE);
                llOperatorVisible.setVisibility(View.GONE);
                tvRight.setText("提交");
                llOtherMessage.setVisibility(View.GONE);
                llOtherYy.setVisibility(View.GONE);
            }
            if (value.equals("commit")) {
                isOperator = true;
                llAgree.setVisibility(View.GONE);
//                llCheckApply.setVisibility(View.GONE);
                tvCommit.setVisibility(View.VISIBLE);
                llChooseKf.setVisibility(View.GONE);
                llOperatorVisible.setVisibility(View.VISIBLE);
                llOperatorGone.setVisibility(View.GONE);
            }
        } else {
            llAgree.setVisibility(View.GONE);
            llCheckApply.setVisibility(View.GONE);
        }
        if (StringUtils.isNoEmpty(borrowId)) {
            if (borrowId.equals("sale")) {
                borrowId = "0";
                isSale = true;
            }
        } else {
            borrowId = "0";
        }
        if (isSale && !isCheck) {
            llOtherMessage.setVisibility(View.GONE);
            llOtherYy.setVisibility(View.GONE);
        }
        //图片显示封装
        imageAddAdapter = new ImageAddAdapter(this, imgList, "");
        ImageAddFunction.addImageFun(imageAddAdapter, imgList, this, gridApplyImg);

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
        //获取详情
        getYwDataDetail();
        getKeyData("业务用款_汇收款方式", listTradeType);
        getKeyData("业务用款_客户_货款性质", listCustomer);
        getKeyData("业务用款_平台_货款性质", listPinTai);
        getKeyData("业务用款_供应商性质", listGysXz);
        cbMys.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cbSmall.setChecked(false);
                    customerType = "贸易商";
                    isMys = true;
                    rlMys.setVisibility(View.VISIBLE);
                } else {
                    trades = "";
                    isMys = false;
                    rlMys.setVisibility(View.GONE);
                }
            }
        });
        cbSmall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    customerType = "中小型";
                    isMys = false;
                    trades = "";
                    tvMys.setText(trades + " ");
                    rlMys.setVisibility(View.GONE);
                }
            }
        });
        cbGonZhan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    accountType = "公账";
                    isPrivateMoney = false;
                    llDkr.setVisibility(View.GONE);
                }
            }
        });
        cbSiZhan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    accountType = "私账";
                    isPrivateMoney = true;
                    llDkr.setVisibility(View.VISIBLE);
                } else {
                    isPrivateMoney = false;
                    llDkr.setVisibility(View.GONE);
                    etDkr.setText("");
                }
            }
        });

        rgIsDc.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_no_dc:
                        isDc = "不打出";
                        break;
                    case R.id.rb_dc:
                        isDc = "打出";
                        break;
                }
            }
        });

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

    private StaggeredGridLayoutManager getLayoutManager() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL);
        return layoutManager;
    }

    @OnClick({R.id.ll_back, R.id.tv_commit, R.id.ll_choose_kf, R.id.ll_pt_dk_xz, R.id.ll_dk_xz,
            R.id.ll_dk_type, R.id.ll_sk_type, R.id.ll_dkkh, R.id.rl_mys, R.id.ll_sk_zh, R.id.ll_ck_zh,
            R.id.ll_yy_ry, R.id.iv_apply_camera, R.id.tv_apply_agree,
            R.id.tv_apply_disagree, R.id.ll_pay_type, R.id.ll_sk_company, R.id.ll_gys_xz, R.id.rl_time,
            R.id.rl_apply_check, R.id.tv_csr, R.id.tv_right, R.id.rl_back_time})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_right:
                if (!StringUtils.isNoEmpty(dkCustomer) && !isOperator) {
                    ToastUtils.getMineToast("请选择打款客户");
                    return;
                }
                if (!StringUtils.isNoEmpty(customerType) && !isOperator) {
                    ToastUtils.getMineToast("请选择客户类型");
                    return;
                }
                if (!StringUtils.isNoEmpty(trades) && isMys) {
                    ToastUtils.getMineToast("请选择所属贸易商");
                    return;
                }
                if (!StringUtils.isNoEmpty(accountType) && !isOperator) {
                    ToastUtils.getMineToast("请选择账户类型");
                    return;
                }
                payMan = etDkr.getText().toString().trim();
                if (!StringUtils.isNoEmpty(payMan) && isPrivateMoney) {
                    ToastUtils.getMineToast("请输入打款人");
                    return;
                }
                if (!StringUtils.isNoEmpty(payType) && !isOperator) {
                    ToastUtils.getMineToast("请选择打款方式");
                    return;
                }
                payMoney = etDkMoney.getText().toString().trim();
                if (!StringUtils.isNoEmpty(payMoney) && !isOperator) {
                    ToastUtils.getMineToast("请输入打款金额");
                    return;
                }
                if (!StringUtils.isNoEmpty(dkXz) && !isOperator) {
                    ToastUtils.getMineToast("请选择客户贷款性质");
                    return;
                }
                if (!StringUtils.isNoEmpty(backTime) && isDianFu) {
                    ToastUtils.getMineToast("请选择回款时间");
                    return;
                }
                if (!StringUtils.isNoEmpty(skZh) && !isOperator) {
                    ToastUtils.getMineToast("请选择收款账户");
                    return;
                }
                if (!StringUtils.isNoEmpty(receiveType) && !isOperator) {
                    ToastUtils.getMineToast("请选择收款方式");
                    return;
                }
                if (!StringUtils.isNoEmpty(kfMember) && !isOperator) {
                    ToastUtils.getMineToast("请选推送所属客服");
                    return;
                }
                loanUse = etDkYt.getText().toString().trim();
                hasPayMoney = etYfMoney.getText().toString().trim();
                customerRemark = etRemark.getText().toString().trim();
                ylMoney = etYl.getText().toString().trim();

                remark = etRemark.getText().toString().trim();
                if (checkList.size() == 1) {
                    approveId = checkList.get(0).getId();
                } else {
                    approveId = "";
                }
                if (!StringUtils.isNoEmpty(approveId) && isOperator) {
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

                CopyIosDialogUtils.getInstance().getIosDialog(this, "是否提交", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        PhotoUtils.getInstance().mutilLocalImageUp(imgList, YwMoneyNewActivity.this, new StringCallBack() {
                            @Override
                            public void onSuccess(String str) {
                                String pics = PhotoUtils.getInstance().getImageStr(imgGetList) + "," + str;
                                putCommit(pics, "no");
                            }

                            @Override
                            public void onFail() {
                                ToastUtils.getMineToast("提交失败，请重试");
                            }
                        });
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
                break;
            //选择审批人
            case R.id.rl_apply_check:
                Intent intentCheck = new Intent(this, ChoosCheckManActivity.class);
                intentCheck.putExtra(IntentUtils.getInstance().TITLE, "审批人");
                startActivityForResult(intentCheck, 4);
                break;
            case R.id.tv_csr:
                if (checkImgBeenList.size() >= 3) {
                    ToastUtils.getMineToast("最多选择3个抄送人");
                    return;
                }
                Intent intent2 = new Intent(this, ChoosCheckManActivity.class);
                intent2.putExtra(IntentUtils.getInstance().TITLE, "抄送人");
                startActivityForResult(intent2, 5);
                break;
            case R.id.rl_time:
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
            case R.id.ll_choose_kf:
                isChooseKf = true;
                IntentUtils.getInstance().toYwActivity(getContext(), GroupManActivity.class, "", "抄送客服", "kfMember", "", "CustomerServiceStaff");
//                Intent intentKf = new Intent(getContext(), KfMemberActivity.class);
//                startActivityForResult(intentKf, 3);
                break;
            //打款客户
            case R.id.ll_dkkh:
                Intent intent = new Intent(getContext(), DkCustomerActivity.class);
                intent.putExtra(IntentUtils.getInstance().TYPE, IntentUtils.getInstance().DK_CUNSTOMER);
                intent.putExtra(IntentUtils.getInstance().VALUE, "打款客户");
                startActivityForResult(intent, 1);
                break;
            case R.id.tv_commit:
                if (!StringUtils.isNoEmpty(dkCustomer) && isSale) {
                    ToastUtils.getMineToast("请选择打款客户");
                    return;
                }
                if (!StringUtils.isNoEmpty(customerType) && isSale) {
                    ToastUtils.getMineToast("请选择客户类型");
                    return;
                }
                if (!StringUtils.isNoEmpty(trades) && isMys) {
                    ToastUtils.getMineToast("请选择所属贸易商");
                    return;
                }
                if (!StringUtils.isNoEmpty(accountType) && isSale) {
                    ToastUtils.getMineToast("请选择账户类型");
                    return;
                }
                payMan = etDkr.getText().toString().trim();
                if (!StringUtils.isNoEmpty(payMan) && isPrivateMoney) {
                    ToastUtils.getMineToast("请输入打款人");
                    return;
                }
                if (!StringUtils.isNoEmpty(payType) && isSale) {
                    ToastUtils.getMineToast("请选择打款方式");
                    return;
                }
                payMoney = etDkMoney.getText().toString().trim();
                if (!StringUtils.isNoEmpty(payMoney) && isSale) {
                    ToastUtils.getMineToast("请输入打款金额");
                    return;
                }
                if (!StringUtils.isNoEmpty(dkXz) && isSale) {
                    ToastUtils.getMineToast("请选择客户贷款性质");
                    return;
                }
                if (!StringUtils.isNoEmpty(backTime) && isDianFu) {
                    ToastUtils.getMineToast("请选择回款时间");
                    return;
                }
                if (!StringUtils.isNoEmpty(skZh)) {
                    ToastUtils.getMineToast("请选择收款账户");
                    return;
                }
                if (!StringUtils.isNoEmpty(receiveType)) {
                    ToastUtils.getMineToast("请选择收款方式");
                    return;
                }
//                if (!StringUtils.isNoEmpty(czZh) && !isSale) {
//                    ToastUtils.getMineToast("请选择出账账户");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(dkXzPt) && !isSale) {
//                    ToastUtils.getMineToast("请选择平台贷款性质");
//                    return;
//                }
                loanUse = etDkYt.getText().toString().trim();
//                if (!StringUtils.isNoEmpty(loanUse) && !isSale) {
//                    ToastUtils.getMineToast("请输入货款用途");
//                    return;
//                }
                hasPayMoney = etYfMoney.getText().toString().trim();
//                if (!StringUtils.isNoEmpty(hasPayMoney) && !isSale) {
//                    ToastUtils.getMineToast("请输入应付金额");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(isDc) && !isSale) {
//                    ToastUtils.getMineToast("请选择是否打出");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(gysXz) && !isSale) {
//                    ToastUtils.getMineToast("请选择贸易商性质");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(receiveCompany) && !isSale) {
//                    ToastUtils.getMineToast("请选择收款公司");
//                    return;
//                }
                customerRemark = etRemark.getText().toString().trim();
//                if (!StringUtils.isNoEmpty(time) && !isSale) {
//                    ToastUtils.getMineToast("请选择日期");
//                    return;
//                }
                ylMoney = etYl.getText().toString().trim();
//                if (!StringUtils.isNoEmpty(ylMoney) && !isSale) {
//                    ToastUtils.getMineToast("请输入盈利金额");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(operateMember) && !isSale) {
//                    ToastUtils.getMineToast("请选择运营人员");
//                    return;
//                }
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

//                if (!StringUtils.isNoEmpty(kfMember)) {
//                    ToastUtils.getMineToast("请选择推送客服");
//                    return;
//                }
                remark = etRemark.getText().toString().trim();
                CopyIosDialogUtils.getInstance().getIosDialog(this, "是否提交", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        PhotoUtils.getInstance().mutilLocalImageUp(imgList, YwMoneyNewActivity.this, new StringCallBack() {
                            @Override
                            public void onSuccess(String str) {
                                putCommit(str, "check");
                            }

                            @Override
                            public void onFail() {
                                ToastUtils.getMineToast("提交失败，请重试");
                            }
                        });
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
                break;
            //运营人员
            case R.id.ll_yy_ry:
                isChooseKf = false;
                IntentUtils.getInstance().toYwActivity(getContext(), GroupManActivity.class, "", "运营中心人员", "kfMember", "", "Salesman");
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
            //出款账户
            case R.id.ll_ck_zh:
//                KeyBoardUtils.closeKeybord(etRemark, getContext());
//                DialogUtils.getInstance().getBaseDialog(getContext(), bankList, new StringPositionCallBack() {
//                    @Override
//                    public void onStringPosition(String str, int position) {
//                        czZh = str;
//                        tvCkZh.setText(czZh + " ");
//                    }
//                });
                Intent intentCkAccount = new Intent(getContext(), DkCustomerActivity.class);
                intentCkAccount.putExtra(IntentUtils.getInstance().TYPE, IntentUtils.getInstance().CK_ACCOUNT);
                intentCkAccount.putExtra(IntentUtils.getInstance().VALUE, "出款账户");
                startActivityForResult(intentCkAccount, 8);
                break;
            //收款账户
            case R.id.ll_sk_zh:
//                Intent intentBank = new Intent(this, ApproveBankActivity.class);
//                startActivityForResult(intentBank, 6);
                Intent intentSkAccount = new Intent(getContext(), DkCustomerActivity.class);
                intentSkAccount.putExtra(IntentUtils.getInstance().TYPE, IntentUtils.getInstance().SK_ACCOUNT);
                intentSkAccount.putExtra(IntentUtils.getInstance().VALUE, "收款账户");
                startActivityForResult(intentSkAccount, 7);
                break;
            case R.id.rl_mys:
//                if (isSmall) {
//                    ToastUtils.getMineToast("中小型客户没有贸易商");
//                    return;
//                }
//                KeyBoardUtils.closeKeybord(etRemark, getContext());
//                DialogUtils.getInstance().getBaseDialog(getContext(), listTrade, new StringPositionCallBack() {
//                    @Override
//                    public void onStringPosition(String str, int position) {
//                        trades = str;
//                        tvMys.setText(trades + " ");
//                    }
//                });
                Intent intentMys = new Intent(getContext(), DkCustomerActivity.class);
                intentMys.putExtra(IntentUtils.getInstance().TYPE, IntentUtils.getInstance().MYS_TRADES);
                intentMys.putExtra(IntentUtils.getInstance().VALUE, "贸易商");
                startActivityForResult(intentMys, 10);
                break;
            case R.id.ll_dk_type:
                KeyBoardUtils.closeKeybord(etRemark, getContext());
                DialogUtils.getInstance().getBaseDialog(getContext(), listTradeType, new StringPositionCallBack() {
                    @Override
                    public void onStringPosition(String str, int position) {
                        payType = str;
                        tvDkType.setText(payType + " ");
                    }
                });
                break;
            case R.id.ll_sk_type:
                KeyBoardUtils.closeKeybord(etRemark, getContext());
                DialogUtils.getInstance().getBaseDialog(getContext(), listTradeType, new StringPositionCallBack() {
                    @Override
                    public void onStringPosition(String str, int position) {
                        receiveType = str;
                        tvSkType.setText(receiveType + " ");
                    }
                });
                break;
            //客户贷款性质
            case R.id.ll_dk_xz:
                KeyBoardUtils.closeKeybord(etRemark, getContext());
                DialogUtils.getInstance().getBaseDialog(getContext(), listCustomer, new StringPositionCallBack() {
                    @Override
                    public void onStringPosition(String str, int position) {
                        dkXz = str;
                        tvDkXz.setText(dkXz + " ");
                        if (str.equals("垫付")) {
                            rlBackTime.setVisibility(View.VISIBLE);
                            isDianFu = true;
                        } else {
                            rlBackTime.setVisibility(View.GONE);
                            isDianFu = false;
                        }
                    }
                });
                break;
            //平台贷款性质
            case R.id.ll_pt_dk_xz:
                KeyBoardUtils.closeKeybord(etRemark, getContext());
                DialogUtils.getInstance().getBaseDialog(getContext(), listPinTai, new StringPositionCallBack() {
                    @Override
                    public void onStringPosition(String str, int position) {
                        dkXzPt = str;
                        tvDkXzPt.setText(dkXzPt + " ");
                    }
                });
                break;
            //出款方式
            case R.id.ll_pay_type:
                KeyBoardUtils.closeKeybord(etRemark, getContext());
                DialogUtils.getInstance().getBaseDialog(getContext(), listTradeType, new StringPositionCallBack() {
                    @Override
                    public void onStringPosition(String str, int position) {
                        outMoneyType = str;
                        tvPayType.setText(outMoneyType + " ");
                    }
                });
                break;
            //贸易商性质
            case R.id.ll_gys_xz:
                KeyBoardUtils.closeKeybord(etRemark, getContext());
                DialogUtils.getInstance().getBaseDialog(getContext(), listGysXz, new StringPositionCallBack() {
                    @Override
                    public void onStringPosition(String str, int position) {
                        gysXz = str;
                        tvGysXz.setText(gysXz + " ");
                    }
                });
                break;
            //收款公司
            case R.id.ll_sk_company:
                Intent intentSkCompany = new Intent(getContext(), DkCustomerActivity.class);
                intentSkCompany.putExtra(IntentUtils.getInstance().TYPE, IntentUtils.getInstance().SK_COMPANY);
                intentSkCompany.putExtra(IntentUtils.getInstance().VALUE, "收款公司");
                startActivityForResult(intentSkCompany, 11);
                break;
            //同意审批
            case R.id.tv_apply_agree:
//                if (!StringUtils.isNoEmpty(dkCustomer)) {
//                    ToastUtils.getMineToast("请选择打款客户");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(customerType)) {
//                    ToastUtils.getMineToast("请选择客户类型");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(trades) && !isSmall) {
//                    ToastUtils.getMineToast("请选择所属贸易商");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(accountType)) {
//                    ToastUtils.getMineToast("请选择账户类型");
//                    return;
//                }
                payMan = etDkr.getText().toString().trim();
//                if (!StringUtils.isNoEmpty(payMan) && isPrivateMoney) {
//                    ToastUtils.getMineToast("请输入打款人");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(payType)) {
//                    ToastUtils.getMineToast("请选择打款方式");
//                    return;
//                }
                payMoney = etDkMoney.getText().toString().trim();
//                if (!StringUtils.isNoEmpty(payMoney)) {
//                    ToastUtils.getMineToast("请输入打款金额");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(dkXz)) {
//                    ToastUtils.getMineToast("请选择客户贷款性质");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(skZh)) {
//                    ToastUtils.getMineToast("请选择收款账户");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(receiveType)) {
//                    ToastUtils.getMineToast("请选择收款方式");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(czZh) && !isSale) {
//                    ToastUtils.getMineToast("请选择出账账户");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(dkXzPt) && !isSale) {
//                    ToastUtils.getMineToast("请选择平台贷款性质");
//                    return;
//                }
                loanUse = etDkYt.getText().toString().trim();
//                if (!StringUtils.isNoEmpty(loanUse) && !isSale) {
//                    ToastUtils.getMineToast("请输入货款用途");
//                    return;
//                }
                hasPayMoney = etYfMoney.getText().toString().trim();
//                if (!StringUtils.isNoEmpty(hasPayMoney) && !isSale) {
//                    ToastUtils.getMineToast("请输入应付金额");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(isDc) && !isSale) {
//                    ToastUtils.getMineToast("请选择是否打出");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(outMoneyType)) {
//                    ToastUtils.getMineToast("请选择平台出款方式");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(gysXz) && !isSale) {
//                    ToastUtils.getMineToast("请选择贸易商性质");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(receiveCompany) && !isSale) {
//                    ToastUtils.getMineToast("请选择收款公司");
//                    return;
//                }
                customerRemark = etRemark.getText().toString().trim();

//                if (!StringUtils.isNoEmpty(time)) {
//                    ToastUtils.getMineToast("请选择日期");
//                    return;
//                }
                ylMoney = etYl.getText().toString().trim();
//                if (!StringUtils.isNoEmpty(ylMoney)) {
//                    ToastUtils.getMineToast("请输入盈利金额");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(operateMember)) {
//                    ToastUtils.getMineToast("请选择运营人员");
//                    return;
//                }
                remark = etOtherRemark.getText().toString().trim();
                PhotoUtils.getInstance().mutilLocalImageUp(imgList, this, new StringCallBack() {
                    @Override
                    public void onSuccess(String str) {
                        Map<String, String> map = putMap(str);
                        //让hashmap实现可序列化则要定义一个实现可序列化的类。
                        SerMap serMap = new SerMap();
                        serMap.setMap(map);
                        Intent intentAgree = new Intent(YwMoneyNewActivity.this, ApproveAgreeActivity.class);
                        intentAgree.putExtra(IntentUtils.getInstance().MAP, serMap);
                        intentAgree.putExtra(IntentUtils.getInstance().TYPE, IntentUtils.getInstance().AGREE_APPROVE);
                        intentAgree.putParcelableArrayListExtra(IntentUtils.getInstance().LIST, approvalMsgList);
                        startActivity(intentAgree);
                    }

                    @Override
                    public void onFail() {
                        ToastUtils.getMineToast("提交失败，请重试");
                    }
                });
                break;
            //不同意审批
            case R.id.tv_apply_disagree:
//                if (!StringUtils.isNoEmpty(dkCustomer) && isSale) {
//                    ToastUtils.getMineToast("请选择打款客户");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(customerType) && isSale) {
//                    ToastUtils.getMineToast("请选择客户类型");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(trades) && !isSmall) {
//                    ToastUtils.getMineToast("请选择所属贸易商");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(accountType) && isSale) {
//                    ToastUtils.getMineToast("请选择账户类型");
//                    return;
//                }
                payMan = etDkr.getText().toString().trim();
//                if (!StringUtils.isNoEmpty(payMan) && isPrivateMoney) {
//                    ToastUtils.getMineToast("请输入打款人");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(payType)  && isSale) {
//                    ToastUtils.getMineToast("请选择打款方式");
//                    return;
//                }
                payMoney = etDkMoney.getText().toString().trim();
//                if (!StringUtils.isNoEmpty(payMoney)  && isSale) {
//                    ToastUtils.getMineToast("请输入打款金额");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(dkXz)  && isSale) {
//                    ToastUtils.getMineToast("请选择客户贷款性质");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(skZh) && isSale) {
//                    ToastUtils.getMineToast("请选择收款账户");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(receiveType) && isSale) {
//                    ToastUtils.getMineToast("请选择收款方式");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(czZh) && !isSale) {
//                    ToastUtils.getMineToast("请选择出账账户");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(dkXzPt) && !isSale) {
//                    ToastUtils.getMineToast("请选择平台贷款性质");
//                    return;
//                }
                loanUse = etDkYt.getText().toString().trim();
//                if (!StringUtils.isNoEmpty(loanUse) && !isSale) {
//                    ToastUtils.getMineToast("请输入货款用途");
//                    return;
//                }
                hasPayMoney = etYfMoney.getText().toString().trim();
//                if (!StringUtils.isNoEmpty(hasPayMoney) && !isSale) {
//                    ToastUtils.getMineToast("请输入应付金额");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(isDc) && !isSale) {
//                    ToastUtils.getMineToast("请选择是否打出");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(outMoneyType)) {
//                    ToastUtils.getMineToast("请选择平台出款方式");
//                    return;
//                }
                remark = etRemark.getText().toString().trim();
//                if (!StringUtils.isNoEmpty(gysXz) && !isSale) {
//                    ToastUtils.getMineToast("请选择贸易商性质");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(receiveCompany) && !isSale) {
//                    ToastUtils.getMineToast("请选择收款公司");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(time)) {
//                    ToastUtils.getMineToast("请选择日期");
//                    return;
//                }
                ylMoney = etYl.getText().toString().trim();
//                if (!StringUtils.isNoEmpty(ylMoney)) {
//                    ToastUtils.getMineToast("请输入盈利金额");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(operateMember)) {
//                    ToastUtils.getMineToast("请选择运营人员");
//                    return;
                PhotoUtils.getInstance().mutilLocalImageUp(imgList, this, new StringCallBack() {
                    @Override
                    public void onSuccess(String str) {
                        Map<String, String> disMap = putMap(str);
                        //让hashmap实现可序列化则要定义一个实现可序列化的类。
                        SerMap serDisMap = new SerMap();
                        serDisMap.setMap(disMap);
                        Intent intentDisAgree = new Intent(YwMoneyNewActivity.this, ApproveAgreeActivity.class);
                        intentDisAgree.putExtra(IntentUtils.getInstance().MAP, serDisMap);
                        intentDisAgree.putExtra(IntentUtils.getInstance().TYPE, IntentUtils.getInstance().DISAGREE_APPROVE);
                        intentDisAgree.putParcelableArrayListExtra(IntentUtils.getInstance().LIST, approvalMsgList);
                        startActivity(intentDisAgree);
                    }

                    @Override
                    public void onFail() {
                        ToastUtils.getMineToast("提交失败，请重试");
                    }
                });
                break;
            case R.id.rl_back_time:
                TimeDialogUtils.getInstance().getTime(this, new OnTimeCallBack() {
                    @Override
                    public void getYearTime(String year) {
                        backTime = year;
                        tvBackTime.setText(year + " ");
                    }

                    @Override
                    public void getMinuteTime(String minute) {

                    }
                });
                break;
        }
    }

    public Map<String, String> putMap(String pic) {
        Map<String, String> map = new HashMap<>();
        map.put("BorrowingId", borrowId);
        map.put("PayingCustomers", dkCustomer);
        map.put("ApprovalTypeID", approveTypeId);
        map.put("CustomerType", customerType);
        map.put("ParentTrader", trades);
        map.put("CustomerAccountType", accountType);
        map.put("Payer", payMan);
        map.put("BackMoneyTime", backTime);
        map.put("CustmerPaymentAmount", payMoney);
        map.put("CustomerPaymentMethod", payType);
        map.put("CustomerGoodsMoneyNature", dkXz);
        map.put("CustomerRemark", customerRemark);
        map.put("PlatformInMoneyAccount", skZh);
        map.put("PlatformInMoneyMethod", receiveType);
        map.put("userID", PersonConstant.getInstance().getUserId());
        if (!isSale) {
            map.put("PlatformOutMoneyAccount", czZh);
            map.put("PlatformGoodsMoneyNature", dkXzPt);
            map.put("GoodsMoneyPurpose", loanUse);
            map.put("PayableAmount", hasPayMoney);
            map.put("PlatformOutMoneyMethod", outMoneyType);
            map.put("IsOutMoney", isDc);
            map.put("SupplierNature", gysXz);
            map.put("InMoneySupplier", receiveCompany);
            map.put("IsOpenInvoice", invoince);
            map.put("CreateTime", time);
            map.put("Profit", ylMoney);
            map.put("Operator", operateMember);
            map.put("Pics", pic);
        } else {
            map.put("ApprovalUserID", kfMember);
        }
        map.put("Remark", remark);
        return map;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        switch (requestCode) {
            //打款客户
            case 1:
                String dk = data.getStringExtra(IntentUtils.getInstance().TYPE);
                dkCustomer = dk;
                tvDkCustomer.setText(dkCustomer + " ");
                break;
            //运营人员
            case 2:
                String operate = data.getStringExtra(IntentUtils.getInstance().TYPE);
                tvYyRy.setText(operate);
                operateMember = operate;
                break;
            //客服
            case 3:

                //审批人
            case 4:
                if (isHave(approveId)) {
                    ToastUtils.getMineToast("审批人不能与抄送人相同");
                    approveId = "";
                    return;
                }
                checkList.clear();
                approveId = data.getStringExtra("id");
//            tvCheckMan.setText(data.getStringExtra("name"));
                String approveIdCheck = data.getStringExtra("id");
                String nameCheck = data.getStringExtra("name");
                String imgCheck = data.getStringExtra("img");
                CheckImgBean checkImgBeanCheck = new CheckImgBean();
                checkImgBeanCheck.setName(nameCheck);
                checkImgBeanCheck.setImg(imgCheck);
                checkImgBeanCheck.setId(approveIdCheck);
                checkList.add(checkImgBeanCheck);

                checkAdapter.notifyDataSetChanged();
                break;
            //抄送人
            case 5:
                csMan(data);
                break;
            case 6:
                ApproveBankListBean bankListBean = (ApproveBankListBean) data.getSerializableExtra("bean");
                skZh = bankListBean.getCompany() + "-"
                        + bankListBean.getBankName() + "-"
                        + bankListBean.getBankAccount();
                tvSkZh.setText(bankListBean.getCompany() + "-"
                        + bankListBean.getBankName() + "-"
                        + bankListBean.getBankAccount());
//                bankId = bankListBean.getID() + "";
                break;
            //出款账户
            case 8:
                czZh = data.getStringExtra(IntentUtils.getInstance().TYPE);
                tvCkZh.setText(czZh + " ");
                break;
            //收款账户
            case 7:
                skZh = data.getStringExtra(IntentUtils.getInstance().TYPE);
                tvSkZh.setText(skZh);
                break;
            //打款公司
            case 9:
                break;
            //所属贸易商
            case 10:
                trades = data.getStringExtra(IntentUtils.getInstance().TYPE);
                tvMys.setText(trades + " ");
                break;
            case 11:
                receiveCompany = data.getStringExtra(IntentUtils.getInstance().TYPE);
                tvSkCompany.setText(receiveCompany + " ");
                break;
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
        if (check.length == 4) {
            type = check[3];
        }
        LogUtils.i(id + "   " + name + "  " + img);
        Intent intent = new Intent();
        intent.putExtra("id", id);
        intent.putExtra("name", name);
        intent.putExtra("img", img);
        intent.putExtra("type", type);
        csMan(intent);
    }

    public void csMan(Intent data) {
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

    private String type = "";

    //业务字典
    public void getKeyData(String key, final List<String> list) {
        Map<String, String> map = new HashMap();
        map.put("parentFieldName", key);
        String json = JSON.toJSONString(map);
        type = key;
        OkhttpUtil.getInstance().basePostCall(this, json, UrlConstant.getInstance().YW_URL + "GetFieldsByParentName", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(type + "  " + str);
                KeyValueBean valueBean = JSON.parseObject(str, KeyValueBean.class);
                if (valueBean == null || valueBean.getD() == null) {
                    return;
                }
                List<KeyValueBean.DBean.ReObjBean> reObj = valueBean.getD().getReObj();
                for (int i = 0; i < reObj.size(); i++) {
                    list.add(reObj.get(i).getName());
                }

            }

            @Override
            public void onFail() {

            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        String kf = (String) SharedPreferencesUtils.getInstance().get(this, SharedKeyConstant.getInstance().KF_MEMBER, "");
        String[] split = kf.split(",");
        if (split != null && split.length == 2) {
            if (isChooseKf) {
                kfMember = split[1];
                tvKfMember.setText(split[0] + " ");
            } else {
                operateMember = split[0];
                tvYyRy.setText(split[0] + " ");
            }

            SharedPreferencesUtils.getInstance().put(this, SharedKeyConstant.getInstance().KF_MEMBER, "");
        }
    }

    //业务用款审批提交
    public void putCommit(String pic, String type) {
        Map<String, String> map = new HashMap<>();
        if (isEdit) {
            borrowId = "0";
        }
        map.put("BorrowingId", borrowId);
        map.put("PayingCustomers", dkCustomer);
        map.put("ApprovalTypeID", approveTypeId);
        map.put("CustomerType", customerType);
        map.put("ParentTrader", trades);
        map.put("CustomerAccountType", accountType);
        map.put("Payer", payMan);
        map.put("BackMoneyTime", backTime);
        if (!StringUtils.isNoEmpty(payMoney)) {
            payMoney = null;
        }
        map.put("CustmerPaymentAmount", payMoney);
        map.put("CustomerPaymentMethod", payType);
        map.put("CustomerGoodsMoneyNature", dkXz);
        map.put("PlatformInMoneyAccount", skZh);
        map.put("PlatformInMoneyMethod", receiveType);
        map.put("userID", PersonConstant.getInstance().getUserId());
        if (isOperator) {
            map.put(FieldConstant.getInstance().CS_ID, csId);
            map.put("ApprovalUserID", approveId);
            if (type.equals("check")) {
                map.put("ApprovalOver", "1");
            }
            map.put("Pics", pic);
        }
        if (!isSale) {
            map.put("PlatformOutMoneyAccount", czZh);
            map.put("PlatformGoodsMoneyNature", dkXzPt);
            map.put("GoodsMoneyPurpose", loanUse);
            if (!StringUtils.isNoEmpty(hasPayMoney)) {
                hasPayMoney = null;
            }
            map.put("PayableAmount", hasPayMoney);
            map.put("PlatformOutMoneyMethod", outMoneyType);
            map.put("IsOutMoney", isDc);
            map.put("SupplierNature", gysXz);
            map.put("InMoneySupplier", receiveCompany);
            map.put("IsOpenInvoice", invoince);
            map.put("CreateTime", time);
            if (!StringUtils.isNoEmpty(ylMoney)) {
                ylMoney = null;
            }
            map.put("Profit", ylMoney);
            map.put("Operator", operateMember);
        } else {
            map.put("ApprovalUserID", kfMember);
        }
        map.put("Remark", remark);
        String s = JSON.toJSONString(map);
        String json = "{\n" +
                "\t\"jsondata\":" + s + "}";
        LogUtils.e(json);
        OkhttpUtil.getInstance().basePostCall(this, json, UrlConstant.getInstance().YW_URL + "ApprovalOfBusinessFunds", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                YwResultBean resultBean = JSON.parseObject(str, YwResultBean.class);
                if (resultBean == null) {
                    return;
                }
                if (resultBean.getD().getSuccess() == 1) {
                    ToastUtils.getMineToast("提交成功");
                    ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().approveCheckList);
                } else {
                    ToastUtils.getMineToast(resultBean.getD().getErrMsg());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    //获取业务用款详情
    public void getYwDataDetail() {
        Map<String, String> map = new HashMap<>();
        map.put("BorrowId", borrowId);
        OkhttpUtil.getInstance().basePostCall(this, map, UrlConstant.getInstance().YW_URL + "ApprovalOfBusinessFundsDetail", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                //获取业务字典相关信息
                YwNewDetailBean detailBean = JSON.parseObject(str, YwNewDetailBean.class);
                if (detailBean.getD().getSuccess() == -1) {
                    return;
                }
                initUiData(detailBean.getD().getReObj());
            }

            @Override
            public void onFail() {

            }
        });
    }

    //更新UI
    public void initUiData(YwNewDetailBean.DBean.ReObjBean objBean) {
//        scrollView.post(new Runnable() {
//            @Override
//            public void run() {
//                scrollView.scrollTo(0, 0);
//            }
//        });
        approvalMsgList.addAll(objBean.getApprovalOfMsgs());
        dkCustomer = objBean.getPayingCustomers();
        tvDkCustomer.setText(objBean.getPayingCustomers());
        if (objBean.getCustomerType() != null) {
            switch (objBean.getCustomerType()) {
                case "贸易商":
                    rlMys.setVisibility(View.VISIBLE);
                    cbMys.setChecked(true);
                    break;
                case "中小型":
                    rlMys.setVisibility(View.GONE);
                    cbSmall.setChecked(true);
                    break;
            }
        }

        customerType = objBean.getCustomerType();
        trades = objBean.getParentTrader();
//        tvKhlx.setText(objBean.getCustomerType());
        tvMys.setText(objBean.getParentTrader());
        if (objBean.getCustomerAccountType() != null) {
            switch (StringUtils.getNoNullStr(objBean.getCustomerAccountType())) {
                case "公账":
                    cbGonZhan.setChecked(true);
                    break;
                case "私账":
                    cbSiZhan.setChecked(true);
                    break;
            }
        }

        accountType = objBean.getCustomerAccountType();
        etDkr.setText(objBean.getPayer());
        payMan = objBean.getPayer();
        tvDkType.setText(objBean.getCustomerPaymentMethod());
        payType = objBean.getCustomerPaymentMethod();
        etDkMoney.setText(objBean.getCustmerPaymentAmount() + "");
        payMoney = objBean.getCustmerPaymentAmount() + "";
        tvDkXz.setText(objBean.getCustomerGoodsMoneyNature());
        if (StringUtils.getNoEmptyStr(objBean.getCustomerGoodsMoneyNature()).equals("垫付")) {
            rlBackTime.setVisibility(View.VISIBLE);
            backTime = objBean.getBackMoneyTime();
            tvBackTime.setText(objBean.getBackMoneyTime());
            isDianFu = true;
        } else {
            rlBackTime.setVisibility(View.GONE);
            isDianFu = false;
        }
        dkXz = objBean.getCustomerGoodsMoneyNature();
        etRemark.setText(objBean.getRemark());
        tvSkZh.setText(objBean.getPlatformInMoneyAccount());
        skZh = objBean.getPlatformInMoneyAccount();
        tvSkType.setText(objBean.getPlatformInMoneyMethod());
        receiveType = objBean.getPlatformInMoneyMethod();
        tvCkZh.setText(objBean.getPlatformOutMoneyAccount());
        czZh = objBean.getPlatformOutMoneyAccount();
        tvDkXzPt.setText(objBean.getPlatformGoodsMoneyNature());
        dkXzPt = objBean.getPlatformGoodsMoneyNature();
        etDkYt.setText(objBean.getGoodsMoneyPurpose());
        etYfMoney.setText(objBean.getPayableAmount() + "");
        tvPayType.setText(objBean.getPlatformOutMoneyMethod());
        outMoneyType = objBean.getPlatformOutMoneyMethod();
        //0 需打出 1 不打出
        switch (StringUtils.getNoNullStr(objBean.getIsOutMoney())) {
            case "打出":
                ((RadioButton) rgIsDc.getChildAt(1)).setChecked(true);
                break;
            case "不打出":
                ((RadioButton) rgIsDc.getChildAt(0)).setChecked(true);
                break;
        }
        gysXz = objBean.getSupplierNature();
        tvGysXz.setText(objBean.getSupplierNature());
        receiveCompany = objBean.getInMoneySupplier();
        tvSkCompany.setText(objBean.getInMoneySupplier());

        //待提交发票
        if (objBean.getIsOpenInvoice() != null) {
            if (objBean.getIsOpenInvoice().equals("有发票")) {
                cbInvoice.setChecked(true);
                //未提交发票
            } else {
                cbInvoice.setChecked(false);
            }
        }
        time = objBean.getCreateTime();
        tvTime.setText(time);
        etYl.setText(objBean.getProfit() + "");
        operateMember = objBean.getOperator();
        tvYyRy.setText(objBean.getOperator());
        ApproveAgreeAdapter agreeAdapter = new ApproveAgreeAdapter(getContext(), approvalMsgList, R.layout.item_approve_result);
        lvCheckResult.setAdapter(agreeAdapter);
        lvCheckResult.setDividerHeight(0);
        lvCheckResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

        if (!StringUtils.isNoEmpty(objBean.getPics())) {
            return;
        }

        //图片
        if (isEdit) {
            String[] split = objBean.getPics().split(",");
            int num = split.length;
            for (int i = 0; i < num; i++) {
                LogUtils.i(split[i]);
                imgGetList.add(split[i]);
            }
        }

        ImageAddAdapter imageAddAdapter = new ImageAddAdapter(this, imgGetList, PhotoConstant.getInstance().IS_NO_DELETE);
        gridApplyImg.setAdapter(imageAddAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
