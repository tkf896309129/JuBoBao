package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.ChildGroupActivity;
import com.example.huoshangkou.jubowan.activity.CustomerManagerActivity;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.CheckGroupBean;
import com.example.huoshangkou.jubowan.chat.ChatActivity;
import com.example.huoshangkou.jubowan.chat.MessageListActivity;
import com.example.huoshangkou.jubowan.chat.helper.ChatHelper;
import com.example.huoshangkou.jubowan.chat.mine.MineMessageListActivity;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.inter.ChooseCheckMan;
import com.example.huoshangkou.jubowan.inter.ChooseCheckTwoMan;
import com.example.huoshangkou.jubowan.inter.OnPositionCallBack;
import com.example.huoshangkou.jubowan.inter.OnSignManCallBack;
import com.example.huoshangkou.jubowan.inter.StringPositionCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.DialogUtils;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.PhoneUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：CheckManAdapter
 * 描述：
 * 创建时间：2019-02-19  14:49
 */

public class CheckManAdapter extends BaseAbstractAdapter<CheckGroupBean.ReObjBean.UserListBean> {
    //是否是通讯录
    private String isAllMember = "";
    private String pic = "";
    private String titleDialog = "";
    private String saleManage = "";
    private String id = "";
    private String groupName = "";
    private boolean isSuper = false;
    private OnPositionCallBack positionCallBack;
    private OnSignManCallBack signManCallBack;

    public CheckManAdapter(Context context, List<CheckGroupBean.ReObjBean.UserListBean> listData, boolean isSuper, int resId, String isAllMember,
                           String titleDialog, String saleManage, String id, String groupName) {
        super(context, listData, resId);
        this.isAllMember = isAllMember;
        this.titleDialog = titleDialog;
        this.isSuper = isSuper;
        this.saleManage = saleManage;
        this.id = id;
        this.groupName = groupName;
    }

    public CheckManAdapter(Context context, List<CheckGroupBean.ReObjBean.UserListBean> listData, boolean isSuper, int resId, String isAllMember, String titleDialog) {
        super(context, listData, resId);
        this.isAllMember = isAllMember;
        this.titleDialog = titleDialog;
        this.isSuper = isSuper;
    }

    public CheckManAdapter(Context context, List<CheckGroupBean.ReObjBean.UserListBean> listData, int resId, String isAllMember, String titleDialog) {
        super(context, listData, resId);
        this.isAllMember = isAllMember;
        this.titleDialog = titleDialog;
    }

    @Override
    public void convert(ViewHolder holder, final CheckGroupBean.ReObjBean.UserListBean bean, final int position) {
        RelativeLayout rlCheckMan = holder.getView(R.id.rl_check_man);
        TextView tvChild = holder.getView(R.id.tv_child);
        TextView tvPhone = holder.getView(R.id.tv_phone);
        final SwipeMenuLayout swipeMenuLayout = holder.getView(R.id.swim_menu);
        //休息
        TextView tvRest = holder.getView(R.id.tv_rest);
        //飞行中
        TextView tvFly = holder.getView(R.id.tv_fly);
        //请假
        TextView tvHoliday = holder.getView(R.id.tv_holiday);
        //会议中
        TextView tvMeting = holder.getView(R.id.tv_meting);
        //出差
        TextView tvWork = holder.getView(R.id.tv_work);
        TextView tvZhiBan = holder.getView(R.id.tv_zhi_ban);
        TextView tvOutWork = holder.getView(R.id.tv_out_work);
        final TextView tvRoleName = holder.getView(R.id.tv_role_name);
        //下班
        TextView tvAfterWoke = holder.getView(R.id.tv_after_woke);
        //旷工
        TextView tvMissWoke = holder.getView(R.id.tv_miss_woke);
        //值班
        TextView tvStillWoke = holder.getView(R.id.tv_still_woke);
        ImageView imgChat = holder.getView(R.id.iv_chat_message);

        tvRoleName.setText(bean.getRole());
        //0：无模式  1：飞行中  2：会议中
        switch (bean.getNowPattern()) {
            case 0:
                tvFly.setVisibility(View.GONE);
                tvMeting.setVisibility(View.GONE);
                break;
            case 1:
                tvFly.setVisibility(View.VISIBLE);
                tvMeting.setVisibility(View.GONE);
                break;
            case 2:
                tvFly.setVisibility(View.GONE);
                tvMeting.setVisibility(View.VISIBLE);
                break;
        }
        //    1：上班  2：休息  3：请假 4：出差 7:下班 8:旷工 9:值班
        switch (bean.getNowState()) {
            case 1:
                tvWork.setVisibility(View.VISIBLE);
                tvRest.setVisibility(View.GONE);
                tvHoliday.setVisibility(View.GONE);
                tvOutWork.setVisibility(View.GONE);
                tvZhiBan.setVisibility(View.GONE);
                tvAfterWoke.setVisibility(View.GONE);
                tvMissWoke.setVisibility(View.GONE);
                tvStillWoke.setVisibility(View.GONE);
                break;
            case 2:
                tvRest.setVisibility(View.VISIBLE);
                tvWork.setVisibility(View.GONE);
                tvHoliday.setVisibility(View.GONE);
                tvOutWork.setVisibility(View.GONE);
                tvZhiBan.setVisibility(View.GONE);
                tvAfterWoke.setVisibility(View.GONE);
                tvMissWoke.setVisibility(View.GONE);
                tvStillWoke.setVisibility(View.GONE);
                break;
            case 3:
                tvHoliday.setVisibility(View.VISIBLE);
                tvWork.setVisibility(View.GONE);
                tvRest.setVisibility(View.GONE);
                tvOutWork.setVisibility(View.GONE);
                tvZhiBan.setVisibility(View.GONE);
                tvAfterWoke.setVisibility(View.GONE);
                tvMissWoke.setVisibility(View.GONE);
                tvStillWoke.setVisibility(View.GONE);
                break;
            case 4:
                tvOutWork.setVisibility(View.VISIBLE);
                tvWork.setVisibility(View.GONE);
                tvRest.setVisibility(View.GONE);
                tvHoliday.setVisibility(View.GONE);
                tvZhiBan.setVisibility(View.GONE);
                tvAfterWoke.setVisibility(View.GONE);
                tvMissWoke.setVisibility(View.GONE);
                tvStillWoke.setVisibility(View.GONE);
                break;
            case 7:
                tvOutWork.setVisibility(View.GONE);
                tvWork.setVisibility(View.GONE);
                tvRest.setVisibility(View.GONE);
                tvHoliday.setVisibility(View.GONE);
                tvAfterWoke.setVisibility(View.VISIBLE);
                tvMissWoke.setVisibility(View.GONE);
                tvStillWoke.setVisibility(View.GONE);
                break;
            case 8:
                tvOutWork.setVisibility(View.GONE);
                tvWork.setVisibility(View.GONE);
                tvRest.setVisibility(View.GONE);
                tvHoliday.setVisibility(View.GONE);
                tvAfterWoke.setVisibility(View.GONE);
                tvMissWoke.setVisibility(View.VISIBLE);
                tvStillWoke.setVisibility(View.GONE);
                break;
            case 9:
                tvOutWork.setVisibility(View.GONE);
                tvWork.setVisibility(View.GONE);
                tvRest.setVisibility(View.GONE);
                tvHoliday.setVisibility(View.GONE);
                tvAfterWoke.setVisibility(View.GONE);
                tvMissWoke.setVisibility(View.GONE);
                tvStillWoke.setVisibility(View.VISIBLE);
                break;
            default:
                tvOutWork.setVisibility(View.GONE);
                tvWork.setVisibility(View.GONE);
                tvRest.setVisibility(View.GONE);
                tvHoliday.setVisibility(View.GONE);
                tvAfterWoke.setVisibility(View.GONE);
                tvMissWoke.setVisibility(View.GONE);
                tvStillWoke.setVisibility(View.GONE);
                break;
        }
        TextView tvEdit = holder.getView(R.id.tv_edit);
        ImageView imgPhone = holder.getView(R.id.iv_phone);
        ImageView imgHead = holder.getView(R.id.iv_check_head);
        ImageView imgCheck = holder.getView(R.id.img_check);
        ImageView imgManage = holder.getView(R.id.iv_manage);
        //操作权限按钮
        final List<String> listOpera = new ArrayList<>();

        if (bean.getAdministrator() == 1 || bean.getAdministrator() == 2) {
            imgManage.setVisibility(View.VISIBLE);
            if (isSuper) {
                listOpera.add("取消管理员");
            }
            listOpera.add("回访记录");
            listOpera.add("分配子部门");
        } else {
            imgManage.setVisibility(View.GONE);
            if (isSuper) {
                listOpera.add("设为管理员");
            }
            listOpera.add("回访记录");
        }
        listOpera.add("成员列表");
        listOpera.add("删除");
        if (StringUtils.isNoEmpty(isAllMember) && (isAllMember.equals("linkMan") || isAllMember.equals("kfMember") || isAllMember.equals("csMan"))) {
            swipeMenuLayout.setSwipeEnable(false);
        } else {
            if (isSuper) {
                swipeMenuLayout.setSwipeEnable(true);
            } else {
                swipeMenuLayout.setSwipeEnable(false);
            }
        }
        if (bean.isCheck()) {
            imgCheck.setVisibility(View.VISIBLE);
        } else {
            imgCheck.setVisibility(View.GONE);
        }
        //通讯录电话
        if (StringUtils.isNoEmpty(isAllMember) && isAllMember.equals("linkMan")) {
            imgPhone.setVisibility(View.VISIBLE);
        }
        imgPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CopyIosDialogUtils.getInstance().getIosDialog(context, "是否拨打：" + bean.getLinkMan() + "\n" + bean.getLoginName(), new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        PhoneUtils.getInstance().callDialog(bean.getLoginName(), context);
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
            }
        });

        if (StringUtils.isNoEmpty(saleManage)) {
            swipeMenuLayout.setSwipeEnable(false);
        }

        rlCheckMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //销售管理
                if (StringUtils.isNoEmpty(saleManage)) {
                    IntentUtils.getInstance().toActivity(context, CustomerManagerActivity.class, listData.get(position).getID() + "", listData.get(position).getLoginName(), id, groupName);
                    return;
                }
                //选择运营人员
                if (StringUtils.isNoEmpty(isAllMember) && isAllMember.equals("kfMember")) {
                    ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().memberList);
                    SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().KF_MEMBER, bean.getLinkMan() + "," + bean.getID());
                    return;
                }
                //考勤管理
                if (StringUtils.isNoEmpty(isAllMember) && isAllMember.equals("sign")) {
                    if (positionCallBack != null) {
                        positionCallBack.onPositionClick(position);
                    }
                    return;
                }
                if (StringUtils.isNoEmpty(isAllMember) && isAllMember.equals("linkMan")) {
//                    CopyIosDialogUtils.getInstance().getIosDialog(context, "是否拨打：" + bean.getLinkMan() + "\n" + bean.getLoginName(), new CopyIosDialogUtils.iosDialogClick() {
//                        @Override
//                        public void confimBtn() {
//                            PhoneUtils.getInstance().callDialog(bean.getLoginName(), context);
//                        }
//
//                        @Override
//                        public void cancelBtn() {
//
//                        }
//                    });
                    return;
                }
                pic = "";
                if (StringUtils.isNoEmpty(bean.getPic1())) {
                    pic = bean.getPic1();
                } else {
                    pic = "noPic";
                }
                if (bean.getNowPattern() == 1 && titleDialog.equals("审批人")) {
                    DialogUtils.getInstance().chooseCheckMan(context, "温馨提示", bean.getLinkMan() + "当前在飞行中,可能无法及时审批您的单子,建议您重新选择" + titleDialog, "选为抄送人后继续选择其他审批人\n", "执意选择该审批人", new ChooseCheckTwoMan() {
                        @Override
                        public void onCheck() {
                            EventBus.getDefault().post(bean.getID() + "," + bean.getLinkMan() + "," + pic, "checkManId");
                            ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().memberList);
                        }

                        @Override
                        public void onCancel() {
//                            ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().memberList);
                            SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().METING_CS_MAN, bean.getID() + "," + bean.getLinkMan() + "," + pic + ",csId");
                            ToastUtils.getMineToast("选为抄送人成功,请继续选择其他审批人");
                        }
                    });
                    return;
                }
                if (bean.getNowPattern() == 2 && titleDialog.equals("审批人")) {
                    DialogUtils.getInstance().chooseCheckMan(context, "温馨提示", bean.getLinkMan() + "当前在会议中,可能无法及时审批您的单子,建议您重新选择" + titleDialog, "选为抄送人后继续选择其他审批人\n", "执意选择该审批人", new ChooseCheckTwoMan() {
                        @Override
                        public void onCheck() {
                            EventBus.getDefault().post(bean.getID() + "," + bean.getLinkMan() + "," + pic, "checkManId");
                            ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().memberList);
                        }

                        @Override
                        public void onCancel() {
//                            ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().memberList);
                            SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().METING_CS_MAN, bean.getID() + "," + bean.getLinkMan() + "," + pic + ",csId");
//                            EventBus.getDefault().post(bean.getID() + "," + bean.getLinkMan() + "," + pic + ",csId", "checkManIdMetting");
                            ToastUtils.getMineToast("选为抄送人成功,请继续选择其他审批人");
                        }
                    });
                    return;
                }

                DialogUtils.getInstance().chooseCheckMan(context, "温馨提示", "是否选择 (" + bean.getLinkMan() + ") 作为" + titleDialog, new ChooseCheckMan() {
                    @Override
                    public void onCheck() {
                        ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().memberList);
                        EventBus.getDefault().post(bean.getID() + "," + bean.getLinkMan() + "," + pic, "checkManId");
                        String csMan = (String) SharedPreferencesUtils.getInstance().get(context, SharedKeyConstant.getInstance().METING_CS_MAN, "");
                        LogUtils.e(csMan);
                        if (StringUtils.isNoEmpty(csMan)) {
                            EventBus.getDefault().post(csMan, "checkManIdMetting");
                            SharedPreferencesUtils.getInstance().put(context, SharedKeyConstant.getInstance().METING_CS_MAN, "");
                        }
                    }
                });
            }
        });

        tvChild.setText(bean.getLinkMan());
//        tvPhone.setText("手机号：" + bean.getLoginName());
        tvPhone.setText(bean.getRole());
        GlideUtils.getInstance().displayCricleImage(context, bean.getPic1(), imgHead);

        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swipeMenuLayout.quickClose();
                DialogUtils.getInstance().getBaseDialog(context, listOpera, new StringPositionCallBack() {
                    @Override
                    public void onStringPosition(String str, int i) {
                        switch (str) {
                            case "设为管理员":
                            case "取消管理员":
                                if (signManCallBack == null) {
                                    return;
                                }
                                signManCallBack.onSetManage(position);
                                break;
                            case "回访记录":
                                if (signManCallBack == null) {
                                    return;
                                }
                                signManCallBack.onBackRecord(position);
                                break;
                            case "分配子部门":
                                IntentUtils.getInstance().toActivity(context, ChildGroupActivity.class, "", listData.get(position).getID() + "");
                                break;
                            case "成员列表":
                                if (signManCallBack == null) {
                                    return;
                                }
                                signManCallBack.onMemberList(position);
                                break;
                            case "删除":
                                if (signManCallBack == null) {
                                    return;
                                }
                                signManCallBack.onDelete(position);
                                break;
                        }
                    }
                });
            }
        });

        imgChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatHelper.getInstance().toPersonChat(context, bean.getID() + "", bean.getLinkMan());
            }
        });

    }

    public void setPositionCallBack(OnPositionCallBack positionCallBack) {
        this.positionCallBack = positionCallBack;
    }

    public void setSignManCallBack(OnSignManCallBack signManCallBack) {
        this.signManCallBack = signManCallBack;
    }
}
