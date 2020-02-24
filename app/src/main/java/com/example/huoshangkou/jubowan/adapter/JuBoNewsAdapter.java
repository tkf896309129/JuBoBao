package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.NewsListBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：JuBoNewsAdapter
 * 描述：
 * 创建时间：2017-07-12  16:06
 */

public class JuBoNewsAdapter extends BaseAbstractAdapter<NewsListBean> {

    public JuBoNewsAdapter(Context context, List<NewsListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, NewsListBean bean, int position) {
        TextView tvNews = holder.getView(R.id.tv_news);
        TextView tvNewsTime = holder.getView(R.id.tv_news_time);

        tvNews.setText(bean.getTitle());
        tvNewsTime.setText(bean.getCreateTime());
    }
}
