package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.ImageAddFunction;
import com.example.huoshangkou.jubowan.adapter.CheckImgAdapter;
import com.example.huoshangkou.jubowan.adapter.ImageAddAdapter;
import com.example.huoshangkou.jubowan.adapter.LanImageShowAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.CheckImgBean;
import com.example.huoshangkou.jubowan.bean.KxTypeBean;
import com.example.huoshangkou.jubowan.bean.SuccessDBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.inter.StringPositionCallBack;
import com.example.huoshangkou.jubowan.photo.PhotoCallBack;
import com.example.huoshangkou.jubowan.utils.DialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：InCompanyMoneyActivity
 * 描述：
 * 创建时间：2019-10-29  10:04
 */

public class InCompanyMoneyActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.grid_view_apply)
    ScrollGridView gridApplyImg;
    @Bind(R.id.tv_ck_account)
    TextView tvCkAccount;
    @Bind(R.id.tv_rk_account)
    TextView tvRkAccount;
    @Bind(R.id.recyc_check)
    RecyclerView recycCheck;
    @Bind(R.id.recyc_check_man)
    RecyclerView recycCheckMan;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.tv_ck_type)
    TextView tvCkType;
    @Bind(R.id.et_remark)
    EditText etRemark;
    @Bind(R.id.et_dk_money)
    EditText etDkMoney;
    List<String> imgList = new ArrayList<>();
    List<CheckImgBean> checkImgBeenList = new ArrayList<>();
    List<CheckImgBean> checkList = new ArrayList<>();
    private int imgNum = 20;

    //图片适配器
    ImageAddAdapter imageAddAdapter;
    //审批人
    CheckImgAdapter checkAdapter;
    //抄送人
    CheckImgAdapter checkImgAdapter;
    private String ckAccount = "";
    private String rkAccount = "";
    private String dkMoney = "";
    //审批人id
    private String approveId = "";
    private String csId = "";
    private String remark = "";
    private String payType = "";
    private String pics = "";
    private List<String> listType = new ArrayList<>();

    @Override
    public int initLayout() {
        return R.layout.activity_in_money;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {

        tvTitle.setText("内部往来账");
        tvRight.setText("提交");

        //图片显示封装
        imageAddAdapter = new ImageAddAdapter(this, imgList, "");
        ImageAddFunction.addImageFun(imageAddAdapter, imgList, this, gridApplyImg);

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
        getType();
    }

    @OnClick({R.id.ll_back, R.id.tv_right, R.id.iv_apply_camera, R.id.rl_in_company, R.id.rl_kx_type, R.id.tv_csr, R.id.rl_apply_check, R.id.rl_ck_company})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.rl_kx_type:
                DialogUtils.getInstance().getBaseDialog(this, listType, new StringPositionCallBack() {
                    @Override
                    public void onStringPosition(String str, int position) {
                        payType = str;
                        tvCkType.setText(str);
                    }
                });
                break;
            case R.id.tv_right:
                if (!StringUtils.isNoEmpty(ckAccount)) {
                    ToastUtils.getMineToast("请选择出款公司账户");
                    return;
                }
                if (!StringUtils.isNoEmpty(rkAccount)) {
                    ToastUtils.getMineToast("请选择入款公司账户");
                    return;
                }
                dkMoney = etDkMoney.getText().toString().trim();
                if (!StringUtils.isNoEmpty(dkMoney)) {
                    ToastUtils.getMineToast("请输入出款金额");
                    return;
                }
                remark = etRemark.getText().toString().trim();
                PhotoUtils.getInstance().mutilLocalImageUp(imgList, InCompanyMoneyActivity.this, new StringCallBack() {
                    @Override
                    public void onSuccess(String str) {
                        pics = str;
                        commit();
                    }

                    @Override
                    public void onFail() {
                        ToastUtils.getMineToast("上传失败，请重新上传");
                    }
                });
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
            case R.id.rl_in_company:
//                Intent intentBank = new Intent(this, ApproveBankActivity.class);
//                startActivityForResult(intentBank, 6);
                Intent intentSkAccount = new Intent(getContext(), DkCustomerActivity.class);
                intentSkAccount.putExtra(IntentUtils.getInstance().TYPE, IntentUtils.getInstance().SK_ACCOUNT);
                intentSkAccount.putExtra(IntentUtils.getInstance().VALUE, "收款账户");
                startActivityForResult(intentSkAccount, 7);
                break;
            case R.id.rl_ck_company:
                Intent intentCkAccount = new Intent(getContext(), DkCustomerActivity.class);
                intentCkAccount.putExtra(IntentUtils.getInstance().TYPE, IntentUtils.getInstance().CK_ACCOUNT);
                intentCkAccount.putExtra(IntentUtils.getInstance().VALUE, "出款账户");
                startActivityForResult(intentCkAccount, 8);
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
            //选择审批人
            case R.id.rl_apply_check:
                Intent intentCheck = new Intent(this, ChoosCheckManActivity.class);
                intentCheck.putExtra(IntentUtils.getInstance().TITLE, "审批人");
                startActivityForResult(intentCheck, 4);
                break;
        }
    }

    //内部往来款
    public void commit() {
        csId = "";
        for (CheckImgBean checkImgBean : checkImgBeenList) {
            csId += checkImgBean.getId() + ";";
        }
        if (StringUtils.isNoEmpty(csId)) {
            csId = csId.substring(0, csId.length() - 1);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("BorrowingId", 0);
        map.put("ApprovalTypeID", 1401);
        map.put("PaymentUnitAccount", ckAccount);
        map.put("PaymentMethod", payType);
        map.put("ReceiptUnitAccount", rkAccount);
        map.put("PaymentAmount", dkMoney);
        map.put("Pics", pics);
        map.put("Remark", remark);
        map.put("UserID", PersonConstant.getInstance().getUserId());
        map.put("ApprovalUserID", approveId);
        map.put("CopyUserID", csId);
        map.put("ApprovalOver", 1);
        map.put("Approvalremark", "");
        map.put("ApprovalPic", "");
        map.put("PaymentProvePic", "");
        String json = "{\n" +
                "\"dealingPayment\":" + JSON.toJSONString(map) + "}";
        OkhttpUtil.getInstance().basePostCall(this, json, UrlConstant.getInstance().IN_DOOR_MONEY + "DealingPaymentApprovalApi", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                SuccessDBean dBean = JSON.parseObject(str, SuccessDBean.class);
                if (dBean.getD().getSuccess() == 1) {
                    InCompanyMoneyActivity.this.finish();
                    ToastUtils.getMineToast("提交成功");
                }else {
                    ToastUtils.getMineToast("提交失败");
                }
//                ToastUtils.getMineToast(dBean.getD().getErrMsg());
            }

            @Override
            public void onFail() {

            }
        });
    }

    //款项类型
    public void getType() {
        OkhttpUtil.getInstance().basePostCall(this, "", UrlConstant.getInstance().IN_DOOR_MONEY + "GetNatureOfCurrentAccount", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                KxTypeBean typeBean = JSON.parseObject(str, KxTypeBean.class);
                List<KxTypeBean.DBean.ReObjBean> reObj = typeBean.getD().getReObj();
                if (reObj != null) {
                    for (int i = 0; i < reObj.size(); i++) {
                        listType.add(reObj.get(i).getMoneyNature());
                    }
                }

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
            case 4:
                approveId = data.getStringExtra("id");
                if (isHave(approveId)) {
                    ToastUtils.getMineToast("审批人不能与抄送人相同");
                    approveId = "";
                    return;
                }
                approveId = data.getStringExtra("id");
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
            //出款账户
            case 8:
                ckAccount = data.getStringExtra(IntentUtils.getInstance().TYPE);
                tvCkAccount.setText(ckAccount);
                break;
            //收款账户
            case 7:
                rkAccount = data.getStringExtra(IntentUtils.getInstance().TYPE);
                tvRkAccount.setText(rkAccount);
                break;
        }
    }

    private StaggeredGridLayoutManager getLayoutManager() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL);
        return layoutManager;
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
