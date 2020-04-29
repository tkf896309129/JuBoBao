package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ReobjDBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：MyWalletActivity
 * 描述：
 * 创建时间：2017-10-18  11:31
 */

public class MyWalletActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.tv_price)
    TextView tvPrice;

    @Override
    public int initLayout() {
        return R.layout.activity_my_wallet;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("我的钱包");
        tvRight.setText("明细");

        getExtraMoney();
    }

    @OnClick({R.id.ll_back, R.id.tv_right, R.id.tv_put_money})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_right:
                IntentUtils.getInstance().toActivity(getContext(), WalletListActivity.class);
                break;
            case R.id.tv_put_money:
                String price = money.substring(0, money.length() - 1);
                LogUtils.i(price);
                if (Double.parseDouble(price) < 100) {
                    CopyIosDialogUtils.getInstance().getErrorDialog(getContext(), "低于100元不能提现", new CopyIosDialogUtils.ErrDialogCallBack() {
                        @Override
                        public void confirm() {

                        }
                    });
                    return;
                }

                IntentUtils.getInstance().toActivity(getContext(), WalletTiXianActivity.class);
                break;
        }
    }

    String money = "";

    @Override
    protected void onRestart() {
        super.onRestart();
        getExtraMoney();
    }

    //获取账户余额
    public void getExtraMoney() {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(getContext(), UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_PERSONAL_WALLET + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    JSONObject jsonObject1 = jsonObject.getJSONObject("ReObj");
                    money = jsonObject1.getString("LuckyMoney");
                    tvPrice.setText(money);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail() {

            }
        });
    }



}
