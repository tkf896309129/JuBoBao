package com.example.huoshangkou.jubowan.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.LibraryListAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.JuSchoolHomeBean;
import com.example.huoshangkou.jubowan.bean.LibraryListBean;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：LibraryListActivity
 * 描述：
 * 创建时间：2019-04-09  13:37
 */

public class LibraryListActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_library_list)
    ListView lvLibraryList;

    private int page = 1;
    private String type = "";
    List<LibraryListBean.DBean.ResultBean> list = new ArrayList<>();
    LibraryListAdapter libraryListAdapter;

    @Override
    public int initLayout() {
        return R.layout.activity_library_list;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        type = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        String title = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        tvTitle.setText(title);
        libraryListAdapter = new LibraryListAdapter(this, list, R.layout.item_library_list);
        lvLibraryList.setAdapter(libraryListAdapter);
        lvLibraryList.setDividerHeight(0);

        lvLibraryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IntentUtils.getInstance().toWebActivity(getContext(), UrlConstant.getInstance().BOOK_DETAIL + list.get(i).getID(), list.get(i).getTitle());
            }
        });

        getData();
    }

    public void getData() {
        //http://192.168.10.120/webapi/ServiceInterface/JuboBao/JbCollege.asmx/GetBookType
        //UrlConstant.getInstance().JU_SCHOOL_URL + "GetBookType"
        Map<String, String> map = new HashMap<>();
        map.put("type", type);
        map.put("pageSize", 20 + "");
        map.put("pageIndex", page + "");
        LogUtils.e(UrlConstant.getInstance().JU_SCHOOL_URL + "GetBookList?type=" + type + "&pageSize=20&pageIndex=" + page);
        OkhttpUtil.getInstance().basePostCall(this, map, UrlConstant.getInstance().JU_SCHOOL_URL + "GetBookList", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                LibraryListBean listBean = JSON.parseObject(str, LibraryListBean.class);
                list.addAll(listBean.getD().getResult());
                libraryListAdapter.notifyDataSetChanged();
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
