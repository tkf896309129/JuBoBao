package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.BaoReqBean;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：BaoRequirAdapter
 * 描述：
 * 创建时间：2019-05-15  15:21
 */

public class BaoRequirAdapter extends BaseAbstractAdapter<BaoReqBean> {

    public BaoRequirAdapter(Context context, List<BaoReqBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, BaoReqBean bean, int position) {
        TextView tvBaoReq = holder.getView(R.id.tv_bao_requir);
        ImageView imgBaoReq = holder.getView(R.id.iv_bao_check);
        tvBaoReq.setText(bean.getContent());
        if (bean.isCheck()) {
            imgBaoReq.setImageResource(R.mipmap.gouxuan_icon);
            imgBaoReq.setVisibility(View.VISIBLE);
        } else {
            imgBaoReq.setVisibility(View.GONE);
        }


    }
}
