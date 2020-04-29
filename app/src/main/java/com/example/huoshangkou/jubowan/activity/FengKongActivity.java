package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.FengKongAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.FengKongListBean;
import com.example.huoshangkou.jubowan.bean.ReobjDBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：FengKongActivity
 * 描述：
 * 创建时间：2019-07-23  08:57
 */

public class FengKongActivity extends BaseActivity {
    @Bind(R.id.lv_fk)
    ListView lvFengKong;
    @Bind(R.id.tv_title)
    TextView tvTitle;


    List<FengKongListBean.DBean.ResultBean> list = new ArrayList<>();
    FengKongAdapter fengKongAdapter;

    @Override
    public int initLayout() {
        return R.layout.activity_feng_kong;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("企业资料信息");
        fengKongAdapter = new FengKongAdapter(this, list, R.layout.item_feng_kong);
        lvFengKong.setAdapter(fengKongAdapter);
        lvFengKong.setDividerHeight(0);
        lvFengKong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (StringUtils.isNoEmpty(list.get(i).getUrl())) {
                    IntentUtils.getInstance().toWebActivity(getContext(), list.get(i).getUrl() + "?UserID=" + PersonConstant.getInstance().getUserId() + "&type=" + list.get(i).getType(), list.get(i).getTitle());
                    return;
                }
                IntentUtils.getInstance().toActivity(FengKongActivity.this, FengKongPicActivity.class, list.get(i).getType() + "", list.get(i).getTitle());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getListData();
    }

    //获取标题
    public void getListData() {
        Map<String, String> map = new HashMap<>();
        map.put("userid", PersonConstant.getInstance().getUserId());
        OkhttpUtil.getInstance().basePostCall(this, map, UrlConstant.getInstance().FENG_KONG_URL, new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                list.clear();
                FengKongListBean listBean = JSON.parseObject(str, FengKongListBean.class);
                if (listBean.getD().getErrorCode() == 0) {
                    list.addAll(listBean.getD().getResult());
                    fengKongAdapter.notifyDataSetChanged();
                } else {
                    ToastUtils.getMineToast(listBean.getD().getMsg());
                }
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
