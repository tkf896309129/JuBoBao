package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.ApproveListBean;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：ApproveAgreeAdapter
 * 描述：
 * 创建时间：2017-03-22  13:08
 */

public class ApproveAgreeAdapter extends BaseAbstractAdapter<ApproveListBean> {

    public ApproveAgreeAdapter(Context context, List<ApproveListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, ApproveListBean bean, int position) {
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvState = holder.getView(R.id.tv_approve_state);
        TextView tvRemark = holder.getView(R.id.tv_remark);
        ImageView imgPic = holder.getView(R.id.iv_pic);

        TextView tvCheckTime = holder.getView(R.id.tv_check_time);
        if (StringUtils.isNoEmpty(bean.getCreateTime())) {

        } else {
            tvCheckTime.setText("无审批时间");
        }
        if (StringUtils.isNoEmpty(bean.getApprovalremark())) {
            tvRemark.setText(bean.getApprovalremark());
            tvRemark.setVisibility(View.VISIBLE);
        } else {
            tvRemark.setVisibility(View.GONE);
        }


        tvName.setText(bean.getApprovalUserName());
        GlideUtils.getInstance().displayCricleImage(context, bean.getHeadPic(), imgPic);
        String timeType = "";
        //-1=未审批，1=同意，0=不同意 2无效 3未读抄送  4已读抄送
        switch (bean.getApprovalOver()) {
            case 1:
                tvState.setText("同意");
                tvState.setTextColor(context.getResources().getColor(R.color.main_tab_blue_all));
                timeType = "审批时间：";
                break;
            case 0:
                tvState.setText("不同意");
                tvState.setTextColor(context.getResources().getColor(R.color.approve_red));
                timeType = "审批时间：";
                break;
            case 2:
                tvState.setText("被改派");
                tvState.setTextColor(context.getResources().getColor(R.color.approve_red));
                timeType = "改派时间：";
                break;
            case -1:
                tvState.setText("未审批");
                tvState.setTextColor(context.getResources().getColor(R.color.gray));
                timeType = "提交时间：";
                break;
            case 3:
                tvState.setText("抄送(未读)");
                tvState.setTextColor(context.getResources().getColor(R.color.approve_red));
                timeType = "抄送时间：";
                break;
            case 4:
                tvState.setText("抄送(已读)");
                tvState.setTextColor(context.getResources().getColor(R.color.approve_red));
                timeType = "阅读时间：";
                break;
            default:
                tvState.setText("未知状态");
                tvState.setTextColor(context.getResources().getColor(R.color.approve_red));
                timeType = "审批时间：";
                break;
        }
        if (bean.getApprovalOver() == -1) {
            tvCheckTime.setText("无审批时间");
        } else {
            tvCheckTime.setText(timeType + (StringUtils.isNoEmpty(bean.getCreateTime()) ? bean.getCreateTime() : "无审批时间"));
        }
    }
}
