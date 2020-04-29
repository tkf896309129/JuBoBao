package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.RepliesListBean;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.GlideUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：AboutMeAdapter
 * 描述：
 * 创建时间：2017-04-26  11:42
 */

public class AboutMeAdapter extends BaseAbstractAdapter<RepliesListBean> {
    private boolean isMineCommon;

    public AboutMeAdapter(Context context, List<RepliesListBean> listData, boolean isMineCommon, int resId) {
        super(context, listData, resId);
        this.isMineCommon = isMineCommon;
    }

    @Override
    public void convert(ViewHolder holder, RepliesListBean bean, int position) {
        LinearLayout llTopTitle = holder.getView(R.id.ll_top_title);
        if (isMineCommon) {
            llTopTitle.setVisibility(View.GONE);
        } else {
            llTopTitle.setVisibility(View.VISIBLE);
        }

        ImageView imgPic = holder.getView(R.id.iv_pic);
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvContent = holder.getView(R.id.tv_content);
        TextView tvTitle = holder.getView(R.id.tv_title);
        TextView tvTime = holder.getView(R.id.tv_time);

        GlideUtils.getInstance().displayCricleImage(context, bean.getPostUserPic(), imgPic);
        tvContent.setText(bean.getPostTitle());
        tvTitle.setText(bean.getRepliesText());
        tvName.setText(bean.getPostNickname());
        String forumType = "";
        switch (bean.getPostTypeID()) {
            case 1:
                forumType = "原片专区";
                break;
            case 2:
                forumType = "辅料专区";
                break;
            case 3:
                forumType = "物流专区";
                break;
            case 4:
                forumType = "时事新闻";
                break;
            case 5:
                forumType = "搞笑内涵";
                break;
            case 6:
                forumType = "灌水吐槽";
                break;
            case 7:
                forumType = "生活实事";
                break;
            case 9:
                forumType = "维修专区";
                break;
        }
        tvTime.setText(forumType + "  " + DateUtils.getFormMinuteData(bean.getCreateTime()));

    }
}
