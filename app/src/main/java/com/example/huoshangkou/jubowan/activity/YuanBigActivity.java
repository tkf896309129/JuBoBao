package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.FuBigAdapter;
import com.example.huoshangkou.jubowan.adapter.YuanBigDetailAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.BigFuTypeBean;
import com.example.huoshangkou.jubowan.bean.BigYuanBrandBean;
import com.example.huoshangkou.jubowan.bean.YuanBigDetailBean;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：YuanBigActivity
 * 描述：原片大牌专卖
 * 创建时间：2019-03-25  13:39
 */

public class YuanBigActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_name)
    ListView lvName;
    @Bind(R.id.grid_detail)
    GridView lvDetail;

    List<BigYuanBrandBean.DBean.ResultBean> fuTypeBeanList = new ArrayList<>();
    List<YuanBigDetailBean.DBean.ResultBean> fuTypeDetailList = new ArrayList<>();
    FuBigAdapter fuBigAdapter;
    YuanBigDetailAdapter detailAdapter;

    @Override
    public int initLayout() {
        return R.layout.activity_big_yuan;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("原片大牌专卖");
        fuBigAdapter = new FuBigAdapter(this, fuTypeBeanList, R.layout.item_blue_txt);
        lvName.setAdapter(fuBigAdapter);
        lvName.setDividerHeight(0);
        lvName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                setSingleTypeChoose(i);
                getYuanAreaBrand(fuTypeBeanList.get(i).getBrandName());
            }
        });

        detailAdapter = new YuanBigDetailAdapter(this, fuTypeDetailList, R.layout.item_yuan_big_detail);
        lvDetail.setAdapter(detailAdapter);
        lvDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IntentUtils.getInstance().toActivity(getContext(), BuyYuanActivity.class, "yuan", fuTypeDetailList.get(i).getBrandName());
            }
        });

        getYuanBrand();
    }

    @OnClick({R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
        }
    }

    //设置单选
    public void setSingleTypeChoose(int position) {
        int num = fuTypeBeanList.size();
        for (int i = 0; i < num; i++) {
            fuTypeBeanList.get(i).setCheck(false);
        }
        for (int i = 0; i < num; i++) {
            if (i == position) {
                fuTypeBeanList.get(i).setCheck(true);
                break;
            }
        }
        fuBigAdapter.notifyDataSetChanged();
    }

    //原片品牌
    public void getYuanBrand() {
        OkhttpUtil.getInstance().basePostCall(this, UrlConstant.getInstance().JU_BIG_BRAND + "GetYPBrandList", new StringCallBack() {
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
                if (fuTypeBeanList.size() <= 0) {
                    return;
                }
                setSingleTypeChoose(0);
                getYuanAreaBrand(fuTypeBeanList.get(0).getBrandName());
            }

            @Override
            public void onFail() {

            }
        });
    }

    //原片品牌区域列表
    public void getYuanAreaBrand(String name) {
        Map<String, String> map = new HashMap<>();
        map.put("BrandName", name);
        OkhttpUtil.getInstance().basePostCall(this, map, UrlConstant.getInstance().JU_BIG_BRAND + "GetYPBrandAreaList", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                YuanBigDetailBean detailBean = JSON.parseObject(str, YuanBigDetailBean.class);
                if (detailBean.getD().getErrorCode() != 0) {
                    ToastUtils.getMineToast(detailBean.getD().getMsg());
                    return;
                }
                fuTypeDetailList.clear();
                fuTypeDetailList.addAll(detailBean.getD().getResult());
                detailAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail() {

            }
        });
    }

}
