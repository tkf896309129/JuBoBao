package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.ImageAddFunction;
import com.example.huoshangkou.jubowan.adapter.CheckImgAdapter;
import com.example.huoshangkou.jubowan.adapter.ImageAddAdapter;
import com.example.huoshangkou.jubowan.adapter.LanImageShowAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.CheckImgBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.bean.SuccessDBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnTimeCallBack;
import com.example.huoshangkou.jubowan.inter.PickPositonCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.photo.PhotoCallBack;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.KeyBoardUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.PickDialogUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.TimeDialogUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollGridView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：DianCheckDetailActivity
 * 描述：
 * 创建时间：2018-06-11  11:03
 */

public class DianCheckDetailActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.tv_order_id)
    TextView tvOrderId;
    @Bind(R.id.tv_dian_company)
    TextView tvDianCompany;
    @Bind(R.id.tv_apply_price)
    EditText tvApplyPrice;
    @Bind(R.id.tv_check_order)
    TextView tvCheckOrder;
    @Bind(R.id.tv_is_kp)
    TextView tvIsKp;
    @Bind(R.id.tv_df_cars)
    TextView tvDfCars;
    @Bind(R.id.tv_back_time)
    TextView tvBackTime;
    @Bind(R.id.et_remark)
    EditText etRemark;
    //图片显示GridView
    @Bind(R.id.grid_view_apply)
    ScrollGridView scrollGridView;
    @Bind(R.id.tv_apply_check)
    TextView tvCheckMan;

    //审批类型
    private String approveTypeId = "1008";
    //垫付单位
    private String dianCompany = "";
    //申请金额
    private String applyPrice = "";
    //查看订单
    private String checkOrder = "";
    //是否开票
    private String isKp = "";
    //垫付车数
    private String dfCars = "";
    //还款时间
    private String backTime = "";
    //备注
    private String remark = "";
    //订单号
    private String orderId = "";
    //公司单位
    private String company = "";

    //是否开票
    ArrayList<String> listPiao;
    //垫付情况
    ArrayList<String> listDianFu;
    //是否开票
    private String Invoice = "";
    //垫付情况
    private String Advance = "";
    //审批人id
    private String approveId = "";
    private String pics = "";
    //抄送人
    private String csId = "";

    //图片
    List<String> imgList;
    //图片适配器
    ImageAddAdapter imageAddAdapter;
    //最大图片数
    private int imgNum = 9;
    private int REQUEST_CODE = 1;
    private int REQUEST_ORDER_CODE = 2;

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
        return R.layout.activity_dian_check_detail;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        listPiao = new ArrayList<>();
        listDianFu = new ArrayList<>();
        imgList = new ArrayList<>();

        listPiao.add("开票");
        listPiao.add("不开票");

        listDianFu.add("一车一结(第一车)");
        listDianFu.add("二车一结(第一车)");
        listDianFu.add("二车一结(第二车)");
        listDianFu.add("三车一结(第一车)");
        listDianFu.add("三车一结(第二车)");
        listDianFu.add("三车一结(第三车)");

        tvRight.setText("提交");
        tvTitle.setText("垫付款申请");

        tvOrderId.setText(orderId);

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

        //图片显示封装
        imageAddAdapter = new ImageAddAdapter(this, imgList, "");
        ImageAddFunction.addImageFun(imageAddAdapter, imgList, this, scrollGridView);
    }

    private StaggeredGridLayoutManager getLayoutManager() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL);
        return layoutManager;
    }

    @OnClick({R.id.ll_order_id, R.id.rl_apply_check, R.id.tv_csr,R.id.iv_apply_camera, R.id.ll_back_time, R.id.ll_back, R.id.tv_right, R.id.rl_sfkp, R.id.rl_df_cs, R.id.ll_check_order})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_right:
                orderId = tvOrderId.getText().toString().trim();
                if (!StringUtils.isNoEmpty(orderId)) {
                    ToastUtils.getMineToast("请选择订单号");
                    return;
                }
                company = tvDianCompany.getText().toString().trim();
                if (!StringUtils.isNoEmpty(company)) {
                    ToastUtils.getMineToast("请选择垫付单位");
                    return;
                }
                applyPrice = tvApplyPrice.getText().toString().trim();
                if (!StringUtils.isNoEmpty(applyPrice)) {
                    ToastUtils.getMineToast("请输入申请金额");
                    return;
                }
                if (!StringUtils.isNoEmpty(Invoice)) {
                    ToastUtils.getMineToast("请选择是否开票");
                    return;
                }
                if (!StringUtils.isNoEmpty(dfCars)) {
                    ToastUtils.getMineToast("请选择垫付车数");
                    return;
                }
                if (!StringUtils.isNoEmpty(backTime)) {
                    ToastUtils.getMineToast("请选择还款时间");
                    return;
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

                PhotoUtils.getInstance().mutilLocalImageUp(imgList, this, new StringCallBack() {
                    @Override
                    public void onSuccess(String str) {
                        pics = str;
                        commitDianPay();
                    }

                    @Override
                    public void onFail() {

                    }
                });

                break;
            case R.id.rl_sfkp:
                KeyBoardUtils.closeKeybord(etRemark, getContext());
                PickDialogUtils.getInstance().getChooseDialog(getContext(), "是否开票", listPiao, new PickPositonCallBack() {
                    @Override
                    public void onClickSuccess(String choose, int position) {
                        if (choose.equals("开票")) {
                            Invoice = 1 + "";
                        } else if (choose.equals("不开票")) {
                            Invoice = 0 + "";
                        }
                        tvIsKp.setText(choose + " ");
                    }
                });
                break;
            case R.id.rl_df_cs:
                KeyBoardUtils.closeKeybord(etRemark, getContext());
                PickDialogUtils.getInstance().getChooseDialog(getContext(), "垫付情况", listDianFu, new PickPositonCallBack() {
                    @Override
                    public void onClickSuccess(String choose, int position) {
                        dfCars = choose;
                        tvDfCars.setText(choose + " ");
                    }
                });
                break;
            case R.id.ll_check_order:
                IntentUtils.getInstance().toActivity(getContext(), MyAllOrderAcitivty.class, orderId);
                break;
            case R.id.ll_back_time:
                TimeDialogUtils.getInstance().getTime(this, new OnTimeCallBack() {
                    @Override
                    public void getYearTime(String year) {
                        backTime = year;
                        tvBackTime.setText(year + " ");
                    }

                    @Override
                    public void getMinuteTime(String minute) {

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
            //选择审批人
            case R.id.rl_apply_check:
                Intent intent = new Intent(this, ChoosCheckManActivity.class);
                intent.putExtra(IntentUtils.getInstance().TITLE, "审批人");
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.ll_order_id:
                Intent intentOrder = new Intent(this, WriteOrderIdActivity.class);
                startActivityForResult(intentOrder, REQUEST_ORDER_CODE);
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

    //根据订单号获取垫付单位
    public void getDianFuCompany() {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(getContext(), UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_ORDER_DETAILS
                + FieldConstant.getInstance().ORDER_ID + "=" + orderId, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                try {
                    company = new JSONObject(json).getJSONObject("ReObj").getString("Company");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tvDianCompany.setText(company + " ");
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
        if (requestCode == REQUEST_CODE) {
            approveId = data.getStringExtra("id");
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
        } else if (requestCode == 2) {
            orderId = data.getStringExtra(IntentUtils.getInstance().ORDER_ID);
            tvOrderId.setText(orderId + " ");
            getDianFuCompany();
        }else if(requestCode == 3) {
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

    //垫付款审批提交
    public void commitDianPay() {
        Map<String, String> map = new HashMap<>();
        map.put(FieldConstant.getInstance().APPROVE_TYPE_ID, approveTypeId);
        map.put(FieldConstant.getInstance().END_TIME, backTime);
        map.put(FieldConstant.getInstance().INVOICE, Invoice);
        map.put(FieldConstant.getInstance().REMARK, remark);
        map.put(FieldConstant.getInstance().PICS, pics);
        map.put(FieldConstant.getInstance().TYPE_PRICES, applyPrice);
        map.put(FieldConstant.getInstance().ADVANCE, dfCars);
        map.put(FieldConstant.getInstance().USER_ID, PersonConstant.getInstance().getUserId());
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
                    DianCheckDetailActivity.this.finish();
                } else {
                    ToastUtils.getMineToast(successBean.getD().getErrMsg());
                }
            }

            @Override
            public void onFail() {

            }
        });

//        OkhttpUtil.getInstance().setUnCacheData(this, "正在提交", UrlConstant.getInstance().URL
//                        + PostConstant.getInstance().ADD_APPLY
//                        + FieldConstant.getInstance().APPROVE_TYPE_ID + "=" + approveTypeId + "&"
//                        + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
//                        + FieldConstant.getInstance().END_TIME + "=" + backTime + "&"
//                        + FieldConstant.getInstance().ADVANCE_ORDER_ID + "=" + orderId + "&"
//                        + FieldConstant.getInstance().TYPE_PRICES + "=" + applyPrice + "&"
//                        + FieldConstant.getInstance().ADVANCE + "=" + EncodeUtils.getInstance().getEncodeString(dfCars) + "&"
//                        + FieldConstant.getInstance().APPROVE_USER_ID + "=" + approveId + "&"
//                        + FieldConstant.getInstance().COPY_USER_ID + "=" + csId + "&"
//                        + FieldConstant.getInstance().PICS + "=" + EncodeUtils.getInstance().getEncodeString(pics) + "&"
//                        + FieldConstant.getInstance().INVOINCE + "=" + Invoice, new OkhttpCallBack() {
//                    @Override
//                    public void onSuccess(String json) {
//                        SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
//                        if (successBean.getSuccess() == 1) {
//                            DianCheckDetailActivity.this.finish();
//                        } else {
//                            ToastUtils.getMineToast(successBean.getErrMsg());
//                        }
//                    }
//
//                    @Override
//                    public void onFail() {
//
//                    }
//                }
//        );
    }
}
