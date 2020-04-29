package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.ImageAddFunction;
import com.example.huoshangkou.jubowan.adapter.ApproveAgreeAdapter;
import com.example.huoshangkou.jubowan.adapter.BtCheckAdapter;
import com.example.huoshangkou.jubowan.adapter.ImageAddAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ApproveDetailBean;
import com.example.huoshangkou.jubowan.bean.ApproveDetailListBean;
import com.example.huoshangkou.jubowan.bean.ApproveListBean;
import com.example.huoshangkou.jubowan.bean.IousPayApprovalBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PhotoConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.MoneyUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollGridView;
import com.example.huoshangkou.jubowan.view.ScrollListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：BtDetailCheckActivity
 * 描述：不可修改详情
 * 创建时间：2019-03-11  18:52
 */

public class BtDetailCheckActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_bt_detail)
    ScrollListView lvBtDetail;
    @Bind(R.id.tv_isdc)
    TextView tvIsdc;
    @Bind(R.id.tv_gys_xz)
    TextView tvGysXz;
    @Bind(R.id.tv_sk_company)
    TextView tvSkCompany;
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
    @Bind(R.id.grid_apply_img)
    ScrollGridView gridApplyImg;
    @Bind(R.id.ll_apply_agree)
    LinearLayout llAgree;
    @Bind(R.id.lv_check_result)
    ScrollListView lvCheckResult;
    @Bind(R.id.tv_dk_money)
    TextView tvDkMoney;
    @Bind(R.id.ll_dk_money)
    LinearLayout llDkMoney;

    //订单id
    private String orderId = "";
    //类型id
    private String approveTypeId = "";
    private String approveOrderId = "";

    List<String> list = new ArrayList<>();
    BtCheckAdapter btCheckAdapter;
    //图片
    List<String> imgList = new ArrayList<>();
    //图片适配器
    ImageAddAdapter imageAddAdapter;
    //是否需要审批
    private boolean isNeedCheck = false;
    //审批人
    ArrayList<ApproveListBean> approvalMsgList = new ArrayList<>();
    ApproveAgreeAdapter agreeAdapter;

    @Override
    public int initLayout() {
        return R.layout.activity_bt_check_detail_agree;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().approveCheckList);
        orderId = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        approveTypeId = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        String type = getIntent().getStringExtra(IntentUtils.getInstance().STR);
        String approveName = getIntent().getStringExtra(IntentUtils.getInstance().APPROVE_NAME);
        tvTitle.setText(approveName + "白条审批详情");
        if (StringUtils.isNoEmpty(type)) {
            switch (type) {
                case "check":
                    isNeedCheck = false;
                    llAgree.setVisibility(View.GONE);
                    break;
                case "unCheck":
                    isNeedCheck = true;
                    llAgree.setVisibility(View.VISIBLE);
                    break;
            }
        }
        //图片显示封装
        imageAddAdapter = new ImageAddAdapter(this, imgList, "");
        ImageAddFunction.addImageFun(imageAddAdapter, imgList, this, gridApplyImg);

        btCheckAdapter = new BtCheckAdapter(this, list, R.layout.item_bt_kf_layout);
        lvBtDetail.setAdapter(btCheckAdapter);
        lvBtDetail.setDividerHeight(0);

        agreeAdapter = new ApproveAgreeAdapter(getContext(), approvalMsgList, R.layout.item_approve_result);
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
        getApproveDetail();
    }

    @OnClick({R.id.ll_back, R.id.tv_apply_agree, R.id.tv_apply_disagree})
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
                Intent intentAgree = new Intent(getContext(), ApproveAgreeActivity.class);
                intentAgree.putExtra(IntentUtils.getInstance().TYPE, IntentUtils.getInstance().AGREE_APPROVE);
                intentAgree.putExtra(IntentUtils.getInstance().VALUE, orderId);
                intentAgree.putExtra(IntentUtils.getInstance().STR, approveTypeId);
                intentAgree.putExtra(IntentUtils.getInstance().USER_ID, PersonConstant.getInstance().getUserId());
                intentAgree.putExtra(IntentUtils.getInstance().CSR, csId);
                intentAgree.putExtra(IntentUtils.getInstance().ID, ids);
                startActivity(intentAgree);
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
                Intent intentDisAgree = new Intent(getContext(), ApproveAgreeActivity.class);
                intentDisAgree.putExtra(IntentUtils.getInstance().TYPE, IntentUtils.getInstance().DISAGREE_APPROVE);
                intentDisAgree.putExtra(IntentUtils.getInstance().VALUE, orderId);
                intentDisAgree.putExtra(IntentUtils.getInstance().STR, approveTypeId);
                intentDisAgree.putExtra(IntentUtils.getInstance().USER_ID, PersonConstant.getInstance().getUserId());
                intentDisAgree.putExtra(IntentUtils.getInstance().CSR, csId2);
                intentDisAgree.putExtra(IntentUtils.getInstance().ID, ids2);
                startActivity(intentDisAgree);
                break;
        }
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
                        list.add("借款金额、" + MoneyUtils.getInstance().getFormPrice(iousPayApproval.getMoney()));
                        list.add("白条额度、" + MoneyUtils.getInstance().getFormPrice(iousPayApproval.getIousTotalAmount()));
                        list.add("剩余额度、" + MoneyUtils.getInstance().getFormPrice(iousPayApproval.getIousRemainingAmount()));
                        list.add("借款日期、" + iousPayApproval.getCreateTime());
                        btCheckAdapter.notifyDataSetChanged();
                        approveOrderId = approveDetailListBean.getID() + "";
                        approvalMsgList.addAll(approveDetailListBean.getApprovalMsgList());
                        agreeAdapter.notifyDataSetChanged();

                        if (iousPayApproval.getIsHaveInvoice().equals("0")) {
                            tvInvoince.setText("无发票");
                        } else {
                            tvInvoince.setText("有发票");
                        }
                        tvTime.setText(iousPayApproval.getTransferDate());
                        tvYl.setText(MoneyUtils.getInstance().getFormPrice(iousPayApproval.getProfit()));
                        tvYyRy.setText(iousPayApproval.getOperationPersonnel());
                        tvOtherRemark.setText(approveDetailListBean.getRemark());
                        tvGysXz.setText(iousPayApproval.getSupplierNature());
                        tvSkCompany.setText(iousPayApproval.getCollectionCompany());
                        tvDkMoney.setText(MoneyUtils.getInstance().getFormPrice(iousPayApproval.getAmountOfPayment()));

                        if (!(StringUtils.isNoEmpty(iousPayApproval.getAmountOfPayment()) && iousPayApproval.getAmountOfPayment().equals("NoDisplay"))) {
                            llDkMoney.setVisibility(View.VISIBLE);
                        }
                        //待提交发票
                        //0 需打出 1 不打出   
                        tvIsdc.setText(iousPayApproval.getNeedTransfer());
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
                        ImageAddAdapter imageAddAdapter = new ImageAddAdapter(BtDetailCheckActivity.this, imgList, PhotoConstant.getInstance().IS_NO_DELETE);
                        gridApplyImg.setAdapter(imageAddAdapter);
                    }

                    @Override
                    public void onFail() {

                    }
                });
    }
}
