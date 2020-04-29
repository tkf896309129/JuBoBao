package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.AddressListBean;
import com.example.huoshangkou.jubowan.inter.DeleteAddressBack;
import com.example.huoshangkou.jubowan.inter.EditAddressBack;
import com.example.huoshangkou.jubowan.inter.SetMorenBack;
import com.example.huoshangkou.jubowan.utils.CopyIosDialogUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：AddressListAdapter
 * 描述：地址管理列表适配器
 * 创建时间：2017-02-14  16:01
 */

public class AddressListAdapter extends BaseAbstractAdapter<AddressListBean> {

    //设置默认地址回调
    private SetMorenBack morenCheck;
    //删除地址回调
    private DeleteAddressBack deleteAddressBack;
    //编辑地址回调
    private EditAddressBack editAddressBack;

    public AddressListAdapter(Context context, List<AddressListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, final AddressListBean bean, final int position) {

        //收货人
        TextView tvReceive = holder.getView(R.id.tv_receive);
        //联系人
        TextView tvLinkPhone = holder.getView(R.id.tv_link_phone);
        //收货地址
        TextView tvAddress = holder.getView(R.id.tv_address);


        //设为默认
        LinearLayout llSetCheck = holder.getView(R.id.ll_set_check);
        llSetCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CopyIosDialogUtils.getInstance().getIosDialog(context, context.getString(R.string.is_moren_address), new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        morenCheck.onGetAddressId(bean.getAdressID(), bean.getProvinces() + " " + bean.getDetailAddress(), bean.getLinkMan(), bean.getLinkTel());
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
            }
        });


        //编辑地址
        TextView tvEidt = holder.getView(R.id.tv_edit_address);
        tvEidt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editAddressBack.setEditAddress(bean);
            }
        });


        //删除地址
        TextView tvDel = holder.getView(R.id.tv_delete_address);
        tvDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CopyIosDialogUtils.getInstance().getIosDialog(context, context.getString(R.string.is_delete_address), new CopyIosDialogUtils.iosDialogClick() {
                    @Override
                    public void confimBtn() {
                        deleteAddressBack.deleteAddress(bean.getAdressID(), position);
                    }

                    @Override
                    public void cancelBtn() {

                    }
                });
            }
        });

        ImageView imgSetCheck = holder.getView(R.id.iv_set_check);
        if (bean.isDefaultAddress()) {
            imgSetCheck.setImageResource(R.mipmap.dz_gouxuan_icon_on);
        } else {
            imgSetCheck.setImageResource(R.mipmap.dz_gouxuan_icon_off);
        }

        tvReceive.setText(bean.getLinkMan());
        tvLinkPhone.setText(bean.getLinkTel());
        tvAddress.setText(bean.getProvinces() + " " + bean.getDetailAddress());
    }

    public SetMorenBack getMorenCheck() {
        return morenCheck;
    }

    public void setMorenCheck(SetMorenBack morenCheck) {
        this.morenCheck = morenCheck;
    }

    public DeleteAddressBack getDeleteAddressBack() {
        return deleteAddressBack;
    }

    public void setDeleteAddressBack(DeleteAddressBack deleteAddressBack) {
        this.deleteAddressBack = deleteAddressBack;
    }

    public EditAddressBack getEditAddressBack() {
        return editAddressBack;
    }

    public void setEditAddressBack(EditAddressBack editAddressBack) {
        this.editAddressBack = editAddressBack;
    }
}
