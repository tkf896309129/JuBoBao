package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.ApproveCheckListBean;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：ApproveAdapter
 * 描述：
 * 创建时间：2017-03-17  10:15
 */

public class ApproveAdapter extends BaseAbstractAdapter<ApproveCheckListBean> {

    public ApproveAdapter(Context context, List<ApproveCheckListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, ApproveCheckListBean bean, int position) {
        ImageView imgType = holder.getView(R.id.iv_approve_state);
        TextView tvState = holder.getView(R.id.tv_approve_sate);
        TextView tvType = holder.getView(R.id.tv_approve_type);
        TextView tvName = holder.getView(R.id.tv_approve_name);
        TextView tvIntro = holder.getView(R.id.tv_intro);
        TextView tvTime = holder.getView(R.id.tv_time);
        tvName.setText(bean.getUserName());

        tvTime.setText(bean.getCreateTime());
//        tvTime.setText(DateUtils.getInstance().getFormMinuteDesData(bean.getCreateTime()));
        //-1=未审批，1=同意，0=不同意 2无效 3未读抄送  4已读抄送
        switch (bean.getApprovalOver()) {
            case 1:
                tvState.setText("同意");
                tvState.setTextColor(context.getResources().getColor(R.color.main_tab_blue_all));
                break;
            case 0:
                tvState.setText("不同意");
                tvState.setTextColor(context.getResources().getColor(R.color.approve_red));
                break;
            case 2:
                tvState.setText("被改派");
                tvState.setTextColor(context.getResources().getColor(R.color.approve_red));
                break;
        }

        if (StringUtils.isNoEmpty(bean.getRemark())) {
            tvIntro.setText(bean.getRemark());
        } else {
            tvIntro.setText("垫付款申请公司：" + bean.getTypeName());
        }

        //1=费用报销 2=用款申请 3=请假 4=出差 1006=车队  承兑
        switch (bean.getApprovalTypeID()) {
            case 1:
                imgType.setImageResource(R.mipmap.baoxiao_icon);
                tvType.setText("费用报销");
                break;
            case 2:
                imgType.setImageResource(R.mipmap.yongkuan_icon);
                tvType.setText("普通用款");
                break;
            case 3:
                imgType.setImageResource(R.mipmap.qingjia_icon);
                tvType.setText("请假");
                break;
            case 4:
                imgType.setImageResource(R.mipmap.chucai_icon);
                tvType.setText("出差");
                break;
            case 1006:
                imgType.setImageResource(R.mipmap.chengdui);
                tvType.setText("承兑");
                break;
            case 1009:
                imgType.setImageResource(R.mipmap.icon_wdsp_jiekuan);
                tvType.setText("齐家借款");
                break;
            case 1007:
                imgType.setImageResource(R.mipmap.icon_wdsp_edu);
                tvType.setText("信用额度");
                tvIntro.setText("信用额度：" + bean.getLoan() + "元");
                break;
            case 1008:
                imgType.setImageResource(R.mipmap.approval_disbursements);
                tvType.setText("垫付款");
                tvIntro.setText("垫付款申请公司：" + bean.getTypeName());
                break;
            case 1100:
                imgType.setImageResource(R.mipmap.icon_wdsp_qita);
                tvType.setText("其他");
                break;
            case 1101:
                imgType.setImageResource(R.mipmap.icon_wdsp_yewu);
                tvType.setText("业务用款");
                break;
            //白条额度审批
            case 1010:
                imgType.setImageResource(R.mipmap.edu);
                tvType.setText("白条额度");
                break;
            //白条支付审批
            case 1011:
                imgType.setImageResource(R.mipmap.shenpi);
                tvType.setText("白条审批");
                break;
            case 1201:
                imgType.setImageResource(R.mipmap.icon_wdsp_yewu);
                tvType.setText("业务用款");
                break;
            case 1301:
                imgType.setImageResource(R.mipmap.baoxiao_icon);
                tvType.setText("垫付款支付");
                break;
            case 1401:
                imgType.setImageResource(R.mipmap.incoimic);
                tvType.setText("内部往来款");
                break;
        }
        if (StringUtils.isNoEmpty(bean.getRemark())) {
            tvIntro.setText(bean.getRemark());
        } else {
            tvIntro.setText("未填写备注说明");
        }
    }
}
