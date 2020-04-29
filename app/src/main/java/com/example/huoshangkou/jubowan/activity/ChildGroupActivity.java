package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.ChildGroupAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.ChildGroupBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.bean.SuccessStrBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnPositionCallBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity
 * 类名：ChildGroupActivity
 * 描述：
 * 创建时间：2019-05-29  08:45
 */

public class ChildGroupActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.lv_child_group)
    ListView lvChildGroup;
    @Bind(R.id.tv_right)
    TextView tvRight;

    private String parentId = "";
    private String userId = "";
    List<ChildGroupBean.ReObjBean> groupList = new ArrayList<>();
    private ChildGroupAdapter groupAdapter;

    @Override
    public int initLayout() {
        return R.layout.activity_child_group;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        tvTitle.setText("部门分配");
        tvRight.setText("确定");
        parentId = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        userId = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);

        groupAdapter = new ChildGroupAdapter(this, groupList, R.layout.item_child_group);
        lvChildGroup.setAdapter(groupAdapter);
        lvChildGroup.setDividerHeight(0);

        groupAdapter.setPositionCallBack(new OnPositionCallBack() {
            @Override
            public void onPositionClick(int i) {
                if (groupList.get(i).getIsManager() == 1) {
                    groupList.get(i).setIsManager(0);
                } else {
                    groupList.get(i).setIsManager(1);
                }
                groupAdapter.notifyDataSetChanged();
            }
        });
        lvChildGroup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (groupList.get(i).getIsHaveChild() == 0) {
                    ToastUtils.getMineToast("该部门没有子部门");
                    return;
                }
                IntentUtils.getInstance().toActivity(getContext(), ChildGroupActivity.class, groupList.get(i).getID() + "", userId);
            }
        });
    }

    String depIds = "";

    @OnClick({R.id.ll_back, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_right:
                int num = groupList.size();
                depIds = "";
                for (int i = 0; i < num; i++) {
                    if (groupList.get(i).getIsManager() == 1) {
                        depIds += groupList.get(i).getID() + ",";
                    }
                }
                CopyIosDialogUtils.getInstance().getIosDialog(this, "是否分配", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        addGroup(depIds);
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
                break;
        }
    }

    public void getChildGroup(String parentId) {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(this, UrlConstant.getInstance().URL + PostConstant.getInstance().GET_CHILD_GROUP
                + FieldConstant.getInstance().PARENT_ID + "=" + parentId + "&"
                + FieldConstant.getInstance().MANAGE_USER_ID + "=" + userId, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                groupList.clear();
                ChildGroupBean groupBean = JSON.parseObject(json, ChildGroupBean.class);
                if (groupBean.getReObj() != null && groupBean.getReObj().size() != 0) {
                    groupList.addAll(groupBean.getReObj());
                    groupAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getChildGroup(parentId);
    }

    //添加部门
    public void addGroup(String depIds) {
        String ids = "";
        if (StringUtils.isNoEmpty(depIds)) {
            ids = depIds.substring(0, depIds.length() - 1);
        }
        OkhttpUtil.getInstance().setUnCacheData(this,"正在添加...", UrlConstant.getInstance().URL + PostConstant.getInstance().ADD_MANAGEMENT
                + FieldConstant.getInstance().PARENT_ID + "=" + parentId + "&"
                + FieldConstant.getInstance().DEP_IDS + "=" + ids + "&"
                + FieldConstant.getInstance().MANAGE_USER_ID + "=" + userId, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                LogUtils.e(json);
                SuccessStrBean successBean = JSON.parseObject(json, SuccessStrBean.class);
                if (successBean.getState() == 1) {
                    ToastUtils.getMineToast("分配成功");
                    getChildGroup(parentId);
                } else {
                    ToastUtils.getMineToast("分配失败");
                }
            }

            @Override
            public void onFail() {

            }
        });
    }
}
