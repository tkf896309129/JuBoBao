package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.DriverAdapter;
import com.example.huoshangkou.jubowan.adapter.WeiTuoAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.DriverBean;
import com.example.huoshangkou.jubowan.bean.OrderListsBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.bean.WeiTuoBean;
import com.example.huoshangkou.jubowan.bean.WeiTuoListBean;
import com.example.huoshangkou.jubowan.bean.WeiTuoListDetailsBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollListView;
import com.zhy.http.okhttp.OkHttpUtils;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：WeiTuoShuActivity
 * 描述：
 * 创建时间：2018-04-17  08:19
 */

public class WeiTuoShuActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_wei_tuo)
    ScrollListView listView;
    @Bind(R.id.lv_driver)
    ScrollListView listViewDriver;
    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.tv_put_address)
    EditText tvPutAddress;

    @Bind(R.id.tv_get_address)
    EditText tvGetAddress;
    @Bind(R.id.tv_order_num)
    TextView tvOrderNum;
    @Bind(R.id.tv_de_company)
    TextView tvDeCompany;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_right)
    TextView tvRight;

    List<WeiTuoListDetailsBean> list;
    ArrayList<DriverBean> listDriver;
    WeiTuoAdapter weiTuoAdapter;
    DriverAdapter driverAdapter;
    String orderId = "";
    String driverName = "";
    String driverChePai = "";
    String driverTel = "";
    String tiHuo = "";
    String daoHuo = "";
    String deCompany = "";


    @Override
    public int initLayout() {
        return R.layout.activity_wei_tuo;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvRight.setText("提交");
        tvTitle.setText("提货委托书");
        list = new ArrayList<>();
        listDriver = new ArrayList<>();
        orderId = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);


        driverAdapter = new DriverAdapter(this, listDriver, R.layout.item_driver);
        listViewDriver.setAdapter(driverAdapter);
        listViewDriver.setDividerHeight(0);

        listViewDriver.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(WeiTuoShuActivity.this, DriverAddActivity.class);
                intent.putParcelableArrayListExtra(IntentUtils.getInstance().LIST, listDriver);
                startActivityForResult(intent, 1);
            }
        });

        weiTuoAdapter = new WeiTuoAdapter(this, list, R.layout.item_wei_tuo_layout);
        listView.setAdapter(weiTuoAdapter);
        listView.setDividerHeight(0);

        //字体颜色
        String str_1 = "      本公司特别授权以下人员到贵公司指定货仓办理提货签单等相关业务手续，授权有效期：";
        String str_2 = DateUtils.getCurrentWordDate();
        String str_3 = "当日有效。在此期间由以下人员办理的提货业务所发生的一切责任由本公司承担。";

        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        int length = str_1.length();
        spannableStringBuilder.append(str_1);
        spannableStringBuilder.append(str_2);
        spannableStringBuilder.append(str_3);
        //下划线
        UnderlineSpan underlineSpan1 = new UnderlineSpan();
        UnderlineSpan underlineSpan2 = new UnderlineSpan();
        UnderlineSpan underlineSpan3 = new UnderlineSpan();
        spannableStringBuilder.setSpan(underlineSpan1, length + 0, length + 4, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        spannableStringBuilder.setSpan(underlineSpan2, length + 5, length + 7, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        spannableStringBuilder.setSpan(underlineSpan3, length + 8, length + 10, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

        tvContent.setText(spannableStringBuilder);


        getWeiTuoShu(orderId);
    }

    @OnClick({R.id.ll_driver, R.id.tv_right, R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_driver:
                Intent intent = new Intent(WeiTuoShuActivity.this, DriverAddActivity.class);
                intent.putParcelableArrayListExtra(IntentUtils.getInstance().LIST, listDriver);
                startActivityForResult(intent, 1);
                break;
            case R.id.tv_right:
                ToastUtils.getMineToast("提交");

                tiHuo = tvPutAddress.getText().toString().trim();
                if (!StringUtils.isNoEmpty(tiHuo)) {
                    ToastUtils.getMineToast("请输入提货地址");
                    return;
                }
                daoHuo = tvGetAddress.getText().toString().trim();
                if (!StringUtils.isNoEmpty(daoHuo)) {
                    ToastUtils.getMineToast("请输入到货地址");
                    return;
                }

                int num = listDriver.size();
                for (int i = 0; i < num; i++) {
                    driverName += listDriver.get(i).getName() + ",";
                    driverChePai += listDriver.get(i).getCarNum() + ",";
                    driverTel += listDriver.get(i).getPhone() + ",";
                }
                if (driverName.length() < 2 || driverChePai.length() < 2 || driverTel.length() < 2) {
                    ToastUtils.getMineToast("请添加司机信息");
                    return;
                }
                putWeiTuo(orderId, driverName, driverChePai, driverTel, tiHuo, daoHuo, deCompany);
                break;
            case R.id.ll_back:
                this.finish();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (requestCode == 1) {
            listDriver.clear();
            ArrayList<DriverBean> list = data.getParcelableArrayListExtra(IntentUtils.getInstance().LIST);
            listDriver.addAll(list);
            driverAdapter.notifyDataSetChanged();
        }
    }


    //生成委托书
    public void getWeiTuoShu(String id) {
        OkhttpUtil.getInstance().setUnCacheData(this, "数据获取中", UrlConstant.getInstance().URL
                + PostConstant.getInstance().ADD_DELEG
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().ORDER_ID + "=" + id, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                WeiTuoBean weiTuoBean = JSON.parseObject(json, WeiTuoBean.class);
                list.addAll(weiTuoBean.getReObj().getDetails());
                weiTuoAdapter.notifyDataSetChanged();
                deCompany = weiTuoBean.getReObj().getDeCompany();
                tvDeCompany.setText(weiTuoBean.getReObj().getDeCompany());
                tvTime.setText(weiTuoBean.getReObj().getCreateTime());
                tvGetAddress.setText(weiTuoBean.getReObj().getDaoHuo());
                tvPutAddress.setText(weiTuoBean.getReObj().getTiHuo());
                tvOrderNum.setText(weiTuoBean.getReObj().getOrderID());

                String[] name = weiTuoBean.getReObj().getDriverName().split(",");
                String[] tel = weiTuoBean.getReObj().getDriverTel().split(",");
                String[] chePai = weiTuoBean.getReObj().getDriverChePai().split(",");

                int num = name.length;
                for (int i = 0; i < num; i++) {
                    DriverBean driverBean = new DriverBean();
                    if (StringUtils.isNoEmpty(name[i])) {
                        driverBean.setName(name[i]);
                        driverBean.setPhone(tel[i]);
                        driverBean.setCarNum(chePai[i]);
                        listDriver.add(driverBean);
                    }
                }

                if (listDriver.size() == 0) {
                    //司机
                    DriverBean driverBean_1 = new DriverBean();
                    driverBean_1.setName("");
                    driverBean_1.setCarNum("");
                    driverBean_1.setPhone("");

                    listDriver.add(driverBean_1);
                }
                driverAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail() {

            }
        });//
    }

    //提交委托书  
    public void putWeiTuo(String id, String driverName, String driverChePai, String driverTel, String tiHuo, String daoHuo, String deCompany) {
        OkhttpUtil.getInstance().setUnCacheData(this, "数据获取中", UrlConstant.getInstance().URL
                + PostConstant.getInstance().DELEG_ED
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().DRIVER_NAME + "=" + EncodeUtils.getInstance().getEncodeString(driverName) + "&"
                + FieldConstant.getInstance().DRIVER_CHE_PAI + "=" + EncodeUtils.getInstance().getEncodeString(driverChePai) + "&"
                + FieldConstant.getInstance().DRIVER_TEL + "=" + EncodeUtils.getInstance().getEncodeString(driverTel) + "&"
                + FieldConstant.getInstance().TIHUO + "=" + EncodeUtils.getInstance().getEncodeString(tiHuo) + "&"
                + FieldConstant.getInstance().DAOHUO + "=" + EncodeUtils.getInstance().getEncodeString(daoHuo) + "&"
                + FieldConstant.getInstance().DE_COMPANY + "=" + EncodeUtils.getInstance().getEncodeString(deCompany) + "&"
                + FieldConstant.getInstance().ORDER_ID + "=" + id, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    EventBus.getDefault().post("initOrder", "initOrder");
                    WeiTuoShuActivity.this.finish();
                } else {
                    ToastUtils.getMineToast(successBean.getErrMsg());
                }

            }

            @Override
            public void onFail() {

            }
        });
    }
}
