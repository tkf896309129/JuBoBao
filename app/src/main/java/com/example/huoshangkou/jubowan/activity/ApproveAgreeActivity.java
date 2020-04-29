package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.ImageAddFunction;
import com.example.huoshangkou.jubowan.adapter.CheckImgAdapter;
import com.example.huoshangkou.jubowan.adapter.ImageAddAdapter;
import com.example.huoshangkou.jubowan.adapter.LanImageShowAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ApproveListBean;
import com.example.huoshangkou.jubowan.bean.BaseCheckResultBean;
import com.example.huoshangkou.jubowan.bean.CheckImgBean;
import com.example.huoshangkou.jubowan.bean.SerMap;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.bean.SuccessDBean;
import com.example.huoshangkou.jubowan.bean.YwSuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.SharedValueConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.ChooseDialogPositionCallBack;
import com.example.huoshangkou.jubowan.inter.OnSignImageCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.photo.PhotoCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.KeyBoardUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.SignDialogUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollGridView;

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
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：ApproveAgreeActivity
 * 描述：
 * 创建时间：2017-03-22  14:52
 */

public class ApproveAgreeActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.cb_finish)
    Switch cbFinish;
    @Bind(R.id.tv_choose_man)
    TextView tvChooseMan;
    @Bind(R.id.tv_cs_man)
    TextView tvCsMan;
    //下级审批人
    @Bind(R.id.rl_next_choose)
    RelativeLayout rlNext;
    @Bind(R.id.recyc_check)
    RecyclerView recyclerView;
    @Bind(R.id.recyc_check_man)
    RecyclerView recyclerViewMan;
    //是否流程结束
    private boolean isFinish = false;
    //图片
    List<String> imgList;
    //图片适配器
    ImageAddAdapter imageAddAdapter;
    @Bind(R.id.grid_view_apply)
    ScrollGridView scrollGridView;
    private int REQUEST_CODE = 1;
    private int CS_CODE = 2;
    private String approveId = "";
    private String approveUserId = "0";
    private String approveTypeId = "";
    //抄送人id
    private String csId;
    //是否同意
    private String isAgree = "";
    //0=同意，1=不同意
    private int agreeApprove = 0;
    //是否结束  0不结束,1结束
    private int isEnd = 0;
    private int imgNum = 9;
    //说明
    private String remark = "";
    private String pics = "";
    //款项用途
    private String moneyUse = "";
    //银行id
    private String bankId = "";
    //id集合
    private String ids = "";

    @Bind(R.id.et_intro)
    EditText etIntro;
    private String content = "";
    private String userId = "";
    private String btEd = "";
    //表格信息
    private String tableMessage = "";

    //是否是销售业务用款审批
    private boolean isSaleYwCheck = false;
    private boolean isDianFuCheck = false;
    private boolean isNeiBuMoney = false;
    private Map<String, String> ywMap;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    PhotoUtils.getInstance().mutilLocalImageUp(imgList, ApproveAgreeActivity.this, new StringCallBack() {
                        @Override
                        public void onSuccess(String str) {
                            pics = str + "," + signPath;
                            for (CheckImgBean checkImgBean : checkImgBeenList) {
                                csId += checkImgBean.getId() + ";";
                            }

                            baseCheck();

//                            if (isNeiBuMoney) {
//                                otherCheck();
//                                return;
//                            }
//                            if (isDianFuCheck) {
//                                ywMap.put("ApprovalOver", agreeApprove + "");
//                                ywMap.put("ApprovalPic", pics);
//                                ywMap.put("CopyUserID", csId);
//                                if (!StringUtils.isNoEmpty(approveUserId)) {
//                                    approveUserId = "0";
//                                }
//                                ywMap.put("ApprovalUserID", approveUserId);
//                                ywMap.put("Approvalremark", remark);
//                                checkDianDetail();
//                                return;
//                            }
//                            if (isSaleYwCheck) {
//                                ywMap.put("ApprovalOver", agreeApprove + "");
//                                ywMap.put("ApprovalPic", pics);
//                                ywMap.put("CopyUserID", csId);
//                                if (!StringUtils.isNoEmpty(approveUserId)) {
//                                    approveUserId = "0";
//                                }
//                                ywMap.put("ApprovalUserID", approveUserId);
//                                ywMap.put("Approvalremark", remark);
//                                ywCheck();
//                                return;
//                            }
//
//                            setDianCheck(pics, remark, agreeApprove, approveId, approveUserId, approveTypeId, typePrice,
//                                    sellUserId, SellPrice, sellCashOrAccept, StartTime, Invoice, Advance, EndTime, Remark,
//                                    adVanceId, PurchasePrice, surchaseCashOrAccept, RemitDircetion, RemitAccount, ProceedsAccount,
//                                    Profit, FreightTotalPrice, syCompany, syId);
                        }

                        @Override
                        public void onFail() {
                            ToastUtils.getMineToast("上传失败，请重新上传");
                        }
                    });
                    break;
            }
        }
    };

    private String sellUserId = "";
    private String isOpen = "";
    private String typePrice = "";
    private String adVanceId = "";
    private String SellPrice = "";
    private String sellCashOrAccept = "";
    private String StartTime = "";
    private String Invoice = "";
    private String Advance = "";
    private String EndTime = "";
    private String Remark = "";
    private String PurchasePrice = "";
    private String RemitDircetion = "";
    private String RemitAccount = "";
    private String ProceedsAccount = "";
    private String Profit = "";
    private String FreightTotalPrice = "";
    private String surchaseCashOrAccept = "";
    //上游公司
    private String syCompany = "";
    //上游id
    private String syId = "";
    //是否打出
    private String isDaChu = "";
    private String signPath = "";
    private String borrowId = "";

    List<String> listId = new ArrayList<>();
    List<String> csIdList = new ArrayList<>();
    //抄送人
    CheckImgAdapter checkImgAdapter;
    //审批人
    CheckImgAdapter checkAdapter;
    List<CheckImgBean> checkImgBeenList = new ArrayList<>();
    List<CheckImgBean> checkList = new ArrayList<>();
    //审批人列表
    ArrayList<ApproveListBean> approvalMsgList = new ArrayList<>();
    private int csChangePosition = -1;


    @Override
    public int initLayout() {
        return R.layout.activity_approve_agree;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().approveCheckList);

        approveTypeId = getIntent().getStringExtra(IntentUtils.getInstance().APPROVE_TYPE_ID);
        approveId = getIntent().getStringExtra(IntentUtils.getInstance().APPROVE_ID);
        isAgree = getIntent().getStringExtra(IntentUtils.getInstance().APPROVE_OVER);
        String csIdApprove = getIntent().getStringExtra(IntentUtils.getInstance().CSR);
        String spId = getIntent().getStringExtra(IntentUtils.getInstance().SPR);
        if (StringUtils.isNoEmpty(csIdApprove)) {
            String[] split2 = csIdApprove.split(",");
            for (String csIds : split2) {
                csIdList.add(csIds);
            }
        }
        if (StringUtils.isNoEmpty(spId)) {
            String[] split1 = spId.split(",");
            for (String id : split1) {
                listId.add(id);
            }
        }

        switch (approveTypeId) {
            case "1401":
                isNeiBuMoney = true;
                break;
            case "1301":
                isDianFuCheck = true;
                break;
            case "16":
            case "1201":
                isSaleYwCheck = true;
                break;
        }

//-----
//        SerMap sapMap = (SerMap) getIntent().getSerializableExtra(IntentUtils.getInstance().MAP);
//        String checkType = getIntent().getStringExtra(IntentUtils.getInstance().CHECK_TYPE);
//        if (StringUtils.isNoEmpty(checkType)) {
//            borrowId = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
//            switch (checkType) {
//                case "in_company_type":
//                    isNeiBuMoney = true;
//                    break;
//            }
//        }
//        if (sapMap != null) {
//            ywMap = sapMap.getMap();
//            String stringExtra = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
//            if (ywMap != null) {
//                if (StringUtils.isNoEmpty(stringExtra)) {
//                    isDianFuCheck = true;
//                } else {
//                    isSaleYwCheck = true;
//                }
//            }
//        }
//        approvalMsgList = getIntent().getParcelableArrayListExtra(IntentUtils.getInstance().LIST);
//        isAgree = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
//        if (!isSaleYwCheck && !isDianFuCheck && !isNeiBuMoney) {
//            approveId = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
//            approveTypeId = getIntent().getStringExtra(IntentUtils.getInstance().STR);
//            userId = getIntent().getStringExtra(IntentUtils.getInstance().USER_ID);
//            content = getIntent().getStringExtra(IntentUtils.getInstance().TXT);
//            syCompany = getIntent().getStringExtra(IntentUtils.getInstance().SY_COMPANY);
//            syId = getIntent().getStringExtra(IntentUtils.getInstance().SY_ID);
//            moneyUse = getIntent().getStringExtra(IntentUtils.getInstance().MONEY_USE);
//            bankId = getIntent().getStringExtra(IntentUtils.getInstance().BANK_ID);
//            isDaChu = getIntent().getStringExtra(IntentUtils.getInstance().IS_DA_CHU);
//            tableMessage = getIntent().getStringExtra(IntentUtils.getInstance().TAB_MESSAGE);
//            ids = getIntent().getStringExtra(IntentUtils.getInstance().ID);
//            btEd = getIntent().getStringExtra(IntentUtils.getInstance().BT_ED);
//            String csIds = getIntent().getStringExtra(IntentUtils.getInstance().CSR);
//            String[] split2 = csIds.split(",");
//            for (String csId : split2) {
//                csIdList.add(csId);
//            }
//            String[] split1 = ids.split(",");
//            for (String id : split1) {
//                listId.add(id);
//            }
//        }
//
//        if (StringUtils.isNoEmpty(content)) {
//            String[] split = content.split("、");
//            LogUtils.i(content + split.length);
//            if (split.length >= 17) {
//                sellUserId = split[0];
//                isOpen = split[1];
//                typePrice = split[2];
//                adVanceId = split[3];
//                SellPrice = split[4];
//                sellCashOrAccept = split[5];
//                StartTime = split[6];
//                Invoice = split[7];
//                Advance = split[8];
//                EndTime = split[9];
//                Remark = split[10];
//                PurchasePrice = split[11];
//                surchaseCashOrAccept = split[12];
//                RemitDircetion = split[13];
//                RemitAccount = split[14];
//                ProceedsAccount = split[15];
//                Profit = split[16];
//                if (split.length == 18) {
//                    FreightTotalPrice = split[17];
//                }
//            }
//        }
//-----
        if (isAgree.equals(IntentUtils.getInstance().AGREE_APPROVE)) {
            agreeApprove = 1;
            tvTitle.setText("同意");
        } else if (isAgree.equals(IntentUtils.getInstance().DISAGREE_APPROVE)) {
            agreeApprove = 0;
            tvTitle.setText("不同意");
            cbFinish.setChecked(true);
            cbFinish.setClickable(false);
            rlNext.setVisibility(View.GONE);
        }

        tvRight.setText("提交");
        imgList = new ArrayList<>();

        //图片显示封装
        imageAddAdapter = new ImageAddAdapter(this, imgList, "");
        ImageAddFunction.addImageFun(imageAddAdapter, imgList, this, scrollGridView);

        cbFinish.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isFinish = b;
                if (isFinish) {
                    ToastUtils.getMineToast("流程结束");
                    tvChooseMan.setTextColor(getResources().getColor(R.color.main_tab_gray));
                    approveUserId = "0";
                    isEnd = 1;
                    rlNext.setVisibility(View.GONE);
                    checkList.clear();
                    checkAdapter.notifyDataSetChanged();
                } else {
                    tvChooseMan.setTextColor(getResources().getColor(R.color.address_black_key));
                    isEnd = 0;
                    rlNext.setVisibility(View.VISIBLE);
                }
            }
        });

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
        checkImgAdapter.setChangePositionCallBack(new ChooseDialogPositionCallBack() {
            @Override
            public void onGetMessagePosition(String message, int position) {


                csChangePosition = position;
                Intent intent2 = new Intent(ApproveAgreeActivity.this, ChoosCheckManActivity.class);
                intent2.putExtra(IntentUtils.getInstance().TITLE, "抄送人");
                startActivityForResult(intent2, CS_CODE);
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
                approveUserId = "";
            }
        });
        checkAdapter.setChangePositionCallBack(new ChooseDialogPositionCallBack() {
            @Override
            public void onGetMessagePosition(String message, int position) {
                Intent intent = new Intent(ApproveAgreeActivity.this, ChoosCheckManActivity.class);
                intent.putExtra(IntentUtils.getInstance().TITLE, "审批人");
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    private StaggeredGridLayoutManager getLayoutManager() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL);
        return layoutManager;
    }

    @OnClick({R.id.rl_cs_choose, R.id.rl_next_choose, R.id.ll_back, R.id.iv_apply_camera, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_next_choose:
                if (isFinish) {
                    ToastUtils.getMineToast("流程已经结束");
                    return;
                }
                Intent intent = new Intent(this, ChoosCheckManActivity.class);
                intent.putExtra(IntentUtils.getInstance().TITLE, "审批人");
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.iv_apply_camera:
                if (imgList.size() >= 9) {
                    ToastUtils.getMineToast("最多选择9张图片");
                    return;
                }
                int num = imgNum - imgList.size();
                PhotoUtils.getInstance().getMoreLocalPhoto(this, num, new PhotoCallBack() {
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
            case R.id.tv_right:
                remark = etIntro.getText().toString().trim();
                KeyBoardUtils.closeKeybord(etIntro, getContext());
                if ((!isFinish && StringUtils.isNoEmpty(approveUserId) && (agreeApprove == 1 && approveUserId.equals("0"))) || !StringUtils.isNoEmpty(approveUserId)) {
                    ToastUtils.getMineToast("请选择审批人");
                    return;
                }
                csId = "";
                SignDialogUtils.getInstance().updateDialog(getContext(), new OnSignImageCallBack() {
                    @Override
                    public void onSignSuccess(final String path) {
                        signPath = path;
                        handler.sendEmptyMessage(1);
                    }
                });
                break;
            case R.id.rl_cs_choose:
                int numCs = checkImgBeenList.size();
                if (numCs >= 3) {
                    CopyIosDialogUtils.getInstance().getErrorDialog(getContext(), "抄送人最多选择三个人", new CopyIosDialogUtils.ErrDialogCallBack() {
                        @Override
                        public void confirm() {

                        }
                    });
                    return;
                }
                csChangePosition = -1;
                Intent intentCs = new Intent(this, ChoosCheckManActivity.class);
                intentCs.putExtra(IntentUtils.getInstance().TITLE, "抄送人");
                startActivityForResult(intentCs, CS_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (requestCode == REQUEST_CODE) {
            String type = data.getStringExtra("type");
            if (StringUtils.isNoEmpty(type) && type.equals("csId")) {
                csMan(data);
                return;
            }
            String id_1 = data.getStringExtra("id");
            if (id_1.equals(userId)) {
                CopyIosDialogUtils.getInstance().getErrorDialog(getContext(), "审批人不能为申请人", new CopyIosDialogUtils.ErrDialogCallBack() {
                    @Override
                    public void confirm() {

                    }
                });
                return;
            }
            if (StringUtils.isNoEmpty(csId) && id_1.equals(csId)) {
                CopyIosDialogUtils.getInstance().getErrorDialog(getContext(), "此人已被选为抄送人", new CopyIosDialogUtils.ErrDialogCallBack() {
                    @Override
                    public void confirm() {

                    }
                });
                return;
            }
            if (isHave(id_1, listId)) {
                ToastUtils.getMineToast("该人员已经参与过审核");
                return;
            }
            if (approvalMsgList != null) {
                int num = approvalMsgList.size();
                //-1=未审批，1=同意，0=不同意 2无效 3未读抄送  4已读抄送
                for (int i = 0; i < num; i++) {
                    int approvalOver = approvalMsgList.get(i).getApprovalOver();
                    if (approvalOver == 1 || approvalOver == 0) {
                        if (id_1.equals(approvalMsgList.get(i).getApprovalUserID() + "")) {
                            CopyIosDialogUtils.getInstance().getErrorDialog(getContext(), " 该订单已被" + approvalMsgList.get(i).getApprovalUserName() + "审批,请勿重复审批", new CopyIosDialogUtils.ErrDialogCallBack() {
                                @Override
                                public void confirm() {

                                }
                            });
                            return;
                        }
                    }
                }
            }

            int numCs = checkImgBeenList.size();
            for (int i = 0; i < numCs; i++) {
                if (id_1.equals(checkImgBeenList.get(i).getId())) {
                    CopyIosDialogUtils.getInstance().getErrorDialog(getContext(), "此人已被选为抄送人", new CopyIosDialogUtils.ErrDialogCallBack() {
                        @Override
                        public void confirm() {

                        }
                    });
                    return;
                }
            }

            approveUserId = data.getStringExtra("id");
//            tvChooseMan.setText(data.getStringExtra("name"));
            checkList.clear();
//            tvCheckMan.setText(data.getStringExtra("name"));
            String approveId = data.getStringExtra("id");
            String name = data.getStringExtra("name");
            String img = data.getStringExtra("img");

            CheckImgBean checkImgBean = new CheckImgBean();
            checkImgBean.setName(name);
            checkImgBean.setImg(img);
            checkImgBean.setId(approveId);
            checkList.add(checkImgBean);
            checkAdapter.notifyDataSetChanged();

        } else if (requestCode == CS_CODE) {
            csMan(data);
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

    //抄送人
    public void csMan(Intent data) {
        String id = data.getStringExtra("id");
        if (id.equals(userId)) {
            CopyIosDialogUtils.getInstance().getErrorDialog(getContext(), "抄送人不能为申请人", new CopyIosDialogUtils.ErrDialogCallBack() {
                @Override
                public void confirm() {

                }
            });
            return;
        }
        if (StringUtils.isNoEmpty(approveUserId) && id.equals(approveUserId)) {
            CopyIosDialogUtils.getInstance().getErrorDialog(getContext(), "此人已被选为审批人", new CopyIosDialogUtils.ErrDialogCallBack() {
                @Override
                public void confirm() {

                }
            });
            return;
        }
        if (isHave(id, listId) || isHave(id, csIdList)) {
            ToastUtils.getMineToast("该人员已经参与过审核或者抄送");
            return;
        }
        if (approvalMsgList != null) {
            int num = approvalMsgList.size();
            //-1=未审批，1=同意，0=不同意 2无效 3未读抄送  4已读抄送
            for (int i = 0; i < num; i++) {
                LogUtils.e(id + "   " + approvalMsgList.get(i).getApprovalUserID());
                if (id.equals(approvalMsgList.get(i).getApprovalUserID() + "")) {
                    String message = "";
                    switch (approvalMsgList.get(i).getApprovalOver()) {
                        case -1:
                        case 1:
                        case 0:
                            message = " 该订单已被" + approvalMsgList.get(i).getApprovalUserName() + "审批,请勿重复抄送";
                            break;
                        case 3:
                        case 4:
                            message = " 该订单已为" + approvalMsgList.get(i).getApprovalUserName() + "抄送,请勿重复抄送";
                            break;
                    }
                    CopyIosDialogUtils.getInstance().getErrorDialog(getContext(), message, new CopyIosDialogUtils.ErrDialogCallBack() {
                        @Override
                        public void confirm() {

                        }
                    });
                    return;
                }
            }
        }

        int numCs = checkImgBeenList.size();
        for (int i = 0; i < numCs; i++) {
            if (id.equals(checkImgBeenList.get(i).getId())) {
                CopyIosDialogUtils.getInstance().getErrorDialog(getContext(), "抄送人已存在请勿重复添加", new CopyIosDialogUtils.ErrDialogCallBack() {
                    @Override
                    public void confirm() {

                    }
                });
                return;
            }
        }

        String name = data.getStringExtra("name");
        String img = data.getStringExtra("img");
//        CheckImgBean checkImgBean = new CheckImgBean();
//        checkImgBean.setName(name);
//        checkImgBean.setImg(img);
//        checkImgBean.setId(id);
//        checkImgBeenList.add(checkImgBean);

        if (csChangePosition == -1) {
            CheckImgBean checkImgBean = new CheckImgBean();
            checkImgBean.setName(name);
            checkImgBean.setImg(img);
            checkImgBean.setId(id);
            checkImgBeenList.add(checkImgBean);
        } else {
            checkImgBeenList.get(csChangePosition).setId(id);
            checkImgBeenList.get(csChangePosition).setImg(img);
            checkImgBeenList.get(csChangePosition).setName(name);
        }
        checkImgAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (checkAdapter != null) {
            checkAdapter.notifyDataSetChanged();
        }
    }

    //垫付款审批
    public void setDianCheck(String pic, String remarks, int approveOver,
                             String approveId, String approveUserId, String approveTypeId, String typePrice, String sellUserId, String sellPrice, String sellCashOrAccept, String startTime, String invoince,
                             String advance, String endTime, String remark, String advanceId, String purchasePrice,
                             String purchaseCashOrAccept, String remitDirection, String remitAccount, String procedsAccount,
                             String profit, String freightTotalPrice, String syCompany, String syId) {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message),
                UrlConstant.getInstance().URL + PostConstant.getInstance().SET_CHECK_AD
                        + FieldConstant.getInstance().APPROVE_PIC + "=" + pic + "&"
                        + FieldConstant.getInstance().APPROVE_REMARK + "=" + EncodeUtils.getInstance().getEncodeString(remarks) + "&"
                        + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                        + FieldConstant.getInstance().CS_ID + "=" + csId + "&"
                        + FieldConstant.getInstance().APPROVE_RESULT + "=" + approveOver + "&"
                        + FieldConstant.getInstance().APPROVE_ID + "=" + approveId + "&"
                        + FieldConstant.getInstance().IS_OPEN + "=" + isOpen + "&"
                        + FieldConstant.getInstance().UP_YUAN_ID + "=" + syId + "&"
                        + FieldConstant.getInstance().APPROVE_TYPE_ID + "=" + approveTypeId + "&"
                        + FieldConstant.getInstance().APPROVE_USER_ID + "=" + approveUserId + "&"
                        + FieldConstant.getInstance().SELL_USER_ID + "=" + sellUserId + "&"
                        + FieldConstant.getInstance().TYPE_PRICE + "=" + typePrice + "&"
                        + FieldConstant.getInstance().ADVANCE_ORDER_ID + "=" + advanceId + "&"
                        + FieldConstant.getInstance().SELL_PRICE + "=" + sellPrice + "&"
                        + FieldConstant.getInstance().SELL_CASH_OR_ACCEPT + "=" + sellCashOrAccept + "&"
                        + FieldConstant.getInstance().START_TIME + "=" + startTime + "&"
                        + FieldConstant.getInstance().INVOINCE + "=" + EncodeUtils.getInstance().getEncodeString(invoince) + "&"
                        + FieldConstant.getInstance().ADVANCE + "=" + EncodeUtils.getInstance().getEncodeString(advance) + "&"
                        + FieldConstant.getInstance().END_TIME + "=" + endTime + "&"
                        + FieldConstant.getInstance().REMARK + "=" + EncodeUtils.getInstance().getEncodeString(StringUtils.getNoEmptyStr(remark)) + "&"
                        + FieldConstant.getInstance().PURCHASE_PRICE + "=" + purchasePrice + "&"
                        + FieldConstant.getInstance().SP_BANK_ID + "=" + bankId + "&"
                        + FieldConstant.getInstance().FUND_WAY + "=" + EncodeUtils.getInstance().getEncodeString(moneyUse) + "&"
                        + FieldConstant.getInstance().PURCHASE_CASH_OR_ACCEPT + "=" + purchaseCashOrAccept + "&"
                        + FieldConstant.getInstance().REMIT_DIRCETION + "=" + EncodeUtils.getInstance().getEncodeString(remitDirection) + "&"
                        + FieldConstant.getInstance().REMIT_ACCOUNT + "=" + EncodeUtils.getInstance().getEncodeString(remitAccount) + "&"
                        + FieldConstant.getInstance().PROCEEDS_ACCOUNT + "=" + EncodeUtils.getInstance().getEncodeString(procedsAccount) + "&"
                        + FieldConstant.getInstance().PROFIT + "=" + EncodeUtils.getInstance().getEncodeString(profit) + "&"
                        + FieldConstant.getInstance().IS_DACHU + "=" + isDaChu + "&"
                        + FieldConstant.getInstance().ORDER_TABLE + "=" + tableMessage + "&"
                        + FieldConstant.getInstance().IOUS_AMOUNT + "=" + btEd + "&"
                        + FieldConstant.getInstance().FREIGHT_TOTAL_PRICE + "=" + freightTotalPrice, new OkhttpCallBack() {
                    @Override
                    public void onSuccess(String json) {
                        SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                        if (successBean.getSuccess() == 1) {
                            ToastUtils.getMineToast("审批完成");
                            ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().approveCheckList);
                            SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().APPROVE_AGREE, SharedValueConstant.getInstance().APPROVE_AGREE);
                        } else {
                            ToastUtils.getMineToast(successBean.getErrMsg());
                        }
                    }

                    @Override
                    public void onFail() {
                        ToastUtils.getMineToast("审批失败");
                    }
                });
    }

    //业务用款审批
    public void ywCheck() {
        String json = JSON.toJSONString(ywMap);
        String putJson = "{\n" +
                "\t\"jsondata\":" + json + "}";
        LogUtils.e(putJson);
        OkhttpUtil.getInstance().basePostCall(this, putJson, UrlConstant.getInstance().YW_URL + "ApprovalOfBusinessFunds", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                YwSuccessBean successBean = JSON.parseObject(str, YwSuccessBean.class);
                if (successBean != null && successBean.getD() != null) {
                    if (successBean.getD().getSuccess() == 1) {
                        ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().approveCheckList);
                        SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().APPROVE_AGREE, SharedValueConstant.getInstance().APPROVE_AGREE);
                        ToastUtils.getMineToast("提交成功");
                    } else {
                        ToastUtils.getMineToast(successBean.getD().getErrMsg());
                    }
                }
            }

            @Override
            public void onFail() {
                ToastUtils.getMineToast("审批失败");
            }
        });
    }

    //垫付款审批
    public void checkDianDetail() {
        String json = "{\"padPayment\":" + JSON.toJSONString(ywMap) + "}";

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

    //内部往来款其他人员审批
    public void otherCheck() {
        Map<String, Object> map = new HashMap<>();
        map.put("ApprovalTypeID", approveTypeId);
        map.put("BorrowingId", approveId);
        map.put("UserID", PersonConstant.getInstance().getUserId());
        map.put("ApprovalUserID", approveUserId);
        map.put("CopyUserID", csId);
        map.put("ApprovalOver", agreeApprove);
        map.put("Approvalremark", remark);
        map.put("ApprovalPic", pics);
        map.put("PaymentProvePic", "");

        String json = "{dealingPayment:" + JSON.toJSONString(map) + "}";
        OkhttpUtil.getInstance().basePostCall(this, json, UrlConstant.getInstance().IN_DOOR_MONEY + "DealingPaymentApprovalApi", new StringCallBack() {
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

    //审批通用接口
    public void baseCheck() {
        Map<String, Object> map = new HashMap<>();
        map.put("EditOrAddType", 1);
        map.put("ApprovalTypeID", approveTypeId);
        map.put("BorrowingId", approveId);
        map.put("ApprovalID", approveId);
        map.put("UserID", PersonConstant.getInstance().getUserId());
        map.put("ApprovalUserID", approveUserId);
        map.put("CopyUserID", csId);
        map.put("ApprovalOver", agreeApprove);
        map.put("Approvalremark", remark);
        map.put("ApprovalPic", pics);
        map.put("PaymentProvePic", "");
        String json = "{model:" + JSON.toJSONString(map) + "}";
        OkhttpUtil.getInstance().basePostCallDialog(this, json, UrlConstant.getInstance().POST_COMMON_CHECK + "AddApprovalCommonApi", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                BaseCheckResultBean successBean = JSON.parseObject(str, BaseCheckResultBean.class);
                if (successBean.getD() != null && successBean.getD().getSuccess() == 1) {
                    ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().approveCheckList);
                }
                ToastUtils.getMineToast(successBean.getD().getErrMsg());
            }

            @Override
            public void onFail() {

            }
        });
    }

    //判断是否有改派
    public boolean isHave(String id, List<String> list) {
        for (String checkBean : list) {
            if (id.equals(checkBean)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
