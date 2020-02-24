package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.inter.ChooseDialogCallBack;
import com.example.huoshangkou.jubowan.inter.OnTimeCallBack;
import com.example.huoshangkou.jubowan.utils.EditDialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.KeyBoardUtils;
import com.example.huoshangkou.jubowan.utils.PickDialogUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.TimeDialogUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：ToolDetailDescActivity
 * 描述：
 * 创建时间：2017-08-11  15:40
 */

public class ToolDetailDescActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.et_too_name)
    TextView tvTooName;
    @Bind(R.id.et_tool_num)
    EditText etToolNum;
    @Bind(R.id.et_tool_brand)
    EditText etToolBrand;
    @Bind(R.id.et_tool_type)
    EditText etToolType;
    @Bind(R.id.tv_buy_time)
    TextView tvBuyTime;
    @Bind(R.id.et_history_data)
    EditText etHistoryData;
    @Bind(R.id.et_tool_size)
    EditText etToolSize;
    @Bind(R.id.et_remark)
    EditText etRemark;

    //配件名称
    private String toolName = "";
    //数量
    private String num = "";
    //品牌
    private String brand = "";
    //类型
    private String type = "";
    //购买时间
    private String buyTime = "";
    //历史数据
    private String historyData = "";
    //尺寸
    private String size = "";
    //备注
    private String remark = "";
    private ArrayList<String> toolList;

    private String priceDetail = "";

    @Override
    public int initLayout() {
        return R.layout.activity_tool_detail_desc;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvRight.setText("提交");
        tvTitle.setText("设备清单");
        toolList = new ArrayList<>();
        toolList.add("切割机");
        toolList.add("磨边机");
        toolList.add("钢化炉");
        toolList.add("中空线");
        toolList.add("夹胶线");
        toolList.add("高压釜");
        toolList.add("清洗机");
        toolList.add("丝印线");
        toolList.add("其他设备");

        priceDetail = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);

        setData(priceDetail);

    }

    private void setData(String str) {
        if (!StringUtils.isNoEmpty(str)) {
            return;
        }
        String[] detail = str.split(",");

        toolName = detail[0];
        tvTooName.setText(detail[0]);
        etToolNum.setText(detail[1]);
        etToolBrand.setText(detail[2]);
        etToolType.setText(detail[3]);
        buyTime = detail[4];
        tvBuyTime.setText(detail[4]);
        etHistoryData.setText(detail[5]);
        etToolSize.setText(detail[6]);
        etRemark.setText(detail[7]);
    }

    @OnClick({R.id.ll_back, R.id.tv_right, R.id.tv_buy_time, R.id.ll_tool_name})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_buy_time:
                KeyBoardUtils.closeKeybord(etHistoryData, getContext());
                TimeDialogUtils.getInstance().getTime(getContext(), new OnTimeCallBack() {
                    @Override
                    public void getYearTime(String year) {
                        buyTime = year;
                        tvBuyTime.setText(year);
                    }

                    @Override
                    public void getMinuteTime(String minute) {

                    }
                });

                break;
            case R.id.tv_right:
                if (!StringUtils.isNoEmpty(toolName)) {
                    ToastUtils.getMineToast("请选择配件名称");
                    return;
                }

                num = etToolNum.getText().toString().trim();
                if (!StringUtils.isNoEmpty(num)) {
                    ToastUtils.getMineToast("请输入数量");
                    return;
                }

                brand = etToolBrand.getText().toString().trim();
                if (!StringUtils.isNoEmpty(brand)) {
                    ToastUtils.getMineToast("请输入品牌");
                    return;
                }

                type = etToolType.getText().toString().trim();
                if (!StringUtils.isNoEmpty(type)) {
                    ToastUtils.getMineToast("请输入类型");
                    return;
                }

                if (!StringUtils.isNoEmpty(buyTime)) {
                    ToastUtils.getMineToast("请选择购买时间");
                    return;
                }

                historyData = etHistoryData.getText().toString().trim();
                if (!StringUtils.isNoEmpty(historyData)) {
                    ToastUtils.getMineToast("请输入历史数据");
                    return;
                }

                size = etToolSize.getText().toString().trim();
                if (!StringUtils.isNoEmpty(size)) {
                    ToastUtils.getMineToast("请输入尺寸");
                    return;
                }
                remark = etRemark.getText().toString().trim();
                if (!StringUtils.isNoEmpty(remark)) {
                    ToastUtils.getMineToast("请输入备注说明");
                    return;
                }

                String baseMessage = toolName
                        + ":" + num
                        + ":" + brand
                        + ":" + type
                        + ":" + buyTime
                        + ":" + historyData
                        + ":" + size
                        + ":" + remark;

                Intent intent2 = new Intent();
                intent2.putExtra(IntentUtils.getInstance().TYPE, baseMessage);
                setResult(4, intent2);
                this.finish();

                break;

            case R.id.ll_tool_name:
                KeyBoardUtils.closeKeybord(etHistoryData, getContext());
                PickDialogUtils.getInstance().getChooseDialog(getContext(), "请选择设备", toolList, new ChooseDialogCallBack() {
                    @Override
                    public void onClickSuccess(String choose) {
                        toolName = choose;
                        tvTooName.setText(toolName);
                    }
                });

                break;
        }
    }
}
