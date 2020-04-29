package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.StudyChooseAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.utils.IntentUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：StudyChooseActivity
 * 描述：
 * 创建时间：2019-11-04  11:46
 */

public class StudyChooseActivity extends BaseActivity {
    @Bind(R.id.lv_study_choose)
    ListView lvStudyChoose;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    private StudyChooseAdapter chooseAdapter;
    private List<String> list = new ArrayList<>();

    @Override
    public int initLayout() {
        return R.layout.activity_study_choose;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        list.add("通知制度区");
        list.add("技能学习区");
        tvTitle.setText("我的学习");
        chooseAdapter = new StudyChooseAdapter(this, list, R.layout.item_study_choose);
        lvStudyChoose.setAdapter(chooseAdapter);
        lvStudyChoose.setDividerHeight(0);

        lvStudyChoose.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IntentUtils.getInstance().toActivity(getContext(), MyStudyActivity.class, list.get(i));
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
