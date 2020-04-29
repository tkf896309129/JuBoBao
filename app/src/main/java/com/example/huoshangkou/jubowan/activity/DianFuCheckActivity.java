package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.ImageAddFunction;
import com.example.huoshangkou.jubowan.adapter.BtCheckAdapter;
import com.example.huoshangkou.jubowan.adapter.CheckImgAdapter;
import com.example.huoshangkou.jubowan.adapter.ImageAddAdapter;
import com.example.huoshangkou.jubowan.adapter.LanImageShowAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.BankAccountBean;
import com.example.huoshangkou.jubowan.bean.CheckImgBean;
import com.example.huoshangkou.jubowan.bean.DianFuCheckBean;
import com.example.huoshangkou.jubowan.bean.KeyValueBean;
import com.example.huoshangkou.jubowan.bean.SuccessDBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
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
import com.example.huoshangkou.jubowan.utils.MoneyUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.TimeDialogUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollGridView;
import com.example.huoshangkou.jubowan.view.ScrollListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：DianFuCheckActivity
 * 描述：
 * 创建时间：2019-10-10  08:21
 */

public class DianFuCheckActivity extends BaseActivity {
    @Bind(R.id.lv_bt_detail)
    ScrollListView listView;
    //图片显示GridView
    @Bind(R.id.grid_view_apply)
    ScrollGridView scrollGridView;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rg_is_dc)
    RadioGroup rgIsDc;
    @Bind(R.id.tv_gys_xz)
    TextView tvGysXz;
    @Bind(R.id.tv_ck_xz)
    TextView tvCkXz;
    @Bind(R.id.tv_sk_company)
    TextView tvSkCompany;
    @Bind(R.id.tv_ck_account)
    TextView tvCkAccount;
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
    @Bind(R.id.tv_apply_check)
    TextView tvApplyCheck;
    @Bind(R.id.recyc_check_man)
    RecyclerView recycCheckMan;
    @Bind(R.id.tv_csr)
    TextView tvCsr;
    @Bind(R.id.recyc_check)
    RecyclerView recycCheck;
    @Bind(R.id.ll_yw_apply)
    LinearLayout llYwApply;
    @Bind(R.id.grid_apply_img)
    ScrollGridView gridApplyImg;
    @Bind(R.id.tv_change_check)
    TextView tvChangeCheck;
    @Bind(R.id.lv_check_result)
    ScrollListView lvCheckResult;
    @Bind(R.id.tv_invoice_name)
    TextView tvInvoiceName;
    @Bind(R.id.tv_invoice_commit)
    TextView tvInvoiceCommit;
    @Bind(R.id.ll_check_invoince)
    LinearLayout llCheckInvoince;
    @Bind(R.id.ll_check_apply)
    LinearLayout llCheckApply;
    @Bind(R.id.et_dkje)
    EditText etDkMoney;
    @Bind(R.id.ll_dk_money)
    LinearLayout llDkMoney;
    @Bind(R.id.ll_operator_show)
    LinearLayout llOperatorShow;
    @Bind(R.id.ll_choose_kf)
    LinearLayout llKfMember;
    @Bind(R.id.ll_yy_member)
    LinearLayout llYyMember;
    @Bind(R.id.tv_kf_member)
    TextView tvKfMember;


    List<String> list = new ArrayList<>();
    BtCheckAdapter btCheckAdapter;
    //图片
    List<String> imgList = new ArrayList<>();
    //图片适配器
    ImageAddAdapter imageAddAdapter;
    private int imgNum = 9;
    ArrayList<String> skCompanyList = new ArrayList<>();
    List<CheckImgBean> checkImgBeenList = new ArrayList<>();
    List<CheckImgBean> checkList = new ArrayList<>();
    //getKeyData("业务用款_贸易商性质");
    List<String> listGysXz = new ArrayList<>();
    List<String> listPlatsXz = new ArrayList<>();
    //审批人
    CheckImgAdapter checkAdapter;
    //抄送人
    CheckImgAdapter checkImgAdapter;
    private String borrowingId = "";
    private String csId = "";
    private String orderId = "";
    //是否打出
    private String isDc = "";
    //发票
    private String invoince = "0";
    //贸易商性质
    private String gysXz = "";
    private String ckXz = "";
    //时间
    private String time = "";
    //盈利
    private String ylMoney = "0";
    private String dkMoney = "0";
    private String pics = "";
    //收款公司
    private String receiveCompany = "";
    //审批人id
    private String approveId = "";
    //订单id
    private String approveOrderId = "";
    //营运人员
    private String operateMember = "";
    //出款账户
    private String czZh = "";
    //申请单位
    private String applyUnit = "";
    private String legalperson = "";
    private String loanUsage = "";
    private String loanAmount = "0";
    private String loanDate = "";
    private String backMoneyDate = "";
    private String platformInMoneyAccount = "";
    private String isOutMoney = "";
    private String inMoneySupplier = "";
    private String operator = "";
    private String remark = "";
    //是否选择客服
    private boolean isChooseKf = false;
    //是否是销售
    private boolean isSale = false;

    @Override
    public int initLayout() {
        return R.layout.activity_dian_fu_check;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("垫付款审批详情");
        borrowingId = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().approveCheckList);
        btCheckAdapter = new BtCheckAdapter(this, list, R.layout.item_bt_kf_layout);
        listView.setAdapter(btCheckAdapter);
        listView.setDividerHeight(0);

        //身份信息   //1:销售部    2：运营部   3：其它
        String ky = (String) SharedPreferencesUtils.getInstance().get(getContext(), SharedKeyConstant.getInstance().KEY_MAN_ID, "");
        //不同意 并且只有运营可以进行编辑
//        if (StringUtils.isNoEmpty(checkApprove) && checkApprove.equals("0") && ky.equals("2")) {
//            tvRight.setText("编辑");
//        }
        if (ky.equals("1")) {
            isSale = true;
            llOperatorShow.setVisibility(View.GONE);
            llYyMember.setVisibility(View.GONE);
            llYwApply.setVisibility(View.GONE);
            llKfMember.setVisibility(View.VISIBLE);
        }

        //图片显示封装
        imageAddAdapter = new ImageAddAdapter(this, imgList, "");
        ImageAddFunction.addImageFun(imageAddAdapter, imgList, this, scrollGridView);

        checkImgAdapter = new CheckImgAdapter(this, checkImgBeenList);
        recycCheck.setAdapter(checkImgAdapter);
        recycCheck.setLayoutManager(getLayoutManager());
        checkImgAdapter.setDeleteImg(new LanImageShowAdapter.deleteClick() {
            @Override
            public void deleteImgClick(int position) {
                checkImgBeenList.remove(position);
                checkImgAdapter.notifyDataSetChanged();
            }
        });

        checkAdapter = new CheckImgAdapter(this, checkList);
        recycCheckMan.setAdapter(checkAdapter);
        recycCheckMan.setLayoutManager(getLayoutManager());
        checkAdapter.setDeleteImg(new LanImageShowAdapter.deleteClick() {
            @Override
            public void deleteImgClick(int position) {
                checkList.remove(position);
                checkAdapter.notifyDataSetChanged();
            }
        });
        rgIsDc.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_no_dc:
                        isOutMoney = "不打出";
                        break;
                    case R.id.rb_dc:
                        isOutMoney = "打出";
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


        getGysBank();
        getKeyData("业务用款_贸易商性质", listGysXz);
        getKeyData("业务用款_平台_货款性质", listPlatsXz);
        getDianCheckDetail();
    }

    @OnClick({R.id.ll_back, R.id.tv_commit, R.id.ll_ck_xz, R.id.ll_choose_kf, R.id.ll_ck_account, R.id.iv_apply_camera, R.id.rl_time, R.id.ll_yy_member, R.id.rl_apply_check, R.id.tv_csr, R.id.ll_gys_xz, R.id.ll_sk_company})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
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
            //运营人员
            case R.id.ll_yy_member:
                IntentUtils.getInstance().toYwActivity(getContext(), GroupManActivity.class, "", "运营部", "kfMember", "", "Salesman");
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
            //贸易商性质
            case R.id.ll_gys_xz:
                KeyBoardUtils.closeKeybord(etOtherRemark, getContext());
                DialogUtils.getInstance().getBaseDialog(getContext(), listGysXz, new StringPositionCallBack() {
                    @Override
                    public void onStringPosition(String str, int position) {
                        gysXz = str;
                        tvGysXz.setText(gysXz + " ");
                    }
                });
                break;
            //收款公司
            //收款公司
            case R.id.ll_sk_company:
                Intent intentSkCompany = new Intent(getContext(), DkCustomerActivity.class);
                intentSkCompany.putExtra(IntentUtils.getInstance().TYPE, IntentUtils.getInstance().SK_COMPANY);
                intentSkCompany.putExtra(IntentUtils.getInstance().VALUE, "收款公司");
                startActivityForResult(intentSkCompany, 11);
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
            case R.id.ll_ck_account:
                Intent intentCkAccount = new Intent(getContext(), DkCustomerActivity.class);
                intentCkAccount.putExtra(IntentUtils.getInstance().TYPE, IntentUtils.getInstance().CK_ACCOUNT);
                intentCkAccount.putExtra(IntentUtils.getInstance().VALUE, "出款账户");
                startActivityForResult(intentCkAccount, 8);
                break;
            case R.id.tv_commit:
                if (!StringUtils.isNoEmpty(platformInMoneyAccount) && !isSale) {
                    ToastUtils.getMineToast("请选择出款账户");
                    return;
                }
                if (!StringUtils.isNoEmpty(ckXz) && !isSale) {
                    ToastUtils.getMineToast("请选择出款性质");
                    return;
                }
                if (!StringUtils.isNoEmpty(isOutMoney) && !isSale) {
                    ToastUtils.getMineToast("请选择是否打出");
                    return;
                }
                if (!StringUtils.isNoEmpty(gysXz) && !isSale) {
                    ToastUtils.getMineToast("请选择贸易商性质");
                    return;
                }
                if (!StringUtils.isNoEmpty(inMoneySupplier) && !isSale) {
                    ToastUtils.getMineToast("请选择收款公司");
                    return;
                }
//                if (!StringUtils.isNoEmpty(time) && !isSale) {
//                    ToastUtils.getMineToast("请选择日期");
//                    return;
//                }
                ylMoney = etYl.getText().toString().trim();
                if (!StringUtils.isNoEmpty(ylMoney) && !isSale) {
                    ToastUtils.getMineToast("请输入盈利金额");
                    return;
                }
                dkMoney = etDkMoney.getText().toString().trim();
                if (!StringUtils.isNoEmpty(dkMoney) && !isSale) {
                    ToastUtils.getMineToast("请输入打款金额");
                    return;
                }
                if (!StringUtils.isNoEmpty(operator) && !isSale) {
                    ToastUtils.getMineToast("请选择运营人员");
                    return;
                }
                if (!StringUtils.isNoEmpty(approveId)) {
                    if (isSale) {
                        ToastUtils.getMineToast("请选所属客服");
                        return;
                    }
                    ToastUtils.getMineToast("请选择审批人");
                    return;
                }
                remark = etOtherRemark.getText().toString().trim();
                PhotoUtils.getInstance().mutilLocalImageUp(imgList, DianFuCheckActivity.this, new StringCallBack() {
                    @Override
                    public void onSuccess(String str) {
                        pics += str;
                        checkDianDetail();
                    }

                    @Override
                    public void onFail() {
                        ToastUtils.getMineToast("上传失败，请重新上传");
                    }
                });
                break;
            case R.id.ll_choose_kf:
                isChooseKf = true;
                IntentUtils.getInstance().toYwActivity(getContext(), GroupManActivity.class, "", "抄送客服", "kfMember", "", "CustomerServiceStaff");
//                Intent intentKf = new Intent(getContext(), KfMemberActivity.class);
//                startActivityForResult(intentKf, 3);
                break;
            case R.id.ll_ck_xz:
                KeyBoardUtils.closeKeybord(etOtherRemark, getContext());
                DialogUtils.getInstance().getBaseDialog(getContext(), listPlatsXz, new StringPositionCallBack() {
                    @Override
                    public void onStringPosition(String str, int position) {
                        ckXz = str;
                        tvCkXz.setText(ckXz + " ");
                    }
                });
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        switch (requestCode) {
            //审批人
            case 4:
                approveId = data.getStringExtra("id");
                if (isHave(approveId)) {
                    ToastUtils.getMineToast("审批人不能与抄送人相同");
                    approveId = "";
                    return;
                }
                checkList.clear();
//            tvCheckMan.setText(data.getStringExtra("name"));
                String name = data.getStringExtra("name");
                String img = data.getStringExtra("img");
                CheckImgBean checkImgBean = new CheckImgBean();
                checkImgBean.setName(name);
                checkImgBean.setImg(img);
                checkImgBean.setId(approveId);
                checkList.add(checkImgBean);
                checkAdapter.notifyDataSetChanged();
                break;
            //抄送人
            case 5:
                String approveIds = data.getStringExtra("id");
                if (approveIds.equals(approveId)) {
                    ToastUtils.getMineToast("审批人不能与抄送人相同");
                    return;
                }
                if (isHave(approveIds)) {
                    ToastUtils.getMineToast("抄送人不能重复");
                    return;
                }
                String nameCs = data.getStringExtra("name");
                String imgCs = data.getStringExtra("img");
                CheckImgBean checkImgBeanCs = new CheckImgBean();
                checkImgBeanCs.setName(nameCs);
                checkImgBeanCs.setImg(imgCs);
                checkImgBeanCs.setId(approveIds);
                checkImgBeenList.add(checkImgBeanCs);
                checkImgAdapter.notifyDataSetChanged();
                break;
            case 11:
                inMoneySupplier = data.getStringExtra(IntentUtils.getInstance().TYPE);
                tvSkCompany.setText(inMoneySupplier + " ");
                break;
            //出款账户
            case 8:
                platformInMoneyAccount = data.getStringExtra(IntentUtils.getInstance().TYPE);
                tvCkAccount.setText(platformInMoneyAccount + " ");
                break;
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

    //垫付款审批
    public void checkDianDetail() {
        csId = "";
        for (CheckImgBean checkImgBean : checkImgBeenList) {
            csId += checkImgBean.getId() + ";";
        }
        if (StringUtils.isNoEmpty(csId)) {
            csId = csId.substring(0, csId.length() - 1);
        }
        Map<String, String> map = new HashMap<>();
        map.put("BorrowingId", borrowingId);
        map.put("CopyUserID", csId);
        map.put("ApprovalTypeID", "1301");
        map.put("OrderId", orderId);
        map.put("ApplyUnit", applyUnit);
        map.put("Legalperson", legalperson);
        map.put("LoanUsage", loanUsage);
        map.put("LoanAmount", StringUtils.getZeroStr(loanAmount));
        map.put("LoanDate", loanDate);
        map.put("BackMoneyDate", backMoneyDate);
        map.put("PlatformOutMoneyAccount", platformInMoneyAccount);
        map.put("PlatformOutMoneyNature", ckXz);
        map.put("IsOutMoney", isOutMoney);
        map.put("SupplierNature", gysXz);
        map.put("InMoneySupplier", inMoneySupplier);
        map.put("IsOpenInvoice", invoince);
//        map.put("CreateTime", time);
        map.put("Profit", StringUtils.getZeroStr(ylMoney));
        map.put("PayAmount", StringUtils.getZeroStr(dkMoney));
        map.put("Operator", operator);
        map.put("Remark", remark);
        map.put("UserID", PersonConstant.getInstance().getUserId());
        map.put("ApprovalUserID", approveId);
        map.put("ApprovalOver", "1");
        map.put("ApprovalRemark", "");
        map.put("ApprovalPic", "");
        map.put("Pics", pics);
        String json = "{\"padPayment\":" + JSON.toJSONString(map) + "}";

        OkhttpUtil.getInstance().basePostCall(this, json, UrlConstant.getInstance().PADPAY_MANAGE + "PadPaymentApprovalApi", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                SuccessDBean dBean = JSON.parseObject(str, SuccessDBean.class);
                if (dBean.getD().getSuccess() == 1) {
                    ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().approveCheckList);
                }
                ToastUtils.getMineToast(dBean.getD().getErrMsg());
            }

            @Override
            public void onFail() {

            }
        });
    }

    //获取垫付款审批详情
    public void getDianCheckDetail() {
        Map<String, String> map = new HashMap<>();
        map.put("borrowingId", borrowingId);
        String json = JSON.toJSONString(map);
        OkhttpUtil.getInstance().basePostCall(this, json, UrlConstant.getInstance().PADPAY_MANAGE + "PadPaymentApprovalDetail", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                DianFuCheckBean checkBean = JSON.parseObject(str, DianFuCheckBean.class);
                DianFuCheckBean.DBean.ReObjBean iousPayApproval = checkBean.getD().getReObj();
                orderId = iousPayApproval.getOrderId();
                list.add("垫资订单号、" + StringUtils.getNoEmptyStr(iousPayApproval.getOrderId()));
                applyUnit = iousPayApproval.getApplyUnit();
                list.add("申请单位、" + StringUtils.getNoEmptyStr(iousPayApproval.getApplyUnit()));
                legalperson = iousPayApproval.getLegalperson();
                list.add("公司法人、" + StringUtils.getNoEmptyStr(iousPayApproval.getLegalperson()));
                loanUsage = iousPayApproval.getLoanUsage();
                list.add("借款用途、" + StringUtils.getNoEmptyStr(iousPayApproval.getLoanUsage()));
                loanAmount = iousPayApproval.getLoanAmount() + "";
                list.add("借款金额、" + StringUtils.getNoEmptyStr(iousPayApproval.getLoanAmount() + ""));
                loanDate = iousPayApproval.getLoanDate();
                list.add("借款日期、" + StringUtils.getNoEmptyStr(iousPayApproval.getLoanDate()));
                backMoneyDate = iousPayApproval.getBackMoneyDate();
                list.add("汇款日期、" + StringUtils.getNoEmptyStr(iousPayApproval.getBackMoneyDate()));
                btCheckAdapter.notifyDataSetChanged();
                operator= iousPayApproval.getOperator();
                tvYyRy.setText(iousPayApproval.getOperator());
                pics = checkBean.getD().getReObj().getPics();
                etOtherRemark.setText(iousPayApproval.getRemark());
            }

            @Override
            public void onFail() {

            }
        });
    }

    //业务字典
    public void getKeyData(String key, final List<String> list) {
        Map<String, String> map = new HashMap();
        map.put("parentFieldName", key);
        OkhttpUtil.getInstance().basePostCall(this, map, UrlConstant.getInstance().YW_URL + "GetFieldsByParentName", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                KeyValueBean valueBean = JSON.parseObject(str, KeyValueBean.class);
                List<KeyValueBean.DBean.ReObjBean> reObj = valueBean.getD().getReObj();
                if (reObj != null) {
                    for (int i = 0; i < reObj.size(); i++) {
                        list.add(reObj.get(i).getName());
                    }
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    public void getGysBank() {
        Map<String, String> map = new HashMap<>();
        map.put("keyword", "");
        map.put("pageIndex", "1");
        map.put("pageSize", "20");
        OkhttpUtil.getInstance().basePostCall(getContext(), map, UrlConstant.getInstance().YW_URL + "GetCustomerBankAccounts", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e("获取贸易商收款账户:" + str);
                BankAccountBean accountBean = JSON.parseObject(str, BankAccountBean.class);
                List<BankAccountBean.DBean.ReListBean> reList = accountBean.getD().getReList();
                int num = reList.size();
                for (int i = 0; i < num; i++) {
                    skCompanyList.add(reList.get(i).getMsg());
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
                approveId = split[1];
                tvKfMember.setText(split[0] + " ");
            } else {
                operator = split[0];
                tvYyRy.setText(split[0] + " ");
            }
            SharedPreferencesUtils.getInstance().put(this, SharedKeyConstant.getInstance().KF_MEMBER, "");
        }
//        String kf = (String) SharedPreferencesUtils.getInstance().get(this, SharedKeyConstant.getInstance().KF_MEMBER, "");
//        String[] split = kf.split(",");
//        if (split != null && split.length == 2) {
//            operator = split[0];
//            tvYyRy.setText(split[0] + " ");
//
//            SharedPreferencesUtils.getInstance().put(this, SharedKeyConstant.getInstance().KF_MEMBER, "");
//        }
    }

    private StaggeredGridLayoutManager getLayoutManager() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL);
        return layoutManager;
    }
}
