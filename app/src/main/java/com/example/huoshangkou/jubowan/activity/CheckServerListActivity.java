package com.example.huoshangkou.jubowan.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ServeBean;
import com.example.huoshangkou.jubowan.bean.ServeListBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：CheckServerListActivity
 * 描述：
 * 创建时间：2017-08-17  15:27
 */

public class CheckServerListActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.tv_company_name)
    TextView tvCompanyName;
    @Bind(R.id.tv_service_man)
    TextView tvServiceMan;
    @Bind(R.id.tv_repair_pro)
    TextView tvRepairPro;
    @Bind(R.id.tv_buys_time)
    TextView tvBuysTime;
    @Bind(R.id.tv_brand_num)
    TextView tvBrandNum;
    @Bind(R.id.tv_start_time)
    TextView tvStartTime;
    @Bind(R.id.tv_leave_time)
    TextView tvLeaveTime;
    @Bind(R.id.tv_problem)
    TextView tvProblem;
    @Bind(R.id.tv_tool_name)
    TextView tvToolName;
    @Bind(R.id.tv_tool_num)
    TextView tvToolNum;
    @Bind(R.id.tv_opions)
    TextView tvOpions;
    @Bind(R.id.tv_num)
    TextView tvNum;
    @Bind(R.id.tv_single_price)
    TextView tvSinglePrice;
    @Bind(R.id.tv_all_price)
    TextView tvAllPrice;
    @Bind(R.id.tv_machine_name)
    TextView tvMachineName;
    @Bind(R.id.tv_machine_num)
    TextView tvMachineNum;
    @Bind(R.id.tv_brand)
    TextView tvBrand;
    @Bind(R.id.tv_type)
    TextView tvType;
    @Bind(R.id.tv_buy_time)
    TextView tvBuyTime;
    @Bind(R.id.tv_history_data)
    TextView tvHistoryData;
    @Bind(R.id.tv_size)
    TextView tvSize;
    @Bind(R.id.tv_remark)
    TextView tvRemark;
    @Bind(R.id.tv_machine_price)
    TextView tvMachinePrice;
    @Bind(R.id.tv_hour_price)
    TextView tvHourPrice;
    @Bind(R.id.tv_work_price)
    TextView tvWorkPrice;
    @Bind(R.id.tv_other_price)
    TextView tvOtherPrice;
    @Bind(R.id.tv_elect_remark)
    TextView tvElectRemark;
    @Bind(R.id.tv_work_remark)
    TextView tvWorkRemark;
    @Bind(R.id.tv_conclusion)
    TextView tvConclusion;

    private String serveId = "";

    @Override
    public int initLayout() {
        return R.layout.activity_check_service;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {

        tvTitle.setText("查看服务单");
        serveId = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        getData();
    }

    //获取清单
    public void getData() {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_MAINTAIN_SERVE + FieldConstant.getInstance().SERVE_ID + "=" + serveId, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                ServeBean serveBean = JSON.parseObject(json, ServeBean.class);

                bindData(serveBean.getReList().get(0));
            }

            @Override
            public void onFail() {

            }
        });
    }


    //绑定数据
    public void bindData(ServeListBean serveBean) {
        //基础信息
        tvCompanyName.setText(serveBean.getServeCompanyName());
        tvServiceMan.setText(serveBean.getServeStaff());
        tvRepairPro.setText(serveBean.getServeItem());
        tvBuysTime.setText(serveBean.getPurchaseTime());
        tvBrandNum.setText(serveBean.getProductBrand());
        tvStartTime.setText(serveBean.getServeSetupTime());
        tvLeaveTime.setText(serveBean.getServeArriveTime());

        //基础信息
        tvProblem.setText(serveBean.getProblemsMethods());

        String[] toolList = serveBean.getAccessoryDetail().split(",");
        //配件情况
        tvToolName.setText(toolList[0]);
        tvToolNum.setText(toolList[1]);
        tvOpions.setText(toolList[2]);
        tvNum.setText(toolList[3]);
        tvSinglePrice.setText(toolList[4]);
        tvAllPrice.setText(toolList[5]);


        String[] machineList = serveBean.getProductDetail().split(",");
        //设备名称
        tvMachineName.setText(machineList[0]);
        tvMachineNum.setText(machineList[1]);
        tvBrand.setText(machineList[2]);
        tvType.setText(machineList[3]);
        tvBuyTime.setText(machineList[4]);
        tvHistoryData.setText(machineList[5]);
        tvSize.setText(machineList[6]);
        tvRemark.setText(machineList[7]);


        String[] serverMoney = serveBean.getServeMoney().split(",");

        //设备清单
        tvMachinePrice.setText(serverMoney[0]);
        tvHourPrice.setText(serverMoney[1]);
        tvWorkPrice.setText(serverMoney[2]);
        tvOtherPrice.setText(serverMoney[3]);

        //电费说明
        tvElectRemark.setText(serveBean.getElectricExplain());
        tvWorkRemark.setText(serveBean.getCapacityMon());
        //总结
        tvConclusion.setText(serveBean.getOpinionSignature());

    }

    @OnClick({R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
        }
    }
}
