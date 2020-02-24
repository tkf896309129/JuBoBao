package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.MyMoneyBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：MyMoneyActivity
 * 描述：我的佣金界面
 * 创建时间：2017-05-26  10:58
 */

public class MyMoneyActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_price)
    TextView tvPrice;

    private String url = "";
    private String price = "";

    @Override
    public int initLayout() {
        return R.layout.activity_my_money;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("我的佣金");
        ActivityUtils.getInstance().addActivity(this,ActivityUtils.getInstance().forumList);
        getMoney();
    }


    @OnClick({R.id.tv_get_money, R.id.ll_back, R.id.ll_money_rule, R.id.ll_detail})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_get_money:
                IntentUtils.getInstance().toActivity(getContext(), YjMoneyActivity.class,price);
                break;
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.ll_money_rule:
                if (!StringUtils.isNoEmpty(url)) {
                    ToastUtils.getMineToast("获取地址失败");
                    return;
                }

                IntentUtils.getInstance().toWebActivity(getContext(), url, "佣金规则");
                break;
            case R.id.ll_detail:
                IntentUtils.getInstance().toActivity(getContext(), TiXianDetailActivity.class);
                break;
        }
    }

    //获取佣金
    public void getMoney() {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().SHOW_MONEY + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                MyMoneyBean moneyBean = JSON.parseObject(json, MyMoneyBean.class);
                url = moneyBean.getRuleUrl();
                price = moneyBean.getToTalMoney();
                tvPrice.setText(moneyBean.getToTalMoney());
            }

            @Override
            public void onFail() {

            }
        });
    }

}
