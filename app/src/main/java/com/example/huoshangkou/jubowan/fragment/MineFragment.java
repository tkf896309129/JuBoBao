package com.example.huoshangkou.jubowan.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.AboutUsActivity;
import com.example.huoshangkou.jubowan.activity.AddBankCardActivity;
import com.example.huoshangkou.jubowan.activity.AddressListActivity;
import com.example.huoshangkou.jubowan.activity.ApproveSelectActivity;
import com.example.huoshangkou.jubowan.activity.BaseCheckActivity;
import com.example.huoshangkou.jubowan.activity.BuyApproveActivity;
import com.example.huoshangkou.jubowan.activity.CheckApplyActivity;
import com.example.huoshangkou.jubowan.activity.CheckWorkActivity;
import com.example.huoshangkou.jubowan.activity.ChoosCheckManActivity;
import com.example.huoshangkou.jubowan.activity.CustomerManagerActivity;
import com.example.huoshangkou.jubowan.activity.LoginActivity;
import com.example.huoshangkou.jubowan.activity.MessageActivity;
import com.example.huoshangkou.jubowan.activity.MineZbActivity;
import com.example.huoshangkou.jubowan.activity.MyMoneyActivity;
import com.example.huoshangkou.jubowan.activity.MyRepairActivity;
import com.example.huoshangkou.jubowan.activity.MyTieActivity;
import com.example.huoshangkou.jubowan.activity.MyWalletActivity;
import com.example.huoshangkou.jubowan.activity.PersonBusinessApprove;
import com.example.huoshangkou.jubowan.activity.RepairApproveActivity;
import com.example.huoshangkou.jubowan.activity.SettingActivity;
import com.example.huoshangkou.jubowan.activity.SignManActivity;
import com.example.huoshangkou.jubowan.activity.StudyChooseActivity;
import com.example.huoshangkou.jubowan.activity.YwChooseActivity;
import com.example.huoshangkou.jubowan.activity.YwyApproveActivity;
import com.example.huoshangkou.jubowan.activity.function.LoginFunction;
import com.example.huoshangkou.jubowan.adapter.MineTypeAdapter;
import com.example.huoshangkou.jubowan.base.BaseApp;
import com.example.huoshangkou.jubowan.base.BaseFragment;
import com.example.huoshangkou.jubowan.bean.DReobjBean;
import com.example.huoshangkou.jubowan.bean.MineTypeBean;
import com.example.huoshangkou.jubowan.bean.QuaMenuBean;
import com.example.huoshangkou.jubowan.chat.MessageListActivity;
import com.example.huoshangkou.jubowan.chat.helper.ChatHelper;
import com.example.huoshangkou.jubowan.chat.mine.MineMessageListActivity;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.SharedValueConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.fragment.function.MineFunciont;
import com.example.huoshangkou.jubowan.inter.OnSetUserPicCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.photo.PhotoCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.AddressUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollListView;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageListener;
import com.tencent.qcloud.tim.uikit.modules.chat.GroupChatManagerKit;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationManagerKit;
import com.tencent.qcloud.tim.uikit.utils.NightUtils;
import com.umeng.message.UTrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.fragment
 * 类名：MineFragment
 * 描述：个人中心界面
 * 创建时间：2017-01-04  10:18
 */

public class MineFragment extends BaseFragment implements TIMMessageListener{
    //头像  图片压缩
    @Bind(R.id.iv_head_pic)
    ImageView ivHeadPic;
    @Bind(R.id.tv_approve_state)
    TextView tvApproveState;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.tv_to_approve)
    TextView tvToApprove;
    @Bind(R.id.lv_mine_type)
    ScrollListView lvMineType;
    @Bind(R.id.iv_type)
    ImageView ivType;
    @Bind(R.id.ll_jfw)
    LinearLayout llJfw;
    @Bind(R.id.scroll_mine_view)
    ScrollView scrollView;
    //佣金规则
    @Bind(R.id.rl_money_rule)
    RelativeLayout rlRule;
    @Bind(R.id.iv_bg)
    ImageView imgBg;

    //个人中心类适配器
    MineTypeAdapter typeAdapter;
    //数据集合
    List<QuaMenuBean.DBean.ReListBean> mineList;
    //图片集合买了
    int[] images = {R.drawable.mine_customer_manage, R.drawable.mine_study, R.drawable.mine_wallet, R.drawable.mine_fourm, R.drawable.mine_address, R.drawable.mine_setting,
            R.drawable.mine_jb};
    String[] names = {"客户管理", "我的学习", "我的钱包", "我的论坛", "地址管理", "个人设置", "关于聚玻宝"};

    Map<String, Integer> mineMaps = new HashMap<>();
    int roleTypeManager = -1;
    int depId;
    private String checkId = "2";

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    //获取经纬度
    private String latLong = "";
    String state;
    String type;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initData() {
        GroupChatManagerKit.getInstance();
        //审核状态
        state = (String) SharedPreferencesUtils.getInstance().get(getActivity(), SharedKeyConstant.getInstance().USER_STATE, "");
//        rlRule.setVisibility(View.VISIBLE);
        //集合初始化
        mineList = new ArrayList<>();
        if (NightUtils.isNight()) {
            imgBg.setImageResource(R.mipmap.mine_bg_night);
        } else {
            imgBg.setImageResource(R.mipmap.mine_bg);
        }

        initMap();
//        我的招标
        final String roleType = PersonConstant.getInstance().getRoleType(getActivity());
        if (!(roleType.equals("1") || roleType.equals("2"))) {
            mineList.remove(new MineTypeBean("我的招标"));
        }
        //设置消息监听器，收到新消息时，通过此监听器回调
        TIMManager.getInstance().addMessageListener(this);
        typeAdapter = new MineTypeAdapter(getActivity(), mineList, R.layout.item_new_mine);
        lvMineType.setAdapter(typeAdapter);
        lvMineType.setDividerHeight(0);
        lvMineType.setFocusable(false);

        //点击事件
        lvMineType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                switch (mineList.get(i).getId()) {
                    case 3:
                        IntentUtils.getInstance().toActivity(getActivity(), StudyChooseActivity.class);
                        break;
                    //客户管理
                    case 1:
                        //清除销售员userid
                        SharedPreferencesUtils.getInstance().put(getActivity(), SharedKeyConstant.getInstance().USER_MANAGE_ID, "");
                        SharedPreferencesUtils.getInstance().put(getActivity(), SharedKeyConstant.getInstance().USER_MANAGE_PHONE_ID, "");
//                        IntentUtils.getInstance().toRoleActivity(getActivity(), CustomerManagerActivity.class, roleTypeManager + "", depId + "");
                        if (roleTypeManager == 2 || roleTypeManager == 3) {
                            IntentUtils.getInstance().toRoleTypeActivity(getActivity(), SignManActivity.class, depId + "", "成员列表", "", roleTypeManager + "", "saleManage");
                        } else {
                            IntentUtils.getInstance().toActivity(getActivity(), CustomerManagerActivity.class);
                        }
                        break;
                    //关于聚玻宝
                    case 8:
//                        IntentUtils.getInstance().toActivity(getActivity(), RecordVoiceActivity.class);
//                        IntentUtils.getInstance().toActivity(getActivity(), BaseCheckActivity.class);
                        IntentUtils.getInstance().toActivity(getActivity(), AboutUsActivity.class);
                        break;
                    //个人设置
                    case 7:
                        IntentUtils.getInstance().toActivity(getActivity(), SettingActivity.class);
                        break;
                    //地址管理
                    case 6:
                        IntentUtils.getInstance().toActivity(getActivity(), AddressListActivity.class);
                        break;
                    //我的论坛
                    case 5:
                        IntentUtils.getInstance().toActivity(getActivity(), MyTieActivity.class);
                        break;
                    //我的钱包
                    case 4:
                        IntentUtils.getInstance().toActivity(getActivity(), MyWalletActivity.class);
                        break;
                    //我的贷款
                    case 11:
                        IntentUtils.getInstance().toActivity(getActivity(), YwChooseActivity.class);
//                        if (state.equals(getString(R.string.has_checking))) {
//                            IntentUtils.getInstance().toActivity(getActivity(), BorrowMoneyActivity.class);
//                        } else {
//                            CopyIosDialogUtils.getInstance().getErrorDialog(getContext(), "您的个人信息未认证,请您先去认证", new CopyIosDialogUtils.ErrDialogCallBack() {
//                                @Override
//                                public void confirm() {
//
//                                }
//                            });
//                        }
                        break;
                    //我的审批
                    case R.string.my_check:
                        IntentUtils.getInstance().toActivity(getActivity(), CheckApplyActivity.class, checkId);
                        break;
                    case R.string.my_kq_value:
                        IntentUtils.getInstance().toActivity(getActivity(), CheckWorkActivity.class, latLong);
                        break;
                    case 2:
                        IntentUtils.getInstance().toActivity(getActivity(), MineZbActivity.class);
                        break;
                    case 9:
                        IntentUtils.getInstance().toActivity(getActivity(), MyRepairActivity.class);
                        break;
                    case 12:
                        IntentUtils.getInstance().toActivity(getContext(), ChoosCheckManActivity.class, "isLinkCard");
                        break;
                    //消息列表
                    case 13:
                        IntentUtils.getInstance().toActivity(getActivity(), MineMessageListActivity.class);
//                        IntentUtils.getInstance().toActivity(getActivity(), MessageListActivity.class);
                        break;
                }
            }
        });
        setData();
    }


    //点击事件
    @OnClick({R.id.ll_exit_account, R.id.iv_head_pic, R.id.tv_to_approve, R.id.rl_my_bank,
            R.id.tv_true_money, R.id.iv_message_icon, R.id.rl_money_rule, R.id.ll_kq, R.id.ll_sp, R.id.ll_jfw, R.id.ll_txl})
    public void onClick(View view) {
        switch (view.getId()) {
            //退出当前账号
            case R.id.ll_exit_account:
                CopyIosDialogUtils.getInstance().getIosDialog(getActivity(), getString(R.string.is_exit_account), new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        SharedPreferencesUtils.getInstance().put(getActivity(), SharedKeyConstant.getInstance().LOGIN_STATE, SharedValueConstant.getInstance().LOGIN_FAIL);
                        IntentUtils.getInstance().toActivity(getActivity(), LoginActivity.class);
                        ActivityUtils.getInstance().finishActivity(ActivityUtils.getInstance().activityLoginList);
                        //退出登录
                        AddressUtils.getInstance().saveDefaultAddress("", "", "", "");
                        //清空用户登录信息
                        LoginFunction.getInstance().unSaveLoginMessage(getActivity());
                        ChatHelper.getInstance().loginOutChat();
                        BaseApp.getPush().removeAlias(PersonConstant.getInstance().getUserId(), "UserID", new UTrack.ICallBack() {
                            @Override
                            public void onMessage(boolean b, String s) {
                                if (b) {
                                    LogUtils.i(s);
                                } else {
                                    LogUtils.i("失败");
                                }
                                SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().PUSH, "pushOff");
                            }
                        });
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
                break;
            //头像点击事件
            case R.id.iv_head_pic:
                PhotoUtils.getInstance().getPhotoSelectUtils(getActivity(), new PhotoCallBack() {
                    @Override
                    public void getPhoto(final String path) {
                        //设置头像
                        MineFunciont.getInstance().setUserPic(getActivity(), path, new OnSetUserPicCallBack() {
                            @Override
                            public void onSuccess() {
                                PersonConstant.getInstance().setHeadPic(getActivity(), path);
                                GlideUtils.getInstance().displayCricleImage(getActivity(), path, ivHeadPic);
                                ToastUtils.getMineToast("设置成功");
                            }

                            @Override
                            public void onFail() {

                            }
                        });
                    }
                });
                break;
            //认证
            case R.id.tv_to_approve:
                //判断当前用户身份认证 以及 认证状态
//                IntentUtils.getInstance().toActivity(getActivity(), ApproveSelectActivity.class);
//                state = "未认证";
                //如果是已经审核中 或者已认证的状态 直接跳到对应的认证界面进行数据展示$type $state
//                type ="2";
                if (state.equals(getString(R.string.has_checking))) {
                    switch (type) {
                        case "1":
//                            rlRule.setVisibility(View.GONE);
                            IntentUtils.getInstance().toActivity(getActivity(), BuyApproveActivity.class, "1", state);
                            break;
                        case "2":
//                            rlRule.setVisibility(View.GONE);
                            IntentUtils.getInstance().toActivity(getActivity(), BuyApproveActivity.class, "2", state);
                            break;
                        case "3":
//                            rlRule.setVisibility(View.VISIBLE);
                            IntentUtils.getInstance().toActivity(getActivity(), YwyApproveActivity.class, "3", state);
                            break;
                        case "4":
//                            rlRule.setVisibility(View.VISIBLE);
                            IntentUtils.getInstance().toActivity(getActivity(), YwyApproveActivity.class, "4", state);
                            break;
                        case "5":
//                            rlRule.setVisibility(View.VISIBLE);
                            IntentUtils.getInstance().toActivity(getActivity(), YwyApproveActivity.class, "5", state);
                            break;
                        case "6":
//                            rlRule.setVisibility(View.VISIBLE);
                            IntentUtils.getInstance().toActivity(getActivity(), YwyApproveActivity.class, "6", state);
                            break;
                        case "7":
//                            rlRule.setVisibility(View.GONE);
                            IntentUtils.getInstance().toActivity(getActivity(), RepairApproveActivity.class, "7", state);
                            break;
                        case "8":
//                            rlRule.setVisibility(View.GONE);
                            IntentUtils.getInstance().toActivity(getActivity(), BuyApproveActivity.class, "8", state);
                            break;
                        case "9":
//                            rlRule.setVisibility(View.GONE);
//                            IntentUtils.getInstance().toActivity(getActivity(), PersonBusinessApprove.class, "9", state);
                            IntentUtils.getInstance().toActivity(getActivity(), PersonBusinessApprove.class, "9", state);
                            break;
                    }
                } else if (state.equals(getString(R.string.checking))) {
                    //认证选择界面
                    IntentUtils.getInstance().toActivity(getActivity(), ApproveSelectActivity.class, type, state);
                } else {
                    IntentUtils.getInstance().toActivity(getActivity(), ApproveSelectActivity.class, type, state);
                }
                break;
            //我的银行卡  
            case R.id.rl_my_bank:
                IntentUtils.getInstance().toActivity(getActivity(), AddBankCardActivity.class);
                break;
            //提现
            case R.id.tv_true_money:
//                IntentUtils.getInstance().toActivity(getActivity(), CompanyMoneyActivity.class);
                break;
            case R.id.iv_message_icon:
                IntentUtils.getInstance().toActivity(getActivity(), MessageActivity.class);
                break;
            //佣金规则
            case R.id.rl_money_rule:
                IntentUtils.getInstance().toActivity(getActivity(), MyMoneyActivity.class);
                break;
            case R.id.ll_kq:
                IntentUtils.getInstance().toActivity(getActivity(), CheckWorkActivity.class, latLong);
                break;
            case R.id.ll_sp:
                IntentUtils.getInstance().toActivity(getActivity(), CheckApplyActivity.class, checkId);
                break;
            case R.id.ll_jfw:
                IntentUtils.getInstance().toActivity(getActivity(), YwChooseActivity.class);
                break;
            case R.id.ll_txl:
                IntentUtils.getInstance().toActivity(getContext(), ChoosCheckManActivity.class, "isLinkCard");
                break;
        }
    }



    public void setData() {
        //用户身份类型
        type = (String) SharedPreferencesUtils.getInstance().get(getActivity(), SharedKeyConstant.getInstance().USER_TYPE, "");
        String phone = PersonConstant.getInstance().getPhone(getActivity());
        getDaySignTimes();
//        String change = (String) SharedPreferencesUtils.getInstance().get(getActivity(), SharedKeyConstant.getInstance().MINE_NIGHT_DEFAULT_CHANGE, "");
        if (typeAdapter != null && mineList != null && mineList.size() > 1 && mineList.get(0).getName().equals("消息列表")) {
            mineList.get(0).setUnRead((int) ChatHelper.getInstance().getUnread());
            typeAdapter.notifyDataSetChanged();
        }
        String str = (String) SharedPreferencesUtils.getInstance().get(getActivity(), SharedKeyConstant.getInstance().MINE_DATA, "");
        initLoadData(str);
        SharedPreferencesUtils.getInstance().put(getActivity(), SharedKeyConstant.getInstance().MINE_NIGHT_DEFAULT_CHANGE, "");
        tvToApprove.setText(state + "  ");
        tvPhone.setText(phone);
        getQuaMenu();

        //测试账号
        if (StringUtils.isNoEmpty(phone) && phone.equals("15988112226")) {
            llJfw.setVisibility(View.GONE);
        }

        //设置头像
        GlideUtils.getInstance().displayCricleImage(getActivity(), PersonConstant.getInstance().getHeadPic(getActivity()), ivHeadPic);
        //设置昵称
        tvApproveState.setText(StringUtils.getNoEmptyStr(PersonConstant.getInstance().getRealName(getActivity())));

        String states = (String) SharedPreferencesUtils.getInstance().get(getActivity(), SharedKeyConstant.getInstance().USER_STATE, "");
        if (!StringUtils.isNoEmpty(states)) {
            return;
        }
        if (states.equals(getString(R.string.checking))) {
            tvToApprove.setText(states + "  ");
            state = states;
        }
    }

    //获取当天的考勤次数
    public void getDaySignTimes() {
        Map<String, String> map = new HashMap<>();
        map.put("userId", PersonConstant.getInstance().getUserId());
        String json = JSON.toJSONString(map);
        OkhttpUtil.getInstance().basePostCall(getActivity(), json, UrlConstant.getInstance().CUSTOMER_MANAGE_URL + "IsManager", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                DReobjBean dReobjBean = JSON.parseObject(str, DReobjBean.class);
                roleTypeManager = dReobjBean.getD().getReObj().getType();
                depId = dReobjBean.getD().getReObj().getRoleId();
                if (typeAdapter != null) {
                    typeAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        String phone = PersonConstant.getInstance().getPhone(getActivity());
        state = (String) SharedPreferencesUtils.getInstance().get(getActivity(), SharedKeyConstant.getInstance().USER_STATE, "");
        tvToApprove.setText(state + "  ");
        tvPhone.setText(phone);
        //设置头像
        GlideUtils.getInstance().displayCricleImage(getActivity(), PersonConstant.getInstance().getHeadPic(getActivity()), ivHeadPic);
        //设置昵称
        tvApproveState.setText(StringUtils.getNoEmptyStr(PersonConstant.getInstance().getRealName(getActivity())));
        if (mineList.size() > 1 && mineList.get(0).getName().equals("消息列表")) {
            mineList.get(0).setUnRead((int) ChatHelper.getInstance().getUnread());
            typeAdapter.notifyDataSetChanged();
        }
    }

    //获取权限菜单
    public void getQuaMenu() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", PersonConstant.getInstance().getUserId());
        map.put("id", 0);
        map.put("tier", 1);
        String json = JSON.toJSONString(map);
        OkhttpUtil.getInstance().basePostCall(getActivity(), json, UrlConstant.getInstance().QUA_MENU + "GetFunctionList", new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                LogUtils.e("菜单权限：" + str);
                SharedPreferencesUtils.getInstance().put(getActivity(), SharedKeyConstant.getInstance().MINE_DATA, str);
                initLoadData(str);
            }

            @Override
            public void onFail() {

            }
        });
    }

    //初始化map
    public void initMap() {
        int num = images.length;
        for (int i = 0; i < num; i++) {
            mineMaps.put(names[i], images[i]);
        }
    }

    //加载数据
    private void initLoadData(String str) {
        if (!StringUtils.isNoEmpty(str)) {
            return;
        }
        QuaMenuBean menuBean = JSON.parseObject(str, QuaMenuBean.class);
        if (menuBean.getD().getReList() == null) {
            return;
        }
        mineList.clear();
        List<QuaMenuBean.DBean.ReListBean> reList = menuBean.getD().getReList();
        int num = reList.size();
        for (int i = 0; i < num; i++) {
            reList.get(i).setImg(mineMaps.get(reList.get(i).getName()));
        }
//        QuaMenuBean.DBean.ReListBean listBean = new QuaMenuBean.DBean.ReListBean();
//        listBean.setImg(mineMaps.get("客户管理"));
//        listBean.setId(13);
//        listBean.setName("消息列表");
//        mineList.add(listBean);
        mineList.addAll(reList);
        typeAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onNewMessages(List<TIMMessage> list) {
        if (mineList.size() > 1 && mineList.get(0).getName().equals("消息列表")) {
            mineList.get(0).setUnRead((int) ChatHelper.getInstance().getUnread());
            typeAdapter.notifyDataSetChanged();
        }
        return false;
    }
}
