package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.LoginActivity;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.BuyYaunListBean;
import com.example.huoshangkou.jubowan.inter.OnAddCarClickBack;
import com.example.huoshangkou.jubowan.inter.OnListViewDeleteCallBack;
import com.example.huoshangkou.jubowan.inter.onAnimItemClick;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LoginUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：BuyYuanAdapter
 * 描述：
 * 创建时间：2017-02-17  13:14
 */

public class BuyYuanAdapter extends BaseAbstractAdapter<BuyYaunListBean> {

    private int itemClick = -1;
    private OnListViewDeleteCallBack deleteCallBack;
    //点击购物车
    private OnAddCarClickBack carClickBack;

    private onAnimItemClick animItemClick;

    public BuyYuanAdapter(Context context, List<BuyYaunListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, final BuyYaunListBean bean, final int position) {

        //加入购物车
        ImageView imgCar = holder.getView(R.id.iv_car);

        final RelativeLayout rlCar = holder.getView(R.id.rl_car);
        final ImageView imgPic = holder.getView(R.id.iv_buy_pic);

        GlideUtils.getInstance().displayImage(bean.getPic(), context, imgPic);

        TextView tvBrand = holder.getView(R.id.tv_brand);
        tvBrand.setText("品牌：" + bean.getBrandName());
        TextView tvLevel = holder.getView(R.id.tv_level);
        tvLevel.setText("规格：" + bean.getXy());




        TextView tvStandard = holder.getView(R.id.tv_standard);
        if(bean.getClassName().equals("Low-E")){
            tvStandard.setText("膜系：" + bean.getMoxiName());
        }else{
            tvStandard.setText("等级：" + bean.getLevelName());
        }

        TextView tvType = holder.getView(R.id.tv_type);
        tvType.setText("品类：" + bean.getClassName());

        TextView tvThick = holder.getView(R.id.tv_thick);
        tvThick.setText("厚度：" + bean.getWeight() + "mm");

        //改变字体颜色以及大小
        TextView tvPrice = holder.getView(R.id.tv_price);

        String price = "￥" + bean.getPrice() + "/吨";
        int colorPosition = (price).indexOf(".");
        int linePosition = price.indexOf("/");


        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append(price);
        spannableString.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.main_tab_blue)), 1, linePosition, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        //字体大小
        AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(28);
        AbsoluteSizeSpan sizeSpan2 = new AbsoluteSizeSpan(28);
        spannableString.setSpan(sizeSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        spannableString.setSpan(sizeSpan2, colorPosition, linePosition, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        tvPrice.setText(spannableString);

        imgCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!LoginUtils.getInstance().isLogin(context)) {
                    IntentUtils.getInstance().toActivity(context, LoginActivity.class);
                    return;
                }

                int[] startLocation = new int[2];
                imgPic.getLocationInWindow(startLocation);
                Drawable drawable = imgPic.getDrawable();
                carClickBack.onAddCarClick(bean.getID() + "", drawable, startLocation);
            }
        });
    }

    public int getItemClick() {
        return itemClick;
    }

    public void setItemClick(int itemClick) {
        this.itemClick = itemClick;
    }

    public OnListViewDeleteCallBack getDeleteCallBack() {
        return deleteCallBack;
    }

    public void setDeleteCallBack(OnListViewDeleteCallBack deleteCallBack) {
        this.deleteCallBack = deleteCallBack;
    }

    public void setCarClickBack(OnAddCarClickBack carClickBack) {

        this.carClickBack = carClickBack;
    }

    public void setAnimItemClick(onAnimItemClick animItemClick) {
        this.animItemClick = animItemClick;
    }
}
