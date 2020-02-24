package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.CheckSignBean;
import com.example.huoshangkou.jubowan.bean.CheckSignListBean;
import com.example.huoshangkou.jubowan.inter.CheckSignCallBack;
import com.example.huoshangkou.jubowan.utils.BlueTxtUtils;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：ReadyCheckSignAdapter
 * 描述：待审核的补签
 * 创建时间：2017-04-19  09:45
 */

public class ReadyCheckSignAdapter extends BaseAbstractAdapter<CheckSignListBean> {
    CheckSignCallBack signCallBack;

    public ReadyCheckSignAdapter(Context context, List<CheckSignListBean> listData, int resId) {
        super(context, listData, resId);

    }

    @Override
    public void convert(ViewHolder holder, final CheckSignListBean bean, final int position) {
        ImageView imgPic = holder.getView(R.id.iv_head_pic_check);
//        TextView tvName = holder.getView(R.id.tv_name_check);
        TextView tvName = holder.getView(R.id.tv_name_check);

//        tvName.setText(bean.getLinkMan() + "");

        TextView tvState = holder.getView(R.id.tv_time_check);
        TextView tvSignTime = holder.getView(R.id.tv_sign_time_check);
        TextView tvRemark = holder.getView(R.id.tv_remark_check);
        TextView tvAgree = holder.getView(R.id.tv_agree_check);
        TextView tvDisAgree = holder.getView(R.id.tv_dis_agree_check);
        LinearLayout rlCheck = holder.getView(R.id.rl_sign_check);

        rlCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.getMineToast(position + "" + bean.getLeakTime());
            }
        });


        GlideUtils.getInstance().displayCricleImage(context, bean.getPic(), imgPic);
        tvName.setText(Html.fromHtml(BlueTxtUtils.getInstance().getBlueTxt(bean.getLinkMan()) + "补签申请"));

        tvSignTime.setText("漏签时间：" + bean.getLeakTime() + " " + bean.getTimeSpan());
        tvRemark.setText("备注：" + bean.getRemark());

        if (bean.isNeedCheck()) {
            rlCheck.setVisibility(View.VISIBLE);
        } else {
            rlCheck.setVisibility(View.GONE);
        }

        if (bean.getApprovalOver() == 1) {
            tvState.setText("同意");
            tvState.setTextColor(context.getResources().getColor(R.color.main_tab_blue));
        } else if (bean.getApprovalOver() == 0) {
            tvState.setText("不同意");
            tvState.setTextColor(context.getResources().getColor(R.color.red));
        }

        tvAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signCallBack.onAgree(bean.getID() + "");
            }
        });

        tvDisAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signCallBack.onDisAgree(bean.getID() + "");
            }
        });

    }

    public CheckSignCallBack getSignCallBack() {
        return signCallBack;
    }

    public void setSignCallBack(CheckSignCallBack signCallBack) {
        this.signCallBack = signCallBack;
    }
}
