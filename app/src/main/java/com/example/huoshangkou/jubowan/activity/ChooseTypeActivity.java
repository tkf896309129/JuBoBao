package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Scroller;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.ChooseTypeAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ChooseTypeBean;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：ChooseTypeActivity
 * 描述：故障描述选择
 * 创建时间：2017-03-24  16:11
 */

public class ChooseTypeActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.lv_refresh)
    ListView lvRefresh;
    @Bind(R.id.x_refresh)
    XRefreshView xRefresh;
    @Bind(R.id.ll_no_data)
    LinearLayout llNoData;

    private ChooseTypeAdapter typeAdapter;
    private List<ChooseTypeBean> listType;
    private ArrayList<String> problemList;
    private String problemIntro = "";

    private List<Integer> positionList;

    private Scroller scroller;


    @Override
    public int initLayout() {
        return R.layout.activity_choose_type;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        problemList = getIntent().getStringArrayListExtra(IntentUtils.getInstance().BEAN_TYPE);
        problemIntro = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        listType = new ArrayList<>();
        //记录以及选择的位置
        positionList = new ArrayList<>();

        int num = problemList.size();
        for (int i = 0; i < num; i++) {
            ChooseTypeBean typeBean = new ChooseTypeBean();
            typeBean.setType(problemList.get(i));
            listType.add(typeBean);
        }
        LogUtils.i(problemIntro);
        setCheckMode(problemIntro);

        tvRight.setText("保存");
        tvTitle.setText("故障选择");
        typeAdapter = new ChooseTypeAdapter(getContext(), listType, R.layout.item_choose_type);
        lvRefresh.setAdapter(typeAdapter);
        lvRefresh.setDividerHeight(0);

        xRefresh.setPullRefreshEnable(false);


        //单击
        lvRefresh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ChooseTypeBean typeBean = listType.get(i);
                if (!typeBean.isCheck()) {
                    typeAdapter.setItemClick(i);
                    listType.get(i).setCheck(true);
                    typeAdapter.notifyDataSetChanged();
                } else {
                    typeAdapter.setItemClick(-1);
                    listType.get(i).setCheck(false);
                    typeAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    @OnClick({R.id.ll_back, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_right:
                String str = "";
                int num = listType.size();
                for (int i = 0; i < num; i++) {
                    if (listType.get(i).isCheck()) {
                        str += listType.get(i).getType() + ",";
                    }
                }
                if (!StringUtils.isNoEmpty(str)) {
                    ToastUtils.getMineToast("请选择故障描述");
                    return;
                }

                Intent intent = new Intent();
                intent.putExtra(IntentUtils.getInstance().VALUE, str);
                setResult(1, intent);
                this.finish();
                break;
        }
    }

    //判断哪几个是被选择的
    private void setCheckMode(String intro) {
        String[] split = intro.split(",");
        int num = listType.size();
        int num2 = split.length;
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num2; j++) {
                if (listType.get(i).getType().equals(split[j])) {
                    positionList.add(i);
                    LogUtils.i(i + "");
                    break;
                }
            }
        }

        int size = positionList.size();
        for (int i = 0; i < size; i++) {
            listType.get(positionList.get(i)).setCheck(true);
        }
    }
}
