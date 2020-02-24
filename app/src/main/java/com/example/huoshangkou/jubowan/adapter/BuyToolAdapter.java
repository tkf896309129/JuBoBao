package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.bean.BuyPjListBean;
import com.example.huoshangkou.jubowan.utils.GlideUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：BuyToolAdapter
 * 描述：
 * 创建时间：2017-11-22  14:55
 */

public class BuyToolAdapter extends BaseAdapter {
    private Context context;
    List<BuyPjListBean> list;

    public BuyToolAdapter(Context context, List<BuyPjListBean> list) {
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
        ViewHolder holder = null;
        View convertView = LayoutInflater.from(context).inflate(R.layout.item_buy_tools, null);
        if (holder == null) {
            holder = new ViewHolder();
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ImageView imgPic = (ImageView) convertView.findViewById(R.id.iv_pj_name);
        TextView tvName = (TextView) convertView.findViewById(R.id.tv_pj_name);
        tvName.setText(list.get(i).getClassTitle());
        GlideUtils.getInstance().displayImage(list.get(i).getPic(), context, imgPic);

        return convertView;
    }

    class ViewHolder {

    }
}
