package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.ShopCarFunction;
import com.example.huoshangkou.jubowan.bean.ShopCarListBean;
import com.example.huoshangkou.jubowan.bean.ShopChildBean;
import com.example.huoshangkou.jubowan.bean.ShopGroupBean;
import com.example.huoshangkou.jubowan.constant.OrderTypeConstant;
import com.example.huoshangkou.jubowan.inter.OnShopCarAddDecreaseCallBack;
import com.example.huoshangkou.jubowan.inter.OnShopCarChooseCallBack;
import com.example.huoshangkou.jubowan.inter.ShopCarCloseClick;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;
import com.example.huoshangkou.jubowan.view.AnimatedExpandableListView;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：ShopAnimAdapter
 * 描述：
 * 创建时间：2017-03-09  14:24
 */

public class ShopAnimAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {

    //父布局数据
    private List<ShopGroupBean> list_group;
    //子布局数据
    private List<List<ShopCarListBean>> list_child;
    //上下文
    Context context;
    //接口回调
    ShopCarCloseClick closeClick;
    //购物车选择点击事件
    OnShopCarChooseCallBack chooseCallBack;
    //购物车添加、减少回调接口
    OnShopCarAddDecreaseCallBack decreaseCallBack;

    public ShopAnimAdapter(List<ShopGroupBean> list_group, List<List<ShopCarListBean>> list_child,
                           Context context, ShopCarCloseClick closeClick) {
        this.list_group = list_group;
        this.list_child = list_child;
        this.context = context;
        this.closeClick = closeClick;
    }


    @Override
    public View getRealChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View childView = LayoutInflater.from(context).inflate(R.layout.shop_car_second, parent, false);

        ImageView imgAdd = (ImageView) childView.findViewById(R.id.iv_add);
        ImageView imgDecrease = (ImageView) childView.findViewById(R.id.iv_decrease);
        final TextView tvNum = (TextView) childView.findViewById(R.id.tv_num);
        final TextView tvPrice = (TextView) childView.findViewById(R.id.tv_price);
        ImageView imgCheck = (ImageView) childView.findViewById(R.id.iv_check);
        LinearLayout llClick = (LinearLayout) childView.findViewById(R.id.ll_check);
        ImageView imgPic = (ImageView) childView.findViewById(R.id.iv_car_pic);


        //原：品牌   辅：类别
        TextView tvBrand = (TextView) childView.findViewById(R.id.tv_brand);
        //原：级别   辅：品牌
        TextView tvLevel = (TextView) childView.findViewById(R.id.tv_level);
        //原：规格   辅：规格
        TextView tvStandard = (TextView) childView.findViewById(R.id.tv_standard);
        //原：类别
        TextView tvClass = (TextView) childView.findViewById(R.id.tv_class);
        //原：厚度
        TextView tvThick = (TextView) childView.findViewById(R.id.tv_thick);

        final ShopCarListBean shopCarListBean = list_child.get(groupPosition).get(childPosition);

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
        }else {
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
                decreaseCallBack.onClickPosition(shopCarListBean.getType(), shopCarListBean.getID(), Double.parseDouble(shopCarListBean.getToNum()), groupPosition, childPosition);
            }
        });

        llClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseCallBack.onChildClick(groupPosition, childPosition);
            }
        });

        //增加点击
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decreaseCallBack.onAddCar(shopCarListBean.getType(), shopCarListBean.getID(), OrderTypeConstant.getInstance().ADD_CAR, groupPosition, childPosition);
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
                decreaseCallBack.onAddCar(shopCarListBean.getType(), shopCarListBean.getID(), OrderTypeConstant.getInstance().DES_CAR, groupPosition, childPosition);
            }
        });

        //设置是否被选中
        if (list_child.get(groupPosition).get(childPosition).isCheck()) {
            imgCheck.setImageResource(R.mipmap.gouxuany_icon_on);
        } else {
            imgCheck.setImageResource(R.mipmap.gouxuany_icon_off);
        }

        if (shopCarListBean.getType().equals(ShopCarFunction.getInstance().YUAN)) {
            String price = "￥" + shopCarListBean.getPrice() + "/" + shopCarListBean.getNameUnit();
            int colorPosition = (price).indexOf(".");
            int linePosition = price.indexOf("/");
            if (colorPosition >= 0 && linePosition >= 0) {
                SpannableStringBuilder spannableString = new SpannableStringBuilder();
                spannableString.append(price);
                spannableString.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.main_tab_blue)), 1, linePosition, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

                //字体大小
                AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(28);
                AbsoluteSizeSpan sizeSpan2 = new AbsoluteSizeSpan(28);
                spannableString.setSpan(sizeSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                spannableString.setSpan(sizeSpan2, colorPosition, linePosition, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

                tvPrice.setText(spannableString);
            } else {
                tvPrice.setText("￥" + shopCarListBean.getPrice());
            }
            //辅材
        } else if (shopCarListBean.getType().equals(ShopCarFunction.getInstance().FU)) {
            String price = "￥" + shopCarListBean.getPrice() + "/" + shopCarListBean.getNameUnit();
            int colorPosition = (price).indexOf(".");
            int linePosition = price.indexOf("/");
            if (colorPosition >= 0 && linePosition >= 0) {
                SpannableStringBuilder spannableString = new SpannableStringBuilder();
                spannableString.append(price);
                spannableString.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.main_tab_blue)), 1, linePosition, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

                //字体大小
                AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(28);
                AbsoluteSizeSpan sizeSpan2 = new AbsoluteSizeSpan(28);
                spannableString.setSpan(sizeSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                spannableString.setSpan(sizeSpan2, colorPosition, linePosition, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

                tvPrice.setText(spannableString);
            } else {
                tvPrice.setText("￥" + shopCarListBean.getPrice() + "/" + shopCarListBean.getNameUnit());
            }
        }
        return childView;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return list_child.get(groupPosition) == null ? 0 : list_child.get(groupPosition).size();
    }

    @Override
    public int getGroupCount() {
        return list_group == null ? 0 : list_group.size();
    }

    @Override
    public Object getGroup(int i) {
        return list_group.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return list_child.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {
        View groupView = LayoutInflater.from(context).inflate(R.layout.shop_car_first, viewGroup, false);
        final TextView tvClose = (TextView) groupView.findViewById(R.id.tv_close);
        final TextView tvDelete = (TextView) groupView.findViewById(R.id.tv_delete);
        LinearLayout llClick = (LinearLayout) groupView.findViewById(R.id.ll_car_check);
        ImageView imgCar = (ImageView) groupView.findViewById(R.id.iv_car_check);
        TextView tvTitle = (TextView) groupView.findViewById(R.id.tv_group_title);

        tvTitle.setText(list_group.get(i).getTitle());


        llClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseCallBack.onGroupClick(i);
            }
        });

        tvClose.setText(list_group.get(i).getStr());

        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeClick.onCloseClick(i, tvClose);
            }
        });

        //删除订单
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decreaseCallBack.deleteOrder(list_group.get(i).getType(), i, "");
            }
        });


        //设置是否被选中
        if (list_group.get(i).isCheck()) {
            imgCar.setImageResource(R.mipmap.gouxuany_icon_on);
        } else {
            imgCar.setImageResource(R.mipmap.gouxuany_icon_off);
        }

        return groupView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    public void setChooseCallBack(OnShopCarChooseCallBack chooseCallBack) {
        this.chooseCallBack = chooseCallBack;
    }

    public void setDecreaseCallBack(OnShopCarAddDecreaseCallBack decreaseCallBack) {
        this.decreaseCallBack = decreaseCallBack;
    }
}
