package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.AddressListFunction;
import com.example.huoshangkou.jubowan.adapter.AddressListAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.AddressListBean;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.SharedValueConstant;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：AddressListActivity
 * 描述：地址管理界面
 * 创建时间：2017-02-14  15:21
 */

public class AddressListActivity extends BaseActivity {
    //地址管理适配器
    AddressListAdapter addressListAdapter;
    //数据
    List<AddressListBean> listAddress;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_refresh)
    ListView lvRefresh;
    @Bind(R.id.x_refresh)
    SmartRefreshLayout xRefresh;

    //如果不为空
    private String addressChoose = "";

    @Override
    public int initLayout() {
        return R.layout.activity_address_list;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {

        tvTitle.setText("地址管理");
        listAddress = new ArrayList<>();
        addressChoose = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);

        //加载数据操作
        addressListAdapter = new AddressListAdapter(this, listAddress, R.layout.item_address_layout);
        AddressListFunction.getInstance().setMorenAddress(lvRefresh, addressListAdapter, listAddress, xRefresh, this);

        //地址选择
        lvRefresh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //如果为空 就不进行地址选择
                if (!StringUtils.isNoEmpty(addressChoose)) {
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra(IntentUtils.getInstance().ADDRESS_NAME, listAddress.get(i).getProvinces() + " " + listAddress.get(i).getDetailAddress());
                intent.putExtra(IntentUtils.getInstance().ADDRESS_ID, listAddress.get(i).getAdressID() + "");
                intent.putExtra(IntentUtils.getInstance().ADDRESS_LINK_MAN, listAddress.get(i).getLinkMan());
                intent.putExtra(IntentUtils.getInstance().ADDRESS_LINK_TEL, listAddress.get(i).getLinkTel());
                setResult(105, intent);
                AddressListActivity.this.finish();
            }
        });
    }

    //点击事件
    @OnClick({R.id.tv_add_address, R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            //地址编辑
            case R.id.tv_add_address:
                IntentUtils.getInstance().toActivity(this, AddressEditActivity.class);
                break;
            //返回
            case R.id.ll_back:
                this.finish();
                break;
        }
    }

    //编辑收货地址后进行更新
    @Override
    protected void onRestart() {
        super.onRestart();
        //如果不为空就重新刷新地址数据
        String addressEdit = (String) SharedPreferencesUtils.getInstance().get(this, SharedKeyConstant.getInstance().ADD_ADDRESS, SharedValueConstant.getInstance().SAVE_ADDRESS);
        if (StringUtils.isNoEmpty(addressEdit)) {
            listAddress.clear();
            AddressListFunction.getInstance().getAddressListData(listAddress, addressListAdapter, xRefresh);
            //添加空的标识
            SharedPreferencesUtils.getInstance().put(this, SharedKeyConstant.getInstance().ADD_ADDRESS, "");
        }
    }
}
