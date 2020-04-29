package com.example.huoshangkou.jubowan.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.LoginActivity;
import com.example.huoshangkou.jubowan.activity.MyTieActivity;
import com.example.huoshangkou.jubowan.activity.SearchTieActivity;
import com.example.huoshangkou.jubowan.base.BaseFragment;
import com.example.huoshangkou.jubowan.constant.EventBusConstant;
import com.example.huoshangkou.jubowan.fragment.function.ForumFunction;
import com.example.huoshangkou.jubowan.inter.OnPositionClick;
import com.example.huoshangkou.jubowan.inter.StringPositionCallBack;
import com.example.huoshangkou.jubowan.utils.DialogUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LoginUtils;
import com.example.huoshangkou.jubowan.utils.PostTieDialogUtils;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.fragment
 * 类名：ForumFragment
 * 描述：论坛界面
 * 创建时间：2017-01-03  09:41
 */

public class ForumFragment extends BaseFragment {
    @Bind(R.id.tab_forum)
    TabLayout tabForum;
    @Bind(R.id.vp_forum)
    ViewPager vpForum;
    //论坛筛选
    @Bind(R.id.ll_choose_select)
    LinearLayout llChoose;

    private int tabPosition = 0;
    private int checkPosition = 8;

    private ArrayList<String> sortList;
    private List<String> dataList = new ArrayList<>();

    public static ForumFragment newInstance() {
        ForumFragment fragment = new ForumFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_forum;
    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
        sortList = new ArrayList<>();
        sortList.add("时间排序");
        sortList.add("热度排序");

        //论坛界面切换
        ForumFunction.changeForumPages(tabForum, vpForum, getChildFragmentManager(), new OnPositionClick() {
            @Override
            public void onPositionClick(int position) {
                tabPosition = position;
            }
        });
    }


    //跳转至论坛界面
    @Subscriber(tag = "forumClick")
    public void toForum(String str) {
        if (!str.isEmpty()) {
            vpForum.setCurrentItem(1);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    //点击事件
    @OnClick({R.id.iv_post_tie, R.id.ll_choose_select, R.id.ll_my_luntan, R.id.tv_search_tie})
    public void onClick(View view) {
        switch (view.getId()) {
            //跳转至编辑帖子
            case R.id.iv_post_tie:
                if (!LoginUtils.getInstance().isLogin(getActivity())) {
                    IntentUtils.getInstance().toActivity(getActivity(), LoginActivity.class);
                    return;
                }
                PostTieDialogUtils.getInstance().getPostTieDialogUtils(getActivity());
                break;
            //筛选帖子
            case R.id.ll_choose_select:
                DialogUtils.getInstance().getBaseDialog(getActivity(), sortList, new StringPositionCallBack() {
                    @Override
                    public void onStringPosition(String str, int position) {
                        checkPosition = position;
                        //时间排序
                        if (position == 0) {
                            EventBus.getDefault().post(EventBusConstant.getInstance().TIME_SORT + "," + tabPosition, EventBusConstant.getInstance().FORUM_SORT);
                            //热度排序
                        } else {
                            EventBus.getDefault().post(EventBusConstant.getInstance().HOT_SORT + "," + tabPosition, EventBusConstant.getInstance().FORUM_SORT);
                        }
                    }
                });
                break;
            //我的论坛
            case R.id.ll_my_luntan:
                if (!LoginUtils.getInstance().isLogin(getActivity())) {
                    IntentUtils.getInstance().toActivity(getActivity(), LoginActivity.class);
                    return;
                }

                IntentUtils.getInstance().toActivity(getActivity(), MyTieActivity.class);
                break;
            //搜索论坛帖
            case R.id.tv_search_tie:
                IntentUtils.getInstance().toActivity(getActivity(), SearchTieActivity.class);
                break;
        }
    }


}
