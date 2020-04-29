package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.ImageAddFunction;
import com.example.huoshangkou.jubowan.adapter.CheckImgAdapter;
import com.example.huoshangkou.jubowan.adapter.ImageAddAdapter;
import com.example.huoshangkou.jubowan.adapter.LanImageShowAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ApproveBankListBean;
import com.example.huoshangkou.jubowan.bean.ApproveDetailListBean;
import com.example.huoshangkou.jubowan.bean.CheckImgBean;
import com.example.huoshangkou.jubowan.bean.OutBankBean;
import com.example.huoshangkou.jubowan.bean.OutBankListBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.bean.SuccessDBean;
import com.example.huoshangkou.jubowan.bean.SyYuanBean;
import com.example.huoshangkou.jubowan.bean.SyYuanListBean;
import com.example.huoshangkou.jubowan.bean.YyZxObjBean;
import com.example.huoshangkou.jubowan.constant.ApproveConstant;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.ChooseDialogCallBack;
import com.example.huoshangkou.jubowan.inter.ChooseDialogPositionCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.photo.PhotoCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.KeyBoardUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.PickDialogUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：YwMoneyActivity
 * 描述：
 * 创建时间：2018-05-22  13:27
 */

public class YwMoneyActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_rzkh)
    TextView tvRzKh;
    @Bind(R.id.tv_rzzh)
    TextView tvRzzh;

    ArrayList<String> listDkfx;
    ArrayList<String> bankList;
    //加工单位
    ArrayList<SyYuanListBean> yuanList;
    ArrayList<SyYuanListBean> yyzxList;

    @Bind(R.id.cb_invoice)
    CheckBox cbInvoice;
    @Bind(R.id.rl_dkfx)
    RelativeLayout rlDkfx;
    @Bind(R.id.et_remark)
    EditText etRemark;
    @Bind(R.id.et_rzje)
    EditText etRzje;
    @Bind(R.id.et_kxyt)
    EditText etKxyt;
    @Bind(R.id.et_yfje)
    EditText etYfje;
    @Bind(R.id.tv_ckzh)
    TextView tvCkzh;
    @Bind(R.id.tv_dkfx)
    TextView tvDkfx;
    @Bind(R.id.et_yl)
    EditText etYl;
    @Bind(R.id.tv_yyzx)
    TextView tvYyzx;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.rg_rkfs)
    RadioGroup rgRkfs;
    @Bind(R.id.rg_rzxz)
    RadioGroup rgRzxz;
    @Bind(R.id.rg_zhlx)
    RadioGroup rgZhlx;
    @Bind(R.id.rg_dkfs)
    RadioGroup rgDkfs;
    @Bind(R.id.rg_sfdc)
    RadioGroup rgSfdc;
    @Bind(R.id.tv_apply_check)//那巨大的黑色长剑 也就这样没了磨豆腐
            TextView tvCheckMan;
    //图片显示GridView
    @Bind(R.id.grid_view_apply)
    ScrollGridView scrollGridView;
    @Bind(R.id.rb_cash)
    RadioButton rbCash;
    @Bind(R.id.rb_cd)
    RadioButton rbCd;
    @Bind(R.id.rb_khzf)
    RadioButton rbKhzf;
    @Bind(R.id.rb_rzzf)
    RadioButton rbRzzf;
    @Bind(R.id.rb_gz)
    RadioButton rbGz;
    @Bind(R.id.rb_sz)
    RadioButton rbSz;
    @Bind(R.id.rb_dk_xj)
    RadioButton rbDkXj;
    @Bind(R.id.rb_dk_cd)
    RadioButton rbDkCd;
    @Bind(R.id.rb_dc)
    RadioButton rbDc;
    @Bind(R.id.rb_bdc)
    RadioButton rbBdc;

    private String ProceedsAccount;
    private String bankId;
    private String csId;
    ApproveDetailListBean approveDetailListBean;
    ArrayList<String> listYyZx;

    //入账客户
    private String rzKh;
    //入款方式
    private String rkType;
    //入账金额
    private String rzMoney;
    //入账性质
    private String rzXz;
    //款项用途
    private String kxUse;
    //应付金额
    private String yfPrice;
    //出款账户
    private String ckZh;
    //入款账户
    private String rkZh;
    //账户类型
    private String zhType;
    //打款方式
    private String dkType;
    //是否打出
    private String isDaChu;
    //发票
    private String invoince = "0";
    //打款方向
    private String dkFx;
    //盈利
    private String yinLi;
    //运营中心
    private String yunYinCenter;
    //备注
    private String remark;
    //上游原片厂id
    private String syId = "";
    //上游原片厂名称
    private String syCompany = "";
    //审批人id
    private String approveId = "";
    //图片
    public String pics = "";
    private int imgNum = 20;

    //图片
    List<String> imgList;
    //图片适配器
    ImageAddAdapter imageAddAdapter;

    private final int REQUEST_CODE_BANK = 2;
    private int REQUEST_CODE = 3;

    @Bind(R.id.recyc_check)
    RecyclerView recyclerView;
    @Bind(R.id.recyc_check_man)
    RecyclerView recyclerViewMan;

    //审批人
    CheckImgAdapter checkAdapter;
    //抄送人
    CheckImgAdapter checkImgAdapter;

    List<CheckImgBean> checkImgBeenList = new ArrayList<>();
    List<CheckImgBean> checkList = new ArrayList<>();

    @Override
    public int initLayout() {
        return R.layout.activity_yw_money;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("业务用款");
        listDkfx = new ArrayList<>();
        bankList = new ArrayList<>();
        yuanList = new ArrayList<>();
        yyzxList = new ArrayList<>();
        listYyZx = new ArrayList<>();
        imgList = new ArrayList<>();
        listDkfx.add("贸易商-原片厂");
        listDkfx.add("中小客户-原片厂");
        listDkfx.add("中小客户-贸易商");
        tvRight.setText("提交");
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().approveCheckList);
        approveDetailListBean = (ApproveDetailListBean) getIntent().getSerializableExtra(IntentUtils.getInstance().BEAN_TYPE);
        //图片显示封装
        imageAddAdapter = new ImageAddAdapter(this, imgList, "");
        ImageAddFunction.addImageFun(imageAddAdapter, imgList, this, scrollGridView);

        if (approveDetailListBean != null) {
            tvRzKh.setText(approveDetailListBean.getRuZhangCompany());
            rzKh = approveDetailListBean.getRuZhangCompany();
            if (approveDetailListBean.getRuKuanWay().equals("现金")) {
                rbCash.setChecked(true);
                rkType = "现金";
            } else {
                rkType = "承兑";
                rbCd.setChecked(true);
            }
            etRzje.setText(approveDetailListBean.getRuZhangPrice());
            etYfje.setText(approveDetailListBean.getTypePrice() + "");
            etKxyt.setText(approveDetailListBean.getRuZhangXingZhi());
            if (approveDetailListBean.getRuZhangXingZhi().equals("客户自付")) {
                rbKhzf.setChecked(true);
                rzXz = "客户自付";
            } else {
                rzXz = "融资支付";
                rbRzzf.setChecked(true);
            }

            etKxyt.setText(approveDetailListBean.getKuanXiangWay());
            tvCkzh.setText(approveDetailListBean.getRemitAccount());
            ckZh = approveDetailListBean.getRemitAccount();
            tvRzzh.setText(approveDetailListBean.getProceedsAccount());
            rkZh = approveDetailListBean.getProceedsAccount();
            if (approveDetailListBean.getAccountType().equals("公账")) {
                rbGz.setChecked(true);
                zhType = "公账";
            } else {
                zhType = "私账";
                rbSz.setChecked(true);
            }
            if (approveDetailListBean.getFundWay().equals("现金")) {
                rbDkCd.setChecked(true);
                dkType = "现金";
            } else {
                dkType = "承兑";
                rbDkXj.setChecked(true);
            }

            //默认需要打出
            rbDc.setChecked(true);
            isDaChu = "0";

            if (approveDetailListBean.getRuZhangCompany().equals("0")) {
                rbDc.setChecked(true);
                isDaChu = "0";
            } else {
                isDaChu = "1";
                rbBdc.setChecked(true);
            }

            if (approveDetailListBean.getRuZhangCompany().equals("1")) {
                cbInvoice.setChecked(true);
            }

            etYl.setText(approveDetailListBean.getProfit());
            tvDkfx.setText(approveDetailListBean.getRemitDircetion());
            dkFx = approveDetailListBean.getRemitDircetion();
            tvYyzx.setText(approveDetailListBean.getYYZX());
            yunYinCenter = approveDetailListBean.getYYZX();
            etRemark.setText(approveDetailListBean.getRemark());
            imgList.addAll(PhotoUtils.getInstance().getListImage(approveDetailListBean.getPics()));
            imageAddAdapter.notifyDataSetChanged();
        }

        getOutBank();
        getYuanFactory();
        getYyzx();

        rgRkfs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_cash:
                        rkType = "现金";
                        break;
                    case R.id.rb_cd:
                        rkType = "承兑";
                        break;
                }
            }
        });
        rgRzxz.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_khzf:
                        rzXz = "客户自付";
                        break;
                    case R.id.rb_rzzf:
                        rzXz = "融资支付";
                        break;
                }
            }
        });
        rgZhlx.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_gz:
                        zhType = "公账";
                        break;
                    case R.id.rb_sz:
                        zhType = "私账";
                        break;
                }
            }
        });
        rgDkfs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_dk_xj:
                        dkType = "现金";
                        break;
                    case R.id.rb_dk_cd:
                        dkType = "承兑";
                        break;
                }
            }
        });
        rgSfdc.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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

    }


    private StaggeredGridLayoutManager getLayoutManager() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL);
        return layoutManager;
    }

    @OnClick({R.id.ll_back, R.id.tv_choose_bank, R.id.tv_csr, R.id.rl_dkfx, R.id.rl_ck_zh, R.id.iv_apply_camera, R.id.ll_rzzh, R.id.rl_yyzx, R.id.ll_rzkh, R.id.tv_right, R.id.rl_apply_check})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.rl_dkfx:
                PickDialogUtils.getInstance().getChoosePositionDialog(this, "选择打款方向", listDkfx, new ChooseDialogPositionCallBack() {
                    @Override
                    public void onGetMessagePosition(String message, int position) {
                        dkFx = message;
                        tvDkfx.setText(dkFx + " ");
                    }
                });
                break;
            case R.id.rl_ck_zh:
                KeyBoardUtils.closeKeybord(etRemark, getContext());
                PickDialogUtils.getInstance().getChooseDialog(getContext(), "选择出款账户", bankList, new ChooseDialogCallBack() {
                    @Override
                    public void onClickSuccess(String choose) {
//                    RemitAccount = choose;
//                    tvCkZh.setText(choose);
                        ckZh = choose;
                        tvCkzh.setText(ckZh + " ");
                    }
                });
                break;
            case R.id.ll_rzzh:
                Intent intentBank = new Intent(this, ApproveBankActivity.class);
                startActivityForResult(intentBank, REQUEST_CODE_BANK);
                break;
            case R.id.rl_yyzx:
                Intent intent4 = new Intent(getContext(), SyYuanActivity.class);
                intent4.putParcelableArrayListExtra("manList", yyzxList);
                intent4.putExtra(IntentUtils.getInstance().TITLE, "运营中心");
                startActivityForResult(intent4, 4);
                break;
            case R.id.ll_rzkh:
                Intent intent = new Intent(getContext(), SyYuanActivity.class);
                intent.putParcelableArrayListExtra("manList", yuanList);
                startActivityForResult(intent, 1);
                break;
            case R.id.tv_right:
                if (!StringUtils.isNoEmpty(rzKh)) {
                    ToastUtils.getMineToast("请选择入账客户");
                    return;
                }
                if (!StringUtils.isNoEmpty(rkType)) {
                    ToastUtils.getMineToast("请选择入款方式");
                    return;
                }
                rzMoney = etRzje.getText().toString().trim();
                if (!StringUtils.isNoEmpty(rzMoney)) {
                    ToastUtils.getMineToast("请输入入账金额");
                    return;
                }
                if (!StringUtils.isNoEmpty(rzXz)) {
                    ToastUtils.getMineToast("请选择入账性质");
                    return;
                }
                kxUse = etKxyt.getText().toString().trim();
                if (!StringUtils.isNoEmpty(kxUse)) {
                    ToastUtils.getMineToast("请输入款项用途");
                    return;
                }
                yfPrice = etYfje.getText().toString().trim();
                if (!StringUtils.isNoEmpty(yfPrice)) {
                    ToastUtils.getMineToast("请输入应付金额");
                    return;
                }
                if (!StringUtils.isNoEmpty(ckZh)) {
                    ToastUtils.getMineToast("请选择出款账户");
                    return;
                }
                if (!StringUtils.isNoEmpty(rkZh)) {
                    ToastUtils.getMineToast("请选择入款账户");
                    return;
                }
                if (!StringUtils.isNoEmpty(zhType)) {
                    ToastUtils.getMineToast("请选择账户类型");
                    return;
                }
                if (!StringUtils.isNoEmpty(dkType)) {
                    ToastUtils.getMineToast("请选择打款方式");
                    return;
                }
                if (!StringUtils.isNoEmpty(isDaChu)) {
                    ToastUtils.getMineToast("请选择是否打出");
                    return;
                }
                if (!StringUtils.isNoEmpty(dkFx)) {
                    ToastUtils.getMineToast("请选择打款方向");
                    return;
                }
                yinLi = etYl.getText().toString().trim();
                if (!StringUtils.isNoEmpty(yinLi)) {
                    ToastUtils.getMineToast("请输入盈利");
                    return;
                }
                if (!StringUtils.isNoEmpty(yunYinCenter)) {
                    ToastUtils.getMineToast("请选择运营中心");
                    return;
                }
                if (!StringUtils.isNoEmpty(approveId)) {
                    ToastUtils.getMineToast("请选择审批人");
                    return;
                }
                remark = etRemark.getText().toString().trim();
                if (!StringUtils.isNoEmpty(remark)) {
                    ToastUtils.getMineToast("请输入备注说明");
                    return;
                }
                for (CheckImgBean checkImgBean : checkImgBeenList) {
                    csId += checkImgBean.getId() + ";";
                }
                PhotoUtils.getInstance().mutilLocalImageUp(imgList, this, new StringCallBack() {
                    @Override
                    public void onSuccess(String str) {
                        pics = str;
                        putYwMoney(rzKh, rkType, rzMoney, rzXz, kxUse, yfPrice, ckZh, rkZh, zhType, dkType, isDaChu, dkFx, yinLi, yunYinCenter, remark);
                    }

                    @Override
                    public void onFail() {

                    }
                });
                break;
            //选择审批人
            case R.id.rl_apply_check:
                Intent intent2 = new Intent(this, ChoosCheckManActivity.class);
                intent2.putExtra(IntentUtils.getInstance().TITLE, "审批人");
                startActivityForResult(intent2, REQUEST_CODE);
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
            //选择银行信息
            case R.id.tv_choose_bank:
//                IntentUtils.getInstance().toActivity(this,ApproveBankActivity.class);
                Intent intentBankList = new Intent(this, ApproveBankActivity.class);
                startActivityForResult(intentBankList, 5);
                break;
            case R.id.tv_csr:
                if (checkImgBeenList.size() >= 3) {
                    ToastUtils.getMineToast("最多选择3个抄送人");
                    return;
                }
                Intent intent3 = new Intent(this, ChoosCheckManActivity.class);
                intent3.putExtra(IntentUtils.getInstance().TITLE, "抄送人");
                startActivityForResult(intent3, 6);
                break;
        }
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

    //获取原片厂
    public void getYuanFactory() {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), "数据加载中", UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_JG_FACTORY, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SyYuanBean yuanBean = JSON.parseObject(json, SyYuanBean.class);
                yuanList.addAll(yuanBean.getReList());
            }

            @Override
            public void onFail() {

            }
        });
    }


    //获取运营中心
    public void getYyzx() {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(getContext(), UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_YYZX, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {

                YyZxObjBean objBean = JSON.parseObject(json, YyZxObjBean.class);
                int num = objBean.getReList().size();

                for (int i = 0; i < num; i++) {
                    SyYuanListBean yuanListBean = new SyYuanListBean();
                    yuanListBean.setCompany(objBean.getReList().get(i).getRealName());
                    yyzxList.add(yuanListBean);
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
        if (requestCode == REQUEST_CODE_BANK) {
            ApproveBankListBean bankListBean = (ApproveBankListBean) data.getSerializableExtra("bean");
            rkZh = bankListBean.getCompany() + "-"
                    + bankListBean.getBankName() + "-"
                    + bankListBean.getBankAccount();
            tvRzzh.setText(bankListBean.getCompany() + "-"
                    + bankListBean.getBankName() + "-"
                    + bankListBean.getBankAccount());
            bankId = bankListBean.getID() + "";
            //上游原片厂
        } else if (requestCode == 1) {
            syCompany = data.getStringExtra(IntentUtils.getInstance().STR);
            syId = data.getStringExtra(IntentUtils.getInstance().ID);
            rzKh = syCompany;
            //
            tvRzKh.setText(syCompany);
        } else if (requestCode == REQUEST_CODE) {
            approveId = data.getStringExtra("id");
            if (isHave(approveId)) {
                ToastUtils.getMineToast("审批人不能与抄送人相同");
                approveId = "";
                return;
            }
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
        } else if (requestCode == 4) {
            yunYinCenter = data.getStringExtra(IntentUtils.getInstance().STR);
            tvYyzx.setText(yunYinCenter);
        } else if (requestCode == 5) {
            ApproveBankListBean bankListBean = (ApproveBankListBean) data.getSerializableExtra("bean");
            etRemark.setText("公司名称：" + bankListBean.getCompany() + "\n"
                    + "开户银行：" + bankListBean.getBankName() + "\n"
                    + "银行账号：" + bankListBean.getBankAccount());
        } else if (requestCode == 6) {
            String approveIds = data.getStringExtra("id");
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
    }


    public boolean isHave(String id) {
        for (CheckImgBean checkBean : checkImgBeenList) {
            if (id.equals(checkBean.getId())) {
                return true;
            }
        }
        return false;
    }

    //提交业务用款
    public void putYwMoney(String rzCompany, String rkWay, String rzPrice, String rzXingZhi, String kxWay, String typePrice, String remitAccount,
                           String procesAccount, String accountType, String fundWay, String isDaChu, String remitDirction, String profit,
                           String yyZx, String remark) {
        Map<String, String> map = new HashMap<>();
        map.put(FieldConstant.getInstance().USER_ID, PersonConstant.getInstance().getUserId());
        map.put(FieldConstant.getInstance().APPROVE_TYPE_ID, ApproveConstant.getInstance().YW_MONEY + "");
        map.put(FieldConstant.getInstance().RZ_COMPANY, rzCompany);
        map.put(FieldConstant.getInstance().RK_WAY, rkWay);
        map.put(FieldConstant.getInstance().RZ_PRICE, rzPrice);
        map.put(FieldConstant.getInstance().RZ_XINGZHI, rzXingZhi);
        map.put(FieldConstant.getInstance().KX_YONGTU, kxWay);
        map.put(FieldConstant.getInstance().TYPE_PRICE, typePrice);
        map.put(FieldConstant.getInstance().REMIT_ACCOUNT, remitAccount);
        map.put(FieldConstant.getInstance().PROCEEDS_ACCOUNT, procesAccount);
        map.put(FieldConstant.getInstance().ACCOUNT_TYPE, accountType);
        map.put(FieldConstant.getInstance().FUND_WAY, fundWay);
        map.put(FieldConstant.getInstance().IS_DACHU, isDaChu);
        map.put(FieldConstant.getInstance().REMIT_DIRCETION, remitDirction);
        map.put(FieldConstant.getInstance().PROFIT, profit);
        map.put(FieldConstant.getInstance().YYZX, yyZx);
        map.put(FieldConstant.getInstance().REMARK, remark);
        map.put(FieldConstant.getInstance().SP_BANK_ID, bankId);
        map.put(FieldConstant.getInstance().PICS, pics);
        map.put(FieldConstant.getInstance().INVOICE, invoince);
        map.put(FieldConstant.getInstance().COPY_USER_ID, csId);
        map.put(FieldConstant.getInstance().APPROVE_USER_ID, approveId);
        String json = "{\n" +
                "\"model\":" + JSON.toJSONString(map) + "}";
        OkhttpUtil.getInstance().basePostCall(this, json, UrlConstant.getInstance().POST_APPLY_CHECK, new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                SuccessDBean successBean = JSON.parseObject(str, SuccessDBean.class);
                if (successBean.getD() != null && successBean.getD().getSuccess() == 1) {
                    ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().approveCheckList);
                    ToastUtils.getMineToast("提交成功");
                } else {
                    ToastUtils.getMineToast(successBean.getD().getErrMsg());
                }
            }

            @Override
            public void onFail() {

            }
        });
//        OkhttpUtil.getInstance().setUnCacheData(this, "数据加载中", UrlConstant.getInstance().URL
//                + PostConstant.getInstance().ADD_APPLY
//                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
//                + FieldConstant.getInstance().APPROVE_TYPE_ID + "=" + ApproveConstant.getInstance().YW_MONEY + "&"
//                + FieldConstant.getInstance().RZ_COMPANY + "=" + EncodeUtils.getInstance().getEncodeString(rzCompany) + "&"
//                + FieldConstant.getInstance().RK_WAY + "=" + EncodeUtils.getInstance().getEncodeString(rkWay) + "&"
//                + FieldConstant.getInstance().RZ_PRICE + "=" + EncodeUtils.getInstance().getEncodeString(rzPrice) + "&"
//                + FieldConstant.getInstance().RZ_XINGZHI + "=" + EncodeUtils.getInstance().getEncodeString(rzXingZhi) + "&"
//                + FieldConstant.getInstance().KX_YONGTU + "=" + EncodeUtils.getInstance().getEncodeString(kxWay) + "&"
//                + FieldConstant.getInstance().TYPE_PRICE + "=" + EncodeUtils.getInstance().getEncodeString(typePrice) + "&"
//                + FieldConstant.getInstance().REMIT_ACCOUNT + "=" + EncodeUtils.getInstance().getEncodeString(remitAccount) + "&"
//                + FieldConstant.getInstance().PROCEEDS_ACCOUNT + "=" + EncodeUtils.getInstance().getEncodeString(procesAccount) + "&"
//                + FieldConstant.getInstance().ACCOUNT_TYPE + "=" + EncodeUtils.getInstance().getEncodeString(accountType) + "&"
//                + FieldConstant.getInstance().FUND_WAY + "=" + EncodeUtils.getInstance().getEncodeString(fundWay) + "&"
//                + FieldConstant.getInstance().IS_DACHU + "=" + EncodeUtils.getInstance().getEncodeString(isDaChu) + "&"
//                + FieldConstant.getInstance().REMIT_DIRCETION + "=" + EncodeUtils.getInstance().getEncodeString(remitDirction) + "&"
//                + FieldConstant.getInstance().PROFIT + "=" + EncodeUtils.getInstance().getEncodeString(profit) + "&"
//                + FieldConstant.getInstance().YYZX + "=" + EncodeUtils.getInstance().getEncodeString(yyZx) + "&"
//                + FieldConstant.getInstance().REMARK + "=" + EncodeUtils.getInstance().getEncodeString(remark) + "&"
//                + FieldConstant.getInstance().SP_BANK_ID + "=" + EncodeUtils.getInstance().getEncodeString(bankId) + "&"
//                + FieldConstant.getInstance().PICS + "=" + EncodeUtils.getInstance().getEncodeString(pics) + "&"
//                + FieldConstant.getInstance().INVOINCE + "=" + invoince + "&"
//                + FieldConstant.getInstance().COPY_USER_ID + "=" + csId + "&"
//                + FieldConstant.getInstance().APPROVE_USER_ID + "=" + approveId, new OkhttpCallBack() {
//            @Override
//            public void onSuccess(String json) {
//                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
//                if (successBean.getSuccess() == 1) {
//                    ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().approveCheckList);
//                    ToastUtils.getMineToast("提交成功");
//                } else {
//                    ToastUtils.getMineToast(successBean.getErrMsg());
//                    ToastUtils.getMineToast(successBean.getErrMsg());
//                }
//            }
//
//            @Override
//            public void onFail() {
//
//            }
//        });
    }
}
