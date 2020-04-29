package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.BuyToolAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.BuyPjBean;
import com.example.huoshangkou.jubowan.bean.BuyPjListBean;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：BuyPjActivity
 * 描述：
 * 创建时间：2017-11-24  15:11
 */
public class BuyPjActivity extends BaseActivity {
    List<BuyPjListBean> list;
    BuyToolAdapter toolAdapter;
    @Bind(R.id.grid_view)
    GridView gridView;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Override
    public int initLayout() {
        return R.layout.activity_buy_pj;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        list = new ArrayList<>();

        toolAdapter = new BuyToolAdapter(getContext(), list);
        gridView.setAdapter(toolAdapter);

        tvTitle.setText("买配件");
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IntentUtils.getInstance().toActivity(getContext(), PjDetailListActivity.class,list.get(i).getID(),list.get(i).getClassTitle());
            }
        });

        getPj();
    }

    @OnClick({R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
        }
    }

    //获取配件
    public void getPj() {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(getContext(), UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_PJ_CLASS, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                BuyPjBean pjBean = JSON.parseObject(json, BuyPjBean.class);
                list.addAll(pjBean.getReList());
                toolAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail() {

            }
        });
    }

}
