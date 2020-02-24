package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.PjListBean;
import com.example.huoshangkou.jubowan.utils.GlideUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：PjDetailListAdapter
 * 描述：
 * 创建时间：2017-11-28  14:02
 */

public class PjDetailListAdapter extends BaseAdapter {

    private Context context;
    List<PjListBean> list;

    public PjDetailListAdapter(Context context, List<PjListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View convertView = LayoutInflater.from(context).inflate(R.layout.item_tool_desc, null);
        ViewHolder holder = null;
        if (holder == null) {
            holder = new ViewHolder();
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ImageView imgPic= (ImageView) convertView.findViewById(R.id.iv_tool_desc);
        GlideUtils.getInstance().displayImage(list.get(i).getPic(),context,imgPic);

        TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
        tvName.setText(list.get(i).getModelTitle());
        TextView tvBy = (TextView) convertView.findViewById(R.id.tv_by);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
        tvPrice.setText("￥"+list.get(i).getPriceRange());


        return convertView;
    }


    class ViewHolder {

    }
}
