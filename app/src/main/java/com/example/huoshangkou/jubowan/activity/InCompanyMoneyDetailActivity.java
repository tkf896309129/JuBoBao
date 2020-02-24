package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.ApproveAgreeAdapter;
import com.example.huoshangkou.jubowan.adapter.ImageAddAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ApproveListBean;
import com.example.huoshangkou.jubowan.bean.InCompanyDetailBean;
import com.example.huoshangkou.jubowan.constant.Constant;
import com.example.huoshangkou.jubowan.constant.PhotoConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.MoneyUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
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
 * 类名：InCompanyMoneyDetailActivity
 * 描述：
 * 创建时间：2019-10-31  13:20
 */

public class InCompanyMoneyDetailActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_ck_account)
    TextView tvCkAccount;
    @Bind(R.id.tv_dk_money)
    TextView tvDkMoney;
    @Bind(R.id.tv_ck_type)
    TextView tvCkType;
    @Bind(R.id.tv_rk_account)
    TextView tvRkAccount;
    @Bind(R.id.grid_view_apply)
    ScrollGridView gridViewApply;
    @Bind(R.id.et_remark)
    TextView etRemark;
    @Bind(R.id.tv_change_check)
    TextView tvChangeCheck;
    @Bind(R.id.lv_check_result)
    ScrollListView lvApproveResult;
    @Bind(R.id.ll_apply_agree)
    LinearLayout llApply;

    private String borrowId = "";
    private String type = "";
    private String approveOver = "";
    private ArrayList<ApproveListBean> approvalMsgList = new ArrayList<>();
    //图片
    private List<String> imgList = new ArrayList<>();

    @Override
    public int initLayout() {
        return R.layout.activity_in_money_detail;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().approveCheckList);

        borrowId = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        type = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        approveOver = getIntent().getStringExtra(IntentUtils.getInstance().STR);
        String name = getIntent().getStringExtra(IntentUtils.getInstance().TXT) + "的";

        tvTitle.setText(StringUtils.getNoNullStr(name) + "内部往来账");
        if (type.equals(Constant.APPLY) || (type.equals(Constant.APPROVE) && !approveOver.equals("-1"))) {
            llApply.setVisibility(View.GONE);
        }

        getDetail();
    }

    @OnClick({R.id.ll_back, R.id.tv_apply_agree, R.id.tv_apply_disagree})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            //同意审批
            case R.id.tv_apply_agree:
                IntentUtils.getInstance().toInCompanyActivity(getContext(), ApproveAgreeActivity.class,
                        IntentUtils.getInstance().AGREE_APPROVE, borrowId, IntentUtils.getInstance().IN_COMPANY_TYPE, approvalMsgList);
                break;
            //不同意审批
            case R.id.tv_apply_disagree:
                IntentUtils.getInstance().toInCompanyActivity(getContext(), ApproveAgreeActivity.class,
                        IntentUtils.getInstance().DISAGREE_APPROVE, borrowId, IntentUtils.getInstance().IN_COMPANY_TYPE, approvalMsgList);
                break;
        }
    }


    //获取审批详情
    public void getDetail() {
        Map<String, Object> map = new HashMap<>();
        map.put("borrowId", borrowId);
        String json = JSON.toJSONString(map);
        OkhttpUtil.getInstance().basePostCall(this, json, UrlConstant.getInstance().IN_DOOR_MONEY + "GetDealingApprovalDetail", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                InCompanyDetailBean detailBean = JSON.parseObject(str, InCompanyDetailBean.class);
                InCompanyDetailBean.DBean.ReObjBean reObj = detailBean.getD().getReObj();
                tvCkAccount.setText(reObj.getPaymentUnitAccount());
                tvRkAccount.setText(reObj.getReceiptUnitAccount());
                tvCkType.setText(reObj.getPaymentMethod());
                tvDkMoney.setText(MoneyUtils.getInstance().getFormPrice(reObj.getPaymentAmount() + ""));
                etRemark.setText(reObj.getRemark());
                approvalMsgList.clear();
                approvalMsgList.addAll(reObj.getApprovalOfMsgs());
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

                if (!StringUtils.isNoEmpty(reObj.getPics())) {
                    return;
                }
                //图片
                String[] split = reObj.getPics().split(",");
                int num = split.length;
                for (int i = 0; i < num; i++) {
                    LogUtils.i(split[i]);
                    imgList.add(split[i]);
                }
                ImageAddAdapter imageAddAdapter = new ImageAddAdapter(InCompanyMoneyDetailActivity.this, imgList, PhotoConstant.getInstance().IS_NO_DELETE);
                gridViewApply.setAdapter(imageAddAdapter);

            }

            @Override
            public void onFail() {

            }
        });
    }
}
