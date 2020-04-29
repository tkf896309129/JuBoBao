package com.example.huoshangkou.jubowan.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.TieDetailsActivity;
import com.example.huoshangkou.jubowan.activity.function.TieDetailFunction;
import com.example.huoshangkou.jubowan.adapter.CheckBackTieAdapter;
import com.example.huoshangkou.jubowan.adapter.FragmentChangeAdapter;
import com.example.huoshangkou.jubowan.adapter.TieAdapter;
import com.example.huoshangkou.jubowan.base.BaseFragment;
import com.example.huoshangkou.jubowan.base.BaseListFragment;
import com.example.huoshangkou.jubowan.bean.ForumBean;
import com.example.huoshangkou.jubowan.bean.ForumListBean;
import com.example.huoshangkou.jubowan.bean.RepliesBean;
import com.example.huoshangkou.jubowan.bean.RepliesListBean;
import com.example.huoshangkou.jubowan.bean.SuccessBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.SharedKeyConstant;
import com.example.huoshangkou.jubowan.constant.TieDetailConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.fragment.function.ForumListFunction;
import com.example.huoshangkou.jubowan.inter.OnCommonSuccessCallBack;
import com.example.huoshangkou.jubowan.inter.OnForumCallBack;
import com.example.huoshangkou.jubowan.inter.OnForumItemClick;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SharedPreferencesUtils;
import com.example.huoshangkou.jubowan.utils.SmartUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.fragment
 * 类名：ForumListFragment
 * 描述：
 * 创建时间：2017-02-14  11:47
 */

public class ForumListFragment extends BaseFragment {
    @Bind(R.id.x_refresh)
    SmartRefreshLayout xRefresh;
    @Bind(R.id.lv_refresh)
    ListView lvRefresh;
    @Bind(R.id.ll_no_data)
    LinearLayout llNoData;

    TieAdapter tieAdapter;
    private int pageSize = 1;
    //帖子相关
    List<ForumListBean> detailBeanList;

    //排序 1、时间排序  2、热度排序  默认时间排序
    public int rank = 1;
    //排序下标
    public int sortIndex = -1;


    //待审回帖
    private List<RepliesListBean> tieList;
    private CheckBackTieAdapter backTieAdapter;
    private String index = "";

    //审核状态
    private String checkState = "";

    public static ForumListFragment newInstance() {
        ForumListFragment fragment = new ForumListFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_forum_list;
    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
        Bundle arguments = getArguments();
        //获取论坛的类型
        if (arguments != null) {
            index = arguments.getString(IntentUtils.getInstance().TYPE);
            checkState = arguments.getString(IntentUtils.getInstance().CHECK_TYPE);
        }
        detailBeanList = new ArrayList<>();
        tieList = new ArrayList<>();
        //待审回帖
        if (StringUtils.isNoEmpty(checkState) && checkState.equals("2")) {
            checkBackTie();
            //非待审回帖
        } else {
            unCheckBackTie();
        }
    }


    @Subscriber(tag = "toJuYiLianBuy")
    public void juYiLianBuyRefresh(String str) {
        if (!str.isEmpty()) {
            getData();
        }
    }

    //非待审回帖操作
    public void unCheckBackTie() {
        try {
            tieAdapter = new TieAdapter(getActivity(), detailBeanList, R.layout.item_tie_layout);
            lvRefresh.setAdapter(tieAdapter);
            lvRefresh.setDividerHeight(0);
        } catch (Exception e) {

        }
        //点击事件
        tieAdapter.setItemClick(new OnForumItemClick() {
            @Override
            public void onForumClick(final int position) {
                //论坛管理  锁定中
                if (StringUtils.isNoEmpty(checkState) && checkState.equals("3")) {
                    IntentUtils.getInstance().toActivity(getActivity(), TieDetailsActivity.class, detailBeanList.get(position), TieDetailConstant.getInstance().IS_CLOCK);
                    //帖子管理
                } else if (StringUtils.isNoEmpty(checkState) && checkState.equals("1")) {
                    IntentUtils.getInstance().toActivity(getActivity(), TieDetailsActivity.class, detailBeanList.get(position), TieDetailConstant.getInstance().TIE_APPLY);
                } else {
                    TieDetailFunction.getInstance().changeReviewCount(getContext(), detailBeanList.get(position).getID() + "", new OnCommonSuccessCallBack() {
                        @Override
                        public void onSuccess() {
                            detailBeanList.get(position).setPageView(detailBeanList.get(position).getPageView() + 1);
                        }

                        @Override
                        public void onFail() {

                        }
                    });
                    tieAdapter.notifyDataSetChanged();
                    IntentUtils.getInstance().toActivity(getActivity(), TieDetailsActivity.class, detailBeanList.get(position), TieDetailConstant.getInstance().SHARE);
                }
            }

            @Override
            public void onMainZanClick(int position, String postId) {

            }
        });
        getData();
        xRefresh.setEnableRefresh(true);
        xRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageSize = 1;
                getData();
            }
        });
        xRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageSize++;
                getData();
            }
        });

    }

    //获取待审回帖
    private void getCheckData() {
        ForumListFunction.getInstance().getCheckBackTie(getActivity(), pageSize, new StringCallBack() {
            @Override
            public void onSuccess(String str) {
                RepliesBean repliesBean = JSON.parseObject(str, RepliesBean.class);
                tieList.addAll(repliesBean.getReList());
                if (backTieAdapter != null) {
                    backTieAdapter.notifyDataSetChanged();
                }
                SmartUtils.finishSmart(xRefresh);
                if (tieList.size() == 0) {
                    llNoData.setVisibility(View.VISIBLE);
                } else {
                    llNoData.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFail() {
                SmartUtils.finishSmart(xRefresh);
            }
        });
    }

    //获取帖子
    private void getData() {
        ForumListFunction.getInstance().getForumData(getActivity(), index, pageSize, checkState, rank, new OnForumCallBack() {
            @Override
            public void onSuccess(ForumBean forumBean) {
                if (pageSize == 1) {
                    detailBeanList.clear();
                }
                detailBeanList.addAll(forumBean.getReList());
                if (tieAdapter != null) {
                    tieAdapter.notifyDataSetChanged();
                }
                SmartUtils.finishSmart(xRefresh);
                if (llNoData == null) {
                    return;
                }

                if (detailBeanList.size() == 0) {
                    llNoData.setVisibility(View.VISIBLE);
                } else {
                    llNoData.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFail() {
                llNoData.setVisibility(View.VISIBLE);
                SmartUtils.finishSmart(xRefresh);
            }
        });
    }

    //待审回帖
    public void checkBackTie() {
        backTieAdapter = new CheckBackTieAdapter(getActivity(), tieList, R.layout.item_check_back_tie);
        lvRefresh.setAdapter(backTieAdapter);
        lvRefresh.setDividerHeight(0);

        lvRefresh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                ForumListBean listBean = new ForumListBean();
                listBean.setPostTitle(tieList.get(position).getPostMode().getPostTitle());
                listBean.setReplies(tieList.get(position).getPostMode().getReplies());
                listBean.setID(tieList.get(position).getPostMode().getID());
                listBean.setPageView(tieList.get(position).getPostMode().getPageView());
                listBean.setUserID(tieList.get(position).getPostMode().getUserID());
                listBean.setJobobiScore(tieList.get(position).getPostMode().getJobobiScore());
                listBean.setNickname(tieList.get(position).getPostNickname());
                listBean.setPics(tieList.get(position).getPostMode().getPics());
                listBean.setPostState(tieList.get(position).getPostMode().getPostState());
                listBean.setUserPic(tieList.get(position).getPostUserPic());
                listBean.setPostText(tieList.get(position).getPostMode().getPostText());
                listBean.setPostTypeID(tieList.get(position).getID());
                listBean.setCreateTime(tieList.get(position).getPostMode().getCreateTime());

                IntentUtils.getInstance().toActivity(getActivity(), TieDetailsActivity.class, listBean, TieDetailConstant.getInstance().APPLY_BACK_TIE, "check_back_tie");
            }
        });

        getCheckData();
        xRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageSize++;
                getCheckData();
            }
        });
        xRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageSize = 1;
                tieList.clear();
                getCheckData();
            }
        });
    }


    /**
     * 判断集合是否需要刷新
     */
    public void isNeedInit() {
        int size = detailBeanList.size();
        //有多少个20的数组
        //取莫
        int smallSize = size % 20;
        int num = size - smallSize;
        for (int i = size - 1; i >= num; i--) {
            detailBeanList.remove(i);
        }
    }

    //排序刷新
    @Subscriber(tag = "forum_sort")
    public void getSortTime(String sort) {
        String[] split = sort.split(",");
        if (split == null || split.length < 2) {
            return;
        }
        rank = Integer.parseInt(split[0]);
        sortIndex = Integer.parseInt(split[1]);
        if ((sortIndex + "").equals(index)) {
            pageSize = 1;
            getData();
        }
    }

    //审核后进行刷新
    @Subscriber(tag = "initManagerData")
    public void initTheData(String str) {
        //待审回帖
        if (StringUtils.isNoEmpty(checkState) && checkState.equals("2")) {
            pageSize = 1;
            tieList.clear();
            getCheckData();
            //非待审回帖
        } else {
            pageSize = 1;
            getData();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        String isInit = (String) SharedPreferencesUtils.getInstance().get(getContext(), SharedKeyConstant.getInstance().PASS_CHECK, "");
        if (!StringUtils.isNoEmpty(isInit)) {
            return;
        }
        EventBus.getDefault().post("initManagerData", "initManagerData");
        SharedPreferencesUtils.getInstance().put(getContext(), SharedKeyConstant.getInstance().PASS_CHECK, "");
    }
}
