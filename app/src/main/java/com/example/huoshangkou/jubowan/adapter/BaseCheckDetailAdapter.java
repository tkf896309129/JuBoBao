package com.example.huoshangkou.jubowan.adapter;

import android.content.Context;
import android.text.TextPaint;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huoshangkou.jubowan.R;
import com.example.huoshangkou.jubowan.activity.function.BaseCheckFunction;
import com.example.huoshangkou.jubowan.base.BaseAbstractAdapter;
import com.example.huoshangkou.jubowan.base.ViewHolder;
import com.example.huoshangkou.jubowan.bean.ApproveListBean;
import com.example.huoshangkou.jubowan.bean.BaseCheckBean;
import com.example.huoshangkou.jubowan.utils.DateUtils;
import com.example.huoshangkou.jubowan.utils.MoneyUtils;
import com.example.huoshangkou.jubowan.utils.StringUtils;
import com.example.huoshangkou.jubowan.utils.ToastUtils;

import java.util.List;

/**
 * 作者：唐先生
 * 包名：com.example.huoshangkou.jubowan.adapter
 * 类名：BaseCheckDetailAdapter
 * 描述：
 * 创建时间：2019-12-09  14:21
 */

public class BaseCheckDetailAdapter extends BaseAbstractAdapter<BaseCheckBean> {
    public BaseCheckDetailAdapter(Context context, List<BaseCheckBean> listData, int resId) {
        super(context, listData, resId);
    }

    @Override
    public void convert(ViewHolder holder, BaseCheckBean bean, int position) {
        TextView tvHint = holder.getView(R.id.tv_hint);
        TextView tvContent = holder.getView(R.id.tv_content);
        RelativeLayout llDetail = holder.getView(R.id.rl_detail);
        if (bean.isHide()) {
            llDetail.setVisibility(View.GONE);
        } else {
            llDetail.setVisibility(View.VISIBLE);
        }
        tvHint.setText(bean.getHintType());

        if (bean.getHintType().contains("日期")) {
            if (StringUtils.isNoEmpty(bean.getContent()) && bean.getContent().contains("Date")) {
                tvContent.setText(DateUtils.getFormDesData(bean.getContent()));
            } else {
                tvContent.setText(StringUtils.getNoEmptyStr(bean.getContent()));
            }
        } else if (bean.getHintType().contains("发票")) {
            switch (bean.getContent()) {
                case "无发票":
                case "0":
                    tvContent.setText("无发票");
                    break;
                case "有发票":
                case "1":
                    tvContent.setText("有发票");
                    break;
                default:
                    tvContent.setText("无发票");
                    break;
            }
        } else if (bean.getEditType() == 1) {
            tvContent.setText(MoneyUtils.getInstance().getFormPrice(bean.getContent()));
        } else {
            tvContent.setText(StringUtils.getNoEmptyStr(bean.getContent()));
        }
        //标题
        if (bean.getType() == 6) {
            tvHint.setTextSize(16);
            TextPaint tp = tvHint.getPaint();
            tp.setFakeBoldText(true);
            tvContent.setText("");
        } else {
            tvHint.setTextSize(14);
            TextPaint tp = tvHint.getPaint();
            tp.setFakeBoldText(false);
        }

    }
}
