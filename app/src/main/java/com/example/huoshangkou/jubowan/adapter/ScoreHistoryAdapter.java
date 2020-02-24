package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.ScoreHistoryListBean;
import com.example.huoshangkou.jubowan.utils.DateUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：ScoreHistoryAdapter
 * 描述：
 * 创建时间：2017-04-10  13:51
 */

public class ScoreHistoryAdapter extends BaseAbstractAdapter<ScoreHistoryListBean> {

    public ScoreHistoryAdapter(Context context, List<ScoreHistoryListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, ScoreHistoryListBean bean, int position) {

        TextView tvName = holder.getView(R.id.tv_score_name);
        TextView tvTime = holder.getView(R.id.tv_time);
        TextView tvGetScore = holder.getView(R.id.tv_get_score);
        tvName.setText(bean.getSource());
        tvTime.setText(DateUtils.getFormData(bean.getCreateTime()));
        tvGetScore.setText(bean.getAddorSub() + bean.getJubobiCount());

    }
}
