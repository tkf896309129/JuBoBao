package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.MyStudyAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.StudyBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：MyStudyActivity
 * 描述：13758158881 225097
 * 创建时间：2019-11-04  09:16
 */

public class MyStudyActivity extends BaseActivity {
    @Bind(R.id.lv_study)
    ListView lvStudy;
    @Bind(R.id.smart)
    SmartRefreshLayout smart;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    List<StudyBean.DBean.ReListBean> list = new ArrayList<>();

    MyStudyAdapter myStudyAdapter;
    private int page = 1;

    //技能学习
    private String SKILL = "GetSkillList";
    private String SKILL_DETAIL = "InstitutionalNotice.html";
    //制度通知
    private String NOTIFY = "GetNoticeList";
    private String NOTIFY_DETAIL = "SkillsLearning.html";

    private String POST_URL = "";
    private String DETAIL_URL = "";
    private String keyWord = "";
    private String startTime = "";
    private String endTime = "";

    @Override
    public int initLayout() {
        return R.layout.activity_my_study;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvRight.setText("筛选");
        String type = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        tvTitle.setText(type);
        switch (type) {
            case "通知制度区":
                POST_URL = NOTIFY;
                DETAIL_URL = SKILL_DETAIL;
                break;
            case "技能学习区":
                DETAIL_URL = NOTIFY_DETAIL;
                POST_URL = SKILL;
                break;
        }

        //http://192.168.1.120:88/serviceagree/NoticeLearning/InstitutionalNotice.html?id=4&userid=10445
        myStudyAdapter = new MyStudyAdapter(this, list, R.layout.item_my_study);
        lvStudy.setAdapter(myStudyAdapter);
        lvStudy.setDividerHeight(0);
        lvStudy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String url = UrlConstant.getInstance().WEB_URL+"http://192.168.1.120:88/serviceagree/NoticeLearning/" + DETAIL_URL + "?id=" + list.get(i).getId() + "&userid=" + PersonConstant.getInstance().getUserId();
                String url = UrlConstant.getInstance().INFORM_DETAIL + DETAIL_URL + "?id=" + list.get(i).getId() + "&userid=" + PersonConstant.getInstance().getUserId();
                IntentUtils.getInstance().toActivity(getContext(), WebActivity.class, url, list.get(i).getTitle());
            }
        });

        smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;//woj
                getNoticeData();
            }
        });
        smart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getNoticeData();
            }
        });
        getNoticeData();
    }

    @OnClick({R.id.ll_back,R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_right:
                Intent intent = new Intent(this, SearchTimeKeyActivity.class);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        switch (requestCode) {
            case 1:
                keyWord = data.getStringExtra(IntentUtils.getInstance().TYPE);
                startTime = data.getStringExtra(IntentUtils.getInstance().VALUE);
                endTime = data.getStringExtra(IntentUtils.getInstance().STR);
                page = 1;
                getNoticeData();
                break;
        }
    }


    //获取制度通知数据
    public void getNoticeData() {
        Map<String, Object> map = new HashMap<>();
        map.put("pageIndex", page);
        map.put("pageSize", 20);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("title", keyWord);
        map.put("userId", PersonConstant.getInstance().getUserId());
        String json = JSON.toJSONString(map);
        OkhttpUtil.getInstance().basePostCall(this, json, UrlConstant.getInstance().COMPANY_INFORM + POST_URL, new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                if (page == 1) {
                    list.clear();
                }
                StudyBean studyBean = JSON.parseObject(str, StudyBean.class);
                list.addAll(studyBean.getD().getReList());
                myStudyAdapter.notifyDataSetChanged();
                if(smart==null){
                    return;
                }
                smart.finishLoadMore();
                smart.finishRefresh();
            }

            @Override
            public void onFail() {
                if(smart==null){
                    return;
                }
                smart.finishLoadMore();
                smart.finishRefresh();
            }
        });
    }
}
