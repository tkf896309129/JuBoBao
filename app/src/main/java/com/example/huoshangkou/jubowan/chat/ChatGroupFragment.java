package com.example.huoshangkou.jubowan.chat;

import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseFragment;
import com.example.huoshangkou.jubowan.inter.OnAddWorkCallBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.EditDialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.KeyBoardUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.MineLoadingDialogUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMGroupManager;
import com.tencent.imsdk.TIMGroupReceiveMessageOpt;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.ext.group.TIMGroupManagerExt;
import com.tencent.imsdk.ext.group.TIMGroupMemberResult;
import com.tencent.imsdk.ext.group.TIMGroupSelfInfo;
import com.tencent.qcloud.tim.uikit.base.ITitleBarLayout;
import com.tencent.qcloud.tim.uikit.component.LineControllerView;
import com.tencent.qcloud.tim.uikit.modules.group.info.GroupInfo;
import com.tencent.qcloud.tim.uikit.modules.group.info.GroupInfoAdapter;
import com.tencent.qcloud.tim.uikit.modules.group.info.GroupInfoLayout;
import com.tencent.qcloud.tim.uikit.modules.group.member.GroupMemberInfo;
import com.tencent.qcloud.tim.uikit.modules.group.member.IGroupMemberRouter;
import com.tencent.qcloud.tim.uikit.utils.DeleteMemberCallBack;
import com.tencent.qcloud.tim.uikit.utils.TUIKitConstants;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 作者：唐先生
 * 包名：com.example.huos hangkou.jubowan.chat
 * 类名：ChatGroupFragment
 * 描述：
 * 创建时间：2020-04-20  11:42
 */

public class ChatGroupFragment extends BaseFragment {
    @Bind(R.id.group_info_layout)
    GroupInfoLayout mGroupInfoLayout;

    private LineControllerView chageGroupName;
    private String groupId;
    private GroupInfo mGroupInfo;
    private boolean isOwner = false;
    private String deleteMessage = "您确定退出群聊?";
    private GroupInfoAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.group_info_fragment;
    }

    @Override
    public void initData() {
        groupId = getArguments().getString(TUIKitConstants.Group.GROUP_ID);
        mGroupInfoLayout.setGroupId(groupId);
        mGroupInfoLayout.setRouter(new IGroupMemberRouter() {
            @Override
            public void forwardListMember(GroupInfo info) {

            }

            @Override
            public void forwardAddMember(GroupInfo info) {
                IntentUtils.getInstance().toGroupAddActivity(getActivity(), "invite", groupId);
            }

            @Override
            public void forwardDeleteMember(GroupInfo info) {

            }
        });
        chageGroupName = mGroupInfoLayout.getChageGroupName();
        chageGroupName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditDialogUtils.getInstance().showEditTextDialog("text", getActivity(), "修改群聊名称", new OnAddWorkCallBack() {
                    @Override
                    public void addWorkExp(String content) {
                        changeName(content);
                    }
                });
            }
        });
        mGroupInfoLayout.getMessageNoHint().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mGroupInfoLayout.getDeleteAllMessage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteMessage();
            }
        });
        mGroupInfoLayout.getQuikeBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CopyIosDialogUtils.getInstance().getIosDialog(getActivity(), deleteMessage, new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        mGroupInfo = mGroupInfoLayout.getmGroupInfo();
                        if (mGroupInfo != null && mGroupInfo.isOwner() && !mGroupInfo.getGroupType().equals("Private")) {
                            isOwner = true;
                            deleteMessage = "您确定解散群聊?";
                        }
                        if (isOwner) {
                            mGroupInfoLayout.getmPresenter().deleteGroup();
                        } else {
                            mGroupInfoLayout.getmPresenter().quitGroup();
                        }
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
            }
        });

        TIMGroupManager.getInstance().getSelfInfo(groupId, new TIMValueCallBack<TIMGroupSelfInfo>() {
            @Override
            public void onError(int i, String s) {

            }

            @Override
            public void onSuccess(TIMGroupSelfInfo timGroupSelfInfo) {
                if (timGroupSelfInfo.getRecvOpt().toString().equals("ReceiveNotNotify")) {
                    mGroupInfoLayout.getMessageNoHint().setChecked(true);
                } else {
                    mGroupInfoLayout.getMessageNoHint().setChecked(false);
                }
            }
        });

        mGroupInfoLayout.getMessageNoHint().setCheckListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.e(groupId,TIMManager.getInstance().getLoginUser());
                TIMGroupManagerExt.ModifyMemberInfoParam param = new TIMGroupManagerExt.ModifyMemberInfoParam(groupId, TIMManager.getInstance().getLoginUser());
                if (b) {
                    param.setReceiveMessageOpt(TIMGroupReceiveMessageOpt.ReceiveNotNotify);
                } else {
                    param.setReceiveMessageOpt(TIMGroupReceiveMessageOpt.ReceiveAndNotify);
                }
                TIMGroupManagerExt.getInstance().modifyMemberInfo(param, new TIMCallBack() {
                    @Override
                    public void onError(int i, String s) {

                    }

                    @Override
                    public void onSuccess() {

                    }
                });
            }
        });

        adapter = mGroupInfoLayout.getAdapter();
        adapter.setMemberCallBack(new DeleteMemberCallBack() {
            @Override
            public void delete(final GroupMemberInfo info, final List<GroupMemberInfo> mGroupMembers, final int i) {
                CopyIosDialogUtils.getInstance().getIosDialog(getActivity(), "是否踢出该成员", new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {

                        deleteMember(info.getAccount(), mGroupMembers, i);
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
            }
        });
    }

    public void deleteMember(String id, final List<GroupMemberInfo> mGroupMembers, final int i) {
        //创建待踢出群组的用户列表
        final AlertDialog alertDialog = MineLoadingDialogUtils.updateDialog(getActivity(), "删除中...");

        ArrayList list = new ArrayList();
        list.add(id);
        TIMGroupManager.DeleteMemberParam param = new TIMGroupManager.DeleteMemberParam(groupId, list);
        TIMGroupManager.getInstance().deleteGroupMember(param, new TIMValueCallBack<List<TIMGroupMemberResult>>() {
            @Override
            public void onError(int code, String desc) {
                alertDialog.dismiss();
            }

            @Override
            public void onSuccess(List<TIMGroupMemberResult> results) { //群组成员操作结果
                for (TIMGroupMemberResult r : results) {
                    if (r.getResult() == 1) {
                        mGroupMembers.remove(i);
                        adapter.notifyDataSetChanged();
                        ToastUtil.toastLongMessage("删除成功");
                    } else {
                        ToastUtil.toastLongMessage("删除失败");
                    }
                    alertDialog.dismiss();
                }
            }
        });
    }

    //删除会话
    public void deleteMessage() {
        CopyIosDialogUtils.getInstance().getIosDialog(getContext(), "是否清空聊天记录", new CopyIosDialogUtils.iosDialogClick() {
            @Override
            public void confimBtn() {
                TIMManager.getInstance().getConversation(TIMConversationType.Group, groupId).deleteLocalMessage(new TIMCallBack() {
                    @Override
                    public void onError(int i, String s) {

                    }

                    @Override
                    public void onSuccess() {
                        ToastUtil.toastLongMessage("删除成功");
                    }
                });
            }

            @Override
            public void cancelBtn() {

            }
        });


    }

    public void changeName(final String name) {
        TIMGroupManager.ModifyGroupInfoParam param = new TIMGroupManager.ModifyGroupInfoParam(groupId);
        param.setGroupName(name);
        TIMGroupManager.getInstance().modifyGroupInfo(param, new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {

            }

            @Override
            public void onSuccess() {
                ToastUtil.toastLongMessage("修改成功");
                KeyBoardUtils.closeKeybord(new EditText(getActivity()), getActivity());
                mGroupInfo = mGroupInfoLayout.getmGroupInfo();
                chageGroupName.setContent(name);
                mGroupInfoLayout.getTitleBar().setTitle(name + "(" + mGroupInfo.getMemberCount() + "人)", ITitleBarLayout.POSITION.MIDDLE);
            }
        });
    }
}
