package com.example.huoshangkou.jubowan.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.WebActivity;
import com.example.huoshangkou.jubowan.adapter.MyStudyAdapter;
import com.example.huoshangkou.jubowan.base.BaseFragment;
import com.example.huoshangkou.jubowan.bean.StudyBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.fragment
 * 类名：MyStudyFragment
 * 描述：
 * 创建时间：2020-02-25  08:45
 */

public class MyStudyFragment extends BaseFragment {

    @Bind(R.id.lv_study)
    ListView lvStudy;
    @Bind(R.id.smart)
    SmartRefreshLayout smart;


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
    private String roleType = "";
    private String title = "";
    private String type = "";

    public static MyStudyFragment getInstance() {
        MyStudyFragment studyFragment = new MyStudyFragment();
        return studyFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my_study;
    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
        type = getArguments().getString(IntentUtils.getInstance().TYPE);
        roleType = getArguments().getString(IntentUtils.getInstance().VALUE);
        title = getArguments().getString(IntentUtils.getInstance().STR);
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
        myStudyAdapter = new MyStudyAdapter(getActivity(), list, R.layout.item_my_study);
        lvStudy.setAdapter(myStudyAdapter);
        lvStudy.setDividerHeight(0);
        lvStudy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String url = UrlConstant.getInstance().WEB_URL+"http://192.168.1.120:88/serviceagree/NoticeLearning/" + DETAIL_URL + "?id=" + list.get(i).getId() + "&userid=" + PersonConstant.getInstance().getUserId();
                String url = UrlConstant.getInstance().INFORM_DETAIL + DETAIL_URL + "?id=" + list.get(i).getId() + "&userid=" + PersonConstant.getInstance().getUserId();
                IntentUtils.getInstance().toActivity(getContext(), WebActivity.class, url, list.get(i).getTitle(), "file", list.get(i).getId() + "", type,Integer.parseInt(roleType));
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

    //获取制度通知数据
    public void getNoticeData() {
        Map<String, Object> map = new HashMap<>();
        map.put("pageIndex", page);
        map.put("pageSize", 20);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("title", keyWord);
        map.put("roleId", roleType);
        map.put("userId", PersonConstant.getInstance().getUserId());
        String json = JSON.toJSONString(map);
        OkhttpUtil.getInstance().basePostCall(getActivity(), json, UrlConstant.getInstance().COMPANY_INFORM + POST_URL, new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e(str);
                if (page == 1) {
                    list.clear();
                }
                StudyBean studyBean = JSON.parseObject(str, StudyBean.class);
                list.addAll(studyBean.getD().getReList());
                myStudyAdapter.notifyDataSetChanged();
                if (smart == null) {
                    return;
                }
                smart.finishLoadMore();
                smart.finishRefresh();
            }

            @Override
            public void onFail() {
                if (smart == null) {
                    return;
                }
                smart.finishLoadMore();
                smart.finishRefresh();
            }
        });
    }

    //筛选
    @Subscriber(tag = "studyChoose")
    public void chooseData(String str) {
        ToastUtils.getMineToast(str);
        if (StringUtils.isNoEmpty(str)) {
            String[] split = str.split(",");
            keyWord = split[0];
            startTime = split[1];
            endTime = split[2];
        }
        page = 1;
        getNoticeData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
