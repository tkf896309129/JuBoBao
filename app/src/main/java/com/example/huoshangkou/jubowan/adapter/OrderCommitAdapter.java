package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.ShopCarFunction;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.ShopCarListBean;
import com.example.huoshangkou.jubowan.utils.GlideUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：OrderCommitAdapter
 * 描述：
 * 创建时间：2017-03-14  16:59
 */

public class OrderCommitAdapter extends BaseAbstractAdapter<ShopCarListBean> {

    public OrderCommitAdapter(Context context, List<ShopCarListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, ShopCarListBean bean, int position) {

        ImageView imgPic = holder.getView(R.id.iv_car_pic);
        TextView tvPrice = holder.getView(R.id.tv_price);

        //原：品牌   辅：类别
        TextView tvBrand = holder.getView(R.id.tv_brand);
        //原：级别   辅：品牌
        TextView tvLevel = holder.getView(R.id.tv_level);
        //原：规格   辅：规格
        TextView tvStandard = holder.getView(R.id.tv_standard);
        //原：类别
        TextView tvClass = holder.getView(R.id.tv_class);
        //原：厚度
        TextView tvThick = holder.getView(R.id.tv_thick);

        TextView tvNum = holder.getView(R.id.tv_num);

        tvNum.setText("x" + bean.getToNum());

        GlideUtils.getInstance().displayImage(bean.getPic(), context, imgPic);
        //原片
        if (bean.getType().equals(ShopCarFunction.getInstance().YUAN)) {
            tvBrand.setText("品牌：" + bean.getBrandName());
            tvLevel.setText("级别：" + bean.getLevelName());
            tvStandard.setText("规格：" + bean.getXy());
            tvClass.setText("类别：" + bean.getClassName());
            tvThick.setText("厚度：" + bean.getWeight() + "mm");
            //辅材
        } else if (bean.getType().equals(ShopCarFunction.getInstance().FU)) {
            tvBrand.setText("类别：" + bean.getClassName());
            tvStandard.setText("品牌：" + bean.getBrandName());
            tvLevel.setText("规格：" + bean.getGuigeName());
        }


        if (bean.getType().equals(ShopCarFunction.getInstance().YUAN)) {
            String price = "￥" + bean.getPrice()+"/吨";
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
            //辅材
        } else if (bean.getType().equals(ShopCarFunction.getInstance().FU)) {
            String price = "￥" + bean.getPrice() + "/" + bean.getNameUnit();
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
        }

    }
}
