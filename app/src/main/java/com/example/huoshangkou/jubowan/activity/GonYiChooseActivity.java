package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.PostZbFunction;
import com.example.huoshangkou.jubowan.adapter.GonYiChooseAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.GonYiBean;
import com.example.huoshangkou.jubowan.bean.GonYiListBean;
import com.example.huoshangkou.jubowan.bean.SerMap;
import com.example.huoshangkou.jubowan.bean.ZbResultBean;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnGonYiCallBack;
import com.example.huoshangkou.jubowan.inter.OnPositionCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：GonYiChooseActivity
 * 描述：
 * 创建时间：2019-04-16  16:31
 */

public class GonYiChooseActivity extends BaseActivity {
    @Bind(R.id.lv_gy_choose)
    ScrollListView lvGonYiChoose;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_price)
    TextView tvPrice;

    private String gonYi = "";
    List<GonYiListBean> reList = new ArrayList<>();
    GonYiChooseAdapter chooseAdapter;
    Map<String, String> map;

    @Override
    public int initLayout() {
        return R.layout.activity_gy_choose;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().newZbList);
        tvTitle.setText("加工工艺(多选)");
        SerMap sapMap = (SerMap) getIntent().getSerializableExtra(IntentUtils.getInstance().MAP);
        map = sapMap.getMap();

        chooseAdapter = new GonYiChooseAdapter(this, reList, R.layout.item_gy_choose);
        lvGonYiChoose.setAdapter(chooseAdapter);
        chooseAdapter.setPositionCallBack(new OnPositionCallBack() {
            @Override
            public void onPositionClick(int i) {
                if (reList.get(i).isCheck()) {
                    reList.get(i).setCheck(false);
                } else {
                    reList.get(i).setCheck(true);
                }
                tvPrice.setText(getAllPrice(reList) + "");
                chooseAdapter.notifyDataSetChanged();
            }
        });
        getJGGonYiData();
    }

    @OnClick({R.id.ll_back, R.id.tv_post_zb})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_post_zb:
                ToastUtils.getMineToast("发布招标");
                int num = reList.size();
                for (int i = 0; i < num; i++) {
                    if (reList.get(i).isCheck()) {
                        gonYi += reList.get(i).getTitle() + "," + StringUtils.getNoNullStr(reList.get(i).getContent() )+ ";";
                    }
                }
                map.put("Gongyis", gonYi);

                postProZb();
                break;
        }
    }

    //发布项目招标
    public void postProZb() {
//        String url = "http://192.168.10.120/webapi/ServiceInterface/JuboBao/ProjectRequest.asmx/YpRequestAdd";
        String json = JSON.toJSONString(map);
        String putJson = "{\"model\":" + json + "}";
        LogUtils.e(putJson);
        OkhttpUtil.getInstance().basePostCall(this, putJson, UrlConstant.getInstance().YUAN_FU_URL + "YpRequestAdd", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                ZbResultBean resultBean = JSON.parseObject(str, ZbResultBean.class);
                if (resultBean.getD().getResult().equals("sucess")) {
                    ToastUtils.getMineToast("提交成功");
                    ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().newZbList);
                } else {
                    ToastUtils.getMineToast(resultBean.getD().getMsg());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    //获取加工工艺
    public void getJGGonYiData() {
        PostZbFunction.getInstance().getGonYiData(getContext(), new OnGonYiCallBack() {
            @Override
            public void onSuccess(GonYiBean gonYiBean) {
                reList.addAll(gonYiBean.getReList());
                chooseAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail() {

            }
        });
    }

    //计算价格
    public double getAllPrice(List<GonYiListBean> reList) {
        int num = reList.size();
        double price = 0;
        for (int i = 0; i < num; i++) {
            if (reList.get(i).isCheck()) {
                price += reList.get(i).getToPrice();
            }
        }
        return price;
    }
}
