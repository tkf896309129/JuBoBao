package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.TieDetailsActivity;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.ForumListBean;
import com.example.huoshangkou.jubowan.bean.TieDetailBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.constant.PhotoConstant;
import com.example.huoshangkou.jubowan.inter.OnForumItemClick;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.PhotoUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：TieAdapter
 * 描述：
 * 创建时间：2017-02-14  13:50
 */

public class TieAdapter extends BaseAbstractAdapter<ForumListBean> {

    public TieAdapter(Context context, List<ForumListBean> listData, int resId) {
        super(context, listData, resId);
    }

    private OnForumItemClick itemClick;

    @Override
    public void convert(ViewHolder holder, final ForumListBean bean, final int position) {

        ImageView imgHead = holder.getView(R.id.iv_head_img);
        GlideUtils.getInstance().displayCricleImage(context, bean.getUserPic(), imgHead);

        TextView tvName = holder.getView(R.id.tv_name);
        tvName.setText(bean.getNickname());

        //手机型号
        TextView tvPhoneType = holder.getView(R.id.tv_phone_type);
        TextView tvZanNum = holder.getView(R.id.tv_zan_num);

        LinearLayout llZan = holder.getView(R.id.ll_zan);

        tvZanNum.setText(bean.getGiveUpCount() + "");
        llZan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClick.onMainZanClick(position, listData.get(position).getID() + "");
            }
        });


        switch (bean.getPostTypeID()) {
            case 1:
                tvPhoneType.setText("原片专区");
                break;
            case 2:
                tvPhoneType.setText("辅料专区");
                break;
            case 3:
                tvPhoneType.setText("物流专区");
                break;
            case 4:
                tvPhoneType.setText("时事新闻");
                break;
            case 5:
                tvPhoneType.setText("搞笑内涵");
                break;
            case 6:
                tvPhoneType.setText("灌水吐槽");
                break;
            case 7:
                tvPhoneType.setText("秀一秀");
                break;
            case 9:
                tvPhoneType.setText("维修维保");
                break;
            case 12:
                tvPhoneType.setText("聚玻早报");
                break;
        }


        TextView tvMessage = holder.getView(R.id.tv_conversion_message);
        //锁定中   + list.get(position).getPostTitle()
        String clock = "<font color=\"#FFA07A\">【锁定中】</font>";

        String message = "<font color=\"#000000\">" + bean.getPostTitle() + "</font>";
        //已关闭
        String close = "<font color=\"#cccccc\">【已关闭】</font>";
        if (bean.getPostState() == 1) {
            tvMessage.setText(Html.fromHtml(message));
        } else if (bean.getPostState() == 2) {
            tvMessage.setText(Html.fromHtml(clock + message));
        } else if (bean.getPostState() == 3) {
            tvMessage.setText(Html.fromHtml(close + message));
        } else {
            tvMessage.setText(Html.fromHtml(message));
        }


        List<String> imgList = PhotoUtils.getInstance().getListImage(bean.getPics());
        ScrollGridView gridViewTieZi = holder.getView(R.id.grid_conversion);
        ScrollGridView gridViewTieZiTwo = holder.getView(R.id.grid_conversion_two);

        LinearLayout llGrid = holder.getView(R.id.ll_grid);

        ImageView imgOne = holder.getView(R.id.img_one);

        imgOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtils.getInstance().toImageShowActivity(context, PhotoUtils.getInstance().getListImage(bean.getPics()), position);
            }
        });

        if (imgList.size() == 1) {
            GlideUtils.getInstance().displayImage(imgList.get(0), context, imgOne);
            gridViewTieZi.setVisibility(View.GONE);
            gridViewTieZiTwo.setVisibility(View.GONE);
            imgOne.setVisibility(View.VISIBLE);
            llGrid.setVisibility(View.GONE);
        } else if (imgList.size() == 4) {
            gridViewTieZi.setNumColumns(2);
            gridViewTieZi.setVisibility(View.GONE);
            gridViewTieZiTwo.setVisibility(View.VISIBLE);
            imgOne.setVisibility(View.GONE);
            ImageAddAdapter imageAddAdapter = new ImageAddAdapter(context, imgList, PhotoConstant.getInstance().IS_NO_DELETE);
            gridViewTieZiTwo.setAdapter(imageAddAdapter);
            llGrid.setVisibility(View.VISIBLE);
        } else if (imgList.size() == 0) {
            gridViewTieZi.setVisibility(View.GONE);
            gridViewTieZiTwo.setVisibility(View.GONE);
            imgOne.setVisibility(View.GONE);
            llGrid.setVisibility(View.GONE);
        } else {
            gridViewTieZi.setNumColumns(3);
            imgOne.setVisibility(View.GONE);
            gridViewTieZi.setVisibility(View.VISIBLE);
            gridViewTieZiTwo.setVisibility(View.GONE);
            llGrid.setVisibility(View.GONE);
            ImageAddAdapter imageAddAdapter = new ImageAddAdapter(context, imgList, PhotoConstant.getInstance().IS_NO_DELETE);
            gridViewTieZi.setAdapter(imageAddAdapter);

        }


        TextView tvTime = holder.getView(R.id.tv_conversion_time);
        tvTime.setText(DateUtils.getFormMinuteData(bean.getCreateTime()));

        TextView tvViewPage = holder.getView(R.id.tv_view_num);
        tvViewPage.setText(bean.getPageView() + "");

        TextView tvCommonNum = holder.getView(R.id.tv_common_num);
        tvCommonNum.setText(bean.getReplies() + "");

        LinearLayout rlTieZiClick = holder.getView(R.id.rl_tiezi_click);


        rlTieZiClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClick.onForumClick(position);

            }
        });

        gridViewTieZi.setOnTouchInvalidPositionListener(new ScrollGridView.OnTouchInvalidPositionListener() {
            @Override
            public boolean onTouchInvalidPosition(int motionEvent) {
                itemClick.onForumClick(position);

                return false;
            }
        });

    }

    public void setItemClick(OnForumItemClick itemClick) {
        this.itemClick = itemClick;
    }
}
