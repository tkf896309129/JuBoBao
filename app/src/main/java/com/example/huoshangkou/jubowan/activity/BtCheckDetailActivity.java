package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.huoshangkou.jubowan.bean.ApproveBankListBean;
import com.example.huoshangkou.jubowan.bean.ApproveDetailBean;
import com.example.huoshangkou.jubowan.bean.ApproveDetailListBean;
import com.example.huoshangkou.jubowan.bean.BankAccountBean;
import com.example.huoshangkou.jubowan.bean.CheckImgBean;
import com.example.huoshangkou.jubowan.bean.IousPayApprovalBean;
import com.example.huoshangkou.jubowan.bean.KeyValueBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnTimeCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.inter.StringPositionCallBack;
import com.example.huoshangkou.jubowan.photo.PhotoCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.DialogUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.KeyBoardUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.MoneyUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
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
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：BtCheckDetailActivity
 * 描述：
 * 创建时间：2019-02-27  13:49
 */

public class BtCheckDetailActivity extends BaseActivity {
    @Bind(R.id.lv_bt_detail)
    ScrollListView listView;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    //图片显示GridView
    @Bind(R.id.grid_view_apply)
    ScrollGridView scrollGridView;
    List<String> list = new ArrayList<>();
    BtCheckAdapter btCheckAdapter;
    @Bind(R.id.rg_is_dc)
    RadioGroup rgIsDc;
    @Bind(R.id.tv_gys_xz)
    TextView tvGysXz;
    @Bind(R.id.tv_sk_company)
    TextView tvSkCompany;
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
    @Bind(R.id.et_dk_money)
    EditText etDkMoney;
    @Bind(R.id.ll_dk_money)
    LinearLayout llDkMoney;
    //订单id
    private String orderId = "";
    //类型id
    private String approveTypeId = "";
    //贸易商性质
    private String gysXz = "";
    //收款公司
    private String receiveCompany = "";
    //审批人id
    private String approveId = "";
    //订单id
    private String approveOrderId = "";
    //营运人员
    private String operateMember = "";
    //图片
    List<String> imgList = new ArrayList<>();
    //图片适配器
    ImageAddAdapter imageAddAdapter;
    //审批人
    CheckImgAdapter checkAdapter;
    //抄送人
    CheckImgAdapter checkImgAdapter;
    //是否打出
    private String isDc = "";
    //发票
    private String invoince = "";
    //时间
    private String time = "";
    //利润
    private String profit = "";
    //盈利
    private String ylMoney = "";
    private String dkMoney = "";
    private String remark = "";
    private String pics = "";
    //是否可以修改
    private boolean isEnableChange = true;

    ArrayList<String> skCompanyList = new ArrayList<>();
    List<CheckImgBean> checkImgBeenList = new ArrayList<>();
    List<CheckImgBean> checkList = new ArrayList<>();
    //getKeyData("业务用款_贸易商性质");
    List<String> listGysXz = new ArrayList<>();

    private int imgNum = 9;
    private String picsCard = "";

    @Override
    public int initLayout() {
        return R.layout.activity_bt_check_detail;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("白条审批");
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().approveCheckList);
        orderId = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        approveTypeId = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        btCheckAdapter = new BtCheckAdapter(this, list, R.layout.item_bt_kf_layout);
        listView.setAdapter(btCheckAdapter);
        listView.setDividerHeight(0);
        getApproveDetail();

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

        getKeyData("业务用款_贸易商性质");
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

    //获取审批详情订单信息
    public void getApproveDetail() {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message),
                UrlConstant.getInstance().URL + PostConstant.getInstance().GET_APPROVE_DETAIL_AD
                        + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                        + FieldConstant.getInstance().APPROVAL_TYPE + "=" + approveTypeId + "&"
                        + FieldConstant.getInstance().APPROVE_ID + "=" + orderId, new OkhttpCallBack() {
                    @Override
                    public void onSuccess(String json) {
                        ApproveDetailBean detailBean = JSON.parseObject(json, ApproveDetailBean.class);
                        if (detailBean.getReList() == null || detailBean.getReList().size() == 0) {
                            return;
                        }
                        ApproveDetailListBean approveDetailListBean = detailBean.getReList().get(0);
                        IousPayApprovalBean iousPayApproval = approveDetailListBean.getIousPayApproval();
                        list.add("白条订单号、" + iousPayApproval.getOrderId());
                        list.add("申请单位、" + iousPayApproval.getApplyUnit());
                        list.add("公司法人、" + iousPayApproval.getLegalPerson());
                        list.add("入账单位、" + iousPayApproval.getAccountBank());
                        list.add("入款开户行、" + iousPayApproval.getBankOpenAccount());
                        list.add("开户行账号、" + iousPayApproval.getAccountNum());
                        list.add("借款用途、" + iousPayApproval.getLoanPurposes());
                        list.add("借款金额、" + MoneyUtils.getInstance().getFormPrice( iousPayApproval.getMoney()));
                        list.add("白条额度、" + MoneyUtils.getInstance().getFormPrice( iousPayApproval.getIousTotalAmount()));
                        list.add("剩余额度、" + MoneyUtils.getInstance().getFormPrice( iousPayApproval.getIousRemainingAmount()));
                        list.add("借款日期、" + iousPayApproval.getCreateTime());
                        btCheckAdapter.notifyDataSetChanged();
                        approveOrderId = approveDetailListBean.getID() + "";
                        picsCard = approveDetailListBean.getPics();

                        etYl.setText(StringUtils.getNoNullStr(iousPayApproval.getProfit()));
                        etDkMoney.setText(StringUtils.getNoNullStr(iousPayApproval.getAmountOfPayment()));

                        if(!(StringUtils.isNoEmpty(iousPayApproval.getAmountOfPayment()) && iousPayApproval.getAmountOfPayment().equals("NoDisplay"))){
                            llDkMoney.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFail() {

                    }
                });
    }

    @OnClick({R.id.ll_back, R.id.tv_commit, R.id.iv_apply_camera, R.id.rl_time, R.id.ll_yy_ry, R.id.rl_apply_check, R.id.tv_csr, R.id.ll_gys_xz, R.id.ll_sk_company})
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
            case R.id.ll_yy_ry:
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
            case R.id.tv_commit:
//                if (!StringUtils.isNoEmpty(isDc)) {
//                    ToastUtils.getMineToast("请选择是否打出");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(gysXz)) {
//                    ToastUtils.getMineToast("请选择贸易商性质");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(receiveCompany)) {
//                    ToastUtils.getMineToast("请选择收款公司");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(time)) {
//                    ToastUtils.getMineToast("请选择日期");
//                    return;
//                }
                ylMoney = etYl.getText().toString().trim();
                dkMoney = etDkMoney.getText().toString().trim();
//                if (!StringUtils.isNoEmpty(ylMoney)) {
//                    ToastUtils.getMineToast("请输入盈利金额");
//                    return;
//                }
//                if (!StringUtils.isNoEmpty(operateMember)) {
//                    ToastUtils.getMineToast("请选择运营人员");
//                    return;
//                }
                remark = etOtherRemark.getText().toString().trim();
                if (!StringUtils.isNoEmpty(approveId)) {
                    ToastUtils.getMineToast("请选择审批人");
                    return;
                }
                CopyIosDialogUtils.getInstance().getIosDialog(this, "是否提交审批", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        PhotoUtils.getInstance().mutilLocalImageUp(imgList, BtCheckDetailActivity.this, new StringCallBack() {
                            @Override
                            public void onSuccess(String str) {
                                pics = str;
                                String csId = "";
                                for (CheckImgBean checkImgBean : checkImgBeenList) {
                                    csId += checkImgBean.getId() + ";";
                                }
                                btCheckApply(pics, remark, approveOrderId, approveId, csId);
                            }

                            @Override
                            public void onFail() {
                                ToastUtils.getMineToast("上传失败，请重新上传");
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

    private String type = "";

    //业务字典
    public void getKeyData(String key) {
        Map<String, String> map = new HashMap();
        map.put("parentFieldName", key);
        type = key;
        OkhttpUtil.getInstance().basePostCall(this, map, UrlConstant.getInstance().YW_URL + "GetFieldsByParentName", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(type + "  " + str);
                KeyValueBean valueBean = JSON.parseObject(str, KeyValueBean.class);
                List<KeyValueBean.DBean.ReObjBean> reObj = valueBean.getD().getReObj();
                if (reObj != null) {
                    switch (type) {
                        case "业务用款_贸易商性质":
                            for (int i = 0; i < reObj.size(); i++) {
                                listGysXz.add(reObj.get(i).getName());
                            }
                            break;
                    }
                }

                //获取出账账户
                getGysBank();
            }

            @Override
            public void onFail() {

            }
        });
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
                receiveCompany = data.getStringExtra(IntentUtils.getInstance().TYPE);
                tvSkCompany.setText(receiveCompany + " ");
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        String kf = (String) SharedPreferencesUtils.getInstance().get(this, SharedKeyConstant.getInstance().KF_MEMBER, "");
        String[] split = kf.split(",");
        if (split != null && split.length == 2) {
            operateMember = split[0];
            tvYyRy.setText(split[0] + " ");

            SharedPreferencesUtils.getInstance().put(this, SharedKeyConstant.getInstance().KF_MEMBER, "");
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

    //白条审批申请
    public void btCheckApply(String pic, String remark, String approveId, String approveUserId, String csId) {
        if (!StringUtils.isNoEmpty(ylMoney)) {
            ylMoney = null;
        }
        if (!StringUtils.isNoEmpty(dkMoney)) {
            dkMoney = null;
        }
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(this, UrlConstant.getInstance().URL
                        + PostConstant.getInstance().BT_CHECK
                        + FieldConstant.getInstance().APPROVE_TYPE_ID + "=1011" + "&"
                        + FieldConstant.getInstance().PICS + "=" + pic + "&"
                        + FieldConstant.getInstance().REMARK_BIG + "=" + EncodeUtils.getInstance().getEncodeString(remark) + "&"
                        + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                        + FieldConstant.getInstance().APPROVE_OVER + "=1&"
                        + FieldConstant.getInstance().APPROVE_ID + "=" + approveId + "&"
                        + FieldConstant.getInstance().CS_ID + "=" + csId + "&"
                        + FieldConstant.getInstance().APPROVE_USER_ID + "=" + approveUserId + "&"
                        + FieldConstant.getInstance().IS_DC + "=" + EncodeUtils.getInstance().getEncodeString(isDc) + "&"
                        + FieldConstant.getInstance().SUPPLIER_NATURE + "=" + EncodeUtils.getInstance().getEncodeString(gysXz) + "&"
                        + FieldConstant.getInstance().COLLECT_COMPANY + "=" + EncodeUtils.getInstance().getEncodeString(receiveCompany) + "&"
                        + FieldConstant.getInstance().IS_HAVE_INVOINCE + "=" + invoince + "&"
                        + FieldConstant.getInstance().TRANSFER_DATE + "=" + time + "&"
                        + FieldConstant.getInstance().PROFIT + "=" + ylMoney + "&"
                        + FieldConstant.getInstance().AMOUNT_OF_PAYMENT + "=" + dkMoney + "&"
                        + FieldConstant.getInstance().OPERATION_PERSONEL + "=" + EncodeUtils.getInstance().getEncodeString(operateMember), new OkhttpCallBack() {
                    @Override
                    public void onSuccess(String json) {
                        LogUtils.e(json);
                        SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                        if (successBean.getSuccess() == 1) {
                            ToastUtils.getMineToast("提交成功");
                            ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().approveCheckList);
                        } else {
                            ToastUtils.getMineToast(successBean.getErrMsg());
                        }
                    }

                    @Override
                    public void onFail() {

                    }
                }
        );
    }
}
