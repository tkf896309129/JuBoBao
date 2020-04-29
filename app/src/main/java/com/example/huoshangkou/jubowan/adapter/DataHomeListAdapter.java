package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.CustomerHomeDataBean;
import com.example.huoshangkou.jubowan.inter.OnPositionCallBack;
import com.example.huoshangkou.jubowan.inter.StringCallBack;
import com.example.huoshangkou.jubowan.utils.LogUtils;
import com.example.huoshangkou.jubowan.utils.TwoPointUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：DataHomeListAdapter
 * 描述：
 * 创建时间：2019-09-09  13:26
 */

public class DataHomeListAdapter extends BaseAbstractAdapter<CustomerHomeDataBean.DBean.ResultBean.ItemsBean> {
    public DataHomeListAdapter(Context context, List<CustomerHomeDataBean.DBean.ResultBean.ItemsBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, final CustomerHomeDataBean.DBean.ResultBean.ItemsBean bean, final int position) {
        TextView tvType = holder.getView(R.id.tv_type);
        TextView tvNum = holder.getView(R.id.tv_nums);
        ImageView ivNum = holder.getView(R.id.iv_pic);
        double nums = Double.parseDouble(bean.getStatisValue());
        tvNum.setText(calPoint(nums, position));
        tvType.setText(bean.getSatatisTypeName());
        ivNum.setImageResource(bean.getImg());
    }

    public String calPoint(double nums, int position) {
        String showNum = "0";
        if (nums < 10000 && nums > 0) {
            showNum = (int) nums + "";
        } else if (nums > 9999 && nums < 99999999) {
            showNum = position == 1 ? (int)(nums / 10000) + "万" : TwoPointUtils.getInstance().getTwoPoint(nums / 10000) + "万";
        } else if (nums > 99999999) {
            showNum = TwoPointUtils.getInstance().getTwoPoint(nums / 100000000) + "亿";
        }
        return showNum;
    }
}
