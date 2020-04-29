package com.example.huoshangkou.jubowan.activity;

import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.CheckGroupAdapter;
import com.example.huoshangkou.jubowan.adapter.CheckManAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.CheckGroupBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.inter.OnCommonSuccessCallBack;
import com.example.huoshangkou.jubowan.inter.OnPositionCallBack;
import com.example.huoshangkou.jubowan.inter.OnSignManCallBack;
import com.example.huoshangkou.jubowan.inter.SuccessCallBack;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SignManUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：SignManActivity
 * 描述：
 * 创建时间：2017-04-24  10:03
 */

public class SignManActivity extends BaseActivity {
    @Bind(R.id.tv_right)
    TextView tvRight;
    //身份类型
    private String roleType = "";
    @Bind(R.id.lv_check_group)
    ScrollListView lvCheckGroup;
    @Bind(R.id.lv_check_man)
    ScrollListView lvCheckMan;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_add_group)
    TextView tvAddGroup;
    CheckGroupAdapter checkGroupAdapter;
    CheckManAdapter checkManAdapter;
    List<CheckGroupBean.ReObjBean.DepListBean> groupList = new ArrayList<>();
    List<CheckGroupBean.ReObjBean.UserListBean> manList = new ArrayList<>();
    private String title = "";
    private String id = "";
    private String name = "";
    private String third = "";
    //父节点id
    private String fatherId = "";
    //是否是超级管理员
    private boolean isSuper = false;
    //是否是子成员
    private boolean isChild = false;
    //是否是销售管理
    private boolean isSaleManage = false;
    private String childId = "";
    //是否是销售管理
    private String saleManage = "";

    @Override
    public int initLayout() {
        return R.layout.activity_sign_man;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().approveCheckList);
        id = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        title = getIntent().getStringExtra(IntentUtils.getInstance().VALUE);
        childId = getIntent().getStringExtra(IntentUtils.getInstance().STR);
        saleManage = getIntent().getStringExtra(IntentUtils.getInstance().ROLE_MANAGE_TYPE);
        tvRight.setText("添加");

        if (StringUtils.isNoEmpty(childId)) {
            isChild = true;
        }
        if (StringUtils.isNoEmpty(title)) {
            tvTitle.setText(title.trim());
        } else {
            tvTitle.setText("成员列表");
            if (isChild && childId.equals("child")) {
                tvTitle.setText(name + "的成员列表");
            }
        }

        //从考勤界面传过来的值  
        roleType = getIntent().getStringExtra(IntentUtils.getInstance().ROLE_TYPE);
        if (StringUtils.isNoEmpty(roleType)) {
            //普通管理员
            if (roleType.equals("1")) {
                isSuper = false;
                tvRight.setVisibility(View.GONE);
                //超级管理员
            } else {
                isSuper = true;
                tvAddGroup.setVisibility(View.VISIBLE);
                tvRight.setVisibility(View.VISIBLE);
                if (isChild) {
                    tvRight.setVisibility(View.GONE);
                    tvAddGroup.setVisibility(View.GONE);
                }
            }
        }

        //销售管理
        if (StringUtils.isNoEmpty(saleManage)) {
            tvRight.setText("我的CRM");
            isSaleManage = true;
            tvAddGroup.setVisibility(View.GONE);
        }

        //部门
        checkGroupAdapter = new CheckGroupAdapter(this, groupList, "sign", isChild,isSuper, R.layout.item_ep_group, saleManage);
        lvCheckGroup.setAdapter(checkGroupAdapter);
        lvCheckGroup.setDividerHeight(0);

        //人员
        checkManAdapter = new CheckManAdapter(this, manList, isSuper, R.layout.item_ep_child, "sign", "sign", saleManage, id, title);
        lvCheckMan.setAdapter(checkManAdapter);
        lvCheckMan.setDividerHeight(0);

        //人员侧滑操作
        checkManAdapter.setSignManCallBack(new OnSignManCallBack() {
            @Override
            public void onSetManage(int i) {
                String type = "";
                //取消管理员
                if (manList.get(i).getAdministrator() == 1 || manList.get(i).getAdministrator() == 2) {
                    type = "0";
                    //设为管理员
                } else {
                    type = "1";
                }
                setMange(manList.get(i).getID() + "", type, new SuccessCallBack() {
                    @Override
                    public void onSuccess() {
                        //二级成员列表
                        if (isChild) {
                            getChildMember();
                            return;
                        }
                        getMemberData();
                    }

                    @Override
                    public void onFail() {

                    }
                });
            }

            //回访记录
            @Override
            public void onBackRecord(int i) {
                IntentUtils.getInstance().toActivity(SignManActivity.this, SignDetailsActivity.class, manList.get(i).getID() + "", manList.get(i).getPic1(), "visitor");
            }

            //成员列表
            @Override
            public void onMemberList(int i) {
                IntentUtils.getInstance().toRoleTypeActivity(SignManActivity.this, SignManActivity.class, manList.get(i).getID() + "", manList.get(i).getLinkMan() + "", "child", roleType);
            }

            //删除
            @Override
            public void onDelete(final int i) {
                CopyIosDialogUtils.getInstance().getIosDialog(SignManActivity.this, "是否删除" + manList.get(i).getLinkMan(), new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        SignManUtils.deleteMember(SignManActivity.this, manList.get(i).getID() + "", id, new OnCommonSuccessCallBack() {
                            @Override
                            public void onSuccess() {
                                //二级成员列表
                                if (isChild) {
                                    getChildMember();
                                    return;
                                }
                                getMemberData();
                            }

                            @Override
                            public void onFail() {

                            }
                        });
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
            }
        });

        checkGroupAdapter.setPositionCallBack(new OnPositionCallBack() {
            @Override
            public void onPositionClick(int i) {
                if (isChild) {
//                    if (StringUtils.isNoEmpty(saleManage)) {
//                        IntentUtils.getInstance().toActivity(SignManActivity.this, CustomerManagerActivity.class, manList.get(i).getID() + "",id,title);
//                        return;
//                    }
                    if (!StringUtils.isNoEmpty(id)) {
                        id = "child";
                    }
                    IntentUtils.getInstance().toRoleTypeActivity(SignManActivity.this, SignManActivity.class, groupList.get(i).getID() + "", groupList.get(i).getRoleName(), childId + "", roleType);
                    return;
                }
                IntentUtils.getInstance().toRoleManageTypeActivity(SignManActivity.this, SignManActivity.class, groupList.get(i).getID() + "", groupList.get(i).getRoleName(), roleType, saleManage);
            }
        });

        checkManAdapter.setPositionCallBack(new OnPositionCallBack() {
            @Override
            public void onPositionClick(int i) {

                IntentUtils.getInstance().toActivity(getContext(), SignDetailsActivity.class, manList.get(i).getID() + "", manList.get(i).getPic1());
//                if (!manList.get(i).isCheck()) {
//                    manList.get(i).setCheck(true);
//                } else {
//                    manList.get(i).setCheck(false);
//                }
//                checkManAdapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick({R.id.ll_back, R.id.tv_right, R.id.tv_add_group})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_right:
                if (isSaleManage) {
                    IntentUtils.getInstance().toRoleActivity(this, CustomerManagerActivity.class, roleType + "", "");
                    return;
                }
                if (isChild) {
                    IntentUtils.getInstance().toActivity(getContext(), SignManActivity.class, "", "人员分配");
                    return;
                }
                IntentUtils.getInstance().toActivity(getContext(), AddSignManActivity.class, title, id);
                break;
            case R.id.tv_add_group:
                IntentUtils.getInstance().toActivity(this, AddGroupActivity.class, id);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //子部门成员
        if (isChild) {
            getChildMember();
            return;
        }
        //非超级管理员
        if (!isSuper) {
            getChildMember();
            return;
        }
        //超级管理员
        getMemberData();
    }

    //获取员工数据
    public void getMemberData() {
        if (id == null) {
            id = "";
        }
        if (StringUtils.isNoEmpty(id) && id.equals("child")) {
            id = "";
        }
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(this, UrlConstant.getInstance().URL
                + PostConstant.getInstance().GROUP_PRO
                + FieldConstant.getInstance().TYPE + "=&"
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().GROUP_ID + "=" + id, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                groupList.clear();
                manList.clear();
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

    //获取下属部门与下属人员
    public void getChildMember() {
        if (id == null) {
            id = "";
        }
        //成员列表
        if (StringUtils.isNoEmpty(childId) && childId.equals("child")) {
            childId = id;
            id = "";
        }
        if (StringUtils.isNoEmpty(id) && id.equals("child")) {
            id = "";
        }
        //部门管理员
        if (childId == null) {
            childId = PersonConstant.getInstance().getUserId();
        }
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(this, UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_USER_CHILD
                + FieldConstant.getInstance().USER_ID + "=" + childId + "&"
                + FieldConstant.getInstance().DEP_ID + "=" + id, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                groupList.clear();
                manList.clear();

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

    //设为管理员
    public void setMange(String adminId, String type, final SuccessCallBack successCallBack) {
        OkhttpUtil.getInstance().setUnCacheDataNoDialog(this, UrlConstant.getInstance().URL
                + PostConstant.getInstance().SET_ADMINSTORS
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId() + "&"
                + FieldConstant.getInstance().ADMIN_ID + "=" + adminId + "&"
                + FieldConstant.getInstance().LEVELS + "=" + type, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                SuccessBean successBean = JSON.parseObject(json, SuccessBean.class);
                if (successBean.getSuccess() == 1) {
                    successCallBack.onSuccess();
                } else {
                    ToastUtils.getMineToast(successBean.getErrMsg());
                }
            }

            @Override
            public void onFail() {

            }
        });
    }
}
