package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.AllCustomerBean;
import com.example.huoshangkou.jubowan.constant.PersonConstant;
import com.example.huoshangkou.jubowan.inter.OnPositionClick;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;
import com.example.huoshangkou.jubowan.utils.PhoneUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：CustomerAllAdapter
 * 描述：
 * 创建时间：2019-08-26  10:26
 */

public class CustomerAllAdapter extends BaseAbstractAdapter<AllCustomerBean.DBean.ReListBean> {

    public OnPositionClick putCallBack;
    private boolean isManage = false;
    private String userId = "";

    public CustomerAllAdapter(Context context, List<AllCustomerBean.DBean.ReListBean> listData, boolean isManage,String userId , int resId) {
        super(context, listData, resId);
        this.isManage = isManage;
        this.userId = userId;
    }

    @Override
    public void convert(ViewHolder holder, final AllCustomerBean.DBean.ReListBean bean, final int position) {
        TextView tvCompany = holder.getView(R.id.tv_company_name);
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvRoleName = holder.getView(R.id.tv_role_name);
        ImageView imgState = holder.getView(R.id.iv_state);
        ImageView imgPhone = holder.getView(R.id.iv_phone);
        TextView tvState = holder.getView(R.id.tv_state);
        TextView tvVisitor = holder.getView(R.id.tv_visitor);
        TextView tvSaleName = holder.getView(R.id.tv_sale_name);

        tvSaleName.setText("所属销售："+bean.getSaleMan());
        tvCompany.setText(bean.getCompany());
        tvName.setText(bean.getName());
        tvRoleName.setText(StringUtils.getNoEmptyStr(bean.getProfession()));

        switch (bean.getCustomerType()) {
            case 0:
                imgState.setVisibility(View.GONE);
                imgPhone.setVisibility(View.VISIBLE);
                if ((bean.getSaleManId() + "").equals(userId)) {
                    tvState.setText("");
                    tvVisitor.setText("");
                    tvVisitor.setVisibility(View.GONE);
                } else {
                    if (!isManage) {
                        tvVisitor.setVisibility(View.VISIBLE);
                        tvState.setText("");
                        if (bean.getAskState() == 0) {
                            tvVisitor.setText("索要");
                            tvVisitor.setTextColor(context.getResources().getColor(R.color.main_tab_blue));
                        } else {
                            tvVisitor.setText("已索要");
                            tvVisitor.setTextColor(context.getResources().getColor(R.color.red));
                        }
                        imgPhone.setVisibility(View.GONE);
                    }
                }
                break;
            case 1:
                imgState.setVisibility(View.VISIBLE);
                imgPhone.setVisibility(View.VISIBLE);
                tvVisitor.setVisibility(View.GONE);
                imgState.setImageResource(R.mipmap.dangerous_customer);
                tvState.setText("");
                tvVisitor.setText("");
                break;
            case 2:
            case 3:
                tvVisitor.setVisibility(View.GONE);
                imgState.setVisibility(View.VISIBLE);
                imgPhone.setVisibility(View.VISIBLE);
                imgState.setImageResource(R.mipmap.main_customer);
                tvState.setText("");
                tvVisitor.setText("");
                break;
            case 4:
                tvVisitor.setVisibility(View.GONE);
                imgPhone.setVisibility(View.VISIBLE);
                imgState.setVisibility(View.VISIBLE);
                imgState.setImageResource(R.mipmap.important_customer);
                tvState.setText("");
                tvVisitor.setText("");
                break;
        }

        imgPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CopyIosDialogUtils.getInstance().getIosDialog(context, "是否拨打：" + bean.getContactMode(), new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        PhoneUtils.getInstance().callDialog(bean.getContactMode(), context);
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
            }
        });
        tvVisitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putCallBack.onPositionClick(position);
            }
        });
    }

    public void setPutCallBack(OnPositionClick putCallBack) {
        this.putCallBack = putCallBack;
    }
}
