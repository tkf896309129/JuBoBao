package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.CheckGroupAdapter;
import com.example.huoshangkou.jubowan.adapter.CheckManAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.CheckGroupBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnPositionCallBack;
import com.example.huoshangkou.jubowan.inter.SuccessCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.EncodeUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollListView;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：GroupManActivity
 * 描述：组织架构
 * 创建时间：2019-02-19  15:19
 */

public class GroupManActivity extends BaseActivity {
    @Bind(R.id.lv_check_group)
    ScrollListView lvCheckGroup;
    @Bind(R.id.lv_check_man)
    ScrollListView lvCheckMan;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    CheckGroupAdapter checkGroupAdapter;
    CheckManAdapter checkManAdapter;

    List<CheckGroupBean.ReObjBean.DepListBean> groupList = new ArrayList<>();
    List<CheckGroupBean.ReObjBean.UserListBean> manList = new ArrayList<>();

    private String id = "";
    private String title = "";
    private String csR = "";
    private String dialogTitle = "";
    private String ywType = "";

    //是否是业务用款
    private boolean isYwMoney = false;

    @Override
    public int initLayout() {
        return R.layout.activity_group_man;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().memberList);
        id = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        title = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        csR = getIntent().getStringExtra(IntentUtils.getInstance().STR);
        dialogTitle = getIntent().getStringExtra(IntentUtils.getInstance().TXT);
        ywType = getIntent().getStringExtra(IntentUtils.getInstance().YW_TYPE);
        if (StringUtils.isNoEmpty(ywType)) {
            if(!ywType.equals("1")){
                isYwMoney = true;
            }
        }
        tvTitle.setText(title.trim());
        //请求链接

        //部门
        checkGroupAdapter = new CheckGroupAdapter(this, groupList, R.layout.item_ep_group);
        lvCheckGroup.setAdapter(checkGroupAdapter);
        lvCheckGroup.setDividerHeight(0);
        //人员
        checkManAdapter = new CheckManAdapter(this, manList, R.layout.item_ep_child, csR, dialogTitle);
        lvCheckMan.setAdapter(checkManAdapter);
        lvCheckMan.setDividerHeight(0);

        getMemberData();
        checkGroupAdapter.setPositionCallBack(new OnPositionCallBack() {
            @Override
            public void onPositionClick(int i) {
                if(StringUtils.isNoEmpty(ywType) && ywType.equals("1")){
                    IntentUtils.getInstance().toYwActivity(GroupManActivity.this, GroupManActivity.class,groupList.get(i).getID()+"",groupList.get(i).getRoleName(),csR,dialogTitle,"1");
                    return;
                }
                IntentUtils.getInstance().toActivity(GroupManActivity.this, GroupManActivity.class, groupList.get(i).getID() + "", groupList.get(i).getRoleName(), csR, dialogTitle);
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


    //获取员工数据
    public void getMemberData() {
        String url = "";
        if (!isYwMoney) {
            if(!StringUtils.isNoEmpty(ywType)){
                ywType = "";
            }
            url = UrlConstant.getInstance().URL
                    + PostConstant.getInstance().GROUP_PRO
                    + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                    + FieldConstant.getInstance().TYPE + "=" + EncodeUtils.getInstance().getEncodeString(ywType) + "&"
                    + FieldConstant.getInstance().GROUP_ID + "=" + id;
        } else {
            url = UrlConstant.getInstance().URL
                    + PostConstant.getInstance().GROUP_DESIGN_PRO
                    + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                    + FieldConstant.getInstance().TYPE + "=" + EncodeUtils.getInstance().getEncodeString(ywType) + "&"
                    + FieldConstant.getInstance().GROUP_ID + "=" + id;
        }

        OkhttpUtil.getInstance().setUnCacheData(this, "数据加载中", url, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                CheckGroupBean groupBean = JSON.parseObject(json, CheckGroupBean.class);
                if (groupBean.getReObj().getDepList() != null && groupBean.getReObj().getDepList().size() != 0) {
                    groupList.addAll(groupBean.getReObj().getDepList());
                    checkGroupAdapter.notifyDataSetChanged();
                }
                if (groupBean.getReObj().getUserList() != null) {
                    manList.addAll(groupBean.getReObj().getUserList());
                    checkManAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFail() {

            }
        });
    }
}
