package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.SearchToolListBean;
import com.example.huoshangkou.jubowan.utils.BlueTxtUtils;
import com.example.huoshangkou.jubowan.utils.GlideUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：ToolSearchAdapter
 * 描述：
 * 创建时间：2017-06-19  16:52
 */

public class ToolSearchAdapter extends BaseAbstractAdapter<SearchToolListBean> {
    public ToolSearchAdapter(Context context, List<SearchToolListBean> listData, int resId) {
        super(context, listData, resId);

    }

    @Override
    public void convert(ViewHolder holder, SearchToolListBean bean, int position) {
        ImageView imgTool = holder.getView(R.id.iv_search_tool);
        TextView tvType = holder.getView(R.id.tv_type);
        TextView tvProduct = holder.getView(R.id.tv_product);
        TextView tvAddress = holder.getView(R.id.tv_address);
        TextView tvPrice = holder.getView(R.id.tv_price);
        
        GlideUtils.getInstance().displayImage(bean.getPics(), context, imgTool);
        tvType.setText("类别：" + bean.getMaintainName());
        tvProduct.setText("品牌：" + bean.getBrandName());
        tvAddress.setText("出厂日期：" + bean.getTime());

        if (bean.getPrices() == 0.0) {
            tvPrice.setText(Html.fromHtml("参考价：" +BlueTxtUtils.getInstance().getBlueTxt("面议")));
        } else {

            tvPrice.setText(Html.fromHtml("参考价：" +BlueTxtUtils.getInstance().getBlueTxt("￥"+bean.getPrices()+"") + "元"));
        }

    }
}
