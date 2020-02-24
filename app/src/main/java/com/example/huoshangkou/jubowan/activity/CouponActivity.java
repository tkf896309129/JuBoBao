package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.YhjAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.YhjBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：CouponActivity
 * 描述：
 * 创建时间：2018-12-07  15:11
 */

public class CouponActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;

    YhjAdapter yhjAdapter;
    @Bind(R.id.ll_no_data)
    LinearLayout llNoData;
    @Bind(R.id.lv_refresh)
    ListView lvRefresh;
    @Bind(R.id.x_refresh)
    XRefreshView xRefresh;
    private List<YhjBean.ReObjBean> list = new ArrayList<>();

    @Override
    public int initLayout() {
        return R.layout.activity_coupon;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("优惠券");

        yhjAdapter = new YhjAdapter(this, list, R.layout.item_yhj);
        lvRefresh.setAdapter(yhjAdapter);
        lvRefresh.setDividerHeight(0);
        getYhj();

        lvRefresh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (list.get(i).getState()) {
                    case "未使用":
                        Intent intent = new Intent();
                        intent.putExtra(IntentUtils.getInstance().ID, list.get(i).getId());
                        intent.putExtra(IntentUtils.getInstance().VALUE, list.get(i).getName());
                        setResult(101, intent);
                        CouponActivity.this.finish();
                        break;
                    case "已使用":
                        break;
                    case "已过期":
                        break;
                }

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

    //获取优惠券
    public void getYhj() {
        OkhttpUtil.getInstance().setUnCacheData(this, "数据加载中", UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_USING_COUPON
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                YhjBean yhjBean = JSON.parseObject(json, YhjBean.class);
                list.addAll(yhjBean.getReObj());
                yhjAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail() {

            }
        });
    }


}
