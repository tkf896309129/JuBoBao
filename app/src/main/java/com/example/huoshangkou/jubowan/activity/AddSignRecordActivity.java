package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.AddSignRecordAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.SignRecordBean;
import com.example.huoshangkou.jubowan.bean.SignRecordListBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：AddSignRecordActivity
 * 描述：补签记录
 * 创建时间：2017-04-18  15:43
 */

public class AddSignRecordActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;

    AddSignRecordAdapter recordAdapter;
    List<SignRecordListBean> list;
    @Bind(R.id.lv_refresh)
    ListView lvRefresh;
    @Bind(R.id.x_refresh)
    XRefreshView xRefresh;
    @Bind(R.id.ll_no_data)
    LinearLayout llNoData;
    private int pageSize = 1;

    @Override
    public int initLayout() {
        return R.layout.activity_add_sign_record;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {

        tvTitle.setText("补签记录");
        list = new ArrayList<>();

        recordAdapter = new AddSignRecordAdapter(getContext(), list, R.layout.item_add_sign_record);
        lvRefresh.setAdapter(recordAdapter);
        lvRefresh.setDividerHeight(0);

        getSignRecord();

        xRefresh.setPullLoadEnable(true);
        xRefresh.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                pageSize = 1;
                list.clear();
                getSignRecord();
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                pageSize++;
                getSignRecord();
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

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


    //补签记录
    public void getSignRecord() {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().SIGN_RECORD_LIST
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().PAGE_SIZE + "=" + pageSize, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SignRecordBean recordBean = JSON.parseObject(json, SignRecordBean.class);
                if (recordBean.getSuccess() == 1) {
                    list.addAll(recordBean.getReList());
                    recordAdapter.notifyDataSetChanged();

                    if (list.size() == 0) {
                        llNoData.setVisibility(View.VISIBLE);
                    } else {
                        llNoData.setVisibility(View.GONE);
                    }
                }
                xRefresh.stopRefresh();
                xRefresh.stopLoadMore();
            }

            @Override
            public void onFail() {

            }
        });
    }
}
