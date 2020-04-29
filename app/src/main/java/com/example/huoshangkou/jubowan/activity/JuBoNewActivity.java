package com.example.huoshangkou.jubowan.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.adapter.JuBoNewsAdapter;
import com.example.huoshangkou.jubowan.base.BaseActivity;
import com.example.huoshangkou.jubowan.bean.NewsBean;
import com.example.huoshangkou.jubowan.bean.NewsListBean;
import com.example.huoshangkou.jubowan.constant.FieldConstant;
import com.example.huoshangkou.jubowan.constant.PostConstant;
import com.example.huoshangkou.jubowan.constant.UrlConstant;
import com.example.huoshangkou.jubowan.utils.GetPacketUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.OkhttpCallBack;
import com.example.huoshangkou.jubowan.utils.OkhttpUtil;
import com.example.huoshangkou.jubowan.utils.SmartUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.BulletinView;
import com.example.huoshangkou.jubowan.view.ForeverMarque;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.activity_bind_blank
 * 类名：JuBoNewActivity
 * 描述：
 * 创建时间：2017-07-12  14:50
 */

public class JuBoNewActivity extends BaseActivity {
    JuBoNewsAdapter newsAdapter;
    List<NewsListBean> newsList;
    @Bind(R.id.lv_refresh)
    ListView listView;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.x_refresh)
    SmartRefreshLayout xRefreshView;
    @Bind(R.id.tv_right)
    TextView tvRight;

    @Bind(R.id.tv_ma_key)
    ForeverMarque bulletinView;

    //友盟分享回调
    private UMShareListener umShareListener;

    final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]{
//                   SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SINA,
//            SHARE_MEDIA.QQ,
//            SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QZONE,
            SHARE_MEDIA.WEIXIN_CIRCLE
//                    , SHARE_MEDIA.DOUBAN
    };

    private int page = 1;

    @Override
    public int initLayout() {
        return R.layout.activity_jubo_news;
    }

    @Override
    protected boolean setIsFull() {
        return false;
    }

    @Override
    public void initData() {
        newsList = new ArrayList<>();

        tvTitle.setText("聚玻资讯");
        newsAdapter = new JuBoNewsAdapter(getContext(), newsList, R.layout.item_jubo_news);
        listView.setAdapter(newsAdapter);
        listView.setDividerHeight(0);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IntentUtils.getInstance().toWebActivity(getContext(), newsList.get(i).getWebUrl(), newsList.get(i).getTitle(), "yes", newsList.get(i).getCreateTime());
            }
        });
        getNewsData();

        xRefreshView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                newsList.clear();
                page = 1;
                getNewsData();
            }
        });
        xRefreshView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getNewsData();
            }
        });
//        GetPacketUtils.getPacketDialogShow(getContext());
    }


    //获取聚玻资讯信息
    public void getNewsData() {
        OkhttpUtil.getInstance().setUnCacheData(getContext(), getString(R.string.loading_message),
                UrlConstant.getInstance().URL + PostConstant.getInstance().JUBO_NEWS
                        + FieldConstant.getInstance().PAGE_SIZE + "=" + page, new OkhttpCallBack() {
                    @Override
                    public void onSuccess(String json) {
                        NewsBean newsBean = JSON.parseObject(json, NewsBean.class);
                        newsList.addAll(newsBean.getReList());
                        newsAdapter.notifyDataSetChanged();
                        SmartUtils.finishSmart(xRefreshView);
                    }

                    @Override
                    public void onFail() {
                        SmartUtils.finishSmart(xRefreshView);
                    }
                });
    }

    @OnClick({R.id.ll_back, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.tv_right:


                break;
        }
    }


}
