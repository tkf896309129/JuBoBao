package com.example.huoshangkou.jubowan.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.MoreZbFunction;
import com.example.huoshangkou.jubowan.adapter.ZbNewAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ZbBean;
import com.example.huoshangkou.jubowan.bean.ZbListBean;
import com.example.huoshangkou.jubowan.inter.OnZbBeanCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.SmartUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：ZbSearchActivity
 * 描述：
 * 创建时间：2019-04-19  17:31
 */

public class ZbSearchActivity extends BaseActivity {
    @Bind(R.id.et_search)
    EditText etSearch;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.grid_zb_new)
    GridView gridZbNew;
    @Bind(R.id.smart)
    SmartRefreshLayout smart;

    private List<ZbListBean> list = new ArrayList<>();
    private ZbNewAdapter zbNewAdapter;
    private int pageSize = 1;

    @Override
    public int initLayout() {
        return R.layout.activity_zb_search;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("项目招标");
        zbNewAdapter = new ZbNewAdapter(this, list, R.layout.item_new_zb);
        gridZbNew.setAdapter(zbNewAdapter);
        gridZbNew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                IntentUtils.getInstance().toActivity(getContext(), ZbPriceActivity.class);
                switch (list.get(i).getProjectType()) {
                    case "幕墙招标":
                        IntentUtils.getInstance().toActivity(getContext(), NewZbDetailActivity.class, IntentUtils.getInstance().MUQ_ZB, list.get(i).getRequestID() + "");
                        break;
                    case "系统门窗":
                        IntentUtils.getInstance().toActivity(getContext(), NewZbDetailActivity.class, IntentUtils.getInstance().SYSTEM_ZB, list.get(i).getRequestID() + "");
                        break;
                    case "光伏幕墙":
                        IntentUtils.getInstance().toActivity(getContext(), NewZbDetailActivity.class, IntentUtils.getInstance().GF_ZB, list.get(i).getRequestID() + "");
                        break;
                    case "门窗定制招标":
                        IntentUtils.getInstance().toActivity(getContext(), NewZbDetailActivity.class, IntentUtils.getInstance().DOOR_WINDOW, list.get(i).getRequestID() + "");
                        break;
                }
            }
        });
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String search = charSequence.toString();
                pageSize = 1;
                getSearchZb(search);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    //搜索招标数据
    public void getSearchZb(String searchZb) {
        MoreZbFunction.getInstance().searchZbList(getContext(), searchZb, "", "", "", pageSize, new OnZbBeanCallBack() {
            @Override
            public void onSuccess(ZbBean zbBean) {
                if (pageSize == 1) {
                    list.clear();
                }
                list.addAll(zbBean.getReList());
                zbNewAdapter.notifyDataSetChanged();
                SmartUtils.finishSmart(smart);
            }

            @Override
            public void onFail() {
                SmartUtils.finishSmart(smart);
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
