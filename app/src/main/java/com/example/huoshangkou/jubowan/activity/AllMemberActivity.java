package com.example.huoshangkou.jubowan.activity;

import android.os.Message;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.EpCheckGroupAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.MemberBean;
import com.example.huoshangkou.jubowan.bean.MemberListBean;
import com.example.huoshangkou.jubowan.bean.SelectManBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.view.AnimatedExpandableListView;
import com.example.huoshangkou.jubowan.widget.ContactItemInterface;
import com.example.huoshangkou.jubowan.widget.pinyin.PinYin;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：AllMemberActivity
 * 描述：
 * 创建时间：2018-01-09  08:41
 */

public class AllMemberActivity extends BaseActivity {
    @Bind(R.id.ep_group_list_view)
    AnimatedExpandableListView epListView;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    private List<String> listGroup;
    private List<List<MemberListBean>> listChild;
    private EpCheckGroupAdapter groupAdapter;

    //选择审批人
    List<ContactItemInterface> selectList;

    private List<MemberListBean> memberList;

    @Override
    public int initLayout() {
        return R.layout.activity_all_member;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        selectList = new ArrayList<>();
        memberList = new ArrayList<>();
        listGroup = new ArrayList<>();
        listChild = new ArrayList<>();

        tvTitle.setText("通讯录");

        OkhttpUtil.getInstance().setUnCacheData(this, getString(R.string.loading_message), UrlConstant.getInstance().URL + PostConstant.getInstance().GET_EMPLOYEE_LIST
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                MemberBean memberBean = JSON.parseObject(json, MemberBean.class);
                memberList.addAll(memberBean.getReList());
                int num_1 = memberList.size();
                for (int i = 0; i < num_1; i++) {
                    selectList.add(new SelectManBean(memberList.get(i).getLinkMan()
                            , PinYin.getPinYin(memberList.get(i).getLinkMan())
                            , memberList.get(i).getPic1()
                            , memberList.get(i).getID() + ""
                            , memberList.get(i).getRole()
                            ,memberList.get(i).getLoginName()
                            ,memberList.get(i).getNowState()
                            ,memberList.get(i).getNowPattern()));
                }


                if (memberList.size() == 0 || memberList == null) {
                    return;
                }


                //增加部门数据
                int num = memberList.size();
                for (int i = 0; i < num; i++) {
                    if (!isHaveGroup(listGroup, memberList.get(i).getRole())) {
                        listGroup.add(memberList.get(i).getRole());
                    }
                }

                //增加部门每个员工的数据
                int num2 = listGroup.size();
                for (int i = 0; i < num2; i++) {
                    List<MemberListBean> list_Child = new ArrayList<>();
                    int num3 = memberList.size();
                    for (int j = 0; j < num3; j++) {
                        if (listGroup.get(i).equals(memberList.get(j).getRole())) {
                            list_Child.add(memberList.get(j));
                        }
                    }
                    listChild.add(list_Child);
                }


                groupAdapter = new EpCheckGroupAdapter(listGroup, listChild, getContext(), "allMember");
                int width = getWindowManager().getDefaultDisplay().getWidth();
                epListView.setIndicatorBounds(width, width);
                epListView.setAdapter(groupAdapter);

                //取消分割线
                epListView.setDividerHeight(0);

                epListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {

                        if (epListView.isGroupExpanded(i)) {
                            epListView.collapseGroupWithAnimation(i);
                        } else {
                            epListView.expandGroupWithAnimation(i);
                        }
                        return true;
                    }
                });

            }

            @Override
            public void onFail() {

            }
        });
    }

    /**
     * 判断部门里面是否已经有该部门了
     */
    public boolean isHaveGroup(List<String> listGroup, String group) {
        int num = listGroup.size();
        for (int i = 0; i < num; i++) {
            if (StringUtils.isNoEmpty(listGroup.get(i)) && listGroup.get(i).equals(group)) {
                return true;
            }
        }
        return false;
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
