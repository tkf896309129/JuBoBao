package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.WriteListAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.base.BaseApp;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.bean.WriteListBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.db.WriteDbListBean;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：WriteListActivity
 * 描述：
 * 创建时间：2017-08-09  14:07
 */

public class WriteListActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_write_list)
    ListView lvWriteList;
    @Bind(R.id.tv_right)
    TextView tvRight;

    WriteListAdapter writeListAdapter;
    String[] title = {"基础信息", "问题及解决办法", "配件情况", "设备清单", "服务费用合计", "总结"};
    String[] content = {"添加基础信息", "  添加问题及解决办法", "  添加配件情况", "  添加设备清单", "  添加服务费用合计", "  添加总结"};

    List<WriteListBean> list;
    private String orderId = "";
    WriteDbListBean listBean;
    private final int BASEMESSAGE = 1;
    private final int PROBLEM = 2;
    private final int TOOL_DESC = 3;
    private final int TOOL_DETAIL = 4;
    private final int ALL_PRICE = 5;
    private final int CONCLUSION = 6;

    //月产能说明
    private String monthPro = "";
    //电费说明
    private String electPro = "";
    //总结
    private String conclusioin = "";
    //服务器中出现的问题
    private String serviceProblem = "";
    //设备品牌
    private String toolBrand = "";
    //设备明细
    private String toolDetail = "";
    //采购时间
    private String buyTime = "";
    //平台客户签字
    private String userSign = "";
    //服务员到达时间
    private String arriveTime = "";
    //服务厂家名称
    private String companyName = "";
    //服务项目
    private String servicePro = "";
    //费用明细
    private String priceDetail = "";
    //服务员出发时间
    private String startTime = "";
    //配件详情
    private String accessoryDetail = "";
    //售后服务人员
    private String serveStaff = "";
    //基础信息
    private String baseMessageStr = "";

    @Override
    public int initLayout() {
        return R.layout.activity_write_list;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("服务报告单");
        tvRight.setText("保存");
        list = new ArrayList<>();
        listBean = new WriteDbListBean();
        listBean.setId(orderId);
        setData();

        orderId = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);

        writeListAdapter = new WriteListAdapter(getContext(), list, R.layout.item_write_list);
        lvWriteList.setAdapter(writeListAdapter);
        lvWriteList.setDividerHeight(0);

        lvWriteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    //基础信息
                    case 0:
                        Intent intent = new Intent(getContext(), BaseMessageActivity.class);
                        intent.putExtra(IntentUtils.getInstance().TYPE, baseMessageStr);
                        startActivityForResult(intent, BASEMESSAGE);
                        //                        IntentUtils.getInstance().toActivity(getContext(), BaseMessageActivity.class);
                        break;
                    //问题及解决办法
                    case 1:
                        Intent intent_1 = new Intent(getContext(), WriteListDetailActivity.class);
                        intent_1.putExtra(IntentUtils.getInstance().TYPE, "请详细描述问题及解决方法");
                        intent_1.putExtra(IntentUtils.getInstance().VALUE, PROBLEM);
                        intent_1.putExtra(IntentUtils.getInstance().STR, serviceProblem);
                        startActivityForResult(intent_1, PROBLEM);
                        //                        IntentUtils.getInstance().toActivity(getContext(), WriteListDetailActivity.class, "请详细描述问题及解决方法");
                        break;
                    //配件情况
                    case 2:
                        Intent intent_2 = new Intent(getContext(), ToolDescActivity.class);
                        intent_2.putExtra(IntentUtils.getInstance().TYPE, accessoryDetail);
                        startActivityForResult(intent_2, TOOL_DESC);
                        //                        IntentUtils.getInstance().toActivity(getContext(), ToolDescActivity.class);
                        break;
                    //设备清单
                    case 3:
                        Intent intent_3 = new Intent(getContext(), ToolDetailDescActivity.class);
                        intent_3.putExtra(IntentUtils.getInstance().TYPE, toolDetail);
                        startActivityForResult(intent_3, TOOL_DETAIL);
                        //                        IntentUtils.getInstance().toActivity(getContext(), ToolDetailDescActivity.class);
                        break;
                    //服务费用合计
                    case 4:
                        Intent intent_4 = new Intent(getContext(), AllPriceActivity.class);
                        intent_4.putExtra(IntentUtils.getInstance().TYPE, priceDetail);
                        intent_4.putExtra(IntentUtils.getInstance().VALUE, electPro);
                        intent_4.putExtra(IntentUtils.getInstance().STR, monthPro);
                        startActivityForResult(intent_4, ALL_PRICE);
                        //                        IntentUtils.getInstance().toActivity(getContext(), AllPriceActivity.class);
                        break;
                    //总结
                    case 5:
                        Intent intent_5 = new Intent(getContext(), WriteListDetailActivity.class);
                        intent_5.putExtra(IntentUtils.getInstance().TYPE, "请详细描述总结");
                        intent_5.putExtra(IntentUtils.getInstance().VALUE, CONCLUSION);
                        intent_5.putExtra(IntentUtils.getInstance().STR, conclusioin);
                        startActivityForResult(intent_5, CONCLUSION);
                        //                        IntentUtils.getInstance().toActivity(getContext(), WriteListDetailActivity.class, "请详细描述总结");
                        break;
                }
            }
        });

        //搜索并获取本地数据
        getData();
    }

    private void setData() {
        int num = title.length;
        for (int i = 0; i < num; i++) {
            WriteListBean writeListBean = new WriteListBean();
            writeListBean.setTitle(title[i]);
            writeListBean.setContent(content[i]);
            list.add(writeListBean);
        }

        WriteDbListBean listBean = BaseApp.getDbHelper().querySingle(orderId);
        if (listBean == null) {
            return;
        }
        if (StringUtils.isNoEmpty(listBean.getBaseMessage())) {
            list.get(0).setDesc(listBean.getBaseMessage());
        }
        if (StringUtils.isNoEmpty(listBean.getProblem())) {
            list.get(1).setDesc(listBean.getProblem());
        }
        if (StringUtils.isNoEmpty(listBean.getToolDesc())) {
            list.get(2).setDesc(listBean.getToolDesc());
        }
        if (StringUtils.isNoEmpty(listBean.getToolList())) {
            list.get(3).setDesc(listBean.getToolList());
        }
        if (StringUtils.isNoEmpty(listBean.getServicePrice())) {
            list.get(4).setDesc(listBean.getServicePrice());
        }
        if (StringUtils.isNoEmpty(listBean.getConclusion())) {
            list.get(5).setDesc(listBean.getConclusion());
        }
    }

    @OnClick({R.id.ll_back, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_right:
                if (!StringUtils.isNoEmpty(companyName)) {
                    ToastUtils.getMineToast("请添加基础信息");
                    return;
                }
                if (!StringUtils.isNoEmpty(serviceProblem)) {
                    ToastUtils.getMineToast("请添加问题及解决办法");
                    return;
                }
                if (!StringUtils.isNoEmpty(accessoryDetail)) {
                    ToastUtils.getMineToast("请添加配件情况");
                    return;
                }
                if (!StringUtils.isNoEmpty(toolDetail)) {
                    ToastUtils.getMineToast("请添加设备清单");
                    return;
                }
                if (!StringUtils.isNoEmpty(priceDetail)) {
                    ToastUtils.getMineToast("请添加服务费用合计");
                    return;
                }
                if (!StringUtils.isNoEmpty(conclusioin)) {
                    ToastUtils.getMineToast("请添加总结");
                    return;
                }
                commitOrder(orderId, monthPro, electPro, conclusioin, serviceProblem,
                        toolBrand, toolDetail, buyTime, arriveTime, companyName, servicePro, priceDetail, startTime, accessoryDetail, serveStaff);
                //                IntentUtils.getInstance().toActivity(getContext(), CheckServerListActivity.class);
                break;
        }
    }

    String[] descs;
    String[] desc_2s;
    String[] prices;
    String[] detail;
    String desc = "";
    String desc_1 = "";
    String desc_2 = "";
    String desc_3 = "";
    String desc_4 = "";
    String desc_5 = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        switch (requestCode) {
            case BASEMESSAGE:
                desc = data.getStringExtra(IntentUtils.getInstance().TYPE);
                descs = desc.split(":");
                String baseMessage = "厂家名称：" + descs[0] + "\n"
                        + "售后服务人员：" + descs[1] + "\n"
                        + "维修项目：" + descs[2] + "\n"
                        + "采购时间：" + descs[3] + "\n"
                        + "设备品牌型号：" + descs[4] + "\n"
                        + "出发时间：" + descs[5] + "\n"
                        + "离开时间：" + descs[6];
                baseMessageStr = descs[0]
                        + "," + descs[1]
                        + "," + descs[2]
                        + "," + descs[3]
                        + "," + descs[4]
                        + "," + descs[5]
                        + "," + descs[6];
                companyName = descs[0];
                serveStaff = descs[1];
                servicePro = descs[2];
                buyTime = descs[3];
                toolBrand = descs[4];
                startTime = descs[5];
                arriveTime = descs[6];
                list.get(0).setDesc(baseMessage);
                listBean.setBaseMessage(list.get(0).getDesc());
                writeListAdapter.notifyDataSetChanged();
                break;
            case PROBLEM:
                desc_1 = data.getStringExtra(IntentUtils.getInstance().TYPE);
                serviceProblem = desc_1;
                list.get(1).setDesc(desc_1);
                listBean.setProblem(list.get(1).getDesc());
                writeListAdapter.notifyDataSetChanged();
                break;
            case TOOL_DESC:
                desc_2 = data.getStringExtra(IntentUtils.getInstance().TYPE);
                desc_2s = desc_2.split(":");
                String desc_2str = "配件名称：" + desc_2s[0] + "\n"
                        + "配件型号：" + desc_2s[1] + "\n"
                        + "单位：" + desc_2s[2] + "\n"
                        + "数量：" + desc_2s[3] + "\n"
                        + "单价：" + desc_2s[4] + "\n"
                        + "总金额：" + desc_2s[5];
                accessoryDetail = desc_2s[0]
                        + "," + desc_2s[1]
                        + "," + desc_2s[2]
                        + "," + desc_2s[3]
                        + "," + desc_2s[4]
                        + "," + desc_2s[5];
                list.get(2).setDesc(desc_2str);
                listBean.setToolDesc(list.get(2).getDesc());
                writeListAdapter.notifyDataSetChanged();
                break;
            case TOOL_DETAIL:
                desc_3 = data.getStringExtra(IntentUtils.getInstance().TYPE);
                detail = desc_3.split(":");
                String details = "配件名称：" + detail[0] + "\n"
                        + "数量：" + detail[1] + "\n"
                        + "品牌：" + detail[2] + "\n"
                        + "类型：" + detail[3] + "\n"
                        + "购买时间：" + detail[4] + "\n"
                        + "历史数据：" + detail[5] + "\n"
                        + "尺寸：" + detail[6] + "\n"
                        + "备注：" + detail[7];
                toolDetail = detail[0]
                        + "," + detail[1]
                        + "," + detail[2]
                        + "," + detail[3]
                        + "," + detail[4]
                        + "," + detail[5]
                        + "," + detail[6]
                        + "," + detail[7];
                list.get(3).setDesc(details);
                listBean.setToolDesc(list.get(3).getDesc());
                writeListAdapter.notifyDataSetChanged();
                break;
            case ALL_PRICE:
                desc_4 = data.getStringExtra(IntentUtils.getInstance().TYPE);
                prices = desc_4.split(":");
                String price = "材料费用：" + prices[0] + "\n"
                        + "工时费：" + prices[1] + "\n"
                        + "差旅费：" + prices[2] + "\n"
                        + "其他费用：" + prices[3] + "\n"
                        + "电费说明：" + prices[4] + "\n"
                        + "月产能费用说明：" + prices[5];
                monthPro = prices[5];
                electPro = prices[4];
                priceDetail = prices[0]
                        + "," + prices[1]
                        + "," + prices[2]
                        + "," + prices[3];
                list.get(4).setDesc(price);
                listBean.setToolDesc(list.get(4).getDesc());
                writeListAdapter.notifyDataSetChanged();
                break;
            case CONCLUSION:
                desc_5 = data.getStringExtra(IntentUtils.getInstance().TYPE);
                conclusioin = desc_5;
                list.get(5).setDesc(desc_5);
                listBean.setToolDesc(list.get(5).getDesc());
                writeListAdapter.notifyDataSetChanged();
                break;
        }
        putData();
    }

    //提交服务清单
    public void commitOrder(String oderId, String monCap, String electDesc, String opinionSign,
                            String problemMethod, String productBrand,
                            String productDetail, String purchaseTime, String serveArriveTime, String serveCompanyName,
                            String serveItem, String serveMoney, String serveSetupTime, String accessoryDetail, String serveStaff) {
        OkHttpUtils.post()
                .addParams(FieldConstant.getInstance().USER_ID, PersonConstant.getInstance().getUserId())
                .addParams(FieldConstant.getInstance().ORDER_ID, oderId)
                .addParams(FieldConstant.getInstance().PRODUCT_BRAND, productBrand)
                .addParams(FieldConstant.getInstance().CAPACITY_MON, monCap)
                .addParams(FieldConstant.getInstance().ELECTRIC_EXPLAIN, electDesc)
                .addParams(FieldConstant.getInstance().OPINION_SIGNATURE, opinionSign)
                .addParams(FieldConstant.getInstance().PROBLEMS_METHODS, problemMethod)
                .addParams(FieldConstant.getInstance().PRODUCT_DETAIL, productDetail)
                .addParams(FieldConstant.getInstance().PURCHASE_TIME, purchaseTime)
                .addParams(FieldConstant.getInstance().SERVICE_ARRIVE_TIME, serveArriveTime)
                .addParams(FieldConstant.getInstance().SERVE_COMPANY_NAME, serveCompanyName)
                .addParams(FieldConstant.getInstance().SERVE_ITEM, serveItem)
                .addParams(FieldConstant.getInstance().SERVE_MONEY, serveMoney)
                .addParams(FieldConstant.getInstance().ACCESSORY_DETAIL, accessoryDetail)
                .addParams(FieldConstant.getInstance().SERVE_STAFF, serveStaff)
                .addParams(FieldConstant.getInstance().SERVE_SETUP_TIME, serveSetupTime)
                .url(UrlConstant.getInstance().POST_SERVICE)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtils.i(e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                SuccessBean successBean = JSON.parseObject(response, SuccessBean.class);
                //提交成功
                if (successBean.getSuccess() == 1) {
                    ToastUtils.getMineToast("提交成功");
                    WriteListActivity.this.finish();
                    SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().SERVICE_LIST, "yes");
                } else {
                    ToastUtils.getMineToast("提交失败");
                }
            }
        });
    }

    public void putData() {
        SharedPreferencesUtils.getInstance().put(getContext(), orderId + "_0", desc);
        SharedPreferencesUtils.getInstance().put(getContext(), orderId + "_1", desc_1);
        SharedPreferencesUtils.getInstance().put(getContext(), orderId + "_2", desc_2);
        SharedPreferencesUtils.getInstance().put(getContext(), orderId + "_3", desc_3);
        SharedPreferencesUtils.getInstance().put(getContext(), orderId + "_4", desc_4);
        SharedPreferencesUtils.getInstance().put(getContext(), orderId + "_5", desc_5);
    }

    public void getData() {
        desc = (String) SharedPreferencesUtils.getInstance().get(getContext(), orderId + "_0", "");
        if (StringUtils.isNoEmpty(desc)) {
            descs = desc.split(":");
            String baseMessage = "厂家名称：" + descs[0] + "\n"
                    + "售后服务人员：" + descs[1] + "\n"
                    + "维修项目：" + descs[2] + "\n"
                    + "采购时间：" + descs[3] + "\n"
                    + "设备品牌型号：" + descs[4] + "\n"
                    + "出发时间：" + descs[5] + "\n"
                    + "离开时间：" + descs[6];
            baseMessageStr = descs[0]
                    + "," + descs[1]
                    + "," + descs[2]
                    + "," + descs[3]
                    + "," + descs[4]
                    + "," + descs[5]
                    + "," + descs[6];
            companyName = descs[0];
            serveStaff = descs[1];
            servicePro = descs[2];
            buyTime = descs[3];
            toolBrand = descs[4];
            startTime = descs[5];
            arriveTime = descs[6];
            list.get(0).setDesc(baseMessage);
            listBean.setBaseMessage(list.get(0).getDesc());
        }

        desc_1 = (String) SharedPreferencesUtils.getInstance().get(getContext(), orderId + "_1", "");
        if (StringUtils.isNoEmpty(desc_1)) {
            serviceProblem = desc_1;
            list.get(1).setDesc(desc_1);
            listBean.setProblem(list.get(1).getDesc());
        }

        desc_2 = (String) SharedPreferencesUtils.getInstance().get(getContext(), orderId + "_2", "");
        if (StringUtils.isNoEmpty(desc_2)) {
            desc_2s = desc_2.split(":");
            String desc_2str = "配件名称：" + desc_2s[0] + "\n"
                    + "配件型号：" + desc_2s[1] + "\n"
                    + "单位：" + desc_2s[2] + "\n"
                    + "数量：" + desc_2s[3] + "\n"
                    + "单价：" + desc_2s[4] + "\n"
                    + "总金额：" + desc_2s[5];
            accessoryDetail = desc_2s[0]
                    + "," + desc_2s[1]
                    + "," + desc_2s[2]
                    + "," + desc_2s[3]
                    + "," + desc_2s[4]
                    + "," + desc_2s[5];
            list.get(2).setDesc(desc_2str);
            listBean.setToolDesc(list.get(2).getDesc());
        }

        desc_3 = (String) SharedPreferencesUtils.getInstance().get(getContext(), orderId + "_3", "");
        if (StringUtils.isNoEmpty(desc_3)) {
            detail = desc_3.split(":");
            String details = "配件名称：" + detail[0] + "\n"
                    + "数量：" + detail[1] + "\n"
                    + "品牌：" + detail[2] + "\n"
                    + "类型：" + detail[3] + "\n"
                    + "购买时间：" + detail[4] + "\n"
                    + "历史数据：" + detail[5] + "\n"
                    + "尺寸：" + detail[6] + "\n"
                    + "备注：" + detail[7];
            toolDetail = detail[0]
                    + "," + detail[1]
                    + "," + detail[2]
                    + "," + detail[3]
                    + "," + detail[4]
                    + "," + detail[5]
                    + "," + detail[6]
                    + "," + detail[7];
            list.get(3).setDesc(details);
            listBean.setToolDesc(list.get(3).getDesc());
        }

        desc_4 = (String) SharedPreferencesUtils.getInstance().get(getContext(), orderId + "_4", "");
        if (StringUtils.isNoEmpty(desc_4)) {
            prices = desc_4.split(":");
            String price = "材料费用：" + prices[0] + "\n"
                    + "工时费：" + prices[1] + "\n"
                    + "差旅费：" + prices[2] + "\n"
                    + "其他费用：" + prices[3] + "\n"
                    + "电费说明：" + prices[4] + "\n"
                    + "月产能费用说明：" + prices[5];
            monthPro = prices[5];
            electPro = prices[4];
            priceDetail = prices[0]
                    + "," + prices[1]
                    + "," + prices[2]
                    + "," + prices[3];
            list.get(4).setDesc(price);
            listBean.setToolDesc(list.get(4).getDesc());
            writeListAdapter.notifyDataSetChanged();
        }
        desc_5 = (String) SharedPreferencesUtils.getInstance().get(getContext(), orderId + "_5", "");
        if (StringUtils.isNoEmpty(desc_5)) {
            conclusioin = desc_5;
            list.get(5).setDesc(desc_5);
            listBean.setToolDesc(list.get(5).getDesc());
        }
        writeListAdapter.notifyDataSetChanged();
    }
}
