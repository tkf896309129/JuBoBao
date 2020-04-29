package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.ApproveAgreeAdapter;
import com.example.huoshangkou.jubowan.adapter.DianTabAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ApproveBankListBean;
import com.example.huoshangkou.jubowan.bean.ApproveDetailBean;
import com.example.huoshangkou.jubowan.bean.ApproveListBean;
import com.example.huoshangkou.jubowan.bean.DianTabBean;
import com.example.huoshangkou.jubowan.bean.OutBankBean;
import com.example.huoshangkou.jubowan.bean.OutBankListBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.bean.SyYuanBean;
import com.example.huoshangkou.jubowan.bean.SyYuanListBean;
import com.example.huoshangkou.jubowan.constant.ApproveConstant;
import com.example.huoshangkou.jubowan.constant.Constant;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.ChooseDialogCallBack;
import com.example.huoshangkou.jubowan.inter.ConfirmCallBack;
import com.example.huoshangkou.jubowan.inter.OnStringPositionCallBack;
import com.example.huoshangkou.jubowan.inter.OnTimeCallBack;
import com.example.huoshangkou.jubowan.inter.PickPositonCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.DoubleUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.KeyBoardUtils;
import com.example.huoshangkou.jubowan.utils.LoanAdviceDialogUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.MoneyUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.PickDialogUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.TimeDialogUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：PadPaymentActivity
 * 描述：
 * 创建时间：2017-10-31  09:52
 */

public class PadPaymentActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_apply_time)
    TextView tvApplyTime;
    @Bind(R.id.tv_piao)
    TextView tvPiao;
    @Bind(R.id.tv_dianfu)
    TextView tvDianfu;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.et_remark)
    EditText etRemark;
    @Bind(R.id.rl_sale)
    RelativeLayout rlSale;
    @Bind(R.id.ll_sale)
    LinearLayout llSale;
    //是否开票
    ArrayList<String> listPiao;
    //垫付情况
    ArrayList<String> listDianFu;
    //打款方向
    ArrayList<String> listDkFx;
    ArrayList<String> bankList;

    @Bind(R.id.tv_buy_price)
    TextView tvBuyPrice;
    @Bind(R.id.tv_dk_fx)
    TextView tvDkFx;
    @Bind(R.id.tv_ck_zh)
    TextView tvCkZh;
    @Bind(R.id.tv_sk_zh)
    TextView tvSkZh;
    @Bind(R.id.et_yl)
    TextView etYl;
    @Bind(R.id.et_yfze)
    EditText etYfze;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.lv_check_result)
    ScrollListView listView;
    @Bind(R.id.ll_bottom)
    LinearLayout llBottom;
    @Bind(R.id.et_apply_price)
    EditText etApplyPrice;
    @Bind(R.id.tv_company)
    TextView tvCompany;
    @Bind(R.id.tv_sell_price)
    TextView tvSellPrice;
    @Bind(R.id.rb_cash)
    RadioButton rbCash;
    @Bind(R.id.rb_cd)
    RadioButton rbCd;
    @Bind(R.id.rb_dc)
    RadioButton rbDc;
    @Bind(R.id.rb_bdc)
    RadioButton rbBdc;
    @Bind(R.id.rb_buy_cash)
    RadioButton rbBuyCash;
    @Bind(R.id.rb_buy_cd)
    RadioButton rbBuyCd;
    @Bind(R.id.tv_agree)
    TextView tvAgree;
    @Bind(R.id.tv_disagree)
    TextView tvDisAgree;
    @Bind(R.id.rg_sell)
    RadioGroup rgSell;
    @Bind(R.id.rg_buy)
    RadioGroup rgBuy;
    @Bind(R.id.tv_dian_times)
    TextView tvDianTimes;
    @Bind(R.id.scroll_view)
    ScrollView scrollView;
    @Bind(R.id.tv_sy_ypj)
    TextView tvSyCompany;
    @Bind(R.id.tv_sk_kx)
    TextView tvFundWay;
    @Bind(R.id.rg_sfdc)
    RadioGroup rgDaChu;
    @Bind(R.id.rl_tab_line)
    RelativeLayout rlLineTab;
    @Bind(R.id.tv_line_tab)
    TextView tvLineTab;
    @Bind(R.id.rl_check_id)
    RelativeLayout rlCheckId;

    @Bind(R.id.rl_dian_tab)
    RelativeLayout rlDianTab;
    @Bind(R.id.ll_is_dc)
    LinearLayout llIsDc;

    //垫付次数
    private String adCounts = "";
    ArrayList<ApproveListBean> approvalMsgList;
    //审批类型
    private String checkType = "";
    //身份类型  //1销售（展示部分） 2运营（完全展示并编辑） 3财务（完全展示）
    private String type = "";
    //申请表id
    private String approveId = "";
    //类型id
    private String approveTypeId = "";
    //销售用户id
    private String sellUserId = "";
    //订单id
    private String orderId = "";
    //同意审批
    private final String AGREE = "agree";
    //不同意审批
    private final String DISAGREE = "disAgree";
    //判断是否是同一
    private String isAgree = "";
    //是否可以编辑
    private boolean isEdit = false;
    //是否是垫付款编辑
    private boolean isDianEdit = false;
    String value = "";
    private String name = "";
    //加工单位
    ArrayList<SyYuanListBean> yuanList;
    private final int REQUEST_CODE_BANK = 2;
    //标题
    private String title = "";
    //记录原始申请金额
    private double oldPrice;
    //记录修改后的申请金额
    private double newPrice;
    //销售用户id
    private String sellId = "";
    //重走流程
    private String isOpen = "";
    //申请金额
    private String typePrice = "";
    //订单id
    private String adVanceId = "";
    //查看订单
    private String checkId = "";
    //销售总额
    private String SellPrice = "";
    //现金或承兑
    private String sellCashOrAccept = "";
    //申请日期
    private String StartTime = "";
    //是否开票
    private String Invoice = "";
    //垫付情况
    private String Advance = "";
    //还款时间
    private String EndTime = "";
    //备注
    private String remark = "";
    //采购总额
    private String PurchasePrice = "";
    //现金或者承兑
    private String surchaseCashOrAccept = "";
    //打款方向
    private String RemitDircetion = "";
    //出款账户
    private String RemitAccount = "";
    //收款账户
    private String ProceedsAccount = "";
    //盈利
    private String Profit = "";
    //运费总额
    private String FreightTotalPrice = "";
    //是否打出
    private String isDaChu = "";
    //上游原片厂id
    private String syId = "";
    //上游原片厂名称
    private String syCompany = "";
    //是否是销售部
    private boolean isSaleGroup = false;
    //款项用途
    private String fundWay = "";
    private String upYuanId = "";
    //审批银行id
    private String bankId = "";
    //是否需要审核
    private boolean isCheck = false;
    //表哥信息
    private String tableMessage = "";


    //    垫付款表格
    private ArrayList<String> list;
    private DianTabAdapter dianTabAdapter;
    @Bind(R.id.lv_dian)
    ScrollListView listViewDianTab;
    @Bind(R.id.tv_weight_total)
    TextView tvWeightTotal;
    @Bind(R.id.tv_thj_total)
    TextView tvThjTotal;
    @Bind(R.id.tv_cbj_total)
    TextView tvCbjTotal;
    @Bind(R.id.tv_xsj_total)
    TextView tvXsjTotal;
    @Bind(R.id.tv_yl_total)
    TextView tvYlTotal;
    @Bind(R.id.tv_change_check)
    TextView tvChangeCheck;

    private String userId = "";


    @Override
    public int initLayout() {
        return R.layout.activity_payment_layout;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        list = new ArrayList<>();
        dianTabBeanList = new ArrayList<>();
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().dianList);
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().approveCheckList);

        listPiao = new ArrayList<>();
        bankList = new ArrayList<>();
        yuanList = new ArrayList<>();
        listPiao.add("开票");
        listPiao.add("不开票");
        tvRight.setText("编辑");
        tvRight.setVisibility(View.GONE);

        listDianFu = new ArrayList<>();
        listDianFu.add("一车一结(第一车)");
        listDianFu.add("二车一结(第一车)");
        listDianFu.add("二车一结(第二车)");
        listDianFu.add("三车一结(第一车)");
        listDianFu.add("三车一结(第二车)");
        listDianFu.add("三车一结(第三车)");

        list.add("含税\n承兑");
        list.add("含税\n现金");
        list.add("不含税\n承兑");
        list.add("不含税\n现金");

        listDkFx = new ArrayList<>();
        listDkFx.add("贸易商-原片厂");
        listDkFx.add("中小客户-原片厂");
        listDkFx.add("中小客户-贸易商");
        isAgree = getIntent().getStringExtra(IntentUtils.getInstance().TXT);
        checkType = getIntent().getStringExtra(IntentUtils.getInstance().STR);
        title = getIntent().getStringExtra(IntentUtils.getInstance().TITLE);
        type = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        value = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        name = getIntent().getStringExtra(IntentUtils.getInstance().APPROVE_TYPE);
        tvTitle.setText(StringUtils.getNoNullStr(name)+"垫付款详情");
        String[] split = value.split(",");
        if (split != null && split.length == 4) {
            approveId = split[0];
            approveTypeId = split[1];
            sellUserId = split[2];
            adCounts = split[3];
            tvDianTimes.setText(adCounts);
        }
        if (StringUtils.isNoEmpty(title)) {
            isDianEdit = true;
            tvTitle.setText(title);
            tvAgree.setText("重走审批");
            tvDisAgree.setText("确认修改");
            tvRight.setVisibility(View.GONE);
        }

        tvRight.setVisibility(View.GONE);

        getYuanFactory();
        //是否是审批
        if (checkType.equals(Constant.APPROVE)) {
            //是否需要审核
            if (isAgree.equals(ApproveConstant.getInstance().UN_CHECK + "")) {
                llBottom.setVisibility(View.VISIBLE);
                isCheck = true;
            } else if (isAgree.equals(ApproveConstant.getInstance().CHECK + "")) {
                llBottom.setVisibility(View.GONE);
                isCheck = false;
                isEdit = false;
                if (type.equals("2")) {
                    tvRight.setVisibility(View.VISIBLE);
                } else {
                    tvRight.setVisibility(View.GONE);
                }
            }
        }

        getApproveDetail(approveId, approveTypeId, sellUserId);

        rgBuy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_buy_cd:
                        surchaseCashOrAccept = 1 + "";
                        break;
                    case R.id.rb_buy_cash:
                        surchaseCashOrAccept = 0 + "";
                        break;
                }
            }
        });

        rgSell.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_cd:
                        sellCashOrAccept = 1 + "";
                        break;
                    case R.id.rb_cash:
                        sellCashOrAccept = 0 + "";
                        break;
                }
            }
        });

        getOutBank();

        etYl.setText(Profit);

        rgDaChu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_dc:
                        isDaChu = "0";
                        break;
                    case R.id.rb_bdc:
                        isDaChu = "1";
                        break;
                }
            }
        });

//        Canvas canvas = new Canvas();
//        Paint paint = new Paint();
//        paint.setColor(getResources().getColor(R.color.gray));
//        canvas.drawLine(rlLineTab.getLeft(), rlLineTab.getTop(), rlLineTab.getRight(), rlLineTab.getBottom(), paint);
    }

    @OnClick({R.id.rl_ck_zh, R.id.rl_sk_zh, R.id.rl_dk_fx, R.id.rl_check_id, R.id.tv_right,
            R.id.ll_back, R.id.rl_time, R.id.rl_piao, R.id.rl_dianfu, R.id.rl_back_time,
            R.id.tv_agree, R.id.tv_disagree, R.id.rl_sy_ypj, R.id.rl_sk_kx})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_check_id:
                IntentUtils.getInstance().toActivity(getContext(), MyAllOrderAcitivty.class, detailBean.getReList().get(0).getAdvanceOrderID());
                break;
            case R.id.tv_right:
                IntentUtils.getInstance().toActivityTitle(getContext(), PadPaymentActivity.class, type, value, Constant.APPROVE, ApproveConstant.getInstance().UN_CHECK + "", "垫付款详情编辑");
//                IntentUtils.getInstance().toActivity(getContext(), PadPaymentActivity.class);
                break;
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.rl_time:
                if (!isEdit) {
                    return;
                }
                TimeDialogUtils.getInstance().getTime(getContext(), new OnTimeCallBack() {
                    @Override
                    public void getYearTime(String year) {
                        StartTime = year;
                        tvApplyTime.setText(year);
                    }

                    @Override
                    public void getMinuteTime(String minute) {

                    }
                });
                break;
            case R.id.rl_sk_kx:
                if (!isEdit) {
                    return;
                }
                KeyBoardUtils.closeKeybord(etApplyPrice, getContext());
                ArrayList<String> kxList = new ArrayList<>();
                kxList.add("货款");
                kxList.add("运费");
                kxList.add("其他");
                PickDialogUtils.getInstance().getChooseDialog(getContext(), "款项用途", kxList, new PickPositonCallBack() {
                    @Override
                    public void onClickSuccess(String choose, int position) {
                        fundWay = choose;
                        tvFundWay.setText(fundWay);
                    }
                });
                break;
            case R.id.rl_piao:
                if (!isEdit) {
                    return;
                }
                KeyBoardUtils.closeKeybord(etApplyPrice, getContext());
                PickDialogUtils.getInstance().getChooseDialog(getContext(), "是否开票", listPiao, new PickPositonCallBack() {
                    @Override
                    public void onClickSuccess(String choose, int position) {
                        if (choose.equals("开票")) {
                            Invoice = 1 + "";
                        } else if (choose.equals("不开票")) {
                            Invoice = 0 + "";
                        }
                        tvPiao.setText(choose);
                    }
                });
                break;
            case R.id.rl_dianfu:
                if (!isEdit) {
                    return;
                }
                KeyBoardUtils.closeKeybord(etApplyPrice, getContext());
                PickDialogUtils.getInstance().getChooseDialog(getContext(), "垫付情况", listDianFu, new PickPositonCallBack() {
                    @Override
                    public void onClickSuccess(String choose, int position) {
                        Advance = choose;
                        tvDianfu.setText(choose);
                    }
                });
                break;
            case R.id.rl_back_time:
                if (!isEdit) {
                    return;
                }
                TimeDialogUtils.getInstance().getTime(getContext(), new OnTimeCallBack() {
                    @Override
                    public void getYearTime(String year) {
                        EndTime = year;
                        tvTime.setText(year);
                    }

                    @Override
                    public void getMinuteTime(String minute) {

                    }
                });
                break;
            case R.id.tv_agree:
                //表格信息
                tableMessage = JSONArray.toJSONString(dianTabBeanList, new PascalNameFilter());
                if (!StringUtils.isNoEmpty(typePrice)) {
                    ToastUtils.getMineToast("请输入申请价格");
                    return;
                }
                if (!StringUtils.isNoEmpty(SellPrice)) {
                    ToastUtils.getMineToast("请输入申请价格");
                    return;
                }
                if (!StringUtils.isNoEmpty(sellCashOrAccept)) {
                    ToastUtils.getMineToast("请选择现金或承兑");
                    return;
                }
                if (!StringUtils.isNoEmpty(Advance)) {
                    ToastUtils.getMineToast("请输入垫付情况");
                    return;
                }
                if (!StringUtils.isNoEmpty(EndTime)) {
                    ToastUtils.getMineToast("请选择还款时间");
                    return;
                }
                //测试数据
//                PurchasePrice = "55";

                if (!isSaleGroup && StringUtils.isNoEmpty(PurchasePrice) && PurchasePrice.equals("0.00")) {
                    ToastUtils.getMineToast("请在后台管理输入采购价格");
                    return;
                }
                if (!isSaleGroup && !StringUtils.isNoEmpty(surchaseCashOrAccept)) {
                    ToastUtils.getMineToast("请选择现金或承兑");
                    return;
                }
                if (!isSaleGroup && !StringUtils.isNoEmpty(fundWay)) {
                    ToastUtils.getMineToast("请选择款项用途");
                    return;
                }
                if (!isSaleGroup && !StringUtils.isNoEmpty(RemitDircetion)) {
                    ToastUtils.getMineToast("请输入打款方向");
                    return;
                }
                if (!isSaleGroup && !StringUtils.isNoEmpty(RemitAccount)) {
                    ToastUtils.getMineToast("请输入打款账户");
                    return;
                }
                if (!isSaleGroup && !StringUtils.isNoEmpty(ProceedsAccount)) {
                    ToastUtils.getMineToast("请输入收款账户");
                    return;
                }
                if (!isSaleGroup && !StringUtils.isNoEmpty(syCompany)) {
                    ToastUtils.getMineToast("请选择收款单位");
                    return;
                }
                FreightTotalPrice = etYfze.getText().toString().trim();
                if (!isSaleGroup && !StringUtils.isNoEmpty(FreightTotalPrice)) {
                    ToastUtils.getMineToast("请输入运费总额");
                    return;
                }
                if (!StringUtils.isNoEmpty(isDaChu)) {
                    ToastUtils.getMineToast("请选择是否打出");
                    return;
                }
                //没有预警功能
                if (isYuJin == 0) {
                    agreeDianFu();
                    //有预警
                } else if (isYuJin == 1) {
                    LoanAdviceDialogUtils.getInstance().getBaoJinDialog(id, getContext(), new ConfirmCallBack() {
                        @Override
                        public void onConfirm() {
                            agreeDianFu();
                        }

                        @Override
                        public void onCancel() {

                        }
                    });
                }
                break;
            case R.id.tv_disagree:
                //表格信息
                tableMessage = JSONArray.toJSONString(dianTabBeanList, new PascalNameFilter());
                if (!StringUtils.isNoEmpty(typePrice)) {
                    ToastUtils.getMineToast("请输入申请价格");
                    return;
                }
                if (!StringUtils.isNoEmpty(SellPrice)) {
                    ToastUtils.getMineToast("请输入申请价格");
                    return;
                }
                if (!StringUtils.isNoEmpty(sellCashOrAccept)) {
                    ToastUtils.getMineToast("请选择现金或承兑");
                    return;
                }
                if (!StringUtils.isNoEmpty(Advance)) {
                    ToastUtils.getMineToast("请输入垫付情况");
                    return;
                }
                if (!StringUtils.isNoEmpty(EndTime)) {
                    ToastUtils.getMineToast("请选择还款时间");
                    return;
                }
                PurchasePrice = tvBuyPrice.getText().toString().trim();
                if (!isSaleGroup && StringUtils.isNoEmpty(PurchasePrice) && PurchasePrice.equals("0.00")) {
                    ToastUtils.getMineToast("请在后台管理输入采购价格");
                    return;
                }
                if (!isSaleGroup && !StringUtils.isNoEmpty(surchaseCashOrAccept)) {
                    ToastUtils.getMineToast("请选择现金或承兑");
                    return;
                }
                if (!isSaleGroup && !StringUtils.isNoEmpty(fundWay)) {
                    ToastUtils.getMineToast("请选择款项用途");
                    return;
                }
                if (!isSaleGroup && !StringUtils.isNoEmpty(RemitDircetion)) {
                    ToastUtils.getMineToast("请输入打款方向");
                    return;
                }
                if (!isSaleGroup && !StringUtils.isNoEmpty(RemitAccount)) {
                    ToastUtils.getMineToast("请输入打款账户");
                    return;
                }
                if (!isSaleGroup && !StringUtils.isNoEmpty(ProceedsAccount)) {
                    ToastUtils.getMineToast("请输入收款账户");
                    return;
                }
//                Profit = etYl.getText().toString().trim();
//                if (!StringUtils.isNoEmpty(Profit)) {
//                    ToastUtils.getMineToast("请输入盈利");
//                    return;
//                }
                if (!isSaleGroup && !StringUtils.isNoEmpty(syCompany)) {
                    ToastUtils.getMineToast("请选择收款单位");
                    return;
                }
                FreightTotalPrice = etYfze.getText().toString().trim();
                if (!isSaleGroup && !StringUtils.isNoEmpty(FreightTotalPrice)) {
                    ToastUtils.getMineToast("请输入运费总额");
                    return;
                }
                remark = etRemark.getText().toString().trim();
                if (!StringUtils.isNoEmpty(isDaChu)) {
                    ToastUtils.getMineToast("请选择是否打出");
                    return;
                }
                if (!isSaleGroup && !StringUtils.isNoEmpty(isDaChu)) {
                    ToastUtils.getMineToast("请选择是否打出");
                    return;
                }
                if (isDianEdit) {
                    newPrice = Double.parseDouble(MoneyUtils.getInstance().getNormPrice(etApplyPrice.getText().toString()));
                    double calPrice = Math.abs(newPrice - oldPrice);
                    if (calPrice >= 10000) {
                        CopyIosDialogUtils.getInstance().getErrorDialog(getContext(), "原始金额修改大于10000，请重走流程。", new CopyIosDialogUtils.ErrDialogCallBack() {
                            @Override
                            public void confirm() {

                            }
                        });
                        return;
                    }
                    editDianMessage(approveId, sellUserId, etApplyPrice.getText().toString().trim(), sellCashOrAccept,
                            StartTime, Invoice, Advance, EndTime, remark, PurchasePrice, surchaseCashOrAccept,
                            RemitDircetion, RemitAccount, ProceedsAccount, Profit, FreightTotalPrice);
                    return;
                }
                ids = "";
                csId = "";
                for (ApproveListBean listBean : approvalMsgList) {
                    if (listBean.getApprovalOver() == 3) {
                        csId += listBean.getApprovalUserID() + ",";
                    } else {
                        ids += listBean.getApprovalUserID() + ",";
                    }
                }

                if (detailBean != null && detailBean.getReList().get(0).getApprovalTypeID() == 2) {
                    isChange(DISAGREE, orderId, ids, csId);
                    return;
                }

                String contentDis = sellUserId + "、" + isOpen + "、" + typePrice + "、" + adVanceId + "、" + SellPrice + "、" + sellCashOrAccept
                        + "、" + StartTime + "、" + Invoice + "、" + Advance + "、" + EndTime + "、" + remark + "、" + PurchasePrice + "、" + surchaseCashOrAccept
                        + "、" + RemitDircetion + "、" + RemitAccount + "、" + ProceedsAccount + "、" + Profit + "、" + FreightTotalPrice + "、";
                IntentUtils.getInstance().toDianActivity(getContext(), ApproveAgreeActivity.class,
                        IntentUtils.getInstance().DISAGREE_APPROVE, approveId, approveTypeId, contentDis, syCompany, syId, fundWay, bankId, isDaChu, tableMessage, ids, csId, userId, approvalMsgList);
//                IntentUtils.getInstance().toActivity(getContext(), ApproveAgreeActivity.class,
//                        IntentUtils.getInstance().DISAGREE_APPROVE, sellUserId, approveTypeId);
                break;
            //打款方向
            case R.id.rl_dk_fx:
                if (!isEdit) {
                    return;
                }
                KeyBoardUtils.closeKeybord(etApplyPrice, getContext());
                PickDialogUtils.getInstance().getChooseDialog(getContext(), "打款方向", listDkFx, new PickPositonCallBack() {
                    @Override
                    public void onClickSuccess(String choose, int position) {
                        RemitDircetion = choose;
                        tvDkFx.setText(choose);
                    }
                });
                break;
            case R.id.rl_sk_zh:
                if (!isEdit) {
                    return;
                }
                Intent intentBank = new Intent(this, ApproveBankActivity.class);
                startActivityForResult(intentBank, REQUEST_CODE_BANK);
                break;
            case R.id.rl_ck_zh:
                if (!isEdit) {
                    return;
                }
                KeyBoardUtils.closeKeybord(etApplyPrice, getContext());
                PickDialogUtils.getInstance().getChooseDialog(getContext(), "选择出款账户", bankList, new ChooseDialogCallBack() {
                    @Override
                    public void onClickSuccess(String choose) {
                        RemitAccount = choose;
                        tvCkZh.setText(choose);
                    }
                });
                break;
            case R.id.rl_sy_ypj:
                if (!isEdit) {
                    return;
                }
                Intent intent = new Intent(getContext(), SyYuanActivity.class);
                intent.putParcelableArrayListExtra("manList", yuanList);
                startActivityForResult(intent, 1);
                break;
        }
    }

    //获取原片厂
    public void getYuanFactory() {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), "数据加载中", UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_YUAN_FACTORY, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SyYuanBean yuanBean = JSON.parseObject(json, SyYuanBean.class);
                yuanList.addAll(yuanBean.getReList());
//                int num = yuanList.size();
//                for (int i = 0; i < num; i++) {
//                    selectList.add(new SelectManBean(yuanList.get(i).getCompany(), PinYin.getPinYin(yuanList.get(i).getCompany())
//                            , yuanList.get(i).getCompany(), yuanList.get(i).getID() + "", yuanList.get(i).getCompany(), yuanList.get(i).getCompany()));
//                }
//                yuanAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail() {

            }
        });
    }

    String ids = "";
    String csId = "";

    //同意垫付款
    public void agreeDianFu() {
        remark = etRemark.getText().toString().trim();
        ids = "";
        csId = "";
        for (ApproveListBean listBean : approvalMsgList) {
            if (listBean.getApprovalOver() == 3) {
                csId += listBean.getApprovalUserID() + ",";
            } else {
                ids += listBean.getApprovalUserID() + ",";
            }
        }

        if (isDianEdit) {
            newPrice = Double.parseDouble(MoneyUtils.getInstance().getNormPrice(etApplyPrice.getText().toString().trim()));
            double calPrice = Math.abs(newPrice - oldPrice);
            //垫付款金额不能小于零
            if (calPrice <= 0) {
                CopyIosDialogUtils.getInstance().getErrorDialog(getContext(), "垫付款金额必须大于0", new CopyIosDialogUtils.ErrDialogCallBack() {
                    @Override
                    public void confirm() {

                    }
                });
                return;
            }
            //垫付款金额小于一万
            if (calPrice <= 10000) {
                CopyIosDialogUtils.getInstance().getIosDialog(getContext(), "原始金额修改不大于10000，不必重走流程，在本页面修改保存即可。如若有必要重走流程，点击确认按钮。", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {

                        isOpen = "1008";
                        String content = sellUserId + "、" + isOpen + "、" + etApplyPrice.getText().toString().trim() + "、" + adVanceId + "、" + SellPrice + "、" + sellCashOrAccept
                                + "、" + StartTime + "、" + Invoice + "、" + Advance + "、" + EndTime + "、" + remark + "、" + PurchasePrice + "、" + surchaseCashOrAccept
                                + "、" + RemitDircetion + "、" + RemitAccount + "、" + ProceedsAccount + "、" + Profit + "、" + FreightTotalPrice + "、";

                        IntentUtils.getInstance().toDianActivity(getContext(), ApproveAgreeActivity.class,
                                IntentUtils.getInstance().AGREE_APPROVE, approveId, approveTypeId, content, syCompany, syId, fundWay, bankId, isDaChu, tableMessage, ids, csId, userId,approvalMsgList);

                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
                return;
            }

            isOpen = "1008";
            String content = sellUserId + "、" + isOpen + "、" + etApplyPrice.getText().toString().trim() + "、" + adVanceId + "、" + SellPrice + "、" + sellCashOrAccept
                    + "、" + StartTime + "、" + Invoice + "、" + Advance + "、" + EndTime + "、" + remark + "、" + PurchasePrice + "、" + surchaseCashOrAccept
                    + "、" + RemitDircetion + "、" + RemitAccount + "、" + ProceedsAccount + "、" + Profit + "、" + FreightTotalPrice + "、";

            IntentUtils.getInstance().toActivity(getContext(), ApproveAgreeActivity.class,
                    IntentUtils.getInstance().AGREE_APPROVE, approveId, approveTypeId, content, syCompany, syId, fundWay, bankId, tableMessage, ids, csId, userId,approvalMsgList);
            return;
        }

        if (detailBean != null && detailBean.getReList().get(0).getApprovalTypeID() == 2) {
            isChange(AGREE, orderId, ids, csId);
            return;
        }

                /*
                    销售用户id    SellUserID ,重新走流程   IsReopen,申请金额  TypePrice,查看订单（多个订单）  AdvanceOrderID,销售总额   SellPrice
                    现金或者承兑（销售金额） SellCashOrAccept,申请日期  StartTime ,是否开票   Invoice,垫付情况  Advance,还款时间 EndTime,备注        Remark
                    采购总额 PurchasePrice,现金或者承兑（销售金额） PurchaseCashOrAccept,打款方向  RemitDircetion,出款账户  RemitAccount,收款账户  ProceedsAccount
                    盈利         Profit,运费总额   FreightTotalPrice
                 */

        String content = sellUserId + "、" + isOpen + "、" + typePrice + "、" + adVanceId + "、" + SellPrice + "、" + sellCashOrAccept
                + "、" + StartTime + "、" + Invoice + "、" + Advance + "、" + EndTime + "、" + remark + "、" + PurchasePrice + "、" + surchaseCashOrAccept
                + "、" + RemitDircetion + "、" + RemitAccount + "、" + ProceedsAccount + "、" + Profit + "、" + FreightTotalPrice + "、";

        IntentUtils.getInstance().toActivity(getContext(), ApproveAgreeActivity.class,
                IntentUtils.getInstance().AGREE_APPROVE, approveId, approveTypeId, content, syCompany, syId, fundWay, bankId, tableMessage, ids, csId, userId,approvalMsgList);
    }

    List<DianTabBean> dianTabBeanList;
    ApproveDetailBean detailBean;
    //是否有预警
    private int isYuJin = -1;
    private String id = "";

    //获取审批信息详情
    public void getApproveDetail(String approveId, String approveTypeId, String sellUserId) {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(getContext(), UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_APPROVE_DETAIL_AD
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().APPROVE_ID + "=" + approveId + "&"
                + FieldConstant.getInstance().APPROVE_TYPE_ID + "=" + approveTypeId + "&"
                + FieldConstant.getInstance().PICS + "=" + EncodeUtils.getInstance().getEncodeString(syId + "," + syCompany) + "&"
                + FieldConstant.getInstance().SELL_USER_ID + "=" + sellUserId, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                detailBean = JSON.parseObject(json, ApproveDetailBean.class);
                if (detailBean.getReList().size() == 0) {
                    return;
                }
                initDetailData(detailBean);
                String tab = detailBean.getReList().get(0).getOrderTable();
                List<DianTabBean> dianTabBeen = JSON.parseArray(tab, DianTabBean.class);
                int size = dianTabBeen.size();
                if (size > 0) {
                    dianTabBeen.get(size - 1).setTableRemark("合计");
                }

                dianTabBeanList.addAll(dianTabBeen);
//                totalTable();
                if (dianTabAdapter != null) {
                    dianTabAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    private void initDetailData(ApproveDetailBean detailBean) {
        tvApplyTime.setText(DateUtils.getInstance().getFormData(detailBean.getReList().get(0).getStartTime()));
        if (detailBean.getReList().get(0).getInvoice() == 1) {
            tvPiao.setText("开票");
        } else {
            tvPiao.setText("不开票");
        }

        approvalMsgList.addAll(detailBean.getReList().get(0).getApprovalMsgList());
        int stateId = Integer.parseInt(type);

        userId = detailBean.getReList().get(0).getUserID() + "";

        //1销售（展示部分） 2运营（完全展示并编辑） 3财务（完全展示）
        if (stateId == 1) {
            llSale.setVisibility(View.GONE);
            tvRight.setVisibility(View.GONE);
            rlDianTab.setVisibility(View.GONE);
            llIsDc.setVisibility(View.GONE);
            isSaleGroup = true;
            if (isCheck) {
                isEdit = true;
            }
        } else if (stateId == 2) {
            if (isCheck) {
                isEdit = true;
            }
            isSaleGroup = false;
            rbCash.setClickable(true);
            rbCd.setClickable(true);
        } else if (stateId == 3) {
            if (isCheck) {
                isEdit = false;
            }
            rbCash.setClickable(false);
            rbCd.setClickable(false);
        }

        dianTabAdapter = new DianTabAdapter(this, dianTabBeanList, tvWeightTotal, tvThjTotal, tvCbjTotal, tvXsjTotal, tvYlTotal, isEdit, R.layout.item_dian_tab);
        listViewDianTab.setAdapter(dianTabAdapter);
        listViewDianTab.setDividerHeight(0);

        dianTabAdapter.setStringCallBack(new OnStringPositionCallBack() {
            @Override
            public void onClick(String type, int position) {
                if (!isEdit) {
                    return;
                }
                KeyBoardUtils.closeKeybord(etRemark, getContext());
                PickDialogUtils.getInstance().getChooseDialog(getContext(), "垫付款说明", list, new ChooseDialogCallBack() {
                    @Override
                    public void onClickSuccess(String choose) {
                        dianTabBeanList.get(0).setTableRemark(choose);
                        if (dianTabAdapter != null) {
                            dianTabAdapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        });

        isDaChu = detailBean.getReList().get(0).getIsDaChu();
        if (StringUtils.isNoEmpty(isDaChu) && isDaChu.equals("0")) {
            rbDc.setChecked(true);
        } else if (StringUtils.isNoEmpty(isDaChu) && isDaChu.equals("1")) {
            rbBdc.setChecked(true);
        }
        LogUtils.i(isDaChu);

        isYuJin = detailBean.getReList().get(0).getIsSee_XiaoShou();
        id = detailBean.getReList().get(0).getUserID() + "";

        ApproveAgreeAdapter agreeAdapter = new ApproveAgreeAdapter(getContext(), approvalMsgList, R.layout.item_approve_result);
        listView.setAdapter(agreeAdapter);
        listView.setDividerHeight(0);

        fundWay = detailBean.getReList().get(0).getFundWay();
        tvFundWay.setText(fundWay);
        bankId = detailBean.getReList().get(0).getSpBankID();
        upYuanId = detailBean.getReList().get(0).getUpYuanpianID();

        Advance = detailBean.getReList().get(0).getAdvance();
        EndTime = DateUtils.getInstance().getFormData(detailBean.getReList().get(0).getEndTime());
        StartTime = DateUtils.getInstance().getFormData(detailBean.getReList().get(0).getStartTime());
        remark = detailBean.getReList().get(0).getRemark();
        PurchasePrice = detailBean.getReList().get(0).getPurchasePrice();
        RemitDircetion = detailBean.getReList().get(0).getRemitDircetion();
        RemitAccount = detailBean.getReList().get(0).getRemitAccount();
        ProceedsAccount = detailBean.getReList().get(0).getProceedsAccount();
        Profit = detailBean.getReList().get(0).getProfit();
        FreightTotalPrice = detailBean.getReList().get(0).getFreightTotalPrice();
        typePrice = detailBean.getReList().get(0).getTypePrice() + "";
        SellPrice = detailBean.getReList().get(0).getSellPrice();
        PurchasePrice = detailBean.getReList().get(0).getPurchasePrice();
        surchaseCashOrAccept = detailBean.getReList().get(0).getPurchaseCashOrAccept();
        sellCashOrAccept = detailBean.getReList().get(0).getSellCashOrAccept();
        Invoice = detailBean.getReList().get(0).getInvoice() + "";
        String syMessage = detailBean.getReList().get(0).getUpYuanpian();

        if (StringUtils.isNoEmpty(syMessage)) {
            String[] split = syMessage.split(",");
            if (split.length == 2) {
                syCompany = split[1];
                syId = split[0];
                tvSyCompany.setText(syCompany);
            }
        }

        tvSellPrice.setText("￥" + MoneyUtils.getInstance().getFormPrice(StringUtils.getNoNullStr(detailBean.getReList().get(0).getSellPrice() + "")));
        tvDianfu.setText(StringUtils.getNoNullStr(detailBean.getReList().get(0).getAdvance()));
        tvTime.setText(StringUtils.getNoNullStr(DateUtils.getInstance().getFormData(detailBean.getReList().get(0).getEndTime())));
        etRemark.setText(StringUtils.getNoNullStr(detailBean.getReList().get(0).getRemark()));

        String oldPrices = detailBean.getReList().get(0).getOriginalTypePrice();
        if (Double.parseDouble(oldPrices) == detailBean.getReList().get(0).getTypePrice()) {
            etApplyPrice.setText(MoneyUtils.getInstance().getFormPrice(StringUtils.getNoNullStr(detailBean.getReList().get(0).getTypePrice() + "")));
        } else {
            if (!isEdit) {
                etApplyPrice.setText("原：" + MoneyUtils.getInstance().getFormPrice(detailBean.getReList().get(0).getOriginalTypePrice()) + "  改：" + MoneyUtils.getInstance().getFormPrice(StringUtils.getNoNullStr(detailBean.getReList().get(0).getTypePrice() + "")));
            } else {
                etApplyPrice.setText(StringUtils.getNoNullStr(detailBean.getReList().get(0).getTypePrice() + ""));
            }
        }

        adVanceId = detailBean.getReList().get(0).getAdvanceOrderID();
        if (StringUtils.isNoEmpty(detailBean.getReList().get(0).getOriginalTypePrice())) {
            oldPrice = Double.parseDouble(detailBean.getReList().get(0).getOriginalTypePrice());
        }
        tvCompany.setText(StringUtils.getNoNullStr(detailBean.getReList().get(0).getTypeName() + ""));

        //判断是承兑还是现金销售
        if ((detailBean.getReList().get(0).getSellCashOrAccept() + "").equals("0")) {
            rbCash.setChecked(true);
        } else if ((detailBean.getReList().get(0).getSellCashOrAccept() + "").equals("1")) {
            rbCd.setChecked(true);
        }

        //采购
        if ((detailBean.getReList().get(0).getPurchaseCashOrAccept() + "").equals("0")) {
            rbBuyCash.setChecked(true);
        } else if ((detailBean.getReList().get(0).getPurchaseCashOrAccept() + "").equals("1")) {
            rbBuyCd.setChecked(true);
        }

        //不可编辑
        if (!isEdit) {
            rbBuyCash.setClickable(false);
            rbBuyCd.setClickable(false);
            rbCd.setClickable(false);
            rbCash.setClickable(false);
            rbDc.setClickable(false);
            rbBdc.setClickable(false);
            etRemark.setEnabled(false);
            etYfze.setEnabled(false);
            etYl.setEnabled(false);
            etApplyPrice.setEnabled(false);
            tvBuyPrice.setEnabled(false);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
        });

        tvDkFx.setText(StringUtils.getNoNullStr(detailBean.getReList().get(0).getRemitDircetion() + ""));
        tvCkZh.setText(StringUtils.getNoNullStr(detailBean.getReList().get(0).getRemitAccount() + ""));
        tvSkZh.setText(StringUtils.getNoNullStr(detailBean.getReList().get(0).getProceedsAccount() + ""));
        etYl.setText(StringUtils.getNoNullStr(detailBean.getReList().get(0).getProfit() + ""));
        etYfze.setText(MoneyUtils.getInstance().getFormPrice(StringUtils.getNoNullStr(detailBean.getReList().get(0).getFreightTotalPrice() + "")));

        if (StringUtils.getNoNullStr(detailBean.getReList().get(0).getPurchasePrice() + "").equals("0.00")) {
            tvBuyPrice.setText("请在后台管理输入采购价格");
        } else {
            tvBuyPrice.setText(MoneyUtils.getInstance().getFormPrice(StringUtils.getNoNullStr(detailBean.getReList().get(0).getPurchasePrice() + "")));
        }

//        scrollView.post(new Runnable() {
//            @Override
//            public void run() {
//                scrollView.scrollTo(0, 0);
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (requestCode == REQUEST_CODE_BANK) {
            ApproveBankListBean bankListBean = (ApproveBankListBean) data.getSerializableExtra("bean");
            ProceedsAccount = bankListBean.getCompany() + "-"
                    + bankListBean.getBankName() + "-"
                    + bankListBean.getBankAccount();
            tvSkZh.setText(bankListBean.getCompany() + "-"
                    + bankListBean.getBankName() + "-"
                    + bankListBean.getBankAccount());
            bankId = bankListBean.getID() + "";
            //上游原片厂
        } else if (requestCode == 1) {
            syCompany = data.getStringExtra(IntentUtils.getInstance().STR);
            syId = data.getStringExtra(IntentUtils.getInstance().ID);
            //
            tvSyCompany.setText(syCompany);
        }
    }

    //判断是否已经被改
    public void isChange(final String isAgree, String approveId, final String ids, final String csId) {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(getContext(), UrlConstant.getInstance().URL
                + PostConstant.getInstance().CHECK_OUT
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().APPROVE_ID + "=" + approveId, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    //同意审批
                    if (isAgree.equals(AGREE)) {
                        IntentUtils.getInstance().toLoanActivity(getContext(), ApproveAgreeActivity.class,
                                IntentUtils.getInstance().AGREE_APPROVE, orderId, approveTypeId, syCompany, syId, ids, csId,approvalMsgList);
                        //不同意审批
                    } else if (isAgree.equals(DISAGREE)) {
                        IntentUtils.getInstance().toLoanActivity(getContext(), ApproveAgreeActivity.class,
                                IntentUtils.getInstance().DISAGREE_APPROVE, orderId, approveTypeId, syCompany, syId, ids, csId,approvalMsgList);
                    }
                } else {
                    ToastUtils.getMineToast(successBean.getErrMsg());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    //编辑垫付款信息
    public void editDianMessage(String approveId, String sellUserId, String typePrice,
                                String sellCashOrAccept, String StartTime,
                                String Invoice, String Advance, String EndTime, String Remark, String PurchasePrice, String PurchaseCashOrAccept,
                                String RemitDircetion, String RemitAccount, String ProceedsAccount, String Profit, String FreightTotalPrice) {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(getContext(), UrlConstant.getInstance().URL
                + PostConstant.getInstance().ADD_ADVANCE
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().APPROVE_ID + "=" + approveId + "&"
                + FieldConstant.getInstance().SELL_USER_ID + "=" + sellUserId + "&"
                + FieldConstant.getInstance().TYPE_PRICE + "=" + typePrice + "&"
                + FieldConstant.getInstance().SELL_CASH_OR_ACCEPT + "=" + sellCashOrAccept + "&"
                + FieldConstant.getInstance().START_TIME + "=" + StartTime + "&"
                + FieldConstant.getInstance().INVOINCE + "=" + EncodeUtils.getInstance().getEncodeString(Invoice) + "&"
                + FieldConstant.getInstance().ADVANCE + "=" + EncodeUtils.getInstance().getEncodeString(Advance) + "&"
                + FieldConstant.getInstance().END_TIME + "=" + EndTime + "&"
                + FieldConstant.getInstance().REMARK + "=" + EncodeUtils.getInstance().getEncodeString(Remark) + "&"
                + FieldConstant.getInstance().PURCHASE_PRICE + "=" + PurchasePrice + "&"
                + FieldConstant.getInstance().PURCHASE_CASH_OR_ACCEPT + "=" + PurchaseCashOrAccept + "&"
                + FieldConstant.getInstance().REMIT_DIRCETION + "=" + EncodeUtils.getInstance().getEncodeString(RemitDircetion) + "&"
                + FieldConstant.getInstance().FUND_WAY + "=" + EncodeUtils.getInstance().getEncodeString(fundWay) + "&"
                + FieldConstant.getInstance().REMIT_ACCOUNT + "=" + EncodeUtils.getInstance().getEncodeString(RemitAccount) + "&"
                + FieldConstant.getInstance().PROCEEDS_ACCOUNT + "=" + EncodeUtils.getInstance().getEncodeString(ProceedsAccount) + "&"
                + FieldConstant.getInstance().PROFIT + "=" + Profit + "&"
                + FieldConstant.getInstance().ORDER_TABLE + "=" + tableMessage + "&"
                + FieldConstant.getInstance().UP_YUAN_ID + "=" + syId + "&"
                + FieldConstant.getInstance().IS_DACHU + "=" + isDaChu + "&"
                + FieldConstant.getInstance().FREIGHT_TOTAL_PRICE + "=" + FreightTotalPrice, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    ToastUtils.getMineToast("修改成功");
                    ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().dianList);
                } else {
                    ToastUtils.getMineToast(successBean.getErrMsg());
                }
            }

            @Override
            public void onFail() {
                ToastUtils.getMineToast("修改失败");
            }
        });
    }

    //获取出款账户
    public void getOutBank() {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(getContext(), UrlConstant.getInstance().URL
                + PostConstant.getInstance().BANK_LIST, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                OutBankBean bankBean = JSON.parseObject(json, OutBankBean.class);
                List<OutBankListBean> reList = bankBean.getReList();
                int num = reList.size();
                for (int i = 0; i < num; i++) {
                    bankList.add(reList.get(i).getName() + "(" + reList.get(i).getKhh() + ")");
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    //计算表格总金额
    private void totalTable() {
        double weightTotal = 0;
        double tihuoTotal = 0;
        double chenbenTotal = 0;
        double saleTotal = 0;
        double ylTotal = 0;

        int num = dianTabBeanList.size();
        for (int i = 0; i < num; i++) {
            weightTotal += DoubleUtils.getDouble(dianTabBeanList.get(i).getWeight());
            tihuoTotal += DoubleUtils.getDouble(dianTabBeanList.get(i).getTiHuoPrice());
            chenbenTotal += DoubleUtils.getDouble(dianTabBeanList.get(i).getChengBenPrice());
            saleTotal += DoubleUtils.getDouble(dianTabBeanList.get(i).getXiaoShouPrice());
            ylTotal += DoubleUtils.getDouble(dianTabBeanList.get(i).getYingLiPrice());
        }

        tvWeightTotal.setText(weightTotal + "");
        tvThjTotal.setText(tihuoTotal + "");
        tvCbjTotal.setText(chenbenTotal + "");
        tvXsjTotal.setText(saleTotal + "");
        tvYlTotal.setText(ylTotal + "");
    }
}
