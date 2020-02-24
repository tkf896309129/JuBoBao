package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.ApproveAgreeAdapter;
import com.example.huoshangkou.jubowan.adapter.BtCheckAdapter;
import com.example.huoshangkou.jubowan.adapter.ImageAddAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ApproveListBean;
import com.example.huoshangkou.jubowan.bean.DianFuCheckBean;
import com.example.huoshangkou.jubowan.bean.SerMap;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PhotoConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.MoneyUtils;
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
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：DianFuFinishActivity
 * 描述：
 * 创建时间：2019-10-11  13:23
 */

public class DianFuFinishActivity extends BaseActivity {
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
    @Bind(R.id.tv_ck_zh)
    TextView tvCkZh;
    @Bind(R.id.tv_ck_xz)
    TextView tvCkXz;
    @Bind(R.id.tv_dk_money)
    TextView tvDkMoney;
    @Bind(R.id.ll_dian_show)
    LinearLayout llShow;
    private String borrowingId = "";
    private String peopleType = "";
    //订单id
    private String orderId = "";
    //类型id
    private String approveTypeId = "1301";
    private String approveOrderId = "";
    private String name = "";
    List<String> list = new ArrayList<>();
    BtCheckAdapter btCheckAdapter;
    //图片
    List<String> imgList = new ArrayList<>();
    //图片适配器
    ImageAddAdapter imageAddAdapter;
    //是否需要审批
    private boolean isNeedCheck = false;
    private boolean isApply = false;
    //审批人
    ArrayList<ApproveListBean> approvalMsgList = new ArrayList<>();
    ApproveAgreeAdapter agreeAdapter;

    @Override
    public int initLayout() {
        return R.layout.activity_dian_check_finish_detail;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().approveCheckList);
        borrowingId = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        peopleType = getIntent().getStringExtra(IntentUtils.getInstance().TXT);
        String type = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        name = getIntent().getStringExtra(IntentUtils.getInstance().STR);
        tvTitle.setText(name + "的垫付款审批详情");
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
                case "checkApply":
                    isApply = true;
                    llAgree.setVisibility(View.GONE);
                    break;
            }
        }

        //身份信息   //1:销售部    2：运营部   3：其它
        String ky = (String) SharedPreferencesUtils.getInstance().get(getContext(), SharedKeyConstant.getInstance().KEY_MAN_ID, "");
        //不同意 并且只有运营可以进行编辑
//        if (StringUtils.isNoEmpty(checkApprove) && checkApprove.equals("0") && ky.equals("2")) {
//            tvRight.setText("编辑");
//        }
        if (isApply || peopleType.equals("0") || (!StringUtils.isNoEmpty(peopleType) && ky.equals("1"))) {
            llShow.setVisibility(View.GONE);
        }

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

        getDianCheckDetail();
    }

    @OnClick({R.id.ll_back, R.id.tv_apply_disagree, R.id.tv_apply_agree})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
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
                intentDisAgree.putExtra(IntentUtils.getInstance().VALUE, "padPay");
                intentDisAgree.putParcelableArrayListExtra(IntentUtils.getInstance().LIST, approvalMsgList);
                startActivity(intentDisAgree);
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
                intentAgree.putExtra(IntentUtils.getInstance().VALUE, "padPay");
                intentAgree.putParcelableArrayListExtra(IntentUtils.getInstance().LIST, approvalMsgList);
                startActivity(intentAgree);
                break;
        }
    }

    public Map<String, String> putMap() {
        Map<String, String> map = new HashMap<>();
        map.put("BorrowingId", borrowingId);
        map.put("ApprovalTypeID", approveTypeId);
        map.put("userID", PersonConstant.getInstance().getUserId());
        return map;
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
                list.add("垫资订单号、" + StringUtils.getNoEmptyStr(iousPayApproval.getOrderId()));
                list.add("申请单位、" + StringUtils.getNoEmptyStr(iousPayApproval.getApplyUnit()));
                list.add("公司法人、" + StringUtils.getNoEmptyStr(iousPayApproval.getLegalperson()));
                list.add("借款用途、" + StringUtils.getNoEmptyStr(iousPayApproval.getLoanUsage()));
                list.add("借款金额、" + StringUtils.getNoEmptyStr(iousPayApproval.getLoanAmount() + ""));
                list.add("借款日期、" + StringUtils.getNoEmptyStr(iousPayApproval.getLoanDate()));
                list.add("回款日期、" + StringUtils.getNoEmptyStr(iousPayApproval.getBackMoneyDate()));
                btCheckAdapter.notifyDataSetChanged();

                tvCkZh.setText(StringUtils.getNoEmptyStr(iousPayApproval.getPlatformOutMoneyAccount()));
                tvCkXz.setText(StringUtils.getNoEmptyStr(iousPayApproval.getPlatformOutMoneyNature()));
                tvGysXz.setText(StringUtils.getNoEmptyStr(iousPayApproval.getSupplierNature()));
                tvSkCompany.setText(StringUtils.getNoEmptyStr(iousPayApproval.getInMoneySupplier()));
                tvInvoince.setText(StringUtils.getNoEmptyStr(iousPayApproval.getIsOpenInvoice()));
//                tvTime.setText(StringUtils.getNoEmptyStr(iousPayApproval.getCreateTime()));
                tvYl.setText(MoneyUtils.getInstance().getFormPrice(iousPayApproval.getProfit() + ""));
                tvOtherRemark.setText(StringUtils.getNoEmptyStr(iousPayApproval.getRemark() + ""));
                tvIsdc.setText(StringUtils.getNoEmptyStr(iousPayApproval.getIsOutMoney()));
                tvYyRy.setText(StringUtils.getNoEmptyStr(iousPayApproval.getOperator()));
                tvDkMoney.setText(MoneyUtils.getInstance().getFormPrice(iousPayApproval.getPayAmount() + ""));
                approvalMsgList.addAll(iousPayApproval.getApprovalOfMsgs());
                agreeAdapter.notifyDataSetChanged();

                if (!StringUtils.isNoEmpty(iousPayApproval.getPics())) {
                    return;
                }
                //图片
                String[] split = iousPayApproval.getPics().split(",");
                int num = split.length;
                for (int i = 0; i < num; i++) {
                    LogUtils.i(split[i]);
                    imgList.add(split[i]);
                }
                ImageAddAdapter imageAddAdapter = new ImageAddAdapter(DianFuFinishActivity.this, imgList, PhotoConstant.getInstance().IS_NO_DELETE);
                gridApplyImg.setAdapter(imageAddAdapter);
            }

            @Override
            public void onFail() {

            }
        });
    }
}
