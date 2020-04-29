package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.BaseCheckFunction;
import com.example.huoshangkou.jubowan.activity.function.ImageAddFunction;
import com.example.huoshangkou.jubowan.adapter.ApproveAgreeAdapter;
import com.example.huoshangkou.jubowan.adapter.BaseCheckAdapter;
import com.example.huoshangkou.jubowan.adapter.CheckImgAdapter;
import com.example.huoshangkou.jubowan.adapter.ImageAddAdapter;
import com.example.huoshangkou.jubowan.adapter.LanImageShowAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ApproveBankListBean;
import com.example.huoshangkou.jubowan.bean.ApproveListBean;
import com.example.huoshangkou.jubowan.bean.BaseCheckBean;
import com.example.huoshangkou.jubowan.bean.BaseCheckDetailBean;
import com.example.huoshangkou.jubowan.bean.CheckImgBean;
import com.example.huoshangkou.jubowan.bean.KeyValueBean;
import com.example.huoshangkou.jubowan.bean.KxTypeBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PhotoConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.ChooseDialogPositionCallBack;
import com.example.huoshangkou.jubowan.inter.OnPositionCallBack;
import com.example.huoshangkou.jubowan.inter.OnTimeCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.inter.StringPositionCallBack;
import com.example.huoshangkou.jubowan.photo.PhotoCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.DateUtils;
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
import com.example.huoshangkou.jubowan.utils.VersionUtils;
import com.example.huoshangkou.jubowan.view.ScrollGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：BaseCheckActivity
 * 描述：
 * 创建时间：2019-11-26  14:02
 */

public class BaseCheckActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.rlv_base_check)
    RecyclerView recyclerViewMain;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.ll_back)
    LinearLayout llBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    LinearLayout llCheck;
    RelativeLayout llImg;
    ImageView imgApply;
    RelativeLayout rlCheck;
    RelativeLayout rlCsMan;
    ScrollGridView scrollGridView;
    RecyclerView recyclerViewCheck;
    RecyclerView recyclerViewCs;
    TextView tvChooseCheck;
    RelativeLayout rlChooseCheck;
    BaseCheckAdapter checkBaseAdapter;
    EditText etRemark;
    TextView tvIntro;
    TextView tvChooseBank;
    List<BaseCheckBean> list = new ArrayList<>();

    private int imgNum = 9;
    private String pics = "";
    private String timeSpan = "";
    private String bankId = "";
    private String csId = "";
    //是否是更换设备
    private boolean isDevice = false;
    //图片
    List<String> imgList = new ArrayList<>();
    //图片适配器
    ImageAddAdapter imageAddAdapter;
    //抄送人
    CheckImgAdapter checkCsAdapter;
    //审批人
    CheckImgAdapter checkImgAdapter;
    //用款明细
    private String remak = "";
    //开始日期
    private String startTime = "";
    //开始时间段
    private String startTimes = "";
    //结束日期
    private String endTime = "";
    //结束时间段
    private String endTimes = "";
    //时间差
    private double timeCaculate = 0;
    List<CheckImgBean> checkImgBeenList = new ArrayList<>();
    List<CheckImgBean> checkList = new ArrayList<>();
    List<String> listChoose = new ArrayList<>();
    //时间段
    ArrayList<String> dayList = new ArrayList<>();
    //请假类型
    ArrayList<String> holidayList = new ArrayList<>();
    //款项类型
    ArrayList<String> kxList = new ArrayList<>();
    //客户类型
    ArrayList<String> customerList = new ArrayList<>();
    //收汇款方式
    List<String> listTradeType = new ArrayList<>();
    //业务用款_客户_货款性质
    List<String> listCustomer = new ArrayList<>();
    //款项类型
    List<String> listKxType = new ArrayList<>();
    //其他审批
    List<String> listOtherType = new ArrayList<>();
    //业务用款_平台_货款性质
    List<String> listPinTai = new ArrayList<>();
    //getKeyData("业务用款_贸易商性质");
    List<String> listGysXz = new ArrayList<>();
    //详情结果
    ArrayList<ApproveListBean> approvalMsgList = new ArrayList<>();
    BaseCheckDetailBean.DBean.ReObjBean approveDetailListBean;
    private final int REQUEST_CODE = 1;
    private final int REQUEST_CODE_BANK = 2;
    private final int BANK_ACCOUNT = 4;
    private String id = "0";
    private String title = "";
    private String typeId = "";
    private String approveUserId = "";
    private String approveId = "";
    private String name = "";
    private String approveOrderId = "";

    private int accountPosition = 0;
    //是否需要审批人
    private boolean isNeedApproce = true;
    //是否需要客服
    private boolean isChooseKf = false;
    //是否需要选择审批人 
    private boolean isNeedCheckMan = true;
    //是否是添加 表示类型为编辑或添加0添加1编辑
    private int editType = 0;
    private String peopleType = "1";
    private int csChangePosition = -1;

    @Override
    public int initLayout() {
        return R.layout.activty_base_check;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().approveCheckList);
        tvRight.setText("提交");
        id = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        title = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        typeId = getIntent().getStringExtra(IntentUtils.getInstance().STR);
        approveOrderId = getIntent().getStringExtra(IntentUtils.getInstance().TXT);
        peopleType = getIntent().getStringExtra(IntentUtils.getInstance().TYPE_DETAILS);
        approveDetailListBean = (BaseCheckDetailBean.DBean.ReObjBean) getIntent().getSerializableExtra(IntentUtils.getInstance().BEAN_TYPE);
        if (approveDetailListBean != null) {
            id = approveDetailListBean.getID() + "";
            typeId = approveDetailListBean.getApprovalTypeID() + "";
            editType = 0;
        }
        tvTitle.setText(title);
//        String isOK = (String) SharedPreferencesUtils.getInstance().get(this, SharedKeyConstant.getInstance().KEY_MAN_ID, "");
//        //运营
//        if (isOK.equals("2")) {
//            isNeedCheckMan = true;
//        } else {
//            isNeedCheckMan = false;
//        }

        //主体内容
        checkBaseAdapter = new BaseCheckAdapter(this, list);
        recyclerViewMain.setAdapter(checkBaseAdapter);
        View view = LayoutInflater.from(this).inflate(R.layout.item_base_check_bottom, null);
        initFootView(view);
        //初始化数据
        BaseCheckFunction.getInstance().initData(this, Integer.parseInt(typeId), list, rlChooseCheck, llCheck, isNeedApproce, peopleType, llImg, tvIntro, tvChooseBank);
        checkBaseAdapter.setFootView(view);
        recyclerViewMain.setLayoutManager(getLayoutManager());

        switch (id) {
            case 1201 + "":
            case 1011 + "":
            case 1301 + "":
                editType = 1;
                getApplyDetail();
                break;
        }

        //点击事件
        checkBaseAdapter.setChoosePositionCallBack(new OnPositionCallBack() {
            @Override
            public void onPositionClick(final int position) {
                hideInput();
                switch (list.get(position).getChooseType()) {
                    case "时间":
                        TimeDialogUtils.getInstance().getTime(getContext(), new OnTimeCallBack() {
                            @Override
                            public void getYearTime(String year) {
                                //请假出差时长处理
                                if (list.get(position).getType() == 3 || list.get(position).getType() == 4) {
                                    //请假时长处理
                                    switch (list.get(position).getHintType()) {
                                        case "开始日期":
                                            startTime = year;
                                            long timeCaculate1 = DateUtils.getInstance().getTimeCaculate(startTime, endTime);
                                            if (timeCaculate1 < 0 || (timeCaculate1 == 0 && startTimes.equals("下午") && endTimes.equals("上午"))) {
                                                getErrorDialog();
//                                                startTime = "";
                                                return;
                                            }
                                            list.get(position).setContent(startTime);
                                            break;
                                        case "结束日期":
                                            endTime = year;
                                            long timeCaculate2 = DateUtils.getInstance().getTimeCaculate(startTime, endTime);
                                            if (timeCaculate2 < 0 || (timeCaculate2 == 0 && startTimes.equals("下午") && endTimes.equals("上午"))) {
                                                getErrorDialog();
//                                                endTime = "";
                                                return;
                                            }
                                            list.get(position).setContent(endTime);
                                            break;
                                    }
                                }
                                timeSpan = DateUtils.getInstance().getTimeData(startTimes, endTimes, startTime, endTime, timeCaculate);
                                setTimeSpan(list, timeSpan);
                                checkBaseAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void getMinuteTime(String minute) {

                            }
                        });
                        break;
                    case "用款方式":
                        final ArrayList<String> listType = new ArrayList<>();
                        listType.add("现金");
                        listType.add("承兑");
                        listType.add("其他");
                        DialogUtils.getInstance().getBaseDialog(getContext(), listType, new StringPositionCallBack() {
                            @Override
                            public void onStringPosition(String str, int positionType) {
                                list.get(position).setContent(str);
                                checkBaseAdapter.notifyDataSetChanged();
                            }
                        });
                        break;
                    case "请假类型":
                        DialogUtils.getInstance().getBaseDialog(getContext(), holidayList, new StringPositionCallBack() {
                            @Override
                            public void onStringPosition(String str, int positionType) {
                                list.get(position).setContent(str);
                                checkBaseAdapter.notifyDataSetChanged();
                            }
                        });
                        break;
                    case "款项类型":
                        DialogUtils.getInstance().getBaseDialog(getContext(), kxList, new StringPositionCallBack() {
                            @Override
                            public void onStringPosition(String str, int positionType) {
                                list.get(position).setContent(str);
                                checkBaseAdapter.notifyDataSetChanged();
                            }
                        });
                        break;
                    case "时间段":
                        DialogUtils.getInstance().getBaseDialog(getContext(), dayList, new StringPositionCallBack() {
                            @Override
                            public void onStringPosition(String str, int positionType) {
                                switch (list.get(position).getHintType()) {
                                    case "开始时间段":
                                        startTimes = str;
                                        long timeCaculate1 = DateUtils.getInstance().getTimeCaculate(startTime, endTime);
                                        if ((timeCaculate1 == 0 && startTimes.equals("下午") && endTimes.equals("上午"))) {
                                            getErrorDialog();
                                            startTimes = "";
                                            return;
                                        }
                                        list.get(position).setContent(startTimes);
                                        break;
                                    case "结束时间段":
                                        endTimes = str;
                                        long timeCaculate2 = DateUtils.getInstance().getTimeCaculate(startTime, endTime);
                                        if ((timeCaculate2 == 0 && startTimes.equals("下午") && endTimes.equals("上午"))) {
                                            getErrorDialog();
                                            endTimes = "";
                                            return;
                                        }
                                        list.get(position).setContent(endTimes);
                                        break;
                                }
                                timeSpan = DateUtils.getInstance().getTimeData(startTimes, endTimes, startTime, endTime, timeCaculate);
                                setTimeSpan(list, timeSpan);
                                checkBaseAdapter.notifyDataSetChanged();
                            }
                        });
                        break;
                    case "账户":
                        accountPosition = position;
                        switch (list.get(position).getHintType()){
                            case "收款公司":
                                Intent intentSkCompany = new Intent(getContext(), DkCustomerActivity.class);
                                intentSkCompany.putExtra(IntentUtils.getInstance().TYPE, IntentUtils.getInstance().SK_COMPANY);
                                intentSkCompany.putExtra(IntentUtils.getInstance().VALUE, list.get(position).getHintType());
                                startActivityForResult(intentSkCompany, BANK_ACCOUNT);
                                break;
                            default:
                                Intent intentCkAccount = new Intent(getContext(), DkCustomerActivity.class);
                                intentCkAccount.putExtra(IntentUtils.getInstance().TYPE, list.get(position).getAccountKey());
                                intentCkAccount.putExtra(IntentUtils.getInstance().VALUE, list.get(position).getHintType());
                                startActivityForResult(intentCkAccount, BANK_ACCOUNT);
                                break;
                        }
                        break;
                    case "类型":
                        customerList.clear();
                        switch (list.get(position).getHintType()) {
                            case "客户类型":
                                customerList.add("贸易商");
                                customerList.add("中小型");
                                break;
                            case "账户类型":
                                customerList.add("公账");
                                customerList.add("私账");
                                break;
                            case "收款方式":
                            case "出款方式":
                            case "打款方式":
                                customerList.addAll(listTradeType);
                                break;
                            case "出款性质":
                            case "平台贷款性质":
                                customerList.addAll(listPinTai);
                                break;
                            case "供应商性质":
                            case "贸易商性质":
                                customerList.addAll(listGysXz);
                                break;
                            case "客户货款性质":
                                customerList.addAll(listCustomer);
                                break;
                            case "是否打出":
                                customerList.add("打出");
                                customerList.add("不打出");
                                break;
                            case "款项类型":
                                customerList.addAll(listKxType);
                                break;
                            case "审批类型":
                                customerList.addAll(listOtherType);
                                break;
                        }
                        DialogUtils.getInstance().getBaseDialog(getContext(), customerList, new StringPositionCallBack() {
                            @Override
                            public void onStringPosition(String str, int positionType) {
                                list.get(position).setContent(str);
                                if (list.get(position).getHintType().equals("审批类型")) {
                                    if (str.equals("设备更换")) {
                                        list.get(position + 1).setHide(true);
                                        list.get(position + 2).setHide(true);
                                        list.get(position + 3).setHide(true);
                                        list.get(position + 4).setHide(true);
                                    } else {
                                        list.get(position + 1).setHide(false);
                                        list.get(position + 2).setHide(false);
                                        list.get(position + 3).setHide(false);
                                        list.get(position + 4).setHide(false);
                                    }
                                    checkBaseAdapter.notifyDataSetChanged();
                                }
                                if (!(list.get(position).getHintType().equals("客户类型")
                                        || list.get(position).getHintType().equals("账户类型")
                                        || list.get(position).getHintType().equals("客户货款性质"))) {
                                    checkBaseAdapter.notifyDataSetChanged();
                                    return;
                                }

                                if (str.equals("贸易商") || str.equals("私账") || str.equals("垫付")) {
                                    list.get(position + 1).setHide(false);
                                } else {
                                    list.get(position + 1).setHide(true);
                                }
                                checkBaseAdapter.notifyDataSetChanged();

                            }
                        });

                        break;
                    case "客服":
                        accountPosition = position;
                        switch (list.get(position).getHintType()) {
                            case "推送所属客服":
                                isChooseKf = true;
                                IntentUtils.getInstance().toYwActivity(getContext(), GroupManActivity.class, "", "抄送客服", "kfMember", "", "CustomerServiceStaff");
                                break;
                            case "运营中心人员":
                            case "运营人员":
                                isChooseKf = false;
                                IntentUtils.getInstance().toYwActivity(getContext(), GroupManActivity.class, "", "运营中心人员", "kfMember", "", "Salesman");
                                break;
                        }
                        break;
                }

            }
        });

        //图片显示封装
        imageAddAdapter = new ImageAddAdapter(this, imgList, "");
        ImageAddFunction.addImageFun(imageAddAdapter, imgList, this, scrollGridView);
        checkImgAdapter = new CheckImgAdapter(this, checkImgBeenList);
        //审批人
        recyclerViewCheck.setAdapter(checkImgAdapter);
        recyclerViewCheck.setLayoutManager(getHorLayoutManager());
        checkImgAdapter.setDeleteImg(new LanImageShowAdapter.deleteClick() {
            @Override
            public void deleteImgClick(int position) {
                approveUserId = "";
                checkImgBeenList.remove(position);
                checkImgAdapter.notifyDataSetChanged();
            }
        });
        checkImgAdapter.setChangePositionCallBack(new ChooseDialogPositionCallBack() {
            @Override
            public void onGetMessagePosition(String message, int position) {
                Intent intent = new Intent(BaseCheckActivity.this, ChoosCheckManActivity.class);
                intent.putExtra(IntentUtils.getInstance().TITLE, "审批人");
                startActivityForResult(intent, REQUEST_CODE);
            }
        });


        //抄送人
        checkCsAdapter = new CheckImgAdapter(this, checkList);
        recyclerViewCs.setAdapter(checkCsAdapter);
        recyclerViewCs.setLayoutManager(getHorLayoutManager());
        checkCsAdapter.setDeleteImg(new LanImageShowAdapter.deleteClick() {
            @Override
            public void deleteImgClick(int position) {
                checkList.remove(position);
                checkCsAdapter.notifyDataSetChanged();
            }
        });
        checkCsAdapter.setChangePositionCallBack(new ChooseDialogPositionCallBack() {
            @Override
            public void onGetMessagePosition(String message, int position) {
                csChangePosition = position;
                Intent intent2 = new Intent(BaseCheckActivity.this, ChoosCheckManActivity.class);
                intent2.putExtra(IntentUtils.getInstance().TITLE, "抄送人");
                startActivityForResult(intent2, 3);
            }
        });

        getType();

        if (approveDetailListBean != null) {
            initDetailData();
        }
    }

    private StaggeredGridLayoutManager getLayoutManager() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);
        return layoutManager;
    }

    private StaggeredGridLayoutManager getHorLayoutManager() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL);
        return layoutManager;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_right:
                //                IntentUtils.getInstance().toActivity(this, BaseCheckDetailActivity.class, "1011", "567");
                for (BaseCheckBean bean : list) {
//                    if(bean.getInterfaceKey().equals("Time")){
//                        bean.setHide(true);
//                        bean.setContent(DateUtils.getInstance().getCurrentTime());
//                    }
                    if (!StringUtils.isNoEmpty(bean.getContent()) && bean.isMust() && !bean.isHide()) {
                        switch (bean.getType()) {
                            case 2:
                                ToastUtils.getMineToast("请输入" + bean.getHintType());
                                break;
                            case 3:
                                ToastUtils.getMineToast("请选择" + bean.getHintType());
                                break;
                        }
                        return;
                    }
                    if (bean.getEditType() == 1 && StringUtils.isNoEmpty(bean.getContent()) && (bean.getContent().indexOf(".") != -1 && (bean.getContent().substring(bean.getContent().indexOf("."), bean.getContent().length()).length() > 3) || bean.getContent().length() > 15)) {
                        ToastUtils.getMineToast("请输入正确" + bean.getHintType());
                        return;
                    }
                }
                remak = etRemark.getText().toString().trim();

                if (checkImgBeenList.size() >= 1) {
                    approveUserId = checkImgBeenList.get(0).getId();
                }
                if (isNeedCheckMan && !StringUtils.isNoEmpty(approveUserId) && isNeedApproce) {
                    switch (typeId) {
//                        case 1301 + "":
//                            ToastUtils.getMineToast("请选所属客服");
//                            break;
                        default:
                            ToastUtils.getMineToast("请选择审批人");
                            break;
                    }
                    return;
                }
                csId = "";
                for (CheckImgBean checkImgBean : checkList) {
                    if (checkList.size() > 1) {
                        csId += checkImgBean.getId() + ";";
                    } else {
                        csId += checkImgBean.getId();
                    }
                }

                CopyIosDialogUtils.getInstance().getIosDialog(this, getString(R.string.is_commit_apply), new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        PhotoUtils.getInstance().mutilLocalImageUp(imgList, BaseCheckActivity.this, new StringCallBack() {
                            @Override
                            public void onSuccess(String str) {
                                pics = str;
                                BaseCheckFunction.getInstance().commitData(BaseCheckActivity.this, list, typeId, remak, pics, approveUserId, csId, bankId, editType, approveOrderId);
                            }

                            @Override
                            public void onFail() {

                            }
                        });
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
                break;
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.iv_apply_camera:
                if (imgList.size() >= 9) {
                    ToastUtils.getMineToast("最多选择9张图片");
                    return;
                }
                int nums = imgNum - imgList.size();
                PhotoUtils.getInstance().getMoreLocalPhoto(this, nums, new PhotoCallBack() {
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
            case R.id.rl_apply_check:
                Intent intent = new Intent(this, ChoosCheckManActivity.class);
                intent.putExtra(IntentUtils.getInstance().TITLE, "审批人");
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.rl_cs_man:
                if (checkList.size() >= 3) {
                    ToastUtils.getMineToast("最多选择3个抄送人");
                    return;
                }
                csChangePosition = -1;
                Intent intent2 = new Intent(this, ChoosCheckManActivity.class);
                intent2.putExtra(IntentUtils.getInstance().TITLE, "抄送人");
                startActivityForResult(intent2, 3);
                break;
            case R.id.tv_choose_bank:
                Intent intentBank = new Intent(this, ApproveBankActivity.class);
                startActivityForResult(intentBank, REQUEST_CODE_BANK);
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
//            if (isHave(approveUserId)) {
//                ToastUtils.getMineToast("审批人已选择");
//                return;
//            }
            approveUserId = data.getStringExtra("id");
            //            tvCheckMan.setText(data.getStringExtra("name"));
            String approveId = data.getStringExtra("id");
            String name = data.getStringExtra("name");
            String img = data.getStringExtra("img");
            CheckImgBean checkImgBean = new CheckImgBean();
            checkImgBean.setName(name);
            checkImgBean.setImg(img);
            checkImgBean.setId(approveId);
            checkImgBeenList.clear();
            checkImgBeenList.add(checkImgBean);
            checkImgAdapter.notifyDataSetChanged();
        } else if (requestCode == REQUEST_CODE_BANK) {
            ApproveBankListBean bankListBean = (ApproveBankListBean) data.getSerializableExtra("bean");
            bankId = bankListBean.getID() + "";
            etRemark.setText("公司名称：" + bankListBean.getCompany() + "\n"
                    + "开户银行：" + bankListBean.getBankName() + "\n"
                    + "银行账号：" + bankListBean.getBankAccount());
        } else if (requestCode == 3) {
            csMan(data);
        } else if (requestCode == BANK_ACCOUNT) {
            list.get(accountPosition).setContent(data.getStringExtra(IntentUtils.getInstance().TYPE));
            checkBaseAdapter.notifyDataSetChanged();
        }
    }

    //判断是否有改派
    public boolean isHave(String id) {
        for (CheckImgBean checkBean : checkList) {
            if (id.equals(checkBean.getId())) {
                return true;
            }
        }
        return false;
    }

    public void csMan(Intent data) {
        String approveIds = data.getStringExtra("id");
        if (approveIds.equals(approveUserId)) {
            ToastUtils.getMineToast("审批人不能与抄送人相同");
            return;
        }
        if (isHave(approveIds)) {
            ToastUtils.getMineToast("抄送人不能重复");
            return;
        }
        String name = data.getStringExtra("name");
        String img = data.getStringExtra("img");
        if (csChangePosition == -1) {
            CheckImgBean checkImgBean = new CheckImgBean();
            checkImgBean.setName(name);
            checkImgBean.setImg(img);
            checkImgBean.setId(approveIds);
            checkList.add(checkImgBean);
        } else {
            checkList.get(csChangePosition).setId(approveIds);
            checkList.get(csChangePosition).setImg(img);
            checkList.get(csChangePosition).setName(name);
        }
        checkCsAdapter.notifyDataSetChanged();
    }

    public void initFootView(View view) {
        rlCheck = view.findViewById(R.id.rl_apply_check);
        rlCsMan = view.findViewById(R.id.rl_cs_man);
        imgApply = view.findViewById(R.id.iv_apply_camera);
        scrollGridView = view.findViewById(R.id.grid_view_apply);
        recyclerViewCheck = view.findViewById(R.id.recyc_check_man);
        recyclerViewCs = view.findViewById(R.id.recyc_check);
        etRemark = view.findViewById(R.id.et_remark);
        tvChooseCheck = view.findViewById(R.id.tv_choose_bank);
        rlChooseCheck = view.findViewById(R.id.rl_choose_account);
        llCheck = view.findViewById(R.id.ll_check);
        llImg = view.findViewById(R.id.ll_img);
        tvIntro = view.findViewById(R.id.tv_money_detail);
        tvChooseBank = view.findViewById(R.id.tv_choose_bank);

        rlCheck.setOnClickListener(this);
        rlCsMan.setOnClickListener(this);
        imgApply.setOnClickListener(this);
        tvChooseCheck.setOnClickListener(this);
        llBack.setOnClickListener(this);
        tvRight.setOnClickListener(this);

        //时间段
        dayList.add("上午");
        dayList.add("下午");
        //请假类型
        holidayList.add("事假");
        holidayList.add("病假");
        holidayList.add("年假");
        holidayList.add("调休");

        listOtherType.add("设备更换");
        listOtherType.add("其他");

        getKeyData("业务用款_汇收款方式", listTradeType);
        getKeyData("业务用款_客户_货款性质", listCustomer);
        getKeyData("业务用款_平台_货款性质", listPinTai);
        getKeyData("业务用款_供应商性质", listGysXz);
        getKxType();
    }

    //错误信息提示框
    private void getErrorDialog() {
        CopyIosDialogUtils.getInstance().getErrorDialog(getContext(), "结束时间不能大于开始时间", new CopyIosDialogUtils.ErrDialogCallBack() {
            @Override
            public void confirm() {

            }
        });
    }

    //设置时间段
    public void setTimeSpan(List<BaseCheckBean> list, String timeSpan) {
        for (BaseCheckBean bean : list) {
            if (StringUtils.getNoEmptyStr(bean.getHintType()).contains("时长")) {
                bean.setContent(timeSpan);
                break;
            }
        }
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
                        kxList.add(reObj.get(i).getMoneyNature());
                    }
                }

            }

            @Override
            public void onFail() {

            }
        });
    }

    //业务字典
    public void getKeyData(final String key, final List<String> list) {
        Map<String, String> map = new HashMap();
        map.put("Key", key);
        String json = "{\n" +
                "\"model\":" + JSON.toJSONString(map) + "}";
        OkhttpUtil.getInstance().basePostCall(this, json, UrlConstant.getInstance().POST_COMMON_CHECK + "QueryDictionaryApi", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(key + "  " + str);
                try {
                    KeyValueBean valueBean = JSON.parseObject(str, KeyValueBean.class);
                    if (valueBean == null || valueBean.getD() == null) {
                        return;
                    }
                    List<KeyValueBean.DBean.ReObjBean> reObj = valueBean.getD().getReObj();
                    for (int i = 0; i < reObj.size(); i++) {
                        list.add(reObj.get(i).getName());
                    }
                }catch (Exception e){

                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    public void getKxType() {
        OkhttpUtil.getInstance().basePostCall(this, "", UrlConstant.getInstance().IN_DOOR_MONEY + "GetNatureOfCurrentAccount", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                KxTypeBean typeBean = JSON.parseObject(str, KxTypeBean.class);
                List<KxTypeBean.DBean.ReObjBean> reObj = typeBean.getD().getReObj();
                if (reObj != null) {
                    for (int i = 0; i < reObj.size(); i++) {
                        listKxType.add(reObj.get(i).getMoneyNature());
                    }
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
                approveUserId = split[1];
                list.get(accountPosition).setContent(split[0]);
            } else {
                list.get(accountPosition).setContent(split[0]);
            }
            checkBaseAdapter.notifyDataSetChanged();
            SharedPreferencesUtils.getInstance().put(this, SharedKeyConstant.getInstance().KF_MEMBER, "");
        }
    }

    //获取申请详情订单信息
    public void getApplyDetail() {
        Map<String, Object> map = new HashMap<>();
        map.put("InfoType", 1);
        map.put("ApprovalID", approveOrderId);
        map.put("BorrowId", approveOrderId);
        map.put("ApprovalTypeID", id);
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
                switch (id) {
                    case 1011 + "":
                        BaseCheckFunction.getInstance().getBtDetailList(list, detailBean.getD().getReObj().getIousPayApproval(), checkBaseAdapter);
                        break;
                    case 1301 + "":
                    case 1201 + "":
                        BaseCheckFunction.getInstance().getYwDetailList(list, detailBean.getD().getReObj(), checkBaseAdapter);
                        break;
                }
                etRemark.setText(detailBean.getD().getReObj().getRemark());
//                if (!StringUtils.isNoEmpty(detailBean.getD().getReObj().getPics())) {
//                    return;
//                }
//                //图片
//                String[] split = detailBean.getD().getReObj().getPics().split(",");
//                int num = split.length;
//                for (int i = 0; i < num; i++) {
//                    LogUtils.i(split[i]);
//                    imgList.add(split[i]);
//                }
//                ImageAddAdapter imageAddAdapter = new ImageAddAdapter(BaseCheckActivity.this, imgList, PhotoConstant.getInstance().IS_NO_DELETE);
//                scrollGridView.setAdapter(imageAddAdapter);
            }

            @Override
            public void onFail() {

            }
        });
    }

    public void initDetailData() {
        if (tvTitle == null) {
            return;
        }
        list.clear();
        list.addAll(BaseCheckFunction.getInstance().getDetailList(BaseCheckFunction.getInstance().getListType(approveDetailListBean.getApprovalTypeID(), this, tvTitle, StringUtils.getNoNullDeStr(name), peopleType, ""), approveDetailListBean));
        list.add(new BaseCheckBean());
        checkBaseAdapter.notifyDataSetChanged();
        //时长赋值
        if (approveDetailListBean.getApprovalTypeID() == 3 || approveDetailListBean.getApprovalTypeID() == 4) {
            for (int i = 0; i < list.size(); i++) {
                if(!StringUtils.isNoEmpty(list.get(i).getHintType())){
                    break;
                }
                switch (list.get(i).getHintType()) {
                    case "开始日期":
                        startTime = list.get(i).getContent();
                        break;
                    case "开始时间段":
                        startTimes = list.get(i).getContent();
                        break;
                    case "结束日期":
                        endTime = list.get(i).getContent();
                        break;
                    case "结束时间段":
                        endTimes = list.get(i).getContent();
                        break;
                }
            }
            timeSpan = DateUtils.getInstance().getTimeData(startTimes, endTimes, startTime, endTime, timeCaculate);
            setTimeSpan(list, timeSpan);
        }

//        approvalMsgList = (ArrayList<ApproveListBean>) approveDetailListBean.getApprovalMsgList();
        approvalMsgList.clear();
        List<ApproveListBean> approvalMsgLists = approveDetailListBean.getApprovalOfMsgs();
        if (approvalMsgLists != null) {
            approvalMsgList.addAll(approvalMsgLists);
        }
        etRemark.setText(StringUtils.getNoEmptyStr(approveDetailListBean.getRemark()));

//        if (!StringUtils.isNoEmpty(approveDetailListBean.getPics())) {
//            return;
//        }
//        //图片
//        String[] split = approveDetailListBean.getPics().split(",");
//        int num = split.length;
//        for (int i = 0; i < num; i++) {
//            LogUtils.i(split[i]);
//            imgList.add(split[i]);
//        }
//        ImageAddAdapter imageAddAdapter = new ImageAddAdapter(this, imgList, PhotoConstant.getInstance().IS_NO_DELETE);
//        scrollGridView.setAdapter(imageAddAdapter);
    }

    //隐藏键盘
    protected void hideInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View v = getWindow().peekDecorView();
        if (null != v) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
}
