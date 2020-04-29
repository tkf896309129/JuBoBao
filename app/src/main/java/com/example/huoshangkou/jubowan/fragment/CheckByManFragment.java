package com.example.huoshangkou.jubowan.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.CheckByManTopAdapter;
import com.example.huoshangkou.jubowan.adapter.SelectAdapter;
import com.example.huoshangkou.jubowan.base.BaseFragment;
import com.example.huoshangkou.jubowan.bean.MemberListBean;
import com.example.huoshangkou.jubowan.bean.SelectManBean;
import com.example.huoshangkou.jubowan.chat.helper.ChatHelper;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.fragment.function.CheckByManHelper;
import com.example.huoshangkou.jubowan.inter.ChooseCheckMan;
import com.example.huoshangkou.jubowan.inter.ChooseCheckTwoMan;
import com.example.huoshangkou.jubowan.inter.OnAddWorkCallBack;
import com.example.huoshangkou.jubowan.inter.OnPositionClick;
import com.example.huoshangkou.jubowan.inter.OnStringPositionCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.DialogUtils;
import com.example.huoshangkou.jubowan.utils.EditDialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.PhoneUtils;
import com.example.huoshangkou.jubowan.utils.SearchUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.widget.ContactItemInterface;
import com.example.huoshangkou.jubowan.widget.ContactListViewImpl;
import com.example.huoshangkou.jubowan.widget.pinyin.PinYin;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMGroupManager;
import com.tencent.imsdk.TIMGroupMemberInfo;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.group.TIMGroupMemberResult;
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;
import com.tencent.qcloud.tim.uikit.modules.chat.GroupChatManagerKit;
import com.tencent.qcloud.tim.uikit.modules.group.info.GroupInfo;
import com.tencent.qcloud.tim.uikit.modules.group.member.GroupMemberInfo;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.checkapply
 * 类名：CheckByManFragment
 * 描述：
 * 创建时间：2016-12-09  10:34
 */
public class CheckByManFragment extends BaseFragment {

    @Bind(R.id.lv_select_man)
    ContactListViewImpl listView;
    @Bind(R.id.rl_tool_bar)
    RelativeLayout rlToolBar;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.recycle_top)
    RecyclerView recyclerView;
    @Bind(R.id.iv_right)
    ImageView imgRight;


    private SelectAdapter selectAdapter;
    private List<SelectManBean> selectList;
    private List<SelectManBean> checkList = new ArrayList<>();

    private String isLinkCard = "";
    private String dialogTitle = "";
    //添加群聊
    private boolean isAddGroup = false;
    //是否是邀请
    private boolean isInvite = false;
    //群组id
    private String groupId = "";
    private List<ContactItemInterface> searchList = new ArrayList<>();

    private CheckByManTopAdapter manTopAdapter;

    public static CheckByManFragment newInstance() {
        CheckByManFragment fragment = new CheckByManFragment();
        return fragment;
    }

    private List<MemberListBean> memberList;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_by_member;
    }

    @Override
    public void initData() {
        selectList = new ArrayList<>();
        imgRight.setImageResource(R.mipmap.search_icon_2);
        memberList = getArguments().getParcelableArrayList("manList");
        isLinkCard = getArguments().getString(IntentUtils.getInstance().TYPE);
        groupId = getArguments().getString(IntentUtils.getInstance().STR);
        String invite = getArguments().getString(IntentUtils.getInstance().VALUE);
        isInvite = StringUtils.isNoEmpty(invite) ? true : false;
        dialogTitle = getArguments().getString(IntentUtils.getInstance().TITLE);
        rlToolBar.setVisibility(View.GONE);
        if (StringUtils.isNoEmpty(isLinkCard) && isLinkCard.equals("addGroup")) {
            isAddGroup = true;
            tvTitle.setText("创建群聊");
            tvRight.setText("确定");
            tvRight.setVisibility(View.VISIBLE);
            rlToolBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        if (memberList == null || memberList.size() == 0) {
            return;
        }
        int num = memberList.size();
        for (int i = 0; i < num; i++) {
            selectList.add(new SelectManBean(memberList.get(i).getLinkMan()
                    , PinYin.getPinYin(memberList.get(i).getLinkMan())
                    , memberList.get(i).getPic1()
                    , memberList.get(i).getID() + ""
                    , memberList.get(i).getRole()
                    , memberList.get(i).getLoginName()
                    , memberList.get(i).getNowState()
                    , memberList.get(i).getNowPattern()));
        }

        selectAdapter = new SelectAdapter(getActivity(), R.layout.city_item, selectList, isAddGroup);
        listView.setFastScrollEnabled(true);
        listView.setAdapter(selectAdapter);
        listView.setDividerHeight(0);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (isAddGroup) {
                    selectList.get(position).setCheck(!selectList.get(position).isCheck());
                    selectAdapter.notifyDataSetChanged();
                    checkList.clear();
                    for (SelectManBean selBean : selectList) {
                        if (selBean.isCheck()) {
                            checkList.add(selBean);
                        }
                    }
                    manTopAdapter.notifyDataSetChanged();
                    recyclerView.scrollToPosition(manTopAdapter.getItemCount() - 1);
                    return;
                }
                if (StringUtils.isNoEmpty(isLinkCard)) {
                    CopyIosDialogUtils.getInstance().getIosDialog(getContext(), "是否拨打：" + selectList.get(position).getDisplayInfo() + "\n" + selectList.get(position).getPhone(), new CopyIosDialogUtils.iosDialogClick() {
                        @Override
                        public void confimBtn() {
                            PhoneUtils.getInstance().callDialog(selectList.get(position).getPhone(), getContext());
                        }

                        @Override
                        public void cancelBtn() {

                        }
                    });
                    return;
                }
                final ContactItemInterface bean = selectList.get(position);
                if (selectList.get(position).getNowPattern() == 1 && dialogTitle.equals("审批人")) {
                    DialogUtils.getInstance().chooseCheckMan(getActivity(), "温馨提示", bean.getDisplayInfo() + "当前在飞行中,可能无法及时审批您的单子,建议您重新选择" + dialogTitle, "选为抄送人后继续选择其他审批人", "执意选择该审批人", new ChooseCheckTwoMan() {
                        @Override
                        public void onCheck() {
                            EventBus.getDefault().post(bean.getId() + "," + bean.getDisplayInfo() + "," + bean.getHeadImagePic(), "checkManId");
                            ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().memberList);
                            String pic = "";
                            if (StringUtils.isNoEmpty(selectList.get(position).getHeadImagePic())) {
                                pic = selectList.get(position).getHeadImagePic();
                            } else {
                                pic = "noPic";
                            }
                            EventBus.getDefault().post(selectList.get(position).getId() + "," + selectList.get(position).getDisplayInfo() + "," + pic, "checkManId");
                        }

                        @Override
                        public void onCancel() {
//                            ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().memberList);
//                            csMan = bean.getID() + "," + bean.getLinkMan() + "," + pic + ",csId";
                            SharedPreferencesUtils.getInstance().put(getActivity(), SharedKeyConstant.getInstance().METING_CS_MAN, bean.getId() + "," + bean.getDisplayInfo() + "," + bean.getHeadImagePic() + ",csId");
                            ToastUtils.getMineToast("选为抄送人成功,请继续选择其他审批人");
                        }
                    });
                    return;
                }
                if (bean.getNowPattern() == 2 && dialogTitle.equals("审批人")) {
                    DialogUtils.getInstance().chooseCheckMan(getActivity(), "温馨提示", bean.getDisplayInfo() + "当前在会议中,可能无法及时审批您的单子,建议您重新选择" + dialogTitle, "选为抄送人后继续选择其他审批人", "执意选择该审批人", new ChooseCheckTwoMan() {
                        @Override
                        public void onCheck() {
                            ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().memberList);
                            String pic = "";
                            if (StringUtils.isNoEmpty(selectList.get(position).getHeadImagePic())) {
                                pic = selectList.get(position).getHeadImagePic();
                            } else {
                                pic = "noPic";
                            }
                            EventBus.getDefault().post(selectList.get(position).getId() + "," + selectList.get(position).getDisplayInfo() + "," + pic, "checkManId");
//                            EventBus.getDefault().post(bean.getId() + "," + bean.getDisplayInfo() + "," + bean.getHeadImagePic(), "checkManId");
                        }

                        @Override
                        public void onCancel() {
//                            ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().memberList);
//                            csMan = bean.getID() + "," + bean.getLinkMan() + "," + pic + ",csId";
                            SharedPreferencesUtils.getInstance().put(getActivity(), SharedKeyConstant.getInstance().METING_CS_MAN, bean.getId() + "," + bean.getDisplayInfo() + "," + bean.getHeadImagePic() + ",csId");
                            ToastUtils.getMineToast("选为抄送人成功,请继续选择其他审批人");
//                            EventBus.getDefault().post(bean.getId() + "," + bean.getDisplayInfo() + "," + bean.getHeadImagePic() + ",csId", "checkManIdMetting");
//                            ToastUtils.getMineToast("选为抄送人成功,请继续选择其他审批人");
                        }
                    });
                    return;
                }

                DialogUtils.getInstance().chooseCheckMan(getActivity(), "温馨提示", "是否选择 (" + selectList.get(position).getDisplayInfo() + ") 作为" + dialogTitle, new ChooseCheckMan() {
                    @Override
                    public void onCheck() {
                        ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().memberList);
                        String pic = "";
                        if (StringUtils.isNoEmpty(selectList.get(position).getHeadImagePic())) {
                            pic = selectList.get(position).getHeadImagePic();
                        } else {
                            pic = "noPic";
                        }
                        EventBus.getDefault().post(selectList.get(position).getId() + "," + selectList.get(position).getDisplayInfo() + "," + pic, "checkManId");
                        String csMan = (String) SharedPreferencesUtils.getInstance().get(getActivity(), SharedKeyConstant.getInstance().METING_CS_MAN, "");
                        LogUtils.e(csMan);
                        if (StringUtils.isNoEmpty(csMan)) {
                            EventBus.getDefault().post(csMan, "checkManIdMetting");
                            SharedPreferencesUtils.getInstance().put(getActivity(), SharedKeyConstant.getInstance().METING_CS_MAN, "");
                        }
                    }
                });
            }
        });

        manTopAdapter = new CheckByManTopAdapter(checkList, getActivity());
        recyclerView.setAdapter(manTopAdapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL));

        manTopAdapter.setPositionClick(new OnPositionClick() {
            @Override
            public void onPositionClick(int position) {
                for (SelectManBean selBean : selectList) {
                    if (selBean.getId().equals(checkList.get(position).getId())) {
                        selBean.setCheck(false);
                        break;
                    }
                }
                selectAdapter.notifyDataSetChanged();
                checkList.remove(position);
                manTopAdapter.notifyDataSetChanged();
            }
        });

    }

    @OnClick({R.id.ll_back, R.id.tv_right, R.id.iv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_right:
                SearchUtils.getInstance().searchDialog(getActivity(), searchList, selectList, isLinkCard, dialogTitle, isAddGroup, new OnStringPositionCallBack() {
                    @Override
                    public void onClick(String type, int position) {
                        for (SelectManBean manBean : selectList) {
                            if (manBean.getId().equals(type)) {
                                manBean.setCheck(true);
                                checkList.add(manBean);
                                break;
                            }
                        }
                        selectAdapter.notifyDataSetChanged();
                        manTopAdapter.notifyDataSetChanged();
                        recyclerView.scrollToPosition(manTopAdapter.getItemCount()-1);
                    }
                });
                break;
            case R.id.ll_back:
                getActivity().finish();
                break;
            case R.id.tv_right:
                int size = selectList.size();
                final List<String> memberList = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    if (selectList.get(i).isCheck()) {
                        memberList.add(selectList.get(i).getID());
                    }
                }
                if (memberList.size() == 0) {
                    ToastUtil.toastLongMessage("请选择加入群聊人员");
                    return;
                }
                if (isInvite) {
                    CopyIosDialogUtils.getInstance().getIosDialog(getActivity(), "是否邀请入群", new CopyIosDialogUtils.iosDialogClick() {
                        @Override
                        public void confimBtn() {
                            inviteMember(memberList);
                        }

                        @Override
                        public void cancelBtn() {

                        }
                    });
                    return;
                }
                EditDialogUtils.getInstance().showEditTextDialog("text", getActivity(), "请输入群组名称", new OnAddWorkCallBack() {
                    @Override
                    public void addWorkExp(String content) {
                        inviteMember(memberList, content);
                    }
                });
                break;
        }
    }

    //邀请成员
    public void inviteMember(List<String> addMembers, String name) {
        final GroupInfo groupInfo = new GroupInfo();
        groupInfo.setChatName(name);
        groupInfo.setGroupName(name);
        int size = addMembers.size();
        ArrayList<GroupMemberInfo> mMembers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            GroupMemberInfo memberInfo = new GroupMemberInfo();
            memberInfo.setAccount(addMembers.get(i));
            mMembers.add(memberInfo);
        }
        groupInfo.setMemberDetails(mMembers);
        groupInfo.setGroupType("Private");
        GroupChatManagerKit.createGroupChat(groupInfo, new IUIKitCallBack() {
            @Override
            public void onSuccess(Object data) {
                getActivity().finish();
                ChatHelper.getInstance().toGroupChat(getActivity(), data.toString(), groupInfo.getGroupName());
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {

            }
        });
    }

    //邀请成员
    public void inviteMember(List<String> list) {
        //回调
        TIMValueCallBack<List<TIMGroupMemberResult>> cb = new TIMValueCallBack<List<TIMGroupMemberResult>>() {
            @Override
            public void onError(int code, String desc) {
                ToastUtils.getMineToast("加入失败" + desc);
            }

            @Override
            public void onSuccess(List<TIMGroupMemberResult> results) { //群组成员操作结果
                for (TIMGroupMemberResult r : results) {
                    LogUtils.e("result: " + r.getResult()  //操作结果:  0:添加失败；1：添加成功；2：原本是群成员
                            + " user: " + r.getUser());    //用户帐号
                }
                getActivity().finish();
                ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().chatList);
            }
        };
        //将 list 中的用户加入群组
        TIMGroupManager.getInstance().inviteGroupMember(
                groupId,   //群组 ID
                list,      //待加入群组的用户列表
                cb);       //回调
    }
}
