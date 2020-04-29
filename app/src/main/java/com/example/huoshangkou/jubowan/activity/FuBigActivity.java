package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.BigFuDetailAdapter;
import com.example.huoshangkou.jubowan.adapter.FuBigAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.BigFuTypeBean;
import com.example.huoshangkou.jubowan.bean.BigYuanBrandBean;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：FuBigActivity
 * 描述：辅料大牌专卖
 * 创建时间：2019-03-25  08:24
 */

public class FuBigActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_name)
    ListView lvName;


    List<BigYuanBrandBean.DBean.ResultBean> fuTypeBeanList = new ArrayList<>();
    FuBigAdapter fuBigAdapter;

    @Override
    public int initLayout() {
        return R.layout.activity_fu_big;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("辅料大牌专卖");
        fuBigAdapter = new FuBigAdapter(this, fuTypeBeanList, R.layout.item_blue_right);
        lvName.setAdapter(fuBigAdapter);
        lvName.setDividerHeight(0);
        lvName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IntentUtils.getInstance().toActivity(getContext(), BuyYuanActivity.class, "fu", fuTypeBeanList.get(i).getBrandName());
            }
        });

        getFuBrand();
    }

    @OnClick({R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
        }
    }

    //原片品牌
    public void getFuBrand() {
        OkhttpUtil.getInstance().basePostCall(this, UrlConstant.getInstance().JU_BIG_BRAND + "GetFuLiaoBrand", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                BigYuanBrandBean bigYuanBrandBean = JSON.parseObject(str, BigYuanBrandBean.class);
                if (bigYuanBrandBean.getD().getErrorCode() != 0) {
                    ToastUtils.getMineToast(bigYuanBrandBean.getD().getMsg());
                    return;
                }
                fuTypeBeanList.addAll(bigYuanBrandBean.getD().getResult());
                fuBigAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail() {

            }
        });
    }

}
