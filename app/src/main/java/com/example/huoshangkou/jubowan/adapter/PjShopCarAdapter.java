package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.PjCarListBean;
import com.example.huoshangkou.jubowan.constant.OrderTypeConstant;
import com.example.huoshangkou.jubowan.inter.OnPositionClick;
import com.example.huoshangkou.jubowan.inter.OnShopCarAddDecreaseCallBack;
import com.example.huoshangkou.jubowan.inter.OnShopCarChooseCallBack;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：PjShopCarAdapter
 * 描述：
 * 创建时间：2017-12-04  14:01
 */

public class PjShopCarAdapter extends BaseAbstractAdapter<PjCarListBean> {

    private OnPositionClick positionClick;
    //购物车添加、减少回调接口
    OnShopCarAddDecreaseCallBack decreaseCallBack;

    public PjShopCarAdapter(Context context, List<PjCarListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, final PjCarListBean bean, final int position) {
        ImageView imgPic = holder.getView(R.id.iv_shop_car);
        ImageView imgCheck = holder.getView(R.id.iv_check);
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvDelete = holder.getView(R.id.tv_delete);
        TextView tvCarDesc = holder.getView(R.id.tv_price);
        RelativeLayout rlCar = holder.getView(R.id.rl_car);

        TextView tvNumCal = holder.getView(R.id.tv_num_cal);
        ImageView imgAdd = holder.getView(R.id.iv_add);
        ImageView imgDecrease = holder.getView(R.id.iv_decrease);

        rlCar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                decreaseCallBack.deleteOrder("0", position, listData.get(position).getProductID());
                return false;
            }
        });

        tvNumCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decreaseCallBack.onClickPosition("", bean.getProductID(), 0, position, 0);
            }
        });

        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decreaseCallBack.deleteOrder("0", position, listData.get(position).getProductID());
            }
        });

        tvNumCal.setText(bean.getNumber());

        //增加点击
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decreaseCallBack.onAddCar(OrderTypeConstant.getInstance().ADD_CAR, bean.getProductID(), "", position, 0);
            }
        });

        //增加点击
        imgDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decreaseCallBack.onAddCar(OrderTypeConstant.getInstance().DEC_CAR, bean.getProductID(), "", position, 0);
            }
        });

        GlideUtils.getInstance().displayImage(bean.getPic(), context, imgPic);
        tvName.setText("配件信息：" + bean.getName() + "/" + bean.getGuiGeVal() + "/" + bean.getModelTitle());
        tvCarDesc.setText("￥" + bean.getPrice());
        if (bean.isCheck()) {
            imgCheck.setImageResource(R.mipmap.gouxuany_icon_on);
        } else {
            imgCheck.setImageResource(R.mipmap.gouxuany_icon_off);
        }

        imgCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                positionClick.onPositionClick(position);
            }
        });
    }

    public void setPositionClick(OnPositionClick positionClick) {
        this.positionClick = positionClick;
    }

    public void setDecreaseCallBack(OnShopCarAddDecreaseCallBack decreaseCallBack) {
        this.decreaseCallBack = decreaseCallBack;
    }

}
