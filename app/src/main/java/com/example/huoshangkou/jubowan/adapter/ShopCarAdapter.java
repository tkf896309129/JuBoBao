package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.ShopCarFunction;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.ShopCarListBean;
import com.example.huoshangkou.jubowan.bean.ShopCarListNewBean;
import com.example.huoshangkou.jubowan.constant.OrderTypeConstant;
import com.example.huoshangkou.jubowan.inter.NewShopActionCallBack;
import com.example.huoshangkou.jubowan.inter.OnPositionClick;
import com.example.huoshangkou.jubowan.inter.OnShopCarAddDecreaseCallBack;
import com.example.huoshangkou.jubowan.inter.OnShopCarChooseCallBack;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：ShopCarAdapter
 * 描述：
 * 创建时间：2020-03-05  15:31
 */

public class ShopCarAdapter extends BaseAbstractAdapter<ShopCarListBean> {
    //购物车选择点击事件
    OnPositionClick chooseCallBack;
    //购物车添加、减少回调接口
    NewShopActionCallBack decreaseCallBack;

    public ShopCarAdapter(Context context, List<ShopCarListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, ShopCarListBean bean, final int position) {
        final SwipeMenuLayout menuLayout = holder.getView(R.id.swip_shop_car);
        ImageView imgAdd = (ImageView) holder.getView(R.id.iv_add);
        ImageView imgDecrease = (ImageView) holder.getView(R.id.iv_decrease);
        TextView tvNum = (TextView) holder.getView(R.id.tv_num);
        TextView tvPrice = (TextView) holder.getView(R.id.tv_price);
        TextView tvDelete = (TextView) holder.getView(R.id.tv_delete);
        ImageView imgCheck = (ImageView) holder.getView(R.id.iv_check);
        LinearLayout llClick = (LinearLayout) holder.getView(R.id.ll_check);
        ImageView imgPic = (ImageView) holder.getView(R.id.iv_car_pic);


        //原：品牌   辅：类别
        TextView tvBrand = (TextView) holder.getView(R.id.tv_brand);
        //原：级别   辅：品牌
        TextView tvLevel = (TextView) holder.getView(R.id.tv_level);
        //原：规格   辅：规格
        TextView tvStandard = (TextView) holder.getView(R.id.tv_standard);
        //原：类别
        TextView tvClass = (TextView) holder.getView(R.id.tv_class);
        //原：厚度
        TextView tvThick = (TextView) holder.getView(R.id.tv_thick);

        final ShopCarListBean shopCarListBean = bean;

        GlideUtils.getInstance().displayImage(shopCarListBean.getPic(), context, imgPic);
        //原片
        if (shopCarListBean.getType().equals(ShopCarFunction.getInstance().YUAN)) {
            tvBrand.setText("品牌：" + shopCarListBean.getBrandName());
            tvLevel.setText("级别：" + shopCarListBean.getLevelName());
            tvStandard.setText("规格：" + shopCarListBean.getXy());
            tvClass.setText("类别：" + shopCarListBean.getClassName());
            tvThick.setText("厚度：" + shopCarListBean.getWeight() + "mm");
            //辅材
        } else if (shopCarListBean.getType().equals(ShopCarFunction.getInstance().FU)) {
            tvBrand.setText("类别：" + shopCarListBean.getClassName());
            tvStandard.setText("品牌：" + shopCarListBean.getBrandName());
            tvLevel.setText("规格：" + shopCarListBean.getGuigeName());
            //原料
        } else {
            tvBrand.setText("类别：" + shopCarListBean.getCategoryName());
            tvStandard.setText("品牌：" + shopCarListBean.getBrandName());
            tvLevel.setText("等级：" + shopCarListBean.getLevelName());
            tvClass.setText("品类：" + shopCarListBean.getClassName());
        }

        if (!StringUtils.isNoEmpty(shopCarListBean.getToNum())) {
            tvNum.setText("0");
        } else {
            tvNum.setText(shopCarListBean.getToNum());
        }


        tvNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decreaseCallBack.onClickPosition(shopCarListBean.getType(), shopCarListBean.getID(), Double.parseDouble(shopCarListBean.getToNum()), position);
            }
        });

        llClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseCallBack.onPositionClick(position);
            }
        });

        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuLayout.quickClose();
                decreaseCallBack.deleteOrder(position);
            }
        });

        //增加点击
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decreaseCallBack.onAddCar(shopCarListBean.getType(), shopCarListBean.getID(), OrderTypeConstant.getInstance().ADD_CAR, position);
            }
        });

        //增加点击
        imgDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shopCarListBean.getToNum().equals("1") || shopCarListBean.getToNum().equals("1.00")) {
                    return;
                }
//                double num = Double.parseDouble(shopCarListBean.getToNum())-1;
//                ToastUtils.getMineToast(shopCarListBean.getToNum());
                decreaseCallBack.onAddCar(shopCarListBean.getType(), shopCarListBean.getID(), OrderTypeConstant.getInstance().DES_CAR, position);
            }
        });

        //设置是否被选中
        if (bean.isCheck()) {
            imgCheck.setImageResource(R.mipmap.gouxuany_icon_on);
        } else {
            imgCheck.setImageResource(R.mipmap.gouxuany_icon_off);
        }

        String price = "￥" + shopCarListBean.getPrice() + "/" + shopCarListBean.getNameUnit();
        int colorPosition = (price).indexOf(".");
        int linePosition = price.indexOf("/");
        if (colorPosition >= 0 && linePosition >= 0) {
            SpannableStringBuilder spannableString = new SpannableStringBuilder();
            spannableString.append(price);
            spannableString.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.main_tab_blue_all)), 1, linePosition, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

            //字体大小
            AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(28);
            AbsoluteSizeSpan sizeSpan2 = new AbsoluteSizeSpan(28);
            spannableString.setSpan(sizeSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            spannableString.setSpan(sizeSpan2, colorPosition, linePosition, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

            tvPrice.setText(spannableString);
        } else {
            tvPrice.setText("￥" + shopCarListBean.getPrice());
        }

    }


    public void setChooseCallBack(OnPositionClick chooseCallBack) {
        this.chooseCallBack = chooseCallBack;
    }

    public void setDecreaseCallBack(NewShopActionCallBack decreaseCallBack) {
        this.decreaseCallBack = decreaseCallBack;
    }

//    public void setDeleteCallBack(OnPositionClick deleteCallBack) {
//        this.deleteCallBack = deleteCallBack;
//    }
}
