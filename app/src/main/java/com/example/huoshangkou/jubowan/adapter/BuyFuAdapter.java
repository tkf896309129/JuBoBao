package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.LoginActivity;
import com.example.huoshangkou.jubowan.activity.function.BuyFunction;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.BuyBean;
import com.example.huoshangkou.jubowan.bean.BuyFuListBean;
import com.example.huoshangkou.jubowan.bean.BuyYaunListBean;
import com.example.huoshangkou.jubowan.bean.BuyYuanBean;
import com.example.huoshangkou.jubowan.inter.OnAddCarClickBack;
import com.example.huoshangkou.jubowan.inter.onAnimItemClick;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.IntentUtils;
import com.example.huoshangkou.jubowan.utils.LoginUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：BuyFuAdapter
 * 描述：
 * 创建时间：2017-02-17  15:54
 */

public class BuyFuAdapter extends BaseAbstractAdapter<BuyFuListBean> {

    private OnAddCarClickBack carClickBack;

    private onAnimItemClick animItemClick;

    public BuyFuAdapter(Context context, List<BuyFuListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, final BuyFuListBean bean, int position) {

        final ImageView imgPro = holder.getView(R.id.iv_buy_pic);
        TextView tvType = holder.getView(R.id.tv_type);
        TextView tvBrand = holder.getView(R.id.tv_brand);
        TextView tvStandard = holder.getView(R.id.tv_standard);
        TextView tvPrice = holder.getView(R.id.tv_price);
        ImageView imgCar = holder.getView(R.id.iv_car);
        TextView tvSaleNum = holder.getView(R.id.tv_sale_num);


        GlideUtils.getInstance().displayImage(bean.getPic(), context, imgPro);
        tvType.setText("类别：" + bean.getClassName());
        tvBrand.setText("品牌：" + bean.getBrandName());
        tvStandard.setText("规格：" + bean.getGuigeName());
        tvSaleNum.setText("销量：" + bean.getSaleNum() + bean.getNameUnit());

        String price = "￥" + bean.getPrice() + "/" + bean.getNameUnit();
        int colorPosition = (price).indexOf(".");
        int linePosition = price.indexOf("/");

        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append(price);
        spannableString.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.main_tab_blue_all)), 1, linePosition, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

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
                imgPro.getLocationInWindow(startLocation);
                Drawable drawable = imgPro.getDrawable();
                carClickBack.onAddCarClick(bean.getID(), drawable, startLocation);
            }
        });
        TextView tvOrderDetail = holder.getView(R.id.tv_order_detail);
        tvOrderDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BuyFunction.getInstance().getOrderDetail(context,"1",bean.getID());
            }
        });

    }

    public void setCarClickBack(OnAddCarClickBack carClickBack) {
        this.carClickBack = carClickBack;
    }

    public void setAnimItemClick(onAnimItemClick animItemClick) {
        this.animItemClick = animItemClick;
    }
}
