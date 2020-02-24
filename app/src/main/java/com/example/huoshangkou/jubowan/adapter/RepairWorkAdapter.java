package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.RepairWorkBean;
import com.example.huoshangkou.jubowan.inter.OnRepairWorkCallBack;
import com.example.huoshangkou.jubowan.utils.StringUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：RepairWorkAdapter
 * 描述：
 * 创建时间：2017-03-02  14:38
 */

public class RepairWorkAdapter extends BaseAbstractAdapter<RepairWorkBean> {

    private OnRepairWorkCallBack workCallBack;

    public RepairWorkAdapter(Context context, List<RepairWorkBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, final RepairWorkBean bean, final int position) {

        TextView tvStartTime = holder.getView(R.id.tv_start_time);
        TextView tvEndTime = holder.getView(R.id.tv_end_time);
        final TextView etWorkCompany = holder.getView(R.id.et_work_company);
        final TextView etWorkType = holder.getView(R.id.et_work_type);

        TextView tvDelete = holder.getView(R.id.tv_delete);
        if (StringUtils.isNoEmpty(bean.getStartTime())) {
            tvStartTime.setText(bean.getStartTime());
        } else {
            tvStartTime.setText(R.string.start_time);
        }

        if (StringUtils.isNoEmpty(bean.getEndTime())) {
            tvEndTime.setText(bean.getEndTime());
        } else {
            tvEndTime.setText(R.string.end_time);
        }

        etWorkCompany.setText(bean.getWorkCompany());
        etWorkType.setText(bean.getWorkType());

        TextView tvWork = holder.getView(R.id.tv_work);

        tvWork.setText("工作经历" + (position + 1));

        etWorkCompany.setTag(position);
        etWorkType.setTag(position);

        tvStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                workCallBack.onStartTime(position);
            }
        });

        tvEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                workCallBack.onEndTime(position);
            }
        });

        //删除工作经历
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //添加
                if (position == 0) {
                    workCallBack.onAddWork();

                } else {
                    workCallBack.deleteWork(position);
                }
            }
        });

        etWorkCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                workCallBack.onAddCompany(position);
            }
        });


        etWorkType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                workCallBack.onAddWorkType(position);
            }
        });

        if (position == 0) {
            tvDelete.setText("添加");
        } else {
            tvDelete.setText("删除");
        }
    }


    public void setWorkCallBack(OnRepairWorkCallBack workCallBack) {
        this.workCallBack = workCallBack;
    }

}
