package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.CountryCheckAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.CountryCheckBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：CountryCheckActivity
 * 描述：
 * 创建时间：2019-03-27  10:55
 */

public class CountryCheckActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.grid_check)
    GridView gridCheck;

    List<CountryCheckBean.DBean.ResultBean.ChildBean> list = new ArrayList<>();
    CountryCheckAdapter checkAdapter;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.tv_eng)
    TextView tvEnglish;
    @Bind(R.id.iv_back)
    ImageView imgBack;

    @Override
    public int initLayout() {
        return R.layout.activity_country_check;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("直通国检");
        ivRight.setImageResource(R.mipmap.icon_sy_ctc);

        checkAdapter = new CountryCheckAdapter(this, list, R.layout.item_country_check);
        gridCheck.setAdapter(checkAdapter);//+ "userId=" + PersonConstant.getInstance().getUserId()
        gridCheck.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (StringUtils.isNoEmpty(list.get(i).getChildUrl())) {
                    IntentUtils.getInstance().toWebActivity(CountryCheckActivity.this, list.get(i).getChildUrl(), list.get(i).getChildTitle());
                    return;
                }
                //ChildType 0 CCC认证 1 玻璃检测查询
                IntentUtils.getInstance().toActivity(getContext(), CountryApplyActivity.class, list.get(i).getChildType() + "", list.get(i).getChildTitle());
            }
        });
        getCountryData();
    }

    @OnClick({R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
        }
    }

    //获取直通国检相关信息
    public void getCountryData() {
        Map<String, String> map = new HashMap<>();
        map.put("UserID", PersonConstant.getInstance().getUserId());
        OkhttpUtil.getInstance().basePostCall(this, JSON.toJSONString(map), UrlConstant.getInstance().COUNTRY_URL, new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                CountryCheckBean countryCheckBean = JSON.parseObject(str, CountryCheckBean.class);
                //请求成功
                if (countryCheckBean.getD().getErrorCode() == 0) {
                    GlideUtils.getInstance().displayImage(countryCheckBean.getD().getResult().getImg(), getContext(), imgBack);
                    tvContent.setText(countryCheckBean.getD().getResult().getTitle());
                    tvEnglish.setText(countryCheckBean.getD().getResult().getTitleEn());
                    list.addAll(countryCheckBean.getD().getResult().getChild());
                    checkAdapter.notifyDataSetChanged();
                } else {
                    ToastUtils.getMineToast(countryCheckBean.getD().getMsg());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }
}
