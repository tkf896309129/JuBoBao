package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.DriverAddAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.DriverBean;
import com.example.huoshangkou.jubowan.inter.OnDeleteCallBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.PhoneFormatCheckUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：DriverAddActivity
 * 描述：
 * 创建时间：2018-04-17  16:36
 */

public class DriverAddActivity extends BaseActivity {

    @Bind(R.id.lv_refresh)
    ListView listView;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    DriverAddAdapter driverAddAdapter;
    ArrayList<DriverBean> listDriver;

    @Override
    public int initLayout() {
        return R.layout.activity_driver_add;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvRight.setText("添加");
        tvTitle.setText("编辑司机");
        listDriver = new ArrayList<>();
        ArrayList<DriverBean> list = getIntent().getParcelableArrayListExtra(IntentUtils.getInstance().LIST);
        if (list != null) {
            listDriver.addAll(list);
        }
        driverAddAdapter = new DriverAddAdapter(this, listDriver, R.layout.item_add_driver);
        listView.setAdapter(driverAddAdapter);
        listView.setDividerHeight(0);

        driverAddAdapter.setDeleteCallBack(new OnDeleteCallBack() {
            @Override
            public void onDelete(String orderId, final int position) {
                CopyIosDialogUtils.getInstance().getIosDialog(getContext(), "是否删除司机", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        listDriver.remove(position);
                        driverAddAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });

            }
        });

    }

    @OnClick({R.id.tv_right, R.id.tv_save, R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_right:
//                IntentUtils.getInstance().toActivity(this,EditDriverActivity.class);
                DriverBean driverBean = new DriverBean();
                listDriver.add(driverBean);
                driverAddAdapter.notifyDataSetChanged();
                break;
            case R.id.tv_save:

                int num = listDriver.size();
                for (int i = 0; i < num; i++) {
                    if (!StringUtils.isNoEmpty(listDriver.get(i).getName()) || !StringUtils.isNoEmpty(listDriver.get(i).getPhone()) || !StringUtils.isNoEmpty(listDriver.get(i).getCarNum())) {
                        ToastUtils.getMineToast("请完善司机信息");
                        return;
                    }
                    if (!PhoneFormatCheckUtils.getInstance().isPhoneLegal(listDriver.get(i).getPhone())) {
                        ToastUtils.getMineToast("请输入正确的手机号");
                        return;
                    }
                }

                Intent intent = new Intent();
                intent.putParcelableArrayListExtra(IntentUtils.getInstance().LIST, listDriver);
                setResult(1, intent);
                this.finish();
                break;
            case R.id.ll_back:
                this.finish();
                break;
        }
    }
}
