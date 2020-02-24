package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.ImageAddFunction;
import com.example.huoshangkou.jubowan.activity.function.RepairRepliyFunction;
import com.example.huoshangkou.jubowan.adapter.ImageAddAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ReMaintainBrandBean;
import com.example.huoshangkou.jubowan.bean.ReMaintainTypeBean;
import com.example.huoshangkou.jubowan.bean.RepairBrandBean;
import com.example.huoshangkou.jubowan.bean.RepairTypeListBean;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.inter.OnCommonSuccessCallBack;
import com.example.huoshangkou.jubowan.inter.OnRepairRepliyCallBack;
import com.example.huoshangkou.jubowan.inter.OnTimeCallBack;
import com.example.huoshangkou.jubowan.inter.PickPositonCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.photo.PhotoCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.PickDialogUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.TimeDialogUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：RepairRepliyActivity
 * 描述：故障申请界面
 * 创建时间：2017-02-28  15:27
 */

public class RepairRepliyActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_brand_type)
    TextView tvBrandType;
    @Bind(R.id.tv_buy_time)
    TextView tvBuyTime;
    @Bind(R.id.tv_repair_introdetail)
    TextView tvRepairIntro;
    @Bind(R.id.tv_repair_intro)
    TextView tvRepairIntroLeft;
    @Bind(R.id.tv_repair_detail)
    TextView tvRepairDetail;
    @Bind(R.id.tv_link_man)
    TextView tvLinkMan;
    @Bind(R.id.tv_link_phone)
    TextView tvLinkPhone;
    @Bind(R.id.tv_link_address)
    TextView tvLinkAddress;
    @Bind(R.id.grid_view_apply)
    ScrollGridView scrollGridView;
    @Bind(R.id.et_gz_require)
    EditText etGzRequir;
    @Bind(R.id.et_repair_standard)
    EditText etStandard;
    @Bind(R.id.rl_problem_standard)
    RelativeLayout rlGuiGe;
    @Bind(R.id.rl_problem_detail)
    RelativeLayout rlProblem;


    //图片
    List<String> imgList;
    //图片适配器
    ImageAddAdapter imageAddAdapter;
    private RepairTypeListBean typeListBean;
    //机械品牌
    List<ReMaintainBrandBean> wxBrandList;
    private ArrayList<String> brandList;

    //故障描述
    List<ReMaintainTypeBean> wxMaintainTypeList;
    private ArrayList<String> problemList;
    //维修类型id
    private String id = "";

    private int imgNum = 9;

    //品牌id
    private String brandId = "";
    //购买时间
    private String buyDate = "";
    //故障描述
    private String problemIntroId = "";
    //故障详情
    private String problemIntro = "";
    //联系人
    private String linkMan = "";
    //地址id
    private String addressID = "";
    //规格
    private String standard = "";

    private final int REPAIR_CODE = 1;
    //地址选择
    private final int CHOOSE_ADDRESS = 2;

    private String pics = "";

    //是否是规格改造
    private boolean isGz = false;


    @Override
    public int initLayout() {
        return R.layout.activity_repair_repliy;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        typeListBean = (RepairTypeListBean) getIntent().getSerializableExtra(IntentUtils.getInstance().BEAN_TYPE);

        String gz = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        //不为空表示为规格改造
        if (StringUtils.isNoEmpty(gz)) {
            isGz = true;
        }

        if (isGz) {
            tvRepairDetail.setText("上传设备图片：");
            etGzRequir.setVisibility(View.VISIBLE);
            rlGuiGe.setVisibility(View.VISIBLE);
            rlProblem.setVisibility(View.GONE);
        }


        imgList = new ArrayList<>();
        brandList = new ArrayList<>();
        problemList = new ArrayList<>();
        wxBrandList = new ArrayList<>();
        wxMaintainTypeList = new ArrayList<>();


        //图片显示封装
        imageAddAdapter = new ImageAddAdapter(this, imgList, "");
        ImageAddFunction.addImageFun(imageAddAdapter, imgList, this, scrollGridView);

        if (typeListBean != null) {
            tvTitle.setText(typeListBean.getName());
            id = typeListBean.getID() + "";

            //获取故障描述 和 品牌
            RepairRepliyFunction.getInstance().getRepairBrand(getContext(), id, new OnRepairRepliyCallBack() {
                @Override
                public void onSuccess(RepairBrandBean brandBean) {
                    if (brandBean.getReList() == null || brandBean.getReList().size() == 0) {
                        return;
                    }

                    wxBrandList.addAll(brandBean.getReList().get(0).getReMaintainBrand());
                    wxMaintainTypeList.addAll(brandBean.getReList().get(0).getReMaintainType());

                    int num1 = wxBrandList.size();
                    for (int i = 0; i < num1; i++) {
                        brandList.add(wxBrandList.get(i).getName());
                    }

                    int num2 = wxMaintainTypeList.size();
                    for (int i = 0; i < num2; i++) {
                        problemList.add(wxMaintainTypeList.get(i).getName());
                    }
                }
            });
        }
    }

    @OnClick({R.id.ll_back, R.id.tv_repair_send, R.id.rl_brand_type,
            R.id.rl_buy_time, R.id.rl_problem_detail, R.id.rl_link_man,
            R.id.rl_link_address, R.id.rl_link_phone, R.id.iv_apply_camera})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_repair_send:
                if (!StringUtils.isNoEmpty(brandId)) {
                    ToastUtils.getMineToast("请选择机械品牌");
                    return;
                }

                if (!StringUtils.isNoEmpty(buyDate)) {
                    ToastUtils.getMineToast("请选择购买时间");
                    return;
                }


                if (!isGz && !StringUtils.isNoEmpty(problemIntro)) {
                    ToastUtils.getMineToast("请选择故障描述");
                    return;
                }

                standard = etStandard.getText().toString().trim();
                if (isGz && !StringUtils.isNoEmpty(standard)) {
                    ToastUtils.getMineToast("请输入型号规格");
                    return;
                }

                if (isGz) {
                    problemIntro = etGzRequir.getText().toString().trim();
                }

                if (isGz && !StringUtils.isNoEmpty(problemIntro)) {
                    ToastUtils.getMineToast("请输入设备改造要求");
                    return;
                }

                if (!StringUtils.isNoEmpty(addressID)) {
                    ToastUtils.getMineToast("请选择地址");
                    return;
                }

                pics = PhotoUtils.getInstance().getImageStr(imgList);
                PhotoUtils.getInstance().mutilLocalImageUp(imgList, this, new StringCallBack() {
                    @Override
                    public void onSuccess(String str) {
                        pics = str;
                        RepairRepliyFunction.getInstance().commitRepairApply(getContext(), id, brandId, buyDate,
                                problemIntro, pics, addressID, standard, new OnCommonSuccessCallBack() {
                                    @Override
                                    public void onSuccess() {
                                        ToastUtils.getMineToast("提交成功");
                                        RepairRepliyActivity.this.finish();
                                        SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().REPAIR, "yes");
                                    }

                                    @Override
                                    public void onFail() {
                                        ToastUtils.getMineToast("提交失败");
                                        RepairRepliyActivity.this.finish();
                                    }
                                });
                    }

                    @Override
                    public void onFail() {

                    }
                });

                break;
            //机械品牌
            case R.id.rl_brand_type:
                PickDialogUtils.getInstance().getChooseDialog(getContext(), "选择机械品牌", brandList, new PickPositonCallBack() {
                    @Override
                    public void onClickSuccess(String choose, int position) {
                        brandId = wxBrandList.get(position).getID() + "";
                        tvBrandType.setText(choose + "  ");
                    }
                });
                break;
            //购买时间
            case R.id.rl_buy_time:
                TimeDialogUtils.getInstance().getTime(getContext(), new StringCallBack() {
                    @Override
                    public void onSuccess(String str) {
                        buyDate = str;
                        tvBuyTime.setText(str + "  ");
                    }

                    @Override
                    public void onFail() {

                    }
                });
                break;
            //故障详情
            case R.id.rl_problem_detail:
                Intent intent = new Intent(getContext(), ProblemIntroActivity.class);
                intent.putStringArrayListExtra(IntentUtils.getInstance().BEAN_TYPE, problemList);
                intent.putExtra(IntentUtils.getInstance().TYPE, problemIntro);
                startActivityForResult(intent, REPAIR_CODE);
                break;
            //地址选择
            case R.id.rl_link_man:
            case R.id.rl_link_phone:
            case R.id.rl_link_address:

                Intent intent1 = new Intent(getContext(), AddressListActivity.class);
                intent1.putExtra(IntentUtils.getInstance().TYPE, IntentUtils.getInstance().ADDRESS_CHOOSE);
                startActivityForResult(intent1, CHOOSE_ADDRESS);
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
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null) {
            return;
        }

        switch (requestCode) {
            case REPAIR_CODE:
                problemIntro = data.getStringExtra(IntentUtils.getInstance().VALUE);
                tvRepairIntro.setText(problemIntro);
                break;
            case CHOOSE_ADDRESS:
                if (data == null) {
                    return;
                }
                tvLinkAddress.setText(data.getStringExtra(IntentUtils.getInstance().ADDRESS_NAME) + "  ");
                tvLinkMan.setText(data.getStringExtra(IntentUtils.getInstance().ADDRESS_LINK_MAN) + "  ");
                tvLinkPhone.setText(data.getStringExtra(IntentUtils.getInstance().ADDRESS_LINK_TEL) + "  ");
                addressID = data.getStringExtra(IntentUtils.getInstance().ADDRESS_ID);
                break;
        }
    }
}
