package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.MessageTypeDetailListBean;
import com.example.huoshangkou.jubowan.constant.OrderTypeConstant;
import com.example.huoshangkou.jubowan.utils.GlideUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：MessageItemAdapter
 * 描述：
 * 创建时间：2017-05-12  15:22
 */

public class MessageItemAdapter extends BaseAbstractAdapter<MessageTypeDetailListBean> {

    public MessageItemAdapter(Context context, List<MessageTypeDetailListBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, MessageTypeDetailListBean bean, int position) {
        TextView tvContent = holder.getView(R.id.tv_content);
        TextView tvPrice = holder.getView(R.id.tv_price);
        ImageView imgPic = holder.getView(R.id.iv_order_pic);
        if (StringUtils.isNoEmpty(bean.getPic())) {
            GlideUtils.getInstance().displayImage(bean.getPic(), context, imgPic);
        } else if (StringUtils.isNoEmpty(bean.getPics())) {
            GlideUtils.getInstance().displayImage(bean.getPics(), context, imgPic);
        }

        if (bean.getType().equals(OrderTypeConstant.getInstance().WX)) {
            tvContent.setText("商品详情："+getFormMessage(bean.getBrandName())+getFormMessage(bean.getMaintainName()));
            tvPrice.setText("故障描述：" + bean.getDescript());
        } else {
            tvContent.setText("商品详情："+getFormMessage(bean.getBrandName()) + getFormMessage(bean.getClassName()) + getFormMessage(bean.getLevelName())
                    + getFormMessage(bean.getWeight()) + getFormMessage(bean.getXy()) + getFormMessage(bean.getMaintainName()));
            tvPrice.setText("商品价格：￥" + bean.getOnePrice());
        }
    }

    private String getFormMessage(String message) {
        if (StringUtils.isNoEmpty(message)) {
            message += "/";
        } else {
            message = "";
        }
        return message;
    }
}
