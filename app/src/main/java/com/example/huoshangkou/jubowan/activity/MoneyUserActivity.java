package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.ApproveFunction;
import com.example.huoshangkou.jubowan.activity.function.ImageAddFunction;
import com.example.huoshangkou.jubowan.adapter.CheckImgAdapter;
import com.example.huoshangkou.jubowan.adapter.ImageAddAdapter;
import com.example.huoshangkou.jubowan.adapter.LanImageShowAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.base.HideBaseActivity;
import com.example.huoshangkou.jubowan.bean.ApproveBankListBean;
import com.example.huoshangkou.jubowan.bean.CheckImgBean;
import com.example.huoshangkou.jubowan.constant.ApproveConstant;
import com.example.huoshangkou.jubowan.inter.ChooseDialogPositionCallBack;
import com.example.huoshangkou.jubowan.inter.OnApplyCommitCallBack;
import com.example.huoshangkou.jubowan.inter.OnTimeCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.inter.StringPositionCallBack;
import com.example.huoshangkou.jubowan.photo.PhotoCallBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.DialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.PickDialogUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.TimeDialogUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollGridView;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan
 * 类名：MoneyUserActivity
 * 描述：用款申请
 * 创建时间：2017-03-17  08:45
 */

public class MoneyUserActivity extends HideBaseActivity {
    private final int REQUEST_CODE = 1;
    private final int REQUEST_CODE_BANK = 2;

    @Bind(R.id.tv_apply_check)
    TextView tvCheckMan;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.grid_view_apply)
    ScrollGridView scrollGridView;

    @Bind(R.id.tv_title)
    TextView tvTitle;
    //使用日期
    @Bind(R.id.tv_use_time)
    TextView tvUseTime;
    //用款明细
    @Bind(R.id.et_remark)
    EditText etRemark;

    //图片
    List<String> imgList;
    //图片适配器
    ImageAddAdapter imageAddAdapter;
    //类型
    @Bind(R.id.et_type)
    EditText etType;
    //金额
    @Bind(R.id.et_price)
    EditText etPrice;
    //地址
    @Bind(R.id.et_address)
    EditText etAddress;
    //类型
    @Bind(R.id.rl_type)
    RelativeLayout rlType;
    //用款明细
    @Bind(R.id.tv_money_detail)
    TextView tvMoneyDetail;
    @Bind(R.id.tv_fund_way)
    TextView tvFundWay;
    @Bind(R.id.rl_fund_way)
    RelativeLayout rlFundWay;
    @Bind(R.id.rl_use_time)
    RelativeLayout rlUseTime;


    //用款类型
    private String moneyType = "";
    //用款金额
    private String moneyPrice = "";
    //使用日期
    private String time = "";
    //使用地点
    private String area = "";
    //是否有发票   0没有发票 1有发票  默认没有发票
    private String invoince = "0";
    //是否更换设备
    private String isChangeDevice = "0";
    //用款明细
    private String remak = "";
    //审批人
    private String approveId = "";

    //用款类型
    @Bind(R.id.tv_type_money)
    TextView tvMoneyType;
    //金额类型
    @Bind(R.id.tv_price_type)
    TextView tvPriceType;

    //是否需要发票
    @Bind(R.id.cb_invoice)
    CheckBox cbInvoice;
    @Bind(R.id.rl_choose_account)
    RelativeLayout rlChooseAccount;

    @Bind(R.id.recyc_check)
    RecyclerView recyclerView;
    @Bind(R.id.recyc_check_man)
    RecyclerView recyclerViewMan;
    @Bind(R.id.ll_choose)
    LinearLayout llChoose;
    @Bind(R.id.rl_money_use)
    RelativeLayout rlMoneyUse;
    @Bind(R.id.rl_address)
    RelativeLayout rlAddress;
    @Bind(R.id.rl_invoince)
    RelativeLayout rlInvoince;
    @Bind(R.id.tv_choose_bank)
    TextView tvChooseBank;

    //申请类型  真的到
    private String applyType = "";
    private int imgNum = 9;

    //是否是用款申请 1 报销  2用款  3承兑
    private int isMoneyUse;
    private String type;
    private String pics = "";
    private String timeSpan = "";
    private String fundWay = "";
    private String bankId = "";
    private String csId = "";
    //是否是更换设备
    private boolean isDevice = false;

    //审批人
    CheckImgAdapter checkAdapter;
    //抄送人
    CheckImgAdapter checkImgAdapter;

    List<CheckImgBean> checkImgBeenList = new ArrayList<>();
    List<CheckImgBean> checkList = new ArrayList<>();
    List<String> listChoose = new ArrayList<>();

    @Override
    public int initLayout() {
        return R.layout.activity_money_use;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
        tvRight.setText("提交");

        imgList = new ArrayList<>();

        applyType = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        //报销
        if (applyType.equals(ApproveConstant.getInstance().BX_MONEY)) {
            tvMoneyType.setText("报销类型");
            tvTitle.setText("报销");
            tvPriceType.setText("报销金额(元)");
            etRemark.setHint("请输入报销明细");
            isMoneyUse = 1;
            type = ApproveConstant.getInstance().FEED_BX + "";
            tvMoneyDetail.setText("报销明细");
            //其他
        } else if (applyType.equals(ApproveConstant.getInstance().OTHER)) {
            tvMoneyType.setText("审批类型");
            tvTitle.setText("其他审批");
            tvPriceType.setText("审批金额(元)");
            etRemark.setHint("请输入报销明细");
            isMoneyUse = 1100;
            type = ApproveConstant.getInstance().OTHER_APPLY + "";
            rlChooseAccount.setVisibility(View.GONE);
            llChoose.setVisibility(View.VISIBLE);
            listChoose.add("设备更换");
            listChoose.add("其他审批");

        } else if (applyType.equals(ApproveConstant.getInstance().USE_MONEY)) {
            tvMoneyType.setText("用款类型");
            tvTitle.setText("用款");
            tvPriceType.setText("用款金额(元)");
            etRemark.setHint("请输入用款明细");
            isMoneyUse = 2;
            type = ApproveConstant.getInstance().MONEY_USE + "";
            tvMoneyDetail.setText("用款明细");
            rlFundWay.setVisibility(View.VISIBLE);
            //承兑
        } else if (applyType.equals(ApproveConstant.getInstance().CD_MONEY)) {
            tvMoneyType.setText("承兑类型");
            tvTitle.setText("承兑");
            tvPriceType.setText("承兑份数");
            etRemark.setHint("请输入承兑明细");
            isMoneyUse = 3;
            rlType.setVisibility(View.GONE);
            tvChooseBank.setVisibility(View.GONE);
            tvMoneyDetail.setText("承兑明细");
            type = ApproveConstant.getInstance().CD_TEAM + "";
        }
        //图片显示封装
        imageAddAdapter = new ImageAddAdapter(this, imgList, "");
        ImageAddFunction.addImageFun(imageAddAdapter, imgList, this, scrollGridView);
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
        checkAdapter = new CheckImgAdapter(this, checkList);
        recyclerViewMan.setAdapter(checkAdapter);
        recyclerViewMan.setLayoutManager(getLayoutManager());
        checkAdapter.setDeleteImg(new LanImageShowAdapter.deleteClick() {
            @Override
            public void deleteImgClick(int position) {
                checkList.remove(position);
                checkAdapter.notifyDataSetChanged();
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
//        cbChangeDevice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b) {
//                    isChangeDevice = "1";
//                    //更换设备
//                    type = 1102 + "";
//                } else {
//                    isChangeDevice = "0";
//                    type = 1100 + "";
//                }
//            }
//        });
    }

    private StaggeredGridLayoutManager getLayoutManager() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL);
        return layoutManager;
    }

    //点击事件
    @OnClick({R.id.tv_right, R.id.ll_back, R.id.iv_apply_camera,
            R.id.rl_apply_check, R.id.rl_use_time, R.id.tv_choose_bank,
            R.id.rl_fund_way, R.id.tv_csr, R.id.ll_choose})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_choose:
                DialogUtils.getInstance().getBaseDialog(this, listChoose, new StringPositionCallBack() {
                    @Override
                    public void onStringPosition(String str, int position) {
                        switch (str) {
                            case "设备更换":
                                rlMoneyUse.setVisibility(View.GONE);
                                rlAddress.setVisibility(View.GONE);
                                rlChooseAccount.setVisibility(View.GONE);
                                rlInvoince.setVisibility(View.GONE);
                                rlUseTime.setVisibility(View.GONE);
                                isDevice = true;
                                etType.setText(str);
                                etType.setEnabled(false);
                                break;
                            case "其他审批":
                                rlMoneyUse.setVisibility(View.VISIBLE);
                                rlAddress.setVisibility(View.VISIBLE);
                                rlChooseAccount.setVisibility(View.VISIBLE);
                                rlInvoince.setVisibility(View.VISIBLE);
                                rlUseTime.setVisibility(View.VISIBLE);
                                isDevice = false;
                                etType.setText("");
                                etType.setEnabled(true);
                                break;
                        }
                    }
                });
                break;
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_right:
                moneyType = etType.getText().toString().trim();
                if (!StringUtils.isNoEmpty(moneyType) && !(isMoneyUse == 3) && !isDevice) {
                    if (isMoneyUse == 1) {
                        ToastUtils.getMineToast("请输入用款类型");
                    } else if (isMoneyUse == 2) {
                        ToastUtils.getMineToast("请输入报销类型");
                    } else if (isMoneyUse == 1100) {
                        ToastUtils.getMineToast("请输入审批类型");
                    }
                    return;
                }
                moneyPrice = etPrice.getText().toString().trim();

                if (!StringUtils.isNoEmpty(moneyPrice) && !isDevice) {
                    if (isMoneyUse == 1) {
                        ToastUtils.getMineToast("请输入用款金额");
                    } else if (isMoneyUse == 2) {
                        ToastUtils.getMineToast("请输入报销金额");
                    } else if (isMoneyUse == 3) {
                        ToastUtils.getMineToast("请输入承兑份数");
                    } else if (isMoneyUse == 1100 && !isDevice) {
                        ToastUtils.getMineToast("请输入审批金额");
                    }
                    return;
                }
                if (!StringUtils.isNoEmpty(time) && !isDevice) {
                    ToastUtils.getMineToast("请输入使用日期");
                    return;
                }
                area = etAddress.getText().toString().trim();
                if (!StringUtils.isNoEmpty(area) && !isDevice) {
                    ToastUtils.getMineToast("请输入使用地点");
                    return;
                }
                remak = etRemark.getText().toString().trim();
                if (!StringUtils.isNoEmpty(remak)) {
                    ToastUtils.getMineToast("请输入明细");
                    return;
                }
                if (checkList.size() >= 1) {
                    approveId = checkList.get(0).getId();
                } else {
                    approveId = "";
                }
                if (!StringUtils.isNoEmpty(approveId)) {
                    ToastUtils.getMineToast("请选择审批人");
                    return;
                }
                csId = "";
                for (CheckImgBean checkImgBean : checkImgBeenList) {
                    if (checkImgBeenList.size() > 1) {
                        csId += checkImgBean.getId() + ";";
                    } else {
                        csId += checkImgBean.getId();
                    }
                }

                CopyIosDialogUtils.getInstance().getIosDialog(this, getString(R.string.is_commit_apply), new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        PhotoUtils.getInstance().mutilLocalImageUp(imgList, MoneyUserActivity.this, new StringCallBack() {
                            @Override
                            public void onSuccess(String str) {
                                pics = str;
                                ApproveFunction.getInstance().applyToApprove(getContext(), type, moneyType, moneyPrice,
                                        "", "", "", "", area, invoince, remak, pics, approveId, timeSpan, fundWay, bankId,
                                        csId, time, isChangeDevice, new OnApplyCommitCallBack() {
                                            @Override
                                            public void onSuccess() {
                                                MoneyUserActivity.this.finish();
                                            }

                                            @Override
                                            public void onFail() {
                                            }
                                        });
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
            case R.id.rl_fund_way:
                ArrayList<String> list = new ArrayList<>();
                list.add("现金");
                list.add("承兑");
                list.add("其他");
                PickDialogUtils.getInstance().getChoosePositionDialog(getContext(), "请选择用款款项", list, new ChooseDialogPositionCallBack() {
                    @Override
                    public void onGetMessagePosition(String message, int position) {
                        fundWay = message;
                        tvFundWay.setText(message);
                    }
                });

                break;
            //使用日期
            case R.id.rl_use_time:
                TimeDialogUtils.getInstance().getTime(this, new OnTimeCallBack() {
                    @Override
                    public void getYearTime(String year) {
                        time = year;
                        tvUseTime.setText(year);
                    }

                    @Override
                    public void getMinuteTime(String minute) {

                    }
                });
                break;
            //选择银行信息
            case R.id.tv_choose_bank:
//                IntentUtils.getInstance().toActivity(this,ApproveBankActivity.class);
                Intent intentBank = new Intent(this, ApproveBankActivity.class);
                startActivityForResult(intentBank, REQUEST_CODE_BANK);
                break;
            case R.id.tv_csr:
                if (checkImgBeenList.size() >= 3) {
                    ToastUtils.getMineToast("最多选择3个抄送人");
                    return;
                }
                Intent intent2 = new Intent(this, ChoosCheckManActivity.class);
                intent2.putExtra(IntentUtils.getInstance().TITLE, "抄送人");
                startActivityForResult(intent2, 3);
                break;
        }
    }


    //判断是否有改人
    public boolean isHave(String id) {
        for (CheckImgBean checkBean : checkImgBeenList) {
            if (id.equals(checkBean.getId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        LogUtils.e(requestCode + "");
        if (requestCode == REQUEST_CODE) {
            if (isHave(approveId)) {
                ToastUtils.getMineToast("审批人不能与抄送人相同");
                approveId = "";
                return;
            }
            approveId = data.getStringExtra("id");
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

            LogUtils.e(approveId);

        } else if (requestCode == REQUEST_CODE_BANK) {
            ApproveBankListBean bankListBean = (ApproveBankListBean) data.getSerializableExtra("bean");
            bankId = bankListBean.getID() + "";
            etRemark.setText("公司名称：" + bankListBean.getCompany() + "\n"
                    + "开户银行：" + bankListBean.getBankName() + "\n"
                    + "银行账号：" + bankListBean.getBankAccount());
        } else if (requestCode == 3) {
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

    public void csMan(Intent data) {
        String approveIds = data.getStringExtra("id");
        LogUtils.e(approveIds + " ---- " + approveId);
        if (approveIds.equals(approveId)) {
            ToastUtils.getMineToast("审批人不能与抄送人相同");
            return;
        }
        if (isHave(approveIds)) {
            ToastUtils.getMineToast("抄送人不能重复");
            return;
        }
        String name = data.getStringExtra("name");
        String img = data.getStringExtra("img");
        CheckImgBean checkImgBean = new CheckImgBean();
        checkImgBean.setName(name);
        checkImgBean.setImg(img);
        checkImgBean.setId(approveIds);
        checkImgBeenList.add(checkImgBean);
        checkImgAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
