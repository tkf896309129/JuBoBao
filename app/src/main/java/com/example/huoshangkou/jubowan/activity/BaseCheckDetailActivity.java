package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.BaseCheckFunction;
import com.example.huoshangkou.jubowan.adapter.ApproveAgreeAdapter;
import com.example.huoshangkou.jubowan.adapter.BaseCheckDetailAdapter;
import com.example.huoshangkou.jubowan.adapter.ImageAddAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ApproveDetailBean;
import com.example.huoshangkou.jubowan.bean.ApproveDetailListBean;
import com.example.huoshangkou.jubowan.bean.ApproveListBean;
import com.example.huoshangkou.jubowan.bean.BaseCheckBean;
import com.example.huoshangkou.jubowan.bean.BaseCheckDetailBean;
import com.example.huoshangkou.jubowan.bean.CheckImgBean;
import com.example.huoshangkou.jubowan.bean.IousPayApprovalBean;
import com.example.huoshangkou.jubowan.bean.IousUserApprovalBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
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
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.MoneyUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.utils.VersionUtils;
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
 * 类名：BaseCheckDetailActivity
 * 描述：
 * 创建时间：2019-12-06  16:31
 */

public class BaseCheckDetailActivity extends BaseActivity {
    //审核结果
    @Bind(R.id.rl_apply_agree)
    RelativeLayout rlAgree;
    @Bind(R.id.lv_base_detail)
    ScrollListView lvBaseDetail;
    //审核结果
    @Bind(R.id.lv_check_result)
    ScrollListView lvApproveResult;
    //图片
    @Bind(R.id.grid_apply_img)
    ScrollGridView scrollGridView;
    //说明
    @Bind(R.id.tv_apply_introduce)
    TextView tvIntro;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.tv_change_check)
    TextView tvChangeCheck;

    private String approveType = "";
    private String approveTypeId = "";
    private String approveId = "";
    private final int REQUEST_CODE = 1;
    private ArrayList<ApproveListBean> approvalMsgList = new ArrayList<>();
    private List<String> imgList = new ArrayList<>();
    List<BaseCheckBean> listType = new ArrayList<>();
    private BaseCheckDetailAdapter detailAdapter;
    private String name = "";
    private String approveDetails = "";
    private String peopleType = "";
    //判断是否是申请
    private boolean isApply = false;
    private String orderId = "";
    private String changeId = "";

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    changeCheckMan(orderId, changeId, approvalMsgList.get(num - 1).getApprovalUserID() + "");
                    break;
            }
        }
    };

    @Override
    public int initLayout() {
        return R.layout.activity_base_check_detail;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().approveCheckList);
        tvTitle.setText("详情");
        rlAgree.setVisibility(View.VISIBLE);
        approveType = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        approveDetails = getIntent().getStringExtra(IntentUtils.getInstance().TYPE_DETAILS);
        approveId = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        approveTypeId = getIntent().getStringExtra(IntentUtils.getInstance().STR);
        name = getIntent().getStringExtra(IntentUtils.getInstance().TXT);
        peopleType = getIntent().getStringExtra(IntentUtils.getInstance().PEOPLE_TYPE);

        detailAdapter = new BaseCheckDetailAdapter(this, listType, R.layout.item_base_check_detail);
        lvBaseDetail.setAdapter(detailAdapter);
        lvBaseDetail.setDividerHeight(0);
        getApplyDetail();
        //1审批 2知会 3申请
        switch (approveType) {
            case "1":
                if (StringUtils.isNoEmpty(approveDetails) && approveDetails.equals("3")) {
                    rlAgree.setVisibility(View.GONE);
                }
                break;
            case "2":
                rlAgree.setVisibility(View.GONE);
                break;
            case "3":
                isApply = true;
                rlAgree.setVisibility(View.GONE);
                break;
        }
    }

    //获取申请详情订单信息
    public void getApplyDetail() {
        Map<String, Object> map = new HashMap<>();
        map.put("InfoType", approveType);
        map.put("ApprovalID", approveId);
        map.put("BorrowId", approveId);
        map.put("ApprovalTypeID", approveTypeId);
        map.put("UserID", PersonConstant.getInstance().getUserId());
        map.put("PageSize", "1");
        map.put("SellUserID", "0");
        map.put("VersionNo", VersionUtils.getInstance().getLocalVersionNo());
        String json = "{\n" +
                "\"model\":" + JSON.toJSONString(map) + "}";
        OkhttpUtil.getInstance().basePostCall(this, json, UrlConstant.getInstance().POST_COMMON_CHECK_DETAIL, new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                BaseCheckDetailBean detailBean = JSON.parseObject(str, BaseCheckDetailBean.class);
                initDetailData(detailBean);
            }

            @Override
            public void onFail() {

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
//                if (approveDetailListBean != null && approveDetailListBean.getApprovalTypeID() == 2) {
//                    isChange(AGREE, orderId, ids, csId);
//                    return;
//                }
//                btEd = etBtEd.getText().toString().trim();
//                IntentUtils.getInstance().toLoanActivity(getContext(), ApproveAgreeActivity.class,
//                        IntentUtils.getInstance().AGREE_APPROVE, orderId, approveTypeId, syCompany, syId, moneyUse,
//                        bankId, ids, csId, userId, btEd, approvalMsgList);

                IntentUtils.getInstance().toApproveAgreeActivity(getContext(), ApproveAgreeActivity.class, approveTypeId, approveId, IntentUtils.getInstance().AGREE_APPROVE, csId, ids);
                break;
            //不同意审批
            case R.id.tv_apply_disagree:
                String ids2 = "";
                String csId2 = "";
                for (ApproveListBean listBean : approvalMsgList) {
                    //抄送人
                    if (listBean.getApprovalOver() == 3 ||listBean.getApprovalOver() == 4 ) {
                        csId2 += listBean.getApprovalUserID() + ",";
                    } else {
                        ids2 += listBean.getApprovalUserID() + ",";
                    }
                }
//                if (approveDetailListBean != null && approveDetailListBean.getApprovalTypeID() == 2) {
//                    isChange(DISAGREE, orderId, ids2, csId2);
//                    return;
//                }
//                btEd = etBtEd.getText().toString().trim();
                IntentUtils.getInstance().toApproveAgreeActivity(getContext(), ApproveAgreeActivity.class, approveTypeId, approveId, IntentUtils.getInstance().DISAGREE_APPROVE, csId2, ids2);
//                IntentUtils.getInstance().toLoanActivity(getContext(), ApproveAgreeActivity.class,
//                        IntentUtils.getInstance().DISAGREE_APPROVE, orderId, approveTypeId, syCompany, syId,
//                        moneyUse, bankId, ids2, csId2, userId, btEd, approvalMsgList);
                break;
            //更改审批人
            case R.id.tv_right:
//                if (isYwYk) {
//                    IntentUtils.getInstance().toActivity(getContext(), YwMoneyActivity.class, approveDetailListBean);
//                } else {
//                    IntentUtils.getInstance().toActivity(getContext(), EditApproveActivity.class, approveDetailListBean);
//                }
                IntentUtils.getInstance().toActivity(this, BaseCheckActivity.class, approveDetailListBean);
                break;
            //改派
            case R.id.tv_change_check:
                Intent intent = new Intent(this, ChoosCheckManActivity.class);
                intent.putExtra(IntentUtils.getInstance().TITLE, "审批人");
                startActivityForResult(intent, REQUEST_CODE);
                break;
        }
    }

    BaseCheckDetailBean.DBean.ReObjBean approveDetailListBean;

    //赋值
    public void initDetailData(BaseCheckDetailBean detailBean) {
        if (detailBean.getD().getReObj() == null) {
            return;
        }
        approveDetailListBean = detailBean.getD().getReObj();
        orderId = approveDetailListBean.getID() + "";
        listType.clear();
        listType.addAll(BaseCheckFunction.getInstance().getBaseDetailList(BaseCheckFunction.getInstance().getListType(approveDetailListBean.getApprovalTypeID(), this, tvTitle, StringUtils.getNoNullDeStr(name), peopleType, approveType), approveDetailListBean));
        detailAdapter.notifyDataSetChanged();

//        approvalMsgList = (ArrayList<ApproveListBean>) approveDetailListBean.getApprovalMsgList();
        approvalMsgList.clear();
        List<ApproveListBean> approvalMsgLists = approveDetailListBean.getApprovalOfMsgs();
        if (approvalMsgLists != null) {
            approvalMsgList.addAll(approvalMsgLists);
        }
        tvIntro.setText(StringUtils.getNoEmptyStr(approveDetailListBean.getRemark()));
        ApproveAgreeAdapter agreeAdapter = new ApproveAgreeAdapter(getContext(), approvalMsgList, R.layout.item_approve_result);
        lvApproveResult.setAdapter(agreeAdapter);
        lvApproveResult.setDividerHeight(0);

        //不同意
        if (isApply && approvalMsgList.size() > 0 && !isAgee()) {
            tvRight.setText("编辑");
        }

        if (approveDetailListBean.getApprovalState() == 0 && isApply) {
            tvChangeCheck.setVisibility(View.VISIBLE);//
        }
        lvApproveResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                   @Override
                                                   public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                       if (approvalMsgList.get(i).getApprovalOver() == 3 || BaseCheckDetailActivity.this.approvalMsgList.get(i).getApprovalOver() == 4) {
                                                           ToastUtils.getMineToast("暂无抄送详情");
                                                           return;
                                                       }
                                                       if (approvalMsgList.get(i).getApprovalOver() == -1) {
                                                           ToastUtils.getMineToast("暂时未审批");
                                                       } else {
                                                           IntentUtils.getInstance().toActivity(getContext(), ApproveAgreeDetailActivity.class, BaseCheckDetailActivity.this.approvalMsgList.get(i));
                                                       }
                                                   }
                                               }
        );

        if (!StringUtils.isNoEmpty(approveDetailListBean.getPics())) {
            return;
        }
        String isOK = (String) SharedPreferencesUtils.getInstance().get(this, SharedKeyConstant.getInstance().KEY_MAN_ID, "");
        LogUtils.e("isOK：" + isOK);
        //运营
//        if (approveDetailListBean.getApprovalPeopleType() <1 ) {
//            return;
//        }
        imgList.clear();
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
            changeId = data.getStringExtra("id");
            String name = data.getStringExtra("name");
            num = approvalMsgList.size();
            if (isHave(changeId)) {
                CopyIosDialogUtils.getInstance().getErrorDialog(getContext(), "改派已参与审批或已改派", new CopyIosDialogUtils.ErrDialogCallBack() {
                    @Override
                    public void confirm() {

                    }
                });
                return;
            }
            //1月1号
//            if (approvalMsgList.get(num - 1).getApprovalUserName().equals(name)) {
//                CopyIosDialogUtils.getInstance().getErrorDialog(getContext(), "改派已参与审批或已改派", new CopyIosDialogUtils.ErrDialogCallBack() {
//                    @Override
//                    public void confirm() {
//
//                    }
//                });
//                return;
//            }
            String changeName = "";
            for (int i = 0; i < approvalMsgList.size(); i++) {
                if (approvalMsgList.get(i).getApprovalOver() == -1) {
                    changeName = approvalMsgList.get(i).getApprovalUserName();
                }
            }


            CopyIosDialogUtils.getInstance().getIosDialog(getContext(), "是否将 " + changeName + " 改派成 " + name, new CopyIosDialogUtils.iosDialogClick() {
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

    //是否有不同意
    public boolean isAgee(){
        int num = approvalMsgList.size();
        for (int i = 0; i < num; i++) {
            if(approvalMsgList.get(i).getApprovalOver()==-0){
                return false;
            }
        }
        return true;
    }


    //判断是否有改派
    public boolean isHave(String id) {
        for (ApproveListBean checkBean : approvalMsgList) {
            if ((checkBean.getApprovalOver() == 0 || checkBean.getApprovalOver() == 1 || checkBean.getApprovalOver() == 2) && id.equals(checkBean.getApprovalUserID() + "")) {
                return true;
            }
        }
        return false;
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
}
