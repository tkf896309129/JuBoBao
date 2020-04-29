package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.ApproveAgreeAdapter;
import com.example.huoshangkou.jubowan.adapter.ImageAddAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ApproveDetailListBean;
import com.example.huoshangkou.jubowan.bean.ApproveListBean;
import com.example.huoshangkou.jubowan.bean.SerMap;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.bean.SyYuanListBean;
import com.example.huoshangkou.jubowan.bean.YwNewDetailBean;
import com.example.huoshangkou.jubowan.constant.Constant;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PhotoConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.MoneyUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
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
 * 类名：YwNewDetailActivity
 * 描述：
 * 创建时间：2019-03-07  10:36
 */

public class YwNewDetailActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_dk_customer)
    TextView tvDkCustomer;
    @Bind(R.id.tv_khlx)
    TextView tvKhlx;
    @Bind(R.id.tv_mys)
    TextView tvMys;
    @Bind(R.id.tv_zhlx)
    TextView tvZhlx;
    @Bind(R.id.tv_dkr)
    TextView tvDkr;
    @Bind(R.id.tv_dk_type)
    TextView tvDkType;
    @Bind(R.id.tv_dk_money)
    TextView tvDkMoney;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.tv_dk_xz)
    TextView tvDkXz;
    @Bind(R.id.tv_customer_remark)
    TextView tvCustomerRemark;
    @Bind(R.id.tv_sk_zh)
    TextView tvSkZh;
    @Bind(R.id.tv_sk_type)
    TextView tvSkType;
    @Bind(R.id.tv_ck_zh)
    TextView tvCkZh;
    @Bind(R.id.tv_dk_xz_pt)
    TextView tvDkXzPt;
    @Bind(R.id.tv_hkyt)
    TextView tvHkyt;
    @Bind(R.id.tv_yfje)
    TextView tvYfje;
    @Bind(R.id.tv_isdc)
    TextView tvIsdc;
    @Bind(R.id.tv_invoince)
    TextView tvInvoince;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_yl)
    TextView tvYl;
    @Bind(R.id.tv_yy_ry)
    TextView tvYyRy;
    @Bind(R.id.tv_other_remark)
    TextView tvOtherRemark;
    @Bind(R.id.tv_change_check)
    TextView tvChangeCheck;
    @Bind(R.id.lv_check_result)
    ScrollListView lvCheckResult;
    @Bind(R.id.tv_sk_company)
    TextView tvSkCompany;
    @Bind(R.id.tv_gys_xz)
    TextView tvGysXz;
    @Bind(R.id.tv_kf_member)
    TextView tvKfMember;
    @Bind(R.id.grid_apply_img)
    ScrollGridView gridApplyImg;
    @Bind(R.id.sroll_view)
    ScrollView scrollView;
    @Bind(R.id.ll_other_message)
    LinearLayout llOtherMessage;
    @Bind(R.id.ll_other)
    LinearLayout llOther;
    @Bind(R.id.ll_apply_agree)
    LinearLayout llCheckAgree;
    @Bind(R.id.ll_choose_kf)
    LinearLayout llKfMember;
    @Bind(R.id.tv_out_type)
    TextView tvOutType;
    @Bind(R.id.ll_hk_sj)
    LinearLayout llHkTime;
    @Bind(R.id.tv_hk_sj)
    TextView tvHkTime;

    private String keyWord = "";
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
    //出款方式
    private String outType = "";
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
    //其他备注
    private String otherRemark = "";
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
    private int imgNum = 20;
    //审批人id
    private String approveId = "";

    private String borrowId = "";
    private ArrayList<ApproveListBean> approvalMsgList = new ArrayList<>();
    private List<String> imgList = new ArrayList<>();
    YwNewDetailBean.DBean.ReObjBean approveDetailListBean;
    //是否是审批
    private boolean isCheck = false;
    //是否可以编辑
    private boolean isEdit = false;
    //是否是运营人员
    private boolean isOperator = false;
    private final int REQUEST_CODE = 1;
    private String name = "";
    private String peopleType = "";
    //订单id
    private String orderId = "";
    private String value = "";
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
        return R.layout.activity_yw_new_detail; 
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().approveCheckList);
        borrowId = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        value = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        name = getIntent().getStringExtra(IntentUtils.getInstance().STR);
        peopleType = getIntent().getStringExtra(IntentUtils.getInstance().TXT);
        tvTitle.setText(StringUtils.getNoNullStr(name) + "业务用款详情");
        //身份信息   //1:销售部    2：运营部   3：其它
        String ky = (String) SharedPreferencesUtils.getInstance().get(getContext(), SharedKeyConstant.getInstance().KEY_MAN_ID, "");
        String checkApprove = getIntent().getStringExtra(IntentUtils.getInstance().CHECK_APPROVE);
        //不同意 并且只有运营可以进行编辑
        if (StringUtils.isNoEmpty(checkApprove) && checkApprove.equals("0") && ky.equals("2")) {
            tvRight.setText("编辑");
        }
        if (StringUtils.isNoEmpty(value) && value.equals("check")) {
            isCheck = true;
            llCheckAgree.setVisibility(View.VISIBLE);
        }
        if (peopleType.equals("0") || (!StringUtils.isNoEmpty(peopleType) && ky.equals("1"))) {
            llOther.setVisibility(View.GONE);
            llOtherMessage.setVisibility(View.GONE);
            llKfMember.setVisibility(View.VISIBLE);
        }
        //运营人员
        if (StringUtils.isNoEmpty(peopleType) && peopleType.equals("1")) {
            isOperator = true;
        }
        getYwDataDetail();
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
            case R.id.tv_right:
                if (isOperator) {
                    IntentUtils.getInstance().toActivity(getContext(), YwMoneyNewActivity.class, borrowId, "operatorEdit", name);
                    return;
                }
                IntentUtils.getInstance().toActivity(getContext(), YwMoneyNewActivity.class, borrowId, "sale", name);
                break;
            //同意审批
            case R.id.tv_apply_agree:
                Map<String, String> map = putMap();

                //让hashmap实现可序列化则要定义一个实现可序列化的类。
                SerMap serMap = new SerMap();
                serMap.setMap(map);
                Intent intentAgree = new Intent(this, ApproveAgreeActivity.class);
                intentAgree.putExtra(IntentUtils.getInstance().MAP, serMap);
                intentAgree.putExtra(IntentUtils.getInstance().TYPE, IntentUtils.getInstance().AGREE_APPROVE);
                intentAgree.putParcelableArrayListExtra(IntentUtils.getInstance().LIST, approvalMsgList);
                startActivity(intentAgree);
                break;
            //不同意审批
            case R.id.tv_apply_disagree:
                Map<String, String> disMap = putMap();
                //让hashmap实现可序列化则要定义一个实现可序列化的类。
                SerMap serDisMap = new SerMap();
                serDisMap.setMap(disMap);
                Intent intentDisAgree = new Intent(this, ApproveAgreeActivity.class);
                intentDisAgree.putExtra(IntentUtils.getInstance().MAP, serDisMap);
                intentDisAgree.putExtra(IntentUtils.getInstance().TYPE, IntentUtils.getInstance().DISAGREE_APPROVE);
                intentDisAgree.putParcelableArrayListExtra(IntentUtils.getInstance().LIST, approvalMsgList);
                startActivity(intentDisAgree);
                break;
            //改派
            case R.id.tv_change_check:
                Intent intent = new Intent(this, ChoosCheckManActivity.class);
                intent.putExtra(IntentUtils.getInstance().TITLE, "审批人");
                startActivityForResult(intent, REQUEST_CODE);
                break;
        }
    }

    //获取业务用款详情
    public void getYwDataDetail() {
        Map<String, String> map = new HashMap<>();
        map.put("BorrowId", borrowId);
        OkhttpUtil.getInstance().basePostCall(this, map, UrlConstant.getInstance().YW_URL + "ApprovalOfBusinessFundsDetail", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                YwNewDetailBean detailBean = JSON.parseObject(str, YwNewDetailBean.class);
                if (detailBean == null || detailBean.getD() == null) {
                    return;
                }
                approveDetailListBean = detailBean.getD().getReObj();
                initUiData(detailBean.getD().getReObj());
            }

            @Override
            public void onFail() {

            }
        });
    }

    //更新UI
    public void initUiData(YwNewDetailBean.DBean.ReObjBean objBean) {
        approvalMsgList.clear();
        approvalMsgList.addAll(objBean.getApprovalOfMsgs());
        tvDkCustomer.setText(StringUtils.getNoEmptyStr(objBean.getPayingCustomers()));
        dkCustomer = objBean.getPayingCustomers();
        tvKhlx.setText(StringUtils.getNoEmptyStr(objBean.getCustomerType()));
        customerType = objBean.getCustomerType();
        tvMys.setText(StringUtils.getNoEmptyStr(objBean.getParentTrader()));
        trades = objBean.getParentTrader();
        tvZhlx.setText(StringUtils.getNoEmptyStr(objBean.getCustomerAccountType()));
        accountType = objBean.getCustomerAccountType();
        tvDkr.setText(StringUtils.getNoEmptyStr(objBean.getPayer()));
        payMan = objBean.getPayer();
        tvDkType.setText(StringUtils.getNoEmptyStr(objBean.getCustomerPaymentMethod()));
        payType = objBean.getCustomerPaymentMethod();
        tvDkMoney.setText(MoneyUtils.getInstance().getFormPrice(objBean.getCustmerPaymentAmount() + ""));
        payMoney = objBean.getCustmerPaymentAmount() + "";
        tvDkXz.setText(StringUtils.getNoEmptyStr(objBean.getCustomerGoodsMoneyNature()));
        if (StringUtils.getNoEmptyStr(objBean.getCustomerGoodsMoneyNature()).equals("垫付")) {
            llHkTime.setVisibility(View.VISIBLE);
            tvHkTime.setText(objBean.getBackMoneyTime());
        }
        dkXz = objBean.getCustomerGoodsMoneyNature();
        tvCustomerRemark.setText(StringUtils.getNoEmptyStr(objBean.getCustomerRemark()));
        remark = objBean.getCustomerRemark();
        outType = objBean.getPlatformOutMoneyMethod();
        tvOutType.setText(StringUtils.getNoEmptyStr(objBean.getPlatformOutMoneyMethod()));
        tvSkZh.setText(objBean.getPlatformInMoneyAccount());
        skZh = objBean.getPlatformInMoneyAccount();
        tvSkType.setText(StringUtils.getNoEmptyStr(objBean.getPlatformInMoneyMethod()));
        receiveType = objBean.getPlatformInMoneyMethod();
        tvCkZh.setText(StringUtils.getNoEmptyStr(objBean.getPlatformOutMoneyAccount()));
        czZh = objBean.getPlatformOutMoneyAccount();
        tvDkXzPt.setText(StringUtils.getNoEmptyStr(objBean.getPlatformGoodsMoneyNature()));
        dkXzPt = objBean.getPlatformGoodsMoneyNature();
        tvHkyt.setText(StringUtils.getNoEmptyStr(objBean.getGoodsMoneyPurpose()));
        loanUse = objBean.getGoodsMoneyPurpose();
        tvYfje.setText(MoneyUtils.getInstance().getFormPrice(objBean.getPayableAmount() + ""));
        hasPayMoney = objBean.getPayableAmount() + "";
        //0 需打出 1 不打出
        tvIsdc.setText(StringUtils.getNoEmptyStr(objBean.getIsOutMoney()));

        isDc = objBean.getIsOutMoney() + "";
        gysXz = objBean.getSupplierNature();
        receiveCompany = objBean.getInMoneySupplier();
        invoince = objBean.getIsOpenInvoice() + "";
        time = objBean.getCreateTime();
        ylMoney = objBean.getProfit() + "";
        operateMember = objBean.getOperator();
        otherRemark = objBean.getRemark();
        orderId = borrowId;

        tvHkyt.setText(StringUtils.getNoEmptyStr(objBean.getGoodsMoneyPurpose()));
        tvInvoince.setText(StringUtils.getNoEmptyStr(objBean.getIsOpenInvoice()));
        tvTime.setText(StringUtils.getNoEmptyStr(objBean.getCreateTime()));
        tvYl.setText(MoneyUtils.getInstance().getFormPrice(objBean.getProfit() + ""));
        tvYyRy.setText(StringUtils.getNoEmptyStr(objBean.getOperator()));
        tvOtherRemark.setText(StringUtils.getNoEmptyStr(objBean.getRemark()));
        tvGysXz.setText(StringUtils.getNoEmptyStr(objBean.getSupplierNature()));
        tvSkCompany.setText(StringUtils.getNoEmptyStr(objBean.getInMoneySupplier()));
        tvKfMember.setText("暂无");

        ApproveAgreeAdapter agreeAdapter = new ApproveAgreeAdapter(getContext(), approvalMsgList, R.layout.item_approve_result);
        lvCheckResult.setAdapter(agreeAdapter);
        lvCheckResult.setDividerHeight(0);
        lvCheckResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                 @Override
                                                 public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                     if (approvalMsgList.get(i).getApprovalOver() == 3 ||approvalMsgList.get(i).getApprovalOver() == 4) {
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

//        if (approveDetailListBean.getApprovalOfMsgs().size() == 1 && approveDetailListBean.getApprovalOfMsgs().get(0).getApprovalOver() == -1) {
//            tvChangeCheck.setVisibility(View.VISIBLE);//
//        }

        List<ApproveListBean> approvalOfMsgs = approveDetailListBean.getApprovalOfMsgs();
        int size = approvalOfMsgs.size();
        if (StringUtils.getNoEmptyStr(value).equals("apply")) {
            for (int i = 0; i < size; i++) {
                if (approvalOfMsgs.get(i).getApprovalOver() == -1) {
                    tvChangeCheck.setVisibility(View.VISIBLE);//
                    break;
                }
            }
        }


        if (!StringUtils.isNoEmpty(objBean.getPics())) {
            return;
        }

        //图片
        String[] split = objBean.getPics().split(",");
        int num = split.length;
        for (int i = 0; i < num; i++) {
            LogUtils.i(split[i]);
            imgList.add(split[i]);
        }

        ImageAddAdapter imageAddAdapter = new ImageAddAdapter(this, imgList, PhotoConstant.getInstance().IS_NO_DELETE);
        gridApplyImg.setAdapter(imageAddAdapter);
    }

    public Map<String, String> putMap() {
        Map<String, String> map = new HashMap<>();
        map.put("BorrowingId", borrowId);
        map.put("ApprovalTypeID", approveTypeId);
        map.put("userID", PersonConstant.getInstance().getUserId());
        return map;
    }

    //改派审批人
    public void changeCheckMan(String approveId, String toCheckId, String checkId) {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().REDISTRIBUTE + FieldConstant.getInstance().USER_ID + "=" + checkId + "&"
                + FieldConstant.getInstance().APPROVE_ID + "=" + approveId + "&"
                + FieldConstant.getInstance().APPROVAL_GAI_TYPE + "=1201" + "&"
                + FieldConstant.getInstance().NEW_APPROVE_ID + "=" + toCheckId, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    ToastUtils.getMineToast("改派成功");
                    getYwDataDetail();
                } else {
                    ToastUtils.getMineToast(successBean.getErrMsg());
                    getYwDataDetail();
                }
            }

            @Override
            public void onFail() {

            }
        });
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
}
