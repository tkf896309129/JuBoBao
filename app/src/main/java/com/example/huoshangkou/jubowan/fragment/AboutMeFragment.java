package com.example.huoshangkou.jubowan.fragment;

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
import com.example.huoshangkou.jubowan.adapter.AboutMeAdapter;
import com.example.huoshangkou.jubowan.base.BaseFragment;
import com.example.huoshangkou.jubowan.bean.ForumListBean;
import com.example.huoshangkou.jubowan.bean.RepliesBean;
import com.example.huoshangkou.jubowan.bean.RepliesListBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.TieDetailConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.fragment
 * 类名：AboutMeFragment
 * 描述：
 * 创建时间：2017-04-26  11:43
 */

public class AboutMeFragment extends BaseFragment {

    @Bind(R.id.lv_refresh)
    ListView lvRefresh;
    @Bind(R.id.x_refresh)
    SmartRefreshLayout xRefresh;
    @Bind(R.id.ll_no_data)
    LinearLayout llNoData;

    AboutMeAdapter meAdapter;
    List<String> list;

    //判断是我的评论 还是 回复我的
    private String type = "";
    //是否是我的评论
    private boolean isMineCommon = false;

    //获取数据连接
    private String url = "";

    //我的评论
    private List<RepliesListBean> tieList;

    //页码
    private int pageSize = 1;

    public static AboutMeFragment newInstance() {
        AboutMeFragment fragment = new AboutMeFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_about_me;
    }

    @Override
    public void initData() {
        tieList = new ArrayList<>();
        list = new ArrayList<>();

        type = getArguments().getString(IntentUtils.getInstance().TYPE);


        meAdapter = new AboutMeAdapter(getActivity(), tieList, isMineCommon, R.layout.item_about_me);
        lvRefresh.setAdapter(meAdapter);
        lvRefresh.setDividerHeight(0);
        getAboutMeData();

        lvRefresh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ForumListBean listBean = new ForumListBean();
                if (tieList.get(position).getPostMode() == null) {
                    ToastUtils.getMineToast("获取信息失败");
                    return;
                }

                listBean.setPostTitle(tieList.get(position).getPostMode().getPostTitle());
                listBean.setReplies(tieList.get(position).getPostMode().getReplies());
                listBean.setID(tieList.get(position).getPostMode().getID());
                listBean.setPageView(tieList.get(position).getPostMode().getPageView());
                listBean.setUserID(tieList.get(position).getPostMode().getUserID());
                listBean.setJobobiScore(tieList.get(position).getPostMode().getJobobiScore());
                listBean.setPics(tieList.get(position).getPostMode().getPics());
                listBean.setPostState(tieList.get(position).getPostMode().getPostState());
                listBean.setPostText(tieList.get(position).getPostMode().getPostText());
                listBean.setCreateTime(tieList.get(position).getPostMode().getCreateTime());

                listBean.setNickname(tieList.get(position).getPostNickname());
                listBean.setUserPic(tieList.get(position).getPostUserPic());
                listBean.setPostTypeID(tieList.get(position).getID());

                IntentUtils.getInstance().toActivity(getActivity(), TieDetailsActivity.class, listBean, TieDetailConstant.getInstance().SHARE);
            }
        });

        xRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageSize = 1;
                tieList.clear();
                getAboutMeData();
            }
        });
        xRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageSize++;
                getAboutMeData();
            }
        });
    }

    //获取与我相关
    public void getAboutMeData() {

        //我的评论
        if (StringUtils.isNoEmpty(type) && type.equals(TieDetailConstant.getInstance().MINE_COMMON)) {
            isMineCommon = true;
            url = UrlConstant.getInstance().URL + PostConstant.getInstance().GET_MY_COMMON + FieldConstant.getInstance().USER_ID
                    + "=" + PersonConstant.getInstance().getUserId() + "&" + FieldConstant.getInstance().PAGE_SIZE + "=" + pageSize;
        } else {
            isMineCommon = false;
            url = UrlConstant.getInstance().URL + PostConstant.getInstance().GET_REPLY_SELF + FieldConstant.getInstance().USER_ID
                    + "=" + PersonConstant.getInstance().getUserId() + "&" + FieldConstant.getInstance().PAGE_SIZE + "=" + pageSize;
        }

        OkhttpUtil.getInstance().setUnCacheData(getActivity(), getString(R.string.loading_message), url, new OkhttpCallBack() {
            @Override
            public void onSuccess(String json) {
                RepliesBean repliesBean = JSON.parseObject(json, RepliesBean.class);
                tieList.addAll(repliesBean.getReList());
                meAdapter.notifyDataSetChanged();
                if (xRefresh != null) {
                    xRefresh.finishLoadMore();
                    xRefresh.finishRefresh();
                }
            }

            @Override
            public void onFail() {
                if (xRefresh != null) {
                    xRefresh.finishLoadMore();
                    xRefresh.finishRefresh();
                }
            }
        });
    }

}
