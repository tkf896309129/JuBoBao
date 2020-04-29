package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.DatePlanBean;
import com.example.huoshangkou.jubowan.inter.OnPositionCallBack;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：CustomerDateAdapter
 * 描述：
 * 创建时间：2019-08-26  08:21
 */

public class CustomerDateAdapter extends BaseAbstractAdapter<DatePlanBean.DBean.ResultBean> {

    private OnPositionCallBack onDeletePositionCallBack;
    private OnPositionCallBack onChangePositionCallBack;
    private boolean isMange = false;

    public CustomerDateAdapter(Context context, List<DatePlanBean.DBean.ResultBean> listData, boolean isManage, int resId) {
        super(context, listData, resId);
        this.isMange = isManage;
    }

    @Override
    public void convert(ViewHolder holder, DatePlanBean.DBean.ResultBean bean, final int position) {
        TextView tvContent = holder.getView(R.id.tv_content);
        TextView tvTime = holder.getView(R.id.tv_time);
        TextView tvChange = holder.getView(R.id.tv_change);
        TextView tvDelete = holder.getView(R.id.tv_delete);
        final SwipeMenuLayout swipeMenuLayout = holder.getView(R.id.swim_menu);
        if(isMange){
            swipeMenuLayout.setSwipeEnable(false);
        }
        tvTime.setText("  " + bean.getTimeslot());
        tvContent.setText(bean.getTitle());

        tvChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onChangePositionCallBack.onPositionClick(position);
                swipeMenuLayout.quickClose();
            }
        });
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDeletePositionCallBack.onPositionClick(position);
                swipeMenuLayout.quickClose();
            }
        });
    }

    public void setOnDeletePositionCallBack(OnPositionCallBack onDeletePositionCallBack) {
        this.onDeletePositionCallBack = onDeletePositionCallBack;
    }

    public void setOnChangePositionCallBack(OnPositionCallBack onChangePositionCallBack) {
        this.onChangePositionCallBack = onChangePositionCallBack;
    }
}
