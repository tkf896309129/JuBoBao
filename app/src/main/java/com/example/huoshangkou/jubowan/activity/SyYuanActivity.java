package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.SyYuanAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.SelectManBean;
import com.example.huoshangkou.jubowan.bean.SyYuanBean;
import com.example.huoshangkou.jubowan.bean.SyYuanListBean;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnCompanyCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.KeyBoardUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SearchUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.widget.ContactItemInterface;
import com.example.huoshangkou.jubowan.widget.ContactListViewImpl;
import com.example.huoshangkou.jubowan.widget.pinyin.PinYin;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：SyYuanActivity
 * 描述：上游原片厂
 * 创建时间：2017-12-18  08:52
 */

public class SyYuanActivity extends BaseActivity {
    SyYuanAdapter yuanAdapter;
    List<SyYuanListBean> yuanList;

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_company)
    ContactListViewImpl expandableListView;

    private List<SelectManBean> selectList;

    //搜索
    List<ContactItemInterface> searchList;

    private String title = "";

    @Override
    public int initLayout() {
        return R.layout.activity_sy_yuan;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("收款单位");
        yuanList = new ArrayList<>();
        yuanList = getIntent().getParcelableArrayListExtra("manList");
        selectList = new ArrayList<>();
        searchList = new ArrayList<>();
        title = getIntent().getStringExtra(IntentUtils.getInstance().TITLE);

        if (StringUtils.isNoEmpty(title)) {
            tvTitle.setText(title);
        }

        int num = yuanList.size();
        for (int i = 0; i < num; i++) {
            selectList.add(new SelectManBean(yuanList.get(i).getCompany(), PinYin.getPinYin(yuanList.get(i).getCompany())
                    , "", yuanList.get(i).getID() + "", "", yuanList.get(i).getID(),0,0));
        }

        yuanAdapter = new SyYuanAdapter(getContext(), R.layout.city_item, selectList, title);
        expandableListView.setFastScrollEnabled(true);
        expandableListView.setAdapter(yuanAdapter);

        expandableListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.putExtra(IntentUtils.getInstance().STR, selectList.get(i).getDisplayInfo());
                intent.putExtra(IntentUtils.getInstance().ID, selectList.get(i).getId() + "");
                setResult(1, intent);
                SyYuanActivity.this.finish();

            }
        });

    }


    @OnClick({R.id.ll_back, R.id.iv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.iv_right:
                SearchUtils.getInstance().companyDialog(this,title, searchList, selectList, new OnCompanyCallBack() {
                    @Override
                    public void onSuccess(ContactItemInterface contactItemInterface) {
                        Intent intent = new Intent();

                        intent.putExtra(IntentUtils.getInstance().STR, contactItemInterface.getDisplayInfo());
                        intent.putExtra(IntentUtils.getInstance().ID, contactItemInterface.getId() + "");
                        setResult(1, intent);
                        SyYuanActivity.this.finish();
                    }
                });
                break;
        }
    }
}
