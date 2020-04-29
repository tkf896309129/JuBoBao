package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.InternalPhoneAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.InternalPhoneBean;
import com.example.huoshangkou.jubowan.bean.SelectManBean;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.InternalPhoneUtils;
import com.example.huoshangkou.jubowan.widget.ContactItemInterface;
import com.example.huoshangkou.jubowan.widget.ContactListViewImpl;
import com.example.huoshangkou.jubowan.widget.pinyin.PinYin;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：InternationalePhoneActivity
 * 描述：
 * 创建时间：2019-08-23  08:28
 */

public class InternationalePhoneActivity extends BaseActivity {
    @Bind(R.id.lv_inter_phone)
    ContactListViewImpl listView;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    private List<SelectManBean> selectList = new ArrayList<>();
    private List<InternalPhoneBean> phoneList = new ArrayList<>();
    private InternalPhoneAdapter phoneAdapter;

    @Override
    public int initLayout() {
        return R.layout.activity_internal_phone;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("选择归属地");
        phoneList = InternalPhoneUtils.getPhone(this);
        int num = phoneList.size();
        for (int i = 0; i < num; i++) {
            selectList.add(new SelectManBean(phoneList.get(i).getZh()
                    , PinYin.getPinYin(phoneList.get(i).getZh())
                    , phoneList.get(i).getZh()
                    , phoneList.get(i).getCode() + ""
                    , phoneList.get(i).getEn()
                    , phoneList.get(i).getZh()
                    , 1
                    , 1));
        }
        phoneAdapter = new InternalPhoneAdapter(this, R.layout.item_internal_phone, selectList);
        listView.setFastScrollEnabled(true);
        listView.setAdapter(phoneAdapter);
        listView.setDividerHeight(0);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.putExtra(IntentUtils.getInstance().TYPE, selectList.get(i).getId());
                setResult(1, intent);
                InternationalePhoneActivity.this.finish();
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
