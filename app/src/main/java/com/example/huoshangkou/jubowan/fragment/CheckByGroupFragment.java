package com.example.huoshangkou.jubowan.fragment;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.GroupManActivity;
import com.example.huoshangkou.jubowan.adapter.CheckGroupAdapter;
import com.example.huoshangkou.jubowan.adapter.CheckManAdapter;
import com.example.huoshangkou.jubowan.adapter.EpCheckGroupAdapter;
import com.example.huoshangkou.jubowan.base.BaseFragment;
import com.example.huoshangkou.jubowan.bean.CheckGroupBean;
import com.example.huoshangkou.jubowan.bean.MemberListBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnPositionCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.view.ScrollListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.checkapply
 * 类名：CheckByGroupFragment
 * 描述：
 * 创建时间：2016-12-09  13:28
 */
public class CheckByGroupFragment extends BaseFragment {
    @Bind(R.id.lv_check_group)
    ScrollListView lvCheckGroup;
    @Bind(R.id.lv_check_man)
    ScrollListView lvCheckMan;

    CheckGroupAdapter checkGroupAdapter;
    CheckManAdapter checkManAdapter;

    List<CheckGroupBean.ReObjBean.DepListBean> groupList = new ArrayList<>();
    List<CheckGroupBean.ReObjBean.UserListBean> manList = new ArrayList<>();

    private List<String> listGroup;
    private List<List<MemberListBean>> listChild;
    private EpCheckGroupAdapter groupAdapter;
    private List<MemberListBean> memberList;
    private String isLinkCard = "";
    private String dialogTitle = "";
    private String csR = "";
    private String type = "";


    public static CheckByGroupFragment newInstance() {
        CheckByGroupFragment fragment = new CheckByGroupFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_check_group;
    }

    @Override
    public void initData() {
        listGroup = new ArrayList<>();
        listChild = new ArrayList<>();
        memberList = getArguments().getParcelableArrayList("manList");

        isLinkCard = getArguments().getString(IntentUtils.getInstance().TYPE);
        dialogTitle = getArguments().getString(IntentUtils.getInstance().TITLE);
        csR = getArguments().getString(IntentUtils.getInstance().CSR);
        type = StringUtils.isNoEmpty(isLinkCard) ? "0" : "1";

        //部门
        checkGroupAdapter = new CheckGroupAdapter(getActivity(), groupList, R.layout.item_ep_group);
        lvCheckGroup.setAdapter(checkGroupAdapter);
        lvCheckGroup.setDividerHeight(0);

        //人员
        checkManAdapter = new CheckManAdapter(getActivity(), manList, R.layout.item_ep_child, csR, dialogTitle);
        lvCheckMan.setAdapter(checkManAdapter);
        lvCheckMan.setDividerHeight(0);

        checkGroupAdapter.setPositionCallBack(new OnPositionCallBack() {
            @Override
            public void onPositionClick(int i) {
                IntentUtils.getInstance().toYwActivity(getActivity(), GroupManActivity.class, groupList.get(i).getID() + "", groupList.get(i).getRoleName(), csR, dialogTitle, "1");
            }
        });


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


//        epListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                // TODO Auto-generated method stub
//                for (int i = 0; i < groupAdapter.getGroupCount(); i++) {
//                    if (groupPosition != i) {
//                        epListView.collapseGroup(i);
//                    }
//                }
//            }
//        });

        getMemberData();
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


    //获取员工数据
    public void getMemberData() {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(getActivity(), UrlConstant.getInstance().URL
                + PostConstant.getInstance().GROUP_PRO
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().TYPE + "=" + type + "&"
                + FieldConstant.getInstance().GROUP_ID + "=", new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                CheckGroupBean groupBean = JSON.parseObject(json, CheckGroupBean.class);
                if (groupBean.getReObj().getDepList() != null) {
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
