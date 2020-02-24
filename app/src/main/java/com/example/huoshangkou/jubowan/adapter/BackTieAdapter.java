package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.LoginActivity;
import com.example.huoshangkou.jubowan.activity.function.TieDetailFunction;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.RepliesListBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.inter.BackTieCallBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.DataAgoUtils;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LoginUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.ScrollListView;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：BackTieAdapter
 * 描述：回帖
 * 创建时间：2017-04-07  16:15
 */

public class BackTieAdapter extends BaseAbstractAdapter<RepliesListBean> {

    private int mPostState;
    private boolean mIsTieManager;

    public BackTieAdapter(Context context, List<RepliesListBean> listData, int resId, int postState, boolean isTieManager) {
        super(context, listData, resId);
        mPostState = postState;
        mIsTieManager = isTieManager;
    }

    private BackTieCallBack backTieCallBack;

    @Override
    public void convert(ViewHolder holder, final RepliesListBean bean, final int position) {
        ScrollListView listView = holder.getView(R.id.lv_common_back);

        ReceiveTieAdapter receiveTieAdapter = new ReceiveTieAdapter(context, bean.getReGetLTRepliesToo(), R.layout.item_receive_tie, bean.getNickname());
        listView.setAdapter(receiveTieAdapter);
        listView.setDividerHeight(0);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (!LoginUtils.getInstance().isLogin(context)) {
                    IntentUtils.getInstance().toActivity(context, LoginActivity.class);
                    return;
                }

                if ((bean.getReGetLTRepliesToo().get(i).getUserID() + "").equals(PersonConstant.getInstance().getUserId())) {
                    ToastUtils.getMineToast("不能对自己的回帖进行回帖");
                    return;
                }
                backTieCallBack.onBackTie(bean.getReGetLTRepliesToo().get(i).getNickname(), bean.getPostID() + "", bean.getReGetLTRepliesToo().get(i).getID() + "");
            }
        });


        final TextView tvZanAnim = holder.getView(R.id.tv_zan_anim);
        TextView tvTime = holder.getView(R.id.tv_time);
        TextView tvReplies = holder.getView(R.id.tv_replies);
        final RelativeLayout llZanNum = holder.getView(R.id.ll_zan_num);
        final ImageView imgZan = holder.getView(R.id.iv_zan);
        final TextView tvZanNum = holder.getView(R.id.tv_zan_num);


        tvReplies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if ((bean.getUserID() + "").equals(PersonConstant.getInstance().getUserId())) {
//                    ToastUtils.getMineToast("不能对自己的回帖进行回帖");
//                    return;
//                }
                backTieCallBack.onBackTie(bean.getNickname(), bean.getPostID() + "", bean.getID() + "");
            }
        });

        tvTime.setText(DataAgoUtils.getTime(DateUtils.getFormMinuteData(bean.getCreateTime())));

        //没有赞
        if (bean.getGiveUpState() == 0) {
            imgZan.setImageResource(R.mipmap.dianzan_off_icon);
            //已经赞了
        } else {
            imgZan.setImageResource(R.mipmap.dianzan_on_icon);
        }

        //点赞数
        tvZanNum.setText(bean.getGiveUpCount() + "");
        //头像
        ImageView imgHead = holder.getView(R.id.iv_head_pic);
        GlideUtils.getInstance().displayCricleImage(context, bean.getUserPic(), imgHead);
        //昵称
        TextView tvName = holder.getView(R.id.tv_name);
        tvName.setText(bean.getNickname());
        //楼层
        TextView tvFloor = holder.getView(R.id.tv_floor_position);
        tvFloor.setText(bean.getFloorID() + "楼");
        //内容
        TextView tvContent = holder.getView(R.id.tv_content);
        tvContent.setText(bean.getRepliesText());


        llZanNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!LoginUtils.getInstance().isLogin(context)) {
                    IntentUtils.getInstance().toActivity(context, LoginActivity.class);
                    return;
                }

                //锁定中
                if (mPostState == 2) {
                    CopyIosDialogUtils.getInstance().getErrorDialog(context, "该贴已被锁定", new CopyIosDialogUtils.ErrDialogCallBack() {
                        @Override
                        public void confirm() {

                        }
                    });
                    //已关闭
                } else if (mPostState == 3) {
                    CopyIosDialogUtils.getInstance().getErrorDialog(context, "该贴已被关闭", new CopyIosDialogUtils.ErrDialogCallBack() {
                        @Override
                        public void confirm() {

                        }
                    });
                } else if (mIsTieManager) {
                    CopyIosDialogUtils.getInstance().getErrorDialog(context, "论坛管理不支持点赞", new CopyIosDialogUtils.ErrDialogCallBack() {
                        @Override
                        public void confirm() {

                        }
                    });
                } else {
                    if (bean.getGiveUpState() >= 1) {
                        //取消点赞
                        TieDetailFunction.getInstance().setZanOrCancel(context, bean.getPostID() + "",
                                bean.getID() + "", "0", tvZanAnim, "-1", llZanNum, tvZanNum, bean, imgZan);
                    } else {
                        //点赞
                        TieDetailFunction.getInstance().setZanOrCancel(context, bean.getPostID() + "",
                                bean.getID() + "", "1", tvZanAnim, "+1", llZanNum, tvZanNum, bean, imgZan);
                    }
                }
            }
        });
    }

    public void setBackTieCallBack(BackTieCallBack backTieCallBack) {
        this.backTieCallBack = backTieCallBack;
    }
}
