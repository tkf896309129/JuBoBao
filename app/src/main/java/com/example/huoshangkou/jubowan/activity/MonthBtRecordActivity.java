package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.MonthBtRecordAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：MonthBtRecordActivity
 * 描述：
 * 创建时间：2019-08-28  14:21
 */

public class MonthBtRecordActivity extends BaseActivity {
    @Bind(R.id.lv_bt_record)
    ListView lvBtRecord;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    List<String> list = new ArrayList<>();
    MonthBtRecordAdapter recordAdapter;

    @Override
    public int initLayout() {
        return R.layout.activity_month_bt_record;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("白条记录");
        for (int i = 0; i < 5; i++) {
            list.add("");
        }
        recordAdapter = new MonthBtRecordAdapter(this, list, R.layout.item_month_bt_record);
        lvBtRecord.setAdapter(recordAdapter);
        lvBtRecord.setDividerHeight(0);
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
