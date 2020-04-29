package com.example.huoshangkou.jubowan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.CheckSearchAdapter;
import com.example.huoshangkou.jubowan.adapter.FragmentChangeAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.MemberBean;
import com.example.huoshangkou.jubowan.bean.MemberListBean;
import com.example.huoshangkou.jubowan.bean.SelectManBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.fragment.CheckByGroupFragment;
import com.example.huoshangkou.jubowan.fragment.CheckByManFragment;
import com.example.huoshangkou.jubowan.utils.ActivityUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SearchUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.widget.ContactItemInterface;
import com.example.huoshangkou.jubowan.widget.pinyin.PinYin;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：ChoosCheckManActivity
 * 描述：选择审批人界面
 * 创建时间：2017-02-16  10:00
 */

public class ChoosCheckManActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ll_search)
    LinearLayout llSearch;
    @Bind(R.id.tab_check_man)
    TabLayout tabCheckMan;
    @Bind(R.id.vp_check_type)
    ViewPager vpCheckType;

    //是否是通讯录
    private String isLinkCard = "";
    //审批人员
    ArrayList<MemberListBean> memberList;
    //选择审批人
    List<SelectManBean> selectList;
    //搜索
    List<ContactItemInterface> searchList;
    //搜索适配器
    CheckSearchAdapter searchAdapter;
    //Fragment适配器
    FragmentChangeAdapter fragmentsAdapter;
    List<Fragment> fragmentList;

    private String dialogTitle = "";
    private final int SUCCESS = 1;
    private final int FAIL = 2;
    private String csMan = "";
    private String type = "";

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SUCCESS:
                    //按部门
                    Fragment fragmentGroup = CheckByGroupFragment.newInstance();
                    Bundle bundleGroup = new Bundle();
                    bundleGroup.putParcelableArrayList("manList", memberList);
                    //筛选抄送人
                    bundleGroup.putString(IntentUtils.getInstance().CSR, csMan);
                    bundleGroup.putString(IntentUtils.getInstance().TYPE, isLinkCard);
                    bundleGroup.putString(IntentUtils.getInstance().TITLE, dialogTitle);
                    fragmentGroup.setArguments(bundleGroup);
                    fragmentList.add(fragmentGroup);
                    //按员工
                    Fragment fragment = CheckByManFragment.newInstance();
                    Bundle bundle = new Bundle();

                    bundle.putParcelableArrayList("manList", memberList);
                    bundle.putString(IntentUtils.getInstance().TYPE, isLinkCard);
                    bundle.putString(IntentUtils.getInstance().TITLE, dialogTitle);
                    fragment.setArguments(bundle);
                    fragmentList.add(fragment);

                    fragmentsAdapter = new FragmentChangeAdapter(getSupportFragmentManager(), fragmentList);
                    vpCheckType.setAdapter(fragmentsAdapter);

                    tabCheckMan.addTab(tabCheckMan.newTab().setText("按部门"));
                    tabCheckMan.addTab(tabCheckMan.newTab().setText("按员工 (" + memberList.size() + ")"));
                    vpCheckType.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabCheckMan));
                    tabCheckMan.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                        @Override
                        public void onTabSelected(TabLayout.Tab tab) {
                            vpCheckType.setCurrentItem(tab.getPosition());
                        }

                        @Override
                        public void onTabUnselected(TabLayout.Tab tab) {

                        }

                        @Override
                        public void onTabReselected(TabLayout.Tab tab) {

                        }
                    });
                    break;
                case FAIL:
                    fragmentList.add(CheckByGroupFragment.newInstance());
                    Fragment fragment2 = CheckByManFragment.newInstance();
                    Bundle bundle2 = new Bundle();
                    MemberListBean memberListBean = new MemberListBean();
                    memberListBean.setLinkMan("暂时没有获取审批人");
                    memberList.add(memberListBean);
                    bundle2.putParcelableArrayList("manList", memberList);
                    bundle2.putString(IntentUtils.getInstance().TYPE, isLinkCard);
                    fragment2.setArguments(bundle2);
                    fragmentList.add(fragment2);
                    fragmentsAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    public int initLayout() {
        return R.layout.activity_choose_man;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        //EventBus注册
        EventBus.getDefault().register(this);
//        ActivityUtils.getInstance().addActivity(this, ActivityUtils.getInstance().memberList);
        isLinkCard = getIntent().getStringExtra(IntentUtils.getInstance().TYPE);
        dialogTitle = getIntent().getStringExtra(IntentUtils.getInstance().TITLE);

        if (StringUtils.isNoEmpty(isLinkCard)) {
            tvTitle.setText("通讯录");
            csMan = "linkMan";
            type = "0";
        } else {
            tvTitle.setText("选择" + dialogTitle);
            csMan = "csMan";
            type = "1";
        }

        fragmentList = new ArrayList<>();
        searchList = new ArrayList<>();
        memberList = new ArrayList<>();
        selectList = new ArrayList<>();

        //搜索部分
        searchAdapter = new CheckSearchAdapter(this, searchList, dialogTitle, R.layout.item_search_check);

        OkhttpUtil.getInstance().setUnCacheData(this, getString(R.string.loading_message), UrlConstant.getInstance().URL
                + PostConstant.getInstance().GET_EMPLOYEE_LIST
                + FieldConstant.getInstance().TYPE + "=" + type + "&"
                + FieldConstant.getInstance().USER_ID + "=" + PersonConstant.getInstance().getUserId(), new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                MemberBean memberBean = JSON.parseObject(json, MemberBean.class);
                memberList.addAll(memberBean.getReList());
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
                handler.sendEmptyMessage(SUCCESS);
            }

            @Override
            public void onFail() {
                Message message = handler.obtainMessage();
                message.obj = "暂时没有获取到审批人员";
                message.what = SUCCESS;
                handler.sendMessage(message);
            }
        });
    }

    //点击事件
    @OnClick({R.id.ll_back, R.id.ll_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            //搜索审批人
            case R.id.ll_search:
                SearchUtils.getInstance().searchDialog(this, searchList, selectList, isLinkCard, dialogTitle,false,null);
                break;
        }
    }

    @Subscriber(tag = "checkManId")
    public void onCheckMan(String info) {
        String id = "";
        String name = "";
        String img = "";
        String type = "";

        String[] check = info.split(",");
        id = check[0];
        name = check[1];
        img = check[2];
        if (check.length == 4) {
            type = check[3];
        }
        LogUtils.i(id + "   " + name + "  " + img);
        Intent intent = new Intent();
        intent.putExtra("id", id);
        intent.putExtra("name", name);
        intent.putExtra("img", img);
        intent.putExtra("type", type);
        setResult(103, intent);//是根据requestCode传值过去区分的 

        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
