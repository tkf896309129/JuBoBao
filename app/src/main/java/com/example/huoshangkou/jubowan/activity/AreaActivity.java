package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.AreaAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.BankHangBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：AreaActivity
 * 描述：
 * 创建时间：2018-09-25  15:27
 */

public class AreaActivity extends BaseActivity {
    @Bind(R.id.lv_area)
    ListView lvArea;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    AreaAdapter areaAdapter;
    List<String> listArea = new ArrayList<>();
    private String province = "";
    //是否是选择城市
    private boolean isCity = false;

    //是否是银行
    private boolean isBank = false;

    @Override
    public int initLayout() {
        return R.layout.activity_area;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().areaList);
        tvTitle.setText("省份");
        province = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        String title = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        areaAdapter = new AreaAdapter(this, listArea, R.layout.item_area);
        lvArea.setAdapter(areaAdapter);
        lvArea.setDividerHeight(0);

        ArrayList<String> list = (ArrayList<String>) getIntent().getSerializableExtra(IntentUtils.getInstance().BEAN_TYPE);
        if (list != null) {
            isBank = true;
            listArea.addAll(list);
            tvTitle.setText(title);
            areaAdapter.notifyDataSetChanged();
        }

        //城市
        if(!isBank){
            if (StringUtils.isNoEmpty(province)) {
                isCity = true;
                tvTitle.setText("城市");
            }
            if (isCity) {
                getAreaList("2", province);
            } else {
                getAreaList("1", "");
            }
        }


        lvArea.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(isBank){
                    Intent intent = new Intent();
                    intent.putExtra(IntentUtils.getInstance().TYPE,listArea.get(i));
                    setResult(1,intent);
                    AreaActivity.this.finish();
                    return;
                }

                if (isCity) {
                    SharedPreferencesUtils.getInstance().put(AreaActivity.this, "jbArea", province + "," + listArea.get(i));
                    ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().areaList);
                    return;
                }
                IntentUtils.getInstance().toActivity(AreaActivity.this, AreaActivity.class, listArea.get(i));
            }
        });

    }

    //获取城市列表
    public void getAreaList(String type, String province) {
        OkhttpUtil.getInstance().setUnCacheData(this, "数据加载中", UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_IOUS_BAND_ADDRESS
                + FieldConstant.getInstance().PROVINCE_NO_S + "=" + EncodeUtils.getInstance().getEncodeString(province) + "&"
                + FieldConstant.getInstance().TYPE + "=" + EncodeUtils.getInstance().getEncodeString(type), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                BankHangBean bankHangBean = JSON.parseObject(json, BankHangBean.class);
                listArea.addAll(bankHangBean.getReObj());
                areaAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail() {

            }
        });
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
